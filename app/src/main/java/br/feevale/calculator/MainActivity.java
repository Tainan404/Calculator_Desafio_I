package br.feevale.calculator;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_one, btn_two, btn_three, btn_four, btn_five, btn_six, btn_seven, btn_eight, btn_nine,
            btn_zero, btn_dot, btn_equal, btn_sum, btn_subtract, btn_divide, btn_multiply,
            btn_clear, btn_history;

    ImageButton btn_backspace;

    TextView txt_expression, txt_result;

    List<String> expressions = new ArrayList<>();

    private String lastPressed = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) supportActionBar.hide();

        initComponents();
        setListeners();

        btn_clear.setOnClickListener(v -> {
            txt_expression.setText("");
            txt_result.setText("");
            setLastPressed("");
        });

        btn_history.setOnClickListener(v -> accessHistory());

        btn_equal.setOnClickListener(v -> {
            if (txt_expression.getText() != "") {
                String expression = txt_expression.getText().toString();
                String[] expArray = expression.split(" ");
                ArrayList<String> postfixExpression = new InfixToPostfix(expArray).getOutPut();
                double resultValue = new EvaluateString(postfixExpression).getOutPut();
//                String result = resultValue % 1 == 0 ? Integer.toString((int) resultValue) : Double.toString(resultValue);
                String result = Double.toString(resultValue);

                String resultWithExpression = expression + " = " + result;
                expressions.add(resultWithExpression);

                txt_expression.setText("");
                txt_result.setText(result);
                setLastPressed("");
            }
        });

        btn_backspace.setOnClickListener(v -> {
            String expression = txt_expression.getText().toString();
            if(!expression.isEmpty()){
                byte var0 = 0;
                int var1 = expression.length()-1;
                String substrExp = expression.substring(var0, var1);
                txt_expression.setText(substrExp);
            }
        });
    }

    private void initComponents() {
        txt_expression = findViewById(R.id.txt_expression);
        txt_result = findViewById(R.id.txt_result);

        btn_one = findViewById(R.id.btn_one);
        btn_two = findViewById(R.id.btn_two);
        btn_three = findViewById(R.id.btn_three);
        btn_four = findViewById(R.id.btn_four);
        btn_five = findViewById(R.id.btn_five);
        btn_six = findViewById(R.id.btn_six);
        btn_seven = findViewById(R.id.btn_seven);
        btn_eight = findViewById(R.id.btn_eight);
        btn_nine = findViewById(R.id.btn_nine);
        btn_zero = findViewById(R.id.btn_zero);
        btn_dot = findViewById(R.id.btn_dot);
        btn_equal = findViewById(R.id.btn_equal);
        btn_sum = findViewById(R.id.btn_sum);
        btn_subtract = findViewById(R.id.btn_subtract);
        btn_divide = findViewById(R.id.btn_divide);
        btn_multiply = findViewById(R.id.btn_multiply);
        btn_clear = findViewById(R.id.btn_clear);
        btn_backspace = findViewById(R.id.btn_backspace);
        btn_history = findViewById(R.id.btn_history);
    }

    private void setListeners() {
        btn_one.setOnClickListener(this);
        btn_two.setOnClickListener(this);
        btn_three.setOnClickListener(this);
        btn_four.setOnClickListener(this);
        btn_five.setOnClickListener(this);
        btn_six.setOnClickListener(this);
        btn_seven.setOnClickListener(this);
        btn_eight.setOnClickListener(this);
        btn_nine.setOnClickListener(this);
        btn_zero.setOnClickListener(this);
        btn_dot.setOnClickListener(this);
        btn_equal.setOnClickListener(this);
        btn_sum.setOnClickListener(this);
        btn_subtract.setOnClickListener(this);
        btn_divide.setOnClickListener(this);
        btn_multiply.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
        btn_backspace.setOnClickListener(this);
    }

    private void addToExpression(String value, boolean clear_result) {
        if (clear_result) {
            txt_result.setText("");
            txt_expression.append(value);
        } else {
            if (txt_result.getText() != "") {
                txt_expression.append(txt_result.getText());
            }
            txt_expression.append(value);
            txt_result.setText("");
        }
    }

    public void accessHistory() {
        Intent it = new Intent(getBaseContext(), HistoryActivity.class);
        it.putExtra("listExp", (Serializable) expressions);
        startActivity(it);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_zero:
                    addToExpression("0", true);
                    setLastPressed("number");
                break;
            case R.id.btn_one:
                    addToExpression("1", true);
                    setLastPressed("number");
                break;
            case R.id.btn_two:
                    addToExpression("2", true);
                    setLastPressed("number");
                break;
            case R.id.btn_three:
                    addToExpression("3", true);
                    setLastPressed("number");
                break;
            case R.id.btn_four:
                    addToExpression("4", true);
                    setLastPressed("number");
                break;
            case R.id.btn_five:
                    addToExpression("5", true);
                    setLastPressed("number");
                break;
            case R.id.btn_six:
                    addToExpression("6", true);
                    setLastPressed("number");
                break;
            case R.id.btn_seven:
                    addToExpression("7", true);
                    setLastPressed("number");
                break;
            case R.id.btn_eight:
                    addToExpression("8", true);
                    setLastPressed("number");
                break;
            case R.id.btn_nine:
                    addToExpression("9", true);
                    setLastPressed("number");
                break;
            case R.id.btn_dot:
                if (!getLastPressed().equals("operator")) {
                    if(txt_expression.getText() == "")
                        addToExpression("0.", true);
                    else
                        addToExpression(".", true);
                    setLastPressed("dot");
                }
                break;
            case R.id.btn_sum:
                if (getLastPressed().equals("number") || txt_result.getText() != "") {
                    addToExpression(" + ", false);
                    setLastPressed("operator");
                }
                break;
            case R.id.btn_subtract:
                if (getLastPressed().equals("number") || txt_result.getText() != "") {
                    addToExpression(" - ", false);
                    setLastPressed("operator");
                }
                break;
            case R.id.btn_divide:
                if (getLastPressed().equals("number") || txt_result.getText() != "") {
                    addToExpression(" / ", false);
                    setLastPressed("operator");
                }
                break;
            case R.id.btn_multiply:
                if (getLastPressed().equals("number") || txt_result.getText() != "") {
                    addToExpression(" * ", false);
                    setLastPressed("operator");
                }
                break;
        }
    }

    public String getLastPressed() {
        return lastPressed;
    }

    public void setLastPressed(String lastPressed) {
        this.lastPressed = lastPressed;
    }
}