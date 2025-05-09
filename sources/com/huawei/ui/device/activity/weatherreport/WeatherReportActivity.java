package com.huawei.ui.device.activity.weatherreport;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.hianalytics.visual.autocollect.HAWebViewInterface;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.radiobutton.HealthRadioButton;
import com.huawei.ui.commonui.switchbutton.HealthSwitchButton;
import com.huawei.ui.device.interactors.DeviceSettingsInteractors;
import defpackage.cvs;
import defpackage.jdi;
import defpackage.jqi;
import defpackage.jqy;
import defpackage.nrh;
import defpackage.nsn;
import defpackage.nsy;
import health.compact.a.CommonUtil;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.LinkedHashMap;

/* loaded from: classes6.dex */
public class WeatherReportActivity extends BaseActivity {
    private static final String[] c = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"};
    private Context b;
    private Context d;
    private DeviceSettingsInteractors e;
    private boolean h;
    private HealthRadioButton i;
    private jqi j;
    private RelativeLayout k;
    private HealthTextView l;
    private HealthSwitchButton m;
    private LinearLayout n;
    private HealthRadioButton o;

    /* renamed from: a, reason: collision with root package name */
    private DeviceCapability f9274a = null;
    private Handler f = new b(this);
    private CompoundButton.OnCheckedChangeListener g = new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.ui.device.activity.weatherreport.WeatherReportActivity.1
        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            boolean aa = CommonUtil.aa(WeatherReportActivity.this.d);
            LogUtil.a("WeatherReportActivity", "isChecked = " + z);
            if (!aa) {
                nrh.e(WeatherReportActivity.this.d, R.string._2130841588_res_0x7f020ff4);
                WeatherReportActivity.this.m.setChecked(!z);
                ViewClickInstrumentation.clickOnView(compoundButton);
                return;
            }
            WeatherReportActivity.this.h = z;
            if (z) {
                WeatherReportActivity.this.e();
            }
            WeatherReportActivity.this.m.setEnabled(false);
            LogUtil.a("WeatherReportActivity", "isChecked = " + z);
            WeatherReportActivity.this.h();
            if (z) {
                WeatherReportActivity.this.k.setEnabled(true);
                WeatherReportActivity.this.k.setAlpha(1.0f);
                WeatherReportActivity.this.i.setClickable(true);
                WeatherReportActivity.this.o.setClickable(true);
            } else {
                WeatherReportActivity.this.k.setEnabled(false);
                WeatherReportActivity.this.k.setAlpha(0.3f);
                WeatherReportActivity.this.i.setClickable(false);
                WeatherReportActivity.this.o.setClickable(false);
            }
            WeatherReportActivity.this.e.b(z);
            WeatherReportActivity.this.f.sendEmptyMessageDelayed(0, 300L);
            WeatherReportActivity.this.f();
            ViewClickInstrumentation.clickOnView(compoundButton);
        }
    };

    @Override // com.huawei.ui.commonui.base.BaseActivity, android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        Bundle bundleExtra;
        super.onCreate(bundle);
        setContentView(R.layout.activity_settings_weatherreport);
        this.d = this;
        this.b = BaseApplication.getContext();
        this.j = jqi.a();
        Intent intent = getIntent();
        if (intent != null && (bundleExtra = intent.getBundleExtra("key_cache_capability")) != null) {
            this.f9274a = (DeviceCapability) bundleExtra.getParcelable("key_bundle_cache_capability");
        }
        b();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity
    public void initViewTahiti() {
        d();
        HealthTextView healthTextView = this.l;
        if (healthTextView != null) {
            healthTextView.setPadding((int) getResources().getDimension(R.dimen._2131364635_res_0x7f0a0b1b), 0, (int) getResources().getDimension(R.dimen._2131364634_res_0x7f0a0b1a), 0);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        LogUtil.c("WeatherReportActivity", "onDestroy()");
        setResult(0, null);
        CommonUtil.a(this.d);
    }

    private void b() {
        this.e = DeviceSettingsInteractors.d((Context) this);
        ReleaseLogUtil.e("R_WeatherReportActivity", "initData getSwitchSetting.WEATHER_SWITCH_STATUS");
        String c2 = jqy.c("weather_switch_status");
        if (TextUtils.isEmpty(c2)) {
            this.j.getSwitchSetting("weather_switch_status", new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.weatherreport.WeatherReportActivity.2
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    ReleaseLogUtil.e("R_WeatherReportActivity", "getSwitchSetting.WEATHER_SWITCH_STATUS onResponse errorCode = ", Integer.valueOf(i));
                    if (i != 0 || !(obj instanceof String)) {
                        WeatherReportActivity.this.d((String) null);
                        return;
                    }
                    String str = (String) obj;
                    jqy.b("weather_switch_status", str);
                    WeatherReportActivity.this.d(str);
                }
            });
        } else {
            d(c2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str) {
        boolean z = !"false".equals(str);
        LogUtil.a("WeatherReportActivity", "WEATHER_SWITCH_STATUS isEnable = ", Boolean.valueOf(z));
        this.h = z;
        runOnUiThread(new Runnable() { // from class: com.huawei.ui.device.activity.weatherreport.WeatherReportActivity.4
            @Override // java.lang.Runnable
            public void run() {
                WeatherReportActivity.this.d();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        boolean z;
        Object systemService = getSystemService("location");
        if (!(systemService instanceof LocationManager)) {
            LogUtil.h("WeatherReportActivity", "checkLocationServiceStatus systemService not instanceof LocationManager");
            return;
        }
        LocationManager locationManager = (LocationManager) systemService;
        if (cvs.d() == null || !cvs.d().isWeatherPush()) {
            z = false;
        } else {
            LogUtil.a("WeatherReportActivity", "isWeather_push capability is:true");
            z = true;
        }
        if (locationManager.isProviderEnabled(GeocodeSearch.GPS) || locationManager.isProviderEnabled(HAWebViewInterface.NETWORK) || !z) {
            return;
        }
        LogUtil.a("WeatherReportActivity", "checkedChangeSwitch, before runOnUiThread.");
        runOnUiThread(new Runnable() { // from class: com.huawei.ui.device.activity.weatherreport.WeatherReportActivity.5
            @Override // java.lang.Runnable
            public void run() {
                WeatherReportActivity.this.a(R.string._2130841882_res_0x7f02111a, 102);
            }
        });
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        LogUtil.a("WeatherReportActivity", "Enter onActivityResult requestcode:" + i + ";resultcode:" + i2);
        super.onActivityResult(i, i2, intent);
        if (i == 102) {
            LogUtil.a("WeatherReportActivity", "Enter checkGpsPermission 2");
            c();
        } else {
            LogUtil.a("WeatherReportActivity", "Enter onActivityResult default");
        }
    }

    private void c() {
        Context context = this.b;
        String[] strArr = c;
        if (jdi.c(context, strArr)) {
            return;
        }
        e(strArr);
    }

    private void e(String[] strArr) {
        boolean c2 = jdi.c(this.b, strArr);
        LogUtil.a("WeatherReportActivity", "requestPermissions() isPermissionNeeded =" + c2);
        if (!c2) {
            jdi.bFL_(this, strArr, new PermissionsResultAction() { // from class: com.huawei.ui.device.activity.weatherreport.WeatherReportActivity.8
                @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                public void onGranted() {
                    LogUtil.a("WeatherReportActivity", "requestPermissions() permission onGranted()");
                }

                @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                public void onDenied(String str) {
                    LogUtil.b("WeatherReportActivity", "requestPermissions() permission onDenied()");
                }
            });
        } else {
            LogUtil.a("WeatherReportActivity", "requestPermissions() permission if (!hasPermissionNeeded) ELSE");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, final int i2) {
        NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(this.d).e(this.d.getString(i)).czC_(R.string._2130841131_res_0x7f020e2b, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.weatherreport.WeatherReportActivity.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("WeatherReportActivity", "showGPSSettingDialog, onclick.");
                Intent intent = new Intent();
                intent.setAction("android.settings.LOCATION_SOURCE_SETTINGS");
                try {
                    WeatherReportActivity.this.cTj_(intent, i2);
                } catch (ActivityNotFoundException unused) {
                    intent.setAction("android.settings.SETTINGS");
                    try {
                        WeatherReportActivity.this.cTj_(intent, i2);
                    } catch (ActivityNotFoundException unused2) {
                        LogUtil.b("WeatherReportActivity", "startActivity Exception!");
                    }
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czz_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.weatherreport.WeatherReportActivity.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("WeatherReportActivity", "showGpsDialog onclick NegativeButton");
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e();
        e.setCanceledOnTouchOutside(false);
        e.setCancelable(false);
        if (e.isShowing() || isFinishing()) {
            return;
        }
        e.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cTj_(Intent intent, int i) throws ActivityNotFoundException {
        ResolveInfo resolveActivity;
        PackageManager packageManager = getPackageManager();
        if (packageManager == null || (resolveActivity = packageManager.resolveActivity(intent, 65536)) == null) {
            return;
        }
        intent.setComponent(new ComponentName(resolveActivity.activityInfo.packageName, resolveActivity.activityInfo.name));
        startActivityForResult(intent, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        HealthTextView healthTextView = (HealthTextView) nsy.cMc_(this, R.id.tv_unit);
        RelativeLayout relativeLayout = (RelativeLayout) nsy.cMc_(this, R.id.rl_unit);
        this.k = relativeLayout;
        if (relativeLayout == null) {
            LogUtil.h("WeatherReportActivity", "initView mRlUnit is null");
            return;
        }
        this.l = (HealthTextView) nsy.cMc_(this, R.id.weather_report_content);
        RelativeLayout relativeLayout2 = (RelativeLayout) nsy.cMc_(this, R.id.weather_report_switch_item);
        HealthDivider healthDivider = (HealthDivider) nsy.cMc_(this, R.id.item_line);
        this.m = (HealthSwitchButton) nsy.cMc_(this, R.id.weather_report_switch_button);
        setViewSafeRegion(true, healthDivider);
        setViewSafeRegion(true, relativeLayout2);
        setViewSafeRegion(true, this.l);
        setViewSafeRegion(true, this.k);
        this.i = (HealthRadioButton) nsy.cMc_(this, R.id.rb_celsius);
        this.o = (HealthRadioButton) nsy.cMc_(this, R.id.rb_fahrenheit);
        LinearLayout linearLayout = (LinearLayout) nsy.cMc_(this, R.id.weather_report_img_layout);
        this.n = linearLayout;
        nsn.cLA_(linearLayout, 2);
        DeviceCapability deviceCapability = this.f9274a;
        if (deviceCapability != null && deviceCapability.isSupportUnitWeather()) {
            this.k.setVisibility(0);
            healthTextView.setVisibility(0);
            if (this.h) {
                this.k.setEnabled(true);
                this.i.setClickable(true);
                this.o.setClickable(true);
                this.k.setAlpha(1.0f);
            } else {
                this.k.setEnabled(false);
                this.i.setClickable(false);
                this.o.setClickable(false);
                this.k.setAlpha(0.3f);
            }
        } else {
            this.k.setVisibility(8);
            healthTextView.setVisibility(8);
        }
        a();
        j();
        if (this.l == null) {
            LogUtil.b("WeatherReportActivity", "ERROR widget get!");
            return;
        }
        this.m.setChecked(this.h);
        this.m.setOnCheckedChangeListener(this.g);
        h();
        f();
        BaseActivity.cancelLayoutById(this.l);
        HealthTextView healthTextView2 = (HealthTextView) nsy.cMc_(this, R.id.tv_celsius);
        if (healthTextView2 != null) {
            String obj = healthTextView2.getText().toString();
            if (!TextUtils.isEmpty(obj)) {
                String[] split = obj.split(" ");
                if (split.length > 0) {
                    healthTextView2.setContentDescription(split[0]);
                }
            }
        }
        HealthTextView healthTextView3 = (HealthTextView) nsy.cMc_(this, R.id.tv_fahrenheit);
        if (healthTextView3 != null) {
            String obj2 = healthTextView3.getText().toString();
            if (TextUtils.isEmpty(obj2)) {
                return;
            }
            String[] split2 = obj2.split(" ");
            if (split2.length > 0) {
                healthTextView3.setContentDescription(split2[0]);
            }
        }
    }

    private void a() {
        ReleaseLogUtil.e("R_WeatherReportActivity", "initData getSwitchSetting.WEATHER_SWITCH_UNIT_STATUS");
        this.j.getSwitchSetting("weather_switch_unit_status", new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.weatherreport.WeatherReportActivity.10
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                ReleaseLogUtil.e("R_WeatherReportActivity", "getSwitchSetting.WEATHER_SWITCH_UNIT_STATUS onResponse errorCode = ", Integer.valueOf(i));
                final boolean z = true;
                if (i == 0 && (obj instanceof String)) {
                    z = true ^ "false".equals((String) obj);
                } else if (UnitUtil.c()) {
                    z = false;
                }
                LogUtil.a("WeatherReportActivity", "WEATHER_SWITCH_UNIT_STATUS isEnable = ", Boolean.valueOf(z));
                WeatherReportActivity.this.runOnUiThread(new Runnable() { // from class: com.huawei.ui.device.activity.weatherreport.WeatherReportActivity.10.5
                    @Override // java.lang.Runnable
                    public void run() {
                        if (z) {
                            WeatherReportActivity.this.i.setChecked(true);
                            WeatherReportActivity.this.o.setChecked(false);
                        } else {
                            WeatherReportActivity.this.i.setChecked(false);
                            WeatherReportActivity.this.o.setChecked(true);
                        }
                    }
                });
            }
        });
    }

    private void j() {
        HealthRadioButton healthRadioButton = this.i;
        if (healthRadioButton == null || this.o == null) {
            LogUtil.b("WeatherReportActivity", "initViewOnCheckedChangeListener: mRbCelsius or mRbFahrenheit is null");
        } else {
            healthRadioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.ui.device.activity.weatherreport.WeatherReportActivity.9
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    if (!WeatherReportActivity.this.h) {
                        LogUtil.a("WeatherReportActivity", "mIsWeatherReportFlag is " + WeatherReportActivity.this.h);
                        ViewClickInstrumentation.clickOnView(compoundButton);
                        return;
                    }
                    if (z) {
                        LogUtil.a("WeatherReportActivity", "mRbCelsius is checked!");
                        WeatherReportActivity.this.o.setChecked(false);
                        WeatherReportActivity.this.e.c(true);
                        WeatherReportActivity.this.f();
                    }
                    ViewClickInstrumentation.clickOnView(compoundButton);
                }
            });
            this.o.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.ui.device.activity.weatherreport.WeatherReportActivity.3
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    if (!WeatherReportActivity.this.h) {
                        LogUtil.a("WeatherReportActivity", "mIsWeatherReportFlag is " + WeatherReportActivity.this.h);
                        ViewClickInstrumentation.clickOnView(compoundButton);
                        return;
                    }
                    if (z) {
                        LogUtil.a("WeatherReportActivity", "mRbFahrenheit is checked!");
                        WeatherReportActivity.this.i.setChecked(false);
                        WeatherReportActivity.this.e.c(false);
                        WeatherReportActivity.this.f();
                    }
                    ViewClickInstrumentation.clickOnView(compoundButton);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        if (this.h) {
            this.l.setText(nsn.ae(BaseApplication.getContext()) ? R.string._2130844360_res_0x7f021ac8 : R.string._2130842366_res_0x7f0212fe);
        } else {
            this.l.setText(nsn.ae(BaseApplication.getContext()) ? R.string._2130844359_res_0x7f021ac7 : R.string._2130842365_res_0x7f0212fd);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(16);
        linkedHashMap.put("state", this.h ? String.valueOf(1) : String.valueOf(0));
        DeviceCapability deviceCapability = this.f9274a;
        if (deviceCapability != null && deviceCapability.isSupportUnitWeather()) {
            linkedHashMap.put("unit", this.i.isChecked() ? String.valueOf(1) : String.valueOf(2));
        }
        OpAnalyticsUtil.getInstance().setEvent2nd(AnalyticsValue.SETTING_WEATHER_PUSH_1090032.value(), linkedHashMap);
    }

    class b extends Handler {
        WeakReference<WeatherReportActivity> b;

        b(WeatherReportActivity weatherReportActivity) {
            this.b = new WeakReference<>(weatherReportActivity);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (this.b.get() == null) {
                return;
            }
            removeMessages(0);
            WeatherReportActivity.this.m.setEnabled(true);
            LogUtil.a("WeatherReportActivity", "mHandler mIsWeatherReportFlag = " + WeatherReportActivity.this.h);
            WeatherReportActivity.this.m.setChecked(WeatherReportActivity.this.h);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
