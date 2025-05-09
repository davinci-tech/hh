package com.huawei.openalliance.ad.analysis;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.openalliance.ad.beans.inner.ApiStatisticsReq;
import com.huawei.openalliance.ad.beans.metadata.DelayInfo;
import com.huawei.openalliance.ad.beans.metadata.MetaData;
import com.huawei.openalliance.ad.beans.server.AdContentRsp;
import com.huawei.openalliance.ad.constant.ApiNames;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.dc;
import com.huawei.openalliance.ad.dk;
import com.huawei.openalliance.ad.fd;
import com.huawei.openalliance.ad.inter.data.IAd;
import com.huawei.openalliance.ad.utils.ae;
import com.huawei.openalliance.ad.utils.bg;
import com.huawei.openalliance.ad.utils.bl;
import com.huawei.openalliance.ad.utils.cz;
import com.huawei.openalliance.ad.utils.m;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class d {
    private static boolean a(int i) {
        return i >= 200 && i < 300 && i != 204;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean b(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (cz.j(str)) {
            str = dk.d(str);
        }
        return ae.c(context, str, "normal");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int b(DelayInfo delayInfo, String str) {
        int q = delayInfo.q();
        if (q == 494) {
            Integer w = delayInfo.w();
            q = w != null ? w.intValue() : delayInfo.r() + 20000;
        } else if (q == -2) {
            q = delayInfo.s() + ("1".equals(str) ? 10001 : "2".equals(str) ? 10002 : "3".equals(str) ? 10003 : 10000);
        }
        delayInfo.a(q);
        return q;
    }

    public static void a(Context context, String str, String str2, int i, int i2, long j, long j2, int i3) {
        if (context == null || j == 0 || j >= j2) {
            return;
        }
        ApiStatisticsReq a2 = a(str, str2, i2, i3);
        a2.b(j2 - j);
        a2.d(i);
        a(context, a2);
    }

    public static void a(final Context context, final String str, final DelayInfo delayInfo, final String str2, final ContentRecord contentRecord, final int i) {
        if (context == null || delayInfo == null || delayInfo.a() <= 0) {
            return;
        }
        m.f(new Runnable() { // from class: com.huawei.openalliance.ad.analysis.d.3
            @Override // java.lang.Runnable
            public void run() {
                ContentRecord h;
                DelayInfo.this.a(str);
                c cVar = new c(context);
                ContentRecord contentRecord2 = contentRecord;
                if (contentRecord2 != null) {
                    DelayInfo.this.b(contentRecord2.T());
                    DelayInfo.this.b(Collections.singletonList(contentRecord.m()));
                    DelayInfo.this.a(contentRecord.as());
                    DelayInfo.this.a(Integer.valueOf(contentRecord.D()));
                    MetaData h2 = contentRecord.h();
                    if (h2 != null) {
                        DelayInfo.this.d(h2.t());
                    }
                    String l = contentRecord.l();
                    if (!cz.b(l)) {
                        DelayInfo.this.f(String.valueOf(fd.a(context).a(l)));
                    }
                }
                if (!DelayInfo.this.t() && (h = dc.h()) != null) {
                    DelayInfo.this.e(h.m());
                    DelayInfo.this.b(d.b(context, h.y()));
                }
                DelayInfo delayInfo2 = DelayInfo.this;
                ContentRecord contentRecord3 = contentRecord;
                cVar.a(ApiNames.LOAD_AD, str2, DelayInfo.this, i, d.b(delayInfo2, contentRecord3 != null ? contentRecord3.T() : null));
            }
        });
    }

    public static void a(final Context context, final ContentRecord contentRecord, final int i, final int i2) {
        if (context == null) {
            return;
        }
        m.f(new Runnable() { // from class: com.huawei.openalliance.ad.analysis.d.4
            @Override // java.lang.Runnable
            public void run() {
                new c(context).a(contentRecord, i, i2);
            }
        });
    }

    public static void a(final Context context, final ContentRecord contentRecord) {
        m.f(new Runnable() { // from class: com.huawei.openalliance.ad.analysis.d.5
            @Override // java.lang.Runnable
            public void run() {
                new c(context).b(contentRecord);
            }
        });
    }

    private static void a(final Context context, final ApiStatisticsReq apiStatisticsReq) {
        m.f(new Runnable() { // from class: com.huawei.openalliance.ad.analysis.d.1
            @Override // java.lang.Runnable
            public void run() {
                new c(context).a(apiStatisticsReq);
            }
        });
    }

    public static <T extends IAd> void a(final Context context, final int i, final String str, final int i2, final Map<String, List<T>> map, final DelayInfo delayInfo) {
        if (context == null || delayInfo == null || delayInfo.a() <= 0) {
            return;
        }
        m.f(new Runnable() { // from class: com.huawei.openalliance.ad.analysis.d.2
            @Override // java.lang.Runnable
            public void run() {
                c cVar = new c(context);
                if (!bl.a(map)) {
                    ArrayList arrayList = new ArrayList();
                    ArrayList arrayList2 = new ArrayList();
                    for (Map.Entry entry : map.entrySet()) {
                        arrayList.add(entry.getKey());
                        List<IAd> list = (List) entry.getValue();
                        if (!bg.a(list)) {
                            for (IAd iAd : list) {
                                if (iAd != null) {
                                    arrayList2.add(iAd.getContentId());
                                }
                            }
                        }
                    }
                    delayInfo.a(arrayList);
                    delayInfo.b(arrayList2);
                }
                cVar.a(ApiNames.LOAD_AD, str, delayInfo, i2, i);
            }
        });
    }

    private static ApiStatisticsReq a(String str, String str2, int i, int i2) {
        ApiStatisticsReq apiStatisticsReq = new ApiStatisticsReq();
        apiStatisticsReq.b(str);
        apiStatisticsReq.d(str2);
        apiStatisticsReq.c(i);
        apiStatisticsReq.b(i2);
        return apiStatisticsReq;
    }

    private static int a(String str, int i, long j, List<ContentRecord> list) {
        ContentRecord contentRecord;
        if (TextUtils.isEmpty(str)) {
            return 4;
        }
        if (!bg.a(list)) {
            Iterator<ContentRecord> it = list.iterator();
            while (it.hasNext()) {
                contentRecord = it.next();
                if (contentRecord != null && str.equals(contentRecord.m())) {
                    break;
                }
            }
        }
        contentRecord = null;
        if (contentRecord == null) {
            return 6;
        }
        int t = contentRecord.t();
        int s = contentRecord.s();
        if (i == 0 && (contentRecord.D() == 12 || s <= t)) {
            return 2;
        }
        if (i == 1 && contentRecord.D() != 12 && s > t) {
            return 3;
        }
        if (j < contentRecord.r() || j > contentRecord.q()) {
            return 1;
        }
        return contentRecord.i() == 0 ? 5 : 7;
    }

    public static int a(AdContentRsp adContentRsp, ContentRecord contentRecord, String str, int i, long j, List<ContentRecord> list) {
        if (adContentRsp == null) {
            return -1;
        }
        if (!a(adContentRsp.a())) {
            return adContentRsp.a() + 20000;
        }
        if (contentRecord == null) {
            return a(str, i, j, list) + 30000;
        }
        return 200;
    }
}
