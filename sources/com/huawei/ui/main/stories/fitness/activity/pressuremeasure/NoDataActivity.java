package com.huawei.ui.main.stories.fitness.activity.pressuremeasure;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.h5pro.preload.H5PreloadCountStrategy;
import com.huawei.health.marketing.api.MarketingApi;
import com.huawei.health.marketing.datatype.MarketingOption;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.operation.activity.WebViewActivity;
import com.huawei.operation.h5pro.preload.H5ProPkgPreloadSyncTask;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.toolbar.HealthToolBar;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.activity.pressure.activity.PressureMeasureActivity;
import com.huawei.ui.main.stories.fitness.activity.pressurecalibrate.activity.PressureCalibrateActivity;
import com.huawei.ui.main.stories.fitness.activity.pressuremeasure.NoDataActivity;
import com.huawei.ui.main.stories.template.health.common.CommonHealthNoDeviceFragment;
import defpackage.gge;
import defpackage.gnm;
import defpackage.jdx;
import defpackage.jfq;
import defpackage.jpt;
import defpackage.jqi;
import defpackage.nlg;
import defpackage.nsn;
import defpackage.nsy;
import defpackage.pfe;
import defpackage.pvw;
import defpackage.qaz;
import defpackage.qba;
import defpackage.qrp;
import defpackage.rzf;
import defpackage.sdg;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes9.dex */
public class NoDataActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private Context f9879a;
    private LinearLayout b;
    private View c;
    private CustomTitleBar d;
    private View e;
    private boolean g;
    private HealthButton j;
    private View k;
    private LinearLayout l;
    private LinearLayout m;
    private String n;
    private ImageView p;
    private RelativeLayout q;
    private long r;
    private HealthCardView s;
    private HealthTextView t;
    private HealthToolBar u;
    private HealthCardView v;
    private pvw w;
    private ImageView x;
    private qaz y;
    private String o = "";
    private boolean h = false;
    private d i = new d(this);
    private boolean f = true;

    private void b() {
        LoginInit.getInstance(this).browsingToLogin(new IBaseResponseCallback() { // from class: ptx
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                NoDataActivity.this.c(i, obj);
            }
        }, AnalyticsValue.HEALTH_PRESSUER_NODATA_MEASUREMENT_CLICK_2160008.value());
    }

    public /* synthetic */ void c(int i, Object obj) {
        if (i == 0) {
            k();
        } else {
            LogUtil.h("NoDataActivity", "browsingToLogin errorCode is not success", Integer.valueOf(i));
        }
    }

    static class d extends Handler {
        WeakReference<NoDataActivity> b;

        d(NoDataActivity noDataActivity) {
            this.b = new WeakReference<>(noDataActivity);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            NoDataActivity noDataActivity = this.b.get();
            if (noDataActivity == null) {
                LogUtil.b("NoDataActivity", "MyHandler handleMessage fragment null");
                return;
            }
            int i = message.what;
            if (i == 1000) {
                noDataActivity.n();
            } else {
                if (i != 1001) {
                    return;
                }
                noDataActivity.l();
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.a("NoDataActivity", "onCreate mIsAlreadySetInternet = " + this.h);
        this.f9879a = this;
        setContentView(R.layout.stress_activity_no_datas);
        ((WindowManager) this.f9879a.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR)).getDefaultDisplay().getMetrics(new DisplayMetrics());
        i();
        j();
        if (jpt.a("NoDataActivity") == null && !Utils.o()) {
            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.container);
            rzf.e(getSupportFragmentManager(), CommonHealthNoDeviceFragment.b("StressCardConstructor", 3), R.id.container);
            linearLayout.setVisibility(0);
            this.b.setVisibility(8);
            this.d.setVisibility(8);
            this.g = true;
        } else {
            this.g = false;
            this.s.setOnClickListener(this);
        }
        cancelAdaptRingRegion();
        this.r = System.currentTimeMillis();
        this.u.cHc_(this.e);
        this.u.setOnSingleTapListener(new HealthToolBar.OnSingleTapListener() { // from class: ptv
            @Override // com.huawei.ui.commonui.toolbar.HealthToolBar.OnSingleTapListener
            public final void onSingleTap(int i) {
                NoDataActivity.this.a(i);
            }
        });
        this.u.setIcon(1, R.drawable._2131431598_res_0x7f0b10ae);
        this.u.setIconTitle(1, getString(R$string.IDS_hw_pressure_measuremeant));
        this.u.setIcon(2, R.drawable._2131430263_res_0x7f0b0b77);
        this.u.setIconTitle(2, this.f9879a.getResources().getString(R$string.IDS_hw_pressure_adjust));
        this.u.setIconVisible(3, 8);
        this.u.cHf_((Activity) this.f9879a);
        c();
        a();
        dtG_(getIntent());
    }

    public /* synthetic */ void a(int i) {
        if (i == 1) {
            b();
        } else {
            if (i != 2) {
                return;
            }
            e();
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        dtG_(intent);
    }

    private void a() {
        jdx.b(new Runnable() { // from class: ptz
            @Override // java.lang.Runnable
            public final void run() {
                NoDataActivity.this.d();
            }
        });
    }

    public /* synthetic */ void d() {
        String url = GRSManager.a(this.f9879a).getUrl("messageCenterUrl");
        this.n = url;
        LogUtil.c("NoDataActivity", "initHostFromGrs mMessageCenterHost = ", url);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        LogUtil.a("NoDataActivity", "onConfigurationChanged()");
        LinearLayout linearLayout = this.l;
        if (linearLayout != null) {
            pfe.dok_(3, linearLayout);
        }
    }

    private void j() {
        if (nsn.ag(this.f9879a)) {
            this.p.setBackground(this.f9879a.getResources().getDrawable(R.mipmap._2131821450_res_0x7f11038a));
            this.x.setBackground(this.f9879a.getResources().getDrawable(R.mipmap._2131821450_res_0x7f11038a));
        } else {
            this.p.setBackground(this.f9879a.getResources().getDrawable(R.mipmap._2131820754_res_0x7f1100d2));
            this.x.setBackground(this.f9879a.getResources().getDrawable(R.mipmap._2131820754_res_0x7f1100d2));
        }
    }

    private void i() {
        this.c = nsy.cMc_(this, R.id.blank_view);
        this.q = (RelativeLayout) nsy.cMc_(this, R.id.common_auto_test_toast_layout);
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.fitness_detail_titlebar);
        this.d = customTitleBar;
        customTitleBar.setTitleText(getResources().getString(R$string.IDS_settings_one_level_menu_settings_item_text_id14));
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.first_no_data_layout);
        this.b = linearLayout;
        setViewSafeRegion(false, linearLayout);
        this.s = (HealthCardView) findViewById(R.id.pressure_guide);
        this.p = (ImageView) findViewById(R.id.pressure_guide_img);
        this.v = (HealthCardView) findViewById(R.id.pressure_guide_another);
        this.x = (ImageView) findViewById(R.id.pressure_guide_another_img);
        this.v.setOnClickListener(this);
        HealthTextView healthTextView = (HealthTextView) findViewById(R.id.pressure_data_analyse_detail);
        this.t = healthTextView;
        healthTextView.setAutoTextInfo(9, 1, 1);
        this.u = (HealthToolBar) findViewById(R.id.buttomview);
        this.e = View.inflate(this.f9879a, R.layout.hw_toolbar_bottomview, null);
        this.k = findViewById(R.id.pressure_no_data_set_network);
        this.j = (HealthButton) findViewById(R.id.btn_no_net_work);
        this.l = (LinearLayout) findViewById(R.id.message_service);
        this.m = (LinearLayout) findViewById(R.id.pressure_no_data_marketing_layout);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        qba.d(this.f9879a, new IBaseResponseCallback() { // from class: ptw
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                NoDataActivity.this.b(i, obj);
            }
        });
    }

    public /* synthetic */ void b(int i, Object obj) {
        if (i == 0) {
            LogUtil.a("NoDataActivity", "user click adjust button");
            HashMap hashMap = new HashMap(1);
            hashMap.put("click", 1);
            hashMap.put("havedevice", 1);
            gge.e(AnalyticsValue.HEALTH_PRESSUER_NODATAPAGE_ADJUST_CLICK_2160006.value(), hashMap);
            gnm.aPB_(this.f9879a, new Intent(this.f9879a, (Class<?>) PressureCalibrateActivity.class));
            finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        LogUtil.a("NoDataActivity", "mPressureAutoTestLayout.getVisibility() ", Integer.valueOf(this.q.getVisibility()));
        if (this.q.getVisibility() == 8) {
            this.c.setVisibility(0);
        } else if (this.q.getVisibility() == 0) {
            this.c.setVisibility(8);
        } else {
            LogUtil.a("NoDataActivity", "mPressureAutoTestLayout Visibility = ", Integer.valueOf(this.q.getVisibility()));
        }
        nlg.cxS_(this.f9879a, new IBaseResponseCallback() { // from class: ptq
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                NoDataActivity.this.d(i, obj);
            }
        }, this.q, this.f9879a.getResources().getString(R$string.IDS_hw_open_auto_pressure_detector_content, sdg.a()), new String[]{"pressure_auto_detector_agree_no_again_tip", "pressure_auto_detector_dialog_time", "pressure_auto_detector_count", "pressure_auto_detetor_is_show"});
    }

    public /* synthetic */ void d(int i, Object obj) {
        LogUtil.a("NoDataActivity", "errorCode = ", Integer.valueOf(i));
        if (i == -1) {
            this.c.setVisibility(8);
            return;
        }
        if (i != 0) {
            if (i == 100000) {
                this.c.setVisibility(0);
                return;
            } else {
                LogUtil.a("NoDataActivity", "errCode is other and = ", Integer.valueOf(i));
                return;
            }
        }
        this.c.setVisibility(0);
        Intent intent = new Intent();
        intent.setFlags(268435456);
        intent.setPackage(BaseApplication.getAppPackage());
        intent.setClassName(BaseApplication.getAppPackage(), "com.huawei.ui.device.activity.pressautomonitor.PressAutoMonitorActivity");
        intent.putExtra("pressure_is_have_datas", false);
        intent.putExtra("from_card", true);
        gnm.aPB_(this.f9879a, intent);
        finish();
    }

    private void c() {
        this.j.setOnClickListener(this);
        this.l.setVisibility(0);
        if (!CommonUtil.bu() && (!Utils.o() || qrp.e("NoDataActivity", this.f9879a))) {
            pfe.doh_(3, this.l, null);
        }
        this.f9879a = this;
        this.y = new qaz(this.f9879a, false);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        LinearLayout linearLayout;
        super.onResume();
        LogUtil.a("NoDataActivity", "mIsAlreadySetInternet = ", Boolean.valueOf(this.h));
        if (!CommonUtil.aa(this.f9879a)) {
            this.h = true;
            this.b.setVisibility(8);
            this.k.setVisibility(0);
        } else {
            this.b.setVisibility(this.g ? 8 : 0);
            this.k.setVisibility(8);
            if (this.h && g() && (linearLayout = this.l) != null) {
                pfe.doh_(3, linearLayout, null);
            }
            this.h = false;
            String b = SharedPreferenceManager.b(this.f9879a, Integer.toString(10006), "pressure_no_data_is_first_inter");
            this.o = b;
            if (!"true".equals(b) || Utils.o()) {
                this.v.setVisibility(8);
                this.s.setVisibility(0);
            } else {
                this.v.setVisibility(0);
                this.s.setVisibility(8);
            }
            if (Utils.o()) {
                this.s.setOnClickListener(null);
            }
            this.c.setVisibility(0);
            DeviceInfo a2 = jpt.a("NoDataActivity");
            boolean isLogined = LoginInit.getInstance(this).getIsLogined();
            LogUtil.a("NoDataActivity", "onResume ", "isLogin = ", Boolean.valueOf(isLogined));
            if (isLogined && a2 != null) {
                LogUtil.a("NoDataActivity", "currentDeviceInfo", a2.toString());
                if (a2.getDeviceConnectState() == 2) {
                    e(a2);
                }
            }
        }
        boolean z = this.f;
        if (z) {
            LogUtil.a("NoDataActivity", "onResume mIsFirstVisible = ", Boolean.valueOf(z));
            h();
            m();
        }
        this.f = false;
    }

    private boolean g() {
        return (CommonUtil.bu() || CommonUtil.bf() || (Utils.o() && !qrp.e("NoDataActivity", this.f9879a))) ? false : true;
    }

    private void h() {
        MarketingApi marketingApi = (MarketingApi) Services.a("FeatureMarketing", MarketingApi.class);
        if (marketingApi != null) {
            HashMap hashMap = new HashMap();
            hashMap.put(4030, this.m);
            marketingApi.requestMarketingResource(new MarketingOption.Builder().setContext(this).setPageId(330).setLayoutMap(hashMap).build());
        }
    }

    private void e(DeviceInfo deviceInfo) {
        LogUtil.a("NoDataActivity", "isSupportPressAutoMonitor = ", Boolean.valueOf(b(deviceInfo)));
        if (b(deviceInfo)) {
            jqi.a().getSwitchSetting("press_auto_monitor_switch_status", new IBaseResponseCallback() { // from class: com.huawei.ui.main.stories.fitness.activity.pressuremeasure.NoDataActivity.2
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    LogUtil.a("NoDataActivity", "processIfDeviceSupportPressureAutoMonitor errorCode = ", Integer.valueOf(i));
                    if (i == -1) {
                        NoDataActivity.this.i.sendEmptyMessage(1000);
                        return;
                    }
                    if (i == 0 && (obj instanceof String)) {
                        if ("false".equals((String) obj)) {
                            NoDataActivity.this.i.sendEmptyMessage(1000);
                            return;
                        }
                        return;
                    }
                    LogUtil.a("NoDataActivity", "processIfDeviceSupportPressureAutoMonitor errorCode is other.");
                }
            });
        }
    }

    private boolean b(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            return false;
        }
        Map<String, DeviceCapability> a2 = jfq.c().a(1, deviceInfo.getDeviceIdentify(), "NoDataActivity");
        if (a2 == null || a2.isEmpty()) {
            LogUtil.h("NoDataActivity", "inquireDeviceMsdpCapability, deviceCapabilityHashMaps is empty");
            return false;
        }
        DeviceCapability deviceCapability = a2.get(deviceInfo.getDeviceIdentify());
        return deviceCapability != null && deviceCapability.isSupportPressAutoMonitor();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        LogUtil.c("NoDataActivity", "NoDataActivity onDestroy");
        this.h = false;
        long currentTimeMillis = this.r > 0 ? System.currentTimeMillis() - this.r : 0L;
        this.i.removeCallbacksAndMessages(null);
        HashMap hashMap = new HashMap(1);
        hashMap.put("keeptime", Long.valueOf(currentTimeMillis));
        gge.e(AnalyticsValue.HEALTH_PRESSUER_KEEPTIME_2160002.value(), hashMap);
        SharedPreferenceManager.e(this.f9879a, Integer.toString(10006), "pressure_no_data_is_first_inter", "true", new StorageParams());
    }

    private void e() {
        LoginInit.getInstance(this).browsingToLogin(new IBaseResponseCallback() { // from class: ptu
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                NoDataActivity.this.e(i, obj);
            }
        }, AnalyticsValue.HEALTH_PRESSUER_NODATAPAGE_ADJUST_CLICK_2160006.value());
    }

    public /* synthetic */ void e(int i, Object obj) {
        if (i == 0) {
            o();
        } else {
            LogUtil.h("NoDataActivity", "browsingToLogin errorCode is not success", Integer.valueOf(i));
        }
    }

    private void o() {
        LogUtil.c("NoDataActivity", "you click adjust button");
        DeviceInfo a2 = jpt.a("NoDataActivity");
        int i = (a2 == null || a2.getDeviceConnectState() != 2) ? 0 : 1;
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", 1);
        hashMap.put("havedevice", Integer.valueOf(i));
        gge.e(AnalyticsValue.HEALTH_PRESSUER_NODATAPAGE_ADJUST_CLICK_2160006.value(), hashMap);
        LogUtil.a("NoDataActivity", "dialog for click pressure adjust button ");
        if (this.y.c()) {
            this.y.d();
        } else if (this.y.b()) {
            this.y.a();
        } else {
            qba.b(this.f9879a, new IBaseResponseCallback() { // from class: pty
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i2, Object obj) {
                    NoDataActivity.this.a(i2, obj);
                }
            });
        }
    }

    public /* synthetic */ void a(int i, Object obj) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", 1);
        hashMap.put("havedevice", 1);
        gge.e(AnalyticsValue.HEALTH_PRESSUER_NODATAPAGE_ADJUST_CLICK_2160006.value(), hashMap);
        Intent intent = new Intent(this.f9879a, (Class<?>) PressureCalibrateActivity.class);
        intent.putExtra("pressure_is_have_datas", false);
        gnm.aPB_(this.f9879a, intent);
        finish();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_no_net_work) {
            CommonUtil.q(this.f9879a);
        } else if (id == R.id.pressure_guide_another) {
            p();
        } else {
            LogUtil.h("NoDataActivity", "unhandled click event! ");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void p() {
        HashMap hashMap = new HashMap(1);
        hashMap.put("click", "1");
        gge.e(AnalyticsValue.HEALTH_PRESSURE_DATA_ANALYSE_CLICK_2160016.value(), hashMap);
        if (TextUtils.isEmpty(this.n)) {
            LogUtil.b("NoDataActivity", "processSecondNoDataLayoutClickEvent mMessageCenterHost is empty");
            return;
        }
        Intent intent = new Intent(this.f9879a, (Class<?>) WebViewActivity.class);
        intent.putExtra("url", this.n + "/messageH5/sleephtml/salesPromotion.html");
        gnm.aPB_(this.f9879a, intent);
    }

    private void k() {
        int i;
        LogUtil.c("NoDataActivity", "you click detector button");
        HashMap hashMap = new HashMap(16);
        DeviceInfo a2 = jpt.a("NoDataActivity");
        if (a2 != null) {
            LogUtil.c("NoDataActivity", "currentDeviceInfo", a2.toString());
            if (a2.getDeviceConnectState() == 2) {
                i = 1;
                hashMap.put("click", 1);
                hashMap.put("havedevice", Integer.valueOf(i));
                gge.e(AnalyticsValue.HEALTH_PRESSUER_NODATA_MEASUREMENT_CLICK_2160008.value(), hashMap);
                qba.a(this.f9879a, new IBaseResponseCallback() { // from class: pts
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public final void d(int i2, Object obj) {
                        NoDataActivity.this.f(i2, obj);
                    }
                });
            }
        }
        i = 0;
        hashMap.put("click", 1);
        hashMap.put("havedevice", Integer.valueOf(i));
        gge.e(AnalyticsValue.HEALTH_PRESSUER_NODATA_MEASUREMENT_CLICK_2160008.value(), hashMap);
        qba.a(this.f9879a, new IBaseResponseCallback() { // from class: pts
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i2, Object obj) {
                NoDataActivity.this.f(i2, obj);
            }
        });
    }

    public /* synthetic */ void f(int i, Object obj) {
        if (i != 0) {
            if (i == 100001) {
                this.i.sendEmptyMessage(1001);
                return;
            } else {
                LogUtil.a("NoDataActivity", " pressureMeasurementButtonDialog onResponse err");
                return;
            }
        }
        Intent intent = new Intent(this.f9879a, (Class<?>) PressureMeasureActivity.class);
        intent.putExtra("pressure_is_have_datas", false);
        gnm.aPB_(this.f9879a, intent);
        finish();
        LogUtil.a("NoDataActivity", " startActivity PressureMeasureActivity ");
    }

    private void f() {
        if (this.w == null) {
            this.w = new pvw();
        }
        this.w.b(this.f9879a);
    }

    private void dtG_(Intent intent) {
        if (intent == null || !intent.getBooleanExtra("start_game", false)) {
            return;
        }
        f();
    }

    private void m() {
        H5ProPkgPreloadSyncTask.startTask(this.f9879a, "com.huawei.health.h5.breath-training", new H5PreloadCountStrategy(1));
    }
}
