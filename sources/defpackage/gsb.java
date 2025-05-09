package defpackage;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* loaded from: classes4.dex */
public class gsb implements Serializable {
    private static final long serialVersionUID = 2867379742733736124L;

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("maxWeek")
    private int f12901a;

    @SerializedName("gear")
    private int b;

    @SerializedName("maxRatio")
    private float c;

    @SerializedName("minRatio")
    private float e;

    public int a() {
        return this.b;
    }

    public void b(int i) {
        this.b = i;
    }

    public void e(float f) {
        this.e = f;
    }

    public void a(float f) {
        this.c = f;
    }

    public void a(int i) {
        this.f12901a = i;
    }

    public String toString() {
        return "FatLossRatio{mGear=" + this.b + ", mMinRatio=" + this.e + ", mMaxRatio=" + this.c + ", mMaxWeek=" + this.f12901a + '}';
    }
}
