package com.huawei.health.marketing.utils;

import android.graphics.Rect;
import android.text.TextUtils;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.views.ColumnLinearLayout;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import defpackage.gge;
import defpackage.ixx;
import defpackage.jae;
import defpackage.koq;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class MarketingBiUtils {
    public static void d(int i, ResourceBriefInfo resourceBriefInfo) {
        if (resourceBriefInfo == null) {
            LogUtil.a("MarketingBiUtils", "resourceBriefInfo is null. return.");
            return;
        }
        LogUtil.a("MarketingBiUtils", "set view event Result. PositionId: ", Integer.valueOf(i), "; resourceName: ", resourceBriefInfo.getResourceName());
        HashMap hashMap = new HashMap();
        hashMap.put("event", 1);
        hashMap.put("resourceId", resourceBriefInfo.getResourceId());
        hashMap.put("resourcePositionId", Integer.valueOf(i));
        hashMap.put("resourceName", resourceBriefInfo.getResourceName());
        hashMap.put("pullOrder", 1);
        hashMap.put("algId", "");
        hashMap.put("smartRecommend", Boolean.valueOf(resourceBriefInfo.getSmartRecommend()));
        ixx.d().d(BaseApplication.e(), AnalyticsValue.MARKETING_RESOURCE.value(), hashMap, 0);
    }

    public static void b(int i, ResourceBriefInfo resourceBriefInfo, long j) {
        if (resourceBriefInfo == null) {
            LogUtil.a("MarketingBiUtils", "resourceBriefInfo is null. return.");
            return;
        }
        LogUtil.a("MarketingBiUtils", "set click event Result. PositionId: ", Integer.valueOf(i), "; resourceName: ", resourceBriefInfo.getResourceName());
        HashMap hashMap = new HashMap();
        hashMap.put("event", 2);
        hashMap.put("resourceId", resourceBriefInfo.getResourceId());
        hashMap.put("resourcePositionId", Integer.valueOf(i));
        hashMap.put("resourceName", resourceBriefInfo.getResourceName());
        hashMap.put("durationTime", Long.valueOf(System.currentTimeMillis() - j));
        hashMap.put("pullOrder", 1);
        hashMap.put("algId", "");
        hashMap.put("smartRecommend", Boolean.valueOf(resourceBriefInfo.getSmartRecommend()));
        ixx.d().d(BaseApplication.e(), AnalyticsValue.MARKETING_RESOURCE.value(), hashMap, 0);
    }

    public static void d(int i, int i2, ResourceBriefInfo resourceBriefInfo, Map<String, Object> map) {
        if (resourceBriefInfo == null) {
            LogUtil.a("MarketingBiUtils", "resourceBriefInfo is null. return.");
            return;
        }
        LogUtil.a("MarketingBiUtils", "set view event Result. PositionId: ", Integer.valueOf(i2), "; resourceName: ", resourceBriefInfo.getResourceName());
        HashMap hashMap = new HashMap();
        hashMap.put("event", Integer.valueOf(i));
        hashMap.put("resourceId", resourceBriefInfo.getResourceId());
        hashMap.put("resourcePositionId", Integer.valueOf(i2));
        hashMap.put("resourceName", resourceBriefInfo.getResourceName());
        hashMap.put("pullOrder", 1);
        hashMap.put("algId", "");
        hashMap.put("smartRecommend", Boolean.valueOf(resourceBriefInfo.getSmartRecommend()));
        if (map != null && map.size() > 0) {
            hashMap.putAll(map);
        }
        a(hashMap, resourceBriefInfo);
    }

    public static boolean alP_(View view) {
        if (view != null && view.getVisibility() == 0) {
            int height = view.getHeight();
            int width = view.getWidth();
            Rect rect = new Rect();
            if (view.getLocalVisibleRect(rect) && ColumnLinearLayout.aoN_(rect, height, width)) {
                return true;
            }
        }
        return false;
    }

    public static void c(int i, ResourceBriefInfo resourceBriefInfo, long j) {
        if (resourceBriefInfo == null) {
            LogUtil.a("MarketingBiUtils", "resourceBriefInfo is null. return.");
            return;
        }
        LogUtil.a("MarketingBiUtils", "set close event Result. PositionId: ", Integer.valueOf(i), "; resourceName: ", resourceBriefInfo.getResourceName());
        HashMap hashMap = new HashMap();
        hashMap.put("event", 4);
        hashMap.put("resourceId", resourceBriefInfo.getResourceId());
        hashMap.put("resourcePositionId", Integer.valueOf(i));
        hashMap.put("resourceName", resourceBriefInfo.getResourceName());
        hashMap.put("durationTime", Long.valueOf(System.currentTimeMillis() - j));
        hashMap.put("pullOrder", 1);
        hashMap.put("algId", "");
        hashMap.put("smartRecommend", Boolean.valueOf(resourceBriefInfo.getSmartRecommend()));
        a(hashMap, resourceBriefInfo);
    }

    public static void e(int i, String str, int i2) {
        LogUtil.a("MarketingBiUtils", "setFitnessCourseBi sportType:", Integer.valueOf(i), ", entrance:", str, ", position:", Integer.valueOf(i2));
        HashMap hashMap = new HashMap(2);
        hashMap.put(BleConstants.SPORT_TYPE, Integer.valueOf(i));
        hashMap.put("entrance", str);
        hashMap.put("position", Integer.valueOf(i2));
        gge.e("1130015", hashMap);
    }

    public static String d(ResourceBriefInfo resourceBriefInfo) {
        if (resourceBriefInfo == null) {
            LogUtil.h("MarketingBiUtils", "ab_info resourceBriefInfo == null");
            return "noAbTest";
        }
        List<String> strategyIds = resourceBriefInfo.getStrategyIds();
        List<String> bucketIds = resourceBriefInfo.getBucketIds();
        if (koq.b(strategyIds) && koq.b(bucketIds)) {
            return "noAbTest";
        }
        LogUtil.a("MarketingBiUtils", "strategyIds: ", strategyIds, ", bucketIds: ", bucketIds);
        return HiJsonUtil.e(new jae.d(strategyIds, bucketIds));
    }

    public static void a(Map<String, Object> map, ResourceBriefInfo resourceBriefInfo) {
        if (resourceBriefInfo == null) {
            LogUtil.h("MarketingBiUtils", "resourceBriefInfo is null in setAbInfoBiEvent. return.");
        } else {
            ixx.d().c(BaseApplication.e().getApplicationContext(), AnalyticsValue.MARKETING_RESOURCE.value(), map, d(resourceBriefInfo), 0);
        }
    }

    public static void a(Map<String, Object> map, Map<String, String> map2) {
        if (map2 == null || map2.size() <= 0) {
            LogUtil.h("MarketingBiUtils", "addMarketingBiParams marketingBiParam is empty");
            return;
        }
        for (String str : map2.keySet()) {
            if (!TextUtils.isEmpty(map2.get(str))) {
                map.put(str, map2.get(str));
            }
        }
    }

    public static class FitnessOnScrollListener extends RecyclerView.OnScrollListener {
        private String b;
        private int c;
        private int d;
        private boolean e;

        public FitnessOnScrollListener(int i, String str, int i2) {
            this.c = i;
            this.b = str;
            this.d = i2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView recyclerView, int i) {
            int i2;
            super.onScrollStateChanged(recyclerView, i);
            if (i == 0 && this.e && (i2 = this.c) == 22) {
                MarketingBiUtils.e(i2, this.b, this.d);
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView recyclerView, int i, int i2) {
            if (i > 0) {
                this.e = true;
            } else {
                this.e = false;
            }
            super.onScrolled(recyclerView, i, i2);
        }
    }
}
