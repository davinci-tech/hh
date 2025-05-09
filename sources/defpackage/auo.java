package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.health.healthmodel.bean.HealthLifeBean;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import java.util.List;

/* loaded from: classes3.dex */
public class auo extends auh {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName(ParsedFieldTag.KAKA_CHECKED_IN_RECORDES)
    private List<HealthLifeBean> f242a;

    public List<HealthLifeBean> e() {
        return this.f242a;
    }

    @Override // defpackage.auh
    public String toString() {
        return "HealthModelRecordResponse{records=" + this.f242a + "}";
    }
}
