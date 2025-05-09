package defpackage;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.device.callback.IDeviceEventHandler;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.LocalBroadcast;

/* loaded from: classes3.dex */
public class cjr {
    private cwz b;
    private boolean d;
    private IDeviceEventHandler g;
    private String i;
    private HealthDevice j;
    private boolean c = false;
    private long f = 3000;
    private BroadcastReceiver h = new BroadcastReceiver() { // from class: cjr.4
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("android.bluetooth.adapter.action.STATE_CHANGED".equals(intent.getAction())) {
                int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", 0);
                LogUtil.a("BluetoothScanUtil", "onReceive blueState: ", Integer.valueOf(intExtra));
                if (intExtra == 10) {
                    cjr.this.e.sendEmptyMessage(10002);
                } else if (intExtra == 12) {
                    cjr cjrVar = cjr.this;
                    cjrVar.e(cjrVar.i, cjr.this.j);
                } else {
                    LogUtil.h("BluetoothScanUtil", "onReceive blueState is ", Integer.valueOf(intExtra));
                }
            }
        }
    };
    private Handler e = new Handler(Looper.getMainLooper()) { // from class: cjr.5
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message == null) {
                LogUtil.h("BluetoothScanUtil", "receive null message");
                return;
            }
            LogUtil.a("BluetoothScanUtil", "handleMessage msg.what: ", Integer.valueOf(message.what));
            int i = message.what;
            if (i == 10001) {
                cjr cjrVar = cjr.this;
                cjrVar.d(cjrVar.i);
            } else if (i == 10002) {
                cjr.this.b();
            } else {
                LogUtil.h("BluetoothScanUtil", "BluetoothScanUtil handleMessage msg.what is error ", Integer.valueOf(message.what));
            }
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private IDeviceEventHandler f752a = new IDeviceEventHandler() { // from class: cjr.2
        @Override // com.huawei.health.device.callback.IDeviceEventHandler
        public void onDeviceFound(HealthDevice healthDevice) {
            if (healthDevice == null || healthDevice.getAddress() == null || cjr.this.j == null || cjr.this.j.getAddress() == null || !healthDevice.getAddress().equals(cjr.this.j.getAddress()) || cjr.this.g == null) {
                return;
            }
            cjr.this.g.onDeviceFound(healthDevice);
            cjr.this.a();
        }

        @Override // com.huawei.health.device.callback.IDeviceEventHandler
        public void onScanFailed(int i) {
            LogUtil.a("BluetoothScanUtil", "onScanFailed code: ", Integer.valueOf(i));
            cjr.this.c();
            if (cjr.this.g != null) {
                cjr.this.g.onScanFailed(i);
            }
        }

        @Override // com.huawei.health.device.callback.IDeviceEventHandler
        public void onStateChanged(int i) {
            LogUtil.a("BluetoothScanUtil", "onStateChanged code: ", Integer.valueOf(i));
            if (cjr.this.g != null) {
                cjr.this.g.onStateChanged(i);
            }
        }
    };

    public cjr(IDeviceEventHandler iDeviceEventHandler) {
        this.g = iDeviceEventHandler;
    }

    public void e(String str, HealthDevice healthDevice) {
        if (TextUtils.isEmpty(str) || healthDevice == null) {
            LogUtil.h("BluetoothScanUtil", "startScan mProductId is null or device is null");
            return;
        }
        LogUtil.a("BluetoothScanUtil", "startScan productId: ", str, " is scanning ", Boolean.valueOf(this.c));
        if (this.c) {
            return;
        }
        this.j = healthDevice;
        this.i = str;
        if (BluetoothAdapter.getDefaultAdapter().getState() != 12) {
            LogUtil.h("BluetoothScanUtil", "startScan Bluetooth not open");
            return;
        }
        this.c = true;
        this.f = 0L;
        c();
        this.f = 3000L;
    }

    public void a() {
        LogUtil.a("BluetoothScanUtil", "stopScan");
        this.j = null;
        this.i = null;
        b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        this.c = false;
        cwz cwzVar = this.b;
        if (cwzVar != null) {
            cwzVar.c();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        LogUtil.a("BluetoothScanUtil", "sendScannerMessage mScanTime: ", Long.valueOf(this.f));
        this.e.sendEmptyMessageDelayed(10001, this.f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str) {
        LogUtil.a("BluetoothScanUtil", "startScanner productId: ", str);
        dcz d = ResourceManager.e().d(str);
        if (d != null) {
            LogUtil.a("BluetoothScanUtil", "startScanner productInfo is not null");
            cwz cwzVar = new cwz();
            this.b = cwzVar;
            boolean d2 = cwzVar.d(d.x(), d.w(), this.f752a);
            LogUtil.a("BluetoothScanUtil", "startScanner isScanner: ", Boolean.valueOf(d2));
            if (d2) {
                return;
            }
            c();
        }
    }

    public void d() {
        LogUtil.a("BluetoothScanUtil", "unRegisterBluetoothMonitor, hasReceiver: ", Boolean.valueOf(this.d));
        if (this.d) {
            try {
                BaseApplication.e().unregisterReceiver(this.h);
            } catch (IllegalArgumentException e) {
                LogUtil.b("BluetoothScanUtil", "BluetoothScanUtil Receiver not registered: ", e.getMessage());
            }
            this.d = false;
        }
    }

    public void e() {
        LogUtil.a("BluetoothScanUtil", "registerBluetoothMonitor, hasReceiver: ", Boolean.valueOf(this.d));
        if (this.d) {
            return;
        }
        BaseApplication.e().registerReceiver(this.h, new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED"), LocalBroadcast.c, null);
        this.d = true;
    }
}
