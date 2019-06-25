package com.example.administrator.maintenanceapp.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.maintenanceapp.Activity.chrid_view.RealNameAuthenticationActivity;
import com.example.administrator.maintenanceapp.Activity.chrid_view.RealNameMessgeActivity;
import com.example.administrator.maintenanceapp.R;
import com.example.administrator.maintenanceapp.View.WiperSwitch;


/*
 * 通用
 * */
public class GeneralActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mMsgBack4;
    private LinearLayout mLanguge;
    private WiperSwitch mWiperSwitc;
    private LinearLayout mGongneng;
    private LinearLayout mShiming;
    private LinearLayout mShouquan;
    private boolean SQM_NUM=false;//是否授权
    private boolean NAME_REG=false;//是否实名认证
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);
        initView();
        RealNameAuthenticationActivity realNameAuthenticationActivity=new RealNameAuthenticationActivity();
        NAME_REG=realNameAuthenticationActivity.T_SUBMIT;
    }

    private void initView() {
        mMsgBack4 = (ImageView) findViewById(R.id.msg_back4);
        mMsgBack4.setOnClickListener(this);
        mLanguge = (LinearLayout) findViewById(R.id.languge);
        mLanguge.setOnClickListener(this);
        mWiperSwitc = (WiperSwitch) findViewById(R.id.wiperSwitc);
        mWiperSwitc.setOnClickListener(this);
        mGongneng = (LinearLayout) findViewById(R.id.gongneng);
        mGongneng.setOnClickListener(this);
        mShiming = (LinearLayout) findViewById(R.id.shiming);
        mShiming.setOnClickListener(this);
        mShouquan = (LinearLayout) findViewById(R.id.shouquan);
        mShouquan.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.msg_back4:
                GeneralActivity.this.finish();
                break;
            case R.id.languge:
                Toast.makeText(GeneralActivity.this,"多语言",Toast.LENGTH_SHORT).show();
                break;
            case R.id.wiperSwitc:
                break;
            case R.id.gongneng:
                Toast.makeText(GeneralActivity.this,"功能",Toast.LENGTH_SHORT).show();
                break;
            case R.id.shiming:
                Toast.makeText(GeneralActivity.this,"实名认证",Toast.LENGTH_SHORT).show();
                /*跳转*/
                if (!NAME_REG){
                    /*以实名认证*/
                    startActivity(new Intent(GeneralActivity.this,RealNameMessgeActivity.class));
                }else{
                    startActivity(new Intent(GeneralActivity.this,RealNameAuthenticationActivity.class));
                }
                break;
            case R.id.shouquan:
                Toast.makeText(GeneralActivity.this,"授权设备",Toast.LENGTH_SHORT).show();
                /*弹框*/
                if(SQM_NUM){
                    setDialogPhone();
                }else{
                    setDialog();
                }
                break;
        }
    }

    public void setDialogPhone(){
        final EditText et = new EditText(this);
        new AlertDialog.Builder(this).setTitle("授权设备")
                .setMessage("请输入管理者手机号")
                .setIcon(android.R.drawable.sym_def_app_icon)
                .setView(et)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //按下确定键后的事件
                        String PgoneNum=et.getText().toString().trim();
                        if(!TextUtils.isEmpty(PgoneNum)){
                            if(isMobileNO(PgoneNum)){
                                SQM_NUM=true;
                            }else{
                                Toast.makeText(getApplicationContext(), "错误号码："+et.getText().toString(),Toast.LENGTH_LONG).show();
                            }
                           }
                        }
                })
                .setNegativeButton("取消",null).show();
        }

    public static boolean isMobileNO(String mobileNums) {
        /**
         * 判断字符串是否符合手机号码格式
         * 移动号段: 134,135,136,137,138,139,147,150,151,152,157,158,159,170,178,182,183,184,187,188
         * 联通号段: 130,131,132,145,155,156,170,171,175,176,185,186
         * 电信号段: 133,149,153,170,173,177,180,181,189
         * @param str
         * @return 待检测的字符串
         */
        String telRegex = "^((13[0-9])|(14[5,7,9])|(15[^4])|(18[0-9])|(17[0,1,3,5,6,7,8]))\\d{8}$";// "[1]"代表下一位为数字可以是几，"[0-9]"代表可以为0-9中的一个，"[5,7,9]"表示可以是5,7,9中的任意一位,[^4]表示除4以外的任何一个,\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobileNums))
            return false;
        else
            return mobileNums.matches(telRegex);
    }

    public void setDialog(){
       AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog alert = builder.setIcon(R.mipmap.ic_launcher)
                .setTitle("授权设备")
                .setMessage("你已授权")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(GeneralActivity.this, "你点击了取消按钮~", Toast.LENGTH_SHORT).show();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(GeneralActivity.this, "你点击了确定按钮~", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNeutralButton("中立", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(GeneralActivity.this, "你点击了中立按钮~", Toast.LENGTH_SHORT).show();
                    }
                }).create();             //创建AlertDialog对象
        alert.show();                    //显示对话框

    }

}
