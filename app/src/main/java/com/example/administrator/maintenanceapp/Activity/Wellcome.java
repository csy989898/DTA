package com.example.administrator.maintenanceapp.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.administrator.maintenanceapp.R;

public class Wellcome extends AppCompatActivity {

    private boolean run = false;
    private ProgressBar mProgressBar;
    private final Handler handler = new Handler();
    private Animation animation = null;
    private ImageView img_pgbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wellcome);


        initView();
        // mProgressBar=findViewById(R.id.progessbar);
        animation = AnimationUtils.loadAnimation(this,
                R.anim.anim_alpha);
        img_pgbar.startAnimation(animation);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(Wellcome.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);


       /* run = false;
        handler.postDelayed(task, 3000);*/

        /*new Thread(){
            @Override
            public void run() {

                
                super.run();
            }
        };*/

    }

     /*
    * 实时刷新
    * */
   /* private final Runnable task = new Runnable() {
        @Override
        public void run() {
            if (run) {
                startActivity(new Intent(OnePageActivity.this, MainActivity.class));
                finish();
                handler.postDelayed(this, 3000);
            }
        }
    };*/

    private void initView() {
        img_pgbar = (ImageView) findViewById(R.id.img_pgbar);
    }

}
