package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;

/* loaded from: classes9.dex */
public class olg implements IRequest {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("ts")
    private long f15763a;

    @SerializedName("lecturerId")
    private String b;

    public void a(String str) {
        this.b = str;
    }

    public void b(long j) {
        this.f15763a = j;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("sportSuggestUrl") + "/audio/audioWorkout/getLecturerInfo";
    }
}
