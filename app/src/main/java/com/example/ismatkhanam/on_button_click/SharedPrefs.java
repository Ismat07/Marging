package com.example.ismatkhanam.on_button_click;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SharedPrefs extends Activity implements View.OnClickListener {

    EditText shared_data;
    TextView data_result;
    public static String file_name="MySharedString";
    SharedPreferences some_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sharedpreferences);
        setupVariables();
        some_data=getSharedPreferences(file_name,0);
    }

    private void setupVariables() {
        Button save=(Button)findViewById(R.id.b_save);
        Button load=(Button)findViewById(R.id.b_load);
        shared_data=(EditText)findViewById(R.id.et_shared_prefs);
        data_result=(TextView)findViewById(R.id.tv_load_shared_prefs);
        save.setOnClickListener(this);
        load.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.b_save:
                String str_data=shared_data.getText().toString();
                SharedPreferences.Editor editor=some_data.edit();
                editor.putString("sharedString",str_data);
                editor.commit();
                break;

            case R.id.b_load:
                some_data=getSharedPreferences(file_name,0);
                String data_return=some_data.getString("sharedString","Couldn't Load Data");
                data_result.setText(data_return);
                break;
        }
    }
}
