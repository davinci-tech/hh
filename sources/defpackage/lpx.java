package defpackage;

import com.google.gson.annotations.SerializedName;
import health.compact.a.CommonUtil;

/* loaded from: classes9.dex */
public class lpx {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("carrierType")
    private int f14830a;

    @SerializedName("ciphertext")
    private String b;

    @SerializedName("activationCode")
    private String c;

    @SerializedName("timestamp")
    private long d;

    @SerializedName("ciphertextSign")
    private String e;

    public void a(int i) {
        this.f14830a = i;
    }

    public void b(String str) {
        this.c = str;
    }

    public void a(long j) {
        this.d = j;
    }

    public void a(String str) {
        this.b = str;
    }

    public void e(String str) {
        this.e = str;
    }

    public String toString() {
        return "EncryptESimProfileInfo{carrierType=" + this.f14830a + ", acCode='" + CommonUtil.l(this.c) + "', timestamp=" + this.d + ", ct=" + CommonUtil.l(this.b) + ", cts='" + CommonUtil.l(this.e) + "'}";
    }
}
