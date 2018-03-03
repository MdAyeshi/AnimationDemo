package com.example.animationdemo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.EventLog.Event;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.widget.Toast;


import static android.R.attr.radius;

/**
 * Created by USER on 24-Aug-17.
 */

public class GameView extends SurfaceView  {

    Bitmap bm1,bm2,bg;
    SurfaceHolder sh;

    GameThread gt;
    Thread t;
    Context ct;
    int x=10,k=0,k1=0;
    Bitmap [] arr = new Bitmap[2];


    public GameView(Context context) {
        super(context);
        ct =context;
        sh = getHolder();
        gt = new GameThread(this);
        t = new Thread(gt);

        sh.addCallback(new Callback() {
            @SuppressLint("WrongCall")
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {

               // Canvas c = sh.lockCanvas();
                //onDraw(c);
                ///sh.unlockCanvasAndPost(c);
                Log.d("GAmeDemo","In Surface Created");
                gt.setRunning(true);
                t.start();

            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
                Log.d("GAmeDemo","In Surface Changed");

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                Log.d("GAmeDemo","In Surface Destroyed");

            }
        });

        bm1 = BitmapFactory.decodeResource(getResources(),R.drawable.mario);
        bm2 = BitmapFactory.decodeResource(getResources(),R.drawable.mario1);

        arr[0]=bm1;
        arr[1]=bm2;

        bg = BitmapFactory.decodeResource(getResources(),R.drawable.backgroung3);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d("GAmeDemo","In ON Draw"+ "Width is"+canvas.getWidth() +"Height is"+ canvas.getHeight());


        //canvas.drawColor(Color.YELLOW);

        Rect r1 = new Rect(0,0,bg.getWidth(),bg.getHeight());
        Rect r2 = new Rect(0,0,canvas.getWidth(),canvas.getHeight());

        canvas.drawBitmap(bg,r1,r2,null);

        canvas.drawBitmap(arr[k],x,canvas.getHeight()-arr[k].getHeight()-250,null);
        x = x+20;
        k=k+1;

        if(k==2)
        {
            k=0;
        }
        if(x>=canvas.getWidth())
        {
            x=10;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(event.getAction()==MotionEvent.ACTION_DOWN)
        {

        }
        return true;
    }
}
