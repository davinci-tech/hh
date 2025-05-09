package defpackage;

import android.text.TextUtils;
import com.huawei.devicesdk.entity.ConnectMode;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.devicesdk.entity.ExternalDeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.unitedevice.entity.UniteDevice;
import health.compact.a.LogUtil;
import java.util.Map;

/* loaded from: classes3.dex */
public class bjx {
    private bgp b;

    private bjx() {
        this.b = bgp.a();
    }

    public static bjx a() {
        return d.b;
    }

    public void d(String str, DeviceInfo deviceInfo) {
        if (TextUtils.isEmpty(str) || deviceInfo == null) {
            LogUtil.a("DeviceInfoManage", "deviceUuid or deviceInfo is invalid, addDeviceInfo error.");
            return;
        }
        if (!deviceInfo.isReconnect()) {
            String e = blo.e();
            deviceInfo.setWearEngineDeviceId(e);
            LogUtil.c("DeviceInfoManage", "addDeviceInfo wearEngineDeviceId: ", blt.a(e));
            deviceInfo.setReconnect(true);
        }
        this.b.b(str, deviceInfo);
        bio.a(str, deviceInfo);
    }

    public void c(DeviceInfo deviceInfo) {
        this.b.d(deviceInfo);
    }

    public DeviceInfo j(String str) {
        return this.b.j(str);
    }

    public Map<String, DeviceInfo> b() {
        return this.b.d();
    }

    public Map<String, DeviceInfo> d() {
        return this.b.c();
    }

    public void a(DeviceInfo deviceInfo) {
        if (deviceInfo == null || TextUtils.isEmpty(deviceInfo.getDeviceMac())) {
            LogUtil.a("DeviceInfoManage", "device info is invalid, removeDeviceInfo error");
            return;
        }
        LogUtil.c("DeviceInfoManage", "remove device info: ", blt.a(deviceInfo));
        String deviceMac = deviceInfo.getDeviceMac();
        this.b.i(deviceMac);
        bio.b(deviceMac);
    }

    public void b(DeviceInfo deviceInfo) {
        this.b.a(deviceInfo);
    }

    public DeviceInfo e(String str) {
        return this.b.a(str);
    }

    public void o(String str) {
        this.b.l(str);
    }

    public void a(String str, int i) {
        if (str == null) {
            LogUtil.a("DeviceInfoManage", "updateDeviceStatus error. device is invalid.");
            return;
        }
        DeviceInfo j = this.b.j(str);
        if (j == null) {
            LogUtil.a("DeviceInfoManage", "updateDeviceStatus error. can not find device: ", blt.a(str));
            return;
        }
        LogUtil.c("DeviceInfoManage", "updateDeviceStatus: ", blt.a(str), " status from ", Integer.valueOf(j.getDeviceConnectState()), " to ", Integer.valueOf(i));
        j.setDeviceConnectState(i);
        bio.a(str, j);
        if (i == 4 || i == 3) {
            bjy.d().c(j);
        }
    }

    public void e(String str, boolean z) {
        if (str == null) {
            LogUtil.a("DeviceInfoManage", "updateUsing error. device is invalid.");
            return;
        }
        DeviceInfo j = this.b.j(str);
        if (j == null) {
            LogUtil.a("DeviceInfoManage", "updateUsing error. can not find device.", blt.a(str));
            return;
        }
        LogUtil.c("DeviceInfoManage", "updateUsing.", blt.a(str), " from ", Boolean.valueOf(j.isUsing()), " to ", Boolean.valueOf(z));
        j.setUsing(z);
        bio.a(str, j);
    }

    public Map<String, ExternalDeviceCapability> e() {
        return this.b.b();
    }

    public void d(String str, ExternalDeviceCapability externalDeviceCapability) {
        if (TextUtils.isEmpty(str) || externalDeviceCapability == null) {
            LogUtil.a("DeviceInfoManage", "device id or capability is invalid.");
        } else {
            this.b.d(str, externalDeviceCapability);
            bio.c(str, externalDeviceCapability);
        }
    }

    public void d(DeviceInfo deviceInfo) {
        if (deviceInfo == null || TextUtils.isEmpty(deviceInfo.getDeviceMac())) {
            return;
        }
        String deviceMac = deviceInfo.getDeviceMac();
        DeviceInfo j = j(deviceMac);
        if (j != null && j.getPairingTime() <= 0) {
            deviceInfo.setPairingTime(System.currentTimeMillis());
        }
        if ("assistant_relationship".equalsIgnoreCase(bme.e(deviceMac))) {
            LogUtil.c("DeviceInfoManage", "assistant device do not need update last connected time.", blt.a(deviceMac));
        } else {
            deviceInfo.setLastConnectedTime(System.currentTimeMillis());
        }
        bio.a(deviceMac, deviceInfo);
    }

    public void k(String str) {
        LogUtil.c("DeviceInfoManage", "enter updateDeviceAfterSimulatConnected", blt.a(str));
        DeviceInfo j = a().j(str);
        if (j == null || TextUtils.isEmpty(j.getDeviceMac())) {
            return;
        }
        j.setLastConnectedTime(System.currentTimeMillis());
        bio.a(str, j);
    }

    public void d(String str, ConnectMode connectMode) {
        if (str == null || connectMode == null) {
            LogUtil.a("DeviceInfoManage", "param is invalid, updateConnectMode fail");
            return;
        }
        bix g = this.b.g(str);
        if (g != null && g.b() != null) {
            LogUtil.c("DeviceInfoManage", "connectMode is already exists.");
            return;
        }
        if (g == null) {
            g = new bix();
            g.d(str);
        }
        g.d(connectMode);
        e(str, g);
        bio.c(str, g);
    }

    public void e(String str, bix bixVar) {
        this.b.a(str, bixVar);
    }

    public bix h(String str) {
        return this.b.g(str);
    }

    public void c(String str, boolean z) {
        if (str == null) {
            LogUtil.a("DeviceInfoManage", "setIsHandshakeRunning: identify is null");
            return;
        }
        DeviceInfo j = this.b.j(str);
        bix g = this.b.g(str);
        if (g == null) {
            LogUtil.c("DeviceInfoManage", "setIsHandshakeRunning: no deviceStatus, so create new one");
            g = new bix();
            this.b.a(str, g);
        }
        g.a(z);
        if (j != null) {
            bio.c(str, g);
        } else {
            LogUtil.a("DeviceInfoManage", "setIsHandshakeRunning device is not exist.", blt.a(str));
        }
    }

    public boolean g(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("DeviceInfoManage", "isHandshakeRunning: identify is empty");
            return false;
        }
        bix g = this.b.g(str);
        if (g == null) {
            return false;
        }
        return g.d();
    }

    public biw c(String str) {
        return this.b.e(str);
    }

    public void a(String str, biw biwVar) {
        this.b.a(str, biwVar);
    }

    public biz i(String str) {
        return this.b.f(str);
    }

    public void b(String str, biz bizVar) {
        this.b.c(str, bizVar);
    }

    public void c(String str, DeviceCapability deviceCapability) {
        this.b.a(str, deviceCapability);
    }

    public DeviceCapability b(String str) {
        return this.b.b(str);
    }

    public void a(DeviceInfo deviceInfo, String str) {
        if (deviceInfo != null) {
            this.b.b(deviceInfo.getDeviceMac(), blp.d(str, deviceInfo.getDeviceMode()));
            return;
        }
        LogUtil.a("DeviceInfoManage", "addExtendDeviceCapability deviceInfo is null.");
    }

    public ExternalDeviceCapability a(String str) {
        return this.b.d(str);
    }

    public void l(String str) {
        this.b.k(str);
        bio.c(str, a(str));
    }

    public void b(UniteDevice uniteDevice) {
        this.b.c(uniteDevice);
    }

    public void b(String str, boolean z) {
        this.b.b(str, z);
    }

    public boolean f(String str) {
        return this.b.h(str);
    }

    public void d(String str) {
        this.b.c(str);
    }

    public void c() {
        bio.c();
    }

    static class d {
        private static bjx b = new bjx();
    }
}
