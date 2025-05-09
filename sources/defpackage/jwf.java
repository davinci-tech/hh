package defpackage;

import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.datatypes.TransferFileInfo;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback;
import health.compact.a.SharedPreferenceManager;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class jwf {

    /* renamed from: a, reason: collision with root package name */
    private static jwf f14147a;
    private jvz b;
    private long g;
    private static final Object e = new Object();
    private static final Object d = new Object();
    private static List<TransferFileInfo> c = new ArrayList(10);
    private e i = new e();
    private IBaseResponseCallback f = new IBaseResponseCallback() { // from class: jwf.2
        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            if (obj == null) {
                return;
            }
            synchronized (jwf.c()) {
                LogUtil.a("HwFileTransferTaskAw70Queue", "myCallback into lock");
                if (jwf.c.isEmpty()) {
                    return;
                }
                LogUtil.a("HwFileTransferTaskAw70Queue", "myCallback transfer finished, number of files in queue is ", Integer.valueOf(jwf.c.size()), ", type of the first file in the queue is ", Integer.valueOf(((TransferFileInfo) jwf.c.get(0)).getType()));
                Object callback = ((TransferFileInfo) jwf.c.get(0)).getCallback();
                ITransferSleepAndDFXFileCallback iTransferSleepAndDFXFileCallback = callback instanceof ITransferSleepAndDFXFileCallback ? (ITransferSleepAndDFXFileCallback) callback : null;
                String b = jwf.this.b();
                if (((TransferFileInfo) jwf.c.get(0)).getType() == 0) {
                    jwa.a(BaseApplication.getContext(), ((TransferFileInfo) jwf.c.get(0)).getDeviceSn());
                    String str = "" + ((System.currentTimeMillis() - jwf.this.g) / 1000);
                    SharedPreferenceManager.d(BaseApplication.getContext(), jwf.this.g, "EXCE_DFT_APP_SYNSTART_TIME");
                    SharedPreferenceManager.d(BaseApplication.getContext(), "" + i, "EXCE_DFT_APP_SYNSTART");
                    jsd.e("910401", "EXCE_DFT_APP_SYNSTOP", str, b);
                }
                jwf.c.remove(0);
                if (!jwf.c.isEmpty() && jwf.this.b != null) {
                    LogUtil.a("HwFileTransferTaskAw70Queue", "myCallback still has data in the queue, continue");
                    jwf.this.b.a((TransferFileInfo) jwf.c.get(0), jwf.this.f);
                }
                LogUtil.a("HwFileTransferTaskAw70Queue", "myCallback return result to upper level, the number of files in the queue is ", Integer.valueOf(jwf.c.size()));
                jwf.this.a(iTransferSleepAndDFXFileCallback, i, obj);
                LogUtil.a("HwFileTransferTaskAw70Queue", "myCallback out of lock");
            }
        }
    };
    private ExtendHandler h = HandlerCenter.yt_(this.i, "HwFileTransferTaskAw70Queue");

    /* JADX INFO: Access modifiers changed from: private */
    public String b() {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "HwFileTransferTaskAw70Queue");
        DeviceInfo deviceInfo = deviceList.size() != 0 ? deviceList.get(0) : null;
        if (deviceInfo == null) {
            List<DeviceInfo> deviceList2 = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_AW70_DEVICES, null, "HwFileTransferTaskAw70Queue");
            if (deviceList2.size() != 0) {
                deviceInfo = deviceList2.get(0);
            }
        }
        return jsd.a(deviceInfo);
    }

    private jwf() {
        this.b = null;
        this.b = jvz.e();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(ITransferSleepAndDFXFileCallback iTransferSleepAndDFXFileCallback, int i, Object obj) {
        if (this.h == null) {
            LogUtil.h("HwFileTransferTaskAw70Queue", "deliverResponse myHanlder is null");
            return;
        }
        Message obtain = Message.obtain();
        if (iTransferSleepAndDFXFileCallback != null) {
            if (i == 10000) {
                jvr jvrVar = new jvr();
                jvrVar.b(iTransferSleepAndDFXFileCallback);
                jvrVar.a((Object) obj.toString());
                jvrVar.c(null);
                obtain.obj = jvrVar;
                obtain.what = 0;
                obtain.arg1 = i;
                this.h.sendMessage(obtain);
                return;
            }
            if (i == 110002) {
                LogUtil.b("HwFileTransferTaskAw70Queue", "error getting LEO log file name");
                return;
            }
            jvr jvrVar2 = new jvr();
            jvrVar2.b(iTransferSleepAndDFXFileCallback);
            jvrVar2.a((Object) obj.toString());
            obtain.obj = jvrVar2;
            obtain.what = 0;
            obtain.arg1 = i;
            this.h.sendMessage(obtain);
        }
    }

    public static jwf d() {
        jwf jwfVar;
        synchronized (d) {
            if (f14147a == null) {
                f14147a = new jwf();
            }
            jwfVar = f14147a;
        }
        return jwfVar;
    }

    public void e(TransferFileInfo transferFileInfo, Object obj) {
        if (transferFileInfo == null || obj == null) {
            LogUtil.h("HwFileTransferTaskAw70Queue", "getFile, transferFileInfo is null or object is null, return");
            return;
        }
        synchronized (c()) {
            LogUtil.a("HwFileTransferTaskAw70Queue", "getFile into lock");
            List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_AW70_DEVICES, null, "HwFileTransferTaskAw70Queue");
            if ((deviceList.size() > 0 ? deviceList.get(0) : null) == null) {
                LogUtil.h("HwFileTransferTaskAw70Queue", "no aw70 device connected");
                return;
            }
            if (c.isEmpty()) {
                LogUtil.a("HwFileTransferTaskAw70Queue", "getFile size is 0");
                transferFileInfo.setCallback(obj);
                LogUtil.a("HwFileTransferTaskAw70Queue", "getFile transferFileInfo callback is ", transferFileInfo.getCallback(), " transferFileInfo type is ", Integer.valueOf(transferFileInfo.getType()));
                c.add(transferFileInfo);
                this.b.a(transferFileInfo, this.f);
            } else {
                LogUtil.a("HwFileTransferTaskAw70Queue", "getFile size = ", Integer.valueOf(c.size()));
                transferFileInfo.setCallback(obj);
                LogUtil.a("HwFileTransferTaskAw70Queue", "getFile transferFileInfo callback is ", transferFileInfo.getCallback(), " transferFileInfo type is ", Integer.valueOf(transferFileInfo.getType()));
                a(c, transferFileInfo);
            }
            LogUtil.a("HwFileTransferTaskAw70Queue", "getFile out of lock");
        }
    }

    public void a(long j) {
        this.g = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object c() {
        Object obj;
        synchronized (jwf.class) {
            obj = e;
        }
        return obj;
    }

    private void a(List<TransferFileInfo> list, TransferFileInfo transferFileInfo) {
        for (int i = 0; i < list.size(); i++) {
            if (transferFileInfo.getType() == list.get(i).getType() && list.get(i).getTaskType() == transferFileInfo.getTaskType()) {
                list.remove(list.get(i));
                list.add(i, transferFileInfo);
                return;
            }
        }
        list.add(transferFileInfo);
    }

    static class e implements Handler.Callback {
        private e() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            if (message.what == 0) {
                if (!(message.obj instanceof jvr)) {
                    LogUtil.h("HwFileTransferTaskAw70Queue", "msg.obj not instanceof TransferTaskInfo");
                    return false;
                }
                jvr jvrVar = (jvr) message.obj;
                try {
                    Object b = jvrVar.b();
                    ITransferSleepAndDFXFileCallback iTransferSleepAndDFXFileCallback = b instanceof ITransferSleepAndDFXFileCallback ? (ITransferSleepAndDFXFileCallback) b : null;
                    if (iTransferSleepAndDFXFileCallback != null) {
                        LogUtil.a("HwFileTransferTaskAw70Queue", "maintance return");
                        if (message.arg1 == 10000) {
                            iTransferSleepAndDFXFileCallback.onSuccess(message.arg1, jvrVar.e().toString(), null);
                        } else {
                            iTransferSleepAndDFXFileCallback.onFailure(message.arg1, jvrVar.e().toString());
                        }
                    } else {
                        LogUtil.h("HwFileTransferTaskAw70Queue", "maintance dfxCallback = null");
                    }
                } catch (RemoteException unused) {
                    LogUtil.b("HwFileTransferTaskAw70Queue", "maintance RemoteException");
                }
                return true;
            }
            LogUtil.h("HwFileTransferTaskAw70Queue", "MyHandler unknown message");
            return false;
        }
    }
}
