package com.huawei.health.knit.section.listener;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import com.huawei.health.knit.section.model.MarketingIdInfo;
import com.huawei.health.marketing.api.MarketingApi;
import com.huawei.health.marketing.datatype.MarketingOption;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.utils.ScrollUtil;
import defpackage.ehu;
import defpackage.eil;
import defpackage.nmh;
import health.compact.a.Services;

/* loaded from: classes3.dex */
public class SportPageResTrigger extends BasePageResTrigger {
    private static final String TAG = "SportPageResTrigger";

    @Override // com.huawei.health.knit.section.listener.BasePageResTrigger, com.huawei.health.knit.section.listener.IPageResTrigger
    public int getPageCategory() {
        return 1;
    }

    public SportPageResTrigger(Context context, int i, MarketingIdInfo marketingIdInfo) {
        super(context, i, marketingIdInfo);
    }

    @Override // com.huawei.health.knit.section.listener.BasePageResTrigger, com.huawei.health.knit.section.listener.IPageResTrigger
    public void onPageCreated(Activity activity, View view) {
        int i = this.mMarketingIdInfo.getmPageId();
        if (activity == null) {
            LogUtil.h(TAG, "onPageCreated failed, cause context is null");
            return;
        }
        if (view == null) {
            LogUtil.b(TAG, "onPageCreated failed, cause scrollableView is null");
            return;
        }
        nmh.a(ehu.f12020a);
        MarketingApi marketingApi = (MarketingApi) Services.a("FeatureMarketing", MarketingApi.class);
        if (marketingApi != null) {
            marketingApi.requestMarketingResource(new MarketingOption.Builder().setContext(activity).setPageId(i).build());
        }
        int i2 = this.mMarketingIdInfo.getmFloatingBoxId();
        LogUtil.a(TAG, "sport setScreenTouchListening: ", Integer.valueOf(i2));
        ScrollUtil.cKx_(view, activity.getWindow().getDecorView(), i2);
    }

    @Override // com.huawei.health.knit.section.listener.BasePageResTrigger, com.huawei.health.knit.section.listener.IPageResTrigger
    public void onPageVisibilityChanged(Activity activity, boolean z, View view) {
        if (activity == null) {
            return;
        }
        LogUtil.a(TAG, "onPageVisibilityChanged, positionId: ", Integer.valueOf(this.mResPosId), " isVisible: ", Boolean.valueOf(z));
        if (activity.getWindow() == null || activity.getWindow().getDecorView() == null) {
            return;
        }
        int i = this.mMarketingIdInfo.getmFloatingBoxId();
        LogUtil.a(TAG, "floatingBoxId: ", Integer.valueOf(i));
        eil.alV_(activity.getWindow().getDecorView(), z, i, "SportPage");
    }
}
