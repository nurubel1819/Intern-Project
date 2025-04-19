import java.util.Arrays;
import java.util.Scanner;

public class Java_Anagrams {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String first = sc.nextLine();
        String second = sc.nextLine();
        first = first.toLowerCase();
        second = second.toLowerCase();
        char[] firstArray = first.toCharArray();
        char[] secondArray = second.toCharArray();
        if(firstArray.length != secondArray.length) System.out.println("Not Anagrams");
        else {
            Arrays.sort(firstArray);
            Arrays.sort(secondArray);
            if(Arrays.equals(firstArray, secondArray)) System.out.println("Anagrams");
            else System.out.println("Not Anagrams");
        }
    }
}
