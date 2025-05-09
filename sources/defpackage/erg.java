package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.networkclient.IRequest;

/* loaded from: classes8.dex */
public class erg implements IRequest {

    @SerializedName("cond")
    private String b;

    private erg(c cVar) {
        this.b = cVar.e;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return eta.am();
    }

    public static final class c {
        private String e;

        public c d(String str) {
            this.e = str;
            return this;
        }

        public erg c() {
            return new erg(this);
        }
    }
}
