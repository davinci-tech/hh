package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import health.compact.a.GRSManager;

/* loaded from: classes3.dex */
public class auv extends auf {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("timestamp")
    private long f244a = System.currentTimeMillis();

    @SerializedName(ParsedFieldTag.RECORD_DAY)
    private int c = DateFormatUtil.b(System.currentTimeMillis());

    @SerializedName("status")
    private int d;

    @SerializedName("challengeId")
    private int e;

    public void d(int i) {
        this.e = i;
    }

    public void a(int i) {
        this.d = i;
    }

    @Override // defpackage.auf, com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("achievementUrl") + "/achievement/updateHealthLifeChallenge";
    }
}
