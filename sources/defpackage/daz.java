package defpackage;

import android.bluetooth.BluetoothDevice;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.health.ecologydevice.callback.NfcMeasureCallback;
import com.huawei.health.ecologydevice.fitness.util.RopeStateMonitor;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes3.dex */
public class daz {

    /* renamed from: a, reason: collision with root package name */
    protected int f11574a;
    protected ExtendHandler b;
    protected String c;
    protected String d;
    protected NfcMeasureCallback e;
    protected int f;
    protected boolean g;
    protected int h = 0;
    private RopeStateMonitor.StateChangeListener i = new RopeStateMonitor.StateChangeListener() { // from class: daz.3
        @Override // com.huawei.health.ecologydevice.fitness.util.RopeStateMonitor.StateChangeListener
        public void onBondStateChanged(int i, BluetoothDevice bluetoothDevice) {
            try {
                String name = bluetoothDevice.getName();
                String address = bluetoothDevice.getAddress();
                if (name == null) {
                    if (address == null || !address.equals(daz.this.d)) {
                        LogUtil.h("BaseBloodSugarManager", "not this bond device");
                        return;
                    }
                } else if (!name.equals(daz.this.c) && !address.equals(daz.this.d)) {
                    LogUtil.a("BaseBloodSugarManager", "Ble pair failed: name and address are not equal");
                    return;
                }
                daz.this.f = i;
                if (i == 0) {
                    daz.this.f11574a = 14;
                } else if (i == 1) {
                    daz.this.f11574a = 15;
                } else if (i == 2) {
                    daz.this.f11574a = 16;
                } else if (i != 9) {
                    LogUtil.h("BaseBloodSugarManager", "not have this status");
                }
                daz.this.e(106);
            } catch (SecurityException e) {
                LogUtil.b("BaseBloodSugarManager", "onBondStateChanged SecurityException:", ExceptionUtils.d(e));
            }
        }

        @Override // com.huawei.health.ecologydevice.fitness.util.RopeStateMonitor.StateChangeListener
        public void onSwitchStateChanged(int i) {
            daz.this.f = i;
            if (i == 3) {
                LogUtil.a("BaseBloodSugarManager", "BLUETOOTH_SWITCH_OFF");
                daz.this.e(105);
            } else if (i == 4) {
                LogUtil.a("BaseBloodSugarManager", "BLUETOOTH_SWITCH_ON");
            } else {
                LogUtil.a("BaseBloodSugarManager", "BluetoothSwitchMonitorReceiver other blueState: ", Integer.valueOf(i));
            }
        }
    };
    private RopeStateMonitor j;

    public boolean d(NfcMeasureCallback nfcMeasureCallback) {
        a(nfcMeasureCallback);
        c();
        return false;
    }

    public void b() {
        e();
        a();
        d();
    }

    private void a(NfcMeasureCallback nfcMeasureCallback) {
        this.e = nfcMeasureCallback;
    }

    private void a() {
        this.e = null;
    }

    private void c() {
        RopeStateMonitor ropeStateMonitor = this.j;
        if (ropeStateMonitor != null) {
            ropeStateMonitor.d();
        }
        RopeStateMonitor ropeStateMonitor2 = new RopeStateMonitor(BaseApplication.getContext(), this.i);
        this.j = ropeStateMonitor2;
        ropeStateMonitor2.c();
    }

    private void e() {
        if (this.b != null) {
            LogUtil.a("BaseBloodSugarManager", "releaseHandler msgHandler will removeCallbacksAndMessages");
            this.b.removeTasksAndMessages();
            this.b.quit(false);
            this.b = null;
        }
    }

    private void d() {
        RopeStateMonitor ropeStateMonitor = this.j;
        if (ropeStateMonitor != null) {
            ropeStateMonitor.d();
            this.j = null;
        }
    }

    protected void a(int i, long j) {
        ExtendHandler extendHandler = this.b;
        if (extendHandler != null) {
            extendHandler.sendEmptyMessage(i, j);
        }
    }

    protected void e(int i) {
        ExtendHandler extendHandler = this.b;
        if (extendHandler != null) {
            extendHandler.sendEmptyMessage(i);
        }
    }

    protected void d(int i) {
        ExtendHandler extendHandler = this.b;
        if (extendHandler != null) {
            extendHandler.removeMessages(i);
        }
    }

    public void d(String str, String str2) {
        this.c = str;
        this.d = str2;
    }

    public void e(boolean z) {
        this.g = z;
        LogUtil.a("BaseBloodSugarManager", "mIsUniformDeviceManagementFlag = ", Boolean.valueOf(z));
    }
}
