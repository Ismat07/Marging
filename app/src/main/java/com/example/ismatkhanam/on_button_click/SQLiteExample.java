package com.example.ismatkhanam.on_button_click;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SQLiteExample extends Activity implements View.OnClickListener{

    Button sql_update,sql_view,sql_modify,sql_get_info,sql_delete;
    EditText sql_name,sql_hotness,sql_row;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqliteexample);
        sql_update=(Button)findViewById(R.id.b_sql_update);
        sql_view=(Button)findViewById(R.id.b_sql_open_view);
        sql_name=(EditText)findViewById(R.id.et_sql_name);
        sql_hotness=(EditText)findViewById(R.id.et_sql_hotness);

        sql_view.setOnClickListener(this);
        sql_update.setOnClickListener(this);

        sql_row=(EditText)findViewById(R.id.et_sql_row_info);
        sql_modify=(Button)findViewById(R.id.b_sql_modify);
        sql_get_info=(Button)findViewById(R.id.b_get_info);
        sql_delete=(Button)findViewById(R.id.b_sql_delete);

        sql_delete.setOnClickListener(this);
        sql_modify.setOnClickListener(this);
        sql_get_info.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.b_sql_update:
                boolean did_it_work=true;
                try {
                    String name = sql_name.getText().toString();
                    String hotness = sql_hotness.getText().toString();

                    HotOrNot entry = new HotOrNot(SQLiteExample.this);
                    entry.open();
                    entry.create_entry(name, hotness);
                    entry.close();
                }catch (Exception e){
                    did_it_work=false;
                    String error=e.toString();
                    Dialog d=new Dialog(this);
                    d.setTitle("Dang it!!");
                    TextView tv=new TextView(this);
                    tv.setText(error);
                    d.setContentView(tv);
                    d.show();
                }
                finally {
                    if(did_it_work){
                        Dialog d=new Dialog(this);
                        d.setTitle("Heck Yea!!");
                        TextView tv=new TextView(this);
                        tv.setText("Success");
                        d.setContentView(tv);
                        d.show();
                    }
                }
                break;

            case R.id.b_sql_open_view:
                Intent i=new Intent("com.example.ismatkhanam.on_button_click.SQLVIEW");
                startActivity(i);
                break;

            case R.id.b_get_info:
                try{
                        String s=sql_row.getText().toString();
                        long l=Long.parseLong(s);
                        HotOrNot hon=new HotOrNot(this);
                        hon.open();
                        String return_name=hon.getName(l);
                        String return_hotness=hon.getHotness(l);
                        hon.close();

                        sql_name.setText(return_name);
                        sql_hotness.setText(return_hotness);
                }catch (Exception e){
                        String error=e.toString();
                        Dialog d=new Dialog(this);
                        d.setTitle("Dang it!!");
                        TextView tv=new TextView(this);
                        tv.setText(error);
                        d.setContentView(tv);
                        d.show();
                }
                break;

            case R.id.b_sql_modify:
                try{
                    String m_name = sql_name.getText().toString();
                    String m_hotness = sql_hotness.getText().toString();
                    String s_row=sql_row.getText().toString();
                    long l_row=Long.parseLong(s_row);

                    HotOrNot ex=new HotOrNot(this);
                    ex.open();
                    ex.update_entry(l_row,m_name,m_hotness);
                    ex.close();
                }catch (Exception e){
                    String error=e.toString();
                    Dialog d=new Dialog(this);
                    d.setTitle("Dang it!!");
                    TextView tv=new TextView(this);
                    tv.setText(error);
                    d.setContentView(tv);
                    d.show();
                }
                break;

            case R.id.b_sql_delete:
                try{
                    String s_row1=sql_row.getText().toString();
                    long l_row1=Long.parseLong(s_row1);
                    HotOrNot ex1=new HotOrNot(this);
                    ex1.open();
                    ex1.delete_entry(l_row1);
                    ex1.close();
                }catch (Exception e){
                    String error=e.toString();
                    Dialog d=new Dialog(this);
                    d.setTitle("Dang it!!");
                    TextView tv=new TextView(this);
                    tv.setText(error);
                    d.setContentView(tv);
                    d.show();
                }
                break;
        }
    }
}
