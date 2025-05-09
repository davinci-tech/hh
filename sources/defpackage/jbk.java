package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import com.huawei.operation.utils.Constants;
import java.util.List;

/* loaded from: classes5.dex */
public class jbk extends CloudCommonReponse {

    @SerializedName("errorMessage")
    private String b;

    @SerializedName("errorCode")
    private int d;

    @SerializedName(Constants.VMALL_SIGN_INFO)
    private List<jbg> e;

    public int e() {
        return this.d;
    }

    public List<jbg> d() {
        return this.e;
    }

    @Override // com.huawei.hwcloudmodel.model.CloudCommonReponse
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("HonorPrivacyRsp{errorCode=");
        stringBuffer.append(this.d);
        stringBuffer.append(", errorMessage=").append(this.b);
        stringBuffer.append(", signInfos=").append(this.e);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
