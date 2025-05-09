package defpackage;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import com.huawei.callback.TransferFileCallback;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.devicemgr.business.entity.HwGetDevicesParameter;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.datatypes.TransferFileInfo;
import com.huawei.hwdevice.mainprocess.receiver.hwdfx.DeviceTriggerDfxReceiver;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback;
import health.compact.a.LocalBroadcast;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes5.dex */
public class jvl {
    private static jvl e;
    private IBaseResponseCallback c = new IBaseResponseCallback() { // from class: jvl.5
        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            ITransferSleepAndDFXFileCallback iTransferSleepAndDFXFileCallback;
            TransferFileCallback transferFileCallback;
            synchronized (jvl.d()) {
                LogUtil.a("HwFileTransferTaskQueue", "mCallback enter lock.");
                jvh.b(0);
                if (jvl.f14131a.size() == 0) {
                    ReleaseLogUtil.e("Dfx_HwFileTransferTaskQueue", "mCallback enter and size is 0.");
                    jvh.c(jvh.e(), jvh.c());
                    return;
                }
                ReleaseLogUtil.e("Dfx_HwFileTransferTaskQueue", "mCallback transfer over,callbacks.size() is ", Integer.valueOf(jvl.f14131a.size()), " callbacks.get(0).getType() is ", Integer.valueOf(((TransferFileInfo) jvl.f14131a.get(0)).getType()));
                TransferFileInfo transferFileInfo = (TransferFileInfo) jvl.f14131a.get(0);
                if (transferFileInfo.getType() == 1 && (transferFileInfo.getCallback() instanceof TransferFileCallback)) {
                    transferFileCallback = (TransferFileCallback) transferFileInfo.getCallback();
                    iTransferSleepAndDFXFileCallback = null;
                } else if (transferFileInfo.getCallback() instanceof ITransferSleepAndDFXFileCallback) {
                    iTransferSleepAndDFXFileCallback = (ITransferSleepAndDFXFileCallback) transferFileInfo.getCallback();
                    transferFileCallback = null;
                } else {
                    ReleaseLogUtil.d("Dfx_HwFileTransferTaskQueue", "transferFileInfo instanceof unKnow");
                    iTransferSleepAndDFXFileCallback = null;
                    transferFileCallback = null;
                }
                String a2 = jsd.a(jvl.this.a(transferFileInfo.getDeviceSn()));
                if (i != 20000 && i != 20001) {
                    jvl.this.d(i, a2);
                }
                jvl.this.d(i, obj, iTransferSleepAndDFXFileCallback, transferFileCallback, transferFileInfo);
                LogUtil.a("HwFileTransferTaskQueue", "mCallback exit lock.");
            }
        }
    };
    private ExtendHandler f;
    private Object g;
    private jvi h;
    private long i;
    private TransferFileInfo j;
    private static final Object b = new Object();
    private static final Object d = new Object();

    /* renamed from: a, reason: collision with root package name */
    private static List<TransferFileInfo> f14131a = new ArrayList(16);

    private jvl() {
        this.h = null;
        this.f = null;
        this.h = jvi.a();
        this.f = HandlerCenter.yt_(new e(), "HwFileTransferTaskQueue");
    }

    public DeviceInfo a(String str) {
        if (TextUtils.isEmpty(str)) {
            ReleaseLogUtil.d("Dfx_HwFileTransferTaskQueue", "deviceSn is null");
            return null;
        }
        HwGetDevicesParameter hwGetDevicesParameter = new HwGetDevicesParameter();
        hwGetDevicesParameter.setDeviceIdentify(str);
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.IDENTIFY_DEVICES, hwGetDevicesParameter, "HwFileTransferTaskQueue");
        DeviceInfo deviceInfo = deviceList.size() != 0 ? deviceList.get(0) : null;
        if (deviceInfo == null) {
            deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "HwFileTransferTaskQueue");
        }
        if (deviceList.size() != 0) {
            deviceInfo = deviceList.get(0);
        }
        if (deviceInfo != null) {
            return deviceInfo;
        }
        List<DeviceInfo> deviceList2 = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_AW70_DEVICES, null, "HwFileTransferTaskQueue");
        return deviceList2.size() != 0 ? deviceList2.get(0) : deviceInfo;
    }

    public static jvl b() {
        jvl jvlVar;
        synchronized (b) {
            if (e == null) {
                e = new jvl();
            }
            jvlVar = e;
        }
        return jvlVar;
    }

    public void a(long j) {
        this.i = j;
    }

    public void b(TransferFileInfo transferFileInfo, Object obj) {
        if (transferFileInfo.getLogPreExport() == 1) {
            this.j = transferFileInfo;
            this.g = obj;
            DeviceInfo a2 = a(transferFileInfo.getDeviceSn());
            LogUtil.a("HwFileTransferTaskQueue", "getFile exportKind: ", transferFileInfo.getLogPreExportKind());
            if (jvg.e().b()) {
                LogUtil.a("HwFileTransferTaskQueue", "getFile isExportingEvent is true handleGetFile");
                c(transferFileInfo, obj);
                return;
            } else {
                LogUtil.a("HwFileTransferTaskQueue", "start sendDeviceEvent exportKind: ", transferFileInfo.getLogPreExportKind());
                jvg.e().d(transferFileInfo.getLogPreExportKind(), a2);
                return;
            }
        }
        c(transferFileInfo, obj);
    }

    private void c(TransferFileInfo transferFileInfo, Object obj) {
        LogUtil.a("HwFileTransferTaskQueue", "getFile handleGetFile");
        synchronized (d()) {
            LogUtil.a("HwFileTransferTaskQueue", "getFile entry lock.");
            String str = "start traansFile, getFile modelType is" + transferFileInfo.getType();
            LogUtil.a("HwFileTransferTaskQueue", "getCallbacksObject object: ", obj);
            LogUtil.a("HwFileTransferTaskQueue", str);
            if (d(transferFileInfo, obj)) {
                return;
            }
            j();
            h(transferFileInfo, obj);
            LogUtil.a("HwFileTransferTaskQueue", "getFile exit lock.");
        }
    }

    public void c(int i, DeviceInfo deviceInfo, int i2) {
        if (i == 0) {
            LogUtil.a("HwFileTransferTaskQueue", "dealPreLogFeedBack executeResponse progress : ", Integer.valueOf(i2));
            if (i2 == 100) {
                c(this.j, this.g);
                return;
            }
            return;
        }
        if (i == 1) {
            LogUtil.a("HwFileTransferTaskQueue", "dealPreLogFeedBack executeResponse timeout");
            c(this.j, this.g);
        } else {
            LogUtil.h("HwFileTransferTaskQueue", "dealPreLogFeedBack others");
            c(this.j, this.g);
        }
    }

    private boolean d(TransferFileInfo transferFileInfo, Object obj) {
        LogUtil.a("HwFileTransferTaskQueue", "enter processDfxTask object: ", obj);
        DeviceInfo deviceInfo = null;
        if (TextUtils.isEmpty(transferFileInfo.getDeviceSn())) {
            LogUtil.a("HwFileTransferTaskQueue", "processDfxTask transferFileInfo.getDeviceSn() is null");
            List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "HwFileTransferTaskQueue");
            DeviceInfo deviceInfo2 = deviceList.size() != 0 ? deviceList.get(0) : null;
            if (deviceInfo2 == null) {
                LogUtil.a("HwFileTransferTaskQueue", "processDfxTask deviceInfo is null");
                List<DeviceInfo> deviceList2 = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_AW70_DEVICES, null, "HwFileTransferTaskQueue");
                if (deviceList2.size() != 0) {
                    deviceInfo = deviceList2.get(0);
                }
            }
            deviceInfo = deviceInfo2;
        } else {
            HwGetDevicesParameter hwGetDevicesParameter = new HwGetDevicesParameter();
            hwGetDevicesParameter.setDeviceIdentify(transferFileInfo.getDeviceSn());
            List<DeviceInfo> deviceList3 = cun.c().getDeviceList(HwGetDevicesMode.IDENTIFY_DEVICES, hwGetDevicesParameter, "HwFileTransferTaskQueue");
            if (deviceList3 != null && deviceList3.size() != 0) {
                deviceInfo = deviceList3.get(0);
            }
        }
        if (deviceInfo == null) {
            e(transferFileInfo, obj);
            return true;
        }
        if (cvt.c(deviceInfo.getProductType())) {
            if (transferFileInfo.getType() == 0) {
                if (deviceInfo.getAutoDetectSwitchStatus() != 1) {
                    jwf.d().e(transferFileInfo, obj);
                    LogUtil.a("HwFileTransferTaskQueue", "processDfxTask  HwFileTransferTaskAw70Queue.getInstance().getFile");
                } else {
                    ReleaseLogUtil.d("Dfx_HwFileTransferTaskQueue", "Work mode don't support DFX.");
                }
            }
            e(transferFileInfo, obj);
            return true;
        }
        LogUtil.a("HwFileTransferTaskQueue", "enter else return false");
        return false;
    }

    private void e(TransferFileInfo transferFileInfo, Object obj) {
        i(transferFileInfo, obj);
        a(transferFileInfo, obj);
        ReleaseLogUtil.d("Dfx_HwFileTransferTaskQueue", "no device connected.");
    }

    private void h(TransferFileInfo transferFileInfo, Object obj) {
        LogUtil.a("HwFileTransferTaskQueue", "taskHandle object: ", obj);
        if (transferFileInfo == null) {
            LogUtil.h("HwFileTransferTaskQueue", "transferFileInfo is null.");
            return;
        }
        ReleaseLogUtil.e("Dfx_HwFileTransferTaskQueue", "getFile size is ", Integer.valueOf(f14131a.size()));
        if (f14131a.size() == 0) {
            if (transferFileInfo.getType() == 200) {
                LogUtil.h("HwFileTransferTaskQueue", "transferFileInfo's type is not rela sleep!");
                return;
            }
            transferFileInfo.setCallback(obj);
            LogUtil.a("HwFileTransferTaskQueue", "getFile transferFileInfo.getCallback() is ", transferFileInfo.getCallback(), " transferFileInfo.getType() is " + transferFileInfo.getType());
            f14131a.add(transferFileInfo);
            this.h.c(transferFileInfo, this.c);
            return;
        }
        TransferFileInfo transferFileInfo2 = f14131a.get(0);
        TransferFileInfo e2 = jcr.e();
        if (transferFileInfo2 == null || e2 == null) {
            LogUtil.h("HwFileTransferTaskQueue", "TransferFileInfo is null.");
            return;
        }
        if (transferFileInfo.getType() == 200) {
            LogUtil.a("HwFileTransferTaskQueue", "transferFileInfo's type is not rela sleep ,and need improve priortiy!");
            if (transferFileInfo2.getType() == 2 && transferFileInfo2.getSuspend() == 0 && transferFileInfo2.getPriority() != 3) {
                LogUtil.a("HwFileTransferTaskQueue", "transferFileInfo's type is not rela sleep ,and the priortiy is improved!");
                transferFileInfo2.setPriority(3);
                e2.setPriority(3);
                return;
            }
            return;
        }
        transferFileInfo.setCallback(obj);
        LogUtil.a("HwFileTransferTaskQueue", "getFile transferFileInfo.getCallback() is ", transferFileInfo.getCallback(), " transferFileInfo.getType() is ", Integer.valueOf(transferFileInfo.getType()));
        c(f14131a, transferFileInfo);
    }

    private void j() {
        if (jvh.e() == null || jvh.c() == null) {
            jvh.b(new Timer("HwFileTransferTaskQueue"));
            jvh.b(new TimerTask() { // from class: jvl.4
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    if (jvh.g()) {
                        jvh.a();
                    }
                    LogUtil.a("HwFileTransferTaskQueue", "FileTransferTimerTask getTimes = ", Integer.valueOf(jvh.i()), ", callbacks size = ", Integer.valueOf(jvl.f14131a.size()));
                    if (jvh.i() > 12) {
                        if (jvl.f14131a.size() != 0) {
                            jvl.this.e();
                            return;
                        } else {
                            jvh.b(0);
                            jvh.c(jvh.e(), jvh.c());
                            return;
                        }
                    }
                    if (jvl.f14131a.size() == 0) {
                        jvh.b(0);
                        jvh.c(jvh.e(), jvh.c());
                    }
                }
            });
            LogUtil.a("HwFileTransferTaskQueue", "Timer start!");
            jvh.b(0);
            jvh.e().schedule(jvh.c(), 0L, PreConnectManager.CONNECT_INTERNAL);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        if (this.c == null || f14131a.size() <= 0) {
            return;
        }
        if (f14131a.get(0).getType() == 1) {
            this.h.z();
        } else {
            this.h.ad();
            if (this.h.u()) {
                jvk.b().c("IS_CONTINUE", "true");
                jvh.c(jvh.d(), jvh.b());
            }
        }
        jsd.c(f14131a.get(0), 10001, "Forced interruption task", this.c);
    }

    private void i(TransferFileInfo transferFileInfo, Object obj) {
        if (transferFileInfo.getType() == 4) {
            try {
                if (obj instanceof ITransferSleepAndDFXFileCallback) {
                    ((ITransferSleepAndDFXFileCallback) obj).onFailure(100001, "no device connected");
                }
            } catch (RemoteException unused) {
                LogUtil.b("HwFileTransferTaskQueue", "setRriCallback RemoteException.");
            }
        }
    }

    private void a(TransferFileInfo transferFileInfo, Object obj) {
        LogUtil.a("HwFileTransferTaskQueue", "enter setCoreSleepBack.");
        if (transferFileInfo.getType() == 2) {
            if (obj instanceof ITransferSleepAndDFXFileCallback) {
                LogUtil.a("HwFileTransferTaskQueue", "ready set back.");
                try {
                    ((ITransferSleepAndDFXFileCallback) obj).onFailure(100001, "no device connected");
                    return;
                } catch (RemoteException unused) {
                    LogUtil.b("HwFileTransferTaskQueue", "setCoreSleepBack RemoteException.");
                    return;
                }
            }
            LogUtil.h("HwFileTransferTaskQueue", "setCoreSleepBack call back in null.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object d() {
        Object obj;
        synchronized (jvl.class) {
            obj = d;
        }
        return obj;
    }

    private void c(List<TransferFileInfo> list, TransferFileInfo transferFileInfo) {
        LogUtil.a("HwFileTransferTaskQueue", "enter getType");
        int i = 0;
        int i2 = -1;
        while (true) {
            if (i >= list.size()) {
                break;
            }
            if (list.get(i).getPriority() < transferFileInfo.getPriority()) {
                if (e(list, transferFileInfo, i)) {
                    return;
                }
            } else {
                if (list.get(i).getPriority() == transferFileInfo.getPriority()) {
                    if (transferFileInfo.getType() == 1 || b(list.get(i), transferFileInfo)) {
                        i2 = i;
                    } else if (list.get(i).getType() == transferFileInfo.getType() && list.get(i).getTaskType() == transferFileInfo.getTaskType()) {
                        LogUtil.a("HwFileTransferTaskQueue", "same task, override current task");
                        int suspend = list.get(i).getSuspend();
                        list.remove(list.get(i));
                        transferFileInfo.setSuspend(suspend);
                        list.add(i, transferFileInfo);
                        return;
                    }
                } else {
                    LogUtil.c("Dfx_HwFileTransferTaskQueue", "compare next task.");
                }
                i++;
            }
        }
        LogUtil.a("HwFileTransferTaskQueue", "the task insert queue");
        if (i2 == -1) {
            list.add(transferFileInfo);
        } else {
            list.add(i2 + 1, transferFileInfo);
        }
    }

    private boolean b(TransferFileInfo transferFileInfo, TransferFileInfo transferFileInfo2) {
        if (transferFileInfo == null || transferFileInfo2 == null || transferFileInfo2.getType() != 0 || transferFileInfo.getDeviceSn() == null || transferFileInfo2.getDeviceSn() == null) {
            return false;
        }
        boolean equals = transferFileInfo.getDeviceSn().equals(transferFileInfo2.getDeviceSn());
        if (transferFileInfo2.getTaskType() != 2 || equals) {
            return false;
        }
        LogUtil.a("HwFileTransferTaskQueue", "insert task queue");
        return true;
    }

    private boolean e(List<TransferFileInfo> list, TransferFileInfo transferFileInfo, int i) {
        if (i == 0) {
            if (f14131a.get(i).getSuspend() == 1) {
                return false;
            }
            list.add(i + 1, transferFileInfo);
            list.get(i).setSuspend(1);
            if (jcr.e() != null) {
                jcr.e().setSuspend(1);
            }
            ReleaseLogUtil.e("Dfx_HwFileTransferTaskQueue", "transferFileInfos FILE_TRANSFER_SUSPENDED.");
            return true;
        }
        list.add(i, transferFileInfo);
        return true;
    }

    static class e implements Handler.Callback {
        private e() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            if (message.obj instanceof jvr) {
                jvr jvrVar = (jvr) message.obj;
                int i = message.what;
                if (i == 0) {
                    bKd_(jvrVar, message);
                    return true;
                }
                if (i == 1) {
                    LogUtil.a("HwFileTransferTaskQueue", "GPS callback return, and return errorCode = ", Integer.valueOf(message.arg1));
                    if (message.arg1 != 104003 && (jvrVar.b() instanceof TransferFileCallback)) {
                        ((TransferFileCallback) jvrVar.b()).onResponse(message.arg1, jvrVar.e());
                    }
                    return true;
                }
                if (i == 2) {
                    bKf_(jvrVar, message);
                    return true;
                }
                if (i == 4) {
                    bKe_(jvrVar, message);
                    return true;
                }
                LogUtil.h("HwFileTransferTaskQueue", "task type is default branch.");
                return false;
            }
            LogUtil.h("HwFileTransferTaskQueue", "inputMessage.obj is null.");
            return false;
        }

        private void bKf_(jvr jvrVar, Message message) {
            try {
                ITransferSleepAndDFXFileCallback iTransferSleepAndDFXFileCallback = (ITransferSleepAndDFXFileCallback) jvrVar.b();
                if (iTransferSleepAndDFXFileCallback != null && message != null) {
                    LogUtil.a("HwFileTransferTaskQueue", "core sleep enter.");
                    if (message.arg1 == 10000) {
                        iTransferSleepAndDFXFileCallback.onSuccess(message.arg1, jvrVar.e() instanceof String ? (String) jvrVar.e() : "", jvrVar.h() instanceof String ? (String) jvrVar.h() : "");
                        return;
                    }
                    if (message.arg1 == 20000) {
                        iTransferSleepAndDFXFileCallback.onProgress(message.arg2, null);
                        return;
                    } else if (message.arg1 == 104003) {
                        LogUtil.a("HwFileTransferTaskQueue", "core sleep errorcode.");
                        return;
                    } else {
                        iTransferSleepAndDFXFileCallback.onFailure(message.arg1, jvrVar.e() instanceof String ? (String) jvrVar.e() : "");
                        return;
                    }
                }
                LogUtil.h("HwFileTransferTaskQueue", "sleep return with parameter is null.");
            } catch (RemoteException unused) {
                LogUtil.b("HwFileTransferTaskQueue", "sleep return with RemoteException.");
            }
        }

        private void bKd_(jvr jvrVar, Message message) {
            try {
                ITransferSleepAndDFXFileCallback iTransferSleepAndDFXFileCallback = (ITransferSleepAndDFXFileCallback) jvrVar.b();
                if (iTransferSleepAndDFXFileCallback != null && message != null) {
                    LogUtil.a("HwFileTransferTaskQueue", "maintance callback return.");
                    if (message.arg1 == 10000) {
                        iTransferSleepAndDFXFileCallback.onSuccess(message.arg1, jvrVar.e().toString(), null);
                        return;
                    }
                    if (message.arg1 == 20001) {
                        iTransferSleepAndDFXFileCallback.onProgress(message.arg2, jvrVar.e().toString());
                        return;
                    } else if (message.arg1 == 104003) {
                        ReleaseLogUtil.e("Dfx_HwFileTransferTaskQueue", "interrupt code DFX.");
                        return;
                    } else {
                        iTransferSleepAndDFXFileCallback.onFailure(message.arg1, jvrVar.e().toString());
                        return;
                    }
                }
                ReleaseLogUtil.d("Dfx_HwFileTransferTaskQueue", "DFX return with parameter is null.");
            } catch (RemoteException e) {
                ReleaseLogUtil.c("Dfx_HwFileTransferTaskQueue", "DFX retrun with RemoteException. e = ", ExceptionUtils.d(e));
                if (message.arg1 == 10000) {
                    b(jvrVar);
                    ReleaseLogUtil.e("Dfx_HwFileTransferTaskQueue", "RemoteException dfx success");
                } else if (message.arg1 == 20001) {
                    LogUtil.a("HwFileTransferTaskQueue", "RemoteException dfx progress");
                } else if (message.arg1 == 104003) {
                    ReleaseLogUtil.e("Dfx_HwFileTransferTaskQueue", "RemoteException interrupt code DFX.");
                } else {
                    b(jvrVar);
                    ReleaseLogUtil.e("Dfx_HwFileTransferTaskQueue", "RemoteException dfx fail");
                }
            }
        }

        private void b(jvr jvrVar) {
            ReleaseLogUtil.e("Dfx_HwFileTransferTaskQueue", "enter intentTriggerReceiver preExport: ", Integer.valueOf(jvrVar.c()));
            if (jvrVar.c() == 1) {
                String a2 = jvrVar.a();
                LogUtil.a("HwFileTransferTaskQueue", "intentTriggerReceiver kind: ", jvrVar.d());
                Intent intent = new Intent("com.huawei.bone.dfx.device_trigger_upload_log");
                intent.setPackage(BaseApplication.getAppPackage());
                intent.putExtra("pre_log_export_mac", a2);
                intent.setClass(BaseApplication.getContext(), DeviceTriggerDfxReceiver.class);
                BaseApplication.getContext().sendBroadcast(intent, LocalBroadcast.c);
                return;
            }
            ReleaseLogUtil.c("HwFileTransferTaskQueue", "intentTriggerReceiver kind: other: ", jvrVar.d());
            sqo.p("HwFileTransferTaskQueueDFX return with RemoteException. enter intentTriggerReceiver preExport: " + jvrVar.c());
        }

        private void bKe_(jvr jvrVar, Message message) {
            try {
                ITransferSleepAndDFXFileCallback iTransferSleepAndDFXFileCallback = (ITransferSleepAndDFXFileCallback) jvrVar.b();
                if (iTransferSleepAndDFXFileCallback != null && message != null) {
                    LogUtil.a("HwFileTransferTaskQueue", "RRI callback retrun. InputMessage.arg1 is", Integer.valueOf(message.arg1));
                    if (message.arg1 == 10000) {
                        iTransferSleepAndDFXFileCallback.onSuccess(message.arg1, jvrVar.e() instanceof String ? (String) jvrVar.e() : "", jvrVar.h() instanceof String ? (String) jvrVar.h() : "");
                        return;
                    }
                    String str = jvrVar.e() instanceof String ? (String) jvrVar.e() : "";
                    LogUtil.a("HwFileTransferTaskQueue", "sync failed with code is ", Integer.valueOf(message.arg1), "sync failed with message is ", str);
                    iTransferSleepAndDFXFileCallback.onFailure(message.arg1, str);
                    return;
                }
                LogUtil.h("HwFileTransferTaskQueue", "RRI return with parameter is null.");
            } catch (RemoteException unused) {
                ReleaseLogUtil.c("Dfx_HwFileTransferTaskQueue", "RRI return with RemoteException.");
                ket.a().e("HwFileTransferTaskQueue");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i, String str) {
        if (i == 104003) {
            ReleaseLogUtil.e("Dfx_HwFileTransferTaskQueue", "The task is interrupted, After deletion,join the task queue again.");
            TransferFileInfo transferFileInfo = f14131a.get(0);
            transferFileInfo.setSuspend(0);
            if (f14131a.get(0).getType() == 0) {
                jwa.a(BaseApplication.getContext(), f14131a.get(0).getDeviceSn());
                String str2 = "" + ((System.currentTimeMillis() - this.i) / 1000);
                SharedPreferenceManager.d(BaseApplication.getContext(), this.i, "EXCE_DFT_APP_SYNSTART_TIME");
                SharedPreferenceManager.d(BaseApplication.getContext(), "" + i, "EXCE_DFT_APP_SYNSTART");
                jsd.e("910401", "EXCE_DFT_APP_SYNSTOP", str2, str);
            }
            f14131a.remove(0);
            jcr.b(null);
            c(f14131a, transferFileInfo);
            f();
            if (f14131a.size() != 0) {
                this.h.c(f14131a.get(0), this.c);
            } else {
                jvh.c(jvh.e(), jvh.c());
            }
            ReleaseLogUtil.e("Dfx_HwFileTransferTaskQueue", "retrun sCallbacks.size() is ", Integer.valueOf(f14131a.size()));
            return;
        }
        b(i, str);
        f14131a.remove(0);
        jcr.b(null);
        if (f14131a.size() != 0) {
            this.h.c(f14131a.get(0), this.c);
        } else {
            jvh.c(jvh.e(), jvh.c());
        }
        ReleaseLogUtil.e("Dfx_HwFileTransferTaskQueue", "mCallback retrun with callbacks.size() is ", Integer.valueOf(f14131a.size()));
    }

    private void f() {
        Collections.sort(f14131a, new Comparator<TransferFileInfo>() { // from class: jvl.2
            @Override // java.util.Comparator
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public int compare(TransferFileInfo transferFileInfo, TransferFileInfo transferFileInfo2) {
                return transferFileInfo2.getPriority() - transferFileInfo.getPriority();
            }
        });
    }

    private void b(int i, String str) {
        if (f14131a.get(0).getType() == 0) {
            jwa.a(BaseApplication.getContext(), f14131a.get(0).getDeviceSn());
            String str2 = "" + ((System.currentTimeMillis() - this.i) / 1000);
            SharedPreferenceManager.d(BaseApplication.getContext(), this.i, "EXCE_DFT_APP_SYNSTART_TIME");
            SharedPreferenceManager.d(BaseApplication.getContext(), "" + i, "EXCE_DFT_APP_SYNSTART");
            jsd.e("910401", "EXCE_DFT_APP_SYNSTOP", str2, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i, Object obj, ITransferSleepAndDFXFileCallback iTransferSleepAndDFXFileCallback, TransferFileCallback transferFileCallback, TransferFileInfo transferFileInfo) {
        Message obtain = Message.obtain();
        if (transferFileCallback != null) {
            jvr jvrVar = new jvr();
            jvrVar.b(transferFileCallback);
            jvrVar.a(obj);
            obtain.obj = jvrVar;
            obtain.what = 1;
            obtain.arg1 = i;
            this.f.sendMessage(obtain);
            return;
        }
        if (iTransferSleepAndDFXFileCallback == null) {
            LogUtil.h("HwFileTransferTaskQueue", "firstCallback is null.");
            return;
        }
        if (transferFileInfo.getType() == 2 || transferFileInfo.getType() == 4) {
            bKc_(i, obj, iTransferSleepAndDFXFileCallback, transferFileInfo.getType(), obtain);
            return;
        }
        if (i == 10000) {
            jvr jvrVar2 = new jvr();
            jvrVar2.b(iTransferSleepAndDFXFileCallback);
            jvrVar2.a((Object) obj.toString());
            jvrVar2.c(null);
            jvrVar2.a(transferFileInfo.getLogPreExport());
            jvrVar2.a(transferFileInfo.getDeviceMac());
            jvrVar2.b(transferFileInfo.getLogPreExportKind());
            obtain.obj = jvrVar2;
            obtain.what = 0;
            obtain.arg1 = i;
            this.f.sendMessage(obtain, 350L);
            return;
        }
        if (i == 20001) {
            bKb_(i, obj, iTransferSleepAndDFXFileCallback, obtain, transferFileInfo);
            return;
        }
        if (i == 110002) {
            LogUtil.h("HwFileTransferTaskQueue", "error code is 110002.");
            return;
        }
        jvr jvrVar3 = new jvr();
        jvrVar3.b(iTransferSleepAndDFXFileCallback);
        jvrVar3.a((Object) obj.toString());
        jvrVar3.a(transferFileInfo.getLogPreExport());
        jvrVar3.a(transferFileInfo.getDeviceMac());
        jvrVar3.b(transferFileInfo.getLogPreExportKind());
        obtain.obj = jvrVar3;
        obtain.what = 0;
        obtain.arg1 = i;
        this.f.sendMessage(obtain);
    }

    private void bKc_(int i, Object obj, ITransferSleepAndDFXFileCallback iTransferSleepAndDFXFileCallback, int i2, Message message) {
        if (i == 10000) {
            try {
                jvo jvoVar = (jvo) obj;
                String d2 = jvoVar.d();
                String e2 = jvoVar.e();
                jvr jvrVar = new jvr();
                jvrVar.b(iTransferSleepAndDFXFileCallback);
                jvrVar.a((Object) d2);
                jvrVar.c(e2);
                if (message == null) {
                    return;
                }
                message.obj = jvrVar;
                message.what = i2;
                message.arg1 = i;
                this.f.sendMessage(message);
                return;
            } catch (ClassCastException unused) {
                ReleaseLogUtil.c("Dfx_HwFileTransferTaskQueue", "classCastException.");
                return;
            }
        }
        if (i == 20000) {
            jvo jvoVar2 = obj instanceof jvo ? (jvo) obj : null;
            if (jvoVar2 == null) {
                LogUtil.h("HwFileTransferTaskQueue", "coreSleepInfo is null.");
                return;
            }
            int a2 = jvoVar2.a();
            LogUtil.a("HwFileTransferTaskQueue", "myHanlder SleepProgress is", Integer.valueOf(a2));
            jvr jvrVar2 = new jvr();
            jvrVar2.b(iTransferSleepAndDFXFileCallback);
            message.obj = jvrVar2;
            message.what = i2;
            message.arg1 = i;
            message.arg2 = a2;
            this.f.sendMessage(message);
            return;
        }
        jvr jvrVar3 = new jvr();
        jvrVar3.b(iTransferSleepAndDFXFileCallback);
        jvrVar3.a((Object) obj.toString());
        message.obj = jvrVar3;
        message.what = i2;
        message.arg1 = i;
        this.f.sendMessage(message);
    }

    private void bKb_(int i, Object obj, ITransferSleepAndDFXFileCallback iTransferSleepAndDFXFileCallback, Message message, TransferFileInfo transferFileInfo) {
        int i2;
        String[] split = ((String) obj).split("&");
        if (split.length < 2) {
            return;
        }
        try {
            i2 = Integer.parseInt(split[0]);
        } catch (NumberFormatException unused) {
            ReleaseLogUtil.c("Dfx_HwFileTransferTaskQueue", "unTLVGetFrameData NumberFormatException.");
            i2 = 0;
        }
        jvr jvrVar = new jvr();
        jvrVar.b(iTransferSleepAndDFXFileCallback);
        jvrVar.a((Object) split[1]);
        jvrVar.c(null);
        jvrVar.a(transferFileInfo.getLogPreExport());
        jvrVar.a(transferFileInfo.getDeviceMac());
        jvrVar.b(transferFileInfo.getLogPreExportKind());
        message.obj = jvrVar;
        message.what = 0;
        message.arg1 = i;
        message.arg2 = i2;
        this.f.sendMessage(message);
    }
}
