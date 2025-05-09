package com.huawei.health.h5pro.view;

import android.graphics.drawable.Drawable;
import android.view.View;

/* loaded from: classes8.dex */
public class FloatingBarItem {

    /* renamed from: a, reason: collision with root package name */
    public Drawable f2461a;
    public String c;
    public View.OnClickListener d;

    public void setDistName(String str) {
        this.c = str;
    }

    public void setDistIcon(Drawable drawable) {
        this.f2461a = drawable;
    }

    public void setClickCallback(View.OnClickListener onClickListener) {
        this.d = onClickListener;
    }

    public String getDistName() {
        return this.c;
    }

    public Drawable getDistIcon() {
        return this.f2461a;
    }

    public View.OnClickListener getClickCallback() {
        return this.d;
    }

    public FloatingBarItem(String str, Drawable drawable, View.OnClickListener onClickListener) {
        this.c = str;
        this.f2461a = drawable;
        this.d = onClickListener;
    }
}
