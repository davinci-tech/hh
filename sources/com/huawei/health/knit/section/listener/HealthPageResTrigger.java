package com.huawei.health.knit.section.listener;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.knit.section.model.MarketingIdInfo;
import com.huawei.health.marketing.api.MarketingApi;
import com.huawei.health.marketing.datatype.MarketingOption;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.utils.ScrollUtil;
import defpackage.eil;
import health.compact.a.Services;

/* loaded from: classes3.dex */
public class HealthPageResTrigger extends BasePageResTrigger {
    private static final int LOAD_FLOATING_BOX_DELAY = 1000;
    private static final String TAG = "PressurePageResTrigger";
    private boolean mIsBootAdapter;
    private boolean mIsFirstEntrySleep;
    private int mPageId;
    private View mScrollableView;

    public HealthPageResTrigger(Context context, int i, MarketingIdInfo marketingIdInfo) {
        super(context, i, marketingIdInfo);
        this.mIsBootAdapter = false;
        this.mIsFirstEntrySleep = true;
    }

    @Override // com.huawei.health.knit.section.listener.BasePageResTrigger, com.huawei.health.knit.section.listener.IPageResTrigger
    public void onPageCreated(final Activity activity, final View view) {
        LogUtil.a(TAG, "onPageCreated ");
        HandlerExecutor.d(new Runnable() { // from class: com.huawei.health.knit.section.listener.HealthPageResTrigger.1
            @Override // java.lang.Runnable
            public void run() {
                HealthPageResTrigger.this.requestMarketingFloatingBox(activity, view);
            }
        }, 1000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestMarketingFloatingBox(Activity activity, View view) {
        this.mPageId = this.mMarketingIdInfo.getmPageId();
        if (view == null) {
            LogUtil.b(TAG, "onPageCreated failed, cause scrollableView is null");
            return;
        }
        LogUtil.a(TAG, "mIsBootAdapter: ", Boolean.valueOf(this.mIsBootAdapter));
        if (!hasGuidePage() || this.mIsBootAdapter || !this.mIsFirstEntrySleep) {
            Context context = this.mContextRef.get();
            if (context == null) {
                LogUtil.h(TAG, "onPageCreated failed, cause context is null");
                return;
            }
            if (!this.mIsLoadFloatingBox) {
                LogUtil.h(TAG, "this res trigger not need load floating box");
                return;
            }
            MarketingApi marketingApi = (MarketingApi) Services.a("FeatureMarketing", MarketingApi.class);
            if (marketingApi != null) {
                marketingApi.requestMarketingResource(new MarketingOption.Builder().setContext(context).setPageId(this.mPageId).build());
            }
            if (context instanceof Activity) {
                ScrollUtil.cKx_(view, ((Activity) context).getWindow().getDecorView(), this.mMarketingIdInfo.getmFloatingBoxId());
            }
        }
        this.mScrollableView = view;
        LogUtil.a(TAG, "mScrollableView: ", view);
    }

    @Override // com.huawei.health.knit.section.listener.BasePageResTrigger, com.huawei.health.knit.section.listener.IPageResTrigger
    public void onPageVisibilityChanged(Activity activity, boolean z, View view) {
        LogUtil.a(TAG, "onPageVisibilityChanged : ", Boolean.valueOf(z));
        if (activity == null || activity.getWindow() == null || activity.getWindow().getDecorView() == null) {
            return;
        }
        eil.alV_(activity.getWindow().getDecorView(), z, this.mMarketingIdInfo.getmFloatingBoxId(), null);
    }

    public void setBootAdapter(boolean z) {
        this.mIsBootAdapter = z;
        LogUtil.a(TAG, "onPageCreated ");
        Context context = this.mContextRef.get();
        if (!this.mIsBootAdapter || context == null) {
            return;
        }
        MarketingApi marketingApi = (MarketingApi) Services.a("FeatureMarketing", MarketingApi.class);
        if (marketingApi != null) {
            marketingApi.requestMarketingResource(new MarketingOption.Builder().setContext(context).setPageId(this.mPageId).build());
        }
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            View view = this.mScrollableView;
            if (view == null) {
                return;
            }
            ScrollUtil.cKx_(view, activity.getWindow().getDecorView(), this.mMarketingIdInfo.getmFloatingBoxId());
        }
    }

    public void setIsFirstEntryNoDataSleep(boolean z) {
        this.mIsFirstEntrySleep = z;
    }

    private boolean hasGuidePage() {
        return this.mPageId == 310;
    }
}
