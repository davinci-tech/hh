package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes9.dex */
public class kun {

    @SerializedName("name")
    private String b;

    @SerializedName("id")
    private int d;

    public kun(int i, String str) {
        this.d = i;
        this.b = str;
    }

    public int a() {
        return this.d;
    }

    public String toString() {
        return "DataType{mId=" + this.d + ", mName='" + this.b + "'}";
    }
}
