package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.networkclient.IRequest;

/* loaded from: classes3.dex */
public class eri implements IRequest {

    @SerializedName("planId")
    private String c;

    private eri(e eVar) {
        this.c = eVar.d;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return eta.ah();
    }

    public static final class e {
        private String d;

        public e c(String str) {
            this.d = str;
            return this;
        }

        public eri d() {
            return new eri(this);
        }
    }
}
