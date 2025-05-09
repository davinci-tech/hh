package defpackage;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes3.dex */
public class cwk {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("carbohydrate")
    private float f11510a;

    @SerializedName("extensionUnits")
    private List<cwh> b;

    @SerializedName("foodId")
    private String c;

    @SerializedName("count")
    private int d;

    @SerializedName("fat")
    private float e;

    @SerializedName("gi")
    private float f;

    @SerializedName("foodName")
    private String g;

    @SerializedName("kilocalorie")
    private int h;

    @SerializedName("imageUrl")
    private String i;

    @SerializedName("protein")
    private float j;

    @SerializedName("unit")
    private String m;

    public String d() {
        return this.c;
    }

    public String h() {
        return this.m;
    }

    public int b() {
        return this.d;
    }

    public float c() {
        return this.f11510a;
    }

    public float i() {
        return this.j;
    }

    public float e() {
        return this.e;
    }

    public List<cwh> a() {
        return this.b;
    }

    public String toString() {
        return "Food{mFoodId=" + this.c + ", mFoodName=" + this.g + ", mImageUrl=" + this.i + ", mUnit=" + this.m + ", mCount=" + this.d + ", mKilocalorie=" + this.h + ", mCarbohydrate=" + this.f11510a + ", mProtein=" + this.j + ", mFat=" + this.e + ", mGi=" + this.f + ", mExtensionUnits=" + this.b + '}';
    }
}
