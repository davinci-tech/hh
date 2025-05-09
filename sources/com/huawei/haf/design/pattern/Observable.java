package com.huawei.haf.design.pattern;

import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public class Observable {
    private final ArrayList<Observer> b = new ArrayList<>();
    protected String d;

    public Observable(String str) {
        this.d = str;
    }

    public void b(Object... objArr) {
        synchronized (this.b) {
            if (this.b.isEmpty()) {
                return;
            }
            Iterator it = new ArrayList(this.b).iterator();
            while (it.hasNext()) {
                ((Observer) it.next()).notify(this.d, objArr);
            }
        }
    }

    public void a(Observer observer) {
        if (observer == null) {
            ReleaseLogUtil.a("Observable", "registerObserver observer is null");
            return;
        }
        synchronized (this.b) {
            if (this.b.contains(observer)) {
                ReleaseLogUtil.a("Observable", "registerObserver already registered observer ", observer);
            } else {
                this.b.add(observer);
            }
        }
    }

    public int d(Observer observer) {
        int size;
        if (observer == null) {
            throw new IllegalArgumentException("The observer is null.");
        }
        synchronized (this.b) {
            int indexOf = this.b.indexOf(observer);
            if (indexOf == -1) {
                LogUtil.c("Observable", "Observer ", observer, " was not registered.");
            } else {
                this.b.remove(indexOf);
            }
            size = this.b.size();
        }
        return size;
    }

    public void e() {
        synchronized (this.b) {
            this.b.clear();
        }
    }
}
