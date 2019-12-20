package com.example.ismatkhanam.on_button_click;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class InternalData extends Activity implements View.OnClickListener{

    EditText shared_data;
    TextView data_result;
    FileOutputStream fos;
    String file_name="InternalString";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sharedpreferences);
        setupVariables();
    }

    private void setupVariables() {
        Button save=(Button)findViewById(R.id.b_save);
        Button load=(Button)findViewById(R.id.b_load);
        shared_data=(EditText)findViewById(R.id.et_shared_prefs);
        data_result=(TextView)findViewById(R.id.tv_load_shared_prefs);
        save.setOnClickListener(this);
        load.setOnClickListener(this);
        try {
            fos=openFileOutput(file_name, Context.MODE_PRIVATE);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.b_save:
                String data=shared_data.getText().toString();

                //saving data via file
                /*File f=new File(file_name);
                try {
                    fos=new FileOutputStream(f);
                    //write some data
                    fos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }*/

                //  or
                try {
                    fos=openFileOutput(file_name,Context.MODE_PRIVATE);
                    fos.write(data.getBytes());
                    fos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case R.id.b_load:
                new load_some_stuff().execute(file_name);
                break;
        }
    }


    public class load_some_stuff extends AsyncTask<String,Integer,String>{

        ProgressDialog dialog;

        protected void onPreExecute() {
            // example of setting up something
            dialog=new ProgressDialog(InternalData.this);
            dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            dialog.setMax(100);
            dialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
                String collected=null;
                FileInputStream fis=null;
                for (int i=0;i<20;i++){
                    publishProgress(5);
                    try {
                        Thread.sleep(88);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                dialog.dismiss();
                try {
                    fis=openFileInput(file_name);
                    byte[] data_array=new byte[fis.available()];
                    while (fis.read(data_array) != -1){
                        collected=new String(data_array);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        fis.close();
                        return collected;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            dialog.incrementProgressBy(values[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            data_result.setText(s);
        }
    }
}
