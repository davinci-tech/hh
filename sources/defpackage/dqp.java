package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes3.dex */
public class dqp {

    @SerializedName("androidMinVersion")
    protected String b;

    @SerializedName("ohMinVersion")
    protected String d;

    @SerializedName("iosMinVersion")
    protected String e;

    public String b() {
        return this.b;
    }

    public String toString() {
        return "{androidMinVersion: " + this.b + ", iosMinVersion: " + this.e + ", ohMinVersion: " + this.d + "}";
    }
}
