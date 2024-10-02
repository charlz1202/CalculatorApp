package com.example.calculator;

import android.os.Bundle;
import org.mariuszgromada.math.mxparser.*;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.input);
        display.setShowSoftInputOnFocus(false);

        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getString(R.string.display).equals(display.getText().toString())) {
                    display.setText("");
                }
            }
        });

    }

    private void updateText(String strToAdd) {
        String oldStr = display.getText().toString();
        int cursorPos = display.getSelectionStart();
        String leftStr = oldStr.substring(0, cursorPos);
        String rightStr = oldStr.substring(cursorPos);
        if (getString(R.string.display).equals(display.getText().toString())) {
            display.setText(strToAdd);
            display.setSelection(cursorPos + 1);
        }

        else {
            display.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr));
            display.setSelection(cursorPos + 1);
        }

    }

    public void btZero(View view) {
        updateText("0");
    }

    public void btOne(View view) {
        updateText("1");
    }

    public void btTwo(View view) {
        updateText("2");
    }

    public void btThree(View view) {
        updateText("3");
    }

    public void btFour(View view) {
        updateText("4");
    }

    public void btFive(View view) {
        updateText("5");
    }

    public void btSix(View view) {
        updateText("6");
    }

    public void btSeven(View view) {
        updateText("7");
    }

    public void btEight(View view) {
        updateText("8");
    }

    public void btNine(View view) {
        updateText("9");
    }

    public void btAdd(View view) {
        updateText("+");
    }

    public void btSubtract(View view) {
        updateText("-");
    }

    public void btMultiply(View view) {
        updateText("×");
    }

    public void btDivide(View view) {
        updateText("÷");
    }

    public void btPlusMinus(View view) {
        updateText("-");
    }

    public void btParentheses(View view) {
        int cursorPos = display.getSelectionStart();
        int openPar = 0;
        int closePar = 0;
        int textLen = display.getText().length();

        for (int i=0; i < cursorPos; i++) {
            if (display.getText().toString().substring(i, i+1).equals("(")) {
                openPar +=1;
            }

            if (display.getText().toString().substring(i, i+1).equals(")")) {
                closePar +=1;
            }

        }

        if (openPar == closePar || (textLen > 0 && display.getText().toString().substring(textLen-1, textLen).equals("("))) {
            updateText("(");
            display.setSelection(cursorPos + 1);
        }

        else if (openPar > closePar && textLen > 0 && !display.getText().toString().substring(textLen-1, textLen).equals("(")) {
            updateText(")");
            display.setSelection(cursorPos + 1);
        }


    }

    public void btPoint(View view) {
        updateText(".");
    }

    public void btBackspace(View view) {
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();

        if (cursorPos != 0 && textLen !=0) {
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.replace(cursorPos - 1, cursorPos, "");
            display.setText(selection);
            display.setSelection(cursorPos -1);
        }

    }

    public void btEquals(View view) {
        String userExp = display.getText().toString();

        userExp = userExp.replaceAll("÷", "/");
        userExp = userExp.replaceAll("×", "*");


        Expression exp = new Expression(userExp);

        double result = exp.calculate();

        if (Double.isNaN(result)) {
            display.setText("Error");
        } else {
            display.setText(String.valueOf(result));
        }
        display.setSelection(display.getText().length());

    }

    public void btClear(View view) {
        display.setText("");
    }

    public void btExponent(View view) {
        updateText("^");

    }

}