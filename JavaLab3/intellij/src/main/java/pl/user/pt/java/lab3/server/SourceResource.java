package pl.user.pt.java.lab3.server;

import java.util.LinkedList;
import java.util.Queue;

public class SourceResource {
    private Queue<Integer> fifo = new LinkedList<Integer>();

    public synchronized Integer take() throws InterruptedException {
        while (fifo.isEmpty()) {
            wait();//Wait, maybe someone will put sth here.
        }
        return fifo.poll();
    }
    public synchronized void put(Integer value) {
        this.fifo.add(value);
        notifyAll();
        System.out.println("added " + value + " to source container");
    }
}
