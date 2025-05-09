package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import com.huawei.devicepair.model.StartPairOption;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.health.R;
import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.devicemgr.business.entity.DeviceParameter;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwservicesmgr.IAddDeviceStateAIDLCallback;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.commonui.dialog.CustomProgressDialog;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import com.huawei.ui.device.activity.pairing.DevicePairGuideActivity;
import com.huawei.ui.homewear21.home.DirectConnectDeviceActivity;
import defpackage.pem;
import health.compact.a.AuthorizationUtils;
import health.compact.a.CommonUtil;
import health.compact.a.EzPluginManager;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class pem {
    private static final Object c = new Object();
    private static pem d;
    private Activity b = null;
    private CustomProgressDialog o = null;
    private CustomProgressDialog.Builder n = null;
    private CustomTextAlertDialog j = null;
    private String h = "";
    private String e = "";
    private String m = "";
    private boolean g = false;
    private int k = -1;

    /* renamed from: a, reason: collision with root package name */
    private int f16096a = 104;
    private oae i = null;
    private Map<String, Integer> f = new HashMap(16);

    private pem() {
        k();
    }

    public static pem d() {
        pem pemVar;
        synchronized (c) {
            if (d == null) {
                d = new pem();
            }
            pemVar = d;
        }
        return pemVar;
    }

    public void b() {
        l();
    }

    private static void l() {
        synchronized (c) {
            d = null;
        }
    }

    public void dmN_(Activity activity) {
        if (activity != null) {
            this.b = activity;
        }
    }

    public void e(String str, String str2, String str3, int i, boolean z) {
        this.h = str;
        this.e = str2;
        this.m = str3;
        this.k = i;
        this.g = z;
    }

    private void k() {
        this.f.put("00M005", 57);
        this.f.put("00M0AA", 71);
        this.f.put("00N0A8", 65);
        this.f.put("00N0A6", 35);
        this.f.put("00N0A7", 35);
        this.f.put("00N0AA", 74);
        this.f.put("00M0A2", 72);
        this.f.put("00M0A3", 72);
        this.f.put("00M0A4", 72);
        this.f.put("00M0A5", 72);
        this.f.put("00M0AS", 82);
        this.f.put("00M0AN", 72);
        this.f.put("00M0A6", 72);
        this.f.put("00M0AT", 72);
        this.f.put("00M0B3", 76);
        this.f.put("00M0B4", 76);
        this.f.put("00M0B9", 76);
        this.f.put("00M0BA", 76);
        this.f.put("00M0BB", 76);
        this.f.put("00M0BE", 78);
        this.f.put("00M0BF", 78);
        this.f.put("00M0BL", 78);
        this.f.put("00M0BM", 78);
        this.f.put("00M0BG", 71);
        this.f.put("00M0BH", 71);
        this.f.put("00M0BI", 79);
        this.f.put("00M0BJ", 79);
        this.f.put("00M0B5", 80);
        this.f.put("00M0A0", 71);
        this.f.put("00M0A1", 71);
        this.f.put("00M0A9", 71);
        this.f.put("00M0AD", 71);
        this.f.put("00M0AE", 71);
        this.f.put("00M0AF", 61);
        this.f.put("00M0AG", 61);
        this.f.put("00M0A7", 73);
        this.f.put("00M0A8", 73);
        this.f.put("00M0B2", 75);
        this.f.put("00M0B6", 77);
        this.f.put("00M0B7", 77);
        this.f.put("00M0BN", 81);
        this.f.put("00M0BO", 73);
        this.f.put("00M0BP", 73);
        this.f.put("00M0BW", 83);
        this.f.put("00M0BX", 84);
        o();
    }

    private void o() {
        this.f.put("00M0C0", 72);
        this.f.put("00M0BZ", 89);
        this.f.put("00M0BY", 89);
        this.f.put("00M0D5", 89);
        this.f.put("00M0E9", 89);
        this.f.put("00M0EA", 89);
        this.f.put("00M0AR", 77);
        this.f.put("00M0C2", 72);
        this.f.put("00M0C7", 85);
        this.f.put("00M0C8", 85);
        this.f.put("00M0C9", 85);
        this.f.put("00M0CA", 85);
        this.f.put("00M0CB", 85);
        this.f.put("00M0CC", 85);
        this.f.put("00M0CD", 85);
        this.f.put("00M0CE", 85);
        this.f.put("00M0CF", 85);
        this.f.put("00M0CG", 85);
        this.f.put("00M0DQ", 85);
        this.f.put("00M0C4", 90);
        this.f.put("00M0C5", 83);
        this.f.put("00M0C6", 83);
        this.f.put("00M0D0", 88);
        this.f.put("00M0CS", 71);
        this.f.put("00M0CT", 71);
        this.f.put("00M0CU", 71);
        this.f.put("00M0CV", 71);
        this.f.put("00M0D7", 71);
        this.f.put("00M0D8", 71);
        this.f.put("00M0CW", 79);
        this.f.put("00M0CY", 79);
        this.f.put("00M0D1", 61);
        this.f.put("00M0D2", 72);
        this.f.put("00M0D3", 72);
        this.f.put("00M0D4", 93);
        this.f.put("00M0CM", 86);
        this.f.put("00M0CO", 86);
        this.f.put("00M0CP", 86);
        this.f.put("00M0E8", 86);
        this.f.put("00M0DF", 71);
        this.f.put("00M0DG", 71);
        this.f.put("00M0DH", 71);
        this.f.put("00M0DI", 71);
        this.f.put("00M0DJ", 71);
        this.f.put("00M0DK", 71);
        this.f.put("00M0DL", 71);
        this.f.put("00M0DM", 71);
        this.f.put("00M0DS", 71);
        this.f.put("00M0DT", 71);
        this.f.put("00M0EG", 71);
        this.f.put("00M0DN", 79);
        this.f.put("00M0DO", 79);
        this.f.put("00M0DB", 94);
        this.f.put("00M0DC", 94);
        this.f.put("00M0DV", 97);
        this.f.put("00M0DZ", 97);
        this.f.put("00M0E2", 97);
        this.f.put("00M0E6", 97);
        this.f.put("00M0EB", 99);
        this.f.put("00M0EE", 99);
        this.f.put("00M0EC", 99);
        this.f.put("00M0EF", 99);
        this.f.put("00M0EK", 101);
        this.f.put("00M0EL", 101);
        this.f.put("00M0EM", 101);
        this.f.put("00M0EH", 100);
        this.f.put("00M0EI", 100);
        this.f.put("00M0EJ", 100);
    }

    public boolean j() {
        boolean isResourcesAvailable = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).isResourcesAvailable(jfu.d(this.k));
        LogUtil.a("DirectConnectDeviceManager", "isHasResource is ", Boolean.valueOf(isResourcesAvailable));
        return isResourcesAvailable;
    }

    public void i() {
        if (c(this.m) && !this.g) {
            LogUtil.a("DirectConnectDeviceManager", "startConnectDevice go to page device.");
            Activity activity = this.b;
            if (activity == null || activity.isFinishing()) {
                LogUtil.h("DirectConnectDeviceManager", "startConnectDevice mActivity is null.");
                return;
            }
            String f = jfu.c(this.k).f();
            if (bgb.d().isSupportH5Pair(this.h)) {
                String j = jfu.j(this.k);
                ArrayList arrayList = new ArrayList();
                arrayList.add(j);
                bgb.d().startPair(this.b, StartPairOption.builder().c(this.e).e(arrayList).d(this.h).b("wear_watch").a(false).c());
                this.b.finish();
                return;
            }
            Intent intent = new Intent(this.b, (Class<?>) DevicePairGuideActivity.class);
            intent.putExtra("pairGuideProductType", this.k);
            intent.putExtra("pairGuideProductName", f);
            intent.putExtra("pairGuideSelectName", this.h);
            intent.putExtra("pairGuideFromScanList", true);
            intent.putExtra("pairGuideFromNearDiscovery", true);
            intent.putExtra("pairGuideSelectAddress", this.e);
            intent.putExtra("pairGuideDeviceMode", 100008);
            this.b.startActivityForResult(intent, 1);
            Activity activity2 = this.b;
            if (activity2 instanceof DirectConnectDeviceActivity) {
                ((DirectConnectDeviceActivity) activity2).e();
                return;
            }
            return;
        }
        LogUtil.a("DirectConnectDeviceManager", "startConnectDevice other device.");
        DeviceParameter deviceParameter = new DeviceParameter();
        deviceParameter.setBluetoothType(oae.d(this.k));
        deviceParameter.setProductType(this.k);
        deviceParameter.setDeviceNameInfo(this.h);
        deviceParameter.setMac(this.e);
        deviceParameter.setIsSupportHeartRate(false);
        deviceParameter.setProductPin("");
        c(deviceParameter);
        n();
    }

    private void c(DeviceParameter deviceParameter) {
        this.i = oae.c(BaseApplication.getContext());
        nxf.c(BaseApplication.getContext()).b((IAddDeviceStateAIDLCallback) null);
        List<DeviceInfo> c2 = this.i.c();
        if (c2 == null || c2.isEmpty()) {
            this.i.d(deviceParameter, "", null);
            return;
        }
        int size = c2.size();
        LogUtil.a("DirectConnectDeviceManager", "handleDeviceConnect connectDeviceSize :", Integer.valueOf(size));
        if (size == 1) {
            e(deviceParameter, c2);
        } else {
            b(deviceParameter, c2);
        }
    }

    private void e(DeviceParameter deviceParameter, List<DeviceInfo> list) {
        DeviceInfo deviceInfo = list.get(0);
        boolean c2 = cvt.c(deviceInfo.getProductType());
        int autoDetectSwitchStatus = deviceInfo.getAutoDetectSwitchStatus();
        String deviceIdentify = deviceInfo.getDeviceIdentify();
        LogUtil.a("DirectConnectDeviceManager", "handleSingleConnectedDevice isAw70Connected: ", Boolean.valueOf(c2), ", deviceWorkMode: ", Integer.valueOf(autoDetectSwitchStatus), ", connectedMacAddress: ", CommonUtil.l(deviceIdentify));
        if (!c2) {
            this.i.d(deviceParameter, deviceIdentify, null);
            return;
        }
        if (autoDetectSwitchStatus == 1) {
            this.i.d(deviceParameter, "", null);
        } else if (autoDetectSwitchStatus == 0) {
            this.i.d(deviceParameter, deviceIdentify, null);
        } else {
            LogUtil.h("DirectConnectDeviceManager", "handleSingleConnectedDevice occur error.");
        }
    }

    private void b(DeviceParameter deviceParameter, List<DeviceInfo> list) {
        for (DeviceInfo deviceInfo : list) {
            if (!cvt.c(deviceInfo.getProductType())) {
                LogUtil.a("DirectConnectDeviceManager", "handleMoreConnectedDevice disconnect one no aw70 device.");
                this.i.d(deviceParameter, deviceInfo.getDeviceIdentify(), null);
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        Activity activity = this.b;
        if (activity == null || activity.isFinishing()) {
            return;
        }
        this.b.finish();
    }

    public void g() {
        LogUtil.a("DirectConnectDeviceManager", "Enter handleDirectConnect mAppEnvironmentState: ", Integer.valueOf(this.f16096a));
        if (Build.VERSION.SDK_INT <= 30) {
            ReleaseLogUtil.d("R_DirectConnectDeviceManager", "goDevicePairPage Phone is below to Android 12");
            p();
        } else {
            PermissionUtil.b(this.b, PermissionUtil.PermissionType.SCAN, new AnonymousClass3(this.b));
        }
    }

    /* renamed from: pem$3, reason: invalid class name */
    class AnonymousClass3 extends CustomPermissionAction {
        AnonymousClass3(Context context) {
            super(context);
        }

        @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
        public void onGranted() {
            pem.this.p();
        }

        @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
        public void onDenied(String str) {
            nrh.b(pem.this.b, R.string._2130846464_res_0x7f022300);
            pem.this.b.finish();
        }

        @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
        public void onForeverDenied(PermissionUtil.PermissionType permissionType) {
            nsn.cLK_(pem.this.b, permissionType, null, new View.OnClickListener() { // from class: peo
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    pem.AnonymousClass3.this.dmO_(view);
                }
            }, new View.OnClickListener() { // from class: peq
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    pem.AnonymousClass3.this.dmP_(view);
                }
            });
        }

        /* synthetic */ void dmO_(View view) {
            pem.this.b.finish();
            ViewClickInstrumentation.clickOnView(view);
        }

        /* synthetic */ void dmP_(View view) {
            pem.this.b.finish();
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p() {
        switch (this.f16096a) {
            case 100:
            case 101:
            case 103:
                h();
                break;
            case 102:
            case 105:
                f();
                break;
            case 104:
            default:
                LogUtil.h("DirectConnectDeviceManager", "handleDirectConnect mAppEnvironmentState is unknown.");
                if (this.b != null) {
                    LogUtil.a("DirectConnectDeviceManager", "handleDirectConnect Enter goAppMainPage.");
                    Intent launchIntentForPackage = this.b.getPackageManager().getLaunchIntentForPackage(this.b.getPackageName());
                    launchIntentForPackage.putExtra("directConnectDevice", "directConnectDevice");
                    launchIntentForPackage.addFlags(AppRouterExtras.COLDSTART);
                    this.b.startActivity(launchIntentForPackage);
                }
                n();
                break;
        }
    }

    private void h() {
        if (LoginInit.getInstance(BaseApplication.getContext()).isBrowseMode()) {
            LoginInit.getInstance(BaseApplication.getContext()).browsingToLogin(new IBaseResponseCallback() { // from class: pem.4
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    LogUtil.a("DirectConnectDeviceManager", "browsingToLogin onResponse errorCode: ", Integer.valueOf(i));
                    if (i == 0) {
                        pem.this.s();
                    } else {
                        pem.this.n();
                    }
                }
            }, "");
        } else {
            s();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        LogUtil.a("DirectConnectDeviceManager", "Enter handleLogin mAppEnvironmentState: ", Integer.valueOf(this.f16096a));
        int i = this.f16096a;
        if (i == 100 || i == 101) {
            f();
        } else if (i == 103) {
            i();
        } else {
            LogUtil.h("DirectConnectDeviceManager", "handleLogin mAppEnvironmentState is unknown.");
            n();
        }
    }

    public void f() {
        LogUtil.a("DirectConnectDeviceManager", "Enter downloadDeviceResource.");
        if (!CommonUtil.aa(BaseApplication.getContext())) {
            t();
            return;
        }
        if (!jfu.g(this.k)) {
            LogUtil.h("DirectConnectDeviceManager", "DeviceInfoManager no has uuid.");
            t();
            return;
        }
        msq e = msn.c().e(jfu.d(this.k));
        if (e != null) {
            EzPluginManager.a().a(e);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(jfu.d(this.k));
        ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).downloadIndexByUuidList(arrayList, null);
        q();
    }

    private void q() {
        CustomProgressDialog customProgressDialog = this.o;
        if (customProgressDialog != null && customProgressDialog.isShowing()) {
            LogUtil.a("DirectConnectDeviceManager", "startDownLoadProgress progress bar is already exist.");
            return;
        }
        Activity activity = this.b;
        if (activity == null || activity.isFinishing()) {
            LogUtil.h("DirectConnectDeviceManager", "startDownLoadProgress mActivity is finished.");
            return;
        }
        this.o = new CustomProgressDialog(this.b);
        CustomProgressDialog.Builder builder = new CustomProgressDialog.Builder(this.b);
        this.n = builder;
        builder.d(this.b.getResources().getString(R.string.IDS_hw_health_wear_update_device_resource_text)).cyH_(new View.OnClickListener() { // from class: pem.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("DirectConnectDeviceManager", "startDownLoadProgress onClick cancel.");
                pem.this.m();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        CustomProgressDialog e = this.n.e();
        this.o = e;
        e.setCanceledOnTouchOutside(false);
        this.o.show();
        this.n.d(0);
        this.n.e(jed.b(0.0d, 2, 0));
        LogUtil.a("DirectConnectDeviceManager", "startDownLoadProgress mCustomProgressDialog show.");
    }

    public void b(int i) {
        if (i == -1) {
            LogUtil.a("DirectConnectDeviceManager", "showDownloadProgress progress is error.");
            oau.c(100100, "");
            m();
            return;
        }
        CustomProgressDialog customProgressDialog = this.o;
        if (customProgressDialog == null || !customProgressDialog.isShowing() || i < 0) {
            return;
        }
        String b = jed.b(i, 2, 0);
        this.n.d(i);
        this.n.e(b);
    }

    public void c() {
        LogUtil.a("DirectConnectDeviceManager", "Enter closeProgress.");
        CustomProgressDialog customProgressDialog = this.o;
        if (customProgressDialog == null || this.b == null) {
            LogUtil.h("DirectConnectDeviceManager", "closeProgress mCustomProgressDialog or mActivity is null.");
        } else {
            if (!customProgressDialog.isShowing() || this.b.isFinishing()) {
                return;
            }
            LogUtil.a("DirectConnectDeviceManager", "closeProgress cancel.");
            this.o.cancel();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        LogUtil.a("DirectConnectDeviceManager", "Enter handleCancel.");
        msq e = msn.c().e(jfu.d(this.k));
        if (e != null) {
            EzPluginManager.a().a(e);
        }
        r();
    }

    private void r() {
        c();
        t();
    }

    private void t() {
        CustomTextAlertDialog customTextAlertDialog = this.j;
        if (customTextAlertDialog != null && customTextAlertDialog.isShowing()) {
            LogUtil.h("DirectConnectDeviceManager", "showErrorLayoutDialog mDownloadErrorDialog is showing.");
            return;
        }
        Activity activity = this.b;
        if (activity == null || activity.isFinishing()) {
            LogUtil.h("DirectConnectDeviceManager", "showErrorLayoutDialog mActivity is finished.");
            return;
        }
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(this.b);
        builder.b(R.string.IDS_service_area_notice_title).d(R.string.IDS_device_device_list_update_failed).cyU_(R.string._2130841794_res_0x7f0210c2, new View.OnClickListener() { // from class: pem.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (pem.this.j != null) {
                    pem.this.j.dismiss();
                    pem.this.j = null;
                    pem.this.n();
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        CustomTextAlertDialog a2 = builder.a();
        this.j = a2;
        a2.setCancelable(false);
        this.j.show();
    }

    public int c(int i) {
        if (!AuthorizationUtils.a(BaseApplication.getContext())) {
            LogUtil.h("DirectConnectDeviceManager", "checkDeviceDownloadState user no agree privacy.");
            this.f16096a = 100;
            return 100;
        }
        boolean isResourcesAvailable = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).isResourcesAvailable(jfu.d(i));
        if (Utils.i()) {
            if (!LoginInit.getInstance(BaseApplication.getContext()).getIsLogined()) {
                if (!isResourcesAvailable) {
                    LogUtil.h("DirectConnectDeviceManager", "checkDeviceDownloadState account no login and no device resource.");
                    this.f16096a = 101;
                } else {
                    LogUtil.a("DirectConnectDeviceManager", "checkDeviceDownloadState account no login but has device resource.");
                    this.f16096a = 103;
                }
                return this.f16096a;
            }
        } else if (!isResourcesAvailable) {
            LogUtil.h("DirectConnectDeviceManager", "checkDeviceDownloadState no cloud and no device resource.");
            this.f16096a = 105;
            return 105;
        }
        if (!isResourcesAvailable) {
            LogUtil.h("DirectConnectDeviceManager", "checkDeviceDownloadState no device resource.");
            this.f16096a = 102;
        }
        return this.f16096a;
    }

    public static boolean e() {
        LogUtil.a("DirectConnectDeviceManager", "Enter isFirstOpenAppToDirectConnect.");
        if (CommonUtil.ag(BaseApplication.getContext()) || b(BaseApplication.getContext(), BaseApplication.getContext().getPackageName())) {
            LogUtil.a("DirectConnectDeviceManager", "isMainActivityToDirectConnect is system app.");
            return AuthorizationUtils.a(BaseApplication.getContext());
        }
        boolean equals = "NOT_FIRST_OPEN_APP_DIRECT_DEVICE".equals(SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(10000), "FIRST_OPEN_APP_DIRECT_DEVICE"));
        if (!equals) {
            LogUtil.a("DirectConnectDeviceManager", "isMainActivityToDirectConnect isDirectlyJump is false.");
            SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(10000), "FIRST_OPEN_APP_DIRECT_DEVICE", "NOT_FIRST_OPEN_APP_DIRECT_DEVICE", (StorageParams) null);
        }
        return equals && AuthorizationUtils.a(BaseApplication.getContext());
    }

    private static boolean b(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            LogUtil.h("DirectConnectDeviceManager", "isSystemApp context or packageName is empty.");
            return false;
        }
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(str, 0);
            if (applicationInfo != null) {
                if ((applicationInfo.flags & 1) != 0) {
                    return true;
                }
            }
        } catch (PackageManager.NameNotFoundException unused) {
            LogUtil.b("DirectConnectDeviceManager", "isSystemApp occur NameNotFoundException.");
        }
        return false;
    }

    public static void a() {
        LogUtil.a("DirectConnectDeviceManager", "Enter setDownloadResourceFlag.");
        SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(10000), "DIRECT_CONNECT_DOWNLOAD_RESOURCE", "DIRECT_CONNECT_DOWNLOAD_RESOURCE", (StorageParams) null);
    }

    public boolean c(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("DirectConnectDeviceManager", "isConnectTypeActivity moduleId is empty.");
            return false;
        }
        if (this.f.get(str) == null) {
            return false;
        }
        LogUtil.a("DirectConnectDeviceManager", "isConnectTypeActivity moduleId is exist.");
        return true;
    }

    public int a(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("DirectConnectDeviceManager", "getDeviceProductType moduleId is empty.");
            return -1;
        }
        if (this.f.get(str) != null) {
            return this.f.get(str).intValue();
        }
        return -1;
    }
}
