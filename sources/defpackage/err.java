package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.networkclient.IRequest;

/* loaded from: classes3.dex */
public class err implements IRequest {

    @SerializedName("planId")
    private String c;

    @SerializedName("name")
    private String d;

    private err(d dVar) {
        this.c = dVar.b;
        this.d = dVar.c;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return eta.av();
    }

    public static final class d {
        private String b;
        private String c;

        public d e(String str) {
            this.b = str;
            return this;
        }

        public d a(String str) {
            this.c = str;
            return this;
        }

        public err d() {
            return new err(this);
        }
    }
}
