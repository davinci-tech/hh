package com.huawei.pluginachievement.manager.model;

/* loaded from: classes6.dex */
public class MedalInfo {
    private String content;
    private int disableResId;
    private int enableResId;
    private String medalId;
    private String text;

    public MedalInfo(String str, int i, int i2, String str2, String str3) {
        this.medalId = str;
        this.enableResId = i;
        this.disableResId = i2;
        this.text = str2;
        this.content = str3;
    }

    public String getMedalId() {
        return this.medalId;
    }

    public int getEnableResId() {
        return this.enableResId;
    }

    public int getDisableResId() {
        return this.disableResId;
    }

    public String getText() {
        return this.text;
    }

    public String getContent() {
        return this.content;
    }
}
