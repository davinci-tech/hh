package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;

/* loaded from: classes3.dex */
public class erc implements IRequest {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("rankCategory")
    private String f12186a;

    @SerializedName("value")
    private double c;

    @SerializedName("userCategory")
    private String d;

    @SerializedName("timestamp")
    private long e;

    public void d(String str) {
        this.f12186a = str;
    }

    public void c(String str) {
        this.d = str;
    }

    public void e(double d) {
        this.c = d;
    }

    public void d(long j) {
        this.e = j;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("healthCloudUrl") + "/dataRecommend/getUserRank";
    }
}
