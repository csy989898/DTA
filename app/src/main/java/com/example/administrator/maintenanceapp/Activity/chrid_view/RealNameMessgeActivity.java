package com.example.administrator.maintenanceapp.Activity.chrid_view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.maintenanceapp.R;


public class RealNameMessgeActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mRealnameBack1;
    private TextView mTxCode;
    private TextView mTxRealname;
    private TextView mTxIdcard;
    private TextView mTxPhonenum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_name_messge);
        initView();
    }

    private void initView() {
        mRealnameBack1 = (TextView) findViewById(R.id.realname_back1);
        mRealnameBack1.setOnClickListener(this);
        mTxCode = (TextView) findViewById(R.id.tx_code);
        mTxRealname = (TextView) findViewById(R.id.tx_realname);
        mTxIdcard = (TextView) findViewById(R.id.tx_idcard);
        mTxPhonenum = (TextView) findViewById(R.id.tx_phonenum);
        SharedPreferences read = getSharedPreferences("lock", MODE_WORLD_READABLE);
        String value1 = read.getString("mEtUserrealnames", "");
        String value2 = read.getString("mEtIdcards", "");
        String value3 = read.getString("mEtPhonenums", "");
        mTxRealname.setText(value1+"");
        mTxIdcard.setText(value2+"");
        mTxPhonenum.setText(value3+"");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.realname_back1:
                RealNameMessgeActivity.this.finish();
                break;
        }
    }
}
