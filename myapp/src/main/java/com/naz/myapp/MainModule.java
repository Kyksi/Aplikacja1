package com.naz.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.Random;

public class MainModule extends AppCompatActivity {

    EditText myText;
    TextView textView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_module);

        myText = (EditText) findViewById(R.id.editText1);
        button = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textView);

        View.OnClickListener oclBtnOk = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!myText.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(),
                            "Dziękuje, " + myText.getText(), Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                    intent.putExtra("name", myText.getText().toString());
                    startActivity(intent);

                } else {
                    Toast.makeText(getApplicationContext(),
                            "Wprowzdż imie, żeby kontynuować", Toast.LENGTH_SHORT).show();
                }
            }
        };

        button.setOnClickListener(oclBtnOk);
    }

}
