package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes6.dex */
public class mpv extends mps {

    @SerializedName("requestFlag")
    private String c;

    @SerializedName("operator")
    private String d;

    @SerializedName("enabled")
    private String e;

    public void j(String str) {
        this.d = str;
    }

    public void e(String str) {
        this.e = str;
    }

    public void h(String str) {
        this.c = str;
    }
}
