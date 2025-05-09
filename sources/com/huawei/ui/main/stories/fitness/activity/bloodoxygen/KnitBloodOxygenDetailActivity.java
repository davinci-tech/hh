package com.huawei.ui.main.stories.fitness.activity.bloodoxygen;

import android.bluetooth.BluetoothAdapter;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import androidx.fragment.app.Fragment;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.health.R;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.knit.model.KnitSubPageConfig;
import com.huawei.health.knit.ui.KnitHealthDetailActivity;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.toolbar.HealthToolBar;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.activity.bloodoxygen.KnitBloodOxygenDetailActivity;
import com.huawei.ui.main.stories.privacy.template.model.bean.PageModelArgs;
import com.huawei.ui.main.stories.privacy.template.view.showdata.PrivacyDataModelActivity;
import com.huawei.ui.main.stories.template.PopMenuManager;
import com.huawei.ui.main.stories.template.data.IPopMenuItemClick;
import com.huawei.ui.main.stories.template.health.common.CommonHealthNoDeviceFragment;
import com.huawei.ui.main.stories.utils.FitnessUtils;
import com.huawei.ui.main.stories.utils.LastTimeHealthDataReader;
import defpackage.cjx;
import defpackage.cun;
import defpackage.cvs;
import defpackage.cwi;
import defpackage.dks;
import defpackage.gge;
import defpackage.gnm;
import defpackage.ixx;
import defpackage.jpt;
import defpackage.koq;
import defpackage.nrh;
import defpackage.nsf;
import defpackage.nsn;
import defpackage.ryf;
import defpackage.sdc;
import health.compact.a.EnvironmentInfo;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.Utils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.slf4j.Marker;

/* loaded from: classes6.dex */
public class KnitBloodOxygenDetailActivity extends KnitHealthDetailActivity {

    /* renamed from: a, reason: collision with root package name */
    private HealthToolBar f9743a;
    private LastTimeHealthDataReader<KnitBloodOxygenDetailActivity> b;
    private CustomTitleBar c;
    private long d;
    private HealthToolBar.OnSingleTapListener e = new HealthToolBar.OnSingleTapListener() { // from class: pjq
        @Override // com.huawei.ui.commonui.toolbar.HealthToolBar.OnSingleTapListener
        public final void onSingleTap(int i) {
            KnitBloodOxygenDetailActivity.this.b(i);
        }
    };

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public void configExtraView(LinearLayout linearLayout) {
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public boolean getExtra(Bundle bundle) {
        return true;
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public int getPageType() {
        return 20;
    }

    @Override // android.view.Window.Callback
    public void onPointerCaptureChanged(boolean z) {
    }

    public /* synthetic */ void b(int i) {
        if (i == 1) {
            d();
        } else {
            if (i == 3) {
                if (nsn.o()) {
                    return;
                }
                startActivity(new Intent(this, (Class<?>) BloodOxygenIntroducedActivity.class));
                return;
            }
            LogUtil.c("KnitBloodOxygenDetailActivity", "unKnow click");
        }
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        LogUtil.a("KnitBloodOxygenDetailActivity", "onCreate");
        super.onCreate(bundle);
        this.d = System.currentTimeMillis();
        cancelMarginAdaptation();
        g();
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        HealthViewPager viewPager = getViewPager();
        if (viewPager != null) {
            viewPager.setOffscreenPageLimit(3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b() {
        ArrayList<String> a2 = cjx.e().a(HealthDevice.HealthDeviceKind.HDK_BLOOD_OXYGEN);
        return !koq.b(a2) && a2.size() > 0;
    }

    private void g() {
        if (this.b == null) {
            this.b = new LastTimeHealthDataReader<>(this, new e());
        }
        this.b.b(LastTimeHealthDataReader.CardData.BLOOD_OXYGEN);
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        LogUtil.a("KnitBloodOxygenDetailActivity", "onResume");
        super.onResume();
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public Fragment onCreateEmptyFragment(KnitSubPageConfig knitSubPageConfig) {
        return CommonHealthNoDeviceFragment.b("BloodOxygenCardConstructor", 20);
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public void configTitleBar(CustomTitleBar customTitleBar) {
        this.c = customTitleBar;
        customTitleBar.setTitleText(getString(R$string.IDS_hw_health_blood_oxygen));
        b(this, this.c, 105);
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public DataInfos getSubPageDataInfos(int i) {
        if (i == 0) {
            return DataInfos.BloodOxygenDayDetail;
        }
        if (i == 1) {
            return DataInfos.BloodOxygenWeekDetail;
        }
        if (i == 2) {
            return DataInfos.BloodOxygenMonthDetail;
        }
        if (i == 3) {
            return DataInfos.BloodOxygenYearDetail;
        }
        return DataInfos.NoDataPlaceHolder;
    }

    private void b(final Context context, final CustomTitleBar customTitleBar, final int i) {
        customTitleBar.setRightButtonDrawable(BaseApplication.getContext().getResources().getDrawable(R.drawable._2131430194_res_0x7f0b0b32), nsf.h(R$string.accessibility_more_item));
        customTitleBar.setRightButtonClickable(true);
        customTitleBar.setRightButtonVisibility(0);
        final PopMenuManager popMenuManager = new PopMenuManager() { // from class: com.huawei.ui.main.stories.fitness.activity.bloodoxygen.KnitBloodOxygenDetailActivity.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.huawei.ui.main.stories.template.PopMenuManager
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public CustomTitleBar getTitleBar() {
                return customTitleBar;
            }

            @Override // com.huawei.ui.main.stories.template.IMenuItemManager
            public List<ryf> getMenuItemList() {
                return KnitBloodOxygenDetailActivity.this.e(context, i);
            }
        };
        customTitleBar.setRightButtonOnClickListener(new View.OnClickListener() { // from class: pjo
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                KnitBloodOxygenDetailActivity.dqe_(PopMenuManager.this, view);
            }
        });
    }

    public static /* synthetic */ void dqe_(PopMenuManager popMenuManager, View view) {
        popMenuManager.showPopMenu();
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ArrayList<ryf> e(final Context context, final int i) {
        ryf ryfVar = new ryf(getResources().getString(R$string.IDS_blood_oxygen_switch), new IPopMenuItemClick() { // from class: com.huawei.ui.main.stories.fitness.activity.bloodoxygen.KnitBloodOxygenDetailActivity.5
            @Override // com.huawei.ui.main.stories.template.data.IPopMenuItemClick
            public void onItemClick() {
                KnitBloodOxygenDetailActivity.this.a(context);
            }
        });
        ryf ryfVar2 = new ryf(getResources().getString(R$string.IDS_highland_health), new IPopMenuItemClick() { // from class: com.huawei.ui.main.stories.fitness.activity.bloodoxygen.KnitBloodOxygenDetailActivity.2
            @Override // com.huawei.ui.main.stories.template.data.IPopMenuItemClick
            public void onItemClick() {
                KnitBloodOxygenDetailActivity.this.c(context);
            }
        });
        ryf ryfVar3 = new ryf(getResources().getString(R$string.IDS_privacy_all_data), new IPopMenuItemClick() { // from class: com.huawei.ui.main.stories.fitness.activity.bloodoxygen.KnitBloodOxygenDetailActivity.4
            @Override // com.huawei.ui.main.stories.template.data.IPopMenuItemClick
            public void onItemClick() {
                KnitBloodOxygenDetailActivity.this.b(context, i);
            }
        });
        ryf ryfVar4 = new ryf(getResources().getString(R$string.IDS_fitness_core_sleep_explain_title), new IPopMenuItemClick() { // from class: com.huawei.ui.main.stories.fitness.activity.bloodoxygen.KnitBloodOxygenDetailActivity.3
            @Override // com.huawei.ui.main.stories.template.data.IPopMenuItemClick
            public void onItemClick() {
                KnitBloodOxygenDetailActivity.this.f();
            }
        });
        ArrayList<ryf> arrayList = new ArrayList<>();
        if (!EnvironmentInfo.k()) {
            arrayList.add(ryfVar);
        }
        if (!Utils.o() && !EnvironmentInfo.k()) {
            arrayList.add(ryfVar2);
        }
        arrayList.add(ryfVar3);
        arrayList.add(ryfVar4);
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        e(getApplicationContext(), AnalyticsValue.BLOOD_OXYGEN_PAGE_CLICK.value(), "6");
        startActivity(new Intent(this, (Class<?>) BloodOxygenIntroducedActivity.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Context context, int i) {
        PageModelArgs pageModelArgs = new PageModelArgs(i, "PrivacyDataConstructor", 3, 1);
        pageModelArgs.setClassType(0);
        PrivacyDataModelActivity.b(context, pageModelArgs);
    }

    private void c(String str) {
        DeviceInfo d = jpt.d("KnitBloodOxygenDetailActivity");
        String multiLinkBleMac = d != null ? d.getMultiLinkBleMac() : "";
        Intent intent = new Intent();
        intent.setClassName(BaseApplication.getContext(), str);
        intent.setFlags(268435456);
        if (!TextUtils.isEmpty(multiLinkBleMac)) {
            intent.putExtra("device_id", multiLinkBleMac);
        }
        intent.setFlags(AppRouterExtras.COLDSTART);
        gnm.aPB_(BaseApplication.getContext(), intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Context context) {
        if (e()) {
            sdc.e(context, R$string.IDS_device_wifi_go_connect, R$string.IDS_core_sleep_not_connect);
            return;
        }
        DeviceCapability d = cvs.d();
        if (d != null && d.isSupportCycleBloodOxygenSwitch()) {
            c("com.huawei.ui.device.activity.bloodoxygen.CycleBloodOxygenSettingActivity");
        } else {
            sdc.e(context, R$string.IDS_device_wifi_go_connect, R$string.IDS_hw_show_main_device_not_connect_blood_oxygen);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(Context context) {
        if (e()) {
            sdc.e(context, R$string.IDS_device_wifi_go_connect, R$string.IDS_core_sleep_not_connect);
        } else if (cwi.c(jpt.a("KnitBloodOxygenDetailActivity"), 72) && !Utils.o()) {
            c("com.huawei.ui.device.activity.bloodoxygen.HighLandBloodOxygenSettingActivity");
        } else {
            sdc.e(context, R$string.IDS_device_wifi_go_connect, R$string.IDS_hw_show_main_device_not_connect);
        }
    }

    public boolean e() {
        DeviceInfo deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, "KnitBloodOxygenDetailActivity");
        return deviceInfo == null || deviceInfo.getDeviceConnectState() != 2;
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public void configToolBar(HealthToolBar healthToolBar) {
        if (b()) {
            this.f9743a = healthToolBar;
            a();
        }
    }

    private void a() {
        this.f9743a.cHc_(View.inflate(this, R.layout.hw_toolbar_bottomview, null));
        this.f9743a.setOnSingleTapListener(this.e);
        this.f9743a.cHf_(this);
        this.f9743a.setIcon(1, R.drawable._2131430272_res_0x7f0b0b80);
        this.f9743a.setIconTitle(1, getResources().getString(R$string.IDS_hw_show_main_health_page_healthdata_bloodpresure_mesure));
        this.f9743a.setIcon(3, R.mipmap._2131821482_res_0x7f1103aa);
        this.f9743a.setIconTitle(3, getResources().getString(R$string.IDS_fitness_core_sleep_explain_title));
    }

    private void d() {
        LoginInit.getInstance(this).browsingToLogin(new IBaseResponseCallback() { // from class: pjr
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                KnitBloodOxygenDetailActivity.this.e(i, obj);
            }
        }, AnalyticsValue.HEALTH_HEALTH_BLOOD_OXYGEN_DETAIL_MEASURE_2060051.value());
    }

    public /* synthetic */ void e(int i, Object obj) {
        if (i == 0) {
            h();
        } else {
            LogUtil.h("KnitBloodOxygenDetailActivity", "browsingToLogin errorCode is not success", Integer.valueOf(i));
        }
    }

    private void h() {
        LogUtil.a("KnitBloodOxygenDetailActivity", "startMeasure enter");
        if (nsn.o()) {
            return;
        }
        final BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter != null && defaultAdapter.getState() == 12) {
            j();
            return;
        }
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this);
        builder.e(R$string.IDS_device_bluetooth_open_request);
        builder.czC_(R$string.IDS_device_ui_dialog_yes, new View.OnClickListener() { // from class: pjl
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                KnitBloodOxygenDetailActivity.this.dqi_(defaultAdapter, view);
            }
        });
        builder.czz_(R$string.IDS_device_ui_dialog_no, new View.OnClickListener() { // from class: pjk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        NoTitleCustomAlertDialog e2 = builder.e();
        e2.setCancelable(false);
        if (isFinishing()) {
            return;
        }
        e2.show();
    }

    public /* synthetic */ void dqi_(BluetoothAdapter bluetoothAdapter, View view) {
        if (bluetoothAdapter == null) {
            LogUtil.h("KnitBloodOxygenDetailActivity", "startMesure bluetoothAdapter is null");
            ViewClickInstrumentation.clickOnView(view);
        } else if (Build.VERSION.SDK_INT > 30 && PermissionUtil.e(this, PermissionUtil.PermissionType.SCAN) != PermissionUtil.PermissionResult.GRANTED) {
            LogUtil.h("KnitBloodOxygenDetailActivity", "no scan Permission");
            PermissionUtil.bFX_(this, PermissionUtil.c(PermissionUtil.PermissionType.SCAN), new PermissionsResultAction() { // from class: com.huawei.ui.main.stories.fitness.activity.bloodoxygen.KnitBloodOxygenDetailActivity.10
                @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                public void onGranted() {
                    KnitBloodOxygenDetailActivity.this.c();
                }

                @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                public void onDenied(String str) {
                    nrh.b(this, R$string.IDS_nearby_permission_exception);
                }
            });
            ViewClickInstrumentation.clickOnView(view);
        } else {
            c();
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        try {
            startActivityForResult(new Intent("android.bluetooth.adapter.action.REQUEST_ENABLE"), 1);
        } catch (ActivityNotFoundException unused) {
            LogUtil.h("KnitBloodOxygenDetailActivity", "createBluetoothDialog ActivityNotFoundException");
        } catch (SecurityException unused2) {
            ReleaseLogUtil.c("KnitBloodOxygenDetailActivity", "createBluetoothDialog SecurityException");
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1 && i2 == -1) {
            j();
        }
    }

    private void j() {
        Intent intent = new Intent();
        intent.setPackage(com.huawei.haf.application.BaseApplication.d());
        intent.setClassName(com.huawei.haf.application.BaseApplication.d(), "com.huawei.health.device.ui.DeviceMainActivity");
        intent.putExtra("kind", "HDK_BLOOD_OXYGEN");
        if (b()) {
            String value = AnalyticsValue.HEALTH_HEALTH_BLOOD_OXYGEN_DETAIL_MEASURE_2060051.value();
            HashMap hashMap = new HashMap(16);
            hashMap.put("click", "1");
            ixx.d().d(BaseApplication.getActivity(), value, hashMap, 0);
            intent.putExtra("view", "MeasureDevice");
            startActivity(intent);
            return;
        }
        i();
    }

    private void i() {
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this);
        builder.e(getString(R$string.IDS_hw_plugin_device_selection_click_bind_my_device_select)).czE_(getString(R$string.IDS_hw_health_show_common_dialog_ok_button), new View.OnClickListener() { // from class: pjt
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                KnitBloodOxygenDetailActivity.dqf_(view);
            }
        }).czA_(getString(R$string.IDS_hw_health_show_common_dialog_cancle_button), new View.OnClickListener() { // from class: pjs
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                KnitBloodOxygenDetailActivity.dqg_(view);
            }
        });
        builder.e().show();
    }

    public static /* synthetic */ void dqf_(View view) {
        String value = AnalyticsValue.HEALTH_HEALTH_BLOOD_OXYGEN_DETAIL_BIND_2060052.value();
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        ixx.d().d(BaseApplication.getActivity(), value, hashMap, 0);
        dks.e(BaseApplication.getActivity(), "HDK_BLOOD_OXYGEN");
        ViewClickInstrumentation.clickOnView(view);
    }

    public static /* synthetic */ void dqg_(View view) {
        LogUtil.a("KnitBloodOxygenDetailActivity", "onClick negative button");
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        LogUtil.a("KnitBloodOxygenDetailActivity", "KnitBloodOxygenDetailActivity do destory");
        long currentTimeMillis = this.d > 0 ? System.currentTimeMillis() - this.d : 0L;
        HashMap hashMap = new HashMap(16);
        hashMap.put("keeptime", Long.valueOf(currentTimeMillis));
        gge.e(AnalyticsValue.HEALTH_HEALTH_HEALTHDATA_BLOOD_OXYGEN_2030063.value(), hashMap);
    }

    public static void a(Context context, long j) {
        FitnessUtils.c(context, KnitBloodOxygenDetailActivity.class, j);
    }

    static class e implements IBaseResponseCallback {
        private final WeakReference<KnitBloodOxygenDetailActivity> c;

        private e(KnitBloodOxygenDetailActivity knitBloodOxygenDetailActivity) {
            this.c = new WeakReference<>(knitBloodOxygenDetailActivity);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(final int i, final Object obj) {
            long j;
            if (!HandlerExecutor.c()) {
                HandlerExecutor.a(new Runnable() { // from class: com.huawei.ui.main.stories.fitness.activity.bloodoxygen.KnitBloodOxygenDetailActivity.e.5
                    @Override // java.lang.Runnable
                    public void run() {
                        e.this.d(i, obj);
                    }
                });
                return;
            }
            KnitBloodOxygenDetailActivity knitBloodOxygenDetailActivity = this.c.get();
            if (knitBloodOxygenDetailActivity == null) {
                LogUtil.h("KnitBloodOxygenDetailActivity", "LastTimeCallback activity is null");
                return;
            }
            if (obj instanceof HiHealthData) {
                j = ((HiHealthData) obj).getStartTime();
                LogUtil.a("KnitBloodOxygenDetailActivity", "read last data time from database,mLastTimestamp=", Long.valueOf(j));
            } else {
                LogUtil.h("KnitBloodOxygenDetailActivity", "read last data time from database,mLastTimestamp=0");
                j = 0;
            }
            DeviceInfo a2 = jpt.a("KnitBloodOxygenDetailActivity");
            if (j != 0 || a2 != null || knitBloodOxygenDetailActivity.b() || Utils.o()) {
                knitBloodOxygenDetailActivity.refreshTitleBar(true);
                knitBloodOxygenDetailActivity.hideEmptyFragment();
            } else {
                knitBloodOxygenDetailActivity.showEmptyFragment();
                knitBloodOxygenDetailActivity.refreshTitleBar(false);
                knitBloodOxygenDetailActivity.c.setVisibility(8);
            }
        }
    }

    private void e(Context context, String str, String str2) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        if (str2 != null) {
            hashMap.put("type", str2);
        }
        ixx.d().d(context, str, hashMap, 0);
        LogUtil.a("KnitBloodOxygenDetailActivity", "BICollect", str + Marker.ANY_NON_NULL_MARKER + hashMap.get("type"));
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public void onKnitPageSelected(int i) {
        super.onKnitPageSelected(i);
        a(i);
    }

    private void a(int i) {
        HashMap hashMap = new HashMap(16);
        String str = i != 0 ? i != 1 ? i != 2 ? i != 3 ? "Err" : "Year" : "Month" : "Week" : "Day";
        hashMap.put("click", 1);
        hashMap.put("click_type", str);
        gge.e(AnalyticsValue.HEALTH_HEALTH_HEALTHDATA_BLOOD_OXYGEN_2030064.value(), hashMap);
        LogUtil.a("KnitBloodOxygenDetailActivity", "addOnPageChangeListener onPageSelected position = ", Integer.valueOf(i));
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public String getLogTag() {
        return "KnitBloodOxygenDetailActivity";
    }
}
