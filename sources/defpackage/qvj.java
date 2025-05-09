package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class qvj implements IRequest {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("foodIds")
    private final List<String> f16610a;

    public qvj(List<String> list) {
        ArrayList arrayList = new ArrayList();
        this.f16610a = arrayList;
        arrayList.clear();
        arrayList.addAll(list);
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("healthCloudUrl") + "/healthExpansion/food/list";
    }
}
