package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes.dex */
public class gyp {

    @SerializedName("mSportResult")
    private long b;

    @SerializedName("mSportType")
    private int c;

    public void c(int i) {
        this.c = i;
    }

    public void d(long j) {
        this.b = j;
    }

    public String toString() {
        return "SportResult{mSportType=" + this.c + ", mSportResult=" + this.b + '}';
    }
}
