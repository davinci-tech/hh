package com.huawei.android.hicloud.sync.logic;

import android.content.Context;
import defpackage.aaw;
import defpackage.abd;
import defpackage.abl;

/* loaded from: classes8.dex */
public class SyncBase {

    /* renamed from: a, reason: collision with root package name */
    protected final Context f1831a;

    public SyncBase(Context context) {
        this.f1831a = context;
    }

    public static boolean e(Context context, String str) {
        boolean d = abl.d(context, str);
        abd.c("SyncBase", "App call: hasPermission: packageName = " + str + ", hasPermission = " + d);
        return d;
    }

    @Deprecated
    public boolean a(String str) {
        boolean e = aaw.e(str, this.f1831a);
        abd.c("SyncBase", "App call: switchState, syncType = " + str + ", status = " + e);
        return e;
    }

    public int c(int i) {
        return abl.d(i);
    }
}
