package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.networkclient.IRequest;

/* loaded from: classes3.dex */
public class esa implements IRequest {

    @SerializedName("type")
    private Integer b;

    private esa(e eVar) {
        this.b = eVar.b;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return eta.bo();
    }

    public static final class e {
        private Integer b;

        public e d(Integer num) {
            this.b = num;
            return this;
        }

        public esa d() {
            return new esa(this);
        }
    }
}
