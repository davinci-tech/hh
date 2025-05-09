package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import health.compact.a.GRSManager;
import java.util.List;

/* loaded from: classes3.dex */
public class but implements IRequest {

    @SerializedName(ParsedFieldTag.KAKA_CHECKED_IN_RECORDES)
    private List<bus> b;

    public void b(List<bus> list) {
        this.b = list;
    }

    public String toString() {
        return "UpdateActiveTargetReq{records=" + this.b + '}';
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("achievementUrl") + "/achievement/updateGoalRecord";
    }
}
