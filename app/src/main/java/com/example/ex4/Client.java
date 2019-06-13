package com.example.ex4;

import android.util.Log;

import java.io.Console;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    protected String IP;
    protected int port;
    protected Socket client;
    protected DataInputStream in;
    protected boolean isConnected;

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

    public void WriteToServer(String string){

        try {
            if(client!=null){
                OutputStream outToServer = this.client.getOutputStream();
                DataOutputStream out = new DataOutputStream(outToServer);
                out.writeUTF(string);
            }
            else{
                Log.d("myDeb","cannot write");
            }
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
