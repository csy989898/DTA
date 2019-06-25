package com.example.administrator.maintenanceapp.Bean;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.maintenanceapp.R;

import java.util.LinkedList;

/**
 * Created by CSY on 2019/6/17.
 */

public class LineAdapter extends BaseAdapter {

    private LinkedList<LineBean> mData;
    private Context mContext;

    public LineAdapter(LinkedList<LineBean> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list_line,parent,false);
        ImageView img_icon = (ImageView) convertView.findViewById(R.id.img_icon);
        TextView txt_aName = (TextView) convertView.findViewById(R.id.txt_aName);
        ImageView Lnext = (ImageView) convertView.findViewById(R.id.txt_aNext);

        img_icon.setBackgroundResource(mData.get(position).getlIcon());
        txt_aName.setText(mData.get(position).getlName());
        Lnext.setBackgroundResource(R.mipmap.next);
        return convertView;
    }
}
