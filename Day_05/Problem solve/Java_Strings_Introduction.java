import java.util.Scanner;

public class Java_Strings_Introduction {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String fist = sc.nextLine();
        String second = sc.nextLine();
        System.out.println(fist.length() + second.length());
        if(fist.compareTo(second) > 0) System.out.println("Yes");
        else System.out.println("No");
        System.out.println(capitalizeFirstLetter(fist)+" "+capitalizeFirstLetter(second));
    }
    public static String capitalizeFirstLetter(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
