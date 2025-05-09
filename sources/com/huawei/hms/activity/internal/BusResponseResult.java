package com.huawei.hms.activity.internal;

import android.content.Intent;

/* loaded from: classes4.dex */
public class BusResponseResult {

    /* renamed from: a, reason: collision with root package name */
    private Intent f4259a;
    private int b;

    public int getCode() {
        return this.b;
    }

    public Intent getIntent() {
        return this.f4259a;
    }

    public void setCode(int i) {
        this.b = i;
    }

    public void setIntent(Intent intent) {
        this.f4259a = intent;
    }
}
