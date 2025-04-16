package Day_1;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class problem_2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int total_person;
        total_person = Integer.parseInt(scanner.nextLine());

        Map<String,String> map = new HashMap<>();

        for(int i=0;i<total_person;i++)
        {
            String name;
            name = scanner.nextLine();
            String phone;
            phone = scanner.nextLine();
            map.put(name,phone);
        }
        while(true)
        {
            String name;
            name = scanner.nextLine();
            if(map.containsKey(name))
            {
                System.out.println(name+"="+map.get(name));
            }
            else System.out.println("Not found");
        }

    }
}
