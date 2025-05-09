package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;

/* loaded from: classes7.dex */
public class jal implements IRequest {

    @SerializedName("labelGroupId")
    private String c;

    @SerializedName("labelGroupName")
    private String e;

    public void d(String str) {
        this.c = str;
    }

    public void c(String str) {
        this.e = str;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("healthCloudUrl") + "/dataRecommend/userlabel/getLabelRuleGroup";
    }
}
