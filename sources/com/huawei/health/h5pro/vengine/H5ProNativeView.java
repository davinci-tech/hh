package com.huawei.health.h5pro.vengine;

import android.view.View;

/* loaded from: classes3.dex */
public class H5ProNativeView {
    public View c;
    public H5ProEventInterceptor d;
    public String e;

    public void setView(View view) {
        this.c = view;
    }

    public void setEventInterceptor(H5ProEventInterceptor h5ProEventInterceptor) {
        this.d = h5ProEventInterceptor;
    }

    public void setData(String str) {
        this.e = str;
    }

    public View getView() {
        return this.c;
    }

    public H5ProEventInterceptor getEventInterceptor() {
        return this.d;
    }

    public String getData() {
        return this.e;
    }

    public H5ProNativeView(View view, String str) {
        this.c = view;
        this.e = str;
    }

    public H5ProNativeView(View view) {
        this.c = view;
    }

    public H5ProNativeView() {
    }
}
