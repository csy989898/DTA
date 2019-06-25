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
 * Created by CSY on 2019/6/24.
 */

public class NewsAdapter extends BaseAdapter {

    private LinkedList<NewsBean> newsData;
    private Context mContext;

    public NewsAdapter(LinkedList<NewsBean> newsData, Context mContext) {
        this.newsData = newsData;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return newsData.size();
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
        convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list_news,parent,false);
        ImageView News_Img = (ImageView) convertView.findViewById(R.id.News_Img);
        TextView News_Title = (TextView) convertView.findViewById(R.id.News_Title);
        TextView News_Content = (TextView) convertView.findViewById(R.id.News_Content);
        News_Img.setBackgroundResource(newsData.get(position).getaNewsIcon());
        News_Title.setText(newsData.get(position).getNewsTitle());
        News_Content.setText(newsData.get(position).getNewsContent());
        return convertView;
    }
}
