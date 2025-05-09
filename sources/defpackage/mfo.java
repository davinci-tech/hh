package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;

/* loaded from: classes6.dex */
public class mfo {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName(ParsedFieldTag.GAIN_COUNT)
    private final int f14955a;

    @SerializedName("timestamp")
    private long ac;

    @SerializedName(ParsedFieldTag.FIRST_TAB_PRIORITY)
    private int b;

    @SerializedName(ParsedFieldTag.FIRST_TAB_DESC)
    private String c;

    @SerializedName(ParsedFieldTag.ACTION_TYPE)
    private final int d;

    @SerializedName("endTime")
    private final String e;

    @SerializedName(ParsedFieldTag.GRAY_PRO_NAME)
    private String f;

    @SerializedName(ParsedFieldTag.GRAY_DESC)
    private final String g;

    @SerializedName(ParsedFieldTag.GOAL)
    private int h;

    @SerializedName(ParsedFieldTag.LIGHT_DESC)
    private final String i;

    @SerializedName("gainTime")
    private final String j;

    @SerializedName("medalLabel")
    private final int k;

    @SerializedName(ParsedFieldTag.MEDAL_LEVEL)
    private int l;

    @SerializedName(ParsedFieldTag.LIGHT_PRO_NAME)
    private String m;

    @SerializedName("medalName")
    private final String n;

    @SerializedName(ParsedFieldTag.MEDAL_ID)
    private final String o;

    @SerializedName(ParsedFieldTag.MEDAL_WEIGHT)
    private int p;

    @SerializedName(ParsedFieldTag.MEDAL_TYPE)
    private final String q;

    @SerializedName("message")
    private final String r;

    @SerializedName("medalUnit")
    private final int s;

    @SerializedName(ParsedFieldTag.REPEATABLE)
    private int t;

    @SerializedName("status")
    private int u;

    @SerializedName(ParsedFieldTag.TAKE_DATE)
    private final String v;

    @SerializedName(ParsedFieldTag.SECOND_TAB_DESC)
    private String w;

    @SerializedName(ParsedFieldTag.SECOND_TAB_PRIORITY)
    private int x;

    @SerializedName("startTime")
    private final String y;

    private mfo(b bVar) {
        this.o = bVar.l;
        this.n = bVar.m;
        this.r = bVar.t;
        this.i = bVar.h;
        this.g = bVar.g;
        this.f14955a = bVar.c;
        this.j = bVar.j;
        this.s = bVar.s;
        this.q = bVar.q;
        this.k = bVar.n;
        this.d = bVar.b;
        this.y = bVar.y;
        this.e = bVar.d;
        this.v = bVar.v;
        this.ac = bVar.ab;
        this.f = bVar.i;
        this.m = bVar.k;
        this.h = bVar.f;
        this.l = bVar.o;
        this.t = bVar.r;
        this.u = bVar.w;
        this.b = bVar.e;
        this.c = bVar.f14956a;
        this.x = bVar.x;
        this.w = bVar.u;
        this.p = bVar.p;
    }

    public String a() {
        return this.o;
    }

    public String d() {
        return this.n;
    }

    public String g() {
        return this.r;
    }

    public String e() {
        return this.i;
    }

    public String b() {
        return this.j;
    }

    public String c() {
        return this.q;
    }

    public static class b {

        /* renamed from: a, reason: collision with root package name */
        private String f14956a;
        private long ab;
        private int b;
        private int c;
        private String d;
        private int e;
        private int f;
        private String g;
        private String h;
        private String i;
        private String j;
        private String k;
        private final String l;
        private String m;
        private int n;
        private int o;
        private int p;
        private String q;
        private int r;
        private int s;
        private String t;
        private String u;
        private String v;
        private int w;
        private int x;
        private String y;

        public b(String str) {
            this.l = str;
        }

        public b g(String str) {
            this.m = str;
            return this;
        }

        public b i(String str) {
            this.t = str;
            return this;
        }

        public b f(String str) {
            this.h = str;
            return this;
        }

        public b h(String str) {
            this.g = str;
            return this;
        }

        public b e(int i) {
            this.c = i;
            return this;
        }

        public b a(String str) {
            this.j = str;
            return this;
        }

        public b l(String str) {
            this.q = str;
            return this;
        }

        public b e(long j) {
            this.ab = j;
            return this;
        }

        public b h(int i) {
            this.s = i;
            return this;
        }

        public b d(int i) {
            this.n = i;
            return this;
        }

        public b c(int i) {
            this.b = i;
            return this;
        }

        public b j(String str) {
            this.y = str;
            return this;
        }

        public b c(String str) {
            this.d = str;
            return this;
        }

        public b o(String str) {
            this.v = str;
            return this;
        }

        public b b(String str) {
            this.i = str;
            return this;
        }

        public b d(String str) {
            this.k = str;
            return this;
        }

        public b b(int i) {
            this.f = i;
            return this;
        }

        public b i(int i) {
            this.o = i;
            return this;
        }

        public b f(int i) {
            this.r = i;
            return this;
        }

        public b n(int i) {
            this.w = i;
            return this;
        }

        public b a(int i) {
            this.e = i;
            return this;
        }

        public b e(String str) {
            this.f14956a = str;
            return this;
        }

        public b g(int i) {
            this.x = i;
            return this;
        }

        public b n(String str) {
            this.u = str;
            return this;
        }

        public b j(int i) {
            this.p = i;
            return this;
        }

        public mfo b() {
            return new mfo(this);
        }
    }
}
