package defpackage;

import com.google.gson.annotations.SerializedName;
import java.util.Arrays;

/* loaded from: classes3.dex */
public class cwj {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("coefficient")
    private int f11509a = 1;

    @SerializedName("carbohydratesSugRange")
    private int[] b;

    @SerializedName("carbohydrates")
    private int c;

    @SerializedName("fatSugRange")
    private int[] d;

    @SerializedName("fat")
    private int e;

    @SerializedName("protein")
    private int f;

    @SerializedName("proteinSugRange")
    private int[] h;

    public cwj(int i, int i2, int i3) {
        this.c = i;
        this.f = i2;
        this.e = i3;
    }

    public void b(int[] iArr) {
        this.b = iArr;
    }

    public void a(int[] iArr) {
        this.h = iArr;
    }

    public void c(int[] iArr) {
        this.d = iArr;
    }

    public String toString() {
        return "DeviceSyncDietAnalysis{mCoefficient=" + this.f11509a + ", mCarbohydrates=" + this.c + ", mProtein=" + this.f + ", mFat=" + this.e + ", mCarbohydratesSugRange=" + Arrays.toString(this.b) + ", mProteinSugRange=" + Arrays.toString(this.h) + ", mFatSugRange=" + Arrays.toString(this.d) + '}';
    }
}
