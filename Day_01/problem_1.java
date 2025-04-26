package Day_1;

import java.util.Scanner;

public class problem_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int row,col;
        System.out.print("Enter number of Rows : ");
        row = scanner.nextInt();
        System.out.print("Enter number of columns : ");
        col = scanner.nextInt();

        int[][] matrix = new int[row][col];
        //input matrix
        System.out.println("Enter matrix element seperated by spaces");
        for(int i=0;i<row;i++)
        {
            for(int j=0;j<col;j++)
            {
                matrix[i][j] = scanner.nextInt();
            }
        }
        //print matrix
        for(int i=0;i<row;i++)
        {
            for(int j=0;j<col;j++)
            {
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }

        // calculation
        int sum=0;
        for(int i=0;i<row;i++)
        {
            for(int j=0;j<col;j++)
            {
                if(i+2>row-1 || j+2>col-1) break;
                // small matrix
                int s=0;
                int s_row = i+2;
                int s_col = j+2;
                for(int m=i;m<=s_row;m++)
                {
                    for(int n=j;n<=s_col;n++)
                    {
                        if(m==i+1 && n==j) continue;
                        else if(m==i+1 && n==j+2) continue;
                        s+=matrix[m][n];
                    }
                }
                //replace big sum
                sum = Math.max(sum,s);
            }
        }
        System.out.println("Output is : "+sum);
    }
}
