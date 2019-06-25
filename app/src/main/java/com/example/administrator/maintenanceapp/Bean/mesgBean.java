package com.example.administrator.maintenanceapp.Bean;

/**
 * Created by CSY on 2019/6/24.
 */

public class mesgBean{
        private int iId;
        private String iName;

        public mesgBean() {
        }

        public mesgBean(int iId, String iName) {
            this.iId = iId;
            this.iName = iName;
        }

        public int getiId() {
            return iId;
        }

        public String getiName() {
            return iName;
        }

        public void setiId(int iId) {
            this.iId = iId;
        }

        public void setiName(String iName) {
            this.iName = iName;
        }
}
