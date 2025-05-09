package defpackage;

import com.google.gson.JsonSyntaxException;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.devicesdk.entity.ExternalDeviceCapability;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.unitedevicemanger.handshake.VariableHandshakeGeneralCommandBase;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.unitedevice.entity.UniteDevice;
import health.compact.a.CommonUtil;
import health.compact.a.utils.StringUtils;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class jti extends VariableHandshakeGeneralCommandBase {
    private boolean b = false;
    private boolean e = false;

    jti() {
    }

    @Override // com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.unitedevicemanger.handshake.VariableHandshakeGeneralCommandBase
    public int constructCommandMessage(bir birVar) {
        a(birVar);
        return 50;
    }

    @Override // com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.unitedevicemanger.handshake.VariableHandshakeGeneralCommandBase
    public int processReceivedData(UniteDevice uniteDevice, String str) {
        if (StringUtils.g(str)) {
            LogUtil.b("QueryDeviceWorkModeCommand", "hexString is null.");
            return 51;
        }
        this.mCurrentCommandTag = getCurrentCommandTag();
        if (!jtm.a(this.mCurrentCommandTag, str)) {
            LogUtil.h("QueryDeviceWorkModeCommand", "is not current command reply, abandon this reply command");
            return 54;
        }
        if (!jtm.e(str)) {
            LogUtil.b("QueryDeviceWorkModeCommand", "processReceivedData checkResponseCode Failed.");
            return 51;
        }
        return d(uniteDevice, str);
    }

    private void a(bir birVar) {
        ByteBuffer allocate = ByteBuffer.allocate(6);
        allocate.put((byte) 38).put((byte) 1).put((byte) 2).put((byte) 0).put((byte) 1).put((byte) 0);
        birVar.e(allocate.array());
    }

    private int d(UniteDevice uniteDevice, String str) {
        cwe a2 = jtm.a(str);
        if (a2 == null) {
            LogUtil.b("QueryDeviceWorkModeCommand", "tlvFather is null.");
            return 51;
        }
        List<cwd> e = a2.e();
        if (koq.b(e)) {
            LogUtil.b("QueryDeviceWorkModeCommand", "tlvList is empty.");
            return 51;
        }
        if (uniteDevice.getCapability() == null) {
            LogUtil.b("QueryDeviceWorkModeCommand", "capabilityJsonString is null");
            return 51;
        }
        DeviceInfo deviceInfo = uniteDevice.getDeviceInfo();
        if (deviceInfo == null) {
            LogUtil.b("QueryDeviceWorkModeCommand", "deviceInfo is null");
            return 51;
        }
        int i = 0;
        for (cwd cwdVar : e) {
            byte w = (byte) CommonUtil.w(cwdVar.e());
            if (w == 1) {
                int w2 = CommonUtil.w(cwdVar.c());
                this.e = true;
                deviceInfo.setAutoDetectSwitchStatus(w2);
            } else if (w == 2) {
                i = CommonUtil.w(cwdVar.c());
                this.b = true;
                deviceInfo.setFootWearPosition(i);
            }
        }
        uniteDevice.setDeviceInfo(deviceInfo);
        e(i);
        return d(uniteDevice);
    }

    private int d(UniteDevice uniteDevice) {
        try {
            ExternalDeviceCapability capability = uniteDevice.getCapability();
            if (capability == null) {
                LogUtil.b("QueryDeviceWorkModeCommand", "externalDeviceCapability is null");
                return 51;
            }
            DeviceCapability compatibleCapacity = capability.getCompatibleCapacity();
            if (compatibleCapacity == null) {
                LogUtil.b("QueryDeviceWorkModeCommand", "deviceCapability is null");
                return 51;
            }
            compatibleCapacity.configureSupportAutoDetectMode(this.b);
            compatibleCapacity.configureSupportFootWear(this.e);
            if (!compatibleCapacity.isSupportAccountSwitch() && !compatibleCapacity.isSupportChangePhonePair()) {
                if (!compatibleCapacity.isSupportSettingRelated()) {
                    return 52;
                }
                this.mNextVariableHandshakeCommand = new jtg();
                return 50;
            }
            this.mNextVariableHandshakeCommand = new jtk(uniteDevice);
            return 50;
        } catch (JsonSyntaxException unused) {
            LogUtil.b("QueryDeviceWorkModeCommand", "get deviceCapability JsonSyntaxException");
            return 52;
        }
    }

    private void e(int i) {
        List<com.huawei.health.devicemgr.business.entity.DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "QueryDeviceWorkModeCommand");
        if (deviceList == null || deviceList.size() <= 0) {
            LogUtil.a("QueryDeviceWorkModeCommand", "usedDeviceList is null");
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (int size = deviceList.size() - 1; size >= 0; size--) {
            com.huawei.health.devicemgr.business.entity.DeviceInfo deviceInfo = deviceList.get(size);
            if (deviceInfo != null && kcw.a().a(deviceInfo)) {
                LogUtil.a("QueryDeviceWorkModeCommand", "processDeviceMode isSupportMultiConnect:", blt.b(deviceInfo.getDeviceIdentify()));
            } else if (deviceInfo != null && deviceInfo.getDeviceConnectState() == 2) {
                int autoDetectSwitchStatus = deviceInfo.getAutoDetectSwitchStatus();
                if ((i == 0 && autoDetectSwitchStatus == 0) || (i == 1 && autoDetectSwitchStatus == 1)) {
                    arrayList.add(deviceInfo.getDeviceIdentify());
                }
            }
        }
        if (arrayList.size() > 0) {
            jtc.c().unPair(arrayList, false);
        }
    }

    @Override // com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.unitedevicemanger.handshake.VariableHandshakeGeneralCommandBase
    public String getCurrentCommandTag() {
        return "2601";
    }
}
