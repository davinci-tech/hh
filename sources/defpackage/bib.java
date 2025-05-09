package defpackage;

import android.text.TextUtils;
import com.huawei.devicesdk.callback.ConnectStatusInterface;
import com.huawei.devicesdk.callback.DeviceStatusChangeCallback;
import com.huawei.devicesdk.callback.MessageReceiveCallback;
import com.huawei.devicesdk.connect.physical.PhysicalLayerBase;
import com.huawei.devicesdk.entity.ConnectMode;
import com.huawei.devicesdk.entity.DeviceInfo;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes3.dex */
public class bib {
    private static Map<String, PhysicalLayerBase> c = new ConcurrentHashMap(16);
    private ConnectStatusInterface b;
    private DeviceStatusChangeCallback d = new DeviceStatusChangeCallback() { // from class: bib.2
        @Override // com.huawei.devicesdk.callback.DeviceStatusChangeCallback
        public void onConnectStatusChanged(DeviceInfo deviceInfo, int i, int i2) {
            ReleaseLogUtil.b("DEVMGR_PhysicalServiceManage", "status: ", Integer.valueOf(i));
            if (bib.this.b != null) {
                if (i == 0) {
                    bib.this.b.onPhysicalLayerDisconnected(deviceInfo, i2);
                    return;
                }
                if (i == 2) {
                    bib.this.b.onPhysicalLayerConnected(deviceInfo);
                    return;
                }
                if (i != 3) {
                    switch (i) {
                        case 30:
                            bib.this.b.onDeviceBond(deviceInfo, 30);
                            break;
                        case 31:
                            bib.this.b.onDeviceBond(deviceInfo, 31);
                            break;
                        case 32:
                            bib.this.b.onDeviceBond(deviceInfo, 32);
                            break;
                        case 33:
                            bib.this.b.onDeviceBond(deviceInfo, 33);
                            break;
                        case 34:
                            bib.this.b.onDeviceBond(deviceInfo, 34);
                            break;
                        default:
                            LogUtil.c("PhysicalServiceManage", "mDeviceStatusClientCallback default");
                            break;
                    }
                    return;
                }
                bib.this.b.onPhysicalLayerConnectFailed(deviceInfo, i2);
            }
        }
    };
    private MessageReceiveCallback e;

    public static bib a() {
        return a.d;
    }

    public PhysicalLayerBase b(String str, ConnectMode connectMode, int i) {
        PhysicalLayerBase bhvVar;
        if (str == null || connectMode == null) {
            return null;
        }
        LogUtil.c("PhysicalServiceManage", "getPhysicalLayerStrategy ", blt.a(str), ", bt type: ", Integer.valueOf(i));
        PhysicalLayerBase physicalLayerBase = c.get(str);
        if (physicalLayerBase != null) {
            return physicalLayerBase;
        }
        if (connectMode == ConnectMode.SIMPLE) {
            bhvVar = new bic();
        } else if (connectMode == ConnectMode.GENERAL && i == 2) {
            bhvVar = new bhq();
        } else if (connectMode == ConnectMode.GENERAL && i == 1) {
            bhvVar = new bhv();
        } else if (connectMode == ConnectMode.GENERAL && i == 0) {
            bhvVar = new bhz();
        } else if (connectMode == ConnectMode.TRANSPARENT && i == 2) {
            bhvVar = new bid();
        } else {
            ReleaseLogUtil.b("DEVMGR_PhysicalServiceManage", "unkown btType, use br service");
            bhvVar = new bhv();
        }
        c.put(str, bhvVar);
        return bhvVar;
    }

    public PhysicalLayerBase d(String str) {
        if (str == null) {
            return null;
        }
        return c.get(str);
    }

    public void e(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.e("PhysicalServiceManage", "clearPhysicalLayerStrategy: id is empty");
            return;
        }
        PhysicalLayerBase remove = c.remove(str);
        if (remove != null) {
            remove.destroy();
        }
    }

    public void d(ConnectMode connectMode, DeviceInfo deviceInfo) {
        if (connectMode == null || deviceInfo == null || deviceInfo.getDeviceMac() == null) {
            LogUtil.e("PhysicalServiceManage", "device or mac address or protocol is null");
            e(deviceInfo);
            return;
        }
        String deviceMac = deviceInfo.getDeviceMac();
        String a2 = blt.a(deviceMac);
        LogUtil.c("PhysicalServiceManage", "connect device. ", a2);
        PhysicalLayerBase b = a().b(deviceMac, connectMode, deviceInfo.getDeviceBtType());
        if (b != null) {
            b.init(deviceInfo, this.d, this.e);
            b.connectDevice(deviceInfo);
        } else {
            LogUtil.e("PhysicalServiceManage", "connect device failed, can not find physical layer.", a2);
            e(deviceInfo);
        }
    }

    public void b(String str, List<bim> list) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.e("PhysicalServiceManage", "sendData error. device mac is null");
            return;
        }
        PhysicalLayerBase d = d(str);
        if (d == null) {
            LogUtil.e("PhysicalServiceManage", "sendData error. physicalLayerBase is null");
            return;
        }
        if (bkz.e(list)) {
            LogUtil.e("PhysicalServiceManage", "can not get physical layer.", blt.a(str));
        }
        Iterator<bim> it = list.iterator();
        while (it.hasNext()) {
            if (!d.sendData(it.next(), str)) {
                if (this.e == null) {
                    LogUtil.e("PhysicalServiceManage", "mMessageReceiveCallback is null");
                    return;
                } else {
                    this.e.onDataReceived(d.getCurrentDeviceInfo(), null, 1);
                }
            }
        }
    }

    public void d(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.e("PhysicalServiceManage", "device is null");
            return;
        }
        String deviceMac = deviceInfo.getDeviceMac();
        if (TextUtils.isEmpty(deviceMac)) {
            LogUtil.e("PhysicalServiceManage", "deviceId is empty");
            return;
        }
        PhysicalLayerBase d = a().d(deviceMac);
        if (d == null) {
            LogUtil.e("PhysicalServiceManage", "physicalLayer is null");
        } else {
            d.disconnectDevice();
        }
    }

    private void e(DeviceInfo deviceInfo) {
        LogUtil.c("PhysicalServiceManage", "notifyDeviceConnectError", blt.a(deviceInfo));
        DeviceStatusChangeCallback deviceStatusChangeCallback = this.d;
        if (deviceStatusChangeCallback != null) {
            deviceStatusChangeCallback.onConnectStatusChanged(deviceInfo, 3, bln.e(7, 303));
        } else {
            LogUtil.e("PhysicalServiceManage", "notifyDeviceConnectError error. mDeviceStatusClientCallback is invalid");
        }
    }

    public boolean b(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.e("PhysicalServiceManage", "query device is SupportService error");
            return false;
        }
        PhysicalLayerBase d = d(str);
        if (d == null) {
            LogUtil.e("PhysicalServiceManage", "isSupportService physicalLayer is null");
            return false;
        }
        return d.isSupportService(str2);
    }

    public boolean d(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            LogUtil.e("PhysicalServiceManage", "query device is supportCharacter error");
            return false;
        }
        PhysicalLayerBase d = d(str);
        if (d == null) {
            LogUtil.e("PhysicalServiceManage", "isSupportCharacter physicalLayer is null");
            return false;
        }
        return d.isSupportCharactor(str2, str3);
    }

    public void a(ConnectStatusInterface connectStatusInterface) {
        this.b = connectStatusInterface;
    }

    public void b(MessageReceiveCallback messageReceiveCallback) {
        this.e = messageReceiveCallback;
    }

    public void e(DeviceInfo deviceInfo, ConnectMode connectMode) {
        PhysicalLayerBase b;
        if (deviceInfo == null || deviceInfo.getDeviceMac() == null) {
            LogUtil.a("PhysicalServiceManage", "PhysicalServiceManage unPairDevice device null");
            return;
        }
        String deviceMac = deviceInfo.getDeviceMac();
        PhysicalLayerBase d = a().d(deviceMac);
        if (d != null) {
            LogUtil.c("PhysicalServiceManage", "unPairDevice", blt.a(deviceInfo));
            d.init(deviceInfo, this.d, this.e);
            d.unPairDevice(deviceInfo);
        } else {
            if (connectMode == null || (b = a().b(deviceMac, connectMode, deviceInfo.getDeviceBtType())) == null) {
                return;
            }
            b.init(deviceInfo, this.d, this.e);
            b.unPairDevice(deviceInfo);
        }
    }

    public void c(DeviceInfo deviceInfo, ConnectMode connectMode) {
        if (deviceInfo == null || deviceInfo.getDeviceMac() == null || connectMode == null) {
            LogUtil.a("PhysicalServiceManage", "pair device error. device or connect mode is invalid.");
            return;
        }
        PhysicalLayerBase b = a().b(deviceInfo.getDeviceMac(), connectMode, deviceInfo.getDeviceBtType());
        if (b != null) {
            LogUtil.c("PhysicalServiceManage", "pairDevice", blt.a(deviceInfo));
            b.init(deviceInfo, this.d, this.e);
            b.pairDevice(deviceInfo);
        }
    }

    public void d(DeviceInfo deviceInfo, int i) {
        LogUtil.c("PhysicalServiceManage", "handleDualSocket enter.");
        if (deviceInfo == null || TextUtils.isEmpty(deviceInfo.getDeviceMac())) {
            LogUtil.a("PhysicalServiceManage", "handleDualSocket deviceInfo is invalid.");
            return;
        }
        PhysicalLayerBase d = d(deviceInfo.getDeviceMac());
        if (d == null) {
            LogUtil.a("PhysicalServiceManage", "isSupportService physicalLayer is null");
        } else if (d instanceof bhv) {
            ((bhv) d).a(i);
        }
    }

    static class a {
        private static bib d = new bib();
    }
}
