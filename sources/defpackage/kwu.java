package defpackage;

import com.huawei.hwsportmodel.CommonSegment;
import health.compact.a.CommonUtil;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class kwu extends CommonSegment {
    private static final long serialVersionUID = -1816333042159669204L;

    /* renamed from: a, reason: collision with root package name */
    private float f14668a;
    private float b;
    private float c;
    private boolean d;
    private int e;
    private int f;
    private float g;
    private int h;
    private float i;
    private long j;
    private int k;
    private int l;
    private int m;
    private int n;
    private int o;
    private long p;
    private int q;
    private long r;
    private int s;
    private long t;
    private int v;

    public int a() {
        return 4;
    }

    @Override // com.huawei.hwsportmodel.CommonSegment
    public int getFieldNum() {
        return 13;
    }

    public kwu() {
        this.d = false;
        this.m = -1;
        this.n = -1;
        this.q = -1;
        this.f = -1;
        this.v = 0;
        this.e = 0;
        this.b = 0.0f;
        this.c = 0.0f;
        this.g = 0.0f;
        this.i = -1.0f;
        this.f14668a = -1.0f;
    }

    public kwu(kwu kwuVar) {
        this.d = false;
        this.m = -1;
        this.n = -1;
        this.q = -1;
        this.f = -1;
        this.v = 0;
        this.e = 0;
        this.b = 0.0f;
        this.c = 0.0f;
        this.g = 0.0f;
        this.i = -1.0f;
        this.f14668a = -1.0f;
        if (kwuVar != null) {
            this.t = kwuVar.t;
            this.j = kwuVar.j;
            this.o = kwuVar.o;
            this.k = kwuVar.k;
            this.h = kwuVar.h;
            this.s = kwuVar.s;
            this.r = kwuVar.r;
            this.p = kwuVar.p;
            this.m = kwuVar.m;
            this.n = kwuVar.n;
            this.q = kwuVar.q;
            this.f = kwuVar.f;
            this.v = kwuVar.v;
            this.e = kwuVar.e;
            this.b = kwuVar.b;
            this.c = kwuVar.c;
            this.g = kwuVar.g;
            this.i = kwuVar.i;
            this.f14668a = kwuVar.f14668a;
            return;
        }
        x();
    }

    public int y() {
        return this.v;
    }

    public void l(int i) {
        this.v = i;
    }

    public int n() {
        return this.l;
    }

    public void f(int i) {
        this.l = i;
    }

    public long t() {
        return this.t;
    }

    public void d(long j) {
        this.t = j;
    }

    public long f() {
        return this.j;
    }

    public void e(long j) {
        this.j = j;
    }

    public int k() {
        return this.o;
    }

    public void j(int i) {
        this.o = i;
    }

    public int l() {
        return this.k;
    }

    public void h(int i) {
        this.k = i;
    }

    public int g() {
        return this.h;
    }

    public void e(int i) {
        this.h = i;
    }

    public int r() {
        return this.s;
    }

    public void g(int i) {
        this.s = i;
    }

    public long p() {
        return this.r;
    }

    public void b(long j) {
        this.r = j;
    }

    public long q() {
        return this.p;
    }

    public void c(long j) {
        this.p = j;
    }

    public int m() {
        return this.m;
    }

    public void b(int i) {
        this.m = i;
    }

    public int o() {
        return this.n;
    }

    public void c(int i) {
        this.n = i;
    }

    public int s() {
        return this.q;
    }

    public void i(int i) {
        this.q = i;
    }

    public int i() {
        return this.f;
    }

    public void a(int i) {
        this.f = i;
    }

    public int c() {
        return this.e;
    }

    public void d(int i) {
        this.e = i;
    }

    public float b() {
        return this.b;
    }

    public void b(float f) {
        this.b = f;
    }

    public float e() {
        return this.c;
    }

    public void d(float f) {
        this.c = f;
    }

    public float h() {
        return this.g;
    }

    public void c(float f) {
        this.g = f;
    }

    public float j() {
        return this.i;
    }

    public void a(float f) {
        this.i = f;
    }

    public float d() {
        return this.f14668a;
    }

    public void e(float f) {
        this.f14668a = f;
    }

    public boolean w() {
        return (this.f == -1 && this.n == -1 && this.m == -1 && this.q == -1) ? false : true;
    }

    @Override // com.huawei.hwsportmodel.CommonSegment
    public int getSportSegmentMode() {
        return this.v > 0 ? 1 : 0;
    }

    public void u() {
        this.t = 0L;
        this.j = 0L;
        this.o = 0;
        this.k = 0;
        this.h = 0;
        this.s = 0;
        this.r = 0L;
        this.p = 0L;
        this.m = 0;
        this.n = 0;
        this.q = 0;
        this.f = 0;
        this.v = 0;
        this.e = 0;
        this.b = 0.0f;
        this.c = 0.0f;
        this.g = 0.0f;
        this.i = 0.0f;
        this.f14668a = 0.0f;
    }

    public final void x() {
        this.t = -1L;
        this.j = -1L;
        this.o = -1;
        this.k = -1;
        this.h = -1;
        this.s = -1;
        this.r = -1L;
        this.p = -1L;
        this.m = -1;
        this.n = -1;
        this.q = -1;
        this.f = -1;
        this.e = -1;
        this.b = -1.0f;
        this.c = -1.0f;
        this.g = -1.0f;
        this.i = -1.0f;
        this.f14668a = -1.0f;
    }

    @Override // com.huawei.hwsportmodel.CommonSegment
    public String toTrackString() {
        StringBuffer stringBuffer = new StringBuffer(50);
        toTrackString(stringBuffer);
        return stringBuffer.toString();
    }

    @Override // com.huawei.hwsportmodel.CommonSegment
    public void toTrackString(StringBuffer stringBuffer) {
        stringBuffer.append("tp=sec").append(";").append("sn=").append(n()).append(";").append("st=").append(t()).append(";").append("sd=").append(f()).append(";").append("sp=").append(k()).append(";").append("shr=").append(l()).append(";").append("sc=").append(g()).append(";").append("ssl=").append(r()).append(";").append("str=").append(p()).append(";").append("std=").append(q()).append(";").append("sgct=").append(m()).append(";").append("sgia=").append(o()).append(";").append("ssa=").append(s()).append(";").append("see=").append(i()).append(";").append("stp=").append(y()).append(";").append(v());
    }

    private StringBuffer v() {
        return new StringBuffer().append("sht=").append(c()).append(";sgtb=").append(b()).append(";svo=").append(e()).append(";svr=").append(h()).append(";spi=").append(j()).append(";spir=").append(d()).append(";").append(System.lineSeparator());
    }

    @Override // com.huawei.hwsportmodel.CommonSegment
    public void fromTrackString(String[] strArr) {
        if (strArr == null) {
            return;
        }
        HashMap hashMap = new HashMap(strArr.length);
        for (String str : strArr) {
            String[] split = str.split("=");
            if (split.length >= 2) {
                hashMap.put(split[0], split[1]);
            }
        }
        this.l = CommonUtil.e(hashMap.get("sn"), -1);
        this.t = CommonUtil.b(hashMap.get("st"), -1L);
        this.j = CommonUtil.b(hashMap.get("sd"), -1L);
        this.o = CommonUtil.e(hashMap.get("sp"), -1);
        this.k = CommonUtil.e(hashMap.get("shr"), -1);
        this.h = CommonUtil.e(hashMap.get("sc"), -1);
        this.s = CommonUtil.e(hashMap.get("ssl"), -1);
        this.r = CommonUtil.b(hashMap.get("str"), -1L);
        this.p = CommonUtil.b(hashMap.get("std"), -1L);
        this.m = CommonUtil.e(hashMap.get("sgct"), -1);
        this.n = CommonUtil.e(hashMap.get("sgia"), -1);
        this.q = CommonUtil.e(hashMap.get("ssa"), -1);
        this.f = CommonUtil.e(hashMap.get("see"), -1);
        this.v = CommonUtil.e(hashMap.get("stp"), -1);
        a(hashMap);
    }

    private void a(Map<String, String> map) {
        if (this.d) {
            this.e = CommonUtil.e(map.get("sht"), -1);
            this.b = CommonUtil.c(map.get("sgtb"), -1.0f);
            this.c = CommonUtil.c(map.get("svo"), -1.0f);
            this.g = CommonUtil.c(map.get("svr"), -1.0f);
            this.i = CommonUtil.c(map.get("spi"), -1.0f);
            this.f14668a = CommonUtil.c(map.get("spir"), -1.0f);
        }
    }
}
