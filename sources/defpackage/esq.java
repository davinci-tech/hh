package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.health.plan.model.intplan.UserProfileBean;
import com.huawei.networkclient.IRequest;

/* loaded from: classes3.dex */
public class esq implements IRequest {

    @SerializedName("planType")
    private int b;

    @SerializedName("isRunFlag")
    private int c;

    @SerializedName("userInfo")
    private UserProfileBean d;

    @SerializedName("planId")
    private String e;

    private esq(e eVar) {
        this.d = eVar.f12232a;
        this.e = eVar.e;
        this.b = eVar.d;
        this.c = eVar.b;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return eta.by();
    }

    public static final class e {

        /* renamed from: a, reason: collision with root package name */
        private UserProfileBean f12232a;
        private int b;
        private int d;
        private String e;

        public e b(UserProfileBean userProfileBean) {
            this.f12232a = userProfileBean;
            return this;
        }

        public e e(String str) {
            this.e = str;
            return this;
        }

        public e e(int i) {
            this.d = i;
            return this;
        }

        public e a(int i) {
            this.b = i;
            return this;
        }

        public esq d() {
            return new esq(this);
        }
    }
}
