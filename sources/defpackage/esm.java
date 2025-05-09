package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.networkclient.IRequest;

/* loaded from: classes3.dex */
public class esm implements IRequest {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("planId")
    private String f12227a;

    @SerializedName("planType")
    private int b;

    @SerializedName("propType")
    private String d;

    @SerializedName("propValue")
    private String e;

    private esm(b bVar) {
        this.f12227a = bVar.b;
        this.b = bVar.c;
        this.d = bVar.d;
        this.e = bVar.e;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return eta.cb();
    }

    public static final class b {
        private String b;
        private int c;
        private String d;
        private String e;

        public b d(String str) {
            this.b = str;
            return this;
        }

        public b a(int i) {
            this.c = i;
            return this;
        }

        public b a(String str) {
            this.d = str;
            return this;
        }

        public b b(String str) {
            this.e = str;
            return this;
        }

        public esm a() {
            return new esm(this);
        }
    }
}
