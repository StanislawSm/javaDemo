package pl.user.pt.java.lab3.main;

import java.io.Serializable;

public class Message implements Serializable {
    final private int number;
    final private String content;

    public Message(int number, String content) {
        this.number = number;
        this.content = content.strip();
    }

    final public int getNumber() {
        return this.number;
    }

    final public String getContent() {
        return this.content;
    }
}