package com.cosmetics.cosmetics.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cosmetics.cosmetics.R;
import com.cosmetics.cosmetics.SharedPrefManager;
import com.cosmetics.cosmetics.model.LoginData;
import com.cosmetics.cosmetics.model.User;
import com.cosmetics.cosmetics.viewmodel.LoginViewModel;
import com.fourhcode.forhutils.FUtilsValidation;

public class LoginActivity extends AppCompatActivity {
Button loginBtn,signUpBtn;
TextView signUpTxt,forgotPasswordTxt;
EditText ET_email,ET_password;
Typeface customFontRegular;
    LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        //setFont();
         loginViewModel= ViewModelProviders.of(this).get(LoginViewModel.class);
        Login();

        signUpTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    private void Login() {
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FUtilsValidation.isEmpty(ET_email,"من فضلك ادخل بريدك الالكترونى");
                FUtilsValidation.isEmpty(ET_password,"من فضلك ادخل كلمه المرور");
                FUtilsValidation.isLengthCorrect(ET_password.getText().toString(),5,16);
                if (!ET_email.getText().toString().equals("")
                        && !ET_password.getText().toString().equals("")
                        && (FUtilsValidation.isLengthCorrect(ET_password.getText().toString(), 5, 16))) {
                    User user = new User();
                    user.setEmail(ET_email.getText().toString());
                    user.setPassword(ET_password.getText().toString());
   /* private void setFont() {
        customFontRegular = Typeface.createFromAsset( getApplicationContext().getAssets(), "robotoFont/Roboto-Regular.ttf" );
        forgotPasswordTxt.setTypeface( customFontRegular );
    }*/
                    loginViewModel.getLogin(user,getApplicationContext()).observe(LoginActivity.this, new Observer<LoginData>() {
                        @Override
                        public void onChanged(@Nullable LoginData loginData) {
                            if(loginData!=null) {
                                Toast.makeText(LoginActivity.this, "success", Toast.LENGTH_SHORT).show();
                                SharedPrefManager.getInstance(getApplicationContext()).saveUserToken(loginData.getUserToken().toString());

                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                startActivity(intent);
                                finish();
                            }else {
                                String error = loginViewModel.getErrorMsg();
                                if (error != null) {
                                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.Email_Or_Password_is_previously_Used), Toast.LENGTH_SHORT).show();
                                }
                            }


                            }
                    });

                }
            }

        });
    }
    private void init() {
        loginBtn=findViewById(R.id.btn_login);
        signUpTxt=findViewById(R.id.T_sign_up);
        forgotPasswordTxt=findViewById(R.id.T_forgot_password);
        ET_email=findViewById(R.id.ET_email);
        ET_password=findViewById(R.id.ET_password);
    }
}
