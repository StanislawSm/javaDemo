package pl.user.pt.java.lab3.server;

import java.util.LinkedList;
import java.util.Queue;

public class DestinationResource {
    private Queue<DestinationSegment> fifo = new LinkedList<DestinationSegment>();

    public synchronized DestinationSegment take() throws InterruptedException {
        while (fifo.isEmpty()) {
            wait();//Wait, maybe someone will put sth here.
        }
        return fifo.poll();
    }
    public synchronized void put(DestinationSegment value) {
        this.fifo.add(value);
        //notifyAll();
    }
}
