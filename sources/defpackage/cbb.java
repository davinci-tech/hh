package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes3.dex */
public class cbb {

    @SerializedName("systemLanguage")
    private String b;

    @SerializedName("systemTimeZone")
    private String c;

    public String d() {
        return this.c;
    }

    public void e(String str) {
        this.c = str;
    }

    public String e() {
        return this.b;
    }

    public void d(String str) {
        this.b = str;
    }
}
