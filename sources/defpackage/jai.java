package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.openalliance.ad.db.bean.ContentResource;

/* loaded from: classes9.dex */
public class jai {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("bizType")
    private int f13701a;

    @SerializedName("bizSubType")
    private int b;

    @SerializedName(ContentResource.FILE_NAME)
    private String c;

    @SerializedName("fileSha256")
    private String d;

    @SerializedName("fileSize")
    private int e;

    @SerializedName("fileType")
    private int i;

    public void a(int i) {
        this.f13701a = i;
    }

    public void e(int i) {
        this.b = i;
    }

    public void e(String str) {
        this.c = str;
    }

    public void b(int i) {
        this.i = i;
    }

    public void c(String str) {
        this.d = str;
    }

    public void d(int i) {
        this.e = i;
    }
}
