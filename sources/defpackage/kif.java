package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import com.huawei.callback.OtaResultCallbackInterface;
import com.huawei.datatype.DeviceCommand;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hms.common.internal.constant.AuthInternalPickerConstant;
import com.huawei.hwdevice.outofprocess.datatype.DataOtaParametersV2;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.phoneservice.feedbackcommon.network.FeedbackWebConstants;
import com.huawei.unitedevice.callback.IResultAIDLCallback;
import com.huawei.unitedevice.callback.ITransferFileCallback;
import com.huawei.unitedevice.entity.UniteDevice;
import com.huawei.unitedevice.hwcommonfilemgr.TransferFileQueueManager;
import com.huawei.unitedevice.hwcommonfilemgr.entity.CommonFileInfoParcel;
import com.huawei.unitedevice.hwwifip2ptransfermgr.HwWifiP2pTransferManager;
import com.huawei.unitedevice.hwwifip2ptransfermgr.WifiP2pTransferListener;
import health.compact.a.CommonUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class kif {
    private static final Map<String, Integer> d = new HashMap(24);
    private static final Object e = new Object();
    private int ae;
    private int ak;
    private OtaResultCallbackInterface am;
    private DataOtaParametersV2 f;
    private izy i;
    private CountDownLatch j;
    private String l;
    private String m;
    private DeviceInfo n;
    private boolean r = true;
    private Timer al = null;
    private boolean v = false;
    private a o = new a();
    private ExtendHandler ag = null;
    private int ad = -1;
    private FileInputStream t = null;
    private int aa = 0;

    /* renamed from: a, reason: collision with root package name */
    private String f14386a = "is_package_already_exists";
    private int aj = 30000;
    private int ai = 180000;
    private boolean q = false;
    private double ah = 0.0d;
    private boolean z = false;
    private int g = 0;
    private boolean s = false;
    private int k = 0;
    private boolean w = false;
    private boolean x = true;
    private boolean u = true;
    private boolean p = false;
    private boolean y = false;
    private List<kii> h = new ArrayList();
    private int af = 0;
    private int c = -1;
    private long b = 0;
    private CountDownLatch ab = null;
    private final BroadcastReceiver ac = new BroadcastReceiver() { // from class: kif.4
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.h("OtaTransferFile", "mNetworkStateReceiver , intent is null.");
                return;
            }
            int intExtra = intent.getIntExtra("wifi_state", 0);
            LogUtil.a("OtaTransferFile", "mNetworkStateReceiver wifiState: ", Integer.valueOf(intExtra), "mTransferFileMode:", Integer.valueOf(kif.this.ae));
            if (intExtra == 1) {
                kif.this.c();
            }
            if (intExtra == 3 && kif.this.ae == 1) {
                kif.this.m();
            }
        }
    };
    private WifiP2pTransferListener an = new WifiP2pTransferListener() { // from class: kif.5
        @Override // com.huawei.unitedevice.hwwifip2ptransfermgr.WifiP2pTransferListener
        public void onSuccess(int i, String str, int i2) {
            jrb.d("OtaTransferFile", "WifiP2pTransferListener onSuccess : ", Integer.valueOf(i), " msg : ", str, "fileType : ", Integer.valueOf(i2));
            if (i == 1000) {
                kif.this.s = false;
                kif.this.b(1);
                kif.this.c();
            } else if (i == 10000) {
                kif.this.ae = 0;
                LogUtil.a("OtaTransferFile", "ota wifi all send, wait 5.9.6 from device.");
            } else {
                LogUtil.h("OtaTransferFile", "unknown code : ", Integer.valueOf(i), " please check.");
            }
        }

        @Override // com.huawei.unitedevice.hwwifip2ptransfermgr.WifiP2pTransferListener
        public void onFail(int i, String str, int i2) {
            kif.this.a(i, str);
        }

        @Override // com.huawei.unitedevice.hwwifip2ptransfermgr.WifiP2pTransferListener
        public void onProcess(int i, int i2) {
            LogUtil.a("OtaTransferFile", "ready onProcess : ", Integer.valueOf(i));
            kif.this.ae = 2;
            kif.this.r = true;
            kif.this.s = true;
            if (kif.this.ag != null) {
                kif.this.ag.removeMessages(6);
            }
            kif.this.c(1);
            kif.this.c(i, "wifi");
        }
    };

    public kif(izy izyVar, String str) {
        this.i = null;
        this.l = "";
        this.i = izyVar;
        this.l = str;
        jrb.d("OtaTransferFile", "OtaTransferFile init");
    }

    private String d(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(str, str2);
            return jSONObject.toString();
        } catch (JSONException unused) {
            LogUtil.b("OtaTransferFile", "getJsonString. please check.");
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, String str) {
        ExtendHandler extendHandler = this.ag;
        if (extendHandler != null) {
            extendHandler.removeMessages(6);
        }
        boolean a2 = HwWifiP2pTransferManager.d().a(i);
        jrb.d("OtaTransferFile", "WifiP2pTransferListener onFail : ", Integer.valueOf(i), " msg : ", str, " isRepeat ", Boolean.valueOf(a2));
        DataOtaParametersV2 dataOtaParametersV2 = this.f;
        e();
        if (!a2 || dataOtaParametersV2 == null) {
            jrb.d("OtaTransferFile", "wifiFail, !isRepeat || (dataOtaParam == null)");
            c(1);
        } else {
            a(dataOtaParametersV2);
        }
    }

    public void d() {
        ExtendHandler extendHandler = this.ag;
        if (extendHandler != null) {
            extendHandler.removeTasksAndMessages();
            this.ag = null;
        }
        try {
            BaseApplication.e().unregisterReceiver(this.ac);
        } catch (IllegalArgumentException unused) {
            LogUtil.b("OtaTransferFile", "IllegalArgumentException e");
        }
    }

    class a implements Handler.Callback {
        private a() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            String str;
            if (message == null) {
                return false;
            }
            jrb.d("OtaTransferFile", "msg what : ", Integer.valueOf(message.what));
            if (message.what != 6) {
                return false;
            }
            LogUtil.a("OtaTransferFile", "Wait OTA upgrade timeout mIsOtaV2Failed is true");
            if (kif.this.ae == 2) {
                str = "wifi";
            } else if (kif.this.ae == 1) {
                str = kif.this.n.getDeviceBluetoothType() == 1 ? "BR" : "BLE";
            } else {
                str = "";
            }
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("transferPath", str);
            } catch (JSONException unused) {
                LogUtil.b("OtaTransferFile", "handleMessage OTA_V2_TIME_OUT JSONException");
            }
            jrd.b(kif.this.n, "090002", "1", "", jSONObject.toString());
            kif.this.b(1, "wait time out");
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i, String str) {
        OtaResultCallbackInterface otaResultCallbackInterface = this.am;
        if (otaResultCallbackInterface != null) {
            otaResultCallbackInterface.onUpgradeFailed(this.l, i, str);
        }
        this.w = true;
        this.x = true;
        this.u = true;
        this.ae = 0;
        e();
        try {
            BaseApplication.e().unregisterReceiver(this.ac);
        } catch (IllegalArgumentException unused) {
            LogUtil.b("OtaTransferFile", "IllegalArgumentException e");
        }
    }

    private void e() {
        FileInputStream fileInputStream = this.t;
        if (fileInputStream != null) {
            try {
                fileInputStream.close();
                this.t = null;
            } catch (IOException unused) {
                LogUtil.b("OtaTransferFile", "IOException closeStream exception");
            }
        }
        this.c = -1;
        this.b = 0L;
        a();
        synchronized (e) {
            this.h.clear();
        }
    }

    public void a(String str, String str2, DataOtaParametersV2 dataOtaParametersV2, int i, OtaResultCallbackInterface otaResultCallbackInterface) {
        int c2;
        if (a(str, str2, dataOtaParametersV2, otaResultCallbackInterface)) {
            this.ab = null;
            this.ak = i;
            jrb.d("OtaTransferFile", "startTransferOTAFile, version", str2, "; updateMode", Integer.valueOf(i));
            if (dataOtaParametersV2.getOtaInterval() == 109000) {
                jrb.d("OtaTransferFile", "startTransferOTAFile dataOtaParametersV2.getOtaInterval() == Constant.OTA_TAINTERVAL");
                this.am = otaResultCallbackInterface;
                return;
            }
            if (this.ag == null) {
                this.ag = HandlerCenter.yt_(this.o, "OtaTransferFile");
            }
            this.am = otaResultCallbackInterface;
            c();
            DeviceInfo f = f();
            this.n = f;
            if (f == null) {
                jrb.d("OtaTransferFile", "startTransferOTAFile, mDeviceInfo == null");
                return;
            }
            d(str);
            n();
            if (this.f14386a.equals(str)) {
                this.f = null;
                a(dataOtaParametersV2);
                return;
            }
            boolean b = b(this.n, 0, 147);
            this.v = b;
            if (b) {
                l();
                int c3 = HwWifiP2pTransferManager.d().c(this.n.getDeviceIdentify());
                if (c3 < 4 || !b(dataOtaParametersV2, c3)) {
                    this.f = null;
                    a(dataOtaParametersV2);
                    return;
                }
                return;
            }
            if (b(this.n, i, 31) && (c2 = HwWifiP2pTransferManager.d().c(this.n.getDeviceIdentify())) >= 4 && b(dataOtaParametersV2, c2)) {
                return;
            }
            this.f = null;
            a(dataOtaParametersV2);
        }
    }

    private void n() {
        sol c2 = TransferFileQueueManager.d().c("sport_ota_send_bt");
        if (c2 == null || this.ak == 2) {
            return;
        }
        c2.n(2);
        jrb.d("OtaTransferFile", "ota ready send fore ground, tell normal file send tag.");
    }

    private CommonFileInfoParcel c(soz sozVar) {
        CommonFileInfoParcel commonFileInfoParcel = new CommonFileInfoParcel();
        commonFileInfoParcel.setFileName(sozVar.i());
        commonFileInfoParcel.setFileType(sozVar.o());
        UniteDevice e2 = sozVar.e();
        if (e2 != null) {
            commonFileInfoParcel.setIdentify(e2.getIdentify());
        }
        commonFileInfoParcel.setSourcePackageName(sozVar.w());
        commonFileInfoParcel.setDestinationPackageName(sozVar.a());
        return commonFileInfoParcel;
    }

    private boolean b(DataOtaParametersV2 dataOtaParametersV2, int i) {
        soz i2 = HwWifiP2pTransferManager.d().i();
        if (i2 != null && i2.u() == 2) {
            return false;
        }
        sol c2 = TransferFileQueueManager.d().c("sport_ota_send_wifi");
        if (c2 != null) {
            jrb.d("OtaTransferFile", "tryOpenWifiChannel file is running, no wifi send.");
            if (this.ak == 2) {
                return false;
            }
            if (i2 != null) {
                jrb.d("OtaTransferFile", "normal file send by wifi, stop it by app.");
                snz.a().e(i2.e(), c(i2), new c(c2));
            }
            jrb.d("OtaTransferFile", "tryOpenWifiChannel return false.");
            return false;
        }
        b(this.n, i);
        this.f = dataOtaParametersV2;
        if (g() || HwWifiP2pTransferManager.d().c(this.n.getDeviceIdentify(), (HwWifiP2pTransferManager.TransferBleToWifiCallback) null)) {
            iyv.n();
            int otaUnitSize = dataOtaParametersV2.getOtaUnitSize();
            this.y = dataOtaParametersV2.getOffsetEnable();
            this.q = dataOtaParametersV2.getAckEnable();
            this.aa = otaUnitSize;
            this.ai = dataOtaParametersV2.getDeviceRestartTimeout() * 1000;
            this.w = false;
            this.x = true;
            this.u = true;
            this.aj = dataOtaParametersV2.getAppWaitTimeout() * 1000;
            c(0);
            this.g = 0;
            this.k = 0;
            this.ah = 0.0d;
            return true;
        }
        HwWifiP2pTransferManager.d().d(-1);
        return false;
    }

    private void l() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        BaseApplication.e().registerReceiver(this.ac, intentFilter);
    }

    private boolean a(String str, String str2, DataOtaParametersV2 dataOtaParametersV2, OtaResultCallbackInterface otaResultCallbackInterface) {
        this.ae = 0;
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && dataOtaParametersV2 != null && otaResultCallbackInterface != null) {
            return true;
        }
        LogUtil.h("OtaTransferFile", "startTransferOTAFile error, parameter is null");
        if (otaResultCallbackInterface != null) {
            otaResultCallbackInterface.onUpgradeFailed(this.l, 109001, AuthInternalPickerConstant.PARAM_ERROR);
        }
        e();
        return false;
    }

    private boolean g() {
        HwWifiP2pTransferManager d2 = HwWifiP2pTransferManager.d();
        if (!d2.k() || !d2.f()) {
            return false;
        }
        LogUtil.h("OtaTransferFile", "wifiP2pTransferChannelAvailable");
        return true;
    }

    private void b(DeviceInfo deviceInfo, int i) {
        sol solVar = new sol();
        solVar.m(-1);
        if (deviceInfo != null) {
            solVar.d(jte.d(deviceInfo));
        }
        solVar.o(i);
        HwWifiP2pTransferManager.d().c(solVar, this.an, 2);
    }

    private void a(DataOtaParametersV2 dataOtaParametersV2) {
        ExtendHandler extendHandler;
        iyv.b();
        b(0);
        int otaUnitSize = dataOtaParametersV2.getOtaUnitSize();
        this.y = dataOtaParametersV2.getOffsetEnable();
        int appWaitTimeout = dataOtaParametersV2.getAppWaitTimeout();
        this.q = dataOtaParametersV2.getAckEnable();
        this.aa = otaUnitSize;
        this.aj = appWaitTimeout * 1000;
        this.ai = dataOtaParametersV2.getDeviceRestartTimeout() * 1000;
        this.w = false;
        this.x = true;
        this.u = true;
        LogUtil.a("OtaTransferFile", "startTransferOTAFile, mMaxPacket ", Integer.valueOf(this.aa), " ; mWaitTimeout ", Integer.valueOf(this.aj), " ; mRestartTimeout ", Integer.valueOf(this.ai), "; mIsAckEnable ", Boolean.valueOf(this.q), "; mIsOffsetEnable ", Boolean.valueOf(this.y), " mIsOtaV2Failed is false");
        int i = this.aj;
        if (i == 0 || (extendHandler = this.ag) == null) {
            return;
        }
        extendHandler.sendEmptyMessage(6, i);
    }

    private boolean b(DeviceInfo deviceInfo, int i, int i2) {
        if (deviceInfo == null) {
            return false;
        }
        String deviceIdentify = deviceInfo.getDeviceIdentify();
        if (TextUtils.isEmpty(deviceIdentify)) {
            LogUtil.h("OtaTransferFile", "cannot get info, please check.");
            return false;
        }
        if (!deviceIdentify.equalsIgnoreCase(this.l)) {
            LogUtil.a("OtaTransferFile", "double device link, aw70 device is using ota.");
            return false;
        }
        if (Build.VERSION.SDK_INT >= 29) {
            return cwi.c(deviceInfo, i2) && i != 2;
        }
        LogUtil.a("OtaTransferFile", "sdk version is too low, can not support wi-fi direct.");
        return false;
    }

    private void d(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            File file = new File(str);
            if (d(file, str) && !file.createNewFile()) {
                LogUtil.a("OtaTransferFile", "startTransferOTAFile, The file already exists,continue");
            }
            String canonicalPath = file.getCanonicalPath();
            this.m = canonicalPath;
            this.ad = c(canonicalPath);
        } catch (IOException e2) {
            LogUtil.b("OtaTransferFile", "IOException exception ", e2.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i) {
        String str;
        String str2;
        String str3 = cvx.e(1) + cvx.e(1) + cvx.e(1);
        if (this.v) {
            str = cvx.e(2) + cvx.e(1) + cvx.e(i);
        } else {
            str = "";
        }
        String str4 = str3 + str;
        LogUtil.a("OtaTransferFile", "5.9.9 otaStatusData : ", str4);
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(9);
        deviceCommand.setCommandID(9);
        deviceCommand.setDataContent(cvx.a(str4));
        deviceCommand.setDataLen(cvx.a(str4).length);
        deviceCommand.setmIdentify(this.l);
        JSONObject jSONObject = new JSONObject();
        if (i == 1) {
            str2 = "WIFI";
        } else if (i == 0) {
            str2 = this.n.getDeviceBluetoothType() == 1 ? "BR" : "BLE";
        } else {
            str2 = "";
        }
        try {
            jSONObject.put("transferPath", str2);
            jSONObject.put("0x01", 1);
            jSONObject.put("0x02", i);
        } catch (JSONException unused) {
            LogUtil.b("OtaTransferFile", "otaStatusNotify JSONException");
        }
        jrd.b(this.n, "050909", "1", "", jSONObject.toString());
        jrb.b("OtaTransferFile", 9, 9);
        c(deviceCommand);
    }

    private int c(String str) {
        if (!b(str) || str == null) {
            return 1;
        }
        try {
            File file = new File(CommonUtil.c(str));
            if (!file.exists() && !file.createNewFile()) {
                LogUtil.a("OtaTransferFile", "getOTAFileByPath, The file already exists continue");
            }
            return (int) file.length();
        } catch (IOException unused) {
            LogUtil.b("OtaTransferFile", "IOException getOTAFileByPath() exception");
            return 1;
        }
    }

    private boolean d(File file, String str) throws IOException {
        if (file.getCanonicalPath().startsWith(new File(str).getCanonicalPath())) {
            return true;
        }
        LogUtil.h("OtaTransferFile", "File is outside extraction target directory.");
        return false;
    }

    private void d(khw khwVar) {
        ExtendHandler extendHandler = this.ag;
        if (extendHandler != null) {
            extendHandler.removeMessages(6);
        }
        int c2 = (int) khwVar.c();
        int e2 = (int) khwVar.e();
        int a2 = (int) khwVar.a();
        if (a2 > 0) {
            b(this.m, c2, a2, khwVar.b());
            this.g += a2;
            return;
        }
        if (this.s) {
            e();
            LogUtil.a("OtaTransferFile", "closeStream");
            this.s = false;
        }
        List<Integer> b = khwVar.b();
        LogUtil.a("OtaTransferFile", "5.9.3 deviceApplyInfo file_offset ", Integer.valueOf(c2), ", file_length ", Integer.valueOf(e2));
        d(c2, e2);
        a(c2, e2, b);
        if (this.z) {
            this.ae = 1;
            if (this.r && a2 == 0) {
                m();
                this.r = false;
            }
            int i = (int) (((this.g + this.k) / this.ah) * 100.0d);
            c(i, "bluetooth");
            LogUtil.a("OtaTransferFile", "onFileTransferState mAlreadySend ", Integer.valueOf(this.g), ", mDeviceReceived ", Integer.valueOf(this.k), ", mOtaFileSizeV2 ", Double.valueOf(this.ah), ", otaProgress ", Integer.valueOf(i));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i, String str) {
        long currentTimeMillis = System.currentTimeMillis();
        if (i != this.c || Math.abs(currentTimeMillis - this.b) >= 1000 || i >= 100) {
            this.c = i;
            this.b = currentTimeMillis;
            e(i, str);
            OtaResultCallbackInterface otaResultCallbackInterface = this.am;
            if (otaResultCallbackInterface != null) {
                otaResultCallbackInterface.onFileTransferState(this.l, i);
                this.am.onFileTransferReport(d("transferWay", str));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        if (!h()) {
            LogUtil.a("OtaTransferFile", "wifi is not open, please check!");
            return;
        }
        if (this.al != null) {
            LogUtil.a("OtaTransferFile", "wifi already count time, no switch this time. wait time switch wifi");
            return;
        }
        int i = (int) (((this.ah - this.g) - this.k) / 1.048576E8d);
        int i2 = 1 <= i ? 1 + i : 1;
        this.al = new Timer("switchBtToWifi");
        LogUtil.a("OtaTransferFile", "trySwitchWifiTask time : ", 0, " limitTimes : ", Integer.valueOf(i2));
        b(0, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final int i, final int i2) {
        LogUtil.a("OtaTransferFile", "delayTask time : ", Integer.valueOf(i), " maxTime : ", Integer.valueOf(i2));
        Timer timer = this.al;
        if (timer == null) {
            LogUtil.h("OtaTransferFile", "delayTask timer is null. please check.");
            return;
        }
        long j = ((i * 2) + 1) * 60000;
        if (j <= 0) {
            j = 0;
        }
        timer.schedule(new TimerTask() { // from class: kif.3
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                if (i >= i2 || kif.this.n == null || kif.this.n.getDeviceConnectState() != 2) {
                    kif.this.c();
                    return;
                }
                if (HwWifiP2pTransferManager.d().n() != 1) {
                    kif.this.j();
                }
                kif.this.b(i + 1, i2);
            }
        }, j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        LogUtil.a("OtaTransferFile", "closeTrySwitchWifiTask");
        Timer timer = this.al;
        if (timer != null) {
            timer.cancel();
        }
        this.al = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: kij
            @Override // java.lang.Runnable
            public final void run() {
                kif.this.b();
            }
        });
    }

    /* synthetic */ void b() {
        LogUtil.a("OtaTransferFile", "processWifiEnableMessage enter.");
        if (!h()) {
            LogUtil.h("OtaTransferFile", "processWifiEnableMessage not support switch");
            return;
        }
        HwWifiP2pTransferManager.d().p();
        b(this.n, 4);
        HwWifiP2pTransferManager.d().c(this.n.getDeviceIdentify(), (HwWifiP2pTransferManager.TransferBleToWifiCallback) null);
    }

    private boolean h() {
        if (Build.VERSION.SDK_INT < 29) {
            return false;
        }
        double d2 = (this.ah - this.g) - this.k;
        LogUtil.a("OtaTransferFile", "translate file remainingSize:", Double.valueOf(d2));
        if (this.ah <= 2097152.0d || d2 < 2097152.0d) {
            LogUtil.h("OtaTransferFile", "file size less than 2M，no need switch to wifi send file");
            return false;
        }
        LogUtil.a("OtaTransferFile", "no support wifi, do not switch to wifi. : ", Boolean.valueOf(this.v));
        return HwWifiP2pTransferManager.d().l() && !HwWifiP2pTransferManager.d().o() && this.v;
    }

    private void d(int i, int i2) {
        kii remove;
        if (TextUtils.isEmpty(this.m)) {
            LogUtil.h("OtaTransferFile", "getTransferDataBuffer info is null.");
            return;
        }
        if (this.t == null) {
            try {
                File file = new File(CommonUtil.c(this.m));
                if (!file.exists()) {
                    LogUtil.h("OtaTransferFile", "getTransferDataBuffer, file is not exists");
                    sqo.as("getTransferDataBuffer, file is not exists:" + this.m);
                    if (!file.createNewFile()) {
                        LogUtil.a("OtaTransferFile", "getTransferDataBuffer, The file already exists continue");
                    }
                }
                this.t = FileUtils.openInputStream(file);
            } catch (IOException unused) {
                LogUtil.b("OtaTransferFile", "IOException getTransferDataBuffer() exception");
            }
        }
        try {
            ByteBuffer allocate = ByteBuffer.allocate(i2);
            FileInputStream fileInputStream = this.t;
            if (fileInputStream != null && fileInputStream.getChannel().read(allocate, i) == -1) {
                LogUtil.h("OtaTransferFile", "getTransferDataBuffer set read position occur error or readSize error");
                if (this.p) {
                    return;
                }
                sqo.as("getTransferDataBuffer set read position occur error or readSize error " + Build.VERSION.RELEASE);
                this.p = true;
                return;
            }
            synchronized (e) {
                if (this.h.size() > 4 && (remove = this.h.remove(0)) != null) {
                    remove.c();
                }
                kii d2 = kii.d();
                d2.b(allocate.array());
                d2.b(i);
                this.h.add(d2);
            }
        } catch (IOException | IllegalArgumentException e2) {
            LogUtil.b("OtaTransferFile", "getTransferDataBuffer Exception.", ExceptionUtils.d(e2));
        }
    }

    private void c(kib kibVar) {
        this.z = true;
        this.g = 0;
        long b = kibVar.b();
        this.ah = b;
        long e2 = kibVar.e();
        this.k = (int) e2;
        LogUtil.a("OtaTransferFile", "5.9.5 updatePacketSize，progress ", Integer.valueOf((int) (e2 / b)), ", packageValidSize", Long.valueOf(b), ", receivedFileSize ", Long.valueOf(e2));
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("0x01", b);
            jSONObject.put("0x02", e2);
        } catch (JSONException unused) {
            LogUtil.b("OtaTransferFile", "updatePacketSize JSONException");
        }
        jrd.b(this.n, "050905", "0", "", jSONObject.toString());
    }

    private void d(kic kicVar) {
        int b = kicVar.b();
        this.z = false;
        a();
        this.ag.removeMessages(6);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("0x01", b);
            jSONObject.put("0x04", kicVar.e());
        } catch (JSONException unused) {
            LogUtil.b("OtaTransferFile", "otaCheckResultsReport JSONException");
        }
        jrd.b(this.n, "050906", "0", "", jSONObject.toString());
        if (b == 0) {
            LogUtil.a("OtaTransferFile", "ota check failed");
            OtaResultCallbackInterface otaResultCallbackInterface = this.am;
            if (otaResultCallbackInterface != null) {
                otaResultCallbackInterface.onFileRespond(this.l, b);
            }
            o();
        } else if (b == 1) {
            LogUtil.a("OtaTransferFile", "ota check success , mRestartTimeout: ", Integer.valueOf(this.ai));
            OtaResultCallbackInterface otaResultCallbackInterface2 = this.am;
            if (otaResultCallbackInterface2 != null) {
                int i = this.ai;
                if (i == 0) {
                    otaResultCallbackInterface2.onFileRespond(this.l, b);
                } else {
                    otaResultCallbackInterface2.onFileRespond(this.l, i);
                }
            }
            if (CommonUtil.as() && TextUtils.equals(kxz.m(com.huawei.hwcommonmodel.application.BaseApplication.getContext()), "true")) {
                List<String> l = kxz.l(com.huawei.hwcommonmodel.application.BaseApplication.getContext());
                if (l.size() >= 2) {
                    l.remove(0);
                }
                l.add(this.m);
                LogUtil.a("OtaTransferFile", "SaveUpgradePackage,FilePath = ", this.m);
                kxz.e(l, com.huawei.hwcommonmodel.application.BaseApplication.getContext());
            } else {
                i();
            }
            o();
        } else {
            LogUtil.h("OtaTransferFile", "ota check default");
        }
        this.ae = 0;
        e();
    }

    private void i() {
        if (TextUtils.isEmpty(this.m)) {
            return;
        }
        File file = new File(this.m);
        if (!file.exists() || file.delete()) {
            return;
        }
        LogUtil.a("OtaTransferFile", "deleteOtaFile: path = ", this.m, " failed!");
    }

    private void o() {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(9);
        deviceCommand.setCommandID(6);
        deviceCommand.setDataContent(null);
        deviceCommand.setDataLen(0);
        c(deviceCommand);
    }

    private void e(int i) {
        LogUtil.a("OtaTransferFile", "5.9.7 otaStatusReport,errorCode ", Integer.valueOf(i));
        jrd.b(this.n, "050907", "0", String.valueOf(i), "");
        if (i != 100000) {
            ExtendHandler extendHandler = this.ag;
            if (extendHandler != null) {
                extendHandler.removeMessages(6);
            }
            b(i, "device error");
            LogUtil.a("OtaTransferFile", "5.9.7 otaStatusReport,mIsOtaV2Failed is true");
        }
    }

    private void a() {
        if (b(f(), this.ak, 31) || this.v) {
            k();
            HwWifiP2pTransferManager.d().d(-1);
            HwWifiP2pTransferManager.d().e();
        } else if (this.f != null) {
            k();
            if (HwWifiP2pTransferManager.d().d(-1)) {
                HwWifiP2pTransferManager.d().e();
            }
        }
    }

    private void k() {
        LogUtil.a("OtaTransferFile", "tryResetWifi enter.");
        soz i = HwWifiP2pTransferManager.d().i();
        if (i != null) {
            HwWifiP2pTransferManager.d().f(i.n());
        }
    }

    private void a(int i, int i2, List<Integer> list) {
        CountDownLatch countDownLatch = this.j;
        if (countDownLatch != null) {
            try {
                if (countDownLatch.await(2L, TimeUnit.SECONDS)) {
                    LogUtil.a("OtaTransferFile", "otaFileDelivery timeout");
                }
            } catch (InterruptedException unused) {
                LogUtil.b("OtaTransferFile", "otaFileDelivery InterruptedException");
            }
        }
        if (c(i, i2, list)) {
            return;
        }
        c(1);
        CountDownLatch countDownLatch2 = this.j;
        if (countDownLatch2 != null) {
            countDownLatch2.countDown();
        }
    }

    private boolean c(int i, int i2, List<Integer> list) {
        int i3 = this.aa - 9;
        int e2 = e(i2, i3);
        boolean z = true;
        this.j = new CountDownLatch(1);
        int i4 = i;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (i5 < e2) {
            int e3 = e(i2, i3, i4, i6);
            if (e3 < 0) {
                LogUtil.h("OtaTransferFile", "out of length mIsOtaV2Failed is true");
                b(104004, "transfer error");
                return z;
            }
            kie b = b(i4 - i, i, e3);
            if (i7 > 255) {
                i7 = 0;
            }
            if (list != null && i5 < list.size()) {
                LogUtil.a("OtaTransferFile", "otaFileDelivery bitmapList.get(index) ", list.get(i5));
                if (list.get(i5).intValue() != 0) {
                    i6 += e3;
                    i4 += i3;
                    i7++;
                    i5++;
                    z = true;
                } else {
                    this.g -= e3;
                }
            }
            d(i7, b, i4);
            this.g += e3;
            i6 += e3;
            i4 += i3;
            i7++;
            i5++;
            z = true;
        }
        return false;
    }

    private kie b(int i, int i2, int i3) {
        byte[] d2;
        kie b = kie.b(i3);
        try {
            d2 = d(i2);
        } catch (IndexOutOfBoundsException e2) {
            LogUtil.b("OtaTransferFile", "IndexOutOfBoundsException ", ExceptionUtils.d(e2));
        }
        if (d2 != null && d2.length > 0) {
            b.c().put(d2, i, i3);
            return b;
        }
        return kie.b(0);
    }

    private byte[] d(int i) {
        synchronized (e) {
            for (int i2 = 0; i2 < this.h.size(); i2++) {
                kii kiiVar = this.h.get(i2);
                if (kiiVar != null && kiiVar.e() == i) {
                    return kiiVar.a();
                }
            }
            return new byte[0];
        }
    }

    private int e(int i, int i2, int i3, int i4) {
        if ((i - i4) / i2 == 0) {
            i2 = i % i2;
        }
        if ((i + i3) - i4 <= this.ad) {
            return i2;
        }
        LogUtil.a("OtaTransferFile", "out of Index");
        return this.ad - i3;
    }

    private int e(int i, int i2) {
        if (i % i2 == 0) {
            return i / i2;
        }
        return (i / i2) + 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i) {
        if (this.ag == null) {
            this.ag = HandlerCenter.yt_(this.o, "OtaTransferFile");
        }
        if (i == 0) {
            this.ag.sendEmptyMessage(6, 45000L);
            return;
        }
        int i2 = this.aj;
        if (i2 != 0) {
            this.ag.sendEmptyMessage(6, i2);
        } else {
            this.ag.sendEmptyMessage(6, OpAnalyticsConstants.H5_LOADING_DELAY);
        }
    }

    private void d(int i, kie kieVar, int i2) {
        byte[] bArr;
        if (this.u) {
            jrb.b("OtaTransferFile", 9, 4);
            jrd.b(this.n, "050904", "1", "", "");
        }
        this.u = false;
        byte[] c2 = cvx.c(i);
        byte[] array = kieVar.c().array();
        if (this.y) {
            byte[] g = cvx.g(i2);
            bArr = new byte[array.length + c2.length + g.length];
            System.arraycopy(g, 0, bArr, 0, g.length);
            System.arraycopy(c2, 0, bArr, g.length, c2.length);
            System.arraycopy(array, 0, bArr, c2.length + g.length, array.length);
        } else {
            bArr = new byte[array.length + c2.length];
            System.arraycopy(c2, 0, bArr, 0, c2.length);
            System.arraycopy(array, 0, bArr, c2.length, array.length);
        }
        kieVar.b();
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(9);
        deviceCommand.setCommandID(4);
        deviceCommand.setDataContent(bArr);
        deviceCommand.setDataLen(bArr.length);
        c(deviceCommand);
    }

    private void e(int i, String str) {
        if (bky.i()) {
            if (i % 5 == 0 && i != this.af) {
                this.af = i;
                jrb.d("OtaTransferFile", str, "ota process : ", Integer.valueOf(i));
            }
            if (i == 100) {
                this.af = 0;
            }
        }
    }

    private void b(byte[] bArr) {
        CountDownLatch countDownLatch = this.j;
        if (countDownLatch != null) {
            try {
                if (countDownLatch.await(2L, TimeUnit.SECONDS)) {
                    LogUtil.a("OtaTransferFile", "sendApplyData timeout");
                }
            } catch (InterruptedException unused) {
                LogUtil.b("OtaTransferFile", "sendApplyData InterruptedException");
            }
        }
        int length = bArr.length - 2;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 2, bArr2, 0, length);
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(9);
        deviceCommand.setCommandID(3);
        deviceCommand.setDataContent(bArr2);
        deviceCommand.setDataLen(length);
        if (this.x) {
            jrd.b(this.n, "050903", "1", "", "");
        }
        this.x = false;
        c(deviceCommand);
    }

    private void e(byte[] bArr) throws cwg {
        if (this.w) {
            return;
        }
        if (bArr[0] == 9) {
            int length = bArr.length;
            int[] iArr = new int[length];
            for (int i = 0; i < length; i++) {
                byte b = bArr[i];
                iArr[i] = b;
                if (b < 0) {
                    iArr[i] = b + 256;
                }
            }
            int i2 = iArr[1];
            if (i2 == 3) {
                if (this.x) {
                    jrb.b("OtaTransferFile", 9, 3);
                    jrd.b(this.n, "050903", "0", "", "");
                }
                b(bArr, iArr);
                return;
            }
            if (i2 == 5) {
                jrb.b("OtaTransferFile", 9, 5);
                c(bArr);
            } else if (i2 == 6) {
                jrb.b("OtaTransferFile", 9, 6);
                d(kid.c(bArr));
            } else if (i2 == 7) {
                jrb.b("OtaTransferFile", 9, 7);
                e(jkw.d(bArr));
            } else {
                LogUtil.h("OtaTransferFile", "otaV2NotificationMsg default");
            }
        }
    }

    private void b(byte[] bArr, int[] iArr) throws cwg {
        if (iArr[2] == 127) {
            LogUtil.h("OtaTransferFile", "error,do nothing");
            return;
        }
        CountDownLatch countDownLatch = this.ab;
        if (countDownLatch == null) {
            LogUtil.a("OtaTransferFile", "queue command.");
            this.ab = new CountDownLatch(1);
        } else {
            try {
                if (!countDownLatch.await(1L, TimeUnit.SECONDS)) {
                    countDownLatch.countDown();
                }
            } catch (InterruptedException e2) {
                LogUtil.b("OtaTransferFile", "5.9.3", ExceptionUtils.d(e2));
                countDownLatch.countDown();
            }
        }
        if (this.q) {
            b(bArr);
        }
        khw b = kid.b(bArr);
        if (b == null) {
            LogUtil.h("OtaTransferFile", "dataOtaApplyReport == null");
        } else {
            d(b);
        }
    }

    private void c(byte[] bArr) throws cwg {
        LogUtil.a("OtaTransferFile", "5.9.5 size report");
        c(kid.a(bArr));
        CountDownLatch countDownLatch = this.ab;
        if (countDownLatch != null) {
            countDownLatch.countDown();
            LogUtil.a("OtaTransferFile", "5.9.5 finish, 5.9.3 can continue go on.");
        }
    }

    private void c(DeviceCommand deviceCommand) {
        deviceCommand.setmIdentify(this.l);
        if (this.i == null) {
            jtc.c().sendDeviceData(deviceCommand);
            return;
        }
        ByteBuffer allocate = ByteBuffer.allocate(deviceCommand.getDataLen() + 2);
        allocate.put(cvx.c(deviceCommand.getServiceID()));
        allocate.put(cvx.c(deviceCommand.getCommandID()));
        if (deviceCommand.getDataContent() != null) {
            allocate.put(deviceCommand.getDataContent());
        } else {
            LogUtil.h("OtaTransferFile", "command data is null, if not OTA, data incorrect");
        }
        allocate.flip();
        izf izfVar = new izf();
        izfVar.e(allocate.array());
        izfVar.e(allocate.array().length);
        izfVar.e(deviceCommand.getNeedAck());
        izfVar.e(this.l);
        izfVar.a(deviceCommand.getNeedEncrypt());
        izfVar.i(deviceCommand.getServiceID());
        izfVar.d(deviceCommand.getCommandID());
        this.i.c(e(this.l), izfVar);
    }

    private int e(String str) {
        if (str == null) {
            return -1;
        }
        Integer num = d.get(str);
        if (num != null) {
            return num.intValue();
        }
        DeviceInfo deviceInfo = null;
        Iterator<DeviceInfo> it = cun.c().getDeviceList(HwGetDevicesMode.ACTIVE_DEVICES, null, "OtaTransferFile").iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            DeviceInfo next = it.next();
            if (next.getDeviceIdentify().equalsIgnoreCase(str)) {
                deviceInfo = next;
                break;
            }
        }
        if (deviceInfo == null) {
            return -1;
        }
        Integer valueOf = Integer.valueOf(deviceInfo.getDeviceBluetoothType());
        d.put(str, valueOf);
        return valueOf.intValue();
    }

    private boolean b(String str) {
        return str == null || str.indexOf(FeedbackWebConstants.INVALID_FILE_NAME_PRE) < 0;
    }

    public void d(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            LogUtil.h("OtaTransferFile", "parseOTAReceivedData error ,data is null");
            return;
        }
        try {
            e(bArr);
        } catch (cwg e2) {
            LogUtil.b("OtaTransferFile", "otaV2NotificationMsg tlv error ", e2.getMessage());
        }
    }

    private void b(String str, int i, int i2, List<Integer> list) {
        LogUtil.a("OtaTransferFile", "sendFileByWifi : ", str, " offset : ", Integer.valueOf(i), "fileLength : ", Integer.valueOf(i2));
        if (TextUtils.isEmpty(str)) {
            this.an.onFail(1002, "filePath is empty.", -1);
            return;
        }
        c(1);
        soz sozVar = new soz();
        sozVar.j(str);
        sozVar.c(-1);
        sozVar.j(i2);
        sozVar.e(i);
        sozVar.d(this.an);
        double d2 = this.ah;
        sozVar.b(d2 == 0.0d ? a(str) : (long) d2);
        sozVar.a(list);
        sozVar.h(4);
        sozVar.f(this.k);
        sozVar.g(this.g);
        HwWifiP2pTransferManager.d().a(sozVar);
    }

    private long a(String str) {
        return new File(str).length();
    }

    private DeviceInfo f() {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_DEVICES, null, "OtaTransferFile");
        if (deviceList.size() <= 0) {
            return null;
        }
        for (DeviceInfo deviceInfo : deviceList) {
            if (deviceInfo.getDeviceIdentify().equals(this.l)) {
                return deviceInfo;
            }
        }
        return null;
    }

    static class c implements ITransferFileCallback {
        private sol c;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.huawei.unitedevice.callback.ITransferFileCallback
        public void onFailure(int i, String str) throws RemoteException {
        }

        @Override // com.huawei.unitedevice.callback.ITransferFileCallback
        public void onProgress(int i, String str) throws RemoteException {
        }

        @Override // com.huawei.unitedevice.callback.ITransferFileCallback
        public void onSuccess(int i, String str, String str2) throws RemoteException {
        }

        public c(sol solVar) {
            this.c = solVar;
        }

        @Override // com.huawei.unitedevice.callback.ITransferFileCallback
        public void onResponse(int i, String str) throws RemoteException {
            LogUtil.a("OtaTransferFile", "sport device ota stop wifi file response, tell ui code.");
            sol solVar = this.c;
            if (solVar != null) {
                IResultAIDLCallback n = solVar.n();
                UniteDevice i2 = this.c.i();
                try {
                    n.onTransferFailed(109022, i2 != null ? i2.getIdentify() : null);
                } catch (RemoteException e) {
                    LogUtil.b("OtaTransferFile", "reportUiFail Exception", ExceptionUtils.d(e));
                }
                this.c = null;
            }
        }
    }
}
