package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.openalliance.ad.ipc.RemoteCallResultCallback;

/* loaded from: classes5.dex */
public class f {

    /* renamed from: a, reason: collision with root package name */
    protected String f6858a;
    protected Context b;
    protected String c;
    protected fs d;
    protected fs e;
    protected fu f;
    protected RemoteCallResultCallback<String> g;
    protected boolean h;

    public f(Context context, String str, RemoteCallResultCallback<String> remoteCallResultCallback, String str2) {
        this.h = false;
        this.f6858a = str;
        this.b = context;
        this.g = remoteCallResultCallback;
        this.c = str2;
        this.d = es.a(context);
        this.e = et.b(context);
        this.f = ev.a(context);
    }

    public f(Context context, RemoteCallResultCallback<String> remoteCallResultCallback, String str) {
        this.h = false;
        this.b = context;
        this.g = remoteCallResultCallback;
        this.c = str;
        this.d = es.a(context);
        this.e = et.b(context);
        this.f = ev.a(context);
        this.h = true;
    }
}
