package com.huawei.healthcloud.plugintrack.ui.presenters;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;
import com.huawei.health.marketrouter.api.MarketRouterApi;
import com.huawei.healthcloud.plugintrack.callback.CommonSingleCallback;
import com.huawei.healthcloud.plugintrack.ui.constraints.DynamicTrackContract;
import com.huawei.healthcloud.plugintrack.ui.presenters.DynamicTrackPresenter;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.Services;

/* loaded from: classes.dex */
public class DynamicTrackPresenter implements DynamicTrackContract.Ipresenter<DynamicTrackContract.Iview> {

    /* renamed from: a, reason: collision with root package name */
    private DynamicTrackContract.Iview f3773a;
    private boolean e = false;
    private boolean b = false;

    public DynamicTrackPresenter(DynamicTrackContract.Iview iview) {
        this.f3773a = iview;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.constraints.DynamicTrackContract.Ipresenter
    public void judgeVipStatus() {
        if (this.f3773a == null) {
            LogUtil.h("Suggestion_DynamicTrackPresenter", "judgeVipStatus: view is mull");
        } else {
            d(new CommonSingleCallback() { // from class: hli
                @Override // com.huawei.healthcloud.plugintrack.callback.CommonSingleCallback
                public final void callback(Object obj) {
                    DynamicTrackPresenter.this.b((Boolean) obj);
                }
            });
        }
    }

    public /* synthetic */ void b(Boolean bool) {
        this.e = bool.booleanValue();
        this.f3773a.judgeVipResult(bool.booleanValue());
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.constraints.DynamicTrackContract.Ipresenter
    public void switchPremiumEdition(boolean z) {
        if (this.f3773a == null) {
            LogUtil.h("Suggestion_DynamicTrackPresenter", "switchPremiumEdition: view is mull");
            return;
        }
        if (z) {
            this.b = false;
            LogUtil.a("Suggestion_DynamicTrackPresenter", "switchPremiumEdition: set mIsNeedJudgeVip = false");
            this.f3773a.judgeVipResult(false);
        } else {
            this.b = true;
            d(new CommonSingleCallback() { // from class: hlf
                @Override // com.huawei.healthcloud.plugintrack.callback.CommonSingleCallback
                public final void callback(Object obj) {
                    DynamicTrackPresenter.this.e((Boolean) obj);
                }
            });
        }
    }

    public /* synthetic */ void e(Boolean bool) {
        this.e = bool.booleanValue();
        this.b = !bool.booleanValue();
        if (bool.booleanValue()) {
            this.f3773a.judgeVipResult(true);
        } else {
            b();
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        LogUtil.a("Suggestion_DynamicTrackPresenter", "onResume: ignore_three_dimensional_track");
    }

    private void b() {
        MarketRouterApi marketRouterApi = (MarketRouterApi) Services.a("FeatureMarketing", MarketRouterApi.class);
        if (marketRouterApi != null) {
            marketRouterApi.router("huaweihealth://huaweihealth.app/openwith?address=com.huawei.health.h5.vip?isImmerse&h5pro=true&urlType=4&pName=com.huawei.health&path=PrivilegeDetail&functionId=3");
        }
    }

    private void d(CommonSingleCallback<Boolean> commonSingleCallback) {
        LogUtil.a("Suggestion_DynamicTrackPresenter", "judgeFunctionAuth: ignore_three_dimensional_track");
    }
}
