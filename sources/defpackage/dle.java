package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes.dex */
public class dle {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("cardId")
    private long f11707a;

    @SerializedName("cardSize")
    private int d;

    @SerializedName("cardType")
    private int e;

    public long e() {
        return this.f11707a;
    }

    public void d(long j) {
        this.f11707a = j;
    }

    public void a(int i) {
        this.e = i;
    }

    public void b(int i) {
        this.d = i;
    }

    public boolean c() {
        return this.e == 1;
    }

    public String toString() {
        return "FaForm{mId=" + this.f11707a + ", mType=" + this.e + ", mCardSize=" + this.d + '}';
    }
}
