package com.example.ex4;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    protected String IP;
    protected int port;
    protected Socket client;
    protected DataInputStream in;
    protected boolean isConnected;
    protected OutputStream outputStream;

    public Client (String IP, int port) {
            this.IP = IP;
            this.port = port;
            client=null;
    }

    public void Connect(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("Connecting to " + IP + " on port " + port);
                    client = new Socket(IP, port);
                    outputStream = client.getOutputStream();
                    isConnected = true;
                    System.out.println("Just connected to " + client.getRemoteSocketAddress());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    public void WriteToServer(String msg){
        byte[] msgAsBytes = msg.getBytes();
        try {
            if(client!=null){
                outputStream.write(msgAsBytes, 0, msgAsBytes.length);
                outputStream.flush();
            }
            else{
                System.out.println("client==null");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void CloseConnection(){
        try {
            System.out.println("Server says " + this.in.readUTF());
            this.client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
