package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes3.dex */
public class bfj {

    /* renamed from: a, reason: collision with root package name */
    private long f350a;
    private int b;
    private int c;
    private int d;
    private long e;
    private int f;
    private float g;

    @SerializedName("snoreFreq")
    private int h;
    private int i;
    private long j;
    private long o;

    public void b(long j) {
        this.j = j;
    }

    public long j() {
        return this.j;
    }

    public void c(long j) {
        this.f350a = j;
    }

    public long b() {
        return this.f350a;
    }

    public void d(long j) {
        this.o = j;
    }

    public long f() {
        return this.o;
    }

    public void e(int i) {
        this.i = i;
    }

    public int h() {
        return this.i;
    }

    public void d(int i) {
        this.f = i;
    }

    public int a() {
        return this.f;
    }

    public void e(long j) {
        this.e = j;
    }

    public long d() {
        return this.e;
    }

    public void e(float f) {
        this.g = f;
    }

    public float g() {
        return this.g;
    }

    public void b(int i) {
        this.b = i;
    }

    public int c() {
        return this.b;
    }

    public void a(int i) {
        this.c = i;
    }

    public int e() {
        return this.c;
    }

    public void c(int i) {
        this.h = i;
    }

    public int i() {
        return this.h;
    }

    public String toString() {
        return "SleepCalcFrame{startTime=" + this.j + "fallAsleepTime=" + this.f350a + "wakeUpTime=" + this.o + "sleepScore=" + this.i + "sleepLatency=" + this.f + "goBedTime=" + this.e + "validData=" + this.g + "sleepEfficiency=" + this.b + "deepSleepTime=" + this.d + "deepSleepPart=" + this.c + "snoreFrequency=" + this.h;
    }
}
