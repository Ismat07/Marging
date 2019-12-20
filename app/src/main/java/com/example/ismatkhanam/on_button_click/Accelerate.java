package com.example.ismatkhanam.on_button_click;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Accelerate extends Activity implements SensorEventListener{

    float x,y,sensor_x,sensor_y;
    Bitmap ball;
    SensorManager sm;
    MyBringBackSurface ourSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sm=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
        if(sm.getSensorList(Sensor.TYPE_ACCELEROMETER).size() != 0){
            Sensor s=sm.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
            sm.registerListener(this,s,SensorManager.SENSOR_DELAY_NORMAL);
        }

        ball= BitmapFactory.decodeResource(getResources(),R.drawable.greenball);
        x = y = sensor_x = sensor_y = 0;
        ourSurfaceView=new MyBringBackSurface(this);
        ourSurfaceView.resume();
        setContentView(ourSurfaceView);
    }

    @Override
    protected void onPause() {
        sm.unregisterListener(this);
        super.onPause();
    }

    @Override
    public void onSensorChanged(SensorEvent e) {
        try {
            Thread.sleep(16);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        sensor_x = e.values[0];
        sensor_y = e.values[1];
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public class MyBringBackSurface extends SurfaceView implements Runnable{

        SurfaceHolder our_holder;
        Thread our_thread=null;
        boolean is_running=false;

        public MyBringBackSurface(Context context) {
            super(context);
            our_holder=getHolder();
        }

        public void pause(){
            is_running=false;
            while (true){
                try {
                    our_thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            }
            our_thread=null;
        }

        public void resume(){
            is_running=true;
            our_thread=new Thread(this);
            our_thread.start();
        }

        @Override
        public void run() {
            while (is_running){
                if (!our_holder.getSurface().isValid())
                    continue;

                Canvas canvas=our_holder.lockCanvas();
                canvas.drawRGB(02,02,150);
                float center_x = canvas.getWidth()/2;
                float center_y = canvas.getHeight()/2;
                canvas.drawBitmap(ball,center_x + sensor_x * 20,center_y + sensor_y * 20,null);

                our_holder.unlockCanvasAndPost(canvas);
            }
        }
    }
}
