package com.example.ismatkhanam.on_button_click;

import android.app.Activity;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ExternalData extends Activity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private TextView can_write,can_read;
    private String state;
    boolean canW,canR;
    Spinner spinner;
    String[] paths={"Music","Pictures","Download"};
    File path=null,file=null;
    EditText save_file;
    Button confirm,save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.externaldata);
        can_write=(TextView)findViewById(R.id.tv_can_write);
        can_read=(TextView)findViewById(R.id.tv_can_read);
        confirm=(Button)findViewById(R.id.b_confirm_save_as);
        save=(Button)findViewById(R.id.b_save_file);
        save_file=(EditText)findViewById(R.id.et_save_as);

        confirm.setOnClickListener(this);
        save.setOnClickListener(this);

        check_state();

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(ExternalData.this,android.R.layout.simple_spinner_item,paths);
        spinner=(Spinner)findViewById(R.id.spinner1);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    private void check_state() {
        state= Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)){
            // read and write
            can_write.setText("true");
            can_read.setText("true");
            canW=canR=true;
        }
        else if (state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)){
            // read but can't write
            can_write.setText("false");
            can_read.setText("true");
            canW=false;
            canR=true;
        }
        else {
            can_write.setText("false");
            can_read.setText("false");
            canW=canR=false;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int pos=spinner.getSelectedItemPosition();
        switch (pos){
            case 0:
                path=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
                break;

            case 1:
                path=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                break;

            case 2:
                path=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.b_save_file:
                String f=save_file.getText().toString();
                file=new File(path,f+".png");

                check_state();
                if (canW == canR ==true){
                    path.mkdirs();
                    try {
                        InputStream is=getResources().openRawResource(R.drawable.greenball);
                        OutputStream os=new FileOutputStream(file);
                        byte[] data=new byte[is.available()];
                        is.read(data);
                        os.write(data);
                        is.close();
                        os.close();

                        Toast t=Toast.makeText(ExternalData.this,"File has been saved!!!",Toast.LENGTH_LONG);
                        t.show();

                        // update files for the user to use
                        MediaScannerConnection.scanFile(ExternalData.this,new String[]{file.toString()},null,
                                new MediaScannerConnection.OnScanCompletedListener() {
                            @Override
                            public void onScanCompleted(String path, Uri uri) {
                                Toast t=Toast.makeText(ExternalData.this,"scan complete!!",Toast.LENGTH_SHORT);
                                t.show();
                            }
                        });

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;

            case R.id.b_confirm_save_as:
                save.setVisibility(View.VISIBLE);
                break;
        }
    }
}
