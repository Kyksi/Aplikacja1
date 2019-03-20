package com.naz.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class SecondActivity extends AppCompatActivity {

    TextView textView2, textView4;
    Button button;
    Random rand = new Random();
    String[] przyslowia = {"A co po czyjej wielkości, jak nie ma w głowie mądrości", "Chcesz być bogatym, bądź siedem lat świnią",
            "Jaka praca, taka płaca", "Kto pod kim dołki kopie, ten sam w nie wpada", "Kuj żelazo póki gorące", "Biednemu zawsze wiatr w oczy",
            "Za młodu żyjesz marzeniem, na starość wspomnieniem", "Kto cuchnie leniem, będzie cieniem.",
            "Nie było nas, był las, nie będzie nas, będzie las.", "Głód zagląda do drzwi pracowitego, ale nie wchodzi.",
            "Lepszy jest jawny gniew niż zmyślona przyjaźń.", "Głupich nie orzą, nie sieją, sami się rodzą.", "Każdy czas ma swoją twarz.",
            "Darowanemu koniowi nie zaglądaj w zęby.", "Do ludzi po rozum, do matki po serce.", "Dobra żona – męża korona.",
            "Dobrego bankiet nie zepsuje.", "Bogatemu to i byk się ocieli.", "Co dwie głowy, to nie jedna.",
            "Gdzie cztery nianie, tam dziecko bez nosa.", "Jak cię widzą, tak cię piszą.", "Kto nie pracuje ten nie je.",
            "Kto się mądrzejszym czuje, ten głupszemu ustępuje.", "Łatwiej coś zganić niż zrobić.", "Nie ma tego złego, co by na dobre nie wyszło."};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textView2 = (TextView) findViewById(R.id.textView2);
        textView4 = (TextView) findViewById(R.id.textView4);
        button = (Button) findViewById(R.id.button2);

        Intent intent = getIntent();
        textView2.setText("Witam, " + intent.getStringExtra("name"));

        View.OnClickListener oclBtnOk = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),
                        "Twoje przyslowie zostało wygenerowane", Toast.LENGTH_SHORT).show();
                textView4.setText(przyslowia[rand.nextInt(przyslowia.length)]);
          }
        };

        button.setOnClickListener(oclBtnOk);
    }

}
