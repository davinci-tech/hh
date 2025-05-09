package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.networkclient.IRequest;

/* loaded from: classes3.dex */
public class eqn implements IRequest {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("dateTime")
    private Long f12170a;

    @SerializedName("duration")
    private Long b;

    @SerializedName("completionRate")
    private Float c;

    @SerializedName("actualDistance")
    private Integer d;

    @SerializedName("actualCalorie")
    private Float e;

    @SerializedName("planId")
    private String j;

    private eqn(b bVar) {
        this.j = bVar.g;
        this.c = bVar.c;
        this.d = bVar.b;
        this.b = bVar.d;
        this.e = bVar.f12171a;
        this.f12170a = bVar.e;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return eta.p();
    }

    public static final class b {

        /* renamed from: a, reason: collision with root package name */
        private Float f12171a;
        private Integer b;
        private Float c;
        private Long d;
        private Long e;
        private String g;

        public b b(String str) {
            this.g = str;
            return this;
        }

        public b e(Float f) {
            this.c = f;
            return this;
        }

        public b a(Integer num) {
            this.b = num;
            return this;
        }

        public b d(Float f) {
            this.f12171a = f;
            return this;
        }

        public b c(Long l) {
            this.d = l;
            return this;
        }

        public b b(Long l) {
            this.e = l;
            return this;
        }

        public eqn a() {
            return new eqn(this);
        }
    }
}
