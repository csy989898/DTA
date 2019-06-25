package com.example.administrator.maintenanceapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.example.administrator.maintenanceapp.Activity.chrid_view.NewInfo_msgActivity;
import com.example.administrator.maintenanceapp.R;

/*
* 新消息通知
* */
public class NewInfoActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mInfoBack1;
    private LinearLayout mNewinfoMsg;
    //private Switch swh_status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_info);
        initView();
    }

    private void initView() {
        mInfoBack1 = (ImageView) findViewById(R.id.info_back1);
        mInfoBack1.setOnClickListener(this);
        mNewinfoMsg = (LinearLayout) findViewById(R.id.newinfo_msg);
        mNewinfoMsg.setOnClickListener(this);
       // swh_status=findViewById(R.id.swh_status);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.info_back1:
                NewInfoActivity.this.finish();
                break;
            case R.id.newinfo_msg:
                startActivity(new Intent(NewInfoActivity.this,NewInfo_msgActivity.class));
                break;
        }
    }
}
