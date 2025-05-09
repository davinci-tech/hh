package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes.dex */
public class quv {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("setting")
    private qvc f16600a;

    @SerializedName("id")
    private int e = 0;

    public quv(qvc qvcVar) {
        this.f16600a = qvcVar;
    }

    public int c() {
        return this.e;
    }

    public qvc b() {
        return this.f16600a;
    }

    public String toString() {
        return "FastingLiteMode{mId=" + this.e + ", mSetting=" + this.f16600a + '}';
    }
}
