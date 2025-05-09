package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.networkclient.IRequest;

/* loaded from: classes3.dex */
public class eqw implements IRequest {

    @SerializedName("categoryCode")
    private String d;

    private eqw(d dVar) {
        this.d = dVar.b;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return eta.ce();
    }

    public static final class d {
        private String b;

        public d a(String str) {
            this.b = str;
            return this;
        }

        public eqw a() {
            return new eqw(this);
        }
    }
}
