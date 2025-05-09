package com.huawei.health.messagecenter.model;

/* loaded from: classes3.dex */
public class MessageExt {
    private long beginTime;
    private String description;
    private long endTime;
    private int groupMsgId;
    private String imgUrl;
    private String imgUrlDaxi;
    private int jumpType;
    private String jumpUrl;
    private String title;
    private int weight;

    public int getGroupMsgId() {
        return this.groupMsgId;
    }

    public void setGroupMsgId(int i) {
        this.groupMsgId = i;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getImgUrl() {
        return this.imgUrl;
    }

    public void setImgUrl(String str) {
        this.imgUrl = str;
    }

    public int getJumpType() {
        return this.jumpType;
    }

    public void setJumpType(int i) {
        this.jumpType = i;
    }

    public String getJumpUrl() {
        return this.jumpUrl;
    }

    public void setJumpUrl(String str) {
        this.jumpUrl = str;
    }

    public long getBeginTime() {
        return this.beginTime;
    }

    public void setBeginTime(long j) {
        this.beginTime = j;
    }

    public long getEndTime() {
        return this.endTime;
    }

    public void setEndTime(long j) {
        this.endTime = j;
    }

    public int getWeight() {
        return this.weight;
    }

    public void setWeight(int i) {
        this.weight = i;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public String getImgUrlDaxi() {
        return this.imgUrlDaxi;
    }

    public void setImgUrlDaxi(String str) {
        this.imgUrlDaxi = str;
    }

    public String toString() {
        return "MessageExt{groupMsgId=" + this.groupMsgId + ", title='" + this.title + "', jumpType=" + this.jumpType + ", beginTime=" + this.beginTime + ", endTime=" + this.endTime + ", description='" + this.description + "'}";
    }
}
