package com.cosmetics.cosmetics.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cosmetics.cosmetics.R;
import com.cosmetics.cosmetics.SharedPrefManager;
import com.cosmetics.cosmetics.model.LoginData;
import com.cosmetics.cosmetics.model.RegisterData;
import com.cosmetics.cosmetics.model.User;
import com.cosmetics.cosmetics.viewmodel.LoginViewModel;
import com.fourhcode.forhutils.FUtilsValidation;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.btn_sign_up)
    Button signUpBtn;
    @BindView(R.id.ET_full_name)
    EditText ET_fullname;
    @BindView(R.id.ET_phone)
    EditText ET_phone;
    @BindView(R.id.ET_email)
    EditText ET_email;
    @BindView(R.id.ET_password)
    EditText ET_password;
    Unbinder unbinder;

    LoginViewModel loginViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        unbinder= ButterKnife.bind(this,this);
        loginViewModel= ViewModelProviders.of(this).get(LoginViewModel.class);
        register();

    }

    private void register() {
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FUtilsValidation.isEmpty(ET_fullname,"please,enter your full name");
                FUtilsValidation.isEmpty(ET_phone,"please,enter your phone");
                FUtilsValidation.isEmpty(ET_email,"please,enter your email address");
                FUtilsValidation.isEmpty(ET_password,"please,enter your password");
                FUtilsValidation.isLengthCorrect(ET_password.getText().toString(),5,16);
                if (!ET_fullname.getText().toString().equals("")
                        && !ET_phone.getText().toString().equals("")
                        &&!ET_email.getText().toString().equals("")
                        && !ET_password.getText().toString().equals("")
                        && (FUtilsValidation.isLengthCorrect(ET_password.getText().toString(), 5, 16))) {
                    User user = new User();
                    user.setFullname(ET_fullname.getText().toString());
                    user.setPhone(ET_phone.getText().toString());
                    user.setEmail(ET_email.getText().toString());
                    user.setPassword(ET_password.getText().toString());
   /* private void setFont() {
        customFontRegular = Typeface.createFromAsset( getApplicationContext().getAssets(), "robotoFont/Roboto-Regular.ttf" );
        forgotPasswordTxt.setTypeface( customFontRegular );
    }*/
                    loginViewModel.getRegister(user,getApplicationContext()).observe(RegisterActivity.this, new Observer<RegisterData>() {
                        @Override
                        public void onChanged(@Nullable RegisterData registerData) {
                            if(registerData!=null) {
                                Toast.makeText(RegisterActivity.this, "success", Toast.LENGTH_SHORT).show();
                               // SharedPrefManager.getInstance(getApplicationContext()).saveUserToken(registerData.getToken());
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }else {
                                String error = loginViewModel.getErrorMsg();
                                if (error != null) {
                                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.Check_the_fields_you_entered), Toast.LENGTH_SHORT).show();
                                }
                            }


                        }
                    });

                }
            }

        });
    }
}
