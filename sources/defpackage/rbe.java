package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes7.dex */
public class rbe {

    @SerializedName("mImageUrl")
    private String c;

    @SerializedName("mHuid")
    private long e;

    public void e(long j) {
        this.e = j;
    }

    public long d() {
        return this.e;
    }

    public void c(String str) {
        this.c = str;
    }

    public String a() {
        return this.c;
    }
}
