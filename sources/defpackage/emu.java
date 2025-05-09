package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;

/* loaded from: classes3.dex */
public class emu implements IRequest {

    @SerializedName("pathId")
    private String b;

    private emu(b bVar) {
        this.b = bVar.f12100a;
    }

    public static final class b {

        /* renamed from: a, reason: collision with root package name */
        private String f12100a;

        public b d(String str) {
            this.f12100a = str;
            return this;
        }

        public emu b() {
            return new emu(this);
        }
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("healthCloudUrl") + "/dataAnalyse/app/getHotPathDetail";
    }

    public String toString() {
        return "GetHotPathDetailReq{pathId='" + this.b + "'}";
    }
}
