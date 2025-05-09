package com.huawei.hianalytics.visual.autocollect.instrument;

import android.content.Intent;
import com.huawei.hianalytics.core.log.HiLog;
import com.huawei.hianalytics.visual.k0;

/* loaded from: classes8.dex */
public class ActivityInstrumentation {
    public static void instrumentStartActivity(Intent intent) {
        if (intent == null) {
            return;
        }
        try {
            if (!intent.hasExtra("$sessionid") && !intent.hasExtra("$sessiontime")) {
                intent.putExtra("$sessionid", k0.b.f3932a.f3930a);
                intent.putExtra("$sessiontime", k0.b.f3932a.b);
            }
        } catch (Exception unused) {
            HiLog.e("ActivityInstrumentation", "instrumentStartActivity failed");
        }
    }
}
