package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.networkclient.IRequest;

/* loaded from: classes3.dex */
public class esp implements IRequest {

    @SerializedName("planId")
    private String b;

    @SerializedName("reportData")
    private String e;

    private esp(e eVar) {
        this.b = eVar.f12231a;
        this.e = eVar.e;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return eta.cf();
    }

    public static final class e {

        /* renamed from: a, reason: collision with root package name */
        private String f12231a;
        private String e;

        public e e(String str) {
            this.f12231a = str;
            return this;
        }

        public e a(String str) {
            this.e = str;
            return this;
        }

        public esp e() {
            return new esp(this);
        }
    }
}
