package com.huawei.openalliance.ad.utils;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hmf.tasks.Task;
import com.huawei.hms.analytics.HiAnalytics;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.ho;

/* loaded from: classes5.dex */
public class a {
    public static boolean a() {
        return ck.c(Constants.HIANALYTICS_SDK);
    }

    public static String a(final Context context) {
        if (!a()) {
            return "";
        }
        final cg a2 = cg.a(context);
        String M = a2.M();
        if (TextUtils.isEmpty(M)) {
            m.f(new Runnable() { // from class: com.huawei.openalliance.ad.utils.a.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        Task aaid = HiAnalytics.getInstance(context).getAAID();
                        if (aaid != null) {
                            a2.F((String) aaid.getResult());
                        }
                    } catch (Throwable th) {
                        ho.c("AaidUtil", "error getAgcAaid: " + th.getClass().getSimpleName());
                    }
                }
            });
        }
        return M;
    }
}
