import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WorkLogger {
    private LocalDateTime startWorkTime;
    private LocalDateTime finishWorkTime;
    private final String filePath;
    private EmployeeStorage storage;


    public WorkLogger(LocalDateTime startWorkTime, LocalDateTime finishWorkTime, String filePath, EmployeeStorage storage) {
        this.startWorkTime = startWorkTime;
        this.finishWorkTime = finishWorkTime;
        this.filePath = filePath;
        this.storage = storage;
    }


    public void writeLogToFile(boolean inWork) throws IOException {
        PrintStream printStream = new PrintStream(filePath);
        for(int i = 0; i < storage.getEmployeeQuantity(); i++) {
            printStream.println(getLogInfo(inWork, i));
        }

    }

    public String getLogInfo(boolean inWork, int count){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HHч:mmм (dd.MM.yyyy)");
        String startTime = dtf.format(startWorkTime),
                finishTime = dtf.format(finishWorkTime);
        if(inWork)
            return "Работник №:" + storage.getEmployeeId(count) + " вошёл в " + startTime;
        else
            return "Работник №:" + storage.getEmployeeId(count) + " вышел в " + finishTime;
    }


    //написать класс который пишет в файл входы выходы в формате id дата время, имя файла(лога) хранить в константе, константу сетапить конструктором
    //подумать + и - того что этот класс static, сделать комит в отдельную ветку
}
