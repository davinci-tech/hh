package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.networkclient.IRequest;

/* loaded from: classes3.dex */
public class ere implements IRequest {

    @SerializedName("timbre")
    private String b;

    @SerializedName("version")
    private Long e;

    private ere(e eVar) {
        this.e = eVar.e;
        this.b = eVar.c;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return eta.af();
    }

    public static final class e {
        private String c;
        private Long e;

        public e d(Long l) {
            this.e = l;
            return this;
        }

        public e c(String str) {
            this.c = str;
            return this;
        }

        public ere e() {
            return new ere(this);
        }
    }
}
