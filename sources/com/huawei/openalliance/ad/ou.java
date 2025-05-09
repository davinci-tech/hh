package com.huawei.openalliance.ad;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.openalliance.ad.beans.inner.AdEventReport;
import com.huawei.openalliance.ad.beans.inner.DownloadBlockInfo;
import com.huawei.openalliance.ad.beans.metadata.AdEventResult;
import com.huawei.openalliance.ad.beans.metadata.AdEventResultV2;
import com.huawei.openalliance.ad.beans.metadata.ApkInfo;
import com.huawei.openalliance.ad.beans.metadata.PostBackEvent;
import com.huawei.openalliance.ad.beans.server.EventReportRsp;
import com.huawei.openalliance.ad.constant.AnalyticsEventType;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.constant.EventType;
import com.huawei.openalliance.ad.constant.InterstitialMethods;
import com.huawei.openalliance.ad.constant.MapKeyNames;
import com.huawei.openalliance.ad.constant.RTCMethods;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.db.bean.EventRecord;
import com.huawei.openalliance.ad.inter.data.FeedbackInfo;
import com.huawei.openalliance.ad.inter.data.MaterialClickInfo;
import com.huawei.openalliance.ad.processor.eventbeans.a;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class ou implements qq {

    /* renamed from: a, reason: collision with root package name */
    private sl f7405a;
    private ContentRecord b;
    private gc c;
    private Context d;
    private jc e;
    private fx f;
    private ga g;
    private int h;

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b(int i) {
        return i == 2 || i == 1;
    }

    private boolean c(int i) {
        return i == 0 || i == 1 || i == 3 || i == 6;
    }

    @Override // com.huawei.openalliance.ad.qq
    public void u() {
        c(EventType.VASTPLAYCOMPLETE);
    }

    @Override // com.huawei.openalliance.ad.qq
    public void t() {
        c(EventType.VASTPLAYSTART);
    }

    @Override // com.huawei.openalliance.ad.qq
    public void s() {
        c(EventType.VASTTHIRDQUART);
    }

    @Override // com.huawei.openalliance.ad.qq
    public void r() {
        c(EventType.VASTMIDPOINT);
    }

    @Override // com.huawei.openalliance.ad.qq
    public void q() {
        c(EventType.VASTFIRSTQURAT);
    }

    @Override // com.huawei.openalliance.ad.qq
    public void p() {
        final EventRecord b = b(EventType.SERVE);
        if (b(b, EventType.SERVE)) {
            return;
        }
        final ContentRecord contentRecord = this.b;
        com.huawei.openalliance.ad.utils.m.h(new Runnable() { // from class: com.huawei.openalliance.ad.ou.15
            @Override // java.lang.Runnable
            public void run() {
                if (com.huawei.openalliance.ad.utils.bg.a(qi.a(b.i(), contentRecord, ou.this.d))) {
                    ho.a("EventProcessor", "serve monitor is empty, cancel report ad serve event.");
                    return;
                }
                int j = os.j(ou.this.b.V());
                ou ouVar = ou.this;
                String a2 = ouVar.a(ouVar.b, b.i(), j);
                ho.b("EventProcessor", "onAdServe key: " + a2);
                if (ou.this.e.a(ou.this.b.e(), a2)) {
                    ho.b("EventProcessor", "onAdServe second + time serve: don't report event");
                } else {
                    ho.b("EventProcessor", "onAdServe first time serve: report  event");
                    qe.a(ou.this.d, ou.this.f7405a, EventType.SERVE).a(EventType.SERVE.value(), b, true, ou.this.b);
                }
            }
        });
    }

    @Override // com.huawei.openalliance.ad.qq
    public void o() {
        EventRecord b = b(EventType.ADLOADED);
        if (b(b, EventType.ADLOADED)) {
            return;
        }
        b.c((String) null);
        qe.a(this.d, this.f7405a, EventType.ADLOADED).b(EventType.ADLOADED.value(), b, this.b);
    }

    @Override // com.huawei.openalliance.ad.qq
    public void n() {
        EventRecord b = b(EventType.RESPONSE);
        if (b(b, EventType.RESPONSE)) {
            return;
        }
        b.c((String) null);
        b(b);
        a(b);
        qe.a(this.d, this.f7405a, EventType.RESPONSE).b(EventType.RESPONSE.value(), b, this.b);
    }

    @Override // com.huawei.openalliance.ad.qq
    public void m() {
        qe.a(this.d, this.f7405a, EventType.SHOWEND).b(EventType.SHOWEND.value(), b(EventType.SHOWEND), this.b);
    }

    @Override // com.huawei.openalliance.ad.qq
    public void l() {
        qe.a(this.d, this.f7405a, EventType.FAVORITE).b(EventType.FAVORITE.value(), b(EventType.FAVORITE), this.b);
    }

    @Override // com.huawei.openalliance.ad.qq
    public void k() {
        qe.a(this.d, this.f7405a, EventType.SHARE).b(EventType.SHARE.value(), b(EventType.SHARE), this.b);
    }

    @Override // com.huawei.openalliance.ad.qq
    public void j() {
        qe.a(this.d, this.f7405a, EventType.REMOVE).b(EventType.REMOVE.value(), b(EventType.REMOVE), this.b);
    }

    @Override // com.huawei.openalliance.ad.qq
    public void i() {
        qe.a(this.d, this.f7405a, EventType.SWIPEUP).b(EventType.SWIPEUP.value(), b(EventType.SWIPEUP), this.b);
    }

    @Override // com.huawei.openalliance.ad.qq
    public void h() {
        qe.a(this.d, this.f7405a, EventType.WEBLOADFINISH).b(EventType.WEBLOADFINISH.value(), b(EventType.WEBLOADFINISH), this.b);
    }

    @Override // com.huawei.openalliance.ad.qq
    public void g() {
        qe.a(this.d, this.f7405a, EventType.WEBOPEN).b(EventType.WEBOPEN.value(), b(EventType.WEBOPEN), this.b);
    }

    @Override // com.huawei.openalliance.ad.qq
    public void f() {
        a(EventType.VIDEOPLAYRESUME, -111111L, -111111L, -111111, -111111, (String) null);
    }

    @Override // com.huawei.openalliance.ad.qq
    public void e() {
        a(EventType.REPLAY, -111111L, -111111L, -111111, -111111, (String) null);
    }

    @Override // com.huawei.openalliance.ad.qq
    public void d(long j, long j2, int i, int i2) {
        a(EventType.EASTEREGGEND, j, j2, i, i2, (String) null);
    }

    @Override // com.huawei.openalliance.ad.qq
    public void d() {
        a(EventType.PLAYBTNSTART, -111111L, -111111L, -111111, -111111, (String) null);
    }

    @Override // com.huawei.openalliance.ad.qq
    public void c(Integer num, Integer num2, String str, String str2, String str3) {
        a(EventType.APPINSTALLFAIL, num, num2, true, true, str, str2, str3);
    }

    @Override // com.huawei.openalliance.ad.qq
    public void c(com.huawei.openalliance.ad.processor.eventbeans.a aVar) {
        a(EventType.INTERACTSHOW, aVar, (ot) null);
    }

    @Override // com.huawei.openalliance.ad.qq
    public void c(long j, long j2, int i, int i2) {
        a(EventType.VIDEOPLAYEND, j, j2, i, i2, (String) null);
    }

    @Override // com.huawei.openalliance.ad.qq
    public void c() {
        a(EventType.VIDEOPLAYSTART, -111111L, -111111L, -111111, -111111, (String) null);
    }

    @Override // com.huawei.openalliance.ad.qq
    public void b(boolean z) {
        EventType eventType = z ? EventType.SOUNDCLICKOFF : EventType.SOUNDCLICKON;
        EventRecord b = b(eventType);
        if (b(b, eventType)) {
            return;
        }
        qf a2 = qe.a(this.d, this.f7405a, eventType);
        a2.b(eventType.value(), b, this.b);
        a2.a(b, this.b);
    }

    @Override // com.huawei.openalliance.ad.qq
    public void b(List<FeedbackInfo> list) {
        EventRecord b = b(EventType.CLOSE);
        if (b(b, EventType.CLOSE)) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        if (!com.huawei.openalliance.ad.utils.bg.a(list)) {
            for (FeedbackInfo feedbackInfo : list) {
                if (feedbackInfo != null && (1 == feedbackInfo.getType() || 3 == feedbackInfo.getType())) {
                    arrayList.add(feedbackInfo.getLabel());
                    arrayList2.add(String.valueOf(feedbackInfo.a()));
                }
            }
        }
        if (!com.huawei.openalliance.ad.utils.bg.a(arrayList)) {
            ho.a("EventProcessor", "onAdClose, selectedKeyWords: %s", arrayList.toString());
        }
        if (!com.huawei.openalliance.ad.utils.bg.a(arrayList2)) {
            ho.a("EventProcessor", "onAdClose, selectedKeyWordsType: %s", arrayList2.toString());
        }
        b.e(0);
        b.f(0);
        b.a(arrayList);
        b.b(arrayList2);
        qe.a(this.d, this.f7405a, EventType.CLOSE).a(EventType.CLOSE.value(), b, false, this.b);
        this.f7405a.d();
        c(b);
    }

    @Override // com.huawei.openalliance.ad.qq
    public void b(Integer num, String str, DownloadBlockInfo downloadBlockInfo, int i, String str2, String str3, String str4) {
        a(EventType.APPDOWNLOAD, num, str, true, false, null, downloadBlockInfo, Integer.valueOf(i), str2, str3, str4);
    }

    @Override // com.huawei.openalliance.ad.qq
    public void b(Integer num, String str, int i, String str2, String str3, String str4) {
        a(EventType.APPDOWNLOADRESUME, num, str, false, true, null, null, Integer.valueOf(i), str2, str3, str4);
    }

    @Override // com.huawei.openalliance.ad.qq
    public void b(Integer num, String str, int i, DownloadBlockInfo downloadBlockInfo, int i2, String str2, String str3, String str4) {
        a(EventType.APPDOWNLOADFAIL, num, str, false, true, Integer.valueOf(i), downloadBlockInfo, Integer.valueOf(i2), str2, str3, str4);
    }

    @Override // com.huawei.openalliance.ad.qq
    public void b(Integer num, Integer num2, String str, String str2, String str3) {
        a(EventType.APPINSTALLSTART, num, num2, false, true, str, str2, str3);
    }

    @Override // com.huawei.openalliance.ad.qq
    public void b(com.huawei.openalliance.ad.processor.eventbeans.a aVar) {
        a(EventType.EASTEREGGSHOW, aVar, (ot) null);
    }

    @Override // com.huawei.openalliance.ad.qq
    public void b(final com.huawei.openalliance.ad.analysis.b bVar, final boolean z, final boolean z2) {
        final ContentRecord contentRecord = this.b;
        com.huawei.openalliance.ad.utils.m.a(new Runnable() { // from class: com.huawei.openalliance.ad.ou.14
            @Override // java.lang.Runnable
            public void run() {
                try {
                    EventRecord a2 = ou.this.a(EventType.ANALYSIS, bVar);
                    if (ou.b(a2, EventType.ANALYSIS)) {
                        return;
                    }
                    qf a3 = qe.a(ou.this.d, ou.this.f7405a, EventType.ANALYSIS);
                    String str = a2.i() + "_" + bVar.aK();
                    if (z) {
                        a3.a(str, a2, z2, contentRecord);
                    } else {
                        a3.b(str, a2, z2, contentRecord);
                    }
                } catch (Throwable th) {
                    ho.d("EventProcessor", "onAnalysis.addEventToCache exception");
                    ho.a(5, th);
                }
            }
        });
    }

    @Override // com.huawei.openalliance.ad.qq
    public void b(long j, long j2, int i, int i2) {
        a(EventType.VIDEOPLAYPAUSE, j, j2, i, i2, (String) null);
    }

    @Override // com.huawei.openalliance.ad.qq
    public void b(int i, int i2) {
        EventType eventType = EventType.SKIP;
        EventRecord b = b(eventType);
        if (b(b, eventType)) {
            return;
        }
        b.e(i);
        b.f(i2);
        ContentRecord contentRecord = this.b;
        if (contentRecord == null || !contentRecord.ap()) {
            qe.a(this.d, this.f7405a, eventType).a(eventType.value(), b, false, this.b);
            this.f7405a.d();
        } else {
            ho.a("EventProcessor", "report exsplash close event");
            AdEventReport c = c(this.b);
            c.b(i);
            c.c(i2);
            ms.a(this.d).a(MapKeyNames.REPORT_CLOSE_EVENT, com.huawei.openalliance.ad.utils.be.b(c), null, null);
        }
        c(b);
    }

    @Override // com.huawei.openalliance.ad.qq
    public void b() {
        a(true);
    }

    public void a(boolean z) {
        if (x()) {
            return;
        }
        EventRecord b = b(EventType.SHOWSTART);
        if (b(b, EventType.SHOWSTART)) {
            return;
        }
        qf a2 = qe.a(this.d, this.f7405a, EventType.SHOWSTART);
        if (z) {
            a2.a(EventType.SHOWSTART.value(), b, false, this.b);
        } else {
            a2.b(EventType.SHOWSTART.value(), b, false, this.b);
        }
        w();
    }

    @Override // com.huawei.openalliance.ad.qq
    public void a(List<FeedbackInfo> list) {
        EventRecord b = b(EventType.POSITIVEFEEDBACK);
        if (b(b, EventType.POSITIVEFEEDBACK)) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        if (!com.huawei.openalliance.ad.utils.bg.a(list)) {
            for (FeedbackInfo feedbackInfo : list) {
                if (feedbackInfo != null && feedbackInfo.getType() == 2) {
                    arrayList.add(feedbackInfo.getLabel());
                    arrayList2.add(String.valueOf(feedbackInfo.a()));
                }
            }
        }
        if (!com.huawei.openalliance.ad.utils.bg.a(arrayList)) {
            ho.a("EventProcessor", "onAdPositiveFeedback, selectedKeyWords: %s", arrayList.toString());
        }
        if (!com.huawei.openalliance.ad.utils.bg.a(arrayList2)) {
            ho.a("EventProcessor", "onAdPositiveFeedback, selectedKeyWordsType: %s", arrayList2.toString());
        }
        b.e(0);
        b.f(0);
        b.a(arrayList);
        b.b(arrayList2);
        qe.a(this.d, this.f7405a, EventType.POSITIVEFEEDBACK).a(EventType.POSITIVEFEEDBACK.value(), b, false, this.b);
    }

    public void a(String str, final List<com.huawei.openalliance.ad.analysis.b> list, final qy qyVar) {
        com.huawei.openalliance.ad.utils.m.b(new Runnable() { // from class: com.huawei.openalliance.ad.ou.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    ArrayList arrayList = new ArrayList();
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        EventRecord a2 = ou.this.a(EventType.ANALYSIS, (com.huawei.openalliance.ad.analysis.b) it.next());
                        if (ou.b(a2, EventType.ANALYSIS)) {
                            return;
                        } else {
                            arrayList.add(a2);
                        }
                    }
                    EventReportRsp a3 = ou.this.f.a(pf.a(arrayList, ou.this.d));
                    if (ou.c(a3) && ou.this.b(a3)) {
                        qyVar.a();
                    }
                } catch (Throwable th) {
                    ho.d("EventProcessor", "onRealTimeAnalysis exception");
                    ho.a(5, th);
                }
            }
        });
    }

    public void a(String str, String str2, boolean z) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            ho.c("EventProcessor", "param is null");
            return;
        }
        EventRecord d = d(str);
        if (d == null) {
            return;
        }
        d.d(str2);
        qf a2 = qe.a(this.d, this.f7405a, str);
        if (z) {
            a2.a(str, d, this.b);
        } else {
            a2.b(str, d, this.b);
        }
    }

    @Override // com.huawei.openalliance.ad.qq
    public void a(String str, String str2, String str3) {
        EventRecord b = b(EventType.ADREWARDED);
        if (b(b, EventType.ADREWARDED)) {
            return;
        }
        b.t(str);
        b.u(str2);
        qe.a(this.d, this.f7405a, EventType.ADREWARDED).a(EventType.ADREWARDED.value(), b, false, this.b);
        e(str3);
    }

    public void a(String str) {
        if (x()) {
            return;
        }
        EventRecord b = b(EventType.SHOWSTART);
        if (b(b, EventType.SHOWSTART)) {
            return;
        }
        b.t(str);
        qe.a(this.d, this.f7405a, EventType.SHOWSTART).a(EventType.SHOWSTART.value(), b, false, this.b);
        w();
    }

    @Override // com.huawei.openalliance.ad.qq
    public void a(Integer num, String str, DownloadBlockInfo downloadBlockInfo, int i, String str2, String str3, String str4) {
        a(EventType.APPDOWNLOADCANCEL, num, str, false, true, null, downloadBlockInfo, Integer.valueOf(i), str2, str3, str4);
    }

    @Override // com.huawei.openalliance.ad.qq
    public void a(Integer num, String str, int i, String str2, String str3, String str4) {
        a(EventType.APPDOWNLOADSTART, num, str, true, true, null, null, Integer.valueOf(i), str2, str3, str4);
    }

    @Override // com.huawei.openalliance.ad.qq
    public void a(Integer num, String str, int i, DownloadBlockInfo downloadBlockInfo, int i2, String str2, String str3, String str4) {
        a(EventType.APPDOWNLOADPAUSE, num, str, false, true, Integer.valueOf(i), downloadBlockInfo, Integer.valueOf(i2), str2, str3, str4);
    }

    @Override // com.huawei.openalliance.ad.qq
    public void a(Integer num, Integer num2, String str, String str2, String str3) {
        a(EventType.APPINSTALL, num, num2, true, false, str, str2, str3);
    }

    @Override // com.huawei.openalliance.ad.qq
    public void a(Integer num) {
        a(EventType.APPOPEN, num, true, true, (Integer) null, (DownloadBlockInfo) null);
    }

    @Override // com.huawei.openalliance.ad.qq
    public void a(com.huawei.openalliance.ad.processor.eventbeans.b bVar) {
        if (x()) {
            return;
        }
        ho.a("EventProcessor", "onAdClick()");
        if (bVar == null) {
            return;
        }
        if (h(bVar)) {
            i(bVar);
        } else {
            f(bVar);
        }
    }

    @Override // com.huawei.openalliance.ad.qq
    public void a(com.huawei.openalliance.ad.processor.eventbeans.a aVar) {
        if (x()) {
            return;
        }
        a(d(aVar) ? EventType.SUPPLEMENTIMP : EventType.SHOW, aVar, (ot) null);
    }

    @Override // com.huawei.openalliance.ad.qq
    public void a(final EventRecord eventRecord, final boolean z, final boolean z2) {
        com.huawei.openalliance.ad.utils.m.a(new Runnable() { // from class: com.huawei.openalliance.ad.ou.7
            @Override // java.lang.Runnable
            public void run() {
                try {
                    EventRecord eventRecord2 = eventRecord;
                    if (eventRecord2 != null) {
                        String i = eventRecord2.i();
                        if (ou.this.A()) {
                            qf a2 = qe.a(ou.this.d, ou.this.f7405a, i);
                            if (z) {
                                a2.a(i, eventRecord, z2, ou.this.b);
                                return;
                            } else {
                                a2.b(i, eventRecord, z2, ou.this.b);
                                return;
                            }
                        }
                        ho.b("EventProcessor", "enableUserInfo is closed");
                        return;
                    }
                    ho.b("EventProcessor", "eventRecord is null");
                } catch (Throwable unused) {
                    ho.d("EventProcessor", "onCommonReport exception");
                }
            }
        });
    }

    @Override // com.huawei.openalliance.ad.qq
    public void a(ContentRecord contentRecord) {
        if (contentRecord != null) {
            this.b = contentRecord;
        }
    }

    @Override // com.huawei.openalliance.ad.qq
    public void a(EventType eventType, Integer num, Integer num2, boolean z) {
        if (eventType == null) {
            return;
        }
        EventRecord b = b(eventType);
        if (b(b, eventType)) {
            return;
        }
        if (num != null) {
            b.h(num.toString());
        }
        if (num2 != null) {
            b.i(num2.toString());
        }
        qf a2 = qe.a(this.d, this.f7405a, eventType);
        if (EventType.INTENTSUCCESS != eventType) {
            a2.b(eventType.value(), b, this.b);
            return;
        }
        a(EventType.INTENTSUCCESS, b);
        String value = eventType.value();
        if (z) {
            a2.a(value, b, false, this.b);
        } else {
            a2.b(value, b, false, this.b);
        }
    }

    @Override // com.huawei.openalliance.ad.qq
    public void a(EventType eventType, Integer num, Integer num2) {
        a(eventType, num, num2, true);
    }

    public void a(final com.huawei.openalliance.ad.analysis.b bVar, final boolean z, final boolean z2) {
        final ContentRecord contentRecord = this.b;
        com.huawei.openalliance.ad.utils.m.a(new Runnable() { // from class: com.huawei.openalliance.ad.ou.13
            @Override // java.lang.Runnable
            public void run() {
                try {
                    EventRecord a2 = ou.this.a(EventType.ANALYSIS, bVar);
                    if (ou.b(a2, EventType.ANALYSIS)) {
                        return;
                    }
                    qf a3 = qe.a(ou.this.d, ou.this.f7405a, EventType.ANALYSIS);
                    a3.c(a2.i() + "_" + bVar.aK(), a2, z2, contentRecord);
                    if (z) {
                        a3.e();
                    }
                } catch (Throwable th) {
                    ho.d("EventProcessor", "onThirdPartException onAnalysis.addEventToCache exception");
                    ho.a(5, th);
                }
            }
        });
    }

    public void a(final com.huawei.openalliance.ad.analysis.b bVar) {
        com.huawei.openalliance.ad.utils.m.a(new Runnable() { // from class: com.huawei.openalliance.ad.ou.6
            @Override // java.lang.Runnable
            public void run() {
                try {
                    EventRecord a2 = ou.this.a(EventType.ANALYSIS, bVar);
                    if (ou.b(a2, EventType.ANALYSIS)) {
                        return;
                    }
                    qe.a(ou.this.d, ou.this.f7405a, EventType.ANALYSIS).a(a2.i() + "_" + bVar.aK(), a2);
                } catch (Throwable th) {
                    ho.d("EventProcessor", "onAnalysis.onAppDataCollectionAnalysis exception");
                    ho.a(5, th);
                }
            }
        });
    }

    @Override // com.huawei.openalliance.ad.qq
    public void a(Context context, ContentRecord contentRecord) {
        a(EventType.LINKEDCONTINUEPLAY, -111111L, -111111L, -111111, -111111, (String) null);
    }

    @Override // com.huawei.openalliance.ad.qq
    public void a(long j, long j2, int i, int i2, String str) {
        a(EventType.INTERACTEND, j, j2, i, i2, str);
    }

    @Override // com.huawei.openalliance.ad.qq
    public void a(long j, long j2, int i, int i2) {
        a(EventType.PLAYBTNPAUSE, j, j2, i, i2, (String) null);
    }

    @Override // com.huawei.openalliance.ad.qq
    public void a(long j, int i, ot otVar) {
        if (x()) {
            return;
        }
        a.C0207a c0207a = new a.C0207a();
        c0207a.a(Long.valueOf(j)).a(Integer.valueOf(i));
        a(EventType.PHYIMP, c0207a.a(), otVar);
    }

    @Override // com.huawei.openalliance.ad.qq
    public void a(long j, int i) {
        if (x()) {
            return;
        }
        a.C0207a c0207a = new a.C0207a();
        c0207a.a(Long.valueOf(j)).a(Integer.valueOf(i));
        a(EventType.PHYIMP, c0207a.a(), (ot) null);
    }

    @Override // com.huawei.openalliance.ad.qq
    public void a(long j) {
        ContentRecord contentRecord = this.b;
        if (contentRecord != null) {
            contentRecord.h(j);
        }
        d(EventType.VIDEOTIME);
        ho.b("EventProcessor", " reportVideoPlayTime, event： %s , videoTime %s", EventType.VIDEOTIME.value(), Long.valueOf(j));
    }

    public void a(int i, String str) {
        EventRecord b = b(EventType.ADPRECHECK);
        if (b(b, EventType.ADPRECHECK)) {
            return;
        }
        if (ho.a()) {
            ho.a("EventProcessor", "onPreCheckResult result: %d contentid: %s", Integer.valueOf(i), this.b.m());
        }
        if (!TextUtils.isEmpty(str)) {
            b.n(str);
        }
        b.l(String.valueOf(i));
        qe.a(this.d, this.f7405a, EventType.ADPRECHECK).a(EventType.ADPRECHECK.value(), b, this.b);
    }

    @Override // com.huawei.openalliance.ad.qq
    public void a(int i, long j) {
        EventRecord b = b(EventType.WEBCLOSE);
        if (b(b, EventType.WEBCLOSE)) {
            return;
        }
        b.g(i);
        b.a(j);
        qe.a(this.d, this.f7405a, EventType.WEBCLOSE).a(EventType.WEBCLOSE.value(), b, this.b);
    }

    @Override // com.huawei.openalliance.ad.qq
    public void a(int i, int i2, List<String> list) {
        a(i, i2, list, EventType.CLOSE);
    }

    @Override // com.huawei.openalliance.ad.qq
    public void a(int i, int i2) {
        a(i, i2, (List<String>) null, EventType.EASTEREGGCLOSE);
    }

    public void a(int i) {
        this.h = i;
    }

    @Override // com.huawei.openalliance.ad.qq
    public ContentRecord a() {
        return this.b;
    }

    private int z() {
        int a2 = this.g.a();
        int b = this.g.b();
        if (a2 < 0 || b <= 0 || a2 >= b) {
            b = 2000;
            a2 = 0;
        }
        int a3 = com.huawei.openalliance.ad.utils.ci.a(b, a2);
        ho.b("EventProcessor", "clk millis: %s", Integer.valueOf(a3));
        return a3;
    }

    private boolean y() {
        ContentRecord contentRecord = this.b;
        return contentRecord != null && (3 == contentRecord.aO() || 99 == this.b.D());
    }

    private boolean x() {
        ContentRecord contentRecord = this.b;
        if (contentRecord == null) {
            return false;
        }
        boolean z = jn.a(contentRecord.m()) instanceof jo;
        ho.a("EventProcessor", "sdkMonitor is %s, cr sdkMonitor is %s, isDoNothing is %s", Integer.valueOf(this.h), Integer.valueOf(this.b.a()), Boolean.valueOf(z));
        return (this.h == this.b.a() || !jn.a(this.b.a()) || z) ? false : true;
    }

    private void w() {
        pl plVar = new pl(this.d);
        plVar.a(this.b);
        plVar.a();
    }

    private boolean v() {
        ContentRecord contentRecord = this.b;
        return com.huawei.openalliance.ad.utils.c.a(contentRecord == null ? null : contentRecord.aZ());
    }

    private void i(final com.huawei.openalliance.ad.processor.eventbeans.b bVar) {
        com.huawei.openalliance.ad.utils.bo.a(new Runnable() { // from class: com.huawei.openalliance.ad.ou.10
            @Override // java.lang.Runnable
            public void run() {
                ou.this.f(bVar);
            }
        }, z());
    }

    private boolean h(com.huawei.openalliance.ad.processor.eventbeans.b bVar) {
        if (v() && bVar != null && bVar.c()) {
            Integer g = bVar.g();
            if (g == null) {
                return true;
            }
            if (g.intValue() != 12 && g.intValue() != 13) {
                ho.a("EventProcessor", "is need delay");
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean g(String str) {
        return bz.b(this.d) && 1 == fg.a(this.d).b(str);
    }

    private void g(com.huawei.openalliance.ad.processor.eventbeans.b bVar) {
        Integer d = bVar.h().d();
        if (d == null) {
            return;
        }
        ho.a("EventProcessor", "adaptOldVersionSld orgSld is %s", d);
        if (d.intValue() == 3 && c(bVar) && !com.huawei.openalliance.ad.utils.ao.i() && !b(bVar.g())) {
            bVar.h().c(5);
        }
    }

    private void f(String str) {
        ContentRecord contentRecord = this.b;
        if (contentRecord != null) {
            int aW = contentRecord.aW();
            ho.a("EventProcessor", "onAdRecallReport, eventType:%s, recallSource:%s", str, Integer.valueOf(aW));
            if (aW == 1) {
                new com.huawei.openalliance.ad.analysis.c(this.d).b(this.b, str);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f(com.huawei.openalliance.ad.processor.eventbeans.b bVar) {
        if (this.b == null) {
            return;
        }
        EventType m = bVar.m();
        EventRecord b = b(m);
        if (b(b, m)) {
            return;
        }
        if (bVar.h() != null) {
            g(bVar);
            a(bVar.h());
            if (bVar.h().a() != null) {
                b.a(bVar.h().a());
            }
            if (bVar.h().b() != null) {
                b.b(bVar.h().b());
            }
            if (bVar.h().c() != null) {
                b.A(bVar.h().c());
            }
            if (bVar.h().g() != null) {
                b.a(bVar.h().g().floatValue());
            }
            if (bVar.h().d() != null) {
                b.k(bVar.h().d().intValue());
            }
            if (bVar.h().e() != null) {
                b.l(bVar.h().e().intValue());
            }
            if (bVar.h().f() != null) {
                b.m(bVar.h().f().intValue());
            }
            if (bVar.h().i() != null) {
                b.h(bVar.h().i().longValue());
            }
            if (bVar.h().h() != null) {
                b.i(bVar.h().h().longValue());
            }
            if (!com.huawei.openalliance.ad.utils.cz.b(bVar.h().k())) {
                b.F(bVar.h().k());
            }
        }
        b.t(bVar.i());
        b.z(bVar.l());
        if (b(bVar)) {
            a(os.j(this.b.V()), b, bVar);
        } else {
            ho.a("EventProcessor", "is not a valid click");
            e(bVar);
        }
    }

    private void e(final String str) {
        com.huawei.openalliance.ad.utils.m.h(new Runnable() { // from class: com.huawei.openalliance.ad.ou.3
            @Override // java.lang.Runnable
            public void run() {
                if (ou.this.b == null) {
                    ho.d("EventProcessor", "contentRecord is null, can't report HA show event");
                } else {
                    com.huawei.openalliance.ad.utils.h.a(ou.this.d, ou.this.d.getPackageName(), AnalyticsEventType.AD_REWARD, ou.this.b.l(), str);
                }
            }
        });
    }

    private void e(com.huawei.openalliance.ad.processor.eventbeans.b bVar) {
        new com.huawei.openalliance.ad.analysis.c(this.d).a(bVar, this.b);
    }

    private boolean d(com.huawei.openalliance.ad.processor.eventbeans.b bVar) {
        return !com.huawei.openalliance.ad.utils.cz.b(bVar.a());
    }

    private boolean d(com.huawei.openalliance.ad.processor.eventbeans.a aVar) {
        if (!v() || aVar == null || !aVar.b() || aVar.l()) {
            return false;
        }
        ho.a("EventProcessor", "isSupplementImp(), adShowInfo.impSource= %s", aVar.e());
        return (com.huawei.openalliance.ad.utils.c.a(aVar.e()) || com.huawei.openalliance.ad.utils.c.b(aVar.e())) ? false : true;
    }

    private void d(EventType eventType) {
        EventRecord b = b(eventType);
        if (b == null) {
            return;
        }
        qe.a(this.d, this.f7405a, eventType).a(b, this.b);
    }

    private EventRecord d(String str) {
        if (this.b == null || !A()) {
            ho.d("EventProcessor", "fail to create %s event record", str);
            return null;
        }
        EventRecord eventRecord = new EventRecord();
        eventRecord.b(str);
        eventRecord.d(this.f7405a.a());
        eventRecord.a(this.b.F());
        eventRecord.d(com.huawei.openalliance.ad.utils.ao.c());
        eventRecord.c(this.b.j());
        eventRecord.a(this.b.bn());
        eventRecord.i(this.b.am());
        eventRecord.x(this.b.ag());
        eventRecord.w(this.b.m());
        eventRecord.f(this.b.aK());
        eventRecord.n(this.b.aW());
        String bf = this.b.bf();
        if (TextUtils.isEmpty(bf)) {
            bf = this.d.getPackageName();
        }
        eventRecord.E(bf);
        if (EventType.SHOW.value().equals(str) || EventType.SUPPLEMENTIMP.value().equals(str)) {
            eventRecord.v(this.b.ao());
        }
        if (EventType.VIDEOTIME.value().equals(str)) {
            eventRecord.g(this.b.be());
        }
        if (ho.a()) {
            ho.a("EventProcessor", "create event, type is : %s, rt : %d", str, Integer.valueOf(this.b.am()));
        }
        return eventRecord;
    }

    private PostBackEvent d(EventRecord eventRecord) {
        if (eventRecord == null) {
            return null;
        }
        PostBackEvent postBackEvent = new PostBackEvent();
        postBackEvent.a(eventRecord.aj());
        postBackEvent.b(eventRecord.l());
        ContentRecord contentRecord = this.b;
        postBackEvent.b(contentRecord != null ? contentRecord.l() : "");
        postBackEvent.e(eventRecord.i());
        postBackEvent.c(eventRecord.Q());
        postBackEvent.d(eventRecord.k());
        postBackEvent.a(eventRecord.g());
        postBackEvent.a(eventRecord.a());
        postBackEvent.b(eventRecord.j());
        postBackEvent.f(eventRecord.R());
        return postBackEvent;
    }

    private boolean c(com.huawei.openalliance.ad.processor.eventbeans.b bVar) {
        return y() && !d(bVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean c(EventReportRsp eventReportRsp) {
        return eventReportRsp != null && eventReportRsp.responseCode == 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(final String str) {
        com.huawei.openalliance.ad.utils.m.h(new Runnable() { // from class: com.huawei.openalliance.ad.ou.11
            @Override // java.lang.Runnable
            public void run() {
                Context context;
                String l;
                String str2;
                if (ou.this.b == null) {
                    ho.d("EventProcessor", "contentRecord is null, can't report HA show event");
                    return;
                }
                String packageName = ou.this.d.getPackageName();
                if (Constants.REWARD_ACTIVITY_NAME.equals(str) || Constants.INTERSTITIAL_ACTIVITY_NAME.equals(str)) {
                    context = ou.this.d;
                    l = ou.this.b.l();
                    str2 = "";
                } else {
                    context = ou.this.d;
                    l = ou.this.b.l();
                    str2 = str;
                }
                com.huawei.openalliance.ad.utils.h.a(context, packageName, AnalyticsEventType.AD_CLICK, l, str2);
            }
        });
    }

    private void c(EventRecord eventRecord) {
        final PostBackEvent d = d(eventRecord);
        if (d == null || com.huawei.openalliance.ad.utils.x.j(this.d)) {
            return;
        }
        com.huawei.openalliance.ad.utils.m.a(new Runnable() { // from class: com.huawei.openalliance.ad.ou.5
            @Override // java.lang.Runnable
            public void run() {
                String b = d.b();
                boolean a2 = el.a(b);
                boolean g = ou.this.g(b);
                if (!a2 || !g) {
                    if (a2 || g) {
                        ou.this.b(d);
                    }
                    ou.this.a(d);
                    return;
                }
                ou.this.b(d);
            }
        });
    }

    private void c(EventType eventType) {
        ContentRecord contentRecord = this.b;
        if (contentRecord == null || eventType == null) {
            return;
        }
        String a2 = a(this.b, eventType.value(), os.j(contentRecord.V()));
        ho.b("EventProcessor", "vastMonitor, key: %s", a2);
        if (!this.e.a(this.b.e(), a2)) {
            d(eventType);
        } else if (ho.a()) {
            ho.a("EventProcessor", "event %s has reported", eventType.value());
        }
    }

    private AdEventReport c(ContentRecord contentRecord) {
        AdEventReport adEventReport = new AdEventReport();
        if (contentRecord != null) {
            adEventReport.a(contentRecord.e());
            adEventReport.a(contentRecord.m());
            adEventReport.b(contentRecord.j());
            adEventReport.c(contentRecord.ag());
            adEventReport.d(contentRecord.ao());
        }
        return adEventReport;
    }

    private boolean b(Integer num) {
        return num != null && num.intValue() == 2;
    }

    private boolean b(com.huawei.openalliance.ad.processor.eventbeans.b bVar) {
        ho.a("EventProcessor", "check if it is a valid click");
        if (!fg.a(this.d).c(this.b.l())) {
            ho.a("EventProcessor", "notNeedCheckClick");
            return true;
        }
        String a2 = com.huawei.openalliance.ad.utils.w.a(this.d).a();
        if ((!"0".equals(a2) && !"1".equals(a2)) || this.b.e() == 1) {
            return true;
        }
        if (c(bVar) && !com.huawei.openalliance.ad.utils.ao.h() && !b(bVar.g())) {
            ho.a("EventProcessor", "It is a template ad & UiEngineVersion is old");
            return true;
        }
        if (d(bVar) && !j.i(bVar.a())) {
            ho.a("EventProcessor", "It is a js ad & jsVersion is old %s", bVar.a());
            return true;
        }
        if (bVar.m() == null) {
            return false;
        }
        if (bVar.m().getCategory() != 2 && !EventType.REPEATEDCLICK.value().equals(bVar.m().value())) {
            return true;
        }
        int[] aq = fh.b(this.d).aq();
        bVar.a(aq[0] + Constants.LINK + aq[1]);
        MaterialClickInfo h = bVar.h();
        if (h == null) {
            ho.a("EventProcessor", "clickInfo is null");
            return false;
        }
        if (h.d() != null && h.d().intValue() != 0) {
            ho.a("EventProcessor", "sld is not click");
            return true;
        }
        if (h.j() != null && h.j().intValue() == 1) {
            ho.a("EventProcessor", "marked");
            return true;
        }
        if (h.i() == null || h.h() == null) {
            return false;
        }
        long longValue = h.h().longValue() - h.i().longValue();
        return longValue >= ((long) aq[0]) && longValue <= ((long) aq[1]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean b(EventRecord eventRecord, EventType eventType) {
        if (eventRecord != null) {
            return false;
        }
        ho.d("EventProcessor", "fail to create %s event record", eventType.value());
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b(EventReportRsp eventReportRsp) {
        if (eventReportRsp.b() != null && eventReportRsp.b().intValue() == 0) {
            return true;
        }
        List<AdEventResult> a2 = eventReportRsp.a();
        List<AdEventResultV2> c = eventReportRsp.c();
        if (com.huawei.openalliance.ad.utils.bg.a(a2) && com.huawei.openalliance.ad.utils.bg.a(c)) {
            ho.d("EventProcessor", "real time report failed");
            return false;
        }
        if (!com.huawei.openalliance.ad.utils.bg.a(c)) {
            return false;
        }
        for (AdEventResult adEventResult : a2) {
            if (adEventResult != null && 200 != adEventResult.b()) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final String str) {
        com.huawei.openalliance.ad.utils.m.a(new Runnable() { // from class: com.huawei.openalliance.ad.ou.1
            @Override // java.lang.Runnable
            public void run() {
                if (ou.this.b == null) {
                    ho.d("EventProcessor", "contentRecord is null, can't report HA show event");
                } else {
                    com.huawei.openalliance.ad.utils.h.a(ou.this.d, ou.this.d.getPackageName(), AnalyticsEventType.AD_IMP, ou.this.b.l(), str);
                }
            }
        });
    }

    private void b(MaterialClickInfo materialClickInfo) {
        Integer a2 = materialClickInfo.a();
        Integer valueOf = Integer.valueOf(Constants.COORDINATE_FAIL_DEF);
        if (a2 == null || materialClickInfo.a().intValue() == -111111) {
            materialClickInfo.a(valueOf);
        }
        if (materialClickInfo.b() == null || materialClickInfo.b().intValue() == -111111) {
            materialClickInfo.b(valueOf);
        }
        if (materialClickInfo.e() == null || materialClickInfo.e().intValue() == -111111) {
            materialClickInfo.d(valueOf);
        }
        if (materialClickInfo.f() == null || materialClickInfo.f().intValue() == -111111) {
            materialClickInfo.e(valueOf);
        }
    }

    private void b(EventRecord eventRecord) {
        ContentRecord contentRecord = this.b;
        if (contentRecord != null) {
            eventRecord.p(contentRecord.ay());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final ContentRecord contentRecord) {
        if (contentRecord != null) {
            if ((contentRecord.e() == 1 || contentRecord.e() == 18) && !TextUtils.isEmpty(contentRecord.m())) {
                com.huawei.openalliance.ad.utils.m.a(new Runnable() { // from class: com.huawei.openalliance.ad.ou.9
                    @Override // java.lang.Runnable
                    public void run() {
                        eu.a(ou.this.d).d(contentRecord.m());
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(PostBackEvent postBackEvent) {
        if (postBackEvent == null) {
            return;
        }
        try {
            ArrayList arrayList = new ArrayList();
            arrayList.add(postBackEvent.b());
            if (!com.huawei.openalliance.ad.utils.cj.a(this.d, 1, arrayList)) {
                ho.a("EventProcessor", "%s not support recommend engine", postBackEvent.b());
                return;
            }
            if (com.huawei.openalliance.ad.utils.cz.b(postBackEvent.a())) {
                postBackEvent.a(this.d.getPackageName());
            }
            ms.a(this.d).a(RTCMethods.REPORT_TO_REC_ENGINE, com.huawei.openalliance.ad.utils.be.b(postBackEvent), null, null);
        } catch (Throwable th) {
            ho.c("EventProcessor", "report to hms recommend engine: %s", th.getClass().getSimpleName());
        }
    }

    private EventRecord b(EventType eventType) {
        if (eventType != null) {
            return d(eventType.value());
        }
        ho.d("EventProcessor", "event is null");
        return null;
    }

    private static boolean a(EventType eventType) {
        return EventType.APPDOWNLOAD.equals(eventType) || EventType.APPOPEN.equals(eventType) || EventType.INTENTSUCCESS.equals(eventType);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, int i, String str2, boolean z) {
        new com.huawei.openalliance.ad.analysis.c(this.d).a(str, Integer.valueOf(i), str2, this.b, z);
    }

    private void a(MaterialClickInfo materialClickInfo) {
        if (materialClickInfo.d() == null) {
            return;
        }
        if (c(materialClickInfo.d().intValue())) {
            b(materialClickInfo);
            return;
        }
        materialClickInfo.a((Integer) null);
        materialClickInfo.b(null);
        materialClickInfo.d(null);
        materialClickInfo.e(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(EventRecord eventRecord, EventType eventType, com.huawei.openalliance.ad.processor.eventbeans.b bVar) {
        eventRecord.e(bVar.d());
        eventRecord.f(bVar.e());
        eventRecord.a(bVar.f());
        if (bVar.g() != null) {
            eventRecord.j(bVar.g().toString());
        }
        ho.a(InterstitialMethods.ON_AD_CLICK, "cacheAndReportEvent, clickSource: %s, sld: %s", bVar.g(), Integer.valueOf(eventRecord.ac()));
        qf a2 = qe.a(this.d, this.f7405a, eventType);
        if (bVar.k()) {
            a2.a(eventType.value(), eventRecord, eventType != EventType.CLICK, this.b);
        } else {
            a2.b(eventType.value(), eventRecord, eventType != EventType.CLICK, this.b);
        }
        if (EventType.CLICK == eventType || EventType.REPEATEDCLICK == eventType) {
            f(eventType.value());
        }
        c(eventRecord);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(EventRecord eventRecord, EventType eventType, com.huawei.openalliance.ad.processor.eventbeans.a aVar, ot otVar) {
        a(eventRecord, eventType, aVar);
        if (otVar != null) {
            ho.b("EventProcessor", "add eventExtInfo data");
            Integer b = otVar.b();
            if (b != null && b.intValue() >= 0) {
                eventRecord.j(b.intValue());
            }
            Integer a2 = otVar.a();
            if (a2 != null && a2.intValue() >= 0) {
                eventRecord.C(String.valueOf(a2));
            }
        }
        ContentRecord contentRecord = this.b;
        if (contentRecord != null) {
            eventRecord.h(contentRecord.k());
        }
        ContentRecord contentRecord2 = this.b;
        if (contentRecord2 != null) {
            eventRecord.f(contentRecord2.aK());
        }
        if (eventType == EventType.SHOW || eventType == EventType.SUPPLEMENTIMP) {
            b(eventRecord);
        }
        qf a3 = qe.a(this.d, this.f7405a, eventType);
        if (aVar.h()) {
            a3.a(eventType.value(), eventRecord, eventType != EventType.SHOW, this.b);
            if (eventType == EventType.REPEATEDIMP) {
                B();
            }
        } else {
            a3.b(eventType.value(), eventRecord, eventType != EventType.SHOW, this.b);
        }
        if (EventType.SHOW.equals(eventType)) {
            this.f7405a.c();
        }
        if (eventType.equals(EventType.SHOW) || eventType.equals(EventType.REPEATEDIMP)) {
            f(eventType.value());
        }
        c(eventRecord);
    }

    private void a(EventRecord eventRecord, EventType eventType, com.huawei.openalliance.ad.processor.eventbeans.a aVar) {
        if (eventRecord == null || aVar == null) {
            return;
        }
        eventRecord.t(aVar.g());
        eventRecord.z(aVar.j());
        eventRecord.D(aVar.k());
        if (ho.a()) {
            Object[] objArr = new Object[6];
            objArr[0] = eventType;
            objArr[1] = aVar.c();
            objArr[2] = aVar.d();
            ContentRecord contentRecord = this.b;
            objArr[3] = contentRecord == null ? "" : contentRecord.j();
            objArr[4] = aVar.e();
            objArr[5] = aVar.a();
            ho.a("EventProcessor", "onAdImpEvent type: %s duration: %s ratio: %s showId: %s source: %s creativeSize: %s", objArr);
        }
        ContentRecord contentRecord2 = this.b;
        if (contentRecord2 != null && (contentRecord2.e() == 1 || this.b.e() == 18)) {
            eventRecord.g(this.b.T());
        }
        if (aVar.c() != null) {
            eventRecord.a(aVar.c().longValue());
        }
        if (aVar.d() != null) {
            eventRecord.c(aVar.d().intValue());
        }
        if (aVar.e() != null) {
            eventRecord.m(String.valueOf(aVar.e()));
        }
        if (aVar.a() != null) {
            eventRecord.A(aVar.a());
        }
    }

    private void a(EventRecord eventRecord) {
        ApkInfo r;
        ContentRecord contentRecord = this.b;
        if (contentRecord == null || contentRecord.h() == null || (r = this.b.h().r()) == null) {
            return;
        }
        String str = r.l() + "#" + r.K();
        ho.a("EventProcessor", "install ways:%s", str);
        eventRecord.t(str);
    }

    private void a(EventType eventType, Integer num, boolean z, boolean z2, Integer num2, DownloadBlockInfo downloadBlockInfo) {
        if (eventType == null || num == null) {
            return;
        }
        EventRecord b = b(eventType);
        if (b(b, eventType)) {
            return;
        }
        ho.a("EventProcessor", "reportDownloadOrOpenEvent type: %s source: %s reason: %s blockInfo: %s", eventType.value(), num, num2, downloadBlockInfo);
        b.j(num.toString());
        if (num2 != null) {
            b.o(String.valueOf(num2));
        }
        if (downloadBlockInfo != null) {
            b.p(String.valueOf(downloadBlockInfo.a()));
            b.q(String.valueOf(downloadBlockInfo.b()));
            b.s(String.valueOf(downloadBlockInfo.c() ? 1 : 0));
        }
        a(eventType, b);
        qf a2 = qe.a(this.d, this.f7405a, eventType);
        String value = eventType.value();
        if (z) {
            a2.a(value, b, z2, this.b);
        } else {
            a2.b(value, b, z2, this.b);
        }
    }

    private void a(EventType eventType, Integer num, String str, boolean z, boolean z2, Integer num2, DownloadBlockInfo downloadBlockInfo, Integer num3, String str2, String str3, String str4) {
        if (eventType == null || num == null) {
            return;
        }
        EventRecord b = b(eventType);
        if (b(b, eventType)) {
            return;
        }
        if (ho.a()) {
            ho.a("EventProcessor", "reportDownloadOrOpenEvent type: %s source: %s reason: %s blockInfo: %s agVerifyCode: %s installType: %s", eventType.value(), num, num2, downloadBlockInfo, str2, str3);
        }
        b.j(num.toString());
        b.k(str);
        if (num2 != null) {
            b.o(String.valueOf(num2));
        }
        if (num3 != null) {
            b.r(String.valueOf(num3));
        }
        if (downloadBlockInfo != null) {
            b.p(String.valueOf(downloadBlockInfo.a()));
            b.q(String.valueOf(downloadBlockInfo.b()));
            b.s(String.valueOf(downloadBlockInfo.c() ? 1 : 0));
        }
        if (str2 != null) {
            b.y(str2);
        }
        if (str3 != null) {
            b.z(str3);
        }
        b.B(com.huawei.openalliance.ad.utils.cz.m(str4));
        a(eventType, b);
        qf a2 = qe.a(this.d, this.f7405a, eventType);
        String value = eventType.value();
        ContentRecord contentRecord = this.b;
        if (z) {
            a2.a(value, b, z2, contentRecord);
        } else {
            a2.b(value, b, z2, contentRecord);
        }
    }

    private void a(EventType eventType, Integer num, Integer num2, boolean z, boolean z2, String str, String str2, String str3) {
        if (eventType == null || num == null) {
            return;
        }
        EventRecord b = b(eventType);
        if (b(b, eventType)) {
            return;
        }
        if (ho.a()) {
            ho.a("EventProcessor", "reportInstallEvent type: %s source: %s agVerifyCode: %s installType: %s", eventType.value(), num, str, str2);
        }
        b.k(num.toString());
        if (num2 != null) {
            b.j(num2.toString());
        }
        if (str != null) {
            b.y(str);
        }
        if (str2 != null) {
            b.z(str2);
        }
        b.B(com.huawei.openalliance.ad.utils.cz.m(str3));
        qf a2 = qe.a(this.d, this.f7405a, eventType);
        String value = eventType.value();
        ContentRecord contentRecord = this.b;
        if (z) {
            a2.a(value, b, z2, contentRecord);
        } else {
            a2.b(value, b, z2, contentRecord);
        }
    }

    private void a(EventType eventType, com.huawei.openalliance.ad.processor.eventbeans.a aVar, ot otVar) {
        int intValue;
        if (aVar.i() == null) {
            ContentRecord contentRecord = this.b;
            if (contentRecord == null) {
                return;
            } else {
                intValue = os.j(contentRecord.V());
            }
        } else {
            intValue = aVar.i().intValue();
        }
        if (eventType == null) {
            return;
        }
        if ("2".equals(com.huawei.openalliance.ad.utils.cm.a(this.d, this.b))) {
            intValue = 1;
        }
        a(intValue, eventType, aVar, otVar);
    }

    private void a(EventType eventType, EventRecord eventRecord) {
        ContentRecord contentRecord;
        if (!a(eventType) || eventRecord == null || (contentRecord = this.b) == null) {
            return;
        }
        eventRecord.t(contentRecord.aA());
        eventRecord.u(this.b.aB());
    }

    private void a(EventType eventType, long j, long j2, int i, int i2, String str) {
        ho.b("EventProcessor", "reportVideoPlayState eventType: %s startTime: %d, endtime: %d startProgress: %d endProgress: %d", eventType, Long.valueOf(j), Long.valueOf(j2), Integer.valueOf(i), Integer.valueOf(i2));
        EventRecord b = b(eventType);
        if (b(b, eventType)) {
            return;
        }
        b.b(j);
        b.c(j2);
        b.a(i);
        b.b(i2);
        b.t(str);
        b(b);
        qe.a(this.d, this.f7405a, eventType).a(eventType.value(), b, false, this.b);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(PostBackEvent postBackEvent) {
        if (postBackEvent != null && el.a(this.d)) {
            el.a(this.d, postBackEvent);
        }
    }

    private void a(final int i, final EventRecord eventRecord, final com.huawei.openalliance.ad.processor.eventbeans.b bVar) {
        if (bVar == null) {
            return;
        }
        com.huawei.openalliance.ad.utils.m.h(new Runnable() { // from class: com.huawei.openalliance.ad.ou.12
            @Override // java.lang.Runnable
            public void run() {
                String str;
                boolean z = false;
                if (!ou.this.b(i)) {
                    ou.this.a(eventRecord, EventType.CLICK, bVar);
                    ou.this.c(bVar.j());
                    str = "";
                } else {
                    if (ou.this.b == null) {
                        return;
                    }
                    ou ouVar = ou.this;
                    str = ouVar.a(ouVar.b, eventRecord.i(), i);
                    ho.b("EventProcessor", "onAdClick key: " + str);
                    if (ou.this.e.a(ou.this.b.e(), str)) {
                        ho.b("EventProcessor", "onAdClick repeated event");
                        if (ou.this.c.E()) {
                            eventRecord.b(EventType.REPEATEDCLICK.value());
                            ou.this.a(eventRecord, EventType.REPEATEDCLICK, bVar);
                        }
                        z = true;
                    } else {
                        ho.b("EventProcessor", "onAdClick report event");
                        ou.this.a(eventRecord, EventType.CLICK, bVar);
                        ou.this.c(bVar.j());
                    }
                }
                ou.this.a(EventType.CLICK.value(), i, str, z);
            }
        });
    }

    private void a(final int i, final EventType eventType, final com.huawei.openalliance.ad.processor.eventbeans.a aVar, final ot otVar) {
        final EventRecord b = b(eventType);
        if (b(b, eventType)) {
            return;
        }
        com.huawei.openalliance.ad.utils.m.h(new Runnable() { // from class: com.huawei.openalliance.ad.ou.8
            @Override // java.lang.Runnable
            public void run() {
                String str;
                boolean z = false;
                if (!eventType.equals(EventType.SHOW) || !ou.this.b(i)) {
                    if (eventType.equals(EventType.SHOW)) {
                        ou.this.a(b, eventType, aVar, otVar);
                        ou.this.b(aVar.f());
                    } else {
                        ou.this.a(b, eventType, aVar, otVar);
                    }
                    str = "";
                } else {
                    if (ou.this.b == null) {
                        return;
                    }
                    ou ouVar = ou.this;
                    str = ouVar.a(ouVar.b, eventType.value(), i);
                    ho.b("EventProcessor", "onAdImp key: " + str);
                    if (ou.this.e.a(ou.this.b.e(), str)) {
                        ho.b("EventProcessor", "onAdImp repeated event");
                        if (!ou.this.c.E()) {
                            ou.this.B();
                        } else {
                            b.b(EventType.REPEATEDIMP.value());
                            ou.this.a(b, EventType.REPEATEDIMP, aVar, otVar);
                        }
                        z = true;
                    } else {
                        ho.b("EventProcessor", "onAdImp report event");
                        ou.this.a(b, eventType, aVar, otVar);
                        ou.this.b(aVar.f());
                        ou ouVar2 = ou.this;
                        ouVar2.b(ouVar2.b);
                    }
                }
                if (eventType.equals(EventType.SHOW)) {
                    ou.this.a(eventType.value(), i, str, z);
                }
            }
        });
    }

    private void a(int i, int i2, List<String> list, EventType eventType) {
        EventRecord b = b(eventType);
        if (b(b, eventType)) {
            return;
        }
        List<String> arrayList = new ArrayList<>();
        List<String> arrayList2 = new ArrayList<>();
        HashMap hashMap = new HashMap();
        ArrayList arrayList3 = new ArrayList();
        ContentRecord contentRecord = this.b;
        if (contentRecord != null) {
            arrayList = contentRecord.I();
            if (!com.huawei.openalliance.ad.utils.bg.a(arrayList)) {
                ho.a("onAdClose", "fullDoseKeyWords: %s", arrayList.toString());
            }
            arrayList2 = this.b.J();
            if (!com.huawei.openalliance.ad.utils.bg.a(arrayList2)) {
                ho.a("onAdClose", "fullDoseKeyWordsType: %s", arrayList2.toString());
            }
        }
        if (!com.huawei.openalliance.ad.utils.bg.a(arrayList) && !com.huawei.openalliance.ad.utils.bg.a(arrayList2) && arrayList.size() == arrayList2.size()) {
            for (int i3 = 0; i3 < arrayList.size(); i3++) {
                hashMap.put(arrayList.get(i3), arrayList2.get(i3));
            }
        }
        if (!hashMap.isEmpty() && !com.huawei.openalliance.ad.utils.bg.a(list)) {
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                arrayList3.add(hashMap.get(it.next()));
            }
        }
        if (!com.huawei.openalliance.ad.utils.bg.a(list)) {
            ho.a("onAdClose", "selectedKeyWords: %s", list.toString());
        }
        if (!com.huawei.openalliance.ad.utils.bg.a(arrayList3)) {
            ho.a("onAdClose", "selectedKeyWordsType: %s", arrayList3.toString());
        }
        b.e(i);
        b.f(i2);
        b.a(list);
        b.b(arrayList3);
        ContentRecord contentRecord2 = this.b;
        if (contentRecord2 == null || !contentRecord2.ap()) {
            qe.a(this.d, this.f7405a, eventType).a(eventType.value(), b, false, this.b);
            if (!EventType.EASTEREGGCLOSE.equals(eventType)) {
                this.f7405a.d();
            }
        } else {
            ho.a("EventProcessor", "report exsplash close event");
            AdEventReport c = c(this.b);
            c.b(i);
            c.c(i2);
            c.a(list);
            ms.a(this.d).a(MapKeyNames.REPORT_CLOSE_EVENT, com.huawei.openalliance.ad.utils.be.b(c), null, null);
        }
        c(b);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String a(ContentRecord contentRecord, String str, int i) {
        StringBuilder sb = new StringBuilder();
        if (contentRecord == null) {
            return sb.toString();
        }
        sb.append(contentRecord.e());
        sb.append("_");
        sb.append(contentRecord.m());
        sb.append("_");
        String ag = contentRecord.ag();
        if (!TextUtils.isEmpty(ag)) {
            sb.append(ag);
            sb.append("_");
        }
        if (i == 2 || (i == 1 && TextUtils.isEmpty(ag))) {
            sb.append(contentRecord.j());
            sb.append("_");
        }
        sb.append(str);
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public EventRecord a(EventType eventType, com.huawei.openalliance.ad.analysis.b bVar) {
        String str;
        try {
            if (!A()) {
                return null;
            }
            EventRecord eventRecord = new EventRecord();
            eventRecord.d(bVar.t() != null ? bVar.t().intValue() : -1);
            eventRecord.b(eventType.value());
            eventRecord.d(com.huawei.openalliance.ad.utils.ao.c());
            eventRecord.d(bVar.aI());
            eventRecord.i(bVar.N());
            ho.b("EventProcessor", "create event, type is : %s, rt : %d", eventType + " " + bVar.toString(), Integer.valueOf(bVar.N()));
            return eventRecord;
        } catch (RuntimeException unused) {
            str = "createAnalysisEvent RuntimeException";
            ho.d("EventProcessor", str);
            return null;
        } catch (Exception unused2) {
            str = "createAnalysisEvent error";
            ho.d("EventProcessor", str);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void B() {
        com.huawei.openalliance.ad.utils.bo.a(new Runnable() { // from class: com.huawei.openalliance.ad.ou.4
            @Override // java.lang.Runnable
            public void run() {
                qe.a(ou.this.d, ou.this.f7405a, EventType.SHOW).e();
            }
        }, 100L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean A() {
        return this.c.aL();
    }

    public ou(Context context, sl slVar, ContentRecord contentRecord) {
        Context applicationContext = context.getApplicationContext();
        this.d = applicationContext;
        this.f7405a = slVar;
        this.c = fh.b(applicationContext);
        this.f = fb.a(this.d);
        this.b = contentRecord;
        this.e = jc.a(this.d);
        this.g = fe.a(this.d);
    }

    public ou(Context context, sl slVar) {
        this(context, slVar, null);
    }
}
