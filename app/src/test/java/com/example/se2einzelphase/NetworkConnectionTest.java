package com.example.se2einzelphase;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class NetworkConnectionTest {

    private NetworkConnection networkConnection;

    @After
    public void afterEachTest() throws Exception {
        networkConnection = null;
    }

    @Test
    public void callCallWithWrongMatrikelnummer() throws ExecutionException, InterruptedException {
        networkConnection = new NetworkConnection("003499");

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> result = executorService.submit(networkConnection);

        assertEquals("Dies ist keine gueltige Matrikelnummer",result.get());
    }
}