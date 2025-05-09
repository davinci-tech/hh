package com.huawei.health.device.ui;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.router.AppRouterUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.device.connectivity.comm.MeasurableDevice;
import com.huawei.health.device.fatscale.multiusers.MultiUsersManager;
import com.huawei.health.device.manager.ResourceFileListener;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.ui.DeviceMainActivity;
import com.huawei.health.device.ui.measure.fragment.HagridDeviceBindGuidFragment;
import com.huawei.health.device.ui.measure.fragment.HagridDeviceBindResultFragment;
import com.huawei.health.device.ui.measure.fragment.HagridDeviceManagerFragment;
import com.huawei.health.device.ui.measure.fragment.HagridDeviceWlanUseGuideFragment;
import com.huawei.health.device.ui.measure.fragment.HagridWiFiInfoConfirmFragment;
import com.huawei.health.device.ui.measure.fragment.HagridWifiProductUpgradeFragment;
import com.huawei.health.device.ui.measure.fragment.HonourDeviceFragment;
import com.huawei.health.device.ui.measure.fragment.WeightAutoMeasureFragment;
import com.huawei.health.device.ui.measure.fragment.WiFiProductIntroductionFragment;
import com.huawei.health.device.ui.measure.fragment.WiFiProductUpgradeFragment;
import com.huawei.health.device.ui.measure.fragment.device.DeviceBindWaitingFragment;
import com.huawei.health.device.ui.measure.fragment.device.DeviceBindWaitingUniversalFragment;
import com.huawei.health.ecologydevice.manager.GprsDeviceHelper;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.ecologydevice.ui.BaseFragment;
import com.huawei.health.ecologydevice.ui.measure.fragment.BaseRopeIntroductionFragment;
import com.huawei.health.ecologydevice.ui.measure.fragment.BloodPressureIntroductionFragment;
import com.huawei.health.ecologydevice.ui.measure.fragment.BloodSugarIntroductionFragment;
import com.huawei.health.ecologydevice.ui.measure.fragment.MyDeviceFragment;
import com.huawei.health.ecologydevice.ui.measure.fragment.NfcOrQrCodeGuideFragment;
import com.huawei.health.ecologydevice.ui.measure.fragment.ProductIntroductionFragment;
import com.huawei.health.ecologydevice.ui.measure.fragment.RopeSkippingGuideFragment;
import com.huawei.health.ecologydevice.ui.measure.fragment.SecondRopeIntroductionFragment;
import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceBindingFragment;
import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment;
import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceConnectingFragment;
import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceScanningFragment;
import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceSilentGuideFragment;
import com.huawei.health.ecologydevice.ui.measure.fragment.utils.ProductCreateFactory;
import com.huawei.health.ecologydevice.ui.util.PermissionDialogHelper;
import com.huawei.health.messagecenter.model.CommonUtil;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.device.open.HealthDevice;
import com.huawei.hihealth.dictionary.constants.ProductMap;
import com.huawei.hihealth.dictionary.constants.ProductMapInfo;
import com.huawei.hihealth.dictionary.utils.ProductMapParseUtil;
import com.huawei.hms.network.embedded.u3;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.AdShowExtras;
import com.huawei.operation.activity.WebViewActivity;
import com.huawei.operation.ble.BleConstants;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dialog.CommonDialog21;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import defpackage.ceo;
import defpackage.cfi;
import defpackage.cgt;
import defpackage.cjx;
import defpackage.ckq;
import defpackage.cld;
import defpackage.cnb;
import defpackage.cpa;
import defpackage.cpl;
import defpackage.cpp;
import defpackage.cpw;
import defpackage.cqa;
import defpackage.cqb;
import defpackage.cqh;
import defpackage.csb;
import defpackage.cue;
import defpackage.dch;
import defpackage.dcq;
import defpackage.dcr;
import defpackage.dcx;
import defpackage.dcz;
import defpackage.dib;
import defpackage.djr;
import defpackage.dks;
import defpackage.gmz;
import defpackage.jeg;
import defpackage.koq;
import defpackage.nrh;
import defpackage.nsn;
import health.compact.a.LanguageUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes.dex */
public class DeviceMainActivity extends BaseActivity {
    private Fragment aa;
    private String ab;
    private String ac;
    private Fragment ad;
    private CustomTitleBar ae;
    private Bundle ag;
    private WeakReference<Timer> ah;
    private String ai;
    private String aj;
    private int am;
    private String c;
    private NoTitleCustomAlertDialog d;
    private Class<?> e;
    private String f;
    private String g;
    private Handler h;
    private String j;
    private boolean l;
    private boolean o;
    private boolean p;
    private long r;
    private boolean s;
    private View u;
    private String v;
    private CommonDialog21 w;
    private String x;
    private Class y;
    private String z;

    /* renamed from: a, reason: collision with root package name */
    private Fragment f2230a = null;
    private boolean q = false;
    private boolean m = false;
    private boolean k = false;
    private Bundle b = new Bundle();
    private ArrayList<String> ak = new ArrayList<>(16);
    private boolean n = true;
    private boolean t = false;
    private ArrayList<HealthDevice> i = new ArrayList<>(10);
    private Map<String, String> af = new HashMap();

    /* loaded from: classes3.dex */
    static class d implements IBaseResponseCallback {
        d() {
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            LogUtil.a("DeviceMainActivity", "DeviceMainActivity getOtherUserData errorCode:", Integer.valueOf(i));
        }
    }

    /* loaded from: classes3.dex */
    static class a extends TimerTask {

        /* renamed from: a, reason: collision with root package name */
        private Handler f2231a;
        private String b;
        private Intent c;
        private Timer d;
        private WeakReference<DeviceMainActivity> e;

        a(DeviceMainActivity deviceMainActivity, Handler handler, Intent intent, String str, Timer timer) {
            this.e = new WeakReference<>(deviceMainActivity);
            this.f2231a = handler;
            this.c = intent;
            this.b = str;
            this.d = timer;
        }

        private void e() {
            this.d.cancel();
            Handler handler = this.f2231a;
            if (handler != null) {
                handler.removeCallbacksAndMessages(null);
            }
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            final DeviceMainActivity deviceMainActivity = this.e.get();
            if (deviceMainActivity == null) {
                LogUtil.h("DeviceMainActivity", "mainActivity is null");
                e();
            } else if (cjx.e().a(this.b) != null) {
                e();
                deviceMainActivity.runOnUiThread(new Runnable() { // from class: com.huawei.health.device.ui.DeviceMainActivity.a.5
                    @Override // java.lang.Runnable
                    public void run() {
                        deviceMainActivity.GP_(a.this.c);
                        deviceMainActivity.n();
                    }
                });
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.ag = bundle;
        }
        this.r = System.currentTimeMillis();
        if (nsn.s()) {
            Resources resources = getResources();
            DisplayMetrics displayMetrics = resources.getDisplayMetrics();
            Configuration configuration = resources.getConfiguration();
            configuration.fontScale = 2.4f;
            resources.updateConfiguration(configuration, displayMetrics);
        }
        LogUtil.a("DeviceMainActivity", "DeviceMainActivity onCreate");
        this.o = true;
        Intent intent = getIntent();
        this.h = new Handler(Looper.getMainLooper());
        GG_(intent);
        setContentView(R.layout.device_main_activity);
        clearBackgroundDrawable();
        if (!nsn.ag(this)) {
            setRequestedOrientation(1);
        }
        t();
        if (GE_(intent)) {
            LogUtil.a("DeviceMainActivity", "handleSmartHeadphones enter");
            return;
        }
        if (GD_(intent)) {
            return;
        }
        j();
        ResourceManager.e().c();
        cfi currentUser = MultiUsersManager.INSTANCE.getCurrentUser();
        if (currentUser == null || currentUser.i() == null) {
            LogUtil.a("DeviceMainActivity", "DeviceMainActivity getMainUser");
            MultiUsersManager.INSTANCE.getMainUser();
        }
        MultiUsersManager.INSTANCE.getOtherUserData(new d());
        cancelAdaptRingRegion();
    }

    private void ak() {
        if ("68".equals(this.f)) {
            if (TextUtils.isEmpty(this.z)) {
                LogUtil.a("DeviceMainActivity", "goto BloodPressureIntroductionFragment");
                ab();
                return;
            } else {
                LogUtil.a("DeviceMainActivity", "goto NfcOrQrCodeGuideFragment");
                ah();
                return;
            }
        }
        if ("69".equals(this.f)) {
            LogUtil.a("DeviceMainActivity", "goto BloodSugarIntroductionFragment");
            ai();
            return;
        }
        if ("262".equals(this.f)) {
            LogUtil.a("DeviceMainActivity", "goto RopeSkippingGuideFragment");
            al();
            return;
        }
        if ("54".equals(this.f)) {
            LogUtil.a("DeviceMainActivity", "goto DeviceBindWaitingFragment");
            ae();
        } else if (c(this.f)) {
            LogUtil.a("DeviceMainActivity", "goto NfcOrQrCodeGuideFragment");
            ah();
        } else if (s()) {
            ah();
        } else {
            LogUtil.a("DeviceMainActivity", "not goto Fragment");
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private boolean c(String str) {
        char c;
        str.hashCode();
        int hashCode = str.hashCode();
        if (hashCode == 49773) {
            if (str.equals("261")) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode == 49778) {
            if (str.equals("266")) {
                c = 1;
            }
            c = 65535;
        } else if (hashCode != 49780) {
            switch (hashCode) {
                case 49803:
                    if (str.equals("270")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case 49804:
                    if (str.equals("271")) {
                        c = 4;
                        break;
                    }
                    c = 65535;
                    break;
                case 49805:
                    if (str.equals("272")) {
                        c = 5;
                        break;
                    }
                    c = 65535;
                    break;
                case 49806:
                    if (str.equals("273")) {
                        c = 6;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
        } else {
            if (str.equals(BleConstants.TYPE_FASCIA_GUN_INDEX)) {
                c = 2;
            }
            c = 65535;
        }
        switch (c) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                return true;
            default:
                return false;
        }
    }

    private void GG_(Intent intent) {
        if (intent != null) {
            try {
                this.s = intent.getBooleanExtra("is_go_rope_jump", false);
                this.b.putInt("bloodSugarMeasureType", intent.getIntExtra("bloodsugar_timeperiod_key", 0));
                if (intent.getStringExtra("bloodsugar_timeperiod_key_string") != null) {
                    this.b.putString("bloodSugarMeasureTypeString", intent.getStringExtra("bloodsugar_timeperiod_key_string"));
                }
                this.am = intent.getIntExtra("type", 0);
                if (intent.getBooleanExtra("isFromDiscover", false)) {
                    LogUtil.a("DeviceMainActivity", "onCreate isFromDiscover.");
                    this.b.putBoolean("isFromDiscover", true);
                } else {
                    this.b.putBoolean("isFromDiscover", false);
                }
                this.b.putInt("isHeartRateDevice", intent.getIntExtra("isHeartRateDevice", 0));
                this.b.putBoolean("isFromFitnessAdvice", intent.getBooleanExtra("isFromFitnessAdvice", false));
                this.b.putString("title", intent.getStringExtra("title"));
                boolean booleanExtra = intent.getBooleanExtra("isNfcConnect", false);
                this.p = booleanExtra;
                LogUtil.a("DeviceMainActivity", "initData getBooleanExtra mIsNfcConnect: ", Boolean.valueOf(booleanExtra));
                this.b.putString("productId", intent.getStringExtra("productId"));
                this.ai = intent.getStringExtra("productId");
                this.aj = intent.getStringExtra("uniqueId");
            } catch (Exception unused) {
                LogUtil.b("DeviceMainActivity", "onCreate Exception");
            }
        }
    }

    public Bundle Hi_() {
        return this.b;
    }

    private boolean GJ_(Intent intent) {
        if (intent == null) {
            return false;
        }
        String stringExtra = intent.getStringExtra("PAYLOAD_FROM_NFC");
        this.ac = stringExtra;
        if (!TextUtils.isEmpty(stringExtra)) {
            this.f = dks.e(this.ac);
            this.v = dks.a("ble", this.ac);
            this.c = dks.a("brd", this.ac);
            this.g = dks.a("n", this.ac);
            this.z = dks.a(u3.m, this.ac);
            this.ab = dks.a("l", this.ac);
        } else if (intent.getBooleanExtra("KEY_TO_GET_START_FROM_QRCODE", false)) {
            this.f = intent.getStringExtra("DEVICE_TYPE_INDEX");
            this.v = intent.getStringExtra("BLE_FROM_QRCODE");
            this.c = intent.getStringExtra("BRAND_FROM_QRCODE");
            this.g = intent.getStringExtra("BLENAME_FROM_QRCODE");
            this.z = intent.getStringExtra("PID_FROM_QRCODE");
            this.ab = intent.getStringExtra("PIN_FROM_QRCODE");
        } else {
            LogUtil.a("DeviceMainActivity", "unknow type");
            this.f = null;
        }
        if (!TextUtils.isEmpty(this.f)) {
            if ("68".equals(this.f)) {
                return GH_(intent);
            }
            if ("69".equals(this.f)) {
                return m();
            }
            if ("262".equals(this.f)) {
                return r();
            }
            if (c(this.f)) {
                return k();
            }
            if ("54".equals(this.f)) {
                return GI_(intent);
            }
            if (s()) {
                q();
                return true;
            }
        }
        return false;
    }

    private boolean s() {
        return BleConstants.SPORT_TYPE_BIKE.equals(this.f) || "261".equals(this.f) || "260".equals(this.f) || "31".equals(this.f);
    }

    private void t() {
        this.af.put("266", "HDK_SMART_PILLOW");
        this.af.put(BleConstants.TYPE_FASCIA_GUN_INDEX, "HDK_MASSAGE_GUN");
        this.af.put("271", "HDK_ECG");
        this.af.put("273", "HDK_BLOOD_OXYGEN");
        this.af.put("270", "HDK_HEART_RATE");
        this.af.put("272", "HDK_BODY_TEMPERATURE");
        this.af.put("68", "HDK_BLOOD_PRESSURE");
        this.af.put("69", "HDK_BLOOD_SUGAR");
        this.af.put("262", "HDK_ROPE_SKIPPING");
        this.af.put("54", "HDK_WEIGHT");
        this.af.put(BleConstants.SPORT_TYPE_BIKE, "HDK_EXERCISE_BIKE");
        this.af.put("261", "HDK_ROWING_MACHINE");
        this.af.put("260", "HDK_ELLIPTICAL_MACHINE");
        this.af.put("31", "HDK_TREADMILL");
    }

    private boolean GH_(Intent intent) {
        if (TextUtils.isEmpty(this.c) || TextUtils.isEmpty(this.v)) {
            return false;
        }
        if ("100".equals(this.c)) {
            q();
            return true;
        }
        intent.setAction("SWITCH_PLUGINDEVICE");
        intent.putExtra("kind", "HDK_UNKNOWN");
        intent.putExtra("arg1", "DeviceList");
        return false;
    }

    private void q() {
        this.b.putString("DEVICE_TYPE_INDEX", this.f);
        this.b.putString("BLE_FROM_QRCODE", this.v);
        this.b.putString("PAYLOAD_FROM_NFC", this.ac);
        this.b.putString("BLENAME_FROM_QRCODE", this.g);
        this.b.putString("BRAND_FROM_QRCODE", this.c);
        this.b.putString("PID_FROM_QRCODE", this.z);
        this.b.putString("PIN_FROM_QRCODE", this.ab);
        this.b.putString("DeviceType", this.af.get(this.f));
    }

    private boolean m() {
        if ((TextUtils.isEmpty(this.v) && TextUtils.isEmpty(this.g)) || TextUtils.isEmpty(this.z)) {
            return false;
        }
        q();
        return true;
    }

    private boolean k() {
        q();
        return true;
    }

    private void ae() {
        String string = this.b.getString("PID_FROM_QRCODE");
        if (TextUtils.isEmpty(string)) {
            LogUtil.h("DeviceMainActivity", "switchBodyFatScaleFragment pid is null");
            return;
        }
        ProductMapParseUtil.b(cpp.a());
        ProductMapInfo d2 = ProductMap.d(string);
        if (d2 == null) {
            LogUtil.h("DeviceMainActivity", "switchBodyFatScaleFragment productInfo is null");
            return;
        }
        if (cpa.ad(d2.h())) {
            if (this.l) {
                ag();
                return;
            } else {
                af();
                return;
            }
        }
        ah();
    }

    private boolean r() {
        if (TextUtils.isEmpty(this.z)) {
            LogUtil.h("DeviceMainActivity", "isRopeType() pid is null");
            return false;
        }
        q();
        this.b.putBoolean("KEY_TO_GET_START_FROM_QRCODE", true);
        return true;
    }

    private boolean GI_(Intent intent) {
        ProductMapParseUtil.b(cpp.a());
        ProductMapInfo d2 = ProductMap.d(this.z);
        if (d2 == null) {
            return false;
        }
        if (cpa.ad(d2.h())) {
            return GK_(intent, this.v, this.z);
        }
        q();
        return true;
    }

    private boolean GK_(Intent intent, String str, String str2) {
        this.l = intent.getBooleanExtra("isBondedDevice", false);
        boolean booleanExtra = intent.getBooleanExtra("isNfcConnect", false);
        this.p = booleanExtra;
        LogUtil.a("DeviceMainActivity", "isFormNfcOrQrCodeForMeasurePressure getBooleanExtra mIsNfcConnect: ", Boolean.valueOf(booleanExtra));
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.h("DeviceMainActivity", "isHagridScaleType address or pid is empty");
            return false;
        }
        if (this.b == null) {
            LogUtil.h("DeviceMainActivity", "isHagridScaleType mBundle is null");
            return false;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("uniqueId", str);
        if ("M00F".equals(str2)) {
            contentValues.put("productId", "b29df4e3-b1f7-4e40-960d-4cfb63ccca05");
        }
        this.b.putString("PID_FROM_QRCODE", str2);
        this.b.putParcelable("commonDeviceInfo", contentValues);
        this.b.putString("productId", "b29df4e3-b1f7-4e40-960d-4cfb63ccca05");
        this.b.putString("DeviceType", "HDK_WEIGHT");
        this.b.putBoolean("isNfcConnect", this.p);
        LogUtil.a("DeviceMainActivity", "putBoolean mIsNfcConnect: ", Boolean.valueOf(this.p));
        return true;
    }

    public Class<?> b() {
        return this.y;
    }

    public void c(Class<?> cls) {
        this.y = cls;
    }

    public boolean g() {
        return this.q;
    }

    public boolean f() {
        return this.m;
    }

    @Override // android.app.Activity
    protected void onRestart() {
        LogUtil.a("DeviceMainActivity", "DeviceMainActivity onRestart.");
        super.onRestart();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        Class<?> cls;
        LogUtil.a("DeviceMainActivity", "DeviceMainActivity onResume.");
        super.onResume();
        Intent intent = getIntent();
        boolean z = false;
        if (intent != null) {
            boolean booleanExtra = intent.getBooleanExtra("isJumpFromFittings", false);
            this.q = intent.getBooleanExtra("FROM_SMART_LIFE", false);
            boolean booleanExtra2 = intent.getBooleanExtra("isNfcConnect", false);
            this.p = booleanExtra2;
            LogUtil.a("DeviceMainActivity", "onResume getBooleanExtra mIsNfcConnect: ", Boolean.valueOf(booleanExtra2), " isJumpFromFittings", Boolean.valueOf(booleanExtra));
            z = booleanExtra;
        } else {
            LogUtil.h("DeviceMainActivity", "onResume intent is null");
        }
        LogUtil.a("DeviceMainActivity", "DeviceMainActivity mIsActivityStoped is ", Boolean.valueOf(this.k));
        if (!this.k || (cls = this.e) == null) {
            return;
        }
        String simpleName = cls.getSimpleName();
        if (TextUtils.isEmpty(simpleName) || "ProductIntroductionFragment".equals(simpleName) || "WiFiProductIntroductionFragment".equals(simpleName) || "HagridDeviceBindResultFragment".equals(simpleName) || z) {
            return;
        }
        getSupportFragmentManager().popBackStack(this.e.getSimpleName(), 1);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        LogUtil.a("DeviceMainActivity", "DeviceMainActivity onNewIntent");
        setIntent(intent);
        this.o = true;
        if (intent == null) {
            return;
        }
        if (!intent.getBooleanExtra("FROM_BIND_USER_INFO", false) && dks.a(this.r)) {
            LogUtil.h("DeviceMainActivity", "repeat init view, can not create fragment immediately again.");
            return;
        }
        t();
        if (GE_(intent) || GD_(intent)) {
            return;
        }
        j();
    }

    public void c(boolean z) {
        this.o = z;
    }

    private boolean GD_(Intent intent) {
        if (!GJ_(intent)) {
            return false;
        }
        LogUtil.a("DeviceMainActivity", "isFormNfcOrQrCodeForMeasurePressure true");
        ak();
        return true;
    }

    private boolean GE_(Intent intent) {
        if (intent == null) {
            ReleaseLogUtil.d("R_Weight_DeviceMainActivity", "handleSmartHeadphones intent == null");
            return false;
        }
        String stringExtra = intent.getStringExtra("Device_Type");
        if (!"082".equals(stringExtra)) {
            return false;
        }
        String stringExtra2 = intent.getStringExtra("macAddress");
        String stringExtra3 = intent.getStringExtra("PID_FROM_QRCODE");
        this.b.putString("BLE_FROM_QRCODE", stringExtra2);
        this.b.putString("BLENAME_FROM_QRCODE", "");
        this.b.putString("DEVICE_TYPE_INDEX", stringExtra);
        this.b.putString("PID_FROM_QRCODE", stringExtra3);
        this.b.putString("PIN_FROM_QRCODE", "");
        this.b.putString("DeviceType", "SMART_HEADPHONES");
        this.b.putBoolean("FROM_SMART_LIFE", true);
        ah();
        return true;
    }

    private void ab() {
        Fragment fragment = this.f2230a;
        if (fragment != null) {
            String simpleName = fragment.getClass().getSimpleName();
            LogUtil.a("DeviceMainActivity", "DeviceMainActivity fragment name is ", simpleName);
            if (!TextUtils.isEmpty(simpleName) && "BloodPressureIntroductionFragment".equals(simpleName)) {
                LogUtil.a("DeviceMainActivity", "switchBloodPressureIntroductionFragment onNewIntent switch");
                return;
            } else {
                if (!TextUtils.isEmpty(simpleName) && "BloodPressureMeasuringProgressFragment".equals(simpleName)) {
                    LogUtil.a("DeviceMainActivity", "BloodPressureMeasuringProgressFragment is isMeasuring");
                    return;
                }
                LogUtil.h("DeviceMainActivity", "switchBloodPressureIntroductionFragment other fragment");
            }
        }
        BloodPressureIntroductionFragment bloodPressureIntroductionFragment = new BloodPressureIntroductionFragment();
        bloodPressureIntroductionFragment.setArguments(this.b);
        a((Fragment) null, bloodPressureIntroductionFragment);
    }

    private void ai() {
        Fragment fragment = this.f2230a;
        if (fragment != null) {
            String simpleName = fragment.getClass().getSimpleName();
            LogUtil.a("DeviceMainActivity", "DeviceMainActivity fragment name is :", simpleName);
            if (!TextUtils.isEmpty(simpleName) && "BloodSugarIntroductionFragment".equals(simpleName)) {
                LogUtil.a("DeviceMainActivity", "DeviceMainActivity onNewIntent switch");
                return;
            }
            LogUtil.h("DeviceMainActivity", "other fragment");
        }
        BloodSugarIntroductionFragment bloodSugarIntroductionFragment = new BloodSugarIntroductionFragment();
        bloodSugarIntroductionFragment.setArguments(this.b);
        a((Fragment) null, bloodSugarIntroductionFragment);
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        LogUtil.a("DeviceMainActivity", "DeviceMainActivity onSaveInstanceState.");
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        LogUtil.a("DeviceMainActivity", "DeviceMainActivity onStop.");
        super.onStop();
        this.k = true;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        LogUtil.a("DeviceMainActivity", "DeviceMainActivity onDestroy mIsDisConnectFlag: ", Boolean.valueOf(this.n));
        cqa.a().b();
        cqb.d().c();
        cqh.c().b();
        Handler handler = this.h;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        ThreadPoolManager.d().execute(new Runnable() { // from class: ckp
            @Override // java.lang.Runnable
            public final void run() {
                DeviceMainActivity.this.h();
            }
        });
        djr.b();
    }

    public /* synthetic */ void h() {
        ContentValues Gq_;
        String str = this.ai;
        if (TextUtils.isEmpty(str) && (Gq_ = cjx.e().Gq_()) != null) {
            str = Gq_.getAsString("productId");
        }
        if (cpa.ak(str)) {
            cld.HG_(this);
        } else if (this.n) {
            cgt.e().cleanup();
        } else {
            this.n = true;
            LogUtil.h("DeviceMainActivity", "bind flow goto BindUserInfoActivity, not disconnect");
        }
    }

    public void d(String str) {
        this.ai = str;
    }

    public void e(String str) {
        this.aj = str;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        LogUtil.a("DeviceMainActivity", "DeviceMainActivity onBackPressed called");
        this.n = true;
        Fragment fragment = this.f2230a;
        if (fragment != null) {
            String simpleName = fragment.getClass().getSimpleName();
            LogUtil.a("DeviceMainActivity", "DeviceMainActivity fragment name is ", simpleName);
            if ("HagridDeviceManagerFragment".equals(simpleName) || "ProductIntroductionFragment".equals(simpleName) || "WiFiProductIntroductionFragment".equals(simpleName)) {
                if (this.q) {
                    moveTaskToBack(true);
                } else if (!nsn.ag(this)) {
                    moveTaskToBack(false);
                }
            }
            Fragment fragment2 = this.f2230a;
            if (!(fragment2 instanceof BaseFragment)) {
                LogUtil.h("DeviceMainActivity", "mCurrentFragment is not instanceof BaseFragment in func onBackPressed");
                return;
            } else {
                if (!((BaseFragment) fragment2).onBackPressed() || isFinishing()) {
                    return;
                }
                super.onBackPressed();
                return;
            }
        }
        super.onBackPressed();
    }

    @Override // androidx.core.app.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        LogUtil.a("DeviceMainActivity", "DeviceMainActivity dispatchKeyEvent called");
        if (keyEvent == null) {
            LogUtil.h("DeviceMainActivity", "DeviceMainActivity dispatchKeyEvent event is null");
            return true;
        }
        if (cpa.ad(this.ai) && keyEvent.getKeyCode() == 4 && keyEvent.getAction() == 1) {
            onBackPressed();
            return true;
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    protected void j() {
        String str;
        IllegalArgumentException e;
        String str2 = "view";
        Intent intent = getIntent();
        HealthDevice.HealthDeviceKind healthDeviceKind = HealthDevice.HealthDeviceKind.HDK_UNKNOWN;
        String str3 = "";
        if (intent != null) {
            this.q = intent.getBooleanExtra("FROM_SMART_LIFE", false);
            this.m = intent.getIntExtra(CommonUtil.PARAM_HEALTH_TYPE, 0) == 6;
            ContentValues contentValues = (ContentValues) intent.getParcelableExtra("commonDeviceInfo");
            if (contentValues != null) {
                this.ai = contentValues.getAsString("productId");
                String asString = contentValues.getAsString("uniqueId");
                this.aj = asString;
                LogUtil.a("DeviceMainActivity", "mProductId: ", this.ai, ", mUniqueId: ", cpw.a(asString));
            } else {
                LogUtil.h("DeviceMainActivity", "device info is null");
            }
            if (TextUtils.isEmpty(this.ai)) {
                this.ai = intent.getStringExtra("productId");
            }
            this.j = intent.getStringExtra("mDeviceId");
            this.x = intent.getStringExtra("mac");
            try {
                this.ak = intent.getStringArrayListExtra("uuid_list");
            } catch (ArrayIndexOutOfBoundsException unused) {
                LogUtil.b("DeviceMainActivity", "ArrayIndexOutOfBoundsException get uuid list");
            } catch (IndexOutOfBoundsException unused2) {
                LogUtil.b("DeviceMainActivity", "IndexOutOfBoundsException get uuid list");
            }
            try {
                try {
                    if (TextUtils.isEmpty(intent.getStringExtra("view")) && TextUtils.isEmpty(intent.getStringExtra("kind")) && TextUtils.isEmpty(intent.getStringExtra("root_in_me")) && AppRouterUtils.zs_(intent) != null) {
                        Uri zs_ = AppRouterUtils.zs_(intent);
                        str2 = zs_.getQueryParameter("view");
                        String queryParameter = zs_.getQueryParameter("kind");
                        healthDeviceKind = HealthDevice.HealthDeviceKind.valueOf(queryParameter);
                        LogUtil.a("DeviceMainActivity", "getRawUriFormIntent viewOption: ", str2, "  strKind: ", queryParameter);
                    } else {
                        str2 = TextUtils.isEmpty(intent.getStringExtra("view")) ? "" : intent.getStringExtra("view");
                        if (!TextUtils.isEmpty(intent.getStringExtra("root_in_me"))) {
                            str3 = intent.getStringExtra("root_in_me");
                        }
                        if (intent.getStringExtra("kind") != null) {
                            healthDeviceKind = HealthDevice.HealthDeviceKind.valueOf(intent.getStringExtra("kind"));
                        }
                    }
                    this.b.putString("productId", intent.getStringExtra("productId"));
                    this.q = intent.getBooleanExtra("FROM_SMART_LIFE", false);
                    String str4 = str3;
                    str3 = str2;
                    str = str4;
                } catch (IllegalArgumentException e2) {
                    e = e2;
                    String str5 = str3;
                    str3 = str2;
                    str = str5;
                    LogUtil.b("DeviceMainActivity", "DeviceMainActivity Invalid device kind.", e.getMessage());
                    dks.b();
                    GX_(str3, intent, str, healthDeviceKind);
                } catch (Exception unused3) {
                    String str6 = str3;
                    str3 = str2;
                    str = str6;
                    LogUtil.b("DeviceMainActivity", "initView Exception");
                    dks.b();
                    GX_(str3, intent, str, healthDeviceKind);
                }
            } catch (IllegalArgumentException e3) {
                e = e3;
                str = "";
            } catch (Exception unused4) {
                str = "";
            }
        } else {
            str = "";
        }
        dks.b();
        GX_(str3, intent, str, healthDeviceKind);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void GX_(String str, Intent intent, String str2, HealthDevice.HealthDeviceKind healthDeviceKind) {
        char c;
        LogUtil.a("DeviceMainActivity", "showDeviceView: viewOption: ", str);
        str.hashCode();
        switch (str.hashCode()) {
            case -2038620884:
                if (str.equals("hagridDeviceWlanUseGuide")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1915525717:
                if (str.equals("WiFiConfirm")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -1117515941:
                if (str.equals("deviceManage")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -955877153:
                if (str.equals("WifiVersionDetails")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -383514121:
                if (str.equals("PickDevice")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 186368793:
                if (str.equals("BondDevice")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 201608446:
                if (str.equals("downloadDevice")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 1915300020:
                if (str.equals("ListDevice")) {
                    c = 7;
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
                GZ_(intent);
                break;
            case 1:
                GT_(intent);
                break;
            case 2:
                GV_(intent);
                break;
            case 3:
                Hc_(intent);
                break;
            case 4:
                b(healthDeviceKind);
                break;
            case 5:
                c(healthDeviceKind, false);
                break;
            case 6:
                u();
                break;
            case 7:
                GY_(intent, str2, healthDeviceKind);
                break;
            default:
                Hd_(str, intent, healthDeviceKind);
                break;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void Hd_(String str, Intent intent, HealthDevice.HealthDeviceKind healthDeviceKind) {
        char c;
        str.hashCode();
        switch (str.hashCode()) {
            case -2051459131:
                if (str.equals("AutoMeasureDevice")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -941616371:
                if (str.equals("bindScale")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -941215433:
                if (str.equals("bindSport")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 894600181:
                if (str.equals("bindGuideDevice")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1280762164:
                if (str.equals("MeasureDevice")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1409212968:
                if (str.equals("WiFiBindDevice")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 1861313190:
                if (str.equals("bindResultConfirm")) {
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
                GO_(healthDeviceKind, intent);
                break;
            case 1:
                aa();
                break;
            case 2:
                He_(intent);
                break;
            case 3:
                w();
                break;
            case 4:
                GW_(healthDeviceKind, intent);
                break;
            case 5:
                ad();
                break;
            case 6:
                Hb_(intent);
                break;
            default:
                if (intent != null) {
                    GS_(intent);
                    break;
                }
                break;
        }
    }

    private void Ha_(Intent intent) {
        DeviceConnectingFragment deviceConnectingFragment = new DeviceConnectingFragment();
        deviceConnectingFragment.setArguments(intent.getExtras());
        a((Fragment) null, deviceConnectingFragment);
    }

    private void ah() {
        Fragment fragment = this.f2230a;
        if (fragment != null && "NfcOrQrCodeGuideFragment".equals(fragment.getClass().getSimpleName())) {
            LogUtil.a("DeviceMainActivity", "DeviceMainActivity onNewIntent switch");
            return;
        }
        NfcOrQrCodeGuideFragment nfcOrQrCodeGuideFragment = new NfcOrQrCodeGuideFragment();
        this.b.putString("ENTER_TYPE", getIntent().getStringExtra("ENTER_TYPE"));
        nfcOrQrCodeGuideFragment.setArguments(this.b);
        a((Fragment) null, nfcOrQrCodeGuideFragment);
    }

    private void al() {
        Fragment fragment = this.f2230a;
        if (fragment != null) {
            String simpleName = fragment.getClass().getSimpleName();
            LogUtil.a("DeviceMainActivity", "DeviceMainActivity fragment name is ", simpleName);
            if (!TextUtils.isEmpty(simpleName) && "RopeSkippingGuideFragment".equals(simpleName)) {
                LogUtil.a("DeviceMainActivity", "switchRopeSkippingGuideFragmentt onNewIntent switch");
                return;
            }
            LogUtil.h("DeviceMainActivity", "switchRopeSkippingGuideFragment other fragment");
        }
        RopeSkippingGuideFragment ropeSkippingGuideFragment = new RopeSkippingGuideFragment();
        ropeSkippingGuideFragment.setArguments(this.b);
        a((Fragment) null, ropeSkippingGuideFragment);
    }

    private void GY_(Intent intent, String str, HealthDevice.HealthDeviceKind healthDeviceKind) {
        final InputStream inputStream;
        if (intent != null) {
            String stringExtra = intent.getStringExtra("ZipPath");
            try {
                if (health.compact.a.CommonUtil.cg()) {
                    inputStream = GN_(intent.getData());
                    e(stringExtra, healthDeviceKind);
                } else {
                    inputStream = null;
                }
                if (stringExtra != null) {
                    LogUtil.a("DeviceMainActivity", "DeviceMainActivity zipPath is ", stringExtra);
                    ResourceManager.e().e(stringExtra, inputStream, new ResourceFileListener() { // from class: ckv
                        @Override // com.huawei.health.device.manager.ResourceFileListener
                        public final void onResult(int i, String str2) {
                            DeviceMainActivity.d(inputStream, i, str2);
                        }
                    });
                } else if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException unused) {
                        LogUtil.b("DeviceMainActivity", "toUnzipFromDemo IOException");
                    }
                }
                if ("me".equals(str)) {
                    a(healthDeviceKind, str);
                } else {
                    e(healthDeviceKind);
                }
            } catch (FileNotFoundException unused2) {
                LogUtil.b("DeviceMainActivity", "toUnzipFromDemo FileNotFoundException");
            }
        }
    }

    public static /* synthetic */ void d(InputStream inputStream, int i, String str) {
        LogUtil.a("DeviceMainActivity", "DeviceMainActivity resultCode is ", Integer.valueOf(i), " resultValue is ", str);
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException unused) {
                LogUtil.b("DeviceMainActivity", "toUnzipFromDemo IOException");
            }
        }
    }

    private void e(String str, HealthDevice.HealthDeviceKind healthDeviceKind) {
        if (TextUtils.isEmpty(str) || !healthDeviceKind.name().equals("HDK_UNKNOWN")) {
            return;
        }
        String substring = str.substring(str.lastIndexOf(File.separator) + 1);
        String substring2 = substring.substring(0, substring.lastIndexOf("."));
        SharedPreferenceManager.e(cpp.a(), String.valueOf(10000), "import_zip_product_id", substring2, new StorageParams());
    }

    private InputStream GN_(Uri uri) throws FileNotFoundException {
        return getContentResolver().openInputStream(uri);
    }

    private void GT_(Intent intent) {
        if (intent != null) {
            Fragment hagridWiFiInfoConfirmFragment = new HagridWiFiInfoConfirmFragment();
            ContentValues contentValues = new ContentValues();
            contentValues.put("uniqueId", this.aj);
            contentValues.put("productId", this.ai);
            Bundle extras = intent.getExtras();
            if (extras != null) {
                extras.putParcelable("commonDeviceInfo", contentValues);
                hagridWiFiInfoConfirmFragment.setArguments(extras);
            }
            a((Fragment) null, hagridWiFiInfoConfirmFragment);
        }
    }

    private void GV_(Intent intent) {
        if (intent != null) {
            HagridDeviceManagerFragment hagridDeviceManagerFragment = new HagridDeviceManagerFragment();
            intent.putExtra("isNfcConnect", this.p);
            LogUtil.a("DeviceMainActivity", "showDeviceManageView put isNfcConnect is ", Boolean.valueOf(this.p));
            hagridDeviceManagerFragment.setArguments(intent.getExtras());
            a((Fragment) null, hagridDeviceManagerFragment);
        }
    }

    private void GZ_(Intent intent) {
        if (intent != null) {
            Fragment fragment = (BaseFragment) a(HagridDeviceWlanUseGuideFragment.class);
            intent.putExtra("isNfcConnect", this.p);
            LogUtil.a("DeviceMainActivity", "showHagridDeviceWlanUseGuideView put isNfcConnect is ", Boolean.valueOf(this.p));
            if (!(fragment instanceof HagridDeviceWlanUseGuideFragment)) {
                fragment = new HagridDeviceWlanUseGuideFragment();
            }
            fragment.setArguments(intent.getExtras());
            a((Fragment) null, fragment);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void GS_(Intent intent) {
        char c;
        String action = intent.getAction();
        if (action == null || !action.equals("SWITCH_PLUGINDEVICE")) {
            return;
        }
        LogUtil.a("DeviceMainActivity", "DeviceMainActivity action is ", action);
        String stringExtra = intent.getStringExtra("arg1");
        if (stringExtra == null) {
            LogUtil.h("DeviceMainActivity", "DeviceMainActivity cloud message arg1 is null");
            return;
        }
        LogUtil.a("DeviceMainActivity", "DeviceMainActivity cloud message arg1: ", stringExtra);
        stringExtra.hashCode();
        switch (stringExtra.hashCode()) {
            case -1520650172:
                if (stringExtra.equals("DeviceInfo")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1520565196:
                if (stringExtra.equals("DeviceList")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -1241139792:
                if (stringExtra.equals("DeviceIntroduction")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -1138550014:
                if (stringExtra.equals("DeviceInfoList")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1478262634:
                if (stringExtra.equals("DeviceBindWaitingFragment")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1659929809:
                if (stringExtra.equals("syncDevice")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 1955964608:
                if (stringExtra.equals("ropeDeviceConnecting")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 2085684337:
                if (stringExtra.equals("DeviceBindWaitingUniversal")) {
                    c = 7;
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
                GQ_(intent);
                break;
            case 1:
                v();
                break;
            case 2:
                GU_(intent);
                break;
            case 3:
                GP_(intent);
                break;
            case 4:
                y();
                break;
            case 5:
                Hh_(intent);
                break;
            case 6:
                Ha_(intent);
                break;
            case 7:
                GR_(intent);
                break;
            default:
                LogUtil.h("DeviceMainActivity", "DeviceMainActivity cloud message arg1 is invalid, arg1: ", stringExtra);
                break;
        }
    }

    private void y() {
        if (this.ag != null) {
            LogUtil.h("DeviceMainActivity", "DeviceMainActivity is rebuild");
        } else if (koq.b(this.ak)) {
            x();
        } else {
            ac();
        }
    }

    private void v() {
        a((Fragment) null, new DeviceBindingFragment());
    }

    private void ac() {
        Intent intent = getIntent();
        if (intent == null) {
            o();
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("uuid_list", this.ak);
        bundle.putString("DeviceType", intent.getStringExtra("DeviceType"));
        bundle.putString("DeviceIconPath", intent.getStringExtra("DeviceIconPath"));
        bundle.putString("title", intent.getStringExtra("DeviceName"));
        bundle.putBoolean("isNfcConnect", this.p);
        LogUtil.a("DeviceMainActivity", "showWeightDeviceBindingWaitingView put isNfcConnect is ", Boolean.valueOf(this.p));
        ContentValues contentValues = new ContentValues();
        contentValues.put("uniqueId", this.aj);
        bundle.putParcelable("commonDeviceInfo", contentValues);
        bundle.putParcelable(AdShowExtras.DOWNLOAD_SOURCE, intent.getParcelableExtra(AdShowExtras.DOWNLOAD_SOURCE));
        bundle.putBoolean("is_go_rope_jump", this.s);
        bundle.putString("fittings_host_sn", intent.getStringExtra("fittings_host_sn"));
        dib.c().UV_(intent, bundle);
        Fragment deviceBindWaitingFragment = new DeviceBindWaitingFragment();
        deviceBindWaitingFragment.setArguments(bundle);
        a((Fragment) null, deviceBindWaitingFragment);
    }

    private void x() {
        Intent intent = getIntent();
        if (TextUtils.isEmpty(this.ai) || intent == null) {
            o();
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("productId", this.ai);
        bundle.putBoolean("is_go_rope_jump", this.s);
        bundle.putString("DeviceType", intent.getStringExtra("DeviceType"));
        bundle.putString("DeviceIconPath", intent.getStringExtra("DeviceIconPath"));
        bundle.putString("title", intent.getStringExtra("DeviceName"));
        bundle.putBoolean("isNfcConnect", this.p);
        LogUtil.a("DeviceMainActivity", "showDeviceBindingWaitingView put isNfcConnect is ", Boolean.valueOf(this.p));
        ContentValues contentValues = new ContentValues();
        contentValues.put("uniqueId", this.aj);
        contentValues.put("productId", this.ai);
        bundle.putBoolean("is_invalidation", intent.getBooleanExtra("is_invalidation", false));
        bundle.putParcelable("commonDeviceInfo", contentValues);
        bundle.putParcelable(AdShowExtras.DOWNLOAD_SOURCE, intent.getParcelableExtra(AdShowExtras.DOWNLOAD_SOURCE));
        bundle.putString("fittings_host_sn", intent.getStringExtra("fittings_host_sn"));
        dib.c().UV_(intent, bundle);
        Fragment deviceBindWaitingFragment = new DeviceBindWaitingFragment();
        deviceBindWaitingFragment.setArguments(bundle);
        if (intent.getBooleanExtra("isJumpFromFittings", false)) {
            a(new SecondRopeIntroductionFragment(), deviceBindWaitingFragment);
        } else {
            a((Fragment) null, deviceBindWaitingFragment);
        }
    }

    private void u() {
        dcr c = c(ResourceManager.e().a().e(), HealthDevice.HealthDeviceKind.HDK_WEIGHT.name());
        if (c != null) {
            e(c);
        } else {
            LogUtil.h("DeviceMainActivity", "WEIGHT ProductGroup is error");
        }
    }

    private void w() {
        Bundle bundle = this.b;
        if (bundle == null) {
            LogUtil.h("DeviceMainActivity", "showBindGuideView mBundle is null");
            o();
            return;
        }
        String string = bundle.getString("productId");
        dcz d2 = ResourceManager.e().d(string);
        if (d2 == null) {
            LogUtil.h("DeviceMainActivity", "showBindGuideView productInfo is null");
            u();
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("uniqueId", this.aj);
        contentValues.put("productId", this.ai);
        this.b.putParcelable("commonDeviceInfo", contentValues);
        this.b.putString("productId", this.ai);
        this.b.putBoolean("isFromIconnectDialog", true);
        this.b.putString("title", dcx.d(string, d2.n().b()));
        LogUtil.a("DeviceMainActivity", "showBindGuideView productId: ", string, ", mUniquidId: ", health.compact.a.CommonUtil.l(this.aj), ", mProductId: ", this.ai);
        HagridDeviceBindGuidFragment hagridDeviceBindGuidFragment = new HagridDeviceBindGuidFragment();
        hagridDeviceBindGuidFragment.setArguments(this.b);
        a((Fragment) null, hagridDeviceBindGuidFragment);
    }

    private void aa() {
        LogUtil.a("DeviceMainActivity", "enter startScaleBinding");
        Bundle bundle = this.b;
        if (bundle == null) {
            LogUtil.h("DeviceMainActivity", "mBundle is null");
            o();
            return;
        }
        String string = bundle.getString("productId");
        if (TextUtils.isEmpty(string)) {
            o();
            return;
        }
        if (ResourceManager.e().d(string) == null) {
            dks.e(this, "HDK_WEIGHT");
        } else if (!TextUtils.isEmpty(string) && cpa.ad(string)) {
            e(string, (String) null);
        }
        o();
    }

    private void He_(Intent intent) {
        Bundle bundle = this.b;
        if (bundle == null) {
            LogUtil.h("DeviceMainActivity", "mBundle is null");
            o();
            return;
        }
        String string = bundle.getString("productId");
        if (TextUtils.isEmpty(string)) {
            LogUtil.h("DeviceMainActivity", "startScaleBinding fail: product id is null");
            o();
            return;
        }
        if (ResourceManager.e().d(string) == null && intent != null) {
            String stringExtra = intent.getStringExtra("Device_Type");
            if (TextUtils.isEmpty(stringExtra)) {
                LogUtil.h("DeviceMainActivity", "startScaleBinding fail: product id is null");
                o();
                return;
            }
            dcr c = c(ResourceManager.e().a().e(), stringExtra);
            if (c != null) {
                e(c);
                return;
            } else {
                LogUtil.h("DeviceMainActivity", stringExtra, " ProductGroup is error");
                return;
            }
        }
        ProductIntroductionFragment productIntroductionFragment = new ProductIntroductionFragment();
        productIntroductionFragment.setArguments(this.b);
        a((Fragment) null, productIntroductionFragment);
    }

    private void e(dcr dcrVar) {
        LogUtil.a("DeviceMainActivity", "switchCategoryFragment item: ", dcrVar.toString());
        Bundle bundle = new Bundle();
        bundle.putBoolean(DeviceCategoryFragment.WIFI_UPDATE_BACK_FINISH, true);
        bundle.putString(DeviceCategoryFragment.DEVICE_KIND, getResources().getString(dcx.e(dcrVar.e())));
        bundle.putString(DeviceCategoryFragment.DEVICE_TYPE, dcrVar.c().name());
        DeviceCategoryFragment deviceCategoryFragment = new DeviceCategoryFragment();
        deviceCategoryFragment.setArguments(bundle);
        a((Fragment) null, deviceCategoryFragment);
    }

    private void GQ_(Intent intent) {
        String stringExtra = intent.getStringExtra("Device_Type");
        ArrayList<dcr> e = ResourceManager.e().a().e();
        if (e.size() <= 0) {
            if (!Utils.o()) {
                e = dch.a("groups.xml");
            } else if (health.compact.a.CommonUtil.cg()) {
                e = dch.a("groups.xml");
            } else {
                e = dch.a("groups_abroad.xml");
            }
        }
        dcr c = c(e, stringExtra);
        if (c != null) {
            LogUtil.a("DeviceMainActivity", "DeviceMainActivity item: ", c.toString());
            DeviceCategoryFragment deviceCategoryFragment = new DeviceCategoryFragment();
            Bundle bundle = new Bundle();
            bundle.putString(DeviceCategoryFragment.DEVICE_TYPE, c.c().name());
            bundle.putString(DeviceCategoryFragment.DEVICE_KIND, getResources().getString(dcx.e(c.e())));
            deviceCategoryFragment.setArguments(bundle);
            a((Fragment) null, deviceCategoryFragment);
        }
    }

    private void GR_(Intent intent) {
        String stringExtra = intent.getStringExtra("arg2");
        String stringExtra2 = intent.getStringExtra("arg3");
        if (stringExtra == null) {
            LogUtil.h("DeviceMainActivity", "DeviceMainActivity cloud message arg2 is null");
            return;
        }
        LogUtil.a("DeviceMainActivity", "DeviceMainActivity cloud message arg2: ", stringExtra);
        Bundle bundle = new Bundle();
        bundle.putString("productId", stringExtra);
        ContentValues contentValues = new ContentValues();
        contentValues.put("uniqueId", stringExtra2);
        contentValues.put("productId", stringExtra);
        bundle.putParcelable("commonDeviceInfo", contentValues);
        Fragment deviceBindWaitingUniversalFragment = new DeviceBindWaitingUniversalFragment();
        deviceBindWaitingUniversalFragment.setArguments(bundle);
        a((Fragment) null, deviceBindWaitingUniversalFragment);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void GP_(Intent intent) {
        LogUtil.a("DeviceMainActivity", "showChooseDeviceBindingListView");
        String stringExtra = intent.getStringExtra("productId");
        dcz d2 = ResourceManager.e().d(stringExtra);
        if (d2 == null) {
            GL_(intent, stringExtra);
            return;
        }
        if (intent.getBooleanExtra("thirdDeviceToApp", false)) {
            h(getString(R.string._2130841415_res_0x7f020f47));
            GL_(intent, this.ai);
            GprsDeviceHelper.c(this.ai, this.aj, new GprsDeviceHelper.CheckAndBindCallback() { // from class: com.huawei.health.device.ui.DeviceMainActivity.5
                @Override // com.huawei.health.ecologydevice.manager.GprsDeviceHelper.CheckAndBindCallback
                public void onBindFinish() {
                    DeviceMainActivity.this.z();
                }

                @Override // com.huawei.health.ecologydevice.manager.GprsDeviceHelper.CheckAndBindCallback
                public void onBindException() {
                    nrh.e(DeviceMainActivity.this, R.string.IDS_device_binding_fail);
                    DeviceMainActivity.this.o();
                }
            });
            return;
        }
        if ("1".equals(d2.j()) || BleConstants.BLE_THIRD_DEVICE_H5.equals(d2.m().d())) {
            if (!PermissionDialogHelper.Vy_(this)) {
                ReleaseLogUtil.e("R_Weight_DeviceMainActivity", "showChooseDeviceBindingListView go to H5 not scan permission");
                nrh.b(this, R.string._2130846464_res_0x7f022300);
                o();
                return;
            }
            if ("1".equals(d2.j())) {
                dks.d(this, d2, this.ai, this.aj);
            } else {
                String stringExtra2 = intent.getStringExtra("operateCode");
                Intent Wx_ = dks.Wx_(d2, this.ai, this.aj);
                if (Wx_ != null) {
                    Wx_.putExtra("operateCode", stringExtra2);
                    startActivity(Wx_);
                }
            }
            o();
            return;
        }
        GL_(intent, stringExtra);
    }

    private void GL_(Intent intent, String str) {
        Fragment createProductFragment;
        Bundle bundle = new Bundle();
        if (cpa.ae(str)) {
            intent.putExtra("isNfcConnect", this.p);
            LogUtil.a("DeviceMainActivity", "showChooseDeviceBindingListView isHuaweiWspScaleProduct put isNfcConnect is ", Boolean.valueOf(this.p));
            createProductFragment = new HagridDeviceManagerFragment();
        } else if (cpa.ac(str)) {
            createProductFragment = new HonourDeviceFragment();
        } else if (cue.a(str)) {
            if (TextUtils.isEmpty(this.aj)) {
                this.aj = GB_(this.j, this.x, str).getAsString("uniqueId");
            }
            createProductFragment = new WiFiProductIntroductionFragment();
            String stringExtra = intent.getStringExtra("arg4");
            if (!TextUtils.isEmpty(stringExtra)) {
                bundle.putString("arg4", stringExtra);
            }
        } else {
            createProductFragment = ProductCreateFactory.createProductFragment(str);
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("uniqueId", this.aj);
        contentValues.put("productId", this.ai);
        bundle.putParcelable("commonDeviceInfo", contentValues);
        bundle.putString("productId", this.ai);
        bundle.putBoolean(BaseRopeIntroductionFragment.KEY_FROM_SCAN, true);
        bundle.putString("operateCode", intent.getStringExtra("operateCode"));
        if (createProductFragment instanceof ProductIntroductionFragment) {
            bundle.putBoolean("isBindSuccess", intent.getBooleanExtra("isBindSuccess", false));
        }
        createProductFragment.setArguments(bundle);
        a((Fragment) null, createProductFragment);
        if (dks.b(this) && intent.getBooleanExtra("isBindSuccess", false)) {
            dks.e();
        }
    }

    private ContentValues GB_(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            return new ContentValues();
        }
        ArrayList<ContentValues> a2 = ceo.d().a(str3);
        LogUtil.a("DeviceMainActivity", "getDeviceInfoByWiseDeivceIdOrSubMac deviceInfos size is ", Integer.valueOf(a2.size()));
        if (koq.b(a2)) {
            return new ContentValues();
        }
        if (a2.size() == 1) {
            return a2.get(0);
        }
        Iterator<ContentValues> it = a2.iterator();
        while (it.hasNext()) {
            ContentValues next = it.next();
            String asString = next.getAsString("mDeviceId");
            if (!TextUtils.isEmpty(asString) && asString.equals(str)) {
                LogUtil.a("DeviceMainActivity", "mDeviceId pass");
                return next;
            }
            String asString2 = next.getAsString("uniqueId");
            if (!TextUtils.isEmpty(asString2) && asString2.equals(str)) {
                LogUtil.a("DeviceMainActivity", "the device has bound on the cloud && no bound by Ble");
                return next;
            }
            if (!TextUtils.isEmpty(asString2) && asString2.replace(":", "").endsWith(str2)) {
                LogUtil.a("DeviceMainActivity", "uniqueId pass");
                return next;
            }
        }
        return new ContentValues();
    }

    private dcr c(ArrayList<dcr> arrayList, String str) {
        Iterator<dcr> it = arrayList.iterator();
        dcr dcrVar = null;
        while (it.hasNext()) {
            dcr next = it.next();
            LogUtil.a("DeviceMainActivity", "DeviceMainActivity item.kind.name(): ", next.c().name(), " device_Type: ", str);
            if (next.c().name().equals(str)) {
                dcrVar = next;
            }
        }
        return dcrVar;
    }

    private void GU_(Intent intent) {
        Fragment honourDeviceFragment;
        String stringExtra = intent.getStringExtra("arg2");
        boolean booleanExtra = intent.getBooleanExtra("isBindSuccess", false);
        if (stringExtra == null) {
            LogUtil.h("DeviceMainActivity", "DeviceMainActivity cloud message arg2 is null");
            return;
        }
        LogUtil.a("DeviceMainActivity", "DeviceMainActivity cloud message arg2: ", stringExtra);
        String stringExtra2 = intent.getStringExtra("sn");
        Bundle bundle = new Bundle();
        bundle.putString("sn", stringExtra2);
        LogUtil.a("DeviceMainActivity", "showDeviceIntroductionFragment serialNumber:", cpw.d(stringExtra2));
        bundle.putString("productId", stringExtra);
        if (cpa.y(stringExtra) || cpa.af(stringExtra)) {
            if (cpa.ae(stringExtra)) {
                intent.putExtra("isNfcConnect", this.p);
                LogUtil.a("DeviceMainActivity", "showDeviceIntroductionFragment isHonorAmBondedDevice put isNfcConnect: ", Boolean.valueOf(this.p));
                honourDeviceFragment = new HagridDeviceManagerFragment();
            } else {
                honourDeviceFragment = new HonourDeviceFragment();
            }
        } else if (cue.a(stringExtra)) {
            honourDeviceFragment = new WiFiProductIntroductionFragment();
        } else {
            honourDeviceFragment = new ProductIntroductionFragment();
        }
        String stringExtra3 = intent.getStringExtra("arg3");
        ContentValues contentValues = new ContentValues();
        contentValues.put("uniqueId", stringExtra3);
        contentValues.put("productId", stringExtra);
        bundle.putParcelable("commonDeviceInfo", contentValues);
        if (cpa.aa(stringExtra)) {
            LogUtil.a("DeviceMainActivity", "showDeviceIntroductionFragment isHonourScale bindSuccess, ", Boolean.valueOf(booleanExtra));
            bundle.putBoolean("isBindSuccess", booleanExtra);
        }
        honourDeviceFragment.setArguments(bundle);
        a((Fragment) null, honourDeviceFragment);
    }

    private void GO_(HealthDevice.HealthDeviceKind healthDeviceKind, Intent intent) {
        if (intent != null) {
            Bundle bundle = new Bundle();
            bundle.putString("kind", String.valueOf(healthDeviceKind));
            bundle.putSerializable("HealthData", intent.getSerializableExtra("HealthData"));
            bundle.putString("productId", intent.getStringExtra("productId"));
            ContentValues contentValues = new ContentValues();
            contentValues.put("uniqueId", this.aj);
            contentValues.put("productId", this.ai);
            bundle.putParcelable("commonDeviceInfo", contentValues);
            Fragment weightAutoMeasureFragment = new WeightAutoMeasureFragment();
            weightAutoMeasureFragment.setArguments(bundle);
            a((Fragment) null, weightAutoMeasureFragment);
        }
    }

    private void Hc_(Intent intent) {
        Fragment wiFiProductUpgradeFragment;
        if (intent != null) {
            Bundle extras = intent.getExtras();
            if (cpl.Kh_(extras)) {
                LogUtil.a("DeviceMainActivity", "showWifiUpgradeFragment is dual_mode device");
                wiFiProductUpgradeFragment = new HagridWifiProductUpgradeFragment();
            } else {
                LogUtil.a("DeviceMainActivity", "showWifiUpgradeFragment is other Device");
                wiFiProductUpgradeFragment = new WiFiProductUpgradeFragment();
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("productId", this.ai);
            contentValues.put("uniqueId", this.aj);
            extras.putParcelable("commonDeviceInfo", contentValues);
            LogUtil.a("DeviceMainActivity", "showWifiUpgradeFragment mUniqueId: ", health.compact.a.CommonUtil.l(this.aj), ", mProductId: ", this.ai);
            wiFiProductUpgradeFragment.setArguments(extras);
            a((Fragment) null, wiFiProductUpgradeFragment);
        }
    }

    public void c(HealthDevice.HealthDeviceKind healthDeviceKind, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putString(DeviceCategoryFragment.DEVICE_TYPE, String.valueOf(healthDeviceKind));
        bundle.putBoolean("isRebind", z);
        ContentValues contentValues = new ContentValues();
        contentValues.put("productId", this.ai);
        contentValues.put("uniqueId", this.aj);
        bundle.putParcelable("commonDeviceInfo", contentValues);
        if (ResourceManager.e().a().c(healthDeviceKind) != null) {
            LogUtil.a("DeviceMainActivity", "DeviceMainActivity showDeviceBondView is not null");
            dcr c = ResourceManager.e().a().c(healthDeviceKind);
            if (c != null) {
                bundle.putString(DeviceCategoryFragment.DEVICE_KIND, getResources().getString(dcx.e(c.e())));
            }
        }
        Fragment deviceCategoryFragment = new DeviceCategoryFragment();
        deviceCategoryFragment.setArguments(bundle);
        a((Fragment) null, deviceCategoryFragment);
    }

    private void Hb_(Intent intent) {
        if (this.ag != null) {
            LogUtil.h("DeviceMainActivity", "showWiFiConfirm mSavedInstanceState is not null, return");
            return;
        }
        if (intent != null) {
            Bundle extras = intent.getExtras();
            if (extras == null) {
                LogUtil.h("DeviceMainActivity", "showWiFiConfirm bundle is null");
                return;
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("uniqueId", this.aj);
            contentValues.put("productId", this.ai);
            extras.putParcelable("commonDeviceInfo", contentValues);
            Fragment hagridDeviceBindResultFragment = new HagridDeviceBindResultFragment();
            hagridDeviceBindResultFragment.setArguments(extras);
            a((Fragment) null, hagridDeviceBindResultFragment);
            LogUtil.a("DeviceMainActivity", "showWiFiConfirm is end");
        }
    }

    private void ad() {
        MyDeviceFragment myDeviceFragment = new MyDeviceFragment();
        Bundle bundle = new Bundle();
        bundle.putString("kind", HealthDevice.HealthDeviceKind.HDK_WEIGHT.name());
        bundle.putString("EntryType", "WiFiDevice");
        myDeviceFragment.setArguments(bundle);
        a((Fragment) null, myDeviceFragment);
    }

    private void b(HealthDevice.HealthDeviceKind healthDeviceKind) {
        MyDeviceFragment myDeviceFragment = new MyDeviceFragment();
        Bundle bundle = new Bundle();
        bundle.putString("kind", healthDeviceKind.name());
        bundle.putString("EntryType", "Pick");
        myDeviceFragment.setArguments(bundle);
        a((Fragment) null, myDeviceFragment);
    }

    private void d(String str, String str2) {
        if ("true".equals(gmz.d().c(402))) {
            dks.n(str);
            c(str, str2);
        } else {
            dks.WC_(this, str, str2, true);
        }
    }

    private void c(String str, String str2) {
        if (!PermissionDialogHelper.Vy_(this)) {
            ReleaseLogUtil.e("R_Weight_DeviceMainActivity", "startWebViewMeasure go to H5 not scan permission");
            nrh.b(this, R.string._2130846464_res_0x7f022300);
            o();
            return;
        }
        dks.n(str);
        dcz d2 = ResourceManager.e().d(str);
        if (d2 == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setPackage(BaseApplication.d());
        intent.setClassName(BaseApplication.d(), "com.huawei.operation.activity.WebViewActivity");
        if (!TextUtils.isEmpty(d2.r()) && !TextUtils.isEmpty(d2.b())) {
            intent.putExtra("url", dcq.b().c(str) + "#/type=2/sn=" + str2);
        } else {
            intent.putExtra("url", dcq.b().c(str));
        }
        intent.putExtra("productId", str);
        ContentValues contentValues = new ContentValues();
        contentValues.put("uniqueId", str2);
        contentValues.put("name", dks.e(str, d2.n().b()));
        contentValues.put("deviceType", d2.l().name());
        contentValues.put(Constants.KEY_BLE_SCAN_MODE, Integer.valueOf(d2.x().c()));
        intent.putExtra("commonDeviceInfo", contentValues);
        intent.putExtra("bleIntroductionType", d2.m().d());
        if (!TextUtils.isEmpty(WebViewActivity.getProductId())) {
            LogUtil.a("DeviceMainActivity", "WebViewActivity productId is not empty");
            intent.setFlags(335544320);
        }
        startActivity(intent);
        o();
    }

    private void GW_(HealthDevice.HealthDeviceKind healthDeviceKind, Intent intent) {
        ArrayList<ContentValues> d2 = cjx.e().d(healthDeviceKind);
        c((Class<?>) null);
        LogUtil.a("DeviceMainActivity", "showDeviceMeasureView mType: ", Integer.valueOf(this.am));
        if (d2.size() == 1) {
            String asString = d2.get(0).getAsString("productId");
            String asString2 = d2.get(0).getAsString("uniqueId");
            dcz d3 = ResourceManager.e().d(asString);
            if ("1".equals(d3.j())) {
                if (PermissionDialogHelper.Vy_(this)) {
                    dks.d(this, d3, asString, asString2);
                } else {
                    ReleaseLogUtil.e("R_Weight_DeviceMainActivity", "startWebViewMeasure go to H5 not scan permission");
                    nrh.b(this, R.string._2130846464_res_0x7f022300);
                }
                o();
                return;
            }
            Fragment a2 = ckq.a(asString);
            if (a2 != null) {
                Bundle bundle = new Bundle();
                bundle.putString("view", "measure");
                bundle.putString("productId", asString);
                ContentValues contentValues = new ContentValues();
                contentValues.put("uniqueId", asString2);
                contentValues.put("productId", asString);
                bundle.putParcelable("commonDeviceInfo", contentValues);
                bundle.putInt("type", this.am);
                bundle.putString("kind", healthDeviceKind.name());
                bundle.putParcelable("commonDeviceInfo", d2.get(0));
                a2.setArguments(bundle);
                a((Fragment) null, a2);
                return;
            }
            if (d3 != null && "H5".equals(d3.b()) && "H5".equals(d3.r())) {
                d(asString, asString2);
                return;
            }
            if (d3 != null && "H5".equals(d3.r())) {
                c(asString, asString2);
                return;
            }
            if (cjx.e().h(asString)) {
                b(cjx.e().d(asString), asString, healthDeviceKind);
                return;
            }
            com.huawei.health.device.model.HealthDevice a3 = cjx.e().a(asString);
            if (a3 instanceof MeasurableDevice) {
                c((MeasurableDevice) a3, asString, healthDeviceKind);
                return;
            }
            return;
        }
        if (d2.size() > 1) {
            GF_(healthDeviceKind, intent, d2);
        } else {
            LogUtil.h("DeviceMainActivity", "product size less than 1");
        }
    }

    private void GF_(HealthDevice.HealthDeviceKind healthDeviceKind, Intent intent, ArrayList<ContentValues> arrayList) {
        ContentValues GA_;
        if (intent == null) {
            LogUtil.h("DeviceMainActivity", "hasBindMoreDevices intent is null");
            return;
        }
        boolean booleanExtra = intent.getBooleanExtra("isFromIconnectDialog", false);
        String stringExtra = intent.getStringExtra("macAddress");
        LogUtil.a("DeviceMainActivity", "bondedDevices.size() > 1 isAccurateMeasure: ", Boolean.valueOf(booleanExtra));
        if (booleanExtra && !TextUtils.isEmpty(stringExtra)) {
            LogUtil.a("DeviceMainActivity", "isAccurateMeasure start target measure fragment macAddress: ", health.compact.a.CommonUtil.l(stringExtra));
            GA_ = GC_(arrayList, stringExtra);
        } else {
            GA_ = GA_(arrayList);
        }
        if (GA_ != null) {
            LogUtil.a("DeviceMainActivity", "deviceInfo != null, go to target measure fragment");
            Hf_(healthDeviceKind, GA_);
        } else {
            d(healthDeviceKind);
        }
    }

    private ContentValues GC_(ArrayList<ContentValues> arrayList, String str) {
        if (koq.b(arrayList) || TextUtils.isEmpty(str)) {
            return null;
        }
        Iterator<ContentValues> it = arrayList.iterator();
        while (it.hasNext()) {
            ContentValues next = it.next();
            if (next != null && TextUtils.equals(str, next.getAsString("uniqueId"))) {
                return next;
            }
        }
        return null;
    }

    private ContentValues GA_(ArrayList<ContentValues> arrayList) {
        if (koq.b(arrayList)) {
            return null;
        }
        String l = l();
        if (TextUtils.isEmpty(l)) {
            return null;
        }
        Iterator<ContentValues> it = arrayList.iterator();
        while (it.hasNext()) {
            ContentValues next = it.next();
            if (next != null && TextUtils.equals(l, next.getAsString("uniqueId"))) {
                return next;
            }
        }
        return null;
    }

    private String l() {
        String j = cgt.e().j();
        LogUtil.a("DeviceMainActivity", "getCurrentConnectedDevice connectDeviceAddress: ", cpw.d(j));
        return !TextUtils.isEmpty(j) ? j : "";
    }

    private void d(HealthDevice.HealthDeviceKind healthDeviceKind) {
        MyDeviceFragment myDeviceFragment = new MyDeviceFragment();
        Bundle bundle = new Bundle();
        bundle.putString("kind", healthDeviceKind.name());
        bundle.putString("EntryType", "Measure");
        myDeviceFragment.setArguments(bundle);
        a((Fragment) null, myDeviceFragment);
    }

    private void b(com.huawei.hihealth.device.open.HealthDevice healthDevice, String str, HealthDevice.HealthDeviceKind healthDeviceKind) {
        dcz d2 = ResourceManager.e().d(str);
        if (d2 == null) {
            LogUtil.h("DeviceMainActivity", "chooseFragment productInfo is null");
            return;
        }
        boolean equals = HiAnalyticsConstant.KeyAndValue.NUMBER_01.equals(d2.p());
        if (healthDevice == null) {
            LogUtil.h("DeviceMainActivity", "the device is null");
            return;
        }
        if (equals) {
            LogUtil.c("DeviceMainActivity", "DeviceMainActivity the productId ", str, " is not res, and this is auto");
            Bundle Gz_ = Gz_(str);
            ContentValues contentValues = new ContentValues();
            contentValues.put("uniqueId", healthDevice.getUniqueId());
            contentValues.put("productId", str);
            Gz_.putParcelable("commonDeviceInfo", contentValues);
            Fragment deviceSilentGuideFragment = new DeviceSilentGuideFragment();
            deviceSilentGuideFragment.setArguments(Gz_);
            a((Fragment) null, deviceSilentGuideFragment);
            return;
        }
        LogUtil.c("DeviceMainActivity", " DeviceMainActivity the productId ", str, " is not res, and this is not auto ", " kind.name is ", healthDeviceKind.name());
        b(healthDeviceKind, str);
    }

    private void c(MeasurableDevice measurableDevice, String str, HealthDevice.HealthDeviceKind healthDeviceKind) {
        dcz d2 = ResourceManager.e().d(str);
        String p = d2 != null ? d2.p() : null;
        String r = d2 != null ? d2.r() : null;
        boolean equals = HiAnalyticsConstant.KeyAndValue.NUMBER_01.equals(p);
        if (measurableDevice == null) {
            LogUtil.h("DeviceMainActivity", "the device is null");
            return;
        }
        if (measurableDevice.isAutoDevice() && equals) {
            LogUtil.c("DeviceMainActivity", "DeviceMainActivity the productId: ", str, " is not res, and this is auto");
            a(measurableDevice, str);
            return;
        }
        LogUtil.c("DeviceMainActivity", "DeviceMainActivity the productId: ", str, " is not res, and this is not auto, kind.name is ", healthDeviceKind.name());
        BaseFragment a2 = ckq.a(healthDeviceKind.name());
        if (a2 != null) {
            b(a2, healthDeviceKind.name(), measurableDevice, str);
        } else if ("H5".equals(r)) {
            j(str);
            o();
        } else {
            LogUtil.h("DeviceMainActivity", " DeviceMainActivity no Fragment");
        }
    }

    private void b(HealthDevice.HealthDeviceKind healthDeviceKind, String str) {
        BaseFragment a2 = ckq.a(healthDeviceKind.name());
        if (a2 != null) {
            Bundle bundle = new Bundle();
            bundle.putString("view", "measure");
            bundle.putString("kind", healthDeviceKind.name());
            bundle.putString("productId", str);
            bundle.putInt("type", this.am);
            a2.setArguments(bundle);
            a((Fragment) null, a2);
        }
    }

    private void Hf_(HealthDevice.HealthDeviceKind healthDeviceKind, ContentValues contentValues) {
        if (contentValues == null) {
            LogUtil.h("DeviceMainActivity", "startMeasureBaseFragment deviceInfo is null");
            return;
        }
        LogUtil.a("DeviceMainActivity", "startMeasureBaseFragment uniqueId: ", contentValues.getAsString("uniqueId"));
        BaseFragment a2 = ckq.a(healthDeviceKind.name());
        if (a2 != null) {
            Bundle bundle = new Bundle();
            bundle.putString("view", "measure");
            bundle.putString("kind", healthDeviceKind.name());
            bundle.putParcelable("commonDeviceInfo", contentValues);
            bundle.putInt("type", this.am);
            a2.setArguments(bundle);
            a((Fragment) null, a2);
        }
    }

    private void a(MeasurableDevice measurableDevice, String str) {
        Bundle Gz_ = Gz_(str);
        DeviceSilentGuideFragment deviceSilentGuideFragment = new DeviceSilentGuideFragment();
        GM_(measurableDevice, str, Gz_);
        deviceSilentGuideFragment.setArguments(Gz_);
        a((Fragment) null, deviceSilentGuideFragment);
    }

    private void b(BaseFragment baseFragment, String str, MeasurableDevice measurableDevice, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString("view", "measure");
        bundle.putString("kind", str);
        bundle.putString("productId", str2);
        bundle.putInt("type", this.am);
        GM_(measurableDevice, str2, bundle);
        baseFragment.setArguments(bundle);
        a((Fragment) null, baseFragment);
    }

    private void j(String str) {
        Intent intent = new Intent();
        intent.setPackage(BaseApplication.d());
        intent.setClassName(BaseApplication.d(), "com.huawei.operation.activity.WebViewActivity");
        intent.putExtra("url", dcq.b().c(str));
        intent.putExtra("productId", str);
        startActivity(intent);
    }

    private Bundle Gz_(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("view", "bond");
        bundle.putString("productId", str);
        bundle.putInt("type", this.am);
        return bundle;
    }

    private void GM_(MeasurableDevice measurableDevice, String str, Bundle bundle) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("uniqueId", measurableDevice.getUniqueId());
        contentValues.put("productId", str);
        bundle.putParcelable("commonDeviceInfo", contentValues);
    }

    public void c(Fragment fragment) {
        this.f2230a = fragment;
    }

    public Fragment a() {
        return this.f2230a;
    }

    private void a(HealthDevice.HealthDeviceKind healthDeviceKind, String str) {
        LogUtil.a("DeviceMainActivity", "DeviceMainActivity showDeviceListView kind ", healthDeviceKind.name());
        Bundle bundle = new Bundle();
        bundle.putString("kind", healthDeviceKind.name());
        bundle.putString("EntryType", "List");
        bundle.putString("root_in_me", str);
        MyDeviceFragment myDeviceFragment = new MyDeviceFragment();
        myDeviceFragment.setArguments(bundle);
        a((Fragment) null, myDeviceFragment);
    }

    private void e(HealthDevice.HealthDeviceKind healthDeviceKind) {
        LogUtil.a("DeviceMainActivity", "DeviceMainActivity showDeviceListView kind ", healthDeviceKind.name());
        Bundle bundle = new Bundle();
        bundle.putString("kind", healthDeviceKind.name());
        bundle.putString("EntryType", "List");
        Intent intent = getIntent();
        if (intent != null) {
            try {
                bundle.putBoolean("isFromFitnessAdvice", intent.getBooleanExtra("isFromFitnessAdvice", false));
                bundle.putStringArrayList("notHeartRateDeviceList", intent.getStringArrayListExtra("notHeartRateDeviceList"));
            } catch (Exception unused) {
                LogUtil.b("DeviceMainActivity", "showDeviceListView Exception");
            }
        }
        MyDeviceFragment myDeviceFragment = new MyDeviceFragment();
        myDeviceFragment.setArguments(bundle);
        a((Fragment) null, myDeviceFragment);
    }

    public void b(Class<?> cls) {
        if (cls != null) {
            FragmentManager supportFragmentManager = getSupportFragmentManager();
            if (d(cls)) {
                this.e = cls;
                LogUtil.a("DeviceMainActivity", "DeviceMainActivity mClass is ", cls.getSimpleName());
                try {
                    supportFragmentManager.popBackStack(cls.getSimpleName(), 1);
                    return;
                } catch (IllegalStateException e) {
                    LogUtil.b("DeviceMainActivity", "DeviceMainActivity throws ", e.getMessage());
                    return;
                }
            }
            LogUtil.h("DeviceMainActivity", "DeviceMainActivity fragmentClass is not Exists InBackStack");
            o();
            return;
        }
        LogUtil.h("DeviceMainActivity", "DeviceMainActivity fragmentClass is null");
        o();
    }

    private boolean d(Class<?> cls) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        int backStackEntryCount = supportFragmentManager.getBackStackEntryCount();
        LogUtil.c("DeviceMainActivity", "DeviceMainActivity fragmentClass is ", cls.getSimpleName());
        boolean z = false;
        for (int i = 0; i < backStackEntryCount; i++) {
            FragmentManager.BackStackEntry backStackEntryAt = supportFragmentManager.getBackStackEntryAt(i);
            LogUtil.c("DeviceMainActivity", "DeviceMainActivity backStackEntry is ", backStackEntryAt.getName());
            if (backStackEntryAt.getName().equals(cls.getSimpleName())) {
                z = true;
            }
        }
        return z;
    }

    public Fragment a(Class<?> cls) {
        if (cls == null) {
            LogUtil.a("DeviceMainActivity", "getSelectFragment fragmentClass is null");
            return null;
        }
        return getSupportFragmentManager().findFragmentByTag(cls.getSimpleName());
    }

    public void a(Fragment fragment, Fragment fragment2) {
        if (fragment2 == null) {
            LogUtil.a("DeviceMainActivity", "switchFragment target fragment is null");
            o();
            return;
        }
        ReleaseLogUtil.e("R_Weight_DeviceMainActivity", "switchFragment target fragment is: ", fragment2.getClass().getSimpleName());
        Bundle arguments = fragment2.getArguments();
        boolean z = (arguments == null || arguments.getInt("scanMode") == 4) ? false : true;
        if (((fragment2 instanceof DeviceScanningFragment) || (fragment2 instanceof HagridDeviceBindGuidFragment)) && z) {
            this.ad = fragment;
            this.aa = fragment2;
            if (!p()) {
                return;
            }
        }
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        if (!this.o && !this.q) {
            if (LanguageUtil.bc(this)) {
                beginTransaction.setCustomAnimations(R.anim._2130772077_res_0x7f01006d, R.anim._2130772079_res_0x7f01006f, R.anim._2130772057_res_0x7f010059, R.anim._2130772061_res_0x7f01005d);
            } else {
                beginTransaction.setCustomAnimations(R.anim._2130772057_res_0x7f010059, R.anim._2130772061_res_0x7f01005d, R.anim._2130772077_res_0x7f01006d, R.anim._2130772079_res_0x7f01006f);
            }
        }
        this.o = false;
        if (fragment != null) {
            ReleaseLogUtil.e("R_Weight_DeviceMainActivity", "switchFragment fromFragment is: ", fragment.getClass().getSimpleName());
            if (!fragment2.isAdded()) {
                beginTransaction.addToBackStack(fragment.getClass().getSimpleName());
            }
        } else {
            ReleaseLogUtil.e("R_Weight_DeviceMainActivity", "switchFragment from fragment is null");
        }
        if (fragment2.isAdded()) {
            beginTransaction.show(fragment2);
        } else {
            beginTransaction.replace(R.id.device_fragment_container, fragment2, fragment2.getClass().getSimpleName());
        }
        this.e = fragment2.getClass();
        beginTransaction.commitAllowingStateLoss();
    }

    public void b(Fragment fragment, Fragment fragment2) {
        if (fragment2 == null) {
            LogUtil.a("DeviceMainActivity", "addFragment target fragment is null");
            o();
            return;
        }
        LogUtil.a("DeviceMainActivity", "addFragment target fragment is: ", fragment2.getClass().getSimpleName());
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
        if (!this.o && !this.q) {
            if (LanguageUtil.bc(this)) {
                beginTransaction.setCustomAnimations(R.anim._2130772077_res_0x7f01006d, R.anim._2130772079_res_0x7f01006f, R.anim._2130772057_res_0x7f010059, R.anim._2130772061_res_0x7f01005d);
            } else {
                beginTransaction.setCustomAnimations(R.anim._2130772057_res_0x7f010059, R.anim._2130772061_res_0x7f01005d, R.anim._2130772077_res_0x7f01006d, R.anim._2130772079_res_0x7f01006f);
            }
        }
        this.o = false;
        if (fragment != null) {
            LogUtil.a("DeviceMainActivity", "addFragment fromFragment is: ", fragment.getClass().getSimpleName());
            if (!fragment2.isAdded()) {
                beginTransaction.addToBackStack(fragment.getClass().getSimpleName());
            }
            beginTransaction.hide(fragment);
        } else {
            LogUtil.a("DeviceMainActivity", "addFragment from fragment is null");
        }
        Fragment findFragmentByTag = supportFragmentManager.findFragmentByTag(fragment2.getClass().getSimpleName());
        if (!fragment2.isAdded() && findFragmentByTag == null) {
            beginTransaction.add(R.id.device_fragment_container, fragment2, fragment2.getClass().getSimpleName());
        } else {
            beginTransaction.show(fragment2);
        }
        this.e = fragment2.getClass();
        beginTransaction.commitAllowingStateLoss();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        Fragment fragment;
        if (iArr != null && strArr != null) {
            jeg.d().d(strArr, iArr);
        }
        if (i == 1 && iArr != null && iArr.length > 0 && iArr[0] == 0 && (fragment = this.aa) != null) {
            a(this.ad, fragment);
        }
        if (i == 20192) {
            if (iArr != null && iArr.length > 0 && strArr != null) {
                c(strArr, i, iArr);
            } else {
                LogUtil.h("DeviceMainActivity", "grantResults is null");
            }
        }
    }

    private void c(String[] strArr, int i, int[] iArr) {
        for (String str : strArr) {
            if ("android.permission.RECORD_AUDIO".equals(str) || "android.permission.MODIFY_AUDIO_SETTINGS".equals(str) || "android.permission.PROCESS_OUTGOING_CALLS".equals(str)) {
                Fragment fragment = this.f2230a;
                if (fragment != null) {
                    fragment.onRequestPermissionsResult(i, strArr, iArr);
                    return;
                }
                return;
            }
        }
    }

    private boolean p() {
        if (checkSelfPermission("android.permission.ACCESS_COARSE_LOCATION") == 0 && checkSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0) {
            return true;
        }
        if (shouldShowRequestPermissionRationale("android.permission.ACCESS_COARSE_LOCATION") || health.compact.a.CommonUtil.a(this, "android.permission.ACCESS_COARSE_LOCATION")) {
            requestPermissions(new String[]{"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"}, 1);
            health.compact.a.CommonUtil.k(this, "android.permission.ACCESS_COARSE_LOCATION");
            return false;
        }
        nsn.a((Context) this, true);
        return false;
    }

    public ArrayList<com.huawei.hihealth.device.open.HealthDevice> d() {
        return this.i;
    }

    public void a(final String str) {
        LogUtil.a("DeviceMainActivity", "showSelectBindDeviceDialog productId: ", str);
        Fragment fragment = this.f2230a;
        if (fragment == null || fragment.getContext() == null) {
            LogUtil.h("DeviceMainActivity", "DeviceMainActivity currentFragment context is null");
            return;
        }
        if (this.d == null) {
            cpl.e(this.aj);
            NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this.f2230a.getContext());
            builder.e(R.string.IDS_device_bluetooth_rebind_msg_new).czC_(R$string.IDS_btsdk_confirm_repair, new View.OnClickListener() { // from class: cko
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DeviceMainActivity.this.Hj_(str, view);
                }
            }).czz_(R$string.IDS_settings_button_cancal, new View.OnClickListener() { // from class: cks
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DeviceMainActivity.this.Hk_(view);
                }
            });
            this.d = builder.e();
        }
        if (this.d.isShowing()) {
            return;
        }
        this.d.show();
    }

    public /* synthetic */ void Hj_(String str, View view) {
        Fragment fragment = this.f2230a;
        if (fragment instanceof HagridDeviceManagerFragment) {
            cnb.a(fragment.getContext(), this.ai, this.aj);
            ViewClickInstrumentation.clickOnView(view);
        } else if (!TextUtils.isEmpty(str) && cpa.ad(str)) {
            e(str, this.aj);
            ViewClickInstrumentation.clickOnView(view);
        } else {
            c(dks.c("HDK_WEIGHT"), true);
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    public /* synthetic */ void Hk_(View view) {
        if (!"HagridDeviceManagerFragment".equals(this.f2230a.getClass().getSimpleName())) {
            o();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void e(String str, String str2) {
        Intent intent = new Intent();
        intent.setPackage(BaseApplication.d());
        intent.setClassName(BaseApplication.d(), "com.huawei.ui.device.activity.adddevice.PairingGuideActivity");
        intent.putExtra("kind_id", "HDK_WEIGHT");
        intent.putExtra("pair_guide", "2");
        intent.putExtra("is_invalidation", true);
        intent.putExtra("uniqueId", str2);
        ArrayList<String> arrayList = new ArrayList<>(16);
        arrayList.add(str);
        intent.putStringArrayListExtra("uuid_list", arrayList);
        startActivity(intent);
    }

    private void h(String str) {
        if (this.w == null) {
            CommonDialog21 a2 = CommonDialog21.a(this);
            this.w = a2;
            a2.e(str);
            this.w.a();
            this.w.setCancelable(false);
            return;
        }
        LogUtil.h("DeviceMainActivity", "showLoadingDialog: mLoadingDialog is not null");
    }

    private void Hh_(final Intent intent) {
        LogUtil.a("DeviceMainActivity", "syncWiFiDeviceAndGoToDeviceManager");
        this.u = findViewById(R.id.device_main_loading);
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.device_main_title);
        this.ae = customTitleBar;
        if (customTitleBar != null) {
            customTitleBar.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: ckr
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DeviceMainActivity.this.Hl_(view);
                }
            });
            this.ae.setVisibility(0);
        }
        View view = this.u;
        if (view != null) {
            view.setVisibility(0);
        }
        final Runnable runnable = new Runnable() { // from class: ckt
            @Override // java.lang.Runnable
            public final void run() {
                DeviceMainActivity.this.i();
            }
        };
        this.h.postDelayed(runnable, 7000L);
        csb.a().b(intent.getStringExtra("productId"), this.aj, new IBaseResponseCallback() { // from class: cku
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                DeviceMainActivity.this.Hm_(intent, runnable, i, obj);
            }
        });
    }

    public /* synthetic */ void Hl_(View view) {
        onBackPressed();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void i() {
        WeakReference<Timer> weakReference = this.ah;
        if (weakReference != null && weakReference.get() != null) {
            this.ah.get().cancel();
        }
        n();
        aa();
    }

    public /* synthetic */ void Hm_(Intent intent, Runnable runnable, int i, Object obj) {
        if (this.h == null) {
            LogUtil.h("DeviceMainActivity", "syncWiFiDevice complete, but handler is null");
            return;
        }
        Message.obtain().obj = intent;
        if (i == 0) {
            LogUtil.a("DeviceMainActivity", "syncWiFiDeviceAndGoToDeviceManager success, showChooseDeviceBindingListView");
            Hg_(intent);
        } else {
            this.h.removeCallbacksAndMessages(null);
            this.h.post(runnable);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        CustomTitleBar customTitleBar = this.ae;
        if (customTitleBar != null) {
            customTitleBar.setVisibility(8);
            this.ae = null;
        }
        View view = this.u;
        if (view != null) {
            view.setVisibility(8);
            this.u = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void z() {
        CommonDialog21 commonDialog21 = this.w;
        if (commonDialog21 != null) {
            commonDialog21.cancel();
            this.w = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.d;
        if (noTitleCustomAlertDialog != null) {
            noTitleCustomAlertDialog.dismiss();
            this.d = null;
        }
        z();
        finish();
    }

    private void Hg_(Intent intent) {
        String stringExtra = intent.getStringExtra("productId");
        Timer timer = new Timer("DeviceMainActivity");
        this.ah = new WeakReference<>(timer);
        a aVar = new a(this, this.h, intent, stringExtra, timer);
        try {
            LogUtil.a("DeviceMainActivity", "startTimerToCheckDevice start checking");
            timer.schedule(aVar, 0L, 200L);
        } catch (IllegalStateException e) {
            LogUtil.b("DeviceMainActivity", "startTimeTask schedule IllegalStateException: ", e.getMessage());
        }
    }

    public void b(boolean z) {
        this.t = z;
    }

    public boolean e() {
        return this.t;
    }

    private void af() {
        Fragment fragment = this.f2230a;
        if (fragment != null) {
            String simpleName = fragment.getClass().getSimpleName();
            LogUtil.a("DeviceMainActivity", "switchDeviceBindWaitingFragment fragment name is ", simpleName);
            if (!TextUtils.isEmpty(simpleName) && "DeviceBindWaitingFragment".equals(simpleName)) {
                LogUtil.a("DeviceMainActivity", "switchDeviceBindWaitingFragment onNewIntent switch");
                return;
            } else {
                if (!TextUtils.isEmpty(simpleName) && "DeviceBindWaitingFragment".equals(simpleName)) {
                    LogUtil.a("DeviceMainActivity", "switchDeviceBindWaitingFragment is isMeasuring");
                    return;
                }
                LogUtil.h("DeviceMainActivity", "switchDeviceBindWaitingFragment other fragment");
            }
        }
        DeviceBindWaitingFragment deviceBindWaitingFragment = new DeviceBindWaitingFragment();
        if (this.b != null && getIntent() != null && getIntent().getParcelableExtra(AdShowExtras.DOWNLOAD_SOURCE) != null) {
            this.b.putParcelable(AdShowExtras.DOWNLOAD_SOURCE, getIntent().getParcelableExtra(AdShowExtras.DOWNLOAD_SOURCE));
            this.b.putBoolean("is_go_rope_jump", this.s);
            this.b.putBoolean("isNfcConnect", this.p);
            this.b.putString("fittings_host_sn", getIntent().getStringExtra("fittings_host_sn"));
            dib.c().UV_(getIntent(), this.b);
        }
        deviceBindWaitingFragment.setArguments(this.b);
        a((Fragment) null, deviceBindWaitingFragment);
    }

    private void ag() {
        Fragment fragment = this.f2230a;
        if (fragment != null) {
            String simpleName = fragment.getClass().getSimpleName();
            LogUtil.a("DeviceMainActivity", "switchHagridDeviceManagerFragment fragment name is ", simpleName);
            if (!TextUtils.isEmpty(simpleName) && "HagridDeviceManagerFragment".equals(simpleName)) {
                LogUtil.a("DeviceMainActivity", "switchHagridDeviceManagerFragment onNewIntent switch");
                return;
            }
            LogUtil.h("DeviceMainActivity", "other fragment");
        }
        HagridDeviceManagerFragment hagridDeviceManagerFragment = new HagridDeviceManagerFragment();
        Bundle bundle = this.b;
        if (bundle != null) {
            bundle.putBoolean("isNfcConnect", this.p);
        }
        hagridDeviceManagerFragment.setArguments(this.b);
        a((Fragment) null, hagridDeviceManagerFragment);
    }

    public void d(boolean z) {
        this.n = z;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
