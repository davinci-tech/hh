package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes3.dex */
public class auh {

    @SerializedName("currentVersion")
    private long b;

    @SerializedName("resultCode")
    private int d;

    public int a() {
        return this.d;
    }

    public long d() {
        return this.b;
    }

    public String toString() {
        return "HealthModelBaseResponse{mResultCode=" + this.d + ", currentVersion=" + this.b + "}";
    }
}
