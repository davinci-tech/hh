package com.huawei.ui.device.activity.update;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.device.connectivity.comm.MeasurableDevice;
import com.huawei.health.device.ui.DeviceMainActivity;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwversionmgr.manager.HwVersionManager;
import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.checkbox.HealthCheckBox;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.ceo;
import defpackage.cpa;
import defpackage.ctk;
import defpackage.cuw;
import defpackage.cvs;
import defpackage.cwc;
import defpackage.ixx;
import defpackage.jfu;
import defpackage.jpt;
import defpackage.jpu;
import defpackage.jqi;
import defpackage.kxy;
import defpackage.kxz;
import defpackage.nsn;
import defpackage.nsy;
import defpackage.oaf;
import defpackage.oaj;
import health.compact.a.CommonUtil;
import health.compact.a.LocalBroadcast;
import java.util.HashMap;
import java.util.LinkedHashMap;

/* loaded from: classes6.dex */
public class BandUpdateDialogActivity extends Activity implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private oaf f9245a;
    private String ac;
    private LinearLayout ad;
    private RelativeLayout b;
    private HealthButton c;
    private RelativeLayout d;
    private HealthButton e;
    private HealthCheckBox f;
    private String h;
    private jqi i;
    private HealthCheckBox j;
    private LinearLayout k;
    private boolean l;
    private boolean n;
    private HealthTextView p;
    private HealthTextView q;
    private HealthTextView r;
    private String s;
    private HealthTextView t;
    private HealthTextView u;
    private HealthTextView v;
    private HealthTextView w;
    private HealthTextView x;
    private HealthTextView y;
    private oaj z;
    private Context g = null;
    private boolean o = false;
    private String m = null;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.g = this;
        LogUtil.a("BandUpdateDialogActivity", "onCreate()");
        this.f9245a = oaf.b(this.g);
        this.i = jqi.a();
        setContentView(R.layout.activity_device_update_dialog);
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        int c = nsn.c(this.g, 24.0f);
        if (!CommonUtil.bh()) {
            attributes.y = c;
        }
        attributes.width = -1;
        attributes.height = -2;
        window.setAttributes(attributes);
        if (nsn.ag(this.g)) {
            window.setGravity(16);
        } else {
            window.setGravity(80);
        }
        e();
        a();
    }

    private void a() {
        Intent intent = getIntent();
        if (intent != null) {
            this.l = intent.getBooleanExtra("isScale", false);
            this.s = intent.getStringExtra("productId");
            this.ac = intent.getStringExtra("uniqueId");
            this.h = intent.getStringExtra("show_device_name");
            this.n = intent.getBooleanExtra("user_type", false);
            String stringExtra = intent.getStringExtra("mac");
            if (this.l) {
                this.z = oaj.a();
            }
            if (stringExtra != null) {
                this.m = a(stringExtra);
                LogUtil.a("BandUpdateDialogActivity", "initUpdateMode mac is ", CommonUtil.l(stringExtra), "mMac is ", CommonUtil.l(this.m));
                DeviceInfo e = jpt.e(this.m, "BandUpdateDialogActivity");
                if (e != null) {
                    this.h = e.getDeviceName();
                }
            }
            cSN_(intent);
            LogUtil.a("BandUpdateDialogActivity", "initUpdateMode, mIsShowBox = ", Boolean.valueOf(this.o));
            if (this.o) {
                this.j.setChecked(true);
                this.b.setVisibility(0);
            } else {
                this.b.setVisibility(8);
            }
            f();
            cSO_(intent);
        }
    }

    private void f() {
        DeviceCapability e = cvs.e(this.m);
        if (e != null && e.getIsSupportAutoUpdate()) {
            LogUtil.a("BandUpdateDialogActivity", "enter autoInstall");
            DeviceInfo e2 = jpt.e(this.m, "BandUpdateDialogActivity");
            if (e2 == null) {
                return;
            }
            String deviceUdid = e2.getDeviceUdid();
            this.d.setVisibility(0);
            d(deviceUdid);
            return;
        }
        LogUtil.a("BandUpdateDialogActivity", "enter autoInstall default");
        this.d.setVisibility(8);
    }

    private void cSO_(Intent intent) {
        if (this.l) {
            this.z.c(intent.getStringExtra("name"));
            this.z.j(nsn.b(this.g, intent.getIntExtra("size", 0)));
            oaj oajVar = this.z;
            oajVar.d(oajVar.h(intent.getStringExtra("message")));
            LogUtil.a("BandUpdateDialogActivity", "scaleNewVersion:", this.z.h(), "size:", this.z.o(), "detail:", this.z.f());
            d(this.z.h(), this.z.o(), this.z.f());
            return;
        }
        this.f9245a.g(intent.getStringExtra("name"));
        this.f9245a.j(nsn.b(this.g, intent.getIntExtra("size", 0)));
        oaf oafVar = this.f9245a;
        oafVar.f(oafVar.o(intent.getStringExtra("message")));
        LogUtil.a("BandUpdateDialogActivity", "bandNewVersion:", this.f9245a.b(this.m), "size:", this.f9245a.b(), "detail:", this.f9245a.e());
        d(this.f9245a.b(this.m), this.f9245a.b(), this.f9245a.e());
    }

    private String a(String str) {
        String deviceIdentify;
        int length;
        String deviceIdentify2;
        int length2;
        DeviceInfo a2 = jpt.a("BandUpdateDialogActivity");
        if (a2 != null && (length2 = (deviceIdentify2 = a2.getDeviceIdentify()).length()) > 4 && str.equalsIgnoreCase(deviceIdentify2.substring(length2 - 4, length2))) {
            return a2.getDeviceIdentify();
        }
        DeviceInfo d = jpu.d("BandUpdateDialogActivity");
        return (d == null || (length = (deviceIdentify = d.getDeviceIdentify()).length()) <= 4 || !str.equalsIgnoreCase(deviceIdentify.substring(length + (-4), length))) ? "" : d.getDeviceIdentify();
    }

    private void cSN_(Intent intent) {
        if (intent == null) {
            return;
        }
        String str = this.s;
        if (str == null) {
            LogUtil.a("BandUpdateDialogActivity", "mProductId is null");
            this.o = intent.getBooleanExtra(ParamConstants.CallbackMethod.ON_SHOW, false);
            return;
        }
        try {
            cuw c = jfu.c(Integer.parseInt(str));
            if (c.u() == 1) {
                this.o = true;
            } else if (c.u() == 2) {
                this.o = false;
            } else {
                this.o = intent.getBooleanExtra(ParamConstants.CallbackMethod.ON_SHOW, false);
            }
        } catch (NumberFormatException unused) {
            LogUtil.a("BandUpdateDialogActivity", "mProductId = ", this.s);
            this.o = intent.getBooleanExtra(ParamConstants.CallbackMethod.ON_SHOW, false);
        }
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        if (this.l) {
            if (kxy.a(this.g, this.s) == null && TextUtils.isEmpty(kxy.a(this.g, this.s))) {
                LogUtil.a("BandUpdateDialogActivity", "onResume() getScaleCheckNewVersion is empty!");
                finish();
                return;
            }
            return;
        }
        if (kxz.j(this.g, this.m) == null || !TextUtils.isEmpty(kxz.j(this.g, this.m))) {
            return;
        }
        LogUtil.a("BandUpdateDialogActivity", "onResume() getBandCheckNewVersion is empty!");
        finish();
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        CommonUtil.a(this.g);
        super.onDestroy();
        this.g = null;
        if (this.f9245a != null) {
            LogUtil.a("BandUpdateDialogActivity", "ondestroy updateInteractor release");
            this.f9245a = null;
        }
        LogUtil.a("BandUpdateDialogActivity", "onDestroy()");
    }

    private void e() {
        LogUtil.a("BandUpdateDialogActivity", "Enter initView!");
        this.ad = (LinearLayout) nsy.cMc_(this, R.id.AppUpdateDialog_show_changelog_band);
        this.x = (HealthTextView) nsy.cMc_(this, R.id.AppUpdateDialog_changelog_title_band);
        this.p = (HealthTextView) nsy.cMc_(this, R.id.AppUpdateDialog_changelog_application_band);
        this.q = (HealthTextView) nsy.cMc_(this, R.id.AppUpdateDialog_changelog_application_value_band);
        this.y = (HealthTextView) nsy.cMc_(this, R.id.AppUpdateDialog_changelog_version_band);
        this.u = (HealthTextView) nsy.cMc_(this, R.id.AppUpdateDialog_changelog_version_value_band);
        this.w = (HealthTextView) nsy.cMc_(this, R.id.AppUpdateDialog_changelog_size_band);
        this.v = (HealthTextView) nsy.cMc_(this, R.id.AppUpdateDialog_changelog_size_value_band);
        this.r = (HealthTextView) nsy.cMc_(this, R.id.AppUpdateDialog_changelog_detail_band);
        this.t = (HealthTextView) nsy.cMc_(this, R.id.AppUpdateDialog_changelog_context_band);
        this.c = (HealthButton) nsy.cMc_(this, R.id.AppUpdateDialog_show_left_band);
        this.e = (HealthButton) nsy.cMc_(this, R.id.AppUpdateDialog_show_right_band);
        this.k = (LinearLayout) nsy.cMc_(this, R.id.AppUpdateDialog_notification_band);
        this.b = (RelativeLayout) nsy.cMc_(this, R.id.AppUpdateDialog_ota_auto_update_band);
        this.j = (HealthCheckBox) nsy.cMc_(this, R.id.AppUpdateDialog_checkbox_band);
        this.d = (RelativeLayout) nsy.cMc_(this, R.id.update_dialog_ota_auto_install_band);
        this.f = (HealthCheckBox) nsy.cMc_(this, R.id.update_dialog_install_checkbox_band);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        LogUtil.a("BandUpdateDialogActivity", "onclick :" + id);
        if (id == R.id.AppUpdateDialog_show_left_band) {
            LogUtil.a("BandUpdateDialogActivity", "user choose not update");
            kxz.q(BaseApplication.getContext());
            d(false);
            finish();
        } else if (id == R.id.AppUpdateDialog_show_right_band) {
            if (nsn.o()) {
                LogUtil.h("BandUpdateDialogActivity", "user choose update return");
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            LogUtil.a("BandUpdateDialogActivity", "user choose update");
            LogUtil.a("BandUpdateDialogActivity", "WLAN update show is " + this.o);
            if (this.o) {
                LogUtil.a("BandUpdateDialogActivity", "user choose update mCheckAutoUpdate isChecked = " + this.j.isChecked());
                c();
                Intent intent = new Intent("com.huawei.health.action.ACTION_BAND_UPDATE_DIALOG");
                intent.setPackage(this.g.getPackageName());
                intent.putExtra("band_update_dialog", this.j.isChecked());
                BaseApplication.getContext().sendBroadcast(intent, LocalBroadcast.c);
            }
            b();
            d(true);
        } else {
            LogUtil.a("BandUpdateDialogActivity", "onClick id is " + id);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void c() {
        HashMap hashMap = new HashMap(16);
        if (this.j.isChecked()) {
            this.i.setSwitchSetting("wlan_auto_update", "1", null);
            hashMap.put("type", "1");
        } else {
            this.i.setSwitchSetting("wlan_auto_update", "2", null);
            hashMap.put("type", "0");
        }
        DeviceCapability e = cvs.e(this.m);
        if (e != null && e.getIsSupportAutoUpdate()) {
            LogUtil.a("BandUpdateDialogActivity", "enter handleSaveState");
            DeviceInfo e2 = jpt.e(this.m, "BandUpdateDialogActivity");
            if (e2 == null) {
                return;
            }
            String deviceUdid = e2.getDeviceUdid();
            if (this.f.isChecked()) {
                oaf.b(BaseApplication.getContext()).c(this.m, true);
                jqi.a().setSwitchSetting("auto_update_status", "true", deviceUdid, null);
            } else {
                oaf.b(BaseApplication.getContext()).c(this.m, false);
                jqi.a().setSwitchSetting("auto_update_status", "false", deviceUdid, null);
            }
        }
        hashMap.put("click", 1);
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.SETTING_1090006.value(), hashMap, 0);
    }

    private void d(String str) {
        jqi.a().getSwitchSetting("auto_update_status", str, new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.update.BandUpdateDialogActivity.1
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("BandUpdateDialogActivity", "getAutoSwitchStatus errorCode = ", Integer.valueOf(i));
                if (i == 0 && (obj instanceof String)) {
                    if (TextUtils.equals("true", String.valueOf(obj))) {
                        BandUpdateDialogActivity.this.b("auto_update_status", true);
                    } else {
                        BandUpdateDialogActivity.this.b("auto_update_status", false);
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final String str, final boolean z) {
        runOnUiThread(new Runnable() { // from class: com.huawei.ui.device.activity.update.BandUpdateDialogActivity.4
            @Override // java.lang.Runnable
            public void run() {
                if (TextUtils.equals(str, "wlan_auto_update")) {
                    BandUpdateDialogActivity.this.j.setChecked(z);
                } else if (TextUtils.equals(str, "auto_update_status")) {
                    BandUpdateDialogActivity.this.f.setChecked(z);
                } else {
                    LogUtil.h("BandUpdateDialogActivity", "updateUiState error");
                }
            }
        });
    }

    private void d(String str, String str2, String str3) {
        LogUtil.a("BandUpdateDialogActivity", "Enter showAppNewVersion");
        this.ad.setVisibility(0);
        this.k.setVisibility(8);
        this.x.setText(this.g.getString(R.string._2130841460_res_0x7f020f74));
        this.p.setText(this.g.getString(R.string.IDS_device_msgnotif_applications));
        this.q.setText(this.h);
        this.y.setText(this.g.getString(R.string._2130841852_res_0x7f0210fc));
        this.u.setText(str);
        this.w.setText(this.g.getString(R.string._2130841853_res_0x7f0210fd));
        this.v.setText(str2);
        this.r.setText(this.g.getString(R.string._2130841854_res_0x7f0210fe));
        this.t.setText(str3);
        this.c.setText(this.g.getString(R.string._2130841855_res_0x7f0210ff));
        this.e.setText(this.g.getString(R.string._2130841856_res_0x7f021100));
        this.c.setOnClickListener(this);
        this.e.setOnClickListener(this);
    }

    private void b() {
        LogUtil.a("BandUpdateDialogActivity", "enterBandupdate mIsScale=", Boolean.valueOf(this.l));
        if (this.l) {
            d();
            return;
        }
        Intent intent = new Intent();
        intent.setClass(this.g, UpdateVersionActivity.class);
        intent.putExtra("device_id", this.m);
        try {
            this.g.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("BandUpdateDialogActivity", "enterBandupdate exception");
        }
        finish();
    }

    private void d() {
        ctk ctkVar;
        LogUtil.a("BandUpdateDialogActivity", "enterScaleBandUpdate mProductId=", this.s, ", mIsMainUser=", Boolean.valueOf(this.n));
        MeasurableDevice e = ceo.d().e(this.s, false);
        if (e == null) {
            LogUtil.h("BandUpdateDialogActivity", "enterScaleBandUpdate bondedDevice is null");
            finish();
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("productId", this.s);
        ContentValues contentValues = new ContentValues();
        if (cpa.x(this.s)) {
            if (e instanceof ctk) {
                LogUtil.a("BandUpdateDialogActivity", "is wifi device");
                ctkVar = (ctk) e;
                if (TextUtils.isEmpty(this.ac)) {
                    this.ac = ctkVar.getUniqueId();
                }
            } else {
                ctkVar = null;
            }
            if (!cpa.c(this.s, this.ac)) {
                cSP_(intent, true);
            } else if (ctkVar != null) {
                cSP_(intent, true);
            } else {
                cSP_(intent, false);
            }
        } else if (cpa.au(this.s)) {
            cSP_(intent, true);
        } else {
            intent.setClass(this.g, WeightUpdateVersionActivity.class);
            intent.putExtra("isUpdateDialog", true);
            intent.putExtra("user_type", this.n);
        }
        contentValues.put("productId", this.s);
        contentValues.put("uniqueId", this.ac);
        intent.putExtra("commonDeviceInfo", contentValues);
        try {
            this.g.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("BandUpdateDialogActivity", "enterScaleBandUpdate exception");
        }
        finish();
    }

    private void cSP_(Intent intent, boolean z) {
        if (z) {
            intent.setClass(this.g, DeviceMainActivity.class);
            intent.putExtra("view", "WifiVersionDetails");
        } else {
            intent.setClass(this.g, WeightUpdateVersionActivity.class);
            intent.putExtra("isUpdateDialog", true);
            intent.putExtra("user_type", true);
            intent.putExtra("isFromNotify", true);
        }
    }

    private void d(boolean z) {
        if (this.l) {
            LogUtil.h("BandUpdateDialogActivity", "sendOtaUpdateEvent scale is not supported currently.");
            return;
        }
        LogUtil.a("BandUpdateDialogActivity", "enter sendOtaUpdateEvent.");
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(16);
        DeviceInfo e = jpt.e(this.m, "BandUpdateDialogActivity");
        if (e == null) {
            LogUtil.a("BandUpdateDialogActivity", "enter sendOtaUpdateEvent decide is null.");
            return;
        }
        int productType = e.getProductType();
        if (productType != -1) {
            linkedHashMap.put(DeviceCategoryFragment.DEVICE_TYPE, String.valueOf(productType));
            linkedHashMap.put("device_classfication", String.valueOf(cwc.b(productType)));
        }
        if (z) {
            LogUtil.a("BandUpdateDialogActivity", "sendOtaUpdateEvent isSelectAutoUpdate::", Boolean.valueOf(this.j.isChecked()));
            if (this.j.isChecked()) {
                linkedHashMap.put("operationID", "3");
            } else {
                linkedHashMap.put("operationID", "4");
            }
        } else {
            linkedHashMap.put("operationID", "1");
        }
        oaf oafVar = this.f9245a;
        if (oafVar != null) {
            linkedHashMap.put("deviceNewVersion", oafVar.b(this.m));
        }
        linkedHashMap.put("versionID", HwVersionManager.c(this.g).i(this.m));
        OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_DEVICE_OTA_UPDATE_80020004.value(), linkedHashMap);
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
