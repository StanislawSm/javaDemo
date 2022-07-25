package pl.user.pt.java.lab3.main;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class MainClient{

    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 9797)) {
            Scanner scanner = new Scanner(System.in);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            Message serverStatus = (Message) in.readObject();
            System.out.println("Server status: " + serverStatus.getContent());
            int numberOfMessage = Integer.parseInt(scanner.nextLine());
            out.writeObject(numberOfMessage);
            System.out.println(((Message) in.readObject()).getContent());

            for (int i = 0; i < numberOfMessage; i++) {
                Message message = new Message(i, scanner.nextLine());
                out.writeObject(message);
            }
            System.out.println((String)((Message) in.readObject()).getContent());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
