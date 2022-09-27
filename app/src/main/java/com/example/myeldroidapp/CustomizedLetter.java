package com.example.myeldroidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class CustomizedLetter extends AppCompatActivity {
    TextView tvLetterTheme, tvReceiver, tvMessage, tvQuote;
    ConstraintLayout clLetter;
    String[] loveQuotes, happyQuotes, sadQuotes, birthdayQuotes;
    int randomPick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customized_letter);

        final int max = 4;
        final int min = 0;
        randomPick = (int)Math.floor(Math.random()*(max-min+1)+min);
        //Random Quotes for love
        loveQuotes = new String[]{
                "Love all, trust a few, do wrong to none.",
                "You call it madness, but I call it love.",
                "A life lived in love will never be dull",
                "We can only learn to love by loving.",
                "True love stories never have endings."
        };
        //Random Quotes for Birthday
        birthdayQuotes = new String[]{
                "Our age is merely the number of years the world has been enjoying us!",
                "Everything about you makes me tick.",
                "Today is all about you.",
                "Forget about your age. Remember all the life blessings.",
                "On this day, the sweetest being on earth was born."
        };
        //Random Quotes for Sad
        sadQuotes = new String[]{
                "Sad hurts but it is a healthy feeling",
                "Proud people breed sad sorrows for themselves",
                "Tears are words that need to be written",
                "Tears are the summer showers to the soul.",
                "Mostly it is loss which teaches us about the worth of things."
        };
        //Random Quotes for Happy
        happyQuotes = new String[]{
                "Happiness is only real when shared",
                "Adopt the pace of nature: Her secret is patience",
                "The present moment is filled with joy and happiness",
                "The more you smile, the better you look.",
                "Happiness is the best makeup."
        };
        String letterTheme = getIntent().getStringExtra("LetterTheme");
        String receiver = getIntent().getStringExtra("Receiver");
        String message = getIntent().getStringExtra("Message");

        boolean addQuote = getIntent().getBooleanExtra("AddQuote", false);
        boolean addBg = getIntent().getBooleanExtra("AddBackground", false);

        clLetter = findViewById(R.id.clLetter);
        tvLetterTheme = findViewById(R.id.tvLetterTheme);
        tvReceiver = findViewById(R.id.tvReceiver);
        tvMessage = findViewById(R.id.tvMessage);
        tvQuote = findViewById(R.id.tvQuote);

        tvLetterTheme.setText(letterTheme);
        tvReceiver.setText(getString(R.string.receiver, receiver));
        tvMessage.setText(message);

        String theme = tvLetterTheme.getText().toString();

        if(addBg) addBackground(theme);
        if(addQuote) addQuote(theme);
        else tvQuote.setText("");

    }

    private void addQuote(String theme) {
        if(theme.equalsIgnoreCase("love")){
            tvQuote.setText("\"" + loveQuotes[randomPick] + "\"");
        }else if(theme.equalsIgnoreCase("birthday")){
            tvQuote.setText("\"" + birthdayQuotes[randomPick] + "\"");
        }else if(theme.equalsIgnoreCase("happy")){
            tvQuote.setText("\"" + happyQuotes[randomPick] + "\"");
        }else{
            tvQuote.setText("\"" + sadQuotes[randomPick] + "\"");
        }
    }

    private void addBackground(String theme) {
        if(theme.equalsIgnoreCase("love")) clLetter.setBackgroundResource(R.drawable.love_theme);
        else if(theme.equalsIgnoreCase("birthday")) clLetter.setBackgroundResource(R.drawable.birthdaybg);
        else if(theme.equalsIgnoreCase("happy")) clLetter.setBackgroundResource(R.drawable.happybg);
        else clLetter.setBackgroundResource(R.drawable.bluesad_bg);
    }
}