package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;

/* loaded from: classes3.dex */
public class emx implements IRequest {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("startDay")
    private int f12101a;

    @SerializedName("endDay")
    private int c;

    private emx(b bVar) {
        this.f12101a = bVar.e;
        this.c = bVar.b;
    }

    public static final class b {
        private int b;
        private int e;

        public b d(int i) {
            this.e = i;
            return this;
        }

        public b c(int i) {
            this.b = i;
            return this;
        }

        public emx b() {
            return new emx(this);
        }
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("healthCloudUrl") + "/dataAnalyse/app/getHotPathParticipateInfos";
    }

    public String toString() {
        return "GetHotPathParticipateInfosReq{startDay=" + this.f12101a + ", endDay=" + this.c + '}';
    }
}
