package com.example.administrator.maintenanceapp.RecyclerView;

import java.util.List;

/**
 * Created by Administrator on 2019/6/21.
 */

public class NewsPhotoBean {
    public List<String> list;//封面图片的集合
    private int type;//排版类型
    private String title;//标题
    private String f_time;//发布时间
    private String author;//作者

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public String getF_time() {
        return f_time;
    }

    public void setF_time(String f_time) {
        this.f_time = f_time;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


}

