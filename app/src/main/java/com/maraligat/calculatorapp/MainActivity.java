package com.maraligat.calculatorapp;

//Import Dependencies
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

//MainActivity Class
public class MainActivity extends AppCompatActivity {
    //Initialize default answer to null
    Integer answer = null;
    @Override
    //OnCreate method runs when app is initialized
    protected void onCreate(Bundle savedInstanceState) {
        //Creates new onclick listener to listen for button presses 
        View.OnClickListener callbackListener = new View.OnClickListener() {
            @Override
            //Method to run code when buttons are clicked.
            public void onClick(View v) {
                //Create variables for the EditTexts and TextView
                EditText firstNumEditText = (EditText) findViewById(R.id.firstNumEditText);
                EditText secondNumEditText = (EditText) findViewById(R.id.secondNumEditText);

                //Hide keyboard when value is entered into textbox
                View view = getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                
                //Error checking, returns message if one or more textboxes are left blank.
                if (firstNumEditText.getText().toString().matches("") || secondNumEditText.getText().toString().matches("")) {
                    Toast.makeText(getApplicationContext(), "Please enter numbers in both fields.",
                            Toast.LENGTH_SHORT).show();

                }
                //Otherwise, adds or subtracts values depending on what button was pressed.
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
        //Add the variables that correspond to xml features and added the onClick listener
        Button addBtn = (Button) findViewById(R.id.addBtn);
        Button subBtn = (Button) findViewById(R.id.subBtn);
        Button clrBtn = (Button) findViewById(R.id.clrBtn);
        
        addBtn.setOnClickListener(callbackListener);


        subBtn.setOnClickListener(callbackListener);

        //Initializing the clear button's click listener
        clrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Initializing variables
                EditText firstNumEditText = (EditText) findViewById(R.id.firstNumEditText);
                EditText secondNumEditText = (EditText) findViewById(R.id.secondNumEditText);
                RadioButton decimalRadioButton = (RadioButton) findViewById((R.id.decimalRadioButton));
                TextView resultTextView = (TextView) findViewById(R.id.resultTextView);

                //Hide keyboard when value entered into textbox.
                View view = getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                //Clears textboxes and the result label, while also reseting the radio buttons and the answer value.
                firstNumEditText.setText("");

                secondNumEditText.setText("");
                resultTextView.setText("");
                decimalRadioButton.setChecked(true);
                answer = null;


            }
        });

    }

    //Method attached to the RadioButton as an onClick method.
    public void onChangeBase(View view) {
        displayAnswer();
    }
    public void displayAnswer(){
        //Create variables for the RadioGroup and TextView
        TextView resultTextView = (TextView) findViewById(R.id.resultTextView);
        RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup);
        int button_id = rg.getCheckedRadioButtonId();

        //Switch to determine what base to display result in depending on radiobuttons
        switch (button_id) {
            case R.id.decimalRadioButton:
                if (answer != null){
                    resultTextView.setText(Integer.toString(answer));
                }
                else{
                    resultTextView.setText("");
                }
                break;
            case R.id.hexRadioButton:
                if (answer != null){
                    resultTextView.setText(Integer.toHexString(answer));
                }
                else{
                    resultTextView.setText("");
                }
                break;
            case R.id.binaryRadioButton:
                if (answer != null){
                    resultTextView.setText(Integer.toBinaryString(answer));
                }
                else{
                    resultTextView.setText("");
                }
                break;
            case R.id.octalRadioButton:
                if (answer != null){
                    resultTextView.setText(Integer.toOctalString(answer));
                }
                else{
                    resultTextView.setText("");
                }
                break;
            case -1: //button_id returns -1 if radio group is empty.
                rg.check(R.id.decimalRadioButton);
                if (answer != null){
                    resultTextView.setText(Integer.toString(answer));
                }
                else{
                    resultTextView.setText("");
                }
                break;
        }
    }
}
