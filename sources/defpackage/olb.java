package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;

/* loaded from: classes9.dex */
public class olb implements IRequest {

    @SerializedName("ts")
    private long e;

    public void e(long j) {
        this.e = j;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("sportSuggestUrl") + "/audio/audioWorkout/userBehaviorList";
    }
}
