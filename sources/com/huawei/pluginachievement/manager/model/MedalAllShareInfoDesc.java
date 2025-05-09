package com.huawei.pluginachievement.manager.model;

import java.io.Serializable;

/* loaded from: classes6.dex */
public class MedalAllShareInfoDesc implements Serializable {
    private static final long serialVersionUID = 1601083277136974325L;
    private String medalId;
    private String medalText;
    private String medalTypeLevel;

    public MedalAllShareInfoDesc(String str, String str2, String str3) {
        this.medalId = str;
        this.medalText = str2;
        this.medalTypeLevel = str3;
    }

    public String acquireMedalId() {
        return this.medalId;
    }

    public String acquireMedalText() {
        return this.medalText;
    }
}
