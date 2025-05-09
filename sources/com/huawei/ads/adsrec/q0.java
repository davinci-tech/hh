package com.huawei.ads.adsrec;

import android.content.Context;
import com.huawei.ads.adsrec.bean.RelationScore;
import com.huawei.ads.adsrec.db.table.AdCreativeContentRecord;
import com.huawei.ads.adsrec.db.table.AdTraceRecord;
import com.huawei.ads.adsrec.db.table.DsContentRelRecord;
import com.huawei.ads.adsrec.db.table.MaterialSummaryRecord;
import com.huawei.ads.fund.util.AsyncExec;
import com.huawei.ads.fund.util.JsonUtil;
import com.huawei.ads.fund.util.ListUtil;
import com.huawei.ads.fund.util.StringUtils;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.constant.TagConstants;
import com.huawei.openplatform.abl.log.HiAdLog;
import defpackage.uw;
import defpackage.vb;
import defpackage.vg;
import defpackage.vh;
import defpackage.vj;
import defpackage.vk;
import defpackage.vp;
import defpackage.vs;
import defpackage.vt;
import defpackage.vw;
import defpackage.wb;
import defpackage.wc;
import defpackage.wk;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class q0 {

    /* renamed from: a, reason: collision with root package name */
    protected Map<String, String> f1681a;
    private final Context b;
    private IUtilCallback c;
    private wb d;
    private boolean e = false;

    public vh c(vh vhVar, vt vtVar) {
        if (vhVar == null) {
            return null;
        }
        IDsRelationCallback a2 = uw.a();
        if (a2 == null) {
            return vhVar;
        }
        this.e = a2.isSupportRelateRank();
        try {
            List<vg> j = vhVar.j();
            if (ListUtil.isEmpty(j)) {
                return vhVar;
            }
            List<vg> c = c(j);
            IUtilCallback iUtilCallback = this.c;
            if (iUtilCallback == null || StringUtils.parseIntOrDefault(iUtilCallback.getConfig("joinDeviceRank"), 0) != 1) {
                e(c, vtVar, a2);
            } else {
                a(c, vtVar, a2);
            }
            return vhVar;
        } catch (Throwable th) {
            HiAdLog.w("RankTask", "rank error: %s", th.getClass().getSimpleName());
            return vhVar;
        }
    }

    private void a(List<vg> list, vt vtVar, IDsRelationCallback iDsRelationCallback) {
        long currentTimeMillis = System.currentTimeMillis();
        Map<String, RelationScore> d2 = d(list, vtVar, iDsRelationCallback);
        long currentTimeMillis2 = System.currentTimeMillis();
        if (!vp.a(d2)) {
            d2.size();
        }
        c(list, d2);
        StringBuilder sb = new StringBuilder();
        b(list, vtVar, sb);
        AsyncExec.submitSeqIO(new d(a(list), vtVar, currentTimeMillis2, currentTimeMillis, sb, System.currentTimeMillis(), d2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(List<vg> list, vt vtVar) {
        if (ListUtil.isEmpty(list)) {
            return;
        }
        if (StringUtils.isBlank(vtVar.c())) {
            HiAdLog.e("RankTask", "requestId is blank");
            return;
        }
        e eVar = new e(this.b);
        d(eVar, list, vtVar);
        e(eVar, list, vtVar);
    }

    private void e(e eVar, List<vg> list, vt vtVar) {
        HiAdLog.i("RankTask", "recordDsContentRelRecord start!");
        ArrayList arrayList = new ArrayList();
        Iterator<vg> it = list.iterator();
        while (it.hasNext()) {
            for (vb vbVar : it.next().a()) {
                try {
                    if (vbVar.y().booleanValue()) {
                        String f = vbVar.f();
                        DsContentRelRecord dsContentRelRecord = new DsContentRelRecord();
                        dsContentRelRecord.d(f);
                        dsContentRelRecord.c(vtVar.c());
                        dsContentRelRecord.d(System.currentTimeMillis());
                        IUtilCallback d2 = uw.d();
                        if (d2 != null) {
                            dsContentRelRecord.e(d2.aes128Encrypt(String.valueOf(vbVar.o())));
                            dsContentRelRecord.j(String.valueOf(d2.getMediaType(vtVar.f())));
                            dsContentRelRecord.b(StringUtils.listToString(d2.getDailyIntentId(0, 5), ","));
                            dsContentRelRecord.a(StringUtils.listToString(d2.getDailyIntentId(1, 5), ","));
                        }
                        dsContentRelRecord.d(vbVar.h());
                        dsContentRelRecord.a(vbVar.a());
                        dsContentRelRecord.b(vbVar.j());
                        dsContentRelRecord.f(vbVar.n());
                        try {
                            dsContentRelRecord.a(Integer.parseInt(vbVar.t()));
                        } catch (Throwable th) {
                            HiAdLog.w("RankTask", "interactionType error: %s", th.getClass().getSimpleName());
                        }
                        dsContentRelRecord.i(vtVar.f());
                        dsContentRelRecord.d(vtVar.h());
                        dsContentRelRecord.g(vbVar.m());
                        dsContentRelRecord.h(vbVar.k());
                        dsContentRelRecord.o(vtVar.i());
                        dsContentRelRecord.n(vtVar.g());
                        dsContentRelRecord.f();
                        dsContentRelRecord.h();
                        vbVar.o();
                        dsContentRelRecord.n();
                        dsContentRelRecord.i();
                        dsContentRelRecord.g();
                        dsContentRelRecord.e();
                        dsContentRelRecord.b();
                        dsContentRelRecord.d();
                        dsContentRelRecord.j();
                        dsContentRelRecord.k();
                        dsContentRelRecord.o();
                        dsContentRelRecord.l();
                        dsContentRelRecord.m();
                        dsContentRelRecord.r();
                        dsContentRelRecord.s();
                        vbVar.p();
                        vtVar.a();
                        arrayList.add(new wk("DsContentRelRecord", null, null, "clientRequestId=? and contentId=?", new String[]{vtVar.c(), f}, dsContentRelRecord.toRecord()));
                    }
                } catch (Exception e) {
                    HiAdLog.e("RankTask", "sv at record error");
                    HiAdLog.printSafeStackTrace(3, e);
                }
            }
        }
        eVar.insertOrUpdate(arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Map<String, RelationScore> d(List<vg> list, vt vtVar, IDsRelationCallback iDsRelationCallback) {
        HashMap hashMap = new HashMap();
        if (!this.e) {
            HiAdLog.i("RankTask", "ds version is too low,not support relate rank.");
            return hashMap;
        }
        System.currentTimeMillis();
        JSONObject a2 = a(list, vtVar);
        System.currentTimeMillis();
        if (a2 == null) {
            return hashMap;
        }
        List<RelationScore> relationScore = iDsRelationCallback.getRelationScore(a2);
        if (!ListUtil.isEmpty(relationScore)) {
            relationScore.size();
            for (RelationScore relationScore2 : relationScore) {
                if (relationScore2 != null) {
                    hashMap.put(relationScore2.c(), relationScore2);
                }
            }
        }
        return hashMap;
    }

    private List<vg> c(List<vg> list) {
        ArrayList arrayList = new ArrayList();
        for (vg vgVar : list) {
            if (!ListUtil.isEmpty(vgVar.a())) {
                int a2 = wc.d(this.b).a(vgVar.h());
                HiAdLog.i("RankTask", "oldRankCfg: %s", Integer.valueOf(a2));
                if (a2 == 1) {
                    arrayList.add(vgVar);
                }
            }
        }
        return arrayList;
    }

    private boolean c(vb vbVar, String str) {
        if (vbVar.y() != null) {
            return vbVar.y().booleanValue();
        }
        vbVar.a(false);
        AdCreativeContentRecord d2 = vbVar.d();
        if (d2 == null || !vk.b(d2.h(), str, this.f1681a)) {
            return false;
        }
        vbVar.a(true);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(Map<String, RelationScore> map, vt vtVar) {
        try {
            ArrayList arrayList = new ArrayList();
            if (!vp.a(map)) {
                ArrayList arrayList2 = new ArrayList();
                Iterator<RelationScore> it = map.values().iterator();
                while (it.hasNext()) {
                    arrayList2.add(new RelationScore(it.next()));
                }
                Collections.sort(arrayList2, new b());
                Iterator it2 = arrayList2.iterator();
                while (it2.hasNext()) {
                    arrayList.add(((RelationScore) it2.next()).c());
                }
            }
            j.a(vtVar, arrayList, this.d);
        } catch (Exception e) {
            HiAdLog.printSafeStackTrace(3, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(List<vg> list, Map<String, RelationScore> map) {
        for (vg vgVar : list) {
            for (vb vbVar : vgVar.a()) {
                if (vbVar != null) {
                    c(vbVar, vgVar.b());
                    vbVar.d(o0.a(vbVar));
                    o0.a(vbVar, map);
                    vbVar.f();
                    vbVar.o();
                    vbVar.y();
                }
            }
        }
    }

    private void b(List<vg> list, vt vtVar, StringBuilder sb) {
        boolean z = true;
        for (vg vgVar : list) {
            if (vgVar != null) {
                String h = vgVar.h();
                int e = wc.d(this.b).e(h);
                HiAdLog.i("RankTask", "rank rankStrategy: %s", Integer.valueOf(e));
                if (!z) {
                    sb.append(";");
                }
                sb.append(h);
                sb.append(",");
                sb.append(e);
                long currentTimeMillis = System.currentTimeMillis();
                List<vb> a2 = vgVar.a();
                if (ListUtil.isEmpty(a2)) {
                    return;
                }
                sb.append(",");
                sb.append(a2.size());
                if (e != 0) {
                    new vw(vgVar.b(), a2, vtVar).a(e);
                }
                long currentTimeMillis2 = System.currentTimeMillis();
                sb.append(",");
                sb.append(currentTimeMillis2 - currentTimeMillis);
                if (HiAdLog.isDebugEnable()) {
                    Iterator<vb> it = a2.iterator();
                    while (it.hasNext()) {
                        it.next().f();
                    }
                }
                z = false;
            }
        }
    }

    private void e(List<vg> list, vt vtVar, IDsRelationCallback iDsRelationCallback) {
        if (ListUtil.isEmpty(list)) {
            return;
        }
        AsyncExec.submitSeqIO(new a(a(list), vtVar, iDsRelationCallback));
    }

    private void d(e eVar, List<vg> list, vt vtVar) {
        Iterator<vg> it;
        if (this.e && vs.e()) {
            String a2 = w0.a(vtVar.c());
            ArrayList arrayList = new ArrayList();
            Iterator<vg> it2 = list.iterator();
            while (it2.hasNext()) {
                vg next = it2.next();
                List<vb> a3 = next.a();
                String h = next.h();
                for (vb vbVar : a3) {
                    try {
                    } catch (Exception e) {
                        e = e;
                        it = it2;
                    }
                    if (vbVar.y().booleanValue()) {
                        String f = vbVar.f();
                        it = it2;
                        try {
                            if (!ListUtil.isEmpty(eVar.query(AdTraceRecord.class, null, "uniqueId=? and contentId=?", new String[]{a2, f}, null, "1"))) {
                                HiAdLog.w("RankTask", "records exists, continue");
                            } else {
                                AdTraceRecord adTraceRecord = new AdTraceRecord();
                                adTraceRecord.h(a2);
                                adTraceRecord.b(f);
                                adTraceRecord.a(System.currentTimeMillis());
                                try {
                                    adTraceRecord.a(d(eVar, f, vtVar, h));
                                    adTraceRecord.b(true);
                                    adTraceRecord.d(vbVar.d().h());
                                    arrayList.add(new wk("AdTraceRecord", null, null, "uniqueId=? and contentId=?", new String[]{a2, f}, adTraceRecord.toRecord()));
                                } catch (Exception e2) {
                                    e = e2;
                                    HiAdLog.e("RankTask", "sv at record error");
                                    HiAdLog.printSafeStackTrace(3, e);
                                    it2 = it;
                                }
                            }
                        } catch (Exception e3) {
                            e = e3;
                        }
                        it2 = it;
                    } else {
                        HiAdLog.w("RankTask", "%s is not rank supported!", vbVar.f());
                    }
                }
            }
            eVar.insertOrUpdate(arrayList);
        }
    }

    private JSONObject a(List<vg> list, vt vtVar) {
        if (ListUtil.isEmpty(list)) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        for (vg vgVar : list) {
            for (vb vbVar : vgVar.a()) {
                if (c(vbVar, vgVar.b())) {
                    AdCreativeContentRecord d2 = vbVar.d();
                    if (d2 == null) {
                        vbVar.f();
                    } else {
                        JSONObject jSONObject = new JSONObject();
                        vj.a(jSONObject, "contentId", vbVar.f());
                        vj.a(jSONObject, TagConstants.TAG_LIST_KEY, StringUtils.unicodeDecode(d2.b()));
                        vj.a(jSONObject, "targets", d2.a());
                        vj.a(jSONObject, "interactionType", vbVar.t());
                        vj.a(jSONObject, "adTitle", vbVar.g());
                        vj.a(jSONObject, "adDescription", vbVar.c());
                        vj.a(jSONObject, "adBrandInfo", vbVar.b());
                        vj.a(jSONObject, "adType", vgVar.b());
                        vj.a(jSONObject, "dspId", vbVar.n());
                        vj.a(jSONObject, "mediaPackageName", vbVar.r());
                        vj.a(jSONObject, "slotId", vgVar.h());
                        vj.a(jSONObject, Constants.RELATION_COL_A_KEY, null);
                        vj.a(jSONObject, "priceRange", null);
                        jSONArray.put(jSONObject);
                    }
                }
            }
        }
        if (jSONArray.length() == 0) {
            return null;
        }
        JSONObject jSONObject2 = new JSONObject();
        vj.b(jSONObject2, "services", jSONArray);
        vj.b(jSONObject2, "traceId", w0.a(vtVar.c()));
        jSONObject2.toString();
        return jSONObject2;
    }

    private List<vg> a(List<vg> list) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<vg> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().d());
        }
        return arrayList;
    }

    private String d(e eVar, String str, vt vtVar, String str2) {
        List query = eVar.query(MaterialSummaryRecord.class, null, "contentId=? and pkgName=? and slotId=?", new String[]{str, vtVar.a(), str2}, null, "1");
        if (ListUtil.isEmpty(query) || query.get(0) == null) {
            return "";
        }
        ((MaterialSummaryRecord) query.get(0)).f();
        return ((MaterialSummaryRecord) query.get(0)).f();
    }

    class a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ List f1682a;
        final /* synthetic */ IDsRelationCallback b;
        final /* synthetic */ vt e;

        @Override // java.lang.Runnable
        public void run() {
            HiAdLog.i("RankTask", "joinDeviceRank :0, adslots.size:%s", Integer.valueOf(this.f1682a.size()));
            long currentTimeMillis = System.currentTimeMillis();
            Map d = q0.this.d((List<vg>) this.f1682a, this.e, this.b);
            long currentTimeMillis2 = System.currentTimeMillis();
            q0.this.c((List<vg>) this.f1682a, (Map<String, RelationScore>) d);
            q0.this.d((List<vg>) this.f1682a, this.e);
            q0.this.d = new wb();
            long j = currentTimeMillis2 - currentTimeMillis;
            q0.this.d.b(j);
            q0.this.d.b("");
            q0.this.d.e(j);
            q0.this.d((Map<String, RelationScore>) d, this.e);
        }

        a(List list, vt vtVar, IDsRelationCallback iDsRelationCallback) {
            this.f1682a = list;
            this.e = vtVar;
            this.b = iDsRelationCallback;
        }
    }

    class b implements Comparator<RelationScore> {
        @Override // java.util.Comparator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public int compare(RelationScore relationScore, RelationScore relationScore2) {
            double d = relationScore.d();
            double d2 = relationScore2.d();
            if (Math.abs(d - d2) < 9.99999993922529E-9d) {
                return 0;
            }
            return d < d2 ? 1 : -1;
        }

        b() {
        }
    }

    class d implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ List f1683a;
        final /* synthetic */ long b;
        final /* synthetic */ vt c;
        final /* synthetic */ StringBuilder d;
        final /* synthetic */ long e;
        final /* synthetic */ Map g;
        final /* synthetic */ long i;

        @Override // java.lang.Runnable
        public void run() {
            q0.this.d((List<vg>) this.f1683a, this.c);
            q0.this.d = new wb(this.f1683a);
            q0.this.d.b(this.e - this.b);
            q0.this.d.b(this.d.toString());
            q0.this.d.e(this.i - this.b);
            q0.this.d((Map<String, RelationScore>) this.g, this.c);
        }

        d(List list, vt vtVar, long j, long j2, StringBuilder sb, long j3, Map map) {
            this.f1683a = list;
            this.c = vtVar;
            this.e = j;
            this.b = j2;
            this.d = sb;
            this.i = j3;
            this.g = map;
        }
    }

    public q0(Context context) {
        this.b = context.getApplicationContext();
        IUtilCallback d2 = uw.d();
        this.c = d2;
        if (d2 != null) {
            String config = d2.getConfig("tradeModeSupportRank");
            if (StringUtils.isBlank(config)) {
                return;
            }
            this.f1681a = JsonUtil.jsonToMap(config);
        }
    }
}
