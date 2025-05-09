package com.huawei.openalliance.ad;

import android.content.Context;
import android.util.Pair;
import com.huawei.openalliance.ad.beans.metadata.AppCollection;
import com.huawei.openalliance.ad.beans.server.AppDataCollectionRsp;
import com.huawei.openalliance.ad.beans.server.DelSyncedAppDataReq;
import com.huawei.openalliance.ad.beans.server.RetAndMsg;
import com.huawei.openalliance.ad.constant.ConfigMapKeys;
import com.huawei.openalliance.ad.constant.RTCMethods;
import com.huawei.openalliance.ad.db.bean.AppDataCollectionRecord;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class on implements qn {

    /* renamed from: a, reason: collision with root package name */
    private final fx f7378a;
    private final Context b;
    private final List<AppDataCollectionRecord> c = new ArrayList();
    private final List<String> d = new ArrayList();
    private final List<String> e = new ArrayList();
    private List<AppDataCollectionRecord> f;
    private List<AppCollection> g;
    private List<AppCollection> h;
    private final int i;

    public RetAndMsg c() {
        String sb;
        RetAndMsg retAndMsg = new RetAndMsg();
        List<AppDataCollectionRecord> a2 = em.a(this.b).a(com.huawei.openalliance.ad.utils.cz.a(fh.b(this.b).b(ConfigMapKeys.MAX_UPLOAD_APP_DATA_SIZE, ""), 50));
        this.f = a2;
        if (com.huawei.openalliance.ad.utils.bg.a(a2)) {
            ho.b("AppDataCollectionProcessor", "report data empty");
            sb = "data empty";
        } else {
            retAndMsg.a(this.f.size());
            ho.a("AppDataCollectionProcessor", "report data");
            AppDataCollectionRsp b = this.f7378a.b(pf.b(this.f, this.b));
            if (b != null && 200 == b.a()) {
                return retAndMsg;
            }
            ho.a("AppDataCollectionProcessor", "report app data collection failed");
            StringBuilder sb2 = new StringBuilder("report failed, code: ");
            sb2.append(b == null ? -2 : b.a());
            sb = sb2.toString();
        }
        retAndMsg.a(sb);
        retAndMsg.a(false);
        return retAndMsg;
    }

    @Override // com.huawei.openalliance.ad.qn
    public void b() {
        com.huawei.openalliance.ad.utils.m.f(new Runnable() { // from class: com.huawei.openalliance.ad.on.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (8 != on.this.i && bz.b(on.this.b)) {
                        if (com.huawei.openalliance.ad.utils.db.a(on.this.b)) {
                            en.a(on.this.b).b();
                            Pair<Integer, String> a2 = com.huawei.openalliance.ad.utils.bz.a(on.this.b, "/dd/sync", "");
                            if (a2 != null && ((Integer) a2.first).intValue() == 200 && !com.huawei.openalliance.ad.utils.cz.b((String) a2.second)) {
                                on.this.a((String) a2.second);
                                if (!com.huawei.openalliance.ad.utils.bg.a(on.this.g) || !com.huawei.openalliance.ad.utils.bg.a(on.this.h)) {
                                    on onVar = on.this;
                                    onVar.a(onVar.g, on.this.h);
                                    on onVar2 = on.this;
                                    onVar2.a(onVar2.a());
                                    on.this.e();
                                    return;
                                }
                                ho.a("AppDataCollectionProcessor", "empty list from kit");
                                on.this.e();
                                return;
                            }
                            ho.a("AppDataCollectionProcessor", "empty resp from kit");
                            on.this.e();
                            return;
                        }
                        ho.a("AppDataCollectionProcessor", "no need to sync");
                        return;
                    }
                    ho.a("AppDataCollectionProcessor", "is TV or not inner device");
                } catch (Exception e) {
                    ho.a(3, "AppDataCollectionProcessor", e);
                }
            }
        });
    }

    public RetAndMsg b(boolean z) {
        RetAndMsg retAndMsg = new RetAndMsg();
        if (!z) {
            retAndMsg.a(false);
            retAndMsg.a("report data error");
            ho.b("AppDataCollectionProcessor", "report app collection data error");
            return retAndMsg;
        }
        if (com.huawei.openalliance.ad.utils.bg.a(this.f)) {
            ho.b("AppDataCollectionProcessor", "no ids need del");
            retAndMsg.a(false);
            retAndMsg.a("upLoadRecords empty");
            return retAndMsg;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<AppDataCollectionRecord> it = this.f.iterator();
        while (it.hasNext()) {
            arrayList.add(String.valueOf(it.next().b()));
        }
        int b = em.a(this.b).b(arrayList);
        retAndMsg.a(b);
        if (b < 0) {
            retAndMsg.a(false);
            retAndMsg.a("del error");
        }
        return retAndMsg;
    }

    public boolean a() {
        long a2 = em.a(this.b).a(this.c);
        ho.a("AppDataCollectionProcessor", "insert appCollectionRecord : %s", Long.valueOf(a2));
        return a2 > 0;
    }

    public void a(boolean z) {
        if (!z) {
            ho.b("AppDataCollectionProcessor", "insert error, no need del sync data");
            return;
        }
        DelSyncedAppDataReq delSyncedAppDataReq = new DelSyncedAppDataReq();
        delSyncedAppDataReq.a(this.b.getPackageName());
        delSyncedAppDataReq.a(this.d);
        delSyncedAppDataReq.b(this.e);
        ho.b("AppDataCollectionProcessor", "rpt del sync data req is : %s", com.huawei.openalliance.ad.utils.be.b(this.b, delSyncedAppDataReq));
        ms.a(this.b).a(RTCMethods.DEL_SYNCED_APP_DATA, com.huawei.openalliance.ad.utils.be.b(delSyncedAppDataReq), null, null);
    }

    public void a(List<AppCollection> list, List<AppCollection> list2) {
        if (!com.huawei.openalliance.ad.utils.bg.a(list)) {
            Collection<AppDataCollectionRecord> a2 = pf.a(list, this.b.getPackageName());
            if (!com.huawei.openalliance.ad.utils.bg.a(a2)) {
                this.c.addAll(a2);
                Iterator<AppCollection> it = list.iterator();
                while (it.hasNext()) {
                    this.d.add(it.next().c());
                }
            }
        }
        if (com.huawei.openalliance.ad.utils.bg.a(list2)) {
            return;
        }
        Collection<AppDataCollectionRecord> a3 = pf.a(list2, this.b.getPackageName());
        if (com.huawei.openalliance.ad.utils.bg.a(a3)) {
            return;
        }
        this.c.addAll(a3);
        Iterator<AppCollection> it2 = list2.iterator();
        while (it2.hasNext()) {
            this.e.add(it2.next().c());
        }
    }

    public void a(long j, long j2, String str, RetAndMsg retAndMsg, RetAndMsg retAndMsg2) {
        com.huawei.openalliance.ad.analysis.b bVar = new com.huawei.openalliance.ad.analysis.b();
        bVar.ak(String.valueOf(j - j2));
        bVar.al(String.valueOf(j2));
        bVar.am(str);
        bVar.an(String.valueOf(retAndMsg.a()));
        bVar.ao(retAndMsg.b());
        bVar.ap(String.valueOf(retAndMsg.c()));
        bVar.aq(String.valueOf(retAndMsg2.a()));
        bVar.ar(retAndMsg2.b());
        bVar.as(String.valueOf(retAndMsg2.c()));
        ho.b("AppDataCollectionProcessor", "cache event beforeCount - afterCount = %s, afterCount = %s", bVar.aM(), bVar.aN());
        new com.huawei.openalliance.ad.analysis.c(this.b).a("AppDataCollectionRecord", bVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        d();
        em a2 = em.a(this.b);
        long c = a2.c();
        if (c == 0) {
            ho.a("AppDataCollectionProcessor", "local data record num is 0, return");
            return;
        }
        RetAndMsg c2 = c();
        RetAndMsg b = b(c2.a());
        long c3 = a2.c();
        Map<String, Long> d = a2.d();
        StringBuilder sb = new StringBuilder();
        if (!com.huawei.openalliance.ad.utils.bl.a(d)) {
            for (Map.Entry<String, Long> entry : d.entrySet()) {
                sb.append(entry.getKey());
                sb.append('=');
                sb.append(entry.getValue());
                sb.append(',');
            }
            com.huawei.openalliance.ad.utils.cz.a(sb, ',');
        }
        String sb2 = sb.toString();
        ho.a("AppDataCollectionProcessor", "report remaining records: %s", sb2);
        a(c, c3, sb2, c2, b);
    }

    private void d() {
        em.a(this.b).b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("appCollections");
            String optString2 = jSONObject.optString("appInstallDatas");
            if (!com.huawei.openalliance.ad.utils.cz.b(optString)) {
                List<AppCollection> list = (List) com.huawei.openalliance.ad.utils.be.a(optString, List.class, AppCollection.class);
                this.g = list;
                ho.b("AppDataCollectionProcessor", "convert collectionRecord size is : %s", Integer.valueOf(list.size()));
            }
            if (com.huawei.openalliance.ad.utils.cz.b(optString2)) {
                return;
            }
            List<AppCollection> list2 = (List) com.huawei.openalliance.ad.utils.be.a(optString2, List.class, AppCollection.class);
            this.h = list2;
            ho.b("AppDataCollectionProcessor", "convert installDataRecords size is : %s", Integer.valueOf(list2.size()));
        } catch (JSONException unused) {
            ho.b("AppDataCollectionProcessor", "parse data error, json format error");
        }
    }

    public on(Context context, int i) {
        this.b = context;
        this.f7378a = fb.a(context);
        this.i = i;
    }
}
