package pl.user.pt.java.lab2.classes;


public class Consumer implements Runnable {
    private SourceResource resource;
    public Consumer(SourceResource resource) {
        this.resource = resource;
    }
    @Override
    public void run() {
        try {
            System.out.println(resource.take());
        } catch (InterruptedException ex) {

        }
    }
}
