package defpackage;

import com.huawei.devicesdk.entity.ExternalDeviceCapability;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.unitedevicemanger.handshake.VariableHandshakeGeneralCommandBase;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.unitedevice.entity.UniteDevice;
import health.compact.a.CommonUtil;
import health.compact.a.KeyValDbManager;
import health.compact.a.utils.StringUtils;
import java.nio.ByteBuffer;
import java.util.Iterator;

/* loaded from: classes5.dex */
public class jtk extends VariableHandshakeGeneralCommandBase {

    /* renamed from: a, reason: collision with root package name */
    private final boolean f14069a;
    private final boolean e;

    jtk(UniteDevice uniteDevice) {
        DeviceInfo c = blc.c(uniteDevice);
        this.e = cwi.c(c, 172);
        this.f14069a = c.getIsDemoWatch() == 1;
    }

    @Override // com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.unitedevicemanger.handshake.VariableHandshakeGeneralCommandBase
    public int processReceivedData(UniteDevice uniteDevice, String str) {
        if (StringUtils.g(str)) {
            LogUtil.h("SendAccountToDeviceCommand", "hexString is null.");
            return 51;
        }
        if (uniteDevice != null && uniteDevice.getDeviceInfo() != null) {
            bmw.e(100047, uniteDevice.getDeviceInfo().getDeviceName(), "", "");
        }
        this.mCurrentCommandTag = getCurrentCommandTag();
        if (!jtm.a(this.mCurrentCommandTag, str)) {
            LogUtil.h("SendAccountToDeviceCommand", "is not current command reply, abandon this reply command");
            return 54;
        }
        if (!jtm.e(str)) {
            LogUtil.h("SendAccountToDeviceCommand", "processReceivedData checkResponseCode Failed.");
            return 51;
        }
        if (d(str, uniteDevice)) {
            return 50;
        }
        LogUtil.h("SendAccountToDeviceCommand", "resolveSendAccountToDeviceCommand Failed.");
        return 51;
    }

    @Override // com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.unitedevicemanger.handshake.VariableHandshakeGeneralCommandBase
    public int constructCommandMessage(bir birVar) {
        ByteBuffer allocate;
        LogUtil.h("SendAccountToDeviceCommand", "constructCommandMessage Enter");
        String e = KeyValDbManager.b(BaseApplication.getContext()).e("user_id");
        if (e == null || "0".equals(e) || "com.huawei.health".equals(e)) {
            LogUtil.h("SendAccountToDeviceCommand", "constructCommandMessage userId is empty");
            e = "";
        }
        if (birVar == null) {
            LogUtil.h("SendAccountToDeviceCommand", "commandMessage is null.");
            return 51;
        }
        if (this.f14069a) {
            e = "demo_account";
        }
        byte[] d = cvx.d(e);
        int length = d.length;
        if (this.e) {
            LogUtil.a("SendAccountToDeviceCommand", "support diff account pairing optimization");
            allocate = ByteBuffer.allocate(length + 7);
            allocate.put((byte) 26).put((byte) 5).put((byte) 1).put(cvx.c(length)).put(d).put((byte) 3).put((byte) 1).put((byte) 1);
        } else {
            LogUtil.a("SendAccountToDeviceCommand", "not support diff account pairing optimization");
            allocate = ByteBuffer.allocate(length + 4);
            allocate.put((byte) 26).put((byte) 5).put((byte) 1).put(cvx.c(length)).put(d);
        }
        birVar.e(allocate.array());
        return 50;
    }

    private boolean d(String str, UniteDevice uniteDevice) {
        if (c(str)) {
            bmw.e(100048, uniteDevice.getDeviceInfo().getDeviceName(), String.valueOf(c(str)), "");
            ExternalDeviceCapability capability = uniteDevice.getCapability();
            if (capability == null) {
                LogUtil.b("SendAccountToDeviceCommand", "resolveSendAccountToDeviceCommand externalDeviceCapability is null.");
                return false;
            }
            DeviceCapability compatibleCapacity = capability.getCompatibleCapacity();
            if (compatibleCapacity == null) {
                LogUtil.h("SendAccountToDeviceCommand", "resolveSendAccountToDeviceCommand deviceCapability is null.");
                return false;
            }
            if (!compatibleCapacity.isSupportSettingRelated()) {
                LogUtil.a("SendAccountToDeviceCommand", "resolveSendAccountToDeviceCommand is not support setting related.");
                return false;
            }
            this.mNextVariableHandshakeCommand = new jtg();
            return true;
        }
        bmw.e(100049, uniteDevice.getDeviceInfo().getDeviceName(), String.valueOf(c(str)), "");
        LogUtil.a("SendAccountToDeviceCommand", "resolveSendAccountToDeviceCommand account is different");
        this.mNextVariableHandshakeCommand = new jth();
        if (this.e) {
            jtm.a(uniteDevice, 13);
            return true;
        }
        jtm.a(uniteDevice, 9);
        return true;
    }

    private boolean c(String str) {
        int i;
        cwe a2 = jtm.a(str);
        if (a2 == null) {
            LogUtil.h("SendAccountToDeviceCommand", "tlvFather is null.");
            return false;
        }
        Iterator<cwd> it = a2.e().iterator();
        while (true) {
            if (!it.hasNext()) {
                i = -1;
                break;
            }
            cwd next = it.next();
            if (CommonUtil.w(next.e()) == 2) {
                i = CommonUtil.w(next.c());
                break;
            }
        }
        a(i);
        return i != 1;
    }

    private void a(int i) {
        LogUtil.a("SendAccountToDeviceCommand", "saveAccountStatus:", Integer.valueOf(i));
        if (4 == i) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: jtk.4
                @Override // java.lang.Runnable
                public void run() {
                    HiHealthManager.d(BaseApplication.getContext()).setUserPreference(new HiUserPreference("sync_account_dialog_mark", "1"), false);
                }
            });
        } else if (i == 0) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: jtk.5
                @Override // java.lang.Runnable
                public void run() {
                    HiHealthManager.d(BaseApplication.getContext()).setUserPreference(new HiUserPreference("sync_account_dialog_mark", "0"), false);
                }
            });
        } else {
            LogUtil.h("SendAccountToDeviceCommand", "saveAccountStatus others: ", Integer.valueOf(i));
        }
    }

    @Override // com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.unitedevicemanger.handshake.VariableHandshakeGeneralCommandBase
    public String getCurrentCommandTag() {
        return "1A05";
    }
}
