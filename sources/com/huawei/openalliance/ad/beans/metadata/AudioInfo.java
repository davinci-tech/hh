package com.huawei.openalliance.ad.beans.metadata;

/* loaded from: classes5.dex */
public class AudioInfo {
    private int duration;
    private int fileSize;
    private String mime;
    private String sha256;
    private String url;

    public String e() {
        return this.mime;
    }

    public String d() {
        return this.sha256;
    }

    public void c(String str) {
        this.mime = str;
    }

    public int c() {
        return this.fileSize;
    }

    public void b(String str) {
        this.sha256 = str;
    }

    public void b(int i) {
        this.fileSize = i;
    }

    public int b() {
        return this.duration;
    }

    public void a(String str) {
        this.url = str;
    }

    public void a(int i) {
        this.duration = i;
    }

    public String a() {
        return this.url;
    }
}
