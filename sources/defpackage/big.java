package defpackage;

import android.os.PowerManager;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.devicesdk.strategy.SendStrategy;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import health.compact.a.LogUtil;
import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: classes3.dex */
public class big extends Thread {

    /* renamed from: a, reason: collision with root package name */
    private PowerManager.WakeLock f386a;
    private DeviceInfo b;
    private final AtomicLong c = new AtomicLong(1);
    private final PriorityBlockingQueue<biq> d;
    private final AtomicBoolean e;

    public big(DeviceInfo deviceInfo) {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        this.e = atomicBoolean;
        this.d = new PriorityBlockingQueue<>(10000, new Comparator<biq>() { // from class: big.1
            @Override // java.util.Comparator
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public int compare(biq biqVar, biq biqVar2) {
                int compare;
                if (biqVar == null || biqVar2 == null) {
                    return 0;
                }
                return (biqVar.b() == null || biqVar2.b() == null || (compare = Integer.compare(biqVar2.b().h(), biqVar.b().h())) == 0) ? Long.compare(biqVar.a(), biqVar2.a()) : compare;
            }
        });
        Object systemService = BaseApplication.e().getSystemService("power");
        if (systemService instanceof PowerManager) {
            LogUtil.c("CommandMessageSender", "init WakeLock");
            this.f386a = ((PowerManager) systemService).newWakeLock(1, "PhoneService_SendDataLock");
        }
        this.b = deviceInfo;
        atomicBoolean.set(true);
        if (deviceInfo == null) {
            LogUtil.e("CommandMessageSender", "deviceInfo is null");
            return;
        }
        setName("DeviceCommandThread-" + deviceInfo.getDeviceMac());
    }

    public boolean d(bir birVar, SendStrategy sendStrategy) {
        DeviceInfo deviceInfo;
        if (this.d == null || (deviceInfo = this.b) == null) {
            LogUtil.e("CommandMessageSender", "command queue is invalid.");
            return false;
        }
        if (birVar == null || sendStrategy == null) {
            LogUtil.e("CommandMessageSender", "command message or send strategy is invalid.");
            return false;
        }
        LogUtil.c("CommandMessageSender", "add command message.", blt.a(deviceInfo.getDeviceMac()), " characterId:", birVar.b(), "socketchannel: ", Integer.valueOf(birVar.i()));
        return this.d.offer(new biq(this.c.incrementAndGet(), birVar, sendStrategy));
    }

    public void e() {
        DeviceInfo deviceInfo = this.b;
        if (deviceInfo == null) {
            LogUtil.e("CommandMessageSender", "mDeviceInfo is invalid.");
            return;
        }
        LogUtil.c("CommandMessageSender", "stop device command thread.", blt.a(deviceInfo.getDeviceMac()));
        this.e.set(false);
        PriorityBlockingQueue<biq> priorityBlockingQueue = this.d;
        if (priorityBlockingQueue != null) {
            priorityBlockingQueue.clear();
            LogUtil.c("CommandMessageSender", "close thread.", blt.a(this.b.getDeviceMac()));
            this.d.offer(new biq(0L, null, null));
        }
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        DeviceInfo deviceInfo = this.b;
        if (deviceInfo == null) {
            LogUtil.e("CommandMessageSender", "mDeviceInfo is invalid.");
            return;
        }
        LogUtil.c("CommandMessageSender", "device command thread start.", blt.a(deviceInfo.getDeviceMac()));
        if (this.d == null) {
            LogUtil.a("CommandMessageSender", "command queue is invalid.");
            return;
        }
        while (true) {
            long j = 0;
            while (this.e.get()) {
                try {
                    biq take = this.d.take();
                    if (System.currentTimeMillis() > j) {
                        j = System.currentTimeMillis() + 60000;
                        b();
                    }
                    if (take != null) {
                        e(take);
                    }
                } catch (Exception unused) {
                    LogUtil.e("CommandMessageSender", "mCommandQueue take Exception");
                }
                if (this.d.isEmpty()) {
                    break;
                }
            }
            LogUtil.c("CommandMessageSender", "device command thread stop.", blt.a(this.b.getDeviceMac()));
            return;
            c();
        }
    }

    private void e(biq biqVar) {
        bir b = biqVar.b();
        SendStrategy d = biqVar.d();
        if (b == null || d == null) {
            return;
        }
        LogUtil.c("CommandMessageSender", "start send command. characterId:", b.b(), blt.a(this.b.getDeviceMac()), " operationType:", b.c(), " chanel: ", Integer.valueOf(b.i()));
        bib.a().b(this.b.getDeviceMac(), d.getSendFrames(b, this.b));
    }

    private void b() {
        PowerManager.WakeLock wakeLock = this.f386a;
        if (wakeLock != null) {
            wakeLock.acquire(60000L);
        }
    }

    private void c() {
        PowerManager.WakeLock wakeLock = this.f386a;
        if (wakeLock != null) {
            try {
                if (wakeLock.isHeld()) {
                    this.f386a.release();
                }
            } catch (Exception e) {
                LogUtil.e("CommandMessageSender", "releaseSendDataLock occur e: ", ExceptionUtils.d(e));
            }
        }
    }

    public int a() {
        return this.d.size();
    }
}
