package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes7.dex */
public class jax {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("latestAuditStatus")
    private int f13706a;

    @SerializedName("betaScopes")
    private String b;

    @SerializedName("passedScopes")
    private String d;

    public String toString() {
        return "AppAuditInfo{latestAuditStatus=" + this.f13706a + ", passedScopes='" + this.d + "', betaScopes='" + this.b + "'}";
    }
}
