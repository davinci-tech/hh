package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hms.network.inner.api.NetworkService;
import com.huawei.hms.support.api.entity.pay.HwPayConstant;
import com.huawei.hms.support.hwid.bean.AckQrLoginReq;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;

/* loaded from: classes7.dex */
public class rbf extends CloudCommonReponse {

    @SerializedName("LoginSNSRsp")
    private e d = new e();

    public e b() {
        return this.d;
    }

    public static class e {

        /* renamed from: a, reason: collision with root package name */
        @SerializedName("grpListVersion")
        private String f16693a;

        @SerializedName("digestKey")
        private String b;

        @SerializedName("frdListVersion")
        private int c;

        @SerializedName(NetworkService.Constants.CONFIG_SERVICE)
        private String d;

        @SerializedName(AckQrLoginReq.KEY_IS_FIRST_LOGIN)
        private String e;

        @SerializedName("sessionValidTime")
        private int g;

        @SerializedName(HwPayConstant.KEY_USER_ID)
        private long j;

        public long a() {
            return this.j;
        }

        public String c() {
            return this.e;
        }

        public int b() {
            return this.g;
        }
    }

    @Override // com.huawei.hwcloudmodel.model.CloudCommonReponse
    public String toString() {
        StringBuilder sb = new StringBuilder("LoginUserSeverResponse{LoginSNSRsp=");
        if (b() != null) {
            sb.append("userId=");
            sb.append(b().a());
            sb.append(", isFirstLogin=");
            sb.append(b().c());
        }
        sb.append('}');
        return sb.toString();
    }
}
