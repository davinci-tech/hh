package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes3.dex */
public class cbg {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("minute")
    private int f592a;

    @SerializedName("daysOfWeek")
    private int b;

    @SerializedName("enabled")
    private boolean c;

    @SerializedName("hour")
    private int d;

    @SerializedName("alarmId")
    private int e;

    @SerializedName("planId")
    private int h;

    public int a() {
        return this.e;
    }

    public void d(int i) {
        this.e = i;
    }

    public int f() {
        return this.h;
    }

    public void e(int i) {
        this.h = i;
    }

    public int d() {
        return this.d;
    }

    public void b(int i) {
        this.d = i;
    }

    public int c() {
        return this.f592a;
    }

    public void c(int i) {
        this.f592a = i;
    }

    public int e() {
        return this.b;
    }

    public void a(int i) {
        this.b = i;
    }

    public boolean b() {
        return this.c;
    }

    public void c(boolean z) {
        this.c = z;
    }

    public String toString() {
        return "BloodPressureAlarmInfoCloud{mAlarmId=" + this.e + ", mPlanId=" + this.h + ", mHour=" + this.d + ", mMinute=" + this.f592a + ", mDaysOfWeek=" + this.b + ", mEnabled=" + this.c + "}";
    }
}
