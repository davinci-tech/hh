package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;

/* loaded from: classes3.dex */
public class emk implements IRequest {

    @SerializedName("countryId")
    private String c;

    private emk(c cVar) {
        this.c = cVar.d;
    }

    public static final class c {
        private String d;

        public c c(String str) {
            this.d = str;
            return this;
        }

        public emk e() {
            return new emk(this);
        }
    }

    public String c() {
        return this.c;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("healthCloudUrl") + "/dataAnalyse/app/getCityInfoList";
    }

    public String toString() {
        return "GetCityInfoListReq{countryId='" + this.c + "'}";
    }
}
