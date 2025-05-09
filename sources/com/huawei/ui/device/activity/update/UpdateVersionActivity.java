package com.huawei.ui.device.activity.update;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Message;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.fullscenarioservice.pluginwearota.callback.InitCallback;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.devicemgr.api.constant.ExecuteMode;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwdevice.mainprocess.mgr.hwotamanager.HwOtaUpgradeManager;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwversionmgr.manager.HwVersionManager;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.checkbox.HealthCheckBox;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressindicator.HealthProgressIndicator;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.device.activity.update.UpdateVersionActivity;
import defpackage.cun;
import defpackage.cuw;
import defpackage.cvc;
import defpackage.cvs;
import defpackage.cvt;
import defpackage.cvx;
import defpackage.cwc;
import defpackage.cwf;
import defpackage.cwi;
import defpackage.ixx;
import defpackage.jds;
import defpackage.jfu;
import defpackage.jhf;
import defpackage.jkj;
import defpackage.jkk;
import defpackage.jkn;
import defpackage.jko;
import defpackage.jkv;
import defpackage.joh;
import defpackage.jpp;
import defpackage.jpt;
import defpackage.jqi;
import defpackage.jrd;
import defpackage.kxz;
import defpackage.nrh;
import defpackage.nsf;
import defpackage.nsn;
import defpackage.nsy;
import defpackage.nyi;
import defpackage.oaf;
import defpackage.oau;
import defpackage.sqo;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

/* loaded from: classes6.dex */
public class UpdateVersionActivity extends BaseActivity implements View.OnClickListener {
    private ImageView ae;
    private HealthCardView af;
    private HealthTextView ag;
    private String ah;
    private HealthTextView ai;
    private LinearLayout ak;
    private HealthTextView al;
    private String am;
    private RelativeLayout ao;
    private LinearLayout ap;
    private String aq;
    private HealthTextView ar;
    private HealthButton as;
    private HealthCardView at;
    private oaf au;
    private HealthTextView av;
    private HealthTextView aw;
    private HealthTextView ax;
    private HealthTextView ba;
    private LinearLayout bb;
    private String d;
    private HealthTextView f;
    private ImageView g;
    private CustomTitleBar i;
    private HealthCardView j;
    private RelativeLayout k;
    private HealthProgressIndicator l;
    private ImageView m;
    private RelativeLayout n;
    private ImageView o;
    private HealthProgressIndicator p;
    private int q;
    private String r;
    private RelativeLayout s;
    private DeviceInfo t;
    private ImageView u;
    private boolean y;
    private Context h = null;
    private int an = -1;
    private BroadcastReceiver e = null;
    private boolean w = true;
    private boolean aa = false;
    private boolean z = false;
    private boolean ab = false;
    private CustomTextAlertDialog v = null;
    private CustomTextAlertDialog bc = null;
    private CustomTextAlertDialog aj = null;
    private boolean ac = false;
    private boolean ad = false;
    private nyi.b x = new nyi.b(this);
    private boolean c = false;
    private BroadcastReceiver b = new BroadcastReceiver() { // from class: com.huawei.ui.device.activity.update.UpdateVersionActivity.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            LogUtil.a("UpdateVersionActivity", "onReceive: action = ", action);
            if ("action_app_check_new_version_state".equals(action)) {
                UpdateVersionActivity.this.cSV_(intent);
            }
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private final BroadcastReceiver f9255a = new BroadcastReceiver() { // from class: com.huawei.ui.device.activity.update.UpdateVersionActivity.12
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Parcelable parcelableExtra;
            LogUtil.a("UpdateVersionActivity", "mConnectStateChangedReceiver(): intent = ", intent.getAction());
            if (context == null || !"com.huawei.bone.action.CONNECTION_STATE_CHANGED".equals(intent.getAction()) || (parcelableExtra = intent.getParcelableExtra("deviceinfo")) == null) {
                return;
            }
            if (!(parcelableExtra instanceof DeviceInfo)) {
                LogUtil.a("UpdateVersionActivity", "! parcelableExtra instanceof DeviceInfo ");
                return;
            }
            DeviceInfo deviceInfo = (DeviceInfo) parcelableExtra;
            if (!TextUtils.equals(deviceInfo.getDeviceIdentify(), UpdateVersionActivity.this.ah)) {
                LogUtil.h("UpdateVersionActivity", "!TextUtils.equals(deviceIdentify, mMacAddress)");
                return;
            }
            int deviceConnectState = deviceInfo.getDeviceConnectState();
            ReleaseLogUtil.e("DEVMGR_UpdateVersionActivity", "mConnectStateChangedReceiver(): state = ", Integer.valueOf(deviceConnectState));
            if (deviceConnectState == 2) {
                UpdateVersionActivity.this.w = true;
                return;
            }
            if (deviceConnectState == 3) {
                UpdateVersionActivity updateVersionActivity = UpdateVersionActivity.this;
                updateVersionActivity.c(updateVersionActivity.h.getString(R.string._2130841439_res_0x7f020f5f));
                UpdateVersionActivity.this.w = false;
            } else if (deviceConnectState == 4) {
                UpdateVersionActivity updateVersionActivity2 = UpdateVersionActivity.this;
                updateVersionActivity2.c(updateVersionActivity2.h.getString(R.string.IDS_device_switch_device_connect_fail));
            } else {
                LogUtil.a("UpdateVersionActivity", "mConnectStateChangedReceiver() default");
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public void cSV_(Intent intent) {
        int intExtra = intent.getIntExtra("state", -1);
        int intExtra2 = intent.getIntExtra("result", -1);
        String stringExtra = intent.getStringExtra("extra_band_imei");
        ReleaseLogUtil.e("DEVMGR_UpdateVersionActivity", "updateAppState: state = ", intExtra + ", result = ", Integer.valueOf(intExtra2));
        if (!TextUtils.equals(stringExtra, this.ah)) {
            LogUtil.h("UpdateVersionActivity", "deviceId != mMacAddress");
            return;
        }
        intent.getStringExtra("content");
        b(intExtra, intExtra2);
        d(intExtra, intExtra2);
        a(intExtra, intExtra2);
    }

    private void b(int i, int i2) {
        switch (i) {
            case 10:
                ReleaseLogUtil.e("DEVMGR_UpdateVersionActivity", "STATE_CHECK_NEW_VERSION_START: mUpdateInteractors.mUpdateStatus = ", Integer.valueOf(k()));
                break;
            case 11:
                ReleaseLogUtil.e("DEVMGR_UpdateVersionActivity", "STATE_CHECK_NEW_VERSION_FAILED: mUpdateInteractors.mUpdateStatus = ", Integer.valueOf(k()));
                if (k() == 1) {
                    if (i2 == 0) {
                        ReleaseLogUtil.e("DEVMGR_UpdateVersionActivity", "No New Version");
                        c();
                        v();
                        jkj.d().b(false);
                        break;
                    } else {
                        d(i2);
                        sqo.as("STATE_CHECK_NEW_VERSION_FAILED");
                        break;
                    }
                }
                break;
            case 12:
                ReleaseLogUtil.e("DEVMGR_UpdateVersionActivity", "STATE_CHECK_NEW_VERSION_SUCCESS: mUpdateInteractors.mUpdateStatus = ", Integer.valueOf(k()));
                kxz.e(i2, this.h, kxz.j(this.h, this.ah), this.ah);
                ReleaseLogUtil.e("DEVMGR_UpdateVersionActivity", "STATE_CHECK_NEW_VERSION_SUCCESS: mBandNewVersion = ", this.au.b(this.ah));
                break;
            default:
                LogUtil.a("UpdateVersionActivity", "updateAppStateOne default");
                break;
        }
    }

    private void d(int i, int i2) {
        ReleaseLogUtil.e("DEVMGR_UpdateVersionActivity", "updateAppStateTwo: getOtaStatus:", Integer.valueOf(k()), ", state:", Integer.valueOf(i));
        if (i != 20) {
            switch (i) {
                case 30:
                    if (k() == 1) {
                        jkj.d().d(this.ah, 2);
                        break;
                    }
                    break;
                case 31:
                    if (k() == 2) {
                        a(this.h.getString(R.string._2130841545_res_0x7f020fc9));
                        break;
                    }
                    break;
                case 32:
                    int k = k();
                    if (i2 != 13) {
                        if (k == 2 || k == 4) {
                            ab();
                        }
                        if (k == 6) {
                            nyi.e(this.h, false, this.ah);
                            finish();
                            break;
                        }
                    } else {
                        jkj.d().d(this.ah, 2);
                        a();
                        break;
                    }
                    break;
                default:
                    LogUtil.a("UpdateVersionActivity", "updateAppStateTwo default");
                    break;
            }
        }
    }

    private void ab() {
        String j = HwVersionManager.c(BaseApplication.getContext()).j(this.ah);
        ReleaseLogUtil.e("DEVMGR_UpdateVersionActivity", "refreshVersionPage: bandCurrentVersion:", this.d, ", bandNewVersion:", j);
        if (TextUtils.isEmpty(this.d) || TextUtils.isEmpty(j)) {
            v();
            return;
        }
        DeviceCapability d2 = cvs.d();
        if (this.d.equals(j)) {
            v();
        } else {
            e(j, d2);
        }
    }

    private void e(String str, DeviceCapability deviceCapability) {
        jkj.d().a(this.ah, 1);
        if (deviceCapability != null && deviceCapability.getIsSupportNotifyDeviceNewVersion()) {
            this.x.sendEmptyMessageDelayed(1, 5000L);
            jko jkoVar = new jko();
            jkoVar.a(this.ah);
            jkoVar.e(str);
            jkoVar.c(CommonUtil.g(kxz.d(BaseApplication.getContext(), this.ah, str)));
            jkoVar.a(System.currentTimeMillis() / 1000);
            jkoVar.b(1);
            jkoVar.a(0);
            String f = kxz.f(this.h, this.ah);
            if (!TextUtils.isEmpty(f)) {
                jkoVar.e(jds.c(f, 10));
            }
            ReleaseLogUtil.e("DEVMGR_UpdateVersionActivity", "setUpdateMessage sendUpdateMessage");
            jkv.b().e(jkoVar, f, new b(this));
            return;
        }
        a();
    }

    public static class b implements IBaseResponseCallback {
        private final WeakReference<UpdateVersionActivity> c;

        b(UpdateVersionActivity updateVersionActivity) {
            this.c = new WeakReference<>(updateVersionActivity);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(final int i, Object obj) {
            ReleaseLogUtil.e("DEVMGR_UpdateVersionActivity", "STATE_CHECK_NEW_VERSION_SUCCESS: errorCode = ", Integer.valueOf(i));
            final UpdateVersionActivity updateVersionActivity = this.c.get();
            if (updateVersionActivity == null) {
                return;
            }
            updateVersionActivity.x.removeMessages(1);
            updateVersionActivity.runOnUiThread(new Runnable() { // from class: nyo
                @Override // java.lang.Runnable
                public final void run() {
                    UpdateVersionActivity.b.c(i, updateVersionActivity);
                }
            });
        }

        public static /* synthetic */ void c(int i, UpdateVersionActivity updateVersionActivity) {
            if (i == 109023) {
                updateVersionActivity.v();
                return;
            }
            if (i == 109028) {
                updateVersionActivity.v();
                updateVersionActivity.c();
                nrh.d(updateVersionActivity.h, updateVersionActivity.h.getResources().getString(R.string.IDS_device_ear_upgrading));
            } else if (i == 109024) {
                updateVersionActivity.aa = true;
                updateVersionActivity.a();
            } else {
                updateVersionActivity.a();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        kxz.j("", this.ah, this.h);
        kxz.d("", this.ah, this.h);
        kxz.e("", this.ah, this.h);
    }

    private void a(int i, int i2) {
        ReleaseLogUtil.e("DEVMGR_UpdateVersionActivity", "STATE_DOWNLOAD_APP: otaStatus = ", Integer.valueOf(k()), ",state = ", Integer.valueOf(i));
        switch (i) {
            case 19:
                x();
                break;
            case 20:
            default:
                LogUtil.a("UpdateVersionActivity", "updateAppStateThree default");
                break;
            case 21:
                a(i2);
                break;
            case 22:
                String a2 = nyi.a(this.h, i2);
                b(a2);
                sqo.as(a2);
                break;
            case 23:
                jkj.d().b(true);
                CustomTextAlertDialog customTextAlertDialog = this.v;
                if (customTextAlertDialog != null && customTextAlertDialog.isShowing()) {
                    this.v.dismiss();
                }
                ah();
                e(23, i2);
                if (kxz.o(this.h)) {
                    nyi.e(this.h, false, this.ah);
                }
                finish();
                break;
        }
    }

    private void d(int i) {
        String string;
        if (i == 1) {
            string = this.h.getResources().getString(R.string._2130844882_res_0x7f021cd2);
        } else if (i == 2) {
            string = this.h.getResources().getString(R.string._2130841552_res_0x7f020fd0);
        } else if (i == 4) {
            string = this.h.getString(R.string._2130841495_res_0x7f020f97);
            if (nsn.ae(BaseApplication.getContext())) {
                string = this.h.getString(R.string._2130844350_res_0x7f021abe, UnitUtil.e(10.0d, 2, 0));
            }
        } else {
            string = this.h.getResources().getString(R.string._2130841553_res_0x7f020fd1);
        }
        a(string);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.h = this;
        ReleaseLogUtil.e("DEVMGR_UpdateVersionActivity", "onCreate()");
        r();
        if (TextUtils.isEmpty(this.ah) && TextUtils.isEmpty(this.am)) {
            ReleaseLogUtil.d("DEVMGR_UpdateVersionActivity", "onCreate macAddress and mPhdDeviceUdid isEmpty");
            finish();
        } else {
            h();
        }
    }

    private void h() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.device.activity.update.UpdateVersionActivity.11
            @Override // java.lang.Runnable
            public void run() {
                List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "UpdateVersionActivity");
                if (deviceList == null) {
                    cun.c().executeDeviceRelatedLogic(ExecuteMode.FORCE_INIT_PHONE_SERVICE, null, "UpdateVersionActivity");
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                        LogUtil.b("UpdateVersionActivity", "getCurrentDeviceInfo InterruptedException ", ExceptionUtils.d(e));
                        sqo.as("getCurrentDeviceInfo InterruptedException");
                    }
                    deviceList = cun.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "UpdateVersionActivity");
                }
                LogUtil.a("UpdateVersionActivity", "deviceList ", deviceList);
                if (deviceList == null || deviceList.size() <= 0) {
                    UpdateVersionActivity.this.t = null;
                    UpdateVersionActivity.this.x.sendEmptyMessage(2);
                    return;
                }
                for (DeviceInfo deviceInfo : deviceList) {
                    if ((!TextUtils.isEmpty(UpdateVersionActivity.this.am) && UpdateVersionActivity.this.am.equals(deviceInfo.getDeviceUdid())) || (!TextUtils.isEmpty(UpdateVersionActivity.this.ah) && UpdateVersionActivity.this.ah.equals(deviceInfo.getDeviceIdentify()))) {
                        UpdateVersionActivity.this.t = deviceInfo;
                        break;
                    }
                }
                UpdateVersionActivity.this.x.sendEmptyMessage(2);
            }
        });
    }

    public void d() {
        DeviceInfo deviceInfo = this.t;
        if (deviceInfo == null) {
            LogUtil.h("UpdateVersionActivity", "onCreate deviceInfo == null");
            Intent intent = new Intent();
            intent.setClassName(this, "com.huawei.ui.homehealth.device.devicelist.DeviceMoreListActivity");
            startActivity(intent);
            finish();
            return;
        }
        this.am = deviceInfo.getDeviceUdid();
        this.ah = this.t.getDeviceIdentify();
        boolean l = Utils.l();
        LogUtil.h("UpdateVersionActivity", "onCreate isOverseaCloud ", Boolean.valueOf(l));
        if (cwi.c(this.t, 108) && !l) {
            m();
            return;
        }
        b(this.t);
        this.d = this.t.getSoftVersion();
        this.aq = this.t.getDeviceSplicingProductVersion();
        this.r = this.t.getDeviceOStVersion();
        boolean z = false;
        if (cvt.c(this.t.getProductType())) {
            this.ac = true;
        } else {
            this.ac = false;
        }
        this.au = oaf.b(this.h);
        setContentView(R.layout.activity_update_version_new);
        getWindow().setFlags(16777216, 16777216);
        jkj.d().o();
        if (cwi.c(this.t, 58) || (cwi.c(this.t, 108) && l)) {
            z = true;
        }
        this.c = z;
        t();
        o();
    }

    private void r() {
        Intent intent = getIntent();
        if (intent == null) {
            LogUtil.h("UpdateVersionActivity", "onCreate it == null");
            finish();
        } else {
            this.ah = intent.getStringExtra("device_id");
            this.am = intent.getStringExtra("UNIQUE_ID");
            this.w = intent.getBooleanExtra("connect_status", true);
        }
    }

    private void m() {
        HwOtaUpgradeManager.a().d(this.am, new InitCallback() { // from class: com.huawei.ui.device.activity.update.UpdateVersionActivity.14
            @Override // com.huawei.fullscenarioservice.pluginwearota.callback.InitCallback
            public void onInitComplete(int i) {
                LogUtil.a("UpdateVersionActivity", "onInitComplete code:", Integer.valueOf(i));
                Message obtainMessage = UpdateVersionActivity.this.x.obtainMessage();
                obtainMessage.what = 3;
                obtainMessage.arg1 = i;
                obtainMessage.obj = UpdateVersionActivity.this.am;
                UpdateVersionActivity.this.x.sendMessage(obtainMessage);
            }
        });
    }

    private void o() {
        jkn.a().e();
        jkj.d().b(true);
        DeviceCapability e = cvs.e(this.ah);
        if (e != null && e.getIsSupportAutoUpdate()) {
            LogUtil.a("UpdateVersionActivity", "mIsSupportBackground is true");
            this.ad = true;
        }
        int k = k();
        ReleaseLogUtil.e("DEVMGR_UpdateVersionActivity", "initData otaStatus is,", Integer.valueOf(k));
        if (k == 6) {
            LogUtil.a("UpdateVersionActivity", "onCreate, device is OTAing");
            nyi.e(this.h, false, this.ah);
            finish();
        } else if (k == 4 || k == 5) {
            LogUtil.a("UpdateVersionActivity", "onCreate, device is background transfer or failed");
            sqo.as("onCreate, device is background transfer or failed");
            nyi.e(this.h, false, this.ah);
            finish();
        } else if (k == 3) {
            LogUtil.a("UpdateVersionActivity", "onCreate, device is background downloading");
            x();
            a(jkk.d().i(this.ah));
            aa();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("action_app_check_new_version_state");
            BroadcastManagerUtil.bFC_(this.h, this.b, intentFilter, LocalBroadcast.c, null);
        } else if (k == 2 && !TextUtils.isEmpty(jkj.d().b())) {
            LogUtil.a("UpdateVersionActivity", "onCreate, device is background download failed");
            sqo.as("onCreate, device is background download failed");
            x();
            jkj.d().d(this.ah, 3);
            b(jkj.d().b());
        } else {
            q();
            IntentFilter intentFilter2 = new IntentFilter();
            intentFilter2.addAction("action_app_check_new_version_state");
            Context context = this.h;
            if (context != null) {
                BroadcastManagerUtil.bFC_(context, this.b, intentFilter2, LocalBroadcast.c, null);
            }
        }
        i();
    }

    private void i() {
        if (ac()) {
            jqi.a().getSwitchSetting("auto_update_status", this.am, new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.update.UpdateVersionActivity.15
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    ReleaseLogUtil.e("DEVMGR_UpdateVersionActivity", "getSwitchStatus errorCode = ", Integer.valueOf(i));
                    if (i == 0 && (obj instanceof String)) {
                        if (TextUtils.equals("true", String.valueOf(obj))) {
                            UpdateVersionActivity.this.y = true;
                        } else {
                            UpdateVersionActivity.this.y = false;
                        }
                    }
                    ReleaseLogUtil.e("DEVMGR_UpdateVersionActivity", "mIsSupportAutoUpdate:", Boolean.valueOf(UpdateVersionActivity.this.y));
                }
            });
        }
    }

    private void q() {
        LogUtil.a("UpdateVersionActivity", "enter initUpdate()");
        jkj.d().d(this.ah, 0);
        z();
        aa();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        LogUtil.a("UpdateVersionActivity", "onResume()");
        kxz.a(this.h, true);
        super.onResume();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        LogUtil.a("UpdateVersionActivity", "onPause()");
        kxz.a(this.h, false);
        super.onPause();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        LogUtil.a("UpdateVersionActivity", "onBackPressed()");
        if (this.z) {
            ar();
            return;
        }
        if (this.ab) {
            p();
            return;
        }
        if (this.au != null) {
            int k = k();
            ReleaseLogUtil.e("DEVMGR_UpdateVersionActivity", "onBackPressed() status = ", Integer.valueOf(k));
            if (k == 0) {
                super.onBackPressed();
            } else if (k == 3) {
                n();
            } else {
                super.onBackPressed();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int k() {
        return jkj.d().c(this.ah);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        CommonUtil.a(this.h);
        kxz.a(this.h, false);
        super.onDestroy();
        this.x.removeCallbacksAndMessages(null);
        ax();
        this.e = null;
        av();
        this.b = null;
        f();
        if (this.au != null && k() != 3 && k() != 4 && k() != 12 && k() != 6) {
            jkj.d().d(this.ah, 0);
            LogUtil.a("UpdateVersionActivity", "onDestroy updateInteractor release");
        }
        this.h = null;
        LogUtil.a("UpdateVersionActivity", "onDestroy()");
    }

    private void f() {
        CustomTextAlertDialog customTextAlertDialog = this.v;
        if (customTextAlertDialog != null) {
            customTextAlertDialog.dismiss();
            this.v = null;
        }
        CustomTextAlertDialog customTextAlertDialog2 = this.bc;
        if (customTextAlertDialog2 != null) {
            customTextAlertDialog2.dismiss();
            this.bc = null;
        }
        CustomTextAlertDialog customTextAlertDialog3 = this.aj;
        if (customTextAlertDialog3 != null) {
            customTextAlertDialog3.dismiss();
            this.aj = null;
        }
    }

    private void t() {
        LogUtil.a("UpdateVersionActivity", "Enter initView!");
        this.i = (CustomTitleBar) nsy.cMc_(this, R.id.update_title);
        this.ao = (RelativeLayout) nsy.cMc_(this, R.id.update_error_layout);
        this.ar = (HealthTextView) nsy.cMc_(this, R.id.update_error_text);
        this.n = (RelativeLayout) nsy.cMc_(this, R.id.detection_circle);
        this.m = (ImageView) nsy.cMc_(this, R.id.detection_check_logo);
        this.l = (HealthProgressIndicator) nsy.cMc_(this, R.id.detection_ota_animation);
        this.k = (RelativeLayout) nsy.cMc_(this, R.id.detection_end);
        this.o = (ImageView) nsy.cMc_(this, R.id.detection_end_check_logo);
        this.s = (RelativeLayout) nsy.cMc_(this, R.id.download_circle);
        this.ak = (LinearLayout) nsy.cMc_(this, R.id.progress_circle_download);
        this.p = (HealthProgressIndicator) nsy.cMc_(this, R.id.download_ota_animation);
        this.ag = (HealthTextView) nsy.cMc_(this, R.id.text_percent);
        this.al = (HealthTextView) nsy.cMc_(this, R.id.text_per_sign);
        this.u = (ImageView) nsy.cMc_(this, R.id.image_check_logo);
        this.al.setText(String.valueOf(new DecimalFormatSymbols(Locale.getDefault()).getPercent()));
        this.bb = (LinearLayout) nsy.cMc_(this, R.id.version_text_layout);
        this.ba = (HealthTextView) nsy.cMc_(this, R.id.version_text);
        this.ax = (HealthTextView) nsy.cMc_(this, R.id.version_download);
        this.aw = (HealthTextView) nsy.cMc_(this, R.id.version_size_text);
        this.af = (HealthCardView) nsy.cMc_(this, R.id.new_version_layout);
        this.ae = (ImageView) nsy.cMc_(this, R.id.new_settings_switch);
        this.ai = (HealthTextView) nsy.cMc_(this, R.id.new_version_name);
        this.j = (HealthCardView) nsy.cMc_(this, R.id.current_version_layout);
        this.g = (ImageView) nsy.cMc_(this, R.id.current_settings_switch);
        this.f = (HealthTextView) nsy.cMc_(this, R.id.current_version_name);
        this.at = (HealthCardView) nsy.cMc_(this, R.id.update_log_layout);
        this.av = (HealthTextView) nsy.cMc_(this, R.id.update_log_content);
        this.ap = (LinearLayout) nsy.cMc_(this, R.id.update_button_layout);
        this.as = (HealthButton) nsy.cMc_(this, R.id.update_button);
        this.af.setOnClickListener(this);
        this.j.setOnClickListener(this);
        this.as.setOnClickListener(this);
        if (LanguageUtil.ac(this.h)) {
            this.ak.setLayoutDirection(0);
        }
        s();
    }

    private void s() {
        Context context = this.h;
        this.i.setTitleText(context != null ? context.getResources().getString(R.string.IDS_device_wear_home_device_ota_upgrade) : "");
        this.i.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.update.UpdateVersionActivity.19
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                UpdateVersionActivity.this.onBackPressed();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.q = ap();
    }

    private void ag() {
        if (this.c) {
            return;
        }
        if (b(this.q) || ac() || nyi.d(this.t)) {
            LogUtil.a("UpdateVersionActivity", "show setting");
            this.i.setRightButtonVisibility(0);
            this.i.setRightButtonDrawable(BaseApplication.getContext().getResources().getDrawable(R.drawable.device_update_setting), nsf.h(R.string._2130841425_res_0x7f020f51));
            this.i.setRightButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.update.UpdateVersionActivity.17
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (nsn.o()) {
                        LogUtil.h("UpdateVersionActivity", "onClick setting isFastClick");
                        ViewClickInstrumentation.clickOnView(view);
                        return;
                    }
                    LogUtil.a("UpdateVersionActivity", "onClick enter setting");
                    Intent intent = new Intent();
                    intent.setClass(UpdateVersionActivity.this.h, UpdateSettingActivity.class);
                    UpdateVersionActivity updateVersionActivity = UpdateVersionActivity.this;
                    intent.putExtra("device_support_wlan", updateVersionActivity.b(updateVersionActivity.q));
                    intent.putExtra("device_support_auto", UpdateVersionActivity.this.ac());
                    intent.putExtra("device_support_wlan_transmit", nyi.d(UpdateVersionActivity.this.t));
                    intent.putExtra("device_unique", UpdateVersionActivity.this.am);
                    intent.putExtra("device_id", UpdateVersionActivity.this.ah);
                    UpdateVersionActivity.this.startActivityForResult(intent, 101);
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
    }

    private int ap() {
        int i;
        DeviceInfo e = jkj.d().e(this.ah);
        if (e != null) {
            this.au.c(e.getProductType());
            LogUtil.a("UpdateVersionActivity", "Enter showDeviceType() getProductType() productType ", Integer.valueOf(e.getProductType()));
            i = e.getProductType();
        } else {
            LogUtil.a("UpdateVersionActivity", "Enter showDeviceType() deviceInfo = null ");
            i = -1;
        }
        cuw c = jfu.c(i);
        if (jfu.m(i)) {
            String j = jfu.j(i);
            LogUtil.a("UpdateVersionActivity", "is plugin download uuid:", j);
            boolean isResourcesAvailable = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).isResourcesAvailable(j);
            LogUtil.a("UpdateVersionActivity", "is plugin download pluginAvaiable:", Boolean.valueOf(isResourcesAvailable));
            if (isResourcesAvailable) {
                e(j);
            } else {
                ae();
            }
        } else if (c.w() != 0) {
            this.m.setImageDrawable(BaseApplication.getContext().getResources().getDrawable(c.w()));
            this.o.setImageDrawable(BaseApplication.getContext().getResources().getDrawable(c.w()));
        }
        return i;
    }

    private void e(String str) {
        cvc pluginInfoByUuid = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getPluginInfoByUuid(str);
        if (pluginInfoByUuid != null) {
            String a2 = cwf.a(pluginInfoByUuid, 3, jpt.b(this.ah, "UpdateVersionActivity"));
            LogUtil.a("UpdateVersionActivity", "is plugin download image:", a2);
            if (a2 == null) {
                ae();
                return;
            } else {
                this.m.setImageBitmap(((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).loadImageByImageName(pluginInfoByUuid, a2));
                this.o.setImageBitmap(((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).loadImageByImageName(pluginInfoByUuid, a2));
                return;
            }
        }
        ae();
    }

    private void ae() {
        oaf oafVar = this.au;
        if (oafVar != null) {
            if (jfu.h(oafVar.d())) {
                this.m.setImageResource(R.mipmap._2131820663_res_0x7f110077);
                this.o.setImageResource(R.mipmap._2131820663_res_0x7f110077);
            } else {
                this.m.setImageResource(R.mipmap._2131820673_res_0x7f110081);
                this.o.setImageResource(R.mipmap._2131820673_res_0x7f110081);
            }
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (!this.w) {
            LogUtil.h("UpdateVersionActivity", "onclick device disconnected");
            nrh.b(this.h, R.string.IDS_hw_health_wear_connect_device_disconnect);
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (id == R.id.update_button) {
            oau.c(2);
            bc();
        } else if (id == R.id.new_version_layout) {
            a();
        } else if (id == R.id.current_version_layout) {
            aj();
        } else {
            LogUtil.h("UpdateVersionActivity", "onclick, default");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void bc() {
        if (this.ac) {
            LogUtil.a("UpdateVersionActivity", "onclick mIsCurrentDeviceAw70");
            bb();
            return;
        }
        int k = k();
        ReleaseLogUtil.e("DEVMGR_UpdateVersionActivity", "updateDeviceVersion otaStatus is: ", Integer.valueOf(k));
        if (k == 0) {
            LogUtil.a("UpdateVersionActivity", "HwOtaConstants.STATUS_INITIAL ");
            z();
        }
        boolean z = this.z;
        if (z && k == 2) {
            ad();
            return;
        }
        if (z && k == 3) {
            LogUtil.a("UpdateVersionActivity", "onCreate, device is background downloading");
            x();
            a(jkk.d().i(this.ah));
        }
        if (k == 4 || k == 5 || k == 6) {
            LogUtil.a("UpdateVersionActivity", "onclick, device is background transfer or failed");
            nyi.e(this.h, false, this.ah);
            finish();
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("action_app_check_new_version_state");
        Context context = this.h;
        if (context == null) {
            ReleaseLogUtil.d("DEVMGR_UpdateVersionActivity", "updateDeviceVersion mContext is null");
        } else {
            BroadcastManagerUtil.bFC_(context, this.b, intentFilter, LocalBroadcast.c, null);
        }
    }

    private void ad() {
        LogUtil.a("UpdateVersionActivity", "processDownloadLogic HwOtaConstants.STATUS_PULL_CHANGE_LOG");
        if (kxz.s(BaseApplication.getContext(), this.ah)) {
            nyi.e(this, this.t, true);
            return;
        }
        if (this.c) {
            au();
        } else if (this.aa) {
            nyi.e(this.h, true, this.ah);
            finish();
        } else {
            e();
        }
    }

    private void au() {
        String j = HwVersionManager.c(BaseApplication.getContext()).j(this.ah);
        jko jkoVar = new jko();
        jkoVar.a(this.ah);
        jkoVar.e(j);
        jkoVar.c(CommonUtil.g(kxz.d(BaseApplication.getContext(), this.ah, j)));
        jkoVar.a(System.currentTimeMillis() / 1000);
        jkoVar.b(1);
        jkoVar.a(0);
        ReleaseLogUtil.e("DEVMGR_UpdateVersionActivity", "updateDealSupportDetect sendUpdateMessage");
        jkv.b().a(jkoVar);
        this.as.setClickable(false);
        this.as.setBackground(getResources().getDrawable(R.drawable.button_background_emphasize_disable));
        this.as.setText(R.string._2130844841_res_0x7f021ca9);
    }

    private void bb() {
        boolean z;
        DeviceCapability e;
        String b2 = jpp.b();
        if (!TextUtils.isEmpty(b2)) {
            long d2 = jhf.d().d(b2);
            LogUtil.a("UpdateVersionActivity", "onclick lastSyncTime is ", Long.valueOf(d2));
            if (System.currentTimeMillis() - d2 > 600000) {
                LogUtil.a("UpdateVersionActivity", "onclick isNeedSync ");
                z = true;
                String j = kxz.j(this.h, this.ah);
                e = cvs.e(this.ah);
                if (e == null && e.isNeedSyncBeforeOta() && z && !TextUtils.isEmpty(j)) {
                    jkv.b().d(this.ah, new d(this));
                    return;
                } else {
                    at();
                }
            }
        }
        z = false;
        String j2 = kxz.j(this.h, this.ah);
        e = cvs.e(this.ah);
        if (e == null) {
        }
        at();
    }

    private void aj() {
        this.z = false;
        this.ab = true;
        this.i.setTitleText(this.h.getString(R.string._2130841459_res_0x7f020f73));
        this.i.setRightButtonVisibility(8);
        this.ao.setVisibility(8);
        this.n.setVisibility(8);
        this.k.setVisibility(0);
        this.s.setVisibility(8);
        this.bb.setVisibility(0);
        this.af.setVisibility(8);
        this.j.setVisibility(8);
        this.at.setVisibility(0);
        this.ap.setVisibility(8);
        this.aw.setVisibility(8);
        this.ax.setVisibility(8);
        aq();
        this.l.setWaitingAnimationEnabled(false);
        this.p.setWaitingAnimationEnabled(false);
    }

    private void aq() {
        if (this.t == null) {
            this.av.setText(R.string._2130842256_res_0x7f021290);
        } else {
            af();
        }
    }

    private void af() {
        if (LanguageUtil.bc(this.h)) {
            this.ba.setGravity(5);
            this.ba.setTextDirection(3);
        } else {
            this.ba.setGravity(3);
            this.ba.setTextDirection(5);
        }
        if (!TextUtils.isEmpty(this.aq) && this.aq.length() > 2) {
            this.ba.setText(this.aq);
        } else {
            this.ba.setText(this.d);
        }
        String e = kxz.e(BaseApplication.getContext(), this.t.getSoftVersion(), this.t.getDeviceIdentify());
        String b2 = kxz.b(BaseApplication.getContext(), this.t.getSoftVersion(), this.t.getDeviceIdentify());
        if (!TextUtils.isEmpty(e)) {
            this.aw.setVisibility(0);
        }
        d(e);
        if (TextUtils.isEmpty(b2)) {
            this.av.setText(R.string._2130842256_res_0x7f021290);
        } else {
            this.av.setText(b2);
        }
    }

    static class d implements IBaseResponseCallback {
        WeakReference<UpdateVersionActivity> b;

        d(UpdateVersionActivity updateVersionActivity) {
            this.b = new WeakReference<>(updateVersionActivity);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            UpdateVersionActivity updateVersionActivity = this.b.get();
            if (updateVersionActivity == null) {
                LogUtil.h("UpdateVersionActivity", "fragment is null");
                return;
            }
            String d = cvx.d((byte[]) obj);
            LogUtil.a("UpdateVersionActivity", "getSwitch berfore onResponse objData = ", d);
            if (TextUtils.isEmpty(d)) {
                return;
            }
            int c = d.length() >= 2 ? jds.c(d.substring(d.length() - 2), 10) : 0;
            LogUtil.a("UpdateVersionActivity", "onResponse errorcode = ", Integer.valueOf(i), ", and value =", Integer.valueOf(c));
            if (c == 1) {
                updateVersionActivity.as();
            } else {
                updateVersionActivity.at();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void at() {
        runOnUiThread(new Runnable() { // from class: com.huawei.ui.device.activity.update.UpdateVersionActivity.20
            @Override // java.lang.Runnable
            public void run() {
                if (!UpdateVersionActivity.this.z && UpdateVersionActivity.this.k() == 0) {
                    ReleaseLogUtil.e("DEVMGR_UpdateVersionActivity", "HwOtaConstants.STATUS_INITIAL ");
                    IntentFilter intentFilter = new IntentFilter();
                    intentFilter.addAction("action_app_check_new_version_state");
                    BroadcastManagerUtil.bFC_(UpdateVersionActivity.this.h, UpdateVersionActivity.this.b, intentFilter, LocalBroadcast.c, null);
                    UpdateVersionActivity.this.z();
                }
                if (UpdateVersionActivity.this.z && UpdateVersionActivity.this.k() == 2) {
                    ReleaseLogUtil.e("DEVMGR_UpdateVersionActivity", "HwOtaConstants.STATUS_PULL_CHANGE_LOG");
                    UpdateVersionActivity.this.e();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void as() {
        runOnUiThread(new Runnable() { // from class: com.huawei.ui.device.activity.update.UpdateVersionActivity.18
            @Override // java.lang.Runnable
            public void run() {
                if (UpdateVersionActivity.this.h != null) {
                    LogUtil.a("UpdateVersionActivity", "showNeedSyncDialog enter");
                    new CustomAlertDialog.Builder(UpdateVersionActivity.this.h).cyp_(View.inflate(UpdateVersionActivity.this.h, R.layout.dialog_sync_before_ota, null)).cyo_(R.string._2130842786_res_0x7f0214a2, new DialogInterface.OnClickListener() { // from class: com.huawei.ui.device.activity.update.UpdateVersionActivity.18.4
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialogInterface, int i) {
                            LogUtil.a("UpdateVersionActivity", "start mNeedSyncDialog, user click Positive button! ");
                            Intent intent = new Intent();
                            intent.setPackage(UpdateVersionActivity.this.h.getPackageName());
                            intent.setClassName(UpdateVersionActivity.this.h.getPackageName(), "com.huawei.health.MainActivity");
                            intent.setFlags(AppRouterExtras.COLDSTART);
                            intent.putExtra(Constants.HOME_TAB_NAME, Constants.HOME);
                            UpdateVersionActivity.this.startActivity(intent);
                            ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
                        }
                    }).cyn_(R.string._2130842782_res_0x7f02149e, new DialogInterface.OnClickListener() { // from class: com.huawei.ui.device.activity.update.UpdateVersionActivity.18.3
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ReleaseLogUtil.e("DEVMGR_UpdateVersionActivity", "start mNeedSyncDialog, user click Negative button!");
                            UpdateVersionActivity.this.at();
                            ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
                        }
                    }).c().show();
                }
            }
        });
    }

    /* renamed from: com.huawei.ui.device.activity.update.UpdateVersionActivity$4, reason: invalid class name */
    class AnonymousClass4 implements Runnable {
        AnonymousClass4() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (!UpdateVersionActivity.this.c) {
                UpdateVersionActivity.this.g();
            } else {
                joh.a().a(new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.update.UpdateVersionActivity.4.4
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public void d(int i, Object obj) {
                        int intValue = (i == 0 && (obj instanceof Integer)) ? ((Integer) obj).intValue() : -1;
                        ReleaseLogUtil.e("DEVMGR_UpdateVersionActivity", "getLockscreenStatus lockScreenStatus", Integer.valueOf(intValue));
                        if (intValue != 3) {
                            UpdateVersionActivity.this.g();
                        } else {
                            UpdateVersionActivity.this.runOnUiThread(new Runnable() { // from class: com.huawei.ui.device.activity.update.UpdateVersionActivity.4.4.2
                                @Override // java.lang.Runnable
                                public void run() {
                                    UpdateVersionActivity.this.a(UpdateVersionActivity.this.h.getString(R.string._2130845012_res_0x7f021d54));
                                }
                            });
                        }
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void z() {
        u();
        this.x.postDelayed(new AnonymousClass4(), 2000L);
        HashMap hashMap = new HashMap(20);
        hashMap.put("state", "device");
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HOME_1010032.value(), hashMap, 0);
        jkk.d().a(this.ah, 25);
    }

    private void u() {
        LogUtil.a("UpdateVersionActivity", "Enter initViewForCheck!");
        this.z = false;
        this.ab = false;
        Context context = this.h;
        if (context == null) {
            LogUtil.a("UpdateVersionActivity", "mContext is null, initViewForCheck failed");
            return;
        }
        this.i.setTitleText(context.getResources().getString(R.string.IDS_device_wear_home_device_ota_upgrade));
        ag();
        this.ao.setVisibility(8);
        this.n.setVisibility(0);
        this.bb.setVisibility(0);
        this.k.setVisibility(8);
        this.s.setVisibility(8);
        this.j.setVisibility(0);
        if (LanguageUtil.bc(this.h)) {
            this.ae.setBackground(BaseApplication.getContext().getResources().getDrawable(R.drawable._2131427841_res_0x7f0b0201));
            this.g.setBackground(BaseApplication.getContext().getResources().getDrawable(R.drawable._2131427841_res_0x7f0b0201));
        } else {
            this.ae.setBackground(BaseApplication.getContext().getResources().getDrawable(R.drawable._2131427842_res_0x7f0b0202));
            this.g.setBackground(BaseApplication.getContext().getResources().getDrawable(R.drawable._2131427842_res_0x7f0b0202));
        }
        this.j.setClickable(false);
        this.af.setClickable(false);
        this.at.setVisibility(8);
        this.ap.setVisibility(0);
        w();
    }

    private void w() {
        if (this.t == null) {
            LogUtil.h("UpdateVersionActivity", "onCreate deviceInfo == null");
        }
        HwVersionManager c = HwVersionManager.c(BaseApplication.getContext());
        String j = c.j(this.ah);
        String c2 = c.c(this.ah);
        if (LanguageUtil.bc(this.h)) {
            this.ai.setGravity(5);
            this.f.setGravity(5);
            this.ai.setTextDirection(3);
            this.f.setTextDirection(3);
        } else {
            this.ai.setGravity(3);
            this.f.setGravity(3);
            this.ai.setTextDirection(5);
            this.f.setTextDirection(5);
        }
        if (!TextUtils.isEmpty(j) && !j.equals(this.d)) {
            this.af.setVisibility(0);
            if (!TextUtils.isEmpty(c2)) {
                this.ai.setText(c2);
            } else {
                this.ai.setText(j);
            }
        } else {
            this.af.setVisibility(8);
        }
        LogUtil.a("UpdateVersionActivity", "initViewForCheckView mBandVersion :", this.d, " mDeviceOStVersion ", this.r, " mSplicingProductVersion ", this.aq, " checkNewBandVersion ", j, " bandOsNewVersion ", c2);
        if (TextUtils.isEmpty(this.r)) {
            if (!TextUtils.isEmpty(this.aq) && this.aq.length() > 2) {
                this.f.setText(this.aq);
            } else {
                this.f.setText(this.d);
            }
        } else {
            this.f.setText(this.r);
        }
        ap();
        this.l.setWaitingAnimationEnabled(true);
        this.p.setWaitingAnimationEnabled(false);
        this.ba.setText(R.string._2130844881_res_0x7f021cd1);
        this.aw.setVisibility(8);
        this.ax.setVisibility(8);
        this.as.setClickable(false);
        this.as.setBackground(getResources().getDrawable(R.drawable.button_background_emphasize_disable));
        this.as.setText(R.string._2130841456_res_0x7f020f70);
    }

    private void p() {
        ReleaseLogUtil.e("DEVMGR_UpdateVersionActivity", "Enter initViewForCheck!");
        this.z = false;
        this.ab = false;
        this.i.setTitleText(this.h.getResources().getString(R.string.IDS_device_wear_home_device_ota_upgrade));
        ag();
        this.ao.setVisibility(8);
        this.n.setVisibility(8);
        this.bb.setVisibility(0);
        this.k.setVisibility(0);
        this.s.setVisibility(8);
        this.j.setVisibility(0);
        if (LanguageUtil.bc(this.h)) {
            this.ae.setBackground(getResources().getDrawable(R.drawable._2131427841_res_0x7f0b0201));
            this.g.setBackground(getResources().getDrawable(R.drawable._2131427841_res_0x7f0b0201));
        } else {
            this.ae.setBackground(getResources().getDrawable(R.drawable._2131427842_res_0x7f0b0202));
            this.g.setBackground(getResources().getDrawable(R.drawable._2131427842_res_0x7f0b0202));
        }
        this.af.setClickable(true);
        this.j.setClickable(true);
        this.at.setVisibility(8);
        this.ap.setVisibility(0);
        y();
    }

    private void y() {
        HwVersionManager c = HwVersionManager.c(BaseApplication.getContext());
        String j = c.j(this.ah);
        String c2 = c.c(this.ah);
        if (LanguageUtil.bc(this.h)) {
            this.ai.setGravity(5);
            this.f.setGravity(5);
            this.ai.setTextDirection(3);
            this.f.setTextDirection(3);
        } else {
            this.ai.setGravity(3);
            this.f.setGravity(3);
            this.ai.setTextDirection(5);
            this.f.setTextDirection(5);
        }
        if (!TextUtils.isEmpty(j) && !j.equals(this.d)) {
            this.af.setVisibility(0);
            this.ba.setText(R.string._2130841460_res_0x7f020f74);
            if (!TextUtils.isEmpty(c2)) {
                this.ai.setText(c2);
            } else {
                this.ai.setText(j);
            }
        } else {
            this.ba.setText(R.string._2130841484_res_0x7f020f8c);
            this.af.setVisibility(8);
        }
        LogUtil.a("UpdateVersionActivity", "initViewCurrentForCheckView mBandVersion :", this.d, " mDeviceOStVersion ", this.r, " mSplicingProductVersion ", this.aq, " checkNewBandVersion ", j, " bandOsNewVersion ", c2);
        if (TextUtils.isEmpty(this.r)) {
            if (!TextUtils.isEmpty(this.aq) && this.aq.length() > 2) {
                this.f.setText(this.aq);
            } else {
                this.f.setText(this.d);
            }
        } else {
            this.f.setText(this.r);
        }
        this.l.setWaitingAnimationEnabled(false);
        this.p.setWaitingAnimationEnabled(false);
        this.aw.setVisibility(8);
        this.ax.setVisibility(8);
        this.as.setClickable(true);
        this.as.setBackground(getResources().getDrawable(R.drawable.button_background_emphasize));
        this.as.setText(R.string._2130841456_res_0x7f020f70);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void v() {
        ReleaseLogUtil.e("DEVMGR_UpdateVersionActivity", "Enter initViewForNoVersion! ");
        this.z = false;
        this.ab = false;
        this.i.setTitleText(this.h.getResources().getString(R.string.IDS_device_wear_home_device_ota_upgrade));
        ag();
        this.ao.setVisibility(8);
        this.n.setVisibility(8);
        this.k.setVisibility(0);
        this.bb.setVisibility(0);
        this.s.setVisibility(8);
        this.af.setVisibility(8);
        this.j.setVisibility(0);
        if (LanguageUtil.bc(this.h)) {
            this.ae.setBackground(BaseApplication.getContext().getResources().getDrawable(R.drawable._2131427841_res_0x7f0b0201));
            this.g.setBackground(BaseApplication.getContext().getResources().getDrawable(R.drawable._2131427841_res_0x7f0b0201));
        } else {
            this.ae.setBackground(BaseApplication.getContext().getResources().getDrawable(R.drawable._2131427842_res_0x7f0b0202));
            this.g.setBackground(BaseApplication.getContext().getResources().getDrawable(R.drawable._2131427842_res_0x7f0b0202));
        }
        this.j.setClickable(true);
        this.at.setVisibility(8);
        this.ap.setVisibility(0);
        this.aw.setVisibility(8);
        this.ax.setVisibility(8);
        this.as.setClickable(true);
        this.as.setBackground(BaseApplication.getContext().getResources().getDrawable(R.drawable.button_background_emphasize));
        this.as.setText(R.string._2130841456_res_0x7f020f70);
        this.ba.setText(R.string._2130841484_res_0x7f020f8c);
        this.l.setWaitingAnimationEnabled(false);
        this.p.setWaitingAnimationEnabled(false);
        jkj.d().d(this.ah, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        ReleaseLogUtil.e("DEVMGR_UpdateVersionActivity", "Enter showCheckFailedLayout!");
        this.z = false;
        this.ab = false;
        this.ao.setVisibility(0);
        this.n.setVisibility(8);
        this.k.setVisibility(0);
        this.s.setVisibility(8);
        this.bb.setVisibility(0);
        this.af.setVisibility(8);
        this.aw.setVisibility(8);
        this.j.setVisibility(0);
        if (LanguageUtil.bc(this.h)) {
            this.ae.setBackground(getResources().getDrawable(R.drawable._2131427841_res_0x7f0b0201));
            this.g.setBackground(getResources().getDrawable(R.drawable._2131427841_res_0x7f0b0201));
        } else {
            this.ae.setBackground(getResources().getDrawable(R.drawable._2131427842_res_0x7f0b0202));
            this.g.setBackground(getResources().getDrawable(R.drawable._2131427842_res_0x7f0b0202));
        }
        this.j.setClickable(true);
        this.at.setVisibility(8);
        this.ap.setVisibility(0);
        this.as.setClickable(true);
        this.as.setBackground(getResources().getDrawable(R.drawable.button_background_emphasize));
        this.ba.setText(R.string._2130841829_res_0x7f0210e5);
        this.as.setText(R.string._2130841456_res_0x7f020f70);
        this.ar.setText(str);
        this.l.setWaitingAnimationEnabled(false);
        this.p.setWaitingAnimationEnabled(false);
        jkj.d().d(this.ah, 0);
    }

    private void ar() {
        ReleaseLogUtil.e("DEVMGR_UpdateVersionActivity", "Enter showDetectionNewVersion");
        this.z = false;
        this.ab = false;
        jkj.d().d(this.ah, 0);
        this.i.setTitleText(this.h.getResources().getString(R.string.IDS_device_wear_home_device_ota_upgrade));
        ag();
        this.ao.setVisibility(8);
        this.n.setVisibility(8);
        this.k.setVisibility(0);
        this.s.setVisibility(8);
        this.bb.setVisibility(0);
        this.af.setVisibility(0);
        this.j.setVisibility(0);
        if (LanguageUtil.bc(this.h)) {
            this.ae.setBackground(getResources().getDrawable(R.drawable._2131427841_res_0x7f0b0201));
            this.g.setBackground(getResources().getDrawable(R.drawable._2131427841_res_0x7f0b0201));
        } else {
            this.ae.setBackground(getResources().getDrawable(R.drawable._2131427842_res_0x7f0b0202));
            this.g.setBackground(getResources().getDrawable(R.drawable._2131427842_res_0x7f0b0202));
        }
        this.af.setClickable(true);
        this.j.setClickable(true);
        this.at.setVisibility(8);
        this.ap.setVisibility(0);
        this.aw.setVisibility(8);
        this.ax.setVisibility(8);
        this.ba.setText(R.string._2130841460_res_0x7f020f74);
        this.as.setClickable(true);
        this.as.setBackground(getResources().getDrawable(R.drawable.button_background_emphasize));
        this.as.setText(R.string._2130841456_res_0x7f020f70);
        ai();
        this.l.setWaitingAnimationEnabled(false);
        this.p.setWaitingAnimationEnabled(false);
    }

    private void ai() {
        if (LanguageUtil.bc(this.h)) {
            this.ai.setGravity(5);
            this.f.setGravity(5);
            this.ai.setTextDirection(3);
            this.f.setTextDirection(3);
        } else {
            this.ai.setGravity(3);
            this.f.setGravity(3);
            this.ai.setTextDirection(5);
            this.f.setTextDirection(5);
        }
        HwVersionManager c = HwVersionManager.c(BaseApplication.getContext());
        String n = kxz.n(BaseApplication.getContext(), this.ah);
        if (!TextUtils.isEmpty(n)) {
            this.ai.setText(n);
        } else {
            this.ai.setText(c.j(this.ah));
        }
        if (TextUtils.isEmpty(this.r)) {
            if (!TextUtils.isEmpty(this.aq) && this.aq.length() > 2) {
                this.f.setText(this.aq);
                return;
            } else {
                this.f.setText(this.d);
                return;
            }
        }
        this.f.setText(this.r);
    }

    public void a() {
        ReleaseLogUtil.e("DEVMGR_UpdateVersionActivity", "Enter showAppNewVersion");
        if (this.h == null) {
            ReleaseLogUtil.d("DEVMGR_UpdateVersionActivity", "showAppNewVersion mContext == null");
            return;
        }
        if (kxz.s(BaseApplication.getContext(), this.ah)) {
            nyi.e(this, this.t, false);
        }
        this.z = true;
        this.ab = false;
        jkj.d().d(this.ah, 2);
        this.i.setTitleText(this.h.getString(R.string._2130841458_res_0x7f020f72));
        this.i.setRightButtonVisibility(8);
        this.ao.setVisibility(8);
        this.n.setVisibility(8);
        this.k.setVisibility(0);
        this.s.setVisibility(8);
        this.bb.setVisibility(0);
        this.af.setVisibility(8);
        this.j.setVisibility(8);
        this.at.setVisibility(0);
        this.ap.setVisibility(0);
        this.aw.setVisibility(8);
        this.ax.setVisibility(8);
        this.as.setClickable(true);
        this.as.setBackground(getResources().getDrawable(R.drawable.button_background_emphasize));
        if (this.c) {
            this.as.setText(R.string._2130844842_res_0x7f021caa);
        } else {
            this.as.setText(R.string._2130844887_res_0x7f021cd7);
        }
        am();
    }

    private void am() {
        HwVersionManager c = HwVersionManager.c(BaseApplication.getContext());
        if (LanguageUtil.bc(this.h)) {
            this.ba.setGravity(5);
            this.ba.setTextDirection(3);
        } else {
            this.ba.setGravity(3);
            this.ba.setTextDirection(5);
        }
        this.ba.setText(c.j(this.ah));
        if (this.t == null) {
            this.av.setText(R.string._2130842256_res_0x7f021290);
        } else {
            String e = kxz.e(BaseApplication.getContext(), c.j(this.ah), this.t.getDeviceIdentify());
            if (this.c) {
                e = kxz.c(BaseApplication.getContext(), c.j(this.ah), this.ah);
            }
            if (!TextUtils.isEmpty(e)) {
                this.aw.setVisibility(0);
            }
            d(e);
            String b2 = kxz.b(BaseApplication.getContext(), c.j(this.ah), this.t.getDeviceIdentify());
            if (this.c) {
                b2 = kxz.a(BaseApplication.getContext(), c.j(this.ah), this.ah);
            }
            if (TextUtils.isEmpty(b2)) {
                this.av.setText(R.string._2130842256_res_0x7f021290);
            } else {
                this.av.setText(b2);
            }
        }
        this.l.setWaitingAnimationEnabled(false);
        this.p.setWaitingAnimationEnabled(false);
    }

    private void d(String str) {
        if (LanguageUtil.ac(this.h)) {
            str = com.huawei.openalliance.ad.constant.Constants.LRM_STR + str;
        }
        this.aw.setText(this.h.getString(R.string._2130844010_res_0x7f02196a, str));
    }

    private void x() {
        ReleaseLogUtil.e("DEVMGR_UpdateVersionActivity", "Enter initViewForDownload!");
        this.z = false;
        this.ab = false;
        this.ao.setVisibility(8);
        this.n.setVisibility(8);
        this.k.setVisibility(8);
        this.s.setVisibility(0);
        this.u.setVisibility(8);
        this.ak.setVisibility(0);
        this.bb.setVisibility(0);
        this.af.setVisibility(8);
        this.j.setVisibility(8);
        this.at.setVisibility(0);
        this.ax.setVisibility(0);
        this.aw.setVisibility(8);
        this.ap.setVisibility(8);
        this.l.setWaitingAnimationEnabled(false);
        this.p.setWaitingAnimationEnabled(false);
        a(0);
        this.ax.setText(R.string._2130841544_res_0x7f020fc8);
        HwVersionManager c = HwVersionManager.c(BaseApplication.getContext());
        this.ba.setText(c.j(this.ah));
        if (this.t == null) {
            this.av.setText(R.string._2130842256_res_0x7f021290);
        } else {
            String e = kxz.e(BaseApplication.getContext(), c.j(this.ah), this.t.getDeviceIdentify());
            if (!TextUtils.isEmpty(e)) {
                this.aw.setVisibility(0);
            }
            String b2 = kxz.b(BaseApplication.getContext(), c.j(this.ah), this.t.getDeviceIdentify());
            d(e);
            if (TextUtils.isEmpty(b2)) {
                this.av.setText(R.string._2130842256_res_0x7f021290);
            } else {
                this.av.setText(b2);
            }
        }
        ReleaseLogUtil.e("DEVMGR_UpdateVersionActivity", "initViewForDownload() mUpdateStatus :", Integer.valueOf(k()));
    }

    private void a(int i) {
        if (this.an == i) {
            return;
        }
        this.an = i;
        this.ag.setText(UnitUtil.e(i, 1, 0));
        this.p.setProgress(i);
    }

    private void ah() {
        this.ax.setText(R.string.IDS_bundle_downloaded_prompt);
        a(100);
        this.as.setClickable(false);
        this.as.setBackground(getResources().getDrawable(R.drawable.button_background_emphasize_disable));
    }

    private void b(String str) {
        ReleaseLogUtil.e("DEVMGR_UpdateVersionActivity", "showErrorMsg(): tipText = ", str);
        this.z = true;
        this.ab = false;
        this.ao.setVisibility(0);
        this.n.setVisibility(8);
        this.k.setVisibility(8);
        this.s.setVisibility(0);
        this.ak.setVisibility(8);
        this.u.setVisibility(0);
        this.l.setWaitingAnimationEnabled(false);
        this.p.setWaitingAnimationEnabled(false);
        this.p.setProgress(0);
        this.ba.setVisibility(0);
        this.aw.setVisibility(0);
        this.ax.setVisibility(0);
        this.ap.setVisibility(0);
        this.as.setClickable(true);
        this.as.setBackground(getResources().getDrawable(R.drawable.button_background_emphasize));
        this.as.setText(this.h.getText(R.string._2130841467_res_0x7f020f7b));
        this.ar.setText(str);
        this.ax.setText(R.string._2130841543_res_0x7f020fc7);
        if (LanguageUtil.bc(this.h)) {
            this.ba.setGravity(5);
            this.ba.setTextDirection(3);
        } else {
            this.ba.setGravity(3);
            this.ba.setTextDirection(5);
        }
        this.ba.setText(HwVersionManager.c(this.h).j(this.ah));
        Context context = this.h;
        d(kxz.e(context, kxz.j(context, this.ah), this.ah));
        jkj.d().d(this.ah, 2);
        this.au.d(this.ah);
        ReleaseLogUtil.e("DEVMGR_UpdateVersionActivity", "showErrorMsg() mUpdateStatus :", Integer.valueOf(k()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str) {
        ReleaseLogUtil.e("DEVMGR_UpdateVersionActivity", "showErrorMsg(): tipText = ", str);
        this.au.d(this.ah);
        this.z = false;
        this.ab = false;
        this.ao.setVisibility(0);
        this.n.setVisibility(8);
        this.k.setVisibility(0);
        this.s.setVisibility(8);
        this.bb.setVisibility(8);
        this.af.setVisibility(8);
        this.at.setVisibility(8);
        this.j.setVisibility(0);
        if (LanguageUtil.bc(this.h)) {
            this.ai.setGravity(5);
            this.f.setGravity(5);
            this.ai.setTextDirection(3);
            this.f.setTextDirection(3);
            this.ae.setBackground(getResources().getDrawable(R.drawable._2131427841_res_0x7f0b0201));
            this.g.setBackground(getResources().getDrawable(R.drawable._2131427841_res_0x7f0b0201));
        } else {
            this.ae.setBackground(getResources().getDrawable(R.drawable._2131427842_res_0x7f0b0202));
            this.g.setBackground(getResources().getDrawable(R.drawable._2131427842_res_0x7f0b0202));
            this.ai.setTextDirection(5);
            this.f.setTextDirection(5);
            this.ai.setGravity(3);
            this.f.setGravity(3);
        }
        this.j.setClickable(true);
        this.ap.setVisibility(0);
        this.l.setWaitingAnimationEnabled(false);
        this.p.setWaitingAnimationEnabled(false);
        a(0);
        this.as.setClickable(true);
        this.as.setBackground(getResources().getDrawable(R.drawable.button_background_emphasize));
        this.as.setText(this.h.getText(R.string._2130841456_res_0x7f020f70));
        this.ar.setText(str);
        if (!TextUtils.isEmpty(this.aq) && this.aq.length() > 2) {
            this.f.setText(this.aq);
        } else {
            this.f.setText(this.d);
        }
        jkj.d().d(this.ah, 0);
        ReleaseLogUtil.e("DEVMGR_UpdateVersionActivity", "showErrorMsg() mUpdateStatus :", Integer.valueOf(k()));
        av();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        ReleaseLogUtil.e("DEVMGR_UpdateVersionActivity", "doCheckAppNewVersion: mUpdateInteractors.mUpdateStatus = ", k() + "mIsConnected:", Boolean.valueOf(this.w));
        if (k() == 0) {
            jkj.d().d(this.ah, 1);
        }
        if (this.w) {
            this.au.e(this.ah);
        } else {
            a(this.h.getString(R.string._2130841439_res_0x7f020f5f));
        }
    }

    public void e() {
        boolean b2 = jkj.d().b(kxz.f(BaseApplication.getContext(), kxz.j(this.h, this.ah), this.ah));
        ReleaseLogUtil.e("DEVMGR_UpdateVersionActivity", "handleAppNewVersionOk: checkMemory = ", Boolean.valueOf(b2));
        if (!b2) {
            b(this.h.getString(R.string._2130841547_res_0x7f020fcb));
            return;
        }
        boolean g = jrd.g();
        ReleaseLogUtil.e("DEVMGR_UpdateVersionActivity", "handleAppNewVersionOk: wifiConnected = ", Boolean.valueOf(g), " CommonUtil.isHuaweiSystem() ", Boolean.valueOf(CommonUtil.bh()));
        if (CommonUtil.bh()) {
            e(g);
        } else if (!g) {
            b();
        } else {
            j();
        }
    }

    private void e(boolean z) {
        if (!z) {
            b();
            return;
        }
        int c = jrd.c();
        ReleaseLogUtil.e("DEVMGR_UpdateVersionActivity", "checkIsEmuiWifiType: wifiType = ", Integer.valueOf(c));
        if (c == 2) {
            ak();
        } else {
            j();
        }
    }

    private void b() {
        if (oaf.b(BaseApplication.getContext()).a()) {
            an();
        } else {
            j();
        }
    }

    private void an() {
        if (this.h == null || this.bc != null) {
            return;
        }
        CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(this.h).b(R.string.IDS_service_area_notice_title).d(R.string._2130841457_res_0x7f020f71).cyU_(R.string._2130841549_res_0x7f020fcd, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.update.UpdateVersionActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.c("UpdateVersionActivity", "start wifiDialog, user click Negative button!");
                UpdateVersionActivity.this.bc.dismiss();
                UpdateVersionActivity.this.bc = null;
                UpdateVersionActivity.this.j();
                ViewClickInstrumentation.clickOnView(view);
            }
        }).cyR_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.update.UpdateVersionActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.c("UpdateVersionActivity", "start wifiDialog, user click Positive button!");
                UpdateVersionActivity.this.bc.dismiss();
                UpdateVersionActivity.this.bc = null;
                ViewClickInstrumentation.clickOnView(view);
            }
        }).a();
        this.bc = a2;
        a2.setCancelable(false);
        this.bc.show();
    }

    private void ak() {
        if (this.h == null || this.aj != null) {
            return;
        }
        CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(this.h).b(R.string.IDS_service_area_notice_title).d(R.string._2130844120_res_0x7f0219d8).cyU_(R.string._2130841549_res_0x7f020fcd, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.update.UpdateVersionActivity.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.c("UpdateVersionActivity", "showAppPersonalHotspotNetDialog, user click Negative button!");
                UpdateVersionActivity.this.aj.dismiss();
                UpdateVersionActivity.this.aj = null;
                UpdateVersionActivity.this.j();
                ViewClickInstrumentation.clickOnView(view);
            }
        }).cyR_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.update.UpdateVersionActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.c("UpdateVersionActivity", "showAppPersonalHotspotNetDialog, user click Positive button!");
                UpdateVersionActivity.this.aj.dismiss();
                UpdateVersionActivity.this.aj = null;
                ViewClickInstrumentation.clickOnView(view);
            }
        }).a();
        this.aj = a2;
        a2.setCancelable(false);
        this.aj.show();
    }

    private void ao() {
        if (this.h == null || this.v != null) {
            return;
        }
        CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(this.h).b(R.string.IDS_service_area_notice_title).d(R.string._2130841491_res_0x7f020f93).cyU_(R.string._2130841131_res_0x7f020e2b, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.update.UpdateVersionActivity.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.c("UpdateVersionActivity", "start showExitUpdateDialog, user click Negative button! ");
                UpdateVersionActivity.this.l();
                UpdateVersionActivity.this.v.dismiss();
                UpdateVersionActivity.this.v = null;
                UpdateVersionActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        }).cyR_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.update.UpdateVersionActivity.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.c("UpdateVersionActivity", "showExitUpdateDialog ok click");
                UpdateVersionActivity.this.v.dismiss();
                UpdateVersionActivity.this.v = null;
                ViewClickInstrumentation.clickOnView(view);
            }
        }).a();
        this.v = a2;
        a2.setCancelable(false);
        this.v.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        if (k() == 3) {
            this.au.d(this.ah);
            jkj.d().d(this.ah, 0);
            ReleaseLogUtil.e("DEVMGR_UpdateVersionActivity", "wear device,cancle downloading file!");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        ReleaseLogUtil.e("DEVMGR_UpdateVersionActivity", "doDownloadAppFile: mUpdateInteractors.mUpdateStatus = ", Integer.valueOf(k()));
        x();
        if (k() == 3) {
            ReleaseLogUtil.e("DEVMGR_UpdateVersionActivity", "doDownloadAppFile, device is background downloading");
            x();
            a(jkk.d().i(this.ah));
        } else if (k() == 4 || k() == 5) {
            ReleaseLogUtil.e("DEVMGR_UpdateVersionActivity", "doDownloadAppFile, device is background transfer or failed");
            nyi.e(this.h, false, this.ah);
            finish();
        } else {
            this.au.d(this.ah);
            this.au.a(this.ah);
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("action_app_check_new_version_state");
        Context context = this.h;
        if (context == null) {
            ReleaseLogUtil.d("DEVMGR_UpdateVersionActivity", "doDownloadAppFile mContext is null");
        } else {
            BroadcastManagerUtil.bFC_(context, this.b, intentFilter, LocalBroadcast.c, null);
        }
    }

    private void aa() {
        IntentFilter intentFilter = new IntentFilter("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
        BroadcastReceiver broadcastReceiver = this.f9255a;
        this.e = broadcastReceiver;
        Context context = this.h;
        if (context == null) {
            ReleaseLogUtil.d("DEVMGR_UpdateVersionActivity", "registerConnectStateBroadcast mContext is null");
        } else {
            BroadcastManagerUtil.bFC_(context, broadcastReceiver, intentFilter, LocalBroadcast.c, null);
        }
    }

    private void ax() {
        Context context;
        if (this.e == null || (context = this.h) == null) {
            ReleaseLogUtil.d("DEVMGR_UpdateVersionActivity", "mConnectChangedReceiver is :", this.f9255a, "mContext is:", this.h);
            return;
        }
        try {
            context.unregisterReceiver(this.f9255a);
        } catch (IllegalArgumentException e) {
            LogUtil.a("UpdateVersionActivity", e.getMessage());
        }
    }

    private void av() {
        BroadcastReceiver broadcastReceiver = this.b;
        if (broadcastReceiver == null) {
            return;
        }
        try {
            this.h.unregisterReceiver(broadcastReceiver);
        } catch (IllegalArgumentException e) {
            LogUtil.a("UpdateVersionActivity", e.getMessage());
        }
    }

    private void e(int i, int i2) {
        ReleaseLogUtil.e("DEVMGR_UpdateVersionActivity", "enter sendOtaEvent errorMsg=", Integer.valueOf(i2));
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(16);
        DeviceInfo deviceInfo = this.t;
        if (deviceInfo == null) {
            LogUtil.a("UpdateVersionActivity", "enter sendOtaEvent device is null.");
            return;
        }
        int productType = deviceInfo.getProductType();
        LogUtil.a("UpdateVersionActivity", "sendOtaEvent deviceType=", Integer.valueOf(productType));
        if (productType != -1) {
            linkedHashMap.put(DeviceCategoryFragment.DEVICE_TYPE, String.valueOf(productType));
            linkedHashMap.put("device_classfication", String.valueOf(cwc.b(productType)));
        }
        if (i == 23) {
            linkedHashMap.put("operationID", "5");
        } else {
            linkedHashMap.put("operationID", "6");
            if (i2 == 3) {
                linkedHashMap.put("errorID", "102");
            } else if (i2 == 4) {
                linkedHashMap.put("errorID", "101");
            } else {
                linkedHashMap.put("errorID", "103");
            }
        }
        oaf oafVar = this.au;
        if (oafVar != null) {
            linkedHashMap.put("deviceNewVersion", oafVar.b(this.ah));
            linkedHashMap.put("versionID", HwVersionManager.c(this.h).i(this.ah));
        } else {
            LogUtil.a("UpdateVersionActivity", "sendOtaEvent UpdateInteractors is null.");
        }
        OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_DEVICE_OTA_UPDATE_80020004.value(), linkedHashMap);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        LogUtil.a("UpdateVersionActivity", "onActivityResult resultCode:", Integer.valueOf(i2), " requestCode:", Integer.valueOf(i));
        if (i == 101) {
            if (i2 == 1000) {
                this.y = true;
            } else if (i2 == 2000) {
                this.y = false;
            } else {
                LogUtil.h("UpdateVersionActivity", "onActivityResult error");
            }
        }
        super.onActivityResult(i, i2, intent);
    }

    private void n() {
        if (this.ac || !b(this.q) || !this.ad) {
            LogUtil.a("UpdateVersionActivity", "handleBack, AW70 or notSupportWlan mIsCurrentDeviceAw70：", Boolean.valueOf(this.ac), "isSupportWlan :", Boolean.valueOf(b(this.q)), "mIsSupportBackground :", Boolean.valueOf(this.ad));
            ao();
            return;
        }
        ReleaseLogUtil.e("DEVMGR_UpdateVersionActivity", "handleBack, wear device isSupportAuto:", Boolean.valueOf(ac()), " mIsAutoUpdateEnable :", Boolean.valueOf(this.y), "not remind : ", Boolean.valueOf(kxz.e(BaseApplication.getContext())));
        if (ac() && !this.y && !kxz.e(BaseApplication.getContext())) {
            al();
        } else {
            aw();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aw() {
        ReleaseLogUtil.e("DEVMGR_UpdateVersionActivity", "startBackgroundUpdate enter");
        jkj.d().j(this.ah);
        av();
        finish();
    }

    private void al() {
        LogUtil.a("UpdateVersionActivity", "showAutoUpdateDialog enter");
        View inflate = View.inflate(this.h, R.layout.dialog_device_auto_tip, null);
        final HealthCheckBox healthCheckBox = (HealthCheckBox) nsy.cMd_(inflate, R.id.device_auto_layout_checkbox);
        healthCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.ui.device.activity.update.UpdateVersionActivity.6
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                LogUtil.a("UpdateVersionActivity", "showAutoUpdateDialog onCheckedChanged:", Boolean.valueOf(z));
                ViewClickInstrumentation.clickOnView(compoundButton);
            }
        });
        CustomAlertDialog c = new CustomAlertDialog.Builder(this.h).e(R.string._2130843086_res_0x7f0215ce).cyp_(inflate).cyn_(R.string._2130842831_res_0x7f0214cf, new DialogInterface.OnClickListener() { // from class: com.huawei.ui.device.activity.update.UpdateVersionActivity.13
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                LogUtil.a("UpdateVersionActivity", "showAutoUpdateDialog, user click Negative button!");
                UpdateVersionActivity.this.aw();
                kxz.c(BaseApplication.getContext(), healthCheckBox.isChecked());
                jqi.a().setSwitchSetting("auto_update_status", "false", UpdateVersionActivity.this.am, null);
                oaf.b(BaseApplication.getContext()).c(UpdateVersionActivity.this.ah, false);
                HashMap hashMap = new HashMap(16);
                hashMap.put("click", 1);
                hashMap.put("type", "1");
                ixx.d().d(BaseApplication.getContext(), AnalyticsValue.SETTING_1090037.value(), hashMap, 0);
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
            }
        }).cyo_(R.string._2130842176_res_0x7f021240, new DialogInterface.OnClickListener() { // from class: com.huawei.ui.device.activity.update.UpdateVersionActivity.9
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                LogUtil.a("UpdateVersionActivity", "showAutoUpdateDialog, user click Positive button!");
                UpdateVersionActivity.this.aw();
                kxz.c(BaseApplication.getContext(), healthCheckBox.isChecked());
                jqi.a().setSwitchSetting("auto_update_status", "true", UpdateVersionActivity.this.am, null);
                oaf.b(BaseApplication.getContext()).c(UpdateVersionActivity.this.ah, true);
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
            }
        }).c();
        c.setCancelable(false);
        c.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b(int i) {
        cuw c = jfu.c(i);
        if (c.u() == 1) {
            return true;
        }
        if (c.u() != 2) {
            if (!cvt.c(i) && 12 != i) {
                return true;
            }
            LogUtil.h("UpdateVersionActivity", "not support wlan");
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean ac() {
        DeviceCapability e = cvs.e(this.ah);
        return e != null && e.getIsSupportAutoUpdate();
    }

    private void b(DeviceInfo deviceInfo) {
        String e = kxz.e(deviceInfo.getSecurityDeviceId(), BaseApplication.getContext());
        if (TextUtils.isEmpty(e) || !e.equals(deviceInfo.getDeviceOtaPackageName())) {
            kxz.k(deviceInfo.getSecurityDeviceId(), deviceInfo.getDeviceOtaPackageName(), this.h);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
