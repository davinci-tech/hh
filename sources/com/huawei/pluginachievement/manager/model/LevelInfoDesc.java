package com.huawei.pluginachievement.manager.model;

import java.util.List;

/* loaded from: classes9.dex */
public class LevelInfoDesc {
    private int backColor;
    private int badgeImage;
    private String description;
    private int icon;
    private List<String> labelList;
    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public List<String> getLabelList() {
        return this.labelList;
    }

    public void setLabelList(List<String> list) {
        this.labelList = list;
    }

    public int getBadgeImage() {
        return this.badgeImage;
    }

    public void setBadgeImage(int i) {
        this.badgeImage = i;
    }

    public int getIcon() {
        return this.icon;
    }

    public void setIcon(int i) {
        this.icon = i;
    }

    public int getBackColor() {
        return this.backColor;
    }

    public void setBackColor(int i) {
        this.backColor = i;
    }
}
