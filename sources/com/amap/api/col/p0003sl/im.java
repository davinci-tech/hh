package com.amap.api.col.p0003sl;

import android.text.TextUtils;
import java.util.Vector;

/* loaded from: classes2.dex */
public final class im {
    private static int b = 100;
    private static int d = 10000;

    /* renamed from: a, reason: collision with root package name */
    private Vector<ij> f1188a;
    private int c;
    private int e;

    public im() {
        this.e = 0;
        this.c = 10;
        this.f1188a = new Vector<>();
    }

    public im(byte b2) {
        this.c = b;
        this.e = 0;
        this.f1188a = new Vector<>();
    }

    public final boolean a(String str) {
        synchronized (this) {
            if (str == null) {
                return false;
            }
            if (this.f1188a.size() >= this.c) {
                return true;
            }
            return this.e + str.getBytes().length > d;
        }
    }

    public final Vector<ij> a() {
        return this.f1188a;
    }

    public final void b() {
        synchronized (this) {
            this.f1188a.clear();
            this.e = 0;
        }
    }

    public final void a(ij ijVar) {
        synchronized (this) {
            if (ijVar != null) {
                if (!TextUtils.isEmpty(ijVar.b())) {
                    this.f1188a.add(ijVar);
                    this.e += ijVar.b().getBytes().length;
                }
            }
        }
    }
}
