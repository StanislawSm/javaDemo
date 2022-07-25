package pl.user.pt.java.lab3.main;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ForkJoinPool;


public class MainServer {
    static final ForkJoinPool pool = ForkJoinPool.commonPool();
    public static void main(String[] args){
        System.out.println("Started server");
        try (ServerSocket socket = new ServerSocket(9797)) {
            while (true) {
                System.out.println("Waiting for connection");
                Socket client = socket.accept();
                System.out.println("Connected");
                ClientHandler clientThread = new ClientHandler(client);

                pool.execute(clientThread);
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        } finally {
            pool.shutdown();
        }

        System.out.println("Stopping server");
    }
}
