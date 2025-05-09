package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import health.compact.a.GRSManager;

/* loaded from: classes3.dex */
public class aud extends auf {

    @SerializedName("challengeEndTime")
    private int b;

    @SerializedName("challengeTime")
    private int d;

    public void d(int i) {
        this.d = i;
    }

    public void b(int i) {
        this.b = i;
    }

    @Override // defpackage.auf, com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("achievementUrl") + "/achievement/getHealthLifeChallenges";
    }
}
