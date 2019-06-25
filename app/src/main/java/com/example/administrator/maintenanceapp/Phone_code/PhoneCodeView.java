package com.example.administrator.maintenanceapp.Phone_code;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.administrator.maintenanceapp.R;

public class PhoneCodeView extends AppCompatActivity {
    private PhoneCodeActivity pc_1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_code_view);
        pc_1 = (PhoneCodeActivity) findViewById(R.id.pc_1);
        //注册事件回调（可写，可不写）
        pc_1.setOnInputListener(new PhoneCodeActivity.OnInputListener() {
            @Override
            public void onSucess(String code) {
                //TODO:
                test();
            }

            @Override
            public void onInput() {
                //TODO:
                // test();
            }
        });
    }

    private void test(){
        //获得验证码
        String phoneCode = pc_1.getPhoneCode();
        Toast.makeText(PhoneCodeView.this,"获得验证码为:"+phoneCode, Toast.LENGTH_LONG).show();
    }
}
