package pl.user.pt.java.lab3.main;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket client;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public ClientHandler(Socket client) throws IOException {
        this.client = client;
        out = new ObjectOutputStream(client.getOutputStream());
        in = new ObjectInputStream(client.getInputStream());
    }

    @Override
    public void run() {
        try {
            out.writeObject(new Message(0, "ready"));
            int numberOfMessages = (Integer)in.readObject();
            out.writeObject(new Message(0, "ready for messages"));

            for (int i = 0; i < numberOfMessages; i++) {
                Message message = (Message)in.readObject();
                System.out.println(message.getNumber() + " " + message.getContent());
            }
            out.writeObject(new Message(0, "finished"));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            in.close();
            out.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
