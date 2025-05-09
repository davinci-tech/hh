package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes7.dex */
public class rhz extends nmk {

    @SerializedName("labelDisplayName")
    private String b;

    @SerializedName("serviceId")
    private int c;

    @SerializedName("labelName")
    private String d;

    @SerializedName("userDisable")
    private int e;

    public rhz() {
    }

    public rhz(int i, String str, String str2, int i2) {
        this.c = i;
        this.d = str;
        this.b = str2;
        this.e = i2;
    }

    public int f() {
        return this.c;
    }

    public String h() {
        return this.d;
    }

    public String i() {
        return this.b;
    }

    public int l() {
        return this.e;
    }

    public void d(int i) {
        this.e = i;
    }

    @Override // defpackage.nmk
    public String e() {
        return this.b;
    }
}
