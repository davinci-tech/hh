package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.networkclient.IRequest;

/* loaded from: classes3.dex */
public class esj implements IRequest {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("lang")
    private String f12222a;

    @SerializedName("courseCategory")
    private Integer d;

    @SerializedName("pageNum")
    private Integer e;

    private esj(b bVar) {
        this.d = bVar.b;
        this.e = bVar.f12223a;
        this.f12222a = bVar.c;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return eta.bq();
    }

    public static final class b {

        /* renamed from: a, reason: collision with root package name */
        private Integer f12223a;
        private Integer b;
        private String c;

        public b a(Integer num) {
            this.b = num;
            return this;
        }

        public b c(Integer num) {
            this.f12223a = num;
            return this;
        }

        public b a(String str) {
            this.c = str;
            return this;
        }

        public esj d() {
            return new esj(this);
        }
    }
}
