package defpackage;

import android.R;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.huawei.devicepair.model.StartPairOption;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.deviceconnect.callback.BtDeviceResponseCallback;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbtsdk.btdatatype.callback.BtDeviceDiscoverCallback;
import com.huawei.hwbtsdk.btdatatype.callback.BtSwitchStateCallback;
import com.huawei.hwbtsdk.btdatatype.datatype.BluetoothDeviceNode;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwdevice.outofprocess.mgr.device.CloudDeviceInfo;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.h5pro.jsmodules.device.BasePairManagerJsApi;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import com.huawei.ui.device.activity.pairing.DevicePairGuideActivity;
import com.huawei.ui.device.activity.pairing.DevicePairGuideUtil;
import defpackage.obi;
import health.compact.a.CommonUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes6.dex */
public class obi {
    private static obi i;

    /* renamed from: a, reason: collision with root package name */
    protected ActivityResultLauncher<IntentSenderRequest> f15599a;
    protected WeakReference<Activity> e;
    private int g;
    private String k;
    private String l;
    private cuw n;
    private boolean p;
    private boolean s;
    private int v;
    private String x;
    private static final String[] c = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"};
    private static final String[] b = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION", "android.permission.READ_PHONE_STATE"};
    private static final String[] h = {"android.permission.BLUETOOTH_CONNECT", "android.permission.BLUETOOTH_SCAN"};
    private static final Object d = new Object();
    private boolean t = false;
    private Timer m = null;
    private Timer q = null;
    private BroadcastReceiver r = new BroadcastReceiver() { // from class: obi.5
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null || intent.getAction() == null) {
                LogUtil.h("CloudDeviceConnectManager", "mPairBroadcastReceiver intent is null");
                return;
            }
            LogUtil.h("CloudDeviceConnectManager", "mPairBroadcastReceiver intent is : ", intent.getAction());
            if ("com.huawei.health.action.PAIR_DEVICE_SUCCESS".equals(intent.getAction())) {
                LogUtil.a("CloudDeviceConnectManager", "pair device success");
                Activity activity = obi.this.e.get();
                if (activity == null || activity.isFinishing()) {
                    LogUtil.a("CloudDeviceConnectManager", "activity is finish");
                } else if (intent.getBooleanExtra(BasePairManagerJsApi.FROM_H5_KEY, false)) {
                    activity.finish();
                }
            }
        }
    };
    private BtDeviceResponseCallback o = new BtDeviceResponseCallback() { // from class: obp
        @Override // com.huawei.health.deviceconnect.callback.BtDeviceResponseCallback
        public final void onResponse(int i2, Object obj) {
            obi.this.a(i2, obj);
        }
    };
    private BtSwitchStateCallback j = new BtSwitchStateCallback() { // from class: obi.4
        @Override // com.huawei.hwbtsdk.btdatatype.callback.BtSwitchStateCallback
        public void onBtSwitchStateCallback(int i2) {
            if (i2 == 1) {
                obi.this.l();
                obi.this.f();
                iyl.d().e(obi.this.j);
            } else if (i2 == 3) {
                iyl.d().a(BaseApplication.getContext(), obi.this.o);
            } else {
                LogUtil.h("CloudDeviceConnectManager", "Bluetooth unknown state.");
            }
        }
    };
    private BtDeviceDiscoverCallback f = new BtDeviceDiscoverCallback() { // from class: obi.1
        @Override // com.huawei.hwbtsdk.btdatatype.callback.BtDeviceDiscoverCallback
        public void onDeviceDiscovered(BluetoothDeviceNode bluetoothDeviceNode, int i2, byte[] bArr) {
            if (bluetoothDeviceNode != null) {
                try {
                    BluetoothDevice btDevice = bluetoothDeviceNode.getBtDevice();
                    if (btDevice != null) {
                        String name = btDevice.getName();
                        if (name == null) {
                            name = bluetoothDeviceNode.getRecordName();
                        }
                        LogUtil.a("CloudDeviceConnectManager", "device name:", CommonUtil.l(name));
                        if (name == null || !name.equals(obi.this.x)) {
                            return;
                        }
                        obi.this.cUu_(btDevice);
                        return;
                    }
                    LogUtil.h("CloudDeviceConnectManager", "btDevice is null");
                } catch (SecurityException e) {
                    LogUtil.b("CloudDeviceConnectManager", "onDeviceDiscovered SecurityException:", ExceptionUtils.d(e));
                }
            }
        }

        @Override // com.huawei.hwbtsdk.btdatatype.callback.BtDeviceDiscoverCallback
        public void onDeviceDiscoveryFinished() {
            LogUtil.c("CloudDeviceConnectManager", "onDeviceDiscoveryFinished");
        }

        @Override // com.huawei.hwbtsdk.btdatatype.callback.BtDeviceDiscoverCallback
        public void onDeviceDiscoveryCanceled() {
            LogUtil.c("CloudDeviceConnectManager", "onDeviceDiscoveryCanceled");
        }

        @Override // com.huawei.hwbtsdk.btdatatype.callback.BtDeviceDiscoverCallback
        public void onFailure(int i2, String str) {
            LogUtil.c("CloudDeviceConnectManager", "onFailure");
        }
    };

    /* synthetic */ void a(int i2, Object obj) {
        if (i2 == 0) {
            LogUtil.a("CloudDeviceConnectManager", "mHfpService set ok.");
            this.t = true;
            j();
        } else {
            LogUtil.h("CloudDeviceConnectManager", "mHfpService is null.");
            this.t = false;
            if (Build.VERSION.SDK_INT < 34) {
                k();
            }
        }
    }

    private obi() {
    }

    public static obi a() {
        obi obiVar;
        synchronized (d) {
            if (i == null) {
                i = new obi();
            }
            obiVar = i;
        }
        return obiVar;
    }

    private void j() {
        cuw c2 = jfu.c(this.v);
        this.n = c2;
        LogUtil.a("CloudDeviceConnectManager", "infoByType.getDeviceName() : ", CommonUtil.l(c2.f()));
        if (TextUtils.isEmpty(this.n.f())) {
            k();
            return;
        }
        Timer timer = new Timer("CloudDeviceConnectManager");
        this.m = timer;
        timer.schedule(new TimerTask() { // from class: obi.3
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                if (obi.this.t) {
                    synchronized (obi.d) {
                        if (obi.this.m != null) {
                            LogUtil.h("CloudDeviceConnectManager", "mHfpTimer cancel");
                            obi.this.m.cancel();
                        }
                    }
                    obi obiVar = obi.this;
                    if (!obiVar.b(obiVar.x)) {
                        LogUtil.a("CloudDeviceConnectManager", "mHfpTimer enter infoByType.getBtType()", Integer.valueOf(obi.this.n.d()));
                        iyl.d().e(oac.a(obi.this.v), obi.this.n.d(), obi.this.f);
                        return;
                    } else {
                        LogUtil.h("CloudDeviceConnectManager", "checkHfpDeviceList");
                        return;
                    }
                }
                LogUtil.h("CloudDeviceConnectManager", "HfpService is not ok.");
            }
        }, 1000L, 1000L);
        f();
        Timer timer2 = new Timer("CloudDeviceConnectManager");
        this.q = timer2;
        timer2.schedule(new TimerTask() { // from class: obi.2
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                obi.this.l();
            }
        }, 20000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        k();
        this.p = false;
        LogUtil.a("CloudDeviceConnectManager", "stop scan ble.");
        synchronized (d) {
            Timer timer = this.m;
            if (timer != null) {
                timer.cancel();
                this.m = null;
            }
        }
        iyl.d().b();
        this.t = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        Timer timer = this.q;
        if (timer != null) {
            timer.cancel();
            this.q = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b(String str) {
        List<BluetoothDevice> i2 = iyl.d().i();
        if (i2 != null && i2.size() > 0) {
            for (BluetoothDevice bluetoothDevice : i2) {
                try {
                } catch (SecurityException e) {
                    LogUtil.b("CloudDeviceConnectManager", "checkHfpDeviceList SecurityException:", ExceptionUtils.d(e));
                }
                if (!str.equalsIgnoreCase(bluetoothDevice.getName())) {
                    LogUtil.h("CloudDeviceConnectManager", "hfp device is : ", bluetoothDevice.getName());
                } else {
                    LogUtil.a("CloudDeviceConnectManager", "checkHfpDeviceList:", str);
                    cUu_(bluetoothDevice);
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cUu_(BluetoothDevice bluetoothDevice) {
        try {
            if (TextUtils.isEmpty(bluetoothDevice.getAddress())) {
                LogUtil.h("CloudDeviceConnectManager", "device address is null");
                return;
            }
            if (this.p) {
                return;
            }
            this.p = true;
            LogUtil.a("CloudDeviceConnectManager", "device address:", CommonUtil.l(bluetoothDevice.getAddress()));
            this.k = bluetoothDevice.getAddress();
            this.l = bluetoothDevice.getName() != null ? bluetoothDevice.getName() : this.x;
            if (bluetoothDevice.getType() != 1 && bluetoothDevice.getType() != 2) {
                this.g = this.n.d();
            } else {
                this.g = bluetoothDevice.getType();
            }
            e();
        } catch (SecurityException e) {
            LogUtil.b("CloudDeviceConnectManager", "connectAboutNormalDevice SecurityException:", ExceptionUtils.d(e));
        }
    }

    public void e() {
        WeakReference<Activity> weakReference = this.e;
        if (weakReference == null) {
            ReleaseLogUtil.d("R_CloudDeviceConnectManager", "checkConnectDevice mActivity == null");
            return;
        }
        Activity activity = weakReference.get();
        if (activity == null) {
            ReleaseLogUtil.d("R_CloudDeviceConnectManager", "checkConnectDevice activity == null");
            return;
        }
        if (obb.a(activity, this.g) && this.v != 75) {
            bkw.d().sK_(BluetoothAdapter.getDefaultAdapter().getRemoteDevice(this.k));
            b(activity);
            return;
        }
        c();
    }

    public void b(Context context) {
        obb.c(this.k, context, this.f15599a, this.g);
    }

    public void e(String str) {
        bmw.e(100112, this.l, str, String.valueOf(100010));
    }

    public void c() {
        if (CommonUtil.g(BaseApplication.getContext(), "com.huawei.ui.device.activity.pairing.DevicePairGuideActivity")) {
            LogUtil.h("CloudDeviceConnectManager", "DevicePairGuideActivity is aready in task");
            k();
        } else {
            final Activity activity = this.e.get();
            if (activity != null) {
                activity.runOnUiThread(new Runnable() { // from class: obk
                    @Override // java.lang.Runnable
                    public final void run() {
                        obi.this.cUF_(activity);
                    }
                });
            }
        }
    }

    /* synthetic */ void cUF_(Activity activity) {
        oaf.b(BaseApplication.getContext()).h(this.k);
        String f = jfu.c(this.v).f();
        if (bgb.d().isSupportH5Pair(this.l)) {
            String j = jfu.j(this.v);
            ArrayList arrayList = new ArrayList();
            arrayList.add(j);
            StartPairOption c2 = StartPairOption.builder().c(this.k).e(arrayList).d(this.l).b("wear_watch").a(true).c();
            BroadcastReceiver broadcastReceiver = this.r;
            if (broadcastReceiver != null && this.s) {
                cUy_(broadcastReceiver);
            }
            bgb.d().startPair(activity, c2);
            return;
        }
        Intent intent = new Intent(activity, (Class<?>) DevicePairGuideActivity.class);
        intent.putExtra("pairGuideProductType", this.v);
        intent.putExtra("pairGuideProductName", f);
        intent.putExtra("pairGuideFromScanList", true);
        intent.putExtra("pairGuideSelectName", this.l);
        intent.putExtra("pairGuideSelectAddress", this.k);
        intent.putExtra("pairGuideDeviceMode", 100010);
        intent.putExtra("cloudDevicePair", true);
        activity.startActivityForResult(intent, 1);
    }

    private void cUy_(BroadcastReceiver broadcastReceiver) {
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("com.huawei.health.action.PAIR_DEVICE_SUCCESS");
            LocalBroadcastManager.getInstance(BaseApplication.getContext()).registerReceiver(broadcastReceiver, intentFilter);
        } catch (IllegalArgumentException unused) {
            LogUtil.b("CloudDeviceConnectManager", "resisterReceiver, broadcastReceiver is error");
        }
    }

    public void b() {
        this.s = false;
        try {
            if (this.r != null) {
                LocalBroadcastManager.getInstance(BaseApplication.getContext()).unregisterReceiver(this.r);
            }
        } catch (IllegalArgumentException unused) {
            LogUtil.b("CloudDeviceConnectManager", "unregisterPairBroadcast is error");
        }
    }

    public void b(boolean z) {
        this.s = z;
    }

    public void cUG_(int i2, String str, Activity activity, ActivityResultLauncher<IntentSenderRequest> activityResultLauncher) {
        if (TextUtils.isEmpty(str) || activity == null) {
            return;
        }
        this.p = false;
        this.f15599a = activityResultLauncher;
        this.e = new WeakReference<>(activity);
        this.v = i2;
        this.x = str;
        LogUtil.a("CloudDeviceConnectManager", "mTargetType:", Integer.valueOf(i2), "mTargetDeviceName:", this.x);
        jfv.b(this.v, this.x, true);
        Activity activity2 = this.e.get();
        if (activity2 != null) {
            cuw c2 = jfu.c(this.v);
            if (Build.VERSION.SDK_INT > 30) {
                cUz_(h, activity2);
            } else if (c2.d() == 2) {
                cUz_(b, activity2);
            } else {
                cUz_(c, activity2);
            }
        }
    }

    private void cUz_(String[] strArr, Activity activity) {
        if (!jdi.c(activity, strArr)) {
            if (Arrays.asList(strArr).contains("android.permission.READ_PHONE_STATE")) {
                cUs_(PermissionUtil.PermissionType.PHONE_STATE, activity);
                return;
            } else if (Arrays.asList(strArr).contains("android.permission.BLUETOOTH_SCAN")) {
                cUs_(PermissionUtil.PermissionType.SCAN, activity);
                return;
            } else {
                cUs_(PermissionUtil.PermissionType.LOCATION, activity);
                return;
            }
        }
        i();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cUs_(final PermissionUtil.PermissionType permissionType, final Activity activity) {
        if (activity.isFinishing()) {
            return;
        }
        activity.runOnUiThread(new Runnable() { // from class: obi.7

            /* renamed from: obi$7$4, reason: invalid class name */
            class AnonymousClass4 extends CustomPermissionAction {
                AnonymousClass4(Context context) {
                    super(context);
                }

                @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                public void onGranted() {
                    LogUtil.a("CloudDeviceConnectManager", "permission allowed permissionType:", permissionType);
                    if (permissionType == PermissionUtil.PermissionType.LOCATION || permissionType == PermissionUtil.PermissionType.SCAN) {
                        obi.this.i();
                    } else {
                        obi.this.cUs_(PermissionUtil.PermissionType.LOCATION, activity);
                    }
                }

                @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                public void onDenied(String str) {
                    super.onDenied(str);
                    obi.this.k();
                    LogUtil.a("CloudDeviceConnectManager", "permission onDenied permission:", str);
                }

                @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                public void onForeverDenied(PermissionUtil.PermissionType permissionType) {
                    LogUtil.a("CloudDeviceConnectManager", "permission onDenied permission onForeverDenied");
                    obi.this.k();
                    if (activity.isFinishing()) {
                        return;
                    }
                    super.onForeverDenied(permissionType, new View.OnClickListener() { // from class: obo
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            obi.AnonymousClass7.AnonymousClass4.cUH_(view);
                        }
                    }, new View.OnClickListener() { // from class: obr
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            obi.AnonymousClass7.AnonymousClass4.cUI_(view);
                        }
                    });
                }

                static /* synthetic */ void cUH_(View view) {
                    LogUtil.c("CloudDeviceConnectManager", "permission onClick");
                    ViewClickInstrumentation.clickOnView(view);
                }

                static /* synthetic */ void cUI_(View view) {
                    LogUtil.c("CloudDeviceConnectManager", "onClick");
                    ViewClickInstrumentation.clickOnView(view);
                }
            }

            @Override // java.lang.Runnable
            public void run() {
                PermissionUtil.b(activity, permissionType, new AnonymousClass4(activity));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        jfv.b(this.v, this.x, false);
        Intent intent = new Intent();
        intent.setAction("com.huawei.health.action.CLOUD_CONNECTION_STATE_CHANGED");
        CloudDeviceInfo e = jfv.e(this.v, this.x);
        if (e != null) {
            LogUtil.a("CloudDeviceConnectManager", "cloudDeviceInfo is not null");
            intent.putExtra("deviceinfo", e);
        }
        BaseApplication.getContext().sendBroadcast(intent, LocalBroadcast.c);
    }

    public void a(int i2, String str) {
        this.v = i2;
        this.x = str;
        k();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        final Activity activity = this.e.get();
        if (activity == null) {
            LogUtil.h("CloudDeviceConnectManager", "getHarmonyStatus runActivity is null");
        } else if (DevicePairGuideUtil.d() != null) {
            activity.runOnUiThread(new Runnable() { // from class: obm
                @Override // java.lang.Runnable
                public final void run() {
                    obi.this.cUD_(activity);
                }
            });
        } else {
            ThreadPoolManager.d().d("CloudDeviceConnectManager", new Runnable() { // from class: obj
                @Override // java.lang.Runnable
                public final void run() {
                    obi.this.cUE_(activity);
                }
            });
        }
    }

    /* synthetic */ void cUD_(Activity activity) {
        if (Build.VERSION.SDK_INT > 30) {
            n();
        } else {
            cUt_(activity);
        }
    }

    /* synthetic */ void cUE_(final Activity activity) {
        LogUtil.a("CloudDeviceConnectManager", "getHarmonyStatus getValue");
        String e = jah.c().e("scale_share_harmony_tips");
        DevicePairGuideUtil.e(e);
        LogUtil.a("CloudDeviceConnectManager", "getHarmonyStatus scale_share_harmony_tips:", e);
        activity.runOnUiThread(new Runnable() { // from class: obi.9
            @Override // java.lang.Runnable
            public void run() {
                if (Build.VERSION.SDK_INT > 30) {
                    obi.this.n();
                } else {
                    obi.this.cUt_(activity);
                }
            }
        });
    }

    private boolean a(int i2) {
        return PermissionUtil.c() && i2 == 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cUt_(Activity activity) {
        String string;
        cuw c2 = jfu.c(this.v);
        if ((a(c2.d()) || c2.d() == 2) && !obb.b()) {
            k();
            LogUtil.h("CloudDeviceConnectManager", "checkGpsState open gps switch.");
            String string2 = BaseApplication.getContext().getString(R.string.ok);
            if ("on".equals(DevicePairGuideUtil.d())) {
                string = BaseApplication.getContext().getString(com.huawei.health.R.string._2130843755_res_0x7f02186b);
            } else {
                string = BaseApplication.getContext().getString(com.huawei.health.R.string._2130843258_res_0x7f02167a);
            }
            cUA_(activity, string2, string);
            return;
        }
        LogUtil.h("CloudDeviceConnectManager", "checkGpsState startScan");
        n();
    }

    private void cUA_(final Activity activity, String str, String str2) {
        NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(this.e.get()).e(str2).czE_(str, new View.OnClickListener() { // from class: obl
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                obi.cUx_(activity, view);
            }
        }).e();
        e.setCanceledOnTouchOutside(false);
        e.setCancelable(false);
        e.show();
    }

    static /* synthetic */ void cUx_(Activity activity, View view) {
        try {
            activity.startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
        } catch (ActivityNotFoundException e) {
            LogUtil.b("CloudDeviceConnectManager", "ActivityNotFoundException e:", e.getMessage());
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        LogUtil.a("CloudDeviceConnectManager", "start scan ble.");
        int g = iyl.d().g();
        if (g == 1 || g == 2) {
            h();
        } else if (g == 3 || g == 4) {
            iyl.d().a(BaseApplication.getContext(), this.o);
        } else {
            LogUtil.h("CloudDeviceConnectManager", "unknown state.");
            g();
        }
    }

    private void g() {
        final Activity activity = this.e.get();
        if (activity != null) {
            activity.runOnUiThread(new Runnable() { // from class: obf
                @Override // java.lang.Runnable
                public final void run() {
                    nrh.b(activity, nsn.ae(BaseApplication.getContext()) ? com.huawei.health.R.string._2130844214_res_0x7f021a36 : com.huawei.health.R.string._2130842174_res_0x7f02123e);
                }
            });
        }
    }

    private void h() {
        final Activity activity = this.e.get();
        if (activity != null) {
            activity.runOnUiThread(new Runnable() { // from class: obn
                @Override // java.lang.Runnable
                public final void run() {
                    obi.this.cUC_(activity);
                }
            });
        }
    }

    /* synthetic */ void cUC_(Activity activity) {
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(activity);
        builder.e(com.huawei.health.R.string._2130843262_res_0x7f02167e).czC_(com.huawei.health.R.string._2130841131_res_0x7f020e2b, new View.OnClickListener() { // from class: obh
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                obi.this.cUB_(view);
            }
        }).czz_(com.huawei.health.R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: obg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                obi.cUv_(view);
            }
        });
        if (activity.isFinishing()) {
            return;
        }
        NoTitleCustomAlertDialog e = builder.e();
        e.setCancelable(false);
        e.show();
    }

    /* synthetic */ void cUB_(View view) {
        iyl.d().d(this.j);
        ViewClickInstrumentation.clickOnView(view);
    }

    static /* synthetic */ void cUv_(View view) {
        LogUtil.c("CloudDeviceConnectManager", "bluetoothSwitch onClick");
        ViewClickInstrumentation.clickOnView(view);
    }
}
