package main;

import classes.Controller;
import classes.Repository;

public class Main {

    public static void main(String[] args) {
        Repository repository = new Repository();
        Controller controller = new Controller(repository);

        controller.save("asa", 2);
        System.out.println(controller.save("asa", 0));

    }


}
