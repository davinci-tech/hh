package com.huawei.health.utils;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.google.gson.Gson;
import com.huawei.health.R;
import com.huawei.health.marketing.api.MarketingApi;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.ResourceResultInfo;
import com.huawei.health.marketing.datatype.templates.NavigationTemplate;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.scrollview.HealthBottomView;
import defpackage.ixx;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class NavigationHelper {

    /* renamed from: a, reason: collision with root package name */
    private Context f3500a;
    private ResourceBriefInfo b;
    private boolean c = false;
    private String d;
    private long e;

    public NavigationHelper(Context context) {
        this.f3500a = context;
    }

    public boolean d() {
        return this.c;
    }

    public void b(final HealthBottomView healthBottomView) {
        if (this.f3500a == null || healthBottomView == null) {
            LogUtil.h("NavigationHelper", "mContext or tabs is null");
            return;
        }
        final MarketingApi marketingApi = (MarketingApi) Services.a("FeatureMarketing", MarketingApi.class);
        if (marketingApi != null) {
            this.c = false;
            marketingApi.getResourceResultInfo(6001).addOnSuccessListener(new OnSuccessListener<Map<Integer, ResourceResultInfo>>() { // from class: com.huawei.health.utils.NavigationHelper.5
                @Override // com.huawei.hmf.tasks.OnSuccessListener
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(Map<Integer, ResourceResultInfo> map) {
                    ResourceBriefInfo e = NavigationHelper.this.e(marketingApi.filterMarketingRules(map));
                    if (e != null) {
                        NavigationHelper.this.c(e, healthBottomView);
                    } else {
                        LogUtil.a("NavigationHelper", "resourceBriefInfo is null");
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(ResourceBriefInfo resourceBriefInfo, final HealthBottomView healthBottomView) {
        Context context = this.f3500a;
        if (context == null || resourceBriefInfo == null || healthBottomView == null) {
            LogUtil.h("NavigationHelper", "context or resourceBriefInfo or tabs is null");
            return;
        }
        if (!(context instanceof Activity)) {
            LogUtil.h("NavigationHelper", "mContext is not Activity");
            return;
        }
        final Activity activity = (Activity) context;
        this.d = resourceBriefInfo.getResourceId();
        this.b = resourceBriefInfo;
        final int contentType = resourceBriefInfo.getContentType();
        final String picture = ((NavigationTemplate) new Gson().fromJson(CommonUtil.z(resourceBriefInfo.getContent().getContent()), NavigationTemplate.class)).getPicture();
        if (TextUtils.isEmpty(picture)) {
            LogUtil.a("NavigationHelper", "imageUrl is null");
        } else {
            healthBottomView.post(new Runnable() { // from class: com.huawei.health.utils.NavigationHelper.3
                @Override // java.lang.Runnable
                public void run() {
                    int i = contentType;
                    if (i == 26) {
                        NavigationHelper.this.aRc_(activity);
                        healthBottomView.e(picture, 2, true);
                    } else if (i == 25) {
                        NavigationHelper.this.aRb_(activity);
                        healthBottomView.e(picture, 2, false);
                    } else {
                        NavigationHelper.this.aRb_(activity);
                        healthBottomView.a(R.string._2130839532_res_0x7f0207ec, picture, 2, false);
                    }
                    NavigationHelper.this.e = System.currentTimeMillis();
                    NavigationHelper.this.c(1);
                    NavigationHelper.this.c = true;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ResourceBriefInfo e(Map<Integer, ResourceResultInfo> map) {
        if (map == null) {
            LogUtil.a("NavigationHelper", "resourceResultInfo is null");
            return null;
        }
        ResourceResultInfo resourceResultInfo = map.get(6001);
        if (resourceResultInfo == null) {
            LogUtil.a("NavigationHelper", "resultInfo is null");
            return null;
        }
        List<ResourceBriefInfo> resources = resourceResultInfo.getResources();
        if (resources == null || resources.isEmpty()) {
            return null;
        }
        Collections.sort(resources, new Comparator<ResourceBriefInfo>() { // from class: com.huawei.health.utils.NavigationHelper.2
            @Override // java.util.Comparator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public int compare(ResourceBriefInfo resourceBriefInfo, ResourceBriefInfo resourceBriefInfo2) {
                return resourceBriefInfo2.getPriority() - resourceBriefInfo.getPriority();
            }
        });
        return resources.get(0);
    }

    public void c(int i) {
        if (this.f3500a == null || TextUtils.isEmpty(this.d)) {
            LogUtil.h("NavigationHelper", "mContext or mResourceId is null");
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("event", Integer.valueOf(i));
        hashMap.put("resourceId", this.d);
        hashMap.put("resourcePositionId", 6001);
        ResourceBriefInfo resourceBriefInfo = this.b;
        if (resourceBriefInfo != null) {
            hashMap.put("resourceName", resourceBriefInfo.getResourceName());
            hashMap.put("smartRecommend", Boolean.valueOf(this.b.getSmartRecommend()));
            hashMap.put("algId", "");
        }
        hashMap.put("pullOrder", 1);
        if (i == 2) {
            hashMap.put("durationTime", Long.valueOf(System.currentTimeMillis() - this.e));
            this.e = System.currentTimeMillis();
        }
        ixx.d().d(this.f3500a, AnalyticsValue.MARKETING_RESOURCE.value(), hashMap, 0);
    }

    public void aRc_(Activity activity) {
        if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
            LogUtil.h("NavigationHelper", "activity is null/isFinishing/isDestroyed");
            return;
        }
        LinearLayout linearLayout = (LinearLayout) activity.findViewById(R.id.hw_main_tabs_layout);
        View findViewById = activity.findViewById(R.id.extend_pic_padding);
        if (findViewById == null) {
            LogUtil.h("NavigationHelper", "enableTabsExtendMode paddingView is null");
            return;
        }
        findViewById.setVisibility(0);
        if (linearLayout.getLayoutParams() instanceof ViewGroup.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) linearLayout.getLayoutParams();
            layoutParams.topMargin = -nsn.c(activity, 4.0f);
            linearLayout.setLayoutParams(layoutParams);
        }
    }

    public void aRb_(Activity activity) {
        if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
            LogUtil.h("NavigationHelper", "activity is null/isFinishing/isDestroyed");
            return;
        }
        LinearLayout linearLayout = (LinearLayout) activity.findViewById(R.id.hw_main_tabs_layout);
        View findViewById = activity.findViewById(R.id.extend_pic_padding);
        if (findViewById == null) {
            LogUtil.h("NavigationHelper", "disableTabsExtendMode paddingView is null");
            return;
        }
        findViewById.setVisibility(8);
        if (linearLayout.getLayoutParams() instanceof ViewGroup.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) linearLayout.getLayoutParams();
            layoutParams.topMargin = 0;
            linearLayout.setLayoutParams(layoutParams);
        }
    }
}
