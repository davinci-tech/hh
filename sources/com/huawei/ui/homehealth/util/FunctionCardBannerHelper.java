package com.huawei.ui.homehealth.util;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import com.huawei.health.marketing.api.MarketingApi;
import com.huawei.health.marketing.datatype.ResourceResultInfo;
import com.huawei.health.marketing.views.HomePageRecommendLayout;
import com.huawei.hmf.tasks.OnSuccessListener;
import defpackage.koq;
import health.compact.a.LogUtil;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class FunctionCardBannerHelper {
    private final IResponseCallback b;
    private ConcurrentHashMap<String, HomePageRecommendLayout> c = new ConcurrentHashMap<>();
    private final WeakReference<Context> d;

    public interface IResponseCallback {
        public static final int RESULT_FAIL = -1;
        public static final int RESULT_SUCCESS = 0;

        void onResponse(int i);
    }

    public FunctionCardBannerHelper(Context context, IResponseCallback iResponseCallback) {
        this.d = new WeakReference<>(context);
        this.b = iResponseCallback;
    }

    public View diX_(String str, int i) {
        LogUtil.c("FunctionCardBannerHelper", "getting bottom banner view... cardID:" + str, " cardPosition:", Integer.valueOf(i));
        ConcurrentHashMap<String, HomePageRecommendLayout> concurrentHashMap = this.c;
        if (concurrentHashMap == null || concurrentHashMap.size() == 0) {
            LogUtil.c("FunctionCardBannerHelper", "bottom banner view list is empty, return null view");
            return null;
        }
        HomePageRecommendLayout homePageRecommendLayout = this.c.get(str);
        if (homePageRecommendLayout != null) {
            homePageRecommendLayout.setCardPosition(i);
        }
        return homePageRecommendLayout;
    }

    public void c(List<String> list) {
        ReleaseLogUtil.e("R_FunctionCardBannerHelper", "start requesting data...");
        final MarketingApi marketingApi = (MarketingApi) Services.a("FeatureMarketing", MarketingApi.class);
        if (marketingApi == null) {
            ReleaseLogUtil.c("R_FunctionCardBannerHelper", "get marketingApi failed");
            this.b.onResponse(-1);
        } else {
            marketingApi.getResourceResultInfo(4161, b(list)).addOnSuccessListener(new OnSuccessListener<Map<Integer, ResourceResultInfo>>() { // from class: com.huawei.ui.homehealth.util.FunctionCardBannerHelper.1
                @Override // com.huawei.hmf.tasks.OnSuccessListener
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(Map<Integer, ResourceResultInfo> map) {
                    if (map == null) {
                        ReleaseLogUtil.c("R_FunctionCardBannerHelper", "request cloud data failed, marketingResponse is null");
                        FunctionCardBannerHelper.this.b.onResponse(-1);
                        return;
                    }
                    if (map.isEmpty()) {
                        ReleaseLogUtil.d("R_FunctionCardBannerHelper", "marketingResponse is empty");
                        FunctionCardBannerHelper.this.a();
                        return;
                    }
                    ReleaseLogUtil.e("R_FunctionCardBannerHelper", "request marketing resource onSuccess");
                    Map<Integer, ResourceResultInfo> filterMarketingRules = marketingApi.filterMarketingRules(map);
                    if (filterMarketingRules != null && !filterMarketingRules.isEmpty()) {
                        Context context = (Context) FunctionCardBannerHelper.this.d.get();
                        if (context == null) {
                            ReleaseLogUtil.d("R_FunctionCardBannerHelper", "context has been recycled, no need to instantiate views...");
                            return;
                        }
                        List<View> marketingViewList = marketingApi.getMarketingViewList(context, filterMarketingRules);
                        if (marketingViewList != null && !marketingViewList.isEmpty()) {
                            Map e = FunctionCardBannerHelper.this.e(marketingViewList);
                            FunctionCardBannerHelper functionCardBannerHelper = FunctionCardBannerHelper.this;
                            if (functionCardBannerHelper.d(functionCardBannerHelper.c, e)) {
                                LogUtil.c("FunctionCardBannerHelper", "need to refresh.");
                                FunctionCardBannerHelper.this.c.clear();
                                FunctionCardBannerHelper.this.c.putAll(e);
                                FunctionCardBannerHelper.this.b.onResponse(0);
                                return;
                            }
                            return;
                        }
                        ReleaseLogUtil.d("R_FunctionCardBannerHelper", "viewList is null or empty");
                        FunctionCardBannerHelper.this.a();
                        return;
                    }
                    ReleaseLogUtil.c("FunctionCardBannerHelper", "filterResultInfoMap is invalid");
                    FunctionCardBannerHelper.this.a();
                }
            });
        }
    }

    private String b(List<String> list) {
        if (koq.b(list)) {
            ReleaseLogUtil.d("R_FunctionCardBannerHelper", "getMarketingExtendParam cardLists is empty");
            return null;
        }
        ReleaseLogUtil.e("R_FunctionCardBannerHelper", "getMarketingExtendParam cardLists:", list.toString());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i);
            if (!TextUtils.isEmpty(str)) {
                sb.append(str);
                if (i < list.size() - 1) {
                    sb.append(",");
                }
            }
        }
        HashMap hashMap = new HashMap(1);
        hashMap.put("HomePageCardSort", sb.toString());
        HashMap hashMap2 = new HashMap(16);
        hashMap2.put(String.valueOf(4161), new JSONObject(hashMap).toString());
        JSONObject jSONObject = new JSONObject(hashMap2);
        LogUtil.c("FunctionCardBannerHelper", "extendParamJson : ", jSONObject.toString());
        return jSONObject.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        if (this.b == null) {
            LogUtil.a("FunctionCardBannerHelper", "processNoData callback is null.");
        } else if (!this.c.isEmpty()) {
            this.c.clear();
            this.b.onResponse(0);
        } else {
            this.b.onResponse(-1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean d(Map<String, HomePageRecommendLayout> map, Map<String, HomePageRecommendLayout> map2) {
        if (map2 == null) {
            LogUtil.c("FunctionCardBannerHelper", "layoutMap == null.");
            return false;
        }
        boolean z = !map2.equals(map);
        LogUtil.c("FunctionCardBannerHelper", "isSameLayouts = ", Boolean.valueOf(z));
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Map<String, HomePageRecommendLayout> e(List<View> list) {
        HashMap hashMap = new HashMap();
        for (View view : list) {
            if (view instanceof HomePageRecommendLayout) {
                HomePageRecommendLayout homePageRecommendLayout = (HomePageRecommendLayout) view;
                if (!hashMap.containsKey(homePageRecommendLayout.getRecommendCard())) {
                    hashMap.put(homePageRecommendLayout.getRecommendCard(), homePageRecommendLayout);
                }
            } else {
                LogUtil.e("FunctionCardBannerHelper", "view Type is INCORRECT, we want HomePageRecommendLayout, but got " + view.getClass().getSimpleName());
            }
        }
        return hashMap;
    }
}
