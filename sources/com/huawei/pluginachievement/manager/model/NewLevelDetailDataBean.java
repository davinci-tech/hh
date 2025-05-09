package com.huawei.pluginachievement.manager.model;

import java.util.ArrayList;

/* loaded from: classes9.dex */
public class NewLevelDetailDataBean {
    private ArrayList<ChildLevelDataBean> childLevelDataList;
    private int count;
    private String date;

    public String getDate() {
        return this.date;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int i) {
        this.count = i;
    }

    public ArrayList<ChildLevelDataBean> getChildLevelDataList() {
        return this.childLevelDataList;
    }

    public void setChildLevelDataList(ArrayList<ChildLevelDataBean> arrayList) {
        this.childLevelDataList = arrayList;
    }

    public static class ChildLevelDataBean {
        private String date;
        private boolean isEachFirstChildTitle;
        private boolean isEndChild;
        private boolean isFirstChild;
        private String name;
        private int newLevel;
        private String time;
        private int value;

        public String getDate() {
            return this.date;
        }

        public void setDate(String str) {
            this.date = str;
        }

        public String getTime() {
            return this.time;
        }

        public void setTime(String str) {
            this.time = str;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }

        public int getValue() {
            return this.value;
        }

        public void setValue(int i) {
            this.value = i;
        }

        public int getNewLevel() {
            return this.newLevel;
        }

        public void setNewLevel(int i) {
            this.newLevel = i;
        }

        public boolean isFirstChild() {
            return this.isFirstChild;
        }

        public void setFirstChild(boolean z) {
            this.isFirstChild = z;
        }

        public boolean isEndChild() {
            return this.isEndChild;
        }

        public void setEndChild(boolean z) {
            this.isEndChild = z;
        }

        public boolean isEachFirstChildTitle() {
            return this.isEachFirstChildTitle;
        }

        public void setEachFirstChildTitle(boolean z) {
            this.isEachFirstChildTitle = z;
        }
    }
}
