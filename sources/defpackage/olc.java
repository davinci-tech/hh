package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;

/* loaded from: classes9.dex */
public class olc implements IRequest {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("ts")
    private long f15759a;

    public void e(long j) {
        this.f15759a = j;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("sportSuggestUrl") + "/audio/audioWorkout/userPlayRecord";
    }
}
