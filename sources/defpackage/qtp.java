package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes7.dex */
public class qtp {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("whichMeal")
    private int f16585a;

    @SerializedName("sugRange")
    private int[] b;

    @SerializedName("eatTime")
    private long c;

    @SerializedName("hasFood")
    private boolean d;

    @SerializedName("kiloCalorie")
    private float e;

    public qtp(int i, long j, float f, int[] iArr) {
        this.f16585a = i;
        this.c = j;
        this.e = f;
        this.b = iArr;
    }

    public void c(boolean z) {
        this.d = z;
    }
}
