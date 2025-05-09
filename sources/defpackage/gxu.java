package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes4.dex */
public class gxu {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("imageUrl")
    private String f12996a;

    @SerializedName("imageBigUrl")
    private String b;

    @SerializedName("endTime")
    private long c;

    @SerializedName("startTime")
    private long d;

    @SerializedName("backgroundImageUrl")
    private String e;

    public void a(long j) {
        this.d = j;
    }

    public void d(long j) {
        this.c = j;
    }

    public String a() {
        return this.f12996a;
    }

    public void a(String str) {
        this.f12996a = str;
    }

    public String e() {
        return this.b;
    }

    public void c(String str) {
        this.b = str;
    }

    public String d() {
        return this.e;
    }

    public void b(String str) {
        this.e = str;
    }

    public boolean c() {
        long currentTimeMillis = System.currentTimeMillis();
        long j = this.d;
        if (j != 0) {
            long j2 = this.c;
            if (j2 != 0) {
                return currentTimeMillis >= j && currentTimeMillis <= j2;
            }
        }
        if (j != 0 && this.c == 0) {
            return currentTimeMillis >= j;
        }
        if (j != 0) {
            return false;
        }
        long j3 = this.c;
        return j3 != 0 && currentTimeMillis <= j3;
    }
}
