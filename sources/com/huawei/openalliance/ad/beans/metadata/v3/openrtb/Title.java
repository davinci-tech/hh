package com.huawei.openalliance.ad.beans.metadata.v3.openrtb;

import java.io.Serializable;

/* loaded from: classes5.dex */
public class Title implements Serializable {
    private static final long serialVersionUID = -6451040467314102476L;
    private int len;
    private String text;

    public int b() {
        return this.len;
    }

    public void a(String str) {
        this.text = str;
    }

    public void a(int i) {
        this.len = i;
    }

    public String a() {
        return this.text;
    }
}
