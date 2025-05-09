package com.huawei.ui.main.stories.fitness.activity.heartrate;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.huawei.android.airsharing.api.IEventListener;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.h5pro.preload.H5PreloadCountStrategy;
import com.huawei.health.h5pro.service.H5ProServiceManager;
import com.huawei.health.marketing.api.MarketingApi;
import com.huawei.health.marketing.datatype.MarketingOption;
import com.huawei.health.marketing.utils.EcgDataInquirer;
import com.huawei.health.marketing.utils.EcgFilterManager;
import com.huawei.health.section.section.MeasureCardView;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.data.listener.HiSubscribeListener;
import com.huawei.hihealth.data.listener.HiUnSubscribeListener;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.h5pro.preload.H5ProPkgPreloadSyncTask;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.calendarview.HealthCalendar;
import com.huawei.ui.commonui.calendarview.HealthDataMarkDateTrigger;
import com.huawei.ui.commonui.linechart.HwHealthChartHolder;
import com.huawei.ui.commonui.linechart.common.ClassType;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.common.DateType;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.commonui.linechart.icommon.IChartLayerHolder;
import com.huawei.ui.commonui.scrollview.HealthScrollView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.utils.ScrollUtil;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.activity.heartrate.HeartRateDetailActivity;
import com.huawei.ui.main.stories.fitness.activity.heartrate.chart.HeartRateBarChartHolder;
import com.huawei.ui.main.stories.fitness.activity.heartrate.chart.HeartRateLineChartHolder;
import com.huawei.ui.main.stories.fitness.views.base.chart.ClassifiedButtonList;
import com.huawei.ui.main.stories.fitness.views.base.chart.ClassifiedViewList;
import com.huawei.ui.main.stories.fitness.views.base.chart.ObserveredClassifiedView;
import com.huawei.ui.main.stories.fitness.views.base.chart.ScrollChartObserverView;
import com.huawei.ui.main.stories.fitness.views.base.chart.icommon.IFocusObserverItem;
import com.huawei.ui.main.stories.fitness.views.heartrate.card.CardContainerView;
import com.huawei.ui.main.stories.fitness.views.heartrate.card.DayCardContainerView;
import com.huawei.ui.main.stories.fitness.views.heartrate.card.ReminderCardView;
import com.huawei.ui.main.stories.fitness.views.heartrate.card.RestCardView;
import com.huawei.ui.main.stories.fitness.views.heartrate.card.SportCardView;
import com.huawei.ui.main.stories.fitness.views.heartrate.classified.HeartRateDayView;
import com.huawei.ui.main.stories.fitness.views.heartrate.classified.HeartRateMonthView;
import com.huawei.ui.main.stories.fitness.views.heartrate.classified.HeartRateWeekView;
import com.huawei.ui.main.stories.fitness.views.heartrate.classified.HeartRateYearView;
import com.huawei.ui.main.stories.privacy.template.model.bean.PageModelArgs;
import com.huawei.ui.main.stories.privacy.template.view.showdata.PrivacyDataModelActivity;
import com.huawei.ui.main.stories.settings.js.CustomDataService;
import com.huawei.ui.main.stories.template.PopMenuManager;
import com.huawei.ui.main.stories.template.data.IPopMenuItemClick;
import com.huawei.ui.main.stories.template.health.common.CommonHealthNoDeviceFragment;
import com.huawei.ui.main.stories.utils.FitnessUtils;
import com.huawei.ui.main.stories.utils.LastTimeHealthDataReader;
import defpackage.bzs;
import defpackage.cun;
import defpackage.cvs;
import defpackage.drl;
import defpackage.gnm;
import defpackage.ixx;
import defpackage.jfq;
import defpackage.jpt;
import defpackage.koq;
import defpackage.nom;
import defpackage.nsf;
import defpackage.nsn;
import defpackage.pfe;
import defpackage.psb;
import defpackage.pxz;
import defpackage.qrp;
import defpackage.ryf;
import defpackage.rzf;
import defpackage.sdc;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.EnvironmentInfo;
import health.compact.a.LocalBroadcast;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* loaded from: classes6.dex */
public class HeartRateDetailActivity extends BaseActivity {
    private static final Object e = new Object();
    private List<Integer> ad;
    private HeartRateBarChartHolder b;
    private HealthCalendar d;
    private ObserveredClassifiedView f;
    private LinearLayout g;
    private ClassifiedViewList i;
    private LinearLayout n;
    private d o;
    private Handler p;
    private LastTimeHealthDataReader<HeartRateDetailActivity> s;
    private ViewStub u;
    private HeartRateLineChartHolder v;
    private MeasureCardView w;
    private CustomTitleBar x;
    private final Context h = BaseApplication.getContext();
    private final List<CardContainerView> c = new ArrayList(4);
    private final List<ReminderCardView> aa = new ArrayList(4);

    /* renamed from: a, reason: collision with root package name */
    private final List<ReminderCardView> f9846a = new ArrayList(4);
    private int j = 0;
    private long q = 0;
    private boolean r = false;
    private boolean l = false;
    private final List<Integer> y = Arrays.asList(5, 6);
    private Observer k = new Observer() { // from class: com.huawei.ui.main.stories.fitness.activity.heartrate.HeartRateDetailActivity.1
        @Override // com.huawei.haf.design.pattern.Observer
        public void notify(String str, Object... objArr) {
            if (objArr == null || objArr.length <= 0) {
                LogUtil.b("HealthHeartRate_HeartRateDetailActivity", "ecg data observer notify do nothing! args is empty");
                return;
            }
            Object obj = objArr[0];
            if (!(obj instanceof Integer)) {
                LogUtil.b("HealthHeartRate_HeartRateDetailActivity", "ecg data observer notify do nothing! args is illegal args = ", obj);
            } else {
                final int intValue = ((Integer) obj).intValue();
                HandlerExecutor.e(new Runnable() { // from class: com.huawei.ui.main.stories.fitness.activity.heartrate.HeartRateDetailActivity.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (HeartRateDetailActivity.this.l) {
                            EcgFilterManager.a().d(HeartRateDetailActivity.this.w, intValue, true);
                            EcgFilterManager.a().a(HeartRateDetailActivity.this.w, intValue);
                        }
                    }
                });
            }
        }
    };
    private final int[] m = {47055, 47005};
    private boolean t = true;

    public static /* synthetic */ boolean c(DataInfos dataInfos) {
        return true;
    }

    public static /* synthetic */ boolean d(DataInfos dataInfos) {
        return true;
    }

    public static void e(Context context, long j) {
        FitnessUtils.c(context, HeartRateDetailActivity.class, j);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity
    public void initViewTahiti() {
        Iterator<CardContainerView> it = this.c.iterator();
        while (it.hasNext()) {
            it.next().e();
        }
        LinearLayout linearLayout = this.g;
        if (linearLayout != null) {
            pfe.dok_(9, linearLayout);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ObserverManagerUtil.d(this.k, EcgDataInquirer.ECG_DATA_OBSERVER);
        setContentView(R.layout.activity_heart_rate_detail);
        clearBackgroundDrawable();
        cancelAdaptRingRegion();
        psb.d(true);
        this.b = new HeartRateBarChartHolder(getApplicationContext());
        this.v = new HeartRateLineChartHolder(getApplicationContext());
        this.x = (CustomTitleBar) findViewById(R.id.fitness_detail_titlebar);
        this.p = new c(this);
        this.u = (ViewStub) findViewById(R.id.measure_card_view_stub);
        b();
        Intent intent = getIntent();
        if (intent != null) {
            long longExtra = intent.getLongExtra("key_bundle_health_last_data_time", 0L);
            this.q = longExtra;
            LogUtil.a("HealthHeartRate_HeartRateDetailActivity", "mLastTimestamp=", Long.valueOf(longExtra));
        }
        if (this.q > 0) {
            j();
        } else {
            g();
        }
        SharedPreferenceManager.e(this.h, String.valueOf(10006), "heart_rate_lastTimes", Long.toString(this.q), new StorageParams());
        n();
        HiHealthNativeApi.a(this.h).subscribeHiHealthData(this.y, new b(this));
        drl.e(null);
    }

    private void b() {
        MeasureCardView measureCardView = (MeasureCardView) this.u.inflate().findViewById(R.id.measure_card_view);
        this.w = measureCardView;
        nsn.cLD_(measureCardView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i) {
        MarketingApi marketingApi = (MarketingApi) Services.a("FeatureMarketing", MarketingApi.class);
        if (marketingApi == null) {
            LogUtil.a("HealthHeartRate_HeartRateDetailActivity", "marketingApi is null");
            return;
        }
        MarketingOption.Builder builder = new MarketingOption.Builder();
        builder.setContext(this);
        builder.setPageId(i);
        if (i == 9) {
            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.heart_rate_marketing);
            if (linearLayout == null) {
                return;
            }
            BaseActivity.cancelLayoutById(linearLayout);
            HashMap hashMap = new HashMap();
            hashMap.put(4005, linearLayout);
            builder.setLayoutMap(hashMap);
        }
        marketingApi.requestMarketingResource(builder.build());
    }

    private void n() {
        this.o = new d(this);
        BroadcastManagerUtil.bFC_(this, this.o, new IntentFilter("com.huawei.bone.action.CONNECTION_STATE_CHANGED"), LocalBroadcast.c, null);
    }

    private void o() {
        try {
            LogUtil.a("HealthHeartRate_HeartRateDetailActivity", "Enter unregisterBindDeviceBroadcast()!");
            unregisterReceiver(this.o);
        } catch (IllegalArgumentException unused) {
            LogUtil.b("HealthHeartRate_HeartRateDetailActivity", "unregisterBindDeviceBroadcast failed");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        this.l = true;
        HealthViewPager healthViewPager = (HealthViewPager) findViewById(R.id.heart_rate_chart_container);
        ClassifiedButtonList classifiedButtonList = (ClassifiedButtonList) findViewById(R.id.classified_button_list);
        this.n = (LinearLayout) findViewById(R.id.heart_rate_extension);
        this.i = new ClassifiedViewList(this, classifiedButtonList, healthViewPager);
        this.x.setRightButtonDrawable(getResources().getDrawable(R.drawable._2131430194_res_0x7f0b0b32), nsf.h(R$string.accessibility_more_item));
        this.x.setRightButtonClickable(true);
        this.x.setRightButtonVisibility(0);
        final PopMenuManager popMenuManager = new PopMenuManager() { // from class: com.huawei.ui.main.stories.fitness.activity.heartrate.HeartRateDetailActivity.4
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.huawei.ui.main.stories.template.PopMenuManager
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public CustomTitleBar getTitleBar() {
                return HeartRateDetailActivity.this.x;
            }

            @Override // com.huawei.ui.main.stories.template.IMenuItemManager
            public List<ryf> getMenuItemList() {
                return HeartRateDetailActivity.this.e();
            }
        };
        this.x.setRightButtonOnClickListener(new View.OnClickListener() { // from class: pqy
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HeartRateDetailActivity.dsk_(PopMenuManager.this, view);
            }
        });
        String string = getString(R$string.IDS_main_watch_heart_rate_unit_string);
        this.v.spetifiyDataTypeUnit(new IChartLayerHolder.DataTypeFilter() { // from class: pqz
            @Override // com.huawei.ui.commonui.linechart.icommon.IChartLayerHolder.DataTypeFilter
            public final boolean isAccept(DataInfos dataInfos) {
                return HeartRateDetailActivity.c(dataInfos);
            }
        }, string);
        this.b.spetifiyDataTypeUnit(new IChartLayerHolder.DataTypeFilter() { // from class: pqx
            @Override // com.huawei.ui.commonui.linechart.icommon.IChartLayerHolder.DataTypeFilter
            public final boolean isAccept(DataInfos dataInfos) {
                return HeartRateDetailActivity.d(dataInfos);
            }
        }, string);
        this.p.post(new Runnable() { // from class: pqu
            @Override // java.lang.Runnable
            public final void run() {
                HeartRateDetailActivity.this.d();
            }
        });
        if (!CommonUtil.bu()) {
            this.p.postDelayed(new Runnable() { // from class: prb
                @Override // java.lang.Runnable
                public final void run() {
                    HeartRateDetailActivity.this.a();
                }
            }, 100L);
        }
        if (LoginInit.getInstance(BaseApplication.getContext()).isKidAccount()) {
            this.p.post(new Runnable() { // from class: pra
                @Override // java.lang.Runnable
                public final void run() {
                    EcgFilterManager.a().g();
                }
            });
        }
    }

    public static /* synthetic */ void dsk_(PopMenuManager popMenuManager, View view) {
        popMenuManager.showPopMenu();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void a() {
        m();
        c(9);
        HealthScrollView healthScrollView = (HealthScrollView) findViewById(R.id.heart_rate_scroll_view);
        if (healthScrollView == null) {
            LogUtil.h("HealthHeartRate_HeartRateDetailActivity", "heart rate scroll view is null");
        } else {
            healthScrollView.setScrollOnlyVertical(true);
            ScrollUtil.cKx_(healthScrollView, getWindow().getDecorView(), IEventListener.EVENT_ID_DEVICE_UPDATE);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ArrayList<ryf> e() {
        ryf ryfVar = new ryf(getResources().getString(R$string.IDS_hwh_motiontrack_sport_heart_rate_setting_tl), new IPopMenuItemClick() { // from class: com.huawei.ui.main.stories.fitness.activity.heartrate.HeartRateDetailActivity.5
            @Override // com.huawei.ui.main.stories.template.data.IPopMenuItemClick
            public void onItemClick() {
                HeartRateDetailActivity.this.h();
            }
        });
        ryf ryfVar2 = new ryf(getResources().getString(R$string.IDS_settings_continue_HeartRate_switch), new IPopMenuItemClick() { // from class: com.huawei.ui.main.stories.fitness.activity.heartrate.HeartRateDetailActivity.3
            @Override // com.huawei.ui.main.stories.template.data.IPopMenuItemClick
            public void onItemClick() {
                HeartRateDetailActivity.this.i();
            }
        });
        ryf ryfVar3 = new ryf(getResources().getString(R$string.IDS_privacy_all_data), new IPopMenuItemClick() { // from class: com.huawei.ui.main.stories.fitness.activity.heartrate.HeartRateDetailActivity.2
            @Override // com.huawei.ui.main.stories.template.data.IPopMenuItemClick
            public void onItemClick() {
                HeartRateDetailActivity.this.f();
            }
        });
        ArrayList<ryf> arrayList = new ArrayList<>();
        arrayList.add(ryfVar);
        if (!EnvironmentInfo.k()) {
            arrayList.add(ryfVar2);
        }
        arrayList.add(ryfVar3);
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        this.r = true;
        PageModelArgs pageModelArgs = new PageModelArgs(102, "PrivacyDataConstructor", 3, 1);
        pageModelArgs.setClassType(0);
        PrivacyDataModelActivity.b(this, pageModelArgs);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        if (LoginInit.getInstance(BaseApplication.getContext()).isBrowseMode()) {
            LoginInit.getInstance(this.h).browsingToLogin(new IBaseResponseCallback() { // from class: prg
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    LogUtil.h("HealthHeartRate_HeartRateDetailActivity", "browsingToLogin errorCode is not success", Integer.valueOf(i));
                }
            }, null);
            return;
        }
        H5ProLaunchOption.Builder builder = new H5ProLaunchOption.Builder();
        H5ProServiceManager.getInstance().registerService(CustomDataService.class);
        bzs.e().loadH5ProApp(this.h, "com.huawei.health.h5.setting", builder);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        if (koq.b(cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_DEVICES, null, "HealthHeartRate_HeartRateDetailActivity"))) {
            sdc.e(this, R$string.IDS_device_wifi_go_connect, R$string.IDS_core_sleep_not_connect);
            return;
        }
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "HealthHeartRate_HeartRateDetailActivity");
        if (koq.b(deviceList)) {
            sdc.e(this, R$string.IDS_device_wifi_go_connect, R$string.IDS_hw_show_main_device_not_connect);
            return;
        }
        DeviceInfo deviceInfo = deviceList.get(0);
        DeviceCapability e2 = cvs.e(deviceInfo.getDeviceIdentify());
        if (e2 != null && e2.isSupportContinueHeartRate()) {
            b("com.huawei.ui.device.activity.heartrate.ContinueHeartRateSettingActivity", deviceInfo);
        } else if (e2 != null && e2.isSupportHeartRateEnable() && !e2.isSupportContinueHeartRate()) {
            b("com.huawei.ui.device.activity.heartrate.HeartRateSettingsActivity", deviceInfo);
        } else {
            sdc.e(this, R$string.IDS_device_wifi_go_connect, R$string.IDS_hw_show_main_device_not_connect);
        }
    }

    private void b(String str, DeviceInfo deviceInfo) {
        String deviceIdentify = deviceInfo.getDeviceIdentify();
        Intent intent = new Intent();
        intent.setClassName(BaseApplication.getContext(), str);
        intent.putExtra("device_id", deviceIdentify);
        intent.addFlags(268435456);
        gnm.aPB_(this.h, intent);
    }

    private void m() {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.configure_service);
        this.g = linearLayout;
        if (linearLayout != null) {
            pfe.doh_(9, linearLayout, null);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        o();
        if (koq.c(this.ad)) {
            HiHealthNativeApi.a(this.h).unSubscribeHiHealthData(this.ad, new HiUnSubscribeListener() { // from class: prf
                @Override // com.huawei.hihealth.data.listener.HiUnSubscribeListener
                public final void onResult(boolean z) {
                    LogUtil.a("HealthHeartRate_HeartRateDetailActivity", "onDestroy unSubscribeHiHealthData isSuccess=", Boolean.valueOf(z));
                }
            });
        }
        List<CardContainerView> list = this.c;
        if (list != null) {
            list.clear();
        }
        List<ReminderCardView> list2 = this.aa;
        if (list2 != null) {
            list2.clear();
        }
        Handler handler = this.p;
        if (handler != null) {
            handler.removeCallbacks(null);
        }
        ObserverManagerUtil.c(this.k);
    }

    private void l() {
        this.i.setOnClassifiedViewChangeListener(new ClassifiedViewList.OnClassifiedViewChangeListener() { // from class: prk
            @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ClassifiedViewList.OnClassifiedViewChangeListener
            public final void onClassifiedViewSelected(View view, int i) {
                HeartRateDetailActivity.this.dso_(view, i);
            }
        });
    }

    public /* synthetic */ void dso_(View view, int i) {
        if (view instanceof ObserveredClassifiedView) {
            this.f = (ObserveredClassifiedView) view;
        }
        this.n.removeAllViews();
        IFocusObserverItem c2 = this.i.c();
        if (c2 == null) {
            LogUtil.h("HealthHeartRate_HeartRateDetailActivity", "onClassifiedViewSelected focusObserverItem is null");
            return;
        }
        View onCreateDetailView = c2.onCreateDetailView();
        if (onCreateDetailView == null) {
            return;
        }
        ViewParent parent = onCreateDetailView.getParent();
        if (parent instanceof ViewGroup) {
            ((ViewGroup) parent).removeView(onCreateDetailView);
            LogUtil.h("HealthHeartRate_HeartRateDetailActivity", "addListenerForDayObserverView(): parent is not instance of ViewGroup");
        }
        this.n.addView(onCreateDetailView, -1, -2);
    }

    public void d() {
        this.c.clear();
        this.d = qrp.a(this.d, nom.a(this.q));
        final ArrayList arrayList = new ArrayList(4);
        d(arrayList);
        b(arrayList);
        e(arrayList);
        a(arrayList);
        this.i.e(arrayList, new ClassifiedViewList.IChartLayerHolderAdapter() { // from class: pre
            @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ClassifiedViewList.IChartLayerHolderAdapter
            public final IChartLayerHolder acquireAdapter(ClassifiedViewList.ClassifiedView classifiedView) {
                return HeartRateDetailActivity.this.e(arrayList, classifiedView);
            }
        });
        if (this.q > 0) {
            Iterator<ClassifiedViewList.ClassifiedView> it = arrayList.iterator();
            while (it.hasNext()) {
                a(it.next());
            }
        }
        l();
        pxz.a(new e(this));
    }

    public /* synthetic */ IChartLayerHolder e(List list, ClassifiedViewList.ClassifiedView classifiedView) {
        if (!list.contains(classifiedView)) {
            return null;
        }
        if (list.indexOf(classifiedView) >= 1) {
            return this.b;
        }
        return this.v;
    }

    private void a(ClassifiedViewList.ClassifiedView classifiedView) {
        if (classifiedView instanceof HeartRateDayView) {
            ((HeartRateDayView) classifiedView).setJumpTableChartLastTimeId(nom.a(this.q));
            return;
        }
        if (classifiedView instanceof HeartRateWeekView) {
            ((HeartRateWeekView) classifiedView).setJumpTableChartLastTimeId(nom.m(this.q));
            return;
        }
        if (classifiedView instanceof HeartRateMonthView) {
            ((HeartRateMonthView) classifiedView).setJumpTableChartLastTimeId(nom.f(this.q));
        } else if (classifiedView instanceof HeartRateYearView) {
            ((HeartRateYearView) classifiedView).setJumpTableChartLastTimeId(nom.t(this.q));
        } else {
            LogUtil.h("HealthHeartRate_HeartRateDetailActivity", "Unknown ClassifiedView");
        }
    }

    static class c extends BaseHandler<HeartRateDetailActivity> {
        c(HeartRateDetailActivity heartRateDetailActivity) {
            super(heartRateDetailActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dsp_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(HeartRateDetailActivity heartRateDetailActivity, Message message) {
            int i = message.what;
            if (i == 1) {
                HeartRateDetailActivity.b((List<ReminderCardView>) heartRateDetailActivity.aa, false);
                HeartRateDetailActivity.b((List<ReminderCardView>) heartRateDetailActivity.f9846a, false);
            } else if (i == 2) {
                HeartRateDetailActivity.b((List<ReminderCardView>) heartRateDetailActivity.aa, true);
                HeartRateDetailActivity.b((List<ReminderCardView>) heartRateDetailActivity.f9846a, true);
            } else {
                LogUtil.h("HealthHeartRate_HeartRateDetailActivity", "message is error");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(List<ReminderCardView> list, boolean z) {
        for (ReminderCardView reminderCardView : list) {
            if (reminderCardView != null && z) {
                reminderCardView.a();
            } else if (reminderCardView != null && !z) {
                reminderCardView.d();
            } else {
                LogUtil.h("HealthHeartRate_HeartRateDetailActivity", "view == null");
            }
        }
    }

    private void c(int i, CardContainerView cardContainerView) {
        synchronized (e) {
            if (i == this.j) {
                return;
            }
            this.j = i;
            for (CardContainerView cardContainerView2 : this.c) {
                if (cardContainerView2 != null && cardContainerView2 != cardContainerView) {
                    cardContainerView2.setCurrentItem(i);
                }
            }
        }
    }

    private ScrollChartObserverView c(ObserveredClassifiedView observeredClassifiedView) {
        DayCardContainerView dayCardContainerView = new DayCardContainerView(this, observeredClassifiedView);
        this.c.add(dayCardContainerView);
        b(dayCardContainerView);
        RestCardView restCardView = new RestCardView(this, observeredClassifiedView, getString(R$string.IDS_hw_health_show_healthdata_resting_heart_bmp));
        restCardView.d(this.v.b());
        ReminderCardView reminderCardView = new ReminderCardView(this, observeredClassifiedView, false);
        reminderCardView.b(this.v.d());
        reminderCardView.d(this.v.c());
        reminderCardView.d(this.v);
        this.aa.add(reminderCardView);
        ReminderCardView reminderCardView2 = new ReminderCardView(this, observeredClassifiedView, true);
        reminderCardView2.b(this.v.a());
        reminderCardView2.d(this.v.e());
        reminderCardView2.d(this.v);
        this.f9846a.add(reminderCardView2);
        ScrollChartObserverView sportCardView = new SportCardView(this, observeredClassifiedView);
        List<ScrollChartObserverView> arrayList = new ArrayList<>(16);
        arrayList.add(sportCardView);
        arrayList.add(restCardView);
        arrayList.add(reminderCardView);
        arrayList.add(reminderCardView2);
        dayCardContainerView.c(arrayList, e(observeredClassifiedView.getStepDataType()));
        return dayCardContainerView;
    }

    private List<HwHealthChartHolder.b> e(DataInfos dataInfos) {
        ArrayList arrayList = new ArrayList(16);
        arrayList.add(b(dataInfos, HwHealthChartHolder.LAYER_ID_NORMAL_HR));
        arrayList.add(b(dataInfos, HwHealthChartHolder.LAYER_ID_REST_HR));
        arrayList.add(b(dataInfos, HwHealthChartHolder.LAYER_ID_WARNING_HR));
        arrayList.add(b(dataInfos, HwHealthChartHolder.LAYER_ID_BRADYCARDIA));
        return arrayList;
    }

    private HwHealthChartHolder.b b(DataInfos dataInfos, String str) {
        HwHealthChartHolder.b bVar = new HwHealthChartHolder.b();
        bVar.e(dataInfos);
        bVar.e(str);
        return bVar;
    }

    private void b(final DayCardContainerView dayCardContainerView) {
        dayCardContainerView.d(new CardContainerView.OnSelectListener() { // from class: prc
            @Override // com.huawei.ui.main.stories.fitness.views.heartrate.card.CardContainerView.OnSelectListener
            public final void onSelect(View view, int i) {
                HeartRateDetailActivity.this.dsl_(dayCardContainerView, view, i);
            }
        });
    }

    public /* synthetic */ void dsl_(DayCardContainerView dayCardContainerView, View view, int i) {
        c(i, dayCardContainerView);
        this.n.removeAllViews();
        IFocusObserverItem c2 = this.i.c();
        if (c2 == null) {
            LogUtil.h("HealthHeartRate_HeartRateDetailActivity", "onSelect focusObserverItem is null");
            return;
        }
        View onCreateDetailView = c2.onCreateDetailView();
        if (onCreateDetailView == null) {
            return;
        }
        ViewParent parent = onCreateDetailView.getParent();
        if (parent != null) {
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(onCreateDetailView);
            } else {
                LogUtil.h("HealthHeartRate_HeartRateDetailActivity", "addListenerForDayObserverView(): parent is not instance of ViewGroup");
            }
        }
        this.n.addView(onCreateDetailView, -1, -2);
    }

    private CardContainerView d(ObserveredClassifiedView observeredClassifiedView) {
        CardContainerView cardContainerView = new CardContainerView(this, observeredClassifiedView);
        this.c.add(cardContainerView);
        SportCardView sportCardView = new SportCardView(this, observeredClassifiedView);
        sportCardView.c(this.b.c());
        sportCardView.b(this.b.d());
        RestCardView restCardView = new RestCardView(this, observeredClassifiedView, getString(R$string.IDS_hw_health_show_healthdata_avg_rest_heartrate));
        restCardView.d(this.b.e());
        ReminderCardView reminderCardView = new ReminderCardView(this, observeredClassifiedView, false);
        reminderCardView.b(this.b.f());
        reminderCardView.d(this.b.h());
        reminderCardView.d(this.b);
        this.aa.add(reminderCardView);
        ReminderCardView reminderCardView2 = new ReminderCardView(this, observeredClassifiedView, true);
        reminderCardView2.b(this.b.b());
        reminderCardView2.d(this.b.a());
        reminderCardView2.d(this.b);
        this.f9846a.add(reminderCardView2);
        a(cardContainerView);
        List<ScrollChartObserverView> arrayList = new ArrayList<>(16);
        arrayList.add(sportCardView);
        arrayList.add(restCardView);
        arrayList.add(reminderCardView);
        arrayList.add(reminderCardView2);
        cardContainerView.c(arrayList, e(observeredClassifiedView.getStepDataType()));
        return cardContainerView;
    }

    private void a(final CardContainerView cardContainerView) {
        cardContainerView.d(new CardContainerView.OnSelectListener() { // from class: prd
            @Override // com.huawei.ui.main.stories.fitness.views.heartrate.card.CardContainerView.OnSelectListener
            public final void onSelect(View view, int i) {
                HeartRateDetailActivity.this.dsm_(cardContainerView, view, i);
            }
        });
    }

    public /* synthetic */ void dsm_(CardContainerView cardContainerView, View view, int i) {
        c(i, cardContainerView);
        this.n.removeAllViews();
        IFocusObserverItem c2 = this.i.c();
        if (c2 == null) {
            LogUtil.h("HealthHeartRate_HeartRateDetailActivity", "enableClassifiedViewObserver onSelect focusObserverItem is null");
            return;
        }
        View onCreateDetailView = c2.onCreateDetailView();
        if (onCreateDetailView == null) {
            return;
        }
        ViewParent parent = onCreateDetailView.getParent();
        if (parent instanceof ViewGroup) {
            ((ViewGroup) parent).removeView(onCreateDetailView);
        } else {
            LogUtil.h("HealthHeartRate_HeartRateDetailActivity", "addListenerForObserverView(): parent is not instance of ViewGroup");
        }
        this.n.addView(onCreateDetailView, -1, -2);
    }

    private void d(List<ClassifiedViewList.ClassifiedView> list) {
        final HeartRateDayView heartRateDayView = new HeartRateDayView(this);
        heartRateDayView.setHorizontalJumpListener(new View.OnClickListener() { // from class: pqw
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HeartRateDetailActivity.this.dsn_(heartRateDayView, view);
            }
        });
        heartRateDayView.setStepDatatype(DataInfos.query(ClassType.TYPE_HEART_RATE, DateType.DATE_DAY));
        heartRateDayView.setHighlightedEntryParser(this.v);
        heartRateDayView.selectDataLayerId(HwHealthChartHolder.LAYER_ID_NORMAL_HR);
        heartRateDayView.initCalendarView(this, this.d, new HealthDataMarkDateTrigger(46019, this.m), true);
        this.f = heartRateDayView;
        heartRateDayView.enableObserverView(c(heartRateDayView));
        list.add(heartRateDayView);
    }

    public /* synthetic */ void dsn_(HeartRateDayView heartRateDayView, View view) {
        HwHealthBaseScrollBarLineChart chart = heartRateDayView.getChart();
        if (this.h == null || chart == null) {
            LogUtil.h("HealthHeartRate_HeartRateDetailActivity", "horizontal jump failed, the context or chart is null");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        long queryMarkerViewTimeRangeMin = chart.queryMarkerViewTimeRangeMin();
        if (((int) chart.acquireShowRangeMaximum()) == TimeUnit.MILLISECONDS.toMinutes(queryMarkerViewTimeRangeMin)) {
            queryMarkerViewTimeRangeMin -= TimeUnit.MINUTES.toMillis(2L);
        }
        HashMap hashMap = new HashMap(1);
        hashMap.put("click", 1);
        ixx.d().d(this.h, AnalyticsValue.HEALTH_HEART_RATE_DAY_HORIZONTAL_2090011.value(), hashMap, 0);
        Intent intent = new Intent(this.h, (Class<?>) HorizontalHeartRateDayActivity.class);
        intent.putExtra(ObserveredClassifiedView.JUMP_TIME_ID, nom.l(queryMarkerViewTimeRangeMin));
        intent.putExtra(ObserveredClassifiedView.JUMP_DATA_LAYER_ID, heartRateDayView.acquireDataLayerIndex());
        intent.putExtra(ObserveredClassifiedView.JUMP_DATA_TYPE, heartRateDayView.getStepDataType());
        startActivity(intent);
        LogUtil.a("HealthHeartRate_HeartRateDetailActivity", "horizontal jump onclick");
        ViewClickInstrumentation.clickOnView(view);
    }

    private void b(List<ClassifiedViewList.ClassifiedView> list) {
        HeartRateWeekView heartRateWeekView = new HeartRateWeekView(this);
        heartRateWeekView.selectDataLayerId(HwHealthChartHolder.LAYER_ID_NORMAL_HR);
        heartRateWeekView.setStepDatatype(DataInfos.query(ClassType.TYPE_HEART_RATE, DateType.DATE_WEEK));
        heartRateWeekView.setHighlightedEntryParser(this.b);
        heartRateWeekView.initCalendarView(this, this.d, new HealthDataMarkDateTrigger(46019, this.m), false);
        heartRateWeekView.enableObserverView(d(heartRateWeekView));
        list.add(heartRateWeekView);
    }

    private void e(List<ClassifiedViewList.ClassifiedView> list) {
        HeartRateMonthView heartRateMonthView = new HeartRateMonthView(this);
        heartRateMonthView.selectDataLayerId(HwHealthChartHolder.LAYER_ID_NORMAL_HR);
        heartRateMonthView.setStepDatatype(DataInfos.query(ClassType.TYPE_HEART_RATE, DateType.DATE_MONTH));
        heartRateMonthView.setHighlightedEntryParser(this.b);
        heartRateMonthView.initCalendarView(this, this.d, new HealthDataMarkDateTrigger(46019, this.m), false);
        heartRateMonthView.enableObserverView(d(heartRateMonthView));
        list.add(heartRateMonthView);
    }

    private void a(List<ClassifiedViewList.ClassifiedView> list) {
        HeartRateYearView heartRateYearView = new HeartRateYearView(this);
        heartRateYearView.selectDataLayerId(HwHealthChartHolder.LAYER_ID_NORMAL_HR);
        heartRateYearView.setStepDatatype(DataInfos.query(ClassType.TYPE_HEART_RATE, DateType.DATE_YEAR));
        heartRateYearView.setHighlightedEntryParser(this.b);
        heartRateYearView.initCalendarView(this, this.d, new HealthDataMarkDateTrigger(46019, this.m), false);
        heartRateYearView.enableObserverView(d(heartRateYearView));
        list.add(heartRateYearView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        this.r = false;
        if (this.s == null) {
            this.s = new LastTimeHealthDataReader<>(this, new a(this));
        }
        this.s.b(LastTimeHealthDataReader.CardData.HEALTH_RATE);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (this.t) {
            H5ProPkgPreloadSyncTask.startTask(this.h, "com.huawei.health.h5.ecg", new H5PreloadCountStrategy(1));
        }
        EcgFilterManager.a().o();
        this.t = false;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        ObserveredClassifiedView observeredClassifiedView;
        super.onActivityResult(i, i2, intent);
        if (i2 != -1 || intent == null) {
            return;
        }
        Serializable serializableExtra = intent.getSerializableExtra("selectedDate");
        if (!(serializableExtra instanceof HealthCalendar) || (observeredClassifiedView = this.f) == null) {
            return;
        }
        HealthCalendar healthCalendar = (HealthCalendar) serializableExtra;
        this.d = healthCalendar;
        observeredClassifiedView.processCalendarSelect(healthCalendar);
    }

    public static class e implements HiDataReadResultListener {
        private final WeakReference<HeartRateDetailActivity> d;

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResultIntent(int i, Object obj, int i2, int i3) {
        }

        e(HeartRateDetailActivity heartRateDetailActivity) {
            this.d = new WeakReference<>(heartRateDetailActivity);
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResult(Object obj, int i, int i2) {
            final HeartRateDetailActivity heartRateDetailActivity = this.d.get();
            if (heartRateDetailActivity == null) {
                LogUtil.h("HealthHeartRate_HeartRateDetailActivity", "activity or intent is null.");
                return;
            }
            if (obj instanceof SparseArray) {
                SparseArray sparseArray = (SparseArray) obj;
                Object obj2 = sparseArray.get(2101);
                Object obj3 = sparseArray.get(2102);
                final boolean z = (obj2 instanceof List) && koq.c((List) obj2);
                final boolean z2 = (obj3 instanceof List) && koq.c((List) obj3);
                heartRateDetailActivity.p.post(new Runnable() { // from class: pri
                    @Override // java.lang.Runnable
                    public final void run() {
                        HeartRateDetailActivity.e.c(HeartRateDetailActivity.this, z, z2);
                    }
                });
            }
        }

        public static /* synthetic */ void c(HeartRateDetailActivity heartRateDetailActivity, boolean z, boolean z2) {
            Iterator it = heartRateDetailActivity.aa.iterator();
            while (it.hasNext()) {
                ((ReminderCardView) it.next()).setHashHistoryData(z);
            }
            Iterator it2 = heartRateDetailActivity.f9846a.iterator();
            while (it2.hasNext()) {
                ((ReminderCardView) it2.next()).setHashHistoryData(z2);
            }
        }
    }

    static class d extends BroadcastReceiver {
        private final WeakReference<HeartRateDetailActivity> d;

        d(HeartRateDetailActivity heartRateDetailActivity) {
            this.d = new WeakReference<>(heartRateDetailActivity);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            DeviceInfo deviceInfo;
            HeartRateDetailActivity heartRateDetailActivity = this.d.get();
            if (heartRateDetailActivity == null || intent == null) {
                LogUtil.h("HealthHeartRate_HeartRateDetailActivity", "activity or intent is null.");
                return;
            }
            String action = intent.getAction();
            LogUtil.a("HealthHeartRate_HeartRateDetailActivity", "mDeviceStatusReceiver onReceive action :", action);
            if (!"com.huawei.bone.action.CONNECTION_STATE_CHANGED".equals(action)) {
                LogUtil.h("HealthHeartRate_HeartRateDetailActivity", "this action do nothing");
                return;
            }
            Parcelable parcelableExtra = intent.getParcelableExtra("deviceinfo");
            if (parcelableExtra instanceof DeviceInfo) {
                deviceInfo = (DeviceInfo) parcelableExtra;
                if (!c(deviceInfo)) {
                    LogUtil.h("HealthHeartRate_HeartRateDetailActivity", "This device does not have the correspond capability.");
                    return;
                }
            } else {
                deviceInfo = null;
            }
            if (deviceInfo == null || deviceInfo.getDeviceConnectState() != 2) {
                heartRateDetailActivity.p.sendEmptyMessage(1);
            } else if (deviceInfo.getDeviceConnectState() == 2) {
                heartRateDetailActivity.p.sendEmptyMessage(2);
            } else {
                LogUtil.h("HealthHeartRate_HeartRateDetailActivity", "mDeviceStatusReceiver do nothing");
            }
        }

        private boolean c(DeviceInfo deviceInfo) {
            if (deviceInfo == null) {
                return false;
            }
            Map<String, DeviceCapability> a2 = jfq.c().a(1, deviceInfo.getDeviceIdentify(), "HealthHeartRate_HeartRateDetailActivity");
            if (a2 == null || a2.isEmpty()) {
                LogUtil.h("HealthHeartRate_HeartRateDetailActivity", "enter mDeviceStatusReceiver, deviceCapabilityHashMaps is null or empty");
                return false;
            }
            DeviceCapability deviceCapability = a2.get(deviceInfo.getDeviceIdentify());
            if (deviceCapability != null && deviceCapability.isSupportHeartRate()) {
                return true;
            }
            LogUtil.h("HealthHeartRate_HeartRateDetailActivity", "device not support heart rate.");
            return false;
        }
    }

    public static class a implements IBaseResponseCallback {
        private final WeakReference<HeartRateDetailActivity> c;

        a(HeartRateDetailActivity heartRateDetailActivity) {
            this.c = new WeakReference<>(heartRateDetailActivity);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            HeartRateDetailActivity heartRateDetailActivity = this.c.get();
            if (heartRateDetailActivity == null) {
                LogUtil.h("HealthHeartRate_HeartRateDetailActivity", "heartRateDetailActivity is null");
                return;
            }
            if (obj instanceof HiHealthData) {
                heartRateDetailActivity.q = ((HiHealthData) obj).getStartTime();
                LogUtil.a("HealthHeartRate_HeartRateDetailActivity", "read last data: ", Long.valueOf(heartRateDetailActivity.q));
                heartRateDetailActivity.j();
                return;
            }
            LogUtil.h("HealthHeartRate_HeartRateDetailActivity", "read last data time from database,mLastTimestamp=0");
            if (jpt.a("HealthHeartRate_HeartRateDetailActivity") != null || Utils.o()) {
                heartRateDetailActivity.j();
                return;
            }
            heartRateDetailActivity.l = false;
            final FrameLayout frameLayout = (FrameLayout) heartRateDetailActivity.findViewById(R.id.heart_rate_addfragment);
            frameLayout.post(new Runnable() { // from class: prj
                @Override // java.lang.Runnable
                public final void run() {
                    frameLayout.setVisibility(0);
                }
            });
            rzf.e(heartRateDetailActivity.getSupportFragmentManager(), CommonHealthNoDeviceFragment.b("HeartRateConstructor", 9), R.id.heart_rate_addfragment);
            heartRateDetailActivity.x.setVisibility(8);
            heartRateDetailActivity.c(360);
            EcgFilterManager.a().d(heartRateDetailActivity.w, 1, false);
        }
    }

    static class b implements HiSubscribeListener {
        private final WeakReference<HeartRateDetailActivity> c;

        b(HeartRateDetailActivity heartRateDetailActivity) {
            this.c = new WeakReference<>(heartRateDetailActivity);
        }

        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onResult(List<Integer> list, List<Integer> list2) {
            HeartRateDetailActivity heartRateDetailActivity = this.c.get();
            if (heartRateDetailActivity != null) {
                heartRateDetailActivity.ad = list;
            }
        }

        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onChange(int i, HiHealthClient hiHealthClient, String str, HiHealthData hiHealthData, long j) {
            HeartRateDetailActivity heartRateDetailActivity = this.c.get();
            if (heartRateDetailActivity != null && !heartRateDetailActivity.isFinishing()) {
                if (heartRateDetailActivity.r) {
                    heartRateDetailActivity.g();
                    return;
                }
                return;
            }
            LogUtil.h("HealthHeartRate_HeartRateDetailActivity", "HeartRateSubscribeListener onChange activity don't exist");
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
