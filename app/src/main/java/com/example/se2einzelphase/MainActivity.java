package com.example.se2einzelphase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private EditText matrikelnummer;
    private TextView output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        matrikelnummer = findViewById(R.id.matrikelnummerInput);
        output = findViewById(R.id.textViewOutput);
    }

    public void buttonSend_Click(View view) {
        String input = matrikelnummer.getText().toString();

        NetworkConnection network = new NetworkConnection(input);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> result = executorService.submit(network);

        try {
            output.setText(result.get());
        } catch (ExecutionException e) {
            Log.e("Execution Exception",e.getMessage());
        } catch (InterruptedException e) {
            Log.e("Interrupted Exception",e.getMessage());
        }
    }

    public void buttonCalculate_Click(View view) {
        //11941767 % 7 = 5

        String input = matrikelnummer.getText().toString();

        if (input.isEmpty()) {
            output.setText("Bitte eine Matrikelnummer eingeben!");
        } else {

            ArrayList<Integer> prim = new ArrayList<>();
            prim.add(2);
            prim.add(3);
            prim.add(5);
            prim.add(7);

            ArrayList<Integer> withOutPrim = new ArrayList<>();

            for (int i = 0; i < input.length(); i++) {
                Integer number = Integer.parseInt(String.valueOf(input.charAt(i)));

                if (!prim.contains(number)) {
                    withOutPrim.add(number);
                }
            }

            ArrayList<Integer> sortedList = new ArrayList<>();

            for (int i = withOutPrim.size() - 1; i >= 0; i--) {
                int smallest = withOutPrim.get(i);
                int counter = 0;
                int position = 0;

                while (counter < withOutPrim.size()) {
                    if (withOutPrim.get(counter) <= smallest) {
                        smallest = withOutPrim.get(counter);
                        position = counter;
                    }
                    counter++;
                }
                sortedList.add(smallest);
                withOutPrim.remove(position);
            }

            String outputSortedList = "";
            int counter = 0;
            while(counter < sortedList.size())
            {
                outputSortedList += sortedList.get(counter).toString();
                counter++;
            }
            output.setText(outputSortedList);
        }
    }
}