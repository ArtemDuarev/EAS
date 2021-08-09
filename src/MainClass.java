import java.time.LocalDateTime;

public class MainClass {
    public static void main(String[] args) {
        //ConsoleEmployeeStorageInteractor consoleEmployee = new ConsoleEmployeeStorageInteractor();
        WorkLogger workLogger = null;
        try {
            workLogger = new WorkLogger("LogFile.txt");
        }
        catch (Exception e){
            Writer.print(e.getMessage());
        }

        workLogger.writeToFile(1, true, LocalDateTime.now().minusMinutes(3));
        workLogger.writeToFile(2, true, LocalDateTime.now().minusMinutes(2));
        workLogger.writeToFile(2, false, LocalDateTime.now().plusHours(2));
        workLogger.writeToFile(0, true, LocalDateTime.now().plusHours(3));
        workLogger.writeToFile(1, false, LocalDateTime.now().plusHours(4));
        workLogger.writeToFile(0, false, LocalDateTime.now().plusHours(5));
    }
}