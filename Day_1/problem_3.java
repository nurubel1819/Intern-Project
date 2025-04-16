package Day_1;

import java.util.Scanner;

public class problem_3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string :");
        String string;
        string = scanner.nextLine();
        String my_string="";
        for(int i=0;i<string.length();i++)
        {
            char ch = string.charAt(i);
            int ascii = (int)ch;
            if((ascii>=65 && ascii<=90)||(ascii>=97 && ascii<=122)) my_string = my_string+ch;
        }
        System.out.println("Remove non-alphanumeric characters = "+my_string);
        my_string = my_string.toLowerCase();
        System.out.println("Lowercase String = "+my_string);

        //reverse string
        String reverse_string="";
        for(int i=my_string.length()-1;i>=0;i--)
        {
            reverse_string = reverse_string+my_string.charAt(i);
        }
        System.out.println("Reverse String = "+reverse_string);

        if(my_string.equals(reverse_string)) System.out.println("True");
        else System.out.println("False");
    }
}
