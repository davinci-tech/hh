package defpackage;

import android.os.RemoteException;
import com.huawei.callback.BluetoothDataReceiveCallback;
import com.huawei.callback.DeviceStatusCallback;
import com.huawei.datatype.DeviceStatusParam;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwservicesmgr.IDeviceStateAIDLCallback;
import health.compact.a.CommonUtil;
import health.compact.a.ReleaseLogUtil;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes5.dex */
public class jfm {
    private static jfm d;

    /* renamed from: a, reason: collision with root package name */
    private IDeviceStateAIDLCallback.Stub f13785a = new IDeviceStateAIDLCallback.Stub() { // from class: jfm.2
        @Override // com.huawei.hwservicesmgr.IDeviceStateAIDLCallback
        public void onAckReceived(DeviceInfo deviceInfo, int i, byte[] bArr) throws RemoteException {
        }

        @Override // com.huawei.hwservicesmgr.IDeviceStateAIDLCallback
        public void onDataReceived(DeviceInfo deviceInfo, int i, byte[] bArr) throws RemoteException {
            if (bArr != null && bArr.length >= 2) {
                byte b2 = bArr[0];
                byte b3 = bArr[1];
                if (jfq.c().e(b2) || jfq.c().c(b2)) {
                    ReleaseLogUtil.b("DEVMGR_DeviceCommandProcessManager", "onDataReceived serviceId is,", Integer.valueOf(b2), "commandId is:", Integer.valueOf(b3));
                }
            }
            if (bArr == null || bArr.length <= 0 || deviceInfo == null) {
                ReleaseLogUtil.a("DeviceCommandProcessManager", "onDataReceived() Received data is null or deviceInfo is null");
                return;
            }
            LogUtil.a("DeviceCommandProcessManager", "onDataReceived() Received data :", cvx.d(bArr));
            jfw.c(bArr);
            if (jfm.this.a(deviceInfo, bArr)) {
                return;
            }
            String deviceIdentify = deviceInfo.getDeviceIdentify();
            int productType = deviceInfo.getProductType();
            if (bArr[0] != 1) {
                jfm.this.b(deviceInfo, bArr);
            } else {
                joi.c().d(deviceIdentify, productType, bArr);
            }
        }

        @Override // com.huawei.hwservicesmgr.IDeviceStateAIDLCallback
        public void onDatasReceived(DeviceInfo deviceInfo, int i, String str) {
            LogUtil.a("DeviceCommandProcessManager", "onDataReceived() Received model :", Integer.valueOf(i), ", data :", str);
            synchronized (jfm.e) {
                BluetoothDataReceiveCallback d2 = jfr.d(i);
                if (d2 != null) {
                    d2.onDataReceived(0, deviceInfo, str.getBytes(Charset.defaultCharset()));
                }
            }
        }

        @Override // com.huawei.hwservicesmgr.IDeviceStateAIDLCallback
        public void onDeviceConnectionStateChanged(DeviceInfo deviceInfo, int i, DeviceStatusParam deviceStatusParam) {
            LogUtil.a("DeviceCommandProcessManager", "onDeviceConnectionStateChanged: ", CommonUtil.l(deviceInfo.getDeviceIdentify()), ", deviceStatus: ", Integer.valueOf(i));
            Iterator<Map.Entry<String, DeviceStatusCallback>> it = jfr.d().entrySet().iterator();
            if (it == null) {
                LogUtil.a("DeviceCommandProcessManager", "onDeviceConnectionStateChanged iterator is null.");
                return;
            }
            while (it.hasNext()) {
                DeviceStatusCallback value = it.next().getValue();
                if (value != null) {
                    value.onDeviceConnectedChanged(deviceInfo, i, deviceStatusParam);
                }
            }
        }
    };
    private static final Object b = new Object();
    private static final Object e = new Object();

    private jfm() {
    }

    private boolean a(DeviceInfo deviceInfo, int i, int i2) {
        if (cvt.c(deviceInfo.getProductType()) && i == 7) {
            return true;
        }
        return i == 32 && i2 == 9;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(DeviceInfo deviceInfo, byte[] bArr) {
        if (!jqx.e(deviceInfo) || !jqx.e(bArr)) {
            return false;
        }
        ReleaseLogUtil.b("DEVMGR_DeviceCommandProcessManager", "is wallet callback");
        BluetoothDataReceiveCallback d2 = jfr.d(27);
        ReleaseLogUtil.b("DEVMGR_DeviceCommandProcessManager", "dataReceiveCallback is:", d2);
        if (d2 == null) {
            return true;
        }
        d2.onDataReceived(0, deviceInfo, bArr);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(DeviceInfo deviceInfo, byte[] bArr) {
        synchronized (e) {
            int i = bArr[0];
            byte b2 = bArr[1];
            if (a(deviceInfo, i, b2)) {
                i += 100000;
            }
            ReleaseLogUtil.b("DEVMGR_DeviceCommandProcessManager", "processDataOrder serviceId is:", Integer.valueOf(i), " commandId is:", Integer.valueOf(b2));
            BluetoothDataReceiveCallback d2 = jfr.d(i);
            ReleaseLogUtil.b("DEVMGR_DeviceCommandProcessManager", "dataReceiveCallback is:", d2);
            if (d2 != null) {
                d2.onDataReceived(0, deviceInfo, bArr);
            } else if (i == 8) {
                d(bArr);
            } else if (i == 26) {
                e(deviceInfo, bArr);
            } else {
                ReleaseLogUtil.b("DEVMGR_DeviceCommandProcessManager", "no support");
            }
        }
    }

    private void d(byte[] bArr) {
        if (bArr.length > 1) {
            byte b2 = bArr[1];
            ReleaseLogUtil.b("DEVMGR_DeviceCommandProcessManager", "handleClockMessage commandId: ", Integer.valueOf(b2));
            jgh d2 = jgh.d(BaseApplication.getContext());
            if (b2 == 5) {
                d2.c(cvx.d(bArr));
            } else if (b2 == 8) {
                d2.b(cvx.d(bArr));
            } else {
                ReleaseLogUtil.b("DEVMGR_DeviceCommandProcessManager", "no support");
            }
        }
    }

    private void e(DeviceInfo deviceInfo, byte[] bArr) {
        if (bArr.length > 1) {
            byte b2 = bArr[1];
            if (b2 == 7 || b2 == 9) {
                jfo.e().onDataReceived(0, deviceInfo, bArr);
            }
        }
    }

    public static jfm e() {
        jfm jfmVar;
        synchronized (b) {
            if (d == null) {
                d = new jfm();
            }
            jfmVar = d;
        }
        return jfmVar;
    }

    public IDeviceStateAIDLCallback.Stub a() {
        return this.f13785a;
    }
}
