package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.networkclient.IRequest;

/* loaded from: classes3.dex */
public class eqr implements IRequest {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("planId")
    private String f12176a;

    @SerializedName("returnAlgorithmInfos")
    private Boolean b;

    @SerializedName("weekIndex")
    private Integer e;

    private eqr(e eVar) {
        this.f12176a = eVar.f12177a;
        this.e = eVar.b;
        this.b = eVar.e;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return eta.u();
    }

    public static final class e {

        /* renamed from: a, reason: collision with root package name */
        private String f12177a;
        private Integer b;
        private Boolean e;

        public e b(String str) {
            this.f12177a = str;
            return this;
        }

        public e e(Integer num) {
            this.b = num;
            return this;
        }

        public e d(Boolean bool) {
            this.e = bool;
            return this;
        }

        public eqr a() {
            return new eqr(this);
        }
    }
}
