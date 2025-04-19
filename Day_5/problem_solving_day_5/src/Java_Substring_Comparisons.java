import java.util.Scanner;

public class Java_Substring_Comparisons {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int n = sc.nextInt();
        String big="" ,small="";
        for(int i=0;i+n<str.length()+1;i++) {
            String substr = str.substring(i,i+n);
            if(i==0)
            {
                big = substr;
                small = substr;
            }
            else {
                if(substr.compareTo(big) > 0) big = substr;
                //else big = substr;
                if(substr.compareTo(small) < 0) small = substr;
            }
        }
        System.out.println(small+"\n"+big);
    }
}
