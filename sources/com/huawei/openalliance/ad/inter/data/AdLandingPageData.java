package com.huawei.openalliance.ad.inter.data;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.openalliance.ad.beans.metadata.ApkInfo;
import com.huawei.openalliance.ad.beans.metadata.MetaData;
import com.huawei.openalliance.ad.beans.metadata.Monitor;
import com.huawei.openalliance.ad.beans.metadata.TextState;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.db.bean.EncryptionField;
import com.huawei.openalliance.ad.utils.cp;
import com.huawei.openalliance.ad.utils.dc;
import java.io.Serializable;
import java.util.List;
import java.util.concurrent.Callable;

/* loaded from: classes5.dex */
public class AdLandingPageData implements Serializable {
    private int A;
    private int B;
    private String C;
    private String D;
    private String E;
    private String F;
    private String G;
    private String H;
    private List<Integer> I;
    private String K;
    private String L;
    private String M;
    private long N;
    private boolean O;
    private String P;
    private int Q;
    private int R;
    private String S;
    private String T;
    private int U;
    private String V;
    private String W;

    /* renamed from: a, reason: collision with root package name */
    private EncryptionField<String> f7035a;
    private String b;
    private int c;
    private String d;
    private String e;
    private ShareInfo f;
    private AppInfo g;
    private EncryptionField<List<Monitor>> h;
    private boolean i;
    private byte[] j;
    private String k;
    private String l;
    private List<TextState> m;
    private List<String> n;
    private String o;
    private String p;
    private String q;
    private String r;
    private EncryptionField<String> s;
    private String w;
    private String x;
    private String y;
    private boolean t = false;
    private boolean u = false;
    private boolean v = true;
    private int z = 0;
    private boolean J = true;
    private long X = 500;
    private int Y = 50;

    public boolean l() {
        return true;
    }

    public boolean z() {
        return this.J;
    }

    public void z(String str) {
        this.W = str;
    }

    public void y(String str) {
        this.P = str;
    }

    public List<Integer> y() {
        return this.I;
    }

    public void x(String str) {
        this.M = str;
    }

    public ContentRecord x() {
        ContentRecord contentRecord = new ContentRecord();
        contentRecord.d(getSlotId());
        contentRecord.f(i());
        contentRecord.L(I());
        contentRecord.t(j());
        contentRecord.e(getContentId());
        contentRecord.b(getAdType());
        contentRecord.a(a());
        contentRecord.k(getLandingUrl());
        contentRecord.c(c());
        contentRecord.b(d());
        contentRecord.s(f());
        contentRecord.g(h());
        contentRecord.c(k());
        contentRecord.a(l());
        contentRecord.x(n());
        contentRecord.y(o());
        contentRecord.b(s());
        contentRecord.o(t());
        contentRecord.e(y());
        contentRecord.G(A());
        contentRecord.H(B());
        contentRecord.J(C());
        contentRecord.f(D());
        contentRecord.d(E());
        contentRecord.m(F());
        contentRecord.t(G());
        contentRecord.s(H());
        contentRecord.Q(b());
        contentRecord.i(q());
        contentRecord.e(v());
        contentRecord.S(u());
        contentRecord.N(w());
        if (getAppInfo() != null && !TextUtils.isEmpty(getAppInfo().getUniqueId())) {
            contentRecord.v(getAppInfo().getUniqueId());
        }
        return contentRecord;
    }

    public void w(String str) {
        this.L = str;
    }

    public String w() {
        return this.V;
    }

    public boolean v() {
        return this.U == 1;
    }

    public void v(String str) {
        this.K = str;
    }

    public void u(String str) {
        this.V = str;
    }

    public String u() {
        return this.T;
    }

    public void t(String str) {
        this.T = str;
    }

    public int t() {
        return this.z;
    }

    public void s(String str) {
        this.H = str;
    }

    public String s() {
        return this.H;
    }

    public void r(String str) {
        this.F = str;
    }

    public int r() {
        return this.B;
    }

    public void q(String str) {
        this.G = str;
    }

    public int q() {
        return this.A;
    }

    public void p(String str) {
        this.E = str;
    }

    public String p() {
        return this.x;
    }

    public void o(String str) {
        this.D = str;
    }

    public String o() {
        return this.y;
    }

    public void n(String str) {
        this.C = str;
    }

    public String n() {
        return this.w;
    }

    public boolean m() {
        return this.v;
    }

    public void m(String str) {
        this.x = str;
    }

    public void l(String str) {
        this.y = str;
    }

    public void k(String str) {
        this.w = str;
    }

    public EncryptionField<String> k() {
        return this.s;
    }

    public void j(String str) {
        this.r = str;
    }

    public String j() {
        return this.q;
    }

    public boolean isShowPageTitle() {
        return this.i;
    }

    public void i(String str) {
        this.q = str;
    }

    public String i() {
        return this.p;
    }

    public void h(String str) {
        this.p = str;
    }

    public List<String> h() {
        return this.n;
    }

    public String getUniqueId() {
        return this.r;
    }

    public String getSlotId() {
        return this.o;
    }

    public ShareInfo getShareInfo() {
        return this.f;
    }

    public String getLandingUrl() {
        return this.b;
    }

    public String getContentId() {
        return this.e;
    }

    public AppInfo getAppInfo() {
        return this.g;
    }

    public int getAdType() {
        return this.c;
    }

    public void g(String str) {
        this.o = str;
    }

    public List<TextState> g() {
        return this.m;
    }

    public void f(boolean z) {
        this.O = z;
    }

    public void f(String str) {
        this.e = str;
    }

    public String f() {
        return this.l;
    }

    public void e(boolean z) {
        this.J = z;
    }

    public void e(String str) {
        this.l = str;
    }

    public String e() {
        return this.k;
    }

    public void d(boolean z) {
        this.U = z ? 1 : 0;
    }

    public void d(String str) {
        this.k = str;
    }

    public void d(int i) {
        this.R = i;
    }

    public EncryptionField<List<Monitor>> d() {
        return this.h;
    }

    public void c(boolean z) {
        this.u = z;
    }

    public void c(List<Integer> list) {
        this.I = list;
    }

    public void c(String str) {
        this.d = str;
    }

    public void c(EncryptionField<String> encryptionField) {
        this.s = encryptionField;
    }

    public void c(int i) {
        this.Q = i;
    }

    public String c() {
        return this.d;
    }

    public void b(boolean z) {
        this.t = z;
    }

    public void b(List<String> list) {
        this.n = list;
    }

    public void b(String str) {
        this.b = str;
    }

    public void b(EncryptionField<List<Monitor>> encryptionField) {
        this.h = encryptionField;
    }

    public void b(int i) {
        this.z = i;
    }

    public String b() {
        return this.S;
    }

    public void a(boolean z) {
        this.i = z;
    }

    public void a(List<TextState> list) {
        this.m = list;
    }

    public void a(String str) {
        this.S = str;
    }

    public void a(ShareInfo shareInfo) {
        this.f = shareInfo;
    }

    public void a(AppInfo appInfo) {
        this.g = appInfo;
    }

    public void a(EncryptionField<String> encryptionField) {
        this.f7035a = encryptionField;
    }

    public void a(long j) {
        this.N = j;
    }

    public void a(int i) {
        this.c = i;
    }

    public EncryptionField<String> a() {
        return this.f7035a;
    }

    public String I() {
        return this.W;
    }

    public int H() {
        return this.R;
    }

    public int G() {
        return this.Q;
    }

    public String F() {
        return this.P;
    }

    public boolean E() {
        return this.O;
    }

    public long D() {
        return this.N;
    }

    public String C() {
        return this.M;
    }

    public String B() {
        return this.L;
    }

    public String A() {
        return this.K;
    }

    static class a implements Callable<byte[]> {

        /* renamed from: a, reason: collision with root package name */
        private final Context f7036a;

        @Override // java.util.concurrent.Callable
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public byte[] call() {
            return cp.b(this.f7036a);
        }

        a(Context context) {
            this.f7036a = context.getApplicationContext();
        }
    }

    private EncryptionField a(EncryptionField encryptionField, Context context) {
        if (encryptionField == null) {
            return null;
        }
        if (!encryptionField.c() && encryptionField.b()) {
            if (this.j == null) {
                this.j = (byte[]) dc.b(new a(context));
            }
            encryptionField.a(encryptionField.b(this.j));
        }
        encryptionField.a((EncryptionField) null);
        return encryptionField;
    }

    public AdLandingPageData(ContentRecord contentRecord, Context context, boolean z) {
        ApkInfo r;
        f(contentRecord.m());
        a(contentRecord.e());
        a((EncryptionField<String>) a(contentRecord.F(), context));
        b(contentRecord.A());
        c(contentRecord.j());
        b(contentRecord.X());
        this.A = contentRecord.B();
        this.B = contentRecord.D();
        MetaData h = contentRecord.h();
        if (h != null) {
            com.huawei.openalliance.ad.beans.metadata.ShareInfo q = h.q();
            if (q != null) {
                a(new ShareInfo(q));
            }
            if (z && (r = h.r()) != null) {
                AppInfo appInfo = new AppInfo(r);
                appInfo.k(h.n());
                appInfo.s(contentRecord.aa());
                appInfo.C(contentRecord.aq());
                appInfo.D(contentRecord.ar());
                appInfo.h(h.B());
                this.g = appInfo;
            }
            a(h.v());
            m(h.B());
            n(h.l());
            o(h.t());
            p(h.m());
            r(h.n());
            q(h.z());
        }
        b((EncryptionField<List<Monitor>>) a(contentRecord.K(), context));
        a(contentRecord.O() == 1);
        d(contentRecord.U());
        e(contentRecord.V());
        g(contentRecord.l());
        h(contentRecord.n());
        i(contentRecord.Y());
        j(contentRecord.aa());
        c((EncryptionField<String>) a(contentRecord.ab(), context));
        b(contentRecord.ac());
        k(contentRecord.ad());
        l(contentRecord.ag());
        b(contentRecord.am());
        s(contentRecord.g());
        c(contentRecord.M());
        v(contentRecord.aA());
        w(contentRecord.aB());
        x(contentRecord.aG());
        f(contentRecord.aM());
        y(contentRecord.G());
        c(contentRecord.aW());
        d(contentRecord.aO());
        z(contentRecord.aN());
        a(contentRecord.aY());
        d(contentRecord.bc());
        t(contentRecord.bb());
        u(contentRecord.aQ());
    }

    public AdLandingPageData() {
    }
}
