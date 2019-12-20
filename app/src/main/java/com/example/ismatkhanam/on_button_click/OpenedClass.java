package com.example.ismatkhanam.on_button_click;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

public class OpenedClass extends Activity implements View.OnClickListener,RadioGroup.OnCheckedChangeListener{

    TextView question,test;
    Button return_data;
    RadioGroup selection_list;
    String got_bread,set_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send);
        initializer();

        //this is to used the Prefs class
        SharedPreferences getData= PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String et=getData.getString("name","Ismat is.....");
        String values=getData.getString("list","4");
        if(values.contentEquals("1")){
            question.setText(et);
        }

        /*Bundle got_basket=getIntent().getExtras();
        got_bread=got_basket.getString("key");
        question.setText(got_bread);*/
    }

    private void initializer() {
        question=(TextView)findViewById(R.id.tv_question);
        test=(TextView)findViewById(R.id.tv_text);
        return_data=(Button)findViewById(R.id.b_return);
        return_data.setOnClickListener(this);
        selection_list=(RadioGroup) findViewById(R.id.rg_answer);
        selection_list.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent p=new Intent();
        Bundle backpack=new Bundle();
        backpack.putString("answer",set_data);
        p.putExtras(backpack);
        setResult(RESULT_OK,p);
        finish();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.r_app:
                set_data="Probably right!!";
                break;
            case R.id.r_project:
                set_data="Definitely right!!";
                break;
            case R.id.r_website:
                set_data="Spot on!!";
                break;
        }
        test.setText(set_data);
    }
}
