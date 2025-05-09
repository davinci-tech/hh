package com.huawei.ui.main.stories.health.activity.healthdata;

import android.bluetooth.BluetoothAdapter;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.health.R;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
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
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.toolbar.HealthToolBar;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.activity.healthdata.KnitTemperatureActivity;
import com.huawei.ui.main.stories.health.temperature.activity.TemperatureIntroduceActivity;
import com.huawei.ui.main.stories.privacy.template.model.bean.PageModelArgs;
import com.huawei.ui.main.stories.privacy.template.view.showdata.PrivacyDataModelActivity;
import com.huawei.ui.main.stories.template.PopMenuManager;
import com.huawei.ui.main.stories.template.data.IPopMenuItemClick;
import com.huawei.ui.main.stories.template.health.common.CommonHealthNoDeviceFragment;
import com.huawei.ui.main.stories.template.health.module.HealthDataDetailActivity;
import com.huawei.ui.main.stories.utils.LastTimeHealthDataReader;
import defpackage.cez;
import defpackage.cjx;
import defpackage.cun;
import defpackage.dks;
import defpackage.gge;
import defpackage.gnm;
import defpackage.ixx;
import defpackage.jll;
import defpackage.jpt;
import defpackage.koq;
import defpackage.moj;
import defpackage.nrh;
import defpackage.nro;
import defpackage.nrz;
import defpackage.nsf;
import defpackage.nsn;
import defpackage.qpk;
import defpackage.ryf;
import defpackage.sdc;
import health.compact.a.EnvironmentInfo;
import health.compact.a.LanguageUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.Utils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes6.dex */
public class KnitTemperatureActivity extends KnitHealthDetailActivity {
    private Context b;
    private HealthToolBar d;
    private boolean e;
    private CustomTitleBar g;
    private boolean h;
    private e i;
    private LastTimeHealthDataReader<KnitTemperatureActivity> j;
    private boolean f = false;

    /* renamed from: a, reason: collision with root package name */
    private long f10069a = -1;
    private String c = null;

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public void configExtraView(LinearLayout linearLayout) {
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public int getPageType() {
        return 24;
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        qpk.d(true);
        this.b = this;
        f();
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public Fragment onCreateEmptyFragment(KnitSubPageConfig knitSubPageConfig) {
        LogUtil.a("Knit_TemperatureActivity", "onCreateEmptyFragment");
        this.h = Utils.o();
        if (knitSubPageConfig != null) {
            Bundle bundle = new Bundle();
            int resPosId = knitSubPageConfig.getResPosId();
            String e2 = moj.e(knitSubPageConfig);
            IPageResTrigger extra = getResTrigger(getPageType(), resPosId, false).setExtra(bundle);
            if (extra instanceof BasePageResTrigger) {
                this.f = true;
                return KnitFragment.newInstance(e2, (BasePageResTrigger) extra);
            }
        }
        return CommonHealthNoDeviceFragment.b("BodyTemperatureCardConstructor", 24);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4 && "MyHealthData".equals(this.c)) {
            b();
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public void configTitleBar(final CustomTitleBar customTitleBar) {
        LogUtil.b("Knit_TemperatureActivity", "goto configTitleBar");
        if (this.b == null) {
            this.b = BaseApplication.getContext();
        }
        this.g = customTitleBar;
        if (this.h) {
            customTitleBar.setTitleText(getString(R$string.IDS_health_skin_temperature));
        } else {
            customTitleBar.setTitleText(getString(R$string.IDS_settings_health_temperature));
        }
        this.g.setRightButtonDrawable(ContextCompat.getDrawable(this, R.drawable._2131430194_res_0x7f0b0b32), nsf.h(R$string.accessibility_more_item));
        this.g.setRightButtonClickable(true);
        this.g.setRightButtonVisibility(0);
        LogUtil.b("Knit_TemperatureActivity", "View.VISIBLE configTitleBar");
        final PopMenuManager popMenuManager = new PopMenuManager() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.KnitTemperatureActivity.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.huawei.ui.main.stories.template.PopMenuManager
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public CustomTitleBar getTitleBar() {
                return customTitleBar;
            }

            @Override // com.huawei.ui.main.stories.template.IMenuItemManager
            public List<ryf> getMenuItemList() {
                return KnitTemperatureActivity.this.j();
            }
        };
        customTitleBar.setRightButtonOnClickListener(new View.OnClickListener() { // from class: qep
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                KnitTemperatureActivity.dBd_(PopMenuManager.this, view);
            }
        });
        m();
    }

    public static /* synthetic */ void dBd_(PopMenuManager popMenuManager, View view) {
        popMenuManager.showPopMenu();
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ArrayList<ryf> j() {
        ryf ryfVar = new ryf(a(), new IPopMenuItemClick() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.KnitTemperatureActivity.4
            @Override // com.huawei.ui.main.stories.template.data.IPopMenuItemClick
            public void onItemClick() {
                KnitTemperatureActivity.this.i();
            }
        });
        ryf ryfVar2 = new ryf(getResources().getString(R$string.IDS_privacy_all_data), new IPopMenuItemClick() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.KnitTemperatureActivity.5
            @Override // com.huawei.ui.main.stories.template.data.IPopMenuItemClick
            public void onItemClick() {
                KnitTemperatureActivity.this.o();
            }
        });
        ryf ryfVar3 = new ryf(getResources().getString(R$string.IDS_fitness_core_sleep_explain_title), new IPopMenuItemClick() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.KnitTemperatureActivity.1
            @Override // com.huawei.ui.main.stories.template.data.IPopMenuItemClick
            public void onItemClick() {
                KnitTemperatureActivity.this.k();
            }
        });
        ArrayList<ryf> arrayList = new ArrayList<>();
        if (!EnvironmentInfo.k()) {
            arrayList.add(ryfVar);
        }
        arrayList.add(ryfVar2);
        arrayList.add(ryfVar3);
        return arrayList;
    }

    private String a() {
        if (Utils.o()) {
            return getString(R$string.IDS_continuous_monitoring_skins);
        }
        if (jll.d()) {
            return getString(R$string.IDS_temp_hyperthermia_warning);
        }
        return getString(R$string.IDS_temp_monitoring);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        b(0);
        PageModelArgs pageModelArgs = new PageModelArgs(this.h ? 113 : 106, "PrivacyDataConstructor", 3, 1);
        pageModelArgs.setClassType(0);
        PrivacyDataModelActivity.b(this.b, pageModelArgs);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        b(1);
        try {
            gnm.aPB_(this.b, new Intent(this.b, (Class<?>) TemperatureIntroduceActivity.class));
        } catch (ActivityNotFoundException e2) {
            LogUtil.b("Knit_TemperatureActivity", "ActivityNotFoundException", e2.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        DeviceInfo deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, "Knit_TemperatureActivity");
        if (deviceInfo == null || deviceInfo.getDeviceConnectState() != 2) {
            sdc.e(this.b, R$string.IDS_device_wifi_go_connect, R$string.IDS_core_sleep_not_connect);
            return;
        }
        if (!jll.c()) {
            sdc.e(this.b, R$string.IDS_device_wifi_go_connect, R$string.IDS_hw_show_main_device_not_connect);
            return;
        }
        DeviceInfo d = jpt.d("Knit_TemperatureActivity");
        String multiLinkBleMac = d != null ? d.getMultiLinkBleMac() : "";
        Intent intent = new Intent();
        intent.setClassName(this.b, "com.huawei.ui.device.activity.temperature.TemperatureMonitoringActivity");
        intent.setFlags(268435456);
        if (!TextUtils.isEmpty(multiLinkBleMac)) {
            intent.putExtra("device_id", multiLinkBleMac);
        }
        intent.setFlags(AppRouterExtras.COLDSTART);
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e2) {
            LogUtil.b("Knit_TemperatureActivity", "ActivityNotFoundException", e2.getMessage());
        }
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public void configToolBar(HealthToolBar healthToolBar) {
        LogUtil.b("Knit_TemperatureActivity", "goto configToolBar");
        if (this.h) {
            return;
        }
        this.d = healthToolBar;
        this.d.cHc_(View.inflate(this.b, R.layout.hw_toolbar_bottomview, null));
        this.d.setIcon(1, dBc_());
        HealthToolBar healthToolBar2 = this.d;
        healthToolBar2.setIconTitle(1, healthToolBar2.getResources().getString(R$string.IDS_hw_health_show_healthdata_input));
        this.d.setIcon(3, R.mipmap._2131821130_res_0x7f11024a);
        HealthToolBar healthToolBar3 = this.d;
        healthToolBar3.setIconTitle(3, healthToolBar3.getResources().getString(R$string.IDS_device_group_name_temperature_scale));
        if (EnvironmentInfo.k()) {
            this.d.setIconVisible(3, 8);
        }
        this.d.setOnSingleTapListener(new HealthToolBar.OnSingleTapListener() { // from class: qeo
            @Override // com.huawei.ui.commonui.toolbar.HealthToolBar.OnSingleTapListener
            public final void onSingleTap(int i) {
                KnitTemperatureActivity.this.e(i);
            }
        });
        setMinibarBottomMargin(getResources().getDimensionPixelSize(R.dimen._2131363906_res_0x7f0a0842));
    }

    public /* synthetic */ void e(int i) {
        if (nsn.a(500)) {
            return;
        }
        if (i == 1) {
            c(String.valueOf(1), AnalyticsValue.HEALTH_HEALTH_TEMPERATURE_INPUT_2060049.value());
        } else if (i == 3) {
            c(String.valueOf(3), AnalyticsValue.HEALTH_PLUGIN_DEVICE_BEGIN_MEASURE_2060043.value());
        } else {
            LogUtil.h("Knit_TemperatureActivity", "unKnow click");
        }
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public boolean getExtra(Bundle bundle) {
        long j = bundle.getLong("key_bundle_health_last_data_time", 0L);
        long j2 = bundle.getLong("key_marker_view_time", 0L);
        Intent intent = getIntent();
        this.i = new e(j, j2);
        if (intent == null) {
            return true;
        }
        long longExtra = intent.getLongExtra("key_bundle_health_last_data_time", 0L);
        this.f10069a = longExtra;
        bundle.putLong("key_bundle_health_last_data_time", longExtra);
        return this.f10069a != 0;
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public DataInfos getSubPageDataInfos(int i) {
        if (i == 0) {
            return DataInfos.TemperatureDayDetail;
        }
        if (i == 1) {
            return DataInfos.TemperatureWeekDetail;
        }
        if (i == 2) {
            return DataInfos.TemperatureMonthDetail;
        }
        return DataInfos.NoDataPlaceHolder;
    }

    private Drawable dBc_() {
        Drawable drawable = ContextCompat.getDrawable(this.b, R.drawable._2131430283_res_0x7f0b0b8b);
        return LanguageUtil.bc(this.b) ? nrz.cKm_(this.b, drawable) : drawable;
    }

    protected void d(String str) {
        if (String.valueOf(1).equals(str)) {
            h();
        } else if (String.valueOf(3).equals(str)) {
            r();
        } else {
            LogUtil.h("Knit_TemperatureActivity", "unknow position=", str);
        }
    }

    private void h() {
        String value = AnalyticsValue.HEALTH_HEALTH_TEMPERATURE_INPUT_2060049.value();
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        ixx.d().d(this.b, value, hashMap, 0);
        Intent intent = new Intent(this.b, (Class<?>) InputTemperatureActivity.class);
        intent.putExtra("isShowInput", true);
        try {
            this.b.startActivity(intent);
        } catch (ActivityNotFoundException e2) {
            LogUtil.b("Knit_TemperatureActivity", "ActivityNotFoundException", e2.getMessage());
        }
    }

    private void r() {
        if (e() > 0) {
            LogUtil.h("Knit_TemperatureActivity", "getBondedProducts() > 0");
            g();
        } else {
            q();
        }
    }

    private int e() {
        ArrayList<String> a2 = cjx.e().a(HealthDevice.HealthDeviceKind.HDK_BODY_TEMPERATURE);
        if (koq.b(a2)) {
            return 0;
        }
        return a2.size();
    }

    private void g() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter != null && defaultAdapter.getState() == 12) {
            l();
            return;
        }
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this.b);
        builder.e(R$string.IDS_device_bluetooth_open_request);
        builder.czC_(R$string.IDS_device_ui_dialog_yes, new View.OnClickListener() { // from class: qem
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                KnitTemperatureActivity.this.dBg_(view);
            }
        });
        builder.czz_(R$string.IDS_device_ui_dialog_no, new View.OnClickListener() { // from class: qel
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        NoTitleCustomAlertDialog e2 = builder.e();
        e2.setCancelable(false);
        e2.show();
    }

    public /* synthetic */ void dBg_(View view) {
        if (Build.VERSION.SDK_INT > 30 && PermissionUtil.e(this, PermissionUtil.PermissionType.SCAN) != PermissionUtil.PermissionResult.GRANTED) {
            LogUtil.h("Knit_TemperatureActivity", "no scan Permission");
            PermissionUtil.bFX_(this, PermissionUtil.c(PermissionUtil.PermissionType.SCAN), new PermissionsResultAction() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.KnitTemperatureActivity.3
                @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                public void onGranted() {
                    KnitTemperatureActivity.this.d();
                }

                @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                public void onDenied(String str) {
                    nrh.b(this, R$string.IDS_nearby_permission_exception);
                }
            });
            ViewClickInstrumentation.clickOnView(view);
        } else {
            d();
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        try {
            startActivityForResult(new Intent("android.bluetooth.adapter.action.REQUEST_ENABLE"), 1001);
        } catch (ActivityNotFoundException unused) {
            LogUtil.h("Knit_TemperatureActivity", "createBluetoothDialog ActivityNotFoundException");
        } catch (SecurityException unused2) {
            ReleaseLogUtil.c("Knit_TemperatureActivity", "createBluetoothDialog SecurityException");
        }
    }

    private void q() {
        String string = this.b.getString(R$string.IDS_hw_temperature_no_device_tip, 1, 2);
        CustomAlertDialog.Builder builder = new CustomAlertDialog.Builder(this.b);
        builder.e(R$string.IDS_hw_temperature_no_device_title).c(string).cyo_(R$string.IDS_hw_temperature_no_device_bind, new DialogInterface.OnClickListener() { // from class: qek
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                KnitTemperatureActivity.this.dBh_(dialogInterface, i);
            }
        }).cyn_(R$string.IDS_hw_health_show_common_dialog_cancle_button, new DialogInterface.OnClickListener() { // from class: qen
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                KnitTemperatureActivity.dBf_(dialogInterface, i);
            }
        });
        builder.c().show();
    }

    public /* synthetic */ void dBh_(DialogInterface dialogInterface, int i) {
        String value = AnalyticsValue.HEALTH_HEALTH_TEMPERATURE_DETAIL_BIND_2060048.value();
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        ixx.d().d(this.b, value, hashMap, 0);
        s();
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
    }

    public static /* synthetic */ void dBf_(DialogInterface dialogInterface, int i) {
        LogUtil.a("Knit_TemperatureActivity", "onClick negative button");
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
    }

    private void s() {
        dks.e(this, "HDK_BODY_TEMPERATURE");
    }

    private void l() {
        Intent intent = new Intent();
        intent.setPackage(cez.w);
        intent.setClassName(cez.w, "com.huawei.health.device.ui.DeviceMainActivity");
        intent.putExtra("kind", "HDK_BODY_TEMPERATURE");
        String value = AnalyticsValue.HEALTH_HEALTH_TEMPERATURE_DETAIL_MEASURE_2060047.value();
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        ixx.d().d(this.b, value, hashMap, 0);
        intent.putExtra("view", "MeasureDevice");
        try {
            this.b.startActivity(intent);
        } catch (ActivityNotFoundException e2) {
            LogUtil.b("Knit_TemperatureActivity", "ActivityNotFoundException", e2.getMessage());
        }
    }

    private void c(final String str, String str2) {
        LoginInit.getInstance(this).browsingToLogin(new IBaseResponseCallback() { // from class: qes
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                KnitTemperatureActivity.this.d(str, i, obj);
            }
        }, str2);
    }

    public /* synthetic */ void d(String str, int i, Object obj) {
        if (i != 0) {
            LogUtil.a("Knit_TemperatureActivity", "browsingToLogin errorCode is not success", Integer.valueOf(i));
        } else {
            LogUtil.b("Knit_TemperatureActivity", "clickMenuAndToLogin");
            d(str);
        }
    }

    private void b(int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        hashMap.put("type", Integer.valueOf(i));
        gge.e(AnalyticsValue.TEMPERATURE_MORE_2060079.value(), hashMap);
    }

    private void m() {
        LogUtil.b("Knit_TemperatureActivity", "setTitleBarShow");
        if (this.b instanceof HealthDataDetailActivity) {
            LogUtil.b("Knit_TemperatureActivity", "mContext instanceof HealthDataDetailActivity");
            if (((HealthDataDetailActivity) this.b).c() > 0) {
                c(0);
                refreshTitleBar(false);
                this.e = false;
            } else {
                c(8);
                refreshTitleBar(true);
            }
        }
    }

    private void c(final int i) {
        runOnUiThread(new Runnable() { // from class: qet
            @Override // java.lang.Runnable
            public final void run() {
                KnitTemperatureActivity.this.a(i);
            }
        });
    }

    public /* synthetic */ void a(int i) {
        this.g.setRightButtonVisibility(i);
    }

    private void f() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.getStringExtra("healthdata") != null) {
                this.c = intent.getStringExtra("healthdata");
            }
            boolean booleanExtra = intent.getBooleanExtra("refreshCard", false);
            LogUtil.a("Knit_TemperatureActivity", "mIsRefreshCard = ", Boolean.valueOf(booleanExtra));
            if (booleanExtra) {
                nro.e(this.b, 24);
            }
        }
    }

    private void b() {
        LogUtil.a("Knit_TemperatureActivity", "onBackPressed jumpToDeviceActivity is Success");
        Intent intent = new Intent();
        intent.setPackage(BaseApplication.getAppPackage());
        intent.setClassName(BaseApplication.getAppPackage(), "com.huawei.health.device.ui.DeviceMainActivity");
        intent.putExtra("kind", "HDK_UNKNOWN");
        intent.putExtra("view", "ListDevice");
        startActivity(intent);
        finish();
    }

    private void n() {
        if (this.j == null) {
            this.j = new LastTimeHealthDataReader<>(this, this.i);
        }
        this.j.b(LastTimeHealthDataReader.CardData.TEMPERATURE);
    }

    static class e implements IBaseResponseCallback {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference<KnitTemperatureActivity> f10073a;
        private long b;
        private long d;

        private e(KnitTemperatureActivity knitTemperatureActivity, long j, long j2) {
            this.f10073a = new WeakReference<>(knitTemperatureActivity);
            this.d = j;
            this.b = j2;
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(final int i, final Object obj) {
            long j;
            if (!HandlerExecutor.c()) {
                HandlerExecutor.a(new Runnable() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.KnitTemperatureActivity.e.4
                    @Override // java.lang.Runnable
                    public void run() {
                        e.this.d(i, obj);
                    }
                });
                return;
            }
            KnitTemperatureActivity knitTemperatureActivity = this.f10073a.get();
            if (knitTemperatureActivity == null) {
                LogUtil.h("Knit_TemperatureActivity", "LastTimeCallback activity is null");
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putLong("key_bundle_health_last_data_time", this.d);
            bundle.putLong("key_marker_view_time", this.b);
            if (obj instanceof HiHealthData) {
                j = ((HiHealthData) obj).getStartTime();
                LogUtil.a("Knit_TemperatureActivity", "read last data time from database,mLastTimestamp=", Long.valueOf(j));
            } else {
                LogUtil.h("Knit_TemperatureActivity", "read last data time from database,mLastTimestamp=0");
                j = 0;
            }
            if (j == 0 && !Utils.o()) {
                LogUtil.h("Knit_TemperatureActivity", "show empty fragment");
                knitTemperatureActivity.showEmptyFragment();
                knitTemperatureActivity.hideNonEmptyFragment();
                knitTemperatureActivity.refreshTitleBar(false);
                knitTemperatureActivity.g.setVisibility(knitTemperatureActivity.f ? 0 : 8);
            } else {
                LogUtil.h("Knit_TemperatureActivity", "show not empty fragment");
                knitTemperatureActivity.refreshTitleBar(true);
                knitTemperatureActivity.g.setVisibility(0);
                knitTemperatureActivity.hideEmptyFragment();
                knitTemperatureActivity.showNonEmptyFragment();
            }
            if (knitTemperatureActivity.f10069a == 0) {
                LogUtil.a("Knit_TemperatureActivity", "reset start time");
                knitTemperatureActivity.f10069a = j;
                bundle.putLong("key_bundle_health_last_data_time", j);
                knitTemperatureActivity.onBundleArrived(bundle);
            }
        }
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        n();
        super.onResume();
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public void onKnitPageSelected(int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        hashMap.put("type", Integer.valueOf(i));
        ixx.d().d(this.b.getApplicationContext(), AnalyticsValue.TEMPERATURE_SWITCH_RANGE_2060075.value(), hashMap, 0);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != 1001 || i2 == 0) {
            return;
        }
        l();
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public String getLogTag() {
        return "Knit_TemperatureActivity";
    }
}
