package com.example.se2einzelphase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.concurrent.Callable;

public class MainActivity extends AppCompatActivity  {

    private EditText matrikelnummer;
    private EditText output;
    public static String resultThread;
    private Button buttonCreate;

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
    }

    public void buttonCreate_Click(View view)
    {
        String input = matrikelnummer.getText().toString();

        NetworkConnection network = new NetworkConnection(input);
        new Thread(network).start();
        output.setText(resultThread);
    }
}