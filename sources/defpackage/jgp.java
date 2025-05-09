package defpackage;

import android.content.Context;
import android.os.Build;
import android.os.RemoteException;
import android.text.TextUtils;
import com.huawei.callback.BluetoothDataReceiveCallback;
import com.huawei.datatype.DataDeviceInfo;
import com.huawei.datatype.DeviceCommand;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.devicemgr.business.entity.HwGetDevicesParameter;
import com.huawei.hwbasemgr.HwBaseManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.datatypes.TransferFileInfo;
import com.huawei.hwdevice.mainprocess.callback.NotifyPhoneServiceCallback;
import com.huawei.hwdevice.mainprocess.mgr.hwdfxmgr.callback.DeviceDfxBaseResponseCallback;
import com.huawei.hwdevice.outofprocess.util.hwdfx.MaintenaceInterface;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback;
import health.compact.a.CommonUtil;
import health.compact.a.CompileParameterUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/* loaded from: classes5.dex */
public class jgp extends HwBaseManager implements BluetoothDataReceiveCallback, NotifyPhoneServiceCallback {

    /* renamed from: a, reason: collision with root package name */
    private static jgp f13823a;
    private static final Object d = new Object();
    private Context b;
    private IBaseResponseCallback c;
    private int e;
    private int f;
    private WeakReference<DeviceDfxBaseResponseCallback> g;
    private String h;
    private ITransferSleepAndDFXFileCallback.Stub i;
    private String j;
    private ITransferSleepAndDFXFileCallback.Stub k;
    private String l;
    private int m;
    private String n;
    private IBaseResponseCallback o;
    private jfq p;
    private boolean q;
    private boolean r;
    private boolean s;
    private int t;
    private MaintenaceInterface u;
    private int v;
    private int w;
    private DeviceDfxBaseResponseCallback x;
    private int y;

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i, String str) {
        LogUtil.a("HwDeviceDfxManager", "uiCallbackOnSuccess, onSuccess. successMsg = ", str);
        WeakReference<DeviceDfxBaseResponseCallback> weakReference = this.g;
        if (weakReference != null) {
            DeviceDfxBaseResponseCallback deviceDfxBaseResponseCallback = weakReference.get();
            if (deviceDfxBaseResponseCallback != null) {
                deviceDfxBaseResponseCallback.onSuccess(i, str);
                return;
            } else {
                LogUtil.a("HwDeviceDfxManager", "uiCallbackOnSuccess, onSuccess mMaintenanceCallback is null.");
                return;
            }
        }
        LogUtil.a("HwDeviceDfxManager", "uiCallbackOnSuccess, onSuccess mDfxResponseCallBack is null.");
    }

    private jgp(Context context) {
        super(context);
        this.u = null;
        this.v = 1;
        this.h = "";
        this.j = "";
        this.f = -1;
        this.q = false;
        this.t = 0;
        this.m = 2;
        this.y = 0;
        this.o = new IBaseResponseCallback() { // from class: jgp.4
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.c("HwDeviceDfxManager", "mFirmwareVersionCallback, err_code = " + i + ", objData = " + obj);
                if (i == 0 && (obj instanceof DataDeviceInfo)) {
                    jgp jgpVar = jgp.this;
                    jgpVar.a((DataDeviceInfo) obj, jgpVar.k);
                }
            }
        };
        this.k = new ITransferSleepAndDFXFileCallback.Stub() { // from class: jgp.5
            @Override // com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback
            public void onProgress(int i, String str) throws RemoteException {
            }

            @Override // com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback
            public void onSuccess(int i, String str, String str2) throws RemoteException {
                ReleaseLogUtil.e("Dfx_HwDeviceDfxManager", "mDfxTransferSleepAndDfxFileCallback, onSuccess.");
                String j = jsd.j(str);
                LogUtil.a("HwDeviceDfxManager", "mDfxTransferSleepAndDfxFileCallback identify size:", iyl.d().e(j));
                if (jgp.this.x != null) {
                    jgp.this.x.onSuccess(i, j);
                    return;
                }
                ReleaseLogUtil.e("Dfx_HwDeviceDfxManager", "mDfxTransferSleepAndDfxFileCallback, onSuccess mMaintenanceCallback is null.");
                if (jgp.this.g != null) {
                    DeviceDfxBaseResponseCallback deviceDfxBaseResponseCallback = (DeviceDfxBaseResponseCallback) jgp.this.g.get();
                    if (deviceDfxBaseResponseCallback != null) {
                        deviceDfxBaseResponseCallback.onSuccess(i, "");
                        return;
                    } else {
                        LogUtil.b("HwDeviceDfxManager", "mDfxTransferSleepAndDfxFileCallback, onSuccess mDfxTransferSleepAndDfxFileCallback DeviceDfxBaseResponseCallback is null.");
                        return;
                    }
                }
                LogUtil.b("HwDeviceDfxManager", "mDfxTransferSleepAndDfxFileCallback, onFailure mMaintenanceCallback is null.");
            }

            @Override // com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback
            public void onFailure(int i, String str) throws RemoteException {
                ReleaseLogUtil.c("Dfx_HwDeviceDfxManager", "mDfxTransferSleepAndDfxFileCallback, onFailure errCode = ", Integer.valueOf(i), ", errMsg = ", str);
                if (jgp.this.x != null) {
                    jgp.this.x.onFailure(i, str);
                    return;
                }
                ReleaseLogUtil.c("Dfx_HwDeviceDfxManager", "mDfxTransferSleepAndDfxFileCallback, onFailure mDfxTransferSleepAndDfxFileCallback is null.");
                if (jgp.this.g != null) {
                    DeviceDfxBaseResponseCallback deviceDfxBaseResponseCallback = (DeviceDfxBaseResponseCallback) jgp.this.g.get();
                    if (deviceDfxBaseResponseCallback != null) {
                        deviceDfxBaseResponseCallback.onFailure(i, str);
                        return;
                    } else {
                        LogUtil.b("HwDeviceDfxManager", "mDfxTransferSleepAndDfxFileCallback, onFailure mDfxTransferSleepAndDfxFileCallback DeviceDfxBaseResponseCallback is null.");
                        return;
                    }
                }
                LogUtil.b("HwDeviceDfxManager", "mDfxTransferSleepAndDfxFileCallback, onFailure mMaintenanceCallback is null.");
            }
        };
        this.i = new ITransferSleepAndDFXFileCallback.Stub() { // from class: jgp.3
            @Override // com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback
            public void onSuccess(int i, String str, String str2) throws RemoteException {
                LogUtil.a("HwDeviceDfxManager", "mDfxTransferDfxFileUiCallback, onSuccess.");
                if (TextUtils.equals(str2, "5.10.12 cancel timeout 7 minutes")) {
                    jgp.this.c(i, str2);
                    return;
                }
                jsd.c(false);
                LogUtil.a("HwDeviceDfxManager", "mDfxTransferDfxFileUiCallback identify size:", iyl.d().e(jsd.j(str)));
                jgp.this.c(i, "");
            }

            @Override // com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback
            public void onFailure(int i, String str) throws RemoteException {
                LogUtil.a("HwDeviceDfxManager", "mDfxTransferDfxFileUiCallback, onFailure errCode = ", Integer.valueOf(i), ", errMsg = ", str);
                jsd.c(false);
                if (jgp.this.g != null) {
                    DeviceDfxBaseResponseCallback deviceDfxBaseResponseCallback = (DeviceDfxBaseResponseCallback) jgp.this.g.get();
                    if (deviceDfxBaseResponseCallback != null) {
                        deviceDfxBaseResponseCallback.onFailure(i, str);
                        return;
                    } else {
                        LogUtil.a("HwDeviceDfxManager", "mDfxTransferDfxFileUiCallback, onFailure mMaintenanceCallback is null.");
                        return;
                    }
                }
                LogUtil.b("HwDeviceDfxManager", "mDfxTransferDfxFileUiCallback, onFailure mDfxResponseCallBack is null.");
            }

            @Override // com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback
            public void onProgress(int i, String str) throws RemoteException {
                DeviceDfxBaseResponseCallback deviceDfxBaseResponseCallback;
                if (jgp.this.g == null || (deviceDfxBaseResponseCallback = (DeviceDfxBaseResponseCallback) jgp.this.g.get()) == null) {
                    return;
                }
                deviceDfxBaseResponseCallback.onProgress(i, str);
            }
        };
        this.b = context;
        jfq c = jfq.c();
        this.p = c;
        if (c == null) {
            LogUtil.b("HwDeviceDfxManager", "mHwDeviceConfigManager is null.");
            return;
        }
        jsd.c(false);
        this.p.e(10, this);
        LogUtil.c("HwDeviceDfxManager", "HwDeviceDfxManager new object!");
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public Integer getModuleId() {
        return 10;
    }

    public static jgp a(Context context) {
        jgp jgpVar;
        synchronized (d) {
            if (f13823a == null && context != null) {
                LogUtil.c("HwDeviceDfxManager", "getInstance(), context = " + context);
                f13823a = new jgp(BaseApplication.getContext());
            }
            jgpVar = f13823a;
        }
        return jgpVar;
    }

    private void d(DeviceInfo deviceInfo, ITransferSleepAndDFXFileCallback iTransferSleepAndDFXFileCallback, boolean z, String str) {
        if (deviceInfo != null) {
            String softVersion = deviceInfo.getSoftVersion();
            this.j = softVersion;
            ReleaseLogUtil.e("Dfx_HwDeviceDfxManager", "startDfxFileTransferTask() handlerFirmwareVersion(), mDeviceVersion = ", softVersion);
            LogUtil.a("HwDeviceDfxManager", "startDfxFileTransferTask identify size:", iyl.d().e(deviceInfo.getDeviceIdentify()));
            TransferFileInfo c = c(deviceInfo);
            c.setFileLogId(this.n);
            c.setBugTypeId(this.e);
            LogUtil.a("HwDeviceDfxManager", "startDfxFileTransferTask, CommonUtil.isReleaseVersion() = " + CommonUtil.bv());
            if (CommonUtil.bv()) {
                g();
            }
            if (z) {
                c.setLogPreExport(1);
                if ("2".equals(str)) {
                    c.setLogPreExportKind("2");
                } else if ("3".equals(str)) {
                    c.setLogPreExportKind("3");
                } else {
                    LogUtil.a("HwDeviceDfxManager", "startDfxFileTransferTask others");
                }
            }
            this.w = 0;
            e(c);
            if (jsd.g(deviceInfo.getDeviceIdentify())) {
                d(deviceInfo, cwi.c(deviceInfo, 217));
                return;
            }
            if (!CommonUtil.bv()) {
                g();
            }
            jfq.c().a(c, iTransferSleepAndDFXFileCallback);
        }
    }

    private void a(int i, DeviceInfo deviceInfo) {
        try {
            if (c(deviceInfo).getIsFromAbout() == 1) {
                this.i.onSuccess(10000, null, "5.10.12 cancel timeout 7 minutes");
            } else {
                LogUtil.a("HwDeviceDfxManager", "5.10.12 timeout, callback cancel errorCode = ", Integer.valueOf(i));
            }
        } catch (RemoteException e) {
            ExceptionUtils.d(e);
        }
    }

    private void e(int i, DeviceInfo deviceInfo) {
        try {
            if (c(deviceInfo).getIsFromAbout() == 1) {
                this.i.onFailure(i, "5.10.12 default failure");
            } else {
                this.k.onFailure(i, "5.10.12 default failure");
            }
        } catch (RemoteException e) {
            ExceptionUtils.d(e);
        }
    }

    private void g() {
        ReleaseLogUtil.c("Dfx_HwDeviceDfxManager", "resetBugTypeIdFileLogId mFileLogId = " + this.n + ", mBugTypeId = " + this.e);
        this.e = 0;
        this.n = "";
        ReleaseLogUtil.c("Dfx_HwDeviceDfxManager", "resetBugTypeIdFileLogId after mFileLogId = " + this.n + ", mBugTypeId = " + this.e);
    }

    private TransferFileInfo c(DeviceInfo deviceInfo) {
        TransferFileInfo transferFileInfo = new TransferFileInfo();
        if (this.q) {
            transferFileInfo.setIsFromAbout(1);
        } else {
            transferFileInfo.setIsFromAbout(0);
        }
        transferFileInfo.setIsUploadAppLog(this.t);
        transferFileInfo.setType(0);
        transferFileInfo.setRecordId(new ArrayList(16));
        transferFileInfo.setLevel(this.v);
        transferFileInfo.setPriority(this.m);
        transferFileInfo.setSuspend(0);
        transferFileInfo.setTaskType(this.y);
        int productType = deviceInfo.getProductType();
        transferFileInfo.setDeviceMac(deviceInfo.getSecurityDeviceId());
        transferFileInfo.setDeviceSn(deviceInfo.getDeviceIdentify());
        transferFileInfo.setDeviceModel(deviceInfo.getDeviceModel());
        transferFileInfo.setDeviceType(productType);
        transferFileInfo.setDeviceVersion(this.j);
        transferFileInfo.setUdidFromDevice(deviceInfo.getUdidFromDevice());
        transferFileInfo.setSelectedFlag(this.w);
        LogUtil.a("HwDeviceDfxManager", "getTransferFileInfo, isMacEmpty = ", Boolean.valueOf(TextUtils.isEmpty(this.h)), ", transferFileInfo.getDeviceVersion = ", transferFileInfo.getDeviceVersion(), ", deviceInfo.getSoftVersion = ", deviceInfo.getSoftVersion());
        return transferFileInfo;
    }

    private List<DeviceInfo> d() {
        LogUtil.a("HwDeviceDfxManager", "enter getConnectedDeviceList");
        ArrayList arrayList = new ArrayList(16);
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ACTIVE_MAIN_DEVICES, null, "HwDeviceDfxManager");
        List<DeviceInfo> deviceList2 = cun.c().getDeviceList(HwGetDevicesMode.FOLLOWED_DEVICES, null, "HwDeviceDfxManager");
        if (deviceList != null && !deviceList.isEmpty()) {
            arrayList.addAll(deviceList);
        }
        if (deviceList2 != null && !deviceList2.isEmpty()) {
            arrayList.addAll(deviceList2);
        }
        return arrayList;
    }

    public void c(int i, DeviceDfxBaseResponseCallback deviceDfxBaseResponseCallback, boolean z) {
        c();
        List<DeviceInfo> d2 = d();
        if (a(d2)) {
            LogUtil.a("HwDeviceDfxManager", "getMaintenanceFileNoRestrict(), level = " + i);
            this.u = jrx.d();
            this.v = 0;
            this.x = deviceDfxBaseResponseCallback;
            this.r = z;
            if (z) {
                String b = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10), "beta_club_fault_code");
                this.e = CommonUtil.m(BaseApplication.getContext(), b);
                this.l = SharedPreferenceManager.c(BaseApplication.getContext());
                LogUtil.a("HwDeviceDfxManager", "faultCode: ", b, ", mBugTypeId: ", Integer.valueOf(this.e), ", mDtsNumber: ", this.l);
                this.s = false;
                this.y = 1;
            } else {
                h();
                this.s = true;
                this.y = 2;
            }
            c(false);
            this.t = 1;
            this.m = 1;
            for (int i2 = 0; i2 < d2.size(); i2++) {
                DeviceInfo deviceInfo = d2.get(i2);
                if (!b(deviceInfo)) {
                    ReleaseLogUtil.c("Dfx_HwDeviceDfxManager", "getMaintenanceFileNoRestrict(), not support!");
                } else if (!e(deviceInfo) && !z) {
                    if (deviceDfxBaseResponseCallback != null) {
                        deviceDfxBaseResponseCallback.onFailure(2, "time is not ok!!!");
                    }
                    ReleaseLogUtil.e("Dfx_HwDeviceDfxManager", "getMaintenanceFileNoRestrict(), time is not ok.");
                } else {
                    b(z, deviceInfo);
                }
            }
        }
    }

    private void b(boolean z, DeviceInfo deviceInfo) {
        boolean c = cwi.c(deviceInfo, 134);
        LogUtil.a("HwDeviceDfxManager", "handlerGetDeviceLog isSupportGet earPhone log isSupport: ", Boolean.valueOf(c));
        if (z && c && deviceInfo != null) {
            d(deviceInfo, this.k, true, "3");
        } else {
            d(deviceInfo, this.k, false, "");
        }
    }

    @Override // com.huawei.hwdevice.mainprocess.callback.NotifyPhoneServiceCallback
    public void executeResponse(int i, DeviceInfo deviceInfo, int i2, String str) {
        if (i == 0) {
            LogUtil.a("HwDeviceDfxManager", "executeResponse progress reserve: ", Integer.valueOf(i2));
            if (i2 == 100) {
                d(deviceInfo, this.k, false, "");
                jfq.c().a("earphoneLogPre");
                return;
            }
            return;
        }
        if (i == 1) {
            LogUtil.a("HwDeviceDfxManager", "executeResponse timeout");
            d(deviceInfo, this.k, false, "");
            jfq.c().a("earphoneLogPre");
            return;
        }
        LogUtil.a("HwDeviceDfxManager", "others");
    }

    private boolean a(List<DeviceInfo> list) {
        boolean z;
        if (list.isEmpty()) {
            LogUtil.h("HwDeviceDfxManager", "connectedDeviceList is empty");
            z = false;
        } else {
            z = true;
        }
        if (!CompileParameterUtil.a("SUPPORT_LOG_FEEDBACK")) {
            ReleaseLogUtil.c("Dfx_HwDeviceDfxManager", "getMaintenanceFileNoRestrict(), the version do not support.");
            z = false;
        }
        if (!jsd.b()) {
            return z;
        }
        ReleaseLogUtil.e("Dfx_HwDeviceDfxManager", "getMaintenanceFileNoRestrict(), collecting device log manually.");
        return false;
    }

    private void c() {
        for (int i = 0; i < 10; i++) {
            if (jez.e() == null) {
                LogUtil.h("HwDeviceDfxManager", "phoneService binder is null,init PhoneService binder");
                try {
                    Thread.sleep(500L);
                } catch (InterruptedException unused) {
                    LogUtil.b("HwDeviceDfxManager", "Exception is InterruptedException");
                }
            } else {
                LogUtil.a("HwDeviceDfxManager", "phoneService binder is not null");
                return;
            }
        }
    }

    public void c(int i, DeviceDfxBaseResponseCallback deviceDfxBaseResponseCallback) {
        if (jsd.b()) {
            LogUtil.a("HwDeviceDfxManager", "getCrowdTestAndMaintenance(), collecting device log manually.");
            return;
        }
        this.v = 0;
        this.x = deviceDfxBaseResponseCallback;
        this.u = jrx.d();
        this.s = false;
        c(false);
        this.t = 0;
        this.m = 1;
        this.y = 1;
        new Thread(new Runnable() { // from class: jgp.2
            @Override // java.lang.Runnable
            public void run() {
                jot.a().a(jgp.this.o);
            }
        }).start();
    }

    public void c(int i, String str, DeviceDfxBaseResponseCallback deviceDfxBaseResponseCallback) {
        if (jsd.b()) {
            LogUtil.a("HwDeviceDfxManager", "getDfxBetaQuestion(), collecting device log manually.");
            return;
        }
        LogUtil.a("HwDeviceDfxManager", "getDfxBetaQuestion(), level = ", Integer.valueOf(i), ", callback = ", deviceDfxBaseResponseCallback);
        this.v = 0;
        this.x = deviceDfxBaseResponseCallback;
        this.u = jrx.d();
        this.s = false;
        c(false);
        this.t = 0;
        this.m = 1;
        this.y = 1;
        DeviceInfo e = jpt.e(str, "HwDeviceDfxManager");
        boolean c = cwi.c(e, 134);
        LogUtil.a("HwDeviceDfxManager", "goBetaFeedBack handlerGetDeviceLog isSupportGet earPhone log isSupport: ", Boolean.valueOf(c));
        if (c) {
            d(e, this.k, true, "2");
        } else {
            d(e, this.k, false, "");
        }
    }

    public boolean b(DeviceInfo deviceInfo) {
        boolean o = Utils.o();
        boolean e = knx.e();
        LogUtil.a("HwDeviceDfxManager", "isDeviceSupport(), BuildConfig.SUPPORT_LOG_FEEDBACK = " + CompileParameterUtil.a("SUPPORT_LOG_FEEDBACK") + ", isExperience = " + e + ",isOversea: " + o);
        if (deviceInfo == null) {
            return false;
        }
        this.f = deviceInfo.getProductType();
        LogUtil.c("HwDeviceDfxManager", "isDeviceSupport(), mDeviceType = " + this.f);
        if (jfu.i(this.f)) {
            return (CompileParameterUtil.a("SUPPORT_LOG_FEEDBACK") || CompileParameterUtil.a("RELEASE_EVENT_LOG_UPLOAD")) && (jad.d(48) && e) && !o;
        }
        return false;
    }

    public boolean b(String str) {
        boolean z;
        boolean e = knx.e();
        ReleaseLogUtil.e("Dfx_HwDeviceDfxManager", "isOverseaDeviceSupport(), BuildConfig.SUPPORT_LOG_FEEDBACK = ", Boolean.valueOf(CompileParameterUtil.a("SUPPORT_LOG_FEEDBACK")), " isExperience = ", Boolean.valueOf(e));
        DeviceInfo f = jsd.f(str);
        if (f != null) {
            this.f = f.getProductType();
            ReleaseLogUtil.e("Dfx_HwDeviceDfxManager", "isOverseaDeviceSupport(), mDeviceType = " + this.f);
            if (jfu.i(this.f) && ((CompileParameterUtil.a("SUPPORT_LOG_FEEDBACK") || CompileParameterUtil.a("RELEASE_EVENT_LOG_UPLOAD")) && e)) {
                z = true;
                ReleaseLogUtil.e("Dfx_HwDeviceDfxManager", "isOverseaDeviceSupport = ", Boolean.valueOf(z));
                return z;
            }
        }
        z = false;
        ReleaseLogUtil.e("Dfx_HwDeviceDfxManager", "isOverseaDeviceSupport = ", Boolean.valueOf(z));
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(DataDeviceInfo dataDeviceInfo, ITransferSleepAndDFXFileCallback iTransferSleepAndDFXFileCallback) {
        if (dataDeviceInfo != null) {
            String deviceSoftVersion = dataDeviceInfo.getDeviceSoftVersion();
            this.j = deviceSoftVersion;
            ReleaseLogUtil.e("Dfx_HwDeviceDfxManager", "handlerFirmwareVersion(), mDeviceVersion = ", deviceSoftVersion);
            this.h = dataDeviceInfo.getDeviceBtMac();
            LogUtil.a("HwDeviceDfxManager", "handlerFirmwareVersion() identify size:", iyl.d().e(this.h));
            DeviceInfo e = jpt.e(this.h, "HwDeviceDfxManager");
            if (e == null) {
                ReleaseLogUtil.c("Dfx_HwDeviceDfxManager", "handlerFirmwareVersion(), deviceInfo is null.");
                return;
            }
            TransferFileInfo transferFileInfo = new TransferFileInfo();
            d(transferFileInfo, dataDeviceInfo, e);
            this.w = 0;
            e(transferFileInfo);
            if (jsd.g(this.h)) {
                d(e, cwi.c(e, 217));
            } else {
                jfq.c().a(transferFileInfo, iTransferSleepAndDFXFileCallback);
            }
        }
    }

    private void e(TransferFileInfo transferFileInfo) {
        ReleaseLogUtil.e("Dfx_HwDeviceDfxManager", "printTransferInfo transferFileInfo.getType() = " + transferFileInfo.getType() + ", getTaskType() = " + transferFileInfo.getTaskType() + ", mBugTypeId = " + this.e + ", mTaskType = " + this.y + ", mDtsNumber = " + this.l + ", mFileLogId = " + this.n);
    }

    private void d(DeviceInfo deviceInfo, boolean z) {
        DeviceDfxBaseResponseCallback deviceDfxBaseResponseCallback;
        LogUtil.a("HwDeviceDfxManager", "postSmartWatchLadingBill(). taskType = " + this.y + ", bugTypeId = " + this.e + ", dtsNumber = " + this.l + ", isSupportSmartWifiLog = " + z + ", CommonUtil.isReleaseVersion() = " + CommonUtil.bv());
        int i = this.y;
        if (i != 0) {
            if (i == 1) {
                e(this.e, this.l, deviceInfo);
                if (!CommonUtil.bv()) {
                    if (z) {
                        LogUtil.a("HwDeviceDfxManager", "postSmartWatchLadingBill ADVICE_TASK isSupportSmartWifiLog is true");
                        return;
                    }
                    g();
                }
                d(this.s, deviceInfo.getDeviceIdentify());
                return;
            }
            LogUtil.h("HwDeviceDfxManager", "enter else");
            return;
        }
        e(this.e, this.l, deviceInfo);
        if (!CommonUtil.bv()) {
            if (z) {
                LogUtil.a("HwDeviceDfxManager", "postSmartWatchLadingBill COMMON_TASK isSupportSmartWifiLog is true");
                return;
            }
            g();
        }
        jsd.c(false);
        WeakReference<DeviceDfxBaseResponseCallback> weakReference = this.g;
        if (weakReference == null || (deviceDfxBaseResponseCallback = weakReference.get()) == null) {
            return;
        }
        deviceDfxBaseResponseCallback.onSuccess(0, null);
    }

    public static void e(int i, String str, DeviceInfo deviceInfo) {
        if (str == null) {
            LogUtil.b("HwDeviceDfxManager", "sendToSmartWatch(), dtsNumber is null.");
            return;
        }
        ReleaseLogUtil.e("Dfx_HwDeviceDfxManager", "5.10.12 sendToSmartWatch(), bugTypeId = ", Integer.valueOf(i), ", dtsNumber = ", str);
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(10);
        deviceCommand.setCommandID(12);
        if (str.isEmpty()) {
            str = "00000000";
        }
        String c = cvx.c(str);
        String str2 = (cvx.e(1) + cvx.e(c.length() / 2) + c) + (cvx.e(2) + cvx.e(4) + cvx.b(i));
        deviceCommand.setDataContent(cvx.a(str2));
        deviceCommand.setDataLen(cvx.a(str2).length);
        deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        jfq.c().b(deviceCommand);
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public void onDestroy() {
        super.onDestroy();
        jsd.c(false);
        c(false);
    }

    public void a(String str, DeviceDfxBaseResponseCallback deviceDfxBaseResponseCallback, int i) {
        this.w = i;
        a(str, deviceDfxBaseResponseCallback);
    }

    public void a(String str, DeviceDfxBaseResponseCallback deviceDfxBaseResponseCallback) {
        long currentTimeMillis = System.currentTimeMillis() - SharedPreferenceManager.c(BaseApplication.getContext(), "BETA_CLUB_QUESTION_NUMBER_TIME");
        if (currentTimeMillis > 43200000) {
            LogUtil.a("HwDeviceDfxManager", "getDeviceLog more than twelve hours, timeDifference: ", Long.valueOf(currentTimeMillis));
            h();
        } else {
            this.e = 0;
            String c = SharedPreferenceManager.c(BaseApplication.getContext());
            this.l = c;
            LogUtil.a("HwDeviceDfxManager", "getDeviceLog mDtsNumber: ", c, ", timeDifference: ", Long.valueOf(currentTimeMillis));
        }
        this.s = false;
        jsd.c(true);
        c(true);
        this.t = 0;
        this.m = 3;
        this.y = 0;
        this.u = jrx.d();
        this.v = 0;
        this.g = new WeakReference<>(deviceDfxBaseResponseCallback);
        DeviceInfo e = jpt.e(str, "HwDeviceDfxManager");
        LogUtil.a("HwDeviceDfxManager", "getDeviceLog mDfxTransferDfxFileUiCallback");
        d(e, this.i, false, "");
    }

    public void d(final boolean z, final String str) {
        LogUtil.c("HwDeviceDfxManager", "startUploadLogFile().");
        ThreadPoolManager.d().c("HwDeviceDfxManager", new Runnable() { // from class: jgp.1
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.c("HwDeviceDfxManager", "BuildConfig.RELEASE_EVENT_LOG_UPLOAD: " + CompileParameterUtil.a("RELEASE_EVENT_LOG_UPLOAD"));
                if (CompileParameterUtil.a("RELEASE_EVENT_LOG_UPLOAD")) {
                    jsd.e(jgp.this.b, str);
                    return;
                }
                if (Utils.o()) {
                    jeq.e().setProductType(20);
                } else {
                    jeq.e().setProductType(1);
                }
                jsd.a(jgp.this.b, z, str);
            }
        });
    }

    public boolean e(DeviceInfo deviceInfo) {
        if (deviceInfo != null && deviceInfo.getProductType() == 34) {
            ReleaseLogUtil.e("Dfx_HwDeviceDfxManager", "current device is Latona");
            return true;
        }
        int i = Calendar.getInstance().get(11);
        LogUtil.a("HwDeviceDfxManager", "isLimitCollectLog(), localHour = " + i);
        if (i >= 6 && i < 10) {
            ReleaseLogUtil.e("Dfx_HwDeviceDfxManager", "isLimitCollectLog(), ", 6, " < localHour < ", 10, " return !!!");
            return false;
        }
        if (i < 17 || i >= 22) {
            return true;
        }
        ReleaseLogUtil.e("Dfx_HwDeviceDfxManager", "isLimitCollectLog(), ", 17, " < localHour < ", 22, " return !!!");
        return false;
    }

    private void d(TransferFileInfo transferFileInfo, DataDeviceInfo dataDeviceInfo, DeviceInfo deviceInfo) {
        if (this.q) {
            transferFileInfo.setIsFromAbout(1);
        } else {
            transferFileInfo.setIsFromAbout(0);
        }
        transferFileInfo.setIsUploadAppLog(this.t);
        transferFileInfo.setType(0);
        transferFileInfo.setRecordId(new ArrayList(16));
        transferFileInfo.setLevel(this.v);
        transferFileInfo.setPriority(this.m);
        transferFileInfo.setSuspend(0);
        transferFileInfo.setTaskType(this.y);
        int deviceType = dataDeviceInfo.getDeviceType();
        if (deviceType == 10) {
            transferFileInfo.setDeviceMac(dataDeviceInfo.getDeviceSn());
        } else {
            transferFileInfo.setDeviceMac(deviceInfo.getSecurityDeviceId());
        }
        transferFileInfo.setDeviceSn(deviceInfo.getDeviceIdentify());
        transferFileInfo.setDeviceModel(deviceInfo.getDeviceModel());
        transferFileInfo.setDeviceType(deviceType);
        transferFileInfo.setDeviceVersion(this.j);
        transferFileInfo.setUdidFromDevice(deviceInfo.getUdidFromDevice());
        transferFileInfo.setSelectedFlag(this.w);
        LogUtil.a("HwDeviceDfxManager", "iniTransferFileInfo, isMacEmpty = ", Boolean.valueOf(TextUtils.isEmpty(this.h)), ", transferFileInfo.getDeviceVersion = ", transferFileInfo.getDeviceVersion(), ", dataDeviceInfo.getDeviceSoftVersion = ", dataDeviceInfo.getDeviceSoftVersion(), ", deviceInfo.getSoftVersion = ", deviceInfo.getSoftVersion());
    }

    public void d(int i, String str, String str2) {
        this.e = i;
        this.l = str;
        this.n = str2;
    }

    private void h() {
        this.e = 0;
        this.l = "00000000";
    }

    public void a() {
        synchronized (d) {
            this.c = null;
        }
    }

    public void c(DeviceInfo deviceInfo, IBaseResponseCallback iBaseResponseCallback) {
        this.c = iBaseResponseCallback;
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(10);
        deviceCommand.setCommandID(15);
        String str = cvx.e(1) + cvx.e(1) + cvx.e(0);
        deviceCommand.setDataContent(cvx.a(str));
        deviceCommand.setDataLen(cvx.a(str).length);
        if (deviceInfo != null) {
            deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        }
        jfq jfqVar = this.p;
        if (jfqVar != null) {
            jfqVar.b(deviceCommand);
        }
    }

    @Override // com.huawei.callback.BluetoothDataReceiveCallback
    public void onDataReceived(int i, DeviceInfo deviceInfo, byte[] bArr) {
        LogUtil.a("HwDeviceDfxManager", "onDataReceived errorCode: ", Integer.valueOf(i), ", deviceInfo = ", deviceInfo);
        if (bArr == null) {
            LogUtil.h("HwDeviceDfxManager", "onDataReceived, data is null");
            return;
        }
        if (bArr.length < 1) {
            LogUtil.h("HwDeviceDfxManager", "onDataReceived data length less than 1");
            return;
        }
        byte b = bArr[1];
        if (b != 12) {
            if (b == 15) {
                if (this.c != null) {
                    LogUtil.a("HwDeviceDfxManager", "device capture response");
                    this.c.d(i, bArr);
                    return;
                }
                return;
            }
            LogUtil.h("HwDeviceDfxManager", "onDataReceived, default");
            return;
        }
        ReleaseLogUtil.e("Dfx_HwDeviceDfxManager", "onDataReceived 5.10.12, CommonUtil.isReleaseVersion() = " + CommonUtil.bv());
        if (CommonUtil.bv()) {
            return;
        }
        HwGetDevicesParameter hwGetDevicesParameter = new HwGetDevicesParameter();
        hwGetDevicesParameter.setDeviceIdentify(deviceInfo.getDeviceIdentify());
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.IDENTIFY_DEVICES, hwGetDevicesParameter, "HwDeviceDfxManager");
        if (deviceList == null || deviceList.isEmpty()) {
            LogUtil.h("HwDeviceDfxManager", "onDataReceived 5.10.12 list = null or empty");
            return;
        }
        DeviceInfo deviceInfo2 = deviceList.get(0);
        boolean c = cwi.c(deviceInfo2, 217);
        int c2 = c(bArr);
        LogUtil.a("HwDeviceDfxManager", "onDataReceived isSupportSmartWifiLog = ", Boolean.valueOf(c), ", 5.10.12 errorCode = ", Integer.valueOf(c2));
        if (c) {
            a(c2, deviceInfo2);
            if (c2 == 100000) {
                a(deviceInfo2);
            } else {
                e(c2, deviceInfo2);
            }
        }
    }

    private int c(byte[] bArr) {
        int i = 0;
        if (bArr != null && bArr.length == 8 && bArr[2] == Byte.MAX_VALUE) {
            String d2 = cvx.d(bArr);
            LogUtil.a("HwDeviceDfxManager", "parseResultCode Cassini messageHex = ", d2);
            if (d2 != null) {
                try {
                } catch (NumberFormatException unused) {
                    LogUtil.b("HwDeviceDfxManager", "parseResultCode mDeviceResponseCallback NumberFormatException");
                }
                if (d2.length() >= 9) {
                    i = Integer.parseInt(d2.substring(8, d2.length()), 16);
                    LogUtil.a("HwDeviceDfxManager", "parseResultCode Cassini messageHex errorCode =", Integer.valueOf(i));
                }
            }
            return 0;
        }
        LogUtil.a("HwDeviceDfxManager", "parseResultCode Cassini messageHex errorCode =", Integer.valueOf(i));
        return i;
    }

    private void a(DeviceInfo deviceInfo) {
        LogUtil.a("HwDeviceDfxManager", "startDfxFileSmartTask 5.10.12 startDfxFileSmartTask, deviceInfo = ", deviceInfo);
        if (deviceInfo != null) {
            LogUtil.a("HwDeviceDfxManager", "startDfxFileSmartTask mFileLogId: ", this.n, " ,mBugTypeId: ", Integer.valueOf(this.e));
            TransferFileInfo c = c(deviceInfo);
            c.setFileLogId(this.n);
            c.setBugTypeId(this.e);
            LogUtil.a("HwDeviceDfxManager", "startDfxFileSmartTask transferFileInfo mFileLogId: ", c.getFileLogId(), " ,mBugTypeId: ", Integer.valueOf(c.getBugTypeId()));
            g();
            LogUtil.a("HwDeviceDfxManager", "startDfxFileSmartTask 5.10.12 startDfxFileSmartTask 5.10.1");
            if (c.getIsFromAbout() == 1) {
                jfq.c().a(c, this.i);
                return;
            } else {
                jfq.c().a(c, this.k);
                return;
            }
        }
        ReleaseLogUtil.e("Dfx_HwDeviceDfxManager", "startDfxFileSmartTask deviceInfo == null");
    }

    public boolean b() {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_DEVICES, null, "HwDeviceDfxManager");
        if (deviceList != null && deviceList.size() != 0) {
            for (DeviceInfo deviceInfo : deviceList) {
                if (!cvz.c(deviceInfo)) {
                    int versionType = deviceInfo.getVersionType();
                    ReleaseLogUtil.e("Dfx_HwDeviceDfxManager", "Device version type is ", Integer.valueOf(versionType));
                    boolean z = CompileParameterUtil.a("IS_RELEASE_VERSION") && (versionType == 0);
                    boolean i = Utils.i();
                    boolean i2 = i();
                    boolean o = Utils.o();
                    boolean z2 = (!z || Build.BRAND.toLowerCase(Locale.ENGLISH).contains("nubia") || jsd.g(deviceInfo.getDeviceIdentify())) ? false : true;
                    boolean z3 = (o || !z2 || e()) ? false : true;
                    boolean z4 = o && z2 && i2 && i;
                    ReleaseLogUtil.e("Dfx_HwDeviceDfxManager", "isAllowLogin: ", Boolean.valueOf(i), ", isOpen: ", Boolean.valueOf(i2), ", isOverSea: ", Boolean.valueOf(o));
                    if (z3 || z4) {
                        ReleaseLogUtil.e("Dfx_HwDeviceDfxManager", "show setting_about_release_question.");
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean i() {
        String e = jah.c().e("common_config_feedbacksdk_switch");
        LogUtil.a("HwDeviceDfxManager", "isFeedbackSwitchOpen , COMMON_CONFIG_FEEDBACKSDK_SWITCH = ", e);
        return "on".equalsIgnoreCase(e);
    }

    private static boolean e() {
        String e = jah.c().e("common_config_feedbacksdk_switch");
        LogUtil.a("HwDeviceDfxManager", "isFeedbackSwitchClose , COMMON_CONFIG_FEEDBACKSDK_SWITCH = ", e);
        return "off".equalsIgnoreCase(e);
    }

    private void c(boolean z) {
        this.q = z;
        SharedPreferenceManager.e(String.valueOf(10), "KEY_IS_CLICKED_BETA_FEEDBACK", z);
    }
}
