package com.huawei.hms.ads.jsb.inner.data;

import android.location.Location;
import java.util.List;

/* loaded from: classes4.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private List<Integer> f4339a;
    private Integer b;
    private Integer c;
    private Integer d;
    private Location e;
    private String f;

    public String f() {
        return this.f;
    }

    public Location e() {
        return this.e;
    }

    public Integer d() {
        return this.d;
    }

    public Integer c() {
        return this.c;
    }

    /* renamed from: com.huawei.hms.ads.jsb.inner.data.a$a, reason: collision with other inner class name */
    public static final class C0078a {

        /* renamed from: a, reason: collision with root package name */
        private List<Integer> f4340a;
        private Integer b;
        private Integer c;
        private Integer d;
        private Location e;
        private String f;

        public C0078a c(Integer num) {
            this.d = num;
            return this;
        }

        public C0078a b(Integer num) {
            this.c = num;
            return this;
        }

        public a a() {
            a aVar = new a();
            aVar.f4339a = this.f4340a;
            aVar.b = this.b;
            aVar.c = this.c;
            aVar.d = this.d;
            aVar.e = this.e;
            aVar.f = this.f;
            return aVar;
        }

        public C0078a a(List<Integer> list) {
            this.f4340a = list;
            return this;
        }

        public C0078a a(String str) {
            this.f = str;
            return this;
        }

        public C0078a a(Integer num) {
            this.b = num;
            return this;
        }

        public C0078a a(Location location) {
            this.e = location;
            return this;
        }
    }

    public Integer b() {
        return this.b;
    }

    public List<Integer> a() {
        return this.f4339a;
    }
}
