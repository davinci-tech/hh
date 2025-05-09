package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.basichealthmodel.cloud.bean.Record;
import com.huawei.haf.application.BaseApplication;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import health.compact.a.GRSManager;
import java.util.List;

/* loaded from: classes3.dex */
public class aup extends auf {

    @SerializedName(ParsedFieldTag.KAKA_CHECKED_IN_RECORDES)
    private List<Record> b;

    public void e(List<Record> list) {
        this.b = list;
    }

    @Override // defpackage.auf, com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("achievementUrl") + "/achievement/addHealthLifeRecord";
    }
}
