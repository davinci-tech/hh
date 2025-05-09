package defpackage;

import android.util.Pair;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes.dex */
public class qvc {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("periodTime")
    private long f16605a;

    @SerializedName("baseTime")
    private long b;

    @SerializedName("settingEndTime")
    private int c;

    @SerializedName("repeat")
    private int d;

    @SerializedName("eatingWindow")
    private long e;

    @SerializedName("validWeekDays")
    private String f;

    @SerializedName("settingInfo")
    private String h;

    @SerializedName("version")
    private int i = 0;

    public qvc(long j, long j2, int i) {
        this.e = j2;
        this.f16605a = j;
        this.d = i;
    }

    public long b() {
        return this.b;
    }

    public void b(long j) {
        this.b = j;
    }

    public long c() {
        return this.e;
    }

    public long a() {
        return this.f16605a;
    }

    public int j() {
        return this.d;
    }

    public void a(int i) {
        this.d = i;
    }

    public int g() {
        return this.i;
    }

    public int f() {
        return this.c;
    }

    public void b(int i) {
        this.c = i;
    }

    public String h() {
        return this.f;
    }

    public void c(String str) {
        this.f = str;
    }

    public void c(long j) {
        this.e = j;
    }

    public void a(long j) {
        this.f16605a = j;
    }

    public Pair<Integer, Integer> dJb_() {
        int i = (int) (this.b / 60);
        int i2 = i / 60;
        if (i2 >= 24) {
            i2 %= 24;
        }
        return new Pair<>(Integer.valueOf(i2), Integer.valueOf(i % 60));
    }

    public Pair<Integer, Integer> dJa_() {
        int i = (int) ((this.b + this.e) / 60);
        int i2 = i / 60;
        if (i2 >= 24) {
            i2 %= 24;
        }
        return new Pair<>(Integer.valueOf(i2), Integer.valueOf(i % 60));
    }

    public String toString() {
        return "FastingLiteSetting{mBaseTime=" + this.b + ", mEatingWindow=" + this.e + ", mPeriodTime=" + this.f16605a + ", mRepeat=" + this.d + ", mSettingEndTime=" + this.c + ", mValidWeekDays=" + this.f + "}";
    }
}
