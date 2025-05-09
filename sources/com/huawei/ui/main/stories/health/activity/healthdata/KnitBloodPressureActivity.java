package com.huawei.ui.main.stories.health.activity.healthdata;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.huawei.basichealth.bloodpressure.BloodPressureSyncManager;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.health.R;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.knit.model.KnitSubPageConfig;
import com.huawei.health.knit.ui.KnitFragment;
import com.huawei.health.knit.ui.KnitHealthDetailActivity;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.trade.PayApi;
import com.huawei.trade.datatype.DeviceBenefitQueryParam;
import com.huawei.ui.commonui.bannerlayout.HealthBannerLayout;
import com.huawei.ui.commonui.calendarview.HealthCalendar;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.dialog.HealthDataInputDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.toolbar.HealthToolBar;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.activity.coresleep.utils.BloodPressureBootPagerHelper;
import com.huawei.ui.main.stories.health.activity.healthdata.KnitBloodPressureActivity;
import com.huawei.ui.main.stories.health.util.BloodPressureUtil;
import com.huawei.ui.main.stories.privacy.template.model.bean.PageModelArgs;
import com.huawei.ui.main.stories.privacy.template.view.showdata.PrivacyDataModelActivity;
import com.huawei.ui.main.stories.template.PopMenuManager;
import com.huawei.ui.main.stories.template.data.IPopMenuItemClick;
import defpackage.ash;
import defpackage.cbk;
import defpackage.dqu;
import defpackage.ggl;
import defpackage.gmn;
import defpackage.ixx;
import defpackage.jdx;
import defpackage.jpt;
import defpackage.koq;
import defpackage.moj;
import defpackage.njn;
import defpackage.nrh;
import defpackage.nro;
import defpackage.nsd;
import defpackage.nsf;
import defpackage.nsn;
import defpackage.nsy;
import defpackage.qgx;
import defpackage.qif;
import defpackage.qkh;
import defpackage.qqe;
import defpackage.qqs;
import defpackage.ryf;
import defpackage.wq;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

/* loaded from: classes.dex */
public class KnitBloodPressureActivity extends KnitHealthDetailActivity {
    private static long b;
    private static int d;

    /* renamed from: a, reason: collision with root package name */
    private Context f10064a;
    private LinearLayout c;
    private HealthCardView g;
    private KnitFragment h;
    private int j;
    private long k;
    private ViewGroup l;
    private int m;
    private d n;
    private HealthBannerLayout o;
    private final Context e = BaseApplication.e();
    private String i = null;
    private volatile int f = Integer.MAX_VALUE;

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public int configSubTabStyle() {
        return R.layout.knit_sub_tab_widget_blood_pressure;
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public int getPageType() {
        return 7;
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        nsn.cLf_(this, bundle);
        super.onCreate(bundle);
        this.f10064a = this;
        j();
        this.g = (HealthCardView) findViewById(R.id.knit_measure_toast);
        String b2 = ash.b("FIRST_IN_HDK_BLOOD_PRESSURE");
        if (TextUtils.isEmpty(b2) || !"true".equals(b2)) {
            p();
            ash.a("FIRST_IN_HDK_BLOOD_PRESSURE", "true");
        }
        if (!nsd.a("BLOOD_SHOW_DIALOG")) {
            b = nsd.c("BLOOD_START_TIME");
            d = nsd.d("BLOOD_SHOW_DIALOG_COUNT");
        }
        n();
        if (koq.b(dqu.b().f())) {
            BloodPressureUtil.f();
        }
    }

    private boolean g() {
        boolean z = qif.b(this.e) > 0;
        boolean e2 = BloodPressureSyncManager.e();
        LogUtil.a("KnitBloodPressureActivity", "hasConnectedBloodPressureDevice isBondedProducts: ", Boolean.valueOf(z), ", isBloodPressureWatch: ", Boolean.valueOf(e2));
        return z || e2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        if (g()) {
            LogUtil.a("KnitBloodPressureActivity", "not to showBloodPressureInputDialog, cause has connected blood pressure device");
            return;
        }
        if (!b(Integer.toString(10053), "BLOOD_PRESSURE_INPUT_DIALOG", 604800000L)) {
            LogUtil.a("KnitBloodPressureActivity", "not to showBloodPressureInputDialog, cause not over one week");
            return;
        }
        HealthDataInputDialog.DataSetFormatter dataSetFormatter = new HealthDataInputDialog.DataSetFormatter() { // from class: qed
            @Override // com.huawei.ui.commonui.dialog.HealthDataInputDialog.DataSetFormatter
            public final String format(int i) {
                String e2;
                e2 = UnitUtil.e(i, 1, 0);
                return e2;
            }
        };
        HealthDataInputDialog.SelectedValueProcessor selectedValueProcessor = new HealthDataInputDialog.SelectedValueProcessor() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.KnitBloodPressureActivity.4
            @Override // com.huawei.ui.commonui.dialog.HealthDataInputDialog.SelectedValueProcessor
            public int process(int i, int i2) {
                return i + i2;
            }

            @Override // com.huawei.ui.commonui.dialog.HealthDataInputDialog.SelectedValueProcessor
            public String format(int i) {
                return UnitUtil.e(i, 1, 0);
            }
        };
        String string = this.f10064a.getResources().getString(R$string.IDS_hw_health_show_healthdata_bloodpress_high);
        String string2 = this.f10064a.getResources().getString(R$string.IDS_hw_health_show_healthdata_bloodpress_low);
        String string3 = this.f10064a.getResources().getString(R$string.IDS_blood_pressure_input_dialog_sys);
        String string4 = this.f10064a.getResources().getString(R$string.IDS_blood_pressure_input_dialog_dia);
        String string5 = this.f10064a.getResources().getString(R$string.IDS_device_measure_pressure_value_unit);
        HealthDataInputDialog.e d2 = new HealthDataInputDialog.e(40, 300, dataSetFormatter, selectedValueProcessor).c(88).b(string).a(string5).d(10);
        HealthDataInputDialog.e d3 = new HealthDataInputDialog.e(30, 200, dataSetFormatter, selectedValueProcessor).c(58).b(string2).a(string5).d(10);
        if (Utils.o()) {
            d2.d(string3).b(true);
            d3.d(string4).b(true);
        }
        String string6 = this.f10064a.getResources().getString(R$string.IDS_hw_blood_pressure_no_data_input_dialog_title);
        final HealthDataInputDialog d4 = new HealthDataInputDialog(this).c(ContextCompat.getColor(this.f10064a, R.color._2131296532_res_0x7f090114)).e(string6).a(this.f10064a.getResources().getString(R$string.IDS_hw_blood_pressure_no_data_input_dialog_content)).d(d2).d(d3);
        if (Utils.o()) {
            d4.b(true);
            d4.a(false);
        }
        d4.c(new HealthDataInputDialog.PositiveBtnClickListener() { // from class: qec
            @Override // com.huawei.ui.commonui.dialog.HealthDataInputDialog.PositiveBtnClickListener
            public final void onClick(List list) {
                KnitBloodPressureActivity.this.c(d4, list);
            }
        });
        d4.czq_(new View.OnClickListener() { // from class: qef
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                KnitBloodPressureActivity.this.dAX_(d4, view);
            }
        });
        d4.show();
        c(0);
    }

    public /* synthetic */ void c(final HealthDataInputDialog healthDataInputDialog, final List list) {
        LoginInit.getInstance(this.e).browsingToLogin(new IBaseResponseCallback() { // from class: qdz
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                KnitBloodPressureActivity.this.d(healthDataInputDialog, list, i, obj);
            }
        }, "");
    }

    public /* synthetic */ void d(HealthDataInputDialog healthDataInputDialog, List list, int i, Object obj) {
        c(2);
        if (i != 0) {
            LogUtil.b("KnitBloodPressureActivity", "input data failed, login failed! errorCode = ", Integer.valueOf(i));
        } else {
            e(healthDataInputDialog, (List<Integer>) list);
        }
    }

    public /* synthetic */ void dAX_(HealthDataInputDialog healthDataInputDialog, View view) {
        healthDataInputDialog.dismiss();
        c(1);
        o();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void c(int i) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        hashMap.put("event", Integer.valueOf(i));
        ixx.d().d(this.f10064a, AnalyticsValue.HEATLH_HEALTH_BLOODPRESSURE_NO_DATA_INPUT_2210001.value(), hashMap, 0);
    }

    private void e(HealthDataInputDialog healthDataInputDialog, List<Integer> list) {
        int intValue = list.get(0).intValue();
        int intValue2 = list.get(1).intValue();
        if (intValue < intValue2) {
            nrh.e(this.e, R$string.IDS_hw_health_show_healthdata_bloodpresserror);
        } else {
            qkh.c().e(System.currentTimeMillis(), new double[]{intValue, intValue2, 0.0d, 4096.0d}, new ArrayList(), "-1", new c(this));
            healthDataInputDialog.dismiss();
        }
    }

    private boolean b(String str, String str2, long j) {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - SharedPreferenceManager.b(str, str2, 0L) <= j) {
            return false;
        }
        SharedPreferenceManager.e(str, str2, currentTimeMillis);
        return true;
    }

    private void o() {
        jdx.b(new Runnable() { // from class: qeb
            @Override // java.lang.Runnable
            public final void run() {
                KnitBloodPressureActivity.this.a();
            }
        });
    }

    public /* synthetic */ void a() {
        boolean z = b() > 0 || BloodPressureSyncManager.e();
        if (Utils.o() || z || getPageState() == 0) {
            return;
        }
        new BloodPressureBootPagerHelper(this.e).showBootPage(this.c);
    }

    private void n() {
        List<DeviceBenefitQueryParam> e2 = njn.e("KnitBloodPressureActivity", DeviceBenefitQueryParam.DeviceBenefitType.DEVICE_BENEFIT_TYPE_ALL);
        PayApi payApi = (PayApi) Services.a("TradeService", PayApi.class);
        if (payApi != null) {
            payApi.getAllDeviceBenefits(e2, new b(this));
        }
    }

    /* loaded from: classes6.dex */
    public static class c implements IBaseResponseCallback {
        private final WeakReference<KnitBloodPressureActivity> c;

        c(KnitBloodPressureActivity knitBloodPressureActivity) {
            this.c = new WeakReference<>(knitBloodPressureActivity);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            LogUtil.a("KnitBloodPressureActivity", "InsertBloodPressureDataCallback onResponse errorCode ", Integer.valueOf(i), " object ", obj);
            boolean z = i == 0;
            if (z) {
                HashMap hashMap = new HashMap(16);
                hashMap.put("click", String.valueOf(1));
                hashMap.put("type", "2");
                hashMap.put("from", 4);
                ixx.d().d(com.huawei.hwcommonmodel.application.BaseApplication.getContext(), AnalyticsValue.HEALTH_HEALTH_BLOODPRESS_INPUT_2030027.value(), hashMap, 0);
            }
            KnitBloodPressureActivity knitBloodPressureActivity = this.c.get();
            if (knitBloodPressureActivity != null && !knitBloodPressureActivity.isFinishing() && !knitBloodPressureActivity.isDestroyed()) {
                if (z) {
                    b(knitBloodPressureActivity);
                }
                qgx.b(knitBloodPressureActivity.n);
                return;
            }
            ReleaseLogUtil.a("R_KnitBloodPressureActivity", "InsertBloodPressureDataCallback onResponse activity ", knitBloodPressureActivity);
        }

        private void b(final KnitBloodPressureActivity knitBloodPressureActivity) {
            HandlerExecutor.e(new Runnable() { // from class: qeg
                @Override // java.lang.Runnable
                public final void run() {
                    KnitBloodPressureActivity.c.d(KnitBloodPressureActivity.this);
                }
            });
        }

        public static /* synthetic */ void d(KnitBloodPressureActivity knitBloodPressureActivity) {
            NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(knitBloodPressureActivity);
            builder.e(R$string.IDS_blood_pressure_input_success).czB_(R$string.IDS_common_notification_know_tips, R.color._2131296532_res_0x7f090114, new View.OnClickListener() { // from class: qei
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    KnitBloodPressureActivity.c.dAY_(view);
                }
            });
            builder.e().show();
        }

        public static /* synthetic */ void dAY_(View view) {
            ReleaseLogUtil.b("R_KnitBloodPressureActivity", "InsertBloodPressureDataCallback showInputSuccessDialog onClick know button");
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    /* loaded from: classes6.dex */
    static class b implements IBaseResponseCallback {
        private final WeakReference<KnitBloodPressureActivity> d;

        b(KnitBloodPressureActivity knitBloodPressureActivity) {
            this.d = new WeakReference<>(knitBloodPressureActivity);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            LogUtil.a("KnitBloodPressureActivity", "EquityDialogCallBack onResponse errorCode ", Integer.valueOf(i), " object ", obj);
            KnitBloodPressureActivity knitBloodPressureActivity = this.d.get();
            if (knitBloodPressureActivity == null || knitBloodPressureActivity.isFinishing() || knitBloodPressureActivity.isDestroyed()) {
                ReleaseLogUtil.a("R_KnitBloodPressureActivity", "EquityDialogCallBack onResponse activity ", knitBloodPressureActivity);
            } else if (i == 0) {
                qqs.d(obj, knitBloodPressureActivity, 2, KnitBloodPressureActivity.b, KnitBloodPressureActivity.d);
            }
        }
    }

    private void p() {
        HiSyncOption hiSyncOption = new HiSyncOption();
        hiSyncOption.setSyncModel(2);
        hiSyncOption.setSyncAction(0);
        hiSyncOption.setSyncDataType(7);
        hiSyncOption.setSyncMethod(2);
        hiSyncOption.setSyncDataArea(1);
        HiHealthNativeApi.a(this.e).synCloud(hiSyncOption, null);
    }

    private void j() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.getStringExtra("healthdata") != null) {
                this.i = intent.getStringExtra("healthdata");
            }
            boolean booleanExtra = intent.getBooleanExtra("refreshCard", false);
            LogUtil.a("KnitBloodPressureActivity", "mIsRefreshCard = ", Boolean.valueOf(booleanExtra));
            if (booleanExtra) {
                nro.e(this.f10064a, 8);
            }
            int intExtra = intent.getIntExtra("biMessageType", 0);
            if (intExtra != 0) {
                g(intExtra);
            }
        }
    }

    private void g(int i) {
        String value = AnalyticsValue.HEATLH_HEALTH_BLOODPRESSURE_MESSAGE_CLICK_2210004.value();
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        hashMap.put("type", Integer.valueOf(i));
        ixx.d().d(this.e, value, hashMap, 0);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            d();
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public DataInfos getSubPageDataInfos(int i) {
        if (i == 0) {
            return DataInfos.BloodPressureDayDetail;
        }
        if (i == 1) {
            return DataInfos.BloodPressureWeekDetail;
        }
        if (i == 2) {
            return DataInfos.BloodPressureMonthDetail;
        }
        return DataInfos.NoDataPlaceHolder;
    }

    private void d() {
        if ("MyHealthData".equals(this.i)) {
            LogUtil.a("KnitBloodPressureActivity", "onBackPressed jumpToDeviceActivity is Success");
            Intent intent = new Intent();
            intent.setPackage(com.huawei.hwcommonmodel.application.BaseApplication.getAppPackage());
            intent.setClassName(com.huawei.hwcommonmodel.application.BaseApplication.getAppPackage(), "com.huawei.health.device.ui.DeviceMainActivity");
            intent.putExtra("kind", "HDK_UNKNOWN");
            intent.putExtra("view", "ListDevice");
            startActivity(intent);
        }
        finish();
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public Fragment onCreateEmptyFragment(KnitSubPageConfig knitSubPageConfig) {
        if (knitSubPageConfig == null) {
            this.h = null;
        } else {
            this.h = KnitFragment.newInstance(moj.e(knitSubPageConfig), getResTrigger(getPageType(), knitSubPageConfig.getResPosId(), false).setExtra(new Bundle()));
        }
        return this.h;
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        this.k = System.currentTimeMillis();
        qgx.b(this.n);
        gmn.c(this.f10064a);
        HandlerExecutor.d(new Runnable() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.KnitBloodPressureActivity.5
            @Override // java.lang.Runnable
            public void run() {
                ObserverManagerUtil.c("HOME_FRAGMENT_ACTIVITY_FOR_RESULT", "FunctionSetBloodPressureReader");
            }
        }, 1000L);
        super.onResume();
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public void configTitleBar(final CustomTitleBar customTitleBar) {
        customTitleBar.setTitleText(getString(R$string.IDS_hw_show_main_home_page_bloodpressure));
        customTitleBar.setRightButtonDrawable(ContextCompat.getDrawable(this, R.drawable._2131430194_res_0x7f0b0b32), nsf.h(R$string.accessibility_more_item));
        customTitleBar.setRightButtonClickable(true);
        customTitleBar.setRightButtonVisibility(0);
        final PopMenuManager popMenuManager = new PopMenuManager() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.KnitBloodPressureActivity.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.huawei.ui.main.stories.template.PopMenuManager
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public CustomTitleBar getTitleBar() {
                return customTitleBar;
            }

            @Override // com.huawei.ui.main.stories.template.IMenuItemManager
            public List<ryf> getMenuItemList() {
                return KnitBloodPressureActivity.this.f();
            }
        };
        customTitleBar.setRightButtonOnClickListener(new View.OnClickListener() { // from class: qdx
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                KnitBloodPressureActivity.dAU_(PopMenuManager.this, view);
            }
        });
    }

    public static /* synthetic */ void dAU_(PopMenuManager popMenuManager, View view) {
        popMenuManager.showPopMenu();
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ArrayList<ryf> f() {
        ryf ryfVar = new ryf(getResources().getString(R$string.IDS_blood_setting_title), new IPopMenuItemClick() { // from class: qdy
            @Override // com.huawei.ui.main.stories.template.data.IPopMenuItemClick
            public final void onItemClick() {
                KnitBloodPressureActivity.this.i();
            }
        });
        ryf ryfVar2 = new ryf(getResources().getString(R$string.IDS_privacy_all_data), new IPopMenuItemClick() { // from class: qee
            @Override // com.huawei.ui.main.stories.template.data.IPopMenuItemClick
            public final void onItemClick() {
                KnitBloodPressureActivity.this.q();
            }
        });
        ryf ryfVar3 = new ryf(getResources().getString(R$string.IDS_fitness_core_sleep_explain_title), new IPopMenuItemClick() { // from class: qea
            @Override // com.huawei.ui.main.stories.template.data.IPopMenuItemClick
            public final void onItemClick() {
                KnitBloodPressureActivity.this.r();
            }
        });
        ArrayList<ryf> arrayList = new ArrayList<>();
        if (BloodPressureSyncManager.e()) {
            arrayList.add(ryfVar);
        }
        arrayList.add(ryfVar2);
        arrayList.add(ryfVar3);
        return arrayList;
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public void configToolBar(HealthToolBar healthToolBar) {
        LogUtil.a("KnitBloodPressureActivity", "configToolBar");
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public void configExtraView(LinearLayout linearLayout) {
        this.c = linearLayout;
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public boolean getExtra(Bundle bundle) {
        LogUtil.a("KnitBloodPressureActivity", "getExtra");
        Intent intent = getIntent();
        this.n = new d(this, bundle);
        if (intent != null) {
            long longExtra = intent.getLongExtra("key_bundle_health_last_data_time", 0L);
            bundle.putLong("key_bundle_health_last_data_time", longExtra);
            LogUtil.a("KnitBloodPressureActivity", "read last data time from database,mLastTimestamp=", Long.valueOf(longExtra));
            if (longExtra == 0) {
                LogUtil.a("KnitBloodPressureActivity", "lastTimestamp is 0");
                return false;
            }
            if (longExtra > 0) {
                LogUtil.a("KnitBloodPressureActivity", "lastTimestamp: " + longExtra);
                bundle.putLong("key_bundle_health_last_data_time", longExtra);
                return true;
            }
            LogUtil.a("KnitBloodPressureActivity", "lastTimestamp is less than 0");
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        DeviceInfo d2 = jpt.d("KnitBloodPressureActivity");
        String multiLinkBleMac = d2 != null ? d2.getMultiLinkBleMac() : "";
        Intent intent = new Intent();
        intent.setClassName(this.f10064a, "com.huawei.ui.device.activity.bloodpressure.BloodPressureSettingActivity");
        intent.setFlags(268435456);
        if (!TextUtils.isEmpty(multiLinkBleMac)) {
            intent.putExtra("device_id", multiLinkBleMac);
        }
        intent.setFlags(AppRouterExtras.COLDSTART);
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e2) {
            LogUtil.b("KnitBloodPressureActivity", "ActivityNotFoundException", e2.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r() {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        hashMap.put("type", 1);
        ixx.d().d(this.f10064a, AnalyticsValue.HEALTH_HEALTH_BLOODPRESS_MORE_2030088.value(), hashMap, 0);
        try {
            this.f10064a.startActivity(new Intent(this.f10064a, (Class<?>) BloodPressureDescriptionActivity.class));
        } catch (ActivityNotFoundException e2) {
            LogUtil.b("KnitBloodPressureActivity", "ActivityNotFoundException", e2.getMessage());
        }
    }

    private int b() {
        SQLiteDatabase openOrCreateDatabase;
        int i = 0;
        try {
            openOrCreateDatabase = openOrCreateDatabase("device.db", 0, null);
        } catch (SQLException unused) {
            LogUtil.b("KnitBloodPressureActivity", "getBondedProducts SQLException");
        }
        try {
            Cursor query = openOrCreateDatabase.query("device", new String[]{"productId"}, "kind = 'HDK_BLOOD_PRESSURE'", null, null, null, null);
            while (query.moveToNext()) {
                try {
                    i++;
                } finally {
                }
            }
            if (query != null) {
                query.close();
            }
            if (openOrCreateDatabase != null) {
                openOrCreateDatabase.close();
            }
            return i;
        } finally {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        c(AnalyticsValue.HEALTH_HEALTH_BLOODPRESS_MORE_2030088.value());
        PrivacyDataModelActivity.b(this, new PageModelArgs(107, "PrivacyDataConstructor", 3, 3));
    }

    private void c(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        hashMap.put("type", 3);
        ixx.d().d(this.e, str, hashMap, 0);
    }

    /* loaded from: classes6.dex */
    static class d implements IBaseResponseCallback {
        public WeakReference<KnitBloodPressureActivity> d;
        private Bundle e;

        public d(KnitBloodPressureActivity knitBloodPressureActivity, Bundle bundle) {
            this.d = new WeakReference<>(knitBloodPressureActivity);
            this.e = bundle;
        }

        /* JADX WARN: Code restructure failed: missing block: B:11:0x001d, code lost:
        
            if (r5.get(0) == null) goto L12;
         */
        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void d(int r4, java.lang.Object r5) {
            /*
                r3 = this;
                boolean r4 = r5 instanceof java.util.List
                if (r4 != 0) goto L5
                return
            L5:
                java.lang.ref.WeakReference<com.huawei.ui.main.stories.health.activity.healthdata.KnitBloodPressureActivity> r4 = r3.d
                java.lang.Object r4 = r4.get()
                com.huawei.ui.main.stories.health.activity.healthdata.KnitBloodPressureActivity r4 = (com.huawei.ui.main.stories.health.activity.healthdata.KnitBloodPressureActivity) r4
                if (r4 != 0) goto L10
                return
            L10:
                java.util.List r5 = (java.util.List) r5
                boolean r0 = defpackage.koq.b(r5)
                if (r0 != 0) goto L1f
                r0 = 0
                java.lang.Object r1 = r5.get(r0)
                if (r1 != 0) goto L20
            L1f:
                r0 = 1
            L20:
                java.lang.String r1 = "isDataEmpty: "
                java.lang.Boolean r2 = java.lang.Boolean.valueOf(r0)
                java.lang.Object[] r1 = new java.lang.Object[]{r1, r2}
                java.lang.String r2 = "KnitBloodPressureActivity"
                com.huawei.hwlogsmodel.LogUtil.a(r2, r1)
                com.huawei.ui.main.stories.health.activity.healthdata.KnitBloodPressureActivity$d$5 r1 = new com.huawei.ui.main.stories.health.activity.healthdata.KnitBloodPressureActivity$d$5
                r1.<init>()
                com.huawei.haf.handler.HandlerExecutor.a(r1)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.main.stories.health.activity.healthdata.KnitBloodPressureActivity.d.d(int, java.lang.Object):void");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(boolean z, KnitBloodPressureActivity knitBloodPressureActivity, List<HiHealthData> list) {
            if (z) {
                knitBloodPressureActivity.showEmptyFragment();
                knitBloodPressureActivity.hideNonEmptyFragment();
                knitBloodPressureActivity.refreshTitleBar(false);
                knitBloodPressureActivity.k();
            } else {
                knitBloodPressureActivity.hideEmptyFragment();
                knitBloodPressureActivity.showNonEmptyFragment();
                knitBloodPressureActivity.refreshTitleBar(true);
                knitBloodPressureActivity.showToast();
                SharedPreferenceManager.e(Integer.toString(30002), "last_open_data_start_time", list.get(0).getStartTime());
            }
            if (this.e != null && koq.d(list, 0) && this.e.getLong("key_bundle_health_last_data_time") == 0) {
                LogUtil.a("KnitBloodPressureActivity", "reset start time");
                this.e.putLong("key_bundle_health_last_data_time", list.get(0).getStartTime());
                knitBloodPressureActivity.onBundleArrived(this.e);
            } else {
                LogUtil.a("KnitBloodPressureActivity", "configHealthDetailActivity");
                knitBloodPressureActivity.configHealthDetailActivity();
            }
        }
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        this.k = System.currentTimeMillis();
        super.onStart();
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public void configToast() {
        LogUtil.a("KnitBloodPressureActivity", "begin to start config bloodPressure measure toast");
        if (this.o == null) {
            HealthBannerLayout healthBannerLayout = new HealthBannerLayout(this);
            this.o = healthBannerLayout;
            healthBannerLayout.setTitle(getString(R$string.IDS_bloodpressure_measure_toast_text));
            if (BloodPressureUtil.e()) {
                this.o.setRightButtonText(getString(R$string.IDS_common_notification_know_tips));
            } else {
                this.o.setLeftButtonText(getString(R$string.IDS_main_btn_state_ignore));
                this.o.setRightButtonText(getString(R$string.IDS_bloodpressure_measure_goto));
            }
            nsy.cMA_(this.o, 8);
        }
        if (this.o.getParent() instanceof ViewGroup) {
            ((ViewGroup) this.o.getParent()).removeView(this.o);
        }
        this.g.addView(this.o);
        LogUtil.a("KnitBloodPressureActivity", "config Toast queryStartTime:" + this.k);
        h();
        m();
        if (this.g.getChildCount() > 2) {
            LogUtil.a("KnitBloodPressureActivity", "mMeasureToastLayout remove all view:");
            this.g.removeView(this.o);
        }
    }

    private void m() {
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: qdv
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                KnitBloodPressureActivity.this.dAV_(view);
            }
        };
        View.OnClickListener onClickListener2 = new View.OnClickListener() { // from class: qdw
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                KnitBloodPressureActivity.this.dAW_(view);
            }
        };
        if (BloodPressureUtil.e()) {
            this.o.setRightButtonClickListener(onClickListener);
        } else {
            this.o.setLeftButtonClickListener(onClickListener);
            this.o.setRightButtonClickListener(onClickListener2);
        }
    }

    public /* synthetic */ void dAV_(View view) {
        this.o.setVisibility(8);
        t();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void dAW_(View view) {
        this.o.setVisibility(8);
        t();
        qqe.a();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void t() {
        LogUtil.a("KnitBloodPressureActivity", "curDayOfWeek is: ", Integer.valueOf(this.j));
        SharedPreferenceManager.e(this.f10064a, "KnitBloodPressureActivity", this.f + String.valueOf(this.j), "false", (StorageParams) null);
    }

    private void h() {
        int i;
        LogUtil.a("KnitBloodPressureActivity", "start to init bloodPressure measure toast");
        HealthCalendar healthCalendar = new HealthCalendar();
        LogUtil.a("KnitBloodPressureActivity", "query start time is :" + this.k);
        healthCalendar.setTime(new Date(this.k));
        this.j = b(healthCalendar.getWeek());
        String c2 = ggl.c(this.k);
        int i2 = 0;
        try {
            i = Integer.parseInt(c2.substring(0, 2));
        } catch (NumberFormatException e2) {
            e = e2;
            i = 0;
        }
        try {
            i2 = Integer.parseInt(c2.substring(3, c2.length()));
        } catch (NumberFormatException e3) {
            e = e3;
            LogUtil.b("KnitBloodPressureActivity", "set enterHour and enterMinute error" + e.getMessage());
            a((i * 60) + i2, this.j);
        }
        a((i * 60) + i2, this.j);
    }

    private void a(int i, int i2) {
        this.f = Integer.MAX_VALUE;
        qif.c(new e(this, i2, i));
        d(i2);
    }

    /* loaded from: classes6.dex */
    public static class e implements ResponseCallback<List<cbk>> {
        private final int c;
        private final WeakReference<KnitBloodPressureActivity> d;
        private final int e;

        e(KnitBloodPressureActivity knitBloodPressureActivity, int i, int i2) {
            this.d = new WeakReference<>(knitBloodPressureActivity);
            this.c = i;
            this.e = i2;
        }

        @Override // com.huawei.hwbasemgr.ResponseCallback
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onResponse(int i, List<cbk> list) {
            final KnitBloodPressureActivity knitBloodPressureActivity = this.d.get();
            if (knitBloodPressureActivity == null || knitBloodPressureActivity.isFinishing()) {
                LogUtil.h("KnitBloodPressureActivity", "BloodPressureAlarmInfoCallback onResponse activity ", knitBloodPressureActivity);
                return;
            }
            if (koq.b(list)) {
                LogUtil.h("KnitBloodPressureActivity", "acquireTodayAllMeasurePlan list ", list);
                return;
            }
            HashSet<wq> hashSet = new HashSet(list);
            LogUtil.a("KnitBloodPressureActivity", "mAlarmInfoSet is null?" + koq.b(hashSet));
            if (koq.b(hashSet)) {
                LogUtil.a("KnitBloodPressureActivity", "CollectionUtils.isEmpty(mAlarmInfoSet)");
                return;
            }
            for (wq wqVar : hashSet) {
                if (knitBloodPressureActivity.d(this.c, wqVar.d())) {
                    knitBloodPressureActivity.b(wqVar, this.e, this.c);
                }
            }
            if (knitBloodPressureActivity.f != Integer.MAX_VALUE) {
                knitBloodPressureActivity.m = knitBloodPressureActivity.f - this.e;
                LogUtil.a("KnitBloodPressureActivity", "diff:" + knitBloodPressureActivity.m);
                String b = SharedPreferenceManager.b(knitBloodPressureActivity.f10064a, "KnitBloodPressureActivity", "BLOOD_SHOW_TOAST");
                if (b == null || !b.equals(String.valueOf(knitBloodPressureActivity.f))) {
                    if (knitBloodPressureActivity.m < 0 || knitBloodPressureActivity.m > 30) {
                        return;
                    }
                    knitBloodPressureActivity.runOnUiThread(new Runnable() { // from class: qej
                        @Override // java.lang.Runnable
                        public final void run() {
                            KnitBloodPressureActivity.this.l();
                        }
                    });
                    return;
                }
                LogUtil.a("KnitBloodPressureActivity", "same measure time");
                return;
            }
            LogUtil.a("KnitBloodPressureActivity", "mMinPlanMin is not init");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        if (this.f10064a == null || this.o == null) {
            return;
        }
        LogUtil.a("KnitBloodPressureActivity", "set visibility to visible");
        this.o.setVisibility(0);
        Context context = this.f10064a;
        int i = this.f;
        SharedPreferenceManager.e(context, "KnitBloodPressureActivity", "BLOOD_SHOW_TOAST", String.valueOf(i), new StorageParams());
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public void configureNoDataToast(ViewGroup viewGroup) {
        this.l = viewGroup;
        if (this.g == null || viewGroup == null || !this.mIsNoDataFragment) {
            LogUtil.a("KnitBloodPressureActivity", "mHealthCardView or container is null");
            return;
        }
        this.g.removeView(this.o);
        if (this.o.getParent() instanceof ViewGroup) {
            ((ViewGroup) this.o.getParent()).removeView(this.o);
        }
        nsy.cMA_(this.g, 8);
        this.l.removeAllViews();
        this.l.addView(this.o);
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public void showToast() {
        if (this.g == null || this.o == null || this.mIsNoDataFragment) {
            LogUtil.a("KnitBloodPressureActivity", "mHealthCardView or mToastView is null");
            return;
        }
        if (this.o.getParent() instanceof ViewGroup) {
            ((ViewGroup) this.o.getParent()).removeView(this.o);
        }
        nsy.cMA_(this.g, 0);
        this.g.addView(this.o);
        this.l = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(wq wqVar, int i, int i2) {
        int a2 = (wqVar.a() * 60) + wqVar.e();
        if (a2 > i) {
            String str = a2 + String.valueOf(i2);
            String b2 = SharedPreferenceManager.b(this.f10064a, "KnitBloodPressureActivity", str);
            if (TextUtils.isEmpty(b2) || Objects.equals(b2, "true")) {
                LogUtil.a("KnitBloodPressureActivity", "valid measure plan :" + a2 + b2);
                if (a2 < this.f) {
                    this.f = a2;
                }
                SharedPreferenceManager.e(this.f10064a, "KnitBloodPressureActivity", str, "true", (StorageParams) null);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean d(int i, int i2) {
        String format = String.format(Locale.ENGLISH, "%07d", Integer.valueOf(Integer.toBinaryString(i2)));
        return format.length() > format.length() - i && format.charAt(format.length() - i) == '1';
    }

    private void d(int i) {
        Map<String, String> a2 = SharedPreferenceManager.a(this.f10064a, "KnitBloodPressureActivity");
        if (a2 == null) {
            return;
        }
        Iterator<Map.Entry<String, String>> it = a2.entrySet().iterator();
        int i2 = 0;
        while (it.hasNext()) {
            LogUtil.a("KnitBloodPressureActivity", "measure plan: " + it.next().getKey());
            if (r4.charAt(r4.length() - 1) - '0' != i) {
                i2++;
            }
        }
        if (i2 == a2.size()) {
            LogUtil.a("KnitBloodPressureActivity", "clear all measure plan");
            SharedPreferenceManager.j(this.f10064a, "KnitBloodPressureActivity");
        }
    }

    private int b(int i) {
        LogUtil.a("KnitBloodPressureActivity", "begin to convert dayOfWeek");
        if (i == 1) {
            return 7;
        }
        return i - 1;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public void onKnitPageSelected(int i) {
        super.onKnitPageSelected(i);
        a(i);
    }

    private void a(int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        hashMap.put("type", Integer.valueOf(i));
        ixx.d().d(this.e, AnalyticsValue.HEALTH_HEALTH_BLOODPRESS_SELECT_PAGE_2040120.value(), hashMap, 0);
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public String getLogTag() {
        return "KnitBloodPressureActivity";
    }
}
