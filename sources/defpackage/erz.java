package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.networkclient.IRequest;

/* loaded from: classes3.dex */
public class erz implements IRequest {

    @SerializedName("id")
    private Integer c;

    @SerializedName("operateType")
    private Integer d;

    private erz(e eVar) {
        this.c = eVar.c;
        this.d = eVar.b;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return eta.az();
    }

    public static final class e {
        private Integer b;
        private Integer c;

        public e c(Integer num) {
            this.c = num;
            return this;
        }

        public e b(Integer num) {
            this.b = num;
            return this;
        }

        public erz b() {
            return new erz(this);
        }
    }
}
