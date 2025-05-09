package defpackage;

import com.huawei.hms.ads.dynamic.a;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.EmuiBuild;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

/* loaded from: classes5.dex */
public class kip {

    /* renamed from: a, reason: collision with root package name */
    private String f14400a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private String k;
    private String l;
    private String m;
    private String n;
    private String o;
    private String p;
    private String q;
    private String r;
    private String s;
    private String t;
    private String x;

    public void s(String str) {
        this.q = str;
    }

    public void n(String str) {
        this.k = str;
    }

    public void o(String str) {
        this.n = str;
    }

    public void k(String str) {
        this.o = str;
    }

    public void b(String str) {
        this.b = str;
    }

    public void c(String str) {
        this.e = str;
    }

    public void i(String str) {
        this.g = str;
    }

    public void a(String str) {
        this.d = str;
    }

    public void e(String str) {
        this.c = str;
    }

    public void g(String str) {
        this.h = str;
    }

    public void r(String str) {
        this.r = str;
    }

    public void h(String str) {
        this.i = str;
    }

    public void q(String str) {
        this.s = str;
    }

    public void m(String str) {
        this.l = str;
    }

    public void p(String str) {
        this.p = str;
    }

    public void t(String str) {
        this.t = str;
    }

    public void u(String str) {
        this.x = str;
    }

    public void f(String str) {
        this.j = str;
    }

    public void j(String str) {
        this.f = str;
    }

    public void d(String str) {
        this.f14400a = str;
    }

    public void l(String str) {
        this.m = str;
    }

    public byte[] e() {
        return cvx.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01 + cvx.e(this.d.length() / 2) + this.d);
    }

    public byte[] b() {
        return cvx.a(a.t + cvx.e(this.c.length() / 2) + this.c);
    }

    public byte[] g() {
        return cvx.a("03" + cvx.e(this.h.length() / 2) + this.h);
    }

    public byte[] y() {
        return cvx.a("04" + cvx.e(this.r.length() / 2) + this.r);
    }

    public byte[] f() {
        return cvx.a("05" + cvx.e(this.i.length() / 2) + this.i);
    }

    public byte[] p() {
        return cvx.a("06" + cvx.e(this.s.length() / 2) + this.s);
    }

    public byte[] n() {
        return cvx.a("07" + cvx.e(this.l.length() / 2) + this.l);
    }

    public byte[] q() {
        return cvx.a("08" + cvx.e(this.p.length() / 2) + this.p);
    }

    public byte[] t() {
        return cvx.a("09" + cvx.e(this.t.length() / 2) + this.t);
    }

    public byte[] x() {
        return cvx.a("0A" + cvx.e(this.x.length() / 2) + this.x);
    }

    public byte[] i() {
        return cvx.a("0B" + cvx.e(this.j.length() / 2) + this.j);
    }

    public byte[] j() {
        return cvx.a("0C" + cvx.e(this.f.length() / 2) + this.f);
    }

    public byte[] c() {
        return cvx.a("0D" + cvx.e(this.f14400a.length() / 2) + this.f14400a);
    }

    public byte[] h() {
        return cvx.a("10" + cvx.e(this.g.length() / 2) + this.g);
    }

    public byte[] a() {
        return cvx.a("11" + cvx.e(this.e.length() / 2) + this.e);
    }

    public byte[] l() {
        return cvx.a("0E" + cvx.e(this.m.length() / 2) + this.m);
    }

    public byte[] d() {
        return cvx.a("12" + cvx.e(this.b.length() / 2) + this.b);
    }

    public byte[] m() {
        return cvx.a("13" + cvx.e(this.k.length() / 2) + this.k);
    }

    public byte[] k() {
        return cvx.a("14" + cvx.e(this.n.length() / 2) + this.n);
    }

    public byte[] o() {
        return cvx.a("15" + cvx.e(this.o.length() / 2) + this.o);
    }

    public byte[] r() {
        return cvx.a("16" + cvx.e(this.q.length() / 2) + this.q);
    }

    public int c(List<Integer> list) {
        int length;
        Iterator<Integer> it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            int intValue = it.next().intValue();
            switch (intValue) {
                case 1:
                    length = e().length;
                    break;
                case 2:
                    length = b().length;
                    break;
                case 3:
                    length = g().length;
                    break;
                case 4:
                    length = y().length;
                    break;
                case 5:
                    length = f().length;
                    break;
                case 6:
                    length = p().length;
                    break;
                case 7:
                    length = n().length;
                    break;
                case 8:
                    length = q().length;
                    break;
                case 9:
                    length = t().length;
                    break;
                case 10:
                    length = x().length;
                    break;
                case 11:
                    length = i().length;
                    break;
                case 12:
                    length = j().length;
                    break;
                default:
                    length = d(intValue);
                    break;
            }
            i += length;
        }
        return i;
    }

    private int d(int i) {
        switch (i) {
            case 13:
                return c().length;
            case 14:
                return l().length;
            case 15:
            default:
                LogUtil.h("PhoneInfo", "can't recognize tag");
                return 0;
            case 16:
                return h().length;
            case 17:
                return a().length;
            case 18:
                return d().length;
            case 19:
                return m().length;
            case 20:
                return k().length;
            case 21:
                return o().length;
            case 22:
                return r().length;
        }
    }

    public void a(List<Integer> list, ByteBuffer byteBuffer) {
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            int intValue = it.next().intValue();
            switch (intValue) {
                case 1:
                    byteBuffer.put(e());
                    break;
                case 2:
                    byteBuffer.put(b());
                    break;
                case 3:
                    byteBuffer.put(g());
                    break;
                case 4:
                    byteBuffer.put(y());
                    break;
                case 5:
                    byteBuffer.put(f());
                    break;
                case 6:
                    byteBuffer.put(p());
                    break;
                case 7:
                    byteBuffer.put(n());
                    break;
                case 8:
                    byteBuffer.put(q());
                    break;
                case 9:
                    byteBuffer.put(t());
                    break;
                case 10:
                    byteBuffer.put(x());
                    break;
                case 11:
                    byteBuffer.put(i());
                    break;
                case 12:
                    byteBuffer.put(j());
                    break;
                case 13:
                    byteBuffer.put(c());
                    break;
                default:
                    d(intValue, byteBuffer);
                    break;
            }
        }
    }

    private void d(int i, ByteBuffer byteBuffer) {
        switch (i) {
            case 14:
                byteBuffer.put(l());
                break;
            case 15:
            default:
                LogUtil.h("PhoneInfo", "can't recognize tag");
                break;
            case 16:
                byteBuffer.put(h());
                break;
            case 17:
                byteBuffer.put(a());
                break;
            case 18:
                byteBuffer.put(d());
                break;
            case 19:
                byteBuffer.put(m());
                break;
            case 20:
                byteBuffer.put(k());
                break;
            case 21:
                byteBuffer.put(o());
                break;
            case 22:
                byteBuffer.put(r());
                break;
        }
    }

    public int s() {
        if (CommonUtil.bh()) {
            return EmuiBuild.f13113a;
        }
        return 0;
    }

    public String w() {
        int currentTimeMillis = (int) (System.currentTimeMillis() / 1000);
        return cvx.e(currentTimeMillis >> 24) + cvx.e((currentTimeMillis >> 16) & 255) + cvx.e((currentTimeMillis >> 8) & 255) + cvx.e(currentTimeMillis & 255);
    }

    public String u() {
        int offset = TimeZone.getDefault().getOffset(System.currentTimeMillis()) / 3600;
        int i = offset / 1000;
        int abs = (Math.abs(offset % 1000) * 60) / 1000;
        if (i < 0) {
            i = Math.abs(i) + 128;
        }
        int i2 = (i << 8) + abs;
        return cvx.e(i2 >> 8) + cvx.e(i2 & 255);
    }
}
