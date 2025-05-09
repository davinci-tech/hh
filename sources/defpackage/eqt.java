package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.networkclient.IRequest;

/* loaded from: classes3.dex */
public class eqt implements IRequest {

    @SerializedName("lang")
    private String d;

    @SerializedName("type")
    private Integer e;

    private eqt(e eVar) {
        this.e = eVar.c;
        this.d = eVar.b;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return eta.s();
    }

    public static final class e {
        private String b;
        private Integer c;

        public e c(Integer num) {
            this.c = num;
            return this;
        }

        public e b(String str) {
            this.b = str;
            return this;
        }

        public eqt d() {
            return new eqt(this);
        }
    }
}
