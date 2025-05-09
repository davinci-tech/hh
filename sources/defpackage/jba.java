package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes7.dex */
public class jba extends jay {

    @SerializedName("healthScopes")
    private String c;

    @Override // defpackage.jay
    public String toString() {
        return "getScopeRsp{healthScopes='" + this.c + "'}";
    }

    public String c() {
        return this.c;
    }
}
