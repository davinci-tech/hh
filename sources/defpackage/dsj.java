package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;

/* loaded from: classes3.dex */
public class dsj implements IRequest {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("startDay")
    private long f11819a;

    @SerializedName("type")
    private String b;

    public void c(String str) {
        this.b = str;
    }

    public void b(long j) {
        this.f11819a = j;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("healthCloudUrl") + "/healthExpansion/healthReport/queryHealthReport";
    }
}
