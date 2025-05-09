package defpackage;

import android.bluetooth.BluetoothDevice;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.text.TextUtils;
import com.huawei.devicesdk.callback.DeviceScanCallback;
import com.huawei.unitedevice.entity.UniteDevice;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* loaded from: classes8.dex */
public class bgi {
    private final DeviceScanCallback d;
    private HandlerThread e;
    private final List<bjf> h;
    private Handler i;

    /* renamed from: a, reason: collision with root package name */
    private boolean f357a = false;
    private boolean c = true;
    private int b = 0;

    static /* synthetic */ int d(bgi bgiVar) {
        int i = bgiVar.b;
        bgiVar.b = i + 1;
        return i;
    }

    public bgi(DeviceScanCallback deviceScanCallback, List<bjf> list) {
        this.e = null;
        this.i = null;
        this.d = deviceScanCallback;
        this.h = new ArrayList(list);
        LogUtil.c("DeviceDiscoveryProcessor", "Enter start new handlerThread.");
        HandlerThread handlerThread = new HandlerThread("DeviceDiscoveryProcessor");
        this.e = handlerThread;
        handlerThread.start();
        this.i = new Handler(this.e.getLooper());
    }

    public boolean e() {
        return this.f357a;
    }

    public void qX_(BluetoothDevice bluetoothDevice, int i, byte[] bArr, int i2) {
        if (bluetoothDevice == null) {
            LogUtil.e("DeviceDiscoveryProcessor", "device is null");
            return;
        }
        String name = bluetoothDevice.getName();
        LogUtil.c("DeviceDiscoveryProcessor", "onDeviceDiscovered. device name:", blt.b(name), " getAddress:", blt.a(bluetoothDevice.getAddress()), " rssi:", Integer.valueOf(i), " reportSwitch:", Boolean.valueOf(this.c));
        if (this.c) {
            d(name, i, bArr, bluetoothDevice.getAddress(), i2);
        }
    }

    private boolean c(String str) {
        if (this.d == null || this.h == null) {
            LogUtil.e("DeviceDiscoveryProcessor", "mDeviceScanCallback or mScanFilters is null.");
            return false;
        }
        if (TextUtils.isEmpty(str)) {
            ReleaseLogUtil.a("DEVMGR_DeviceDiscoveryProcessor", "notify result error.mac address is invalid.");
            return false;
        }
        if (this.e != null) {
            return true;
        }
        LogUtil.a("DeviceDiscoveryProcessor", "mHandlerThread is dead");
        return false;
    }

    private void d(final String str, final int i, final byte[] bArr, final String str2, final int i2) {
        if (c(str2)) {
            this.i.post(new Runnable() { // from class: bgi.4
                @Override // java.lang.Runnable
                public void run() {
                    String c = bgi.this.c(str, bArr);
                    LogUtil.c("DeviceDiscoveryProcessor", "reportFoundDevice: mScanHandler.post run() deviceName: ", c);
                    boolean z = false;
                    for (bjf bjfVar : new ArrayList(bgi.this.h)) {
                        if (bjfVar != null) {
                            z = bjfVar.e() == 5 ? bgi.this.c(bjfVar, str2) : bgi.this.d(bjfVar, c);
                            if (z) {
                                break;
                            }
                        }
                    }
                    if (z) {
                        if (bky.i()) {
                            if (bgi.this.b < 15) {
                                ReleaseLogUtil.b("DEVMGR_DeviceDiscoveryProcessor", "reportFoundDevice call back.", blt.a(str2));
                            }
                            bgi.d(bgi.this);
                        } else {
                            LogUtil.c("DeviceDiscoveryProcessor", "reportFoundDevice call back.", blt.a(str2));
                        }
                        bgi.this.f357a = true;
                        UniteDevice d = blo.d(str2, c);
                        String a2 = blo.a(Integer.valueOf(i), Integer.valueOf(i2));
                        bmw.e(100111, c, blt.a(str2), a2);
                        bgi.this.d.scanResult(d, bArr, a2, 20);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String c(String str, byte[] bArr) {
        bik c;
        return (str != null || bArr == null || (c = bjb.e().c(bArr)) == null) ? str : c.d();
    }

    public void b(boolean z) {
        LogUtil.c("DeviceDiscoveryProcessor", "onDeviceDiscoveryFinished ");
        if (!z) {
            this.d.scanResult(null, null, null, 22);
        }
        this.c = false;
        b();
        a();
    }

    public void a(int i, String str) {
        LogUtil.a("DeviceDiscoveryProcessor", "onFailure");
        this.c = false;
        b();
        a();
        if (this.d == null) {
            LogUtil.e("DeviceDiscoveryProcessor", "mDeviceScanCallback null.");
        } else {
            this.d.scanResult(null, null, blo.a((Integer) null, (Integer) null), 21);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean d(bjf bjfVar, String str) {
        int e = bjfVar.e();
        String d = bjfVar.d();
        if (e == 0) {
            return true;
        }
        if (TextUtils.isEmpty(d)) {
            LogUtil.a("DeviceDiscoveryProcessor", "isExpertDeviceName keyword is empty.");
            return false;
        }
        if (str == null) {
            return false;
        }
        String lowerCase = d.toLowerCase(Locale.ENGLISH);
        String lowerCase2 = str.toLowerCase(Locale.ENGLISH);
        if (e == 1) {
            return lowerCase2.equals(lowerCase);
        }
        if (e == 2) {
            return lowerCase2.startsWith(lowerCase);
        }
        if (e == 3) {
            return lowerCase2.endsWith(lowerCase);
        }
        if (e == 4) {
            return lowerCase2.contains(lowerCase);
        }
        LogUtil.a("DeviceDiscoveryProcessor", "isExpertDeviceName default type: ", Integer.valueOf(e));
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean c(bjf bjfVar, String str) {
        String d = bjfVar.d();
        if (d == null) {
            LogUtil.e("DeviceDiscoveryProcessor", "keyword is null.");
            return false;
        }
        return str.equalsIgnoreCase(d);
    }

    private void b() {
        List<bjf> list = this.h;
        if (list != null) {
            list.clear();
        }
    }

    private void a() {
        HandlerThread handlerThread = this.e;
        if (handlerThread != null) {
            LogUtil.c("DeviceDiscoveryProcessor", "Enter quitHandlerThread.");
            Looper looper = handlerThread.getLooper();
            if (looper != null) {
                looper.quit();
            }
            this.e = null;
        }
    }
}
