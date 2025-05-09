package com.huawei.openalliance.ad.beans.metadata.v3.openrtb;

import com.huawei.openalliance.ad.annotations.d;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class Video implements Serializable {
    private static final long serialVersionUID = -6156262595056906295L;
    private int checkSHA256Flag;
    private long duration;
    private String format;

    @d(a = "H")
    private int height;
    private String localPath;
    private String sha256;
    private long size;
    private String url;

    @d(a = "W")
    private int width;

    public String i() {
        return this.localPath;
    }

    public int h() {
        return this.checkSHA256Flag;
    }

    public String g() {
        return this.format;
    }

    public String f() {
        return this.sha256;
    }

    public long e() {
        return this.size;
    }

    public void d(String str) {
        this.localPath = str;
    }

    public long d() {
        return this.duration;
    }

    public void c(String str) {
        this.format = str;
    }

    public void c(int i) {
        this.checkSHA256Flag = i;
    }

    public int c() {
        return this.height;
    }

    public void b(String str) {
        this.sha256 = str;
    }

    public void b(long j) {
        this.size = j;
    }

    public void b(int i) {
        this.height = i;
    }

    public int b() {
        return this.width;
    }

    public void a(String str) {
        this.url = str;
    }

    public void a(long j) {
        this.duration = j;
    }

    public void a(int i) {
        this.width = i;
    }

    public String a() {
        return this.url;
    }
}
