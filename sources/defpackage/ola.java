package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;

/* loaded from: classes6.dex */
public class ola implements IRequest {

    @SerializedName("endTime")
    private long b;

    @SerializedName("startTime")
    private long e;

    public void a(long j) {
        this.e = j;
    }

    public void d(long j) {
        this.b = j;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("sportSuggestUrl") + "/audio/audioWorkout/queryPlayStatic";
    }
}
