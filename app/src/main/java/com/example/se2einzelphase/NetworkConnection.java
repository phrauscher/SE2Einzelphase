package com.example.se2einzelphase;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.Callable;

public class NetworkConnection implements Callable<String> {

    private String input;
    private String resultServer;
    private Socket clientSocket;

    public NetworkConnection(String input) {
        this.input = input;
    }

    @Override
    public String call() {
        try {
            clientSocket = new Socket("se2-isys.aau.at", 53212);

            DataOutputStream outputStreamToServer = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inputStreamFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            outputStreamToServer.writeBytes(input + '\n');
            resultServer = inputStreamFromServer.readLine();

            return resultServer;

        } catch (IOException e) {
            return e.getMessage();
        }
    }
}