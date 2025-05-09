package com.huawei.uikit.hwviewpager.widget;

import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/* loaded from: classes7.dex */
public abstract class HwPagerAdapter {
    public static final int POSITION_NONE = -2;
    public static final int POSITION_UNCHANGED = -1;
    private static final String c = "HwPagerAdapter";

    /* renamed from: a, reason: collision with root package name */
    private final DataSetObservable f10774a = new DataSetObservable();
    private DataSetObserver b;

    public void a(DataSetObserver dataSetObserver) {
        synchronized (this) {
            this.b = dataSetObserver;
        }
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        Log.i(c, "empty method");
    }

    public void finishUpdate(ViewGroup viewGroup) {
        Log.i(c, "empty method");
    }

    public abstract int getCount();

    public int getItemPosition(Object obj) {
        return -1;
    }

    public float getPageHeight(int i) {
        return 1.0f;
    }

    public CharSequence getPageTitle(int i) {
        return null;
    }

    public float getPageWidth(int i) {
        return 1.0f;
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        return null;
    }

    public abstract boolean isViewFromObject(View view, Object obj);

    public void notifyDataSetChanged() {
        synchronized (this) {
            DataSetObserver dataSetObserver = this.b;
            if (dataSetObserver != null) {
                dataSetObserver.onChanged();
            }
        }
        this.f10774a.notifyChanged();
    }

    public void registerDataSetObserver(DataSetObserver dataSetObserver) {
        this.f10774a.registerObserver(dataSetObserver);
    }

    public void restoreState(Parcelable parcelable, ClassLoader classLoader) {
        Log.i(c, "empty method");
    }

    public Parcelable saveState() {
        return null;
    }

    public void setPrimaryItem(ViewGroup viewGroup, int i, Object obj) {
        Log.i(c, "empty method");
    }

    public void startUpdate(ViewGroup viewGroup) {
        Log.i(c, "empty method");
    }

    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
        this.f10774a.unregisterObserver(dataSetObserver);
    }
}
