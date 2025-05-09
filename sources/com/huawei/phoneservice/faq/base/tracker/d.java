package com.huawei.phoneservice.faq.base.tracker;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hianalytics.process.HiAnalyticsConfig;
import com.huawei.hianalytics.process.HiAnalyticsInstance;
import com.huawei.hianalytics.process.HiAnalyticsManager;
import com.huawei.hianalytics.util.HiAnalyticTools;
import com.huawei.phoneservice.faq.base.constants.FaqConstants;
import com.huawei.phoneservice.faq.base.constants.TrackConstants$Events;
import com.huawei.phoneservice.faq.base.util.i;
import com.huawei.phoneservice.faq.base.util.j;
import com.huawei.phoneservice.faq.base.util.l;
import java.lang.ref.WeakReference;
import java.util.LinkedHashMap;

/* loaded from: classes5.dex */
public class d {

    /* renamed from: a, reason: collision with root package name */
    private static boolean f8238a;
    private static WeakReference<Context> b;
    private static boolean c;
    private static String e;

    public static class e {
        private LinkedHashMap<String, String> d = new LinkedHashMap<>();

        public e d(String str) {
            if (!l.e(str)) {
                this.d.put("url", str);
            }
            return this;
        }

        public e e(String str) {
            this.d.put("type", str);
            return this;
        }

        public e b(String str) {
            if (!l.e(str)) {
                this.d.put("title", str);
            }
            return this;
        }

        public e e() {
            this.d.put("result", "succeed");
            return this;
        }

        public e c(String str) {
            if (!l.e(str)) {
                this.d.put("operation", str);
            }
            return this;
        }

        public e a(String str) {
            if (!l.e(str)) {
                this.d.put("className", str);
            }
            return this;
        }

        public LinkedHashMap<String, String> b() {
            LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(this.d);
            if (linkedHashMap.isEmpty()) {
                i.c("HiAnalyticsUtils", "map is null while building map");
            }
            return linkedHashMap;
        }

        public e d(String str, String str2) {
            if (!l.e(str) && !l.e(str2)) {
                this.d.put(str, str2);
            }
            return this;
        }
    }

    public static void a(String str, String str2, String str3) {
        d(TrackConstants$Events.SDK_API, new e().e("sdk_webapi").d("resCode", str).d("reason", str2).d(str3).b());
    }

    public static void d(String str, LinkedHashMap<String, String> linkedHashMap) {
        String str2;
        if (c()) {
            str2 = "FAQ_DISABLE_HA_REPORT is set to true. Do not report.";
        } else {
            WeakReference<Context> weakReference = b;
            if (weakReference == null || weakReference.get() == null) {
                str2 = "mContext is null, please init() before trackEvent()";
            } else if (linkedHashMap == null || linkedHashMap.isEmpty()) {
                str2 = "param:map is null";
            } else {
                if (!l.e(str)) {
                    HiAnalyticsInstance instanceByTag = HiAnalyticsManager.getInstanceByTag("hiCare");
                    if (instanceByTag == null) {
                        return;
                    }
                    linkedHashMap.put("hostApk", e);
                    instanceByTag.onStreamEvent(1, str, linkedHashMap);
                    if (!f8238a || c) {
                        instanceByTag.onReport(1);
                        i.d("HiAnalyticsUtils", "onReport");
                        return;
                    }
                    return;
                }
                str2 = "Cannot get type";
            }
        }
        i.c("HiAnalyticsUtils", str2);
    }

    public static boolean b() {
        return HiAnalyticsManager.getInstanceByTag("hiCare") != null;
    }

    public static boolean c() {
        return "true".equals(j.c().getSdk(FaqConstants.FAQ_DISABLE_HA_REPORT));
    }

    public static void b(Context context, String str, boolean z, boolean z2) {
        if (c()) {
            i.c("HiAnalyticsUtils", "FAQ_DISABLE_HA_REPORT is set to true. Do not init.");
            return;
        }
        f8238a = z;
        c = z2;
        if (!z || z2) {
            HiAnalyticTools.enableLog(context);
        }
        if (!TextUtils.isEmpty(str) && !b()) {
            new HiAnalyticsInstance.Builder(context).setMaintConf(new HiAnalyticsConfig.Builder().setCollectURL(str).build()).create("hiCare");
        }
        i.b("HiAnalyticsUtils", "hiAnalyticUrl:%s", str);
        e = context.getPackageName();
        b = new WeakReference<>(context);
    }
}
