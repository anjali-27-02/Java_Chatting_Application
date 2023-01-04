package com.company;

public class ClientMain {
    public static void main(String[] args) {
        Client c=new Client();
        c.ConnectToServer();
        c.setIOStream();
    }
}
