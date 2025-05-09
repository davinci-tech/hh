package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes6.dex */
public class nht {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("highlights")
    private nhp f15291a;

    @SerializedName("resultCode")
    private int d;

    public nhp b() {
        return this.f15291a;
    }

    public String toString() {
        return "SummaryResponse{mResultCode=" + this.d + ", mHighlights=" + this.f15291a + '}';
    }
}
