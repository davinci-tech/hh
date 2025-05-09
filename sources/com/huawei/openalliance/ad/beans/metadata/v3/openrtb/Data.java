package com.huawei.openalliance.ad.beans.metadata.v3.openrtb;

import java.io.Serializable;

/* loaded from: classes5.dex */
public class Data implements Serializable {
    private static final long serialVersionUID = 98359189956699056L;
    private int len;
    private int type;
    private String value;

    public int c() {
        return this.len;
    }

    public void b(int i) {
        this.len = i;
    }

    public String b() {
        return this.value;
    }

    public void a(String str) {
        this.value = str;
    }

    public void a(int i) {
        this.type = i;
    }

    public int a() {
        return this.type;
    }
}
