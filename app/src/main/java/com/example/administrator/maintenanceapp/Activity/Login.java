package com.example.administrator.maintenanceapp.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.maintenanceapp.MainActivity;
import com.example.administrator.maintenanceapp.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout main_linearLayout;
    private EditText login_name;
    private EditText login_passwd;
    private Button login_loginbtn;
    private TextView login_reg_btn;
    private TextView login_recover;
    private TextView login_customer;
    private LinearLayout main_layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        initView();
        setinitview();
    }

    public void setinitview() {
        main_linearLayout = findViewById(R.id.main_layout);
        main_linearLayout.getBackground().setAlpha(180);
    }

    private void initView() {
        login_name = (EditText) findViewById(R.id.login_name);
        login_passwd = (EditText) findViewById(R.id.login_passwd);
        login_loginbtn = (Button) findViewById(R.id.login_loginbtn);
        login_reg_btn = (TextView) findViewById(R.id.login_reg_btn);
        login_recover = (TextView) findViewById(R.id.login_recover);
        login_customer = (TextView) findViewById(R.id.login_customer);
        main_layout = (LinearLayout) findViewById(R.id.main_layout);

        login_loginbtn.setOnClickListener(this);
        login_reg_btn.setOnClickListener(this);
        login_recover.setOnClickListener(this);
        login_customer.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_loginbtn:
            //登录
                submit();
                break;
            case R.id.login_reg_btn:
            //注册账号
                startActivity(new Intent(Login.this,RegActivity.class));
                break;
            case R.id.login_recover:
            //忘记密码
                showCustomizeDialog();
                break;
            case R.id.login_customer:
            //游客登录
                startActivity(new Intent(Login.this,MainActivity.class));
                break;
        }
    }

    private void showCustomizeDialog() {
    /*
     * dialog_customize.xml可自定义更复杂的View
     */
        AlertDialog.Builder customizeDialog =
                new AlertDialog.Builder(Login.this);
        final View dialogView = LayoutInflater.from(Login.this)
                .inflate(R.layout.dailog_customse,null);
        customizeDialog.setTitle("找回密码");
        customizeDialog.setView(dialogView);
        customizeDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 获取EditView中的输入内容
                        EditText edit_text =
                                (EditText) dialogView.findViewById(R.id.edit_text);

                        if (isEmail(edit_text.getText().toString().trim()) &&
                                edit_text.getText().toString().trim().length()<=31){

                            Toast.makeText(Login.this,
                                    "邮箱验证成功"+edit_text.getText().toString(),
                                    Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(Login.this,
                                    "邮箱格式错误"+edit_text.getText().toString(),
                                    Toast.LENGTH_SHORT).show();
                        }

                        /*Toast.makeText(Login.this,
                                edit_text.getText().toString(),
                                Toast.LENGTH_SHORT).show();*/
                    }
                });
        customizeDialog.setNegativeButton("关闭",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                        dialog.dismiss();
                    }
                });
        customizeDialog.show();
    }

    /*邮箱的验证*/
    public static boolean isEmail(String email){
        if (null==email || "".equals(email)) return false;
        //Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}"); //简单匹配
        Pattern p =  Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");//复杂匹配
        Matcher m = p.matcher(email);
        return m.matches();
    }

    private void submit() {
        // validate
        String name = login_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
            return;
        }

        String passwd = login_passwd.getText().toString().trim();
        if (TextUtils.isEmpty(passwd)) {
            Toast.makeText(this, "请输入账号密码", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }



}
