package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import health.compact.a.GRSManager;
import java.util.List;

/* loaded from: classes3.dex */
public class aut extends auf {

    @SerializedName("ids")
    private List<Integer> e;

    public void c(List<Integer> list) {
        this.e = list;
    }

    @Override // defpackage.auf, com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("achievementUrl") + "/achievement/getHealthLifeConDays";
    }
}
