package com.example.animationdemo;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.util.Log;

/**
 * Created by USER on 24-Aug-17.
 */

public class GameThread implements Runnable {
    GameView gv;
    boolean flag = false;

    GameThread(GameView gv)
    {
        this.gv = gv;
    }

    void setRunning(boolean flag)
    {
          this.flag = flag;
    }

    @SuppressLint("WrongCall")
    @Override
    public void run() {
        Log.d("GAmeDemo","Run");
        while (flag)
        {
            Canvas c = null;
            try
            {
                Thread.sleep(400);
                c = gv.getHolder().lockCanvas();
                synchronized (gv.getHolder())
                {
                    gv.onDraw(c);
                }

            }
            catch (Exception e){}
            finally {
                if(c!=null)
                {
                    gv.getHolder().unlockCanvasAndPost(c);
                }
            }
        }
    }
}
