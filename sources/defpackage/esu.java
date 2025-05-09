package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.networkclient.IRequest;
import com.huawei.pluginfitnessadvice.plandata.UserInfoBean;

/* loaded from: classes3.dex */
public class esu implements IRequest {

    @SerializedName("weekIndex")
    private Integer b;

    @SerializedName("userInfo")
    private UserInfoBean c;

    @SerializedName("returnAlgorithmInfos")
    private Boolean d;

    private esu(c cVar) {
        this.c = cVar.e;
        this.b = cVar.d;
        this.d = cVar.c;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return eta.cg();
    }

    public static final class c {
        private Boolean c;
        private Integer d;
        private UserInfoBean e;

        public c d(UserInfoBean userInfoBean) {
            this.e = userInfoBean;
            return this;
        }

        public c e(Integer num) {
            this.d = num;
            return this;
        }

        public c d(Boolean bool) {
            this.c = bool;
            return this;
        }

        public esu e() {
            return new esu(this);
        }
    }
}
