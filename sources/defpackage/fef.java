package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.health.socialshare.model.socialsharebean.ShareDataInfo;
import java.io.Serializable;

/* loaded from: classes4.dex */
public class fef extends ShareDataInfo implements Serializable {
    private static final long serialVersionUID = 216593960253233605L;

    @SerializedName("thumbnailSize")
    private int b;

    @SerializedName("thumbnailPath")
    private String c;

    @SerializedName("thumbnailUrl")
    private String d;

    public String d() {
        return this.d;
    }

    public void e(String str) {
        this.d = str;
    }

    public int a() {
        return this.b;
    }

    public void a(int i) {
        this.b = i;
    }

    public String b() {
        return this.c;
    }

    public void d(String str) {
        this.c = str;
    }
}
