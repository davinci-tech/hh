package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes3.dex */
public class biz {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("isConnectNewPhone")
    private boolean f400a;

    @SerializedName("isUseSynergy")
    private boolean b;

    @SerializedName("pairMode")
    private int c;

    @SerializedName("isCompatibleDevice")
    private boolean d;

    @SerializedName("identify")
    private String e;

    public String a() {
        return this.e;
    }

    public void e(String str) {
        this.e = str;
    }

    public void a(boolean z) {
        this.b = z;
    }

    public boolean d() {
        return this.d;
    }

    public void e(boolean z) {
        this.d = z;
    }

    public boolean c() {
        return this.f400a;
    }

    public void d(boolean z) {
        this.f400a = z;
    }

    public int b() {
        return this.c;
    }

    public void c(int i) {
        this.c = i;
    }
}
