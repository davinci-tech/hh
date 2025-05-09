package com.huawei.openalliance.ad;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.openalliance.ad.beans.metadata.AdEventResult;
import com.huawei.openalliance.ad.beans.metadata.AdEventResultV2;
import com.huawei.openalliance.ad.beans.server.EventReportRsp;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.constant.EventType;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.db.bean.EventRecord;
import com.huawei.openalliance.ad.qi;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

/* loaded from: classes5.dex */
abstract class qc implements qf {

    /* renamed from: a, reason: collision with root package name */
    private Context f7464a;
    private fv b;
    private sl c;
    private fx d;

    private static boolean a(int i) {
        return (200 == i || 601 == i || 611 == i) ? false : true;
    }

    protected abstract Class<? extends EventRecord> a();

    protected abstract void a(long j);

    protected abstract Executor b();

    protected abstract String c();

    protected abstract long d();

    @Override // com.huawei.openalliance.ad.qf
    public void f() {
        a(new Runnable() { // from class: com.huawei.openalliance.ad.qc.5
            @Override // java.lang.Runnable
            public void run() {
                new qi(qc.this.f7464a, qc.this.c).a();
                qc.this.h();
            }
        });
    }

    @Override // com.huawei.openalliance.ad.qf
    public void e() {
        a(new Runnable() { // from class: com.huawei.openalliance.ad.qc.3
            @Override // java.lang.Runnable
            public void run() {
                qc.this.h();
            }
        });
    }

    @Override // com.huawei.openalliance.ad.qf
    public void c(String str, EventRecord eventRecord, boolean z, ContentRecord contentRecord) {
        if (eventRecord != null) {
            if ((z && a(str, contentRecord)) || a(str)) {
                return;
            }
            b(eventRecord, contentRecord);
        }
    }

    @Override // com.huawei.openalliance.ad.qf
    public void b(String str, final EventRecord eventRecord, boolean z, final ContentRecord contentRecord) {
        if (eventRecord != null) {
            if ((z && a(str, contentRecord)) || a(str)) {
                return;
            }
            com.huawei.openalliance.ad.utils.m.a(new Runnable() { // from class: com.huawei.openalliance.ad.qc.4
                @Override // java.lang.Runnable
                public void run() {
                    qc.this.b(eventRecord, contentRecord);
                    if (qc.this.g()) {
                        qc.this.f();
                    }
                }
            });
            if ("imp".equalsIgnoreCase(str)) {
                String a2 = com.huawei.openalliance.ad.utils.cm.a(this.f7464a, contentRecord);
                if ("1".equals(a2) || "2".equals(a2)) {
                    a(eventRecord, contentRecord);
                }
            }
        }
    }

    @Override // com.huawei.openalliance.ad.qf
    public void b(String str, EventRecord eventRecord, ContentRecord contentRecord) {
        b(str, eventRecord, true, contentRecord);
    }

    @Override // com.huawei.openalliance.ad.qf
    public void a(String str, final EventRecord eventRecord, boolean z, final ContentRecord contentRecord) {
        if (eventRecord != null) {
            if ((z && a(str, contentRecord)) || a(str)) {
                return;
            }
            com.huawei.openalliance.ad.utils.m.a(new Runnable() { // from class: com.huawei.openalliance.ad.qc.1
                @Override // java.lang.Runnable
                public void run() {
                    ContentRecord contentRecord2;
                    if ((TextUtils.equals(eventRecord.i(), EventType.SHOW.value()) || TextUtils.equals(eventRecord.i(), EventType.SUPPLEMENTIMP.value())) && (contentRecord2 = contentRecord) != null && contentRecord2.ae() != null) {
                        String packageName = contentRecord.ae().getPackageName();
                        if (!TextUtils.isEmpty(packageName)) {
                            int i = com.huawei.openalliance.ad.utils.i.a(qc.this.f7464a, packageName) ? 10 : 11;
                            eventRecord.l(String.valueOf(i));
                            ho.b(qc.this.c(), "appStatus: " + i);
                        }
                    }
                    qc.this.b(eventRecord, contentRecord);
                    qc.this.a(System.currentTimeMillis());
                    qc.this.f();
                }
            });
            a(eventRecord, contentRecord);
        }
    }

    @Override // com.huawei.openalliance.ad.qf
    public void a(String str, EventRecord eventRecord, ContentRecord contentRecord) {
        a(str, eventRecord, true, contentRecord);
    }

    @Override // com.huawei.openalliance.ad.qf
    public void a(String str, EventRecord eventRecord) {
        if (eventRecord == null || a(str)) {
            return;
        }
        b(eventRecord, null);
    }

    @Override // com.huawei.openalliance.ad.qf
    public void a(final EventRecord eventRecord, final ContentRecord contentRecord) {
        a(new Runnable() { // from class: com.huawei.openalliance.ad.qc.2
            @Override // java.lang.Runnable
            public void run() {
                qi qiVar = new qi(qc.this.f7464a, qc.this.c);
                qiVar.a(qc.b(eventRecord));
                qiVar.a(eventRecord.i(), contentRecord);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        Map<String, EventRecord> a2 = this.b.a(a(), Constants.HISUGGESTION_PKG_NAME.equals(this.f7464a.getPackageName()) ? fh.b(this.f7464a).s() : 50);
        String valueOf = String.valueOf(System.currentTimeMillis());
        EventReportRsp a3 = this.d.a(pf.a(a2.values(), this.f7464a));
        if (a3 == null) {
            return;
        }
        if (a(a3)) {
            a(a2, valueOf, a3);
        } else {
            a(valueOf, a3.errorReason != null ? a3.errorReason : String.valueOf(a3.responseCode), a2.values());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean g() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - d() <= 900000) {
            return false;
        }
        a(currentTimeMillis);
        return true;
    }

    private boolean c(String str) {
        return EventType.SHOW.value().equals(str) || EventType.CLICK.value().equals(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(EventRecord eventRecord, ContentRecord contentRecord) {
        if (eventRecord == null) {
            ho.d(c(), "fail to add event to cache");
            return;
        }
        String c = c();
        StringBuilder sb = new StringBuilder("addEventToCache, event:");
        sb.append(eventRecord.i());
        sb.append(" showId:");
        sb.append(eventRecord.k());
        sb.append(" reqId:");
        sb.append(eventRecord.R());
        sb.append(" contentId:");
        sb.append(contentRecord == null ? null : contentRecord.m());
        sb.append(" requestType:");
        sb.append(eventRecord.P());
        ho.b(c, sb.toString());
        sc.a(this.f7464a);
        a(eventRecord, contentRecord, this.b.a(a(), eventRecord) > 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static List<qi.a> b(EventRecord eventRecord) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new rx(eventRecord));
        arrayList.add(new sa(eventRecord));
        arrayList.add(new sj(eventRecord));
        return arrayList;
    }

    private String b(String str) {
        if (com.huawei.openalliance.ad.utils.cz.b(str)) {
            return "";
        }
        String eventTypeIndex = EventType.getEventTypeIndex(str);
        return com.huawei.openalliance.ad.utils.cz.b(eventTypeIndex) ? str.substring(str.indexOf("_") + 1) : eventTypeIndex;
    }

    private boolean a(Map<String, EventRecord> map, String str, EventReportRsp eventReportRsp) {
        try {
            List<AdEventResult> a2 = eventReportRsp.a();
            List<AdEventResultV2> c = eventReportRsp.c();
            ArrayList<String> arrayList = new ArrayList<>();
            ArrayList<EventRecord> arrayList2 = new ArrayList<>();
            if (eventReportRsp.b() != null && eventReportRsp.b().intValue() == 0) {
                arrayList.addAll(map.keySet());
            } else {
                if (com.huawei.openalliance.ad.utils.bg.a(a2) && com.huawei.openalliance.ad.utils.bg.a(c)) {
                    a(str, "no result", map.values());
                    return false;
                }
                if (com.huawei.openalliance.ad.utils.bg.a(c)) {
                    for (AdEventResult adEventResult : a2) {
                        if (adEventResult != null) {
                            a(map, str, arrayList, arrayList2, adEventResult.a(), adEventResult.b());
                        }
                    }
                } else {
                    for (AdEventResultV2 adEventResultV2 : c) {
                        if (adEventResultV2 != null) {
                            a(map, str, arrayList, arrayList2, adEventResultV2.a(), adEventResultV2.b());
                        }
                    }
                    a(new ArrayList<>(map.keySet()), arrayList, c);
                }
            }
            a(arrayList);
            a(str, (String) null, arrayList2);
            return true;
        } catch (Throwable th) {
            ho.c("BaseEventReporter", "dealEventRsp err, %s", th.getClass().getSimpleName());
            return true;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:5:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0071  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean a(java.lang.String r5, com.huawei.openalliance.ad.db.bean.ContentRecord r6) {
        /*
            r4 = this;
            if (r6 == 0) goto L51
            java.util.List r0 = r6.X()     // Catch: java.lang.Exception -> L26 java.lang.RuntimeException -> L33
            boolean r1 = com.huawei.openalliance.ad.utils.bg.a(r0)     // Catch: java.lang.Exception -> L26 java.lang.RuntimeException -> L33
            if (r1 != 0) goto L51
            java.util.Iterator r0 = r0.iterator()     // Catch: java.lang.Exception -> L26 java.lang.RuntimeException -> L33
        L10:
            boolean r1 = r0.hasNext()     // Catch: java.lang.Exception -> L26 java.lang.RuntimeException -> L33
            if (r1 == 0) goto L51
            java.lang.Object r1 = r0.next()     // Catch: java.lang.Exception -> L26 java.lang.RuntimeException -> L33
            java.lang.String r1 = (java.lang.String) r1     // Catch: java.lang.Exception -> L26 java.lang.RuntimeException -> L33
            if (r5 == 0) goto L10
            boolean r1 = r5.equalsIgnoreCase(r1)     // Catch: java.lang.Exception -> L26 java.lang.RuntimeException -> L33
            if (r1 == 0) goto L10
            r0 = 1
            goto L52
        L26:
            r0 = move-exception
            java.lang.String r1 = r4.c()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "isDiscard, Exception:"
            r2.<init>(r3)
            goto L3f
        L33:
            r0 = move-exception
            java.lang.String r1 = r4.c()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "isDiscard, RuntimeException:"
            r2.<init>(r3)
        L3f:
            java.lang.Class r0 = r0.getClass()
            java.lang.String r0 = r0.getSimpleName()
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            com.huawei.openalliance.ad.ho.d(r1, r0)
        L51:
            r0 = 0
        L52:
            java.lang.String r1 = r4.c()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "isDiscard:"
            r2.<init>(r3)
            r2.append(r0)
            java.lang.String r3 = ", eventType:"
            r2.append(r3)
            r2.append(r5)
            java.lang.String r5 = ", contentId:"
            r2.append(r5)
            if (r6 != 0) goto L71
            r5 = 0
            goto L75
        L71:
            java.lang.String r5 = r6.m()
        L75:
            r2.append(r5)
            java.lang.String r5 = r2.toString()
            com.huawei.openalliance.ad.ho.b(r1, r5)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.openalliance.ad.qc.a(java.lang.String, com.huawei.openalliance.ad.db.bean.ContentRecord):boolean");
    }

    private boolean a(String str) {
        String b = b(str);
        boolean z = false;
        if (com.huawei.openalliance.ad.utils.cz.b(b)) {
            return false;
        }
        try {
            List<String> p = fh.b(this.f7464a).p();
            if (!com.huawei.openalliance.ad.utils.bg.a(p)) {
                Iterator<String> it = p.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    if (b.equals(it.next())) {
                        z = true;
                        break;
                    }
                }
            }
        } catch (Throwable th) {
            ho.d(c(), "isInBlackList, Exception:" + th.getClass().getSimpleName());
        }
        ho.a(c(), "isInBlackList, result: %s, eventType: %s", Boolean.valueOf(z), str);
        return z;
    }

    private static boolean a(EventReportRsp eventReportRsp) {
        return eventReportRsp != null && eventReportRsp.responseCode == 0;
    }

    private void a(Map<String, EventRecord> map, String str, ArrayList<String> arrayList, ArrayList<EventRecord> arrayList2, String str2, int i) {
        EventRecord eventRecord = map.get(str2);
        if (!a(i)) {
            arrayList.add(str2);
            a(eventRecord, true);
        } else if (eventRecord != null) {
            eventRecord.e(eventRecord.v() + 1);
            eventRecord.f(String.valueOf(i));
            eventRecord.e(str);
            arrayList2.add(eventRecord);
        }
    }

    private void a(List<String> list, ArrayList<String> arrayList, List<AdEventResultV2> list2) {
        if (com.huawei.openalliance.ad.utils.bg.a(list)) {
            return;
        }
        if (com.huawei.openalliance.ad.utils.bg.a(list2)) {
            arrayList.addAll(list);
            return;
        }
        ArrayList arrayList2 = new ArrayList(list);
        for (AdEventResultV2 adEventResultV2 : list2) {
            if (adEventResultV2 != null) {
                arrayList2.remove(adEventResultV2.a());
            }
        }
        arrayList.addAll(arrayList2);
    }

    private void a(List<String> list) {
        if (list == null || list.size() <= 0) {
            return;
        }
        this.b.b(a(), list);
    }

    private void a(String str, String str2, Collection<EventRecord> collection) {
        if (com.huawei.openalliance.ad.utils.bg.a(collection)) {
            return;
        }
        for (EventRecord eventRecord : collection) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(eventRecord.h());
            this.b.a(a(), str, str2 == null ? eventRecord.u() : str2, eventRecord.v() + 1, arrayList);
            a(eventRecord, false);
        }
    }

    private void a(Runnable runnable) {
        b().execute(new com.huawei.openalliance.ad.utils.df(runnable));
    }

    private void a(EventRecord eventRecord, boolean z) {
        if (eventRecord == null || !c(eventRecord.i())) {
            return;
        }
        new com.huawei.openalliance.ad.analysis.c(this.f7464a).a(eventRecord.i(), eventRecord.l(), eventRecord.R(), eventRecord.k(), eventRecord.Q(), z);
    }

    private void a(EventRecord eventRecord, ContentRecord contentRecord, boolean z) {
        if (eventRecord == null || !c(eventRecord.i())) {
            return;
        }
        new com.huawei.openalliance.ad.analysis.c(this.f7464a).a(eventRecord.i(), contentRecord, z);
    }

    qc(Context context, sl slVar) {
        this.f7464a = context.getApplicationContext();
        this.b = ex.a(context);
        this.c = slVar;
        this.d = fb.a(context);
    }
}
