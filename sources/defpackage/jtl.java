package defpackage;

import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.unitedevicemanger.handshake.VariableHandshakeGeneralCommandBase;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.unitedevice.entity.UniteDevice;
import health.compact.a.utils.StringUtils;
import java.nio.ByteBuffer;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes5.dex */
public class jtl extends VariableHandshakeGeneralCommandBase {
    private DeviceInfo b;
    private ByteBuffer c;

    /* renamed from: a, reason: collision with root package name */
    private final CountDownLatch f14071a = new CountDownLatch(1);
    private final CountDownLatch e = new CountDownLatch(1);
    private boolean d = false;

    jtl(DeviceInfo deviceInfo) {
        this.b = deviceInfo;
    }

    @Override // com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.unitedevicemanger.handshake.VariableHandshakeGeneralCommandBase
    public int constructCommandMessage(bir birVar) {
        DeviceInfo j;
        DeviceInfo deviceInfo = this.b;
        if (deviceInfo != null && deviceInfo.getIsDemoWatch() == 1) {
            b(birVar);
            return 50;
        }
        if (!c(PreConnectManager.CONNECT_INTERNAL) && !this.d) {
            LogUtil.h("StartupGuideUserSettingCommand", "oobe ui no created.");
            return 57;
        }
        this.d = false;
        if (!d(600000L)) {
            LogUtil.h("StartupGuideUserSettingCommand", "user confirm timeout.");
            if (this.b == null || (j = bjx.a().j(this.b.getDeviceMac())) == null || j.getDeviceConnectState() != 2) {
                return 50;
            }
            LogUtil.h("StartupGuideUserSettingCommand", "user confirm timeout, device is connected return.");
            return 54;
        }
        ByteBuffer byteBuffer = this.c;
        if (byteBuffer == null) {
            LogUtil.h("StartupGuideUserSettingCommand", "mCommandMessage is null.");
            return 51;
        }
        ByteBuffer allocate = ByteBuffer.allocate(byteBuffer.capacity() + 2);
        allocate.put((byte) 1);
        allocate.put((byte) 48);
        allocate.put(this.c);
        birVar.e(allocate.array());
        return 50;
    }

    private boolean c(long j) {
        try {
        } catch (InterruptedException unused) {
            LogUtil.b("StartupGuideUserSettingCommand", "countDownLatch exception");
        }
        if (this.f14071a.await(j, TimeUnit.MILLISECONDS)) {
            return true;
        }
        LogUtil.h("StartupGuideUserSettingCommand", "countDownLatch time out");
        return false;
    }

    private boolean d(long j) {
        try {
        } catch (InterruptedException unused) {
            LogUtil.b("StartupGuideUserSettingCommand", "awaitUserConfirm exception");
        }
        if (this.e.await(j, TimeUnit.MILLISECONDS)) {
            return true;
        }
        LogUtil.h("StartupGuideUserSettingCommand", "awaitUserConfirm time out");
        return false;
    }

    @Override // com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.unitedevicemanger.handshake.VariableHandshakeGeneralCommandBase
    public int processReceivedData(UniteDevice uniteDevice, String str) {
        if (StringUtils.g(str)) {
            LogUtil.h("StartupGuideUserSettingCommand", "hexString is null.");
            return 51;
        }
        this.mCurrentCommandTag = getCurrentCommandTag();
        if (!jtm.a(this.mCurrentCommandTag, str)) {
            LogUtil.h("StartupGuideUserSettingCommand", "is not current command reply, abandon this reply command");
            return 54;
        }
        if (jtm.e(str)) {
            return 52;
        }
        LogUtil.h("StartupGuideUserSettingCommand", "processReceivedData checkResponseCode Failed.");
        return 51;
    }

    public void a(byte[] bArr) {
        LogUtil.a("StartupGuideUserSettingCommand", "notifyUserSelect enter.");
        this.c = ByteBuffer.wrap(bArr);
        this.e.countDown();
    }

    public void c() {
        LogUtil.a("StartupGuideUserSettingCommand", "oobeCreated countDown.");
        this.f14071a.countDown();
    }

    public void a(boolean z) {
        this.d = z;
    }

    private void b(bir birVar) {
        String valueOf = String.valueOf(new Date().getTime());
        ByteBuffer c = c(valueOf, "user_license_agreement");
        ByteBuffer c2 = c(valueOf, "device_information_management");
        ByteBuffer c3 = c(valueOf, "software_update_service_statement");
        int capacity = c.capacity() + c2.capacity() + c3.capacity();
        byte[] d = blq.d(capacity);
        ByteBuffer allocate = ByteBuffer.allocate(d.length + 1 + capacity + 2);
        allocate.put((byte) 1);
        allocate.put((byte) 48);
        allocate.put(jto.f14075a.byteValue());
        allocate.put(d);
        allocate.put(c.array());
        allocate.put(c2.array());
        allocate.put(c3.array());
        birVar.e(allocate.array());
    }

    private ByteBuffer c(String str, String str2) {
        int length = str2.length() + 21 + str.length();
        ByteBuffer allocate = ByteBuffer.allocate(length + 11);
        allocate.put(jto.b.byteValue()).put((byte) (length + 9)).put((byte) 3).put(blq.d(str2.length())).put(blq.c(str2)).put((byte) 4).put((byte) 1).put((byte) 1).put((byte) 5).put(blq.d(21)).put(blq.c("20230508-20230508-0-0")).put((byte) 6).put(blq.d(str.length())).put(blq.c(str));
        return allocate;
    }

    @Override // com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.unitedevicemanger.handshake.VariableHandshakeGeneralCommandBase
    public String getCurrentCommandTag() {
        return "0130";
    }
}
