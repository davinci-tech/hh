package defpackage;

import com.huawei.haf.application.BaseApplication;
import health.compact.a.GRSManager;

/* loaded from: classes8.dex */
public class auk extends auf {
    @Override // defpackage.auf, com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("achievementUrl") + "/achievement/getHealthLife";
    }
}
