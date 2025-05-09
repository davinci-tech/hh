package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes6.dex */
public class mws {

    @SerializedName("minVersion")
    Integer b;

    @SerializedName("condition")
    mwp c;

    @SerializedName("maxVersion")
    Integer e;

    public Integer a() {
        return this.b;
    }

    public Integer c() {
        return this.e;
    }

    public mwp e() {
        return this.c;
    }
}
