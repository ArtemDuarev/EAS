import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ConsoleEmployeeStorageInteractor {
    private EmployeeStorage storage;

    public ConsoleEmployeeStorageInteractor(){
        storage = initializeStorage();
    }

    /*public ConsoleEmployeeStorageInteractor(EmployeeStorage storage){
        this.storage = storage;
    }*/

    private EmployeeStorage initializeStorage(){
        String importKey = "i",
                createKey = "n";

        while (true) {
            String createOrReadMessage = new Reader().read("Введите " + "\"" + importKey + "\"" +
                    " для экспорта из файла или " + "\"" + createKey + "\"" + " для создания нового");

            if (createOrReadMessage.equalsIgnoreCase(importKey)) {
                try {
                    this.storage = new EmployeeStorage();
                    storage.readStorageFromFile(new Reader().read("Введите имя файла"), false);//создать новый employeestorage и вернуть его
                    interact();
                } catch (Exception e) {
                    Writer.print("Ошибка, попробуйте заново! Ошибка: " + e.fillInStackTrace());
                    continue;
                }
            }
            else if (createOrReadMessage.equalsIgnoreCase(createKey)) {
                try {
                    this.storage = new EmployeeStorage(Integer.parseInt(new Reader().read("Введите количество людей:")));
                    interact();
                }
                catch (NumberFormatException e) {
                    Writer.print("Введите коректное число! Ошибка: " + e.fillInStackTrace());
                    continue;
                }
            }
            else {
                Writer.print("Неверный ключ. Введите заново!");
                continue;
            }
            return storage;
        }
    }

    public void interact(){
        Reader reader = new Reader();
        final String welcomeMessage = "Введите команду(для справки введите man)",
                helpKey = "man",
                tableKeyWord = "table",
                singleKeyWord = "single",
                removeKeyWord = "remove",
                addKeyWord = "add",
                importKeyWord = "import",
                exportKeyWord = "export",
                searchKeyWord = "search",
                exitKeyWord = "exit",
                help = tableKeyWord + " - вывод хранилища в виде таблицы\n" +
                        singleKeyWord + " N - вывод данных сотрудника под номером N\n" +
                        removeKeyWord + " N - удаление сотрудника под номером N\n" +
                        addKeyWord + " - добавление нового сотрудника\n" +
                        importKeyWord + " [o], _path - импорт хранилища из файла(o - перезаписать хранилище)\n" +
                        exportKeyWord + " - экспорт хранилища в файл\n" +
                        searchKeyWord + " - поиск человека\n" +
                        exitKeyWord + " - выход из программы";
        boolean loopVariable = true;

        while(loopVariable) {
            String keyword = reader.read(welcomeMessage);
            switch (keyword.split(" ")[0]) {
                case helpKey:
                    System.out.println(help);
                    break;
                case tableKeyWord:
                    showDataTable();
                    break;
                case singleKeyWord:
                    try {
                        Writer.print(storage.getEmployeeData(Integer.parseInt(keyword.split(" ")[1])));
                    } catch (Exception e) {
                        Writer.print("неверный формат команды " + singleKeyWord + "\n" + e.getMessage());
                        continue;
                    }
                    break;
                case removeKeyWord:
                    try {
                        storage.removeEmployee(Integer.parseInt(keyword.split(" ")[1]));
                    }
                    catch (Exception e){
                        System.out.println("Неверный формат");
                        continue;
                    }
                    break;
                case addKeyWord:
                    storage.addEmployee(Person.consoleRead(new Reader()));
                    break;
                case importKeyWord:
                    String[] words = keyword.split(" ");
                    if (words.length == 2) {
                        try {
                            storage.readStorageFromFile(words[1], false);
                        } catch (Exception e) {
                            System.out.println("Ошибка, попробуйте заново!");
                            continue;
                        }
                    }
                    break;
                case exportKeyWord:
                    try {
                        storage.writeStorageToFile();
                    } catch (IOException e) {
                        System.out.println("Ошибка, попробуйте заново!");
                        continue;
                    }
                    break;

                case searchKeyWord:
                    searchEmployee();
                    break;

                case exitKeyWord:
                    loopVariable = false;
                    break;
            }
        }
    }

    public void searchEmployee(){
        String welcomeMessage = "Что будем искать?",
                searchCriteriaRaw = new Reader().read(welcomeMessage);
        String[] searchCriteria = searchCriteriaRaw.split(" ");
        HashMap<Integer,Person> resultList = new HashMap<>();

        for(Map.Entry<Integer,Person> pair : storage.employees.entrySet()){
            String employeeInfo = pair.getKey() + " " + pair.getValue().getPersonInfo();
            boolean isEmployeeResult = false;
            for(int j = 0; j < searchCriteria.length; j++){
                isEmployeeResult = employeeInfo.contains(searchCriteria[j]);
                if(!isEmployeeResult)
                    break;
            }
            if(isEmployeeResult)
                resultList.put(pair.getKey(), pair.getValue());
        }
        showDataTable(resultList);
    }


    public void showDataTable(){
        Writer.print("TABLE");
        for(Map.Entry<Integer,Person> pair : storage.employees.entrySet()){
            Writer.print("№" + pair.getKey() + pair.getValue().getTableData());
        }
    }
    public void showDataTable(HashMap<Integer,Person> resultList){
        Writer.print("TABLE");
        for(Map.Entry<Integer,Person> pair : resultList.entrySet()){
            Writer.print("№" + pair.getKey() + pair.getValue().getTableData());
        }
    }

}
//solid прочитать, переделать таблицу employee_storage.csv
//N1 вывести результаты поиска в форме таблицы как в showdatatable с номером
//N2 реализовать поиск не только в данных employee но и в его номере(id),
//git, javarush
