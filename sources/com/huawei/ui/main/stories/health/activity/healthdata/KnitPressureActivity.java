package com.huawei.ui.main.stories.health.activity.healthdata;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Process;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.h5pro.preload.H5PreloadCountStrategy;
import com.huawei.health.knit.model.KnitSubPageConfig;
import com.huawei.health.knit.section.listener.BasePageResTrigger;
import com.huawei.health.knit.section.listener.IPageResTrigger;
import com.huawei.health.knit.ui.KnitFragment;
import com.huawei.health.knit.ui.KnitHealthDetailActivity;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.h5pro.preload.H5ProPkgPreloadSyncTask;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.toolbar.HealthToolBar;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.activity.pressuremeasure.PressureIntroduceActivity;
import com.huawei.ui.main.stories.health.activity.healthdata.KnitPressureActivity;
import com.huawei.ui.main.stories.privacy.template.model.bean.PageModelArgs;
import com.huawei.ui.main.stories.privacy.template.view.showdata.PrivacyDataModelActivity;
import com.huawei.ui.main.stories.template.PopMenuManager;
import com.huawei.ui.main.stories.template.data.IPopMenuItemClick;
import com.huawei.ui.main.stories.utils.FitnessUtils;
import com.huawei.ui.main.stories.utils.LastTimeHealthDataReader;
import defpackage.arx;
import defpackage.bmm;
import defpackage.cun;
import defpackage.cvs;
import defpackage.dkx;
import defpackage.gge;
import defpackage.ixx;
import defpackage.jpt;
import defpackage.koq;
import defpackage.moj;
import defpackage.nro;
import defpackage.nsf;
import defpackage.pvw;
import defpackage.ryf;
import defpackage.scw;
import defpackage.sdc;
import health.compact.a.EnvironmentInfo;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes.dex */
public class KnitPressureActivity extends KnitHealthDetailActivity {

    /* renamed from: a, reason: collision with root package name */
    private long f10066a;
    private boolean b;
    private Context c;
    private Bundle e;
    private LastTimeHealthDataReader<KnitPressureActivity> h;
    private pvw j;
    private String d = null;
    private boolean i = true;

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public void configExtraView(LinearLayout linearLayout) {
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public int configSubTabStyle() {
        return R.layout.knit_sub_tab_widget_pressure;
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public void configToolBar(HealthToolBar healthToolBar) {
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public int getPageType() {
        return 3;
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            c();
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        LogUtil.a("KnitPressureActivity", "onStart");
        getViewPager().setOffscreenPageLimit(4);
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.c = this;
        i();
        dBb_(getIntent());
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (this.h == null) {
            this.h = new LastTimeHealthDataReader<>(this, new a(this, this.e));
        }
        this.h.b(LastTimeHealthDataReader.CardData.PRESSURE);
        boolean z = this.i;
        if (!z || this.b) {
            return;
        }
        LogUtil.a("KnitPressureActivity", "onResume mIsFirstOnResume = ", Boolean.valueOf(z));
        m();
        this.i = false;
    }

    private void m() {
        H5ProPkgPreloadSyncTask.startTask(BaseApplication.getContext(), "com.huawei.health.h5.breath-training", new H5PreloadCountStrategy(1));
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public Fragment onCreateEmptyFragment(KnitSubPageConfig knitSubPageConfig) {
        hideNonEmptyFragment();
        if (knitSubPageConfig == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        int resPosId = knitSubPageConfig.getResPosId();
        String e = moj.e(knitSubPageConfig);
        IPageResTrigger extra = getResTrigger(getPageType(), resPosId, false).setExtra(bundle);
        if (extra instanceof BasePageResTrigger) {
            return KnitFragment.newInstance(e, (BasePageResTrigger) extra);
        }
        return null;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        dBb_(intent);
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public void configTitleBar(final CustomTitleBar customTitleBar) {
        customTitleBar.setTitleText(getString(R$string.IDS_settings_one_level_menu_settings_item_text_id14));
        customTitleBar.setRightButtonDrawable(nsf.cKq_(R.drawable._2131430194_res_0x7f0b0b32), nsf.h(R$string.accessibility_more_item));
        customTitleBar.setRightButtonClickable(true);
        customTitleBar.setRightButtonVisibility(0);
        final PopMenuManager popMenuManager = new PopMenuManager() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.KnitPressureActivity.3
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.huawei.ui.main.stories.template.PopMenuManager
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public CustomTitleBar getTitleBar() {
                return customTitleBar;
            }

            @Override // com.huawei.ui.main.stories.template.IMenuItemManager
            public List<ryf> getMenuItemList() {
                return KnitPressureActivity.this.a();
            }
        };
        customTitleBar.setRightButtonOnClickListener(new View.OnClickListener() { // from class: qeh
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                KnitPressureActivity.dBa_(PopMenuManager.this, view);
            }
        });
    }

    public static /* synthetic */ void dBa_(PopMenuManager popMenuManager, View view) {
        popMenuManager.showPopMenu();
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ArrayList<ryf> a() {
        ryf ryfVar = new ryf(getResources().getString(R$string.IDS_press_auto_monitor), new IPopMenuItemClick() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.KnitPressureActivity.1
            @Override // com.huawei.ui.main.stories.template.data.IPopMenuItemClick
            public void onItemClick() {
                KnitPressureActivity.this.j();
            }
        });
        ryf ryfVar2 = new ryf(getResources().getString(R$string.IDS_privacy_all_data), new IPopMenuItemClick() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.KnitPressureActivity.5
            @Override // com.huawei.ui.main.stories.template.data.IPopMenuItemClick
            public void onItemClick() {
                KnitPressureActivity.this.h();
            }
        });
        ryf ryfVar3 = new ryf(getResources().getString(R$string.IDS_hw_pressure_explain), new IPopMenuItemClick() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.KnitPressureActivity.4
            @Override // com.huawei.ui.main.stories.template.data.IPopMenuItemClick
            public void onItemClick() {
                KnitPressureActivity.this.g();
            }
        });
        ArrayList<ryf> arrayList = new ArrayList<>();
        if (!EnvironmentInfo.k() && !d()) {
            arrayList.add(ryfVar);
        }
        arrayList.add(ryfVar2);
        arrayList.add(ryfVar3);
        return arrayList;
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public DataInfos getSubPageDataInfos(int i) {
        if (i == 0) {
            return DataInfos.PressureDayDetail;
        }
        if (i == 1) {
            return DataInfos.PressureWeekDetail;
        }
        if (i == 2) {
            return DataInfos.PressureMonthDetail;
        }
        if (i == 3) {
            return DataInfos.PressureYearDetail;
        }
        return DataInfos.NoDataPlaceHolder;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        PageModelArgs pageModelArgs = new PageModelArgs(104, "PrivacyDataConstructor", 3, 1);
        pageModelArgs.setClassType(0);
        PrivacyDataModelActivity.b(this.c, pageModelArgs);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        gge.e(AnalyticsValue.HEALTH_PRESSUER_HELP_CLICK_2160003.value(), hashMap);
        try {
            this.c.startActivity(new Intent(this.c, (Class<?>) PressureIntroduceActivity.class));
        } catch (ActivityNotFoundException e) {
            LogUtil.b("KnitPressureActivity", "ActivityNotFoundException", e.getMessage());
        }
    }

    private boolean d() {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "KnitPressureActivity");
        if (!koq.b(deviceList)) {
            return (!bmm.a(deviceList.get(0), 206) || Process.is64Bit() || dkx.b()) ? false : true;
        }
        sdc.e(this.c, R$string.IDS_device_wifi_go_connect, R$string.IDS_core_sleep_not_connect);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        if (koq.b(cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_DEVICES, null, "KnitPressureActivity"))) {
            sdc.e(this, R$string.IDS_device_wifi_go_connect, R$string.IDS_core_sleep_not_connect);
            return;
        }
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "KnitPressureActivity");
        if (koq.b(deviceList)) {
            sdc.e(this.c, R$string.IDS_device_wifi_go_connect, R$string.IDS_hw_show_main_device_not_connect);
            return;
        }
        DeviceInfo deviceInfo = deviceList.get(0);
        DeviceCapability e = cvs.e(deviceInfo.getDeviceIdentify());
        if (e == null || !e.isSupportPressAutoMonitor()) {
            sdc.e(this.c, R$string.IDS_device_wifi_go_connect, R$string.IDS_hw_show_main_device_not_connect);
            return;
        }
        String multiLinkBleMac = deviceInfo.getMultiLinkBleMac();
        Intent intent = new Intent();
        intent.setClassName(this.c, "com.huawei.ui.device.activity.pressautomonitor.PressAutoMonitorActivity");
        intent.setFlags(268435456);
        if (!TextUtils.isEmpty(multiLinkBleMac)) {
            intent.putExtra("device_id", multiLinkBleMac);
        }
        intent.setFlags(AppRouterExtras.COLDSTART);
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e2) {
            LogUtil.b("KnitPressureActivity", "ActivityNotFoundException", e2.getMessage());
        }
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public boolean getExtra(Bundle bundle) {
        Intent intent = getIntent();
        this.e = bundle;
        if (intent == null) {
            return true;
        }
        long longExtra = intent.getLongExtra("key_bundle_health_last_data_time", 0L);
        this.f10066a = longExtra;
        bundle.putLong("key_bundle_health_last_data_time", longExtra);
        long j = this.f10066a;
        if (j <= 0) {
            return false;
        }
        bundle.putLong("key_bundle_health_last_data_time", j);
        return true;
    }

    private void i() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.getStringExtra("healthdata") != null) {
                this.d = intent.getStringExtra("healthdata");
            }
            boolean booleanExtra = intent.getBooleanExtra("refreshCard", false);
            LogUtil.a("KnitPressureActivity", "mIsRefreshCard = ", Boolean.valueOf(booleanExtra));
            if (booleanExtra) {
                nro.e(this.c, 6);
            }
            e();
            dAZ_(intent);
        }
    }

    private void dAZ_(final Intent intent) {
        HandlerExecutor.d(new Runnable() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.KnitPressureActivity.2
            @Override // java.lang.Runnable
            public void run() {
                ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.KnitPressureActivity.2.5
                    @Override // java.lang.Runnable
                    public void run() {
                        int b = KnitPressureActivity.this.b();
                        HashMap hashMap = new HashMap();
                        hashMap.put("click", 1);
                        hashMap.put("isHaveData", Integer.valueOf(intent.getIntExtra("isHaveData", KnitPressureActivity.this.b ? 1 : 0)));
                        hashMap.put("havedevice", Integer.valueOf(b));
                        hashMap.put("type", Integer.valueOf(intent.getIntExtra("type", -1)));
                        LogUtil.a("KnitPressureActivity", "biEvent map ", hashMap);
                        ixx.d().d(arx.b(), AnalyticsValue.HEALTH_PRESSUER_CARD_CLICK_2160001.value(), hashMap, 0);
                    }
                });
            }
        }, 2000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        Intent intent = getIntent();
        long j = this.f10066a;
        boolean z = j > 0;
        if (intent != null) {
            z = intent.getBooleanExtra("pressure_is_have_datas", j > 0);
        }
        LogUtil.a("KnitPressureActivity", "checkHasPressureData, mHasPressureData: ", Boolean.valueOf(this.b), ", hasData: ", Boolean.valueOf(z));
        if (!z || this.b) {
            return;
        }
        o();
    }

    private void c() {
        if ("MyHealthData".equals(this.d)) {
            LogUtil.a("KnitPressureActivity", "onBackPressed jumpToDeviceActivity is Success");
            Intent intent = new Intent();
            intent.setPackage(BaseApplication.getAppPackage());
            intent.setClassName(BaseApplication.getAppPackage(), "com.huawei.health.device.ui.DeviceMainActivity");
            intent.putExtra("kind", "HDK_UNKNOWN");
            intent.putExtra("view", "ListDevice");
            startActivity(intent);
        }
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int b() {
        DeviceInfo a2 = jpt.a("KnitPressureActivity");
        return (a2 == null || a2.getDeviceConnectState() != 2) ? 0 : 1;
    }

    public static void b(Context context, long j) {
        FitnessUtils.c(context, KnitPressureActivity.class, j);
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        LogUtil.a("KnitPressureActivity", "destroy");
    }

    private void dBb_(Intent intent) {
        if (intent == null || !intent.getBooleanExtra("start_game", false)) {
            return;
        }
        f();
    }

    private void f() {
        if (this.j == null) {
            this.j = new pvw();
        }
        this.j.b(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        this.b = false;
        showEmptyFragment();
        hideNonEmptyFragment();
        refreshTitleBar(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        this.b = true;
        hideEmptyFragment();
        showNonEmptyFragment();
        refreshTitleBar(true);
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public void refreshNoDataTitleBar(CustomTitleBar customTitleBar) {
        super.refreshNoDataTitleBar(customTitleBar);
        if (isDeviceConnected()) {
            customTitleBar.setTitleBarBackgroundColor(getColor(R.color._2131298875_res_0x7f090a3b));
            customTitleBar.setLeftButtonTextColor(ContextCompat.getDrawable(this, scw.b()), getColor(R.color._2131299236_res_0x7f090ba4), nsf.h(R$string.accessibility_go_back));
        }
    }

    /* loaded from: classes6.dex */
    class a implements IBaseResponseCallback {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference<KnitPressureActivity> f10068a;
        private Bundle b;

        private a(KnitPressureActivity knitPressureActivity, Bundle bundle) {
            this.f10068a = new WeakReference<>(knitPressureActivity);
            this.b = bundle;
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(final int i, final Object obj) {
            if (!HandlerExecutor.c()) {
                HandlerExecutor.a(new Runnable() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.KnitPressureActivity.a.5
                    @Override // java.lang.Runnable
                    public void run() {
                        a.this.d(i, obj);
                    }
                });
                return;
            }
            KnitPressureActivity knitPressureActivity = this.f10068a.get();
            if (knitPressureActivity == null) {
                return;
            }
            if (obj instanceof HiHealthData) {
                knitPressureActivity.f10066a = ((HiHealthData) obj).getEndTime();
                if (knitPressureActivity.f10066a == 0) {
                    KnitPressureActivity.this.l();
                } else {
                    KnitPressureActivity.this.o();
                }
                LogUtil.a("KnitPressureActivity", "read last data time from database,mLastTimestamp=", Long.valueOf(knitPressureActivity.f10066a));
            } else {
                KnitPressureActivity.this.l();
                knitPressureActivity.f10066a = 0L;
                LogUtil.h("KnitPressureActivity", "read last data time from database,mLastTimestamp=0");
            }
            Bundle bundle = this.b;
            if (bundle != null) {
                if (bundle.getLong("key_bundle_health_last_data_time") == 0 && knitPressureActivity.f10066a != 0) {
                    LogUtil.a("KnitPressureActivity", "lastTimestamp is not 0, onBundleArrived");
                    this.b.putLong("key_bundle_health_last_data_time", knitPressureActivity.f10066a);
                    KnitPressureActivity.this.onBundleArrived(this.b);
                } else {
                    knitPressureActivity.configHealthDetailActivity();
                }
                this.b.putLong("key_bundle_health_last_data_time", knitPressureActivity.f10066a);
            }
            KnitPressureActivity.this.e();
        }
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public String getLogTag() {
        return "KnitPressureActivity";
    }
}
