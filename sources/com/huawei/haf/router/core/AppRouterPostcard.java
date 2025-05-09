package com.huawei.haf.router.core;

import android.net.Uri;
import android.text.TextUtils;
import com.huawei.haf.router.facade.model.RouteMeta;
import com.huawei.haf.router.facade.template.ServiceProvider;

/* loaded from: classes.dex */
public class AppRouterPostcard {

    /* renamed from: a, reason: collision with root package name */
    private ServiceProvider f2148a;
    private boolean b;
    private final String c;
    private int d;
    private final String e;
    private RouteMeta h;
    private final Uri i;
    private Object j;

    public AppRouterPostcard(String str, String str2, Uri uri) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("path parameter invalid!");
        }
        if (TextUtils.isEmpty(str2)) {
            throw new IllegalArgumentException("group parameter invalid!");
        }
        this.c = str;
        this.e = str2;
        this.i = uri;
    }

    public String m() {
        return this.c;
    }

    public String g() {
        return this.e;
    }

    public Uri zN_() {
        return this.i;
    }

    protected void d(RouteMeta routeMeta) {
        this.h = routeMeta;
        this.d = routeMeta.getExtra();
    }

    protected RouteMeta l() {
        return this.h;
    }

    protected void c(ServiceProvider serviceProvider) {
        this.f2148a = serviceProvider;
    }

    protected ServiceProvider n() {
        return this.f2148a;
    }

    protected void r() {
        this.b = true;
    }

    protected boolean s() {
        return this.b;
    }

    protected void e(Object obj) {
        this.j = obj;
    }

    protected Object k() {
        return this.j;
    }

    public int f() {
        RouteMeta routeMeta = this.h;
        return routeMeta == null ? this.d : routeMeta.getExtra();
    }

    public void c(int i) {
        this.d = i;
    }
}
