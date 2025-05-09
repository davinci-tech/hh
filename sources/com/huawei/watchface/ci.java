package com.huawei.watchface;

import com.google.gson.GsonBuilder;
import com.huawei.watchface.cj;

/* loaded from: classes9.dex */
public class ci {

    /* renamed from: a, reason: collision with root package name */
    private final cj.d f10949a;
    private GsonBuilder b;

    public ci() {
        cj.d dVar = new cj.d();
        this.f10949a = dVar;
        dVar.b = true;
        dVar.d = false;
        dVar.c = true;
    }

    public void a(boolean z) {
        this.f10949a.c = z;
    }

    public ch a() {
        if (this.b == null) {
            this.b = new GsonBuilder();
        }
        return new ch(this.b.create(), this.f10949a);
    }
}
