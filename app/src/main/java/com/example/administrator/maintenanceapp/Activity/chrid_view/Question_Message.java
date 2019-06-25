package com.example.administrator.maintenanceapp.Activity.chrid_view;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.design.widget.TextInputLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.maintenanceapp.Activity.Question.Bimp;
import com.example.administrator.maintenanceapp.Activity.Question.FileUtils;
import com.example.administrator.maintenanceapp.R;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Question_Message extends MPermissionsActivity{
    private GridView noScrollgridview;

    private GridAdapter adapter;

    private TextView activity_selectimg_send;

    private TextInputLayout email,code;
    private TextView messg_btn;
    private RatingBar rb_normal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_messge);
        //动态判断权限
        requestPermission(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 0x0001);

        email = (TextInputLayout) findViewById(R.id.email);
        messg_btn=(TextView)findViewById(R.id.messg_btn);
        messg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Question_Message.this.finish();
            }
        });

        rb_normal=(RatingBar)findViewById(R.id.rb_normal);
        rb_normal.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(Question_Message.this, "评分为:" + String.valueOf(rating),
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * 点击事件模拟提交操作
     * @param view
     */
   /* public void login(View view){
        if ("".equals(email.getEditText().getText().toString())){
            email.setError("邮箱不能为空");
        }*//*else if ("".equals(code.getEditText().getText().toString())){
            code.setError("验证码不能为空");
        }*//*else {
           // code.setErrorEnabled(false);
            email.setErrorEnabled(false);
            Toast.makeText(this, "提交成功", Toast.LENGTH_SHORT).show();
        }
    }*/

    /**
     * 权限成功回调函数
     *
     * @param requestCode
     */
    public void permissionSuccess(int requestCode) {
        super.permissionSuccess(requestCode);
        switch (requestCode) {
            case 0x0001:
                Init();
                break;
        }
    }

    public void Init() {
        noScrollgridview = (GridView)findViewById(R.id.noScrollgridview);//初始化控件
        noScrollgridview.setSelector(new ColorDrawable(Color.TRANSPARENT));//设置gridview分割线为透明
        adapter = new GridAdapter(this); //初始化适配器
        adapter.update();//更新数据
        noScrollgridview.setAdapter(adapter);//绑定图片数据
        //绑定点击事件
        noScrollgridview.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3)
            {
                //判断点击的是否是图片
                if (arg2 == Bimp.bmp.size()) {
                    //显示选择提示窗
                    new PopupWindows(Question_Message.this, noScrollgridview);
                }
                else {
                    //进入图片预览页面
                    Intent intent = new Intent(Question_Message.this, PhotoActivity.class);
                    //传递图片标识
                    intent.putExtra("ID", arg2);
                    startActivity(intent);
                }
            }
        });
        activity_selectimg_send = (TextView)findViewById(R.id.activity_selectimg_send);
        activity_selectimg_send.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {
                List<String> list = new ArrayList<String>();
                for (int i = 0; i < Bimp.drr.size(); i++ )
                {
                    String Str = Bimp.drr.get(i).substring(Bimp.drr.get(i).lastIndexOf("/") + 1,
                            Bimp.drr.get(i).lastIndexOf("."));
                    list.add(FileUtils.SDPATH + Str + ".JPEG");
                }
                // 高清的压缩图片全部就在 list 路径里面了
                // 高清的压缩过的 bmp 对象 都在 Bimp.bmp里面
                // 完成上传服务器后 .........
                FileUtils.deleteDir();//删除保存内容
            }
        });
    }

    @SuppressLint("HandlerLeak")
    public class GridAdapter extends BaseAdapter
    {
        private LayoutInflater inflater; // 视图容器
        private int selectedPosition = -1;// 选中的位置
        private boolean shape;
        public boolean isShape()
        {
            return shape;
        }
        public void setShape(boolean shape)
        {
            this.shape = shape;
        }
        public GridAdapter(Context context)
        {
            inflater = LayoutInflater.from(context);
        }
        public void update()
        {
            loading();
        }
        public int getCount()
        {
            return (Bimp.bmp.size() + 1);
        }
        public Object getItem(int arg0) {
            return null;
        }
        public long getItemId(int arg0)
        {

            return 0;
        }
        public void setSelectedPosition(int position)
        {
            selectedPosition = position;
        }
        public int getSelectedPosition()
        {
            return selectedPosition;
        }

        /**
         * ListView Item设置
         */
        public View getView(int position, View convertView, ViewGroup parent)
        {
            final int coord = position;
            ViewHolder holder = null;
            if (convertView == null)
            {
                convertView = inflater.inflate(R.layout.item_published_grida, parent, false);
                holder = new ViewHolder();
                holder.image = (ImageView)convertView.findViewById(R.id.item_grida_image);
                convertView.setTag(holder);
            }
            else
            {
                holder = (ViewHolder)convertView.getTag();
            }

            if (position == Bimp.bmp.size())
            {
                holder.image.setImageBitmap(BitmapFactory.decodeResource(getResources(),
                        R.drawable.icon_addpic_unfocused));
//                if (position == 9)
//                {
//                    holder.image.setVisibility(View.GONE);
//                }
            }
            else
            {
                holder.image.setImageBitmap(Bimp.bmp.get(position));
            }
            return convertView;
        }

        public class ViewHolder
        {
            public ImageView image;
        }

        Handler handler = new Handler()
        {
            public void handleMessage(Message msg)
            {
                switch (msg.what)
                {
                    case 1:
                        adapter.notifyDataSetChanged();
                        break;
                }
                super.handleMessage(msg);
            }
        };

        public void loading()
        {
            new Thread(new Runnable()
            {
                public void run()
                {
                    while (true)
                    {
                        if (Bimp.max == Bimp.drr.size())
                        {
                            Message message = new Message();
                            message.what = 1;
                            handler.sendMessage(message);
                            break;
                        }
                        else
                        {
                            try
                            {
                                String path = Bimp.drr.get(Bimp.max);
                                System.out.println(path);
                                Bitmap bm = Bimp.revitionImageSize(path);
                                Bimp.bmp.add(bm);
                                String newStr = path.substring(path.lastIndexOf("/") + 1,
                                        path.lastIndexOf("."));
                                FileUtils.saveBitmap(bm, "" + newStr);
                                Bimp.max += 1;
                                Message message = new Message();
                                message.what = 1;
                                handler.sendMessage(message);
                            }
                            catch (IOException e)
                            {

                                e.printStackTrace();
                            }
                        }
                    }
                }
            }).start();
        }
    }

    public String getString(String s)
    {
        String path = null;
        if (s == null) return "";
        for (int i = s.length() - 1; i > 0; i++ )
        {
            s.charAt(i);
        }
        return path;
    }

    protected void onRestart()
    {
        adapter.update();
        super.onRestart();
    }

    public class PopupWindows extends PopupWindow
    {

        public PopupWindows(Context mContext, View parent)
        {

            View view = View.inflate(mContext, R.layout.item_popupwid, null);
            view.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.fade_ins));
            LinearLayout ll_popup = (LinearLayout)view.findViewById(R.id.ll_popup);
            ll_popup.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.push_bottom_in_2));

            setWidth(ViewGroup.LayoutParams.FILL_PARENT);
            setHeight(ViewGroup.LayoutParams.FILL_PARENT);
            setBackgroundDrawable(new BitmapDrawable());
            setFocusable(true);
            setOutsideTouchable(true);
            setContentView(view);
            showAtLocation(parent, Gravity.BOTTOM, 0, 0);
            update();
            //初始化控件
            Button bt1 = (Button)view.findViewById(R.id.item_popupwindows_camera);
            Button bt2 = (Button)view.findViewById(R.id.item_popupwindows_Photo);
            Button bt3 = (Button)view.findViewById(R.id.item_popupwindows_cancel);
            //绑定点击事件
            bt1.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    photo();
                    //关闭弹窗
                    dismiss();
                }
            });
            bt2.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    //进入预览页
                    Intent intent = new Intent(Question_Message.this, TestPicActivity.class);
                    startActivity(intent);
                    dismiss();
                }
            });
            bt3.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    dismiss();
                }
            });

        }
    }

    private static final int TAKE_PICTURE = 0x000000;

    private String path = "";

    public void photo()
    {
        //开启相机
        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //创建文件
        File file = new File(Environment.getExternalStorageDirectory() + "/myimage/",
                String.valueOf(System.currentTimeMillis()) + ".jpg");
        path = file.getPath();
        Uri imageUri = Uri.fromFile(file);
        //传递参数
        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        //开始跳转确定回调标识
        startActivityForResult(openCameraIntent, TAKE_PICTURE);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        switch (requestCode)
        {
            case TAKE_PICTURE:
                if (Bimp.drr.size() < 9 && resultCode == -1)
                {
                    Bimp.drr.add(path);
                }
                break;
        }
    }

}
