package com.example.administrator.maintenanceapp.Bean;

/**
 * Created by CSY on 2019/6/24.
 */

public class NewsBean {
    private String NewsTitle;
    private String NewsContent;
    private int aNewsIcon;

    public NewsBean() {
    }

    public NewsBean(String newsTitle, String newsContent, int aNewsIcon) {
        NewsTitle = newsTitle;
        NewsContent = newsContent;
        this.aNewsIcon = aNewsIcon;
    }

    public String getNewsTitle() {
        return NewsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        NewsTitle = newsTitle;
    }

    public String getNewsContent() {
        return NewsContent;
    }

    public void setNewsContent(String newsContent) {
        NewsContent = newsContent;
    }

    public int getaNewsIcon() {
        return aNewsIcon;
    }

    public void setaNewsIcon(int aNewsIcon) {
        this.aNewsIcon = aNewsIcon;
    }
}
