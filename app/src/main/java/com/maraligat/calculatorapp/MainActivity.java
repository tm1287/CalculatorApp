package com.maraligat.calculatorapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Add the java side of the button (variables) and add the onClick listener
        Button addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Create variables for the EditTexts and TextView
                EditText firstNumEditText = findViewById(R.id.firstNumEditText);
                EditText secondNumEditText = findViewById(R.id.secondNumEditText);
                TextView resultTextView = findViewById(R.id.resultTextView);
                RadioGroup rg = findViewById(R.id.radioGroup);

                int button_id = rg.getCheckedRadioButtonId();

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
                    //Create variable les for the values passed into the EditTexts and the sum
                    int num1 = Integer.parseInt(firstNumEditText.getText().toString());
                    int num2 = Integer.parseInt(secondNumEditText.getText().toString());
                    int result = num1 + num2;

                    //Set the text of the TextView to the sum of num1 & num2
                    switch (button_id) {
                        case R.id.decimalRadioButton:
                            resultTextView.setText(Integer.toString(result));
                            break;
                        case R.id.hexRadioButton:
                            resultTextView.setText(Integer.toHexString(result));
                            break;
                        case R.id.binaryRadioButton:
                            resultTextView.setText(Integer.toBinaryString(result));
                            break;
                        case -1: //button_id returns -1 if radio group is empty.
                            rg.check(R.id.decimalRadioButton);
                            resultTextView.setText(Integer.toString(result));
                            break;
                    }
                }
            }
        });
    }

    //Method attached to the RadioButton as an onClick method.
    public void onChangeBase(View view) {
        findViewById(R.id.addBtn).callOnClick();
    }
}