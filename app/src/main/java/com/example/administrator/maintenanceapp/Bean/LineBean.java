package com.example.administrator.maintenanceapp.Bean;

/**
 * Created by CSY on 2019/6/17.
 */

public class LineBean  {
    private String lName;
    private int lIcon;

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public int getlIcon() {
        return lIcon;
    }

    public void setlIcon(int lIcon) {
        this.lIcon = lIcon;
    }

    public LineBean(String lName, int lIcon) {
        this.lName = lName;
        this.lIcon = lIcon;
    }

    public LineBean() {
        super();
    }
}
