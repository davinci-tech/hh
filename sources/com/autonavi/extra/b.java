package com.autonavi.extra;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    private List<a> f1654a = new ArrayList();

    public final void a() {
        synchronized (b.class) {
            List<a> list = this.f1654a;
            if (list != null) {
                list.add(null);
            }
        }
    }

    public final void b() {
        synchronized (b.class) {
            Iterator<a> it = this.f1654a.iterator();
            while (it.hasNext()) {
                it.next();
            }
        }
    }

    public final void c() {
        synchronized (b.class) {
            Iterator<a> it = this.f1654a.iterator();
            while (it.hasNext()) {
                it.next();
            }
        }
    }

    public final void d() {
        synchronized (b.class) {
            Iterator<a> it = this.f1654a.iterator();
            while (it.hasNext()) {
                it.next();
            }
        }
    }

    public final void e() {
        synchronized (b.class) {
            Iterator<a> it = this.f1654a.iterator();
            while (it.hasNext()) {
                it.next();
            }
        }
    }

    public final void f() {
        synchronized (b.class) {
            Iterator<a> it = this.f1654a.iterator();
            while (it.hasNext()) {
                it.next();
            }
            this.f1654a.clear();
        }
    }

    public final String g() {
        String stringBuffer;
        synchronized (b.class) {
            StringBuffer stringBuffer2 = new StringBuffer();
            for (a aVar : this.f1654a) {
                if (aVar != null) {
                    String a2 = aVar.a();
                    if (!TextUtils.isEmpty(a2)) {
                        stringBuffer2.append(a2);
                        if (!a2.endsWith(";")) {
                            stringBuffer2.append(";");
                        }
                    }
                }
            }
            stringBuffer = stringBuffer2.toString();
        }
        return stringBuffer;
    }

    public final void h() {
        synchronized (b.class) {
            Iterator<a> it = this.f1654a.iterator();
            while (it.hasNext()) {
                it.next();
            }
        }
    }

    public final void i() {
        synchronized (b.class) {
            Iterator<a> it = this.f1654a.iterator();
            while (it.hasNext()) {
                it.next();
            }
        }
    }

    public final Object j() {
        Object b;
        synchronized (b.class) {
            for (a aVar : this.f1654a) {
                if (aVar != null && (b = aVar.b()) != null) {
                    return b;
                }
            }
            return null;
        }
    }
}
