package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.List;

/* loaded from: classes3.dex */
public class bzn {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("createTime")
    private long f569a;

    @SerializedName("deviceModel")
    private String b;

    @SerializedName("bundleName")
    private String c;

    @SerializedName("deviceCategory")
    private String d;

    @SerializedName("abilityName")
    private String e;

    @SerializedName("effectiveTime")
    private long f;

    @SerializedName("featureName")
    private String g;

    @SerializedName("deviceName")
    private String h;

    @SerializedName("featureType")
    private int i;

    @SerializedName("expirationTime")
    private long j;

    @SerializedName("formName")
    private String k;

    @SerializedName("messageName")
    private String l;

    @SerializedName("moduleName")
    private String m;

    @SerializedName("messageId")
    private String n;

    @SerializedName("formDimension")
    private String o;

    @SerializedName("timePeriods")
    private List<e> s;

    @SerializedName("timeZone")
    private String t;

    public static class e {

        /* renamed from: a, reason: collision with root package name */
        private long f571a;
        private long d;

        public e(long j, long j2) {
            this.d = j;
            this.f571a = j2;
            if (j2 - j > 5400000) {
                LogUtil.h("TimePeriod", "TimePeriod too large, startTime: ", Long.valueOf(j), ", endTime: ", Long.valueOf(j2));
                this.f571a = this.d + 5400000;
            }
        }

        public void d(long j, long j2) {
            this.d = j;
            if (j2 - j > 5400000) {
                j2 = j + 5400000;
            }
            this.f571a = j2;
        }

        public long c() {
            return this.d;
        }

        public long e() {
            return this.f571a;
        }

        public String toString() {
            return "TimePeriod{mStartTime=" + this.d + ", mEndTime=" + this.f571a + '}';
        }
    }

    public bzn(b bVar) {
        this.l = bVar.m;
        this.n = bVar.o;
        this.b = bVar.b;
        this.d = bVar.c;
        this.h = bVar.f;
        this.i = bVar.i;
        this.t = bVar.q;
        this.f569a = bVar.e;
        this.f = bVar.g;
        this.j = bVar.j;
        this.c = bVar.f570a;
        this.m = bVar.l;
        this.e = bVar.d;
        this.o = bVar.k;
        this.k = bVar.n;
        this.g = bVar.h;
        this.s = bVar.p;
    }

    public String l() {
        return this.l;
    }

    public String o() {
        return this.n;
    }

    public String b() {
        return this.b;
    }

    public String a() {
        return this.d;
    }

    public String f() {
        return this.h;
    }

    public int i() {
        return this.i;
    }

    public String s() {
        return this.t;
    }

    public long e() {
        return this.f569a;
    }

    public long j() {
        return this.f;
    }

    public long g() {
        return this.j;
    }

    public String c() {
        return this.c;
    }

    public String k() {
        return this.m;
    }

    public String d() {
        return this.e;
    }

    public String m() {
        return this.o;
    }

    public String n() {
        return this.k;
    }

    public String h() {
        return this.g;
    }

    public List<e> r() {
        return this.s;
    }

    public String toString() {
        return "DonateBean{MessageName=" + this.l + ", MessageId=" + this.n + ", DeviceModel=" + this.b + ", DeviceCategory=" + this.d + ", DeviceName=" + this.h + ", FeatureType=" + this.i + ", TimeZone=" + this.t + ", CreateTime=" + this.f569a + ", EffectiveTime=" + this.f + ", ExpirationTime=" + this.j + ", BundleName=" + this.c + ", ModuleName=" + this.m + ", AbilityName=" + this.e + ", FormDimension=" + this.o + ", FormName=" + this.k + ", FeatureName=" + this.g + ", TimePeriods=" + this.s;
    }

    public static class b {

        /* renamed from: a, reason: collision with root package name */
        private String f570a;
        private String b;
        private String c;
        private String d;
        private long e;
        private String f;
        private long g;
        private String h;
        private int i;
        private long j;
        private String k;
        private String l;
        private String m;
        private String n;
        private String o;
        private List<e> p;
        private String q;

        public bzn d() {
            return new bzn(this);
        }

        public b f(String str) {
            this.m = str;
            return this;
        }

        public b g(String str) {
            this.o = str;
            return this;
        }

        public b a(String str) {
            this.b = str;
            return this;
        }

        public b c(String str) {
            this.c = str;
            return this;
        }

        public b b(String str) {
            this.f = str;
            return this;
        }

        public b a(int i) {
            this.i = i;
            return this;
        }

        public b l(String str) {
            this.q = str;
            return this;
        }

        public b a(long j) {
            this.e = j;
            return this;
        }

        public b b(long j) {
            this.g = j;
            return this;
        }

        public b c(long j) {
            this.j = j;
            return this;
        }

        public b e(String str) {
            this.f570a = str;
            return this;
        }

        public b o(String str) {
            this.l = str;
            return this;
        }

        public b d(String str) {
            this.d = str;
            return this;
        }

        public b i(String str) {
            this.k = str;
            return this;
        }

        public b h(String str) {
            this.n = str;
            return this;
        }

        public b j(String str) {
            this.h = str;
            return this;
        }

        public b b(List<e> list) {
            this.p = list;
            return this;
        }
    }
}
