package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.networkclient.IRequest;

/* loaded from: classes3.dex */
public class esb implements IRequest {

    @SerializedName("planId")
    private String c;

    private esb(a aVar) {
        this.c = aVar.b;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return eta.bm();
    }

    public static final class a {
        private String b;

        public a c(String str) {
            this.b = str;
            return this;
        }

        public esb c() {
            return new esb(this);
        }
    }
}
