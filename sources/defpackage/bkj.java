package defpackage;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.huawei.devicesdk.strategy.ScanStrategy;
import com.huawei.health.device.open.data.model.HealthData;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes3.dex */
public class bkj extends ScanStrategy {

    /* renamed from: a, reason: collision with root package name */
    private boolean f422a = false;
    private CountDownLatch b = null;
    private boolean c;
    private bkt d;
    private bgi e;
    private b f;

    /* loaded from: classes8.dex */
    class b extends BroadcastReceiver {
        private b() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.a("ScanStrategyBr", "scan brdevice intent is null");
                bkj.this.c(21);
                return;
            }
            String action = intent.getAction();
            LogUtil.c("ScanStrategyBr", "onReceive action:", action);
            if ("android.bluetooth.device.action.FOUND".equals(action)) {
                BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                short shortExtra = intent.getShortExtra("android.bluetooth.device.extra.RSSI", HealthData.INVALID_VALUES_SHORT);
                if (bkj.this.e != null) {
                    bkj.this.e.qX_(bluetoothDevice, shortExtra, null, 1);
                }
            }
            if ("android.bluetooth.adapter.action.DISCOVERY_FINISHED".equals(action)) {
                if (bkj.this.b != null) {
                    ReleaseLogUtil.b("DEVMGR_ScanStrategyBr", "DeviceReceiver mCountDownLatch != null");
                    bkj.this.b.countDown();
                    bkj.this.b = null;
                }
                if (bkj.this.e != null) {
                    bkj.this.e.b(false);
                }
            }
        }
    }

    @Override // com.huawei.devicesdk.strategy.ScanStrategy
    public void scan() {
        ReleaseLogUtil.b("DEVMGR_ScanStrategyBr", "ScanStrategyBr Scan Start");
        a();
        if (!this.d.b()) {
            LogUtil.a("ScanStrategyBr", "ScanStrategyBr Bluetooth error");
            return;
        }
        b bVar = new b();
        this.f = bVar;
        this.c = this.d.sy_(bVar);
        this.d.d();
    }

    @Override // com.huawei.devicesdk.strategy.ScanStrategy
    public void stopScan(boolean z) {
        ReleaseLogUtil.b("DEVMGR_ScanStrategyBr", "stopScan. isScanFinish:", Boolean.valueOf(this.f422a));
        a();
        if (this.f422a) {
            return;
        }
        this.d.c();
        try {
            CountDownLatch countDownLatch = new CountDownLatch(1);
            this.b = countDownLatch;
            countDownLatch.await(200L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException unused) {
            LogUtil.e("ScanStrategyBr", "stopScan InterruptedException exception");
        }
        if (this.c) {
            this.d.sz_(this.f);
            this.c = false;
        }
        this.e.b(z);
        this.f422a = true;
    }

    private void a() {
        if (this.d == null) {
            this.d = bkt.e();
        }
    }

    @Override // com.huawei.devicesdk.strategy.ScanStrategy
    public void init(bgi bgiVar) {
        this.e = bgiVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i) {
        bgi bgiVar = this.e;
        if (bgiVar != null) {
            bgiVar.a(i, "");
        }
    }
}
