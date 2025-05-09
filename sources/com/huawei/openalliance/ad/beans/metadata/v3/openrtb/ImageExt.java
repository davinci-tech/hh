package com.huawei.openalliance.ad.beans.metadata.v3.openrtb;

import java.io.Serializable;

/* loaded from: classes5.dex */
public class ImageExt implements Serializable {
    private static final long serialVersionUID = 687537555709319441L;
    private int checkSHA256Flag;
    private String format;
    private String sha256;
    private long size;

    public int d() {
        return this.checkSHA256Flag;
    }

    public String c() {
        return this.format;
    }

    public void b(String str) {
        this.format = str;
    }

    public String b() {
        return this.sha256;
    }

    public void a(String str) {
        this.sha256 = str;
    }

    public void a(long j) {
        this.size = j;
    }

    public void a(int i) {
        this.checkSHA256Flag = i;
    }

    public long a() {
        return this.size;
    }
}
