package com.huawei.health.marketing.request;

import java.util.List;

/* loaded from: classes3.dex */
public class InformationInfo {
    private List<String> avatarList;
    private int id;
    private int readCount;

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public int getReadCount() {
        return this.readCount;
    }

    public void setReadCount(int i) {
        this.readCount = i;
    }

    public List<String> getAvatarList() {
        return this.avatarList;
    }

    public void setAvatarList(List<String> list) {
        this.avatarList = list;
    }

    public String toString() {
        return "InformationInfo{id=" + this.id + ", readCount=" + this.readCount + '}';
    }
}
