package defpackage;

import android.os.Bundle;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.ecologydevice.connectivity.ScanFilter;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class dcz {

    /* renamed from: a, reason: collision with root package name */
    private String f11597a;
    private ArrayList<d> aa;
    private ArrayList<d> ab;
    private List<bjf> ac;
    private String ad;
    private String b;
    private ArrayList<d> c;
    private String d;
    private ArrayList<d> e;
    private String f;
    private String g;
    private e h;
    private Bundle i;
    private String j;
    private b k;
    private HealthDevice.HealthDeviceKind l;
    private boolean m;
    private int n;
    private boolean o;
    private String p;
    private ArrayList<c> q;
    private ArrayList<d> r;
    private String s;
    private String t;
    private String u;
    private ScanFilter v;
    private String w;
    private a x;
    private String y;
    private cev z;

    public dcz(int i) {
        this.g = "";
        this.o = false;
        this.m = true;
        this.n = i;
    }

    public dcz() {
        this.n = -1;
        this.g = "";
        this.o = false;
        this.m = true;
        this.i = new Bundle();
        this.k = new b();
        this.x = new a();
        this.q = new ArrayList<>(10);
        this.e = new ArrayList<>(10);
        this.r = new ArrayList<>(10);
        this.c = new ArrayList<>(10);
        this.aa = new ArrayList<>(10);
        this.ab = new ArrayList<>(10);
        this.h = new e();
    }

    public long h() {
        return new cke("deviceUsedTime").b(cpp.a(), this.w);
    }

    public int o() {
        return ((Integer) cpt.d(Integer.valueOf(this.n))).intValue();
    }

    public String f() {
        return (String) cpt.d(this.g);
    }

    public void f(String str) {
        this.g = str;
    }

    public boolean i() {
        return this.o;
    }

    public void d(boolean z) {
        this.o = z;
    }

    public boolean u() {
        return this.m;
    }

    public void e(boolean z) {
        this.m = z;
    }

    public void a(String str, String str2, String str3) {
        this.k.d = str;
        this.k.b = str2;
        this.k.e = str3;
    }

    public a v() {
        return (a) cpt.d(this.x);
    }

    public ArrayList<d> e() {
        return (ArrayList) cpt.d(this.e);
    }

    public String s() {
        return (String) cpt.d(this.p);
    }

    public void g(String str) {
        this.p = (String) cpt.d(str);
    }

    public HealthDevice.HealthDeviceKind l() {
        return (HealthDevice.HealthDeviceKind) cpt.d(this.l);
    }

    public void b(HealthDevice.HealthDeviceKind healthDeviceKind) {
        this.l = (HealthDevice.HealthDeviceKind) cpt.d(healthDeviceKind);
    }

    public cev x() {
        return (cev) cpt.d(this.z);
    }

    public void b(cev cevVar) {
        this.z = (cev) cpt.d(cevVar);
    }

    public String y() {
        return (String) cpt.d(this.y);
    }

    public void m(String str) {
        this.y = str;
    }

    public String g() {
        return (String) cpt.d(this.j);
    }

    public void b(String str) {
        this.j = (String) cpt.d(str);
    }

    public String t() {
        return (String) cpt.d(this.w);
    }

    public void n(String str) {
        this.w = (String) cpt.d(str);
    }

    public String ac() {
        return (String) cpt.d(this.ad);
    }

    public void o(String str) {
        this.ad = (String) cpt.d(str);
    }

    public String j() {
        return (String) cpt.d(this.f);
    }

    public void j(String str) {
        this.f = str;
    }

    public b n() {
        return (b) cpt.d(this.k);
    }

    public String p() {
        return (String) cpt.d(this.s);
    }

    public void i(String str) {
        this.s = str;
    }

    public String k() {
        return (String) cpt.d(this.t);
    }

    public void h(String str) {
        this.t = str;
    }

    public String r() {
        return (String) cpt.d(this.u);
    }

    public void k(String str) {
        this.u = str;
    }

    public String b() {
        return (String) cpt.d(this.f11597a);
    }

    public void a(String str) {
        this.f11597a = str;
    }

    public ScanFilter w() {
        return this.v;
    }

    public void a(ScanFilter scanFilter) {
        this.v = scanFilter;
    }

    public List<bjf> aa() {
        return this.ac;
    }

    public void a(List<bjf> list) {
        this.ac = list;
    }

    public e m() {
        return (e) cpt.d(this.h);
    }

    public void d(String str, String str2) {
        this.q.add(new c(str, str2));
    }

    public void e(String str, String str2) {
        this.e.add(new d(str, str2));
    }

    public void h(String str, String str2) {
        Bundle bundle = this.i;
        if (bundle != null) {
            bundle.putString(str, str2);
        }
    }

    public String c(String str) {
        Bundle bundle = this.i;
        return bundle == null ? "" : bundle.getString(str);
    }

    public ArrayList<d> q() {
        return this.r;
    }

    public void a(String str, String str2) {
        this.r.add(new d(str, str2));
    }

    public ArrayList<d> ad() {
        return this.aa;
    }

    public void c(String str, String str2, String str3) {
        this.aa.add(new d(str, str2, str3));
    }

    public ArrayList<d> d() {
        return (ArrayList) cpt.d(this.c);
    }

    public void c(String str, String str2) {
        this.c.add(new d(str, str2));
    }

    public ArrayList<d> ab() {
        return this.ab;
    }

    public void b(String str, String str2) {
        this.ab.add(new d(str, str2));
    }

    public String a() {
        return (String) cpt.d(this.d);
    }

    public void e(String str) {
        this.d = (String) cpt.d(str);
    }

    public String c() {
        return (String) cpt.d(this.b);
    }

    public void d(String str) {
        this.b = (String) cpt.d(str);
    }

    public static class a {
        private String b;

        public String d() {
            return this.b;
        }

        public void a(String str) {
            this.b = str;
        }
    }

    public static class b {

        /* renamed from: a, reason: collision with root package name */
        private String f11598a;
        private String b;
        private String d;
        private String e;

        public String b() {
            return (String) cpt.d(this.b);
        }

        public String d() {
            return (String) cpt.d(this.d);
        }

        public String c() {
            return this.e;
        }

        public String a() {
            return this.f11598a;
        }

        public void a(String str) {
            this.f11598a = str;
        }
    }

    public static class d {

        /* renamed from: a, reason: collision with root package name */
        private String f11599a;
        private String c;
        private String e;

        protected d(String str, String str2) {
            this.c = str;
            this.e = str2;
        }

        protected d(String str, String str2, String str3) {
            this.f11599a = str;
            this.c = str2;
            this.e = str3;
        }

        public String b() {
            return (String) cpt.d(this.f11599a);
        }

        public String c() {
            return (String) cpt.d(this.c);
        }

        public String e() {
            return (String) cpt.d(this.e);
        }
    }

    public static class e {
        private String b = "";

        public String d() {
            return this.b;
        }

        public void a(String str) {
            this.b = str;
        }
    }

    public static class c {
        private String b;
        private String e;

        protected c(String str, String str2) {
            this.b = str;
            this.e = str2;
        }
    }
}
