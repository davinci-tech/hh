package com.huawei.openalliance.ad.processor.eventbeans;

import com.huawei.openalliance.ad.constant.EventType;
import com.huawei.openalliance.ad.inter.data.MaterialClickInfo;

/* loaded from: classes5.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    private int f7454a;
    private int b;
    private String c;
    private Integer d;
    private MaterialClickInfo e;
    private String f;
    private String g;
    private boolean h;
    private String i;
    private boolean j;
    private EventType k;
    private String l;
    private String m;

    public static final class a {
        private String c;
        private Integer d;
        private String i;
        private String l;

        /* renamed from: a, reason: collision with root package name */
        private int f7455a = 0;
        private int b = 0;
        private MaterialClickInfo e = null;
        private String f = null;
        private String g = null;
        private boolean h = true;
        private boolean j = true;
        private EventType k = EventType.CLICK;

        public a e(String str) {
            this.i = str;
            return this;
        }

        public a d(String str) {
            this.g = str;
            return this;
        }

        public a c(String str) {
            this.f = str;
            return this;
        }

        public a b(boolean z) {
            this.h = z;
            return this;
        }

        public a b(String str) {
            this.c = str;
            return this;
        }

        public a b(int i) {
            this.b = i;
            return this;
        }

        public b a() {
            return new b(this);
        }

        public a a(boolean z) {
            this.j = z;
            return this;
        }

        public a a(String str) {
            this.l = str;
            return this;
        }

        public a a(Integer num) {
            this.d = num;
            return this;
        }

        public a a(MaterialClickInfo materialClickInfo) {
            this.e = materialClickInfo;
            return this;
        }

        public a a(EventType eventType) {
            this.k = eventType;
            return this;
        }

        public a a(int i) {
            this.f7455a = i;
            return this;
        }
    }

    public EventType m() {
        return this.k;
    }

    public String l() {
        return this.i;
    }

    public boolean k() {
        return this.h;
    }

    public String j() {
        return this.g;
    }

    public String i() {
        return this.f;
    }

    public MaterialClickInfo h() {
        return this.e;
    }

    public Integer g() {
        return this.d;
    }

    public String f() {
        return this.c;
    }

    public int e() {
        return this.b;
    }

    public int d() {
        return this.f7454a;
    }

    public boolean c() {
        return this.j;
    }

    public String b() {
        return this.l;
    }

    public void a(String str) {
        this.l = str;
    }

    public String a() {
        return this.m;
    }

    private b(a aVar) {
        this.j = true;
        this.k = EventType.CLICK;
        this.f7454a = aVar.f7455a;
        this.b = aVar.b;
        this.c = aVar.c;
        this.d = aVar.d;
        this.e = aVar.e;
        this.f = aVar.f;
        this.g = aVar.g;
        this.h = aVar.h;
        this.i = aVar.i;
        this.j = aVar.j;
        this.k = aVar.k;
        this.m = aVar.l;
    }
}
