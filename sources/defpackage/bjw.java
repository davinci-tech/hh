package defpackage;

import com.huawei.devicesdk.callback.DeviceScanCallback;
import com.huawei.devicesdk.entity.ScanMode;
import com.huawei.devicesdk.strategy.ScanStrategy;
import com.huawei.haf.threadpool.ThreadPoolManager;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Semaphore;

/* loaded from: classes8.dex */
public class bjw {
    private static Semaphore d = new Semaphore(1);
    private final bkw c;
    private Timer e;

    class b implements Runnable {
        private final DeviceScanCallback b;
        private final List<bjf> d;
        private final List<ScanStrategy> e;

        b(List<ScanStrategy> list, DeviceScanCallback deviceScanCallback, List<bjf> list2) {
            this.e = list;
            this.b = deviceScanCallback;
            this.d = list2;
        }

        @Override // java.lang.Runnable
        public void run() {
            LogUtil.c("DevicesManagement_ScanManage", "scan thread start.");
            try {
                bjw.d.acquire();
            } catch (InterruptedException unused) {
                LogUtil.e("DevicesManagement_ScanManage", "ScanRunnable InterruptedException");
            }
            bkl.e().c();
            bjw.this.e = new Timer("DevicesManagement_ScanManage");
            bkl.e().d(bjw.this.e);
            Iterator<ScanStrategy> it = this.e.iterator();
            while (it.hasNext()) {
                b(it.next(), this.b, this.d);
            }
            bjw.d.release();
        }

        private void b(final ScanStrategy scanStrategy, DeviceScanCallback deviceScanCallback, List<bjf> list) {
            final bgi bgiVar = new bgi(deviceScanCallback, list);
            scanStrategy.init(bgiVar);
            bkl.e().e(scanStrategy);
            scanStrategy.scan();
            bjw.this.e.schedule(new TimerTask() { // from class: bjw.b.2
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    bmw.e(100080, "", "", String.valueOf(bgiVar.e()));
                    bkl.e().b(scanStrategy);
                    scanStrategy.stopScan(false);
                }
            }, 15000L);
        }
    }

    private bjw() {
        this.c = bkw.d();
    }

    public static bjw a() {
        return d.d;
    }

    public void c(ScanMode scanMode, List<bjf> list, DeviceScanCallback deviceScanCallback) {
        if (scanMode == null || deviceScanCallback == null || list == null) {
            LogUtil.a("DevicesManagement_ScanManage", "scanDevice error. input param is null");
            return;
        }
        ReleaseLogUtil.b("DEVMGR_DevicesManagement_ScanManage", "scanDevice start. type ", scanMode.toString());
        bkw bkwVar = this.c;
        if (bkwVar == null || !bkwVar.e()) {
            LogUtil.a("DevicesManagement_ScanManage", "bluetooth is disable");
            return;
        }
        bmw.e(100076, "", "", String.valueOf(scanMode));
        ThreadPoolManager.d().execute(new b(e(scanMode), deviceScanCallback, list));
    }

    public void b() {
        ReleaseLogUtil.b("DEVMGR_DevicesManagement_ScanManage", "stopScanDevice");
        bkl.e().c();
    }

    private List<ScanStrategy> e(ScanMode scanMode) {
        bkp bkpVar = new bkp();
        ArrayList arrayList = new ArrayList(16);
        if (scanMode == ScanMode.BR_BLE) {
            arrayList.add(ScanMode.BLE);
            arrayList.add(ScanMode.BR);
        } else {
            arrayList.add(scanMode);
        }
        ArrayList arrayList2 = new ArrayList(16);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Object d2 = bkpVar.d((ScanMode) it.next());
            if (d2 instanceof ScanStrategy) {
                arrayList2.add((ScanStrategy) d2);
            }
        }
        return arrayList2;
    }

    static class d {
        private static bjw d = new bjw();
    }
}
