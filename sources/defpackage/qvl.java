package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;

/* loaded from: classes7.dex */
public class qvl implements IRequest {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("category")
    private int f16612a;

    @SerializedName("subCategory")
    private int c;

    @SerializedName("userInfo")
    private qvr d;

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("sportSuggestUrl") + "/simplefat/v1/getGoalDetail";
    }

    public qvl(int i, int i2, qvr qvrVar) {
        this.f16612a = i;
        this.c = i2;
        this.d = qvrVar;
    }
}
