package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.networkclient.IRequest;
import com.huawei.operation.jsoperation.JsUtil;
import health.compact.a.Utils;
import java.util.List;

/* loaded from: classes7.dex */
public class iua implements IRequest {

    @SerializedName(JsUtil.DATA_LIST)
    private List<iuf> d;

    public void c(List<iuf> list) {
        this.d = list;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return Utils.e() + "/healthExpansion/extended/addExtendedServiceData";
    }
}
