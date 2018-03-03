
package com.example.animationdemo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

/**
 * Created by USER on 31-Aug-17.
 */

public class GameView1 extends SurfaceView {
    SurfaceHolder sh;
    Bitmap bp, bp1, bg1, bg2, cr1, cr2;

    Bitmap arr[] = new Bitmap[2];
    Bitmap arr1[] = new Bitmap[2];
    Bitmap arr2[] = new Bitmap[2];
    int x = 20, k = 0, k1 = 0, p = 0, q = 240, q1 = 0, q2 = 300;
    MyThread mt;


    GameView1(Context c) {
        super(c);

        sh = getHolder();
        mt = new MyThread(this);
        sh.addCallback(new Callback() {
            @SuppressLint("WrongCall")
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {

                Canvas c = sh.lockCanvas();
                onDraw(c);
                sh.unlockCanvasAndPost(c);
                mt.isrunning(true);
                mt.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

            }
        });

        bp = BitmapFactory.decodeResource(getResources(), R.drawable.mario);
        bp1 = BitmapFactory.decodeResource(getResources(), R.drawable.mario1);

        arr[0] = bp;
        arr[1] = bp1;

        bg1 = BitmapFactory.decodeResource(getResources(), R.drawable.backgroung1);
        bg2 = BitmapFactory.decodeResource(getResources(), R.drawable.backgroung3);
        arr1[0] = bg1;
        arr1[1] = bg2;


        cr1 = BitmapFactory.decodeResource(getResources(), R.drawable.crow1);
        cr2 = BitmapFactory.decodeResource(getResources(), R.drawable.crow2);

        arr2[0] = cr1;
        arr2[1] = cr2;

    }

    @Override
    protected void onDraw(Canvas canvas) {

        //canvas.drawColor(Color.CYAN);

        int y1 = bp.getHeight();
        int y2 = canvas.getWidth();

        Rect r1 = new Rect(0, 0, arr1[p].getWidth(), arr1[p].getHeight());
        Rect r2 = new Rect(0, 0, canvas.getWidth(), canvas.getHeight());

        canvas.drawBitmap(arr1[p], r1, r2, null);

        Rect rect1 = new Rect(x, canvas.getHeight() - arr[k].getHeight() - q, arr[k].getWidth(), arr[k].getHeight());

        canvas.drawBitmap(arr[k], x, canvas.getHeight() - arr[k].getHeight() - q, null);


        Rect rect2 = new Rect(canvas.getWidth() - arr2[k1].getWidth() - q1, canvas.getHeight() - arr2[k1].getHeight() - q2, arr2[k1].getWidth(), arr2[k1].getHeight());
        canvas.drawBitmap(arr2[k1], canvas.getWidth() - arr2[k1].getWidth() - q1, canvas.getHeight() - arr2[k1].getHeight() - q2, null);


        q1 = (q1 + 30) % canvas.getWidth();

        if (q1 == 0) {
            q2 = 300 + (int) (Math.random() * 300);
            p = p + 1;
            if (p == 2) {
                p = 0;
            }

        }
        Log.d("hello", "rect1 right" + rect1.right);
        Log.d("hello", "rect2 left" + rect2.left);

        Log.d("hello", "rect1 bottom" + rect1.bottom);
        Log.d("hello", "rect2 top" + rect2.top + "\n");

        if (rect1.right >= rect2.left) {

            mt.isrunning(false);
            canvas.drawColor(Color.RED);
            canvas.drawText("Game Over", canvas.getWidth() / 2, canvas.getHeight() / 2, null);


        }

        k = k + 1;
        if (k == 2) {
            k = 0;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            q = q + 300;
            x = x + 20;
        }
        if (event.getAction() == MotionEvent.ACTION_UP) {
            q = q - 300;
        }
        return true;
    }
}
