package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.networkclient.IRequest;

/* loaded from: classes3.dex */
public class esf implements IRequest {

    @SerializedName("type")
    private Integer e;

    private esf(b bVar) {
        this.e = bVar.f12218a;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return eta.bt();
    }

    public static final class b {

        /* renamed from: a, reason: collision with root package name */
        private Integer f12218a;

        public b a(Integer num) {
            this.f12218a = num;
            return this;
        }

        public esf e() {
            return new esf(this);
        }
    }
}
