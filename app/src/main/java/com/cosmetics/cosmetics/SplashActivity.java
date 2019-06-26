package com.cosmetics.cosmetics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.cosmetics.cosmetics.activity.LoginActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
      /*  sharedPref=getSharedPreferences( "default",Context.MODE_PRIVATE );
        Login=sharedPref.getString( "login_to_home",null );*/
        Thread timer=new Thread(  )
        {
            @Override
            public void run() {
                super.run();
                try {
                    sleep( 3000 );

                }catch (InterruptedException e)
                {
                    e.printStackTrace();
                }finally {

                    /*if (Login!=null)
                    {
                        Intent intent=new Intent( SplashActivity.this,HomeActivity.class);
                        startActivity( intent );
                    }else
                    {
                        Intent intent=new Intent( SplashActivity.this,LoginActivity.class);
                        startActivity( intent );
                    }*/
                    Intent intent=new Intent( SplashActivity.this,LoginActivity.class);
                    startActivity( intent );
                    finish();
                }
            }
        };

        timer.start();

    }

}
