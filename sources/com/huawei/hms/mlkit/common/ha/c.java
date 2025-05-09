package com.huawei.hms.mlkit.common.ha;

import android.content.Context;
import android.util.Log;
import com.huawei.hms.hatool.HmsHiAnalyticsUtils;
import com.huawei.hms.support.hianalytics.HiAnalyticsUtils;
import com.huawei.hms.utils.HMSBIInitializer;
import java.util.LinkedHashMap;
import java.util.List;

/* loaded from: classes4.dex */
public class c {

    /* renamed from: a, reason: collision with root package name */
    public static volatile c f5047a = new c();

    public boolean a(Context context, List<String> list) {
        if (context == null || list == null || list.isEmpty()) {
            b.a("HaLogExecutor", "HianalyticsLogExecutor executeInitialize failed, context or haServiceUrls is empty.");
            return false;
        }
        Log.i("HaLogExecutor", "start initBI");
        HMSBIInitializer.getInstance(context.getApplicationContext()).initBI();
        Log.i("HaLogExecutor", "end initBI");
        HiAnalyticsUtils.getInstance().enableLog();
        b.b("HaLogExecutor", "HianalyticsLogExecutor executeInitialize init success");
        return true;
    }

    public void a(Context context, int i, String str, LinkedHashMap<String, String> linkedHashMap) {
        b.b("HaLogExecutor", "type " + i + " mapValue:" + linkedHashMap);
        HiAnalyticsUtils.getInstance().onNewEvent(context, str, linkedHashMap, i);
    }

    public void a() {
        HmsHiAnalyticsUtils.onReport();
    }
}
