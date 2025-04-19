import java.util.Scanner;
import java.util.regex.Pattern;

public class Pattern_Syntax_Checker {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        while (n-->0) {
            String s = sc.nextLine();
            try {
                Pattern p = Pattern.compile(s);
                System.out.println("Valid");
            } catch (Exception e) {
                System.out.println("Invalid");
            }
        }
    }
}
