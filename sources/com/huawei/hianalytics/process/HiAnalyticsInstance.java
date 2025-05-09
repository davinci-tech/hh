package com.huawei.hianalytics.process;

import android.content.Context;
import com.huawei.hianalytics.a1;
import com.huawei.hianalytics.core.log.HiLog;
import com.huawei.hianalytics.d1;
import com.huawei.hianalytics.framework.config.ICallback;
import com.huawei.hianalytics.k;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public interface HiAnalyticsInstance {
    void clearData();

    void disableAutoReport();

    String getCollectUrl();

    String getOAID(int i);

    String getUDID(int i);

    String getUUID(int i);

    void newInstanceUUID();

    @Deprecated
    void onBackground(long j);

    void onEvent(int i, String str, LinkedHashMap<String, String> linkedHashMap);

    void onEvent(int i, String str, LinkedHashMap<String, String> linkedHashMap, LinkedHashMap<String, String> linkedHashMap2, LinkedHashMap<String, String> linkedHashMap3);

    void onEvent(int i, String str, JSONObject jSONObject);

    void onEvent(int i, List<HaEvent> list);

    @Deprecated
    void onEvent(Context context, String str, String str2);

    void onEvent(String str, LinkedHashMap<String, String> linkedHashMap);

    void onEventSync(int i, String str, JSONObject jSONObject);

    @Deprecated
    void onForeground(long j);

    @Deprecated
    void onPause(Context context);

    @Deprecated
    void onPause(Context context, LinkedHashMap<String, String> linkedHashMap);

    @Deprecated
    void onPause(String str, LinkedHashMap<String, String> linkedHashMap);

    void onReport(int i);

    @Deprecated
    void onReport(Context context, int i);

    @Deprecated
    void onResume(Context context);

    @Deprecated
    void onResume(Context context, LinkedHashMap<String, String> linkedHashMap);

    @Deprecated
    void onResume(String str, LinkedHashMap<String, String> linkedHashMap);

    void onStreamEvent(int i, String str, LinkedHashMap<String, String> linkedHashMap);

    void onStreamEvent(int i, String str, LinkedHashMap<String, String> linkedHashMap, LinkedHashMap<String, String> linkedHashMap2, LinkedHashMap<String, String> linkedHashMap3);

    void onStreamEvent(int i, String str, JSONObject jSONObject);

    void refresh(int i, HiAnalyticsConfig hiAnalyticsConfig);

    void setAccountBrandId(String str);

    void setAppBrandId(String str);

    void setAppid(String str);

    void setCallback(ICallback iCallback);

    void setCommonProp(int i, Map<String, String> map);

    void setHandsetManufacturer(String str);

    void setHansetBrandId(String str);

    void setOAID(int i, String str);

    void setOAIDTrackingFlag(int i, boolean z);

    void setUpid(int i, String str);

    /* loaded from: classes.dex */
    public static final class Builder {
        public static final String TAG = "HABuilder";
        public Context mContext;
        public HiAnalyticsConfig maintConf = null;
        public HiAnalyticsConfig operConf = null;

        private void setConf(d1 d1Var) {
            HiAnalyticsConfig hiAnalyticsConfig = this.operConf;
            if (hiAnalyticsConfig == null) {
                d1Var.b((HiAnalyticsConfig) null);
            } else {
                d1Var.f26a.b = new a1(hiAnalyticsConfig.f3894a);
            }
            HiAnalyticsConfig hiAnalyticsConfig2 = this.maintConf;
            if (hiAnalyticsConfig2 == null) {
                d1Var.a((HiAnalyticsConfig) null);
            } else {
                d1Var.f26a.f3850a = new a1(hiAnalyticsConfig2.f3894a);
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:104:0x0279  */
        /* JADX WARN: Removed duplicated region for block: B:82:0x01cb  */
        /* JADX WARN: Removed duplicated region for block: B:85:0x01e6  */
        /* JADX WARN: Removed duplicated region for block: B:88:0x028a  */
        /* JADX WARN: Removed duplicated region for block: B:93:0x02ba  */
        /* JADX WARN: Removed duplicated region for block: B:95:? A[RETURN, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:96:0x01ed  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public com.huawei.hianalytics.process.HiAnalyticsInstance create(java.lang.String r12, boolean r13) {
            /*
                Method dump skipped, instructions count: 700
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.huawei.hianalytics.process.HiAnalyticsInstance.Builder.create(java.lang.String, boolean):com.huawei.hianalytics.process.HiAnalyticsInstance");
        }

        @Deprecated
        public Builder setDiffConf(HiAnalyticsConfig hiAnalyticsConfig) {
            return this;
        }

        @Deprecated
        public Builder setPreConfig(HiAnalyticsConfig hiAnalyticsConfig) {
            return this;
        }

        public Builder setOperConf(HiAnalyticsConfig hiAnalyticsConfig) {
            this.operConf = hiAnalyticsConfig;
            return this;
        }

        public Builder setMaintConf(HiAnalyticsConfig hiAnalyticsConfig) {
            this.maintConf = hiAnalyticsConfig;
            return this;
        }

        public HiAnalyticsInstance refresh(String str) {
            d1 a2 = k.a().a(str);
            if (a2 != null) {
                a2.refresh(1, this.maintConf);
                a2.refresh(0, this.operConf);
                return a2;
            }
            HiLog.w(TAG, "refresh config failed, instance is null. tag: " + str);
            return create(str);
        }

        public HiAnalyticsInstance create(String str) {
            return create(str, false);
        }

        public Builder(Context context) {
            if (context != null) {
                this.mContext = context.getApplicationContext();
            }
        }
    }
}
