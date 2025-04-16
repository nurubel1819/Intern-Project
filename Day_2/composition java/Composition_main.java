package Day_2;

public class Composition_main {
    public static void main(String[] args) {
        Human nasir = new Human("Nasir");
        Cat mini = new Cat("Mini",nasir);
        System.out.println(mini);
    }
}
