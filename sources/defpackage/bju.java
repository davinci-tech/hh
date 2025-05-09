package defpackage;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.os.SystemClock;
import com.huawei.devicesdk.callback.FrameReceiver;
import com.huawei.devicesdk.entity.ConnectMode;
import com.huawei.devicesdk.entity.DeviceDataFrameParcel;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.haf.common.os.MemoryUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.unitedevice.entity.UniteDevice;
import com.huawei.unitedevice.hwcommonfilemgr.ParserInterface;
import health.compact.a.DfxMonitorCenter;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

/* loaded from: classes8.dex */
public class bju {
    private static Map<Integer, ParserInterface> c = new HashMap(16);
    private static final bju e = new bju();

    /* renamed from: a, reason: collision with root package name */
    private static ThreadPoolManager f410a = ThreadPoolManager.e(3, 10, "DataReceivedManage");
    private Map<String, e> b = new ConcurrentHashMap(16);
    private final Queue<biv> g = new LinkedBlockingQueue();
    private Map<String, Integer> d = new ConcurrentHashMap(16);
    private int i = 0;

    private bju() {
        c.put(52, spq.c());
        c.put(44, snw.d());
        c.put(40, snz.a());
        c.put(55, spt.d());
        c.put(57, spq.c());
    }

    public static bju a() {
        return e;
    }

    public void a(DeviceInfo deviceInfo, biu biuVar, int i) {
        if (deviceInfo == null) {
            LogUtil.a("DataReceivedManage", "onDataReceived deviceInfo is null");
            return;
        }
        if (biuVar == null) {
            LogUtil.a("DataReceivedManage", "onDataReceived frame is null");
        } else if (b(biuVar)) {
            b(deviceInfo, biuVar, i);
        } else {
            c(deviceInfo, biuVar, i, false);
        }
    }

    private e a(String str) {
        StringBuilder sb = new StringBuilder("HighSpeedThread");
        int i = this.i;
        this.i = i + 1;
        sb.append(i);
        HandlerThread handlerThread = new HandlerThread(sb.toString());
        handlerThread.start();
        e eVar = new e(handlerThread.getLooper());
        this.b.put(str, eVar);
        LogUtil.a("DataReceivedManage", "create new HighSpeedThread");
        return eVar;
    }

    private void b(DeviceInfo deviceInfo, biu biuVar, int i) {
        String deviceMac = deviceInfo.getDeviceMac();
        e eVar = this.b.get(deviceMac);
        if (eVar == null) {
            eVar = a(deviceMac);
        }
        if (c(deviceMac) > 50) {
            eVar.removeMessages(1);
            this.d.put(deviceMac, 0);
            LogUtil.e("DataReceivedManage", "fast thread out of bounds");
        }
        Message obtainMessage = eVar.obtainMessage(1);
        obtainMessage.obj = new Object[]{deviceInfo, biuVar};
        obtainMessage.arg1 = i;
        this.d.put(deviceMac, Integer.valueOf(c(deviceMac) + 1));
        if (eVar.sendMessage(obtainMessage)) {
            return;
        }
        ReleaseLogUtil.c("DataReceivedManage", "high speed command send failed");
        this.b.remove(deviceMac);
        eVar.removeCallbacksAndMessages(null);
        eVar.getLooper().quit();
        if (a(deviceMac).sendMessage(obtainMessage)) {
            return;
        }
        ReleaseLogUtil.c("DataReceivedManage", "Retrying high speed command send failed");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(DeviceInfo deviceInfo, biu biuVar, int i, boolean z) {
        if (biuVar == null) {
            LogUtil.a("DataReceivedManage", "dataReceivedHandle dataContents is null.");
            return;
        }
        byte[] a2 = biuVar.a();
        if (a2 != null && a2.length > 1) {
            byte b = a2[0];
            byte b2 = a2[1];
            if (b == 27 || b == 39) {
                ReleaseLogUtil.b("DEVMGR_DataReceivedManage", "response sid: ", Integer.valueOf(b), " cid: ", Integer.valueOf(b2));
            }
            LogUtil.c("DataReceivedManage", "serviceId: ", Integer.valueOf(b), ", commandId: ", Integer.valueOf(b2));
            if (bme.c(b, b2, deviceInfo.getDeviceMac(), "receive from device")) {
                return;
            }
            if (e(deviceInfo, i, a2, b)) {
                LogUtil.c("DataReceivedManage", "specialCommandHandle enter.");
                return;
            }
        }
        Iterator<FrameReceiver> it = bka.c().b().iterator();
        while (it.hasNext()) {
            FrameReceiver next = it.next();
            if (z) {
                c(next, deviceInfo, biuVar, i);
            } else {
                d(next, deviceInfo, biuVar, i);
            }
        }
    }

    private void d(FrameReceiver frameReceiver, DeviceInfo deviceInfo, biu biuVar, int i) {
        biv bivVar = new biv(frameReceiver, deviceInfo, biuVar, i);
        bivVar.e(SystemClock.elapsedRealtime(), this.g.size());
        this.g.add(bivVar);
        ThreadPoolManager threadPoolManager = f410a;
        if (threadPoolManager != null) {
            threadPoolManager.execute(new Runnable() { // from class: bju.3
                @Override // java.lang.Runnable
                public void run() {
                    biv bivVar2 = (biv) bju.this.g.poll();
                    d(bivVar2);
                    if (bivVar2 != null) {
                        bju.this.c(bivVar2.a(), bivVar2.e(), bivVar2.c(), bivVar2.b());
                    }
                }

                private void d(biv bivVar2) {
                    if (bivVar2 == null) {
                        ReleaseLogUtil.a("DEVMGR_DataReceivedManage", "checkFrameReceivedTimeout frame == null");
                        snu.e().setProbabilityProblemEvent("checkFrameReceivedTimeout", "frame == null");
                        return;
                    }
                    long elapsedRealtime = SystemClock.elapsedRealtime();
                    if (elapsedRealtime - bivVar2.i() > 6000) {
                        final String str = "time: " + bivVar2.i() + " -- " + elapsedRealtime + "  size: " + bivVar2.d() + " -- " + bju.this.g.size();
                        ReleaseLogUtil.a("DEVMGR_DataReceivedManage", "checkFrameReceivedTimeout timeoutInfo：", str);
                        iyv.c("CommandMessage", "thread pool execute long time " + a(bivVar2.c()));
                        DfxMonitorCenter.b(new Runnable() { // from class: bju.3.2
                            @Override // java.lang.Runnable
                            public void run() {
                                try {
                                    String str2 = "timeoutInfo：" + str + "  memoryInfo:" + MemoryUtils.e(false) + "  dumpMemoryInfo:" + MemoryUtils.xs_(null, false);
                                    iyv.c("CommandMessage", str2);
                                    snu.e().setProbabilityProblemEvent("checkFrameReceivedTimeout", str2);
                                } catch (Exception unused) {
                                    snu.e().setProbabilityProblemEvent("checkFrameReceivedTimeout", "timeoutInfo：" + str);
                                }
                            }
                        });
                    }
                }

                private String a(biu biuVar2) {
                    if (biuVar2 == null || biuVar2.a() == null || biuVar2.a().length < 2) {
                        return null;
                    }
                    return blq.c(biuVar2.a(), 2);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(FrameReceiver frameReceiver, DeviceInfo deviceInfo, biu biuVar, int i) {
        if (frameReceiver == null) {
            LogUtil.a("DataReceivedManage", "onFrameReceived receiver is null.");
            return;
        }
        try {
            UniteDevice uniteDevice = new UniteDevice();
            uniteDevice.setIdentify(deviceInfo.getDeviceMac());
            uniteDevice.setDeviceInfo(deviceInfo);
            frameReceiver.onFrameReceived(i, uniteDevice, e(biuVar));
        } catch (RemoteException unused) {
            LogUtil.e("DataReceivedManage", "onFrameReceived RemoteException");
        }
    }

    private boolean e(DeviceInfo deviceInfo, int i, byte[] bArr, int i2) {
        bix h = bjx.a().h(deviceInfo.getDeviceMac());
        ConnectMode b = h == null ? null : h.b();
        if (b != null) {
            if (b.value() == ConnectMode.SIMPLE.value()) {
                return false;
            }
        } else {
            LogUtil.a("DataReceivedManage", "getResult connectMode is null");
        }
        return c.containsKey(Integer.valueOf(i2)) && !e(c.get(Integer.valueOf(i2)), deviceInfo, bArr, i);
    }

    private boolean e(ParserInterface parserInterface, DeviceInfo deviceInfo, byte[] bArr, int i) {
        if (parserInterface == null) {
            return true;
        }
        LogUtil.c("DataReceivedManage", "the manager is ", parserInterface.getClass().getSimpleName());
        long currentTimeMillis = System.currentTimeMillis();
        boolean result = parserInterface.getResult(deviceInfo, bArr);
        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
        if (currentTimeMillis2 > 30) {
            blt.e("DataReceivedManage", bArr, "specialCommandHandle block msg:", Long.valueOf(currentTimeMillis2), ",command:");
        }
        return result;
    }

    private DeviceDataFrameParcel e(biu biuVar) {
        DeviceDataFrameParcel deviceDataFrameParcel = new DeviceDataFrameParcel();
        if (biuVar == null) {
            return deviceDataFrameParcel;
        }
        deviceDataFrameParcel.setCharacteristicId(biuVar.b());
        deviceDataFrameParcel.setData(biuVar.a());
        return deviceDataFrameParcel;
    }

    private boolean b(biu biuVar) {
        byte[] a2 = biuVar.a();
        if (a2 == null || a2.length < 1) {
            return false;
        }
        byte b = a2[0];
        return b == 48 || b == 10;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int c(String str) {
        Integer num = this.d.get(str);
        if (num != null) {
            return num.intValue();
        }
        return 0;
    }

    class e extends Handler {
        e(Looper looper) {
            super(looper);
            LogUtil.c("DataReceivedManage", "ProcessHandler start");
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message == null) {
                return;
            }
            if (message.what != 1) {
                return;
            }
            Object[] objArr = message.obj instanceof Object[] ? (Object[]) message.obj : null;
            if (objArr == null || objArr.length < 2) {
                Object[] objArr2 = new Object[1];
                StringBuilder sb = new StringBuilder("Received objects failed, is null ");
                sb.append(objArr == null);
                objArr2[0] = sb.toString();
                LogUtil.a("DataReceivedManage", objArr2);
                return;
            }
            Object obj = objArr[0];
            DeviceInfo deviceInfo = obj instanceof DeviceInfo ? (DeviceInfo) obj : null;
            Object obj2 = objArr[1];
            biu biuVar = obj2 instanceof biu ? (biu) obj2 : null;
            int i = message.arg1;
            if (deviceInfo == null) {
                LogUtil.a("DataReceivedManage", "ProcessHandler deviceInfo is null");
            } else {
                bju.this.d.put(deviceInfo.getDeviceMac(), Integer.valueOf(bju.this.c(deviceInfo.getDeviceMac()) - 1));
                bju.this.c(deviceInfo, biuVar, i, true);
            }
        }
    }
}
