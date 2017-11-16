package com.maraligat.calculatorapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int answer = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        View.OnClickListener callbackListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Create variables for the EditTexts and TextView
                EditText firstNumEditText = (EditText) findViewById(R.id.firstNumEditText);
                EditText secondNumEditText = (EditText) findViewById(R.id.secondNumEditText);

                //Hide keyboard
                View view = getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }

                if (firstNumEditText.getText().toString().matches("") || secondNumEditText.getText().toString().matches("")) {
                    Toast.makeText(getApplicationContext(), "Please enter numbers in both fields.",
                            Toast.LENGTH_SHORT).show();

                }
                else{
                    //Create variables for the values passed into the EditTexts and the sum
                    int num1 = Integer.parseInt(firstNumEditText.getText().toString());
                    int num2 = Integer.parseInt(secondNumEditText.getText().toString());
                    int result;
                    if (v.getId() == R.id.addBtn) {
                        result = num1 + num2;
                        answer = result;
                    }
                    else{
                        result = num1 - num2;
                        answer = result;
                    }
                    displayAnswer();

                }
            }
        };
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Add the java side of the button (variables) and add the onClick listener
        Button addBtn = (Button) findViewById(R.id.addBtn);
        Button subBtn = (Button) findViewById(R.id.subBtn);
        Button clrBtn = (Button) findViewById(R.id.clrBtn);

        addBtn.setOnClickListener(callbackListener);


        subBtn.setOnClickListener(callbackListener);

        clrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText firstNumEditText = (EditText) findViewById(R.id.firstNumEditText);
                EditText secondNumEditText = (EditText) findViewById(R.id.secondNumEditText);
                RadioButton decimalRadioButton = (RadioButton) findViewById((R.id.decimalRadioButton));
                TextView resultTextView = (TextView) findViewById(R.id.resultTextView);

                //Hide keyboard
                View view = getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                firstNumEditText.setText("");

                secondNumEditText.setText("");
                resultTextView.setText("0");
                decimalRadioButton.setChecked(true);
                answer = 0;


            }
        });

    }

    //Method attached to the RadioButton as an onClick method.
    public void onChangeBase(View view) {
        displayAnswer();
    }
    public void displayAnswer(){
        //Create variables for the EditTexts and TextView
        TextView resultTextView = (TextView) findViewById(R.id.resultTextView);
        RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup);
        int button_id = rg.getCheckedRadioButtonId();

        //Set the text of the TextView to the sum of num1 & num2
        switch (button_id) {
            case R.id.decimalRadioButton:
                resultTextView.setText(Integer.toString(answer));
                break;
            case R.id.hexRadioButton:
                resultTextView.setText(Integer.toHexString(answer));
                break;
            case R.id.binaryRadioButton:
                resultTextView.setText(Integer.toBinaryString(answer));
                break;
            case R.id.octalRadioButton:
                resultTextView.setText(Integer.toOctalString(answer));
                break;
            case -1: //button_id returns -1 if radio group is empty.
                rg.check(R.id.decimalRadioButton);
                resultTextView.setText(Integer.toString(answer));
                break;
        }
    }
}
