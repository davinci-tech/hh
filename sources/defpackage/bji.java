package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes3.dex */
public class bji {

    @SerializedName("scanType")
    private int d;
    private int e = -32768;

    @SerializedName("rssi")
    public int a() {
        return this.e;
    }

    public void a(int i) {
        this.e = i;
    }

    public int d() {
        return this.d;
    }

    public void b(int i) {
        this.d = i;
    }
}
