package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;

/* loaded from: classes5.dex */
public class kpn implements IRequest {

    @SerializedName("countryCode")
    private String e;

    public kpn(String str) {
        this.e = str;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("sportSuggestUrl") + "/v1/getDietFilter";
    }
}
