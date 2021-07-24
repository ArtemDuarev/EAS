import java.util.Scanner;

public class Reader {
    public String read(String message) {
        Scanner scanner = new Scanner(System.in);
        Writer.print(message);
        return scanner.nextLine();
    }
}