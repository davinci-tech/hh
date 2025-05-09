package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.health.vip.datatypes.TransferBenefitInfo;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import java.util.List;

/* loaded from: classes8.dex */
public class gps extends CloudCommonReponse {

    @SerializedName("transferBenefitList")
    private List<TransferBenefitInfo> d;

    public List<TransferBenefitInfo> c() {
        return this.d;
    }

    @Override // com.huawei.hwcloudmodel.model.CloudCommonReponse
    public String toString() {
        return "TransferBenefitsRsp{transferBenefitList=" + this.d + '}';
    }
}
