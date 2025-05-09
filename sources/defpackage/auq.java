package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import health.compact.a.GRSManager;
import java.util.List;

/* loaded from: classes3.dex */
public class auq extends auf {

    @SerializedName("ids")
    private List<Integer> b;

    @SerializedName("weekNos")
    private List<Integer> d;

    public void d(List<Integer> list) {
        this.b = list;
    }

    public void c(List<Integer> list) {
        this.d = list;
    }

    @Override // defpackage.auf, com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("achievementUrl") + "/achievement/getHealthLifeStat";
    }
}
