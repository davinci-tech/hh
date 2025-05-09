package com.huawei.openalliance.ad;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.openalliance.ad.constant.EventType;
import com.huawei.openalliance.ad.constant.RewardKeys;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.db.bean.EventRecord;

/* loaded from: classes5.dex */
public class bp {

    /* renamed from: a, reason: collision with root package name */
    private Context f6648a;

    public void a(EventRecord eventRecord, ContentRecord contentRecord, boolean z, boolean z2, qq qqVar) {
        if (eventRecord == null || contentRecord == null || qqVar == null) {
            ho.b("EventRecordUitl", "params is null");
            return;
        }
        a(this.f6648a, eventRecord, contentRecord);
        if (ho.a()) {
            ho.a("EventRecordUitl", "reportEvent, eventRecord: %s", com.huawei.openalliance.ad.utils.be.b(eventRecord));
        }
        ho.b("EventRecordUitl", "start report event, eventType: %s", eventRecord.i());
        qqVar.a(eventRecord, z, z2);
    }

    public void a(Bundle bundle, ContentRecord contentRecord, qq qqVar) {
        if (bundle == null || contentRecord == null || qqVar == null) {
            ho.b("EventRecordUitl", "param or ad is null");
            return;
        }
        gk gkVar = new gk(bundle);
        String d = gkVar.d("eventType");
        if (TextUtils.isEmpty(d)) {
            ho.b("EventRecordUitl", "eventType is null");
            return;
        }
        String d2 = gkVar.d(RewardKeys.EVENT_RECORD);
        boolean a2 = gkVar.a("is_report_now", false);
        boolean a3 = gkVar.a("is_check_discard", false);
        EventRecord eventRecord = (EventRecord) com.huawei.openalliance.ad.utils.be.b(d2, EventRecord.class, new Class[0]);
        if (eventRecord == null) {
            ho.b("EventRecordUitl", "eventRecord is null");
        } else {
            eventRecord.b(d);
            a(eventRecord, contentRecord, a2, a3, qqVar);
        }
    }

    private void a(Context context, EventRecord eventRecord, ContentRecord contentRecord) {
        if (contentRecord == null || eventRecord == null) {
            return;
        }
        String i = eventRecord.i();
        eventRecord.d(contentRecord.e());
        eventRecord.a(contentRecord.F());
        eventRecord.d(com.huawei.openalliance.ad.utils.ao.c());
        eventRecord.c(contentRecord.j());
        eventRecord.a(contentRecord.bn());
        eventRecord.i(contentRecord.am());
        eventRecord.x(contentRecord.ag());
        eventRecord.w(contentRecord.m());
        eventRecord.f(contentRecord.aK());
        eventRecord.n(contentRecord.aW());
        eventRecord.h(contentRecord.k());
        String bf = contentRecord.bf();
        if (TextUtils.isEmpty(bf)) {
            bf = context.getPackageName();
        }
        eventRecord.E(bf);
        if (EventType.SHOW.value().equals(i) || EventType.SUPPLEMENTIMP.value().equals(i)) {
            eventRecord.v(contentRecord.ao());
            eventRecord.p(contentRecord.ay());
        }
        if (EventType.VIDEOTIME.value().equals(i)) {
            eventRecord.g(contentRecord.be());
        }
    }

    public bp(Context context) {
        this.f6648a = context;
    }
}
