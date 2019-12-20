package com.example.ismatkhanam.on_button_click;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Data extends Activity implements View.OnClickListener {

    Button start,startFor;
    EditText sendET;
    TextView got_answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get);
        initializer();
    }

    private void initializer() {
        start=(Button)findViewById(R.id.b_start);
        startFor=(Button)findViewById(R.id.b_start_result);
        sendET=(EditText) findViewById(R.id.et_send);
        got_answer=(TextView) findViewById(R.id.tv_got);
        start.setOnClickListener(this);
        startFor.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.b_start:
                String bread=sendET.getText().toString();
                Bundle basket=new Bundle();
                basket.putString("key",bread);
                Intent a=new Intent(Data.this,OpenedClass.class);
                a.putExtras(basket);
                startActivity(a);
                break;
            case R.id.b_start_result:
                Intent i=new Intent(Data.this,OpenedClass.class);
                startActivityForResult(i,0);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            Bundle basket=data.getExtras();
            String s=basket.getString("answer");
            got_answer.setText(s);
        }
    }
}
