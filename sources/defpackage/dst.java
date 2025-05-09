package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.operation.utils.Constants;

/* loaded from: classes3.dex */
public class dst {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("fileSize")
    private String f11824a;

    @SerializedName(Constants.ACTIVITY_SHARE_TYPE)
    private String b;

    @SerializedName("subTitle")
    private String c;

    @SerializedName("thumbnail")
    private String d;

    @SerializedName("thumbnailSize")
    private String e;

    @SerializedName("url")
    private String f;

    @SerializedName("uri")
    private String h;

    @SerializedName("title")
    private String j;

    public void d(String str) {
        this.b = str;
    }

    public void g(String str) {
        this.h = str;
    }

    public void f(String str) {
        this.f = str;
    }

    public void i(String str) {
        this.j = str;
    }

    public void c(String str) {
        this.c = str;
    }

    public void b(String str) {
        this.d = str;
    }

    public void e(String str) {
        this.e = str;
    }

    public void a(String str) {
        this.f11824a = str;
    }

    public String toString() {
        return "ShareParamData{mShareType='" + this.b + "', mUri='" + this.h + "', mUrl='" + this.f + "', mTitle='" + this.j + "', mSubTitle='" + this.c + "', mThumbnail='" + this.d + "', mThumbnailSize='" + this.e + "', mFileSize='" + this.f11824a + "'}";
    }
}
