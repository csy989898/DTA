package com.example.administrator.maintenanceapp.Activity.chrid_view;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.administrator.maintenanceapp.Bean.IDCard;
import com.example.administrator.maintenanceapp.R;

import java.text.ParseException;

public class RealNameAuthenticationActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mRealnameBack;
    /**
     * 姓名
     */
    private EditText mEtUserrealname;
    /**
     * 身份证号
     */
    private EditText mEtIdcard;
    /**
     * 请填写手机号
     */
    private EditText mEtPhonenum;
    /**
     * 获取验证码
     */
    private Button mBnSmsCode;
    /**
     * 短信验证码
     */
    private EditText mEtSmsCode;
    /**
     * 提交信息后信息
     */
    private TextView mTextView;
    /**
     * 不可修改
     */
    private TextView mTvTermOfService;
    /**
     * 提交信息
     */
    private Button mBnImmediateRegistration;
    private View mView;
    private NotificationManager notificationManager;
    private Notification notification;
    Bitmap LargeBitmap =null;
    private  static final int NOTIFYID=1;
    private Context mContext=RealNameAuthenticationActivity.this;
    private int num_code=0;
    public boolean T_SUBMIT=true;
    private String mEtPhonenums=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_name_authentication);
        LargeBitmap= BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
        notificationManager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        initView();

    }

    private void initView() {
        mRealnameBack = (TextView) findViewById(R.id.realname_back);
        mRealnameBack.setOnClickListener(this);
        mEtUserrealname = (EditText) findViewById(R.id.et_userrealname);
        mEtIdcard = (EditText) findViewById(R.id.et_idcard);
        mEtPhonenum = (EditText) findViewById(R.id.et_phonenum);
        mBnSmsCode = (Button) findViewById(R.id.bn_sms_code);
        mBnSmsCode.setOnClickListener(this);
        mEtSmsCode = (EditText) findViewById(R.id.et_sms_code);
        mTextView = (TextView) findViewById(R.id.textView);
        mTvTermOfService = (TextView) findViewById(R.id.tv_termOfService);
        mBnImmediateRegistration = (Button) findViewById(R.id.bn_immediateRegistration);
        mBnImmediateRegistration.setOnClickListener(this);
        mView = (View) findViewById(R.id.view);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.realname_back:
                RealNameAuthenticationActivity.this.finish();
                break;
            case R.id.bn_sms_code:
                mEtPhonenums = mEtPhonenum.getText().toString().trim();
                if(TextUtils.isEmpty(mEtPhonenums)){
                    Toast.makeText(RealNameAuthenticationActivity.this,"请输入手机号",Toast.LENGTH_SHORT).show();
                }else{
                    if(!isMobileNO(mEtPhonenums)){
                        Toast.makeText(RealNameAuthenticationActivity.this,"请输入正确的手机号",Toast.LENGTH_SHORT).show();
                    }else{
                        setNotifiction();
                    }
                }
                break;
            case R.id.bn_immediateRegistration:
                /*提交信息*/
                String mEtUserrealnames = mEtUserrealname.getText().toString().trim();
                String mEtIdcards = mEtIdcard.getText().toString().trim();
                mEtPhonenums = mEtPhonenum.getText().toString().trim();
                String mEtSmsCodes=mEtSmsCode.getText().toString().trim();
                if(TextUtils.isEmpty(mEtUserrealnames)||TextUtils.isEmpty(mEtIdcards)||TextUtils.isEmpty(mEtPhonenums)||TextUtils.isEmpty(mEtSmsCodes)){
                    Toast.makeText(RealNameAuthenticationActivity.this,"提交信息不能为空",Toast.LENGTH_SHORT).show();
                }else{
                    try {
                        if(!IDCard.IDCardValidate(mEtIdcards+"")){
                            Toast.makeText(RealNameAuthenticationActivity.this,"请输入正确的身份证号码",Toast.LENGTH_SHORT).show();
                            return;
                        }else if(!isMobileNO(mEtPhonenums)){
                            Toast.makeText(RealNameAuthenticationActivity.this,"请输入正确的手机号",Toast.LENGTH_SHORT).show();
                        }else if(!mEtSmsCodes.equals(""+num_code)){
                            Toast.makeText(RealNameAuthenticationActivity.this,"验证码错误",Toast.LENGTH_SHORT).show();
                        }else{
                            SharedPreferences.Editor editor = getSharedPreferences("lock", MODE_WORLD_WRITEABLE).edit();
                            editor.putString("mEtUserrealnames", mEtUserrealnames);
                            editor.putString("mEtIdcards", mEtIdcards);
                            editor.putString("mEtPhonenums", mEtPhonenums);
                            editor.commit();
                            editor.putBoolean("lock",true);
                            T_SUBMIT=true;
                            startActivity(new Intent(RealNameAuthenticationActivity.this,RealNameMessgeActivity.class));
                            finish();
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }

    public void setNotifiction(){
        num_code = (int) ((Math.random() * 9 + 1) * 100000);
        //定义一个PendingIntent点击Notification后启动一个Activity
        Intent it = new Intent(RealNameAuthenticationActivity.this, RealNameAuthenticationActivity.class);
        PendingIntent pit = PendingIntent.getActivity(mContext, 0, it, 0);

        //设置图片,通知标题,发送时间,提示方式等属性
        Notification.Builder mBuilder = new Notification.Builder(this);
        mBuilder.setContentTitle("验证码")                        //标题
                .setContentText("验证码为~"+num_code)      //内容
                .setSubText("——请输入我")                    //内容下面的一小段文字
                .setTicker("收到发送过来的验证码~")             //收到信息后状态栏显示的文字信息
                .setWhen(System.currentTimeMillis())           //设置通知时间
                .setSmallIcon(R.mipmap.ic_launcher)            //设置小图标
                .setLargeIcon(LargeBitmap)                     //设置大图标
                .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE)    //设置默认的三色灯与振动器
                .setSound(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.biaobiao))  //设置自定义的提示音
                .setAutoCancel(true)                           //设置点击后取消Notification
                .setContentIntent(pit);                        //设置PendingIntent
        notification = mBuilder.build();
        notificationManager.notify(NOTIFYID, notification);

    }

    public static boolean isMobileNO(String mobileNums) {
        /**
         * @param str
         * @return 待检测的字符串
         */
        String telRegex = "^((13[0-9])|(14[5,7,9])|(15[^4])|(18[0-9])|(17[0,1,3,5,6,7,8]))\\d{8}$";// "[1]"代表下一位为数字可以是几，"[0-9]"代表可以为0-9中的一个，"[5,7,9]"表示可以是5,7,9中的任意一位,[^4]表示除4以外的任何一个,\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobileNums))
            return false;
        else
            return mobileNums.matches(telRegex);
    }

}
