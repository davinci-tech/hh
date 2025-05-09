package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes.dex */
public class fca extends wq {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("isWakeupFeel")
    private boolean f12433a;

    @SerializedName("maxRecordHour")
    private int b;

    @SerializedName("isThreshold")
    private boolean c;

    @SerializedName("isSleepRemind")
    private boolean d;

    @SerializedName("enableRecord")
    private boolean e;

    @SerializedName("thresholdSize")
    private int f;

    @SerializedName("sleepHour")
    private int h;

    @SerializedName("sleepMinute")
    private int j;

    private fca() {
        this.h = 23;
        this.e = true;
        this.b = 10;
        this.c = false;
        this.f = 100;
        this.d = true;
        this.f12433a = true;
    }

    public fca(int i, int i2, int i3, String str) {
        super(i, i2, i3, str);
        this.h = 23;
        this.e = true;
        this.b = 10;
        this.c = false;
        this.f = 100;
        this.d = true;
        this.f12433a = true;
    }

    public int l() {
        return this.j;
    }

    public void e(int i) {
        this.j = i;
    }

    public int f() {
        return this.h;
    }

    public void d(int i) {
        this.h = i;
    }

    public boolean o() {
        return this.e;
    }

    public void d(boolean z) {
        this.e = z;
    }

    public int h() {
        return this.b;
    }

    public boolean k() {
        return this.c;
    }

    public int m() {
        return this.f;
    }

    public boolean n() {
        return this.d;
    }

    public void c(boolean z) {
        this.d = z;
    }

    public boolean s() {
        return this.f12433a;
    }
}
