package com.example.ismatkhanam.on_button_click;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class GFXSurface extends Activity implements View.OnTouchListener{

    MyBringBackSurface our_surface_view;
    float x,y,sX,sY,fX,fY,dX,dY,scaledX,scaledY,aniX,aniY;
    Bitmap test,plus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        our_surface_view = new MyBringBackSurface(this);
        our_surface_view.setOnTouchListener(this);
        x=0;
        y=0;
        sX=0;
        sY=0;
        fX=0;
        fY=0;
        dX=dY=aniX=aniY=scaledX=scaledY=0;
        test= BitmapFactory.decodeResource(getResources(),R.drawable.greenball);
        plus= BitmapFactory.decodeResource(getResources(),R.drawable.plus);
        setContentView(our_surface_view);
    }

    @Override
    protected void onPause() {
        super.onPause();
        our_surface_view.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        our_surface_view.resume();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        x=event.getX();
        y=event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                sX=event.getX();
                sY=event.getY();
                dX=dY=aniX=aniY=scaledX=scaledY=fX=fY=0;
                break;
            case MotionEvent.ACTION_UP:
                fX=event.getX();
                fY=event.getY();
                dX=fX-sX;
                dY=fY-sY;
                scaledX=dX/30;
                scaledY=dY/30;
                x=y=0;
                break;
        }
        return true;
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
                if (x != 0 && y != 0){
                    canvas.drawBitmap(test,x-(test.getWidth()/2),y-(test.getHeight()/2),null);
                }
                if (sX != 0 && sY != 0){
                    canvas.drawBitmap(plus,sX-(plus.getWidth()/2),sY-(plus.getHeight()/2),null);
                }
                if (fX != 0 && fY != 0){
                    canvas.drawBitmap(test,fX-(test.getWidth()/2)-aniX,fY-(test.getHeight()/2)-aniY,null);
                    canvas.drawBitmap(plus,fX-(plus.getWidth()/2),fY-(plus.getHeight()/2),null);
                }
                aniX=aniX+scaledX;
                aniY=aniY+scaledY;
                our_holder.unlockCanvasAndPost(canvas);
            }
        }
    }
}
