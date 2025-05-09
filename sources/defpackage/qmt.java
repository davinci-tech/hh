package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;

/* loaded from: classes8.dex */
public class qmt implements IRequest {

    @SerializedName("orderCode")
    private String b;

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("tradeService") + "/tradeservice/v1/invoice-queryResult?orderCode=" + this.b;
    }

    public void a(String str) {
        this.b = str;
    }
}
