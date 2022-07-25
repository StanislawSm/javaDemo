package pl.user.pt.java.lab2.classes;

import javax.print.attribute.standard.PrinterMakeAndModel;
import java.util.Random;

public class Computer implements Runnable {
    private SourceResource sourceContainer;
    private DestinationResource destinationContainer;
    private String input;

    public void setInput(String input) {
        this.input = input;
    }

    public Computer(SourceResource sourceContainer, DestinationResource destinationContainer) {
        this.sourceContainer = sourceContainer;
        this.destinationContainer = destinationContainer;
    }

    private boolean computeIfIsPrime(Integer numberToCompute){
        Random rand = new Random();
        for (int i = 2; i < numberToCompute; i++){
            try {
                Thread.sleep(rand.nextInt(2000));
            } catch (InterruptedException e) {
                System.err.println(e);
            }
            /*try{
                wait(rand.nextInt(1000));
            } catch (InterruptedException ex){
                System.err.println(ex);
            }*/

            if (numberToCompute % i == 0){
                return false;
            }
        }
        return true;
    }

    @Override
    public void run() {
        while(!Thread.interrupted()){
            try {
                Integer numberToCompute = sourceContainer.take();
                boolean isPrime = computeIfIsPrime(numberToCompute);
                destinationContainer.put(new DestinationSegment(numberToCompute, isPrime));
                System.out.println(numberToCompute + " is prime? " + isPrime);
            } catch (InterruptedException ex) {
                break;
            }

        }
    }
}
