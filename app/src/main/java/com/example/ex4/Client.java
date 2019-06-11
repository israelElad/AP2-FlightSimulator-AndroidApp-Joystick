package com.example.ex4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    private String IP;
    private int port;
    private Socket client;
    private DataInputStream in;

    public Client (String IP, int port) {
            this.IP = IP;
            this.port = port;
    }

    public void Connect(){
        try {
            System.out.println("Connecting to " + this.IP + " on port " + this.port);
            this.client = new Socket(this.IP, this.port);
            System.out.println("Just connected to " + this.client.getRemoteSocketAddress());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void WriteToServer(String string){
        try {
            OutputStream outToServer = this.client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);
            out.writeUTF(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String ReadFromServer(){
        String serverAns = "";
        try {
            InputStream inFromServer = this.client.getInputStream();
            this.in = new DataInputStream(inFromServer);
            serverAns = this.in.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return serverAns;
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
