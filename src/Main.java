import task1.Math;
import task1.Task1;
import task2.Container;
import task2.Task2;
import task3.Serializator;
import task3.User;

public class Main {
    public static void main(String[] args) {
        task1();
        task2();
        task3();
    }

    public static void task1() {
        System.out.println("Starting task1");
        System.out.println("--------------------------");
        Task1.executeMethods(new Math());
        System.out.println();
    }

    public static void task2() {
        Container container = new Container();
        System.out.println("Starting task2");
        System.out.println("--------------------------");
        Task2.save(new Container());
        System.out.println("Text saved");
        System.out.println("Checking result...");
        System.out.println("Checking is " + Task2.check(container));
        System.out.println();
    }
    public static void task3() {
        System.out.println("Starting task3");
        System.out.println("--------------------------");
        Serializator serializator = new Serializator();
        User user = new User("Alex", 13, "email@domain.com", "Comment is here");
        serializator.serialize(user);
        User userAfterDeserialize = (User)serializator.deserialize();
        if (user.equals(userAfterDeserialize)) {
            System.out.println("Objects are equal!");
        } else {
            System.out.println("Error! Objects don't equal");
        }
    }
}