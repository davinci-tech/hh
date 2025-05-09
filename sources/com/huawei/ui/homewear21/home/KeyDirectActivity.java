package com.huawei.ui.homewear21.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import com.huawei.devicepair.model.StartPairOption;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.health.R;
import com.huawei.health.device.api.IndoorEquipManagerApi;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.ui.DeviceMainActivity;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.nfc.PluginPayAdapter;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import com.huawei.ui.device.activity.coresleep.CoreSleepSelectorActivity;
import com.huawei.ui.device.activity.heartrate.ContinueHeartRateSettingActivity;
import com.huawei.ui.device.activity.intelligenthome.IntelligentHomeLinkageActivity;
import com.huawei.ui.device.activity.notification.NotificationSettingActivity;
import com.huawei.ui.device.activity.pairing.DevicePairGuideActivity;
import com.huawei.ui.device.activity.pressautomonitor.PressAutoMonitorActivity;
import com.huawei.ui.device.activity.selectcontact.ContactMainActivity;
import com.huawei.ui.homewear21.home.KeyDirectActivity;
import defpackage.bgb;
import defpackage.cpl;
import defpackage.cun;
import defpackage.cvs;
import defpackage.iyg;
import defpackage.jdx;
import defpackage.jfu;
import defpackage.nrh;
import defpackage.nsn;
import defpackage.nue;
import defpackage.ocp;
import defpackage.oxi;
import defpackage.pem;
import defpackage.pep;
import defpackage.sqo;
import health.compact.a.AuthorizationUtils;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public class KeyDirectActivity extends BaseActivity {
    private Context e;
    private Uri j;
    private oxi k;
    private String n;

    /* renamed from: a, reason: collision with root package name */
    private String f9639a = "";
    private String d = "";
    private String f = "";
    private boolean g = false;
    private int i = -1;
    private int c = 104;
    private pem b = null;
    private a h = new a(this);

    static class a extends Handler {
        private WeakReference<KeyDirectActivity> d;

        a(KeyDirectActivity keyDirectActivity) {
            this.d = new WeakReference<>(keyDirectActivity);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message == null) {
                LogUtil.h("KeyDirectActivity", "handleMessage message is null");
                return;
            }
            KeyDirectActivity keyDirectActivity = this.d.get();
            if (keyDirectActivity == null || keyDirectActivity.isFinishing()) {
                LogUtil.h("KeyDirectActivity", "handleMessage keyDirectActivity is null or is finish");
                return;
            }
            if (message.what == 2001) {
                if (!TextUtils.isEmpty(keyDirectActivity.n)) {
                    keyDirectActivity.c();
                    return;
                }
                LogUtil.h("KeyDirectActivity", "mWatchFaceGrsUrl is empty");
                if (message.obj instanceof String) {
                    keyDirectActivity.e((String) message.obj);
                } else {
                    LogUtil.h("KeyDirectActivity", "message obj no is String");
                }
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ReleaseLogUtil.e("KeyDirectActivity", "enter onCreate");
        this.e = this;
        pep.g(BaseApplication.getContext());
        Intent intent = getIntent();
        if (intent == null) {
            LogUtil.h("KeyDirectActivity", "intent is null");
            h();
            return;
        }
        try {
            String stringExtra = intent.getStringExtra("DEVICE_MODULE_ID");
            this.f = stringExtra;
            if (!TextUtils.isEmpty(stringExtra)) {
                djr_(intent);
                return;
            }
            Uri data = intent.getData();
            this.j = data;
            if (data == null) {
                sqo.ac("mSchemeDataUri is null.");
                LogUtil.h("KeyDirectActivity", "mSchemeDataUri is null");
                h();
                return;
            }
            LoginInit loginInit = LoginInit.getInstance(this.e);
            if (!AuthorizationUtils.a(this.e) || !loginInit.getIsLogined()) {
                LogUtil.a("KeyDirectActivity", "jump to MainActivity");
                startActivity(getPackageManager().getLaunchIntentForPackage(getPackageName()));
                h();
                return;
            }
            d();
        } catch (Throwable unused) {
            LogUtil.h("KeyDirectActivity", "onCreate get moduleId occur Throwable.");
            h();
        }
    }

    private void d() {
        try {
            String queryParameter = this.j.getQueryParameter("destination");
            if (TextUtils.isEmpty(queryParameter)) {
                LogUtil.h("KeyDirectActivity", "checkDestination() destination is empty");
                h();
                return;
            }
            LogUtil.a("KeyDirectActivity", "destination is ", queryParameter);
            if (!"healthzone".equals(queryParameter)) {
                List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "KeyDirectActivity");
                if (deviceList == null || deviceList.isEmpty()) {
                    LogUtil.h("KeyDirectActivity", "initDeviceMap() no used device");
                    g();
                    return;
                } else {
                    LogUtil.a("KeyDirectActivity", "device list is not empty");
                    a(deviceList, queryParameter);
                    return;
                }
            }
            LogUtil.a("KeyDirectActivity", "go to HealthZone");
            e();
        } catch (UnsupportedOperationException unused) {
            LogUtil.b("KeyDirectActivity", "checkDestination error is UnsupportedOperationException");
        }
    }

    private void a(List<DeviceInfo> list, String str) {
        ArrayList<DeviceInfo> arrayList = new ArrayList(16);
        for (DeviceInfo deviceInfo : list) {
            int productType = deviceInfo.getProductType();
            if (productType == 35 || productType == 34) {
                arrayList.add(deviceInfo);
                LogUtil.a("KeyDirectActivity", deviceInfo.getDeviceName(), "add to checkDeviceList");
            }
        }
        if (arrayList.isEmpty()) {
            LogUtil.h("KeyDirectActivity", "checkDeviceType() GT2 or Magic2");
            g();
            return;
        }
        for (DeviceInfo deviceInfo2 : arrayList) {
            LogUtil.a("KeyDirectActivity", "target device item is ", deviceInfo2.getDeviceName());
            if (deviceInfo2.getDeviceConnectState() == 2) {
                b(deviceInfo2.getDeviceIdentify(), str);
                return;
            }
        }
        String deviceIdentify = ((DeviceInfo) arrayList.get(0)).getDeviceIdentify();
        if (arrayList.size() == 1 && list.size() == 1) {
            e(deviceIdentify);
        } else {
            c((DeviceInfo) arrayList.get(0));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(String str) {
        LogUtil.a("KeyDirectActivity", "jumpToWearHome");
        Intent intent = new Intent();
        intent.setClass(this.e, WearHomeActivity.class);
        intent.setClassName(this.e, "com.huawei.ui.homewear21.home.WearHomeActivity");
        intent.putExtra("device_id", str);
        intent.setFlags(268435456);
        startActivity(intent);
        h();
    }

    private void g() {
        LogUtil.a("KeyDirectActivity", "jumpToDeviceList");
        HealthDevice.HealthDeviceKind healthDeviceKind = HealthDevice.HealthDeviceKind.HDK_UNKNOWN;
        Intent intent = new Intent(this.e, (Class<?>) DeviceMainActivity.class);
        intent.putExtra("view", "ListDevice");
        intent.putExtra("root_in_me", "me");
        intent.putExtra("kind", healthDeviceKind.name());
        intent.setFlags(268435456);
        startActivity(intent);
        h();
    }

    private void b(String str, String str2) {
        DeviceCapability e = cvs.e(str);
        if (e == null) {
            LogUtil.h("KeyDirectActivity", "device capability is null.");
            g();
        } else {
            c(str, str2, e);
        }
    }

    private void c(String str, String str2, DeviceCapability deviceCapability) {
        LogUtil.a("KeyDirectActivity", "doDistribution.");
        if ("sleep".equals(str2)) {
            e(str, deviceCapability);
            return;
        }
        if ("intelligent".equals(str2)) {
            a(str, deviceCapability);
            return;
        }
        if (IndoorEquipManagerApi.KEY_HEART_RATE.equals(str2)) {
            d(str, deviceCapability);
            return;
        }
        if ("press".equals(str2)) {
            f(str, deviceCapability);
            return;
        }
        if ("pay".equals(str2)) {
            i(str, deviceCapability);
            return;
        }
        if ("music".equals(str2)) {
            c(str, deviceCapability);
            return;
        }
        if (RemoteMessageConst.NOTIFICATION.equals(str2)) {
            h(str, deviceCapability);
            return;
        }
        if ("contact".equals(str2)) {
            b(str, deviceCapability);
        } else if ("watchface".equals(str2)) {
            g(str, deviceCapability);
        } else {
            LogUtil.h("KeyDirectActivity", "unknown destination:", str2);
            e(str);
        }
    }

    private void d(String str, DeviceCapability deviceCapability) {
        if (deviceCapability.isSupportContinueHeartRate()) {
            c(str, ContinueHeartRateSettingActivity.class);
        } else {
            LogUtil.h("KeyDirectActivity", "not support ContinueHeartRate");
            e(str);
        }
    }

    private void f(String str, DeviceCapability deviceCapability) {
        if (deviceCapability.isSupportPressAutoMonitor()) {
            c(str, PressAutoMonitorActivity.class);
        } else {
            LogUtil.h("KeyDirectActivity", "not support PressAutoMonitor");
            e(str);
        }
    }

    private void i(String str, DeviceCapability deviceCapability) {
        if (ocp.c(str, deviceCapability)) {
            a(str);
        } else {
            LogUtil.h("KeyDirectActivity", "not support Pay");
            e(str);
        }
    }

    private void c(String str, DeviceCapability deviceCapability) {
        if (deviceCapability.isSupportMusicInfoList()) {
            c(str, MusicSecondaryMenuActivity.class);
        } else {
            LogUtil.h("KeyDirectActivity", "not support Music");
            e(str);
        }
    }

    private void h(String str, DeviceCapability deviceCapability) {
        if (deviceCapability.isMessageAlert()) {
            c(str, NotificationSettingActivity.class);
        } else {
            LogUtil.h("KeyDirectActivity", "not support Notification");
            e(str);
        }
    }

    private void b(String str, DeviceCapability deviceCapability) {
        if (deviceCapability.isContacts()) {
            c((String) null, ContactMainActivity.class);
        } else {
            LogUtil.h("KeyDirectActivity", "not support Contacts");
            e(str);
        }
    }

    private void g(String str, DeviceCapability deviceCapability) {
        if (deviceCapability.isSupportWatchFace()) {
            c(str);
        } else {
            LogUtil.h("KeyDirectActivity", "not support WatchFace");
            e(str);
        }
    }

    private void a(String str, DeviceCapability deviceCapability) {
        if (deviceCapability.isSupportIntelligentHomeLinkage()) {
            c(str, IntelligentHomeLinkageActivity.class);
        } else {
            LogUtil.h("KeyDirectActivity", "not support IntelligentHomeLinkage.");
            e(str);
        }
    }

    private void e(String str, DeviceCapability deviceCapability) {
        if (deviceCapability.isDeviceSupportCoreSleep()) {
            c(str, CoreSleepSelectorActivity.class);
        } else {
            LogUtil.h("KeyDirectActivity", "not support CoreSleep.");
            e(str);
        }
    }

    private void e() {
        Intent intent = new Intent();
        intent.setClassName(BaseApplication.getAppPackage(), "com.huawei.pluginhealthzone.activity.FamilyHealthTempActivity");
        intent.setFlags(AppRouterExtras.COLDSTART);
        startActivity(intent);
        h();
    }

    private void c(String str, Class<?> cls) {
        Intent intent = new Intent(this.e, cls);
        if (!TextUtils.isEmpty(str)) {
            intent.putExtra("device_id", str);
        }
        intent.setFlags(AppRouterExtras.COLDSTART);
        startActivity(intent);
        h();
    }

    private void a(String str) {
        pep.b(this.e);
        pep.c(this.e, str);
        h();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        LogUtil.a("KeyDirectActivity", "gotoWatchFace");
        if (this.k == null) {
            this.k = new oxi(this, "com.huawei.ui.homewear21.home.KeyDirectActivity");
        }
        this.k.e(-1);
    }

    private void h() {
        setIntent(null);
        finish();
    }

    private void c(final String str) {
        LogUtil.a("KeyDirectActivity", "start to get watchFace url");
        jdx.b(new Runnable() { // from class: com.huawei.ui.homewear21.home.KeyDirectActivity.5
            @Override // java.lang.Runnable
            public void run() {
                String commonCountryCode = GRSManager.a(BaseApplication.getContext()).getCommonCountryCode();
                KeyDirectActivity.this.n = GRSManager.a(BaseApplication.getContext()).getNoCheckUrl("domainContentcenterDbankcdnNew", commonCountryCode);
                Message obtainMessage = KeyDirectActivity.this.h.obtainMessage();
                obtainMessage.what = 2001;
                obtainMessage.obj = str;
                KeyDirectActivity.this.h.sendMessage(obtainMessage);
            }
        });
    }

    private void c(DeviceInfo deviceInfo) {
        LogUtil.a("KeyDirectActivity", "jumpDeviceNotConnect");
        cpl c = cpl.c();
        Intent intent = new Intent();
        intent.setClassName(this.e, "com.huawei.ui.homehealth.devicemanagercard.DeviceManagerWearNoConnect");
        intent.putExtra(PluginPayAdapter.KEY_DEVICE_INFO_NAME, deviceInfo.getDeviceName());
        intent.putExtra("device_identify", deviceInfo.getDeviceIdentify());
        intent.putExtra("device_picID", c.d(deviceInfo.getProductType()));
        intent.putExtra(DeviceCategoryFragment.DEVICE_TYPE, deviceInfo.getProductType());
        intent.setFlags(AppRouterExtras.COLDSTART);
        this.e.startActivity(intent);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        ReleaseLogUtil.e("KeyDirectActivity", "enter onResume");
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        LogUtil.a("KeyDirectActivity", "onActivityResult requestCode: ", Integer.valueOf(i), ", resultCode: ", Integer.valueOf(i2));
        if (i == 1 && i2 == 2 && intent != null) {
            nue.cNU_(intent, this, nue.e(i2, true, intent.getIntExtra("product_type", -1), true));
        } else {
            h();
        }
    }

    private void djr_(Intent intent) {
        LogUtil.a("KeyDirectActivity", "Enter handleDirectConnectDevice.");
        this.b = pem.d();
        this.f9639a = intent.getStringExtra("DEVICE_NAME");
        this.d = iyg.c(intent.getStringExtra("DEVICE_ID"));
        this.g = intent.getBooleanExtra("IS_FROM_PHONE_SERVICE", false);
        int a2 = this.b.a(this.f);
        this.i = a2;
        if (a2 == -1) {
            this.i = intent.getIntExtra("DEVICE_PRODUCT_TYPE", -1);
        }
        if (TextUtils.isEmpty(this.f9639a) || TextUtils.isEmpty(this.d)) {
            sqo.ac("mDeviceName or mDeviceIdentify is empty.");
            LogUtil.h("KeyDirectActivity", "handleDirectConnectDevice mDeviceName or mDeviceIdentify is empty.");
            h();
            return;
        }
        f();
    }

    private void f() {
        this.c = this.b.c(this.i);
        LogUtil.a("KeyDirectActivity", "startConnectDevice mDeviceName: ", CommonUtil.l(this.f9639a), ", mDeviceIdentify: ", CommonUtil.l(this.d), ", mProductType: ", Integer.valueOf(this.i), ", mCurrentAppState: ", Integer.valueOf(this.c), ", mIsFromPhoneService: ", Boolean.valueOf(this.g));
        this.b.e(this.f9639a, this.d, this.f, this.i, this.g);
        switch (this.c) {
            case 100:
            case 101:
            case 103:
                sqo.ac("startConnectDevice NO_AGREE_PRIVACY_SCENARIO or NO_LOGIN.");
                pem.a();
                b();
                break;
            case 102:
            case 105:
                a();
                break;
            case 104:
            default:
                LogUtil.h("KeyDirectActivity", "mCurrentAppState is ok.");
                if (this.b.c(this.f)) {
                    djq_(this, jfu.j(this.i));
                    break;
                } else {
                    h();
                    break;
                }
        }
    }

    private void b() {
        LogUtil.a("KeyDirectActivity", "Enter goAppMainPage.");
        Intent launchIntentForPackage = getPackageManager().getLaunchIntentForPackage(getPackageName());
        launchIntentForPackage.putExtra("directConnectDevice", "directConnectDevice");
        launchIntentForPackage.addFlags(AppRouterExtras.COLDSTART);
        startActivity(launchIntentForPackage);
        h();
    }

    private void a() {
        LogUtil.a("KeyDirectActivity", "Enter goDirectConnectPage.");
        startActivity(new Intent(this, (Class<?>) DirectConnectDeviceActivity.class));
        h();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str) {
        LogUtil.a("KeyDirectActivity", "Enter goDevicePairPage.");
        String f = jfu.c(this.i).f();
        ArrayList arrayList = new ArrayList(10);
        arrayList.add(str);
        if (bgb.d().isSupportH5Pair(this.f9639a)) {
            bgb.d().startPair(this.e, StartPairOption.builder().c(this.d).e(arrayList).d(this.f9639a).b("wear_watch").a(false).c());
            finish();
            return;
        }
        Intent intent = new Intent(this, (Class<?>) DevicePairGuideActivity.class);
        intent.putExtra("pairGuideProductType", this.i);
        intent.putExtra("pairGuideProductName", f);
        intent.putExtra("pairGuideSelectName", this.f9639a);
        intent.putExtra("pairGuideFromScanList", true);
        intent.putExtra("pairGuideFromNearDiscovery", true);
        intent.putExtra("pairGuideSelectAddress", this.d);
        intent.putExtra("pairGuideDeviceMode", 100008);
        intent.putExtra("DOWNLOAD_RESOURCE", true);
        intent.putExtra("uuid_list", arrayList);
        startActivityForResult(intent, 1);
    }

    private void djq_(Activity activity, String str) {
        if (activity == null) {
            ReleaseLogUtil.d("R_KeyDirectActivity", "goDevicePairPage the activity is null!");
        } else if (Build.VERSION.SDK_INT <= 30) {
            ReleaseLogUtil.d("R_KeyDirectActivity", "goDevicePairPage Phone is below to Android 12");
            d(str);
        } else {
            PermissionUtil.b(activity, PermissionUtil.PermissionType.SCAN, new AnonymousClass1(activity, str, activity));
        }
    }

    /* renamed from: com.huawei.ui.homewear21.home.KeyDirectActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends CustomPermissionAction {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f9640a;
        final /* synthetic */ Activity b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Context context, String str, Activity activity) {
            super(context);
            this.f9640a = str;
            this.b = activity;
        }

        @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
        public void onGranted() {
            ReleaseLogUtil.c("R_KeyDirectActivity", "goDevicePairPage doActionWithPermissions onGranted");
            KeyDirectActivity.this.d(this.f9640a);
        }

        @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
        public void onDenied(String str) {
            super.onDenied(str);
            nrh.b(this.b, R.string._2130846464_res_0x7f022300);
            this.b.finish();
        }

        @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
        public void onForeverDenied(PermissionUtil.PermissionType permissionType) {
            final Activity activity = this.b;
            View.OnClickListener onClickListener = new View.OnClickListener() { // from class: oxg
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    KeyDirectActivity.AnonymousClass1.djs_(activity, view);
                }
            };
            final Activity activity2 = this.b;
            nsn.cLK_(activity, permissionType, null, onClickListener, new View.OnClickListener() { // from class: oxf
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    KeyDirectActivity.AnonymousClass1.djt_(activity2, view);
                }
            });
        }

        public static /* synthetic */ void djs_(Activity activity, View view) {
            activity.finish();
            ViewClickInstrumentation.clickOnView(view);
        }

        public static /* synthetic */ void djt_(Activity activity, View view) {
            activity.finish();
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
