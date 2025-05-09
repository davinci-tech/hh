package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.networkclient.IRequest;

/* loaded from: classes3.dex */
public class eqj implements IRequest {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("recordId")
    private String f12164a;

    @SerializedName("planType")
    private Integer b;

    @SerializedName("planId")
    private String d;

    private eqj(e eVar) {
        this.b = eVar.f12165a;
        this.d = eVar.d;
        this.f12164a = eVar.c;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return eta.k();
    }

    public static final class e {

        /* renamed from: a, reason: collision with root package name */
        private Integer f12165a;
        private String c;
        private String d;

        public e c(Integer num) {
            this.f12165a = num;
            return this;
        }

        public e c(String str) {
            this.d = str;
            return this;
        }

        public e e(String str) {
            this.c = str;
            return this;
        }

        public eqj a() {
            return new eqj(this);
        }
    }
}
