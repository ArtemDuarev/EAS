import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Person {

    private String name;
    private String surname;
    private LocalDate birthDate;
    private String phoneNumber;
    private String address;

    public Person(String name, String surname, LocalDate birthDate, String phoneNumber, String address) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public Person(String personInfo) {
        String[] employeeInfo = personInfo.split(",");
        name = employeeInfo[0];
        surname = employeeInfo[1];
        birthDate = LocalDate.parse(employeeInfo[2]);
        phoneNumber = employeeInfo[3];
        address = employeeInfo[4];
    }

    public String getTableData(){
        int stringNameLengthFactor = 7,
                stringSurnameLengthFactor = 7,
                stringPhoneNumberLengthFactor = 8,
                stringAddressLengthFactor = 25,
                stringAgeLengthFactor = 6,
                scale = 2;

        return " " +
                (name + getDelimiter(scale * stringNameLengthFactor)).substring(0, scale * stringNameLengthFactor) +
                (surname + getDelimiter(scale * stringSurnameLengthFactor)).substring(0, scale * stringSurnameLengthFactor) +
                (getBirthDate() + getDelimiter(scale * stringAgeLengthFactor)).substring(0, scale * stringAgeLengthFactor)  +
                (phoneNumber + getDelimiter(scale * stringPhoneNumberLengthFactor)).substring(0, scale * stringPhoneNumberLengthFactor)  +
                (address + getDelimiter(scale * stringAddressLengthFactor)).substring(0, scale * stringAddressLengthFactor);
    }

    public String getPersonInfo(){
        return name + "," + surname + "," + birthDate + "," + phoneNumber + "," + address;
    }

    private String getDelimiter(int length){
        String delimiter = "";
        for(int i = 0; i < length; i++){
            delimiter += " ";
        }
        return delimiter;
    }

    public String getDataByKey() {
        int key;
        String personDataKey = "Введите код данных человека (1 = Имя, 2 = Фамилия, " +
                "3 = Дата рождения, 4 = Телефон, 5 = Адрес)",
                errorMassage = "Вы не правы, читайте внимательно. " + personDataKey;
        while (true){
            try{
                key = Integer.parseInt(new Reader().read(personDataKey));
                if(key >= 1 && key <= 5)
                    break;
            }
            catch (Exception e){
                Writer.print(errorMassage + e.getMessage());
                continue;
            }
        }
        if (key == 1) {
            return getName();
        }
        else if (key == 2) {
            return getSurname();
        }
        else if (key == 3) {
            return getBirthDate();
        }
        else if (key == 4)
            return getPhoneNumber();
        else
            return getAddress();
    }

    public static Person consoleRead(Reader reader){
        return new Person(reader.read("Введите имя"),
                reader.read("Введите фамилию"),
                LocalDate.of(Integer.parseInt(reader.read("Введите год рождения")),
                        Integer.parseInt(reader.read("Введите месяц рождения")),
                        Integer.parseInt(reader.read("Введите день рождения"))),
                reader.read("Введите номер телефона"),
                reader.read("Введите адрес"));
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getBirthDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return dtf.format(birthDate);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

}

    /*public Person(ArrayList<String> fieldsList){
        id++;
        name = fieldsList.get(0);
        surname = fieldsList.get(1);
        age = Integer.parseInt(fieldsList.get(2));
        phoneNumber = fieldsList.get(3);
        address = fieldsList.get(4);
    }*/

// переделать if на switch employee_storage.csv