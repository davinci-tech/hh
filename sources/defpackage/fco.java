package defpackage;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* loaded from: classes3.dex */
public class fco implements Serializable {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("val")
    private int f12443a;

    @SerializedName("trans")
    private int d;

    public void d(int i) {
        this.f12443a = i;
    }

    public int c() {
        return this.f12443a;
    }

    public void b(int i) {
        this.d = i;
    }

    public int a() {
        return this.d;
    }

    public String toString() {
        return "{\"DailyFactorDescInput\": {\"val\":" + this.f12443a + ", \"trans\":" + this.d + "}}";
    }
}
