package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes3.dex */
public class bgq {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("encryptData")
    private String f364a;

    @SerializedName("version")
    private int c = 1;

    public String c() {
        return this.f364a;
    }

    public void b(String str) {
        this.f364a = str;
    }

    public int b() {
        return this.c;
    }
}
