import java.util.ArrayList;

public class Writer {
    public static void print(String s) {
        System.out.println(s);
    }
    public static void print(ArrayList<Person> s) {
        s.stream().forEach(p -> System.out.println(p.getPersonInfo()));
    }
}