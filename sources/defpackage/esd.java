package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.health.messagecenter.model.CommonUtil;
import com.huawei.networkclient.IRequest;

/* loaded from: classes8.dex */
public class esd implements IRequest {

    @SerializedName(CommonUtil.PAGE_TYPE)
    private Integer d;

    private esd(d dVar) {
        this.d = dVar.c;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return eta.bj();
    }

    public static final class d {
        private Integer c;

        public d d(Integer num) {
            this.c = num;
            return this;
        }

        public esd e() {
            return new esd(this);
        }
    }
}
