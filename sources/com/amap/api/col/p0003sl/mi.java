package com.amap.api.col.p0003sl;

import java.io.Serializable;

/* loaded from: classes2.dex */
public abstract class mi implements Serializable {

    /* renamed from: a, reason: collision with root package name */
    public String f1336a;
    public String b;
    public int c;
    public int d;
    public long e;
    public long f;
    public int g;
    public boolean h;
    public boolean i;

    @Override // 
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public abstract mi clone();

    public mi() {
        this.f1336a = "";
        this.b = "";
        this.c = 99;
        this.d = Integer.MAX_VALUE;
        this.e = 0L;
        this.f = 0L;
        this.g = 0;
        this.i = true;
    }

    public mi(boolean z, boolean z2) {
        this.f1336a = "";
        this.b = "";
        this.c = 99;
        this.d = Integer.MAX_VALUE;
        this.e = 0L;
        this.f = 0L;
        this.g = 0;
        this.h = z;
        this.i = z2;
    }

    public final int b() {
        return a(this.f1336a);
    }

    public final int c() {
        return a(this.b);
    }

    private static int a(String str) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            ms.a(e);
            return 0;
        }
    }

    public final void a(mi miVar) {
        this.f1336a = miVar.f1336a;
        this.b = miVar.b;
        this.c = miVar.c;
        this.d = miVar.d;
        this.e = miVar.e;
        this.f = miVar.f;
        this.g = miVar.g;
        this.h = miVar.h;
        this.i = miVar.i;
    }

    public String toString() {
        return "AmapCell{mcc=" + this.f1336a + ", mnc=" + this.b + ", signalStrength=" + this.c + ", asulevel=" + this.d + ", lastUpdateSystemMills=" + this.e + ", lastUpdateUtcMills=" + this.f + ", age=" + this.g + ", main=" + this.h + ", newapi=" + this.i + '}';
    }
}
