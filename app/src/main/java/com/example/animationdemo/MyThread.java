package com.example.animationdemo;

import android.annotation.SuppressLint;
import android.graphics.Canvas;

/**
 * Created by USER on 31-Aug-17.
 */

public class MyThread extends Thread
{
    boolean flag = false;
    GameView1 gameView1;

    MyThread(GameView1 gv)
    {
        gameView1 = gv;
    }

    void isrunning(boolean flag)
    {
        this.flag = flag;
    }


    @SuppressLint("WrongCall")
    @Override
    public void run() {

        while (flag)
        {
            Canvas c = null;
            try {

                Thread.sleep(200);
                synchronized (gameView1.getHolder()) {
                    c = gameView1.getHolder().lockCanvas();
                    gameView1.onDraw(c);

                }
            }catch (Exception e){}
            finally {
                gameView1.getHolder().unlockCanvasAndPost(c);
            }


        }
    }
}
