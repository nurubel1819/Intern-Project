package Day_1;

public class problem_0 {
    public static void main(String[] args) {
        int rows = 5;
        for (int i = 1; i <= rows; ++i) {
            for (int j = 1; j <= i; ++j) {
                System.out.print("* ");
            }
            System.out.println();
        }
        System.out.println("--------------------------------------");
        for(int i=rows;i>=0;i--)
        {
            for(int j=i;j>=0;j--)
            {
                System.out.print("* ");
            }
            System.out.println();
        }
        System.out.println("---------------------------------------");
        for(int i=0;i<rows;i++)
        {
            for(int j=i;j<rows;j++)
            {
                System.out.print(" ");
            }
            for(int j=0;j<=i;j++)
            {
                System.out.print("* ");
            }
            System.out.println();
        }
    }
}
