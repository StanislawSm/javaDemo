package pl.user.pt.java.lab2.main;
import pl.user.pt.java.lab2.classes.Computer;
import pl.user.pt.java.lab2.classes.DestinationResource;
import pl.user.pt.java.lab2.classes.SourceResource;

import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //initialize all variables
        int numberOfThreads = Integer.parseInt(args[0]);
        SourceResource sourceResource = new SourceResource();
        DestinationResource destinationResource = new DestinationResource();
        Random rand = new Random();
        LinkedList<Thread> threadsList = new LinkedList<Thread>();

        for (int i = 0; i <= numberOfThreads; i++){
            threadsList.add(new Thread(new Computer(sourceResource, destinationResource)));
        }

        for (Thread thread : threadsList){
            thread.start();
        }

        Scanner in = new Scanner(System.in);
        String input = in.nextLine();

        while (!input.equals("q")){
            Integer inputInt;
            try {
                inputInt = Integer.parseInt(input);
            } catch (NumberFormatException ex){
                System.out.println();
                inputInt = rand.nextInt(10000);
            }
            //add given int to list
            sourceResource.put(inputInt);


            input = in.nextLine();
        }
        for (Thread thread : threadsList) {
            thread.interrupt();
        }

        for (Thread thread : threadsList) {
            try {
                thread.join();
            } catch (InterruptedException ex) {
                System.err.println(ex);
            }
        }
        in.close();
    }
}
