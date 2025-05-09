package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.health.healthmodel.bean.HealthLifeBean;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import java.util.List;

/* loaded from: classes3.dex */
public class auj extends auh {

    @SerializedName(ParsedFieldTag.KAKA_CHECKED_IN_RECORDES)
    private List<HealthLifeBean> c;

    public List<HealthLifeBean> b() {
        return this.c;
    }

    @Override // defpackage.auh
    public String toString() {
        return "HealthModelRecordResponse{records=" + this.c + "}";
    }
}
