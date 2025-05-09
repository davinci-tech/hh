package com.huawei.openalliance.ad.analysis;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Address;
import android.os.Build;
import android.text.TextUtils;
import android.util.Pair;
import com.huawei.openalliance.ad.beans.metadata.DelayInfo;
import com.huawei.openalliance.ad.beans.metadata.GeoLocation;
import com.huawei.openalliance.ad.bz;
import com.huawei.openalliance.ad.constant.ApiNames;
import com.huawei.openalliance.ad.constant.OsType;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.fh;
import com.huawei.openalliance.ad.gc;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.data.AdLandingPageData;
import com.huawei.openalliance.ad.utils.ag;
import com.huawei.openalliance.ad.utils.ah;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.be;
import com.huawei.openalliance.ad.utils.bg;
import com.huawei.openalliance.ad.utils.bv;
import com.huawei.openalliance.ad.utils.cz;
import com.huawei.openalliance.ad.utils.i;
import com.huawei.openalliance.ad.utils.x;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* loaded from: classes5.dex */
public abstract class e {

    /* renamed from: a, reason: collision with root package name */
    protected Context f6636a;

    protected b e(ContentRecord contentRecord) {
        return a(contentRecord, true);
    }

    protected void b(b bVar) {
        gc b = fh.b(this.f6636a);
        if (!b.C()) {
            ho.b("AnalysisReport", "clctStatData is off");
            return;
        }
        ho.a("AnalysisReport", "clctStatData is on");
        int D = b.D();
        ho.a("AnalysisReport", "StatData interval is %d", Integer.valueOf(D));
        bVar.I(cz.n(x.a(this.f6636a, D)));
        bVar.J(x.b(this.f6636a, D));
        bVar.c(x.c(this.f6636a, D));
        bVar.K(x.d(this.f6636a, D));
        bVar.L(String.valueOf(x.e(this.f6636a, D)));
        bVar.M(String.valueOf(x.f(this.f6636a, D)));
        bVar.O(x.i(this.f6636a, D));
        bVar.P(x.j(this.f6636a, D));
    }

    protected void a(b bVar, Double d, Double d2, int i) {
        Address a2;
        if (!ah.a(this.f6636a) || d == null || d2 == null || bVar == null || (a2 = ah.a(this.f6636a, d, d2)) == null) {
            return;
        }
        GeoLocation geoLocation = new GeoLocation();
        geoLocation.a(d);
        geoLocation.b(d2);
        geoLocation.a(Long.valueOf(System.currentTimeMillis()));
        geoLocation.a(i);
        geoLocation.a(ah.a(a2));
        bVar.ai(be.b(geoLocation));
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0108  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0120  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void a(com.huawei.openalliance.ad.analysis.b r15, com.huawei.openalliance.ad.net.http.Response r16, long r17) {
        /*
            Method dump skipped, instructions count: 333
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.openalliance.ad.analysis.e.a(com.huawei.openalliance.ad.analysis.b, com.huawei.openalliance.ad.net.http.Response, long):void");
    }

    protected void a(b bVar, DelayInfo delayInfo) {
        if (bVar == null || delayInfo == null) {
            return;
        }
        bVar.ak(delayInfo.l());
        bVar.al(delayInfo.k());
        bVar.aq(delayInfo.C());
        bVar.c(delayInfo.a());
        bVar.d(delayInfo.j());
        bVar.e(delayInfo.e());
        bVar.f(delayInfo.b());
        bVar.g(delayInfo.c());
        bVar.h(delayInfo.f());
        bVar.i(delayInfo.d());
        bVar.j(delayInfo.m());
        bVar.k(delayInfo.n());
        bVar.l(delayInfo.o());
        bVar.m(delayInfo.B());
        List<String> g = delayInfo.g();
        if (!bg.a(g)) {
            bVar.o(g.toString());
            bVar.am(String.valueOf(g.size()));
        }
        List<String> h = delayInfo.h();
        if (!bg.a(h)) {
            bVar.p(h.toString());
            bVar.an(String.valueOf(h.size()));
        }
        bVar.ao(String.valueOf(delayInfo.i()));
        bVar.ap(String.valueOf(delayInfo.p()));
        bVar.ar(String.valueOf(delayInfo.t()));
        bVar.as(String.valueOf(delayInfo.r()));
        Integer w = delayInfo.w();
        if (w != null) {
            bVar.at(String.valueOf(w));
        }
        bVar.Y(be.b(delayInfo.u()));
        bVar.a(delayInfo.v());
        bVar.Z(delayInfo.x());
        if (delayInfo.D() > 0) {
            bVar.o(delayInfo.D());
        }
        if (delayInfo.E() > 0) {
            bVar.p(delayInfo.E());
        }
        if (ApiNames.LOAD_AD.equalsIgnoreCase(bVar.C())) {
            String r = bVar.r();
            if (r == null) {
                r = "";
            }
            bVar.r(r + "#AdSign:" + delayInfo.y() + "#SpareContentId:" + delayInfo.z() + "#SpareExist:" + delayInfo.A());
        }
    }

    protected void a(b bVar, BaseAnalysisInfo baseAnalysisInfo) {
        if (bVar == null || baseAnalysisInfo == null) {
            return;
        }
        bVar.aj(baseAnalysisInfo.aK());
        bVar.c(baseAnalysisInfo.aL());
        bVar.ak(baseAnalysisInfo.aM());
        bVar.al(baseAnalysisInfo.aN());
        bVar.am(baseAnalysisInfo.aO());
        bVar.an(baseAnalysisInfo.aP());
        bVar.ao(baseAnalysisInfo.aQ());
        bVar.ap(baseAnalysisInfo.aR());
        bVar.aq(baseAnalysisInfo.aS());
        bVar.ar(baseAnalysisInfo.aT());
        bVar.as(baseAnalysisInfo.aU());
        bVar.at(baseAnalysisInfo.aV());
        bVar.d(baseAnalysisInfo.aW());
        bVar.e(baseAnalysisInfo.aX());
        bVar.f(baseAnalysisInfo.aY());
        bVar.g(baseAnalysisInfo.aZ());
        bVar.h(baseAnalysisInfo.ba());
        bVar.i(baseAnalysisInfo.bb());
        bVar.j(baseAnalysisInfo.bc());
        bVar.k(baseAnalysisInfo.bd());
        bVar.l(baseAnalysisInfo.be());
        bVar.m(baseAnalysisInfo.bf());
        bVar.n(baseAnalysisInfo.bg());
        bVar.o(baseAnalysisInfo.bh());
        bVar.p(baseAnalysisInfo.bi());
        bVar.q(baseAnalysisInfo.bj());
        bVar.r(baseAnalysisInfo.bk());
        ho.b("AnalysisReport", "baseToAnalysisInfo completed");
    }

    protected void a(b bVar) {
        String str;
        gc b = fh.b(this.f6636a);
        if (b.B()) {
            int D = b.D();
            ho.a("AnalysisReport", "DyncData interval is %d", Integer.valueOf(D));
            bVar.N(String.valueOf(x.g(this.f6636a, D)));
            bVar.R(x.k(this.f6636a, D));
            bVar.S(x.l(this.f6636a, D));
            bVar.T(x.m(this.f6636a, D));
            bVar.U(x.n(this.f6636a, D));
            bVar.d(x.o(this.f6636a, D));
            bVar.e(x.p(this.f6636a, D));
            bVar.a(x.q(this.f6636a, D));
            bVar.b(x.r(this.f6636a, D));
            bVar.c(x.s(this.f6636a, D));
            bVar.d(x.t(this.f6636a, D));
            str = "clctStatData is off" + x.s(this.f6636a, D);
        } else {
            str = "clctDyncData is off";
        }
        ho.b("AnalysisReport", str);
    }

    protected b a(boolean z, int i) {
        b a2 = a(z);
        if (a2 != null) {
            a2.d(i);
        }
        return a2;
    }

    protected b a(boolean z) {
        b a2 = a();
        if (a2 != null && z) {
            a(this.f6636a, a2);
        }
        b(this.f6636a, a2);
        return a2;
    }

    protected b a(String str, boolean z) {
        b a2 = a(str);
        if (a2 != null && z) {
            a(this.f6636a, a2);
        }
        b(this.f6636a, a2);
        return a2;
    }

    protected b a(String str) {
        StringBuilder sb;
        Pair pair;
        PackageInfo a2;
        try {
            PackageManager packageManager = this.f6636a.getPackageManager();
            String e = bz.a(this.f6636a).e();
            if (packageManager == null) {
                return null;
            }
            b bVar = new b();
            bVar.a(ao.a("yyyy-MM-dd HH:mm:ss.SSSZ").format(new Date()));
            bVar.b("3.4.74.310");
            if (TextUtils.isEmpty(str)) {
                str = this.f6636a.getPackageName();
            }
            bVar.k(str);
            if (i.a(this.f6636a, str) && (a2 = i.a(packageManager, str, 16384)) != null) {
                bVar.j(a2.versionName);
                bVar.i(i.c(this.f6636a, str));
            }
            bVar.c(OsType.ANDROID);
            bVar.g(com.huawei.openalliance.ad.utils.d.a());
            bVar.d(Build.VERSION.RELEASE);
            bVar.h(bz.a(this.f6636a).c());
            bVar.e(Build.MANUFACTURER.toUpperCase(Locale.ENGLISH));
            if (TextUtils.isEmpty(e)) {
                e = bz.a(this.f6636a).i();
            }
            bVar.E(e);
            bVar.ah(x.a());
            String b = bz.a(this.f6636a).b();
            if (b != null) {
                b = b.toUpperCase(Locale.ENGLISH);
            }
            bVar.f(b);
            bVar.l(String.valueOf(bv.d(this.f6636a)));
            Pair<Integer, Pair<String, String>> f = bv.f(this.f6636a);
            if (f != null && (pair = (Pair) f.second) != null) {
                bVar.m((String) pair.first);
                bVar.n((String) pair.second);
            }
            return bVar;
        } catch (RuntimeException e2) {
            e = e2;
            sb = new StringBuilder("createAnalysisInfo RuntimeException:");
            sb.append(e.getClass().getSimpleName());
            ho.c("AnalysisReport", sb.toString());
            return null;
        } catch (Exception e3) {
            e = e3;
            sb = new StringBuilder("createAnalysisInfo Exception:");
            sb.append(e.getClass().getSimpleName());
            ho.c("AnalysisReport", sb.toString());
            return null;
        }
    }

    protected b a(ContentRecord contentRecord, boolean z) {
        return a(a(z), contentRecord);
    }

    protected b a(b bVar, AdLandingPageData adLandingPageData) {
        if (adLandingPageData != null && bVar != null) {
            bVar.o(adLandingPageData.getSlotId());
            bVar.p(adLandingPageData.getContentId());
            bVar.a(adLandingPageData.getAdType());
            bVar.d(adLandingPageData.t());
        }
        return bVar;
    }

    protected b a(b bVar, ContentRecord contentRecord) {
        if (contentRecord != null && bVar != null) {
            bVar.o(contentRecord.l());
            bVar.p(contentRecord.m());
            bVar.a(contentRecord.e());
            bVar.H(contentRecord.n());
            bVar.a(Integer.valueOf(contentRecord.D()));
            bVar.b(Integer.valueOf(contentRecord.i()));
            bVar.s(contentRecord.ag());
            bVar.d(contentRecord.am());
        }
        return bVar;
    }

    protected b a(int i) {
        b a2 = a();
        if (a2 != null) {
            a2.d(i);
        }
        return a2;
    }

    protected b a() {
        return a("");
    }

    protected static void b(Context context, b bVar) {
        ag.a a2;
        if (bVar == null || !ag.b(context) || (a2 = ag.a(context)) == null) {
            return;
        }
        bVar.W(a2.a());
        bVar.X(a2.b() ? "0" : "1");
    }

    private long b(String str) {
        return Math.max(cz.a(str, 0L), 0L);
    }

    protected static void a(Context context, b bVar) {
        Pair<String, Boolean> b;
        if (bVar == null || (b = com.huawei.openalliance.ad.utils.d.b(context, true)) == null) {
            return;
        }
        bVar.C(((Boolean) b.second).booleanValue() ? "0" : "1");
        bVar.x((String) b.first);
    }

    public e(Context context) {
        this.f6636a = context.getApplicationContext();
    }
}
