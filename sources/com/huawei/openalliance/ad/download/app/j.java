package com.huawei.openalliance.ad.download.app;

import android.content.Context;
import android.os.Bundle;
import com.huawei.openalliance.ad.beans.metadata.MetaData;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.data.IAd;
import com.huawei.openalliance.ad.pi;
import com.huawei.openalliance.ad.sz;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.dj;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class j {

    /* renamed from: a, reason: collision with root package name */
    private static long f6787a;

    public boolean a(Context context, IAd iAd, boolean z) {
        if (context == null || iAd == null) {
            return false;
        }
        if (iAd instanceof com.huawei.openalliance.ad.inter.data.i) {
            return true;
        }
        int interfaceDownloadConfig = iAd.getInterfaceDownloadConfig();
        ho.a("DownloadChecker", "api control flag:%s", Integer.valueOf(interfaceDownloadConfig));
        if (interfaceDownloadConfig == 0) {
            return true;
        }
        if (interfaceDownloadConfig != 1) {
            if (interfaceDownloadConfig != 2) {
                ho.c("DownloadChecker", "invalid apiDownloadFlag value!");
            }
            return false;
        }
        if (z) {
            a(context, iAd);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(IAd iAd, Context context) {
        if (iAd == null || context == null) {
            return;
        }
        if (iAd instanceof com.huawei.openalliance.ad.inter.data.e) {
            ho.b("DownloadChecker", "native trigger action list result:%s", Boolean.valueOf(((com.huawei.openalliance.ad.inter.data.e) iAd).a(context, (Bundle) null)));
        } else if (!(iAd instanceof com.huawei.openalliance.ad.inter.data.g)) {
            ho.b("DownloadChecker", "Unsupported ad form");
        } else {
            ContentRecord a2 = pi.a((com.huawei.openalliance.ad.inter.data.g) iAd);
            ho.b("DownloadChecker", "trigger action list result:%s", Boolean.valueOf(sz.a(context, a2, a(a2)).a()));
        }
    }

    private void a(final Context context, final IAd iAd) {
        long c = ao.c();
        ho.b("DownloadChecker", "trigger action list lastTime:%s curTime:%s", Long.valueOf(f6787a), Long.valueOf(c));
        if (c - f6787a < 500) {
            ho.b("DownloadChecker", "trigger action list too frequently");
        } else {
            f6787a = c;
            dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.download.app.j.1
                @Override // java.lang.Runnable
                public void run() {
                    j.this.a(iAd, context);
                }
            });
        }
    }

    private Map<String, String> a(ContentRecord contentRecord) {
        HashMap hashMap = new HashMap();
        if (contentRecord != null && contentRecord.h() != null) {
            MetaData h = contentRecord.h();
            String m = h.m();
            String l = h.l();
            if (m != null && l != null) {
                hashMap.put("appId", m);
                hashMap.put("thirdId", l);
            }
        }
        return hashMap;
    }
}
