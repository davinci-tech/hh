package com.hihonor.assistant.cardmgrsdk.model.recommend;

/* loaded from: classes8.dex */
public class IntentInfo {
    public String action;
    public ExtraInfo extras;
    public String packageName;

    public void setPackageName(String str) {
        this.packageName = str;
    }

    public void setExtras(ExtraInfo extraInfo) {
        this.extras = extraInfo;
    }

    public void setAction(String str) {
        this.action = str;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public ExtraInfo getExtras() {
        return this.extras;
    }

    public String getAction() {
        return this.action;
    }
}
