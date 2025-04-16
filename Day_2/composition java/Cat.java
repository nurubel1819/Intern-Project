package Day_2;

public class Cat {
    private String name;
    //Aat belongs to a Human
    private Human owner;

    public Cat(String name, Human owner)
    {
        this.name = name;
        this.owner = owner;
    }

    public String toString()
    {
        return String.format("My name is %s I belong to %s", name,owner);
    }
}
