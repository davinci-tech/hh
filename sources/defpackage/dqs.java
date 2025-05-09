package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.openalliance.ad.db.bean.ContentResource;

/* loaded from: classes3.dex */
public class dqs {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName(ContentResource.FILE_NAME)
    protected String f11793a;

    @SerializedName("version")
    protected int c;

    public int e() {
        return this.c;
    }

    public void e(int i) {
        this.c = i;
    }

    public String c() {
        return this.f11793a;
    }

    public void a(String str) {
        this.f11793a = str;
    }
}
