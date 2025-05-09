package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class nhw implements IRequest {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("hasDetails")
    private boolean f15293a;

    @SerializedName("domains")
    private List<String> b = new ArrayList();

    @SerializedName("queryMode")
    private int e = 1;

    public nhw() {
    }

    public nhw(List<String> list, boolean z) {
        this.f15293a = z;
        c(list);
    }

    private void c(List<String> list) {
        if (CollectionUtils.d(list)) {
            return;
        }
        this.b = list;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("healthCloudUrl") + "/dataStat/highlights/getHighlights";
    }
}
