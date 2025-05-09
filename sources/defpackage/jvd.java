package defpackage;

import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;
import android.text.TextUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.HwBaseManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.datatypes.TransferFileInfo;
import com.huawei.hwdevice.mainprocess.mgr.hwdfxmgr.callback.DeviceDfxBaseResponseCallback;
import com.huawei.hwdevice.mainprocess.receiver.hwdfx.DeviceTriggerDfxReceiver;
import com.huawei.hwdevice.outofprocess.util.hwdfx.MaintenaceInterface;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback;
import health.compact.a.CommonUtil;
import health.compact.a.CompileParameterUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/* loaded from: classes5.dex */
public class jvd extends HwBaseManager {
    private static final Object c = new Object();
    private static jvd d;

    /* renamed from: a, reason: collision with root package name */
    private Context f14114a;
    private ITransferSleepAndDFXFileCallback.Stub b;
    private int e;
    private MaintenaceInterface f;
    private DeviceDfxBaseResponseCallback g;
    private ITransferSleepAndDFXFileCallback.Stub i;

    private jvd(Context context) {
        super(context);
        this.e = -1;
        this.f = null;
        this.b = new ITransferSleepAndDFXFileCallback.Stub() { // from class: jvd.3
            @Override // com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback
            public void onSuccess(int i, String str, String str2) throws RemoteException {
                LogUtil.a("HwFileDeviceDfxManager", "mDfxTransferSleepAndDfxFileCallback, onSuccess.");
                String j = jsd.j(str);
                LogUtil.a("HwFileDeviceDfxManager", "mDfxTransferSleepAndDfxFileCallback identify size:", iyl.d().e(j));
                SharedPreferenceManager.e(String.valueOf(10), "contain_app_log", false);
                jvd jvdVar = jvd.this;
                if (!jvdVar.e(jvdVar.f14114a)) {
                    if (jvd.h(j)) {
                        jvd.this.g(j);
                    }
                } else {
                    LogUtil.a("HwFileDeviceDfxManager", "isMainProcess is true");
                    jvd.this.g(j);
                }
            }

            @Override // com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback
            public void onFailure(int i, String str) throws RemoteException {
                ReleaseLogUtil.c("Dfx_HwFileDeviceDfxManager", "mDfxTransferSleepAndDfxFileCallback, onFailure errCode: ", Integer.valueOf(i), ", errMsg = ", str);
            }

            @Override // com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback
            public void onProgress(int i, String str) throws RemoteException {
                LogUtil.a("HwFileDeviceDfxManager", "mDfxTransferSleepAndDfxFileCallback, onProgress");
            }
        };
        this.i = new ITransferSleepAndDFXFileCallback.Stub() { // from class: jvd.5
            @Override // com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback
            public void onProgress(int i, String str) throws RemoteException {
            }

            @Override // com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback
            public void onSuccess(int i, String str, String str2) throws RemoteException {
                ReleaseLogUtil.e("Dfx_HwFileDeviceDfxManager", "mDfxTransferSleepAndDfxFileCallback, onSuccess.");
                String j = jsd.j(str);
                LogUtil.a("HwFileDeviceDfxManager", "mDfxTransferSleepAndDfxFileCallback identify size:", iyl.d().e(j));
                jvd.this.b(knl.d(j), true);
                if (jvd.this.g != null) {
                    jvd.this.g.onSuccess(i, j);
                } else {
                    ReleaseLogUtil.e("Dfx_HwFileDeviceDfxManager", "mDfxTransferSleepAndDfxFileCallback, onSuccess mMaintenanceCallback is null.");
                }
            }

            @Override // com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback
            public void onFailure(int i, String str) throws RemoteException {
                ReleaseLogUtil.c("Dfx_HwFileDeviceDfxManager", "mDfxTransferSleepAndDfxFileCallback, onFailure errCode = ", Integer.valueOf(i), ", errMsg = ", str);
                if (jvd.this.g != null) {
                    jvd.this.g.onFailure(i, str);
                } else {
                    ReleaseLogUtil.c("Dfx_HwFileDeviceDfxManager", "mDfxTransferSleepAndDfxFileCallback, onFailure mMaintenanceCallback is null.");
                }
            }
        };
        this.f14114a = context;
        LogUtil.c("HwFileDeviceDfxManager", "HwFileDeviceDfxManager new object!");
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public Integer getModuleId() {
        return 10;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean e(Context context) {
        return BaseApplication.isRunningForeground() || CommonUtil.x(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g(String str) {
        Intent intent = new Intent("com.huawei.bone.dfx.device_trigger_upload_log");
        intent.setPackage(BaseApplication.getAppPackage());
        intent.putExtra("pre_log_export_mac", str);
        intent.putExtra("isAuto", true);
        intent.setClass(BaseApplication.getContext(), DeviceTriggerDfxReceiver.class);
        BaseApplication.getContext().sendBroadcast(intent, LocalBroadcast.c);
    }

    public static jvd d(Context context) {
        jvd jvdVar;
        synchronized (c) {
            if (d == null && context != null) {
                LogUtil.c("HwFileDeviceDfxManager", "getInstance(), context: ", context);
                d = new jvd(context);
            }
            jvdVar = d;
        }
        return jvdVar;
    }

    public void d() {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_DEVICES, null, "HwFileDeviceDfxManager");
        if (deviceList == null || deviceList.isEmpty()) {
            LogUtil.h("HwFileDeviceDfxManager", "connectedDeviceList is empty");
            return;
        }
        if (!CompileParameterUtil.a("SUPPORT_LOG_FEEDBACK")) {
            LogUtil.h("HwFileDeviceDfxManager", "getLogFileReportedByDevice(), the version do not support.");
            return;
        }
        LogUtil.a("HwFileDeviceDfxManager", "enter getLogFileReportedByDevice()");
        for (int i = 0; i < deviceList.size(); i++) {
            DeviceInfo deviceInfo = deviceList.get(i);
            if (!c(deviceInfo)) {
                LogUtil.b("HwFileDeviceDfxManager", "getLogFileReportedByDevice(), not support!");
            } else if (!a(deviceInfo)) {
                LogUtil.a("HwFileDeviceDfxManager", "getLogFileReportedByDevice(), time is not ok.");
            } else {
                e(deviceInfo, this.b);
            }
        }
    }

    private TransferFileInfo b(DeviceInfo deviceInfo) {
        TransferFileInfo transferFileInfo = new TransferFileInfo();
        transferFileInfo.setIsFromAbout(0);
        transferFileInfo.setIsUploadAppLog(1);
        transferFileInfo.setType(0);
        transferFileInfo.setRecordId(new ArrayList(16));
        transferFileInfo.setLevel(0);
        transferFileInfo.setPriority(1);
        transferFileInfo.setSuspend(0);
        transferFileInfo.setTaskType(2);
        int productType = deviceInfo.getProductType();
        transferFileInfo.setDeviceMac(deviceInfo.getSecurityDeviceId());
        transferFileInfo.setDeviceSn(deviceInfo.getDeviceIdentify());
        transferFileInfo.setDeviceModel(deviceInfo.getDeviceModel());
        transferFileInfo.setDeviceType(productType);
        transferFileInfo.setDeviceVersion(deviceInfo.getSoftVersion());
        transferFileInfo.setUdidFromDevice(deviceInfo.getUdidFromDevice());
        transferFileInfo.setSelectedFlag(0);
        return transferFileInfo;
    }

    private boolean c(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            return false;
        }
        boolean o = Utils.o();
        boolean e = knx.e();
        this.e = deviceInfo.getProductType();
        LogUtil.a("HwFileDeviceDfxManager", "isDeviceSupport(), BuildConfig.SUPPORT_LOG_FEEDBACK: ", Boolean.valueOf(CompileParameterUtil.a("SUPPORT_LOG_FEEDBACK")), ", isExperience: ", Boolean.valueOf(e), ",isOversea: ", Boolean.valueOf(o));
        LogUtil.c("HwFileDeviceDfxManager", "isDeviceSupport(), mDeviceType: ", Integer.valueOf(this.e));
        if (juu.i(this.e)) {
            return (CompileParameterUtil.a("SUPPORT_LOG_FEEDBACK") || CompileParameterUtil.a("RELEASE_EVENT_LOG_UPLOAD")) && (jad.d(48) && e) && !o;
        }
        return false;
    }

    private void e(DeviceInfo deviceInfo, ITransferSleepAndDFXFileCallback iTransferSleepAndDFXFileCallback) {
        if (deviceInfo != null) {
            LogUtil.a("HwFileDeviceDfxManager", "handlerFirmwareVersion(), mDeviceVersion: ", deviceInfo.getSoftVersion());
            LogUtil.a("HwFileDeviceDfxManager", "startDfxFileTransferTask identify size: ", iyl.d().e(deviceInfo.getDeviceIdentify()));
            b(knl.d(deviceInfo.getDeviceIdentify()), false);
            TransferFileInfo b = b(deviceInfo);
            if (jsd.d(deviceInfo)) {
                ReleaseLogUtil.e("Dfx_HwFileDeviceDfxManager", "isSupportSmartMcuDeviceLog is true");
                jvl.b().b(b, iTransferSleepAndDFXFileCallback);
            } else if (!i(deviceInfo.getDeviceIdentify())) {
                ReleaseLogUtil.e("Dfx_HwFileDeviceDfxManager", "not isSelfUploadDeviceLog");
                jvl.b().b(b, iTransferSleepAndDFXFileCallback);
            } else {
                ReleaseLogUtil.e("Dfx_HwFileDeviceDfxManager", "isSelfUploadDeviceLog");
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x0021, code lost:
    
        if (r5.isSupportSelfUploadDeviceLog() != false) goto L13;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean i(java.lang.String r5) {
        /*
            r4 = this;
            android.content.Context r0 = r4.f14114a
            com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.dmsmanager.HwDeviceMgrInterface r0 = defpackage.jsz.b(r0)
            r1 = 1
            java.lang.String r2 = "HwFileDeviceDfxManager"
            java.util.Map r0 = r0.queryDeviceCapability(r1, r5, r2)
            if (r0 == 0) goto L24
            boolean r3 = r0.isEmpty()
            if (r3 != 0) goto L24
            java.lang.Object r5 = r0.get(r5)
            com.huawei.health.devicemgr.business.entity.DeviceCapability r5 = (com.huawei.health.devicemgr.business.entity.DeviceCapability) r5
            if (r5 == 0) goto L2d
            boolean r5 = r5.isSupportSelfUploadDeviceLog()
            if (r5 == 0) goto L2d
            goto L2e
        L24:
            java.lang.String r5 = "isSelfUploadDeviceLog queryDeviceCapability is null"
            java.lang.Object[] r5 = new java.lang.Object[]{r5}
            com.huawei.hwlogsmodel.LogUtil.h(r2, r5)
        L2d:
            r1 = 0
        L2e:
            java.lang.String r5 = "isSelfUploadDeviceLog: "
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r1)
            java.lang.Object[] r5 = new java.lang.Object[]{r5, r0}
            java.lang.String r0 = "Dfx_HwFileDeviceDfxManager"
            health.compact.a.hwlogsmodel.ReleaseLogUtil.e(r0, r5)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.jvd.i(java.lang.String):boolean");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean h(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String dayDateTime = new jrx().getDayDateTime();
        LogUtil.a("HwFileDeviceDfxManager", "strCurrentTime: ", dayDateTime);
        if (d(str, dayDateTime)) {
            e(str, dayDateTime, "1");
            LogUtil.a("HwFileDeviceDfxManager", "isCanUploadLog isMoreThanOneDay");
        } else {
            int e = e(str);
            LogUtil.a("HwFileDeviceDfxManager", "needUpload(), retryNumber: ", Integer.valueOf(e));
            if (e < 2) {
                e(str, dayDateTime, String.valueOf(e + 1));
            } else {
                LogUtil.a("HwFileDeviceDfxManager", "needUpload(), but Uploaded 2 times today");
                return false;
            }
        }
        return true;
    }

    private static boolean d(String str, String str2) {
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10), str + "_maintenance_start_time");
        LogUtil.a("HwFileDeviceDfxManager", "isMoreThanOneDay, strLastTime: ", b);
        if (TextUtils.isEmpty(b)) {
            LogUtil.h("HwFileDeviceDfxManager", "strLastTime is isEmpty");
            return true;
        }
        if (TextUtils.isEmpty(str2)) {
            LogUtil.h("HwFileDeviceDfxManager", "strCurrentTime is isEmpty");
            return false;
        }
        LogUtil.a("HwFileDeviceDfxManager", "delayedOneDay(), strLastTime: ", b, ", strCurTime: ", str2);
        return CommonUtil.n(BaseApplication.getContext(), str2) - CommonUtil.n(BaseApplication.getContext(), b) >= 86400000;
    }

    private static int e(String str) {
        LogUtil.a("HwFileDeviceDfxManager", "enter getRetryNumber");
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10), str + "_maintenance_start_retry_number");
        if (!nsn.a(b)) {
            return 0;
        }
        int m = CommonUtil.m(BaseApplication.getContext(), b);
        LogUtil.a("HwFileDeviceDfxManager", "getRetryNumber, retryNumber: ", Integer.valueOf(m));
        return m;
    }

    private static void e(String str, String str2, String str3) {
        LogUtil.a("HwFileDeviceDfxManager", "enter setMaintenanceData strCurrentTime:", str2, ",retryNum: ", str3);
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10), str + "_maintenance_start_time", str2, (StorageParams) null);
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10), str + "_maintenance_start_retry_number", str3, (StorageParams) null);
    }

    public void e(int i, DeviceDfxBaseResponseCallback deviceDfxBaseResponseCallback) {
        List<DeviceInfo> a2 = a();
        if (a2.isEmpty()) {
            LogUtil.h("HwFileDeviceDfxManager", "connectedDeviceList is empty");
            return;
        }
        for (int i2 = 0; i2 < a2.size(); i2++) {
            DeviceInfo deviceInfo = a2.get(i2);
            if (deviceInfo != null) {
                if (cvz.c(deviceInfo) && Utils.o()) {
                    ReleaseLogUtil.e("Dfx_HwFileDeviceDfxManager", "honor device is not support oversea");
                } else if (!jsd.d(deviceInfo) && i(deviceInfo.getDeviceIdentify())) {
                    ReleaseLogUtil.e("Dfx_HwFileDeviceDfxManager", "dfx doing nothing.");
                } else if (a(deviceInfo)) {
                    if (jsd.b()) {
                        ReleaseLogUtil.e("Dfx_HwFileDeviceDfxManager", "getMaintenanceFile(), collecting device log manually.");
                    } else {
                        ReleaseLogUtil.e("HwFileDeviceDfxManager", "getMaintenanceFile(), level = ", Integer.valueOf(i), ", callback = ", deviceDfxBaseResponseCallback);
                        if (!d(deviceInfo)) {
                            ReleaseLogUtil.e("Dfx_HwFileDeviceDfxManager", "getMaintenanceFile(), check device not support!");
                        } else {
                            this.f = jrx.d();
                            boolean f = f(knl.d(deviceInfo.getDeviceIdentify()));
                            ReleaseLogUtil.e("Dfx_HwFileDeviceDfxManager", "getMaintenanceFile(), isCanMaintenance = ", Boolean.valueOf(f));
                            if (!f) {
                                ReleaseLogUtil.e("Dfx_HwFileDeviceDfxManager", "getMaintenanceFile(), today has maintenance or retry > 3.");
                            } else {
                                this.g = deviceDfxBaseResponseCallback;
                                e(deviceInfo, this.i);
                            }
                        }
                    }
                }
            }
        }
    }

    public void a(final String str) {
        LogUtil.c("HwFileDeviceDfxManager", "startUploadLogFile().");
        ThreadPoolManager.d().c("HwFileDeviceDfxManager", new Runnable() { // from class: jvd.4
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.c("HwFileDeviceDfxManager", "BuildConfig.RELEASE_EVENT_LOG_UPLOAD: " + CompileParameterUtil.a("RELEASE_EVENT_LOG_UPLOAD"));
                if (CompileParameterUtil.a("RELEASE_EVENT_LOG_UPLOAD")) {
                    ReleaseLogUtil.e("Dfx_HwFileDeviceDfxManager", "startUploadLogFile(), use release version upload method!");
                    jsd.e(jvd.this.f14114a, str);
                    return;
                }
                ReleaseLogUtil.e("Dfx_HwFileDeviceDfxManager", "startUploadLogFile(), use beta version upload method!");
                if (Utils.o()) {
                    jeq.e().setProductType(20);
                } else {
                    jeq.e().setProductType(1);
                }
                jvd.this.g(str);
            }
        });
    }

    private boolean d(DeviceInfo deviceInfo) {
        boolean e = knx.e();
        ReleaseLogUtil.e("Dfx_HwFileDeviceDfxManager", "isDeviceSupport(), BuildConfig.SUPPORT_LOG_FEEDBACK = " + CompileParameterUtil.a("SUPPORT_LOG_FEEDBACK") + ", isExperience = " + e);
        if (deviceInfo == null) {
            return false;
        }
        this.e = deviceInfo.getProductType();
        ReleaseLogUtil.e("Dfx_HwFileDeviceDfxManager", "isDeviceSupport(), mDeviceType = " + this.e);
        if (!kjw.a(this.e)) {
            return false;
        }
        boolean z = CompileParameterUtil.a("SUPPORT_LOG_FEEDBACK") || CompileParameterUtil.a("RELEASE_EVENT_LOG_UPLOAD");
        boolean z2 = jad.d(48) && e;
        if (z) {
            return z2 || Utils.o();
        }
        return false;
    }

    private boolean f(String str) {
        String dayDateTime = this.f.getDayDateTime();
        LogUtil.a("HwFileDeviceDfxManager", "strCurrentTime:", dayDateTime);
        if (b(str, dayDateTime)) {
            d(str, dayDateTime, "1");
            return true;
        }
        int c2 = c(str);
        boolean b = b(str);
        ReleaseLogUtil.e("Dfx_HwFileDeviceDfxManager", "needMaintenance(), retryNumber = ", Integer.valueOf(c2), ", isSuccess = ", Boolean.valueOf(b));
        if (c2 < 3 && !b) {
            d(str, dayDateTime, String.valueOf(c2 + 1));
            return true;
        }
        ReleaseLogUtil.e("Dfx_HwFileDeviceDfxManager", "needMaintenance(), today has no success maintenance, but retry > 3.");
        return false;
    }

    private boolean b(String str) {
        LogUtil.a("HwFileDeviceDfxManager", "enter getMaintenanceResult");
        Context context = BaseApplication.getContext();
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("_result");
        return "true".equals(SharedPreferenceManager.b(context, String.valueOf(10), sb.toString()));
    }

    private List<DeviceInfo> a() {
        LogUtil.a("HwFileDeviceDfxManager", "enter getConnectedDeviceList");
        ArrayList arrayList = new ArrayList(16);
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ACTIVE_MAIN_DEVICES, null, "HwFileDeviceDfxManager");
        List<DeviceInfo> deviceList2 = cun.c().getDeviceList(HwGetDevicesMode.FOLLOWED_DEVICES, null, "HwFileDeviceDfxManager");
        if (deviceList != null && !deviceList.isEmpty()) {
            arrayList.addAll(deviceList);
        }
        if (deviceList2 != null && !deviceList2.isEmpty()) {
            arrayList.addAll(deviceList2);
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str, boolean z) {
        LogUtil.a("HwFileDeviceDfxManager", "enter setMaintenanceResult");
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10), str + "_result", String.valueOf(z), (StorageParams) null);
    }

    private int c(String str) {
        LogUtil.a("HwFileDeviceDfxManager", "enter getRetryNumber");
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10), str + "_RETRY_NUMBER");
        if (!nsn.a(b)) {
            return 0;
        }
        try {
            int parseInt = Integer.parseInt(b);
            LogUtil.a("HwFileDeviceDfxManager", "getRetryNumber, retryNumber: ", Integer.valueOf(parseInt));
            return parseInt;
        } catch (NumberFormatException unused) {
            LogUtil.b("HwFileDeviceDfxManager", "getRetryNumber has exception ");
            return 0;
        }
    }

    private void d(String str, String str2, String str3) {
        LogUtil.a("HwFileDeviceDfxManager", "enter setMaintenanceData strCurrentTime:", str2, ",retryNum:", str3);
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10), str + "_time", str2, (StorageParams) null);
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10), str + "_RETRY_NUMBER", str3, (StorageParams) null);
    }

    private boolean b(String str, String str2) {
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10), str + "_time");
        LogUtil.a("HwFileDeviceDfxManager", "isMoreThanOneDay, strLastTime:", b);
        if (TextUtils.isEmpty(b)) {
            LogUtil.a("HwFileDeviceDfxManager", "strLastTime is isEmpty");
            return true;
        }
        if (TextUtils.isEmpty(str2)) {
            LogUtil.a("HwFileDeviceDfxManager", "strCurrentTime is isEmpty");
            return false;
        }
        ReleaseLogUtil.e("Dfx_HwFileDeviceDfxManager", "delayedOneDay(), strLastTime = ", b, ", strCurTime = ", str2);
        try {
        } catch (NumberFormatException unused) {
            ReleaseLogUtil.c("Dfx_HwFileDeviceDfxManager", "delayedOneDay(), delayedEightHour exception.");
        }
        return Long.parseLong(str2) - Long.parseLong(b) >= 86400000;
    }

    private boolean a(DeviceInfo deviceInfo) {
        if (deviceInfo != null && deviceInfo.getProductType() == 34) {
            ReleaseLogUtil.e("Dfx_HwFileDeviceDfxManager", "current device is Latona");
            return true;
        }
        int i = Calendar.getInstance().get(11);
        LogUtil.a("HwFileDeviceDfxManager", "isLimitCollectLog(), localHour = " + i);
        if (i >= 6 && i < 10) {
            ReleaseLogUtil.e("Dfx_HwFileDeviceDfxManager", "isLimitCollectLog(), ", 6, " < localHour < ", 10, " return !!!");
            return false;
        }
        if (i < 17 || i >= 22) {
            return true;
        }
        ReleaseLogUtil.e("Dfx_HwFileDeviceDfxManager", "isLimitCollectLog(), ", 17, " < localHour < ", 22, " return !!!");
        return false;
    }
}
