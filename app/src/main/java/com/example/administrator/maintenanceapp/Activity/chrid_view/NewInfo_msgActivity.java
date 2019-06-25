package com.example.administrator.maintenanceapp.Activity.chrid_view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.maintenanceapp.R;


public class NewInfo_msgActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mMsgBack2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_info_msg);
        initView();
    }

    private void initView() {
        mMsgBack2 = (TextView) findViewById(R.id.msg_back2);
        mMsgBack2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.msg_back2:
                NewInfo_msgActivity.this.finish();
                break;
        }
    }
}
