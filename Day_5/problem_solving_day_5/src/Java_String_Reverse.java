import java.util.Scanner;

public class Java_String_Reverse {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String reverse_string = new StringBuilder(s).reverse().toString();
        if(s.equals(reverse_string)) System.out.println("Yes");
        else System.out.println("No");
    }
}
