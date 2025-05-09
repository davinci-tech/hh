package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import com.huawei.operation.utils.Constants;
import health.compact.a.GRSManager;

/* loaded from: classes6.dex */
public class olh implements IRequest {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("endDate")
    private String f15764a;

    @SerializedName("timeZone")
    private String c;

    @SerializedName(Constants.START_DATE)
    private String d;

    public void c(String str) {
        this.d = str;
    }

    public void e(String str) {
        this.f15764a = str;
    }

    public void b(String str) {
        this.c = str;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("sportSuggestUrl") + "/audio/audioWorkout/getWorkoutListHistory";
    }
}
