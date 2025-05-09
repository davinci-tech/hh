package com.huawei.openalliance.ad.inter;

import android.content.Context;
import com.huawei.openalliance.ad.constant.ClickDestination;
import com.huawei.openalliance.ad.constant.EventType;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.data.AdEventRecord;
import com.huawei.openalliance.ad.inter.data.AdEventType;
import com.huawei.openalliance.ad.ou;
import com.huawei.openalliance.ad.pd;
import com.huawei.openalliance.ad.processor.eventbeans.a;
import com.huawei.openalliance.ad.processor.eventbeans.b;
import com.huawei.openalliance.ad.sc;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.bg;
import com.huawei.openalliance.ad.utils.cm;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes9.dex */
public class HiAdEventReporter {

    /* renamed from: a, reason: collision with root package name */
    private static HiAdEventReporter f6989a;
    private static final byte[] b = new byte[0];
    private Context c;

    public void reportEvent(List<AdEventRecord> list) {
        List<AdEventRecord> a2 = a(list);
        ho.b("HiAdEventReporter", "batch report events size:%s", Integer.valueOf(a2.size()));
        if (bg.a(a2)) {
            return;
        }
        b(a2);
        int i = 0;
        while (i < a2.size()) {
            AdEventRecord adEventRecord = a2.get(i);
            com.huawei.openalliance.ad.inter.data.e eVar = (com.huawei.openalliance.ad.inter.data.e) adEventRecord.a();
            ContentRecord a3 = pd.a(eVar);
            if (a3 != null) {
                String d = a2.get(i).d();
                boolean z = i >= a2.size() - 1;
                Context context = this.c;
                ou ouVar = new ou(context, sc.a(context, eVar.e()), a3);
                if ("imp".equalsIgnoreCase(d)) {
                    a(a3, z, ouVar);
                    a(eVar, z, ouVar, adEventRecord.c() - adEventRecord.b());
                } else if (AdEventType.SHOW_START.equalsIgnoreCase(d)) {
                    a(a3, z, ouVar);
                } else if ("click".equalsIgnoreCase(d)) {
                    b.a aVar = new b.a();
                    aVar.b(ClickDestination.ADCONTENTINTERFACE).a((Integer) 12).b(z);
                    ouVar.a(aVar.a());
                } else if (AdEventType.INTENTSUCCESS.equalsIgnoreCase(d)) {
                    ouVar.a(EventType.INTENTSUCCESS, (Integer) 1, (Integer) null, z);
                }
            }
            i++;
        }
    }

    public static HiAdEventReporter getInstance(Context context) {
        return a(context);
    }

    private void b(List<AdEventRecord> list) {
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        for (int i = 0; i < list.size(); i++) {
            String slotId = ((com.huawei.openalliance.ad.inter.data.e) list.get(i).a()).getSlotId();
            if (cm.a(this.c, slotId)) {
                hashSet2.add(slotId);
            } else {
                hashSet.add(slotId);
            }
        }
        if (!bg.a(hashSet2)) {
            Iterator it = hashSet2.iterator();
            while (it.hasNext()) {
                HiAd.a(this.c).b((String) it.next());
            }
        }
        if (bg.a(hashSet)) {
            return;
        }
        Iterator it2 = hashSet.iterator();
        while (it2.hasNext()) {
            HiAd.a(this.c).c((String) it2.next());
        }
    }

    private void a(com.huawei.openalliance.ad.inter.data.e eVar, boolean z, ou ouVar, long j) {
        a.C0207a c0207a = new a.C0207a();
        c0207a.a(Long.valueOf(j)).a(Integer.valueOf(eVar.getMinEffectiveShowRatio())).b(z).b((Integer) 7);
        ouVar.a(c0207a.a());
    }

    private void a(ContentRecord contentRecord, boolean z, ou ouVar) {
        if (contentRecord == null) {
            return;
        }
        contentRecord.c(String.valueOf(ao.c()));
        ouVar.a(contentRecord);
        ouVar.a(z);
    }

    private List<AdEventRecord> a(List<AdEventRecord> list) {
        com.huawei.openalliance.ad.inter.data.e eVar;
        ArrayList arrayList = new ArrayList();
        if (bg.a(list)) {
            return arrayList;
        }
        for (int i = 0; i < list.size(); i++) {
            AdEventRecord adEventRecord = list.get(i);
            if (adEventRecord != null && (adEventRecord.a() instanceof com.huawei.openalliance.ad.inter.data.e) && (eVar = (com.huawei.openalliance.ad.inter.data.e) adEventRecord.a()) != null && eVar.isAdIdInWhiteList()) {
                arrayList.add(adEventRecord);
            }
        }
        return arrayList;
    }

    private static HiAdEventReporter a(Context context) {
        HiAdEventReporter hiAdEventReporter;
        synchronized (b) {
            if (f6989a == null) {
                f6989a = new HiAdEventReporter(context);
            }
            hiAdEventReporter = f6989a;
        }
        return hiAdEventReporter;
    }

    private HiAdEventReporter(Context context) {
        this.c = context.getApplicationContext();
    }
}
