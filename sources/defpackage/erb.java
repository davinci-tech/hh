package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.networkclient.IRequest;

/* loaded from: classes3.dex */
public class erb implements IRequest {

    @SerializedName("type")
    private int b;

    private erb(e eVar) {
        this.b = eVar.d;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return eta.d();
    }

    public static final class e {
        private int d;

        public e d(int i) {
            this.d = i;
            return this;
        }

        public erb d() {
            return new erb(this);
        }
    }
}
