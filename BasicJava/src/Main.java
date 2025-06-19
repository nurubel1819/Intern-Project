import implementation.MyInterfaceImplementation;
import interfaces.MyInterface;
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World");
        MyInterface ob = new MyInterfaceImplementation();
        MyInterfaceImplementation myInterfaceImplementation = new MyInterfaceImplementation();
        myInterfaceImplementation.myNumber();
    }
}