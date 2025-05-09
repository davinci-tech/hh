package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes5.dex */
public class jdc {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("mICCID")
    private String f13751a;
    private String b;

    @SerializedName("mIcon")
    private byte[] c;
    private int d;
    private String e;

    @SerializedName("mProfieName")
    private String g;

    @SerializedName("mSPN")
    private String h;
    private byte[] i;
    private String j;

    public String a() {
        return (String) jdy.d(this.f13751a);
    }

    public void b(String str) {
        this.f13751a = (String) jdy.d(str);
    }

    public String b() {
        return (String) jdy.d(this.h);
    }

    public void i(String str) {
        this.h = (String) jdy.d(str);
    }

    public String d() {
        return (String) jdy.d(this.g);
    }

    public void c(String str) {
        this.g = (String) jdy.d(str);
    }

    public void d(String str) {
        this.b = (String) jdy.d(str);
    }

    public void b(int i) {
        this.d = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public byte[] e() {
        byte[] bArr = this.c;
        if (bArr == null) {
            return null;
        }
        return (byte[]) bArr.clone();
    }

    public void c(byte[] bArr) {
        if (bArr == null) {
            this.c = null;
        } else {
            this.c = (byte[]) bArr.clone();
        }
    }

    public void a(String str) {
        this.e = (String) jdy.d(str);
    }

    public void e(String str) {
        this.j = (String) jdy.d(str);
    }

    public byte[] c() {
        return (byte[]) jdy.d(this.i);
    }

    public void d(byte[] bArr) {
        this.i = (byte[]) jdy.d(bArr);
    }

    public String toString() {
        return "EmbeddedSimProfile{mIccid='" + this.f13751a + "', mSpn='" + this.h + "', mProfileName='" + this.g + "', mProfileClass='" + this.b + "', mIconType='" + this.d + "', mConfigurationInfo='" + this.e + "', mProfileOwner='" + this.j + "'}";
    }
}
