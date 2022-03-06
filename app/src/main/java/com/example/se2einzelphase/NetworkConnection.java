package com.example.se2einzelphase;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class NetworkConnection implements Runnable {

    private String input;
    private String resultServer;
    private Socket clientSocket;

    public NetworkConnection(String input) {
        this.input = input;
    }

    @Override
    public void run() {

        try {
            clientSocket = new Socket("se2-isys.aau.at", 53212);

            DataOutputStream outputStreamServer = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inputStreamFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            outputStreamServer.writeBytes(input + '\n');
            resultServer = inputStreamFromServer.readLine();

            MainActivity.resultThread = resultServer;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getResult()
    {
        return resultServer;
    }
}
