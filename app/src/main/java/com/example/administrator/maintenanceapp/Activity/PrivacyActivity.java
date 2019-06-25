package com.example.administrator.maintenanceapp.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.maintenanceapp.R;
import com.example.administrator.maintenanceapp.View.WiperSwitch;


/*
 * 隐私
 * */
public class PrivacyActivity extends AppCompatActivity implements WiperSwitch.OnChangedListener, View.OnClickListener {

    private ImageView mMsgBack3;
    private WiperSwitch mWiperSwitch2;
    private WiperSwitch mWiperSwitch3;
    private WiperSwitch mWiperSwitch4;
    private WiperSwitch mWiperSwitch5;
    private WiperSwitch mWiperSwitch6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy);
        initView();
        //实例化WiperSwitch
        WiperSwitch wiperSwitch = (WiperSwitch) findViewById(R.id.wiperSwitch1);

        //设置初始状态为false
        wiperSwitch.setChecked(false);

        //设置监听
        wiperSwitch.setOnChangedListener(this);

    }

    @Override
    public void OnChanged(WiperSwitch wiperSwitch, boolean checkState) {
        if (checkState) {
            Toast.makeText(PrivacyActivity.this, "开关开启了", Toast.LENGTH_SHORT).show();
        } else if (!checkState) {
            Toast.makeText(PrivacyActivity.this, "开关关闭了", Toast.LENGTH_SHORT).show();
        }
    }

    private void initView() {
        mMsgBack3 = (ImageView) findViewById(R.id.msg_back3);
        mMsgBack3.setOnClickListener(this);
        mWiperSwitch2 = (WiperSwitch) findViewById(R.id.wiperSwitch2);
        mWiperSwitch2.setOnClickListener(this);
        mWiperSwitch3 = (WiperSwitch) findViewById(R.id.wiperSwitch3);
        mWiperSwitch3.setOnClickListener(this);
        mWiperSwitch4 = (WiperSwitch) findViewById(R.id.wiperSwitch4);
        mWiperSwitch4.setOnClickListener(this);
        mWiperSwitch5 = (WiperSwitch) findViewById(R.id.wiperSwitch5);
        mWiperSwitch5.setOnClickListener(this);
        mWiperSwitch6 = (WiperSwitch) findViewById(R.id.wiperSwitch6);
        mWiperSwitch6.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.msg_back3:
                PrivacyActivity.this.finish();
                break;
            case R.id.wiperSwitch2:
                break;
            case R.id.wiperSwitch3:
                break;
            case R.id.wiperSwitch4:
                break;
            case R.id.wiperSwitch5:
                break;
            case R.id.wiperSwitch6:
                break;
        }
    }
}
