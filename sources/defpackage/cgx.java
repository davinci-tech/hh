package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes3.dex */
public class cgx {
    private int f;

    /* renamed from: a, reason: collision with root package name */
    private int f713a = 30;
    private int b = 180;

    @SerializedName("ackEnable")
    private boolean c = false;
    private long e = 0;

    @SerializedName("offsetEnable")
    private boolean d = false;

    public long h() {
        return ((Long) jdy.d(Long.valueOf(this.e))).longValue();
    }

    public void e(long j) {
        this.e = ((Long) jdy.d(Long.valueOf(j))).longValue();
    }

    public void c(boolean z) {
        this.c = ((Boolean) jdy.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean a() {
        return ((Boolean) jdy.d(Boolean.valueOf(this.c))).booleanValue();
    }

    public int c() {
        return ((Integer) jdy.d(Integer.valueOf(this.b))).intValue();
    }

    public void e(int i) {
        this.b = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int b() {
        return ((Integer) jdy.d(Integer.valueOf(this.f713a))).intValue();
    }

    public int e() {
        return ((Integer) jdy.d(Integer.valueOf(this.f713a))).intValue() * 1000;
    }

    public void a(int i) {
        this.f713a = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int i() {
        return ((Integer) jdy.d(Integer.valueOf(this.f))).intValue();
    }

    public void c(int i) {
        this.f = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public boolean d() {
        return ((Boolean) jdy.d(Boolean.valueOf(this.d))).booleanValue();
    }

    public void e(boolean z) {
        this.d = ((Boolean) jdy.d(Boolean.valueOf(z))).booleanValue();
    }
}
