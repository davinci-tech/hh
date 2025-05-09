package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.networkclient.IRequest;

/* loaded from: classes3.dex */
public class erv implements IRequest {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("planId")
    private String f12201a;

    @SerializedName("remindTime")
    private Integer e;

    private erv(a aVar) {
        this.f12201a = aVar.f12202a;
        this.e = aVar.e;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return eta.bf();
    }

    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        private String f12202a;
        private Integer e;

        public a c(String str) {
            this.f12202a = str;
            return this;
        }

        public a c(Integer num) {
            this.e = num;
            return this;
        }

        public erv d() {
            return new erv(this);
        }
    }
}
