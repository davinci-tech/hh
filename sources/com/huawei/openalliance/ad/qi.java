package com.huawei.openalliance.ad;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.openalliance.ad.beans.metadata.Monitor;
import com.huawei.openalliance.ad.beans.server.ThirdReportRsp;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.db.bean.EncryptionField;
import com.huawei.openalliance.ad.db.bean.ThirdPartyEventRecord;
import com.huawei.openalliance.ad.pc;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes5.dex */
public class qi {

    /* renamed from: a, reason: collision with root package name */
    private static final Executor f7473a = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), new com.huawei.openalliance.ad.utils.n("ThirdMonitor-Event"));
    private Context b;
    private fv c;
    private pc d;
    private fx e;
    private sl f;
    private List<a> g;

    public interface a {
        String a(String str);
    }

    public void a(List<a> list) {
        this.g = new ArrayList(list);
    }

    void a(final String str, final ContentRecord contentRecord) {
        final List<Monitor> a2 = a(str, contentRecord, this.b);
        if (com.huawei.openalliance.ad.utils.bg.a(a2)) {
            return;
        }
        if (ho.a()) {
            ho.a("ThirdMonitorReporter", "reportThirdMonitor, eventType: %s, monitors: %s", str, com.huawei.openalliance.ad.utils.dl.a(com.huawei.openalliance.ad.utils.be.b(a2)));
        }
        com.huawei.openalliance.ad.utils.m.b(new Runnable() { // from class: com.huawei.openalliance.ad.qi.1
            @Override // java.lang.Runnable
            public void run() {
                qi.this.a((List<Monitor>) a2, str, contentRecord);
            }
        });
    }

    void a() {
        a(new Runnable() { // from class: com.huawei.openalliance.ad.qi.2
            @Override // java.lang.Runnable
            public void run() {
                qi.this.b();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        String str;
        List<ThirdPartyEventRecord> b = this.c.b(120000L, 3);
        ho.b("ThirdMonitorReporter", "uploadThirdPartyCacheEvents size: " + b.size());
        if (com.huawei.openalliance.ad.utils.bg.a(b)) {
            return;
        }
        this.c.a(com.huawei.openalliance.ad.utils.ao.c(), b(b));
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        byte[] b2 = com.huawei.openalliance.ad.utils.cp.b(this.b);
        for (ThirdPartyEventRecord thirdPartyEventRecord : b) {
            EncryptionField<String> b3 = thirdPartyEventRecord.b();
            if (b3 == null) {
                str = "urlField is empty";
            } else {
                pc.a a2 = this.d.a(b3.a(b2), thirdPartyEventRecord.d(), thirdPartyEventRecord.i());
                if (a2 == null) {
                    str = "monitorUrl is empty";
                } else {
                    String a3 = a2.a();
                    if (TextUtils.isEmpty(a3)) {
                        ho.c("ThirdMonitorReporter", "formatThirdPartyUrl is empty when format third party cache url ");
                        arrayList.add(thirdPartyEventRecord.a());
                    } else {
                        ContentRecord contentRecord = new ContentRecord();
                        contentRecord.b(thirdPartyEventRecord.c());
                        contentRecord.d(thirdPartyEventRecord.e());
                        contentRecord.e(thirdPartyEventRecord.f());
                        contentRecord.o(thirdPartyEventRecord.h());
                        contentRecord.f(thirdPartyEventRecord.i());
                        long c = com.huawei.openalliance.ad.utils.ao.c();
                        ThirdReportRsp b4 = this.e.b(a3);
                        long c2 = com.huawei.openalliance.ad.utils.ao.c() - c;
                        if (a(b4)) {
                            arrayList.add(thirdPartyEventRecord.a());
                            a(this.b, thirdPartyEventRecord.g(), a3, a2.b(), contentRecord, c2);
                        } else {
                            arrayList2.add(thirdPartyEventRecord.a());
                            this.c.a(thirdPartyEventRecord.a(), a2.b());
                            a(this.b, thirdPartyEventRecord.g(), a3, a2.b(), c2, contentRecord, b4);
                        }
                    }
                }
            }
            ho.b("ThirdMonitorReporter", str);
            arrayList.add(thirdPartyEventRecord.a());
        }
        this.c.b(com.huawei.openalliance.ad.utils.ao.c(), arrayList2);
        this.c.a(arrayList);
    }

    private static List<String> b(List<ThirdPartyEventRecord> list) {
        ArrayList arrayList = new ArrayList();
        Iterator<ThirdPartyEventRecord> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().a());
        }
        return arrayList;
    }

    private static boolean a(ThirdReportRsp thirdReportRsp) {
        return thirdReportRsp != null && thirdReportRsp.responseCode == 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(List<Monitor> list, String str, ContentRecord contentRecord) {
        byte[] bArr;
        Iterator<Monitor> it;
        if (list == null || list.isEmpty()) {
            ho.c("ThirdMonitorReporter", "fail to report to thirdParty event, thirdParty  is empty");
            return;
        }
        byte[] b = com.huawei.openalliance.ad.utils.cp.b(this.b);
        Iterator<Monitor> it2 = list.iterator();
        while (it2.hasNext()) {
            Monitor next = it2.next();
            List<String> b2 = next.b();
            if (b2 == null || b2.isEmpty()) {
                ho.c("ThirdMonitorReporter", "thirdParty monitor urls is empty ");
                it2 = it2;
                b = b;
            } else {
                int c = next.c();
                for (String str2 : b2) {
                    if (!com.huawei.openalliance.ad.utils.cz.b(str2)) {
                        ho.b("ThirdMonitorReporter", "report third party event，excute origin url, eventType:%s, originUrl:%s", str, com.huawei.openalliance.ad.utils.dl.a(str2));
                        if ((jn.a(contentRecord.m()) instanceof jo) || !jn.a(contentRecord.a(), str2)) {
                            String a2 = a(str2);
                            pc.a a3 = this.d.a(a2, contentRecord.aK());
                            if (a3 != null) {
                                String a4 = a3.a();
                                if (TextUtils.isEmpty(a4)) {
                                    ho.c("ThirdMonitorReporter", "url is empty when format third party url ");
                                } else {
                                    ho.b("ThirdMonitorReporter", "report third party event, eventType:%s, url:%s", str, com.huawei.openalliance.ad.utils.dl.a(a4));
                                    long c2 = com.huawei.openalliance.ad.utils.ao.c();
                                    ThirdReportRsp b3 = this.e.b(a4);
                                    long c3 = com.huawei.openalliance.ad.utils.ao.c() - c2;
                                    if (a(b3)) {
                                        bArr = b;
                                        it = it2;
                                        a(this.b, str, a4, a3.b(), contentRecord, c3);
                                    } else if (c == 1) {
                                        ThirdPartyEventRecord thirdPartyEventRecord = new ThirdPartyEventRecord(this.f.a(), a2, a3.b());
                                        thirdPartyEventRecord.d(contentRecord.m());
                                        thirdPartyEventRecord.c(contentRecord.l());
                                        thirdPartyEventRecord.a(contentRecord.e());
                                        thirdPartyEventRecord.b(contentRecord.am());
                                        thirdPartyEventRecord.a(b);
                                        thirdPartyEventRecord.e(str);
                                        bArr = b;
                                        it = it2;
                                        thirdPartyEventRecord.a(System.currentTimeMillis());
                                        thirdPartyEventRecord.b(contentRecord.aK());
                                        this.c.a(thirdPartyEventRecord);
                                        a(this.b, str, a4, a3.b(), c3, contentRecord, b3);
                                    }
                                    it2 = it;
                                    b = bArr;
                                }
                            }
                        } else {
                            ho.b("ThirdMonitorReporter", "mz url had reported by ICustMonitor.");
                        }
                    }
                    bArr = b;
                    it = it2;
                    it2 = it;
                    b = bArr;
                }
            }
        }
    }

    private void a(Runnable runnable) {
        f7473a.execute(new com.huawei.openalliance.ad.utils.df(runnable));
    }

    private static void a(Context context, String str, String str2, String str3, ContentRecord contentRecord, long j) {
        if (contentRecord == null) {
            return;
        }
        new com.huawei.openalliance.ad.analysis.c(context).a(str, str2, str3, j, contentRecord);
    }

    private static void a(Context context, String str, String str2, String str3, long j, ContentRecord contentRecord, ThirdReportRsp thirdReportRsp) {
        String str4;
        if (contentRecord == null) {
            return;
        }
        com.huawei.openalliance.ad.analysis.c cVar = new com.huawei.openalliance.ad.analysis.c(context);
        StringBuilder sb = new StringBuilder();
        if (thirdReportRsp != null) {
            sb.append("httpCode:");
            sb.append(thirdReportRsp.a());
            if (!TextUtils.isEmpty(thirdReportRsp.errorReason)) {
                sb.append(", errorReason:");
                str4 = thirdReportRsp.errorReason;
            }
            cVar.a(str, str2, sb.toString(), str3, j, contentRecord);
        }
        str4 = "httpCode:-1";
        sb.append(str4);
        cVar.a(str, str2, sb.toString(), str3, j, contentRecord);
    }

    public static List<Monitor> a(String str, ContentRecord contentRecord, Context context) {
        EncryptionField<List<Monitor>> K;
        List<Monitor> a2;
        if (contentRecord == null || (K = contentRecord.K()) == null || (a2 = K.a(context)) == null || a2.size() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (Monitor monitor : a2) {
            if (str.equals(monitor.a())) {
                arrayList.add(monitor);
            }
        }
        return arrayList;
    }

    private String a(String str) {
        if (!com.huawei.openalliance.ad.utils.bg.a(this.g) && !TextUtils.isEmpty(str)) {
            for (a aVar : this.g) {
                if (aVar != null) {
                    str = aVar.a(str);
                }
            }
        }
        return str;
    }

    qi(Context context, sl slVar) {
        this.b = context.getApplicationContext();
        this.c = ex.a(context);
        this.d = pc.a(context);
        this.e = fb.a(context);
        this.f = slVar;
    }
}
