package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class fgx implements IRequest {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("broadcastInfoList")
    private List<fha> f12501a = new ArrayList();

    public void b(List<fha> list) {
        this.f12501a = list;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("sportSuggestUrl") + "/v1/getVoiceBroadcastList";
    }
}
