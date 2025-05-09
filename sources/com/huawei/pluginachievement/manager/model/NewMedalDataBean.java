package com.huawei.pluginachievement.manager.model;

import java.util.ArrayList;

/* loaded from: classes6.dex */
public class NewMedalDataBean {
    private ArrayList<ChildMedalDataBean> mChildMedalDataList;
    private String mTitle;

    public String getTitle() {
        return this.mTitle;
    }

    public void setTitle(String str) {
        this.mTitle = str;
    }

    public ArrayList<ChildMedalDataBean> getChildMedalDataList() {
        return this.mChildMedalDataList;
    }

    public void setChildMedalDataList(ArrayList<ChildMedalDataBean> arrayList) {
        this.mChildMedalDataList = arrayList;
    }

    public static class ChildMedalDataBean {
        private ArrayList<MedalInfoDesc> mMedalInfoList;

        public ArrayList<MedalInfoDesc> getMedalInfoList() {
            return this.mMedalInfoList;
        }

        public void setMedalInfoList(ArrayList<MedalInfoDesc> arrayList) {
            this.mMedalInfoList = arrayList;
        }
    }
}
