package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.health.socialshare.model.socialsharebean.ShareDataInfo;
import java.io.Serializable;

/* loaded from: classes4.dex */
public class fdy extends ShareDataInfo implements Serializable {
    private static final long serialVersionUID = -3011399402555708073L;

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("dataWatermark")
    private int f12464a;

    @SerializedName("backgroundImage")
    private int b;

    @SerializedName("sticker")
    private int c;

    @SerializedName("thumbnailSize")
    private int d;

    @SerializedName("thumbnailUrl")
    private String e;

    public int d() {
        return this.b;
    }

    public int a() {
        return this.f12464a;
    }

    public int e() {
        return this.c;
    }

    public String b() {
        return this.e;
    }

    public int c() {
        return this.d;
    }
}
