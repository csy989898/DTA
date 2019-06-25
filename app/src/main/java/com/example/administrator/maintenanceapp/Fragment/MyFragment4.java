package com.example.administrator.maintenanceapp.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.maintenanceapp.Activity.AboutWeActivity;
import com.example.administrator.maintenanceapp.Activity.GeneralActivity;
import com.example.administrator.maintenanceapp.Activity.Login;
import com.example.administrator.maintenanceapp.Activity.NewInfoActivity;
import com.example.administrator.maintenanceapp.Activity.PrivacyActivity;
import com.example.administrator.maintenanceapp.Activity.accuntActivity;
import com.example.administrator.maintenanceapp.Bean.LineAdapter;
import com.example.administrator.maintenanceapp.Bean.LineBean;
import com.example.administrator.maintenanceapp.R;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by csy on 2019/5/21 .
 */
public class MyFragment4 extends Fragment {

    private List<LineBean> mData = null;
    private Context mContext;
    private LineAdapter mAdapter = null;
    private ListView list_line;
    private int num_postion=0;
    public MyFragment4() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_content4,container,false);
        mContext = getActivity();
        list_line = (ListView) view.findViewById(R.id.line_list);
        mData = new LinkedList<LineBean>();
        mData.add(new LineBean( "账号与安全", R.mipmap.line1));
        mData.add(new LineBean( "新消息通知", R.mipmap.line2));
        mData.add(new LineBean( "隐私", R.mipmap.line3));
        mData.add(new LineBean( "通用", R.mipmap.line4));
        mData.add(new LineBean( "关于我们", R.mipmap.line5));
        mAdapter = new LineAdapter((LinkedList<LineBean>) mData, mContext);
        list_line.setAdapter(mAdapter);
        list_line.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(mContext,"你点击了第"+position+"列",Toast.LENGTH_LONG).show();
                switch (position){
                    case 0:
                        /*账号与安全*/
                        startActivity(new Intent(getActivity(), accuntActivity.class));
                        break;
                    case 1:
                        /*新消息通知*/
                        startActivity(new Intent(getActivity(),NewInfoActivity.class));
                        break;
                    case 2:
                        /*隐私*/
                        startActivity(new Intent(getActivity(),PrivacyActivity.class));
                        break;
                    case 3:
                        /*通用*/
                        startActivity(new Intent(getActivity(),GeneralActivity.class));
                        break;
                    case 4:
                        /*关于我们*/
                        startActivity(new Intent(getActivity(),AboutWeActivity.class));
                        break;

                }

            }
        });
        Log.e("HEHE", "第四个Fragment");
        return view;
    }
}
