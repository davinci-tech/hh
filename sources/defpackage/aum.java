package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import health.compact.a.GRSManager;

/* loaded from: classes3.dex */
public class aum extends aup {

    @SerializedName("operType")
    private int b;

    @SerializedName("timestamp")
    private long d;

    public void b(long j) {
        this.d = j;
    }

    public void a(int i) {
        this.b = i;
    }

    @Override // defpackage.aup, defpackage.auf, com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("achievementUrl") + "/achievement/addHealthLifeConfig";
    }
}
