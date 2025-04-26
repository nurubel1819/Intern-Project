import java.util.Scanner;

public class Java_Regex {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String pattern = "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
        while (sc.hasNext()) {
            String line = sc.nextLine();
            if(line.matches(pattern)) System.out.println("true");
            else System.out.println("false");
        }
    }
}
