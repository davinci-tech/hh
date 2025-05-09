package defpackage;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes6.dex */
public final class nth {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("configName")
    private String f15483a;

    @SerializedName("releaseSupportDevice")
    private List<String> b;

    @SerializedName("isOpenArkuix")
    private int c;

    @SerializedName("supportHuid")
    private List<Integer> d;

    @SerializedName("blockHuid")
    private List<String> e;

    @SerializedName("supportVersions")
    private List<ntj> f;

    @SerializedName("whiteCountryCode")
    private List<String> h;

    public List<ntj> b() {
        return this.f;
    }

    public List<Integer> e() {
        return this.d;
    }

    public List<String> a() {
        return this.e;
    }

    public List<String> f() {
        return this.h;
    }

    public List<String> d() {
        return this.b;
    }

    public int c() {
        return this.c;
    }

    public String toString() {
        return "ArkuixPageConfigBean{mConfigName=" + this.f15483a + ", mSupportVersions=" + this.f + ", mSupportHuid=" + this.d + ", mBlockHuid=" + this.e + ", mWhiteCountryCode=" + this.h + ", mReleaseSupportDevice=" + this.b + ", mOpenArkuix=" + this.c + '}';
    }
}
