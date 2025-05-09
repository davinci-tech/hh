package com.huawei.openalliance.ad.beans.vast;

import com.huawei.openalliance.ad.annotations.a;

/* loaded from: classes5.dex */
public class VastIcon {
    private int height;

    @a
    private String htmlResource;

    @a
    private String iconIframRes;
    private String program;
    private StaticResource staticResource;
    private int width;
    private String xPos;
    private String yPos;

    public void e(String str) {
        this.htmlResource = str;
    }

    public void d(String str) {
        this.iconIframRes = str;
    }

    public void c(String str) {
        this.yPos = str;
    }

    public StaticResource c() {
        return this.staticResource;
    }

    public void b(String str) {
        this.xPos = str;
    }

    public void b(int i) {
        this.width = i;
    }

    public int b() {
        return this.width;
    }

    public void a(String str) {
        this.program = str;
    }

    public void a(StaticResource staticResource) {
        this.staticResource = staticResource;
    }

    public void a(int i) {
        this.height = i;
    }

    public int a() {
        return this.height;
    }
}
