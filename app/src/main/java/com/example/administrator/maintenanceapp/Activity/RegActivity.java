package com.example.administrator.maintenanceapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.administrator.maintenanceapp.OKHTTP.okHTTP;
import com.example.administrator.maintenanceapp.R;

import org.json.JSONException;
import org.json.JSONObject;

public class RegActivity extends AppCompatActivity implements View.OnClickListener {
    /**
     * 请输入账号
     */
    private EditText mUserName;
    /**
     * 请输入密码
     */
    private EditText mUserPasswd;
    /**
     * 请确认密码
     */
    private EditText mUserRepasswd;
    /**
     * 请输入邮箱
     */
    private EditText mUserEmail;
    /**
     * 获取验证码
     */
    private TextView mYanzmBtn;
    /**
     * 请输入验证码
     */
    private EditText mUserEmailnumber;
    /**
     * 我已阅读并同意服务条款
     */
    private CheckBox mUserCheckbox;
    /**
     * 注册
     */
    private TextView mUserRegbtn;
    private int num = 0;
    /**
     * 登录
     */
    private TextView mBackLogin;


    Handler myHandler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            String strs=msg.toString().trim();
            if(msg.what==1){
                setDate(strs);
            }
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        initView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user_regbtn:
                setRegDate();
                break;
            case R.id.yanzm_btn:
                num = (int) ((Math.random() * 9 + 1) * 100000);
                Toast.makeText(RegActivity.this, "验证码为" + num,
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.back_login:
                startActivity(new Intent(RegActivity.this,Login.class));
                finish();
                break;
        }
    }

    public void setRegesterDate(String json){
        String ip="192.168.88.36";
        String portNum="8080";
        String RegInterface="/a/mobile/userApp/mobileRegister";
        int msgWhat=1;
        okHTTP okHTTP=new okHTTP(json,ip,portNum,RegInterface,msgWhat,myHandler);
        okHTTP.getConnection();
    }
    /*
    * http://192.168.88.36:8080/a/login/mobile/userApp/mobileRegister
    * */
    public void setDate(String str){
        try {
            Log.e("Regster--------",""+str);
            JSONObject jsonObject=new JSONObject(str);
            String strings=jsonObject.getString("");
            JSONObject object=new JSONObject(strings);
            Log.e("Regster",""+object.getString(""));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public void setRegDate() {
        String UserRegname = mUserName.getText().toString().trim();
        String UserRegPasswd = mUserPasswd.getText().toString().trim();
        String UserRegRepasswd = mUserRepasswd.getText().toString().trim();
        String UserRegEmail = mUserEmail.getText().toString().trim();
        String UserRegYZM = mUserEmailnumber.getText().toString().trim();
        String strPattern = "^[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$";
        if (TextUtils.isEmpty(UserRegname)) {
            Toast.makeText(RegActivity.this, "请输入账号",
                    Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(UserRegPasswd)) {
            Toast.makeText(RegActivity.this, "请输入密码",
                    Toast.LENGTH_SHORT).show();
        } else if (!UserRegRepasswd.equals(UserRegPasswd)) {
            Toast.makeText(RegActivity.this, "请重新输入密码",
                    Toast.LENGTH_SHORT).show();
        } else {
            if (TextUtils.isEmpty(UserRegEmail)) {
                Toast.makeText(RegActivity.this, "请输入邮箱",
                        Toast.LENGTH_SHORT).show();
            } else {
                if (!isEmail(UserRegEmail)) {
                    Toast.makeText(RegActivity.this, "邮箱格式错误",
                            Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(UserRegYZM)) {
                    Toast.makeText(RegActivity.this, "请输入验证码",
                            Toast.LENGTH_SHORT).show();
                } else if (!UserRegYZM.equals(num + "")) {
                    Toast.makeText(RegActivity.this, "验证码错误",
                            Toast.LENGTH_SHORT).show();
                } /*else if (!mUserCheckbox.isChecked()) {
                    Toast.makeText(RegActivity.this, "是否同意服务协议",
                            Toast.LENGTH_SHORT).show();
                } */else {
                    startActivity(new Intent(RegActivity.this, LoginActivity.class));
                    finish();
                }
            }
        }

         /*userName,password ,email*/
        String json="{\"userName\":\""+UserRegname+"\",\"password\":\"" + UserRegPasswd + "\",\"email\":\"" + UserRegEmail + "\"}";
        setRegesterDate(json);


    }

    public static boolean isEmail(String strEmail) {
        String strPattern = "^[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$";
        if (TextUtils.isEmpty(strPattern)) {
            return false;
        } else {
            return strEmail.matches(strPattern);
        }
    }


    private void initView() {
        mUserName = (EditText) findViewById(R.id.user_name);
        mUserPasswd = (EditText) findViewById(R.id.user_passwd);
        mUserRepasswd = (EditText) findViewById(R.id.user_repasswd);
        mUserEmail = (EditText) findViewById(R.id.user_email);
        mYanzmBtn = (TextView) findViewById(R.id.yanzm_btn);
        mYanzmBtn.setOnClickListener(this);
        mUserEmailnumber = (EditText) findViewById(R.id.user_emailnumber);
        //mUserCheckbox = (CheckBox) findViewById(R.id.user_checkbox);
        mUserRegbtn = (TextView) findViewById(R.id.user_regbtn);
        mUserRegbtn.setOnClickListener(this);
        mBackLogin = (TextView) findViewById(R.id.back_login);
        mBackLogin.setOnClickListener(this);
    }

}
