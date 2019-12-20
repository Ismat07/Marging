package com.example.ismatkhanam.on_button_click;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import java.io.IOException;
import java.io.InputStream;

public class Camera extends Activity implements View.OnClickListener{

    ImageButton ib;
    Button b1;
    ImageView iv;
    Intent i;
    final static int camera_data=0;
    Bitmap bmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo);
        initializer();
        InputStream is= getResources().openRawResource(R.drawable.ic_launcher);
        bmp= BitmapFactory.decodeStream(is);
    }

    private void initializer() {
        ib=(ImageButton) findViewById(R.id.ib_take_pic);
        b1=(Button) findViewById(R.id.b_set_wall_paper);
        iv=(ImageView) findViewById(R.id.iv_return_pic);
        b1.setOnClickListener(this);
        ib.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.b_set_wall_paper:
                try {
                    getApplicationContext().setWallpaper(bmp);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.ib_take_pic:
                i=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i,camera_data);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            Bundle extras= data.getExtras();
            bmp=(Bitmap) extras.get("data");
            iv.setImageBitmap(bmp);
        }
    }
}
