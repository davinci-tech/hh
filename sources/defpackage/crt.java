package defpackage;

import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.device.connectivity.comm.MeasurableDevice;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.Utils;

/* loaded from: classes3.dex */
public class crt {
    public static void d(String str, String str2, MeasurableDevice measurableDevice) {
        if (Utils.o() || measurableDevice == null) {
            LogUtil.h("IntellLifeConstants", "IntellLifeConstants can not upload device, is oversea : ", Boolean.valueOf(Utils.o()));
            return;
        }
        LogUtil.a("IntellLifeConstants", "IntellLifeConstants uploadDeviceToCloud ");
        String productId = measurableDevice.getProductId();
        final DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.setDeviceConnectState(0);
        if (cpa.ae(productId)) {
            deviceInfo.setProductType(cpa.j(productId));
        }
        deviceInfo.setDeviceProtocol(2);
        deviceInfo.setDeviceIdentify(measurableDevice.getUniqueId());
        deviceInfo.setDeviceActiveState(1);
        deviceInfo.setDeviceBluetoothType(measurableDevice instanceof cxh ? 2 : -1);
        deviceInfo.setUuid(str);
        deviceInfo.setDeviceName(str2);
        ThreadPoolManager.d().execute(new Runnable() { // from class: crt.2
            @Override // java.lang.Runnable
            public void run() {
                new crv().c(DeviceInfo.this);
            }
        });
    }

    public static void a(final String str, final String str2) {
        if (Utils.o()) {
            return;
        }
        ThreadPoolManager.d().execute(new Runnable() { // from class: crt.3
            @Override // java.lang.Runnable
            public void run() {
                new crv().a(str, str2);
            }
        });
    }
}
