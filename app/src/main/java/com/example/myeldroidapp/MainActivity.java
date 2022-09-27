package com.example.myeldroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RadioGroup rgTheme;
    RadioButton rbTheme;
    EditText etReceiver, etMessage;
    CheckBox cbAddQuote, cbAddBackground;
    Button btnCreate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rgTheme = findViewById(R.id.rgTheme);
        etReceiver = findViewById(R.id.etReceiver);
        etMessage = findViewById(R.id.etMessage);
        cbAddQuote = findViewById(R.id.cbAddRandomQuote);
        cbAddBackground = findViewById(R.id.cbWithBackground);
        btnCreate = findViewById(R.id.btnCreate);



        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean flag_addQuote = false, flag_addBackground = false;
                String message = etMessage.getText().toString();
                String receiver = etReceiver.getText().toString();
                Intent intent = new Intent(MainActivity.this, CustomizedLetter.class);

                //Checking if radio button is selected
                int selectedThemeId = rgTheme.getCheckedRadioButtonId();
                if(selectedThemeId != -1){
                    rbTheme = findViewById(selectedThemeId);
                    intent.putExtra("LetterTheme", rbTheme.getText().toString());
                }

                //Checking checkboxes
                if(cbAddQuote.isChecked()){
                    flag_addQuote = true;
                }
                if(cbAddBackground.isChecked()){
                    flag_addBackground = true;
                }

                //Passing data from main activity to customizedLetter
                intent.putExtra("Receiver", receiver);
                intent.putExtra("Message", message);
                intent.putExtra("AddQuote", flag_addQuote);
                intent.putExtra("AddBackground", flag_addBackground);

                //clearInputs();
                if(message.isEmpty() || receiver.isEmpty() || selectedThemeId == -1){
                    Toast.makeText(
                            getApplicationContext(),
                            "Please specify the letter theme, recipient, and your message.",
                            Toast.LENGTH_SHORT
                    ).show();
                }else{
                    startActivity(intent);
                }
            }
        });
    }

    private void clearInputs(){
        //Clear RadioGroup
        rgTheme.setSelected(false);
        //Clear Receiver & Message
        etReceiver.setText("");
        etMessage.setText("");
        //Clear Checkboxes
        cbAddQuote.setChecked(false);
        cbAddBackground.setChecked(false);

    }

}