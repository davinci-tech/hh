package com.huawei.hms.mlsdk.asr.engine.cloud.vo;

import java.io.Serializable;
import java.util.ArrayList;

/* loaded from: classes9.dex */
public class c implements Serializable {

    /* renamed from: a, reason: collision with root package name */
    private String f5078a;
    private boolean b = false;
    private boolean c = false;
    private ArrayList<RttSegment> d;
    private ArrayList<RttSegment> e;

    public void a(String str) {
        this.f5078a = str;
    }

    public void b(boolean z) {
        this.c = z;
    }

    public String c() {
        return this.f5078a;
    }

    public ArrayList<RttSegment> d() {
        return this.d;
    }

    public boolean e() {
        return this.c;
    }

    public boolean a() {
        return this.b;
    }

    public void b(ArrayList<RttSegment> arrayList) {
        this.d = arrayList;
    }

    public void a(boolean z) {
        this.b = z;
    }

    public ArrayList<RttSegment> b() {
        return this.e;
    }

    public void a(ArrayList<RttSegment> arrayList) {
        this.e = arrayList;
    }
}
