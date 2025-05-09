package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes.dex */
public class wq {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("label")
    private String f17732a;

    @SerializedName("daysOfWeek")
    private int b;

    @SerializedName("hour")
    private int c;

    @SerializedName("alarmId")
    private int d;

    @SerializedName("enabled")
    private boolean e;

    @SerializedName("minute")
    private int f;

    @SerializedName("ringTone")
    private String g;

    @SerializedName("toneDuration")
    private int h;

    @SerializedName("name")
    private String i;

    @SerializedName("retryTimes")
    private int j;

    @SerializedName("toneInterval")
    private int l;

    @SerializedName("vibrate")
    private int m;

    public wq() {
        this.e = true;
        this.c = 7;
        this.m = 1;
        this.h = 5;
        this.l = 10;
        this.j = 3;
    }

    public wq(int i, int i2, int i3, String str) {
        this.e = true;
        this.m = 1;
        this.h = 5;
        this.l = 10;
        this.j = 3;
        this.d = i;
        this.c = i2;
        this.f = i3;
        this.g = str;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        return (obj instanceof wq) && b() == ((wq) obj).b();
    }

    public int hashCode() {
        return b();
    }

    public boolean i() {
        return this.e;
    }

    public void b(boolean z) {
        this.e = z;
    }

    public int e() {
        return this.f;
    }

    public int a() {
        return this.c;
    }

    public int d() {
        return this.b;
    }

    public void b(int i) {
        this.b = i;
    }

    public int g() {
        return this.m;
    }

    public int j() {
        return this.l;
    }

    public int c() {
        return this.j;
    }

    public int b() {
        return this.d;
    }

    public void c(int i) {
        this.f = i;
    }

    public void a(int i) {
        this.c = i;
    }
}
