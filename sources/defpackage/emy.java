package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;

/* loaded from: classes3.dex */
public class emy implements IRequest {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("motionPathStartTime")
    private long f12102a;

    @SerializedName("motionPathEndTime")
    private long b;

    @SerializedName("timeZone")
    private String e;

    private emy(d dVar) {
        this.f12102a = dVar.b;
        this.b = dVar.f12103a;
        this.e = dVar.d;
    }

    public static final class d {

        /* renamed from: a, reason: collision with root package name */
        private long f12103a;
        private long b;
        private String d;

        public d c(long j) {
            this.b = j;
            return this;
        }

        public d e(long j) {
            this.f12103a = j;
            return this;
        }

        public d b(String str) {
            this.d = str;
            return this;
        }

        public emy e() {
            return new emy(this);
        }
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("healthCloudUrl") + "/dataAnalyse/app/getHotPathParticipateByVersion";
    }

    public String toString() {
        return "GetHotPathParticipateInfoReq{mMotionPathStartTime=" + this.f12102a + ", mMotionPathEndTime=" + this.b + ", mTimeZone='" + this.e + "'}";
    }
}
