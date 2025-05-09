package com.huawei.ui.homehealth.device.util;

import android.content.Context;
import android.view.ViewConfiguration;

/* loaded from: classes6.dex */
public class NestScrollHelper {
    private static final float d = ViewConfiguration.getScrollFriction();

    /* renamed from: a, reason: collision with root package name */
    private float f9412a;
    private final float c = (float) (Math.log(0.78d) / Math.log(0.9d));

    public NestScrollHelper(Context context) {
        this.f9412a = context.getResources().getDisplayMetrics().density * 160.0f * 386.0878f * 0.84f;
    }

    private double a(double d2) {
        return Math.log((Math.abs(d2) * 0.3499999940395355d) / (d * this.f9412a));
    }

    private double e(double d2) {
        return ((this.c - 1.0d) * Math.log(d2 / (d * this.f9412a))) / this.c;
    }

    public double c(int i) {
        double a2 = a(i);
        double d2 = this.c;
        return d * this.f9412a * Math.exp((d2 / (d2 - 1.0d)) * a2);
    }

    public double d(double d2) {
        return Math.abs(((Math.exp(e(d2)) * d) * this.f9412a) / 0.3499999940395355d);
    }

    public double c(double d2) {
        return Math.exp(a(d2) / (this.c - 1.0d)) * 1000.0d;
    }
}
