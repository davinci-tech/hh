package defpackage;

import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.devicesdk.entity.ExternalDeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.unitedevicemanger.handshake.VariableHandshakeGeneralCommandBase;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.unitedevice.entity.UniteDevice;
import health.compact.a.CommonUtil;
import health.compact.a.utils.StringUtils;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class jtg extends VariableHandshakeGeneralCommandBase {
    jtg() {
    }

    @Override // com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.unitedevicemanger.handshake.VariableHandshakeGeneralCommandBase
    public int constructCommandMessage(bir birVar) {
        ByteBuffer allocate = ByteBuffer.allocate(14);
        allocate.put((byte) 1).put((byte) 49).put((byte) 1).put((byte) 0).put((byte) 2).put((byte) 0).put((byte) 3).put((byte) 0).put((byte) 4).put((byte) 0).put((byte) 5).put((byte) 0).put((byte) 6).put((byte) 0);
        if (birVar == null) {
            LogUtil.h("QuerySettingRelateCapabilityCommand", "commandMessage is null.");
            return 51;
        }
        birVar.e(allocate.array());
        return 50;
    }

    @Override // com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.unitedevicemanger.handshake.VariableHandshakeGeneralCommandBase
    public int processReceivedData(UniteDevice uniteDevice, String str) {
        if (StringUtils.g(str)) {
            LogUtil.h("QuerySettingRelateCapabilityCommand", "hexString is null.");
            return 51;
        }
        this.mCurrentCommandTag = getCurrentCommandTag();
        if (!jtm.a(this.mCurrentCommandTag, str)) {
            LogUtil.h("QuerySettingRelateCapabilityCommand", "is not current command reply, abandon this reply command");
            return 54;
        }
        if (!jtm.e(str)) {
            LogUtil.h("QuerySettingRelateCapabilityCommand", "processReceivedData checkResponseCode Failed.");
            return 51;
        }
        if (!e(str, uniteDevice)) {
            LogUtil.h("QuerySettingRelateCapabilityCommand", "processReceivedData resolveRelateSettingCommand Failed.");
            return 51;
        }
        DeviceInfo deviceInfo = uniteDevice.getDeviceInfo();
        if (deviceInfo == null) {
            return 51;
        }
        if (deviceInfo.getDeviceFactoryReset() != 1) {
            return 52;
        }
        LogUtil.h("QuerySettingRelateCapabilityCommand", "processReceivedData factory reset");
        if (d(deviceInfo)) {
            LogUtil.h("QuerySettingRelateCapabilityCommand", "processReceivedData factory reset and assistant device, so disconnect the device: ", blt.b(deviceInfo.getDeviceMac()));
            return 51;
        }
        if (deviceInfo.getIsDemoWatch() != 1) {
            this.mNextVariableHandshakeCommand = new jtl(deviceInfo);
            deviceInfo.setDeviceConnectState(11);
            jtm.a(uniteDevice, 11);
            return 50;
        }
        this.mNextVariableHandshakeCommand = new jtl(deviceInfo);
        return 50;
    }

    private boolean d(DeviceInfo deviceInfo) {
        List<com.huawei.health.devicemgr.business.entity.DeviceInfo> d = jtd.b().d();
        if (d.size() == 0) {
            return false;
        }
        for (com.huawei.health.devicemgr.business.entity.DeviceInfo deviceInfo2 : d) {
            if (deviceInfo.getDeviceMac().equals(deviceInfo2.getDeviceIdentify()) && deviceInfo2.getRelationship().equals("assistant_relationship")) {
                jtd.b().c(deviceInfo2.getDeviceIdentify());
                return true;
            }
        }
        return false;
    }

    private boolean e(String str, UniteDevice uniteDevice) {
        cwe a2 = jtm.a(str);
        if (a2 == null) {
            LogUtil.h("QuerySettingRelateCapabilityCommand", "resolveRelateSettingCommand, tlvFather is null.");
            return false;
        }
        ExternalDeviceCapability capability = uniteDevice.getCapability();
        if (capability == null) {
            LogUtil.b("QuerySettingRelateCapabilityCommand", "externalDeviceCapability is null.");
            return false;
        }
        DeviceCapability compatibleCapacity = capability.getCompatibleCapacity();
        if (compatibleCapacity == null) {
            LogUtil.h("QuerySettingRelateCapabilityCommand", "deviceCapability is null.");
            return false;
        }
        Iterator<cwd> it = a2.e().iterator();
        while (it.hasNext()) {
            e(compatibleCapacity, it.next());
        }
        capability.setCompatibleCapacity(compatibleCapacity);
        blc.d(uniteDevice.getIdentify(), compatibleCapacity);
        return true;
    }

    private void e(DeviceCapability deviceCapability, cwd cwdVar) {
        LogUtil.a("QuerySettingRelateCapabilityCommand", "the case is ", Integer.valueOf(CommonUtil.w(cwdVar.e())));
        switch (CommonUtil.w(cwdVar.e())) {
            case 1:
                b(deviceCapability, cwdVar);
                break;
            case 2:
                h(deviceCapability, cwdVar);
                break;
            case 3:
                j(deviceCapability, cwdVar);
                break;
            case 4:
                c(deviceCapability, cwdVar);
                break;
            case 5:
                a(deviceCapability, cwdVar);
                break;
            case 6:
                d(deviceCapability, cwdVar);
                break;
            default:
                LogUtil.h("QuerySettingRelateCapabilityCommand", "resolveRelateSettingCommand default");
                break;
        }
    }

    private void d(DeviceCapability deviceCapability, cwd cwdVar) {
        if (deviceCapability == null || cwdVar == null) {
            LogUtil.h("params is null", new Object[0]);
        } else {
            deviceCapability.configureHmsNotifyUpdate(CommonUtil.w(cwdVar.c()) == 1);
        }
    }

    private void a(DeviceCapability deviceCapability, cwd cwdVar) {
        if (deviceCapability == null || cwdVar == null) {
            LogUtil.h("QuerySettingRelateCapabilityCommand", "capability or tlv is null");
        } else {
            deviceCapability.configureHmsAutoUpdateWifi(CommonUtil.w(cwdVar.c()) == 1);
        }
    }

    private void c(DeviceCapability deviceCapability, cwd cwdVar) {
        if (deviceCapability == null || cwdVar == null) {
            LogUtil.h("QuerySettingRelateCapabilityCommand", "capability or tlv is null");
        } else {
            deviceCapability.configureHmsAutoUpdate(CommonUtil.w(cwdVar.c()) == 1);
        }
    }

    private void j(DeviceCapability deviceCapability, cwd cwdVar) {
        if (deviceCapability == null || cwdVar == null) {
            LogUtil.h("capability tlv is null", new Object[0]);
        } else {
            deviceCapability.setSupportSmartWatchVersionStatus(true);
            deviceCapability.configureSmartWatchVersionTypeValue(CommonUtil.w(cwdVar.c()));
        }
    }

    private void b(DeviceCapability deviceCapability, cwd cwdVar) {
        if (deviceCapability == null || cwdVar == null) {
            LogUtil.h("QuerySettingRelateCapabilityCommand", "capability tlv is null");
            return;
        }
        byte[] a2 = cvx.a(cwdVar.c());
        LogUtil.a("QuerySettingRelateCapabilityCommand", "string bitmap : ", cwdVar.c());
        deviceCapability.configureSupportLegalPrivacy(CommonUtil.a(a2, 0));
        deviceCapability.configureSupportLegalUserAgreement(CommonUtil.a(a2, 1));
        deviceCapability.configureSupportOpenSourceOpen(CommonUtil.a(a2, 2));
        deviceCapability.configureSupportLegalServiceStatement(CommonUtil.a(a2, 3));
        deviceCapability.configureSupportLegalSourceStatement(CommonUtil.a(a2, 4));
        deviceCapability.configureSupportLegalSystemWebView(CommonUtil.a(a2, 5));
    }

    private void h(DeviceCapability deviceCapability, cwd cwdVar) {
        if (deviceCapability == null || cwdVar == null) {
            LogUtil.h("QuerySettingRelateCapabilityCommand", "updateSelfUploadDeviceLogCapability capability tlv is null");
            return;
        }
        byte[] a2 = cvx.a(cwdVar.c());
        LogUtil.a("QuerySettingRelateCapabilityCommand", "string bitmap : ", cwdVar.c());
        deviceCapability.setSupportSelfUploadDeviceLog(CommonUtil.a(a2, 0));
        deviceCapability.configureSupportCoreSleepNewFile(CommonUtil.a(a2, 1));
        deviceCapability.configureSupportRriNewFile(CommonUtil.a(a2, 2));
        deviceCapability.configureIsSupportUploadGpsAndPdrFile(CommonUtil.a(a2, 3));
    }

    @Override // com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.unitedevicemanger.handshake.VariableHandshakeGeneralCommandBase
    public String getCurrentCommandTag() {
        return "0131";
    }
}
