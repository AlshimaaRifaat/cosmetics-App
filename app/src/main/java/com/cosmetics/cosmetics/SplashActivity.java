package com.cosmetics.cosmetics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import com.cosmetics.cosmetics.activity.HomeActivity;
import com.cosmetics.cosmetics.activity.LoginActivity;

public class SplashActivity extends AppCompatActivity {
    String userTokenValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

       userTokenValue= SharedPrefManager.getInstance(this).getUserToken();


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
                    if(userTokenValue==null)
                    {
                        Intent intent=new Intent( SplashActivity.this,LoginActivity.class);
                        startActivity( intent );

                   }else if (userTokenValue!=null){
                        Intent intent=new Intent( SplashActivity.this,HomeActivity.class);
                        startActivity( intent );


                    }

                }
            }
        };

        timer.start();

    }

}
