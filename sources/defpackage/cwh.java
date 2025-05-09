package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes3.dex */
public class cwh {

    @SerializedName("unit")
    private String b;

    @SerializedName("unitValue")
    private int c;

    public String d() {
        return this.b;
    }

    public int c() {
        return this.c;
    }

    public String toString() {
        return "ExtensionUnit{mUnit=" + this.b + ", mUnitValue=" + this.c + '}';
    }
}
