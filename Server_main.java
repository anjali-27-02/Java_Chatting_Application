package com.company;

public class ServerMain {
    public static void main(String[] args) {
        Server s = new Server();
        s.ConnectToClient();
        s.IOStream();
    }

}
