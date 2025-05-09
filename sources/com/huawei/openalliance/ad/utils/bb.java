package com.huawei.openalliance.ad.utils;

import android.content.Context;
import com.huawei.openalliance.ad.download.app.AppDownloadTask;
import com.huawei.openalliance.ad.os;

/* loaded from: classes5.dex */
public class bb {
    public static boolean a(AppDownloadTask appDownloadTask, Context context) {
        if (appDownloadTask != null) {
            String V = appDownloadTask.C().a().V();
            int C = os.C(V);
            boolean h = os.h(V);
            int w = dd.w(context);
            boolean v = dd.v(context);
            if ((C <= 3 || h) && com.huawei.openalliance.ad.download.app.a.a(C, w, v) > 0) {
                return true;
            }
        }
        return false;
    }
}
