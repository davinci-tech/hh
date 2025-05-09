package defpackage;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* loaded from: classes9.dex */
public class rja implements Serializable {

    @SerializedName("count")
    private int b;

    @SerializedName("timemills")
    private long d;

    public rja(int i, long j) {
        this.b = i;
        this.d = j;
    }

    public int b() {
        return this.b;
    }

    public void e(int i) {
        this.b = i;
    }

    public long d() {
        return this.d;
    }

    public void e(long j) {
        this.d = j;
    }
}
