package com.example.se2einzelphase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private EditText matrikelnummer;
    private TextView output;
    public static String resultThread;

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
        Thread thread = new Thread(network);
        thread.start();
        output.setText(resultThread);

        Log.d("Result output",output.getText().toString());
    }

    public void buttonCalculate_Click(View view) {
        //11941767 % 7 = 5

        String input = matrikelnummer.getText().toString();

        if (input.isEmpty()) {
            output.setText("Bitte eine Matrikelnummer eingeben!");
        } else {
            ArrayList<Integer> withOutPrim = new ArrayList<>();

            ArrayList<Integer> prim = new ArrayList<>();
            prim.add(2);
            prim.add(3);
            prim.add(5);
            prim.add(7);

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
                outputSortedList = outputSortedList + sortedList.get(counter).toString();
                counter++;
            }
            output.setText(outputSortedList);
        }
    }
}