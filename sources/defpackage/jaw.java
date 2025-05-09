package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;
import java.util.List;

/* loaded from: classes7.dex */
public class jaw implements IRequest {

    @SerializedName("userLabels")
    private List<jau> e;

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("healthCloudUrl") + "/dataRecommend/userlabel/saveUserLabel";
    }

    public void e(List<jau> list) {
        this.e = list;
    }
}
