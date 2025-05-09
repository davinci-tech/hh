package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.networkclient.IRequest;

/* loaded from: classes3.dex */
public class erk implements IRequest {

    @SerializedName("planId")
    private String d;

    @SerializedName("lang")
    private String e;

    private erk(d dVar) {
        this.d = dVar.d;
        this.e = dVar.c;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return eta.ae();
    }

    public static final class d {
        private String c;
        private String d;

        public d d(String str) {
            this.d = str;
            return this;
        }

        public d a(String str) {
            this.c = str;
            return this;
        }

        public erk c() {
            return new erk(this);
        }
    }
}
