package defpackage;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes6.dex */
public final class ntj {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("vReleaseSupportDevice")
    private List<String> f15484a;

    @SerializedName("vVersionName")
    private String b;

    @SerializedName("vWhiteCountryCode")
    private List<String> c;

    @SerializedName("vBlockHuid")
    private List<String> d;

    @SerializedName("vSupportHuid")
    private List<Integer> e;

    public String a() {
        return this.b;
    }

    public List<Integer> b() {
        return this.e;
    }

    public List<String> c() {
        return this.d;
    }

    public List<String> d() {
        return this.c;
    }

    public List<String> e() {
        return this.f15484a;
    }

    public String toString() {
        return "VersionInnerConfigBean{mVersionName=" + this.b + ", mSupportHuid=" + this.e + ", mBlockHuid=" + this.d + ", mWhiteCountryCode=" + this.c + ", mReleaseSupportDevice=" + this.f15484a + '}';
    }
}
