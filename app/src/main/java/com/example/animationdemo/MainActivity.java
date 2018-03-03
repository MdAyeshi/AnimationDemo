
package com.example.animationdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity  {

    Button b1;
    ImageView iv;
    Animation ani;
    GameView1 gv1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        gv1 = new GameView1(this);
        setContentView(R.layout.activity_main);
        iv = (ImageView)findViewById(R.id.iv);
        b1 = (Button) findViewById(R.id.but0);
        //ani = AnimationUtils.loadAnimation(this,R.anim.myanim);
        b1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(gv1);

            }
        });

    }
}
