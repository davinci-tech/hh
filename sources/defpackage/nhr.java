package defpackage;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class nhr {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("cardId")
    private int f15289a;

    @SerializedName("data")
    private nhq b;

    @SerializedName("cardType")
    private String c;

    @SerializedName("cardErrorCode")
    private int d;

    @SerializedName("domains")
    private List<String> e = new ArrayList();

    @SerializedName("featureName")
    private String f;

    @SerializedName("generationTime")
    private long g;

    @SerializedName("timeScope")
    private String i;

    public int e() {
        return this.f15289a;
    }

    public String d() {
        return this.i;
    }

    public String a() {
        return this.c;
    }

    public nhq c() {
        return this.b;
    }

    public String toString() {
        return "DomainCard{mCardErrorCode=" + this.d + ", mCardId=" + this.f15289a + ", mGenerationTime=" + this.g + ", mFeatureName=" + this.f + ", mTimeScope=" + this.i + ", mCardType=" + this.c + ", mDomainsList=" + this.e + ", mData=" + this.b + '}';
    }
}
