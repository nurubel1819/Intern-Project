import java.util.Scanner;

public class Java_Substring {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int a,b;
        a = sc.nextInt();
        b = sc.nextInt();
        String sub_string = s.substring(a,b);
        System.out.println(sub_string);
    }
}
