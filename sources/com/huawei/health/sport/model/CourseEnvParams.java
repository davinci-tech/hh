package com.huawei.health.sport.model;

import com.huawei.hwcommonmodel.fitnessdatatype.HeartZoneConf;
import defpackage.ffg;

/* loaded from: classes4.dex */
public class CourseEnvParams {

    /* renamed from: a, reason: collision with root package name */
    protected boolean f2992a;
    protected OnInitCompleteListener c;
    protected HeartZoneConf d;
    protected boolean e = false;
    protected ffg j;

    public interface OnInitCompleteListener {
        void onInitComplete();
    }

    public HeartZoneConf c() {
        return this.d;
    }

    public ffg g() {
        return this.j;
    }

    public boolean f() {
        return this.e;
    }

    public void b(OnInitCompleteListener onInitCompleteListener) {
        this.c = onInitCompleteListener;
    }

    public void e(boolean z) {
        this.f2992a = z;
    }

    public boolean i() {
        return this.f2992a;
    }
}
