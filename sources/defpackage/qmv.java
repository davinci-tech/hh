package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;

/* loaded from: classes8.dex */
public class qmv implements IRequest {

    @SerializedName("orderCode")
    private String d;

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("tradeService") + "/tradeservice/v1/invoice-download?orderCode=" + this.d;
    }

    public void c(String str) {
        this.d = str;
    }
}
