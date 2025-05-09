package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hwcloudmodel.model.CloudCommonRequest;
import health.compact.a.GRSManager;

/* loaded from: classes7.dex */
public class rbi extends CloudCommonRequest {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("size")
    private Integer f16694a;

    @SerializedName("from")
    private Integer b;

    @SerializedName("resourceId")
    private String c;

    @SerializedName("product")
    private String d;

    private rbi(c cVar) {
        this.c = cVar.b;
        this.d = cVar.f16695a;
        this.b = cVar.c;
        this.f16694a = cVar.d;
    }

    public static final class c {

        /* renamed from: a, reason: collision with root package name */
        private String f16695a;
        private String b;
        private Integer c;
        private Integer d;

        public rbi b() {
            return new rbi(this);
        }
    }

    public String d() {
        return GRSManager.a(BaseApplication.e()).getUrl("domainHealthCloudCommon") + "/commonAbility/nps/getNPSList";
    }

    public String toString() {
        return "NpsInfosReq{resourceId='" + this.c + "', product='" + this.d + "', from=" + this.b + ", size=" + this.f16694a + '}';
    }
}
