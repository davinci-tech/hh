package com.huawei.ads.adsrec;

import android.content.Context;
import com.huawei.ads.adsrec.db.table.AdTraceRecord;
import com.huawei.ads.adsrec.recall.IAdRequestDelegate;
import com.huawei.ads.fund.util.AsyncExec;
import com.huawei.ads.fund.util.ListUtil;
import com.huawei.ads.fund.util.StringUtils;
import com.huawei.openplatform.abl.log.HiAdLog;
import defpackage.ux;
import defpackage.uy;
import defpackage.ve;
import defpackage.vh;
import defpackage.vt;
import defpackage.wk;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class AdRecommendEngine {

    /* renamed from: a, reason: collision with root package name */
    private final e f1670a;
    private Context b;
    private ux c;
    private e0 d;
    private q0 e;

    public JSONObject b(vt vtVar, String str) {
        try {
            HiAdLog.i("AdRecEngine", "recallAds via api %s", vtVar);
            if (vtVar != null && str != null) {
                vtVar.b(1);
                return a(vtVar, this.c.a(vtVar, new vh(vtVar.a(), new JSONObject(str))));
            }
            return null;
        } catch (Throwable th) {
            HiAdLog.w("AdRecEngine", "recall via api error: %s", th.getClass().getSimpleName());
            return null;
        }
    }

    public JSONObject a(vt vtVar, IAdRequestDelegate iAdRequestDelegate) {
        HiAdLog.i("AdRecEngine", "recallAds %s", vtVar);
        if (vtVar == null) {
            return null;
        }
        vtVar.b(0);
        return a(vtVar, this.c.a(vtVar, (vt) iAdRequestDelegate));
    }

    public void d(uy uyVar) {
        AsyncExec.submitSeqIO(new a(uyVar));
    }

    private JSONObject a(vt vtVar, vh vhVar) {
        Map<String, List<String>> map = null;
        if (vhVar == null) {
            return null;
        }
        try {
            if (!vhVar.g()) {
                ve.b(vtVar.h()).a(this.b, vtVar, vhVar);
                map = o0.a(vhVar);
                vhVar = this.e.c(vhVar, vtVar);
                Set<String> c2 = c(vtVar, vhVar);
                this.d.a(vhVar.d(), c2);
                b(vtVar.c(), c2);
            }
            b(vhVar, vtVar, map);
            return vhVar.a();
        } finally {
            d();
        }
    }

    private void b(String str, Set<String> set) {
        AsyncExec.submitSeqIO(new d(set, str));
    }

    private void b(vh vhVar, vt vtVar, Map<String, List<String>> map) {
        if (ListUtil.isEmpty(vtVar.d())) {
            return;
        }
        AsyncExec.submitSeqIO(new c(vhVar, vtVar, map));
    }

    private void d() {
        AsyncExec.submitSeqIO(new b());
    }

    class a implements Runnable {
        final /* synthetic */ uy e;

        @Override // java.lang.Runnable
        public void run() {
            AdRecommendEngine.this.f1670a.e(this.e);
            AdRecommendEngine.this.f1670a.clearExpiredData();
            new n(AdRecommendEngine.this.b).c(false);
        }

        a(uy uyVar) {
            this.e = uyVar;
        }
    }

    class b implements Runnable {
        @Override // java.lang.Runnable
        public void run() {
            AdRecommendEngine.this.d.a();
            AdRecommendEngine.this.f1670a.clearExpiredData();
        }

        b() {
        }
    }

    class c implements Runnable {
        final /* synthetic */ vt b;
        final /* synthetic */ Map c;
        final /* synthetic */ vh d;

        @Override // java.lang.Runnable
        public void run() {
            j.a(AdRecommendEngine.this.b, this.d, this.b, (Map<String, List<String>>) this.c);
            j.a(AdRecommendEngine.this.b, this.d, this.b);
        }

        c(vh vhVar, vt vtVar, Map map) {
            this.d = vhVar;
            this.b = vtVar;
            this.c = map;
        }
    }

    class d implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ Set f1674a;
        final /* synthetic */ String b;

        @Override // java.lang.Runnable
        public void run() {
            if (ListUtil.isEmpty(this.f1674a)) {
                return;
            }
            String a2 = w0.a(this.b);
            if (StringUtils.isBlank(a2)) {
                return;
            }
            ArrayList arrayList = new ArrayList();
            for (AdTraceRecord adTraceRecord : AdRecommendEngine.this.f1670a.query(AdTraceRecord.class, null, null, null, null, null)) {
                if (Objects.equals(a2, adTraceRecord.f()) && this.f1674a.contains(adTraceRecord.c())) {
                    adTraceRecord.b(false);
                    arrayList.add(new wk("AdTraceRecord", null, null, "uniqueId=? and contentId=?", new String[]{a2, adTraceRecord.c()}, adTraceRecord.toRecord()));
                }
            }
            AdRecommendEngine.this.f1670a.insertOrUpdate(arrayList);
        }

        d(Set set, String str) {
            this.f1674a = set;
            this.b = str;
        }
    }

    private Set<String> c(vt vtVar, vh vhVar) {
        if (vhVar == null) {
            return null;
        }
        return vhVar.d(vtVar.o(), vtVar.j());
    }

    public AdRecommendEngine(Context context) {
        this.b = context.getApplicationContext();
        v vVar = new v(context);
        this.d = vVar;
        this.c = new ux(context, vVar);
        this.e = new q0(context);
        this.f1670a = new e(this.b);
    }
}
