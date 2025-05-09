package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.networkclient.IRequest;
import health.compact.a.Utils;

/* loaded from: classes7.dex */
public class iub implements IRequest {

    @SerializedName("type")
    private int b;

    @SerializedName("version")
    private long c;

    public long d() {
        return this.c;
    }

    public void e(long j) {
        this.c = j;
    }

    public int b() {
        return this.b;
    }

    public void d(int i) {
        this.b = i;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return Utils.e() + "/healthExpansion/extended/queryByVersion";
    }
}
