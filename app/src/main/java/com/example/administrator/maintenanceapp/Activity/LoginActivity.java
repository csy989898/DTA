package com.example.administrator.maintenanceapp.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.maintenanceapp.MainActivity;
import com.example.administrator.maintenanceapp.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    /**
     * 请输入账号
     */
    private EditText mLoginName;
    /**
     * 请输入密码
     */
    private EditText mLoginPasswd;
    /**
     * 记住密码
     */
    private CheckBox mLoginRember;
    /**
     * 自动登录
     */
    private CheckBox mLoginAuto;
    /**
     * 登录
     */
    private TextView mLoginLoginbtn;
    /**
     * 注册账号
     */
    private TextView mLoginRegBtn;
    /**
     * 游客登录
     */
    private TextView mLoginCustomer;
    private SharedPreferences sp;
    private String userNameValue, passwordValue;
    /**
     * 注册
     */
    private TextView mBackReg;
    private TextView login_recover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
       /* initView();
        sp = getSharedPreferences("userInfo", 0);
        String name = sp.getString("USER_NAME", "");
        String pass = sp.getString("PASSWORD", "");

        boolean choseRemember = sp.getBoolean("cbRememberPass", false);
        boolean choseAutoLogin = sp.getBoolean("autologin1", false);


        //如果上次选了记住密码，那进入登录页面也自动勾选记住密码，并填上用户名和密码
        if (choseRemember) {
            mLoginName.setText(name);
            mLoginPasswd.setText(pass);
            mLoginRember.setChecked(true);
        }
        //如果上次登录选了自动登录，那进入登录页面也自动勾选自动登录
        if (choseAutoLogin) {
            mLoginAuto.setChecked(true);
            Toast.makeText(LoginActivity.this, "3秒后自动登录",
                    Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    LoginActivity.this.finish();
                }
            }, 3000);
        }

        mLoginLoginbtn.setOnClickListener(new View.OnClickListener() {

            // 默认可登录帐号demo,密码123
            @Override
            public void onClick(View arg0) {
                userNameValue = mLoginName.getText().toString();
                passwordValue = mLoginPasswd.getText().toString();
                SharedPreferences.Editor editor = sp.edit();

                if (userNameValue.equals("demo")
                        && passwordValue.equals("123")) {
                    Toast.makeText(LoginActivity.this, "登录成功",
                            Toast.LENGTH_SHORT).show();

                    //保存用户名和密码
                    editor.putString("USER_NAME", userNameValue);
                    editor.putString("PASSWORD", passwordValue);

                    //是否记住密码
                    if (mLoginRember.isChecked()) {
                        editor.putBoolean("cbRememberPass", true);
                    } else {
                        editor.putBoolean("cbRememberPass", false);
                    }


                    //是否自动登录
                    if (mLoginAuto.isChecked()) {
                        editor.putBoolean("autologin1", true);
                    } else {
                        editor.putBoolean("autologin1", false);
                    }
                    editor.commit();

                   *//* Intent intent =new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);*//*

                    Intent intent = new Intent();
                    intent.setClass(LoginActivity.this, MainActivity.class);
                    startActivity(intent);


                } else {
                    Toast.makeText(LoginActivity.this, "用户名或密码错误，请重新登录!",
                            Toast.LENGTH_SHORT).show();
                }

            }

        });

*/
    }

    @Override
    public void onClick(View v) {
       /* switch (v.getId()) {
            case R.id.login_loginbtn:
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
                break;
            case R.id.login_reg_btn:
                startActivity(new Intent(LoginActivity.this, RegActivity.class));
                finish();
                break;
            case R.id.login_customer:
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
                break;
            case R.id.back_reg:
                startActivity(new Intent(LoginActivity.this, RegActivity.class));
                finish();
                break;
            case R.id.login_recover:
                //startActivity(new Intent(LoginActivity.this, RecoverPasswdActivity.class));
                break;
        }*/
    }


   /* private void initView() {
        mLoginName = (EditText) findViewById(R.id.login_name);
        mLoginPasswd = (EditText) findViewById(R.id.login_passwd);
        mLoginRember = (CheckBox) findViewById(R.id.login_rember);
        mLoginAuto = (CheckBox) findViewById(R.id.login_auto);
        mLoginLoginbtn = (TextView) findViewById(R.id.login_loginbtn);
        mLoginLoginbtn.setOnClickListener(this);
        mLoginRegBtn = (TextView) findViewById(R.id.login_reg_btn);
        mLoginRegBtn.setOnClickListener(this);
        mLoginCustomer = (TextView) findViewById(R.id.login_customer);
        mLoginCustomer.setOnClickListener(this);
        mBackReg = (TextView) findViewById(R.id.back_reg);
        mBackReg.setOnClickListener(this);
        login_recover=findViewById(R.id.login_recover);
        login_recover.setOnClickListener(this);
    }*/
}
