package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import com.huawei.operation.jsoperation.JsUtil;
import java.util.List;

/* loaded from: classes7.dex */
public class iue extends CloudCommonReponse {

    @SerializedName(JsUtil.DATA_LIST)
    private List<iuf> b;

    @SerializedName("currentVersion")
    private long e;

    public List<iuf> a() {
        return this.b;
    }

    public long c() {
        return this.e;
    }
}
