package com.example.se2einzelphase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText matrikelnummer;
    private EditText output;
    public static String resultThread;
    private Button buttonCreate;
    private Button buttonCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        matrikelnummer = findViewById(R.id.matrikelnummerInput);
        output = findViewById(R.id.editTextAnswerServer);

        buttonCreate = findViewById(R.id.buttonSend);
        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonCreate_Click(view);
            }
        });

        buttonCalculate = findViewById(R.id.buttonCalculate);
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonCalculate_Click(view);
            }
        });
    }

    public void buttonCreate_Click(View view) {
        String input = matrikelnummer.getText().toString();

        NetworkConnection network = new NetworkConnection(input);
        new Thread(network).start();
        output.setText(resultThread);
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

            for (int i = 0; i <= 7; i++) {
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
            output.setText(sortedList.toString());
        }
    }
}