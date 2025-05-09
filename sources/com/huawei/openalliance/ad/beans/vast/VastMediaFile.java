package com.huawei.openalliance.ad.beans.vast;

import com.huawei.openalliance.ad.annotations.a;

/* loaded from: classes5.dex */
public class VastMediaFile {
    private String delivery;
    private int height;
    private String id;
    private String type;

    @a
    private String url;
    private int width;

    public void d(String str) {
        this.url = str;
    }

    public String d() {
        return this.url;
    }

    public void c(String str) {
        this.type = str;
    }

    public int c() {
        return this.height;
    }

    public void b(String str) {
        this.delivery = str;
    }

    public void b(int i) {
        this.height = i;
    }

    public int b() {
        return this.width;
    }

    public void a(String str) {
        this.id = str;
    }

    public void a(int i) {
        this.width = i;
    }

    public String a() {
        return this.type;
    }
}
