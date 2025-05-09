package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.networkclient.IRequest;

/* loaded from: classes7.dex */
public class eqz implements IRequest {

    @SerializedName("version")
    private String b;

    @SerializedName("actionId")
    private String d;

    private eqz(b bVar) {
        this.d = bVar.b;
        this.b = bVar.c;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return eta.aa();
    }

    public static final class b {
        private String b;
        private String c;

        public b b(String str) {
            this.b = str;
            return this;
        }

        public b e(String str) {
            this.c = str;
            return this;
        }

        public eqz d() {
            return new eqz(this);
        }
    }
}
