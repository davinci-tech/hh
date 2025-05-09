package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.networkclient.IRequest;

/* loaded from: classes3.dex */
public class est implements IRequest {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("planType")
    private int f12235a;

    @SerializedName("reportUpdateTime")
    private long b;

    @SerializedName("reportType")
    private int c;

    @SerializedName("planId")
    private String d;

    @SerializedName("report")
    private String e;

    private est(a aVar) {
        this.c = aVar.c;
        this.d = aVar.f12236a;
        this.f12235a = aVar.b;
        this.b = aVar.e;
        this.e = aVar.d;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return eta.bd();
    }

    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        private String f12236a;
        private int b;
        private int c;
        private String d;
        private long e;

        public a b(int i) {
            this.c = i;
            return this;
        }

        public a d(String str) {
            this.f12236a = str;
            return this;
        }

        public a a(int i) {
            this.b = i;
            return this;
        }

        public a c(long j) {
            this.e = j;
            return this;
        }

        public a e(String str) {
            this.d = str;
            return this;
        }

        public est d() {
            return new est(this);
        }
    }
}
