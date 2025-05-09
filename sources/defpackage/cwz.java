package defpackage;

import android.bluetooth.BluetoothAdapter;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.device.callback.IDeviceEventHandler;
import com.huawei.health.ecologydevice.connectivity.ScanFilter;
import com.huawei.health.ecologydevice.ui.util.PermissionDialogHelper;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes3.dex */
public class cwz {
    private cxa b;

    public boolean d(cev cevVar, ScanFilter scanFilter, IDeviceEventHandler iDeviceEventHandler) {
        if (PermissionUtil.e(BaseApplication.wa_(), PermissionDialogHelper.d()) != PermissionUtil.PermissionResult.GRANTED) {
            LogUtil.b("DeviceScanManager", "no scan permission");
            return false;
        }
        LogUtil.a("DeviceScanManager", "Enter scanDevice");
        if (cevVar == null && iDeviceEventHandler == null) {
            return false;
        }
        this.b = new cxa();
        cpp.a(new d(cevVar, scanFilter, iDeviceEventHandler));
        return true;
    }

    class d implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        private IDeviceEventHandler f11514a;
        private cev b;
        private ScanFilter c;

        d(cev cevVar, ScanFilter scanFilter, IDeviceEventHandler iDeviceEventHandler) {
            this.b = cevVar;
            this.c = scanFilter;
            this.f11514a = iDeviceEventHandler;
        }

        @Override // java.lang.Runnable
        public void run() {
            int c = this.b.c();
            if (c == 1 || c == 2) {
                for (int i = 0; i < 10; i++) {
                    try {
                    } catch (InterruptedException unused) {
                        LogUtil.b("DeviceScanManager", "wait bluetooth state InterruptedException");
                    }
                    if (BluetoothAdapter.getDefaultAdapter().getState() == 12) {
                        break;
                    }
                    Thread.sleep(500L);
                }
                if (BluetoothAdapter.getDefaultAdapter().getState() == 10) {
                    LogUtil.h("DeviceScanManager", "bluetooth state is off");
                    this.f11514a.onScanFailed(3);
                    return;
                }
            }
            if (!dfe.a().e()) {
                if (cwz.this.b != null) {
                    cwz.this.b.b(this.b, this.c, this.f11514a);
                    return;
                }
                return;
            }
            this.f11514a.onStateChanged(1001);
        }
    }

    public void c() {
        cxa cxaVar = this.b;
        if (cxaVar != null) {
            cxaVar.j();
        }
    }
}
