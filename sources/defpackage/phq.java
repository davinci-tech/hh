package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes6.dex */
public class phq {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("resultCode")
    private int f16136a;

    @SerializedName("resultDesc")
    private String e;

    public int b() {
        return this.f16136a;
    }

    public String a() {
        return this.e;
    }

    public String toString() {
        return "ThreeCircleTaskBaseResponse{resultCode=" + this.f16136a + ", resultDesc=" + this.e + '}';
    }
}
