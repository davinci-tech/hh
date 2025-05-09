package com.huawei.openalliance.ad.inter.data;

import java.io.Serializable;

/* loaded from: classes5.dex */
public class AudioInfo implements Serializable {

    /* renamed from: a, reason: collision with root package name */
    private String f7041a;
    private int b;
    private int c;
    private String d;
    private String e;

    public String getUrl() {
        return this.f7041a;
    }

    public String getSha256() {
        return this.d;
    }

    public String getMime() {
        return this.e;
    }

    public int getFileSize() {
        return this.c;
    }

    public int getDuration() {
        return this.b;
    }

    public void c(String str) {
        this.e = str;
    }

    public void b(String str) {
        this.d = str;
    }

    public void b(int i) {
        this.c = i;
    }

    public void a(String str) {
        this.f7041a = str;
    }

    public void a(int i) {
        this.b = i;
    }

    public AudioInfo(com.huawei.openalliance.ad.beans.metadata.AudioInfo audioInfo) {
        if (audioInfo != null) {
            this.f7041a = audioInfo.a();
            this.b = audioInfo.b();
            this.c = audioInfo.c();
            this.d = audioInfo.d();
            this.e = audioInfo.e();
        }
    }

    public AudioInfo() {
    }
}
