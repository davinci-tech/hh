package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes6.dex */
public class phl extends phq {

    @SerializedName("newAdd")
    private int c;

    public int c() {
        return this.c;
    }

    @Override // defpackage.phq
    public String toString() {
        return "ThreeCircleNewPointsResponse{resultCode=" + b() + ", resultDesc=" + a() + ", newAdd=" + this.c + '}';
    }
}
