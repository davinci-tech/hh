package com.huawei.agconnect.apms;

import com.squareup.okhttp.Request;

/* loaded from: classes8.dex */
public class ihg implements Runnable {
    public final /* synthetic */ Request abc;
    public final /* synthetic */ wxy bcd;

    public ihg(Request request, wxy wxyVar) {
        this.abc = request;
        this.bcd = wxyVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        xyz.abc(this.abc.url().getHost(), this.bcd);
        this.bcd.mlk = true;
    }
}
