package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.networkclient.IRequest;

/* loaded from: classes3.dex */
public class erd implements IRequest {

    @SerializedName("reportType")
    private int b;

    @SerializedName("planId")
    private String c;

    @SerializedName("planType")
    private int e;

    private erd(a aVar) {
        this.b = aVar.b;
        this.c = aVar.d;
        this.e = aVar.c;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return eta.z();
    }

    public static final class a {
        private int b;
        private int c;
        private String d;

        public a b(int i) {
            this.b = i;
            return this;
        }

        public a d(String str) {
            this.d = str;
            return this;
        }

        public a c(int i) {
            this.c = i;
            return this;
        }

        public erd d() {
            return new erd(this);
        }
    }
}
