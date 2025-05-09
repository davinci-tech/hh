package com.huawei.ui.main.stories.template.health.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.health.marketing.api.MarketingApi;
import com.huawei.health.marketing.datatype.MarketingOption;
import com.huawei.health.marketing.datatype.ResourceResultInfo;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.stories.template.BaseComponent;
import com.huawei.ui.main.stories.template.health.config.HealthCommonExpandViewConfig;
import com.huawei.ui.main.stories.template.health.config.HealthNoDeviceConfig;
import com.huawei.ui.main.stories.template.health.view.NoDataViewContainer;
import defpackage.cdy;
import defpackage.qrl;
import defpackage.rzd;
import health.compact.a.Services;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public class NoDataViewContainer extends BaseNoDataView {
    private static final String TAG = "Main_NoDataViewContainer";
    private SparseArray<NoDataBuyDeviceView> mBuyDeviceLayoutArray;
    private LinearLayout mContainerLayout;
    private NoDataBuyDeviceView mDefBuyDeviceLayout;
    private NoDataNewsView mDefInfoLayout;
    private NoDataServiceView mDefServicesLayout;
    private HealthNoDeviceConfig mHealthNoDeviceConfig;
    private SparseArray<NoDataNewsView> mInfoLayoutArray;
    private boolean mIsNeedShowBuyDeviceView;
    private boolean mIsNeedShowInformationView;
    private boolean mIsNeedShowServiceView;
    private List<cdy> mModuleObjectList;
    private SparseArray<NoDataServiceView> mServicesLayoutArray;

    public NoDataViewContainer(Context context) {
        this(context, null);
    }

    public NoDataViewContainer(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NoDataViewContainer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mBuyDeviceLayoutArray = new SparseArray<>();
        this.mServicesLayoutArray = new SparseArray<>();
        this.mInfoLayoutArray = new SparseArray<>();
        LayoutInflater.from(context).inflate(R.layout.layout_no_data_container, this);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mContainerLayout = (LinearLayout) findViewById(R.id.layout_health_container);
    }

    public void setConfigInfo(HealthNoDeviceConfig healthNoDeviceConfig, boolean z) {
        this.mHealthNoDeviceConfig = healthNoDeviceConfig;
        if (healthNoDeviceConfig == null) {
            LogUtil.h(TAG, "setConfigInfo healthNoDeviceConfig is null");
            return;
        }
        this.mDefServicesLayout = (NoDataServiceView) getExpandedView(healthNoDeviceConfig.getServiceViewConfig());
        this.mDefInfoLayout = (NoDataNewsView) getExpandedView(this.mHealthNoDeviceConfig.getInfoViewConfig());
        if (!z) {
            this.mDefBuyDeviceLayout = (NoDataBuyDeviceView) getExpandedView(this.mHealthNoDeviceConfig.getBuyDeviceViewConfig());
        }
        setComponentInfo(z);
    }

    private void setComponentInfo(boolean z) {
        if (this.mDefBuyDeviceLayout != null && !z) {
            this.mIsNeedShowBuyDeviceView = true;
        }
        if (this.mDefServicesLayout != null) {
            this.mIsNeedShowServiceView = true;
        }
        if (this.mDefInfoLayout != null) {
            this.mIsNeedShowInformationView = true;
        }
    }

    private void setGuideBuyInfo(cdy cdyVar) {
        if (!this.mIsNeedShowBuyDeviceView) {
            LogUtil.a(TAG, "setGuideBuyInfo config file do not show BuyDeviceView");
            return;
        }
        if (this.mHealthNoDeviceConfig == null) {
            LogUtil.h(TAG, "setGuideBuyInfo healthNoDeviceConfig is null");
            return;
        }
        if (cdyVar == null) {
            LogUtil.h(TAG, "setGuideBuyInfo guideBuyData is null");
            return;
        }
        NoDataBuyDeviceView noDataBuyDeviceView = this.mDefBuyDeviceLayout;
        if (noDataBuyDeviceView != null) {
            this.mContainerLayout.removeView(noDataBuyDeviceView);
            this.mDefBuyDeviceLayout = null;
        }
        int f = cdyVar.f();
        LogUtil.c(TAG, "setGuideBuyInfo: pageModuleId :", Integer.valueOf(f));
        NoDataBuyDeviceView noDataBuyDeviceView2 = this.mBuyDeviceLayoutArray.get(f);
        if (noDataBuyDeviceView2 != null) {
            this.mContainerLayout.removeView(noDataBuyDeviceView2);
            this.mBuyDeviceLayoutArray.remove(f);
        }
        NoDataBuyDeviceView noDataBuyDeviceView3 = (NoDataBuyDeviceView) getExpandedView(this.mHealthNoDeviceConfig.getBuyDeviceViewConfig());
        LogUtil.c(TAG, "setGuideBuyInfo: buyDeviceLayout : ", noDataBuyDeviceView3);
        if (noDataBuyDeviceView3 != null) {
            noDataBuyDeviceView3.setUiData(cdyVar);
            this.mContainerLayout.addView(noDataBuyDeviceView3);
            this.mBuyDeviceLayoutArray.put(f, noDataBuyDeviceView3);
        }
    }

    private void setServicesUiData(cdy cdyVar) {
        if (!this.mIsNeedShowServiceView) {
            LogUtil.a(TAG, "setServicesUiData config file do not show ServiceView");
            return;
        }
        if (this.mHealthNoDeviceConfig == null) {
            LogUtil.h(TAG, "setServicesUiData healthNoDeviceConfig is null");
            return;
        }
        if (cdyVar == null) {
            LogUtil.h(TAG, "setServicesUiData servicesUiData is null");
            return;
        }
        NoDataServiceView noDataServiceView = this.mDefServicesLayout;
        if (noDataServiceView != null) {
            this.mContainerLayout.removeView(noDataServiceView);
            this.mDefServicesLayout = null;
        }
        int f = cdyVar.f();
        LogUtil.c(TAG, "setServicesUiData: pageModuleId :", Integer.valueOf(f));
        NoDataServiceView noDataServiceView2 = this.mServicesLayoutArray.get(f);
        if (noDataServiceView2 != null) {
            this.mContainerLayout.removeView(noDataServiceView2);
            this.mServicesLayoutArray.remove(f);
        }
        NoDataServiceView noDataServiceView3 = (NoDataServiceView) getExpandedView(this.mHealthNoDeviceConfig.getServiceViewConfig());
        if (noDataServiceView3 != null) {
            noDataServiceView3.setUiData(cdyVar);
            this.mContainerLayout.addView(noDataServiceView3);
            this.mServicesLayoutArray.put(f, noDataServiceView3);
        }
    }

    private void setNewsUiData(cdy cdyVar) {
        if (!this.mIsNeedShowInformationView) {
            LogUtil.a(TAG, "setNewsUiData config file do not show InformationView");
            return;
        }
        if (this.mHealthNoDeviceConfig == null) {
            LogUtil.h(TAG, "setNewsUiData healthNoDeviceConfig is null");
            return;
        }
        if (cdyVar == null) {
            LogUtil.h(TAG, "setNewsUiData newsUiData is null");
            return;
        }
        NoDataNewsView noDataNewsView = this.mDefInfoLayout;
        if (noDataNewsView != null) {
            this.mContainerLayout.removeView(noDataNewsView);
            this.mDefInfoLayout = null;
        }
        int f = cdyVar.f();
        LogUtil.c(TAG, "setNewsUiData: pageModuleId :", Integer.valueOf(f));
        NoDataNewsView noDataNewsView2 = this.mInfoLayoutArray.get(f);
        if (noDataNewsView2 != null) {
            this.mContainerLayout.removeView(noDataNewsView2);
            this.mInfoLayoutArray.remove(f);
        }
        NoDataNewsView noDataNewsView3 = (NoDataNewsView) getExpandedView(this.mHealthNoDeviceConfig.getInfoViewConfig());
        if (noDataNewsView3 != null) {
            noDataNewsView3.setUiData(cdyVar);
            this.mContainerLayout.addView(noDataNewsView3);
            this.mInfoLayoutArray.put(f, noDataNewsView3);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public void setMarketingResource(Context context, String str) {
        char c;
        str.hashCode();
        switch (str.hashCode()) {
            case 331334480:
                if (str.equals("BloodOxygenCardConstructor")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 354423382:
                if (str.equals("StressCardConstructor")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 1135464211:
                if (str.equals("SleepCardConstructor")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1487186862:
                if (str.equals("BloodSugarCardConstructor")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1698958836:
                if (str.equals("HeartRateConstructor")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1783641739:
                if (str.equals("BloodPressureCardConstructor")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 2131694104:
                if (str.equals("BodyTemperatureCardConstructor")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                initMarketingResource(context, 380, 4029);
                break;
            case 1:
                initMarketingGrid(context, 4030);
                break;
            case 2:
                initSleepMarketingResource(context);
                break;
            case 3:
                initMarketingResource(context, 350, 4033);
                break;
            case 4:
                initMarketingGrid(context, 4028);
                break;
            case 5:
                initMarketingGrid(context, 4034);
                break;
            case 6:
                initMarketingResource(context, 370, 4035);
                break;
            default:
                LogUtil.c(TAG, "No serviceId match for trigger marketing resource. ServiceId: ", str);
                break;
        }
    }

    private void initMarketingGrid(final Context context, int i) {
        final MarketingApi marketingApi = (MarketingApi) Services.a("FeatureMarketing", MarketingApi.class);
        if (marketingApi != null) {
            marketingApi.getResourceResultInfo(i).addOnSuccessListener(new OnSuccessListener() { // from class: rzc
                @Override // com.huawei.hmf.tasks.OnSuccessListener
                public final void onSuccess(Object obj) {
                    NoDataViewContainer.this.m863x23b1d25d(marketingApi, context, (Map) obj);
                }
            });
        }
    }

    /* renamed from: lambda$initMarketingGrid$0$com-huawei-ui-main-stories-template-health-view-NoDataViewContainer, reason: not valid java name */
    public /* synthetic */ void m863x23b1d25d(MarketingApi marketingApi, Context context, Map map) {
        Iterator<View> it = marketingApi.getMarketingViewList(context, marketingApi.filterMarketingRules((Map<Integer, ResourceResultInfo>) map)).iterator();
        while (it.hasNext()) {
            this.mContainerLayout.addView(it.next());
        }
    }

    private void initMarketingResource(Context context, int i, int i2) {
        MarketingApi marketingApi = (MarketingApi) Services.a("FeatureMarketing", MarketingApi.class);
        if (marketingApi != null) {
            marketingApi.requestMarketingResource(new MarketingOption.Builder().setContext(context).setPageId(i).setLayoutMap(setLayoutMap(i2)).build());
        }
    }

    private Map<Integer, LinearLayout> setLayoutMap(int i) {
        HashMap hashMap = new HashMap();
        hashMap.put(Integer.valueOf(i), this.mContainerLayout);
        return hashMap;
    }

    private void initSleepMarketingResource(final Context context) {
        final MarketingApi marketingApi = (MarketingApi) Services.a("FeatureMarketing", MarketingApi.class);
        if (marketingApi != null) {
            marketingApi.requestMarketingResource(new MarketingOption.Builder().setContext(context).setPageId(310).build());
            marketingApi.getResourceResultInfo(4031).addOnSuccessListener(new OnSuccessListener() { // from class: rzg
                @Override // com.huawei.hmf.tasks.OnSuccessListener
                public final void onSuccess(Object obj) {
                    NoDataViewContainer.this.m864x78046449(marketingApi, context, (Map) obj);
                }
            });
        }
    }

    /* renamed from: lambda$initSleepMarketingResource$1$com-huawei-ui-main-stories-template-health-view-NoDataViewContainer, reason: not valid java name */
    public /* synthetic */ void m864x78046449(MarketingApi marketingApi, Context context, Map map) {
        refreshMarketingLayout(marketingApi.getMarketingViewList(context, marketingApi.filterMarketingRules((Map<Integer, ResourceResultInfo>) map)), null);
    }

    public void refreshMarketingLayout(List<View> list, List<Integer> list2) {
        qrl.dHK_(this.mContainerLayout, list, list2);
    }

    public void setAllViewData(List<cdy> list) {
        this.mModuleObjectList = list;
        if (list == null) {
            LogUtil.h(TAG, "setAllViewData moduleObjectList is null");
            return;
        }
        for (cdy cdyVar : list) {
            if (cdyVar != null) {
                int b = cdyVar.b();
                if (b == 1) {
                    setServicesUiData(cdyVar);
                } else if (b == 4) {
                    setBuyOrNews(cdyVar);
                } else {
                    LogUtil.h(TAG, "setAllViewData unSupport moduleType", Integer.valueOf(b));
                }
            }
        }
    }

    private void setBuyOrNews(cdy cdyVar) {
        if (cdyVar == null) {
            LogUtil.h(TAG, "setBuyOrNews moduleObject is null");
        } else if (cdyVar.c() == 11) {
            setGuideBuyInfo(cdyVar);
        } else {
            setNewsUiData(cdyVar);
        }
    }

    public void refreshTahiti() {
        setAllViewData(this.mModuleObjectList);
    }

    private <T extends BaseNoDataView> T getExpandedView(HealthCommonExpandViewConfig healthCommonExpandViewConfig) {
        if (healthCommonExpandViewConfig == null) {
            return null;
        }
        String newComponent = healthCommonExpandViewConfig.getNewComponent();
        LogUtil.c(TAG, "component value", newComponent);
        if (TextUtils.isEmpty(newComponent)) {
            return null;
        }
        BaseComponent d = rzd.d(newComponent, getContext());
        if (d == null) {
            LogUtil.h(TAG, "baseComponent is null");
        }
        if (healthCommonExpandViewConfig.getParams() == null) {
            LogUtil.h(TAG, "config getparam is null");
        } else {
            LogUtil.h(TAG, "config getparam is not null");
            if (d != null) {
                d.initComponent(healthCommonExpandViewConfig.getParams());
            }
        }
        if (((BaseNoDataView) d) != null) {
            return (T) d.getView(getContext());
        }
        return null;
    }
}
