package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes6.dex */
public class omf {

    @SerializedName("listPng")
    private String b;

    @SerializedName("detailPng")
    private String d;

    @SerializedName("backPng")
    private String e;

    public String d() {
        return this.d;
    }

    public String c() {
        return this.b;
    }

    public String toString() {
        return "PictureDaoBean{detailPng='" + this.d + "', backPng='" + this.e + "', listPng='" + this.b + "'}";
    }
}
