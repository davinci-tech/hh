package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.networkclient.IRequest;

/* loaded from: classes3.dex */
public class esi implements IRequest {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("planType")
    private Integer f12220a;

    @SerializedName("propensityWorkoutId")
    private String b;

    @SerializedName("replacedWorkoutId")
    private String c;

    @SerializedName("planId")
    private String d;

    @SerializedName("dayNo")
    private Integer e;

    @SerializedName("weekNo")
    private Integer h;

    private esi(c cVar) {
        this.d = cVar.e;
        this.f12220a = cVar.b;
        this.h = cVar.f;
        this.e = cVar.c;
        this.b = cVar.d;
        this.c = cVar.f12221a;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return eta.bw();
    }

    public static final class c {

        /* renamed from: a, reason: collision with root package name */
        private String f12221a;
        private Integer b;
        private Integer c;
        private String d;
        private String e;
        private Integer f;

        public c b(String str) {
            this.e = str;
            return this;
        }

        public c e(Integer num) {
            this.b = num;
            return this;
        }

        public c a(Integer num) {
            this.f = num;
            return this;
        }

        public c c(Integer num) {
            this.c = num;
            return this;
        }

        public c a(String str) {
            this.d = str;
            return this;
        }

        public c d(String str) {
            this.f12221a = str;
            return this;
        }

        public esi b() {
            return new esi(this);
        }
    }
}
