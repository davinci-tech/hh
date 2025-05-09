package com.huawei.hms.network.embedded;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.PackageManager;

/* loaded from: classes9.dex */
public class z1 extends ContextWrapper {

    /* renamed from: a, reason: collision with root package name */
    public PackageManager f5588a;

    @Override // android.content.ContextWrapper, android.content.Context
    public Context getApplicationContext() {
        return this;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public PackageManager getPackageManager() {
        PackageManager packageManager = this.f5588a;
        if (packageManager != null) {
            return packageManager;
        }
        a2 a2Var = new a2(getBaseContext());
        this.f5588a = a2Var;
        return a2Var;
    }

    public z1(Context context) {
        super(context);
    }
}
