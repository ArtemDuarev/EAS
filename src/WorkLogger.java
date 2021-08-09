import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WorkLogger {
    private final String filePath;
    private PrintStream printStream;

    public WorkLogger(String filePath) throws FileNotFoundException {
        this.filePath = filePath;
        printStream = new PrintStream(filePath);
    }

    public boolean writeToFile(int id, boolean isEntry, LocalDateTime timeStamp){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss (dd.MM.yyyy)");
        String time = dtf.format(timeStamp);
        try{
            printStream.println(id + "," + (isEntry ? "start" : "finish") + "," + time);
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
