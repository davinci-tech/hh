package com.huawei.openalliance.ad.beans.metadata;

import java.io.Serializable;

/* loaded from: classes5.dex */
public class NegativeFb implements Serializable {
    private static final long serialVersionUID = 30456300;
    private long id;
    private String label;
    private int type;

    public int c() {
        return this.type;
    }

    public String b() {
        return this.label;
    }

    public void a(String str) {
        this.label = str;
    }

    public void a(long j) {
        this.id = j;
    }

    public void a(int i) {
        this.type = i;
    }

    public long a() {
        return this.id;
    }
}
