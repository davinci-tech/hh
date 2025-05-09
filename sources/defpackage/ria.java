package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;
import java.util.List;

/* loaded from: classes7.dex */
public class ria implements IRequest {

    @SerializedName("labelConfig")
    private List<rhz> d;

    public ria(List<rhz> list) {
        this.d = list;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("healthCloudUrl") + "/dataRecommend/v1/labelConfig/saveUserLabelConfig";
    }
}
