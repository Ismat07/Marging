package com.example.ismatkhanam.on_button_click;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;
import java.util.Random;

public class TextPlay extends Activity implements View.OnClickListener {

    Button b1;
    ToggleButton tb;
    EditText input;
    TextView display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text);

        someMethod();
        tb.setOnClickListener(this);
        b1.setOnClickListener(this);
    }

    private void someMethod() {
        b1=(Button) findViewById(R.id.button_result);
        tb = (ToggleButton) findViewById(R.id.tb_Password);
        input = (EditText) findViewById(R.id.command);
        display= (TextView) findViewById(R.id.tv_result);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_result:
                String check=input.getText().toString();
                display.setText(check);
                if(check.contentEquals("left")){
                    display.setGravity(Gravity.LEFT);
                }
                else if(check.contentEquals("center")){
                    display.setGravity(Gravity.CENTER);
                }
                else if(check.contentEquals("right")){
                    display.setGravity(Gravity.RIGHT);
                }
                else if(check.contentEquals("blue")){
                    display.setTextColor(Color.BLUE);
                }
                else if(check.contains("WTF")){
                    Random num= new Random();
                    display.setText("WTF!!!!!");
                    display.setTextSize(num.nextInt(75));
                    display.setTextColor(Color.rgb(num.nextInt(265), num.nextInt(265), num.nextInt(265)));
                    switch (num.nextInt(3)){
                        case 0:
                            display.setGravity(Gravity.LEFT);
                            break;
                        case 1:
                            display.setGravity(Gravity.CENTER);
                            break;
                        case 2:
                            display.setGravity(Gravity.RIGHT);
                            break;
                    }
                }
                else{
                    display.setText("invalid");
                    display.setGravity(Gravity.CENTER);
                    display.setTextColor(Color.CYAN);
                }
                break;

            case  R.id.tb_Password:
                if(tb.isChecked()){
                    input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                else{
                    input.setInputType(InputType.TYPE_CLASS_TEXT);
                }
                break;
        }
    }
}
