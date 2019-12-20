package com.example.ismatkhanam.on_button_click;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.View;

public class MyBringBack extends View {

    Bitmap gBall;
    float chang_Y;
    Typeface font;

    public MyBringBack(Context context) {
        super(context);
        gBall= BitmapFactory.decodeResource(getResources(),R.drawable.greenball);
        chang_Y=0;
        //font=Typeface.createFromAsset(context.getAssets(),"G-Unit.TTF");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.CYAN);

        Paint textPaint=new Paint();
        textPaint.setARGB(50,254,10,50);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(50);
        //textPaint.setTypeface(font);
        canvas.drawText("mybringback",canvas.getWidth()/2,200,textPaint);

        canvas.drawBitmap(gBall,(canvas.getWidth()/2),chang_Y,null);
        if (chang_Y < canvas.getHeight()){
            chang_Y += 10;
        }
        else{
            chang_Y=0;
        }

        Rect middleRect=new Rect();
        middleRect.set(0,400,canvas.getWidth(),550);
        Paint outBlue=new Paint();
        outBlue.setColor(Color.BLUE);
        canvas.drawRect(middleRect,outBlue);

        invalidate();
    }
}
