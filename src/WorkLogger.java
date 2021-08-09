import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WorkLogger {
    private final String filePath;

    public WorkLogger(String filePath) {
        this.filePath = filePath;
    }

    public boolean writeToFile(int id, boolean isEntry, LocalDateTime timeStamp){
        try{
            PrintStream printStream = new PrintStream(filePath);
            printStream.println(id + "," + (isEntry ? "start" : "finish") + "," + timeStamp);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }
    //текущую ветку с комитами нужно переименовать в logger feature for separate employee
    //новый класс нужно сделать stash(прибережение)
    //переключиться на ветку develop и создать от неё ветку logger feature в которую распаковать stash
}
