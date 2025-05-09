package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import com.huawei.operation.ble.BleConstants;
import health.compact.a.GRSManager;

/* loaded from: classes6.dex */
public class mui implements IRequest {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName(BleConstants.SPORT_TYPE)
    private Integer f15177a;

    public void b(Integer num) {
        this.f15177a = num;
    }

    public String toString() {
        return "GetShareResourceReq{sportType=" + this.f15177a + '}';
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("sportSuggestUrl") + "/getShareResList";
    }
}
