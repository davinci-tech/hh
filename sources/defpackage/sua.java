package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes7.dex */
public class sua extends sty {
    public sth h;
    public String j;
    public int g = -99;

    @SerializedName("returnDesc")
    private String b = null;

    public void e(String str) {
        this.j = str;
    }

    @Override // defpackage.sty
    public String a() {
        return this.b;
    }

    @Override // defpackage.sty
    public void c(String str) {
        this.b = str;
    }

    public void e(int i) {
        this.g = i;
    }
}
