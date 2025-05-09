package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import java.util.List;

/* loaded from: classes3.dex */
public class dxg extends CloudCommonReponse {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("labelRules")
    private List<dxc> f11881a;

    public List<dxc> b() {
        return this.f11881a;
    }

    @Override // com.huawei.hwcloudmodel.model.CloudCommonReponse
    public String toString() {
        return "QueryLabelRuleRsp{labelRules=" + this.f11881a + "}";
    }
}
