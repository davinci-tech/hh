package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;

/* loaded from: classes3.dex */
public class emn implements IRequest {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("feedbackDescription")
    private String f12096a;

    @SerializedName("pathId")
    private String d;

    private emn(b bVar) {
        this.d = bVar.d;
        this.f12096a = bVar.b;
    }

    public static final class b {
        private String b;
        private String d;

        public b d(String str) {
            this.d = str;
            return this;
        }

        public b c(String str) {
            this.b = str;
            return this;
        }

        public emn c() {
            return new emn(this);
        }
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("healthCloudUrl") + "/dataAnalyse/app/feedbackHotPathInfo";
    }

    public String toString() {
        return "FeedbackHotPathInfoReq{pathId='" + this.d + "', feedbackDescription='" + this.f12096a + "'}";
    }
}
