package com.example.administrator.maintenanceapp.Activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.maintenanceapp.Activity.chrid_view.Question_Message;
import com.example.administrator.maintenanceapp.R;


/*
 * 关于我们
 * */
public class AboutWeActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mAboutweBack;
    /**
     * 客服电话
     */
    private TextView mAboutwePhone;
    /**
     * 问题反馈
     */
    private TextView mAboutweQuestion;
    /**
     * 检查更新
     */
    private TextView mAboutweVesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_we);
        initView();
        TelephonyManager tManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
    }

    private void initView() {
        mAboutweBack = (ImageView) findViewById(R.id.aboutwe_back);
        mAboutweBack.setOnClickListener(this);
        mAboutwePhone = (TextView) findViewById(R.id.aboutwe_phone);
        mAboutwePhone.setOnClickListener(this);
        mAboutweQuestion = (TextView) findViewById(R.id.aboutwe_question);
        mAboutweQuestion.setOnClickListener(this);
        mAboutweVesion = (TextView) findViewById(R.id.aboutwe_vesion);
        mAboutweVesion.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.aboutwe_back:
                AboutWeActivity.this.finish();
                break;
            case R.id.aboutwe_phone:
                callPhone( "18711476096");
                break;
            case R.id.aboutwe_question:
                /*问题反馈*/
                startActivity(new Intent(AboutWeActivity.this, Question_Message.class));
                break;
            case R.id.aboutwe_vesion:
                Toast.makeText(AboutWeActivity.this,"当前已是最新版本",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    /**
     * 拨打电话（直接拨打电话）
     * @param phoneNum 电话号码
     */
    public void callPhone(String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(intent);
    }


}
