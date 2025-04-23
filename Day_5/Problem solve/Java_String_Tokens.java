import java.util.Scanner;

public class Java_String_Tokens {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        sc.close();
        // Remove leading space
        str = str.trim();
        if(str.isEmpty()) System.out.println(0);
        else {
            String[] tokens = str.split("[^a-zA-Z]+");
            System.out.println(tokens.length);
            for (String token : tokens) {
                System.out.println(token);
            }
        }
    }
}
