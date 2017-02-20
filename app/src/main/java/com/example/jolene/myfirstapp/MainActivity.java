package com.example.jolene.myfirstapp;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.text.DecimalFormat;

import com.example.jolene.myfirstapp.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private double valueOne = Double.NaN;
    private double valueTwo;

    private static final char ADDITION = '+';
    private static final char SUBSTRACTION = '-';
    private static final char MULTIPLICATION = '*';
    private static final char DIVISION = '/';

    private char CURRENT_ACTION;

    private DecimalFormat decimalFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        decimalFormat = new DecimalFormat("#.##########");
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);


        // Symbols

        binding.buttonAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                computeCalculation();
                CURRENT_ACTION = ADDITION;
                binding.editText.setText(binding.editText.getText() + "+");
                binding.editText.setText(null);
            }
        });

        binding.buttonSubtract.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                computeCalculation();
                CURRENT_ACTION = SUBSTRACTION;
                binding.editText.setText(binding.editText.getText() + "-");
                binding.editText.setText(null);
            }
        });

        binding.buttonMultiply.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                computeCalculation();
                CURRENT_ACTION = MULTIPLICATION;
                binding.editText.setText(binding.editText.getText() + "*");
                binding.editText.setText(null);
            }
        });

        binding.buttonDivide.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                computeCalculation();
                CURRENT_ACTION = DIVISION;
                binding.editText.setText(binding.editText.getText() + "/");
                binding.editText.setText(null);
            }
        });

        // Numbers
        binding.buttonDot.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + ".");
            }
        });

        binding.buttonZero.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "0");
            }
        });

        binding.buttonOne.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "1");
            }
        });

        binding.buttonTwo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "2");
            }
        });

        binding.buttonThree.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "3");
            }
        });

        binding.buttonFour.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "4");
            }
        });

        binding.buttonFive.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "5");
            }
        });

        binding.buttonSix.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "6");
            }
        });

        binding.buttonSeven.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "7");
            }
        });

        binding.buttonEight.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "8");
            }
        });

        binding.buttonNine.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "9");
            }
        });

        /**
         * Clears screen one char at a time
         *
         * If there is only 1 char then it resets everything
         */
        binding.buttonClear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(binding.editText.getText().length() > 0 ){
                    CharSequence currentText = binding.editText.getText();
                    binding.editText.setText(currentText.subSequence(0, currentText.length()-1));
                } else {
                    valueOne = Double.NaN;
                    valueTwo = Double.NaN;
                    binding.editText.setText("");
                    binding.infoTextView.setText("");
                }
            }
        });
    }

    private void computeCalculation() {
        if(!Double.isNaN(valueOne)) {
            valueTwo = Double.parseDouble(binding.editText.getText().toString());
            binding.editText.setText(null);

            if(CURRENT_ACTION == ADDITION)
                valueOne = this.valueOne + valueTwo;
            else if(CURRENT_ACTION == SUBSTRACTION)
                valueOne = this.valueOne - valueTwo;
            else if(CURRENT_ACTION == MULTIPLICATION)
                valueOne = this.valueOne * valueTwo;
            else if(CURRENT_ACTION == DIVISION)
                valueOne = this.valueOne / valueTwo;
        } else {
            try {
                valueOne = Double.parseDouble(binding.editText.getText().toString());
            } catch (Exception e) {

            }
        }
    }


}
