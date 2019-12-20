package com.example.ismatkhanam.on_button_click;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Email extends Activity implements View.OnClickListener {

    EditText et_email,et_intro,et_name,et_things,et_action,et_outro;
    Button b_send_email;
    String emailAdd,beginning,name,stupidAction,hatefulAct,out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.email);
        inti_var();
        b_send_email.setOnClickListener(this);
    }

    private void inti_var() {
        et_email=(EditText)findViewById(R.id.et_email);
        et_intro=(EditText)findViewById(R.id.et_intro);
        et_name=(EditText)findViewById(R.id.et_name);
        et_things=(EditText)findViewById(R.id.et_things);
        et_action=(EditText)findViewById(R.id.et_action);
        et_outro=(EditText)findViewById(R.id.et_outro);
        b_send_email=(Button)findViewById(R.id.b_send_email);
    }

    @Override
    public void onClick(View v) {
        convert_et_to_string();
        String email_address[]={emailAdd};
        String msg= "Well hello "
                + name
                + " I just wanted to say "
                + beginning
                + ". Not only that but I hate when you "
                + stupidAction
                + ", that just really makes me crazy. I just want to make you "
                + hatefulAct
                + ". Welp, thats all I wanted to chit-chatter about, oh and "
                + out
                + " . oh also if you get bored you should check out www.bringback.com "
                +'\n'
                + "PS.I think I hate you....";

        Intent email_intent=new Intent(Intent.ACTION_SEND);
        email_intent.putExtra(Intent.EXTRA_EMAIL, email_address);
        email_intent.putExtra(Intent.EXTRA_SUBJECT, "I hate you!!!");
        email_intent.setType("plain/text");
        email_intent.putExtra(Intent.EXTRA_TEXT, msg);
        startActivity(email_intent);
    }

    private void convert_et_to_string() {
        emailAdd =et_email.getText().toString();
        beginning =et_intro.getText().toString();
        name =et_name.getText().toString();
        stupidAction =et_things.getText().toString();
        hatefulAct =et_action.getText().toString();
        out =et_outro.getText().toString();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
