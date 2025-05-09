package com.huawei.ui.homehealth.runcard.trackfragments;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.knit.section.listener.SportPageResTrigger;
import com.huawei.health.knit.section.model.MarketingIdInfo;
import com.huawei.health.marketing.api.MarketingApi;
import com.huawei.health.marketing.datatype.MarketingOption;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.utils.ScrollUtil;
import defpackage.ehu;
import defpackage.nmh;
import health.compact.a.Services;
import java.lang.ref.WeakReference;

/* loaded from: classes6.dex */
public class SportTabPageResTrigger extends SportPageResTrigger {
    private static final String TAG = "SportTabPageResTrigger";
    private boolean mIsOnPageCreatedCalled;
    private d mObserver;

    public SportTabPageResTrigger(Context context, int i, MarketingIdInfo marketingIdInfo) {
        super(context, i, marketingIdInfo);
        this.mIsOnPageCreatedCalled = false;
    }

    @Override // com.huawei.health.knit.section.listener.SportPageResTrigger, com.huawei.health.knit.section.listener.BasePageResTrigger, com.huawei.health.knit.section.listener.IPageResTrigger
    public void onPageCreated(Activity activity, View view) {
        if (activity == null) {
            LogUtil.h(TAG, "onPageCreated failed, cause context is null");
            return;
        }
        if (view == null) {
            LogUtil.b(TAG, "onPageCreated failed, cause scrollableView is null");
            return;
        }
        if (this.mIsOnPageCreatedCalled) {
            LogUtil.h(TAG, "onPageCreated has been called");
            return;
        }
        LogUtil.a(TAG, "onPageCreated: ", Integer.valueOf(this.mResPosId));
        nmh.a(ehu.f12020a);
        d dVar = new d(this, activity, view);
        this.mObserver = dVar;
        ObserverManagerUtil.d(dVar, "UPDATE_AD_SHOW");
        initMarketing(activity, view);
        this.mIsOnPageCreatedCalled = true;
    }

    @Override // com.huawei.health.knit.section.listener.SportPageResTrigger, com.huawei.health.knit.section.listener.BasePageResTrigger, com.huawei.health.knit.section.listener.IPageResTrigger
    public void onPageVisibilityChanged(Activity activity, boolean z, View view) {
        if (z && !this.mIsOnPageCreatedCalled) {
            LogUtil.a(TAG, "has not called onPageCreated yet, call it now");
            onPageCreated(activity, view);
        }
        super.onPageVisibilityChanged(activity, z, view);
    }

    @Override // com.huawei.health.knit.section.listener.BasePageResTrigger, com.huawei.health.knit.section.listener.IPageResTrigger
    public void onDestroy(Activity activity) {
        d dVar = this.mObserver;
        if (dVar != null) {
            try {
                ObserverManagerUtil.e(dVar, "UPDATE_AD_SHOW");
            } catch (IllegalStateException unused) {
                LogUtil.b(TAG, "unregisterObserver exception");
            }
            this.mObserver = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initMarketing(Activity activity, View view) {
        if (SportEntranceFragment.a()) {
            LogUtil.h(TAG, "initMarketing isShowAd == true");
            return;
        }
        int i = this.mMarketingIdInfo.getmFloatingBoxId();
        LogUtil.a(TAG, "initMarketing posIdId: ", Integer.valueOf(this.mResPosId), " floatingBoxId: ", Integer.valueOf(i));
        nmh.c(i);
        int i2 = this.mMarketingIdInfo.getmPageId();
        MarketingApi marketingApi = (MarketingApi) Services.a("FeatureMarketing", MarketingApi.class);
        if (marketingApi != null) {
            marketingApi.requestMarketingResource(new MarketingOption.Builder().setContext(activity).setPageId(i2).build());
        }
        LogUtil.a(TAG, "sport setScreenTouchListening: ", Integer.valueOf(i));
        ScrollUtil.cKx_(view, activity.getWindow().getDecorView(), i);
    }

    static class d implements Observer {
        private WeakReference<Activity> c;
        private WeakReference<View> d;
        private WeakReference<SportTabPageResTrigger> e;

        public d(SportTabPageResTrigger sportTabPageResTrigger, Activity activity, View view) {
            this.e = new WeakReference<>(sportTabPageResTrigger);
            this.c = new WeakReference<>(activity);
            this.d = new WeakReference<>(view);
        }

        @Override // com.huawei.haf.design.pattern.Observer
        public void notify(String str, Object... objArr) {
            LogUtil.a(SportTabPageResTrigger.TAG, "SportUpdateShowAdObserver notify, label: ", str);
            if (!"UPDATE_AD_SHOW".equals(str) || this.e == null || this.c == null || this.d == null) {
                return;
            }
            HandlerExecutor.d(new Runnable() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.SportTabPageResTrigger.d.1
                @Override // java.lang.Runnable
                public void run() {
                    SportTabPageResTrigger sportTabPageResTrigger = (SportTabPageResTrigger) d.this.e.get();
                    Activity activity = (Activity) d.this.c.get();
                    View view = (View) d.this.d.get();
                    if (sportTabPageResTrigger == null || activity == null || view == null) {
                        return;
                    }
                    sportTabPageResTrigger.initMarketing(activity, view);
                }
            }, 50L);
        }
    }
}
