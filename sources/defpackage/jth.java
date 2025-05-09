package defpackage;

import com.huawei.datatype.DeviceCommand;
import com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.unitedevicemanger.handshake.VariableHandshakeGeneralCommandBase;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.unitedevice.entity.UniteDevice;
import health.compact.a.CommonUtil;
import health.compact.a.utils.StringUtils;
import java.nio.ByteBuffer;

/* loaded from: classes5.dex */
public class jth extends VariableHandshakeGeneralCommandBase {
    jth() {
    }

    @Override // com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.unitedevicemanger.handshake.VariableHandshakeGeneralCommandBase
    public int constructCommandMessage(bir birVar) {
        LogUtil.h("DeviceReportUserSelectCommand", "constructCommandMessage not set commandMessage.");
        return 50;
    }

    @Override // com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.unitedevicemanger.handshake.VariableHandshakeGeneralCommandBase
    public int processReceivedData(UniteDevice uniteDevice, String str) {
        if (StringUtils.g(str)) {
            LogUtil.h("DeviceReportUserSelectCommand", "hexString is null.");
            return 51;
        }
        if (uniteDevice == null) {
            LogUtil.h("DeviceReportUserSelectCommand", "device is null.");
            return 51;
        }
        this.mCurrentCommandTag = getCurrentCommandTag();
        if (!jtm.a(this.mCurrentCommandTag, str)) {
            LogUtil.h("DeviceReportUserSelectCommand", "is not current command reply, abandon this reply command");
            return 54;
        }
        if (!jtm.e(str)) {
            LogUtil.h("DeviceReportUserSelectCommand", "processReceivedData checkResponseCode Failed.");
            return 51;
        }
        if (c(str)) {
            LogUtil.h("DeviceReportUserSelectCommand", "resolveUserSelectRestoreFactory user select reset factory");
            boolean c = cwi.c(blc.c(uniteDevice), 172);
            if (c) {
                LogUtil.a("DeviceReportUserSelectCommand", "support diff account pairing optimization and send corresponding broadcast");
                jtm.a(uniteDevice, 14);
                jtc.c().sendDeviceData(b(uniteDevice));
                try {
                    Thread.sleep(2000L);
                } catch (InterruptedException unused) {
                    LogUtil.b("DeviceReportUserSelectCommand", "Occur exception when thread sleeping");
                }
            } else {
                jtm.a(uniteDevice, 10);
            }
            if (!uniteDevice.getDeviceInfo().isReconnect()) {
                LogUtil.h("DeviceReportUserSelectCommand", "first pair, device reset factory");
                bjv.e().a(uniteDevice.getDeviceInfo(), !c);
                if (c) {
                    bkw.d().d(uniteDevice.getDeviceInfo());
                }
            }
        } else if (!uniteDevice.getDeviceInfo().isReconnect()) {
            bjv.e().a(uniteDevice.getDeviceInfo(), true);
        }
        return 51;
    }

    public static boolean c(String str) {
        cwe a2 = jtm.a(str);
        if (a2 == null) {
            LogUtil.h("DeviceReportUserSelectCommand", "tlvFather is null.");
            return false;
        }
        for (cwd cwdVar : a2.e()) {
            if (CommonUtil.w(cwdVar.e()) == 1) {
                return CommonUtil.w(cwdVar.c()) == 0;
            }
        }
        return false;
    }

    private DeviceCommand b(UniteDevice uniteDevice) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(26);
        deviceCommand.setCommandID(6);
        ByteBuffer allocate = ByteBuffer.allocate(6);
        allocate.put(Byte.MAX_VALUE).put((byte) 4).put(cvx.g(100000));
        byte[] array = allocate.array();
        deviceCommand.setDataContent(array);
        deviceCommand.setDataLen(array.length);
        deviceCommand.setmIdentify(uniteDevice.getIdentify());
        LogUtil.a("DeviceReportUserSelectCommand", "constructConfirmCommand deviceCommand:", deviceCommand.toString());
        return deviceCommand;
    }

    @Override // com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.unitedevicemanger.handshake.VariableHandshakeGeneralCommandBase
    public String getCurrentCommandTag() {
        return "1A06";
    }
}
