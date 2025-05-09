package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.networkclient.IRequest;

/* loaded from: classes3.dex */
public class eso implements IRequest {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("weekNum")
    private Integer f12229a;

    @SerializedName("planId")
    private String b;

    @SerializedName("record")
    private mob c;

    @SerializedName("planType")
    private Integer d;

    @SerializedName("dayNum")
    private Integer e;

    private eso(a aVar) {
        this.d = aVar.c;
        this.b = aVar.f12230a;
        this.f12229a = aVar.d;
        this.e = aVar.e;
        this.c = aVar.b;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return eta.bx();
    }

    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        private String f12230a;
        private mob b;
        private Integer c;
        private Integer d;
        private Integer e;

        public a a(Integer num) {
            this.c = num;
            return this;
        }

        public a d(String str) {
            this.f12230a = str;
            return this;
        }

        public a b(Integer num) {
            this.d = num;
            return this;
        }

        public a e(Integer num) {
            this.e = num;
            return this;
        }

        public a d(mob mobVar) {
            this.b = mobVar;
            return this;
        }

        public eso b() {
            return new eso(this);
        }
    }
}
