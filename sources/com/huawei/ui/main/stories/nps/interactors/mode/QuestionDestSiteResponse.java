package com.huawei.ui.main.stories.nps.interactors.mode;

import java.util.ArrayList;

/* loaded from: classes7.dex */
public class QuestionDestSiteResponse {
    private ArrayList<CountryInfo> countryInfo;
    private int resCode;

    public static class CountryInfo {
        private String areaCode;
        private String areaName;
        private String countryCode;
        private String countryName;
        private String serverAddress;

        public String getAreaCode() {
            return this.areaCode;
        }

        public void setAreaCode(String str) {
            this.areaCode = str;
        }

        public String getAreaName() {
            return this.areaName;
        }

        public void setAreaName(String str) {
            this.areaName = str;
        }

        public String getCountryCode() {
            return this.countryCode;
        }

        public void setCountryCode(String str) {
            this.countryCode = str;
        }

        public String getServerAddress() {
            return this.serverAddress;
        }

        public void setServerAddress(String str) {
            this.serverAddress = str;
        }

        public String getCountryName() {
            return this.countryName;
        }

        public void setCountryName(String str) {
            this.countryName = str;
        }
    }

    public int getResCode() {
        return this.resCode;
    }

    public void setResCode(int i) {
        this.resCode = i;
    }

    public ArrayList<CountryInfo> getCountryInfo() {
        return this.countryInfo;
    }

    public void setCountryInfo(ArrayList<CountryInfo> arrayList) {
        this.countryInfo = arrayList;
    }
}
