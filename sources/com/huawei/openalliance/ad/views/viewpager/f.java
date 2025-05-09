package com.huawei.openalliance.ad.views.viewpager;

import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;

/* loaded from: classes5.dex */
public abstract class f {

    /* renamed from: a, reason: collision with root package name */
    private final DataSetObservable f8171a = new DataSetObservable();
    private DataSetObserver b;

    public float a(int i) {
        return 1.0f;
    }

    public abstract int a();

    public int a(Object obj) {
        return -1;
    }

    public void a(Parcelable parcelable, ClassLoader classLoader) {
    }

    public void a(ViewGroup viewGroup) {
    }

    public abstract boolean a(View view, Object obj);

    public Parcelable b() {
        return null;
    }

    public void b(ViewGroup viewGroup) {
    }

    public void b(ViewGroup viewGroup, int i, Object obj) {
    }

    public void a(ViewGroup viewGroup, int i, Object obj) {
        throw new UnsupportedOperationException("Required method destroyItem was not overridden");
    }

    void a(DataSetObserver dataSetObserver) {
        synchronized (this) {
            this.b = dataSetObserver;
        }
    }

    public Object a(ViewGroup viewGroup, int i) {
        throw new UnsupportedOperationException("Required method instantiateItem was not overridden");
    }
}
