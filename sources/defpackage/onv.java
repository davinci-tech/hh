package defpackage;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.huawei.datatype.GoogleDeviceCache;
import com.huawei.devicepair.model.StartPairOption;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.device.wifi.interfaces.CommBaseCallbackInterface;
import com.huawei.health.deviceconnect.callback.BtDeviceResponseCallback;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.devicemgr.business.entity.HwGetDevicesParameter;
import com.huawei.health.suggestion.model.FitRunPlayAudio;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbtsdk.btdatatype.callback.BtDeviceDiscoverCallback;
import com.huawei.hwbtsdk.btdatatype.callback.BtSwitchStateCallback;
import com.huawei.hwbtsdk.btdatatype.datatype.BluetoothDeviceNode;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.dialog.CommonDialog21;
import com.huawei.ui.commonui.dialog.CustomProgressDialog;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import com.huawei.ui.device.activity.adddevice.PairingGuideActivity;
import com.huawei.ui.device.activity.pairing.DevicePairGuideActivity;
import com.huawei.ui.device.activity.pairing.DevicePairGuideUtil;
import com.huawei.ui.homehealth.qrcode.activity.QrCodeScanningActivity;
import com.huawei.ui.homehealth.qrcode.activity.QrCodeSchemeActivity;
import com.huawei.ui.homehealth.qrcode.util.QrCodeBaseHandler;
import com.huawei.ui.homehealth.qrcode.util.QrCodeDataBase;
import com.tencent.connect.common.Constants;
import health.compact.a.CommonUtil;
import health.compact.a.EzPluginManager;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes6.dex */
public class onv extends QrCodeBaseHandler {

    /* renamed from: a, reason: collision with root package name */
    private onu f15801a;
    private cuw c;
    private iyl f;
    private BtSwitchStateCallback g;
    private CustomProgressDialog h;
    private CustomProgressDialog.Builder i;
    private int j;
    private onu k;
    private BtDeviceDiscoverCallback l;
    private String m;
    private CommonDialog21 n;
    private String o;
    private BtDeviceResponseCallback p;
    private Timer q;
    private boolean r;
    private CustomTextAlertDialog s;
    private boolean t;
    private BroadcastReceiver u;
    private String v;
    private int w;
    private Timer x;
    private NoTitleCustomAlertDialog y;
    private static final String[] e = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"};
    private static final String[] d = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION", "android.permission.READ_PHONE_STATE"};
    private static final String[] b = {"android.permission.BLUETOOTH_CONNECT", "android.permission.BLUETOOTH_SCAN"};

    @Override // com.huawei.ui.homehealth.qrcode.util.QrCodeBaseHandler
    public void handleMessage(Message message) {
    }

    @Override // com.huawei.ui.homehealth.qrcode.util.QrCodeBaseHandler
    public void mainHandleMessage(Message message, Activity activity) {
    }

    public onv(Activity activity, CommBaseCallbackInterface commBaseCallbackInterface) {
        super(activity, commBaseCallbackInterface);
        this.x = null;
        this.q = null;
        this.n = null;
        this.y = null;
        this.r = false;
        this.t = false;
        this.f15801a = null;
        this.u = new BroadcastReceiver() { // from class: onv.3
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                if (intent == null) {
                    LogUtil.h("DeviceQrCodeHandle", "mProcessLocalBroadcastReceiver intent is null");
                    return;
                }
                if ("com.huawei.health.action.DOWNLOAD_SINGLE_DEVICE_PROCESS".equals(intent.getAction())) {
                    onv.this.a(intent.getIntExtra("progress", -1));
                    return;
                }
                if ("com.huawei.health.action.ACTION_BACKGROUND_DOWNLOAD_DEVICE".equals(intent.getAction())) {
                    LogUtil.h("DeviceQrCodeHandle", "mProcessLocalBroadcastReceiver ACTION_BACKGROUND_DOWNLOAD_DEVICE");
                    onv.this.ah();
                    Activity activity2 = (Activity) onv.this.mActivity.get();
                    if (activity2 != null) {
                        activity2.runOnUiThread(new Runnable() { // from class: onv.3.5
                            @Override // java.lang.Runnable
                            public void run() {
                                onv.this.g();
                                if (onv.this.verify(onv.this.f15801a)) {
                                    onv.this.execute();
                                } else {
                                    LogUtil.b("DeviceQrCodeHandle", "Data validation failed");
                                }
                            }
                        });
                        return;
                    } else {
                        LogUtil.h("DeviceQrCodeHandle", "runActivity is null");
                        return;
                    }
                }
                LogUtil.a("DeviceQrCodeHandle", "mProcessLocalBroadcastReceiver()  intent : ", intent.getAction());
            }
        };
        this.g = new BtSwitchStateCallback() { // from class: onv.15
            @Override // com.huawei.hwbtsdk.btdatatype.callback.BtSwitchStateCallback
            public void onBtSwitchStateCallback(int i) {
                LogUtil.a("DeviceQrCodeHandle", "btSwitchState:", Integer.valueOf(i));
                if (i == 1) {
                    onv.this.j();
                    onv.this.ai();
                    onv.this.i();
                    onv.this.f.e(onv.this.g);
                    onv.this.n();
                    return;
                }
                LogUtil.h("R_QrCode_DeviceQrCodeHandle", "Bluetooth unknown state.");
            }
        };
        this.l = new BtDeviceDiscoverCallback() { // from class: onv.21
            @Override // com.huawei.hwbtsdk.btdatatype.callback.BtDeviceDiscoverCallback
            public void onDeviceDiscoveryCanceled() {
            }

            @Override // com.huawei.hwbtsdk.btdatatype.callback.BtDeviceDiscoverCallback
            public void onFailure(int i, String str) {
            }

            @Override // com.huawei.hwbtsdk.btdatatype.callback.BtDeviceDiscoverCallback
            public void onDeviceDiscovered(BluetoothDeviceNode bluetoothDeviceNode, int i, byte[] bArr) {
                if (bluetoothDeviceNode != null) {
                    try {
                        BluetoothDevice btDevice = bluetoothDeviceNode.getBtDevice();
                        if (btDevice != null) {
                            String name = btDevice.getName();
                            if (name == null) {
                                name = bluetoothDeviceNode.getRecordName();
                            }
                            LogUtil.a("DeviceQrCodeHandle", "device name : ", CommonUtil.l(name));
                            onv.this.ddD_(name, btDevice, i);
                        }
                    } catch (SecurityException e2) {
                        LogUtil.b("DeviceQrCodeHandle", "onDeviceDiscovered SecurityException:", ExceptionUtils.d(e2));
                    }
                }
            }

            @Override // com.huawei.hwbtsdk.btdatatype.callback.BtDeviceDiscoverCallback
            public void onDeviceDiscoveryFinished() {
                LogUtil.a("DeviceQrCodeHandle", "onDeviceDiscoveryFinished enter.");
                if (onv.this.n != null && onv.this.n.isShowing()) {
                    onv.this.ai();
                    onv.this.f.a(BaseApplication.getContext(), onv.this.p);
                    onv.this.ad();
                }
            }
        };
        this.p = new BtDeviceResponseCallback() { // from class: onv.24
            @Override // com.huawei.health.deviceconnect.callback.BtDeviceResponseCallback
            public void onResponse(int i, Object obj) {
                if (i == 0) {
                    LogUtil.a("DeviceQrCodeHandle", "mHfpService set ok.");
                    onv.this.r = true;
                } else {
                    LogUtil.b("R_QrCode_DeviceQrCodeHandle", "mHfpService is null.");
                    onv.this.r = false;
                }
            }
        };
        iyl d2 = iyl.d();
        this.f = d2;
        d2.a(BaseApplication.getContext(), this.p);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ddD_(String str, BluetoothDevice bluetoothDevice, int i) {
        try {
            if (this.v.equalsIgnoreCase(str)) {
                if (jfu.f(this.w) && !this.t) {
                    ai();
                    GoogleDeviceCache.QrBleCache qrBleCache = new GoogleDeviceCache.QrBleCache();
                    qrBleCache.setBluetoothDevice(bluetoothDevice);
                    qrBleCache.setRssi(i);
                    qrBleCache.setTime(System.currentTimeMillis());
                    GoogleDeviceCache.getInstance().saveCache(qrBleCache);
                    i();
                    j();
                    s();
                    this.t = true;
                } else if (this.t) {
                    LogUtil.a("DeviceQrCodeHandle", "no target device : ", CommonUtil.l(bluetoothDevice.getName()));
                } else {
                    ddH_(bluetoothDevice, str);
                }
            }
        } catch (SecurityException e2) {
            LogUtil.b("DeviceQrCodeHandle", "doJump SecurityException:", ExceptionUtils.d(e2));
        }
    }

    private void ddH_(BluetoothDevice bluetoothDevice, String str) {
        this.t = true;
        i();
        ai();
        j();
        try {
            if (bluetoothDevice.getName() != null) {
                str = bluetoothDevice.getName();
            }
            this.m = str;
            this.o = bluetoothDevice.getAddress();
            int i = this.j;
            if (i != 1 && i != 2) {
                this.j = this.c.d();
            } else {
                this.j = bluetoothDevice.getType();
            }
            d();
        } catch (SecurityException e2) {
            LogUtil.b("DeviceQrCodeHandle", "scanedDeviceAboutNormalDevice SecurityException:", ExceptionUtils.d(e2));
        }
    }

    public void d() {
        Activity activity = this.mActivity.get();
        if (activity == null) {
            return;
        }
        if (obb.a(activity, this.j)) {
            new HwGetDevicesParameter().setDeviceIdentify(this.o);
            if (!koq.b(cun.c().getDeviceList(HwGetDevicesMode.IDENTIFY_DEVICES, null, "DeviceQrCodeHandle"))) {
                SharedPreferenceManager.e(Integer.toString(1000), "wearable_unpair_reconnection", true);
                ArrayList arrayList = new ArrayList(16);
                arrayList.add(this.o);
                oae.c(BaseApplication.getContext()).e(arrayList, true);
            } else {
                bkw.d().sK_(BluetoothAdapter.getDefaultAdapter().getRemoteDevice(this.o));
            }
            ddL_(activity);
            return;
        }
        a();
    }

    public void ddL_(Activity activity) {
        if (activity instanceof QrCodeScanningActivity) {
            obb.c(this.o, activity, ((QrCodeScanningActivity) activity).d, this.j);
        } else if (activity instanceof QrCodeSchemeActivity) {
            obb.c(this.o, activity, ((QrCodeSchemeActivity) activity).e, this.j);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        Timer timer = this.x;
        if (timer != null) {
            timer.cancel();
            this.x = null;
        }
    }

    public void a(String str) {
        bmw.e(100112, this.m, str, String.valueOf(100003));
    }

    public void a() {
        final Activity activity = this.mActivity.get();
        if (activity != null) {
            activity.runOnUiThread(new Runnable() { // from class: onv.22
                @Override // java.lang.Runnable
                public void run() {
                    oaf.b(activity).h(onv.this.o);
                    String f = jfu.c(onv.this.w).f();
                    if (bgb.d().isSupportH5Pair(onv.this.m)) {
                        String j = jfu.j(onv.this.w);
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(j);
                        bgb.d().startPair(activity, StartPairOption.builder().c(onv.this.o).e(arrayList).d(onv.this.m).b("wear_watch").a(false).c());
                        activity.finish();
                        return;
                    }
                    Intent intent = new Intent(activity, (Class<?>) DevicePairGuideActivity.class);
                    intent.putExtra("pairGuideProductType", onv.this.w);
                    intent.putExtra("pairGuideProductName", f);
                    intent.putExtra("pairGuideFromScanList", true);
                    intent.putExtra("pairGuideSelectName", onv.this.m);
                    intent.putExtra("pairGuideSelectAddress", onv.this.o);
                    intent.putExtra("pairGuideDeviceMode", 100003);
                    if (onv.this.k != null) {
                        intent.putExtra("pairGuideProductPin", onv.this.k.a());
                    }
                    activity.startActivityForResult(intent, 1);
                }
            });
        }
    }

    @Override // com.huawei.ui.homehealth.qrcode.util.QrCodeBaseHandler
    public QrCodeDataBase parser(String str, Object obj) {
        if (str == null) {
            ReleaseLogUtil.d("R_QrCode_DeviceQrCodeHandle", "parser action is null.");
            return this.f15801a;
        }
        if (!(obj instanceof String)) {
            ReleaseLogUtil.d("R_QrCode_DeviceQrCodeHandle", "data is null or error.");
            return this.f15801a;
        }
        String str2 = (String) obj;
        String str3 = str + opf.b(FitRunPlayAudio.PLAY_TYPE_T, str2);
        if (str2.startsWith("&")) {
            try {
                str2 = URLDecoder.decode(str2.substring(1), "UTF-8");
            } catch (UnsupportedEncodingException unused) {
                LogUtil.b("DeviceQrCodeHandle", "parser UnsupportedEncodingException");
            }
        }
        LogUtil.a("DeviceQrCodeHandle", "codeResult:", str2, "actionType:", str3);
        onu onuVar = new onu(str3);
        this.f15801a = onuVar;
        int parser = onuVar.parser(str2);
        bmw.e(100004, this.f15801a.c(), String.valueOf(parser), "");
        if (parser == 0) {
            return this.f15801a;
        }
        switch (parser) {
            case Constants.ERROR_QQ_NOT_LOGIN /* -21 */:
                t();
                break;
            case Constants.ERROR_QQ_NOT_INSTALL /* -20 */:
                LogUtil.h("DeviceQrCodeHandle", "DEVICE_TO_DOWNLOAD");
                z();
                v();
                break;
            case Constants.ERROR_NO_AUTHORITY /* -19 */:
                ab();
                break;
            default:
                r();
                break;
        }
        return this.f15801a;
    }

    private void v() {
        LogUtil.a("DeviceQrCodeHandle", "enter registerNonLocalBroadcastReceiver");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.health.action.DOWNLOAD_SINGLE_DEVICE_PROCESS");
        intentFilter.addAction("com.huawei.health.action.ACTION_BACKGROUND_DOWNLOAD_DEVICE");
        LocalBroadcastManager.getInstance(BaseApplication.getContext()).registerReceiver(this.u, intentFilter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ah() {
        if (this.u != null) {
            try {
                LocalBroadcastManager.getInstance(BaseApplication.getContext()).unregisterReceiver(this.u);
                LogUtil.a("DeviceQrCodeHandle", "unRegisterProcessBroadcastReceiver unregister");
                return;
            } catch (IllegalArgumentException unused) {
                LogUtil.b("DeviceQrCodeHandle", "IllegalArgumentException unRegisterProcessBroadcastReceiver unregister");
                return;
            }
        }
        LogUtil.a("DeviceQrCodeHandle", "mProcessLocalBroadcastReceiver is null");
    }

    private void t() {
        final Activity activity = this.mActivity.get();
        if (activity == null) {
            LogUtil.b("DeviceQrCodeHandle", "startDownLoadProgress activity is null");
        } else {
            activity.runOnUiThread(new Runnable() { // from class: onv.23
                @Override // java.lang.Runnable
                public void run() {
                    nrh.b(activity, R.string._2130841393_res_0x7f020f31);
                    onv.this.n();
                }
            });
        }
    }

    private void z() {
        CustomProgressDialog customProgressDialog = this.h;
        if (customProgressDialog != null && customProgressDialog.isShowing()) {
            LogUtil.a("DeviceQrCodeHandle", "The progress bar already exists");
            return;
        }
        final Activity activity = this.mActivity.get();
        if (activity == null) {
            LogUtil.b("DeviceQrCodeHandle", "startDownLoadProgress activity is null");
        } else {
            activity.runOnUiThread(new Runnable() { // from class: onv.25
                @Override // java.lang.Runnable
                public void run() {
                    onv.this.i = new CustomProgressDialog.Builder(activity);
                    onv.this.i.d(activity.getString(R.string.IDS_hw_health_wear_update_device_resource_text)).cyH_(new View.OnClickListener() { // from class: onv.25.4
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            LogUtil.a("DeviceQrCodeHandle", "Click Cancel");
                            onv.this.o();
                            ViewClickInstrumentation.clickOnView(view);
                        }
                    });
                    onv onvVar = onv.this;
                    onvVar.h = onvVar.i.e();
                    onv.this.h.setCanceledOnTouchOutside(false);
                    if (!activity.isFinishing()) {
                        onv.this.h.show();
                        onv.this.i.d(0);
                        onv.this.i.e(UnitUtil.e(0.0d, 2, 0));
                    }
                    LogUtil.a("DeviceQrCodeHandle", "mCustomProgressDialog.show()");
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        if (i == -1) {
            oau.c(100101, "");
            o();
            return;
        }
        CustomProgressDialog customProgressDialog = this.h;
        if (customProgressDialog == null || this.i == null || !customProgressDialog.isShowing() || i < 0) {
            return;
        }
        String e2 = UnitUtil.e(i, 2, 0);
        this.i.d(i);
        this.i.e(e2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        LogUtil.a("DeviceQrCodeHandle", "enter handleCancel");
        msq e2 = msn.c().e(jfu.d(this.w));
        if (e2 != null) {
            EzPluginManager.a().a(e2);
        }
        m();
    }

    private void m() {
        g();
        ah();
        x();
    }

    private void x() {
        CustomTextAlertDialog customTextAlertDialog = this.s;
        if (customTextAlertDialog == null || !customTextAlertDialog.isShowing()) {
            final Activity activity = this.mActivity.get();
            if (activity == null) {
                LogUtil.b("DeviceQrCodeHandle", "showErrorLayoutDialog activity is null");
            } else {
                activity.runOnUiThread(new Runnable() { // from class: onv.26
                    @Override // java.lang.Runnable
                    public void run() {
                        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(activity);
                        builder.b(R.string.IDS_service_area_notice_title).d(R.string.IDS_device_device_list_update_failed).cyU_(R.string._2130841794_res_0x7f0210c2, new View.OnClickListener() { // from class: onv.26.1
                            @Override // android.view.View.OnClickListener
                            public void onClick(View view) {
                                if (onv.this.s != null) {
                                    onv.this.s.dismiss();
                                    onv.this.s = null;
                                    onv.this.n();
                                }
                                ViewClickInstrumentation.clickOnView(view);
                            }
                        });
                        onv.this.s = builder.a();
                        onv.this.s.setCancelable(false);
                        onv.this.s.show();
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        LogUtil.a("DeviceQrCodeHandle", "enter closeProgress");
        if (this.h == null) {
            return;
        }
        Activity activity = this.mActivity.get();
        if (activity == null) {
            LogUtil.b("DeviceQrCodeHandle", "closeProgress activity is null");
            return;
        }
        if (this.h.isShowing() && !activity.isFinishing()) {
            this.h.dismiss();
            LogUtil.a("DeviceQrCodeHandle", "enter closeProgress cancel");
        }
        this.h = null;
        this.i = null;
    }

    @Override // com.huawei.ui.homehealth.qrcode.util.QrCodeBaseHandler
    public boolean verify(QrCodeDataBase qrCodeDataBase) {
        if (qrCodeDataBase != null) {
            if (qrCodeDataBase instanceof onu) {
                onu onuVar = (onu) qrCodeDataBase;
                if (!onuVar.b()) {
                    LogUtil.a("DeviceQrCodeHandle", "verify ok. return true.");
                    this.k = onuVar;
                    return true;
                }
                LogUtil.a("R_QrCode_DeviceQrCodeHandle", "parse result is empty or loss info.");
                sendCallBack(-3, qrCodeDataBase.getAction(), null);
            } else {
                sendCallBack(-4, qrCodeDataBase.getAction(), null);
                LogUtil.b("R_QrCode_DeviceQrCodeHandle", "device qrcodeData type is error,");
            }
        } else {
            LogUtil.b("R_QrCode_DeviceQrCodeHandle", "device qrcodeData is null");
        }
        return true;
    }

    @Override // com.huawei.ui.homehealth.qrcode.util.QrCodeBaseHandler
    public void execute() {
        ReleaseLogUtil.e("R_QrCode_DeviceQrCodeHandle", "execute");
        if (this.k == null || this.mActivity == null) {
            LogUtil.h("DeviceQrCodeHandle", "mData or mActivity is null");
            return;
        }
        LogUtil.a("DeviceQrCodeHandle", "deviceName : ", this.k.c(), " , type : ", Integer.valueOf(this.k.d()));
        int d2 = this.k.d();
        this.w = d2;
        boolean m = jfu.m(d2);
        LogUtil.a("DeviceQrCodeHandle", "isPlugin:", Boolean.valueOf(m));
        if (!CommonUtil.as() && m) {
            boolean d3 = ntt.d(((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getPluginInfoByUuid(jfu.j(this.w)), this.w, CommonUtil.d(BaseApplication.getContext()));
            LogUtil.a("DeviceQrCodeHandle", "isUpdate:", Boolean.valueOf(d3));
            final Activity activity = this.mActivity.get();
            if (d3 && activity != null) {
                activity.runOnUiThread(new Runnable() { // from class: onv.30
                    @Override // java.lang.Runnable
                    public void run() {
                        ntt.cNL_(BaseApplication.getContext(), activity, true);
                    }
                });
                return;
            }
        }
        Activity activity2 = this.mActivity.get();
        if (activity2 != null && !obb.e(activity2)) {
            ddI_(activity2);
            return;
        }
        String j = jfu.j(this.w);
        if (((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).isResourcesAvailable(j)) {
            if (!TextUtils.isEmpty(this.k.c())) {
                q();
                return;
            } else {
                ddE_(activity2);
                return;
            }
        }
        LogUtil.h("DeviceQrCodeHandle", "isAvaiable is false,uuid:", j);
    }

    private void ddE_(final Activity activity) {
        LogUtil.a("DeviceQrCodeHandle", "enter jumpToPairingGuideActivity");
        List<cvk> indexList = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getIndexList();
        if (indexList == null) {
            LogUtil.h("DeviceQrCodeHandle", "deviceList is null");
            ab();
            return;
        }
        for (cvk cvkVar : indexList) {
            if (cvkVar.g() != null) {
                if (jfu.j(this.w).equals(cvkVar.e()) && !cwc.e(cvkVar.i())) {
                    LogUtil.h("DeviceQrCodeHandle", "app version is not supported");
                    ab();
                    return;
                }
            }
        }
        activity.runOnUiThread(new Runnable() { // from class: onv.2
            @Override // java.lang.Runnable
            public void run() {
                onv.this.ddF_(activity);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ddF_(Activity activity) {
        String str;
        String str2;
        Intent intent = new Intent(activity, (Class<?>) PairingGuideActivity.class);
        String j = jfu.j(this.w);
        Iterator<cve> it = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getDeviceList().iterator();
        while (true) {
            str = "2";
            if (!it.hasNext()) {
                str2 = "wear_watch";
                break;
            }
            cve next = it.next();
            if (next.ac().contains(j)) {
                str2 = next.r();
                Map<String, String> w = next.w();
                if (w != null && !w.isEmpty()) {
                    str = w.get(j);
                }
                if (TextUtils.isEmpty(str)) {
                    str = next.t();
                }
                LogUtil.a("DeviceQrCodeHandle", "kindId:", str2);
            }
        }
        intent.putExtra("kind_id", str2);
        intent.putExtra("pair_guide", str);
        intent.putExtra("is_scan_to_pair_guide", true);
        ArrayList<String> arrayList = new ArrayList<>(16);
        arrayList.add(j);
        intent.putStringArrayListExtra("uuid_list", arrayList);
        try {
            activity.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("DeviceQrCodeHandle", "jumpToPairingGuideIntent not found");
        }
        n();
    }

    private static void ddI_(final Activity activity) {
        activity.runOnUiThread(new Runnable() { // from class: onv.5
            @Override // java.lang.Runnable
            public void run() {
                NoTitleCustomAlertDialog e2 = new NoTitleCustomAlertDialog.Builder(activity).e(R.string._2130844216_res_0x7f021a38).czC_(R.string._2130843756_res_0x7f02186c, new View.OnClickListener() { // from class: onv.5.4
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        LogUtil.a("DeviceQrCodeHandle", "Bluetooth not supported");
                        activity.finish();
                        ViewClickInstrumentation.clickOnView(view);
                    }
                }).e();
                e2.setCancelable(false);
                e2.show();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        if (jfu.f(this.k.d())) {
            if (nrj.b()) {
                b(this.k.c());
                return;
            } else {
                p();
                return;
            }
        }
        b(this.k.c());
    }

    private void ac() {
        LogUtil.a("DeviceQrCodeHandle", "enter showWaitDialog.");
        final Activity activity = this.mActivity.get();
        if (activity != null) {
            activity.runOnUiThread(new Runnable() { // from class: onv.4
                @Override // java.lang.Runnable
                public void run() {
                    LogUtil.a("DeviceQrCodeHandle", "showWaitDialog. run");
                    if (!activity.isFinishing()) {
                        if (onv.this.n == null) {
                            onv onvVar = onv.this;
                            new CommonDialog21(activity, R.style.app_update_dialogActivity);
                            onvVar.n = CommonDialog21.a(activity);
                        }
                        onv.this.n.e(activity.getString(R.string._2130843058_res_0x7f0215b2));
                        onv.this.n.setCancelable(false);
                        if (activity.isFinishing()) {
                            return;
                        }
                        onv.this.n.show();
                        return;
                    }
                    LogUtil.b("R_QrCode_DeviceQrCodeHandle", "showWaitDialog is null.");
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        final Activity activity = this.mActivity.get();
        if (activity != null) {
            activity.runOnUiThread(new Runnable() { // from class: onv.1
                @Override // java.lang.Runnable
                public void run() {
                    if (!activity.isFinishing() && onv.this.n != null && !activity.isDestroyed()) {
                        onv.this.n.dismiss();
                        onv.this.n = null;
                    } else {
                        LogUtil.a("DeviceQrCodeHandle", "no need dismiss mDialog.");
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w() {
        ReleaseLogUtil.e("R_QrCode_DeviceQrCodeHandle", "enter showNoFindDevice");
        final Activity activity = this.mActivity.get();
        if (activity != null) {
            activity.runOnUiThread(new Runnable() { // from class: onv.6
                @Override // java.lang.Runnable
                public void run() {
                    onv.this.j();
                    NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(activity);
                    builder.e(R.string.IDS_qrcode_no_find_device);
                    builder.czC_(R.string.IDS_qrcode_rescan_device, new View.OnClickListener() { // from class: onv.6.2
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            onv.this.y.dismiss();
                            onv.this.u();
                            ViewClickInstrumentation.clickOnView(view);
                        }
                    });
                    builder.czz_(R.string._2130841939_res_0x7f021153, new View.OnClickListener() { // from class: onv.6.3
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            onv.this.y.dismiss();
                            activity.finish();
                            ViewClickInstrumentation.clickOnView(view);
                        }
                    });
                    onv.this.y = builder.e();
                    onv.this.y.setCancelable(false);
                    if (activity.isFinishing()) {
                        return;
                    }
                    onv.this.y.show();
                }
            });
        }
    }

    private void ab() {
        final Activity activity = this.mActivity.get();
        if (activity != null) {
            activity.runOnUiThread(new Runnable() { // from class: onv.10
                @Override // java.lang.Runnable
                public void run() {
                    NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(activity);
                    builder.e(activity.getResources().getString(R.string.IDS_qrcode_not_support_device));
                    builder.czz_(R.string._2130844420_res_0x7f021b04, new View.OnClickListener() { // from class: onv.10.3
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            onv.this.n();
                            ViewClickInstrumentation.clickOnView(view);
                        }
                    });
                    if (activity.isFinishing()) {
                        return;
                    }
                    NoTitleCustomAlertDialog e2 = builder.e();
                    e2.setCancelable(false);
                    e2.show();
                }
            });
        }
    }

    private void r() {
        final Activity activity = this.mActivity.get();
        if (activity != null) {
            activity.runOnUiThread(new Runnable() { // from class: onv.9
                @Override // java.lang.Runnable
                public void run() {
                    NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(activity);
                    builder.e(activity.getResources().getString(R.string._2130843028_res_0x7f021594));
                    builder.czE_(activity.getResources().getString(R.string._2130841794_res_0x7f0210c2), new View.OnClickListener() { // from class: onv.9.2
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            LogUtil.a("R_QrCode_DeviceQrCodeHandle", "noRecognitionDialog click ok.");
                            onv.this.n();
                            ViewClickInstrumentation.clickOnView(view);
                        }
                    });
                    if (activity.isFinishing()) {
                        return;
                    }
                    NoTitleCustomAlertDialog e2 = builder.e();
                    e2.setCancelable(false);
                    e2.show();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p() {
        final Activity activity = this.mActivity.get();
        if (activity != null) {
            activity.runOnUiThread(new Runnable() { // from class: onv.8
                @Override // java.lang.Runnable
                public void run() {
                    nrj.cKb_(activity, new View.OnClickListener() { // from class: onv.8.5
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            LogUtil.a("DeviceQrCodeHandle", "uninstallShowDialog negative.");
                            onv.this.n();
                            ViewClickInstrumentation.clickOnView(view);
                        }
                    }, new View.OnClickListener() { // from class: onv.8.2
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            if ("network_disconnected".equals(view.getTag())) {
                                LogUtil.a("R_QrCode_DeviceQrCodeHandle", "no net.");
                                onv.this.n();
                            } else if ("network_connected".equals(view.getTag())) {
                                LogUtil.a("R_QrCode_DeviceQrCodeHandle", "jump download.");
                            } else {
                                LogUtil.a("R_QrCode_DeviceQrCodeHandle", "unknown tag.");
                            }
                            ViewClickInstrumentation.clickOnView(view);
                        }
                    });
                }
            });
        }
    }

    private void b(String str) {
        this.v = str;
        SharedPreferenceManager.c(String.valueOf(10), "app_big_data_device_name", str);
        Activity activity = this.mActivity.get();
        if (activity != null) {
            cuw c = jfu.c(this.w);
            if (Build.VERSION.SDK_INT > 30) {
                ddG_(b, activity);
            } else if (c.d() == 2) {
                ddG_(d, activity);
            } else {
                ddG_(e, activity);
            }
        }
    }

    private void ddJ_(final Activity activity, String str, String str2) {
        NoTitleCustomAlertDialog e2 = new NoTitleCustomAlertDialog.Builder(this.mActivity.get()).e(str2).czE_(str, new View.OnClickListener() { // from class: onv.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                activity.startActivityForResult(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"), 3);
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e();
        e2.setCanceledOnTouchOutside(false);
        e2.setCancelable(false);
        e2.show();
    }

    private void ddG_(String[] strArr, Activity activity) {
        if (!jdi.c(activity, strArr)) {
            if (Arrays.asList(strArr).contains("android.permission.READ_PHONE_STATE")) {
                ddB_(PermissionUtil.PermissionType.PHONE_STATE, activity);
                return;
            } else if (Arrays.asList(strArr).contains("android.permission.BLUETOOTH_SCAN")) {
                ddB_(PermissionUtil.PermissionType.SCAN, activity);
                return;
            } else {
                ddB_(PermissionUtil.PermissionType.LOCATION, activity);
                return;
            }
        }
        k();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ddB_(PermissionUtil.PermissionType permissionType, Activity activity) {
        LogUtil.a("DeviceQrCodeHandle", "applyLocationAndPhoneStatePermission", permissionType);
        if (activity.isFinishing()) {
            return;
        }
        activity.runOnUiThread(new AnonymousClass13(activity, permissionType));
    }

    /* renamed from: onv$13, reason: invalid class name */
    class AnonymousClass13 implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ PermissionUtil.PermissionType f15806a;
        final /* synthetic */ Activity b;

        AnonymousClass13(Activity activity, PermissionUtil.PermissionType permissionType) {
            this.b = activity;
            this.f15806a = permissionType;
        }

        @Override // java.lang.Runnable
        public void run() {
            PermissionUtil.b(this.b, this.f15806a, new CustomPermissionAction(this.b) { // from class: onv.13.2
                @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                public void onGranted() {
                    LogUtil.a("DeviceQrCodeHandle", "permission allowed permissionType:", AnonymousClass13.this.f15806a);
                    if (AnonymousClass13.this.f15806a == PermissionUtil.PermissionType.LOCATION || AnonymousClass13.this.f15806a == PermissionUtil.PermissionType.SCAN) {
                        onv.this.k();
                    } else {
                        onv.this.ddB_(PermissionUtil.PermissionType.LOCATION, AnonymousClass13.this.b);
                    }
                }

                @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                public void onDenied(String str) {
                    super.onDenied(str);
                    LogUtil.a("DeviceQrCodeHandle", "permission onDenied permission:", str);
                    if (AnonymousClass13.this.b.isFinishing()) {
                        return;
                    }
                    super.onForeverDenied(AnonymousClass13.this.f15806a, new View.OnClickListener() { // from class: onv.13.2.2
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            onv.this.n();
                            ViewClickInstrumentation.clickOnView(view);
                        }
                    }, new View.OnClickListener() { // from class: onv.13.2.5
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            onv.this.n();
                            ViewClickInstrumentation.clickOnView(view);
                        }
                    });
                }

                @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                public void onForeverDenied(PermissionUtil.PermissionType permissionType) {
                    LogUtil.a("DeviceQrCodeHandle", "permission onForeverDenied:", permissionType);
                    if (!AnonymousClass13.this.b.isFinishing()) {
                        super.onForeverDenied(permissionType, new View.OnClickListener() { // from class: onv.13.2.1
                            @Override // android.view.View.OnClickListener
                            public void onClick(View view) {
                                onv.this.n();
                                ViewClickInstrumentation.clickOnView(view);
                            }
                        }, new View.OnClickListener() { // from class: onv.13.2.4
                            @Override // android.view.View.OnClickListener
                            public void onClick(View view) {
                                onv.this.n();
                                ViewClickInstrumentation.clickOnView(view);
                            }
                        });
                    } else {
                        super.onForeverDenied(permissionType);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        final Activity activity = this.mActivity.get();
        if (activity == null) {
            LogUtil.h("DeviceQrCodeHandle", "getHarmonyStatus runActivity is null");
        } else if (DevicePairGuideUtil.d() != null) {
            activity.runOnUiThread(new Runnable() { // from class: onv.12
                @Override // java.lang.Runnable
                public void run() {
                    if (Build.VERSION.SDK_INT > 30) {
                        onv.this.aa();
                    } else {
                        onv.this.ddC_(activity);
                    }
                }
            });
        } else {
            ThreadPoolManager.d().d("DeviceQrCodeHandle", new Runnable() { // from class: onv.14
                @Override // java.lang.Runnable
                public void run() {
                    LogUtil.a("DeviceQrCodeHandle", "getHarmonyStatus getValue");
                    String e2 = jah.c().e("scale_share_harmony_tips");
                    DevicePairGuideUtil.e(e2);
                    LogUtil.a("DeviceQrCodeHandle", "getHarmonyStatus scale_share_harmony_tips:", e2);
                    activity.runOnUiThread(new Runnable() { // from class: onv.14.3
                        @Override // java.lang.Runnable
                        public void run() {
                            if (Build.VERSION.SDK_INT > 30) {
                                onv.this.aa();
                            } else {
                                onv.this.ddC_(activity);
                            }
                        }
                    });
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ddC_(Activity activity) {
        String string;
        cuw c = jfu.c(this.w);
        if ((e(c.d()) || c.d() == 2) && !obb.b()) {
            LogUtil.h("DeviceQrCodeHandle", "checkGpsState open gps switch.");
            String string2 = BaseApplication.getContext().getString(android.R.string.ok);
            if ("on".equals(DevicePairGuideUtil.d())) {
                string = BaseApplication.getContext().getString(R.string._2130843755_res_0x7f02186b);
            } else {
                string = BaseApplication.getContext().getString(R.string._2130843258_res_0x7f02167a);
            }
            ddJ_(activity, string2, string);
            return;
        }
        aa();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aa() {
        ReleaseLogUtil.e("R_QrCode_DeviceQrCodeHandle", "start scan ble.");
        int g = this.f.g();
        if (g == 1 || g == 2) {
            e();
        } else if (g == 3 || g == 4) {
            y();
        } else {
            LogUtil.h("R_QrCode_DeviceQrCodeHandle", "unknown state.");
            h();
        }
    }

    private void h() {
        final Activity activity = this.mActivity.get();
        if (activity != null) {
            activity.runOnUiThread(new Runnable() { // from class: onv.11
                @Override // java.lang.Runnable
                public void run() {
                    nrh.b(activity, nsn.ae(BaseApplication.getContext()) ? R.string._2130844214_res_0x7f021a36 : R.string._2130842174_res_0x7f02123e);
                    onv.this.n();
                }
            });
        }
    }

    private void y() {
        cuw c = jfu.c(this.w);
        this.c = c;
        if (TextUtils.isEmpty(c.f())) {
            ab();
            LogUtil.a("DeviceQrCodeHandle", "no support type : ", Integer.valueOf(this.w));
            return;
        }
        ac();
        Timer timer = new Timer("DeviceQrCodeHandle");
        this.x = timer;
        timer.schedule(new TimerTask() { // from class: onv.17
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                bmw.e(100006, onv.this.v, "", "");
                onv.this.ai();
                onv.this.w();
            }
        }, 20000L);
        ad();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ad() {
        Timer timer = new Timer("DeviceQrCodeHandle");
        this.q = timer;
        timer.schedule(new TimerTask() { // from class: onv.16
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                LogUtil.a("DeviceQrCodeHandle", "mHfpTimer enter.");
                if (onv.this.r || onv.this.c.d() != 1) {
                    if (onv.this.q != null) {
                        onv.this.q.cancel();
                    }
                    if (onv.this.f()) {
                        return;
                    }
                    onv.this.f.e(oac.a(onv.this.w), onv.this.c.d(), onv.this.l);
                    return;
                }
                LogUtil.h("R_QrCode_DeviceQrCodeHandle", "HfpService is not ok.");
            }
        }, 1000L, 1000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean f() {
        try {
            List<BluetoothDevice> i = this.f.i();
            if (i != null && i.size() > 0) {
                for (BluetoothDevice bluetoothDevice : i) {
                    if (!this.v.equalsIgnoreCase(bluetoothDevice.getName())) {
                        LogUtil.a("DeviceQrCodeHandle", "hfp device is : ", bluetoothDevice.getName());
                    } else {
                        ddH_(bluetoothDevice, null);
                        return true;
                    }
                }
            }
        } catch (SecurityException e2) {
            LogUtil.b("DeviceQrCodeHandle", "checkHfpDeviceList SecurityException:", ExceptionUtils.d(e2));
        }
        return false;
    }

    public void b() {
        j();
        ai();
        i();
        this.f.e(this.g);
    }

    public void c() {
        y();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ai() {
        LogUtil.a("DeviceQrCodeHandle", "stop scan ble.");
        Timer timer = this.q;
        if (timer != null) {
            timer.cancel();
            this.q = null;
        }
        this.f.b();
        this.r = false;
    }

    private void s() {
        final Activity activity = this.mActivity.get();
        if (activity == null) {
            return;
        }
        activity.runOnUiThread(new Runnable() { // from class: onv.20
            @Override // java.lang.Runnable
            public void run() {
                if (nrj.cJZ_() == null) {
                    onv.this.p();
                    return;
                }
                if (nrj.cKa_(activity)) {
                    Activity activity2 = (Activity) onv.this.mActivity.get();
                    if (activity2 != null) {
                        activity2.finish();
                        return;
                    }
                    return;
                }
                onv.this.p();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        Activity activity = this.mActivity.get();
        if (activity == null) {
            return;
        }
        activity.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u() {
        Activity activity = this.mActivity.get();
        if (activity == null) {
            return;
        }
        if (activity instanceof QrCodeScanningActivity) {
            ((QrCodeScanningActivity) activity).a();
        } else {
            activity.finish();
        }
    }

    private void e() {
        final Activity activity = this.mActivity.get();
        if (activity != null) {
            activity.runOnUiThread(new Runnable() { // from class: onv.18
                @Override // java.lang.Runnable
                public void run() {
                    NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(activity);
                    builder.e(R.string._2130843262_res_0x7f02167e).czC_(R.string._2130841131_res_0x7f020e2b, new View.OnClickListener() { // from class: onv.18.5
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            onv.this.f.c(onv.this.g);
                            LogUtil.a("DeviceQrCodeHandle", "bluetoothSwitch ACTION_REQUEST_ENABLE");
                            try {
                                activity.startActivityForResult(new Intent("android.bluetooth.adapter.action.REQUEST_ENABLE"), 102);
                            } catch (SecurityException unused) {
                                ReleaseLogUtil.c("R_QrCode_DeviceQrCodeHandle", "bluetoothSwitch SecurityException");
                                onv.this.n();
                            }
                            ViewClickInstrumentation.clickOnView(view);
                        }
                    }).czz_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: onv.18.3
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            onv.this.n();
                            ViewClickInstrumentation.clickOnView(view);
                        }
                    });
                    if (activity.isFinishing()) {
                        return;
                    }
                    NoTitleCustomAlertDialog e2 = builder.e();
                    e2.setCancelable(false);
                    e2.show();
                }
            });
        }
    }

    private boolean e(int i) {
        return PermissionUtil.c() && i == 1;
    }

    private void q() {
        LogUtil.a("DeviceQrCodeHandle", "Enter handleSwitchDevice.");
        List<DeviceInfo> c = oae.c(BaseApplication.getContext()).c();
        if (c == null || c.isEmpty()) {
            LogUtil.h("DeviceQrCodeHandle", "handleSwitchDevice connectedDeviceInfo is empty.");
            l();
        } else if (c.size() == 1) {
            c(c);
        } else {
            a(c);
        }
    }

    private void c(List<DeviceInfo> list) {
        LogUtil.a("DeviceQrCodeHandle", "Enter switchForOneConnectedDevice.");
        DeviceInfo deviceInfo = list.get(0);
        if (deviceInfo == null) {
            LogUtil.h("DeviceQrCodeHandle", "switchForOneConnectedDevice deviceInfo is null.");
            l();
            return;
        }
        if (cvt.c(deviceInfo.getProductType())) {
            if (deviceInfo.getAutoDetectSwitchStatus() == 0) {
                LogUtil.a("DeviceQrCodeHandle", "switchForOneConnectedDevice connected device is aw70 band mode.");
                b(deviceInfo);
                return;
            } else if (deviceInfo.getAutoDetectSwitchStatus() == 1) {
                LogUtil.a("DeviceQrCodeHandle", "switchForOneConnectedDevice connected device is aw70 run mode.");
                l();
                return;
            } else {
                LogUtil.h("DeviceQrCodeHandle", "switchForOneConnectedDevice connected device is aw70 unknown mode.");
                l();
                return;
            }
        }
        b(deviceInfo);
    }

    private void a(List<DeviceInfo> list) {
        LogUtil.a("DeviceQrCodeHandle", "Enter switchForMoreConnectedDevices.");
        for (DeviceInfo deviceInfo : list) {
            if (!cvt.a(deviceInfo.getProductType(), deviceInfo.getAutoDetectSwitchStatus())) {
                LogUtil.c("DeviceQrCodeHandle", "switchForMoreConnectedDevices connected device is not aw70 run mode.");
                b(deviceInfo);
                return;
            }
        }
    }

    private void b(DeviceInfo deviceInfo) {
        if (this.mActivity == null) {
            LogUtil.h("DeviceQrCodeHandle", "handleSwitchDeviceDialog mActivity is null.");
            return;
        }
        Activity activity = this.mActivity.get();
        if (activity == null) {
            LogUtil.h("DeviceQrCodeHandle", "handleSwitchDeviceDialog activity is null.");
            return;
        }
        oae c = oae.c(BaseApplication.getContext());
        String b2 = c.b(deviceInfo.getProductType());
        String deviceName = deviceInfo.getDeviceName();
        if (!TextUtils.isEmpty(deviceName)) {
            b2 = deviceName;
        }
        String c2 = c.c(this.w);
        onu onuVar = this.k;
        if (onuVar != null && !TextUtils.isEmpty(onuVar.c())) {
            c2 = this.k.c();
        }
        LogUtil.a("DeviceQrCodeHandle", "handleSwitchDeviceDialog scan device: ", c2, ", connected device: ", b2);
        ddK_(activity, String.format(Locale.ENGLISH, activity.getResources().getString(R.string.IDS_replace_device_dialog_content), b2, c2));
    }

    private void ddK_(final Activity activity, final String str) {
        activity.runOnUiThread(new Runnable() { // from class: onv.19
            @Override // java.lang.Runnable
            public void run() {
                CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(activity).b(R.string.IDS_device_replace_dialog_title_notification).e(str).cyR_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: onv.19.5
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        LogUtil.a("DeviceQrCodeHandle", "showSwitchDeviceDialog click cancel.");
                        onv.this.n();
                        ViewClickInstrumentation.clickOnView(view);
                    }
                }).cyU_(R.string._2130841131_res_0x7f020e2b, new View.OnClickListener() { // from class: onv.19.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        LogUtil.a("DeviceQrCodeHandle", "showSwitchDeviceDialog click ok.");
                        onv.this.l();
                        ViewClickInstrumentation.clickOnView(view);
                    }
                }).a();
                a2.setCancelable(false);
                if (activity.isFinishing()) {
                    return;
                }
                a2.show();
            }
        });
    }
}
