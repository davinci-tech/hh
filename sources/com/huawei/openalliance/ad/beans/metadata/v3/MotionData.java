package com.huawei.openalliance.ad.beans.metadata.v3;

import java.io.Serializable;

/* loaded from: classes5.dex */
public class MotionData implements Serializable {
    private static final long serialVersionUID = 5993334213402798519L;
    private long dataId;
    private int duration;
    private String filePath;
    private String format;
    private int height;
    private String sha256;
    private int size;
    private String url;
    private int width;

    public String i() {
        return this.filePath;
    }

    public String h() {
        return this.sha256;
    }

    public String g() {
        return this.url;
    }

    public int f() {
        return this.duration;
    }

    public int e() {
        return this.size;
    }

    public void d(String str) {
        this.filePath = str;
    }

    public void d(int i) {
        this.duration = i;
    }

    public String d() {
        return this.format;
    }

    public void c(String str) {
        this.sha256 = str;
    }

    public void c(int i) {
        this.size = i;
    }

    public int c() {
        return this.height;
    }

    public void b(String str) {
        this.url = str;
    }

    public void b(int i) {
        this.height = i;
    }

    public int b() {
        return this.width;
    }

    public void a(String str) {
        this.format = str;
    }

    public void a(long j) {
        this.dataId = j;
    }

    public void a(int i) {
        this.width = i;
    }

    public long a() {
        return this.dataId;
    }
}
