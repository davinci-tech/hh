package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import java.util.List;

/* loaded from: classes8.dex */
public class ems extends CloudCommonReponse {

    @SerializedName("userFeedbackInfos")
    private List<Object> b;

    @SerializedName("totalSize")
    private int e;

    @Override // com.huawei.hwcloudmodel.model.CloudCommonReponse
    public String toString() {
        return "GetFeedbackInfosRsp{userFeedbackInfos=" + this.b + ", totalSize=" + this.e + '}';
    }
}
