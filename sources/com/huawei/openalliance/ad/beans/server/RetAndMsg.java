package com.huawei.openalliance.ad.beans.server;

/* loaded from: classes5.dex */
public class RetAndMsg {
    private boolean ret = true;
    private String msg = "";
    private int num = 0;

    public int c() {
        return this.num;
    }

    public String b() {
        return this.msg;
    }

    public boolean a() {
        return this.ret;
    }

    public void a(boolean z) {
        this.ret = z;
    }

    public void a(String str) {
        this.msg = str;
    }

    public void a(int i) {
        this.num = i;
    }
}
