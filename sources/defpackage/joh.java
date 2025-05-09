package defpackage;

import android.content.Context;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.datatype.DeviceCommand;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.nio.ByteBuffer;
import java.util.Map;

/* loaded from: classes5.dex */
public class joh {
    private static final Object b = new Object();
    private static joh e;

    /* renamed from: a, reason: collision with root package name */
    private Context f13987a = BaseApplication.getContext();

    private joh() {
    }

    public static joh a() {
        joh johVar;
        synchronized (b) {
            if (e == null) {
                e = new joh();
            }
            johVar = e;
        }
        return johVar;
    }

    public void d(DeviceInfo deviceInfo) {
        LogUtil.a("DeviceNotifySettingManager", " getDefaultSwitch");
        if (a(deviceInfo)) {
            DeviceCommand deviceCommand = new DeviceCommand();
            deviceCommand.setServiceID(1);
            deviceCommand.setCommandID(33);
            ByteBuffer allocate = ByteBuffer.allocate(2);
            allocate.put((byte) 1);
            allocate.put((byte) 0);
            ReleaseLogUtil.e("DEVMGR_DeviceNotifySettingManager", "getDefaultSwitch send command");
            b(deviceCommand, allocate, 33, null, false);
        }
        SharedPreferenceManager.e(this.f13987a, String.valueOf(PrebakedEffectId.RT_COIN_DROP), "new_run_course", "1", new StorageParams(0));
    }

    private boolean a(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.h("DeviceNotifySettingManager", "isSupportDefaultSwitch deviceInfo is null");
            return false;
        }
        Map<String, DeviceCapability> a2 = jfq.c().a(1, deviceInfo.getDeviceIdentify(), "DeviceNotifySettingManager");
        if (a2 == null || a2.isEmpty()) {
            LogUtil.h("DeviceNotifySettingManager", "isSupportDefaultSwitch deviceCapabilityhMap is null or empty");
            return false;
        }
        DeviceCapability deviceCapability = a2.get(deviceInfo.getDeviceIdentify());
        if (deviceCapability != null && deviceCapability.isSupportDefaultSwitch()) {
            return true;
        }
        LogUtil.h("DeviceNotifySettingManager", "isSupportDefaultSwitch device not support default switch");
        return false;
    }

    public void c(jpd jpdVar, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("DeviceNotifySettingManager", "Enter sendFAQMessageToDevice !");
        if (jpdVar == null) {
            LogUtil.b("DeviceNotifySettingManager", "faqMessageInfo is null");
        } else {
            e(jpdVar, iBaseResponseCallback);
        }
    }

    private void e(jpd jpdVar, IBaseResponseCallback iBaseResponseCallback) {
        jou.c(2, iBaseResponseCallback);
        String d = jpdVar.d();
        int i = jpdVar.i();
        long c = jpdVar.c();
        int g = jpdVar.g();
        String a2 = jpdVar.a();
        String b2 = jpdVar.b();
        byte[] e2 = jpdVar.e();
        String c2 = cvx.c(d);
        String d2 = cvx.d(c2.length() / 2);
        String e3 = cvx.e(1);
        String e4 = cvx.e(i);
        String d3 = cvx.d(e4.length() / 2);
        String e5 = cvx.e(2);
        String b3 = cvx.b(c);
        String d4 = cvx.d(b3.length() / 2);
        String e6 = cvx.e(3);
        String e7 = cvx.e(g);
        String d5 = cvx.d(e7.length() / 2);
        String e8 = cvx.e(4);
        String c3 = cvx.c(a2);
        String d6 = cvx.d(c3.length() / 2);
        String e9 = cvx.e(5);
        String c4 = cvx.c(b2);
        String d7 = cvx.d(c4.length() / 2);
        String e10 = cvx.e(6);
        String d8 = e2 != null ? cvx.d(e2) : "";
        String d9 = cvx.d(d8.length() / 2);
        String e11 = cvx.e(7);
        int length = e3.length() / 2;
        int length2 = d2.length() / 2;
        int length3 = c2.length() / 2;
        int length4 = e5.length() / 2;
        int length5 = d3.length() / 2;
        int length6 = e4.length() / 2;
        int length7 = e6.length() / 2;
        int length8 = d4.length() / 2;
        int length9 = b3.length() / 2;
        int length10 = e8.length() / 2;
        int length11 = d5.length() / 2;
        int length12 = e7.length() / 2;
        int length13 = e9.length() / 2;
        int length14 = d6.length() / 2;
        int length15 = c3.length() / 2;
        int length16 = e10.length() / 2;
        int length17 = d7.length() / 2;
        int length18 = c4.length() / 2;
        ByteBuffer allocate = ByteBuffer.allocate(length + length2 + length3 + length4 + length5 + length6 + length7 + length8 + length9 + length10 + length11 + length12 + length13 + length14 + length15 + length16 + length17 + length18 + (e11.length() / 2) + (d9.length() / 2) + (d8.length() / 2));
        a(c2, d2, e3, allocate);
        j(e4, d3, e5, allocate);
        d(b3, d4, e6, allocate);
        b(e7, d5, e8, allocate);
        c(c3, d6, e9, allocate);
        e(c4, d7, e10, allocate);
        c(e2, d8, d9, e11, allocate);
        e(allocate);
    }

    private void e(ByteBuffer byteBuffer) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(2);
        deviceCommand.setCommandID(7);
        deviceCommand.setDataLen(byteBuffer.array().length);
        deviceCommand.setDataContent(byteBuffer.array());
        c(deviceCommand);
    }

    private void c(byte[] bArr, String str, String str2, String str3, ByteBuffer byteBuffer) {
        byteBuffer.put(cvx.a(str3));
        byteBuffer.put(cvx.a(str2));
        if (bArr != null) {
            byteBuffer.put(bArr);
        } else {
            byteBuffer.put(cvx.a(str));
        }
    }

    private void e(String str, String str2, String str3, ByteBuffer byteBuffer) {
        byteBuffer.put(cvx.a(str3));
        byteBuffer.put(cvx.a(str2));
        byteBuffer.put(cvx.a(str));
    }

    private void c(String str, String str2, String str3, ByteBuffer byteBuffer) {
        byteBuffer.put(cvx.a(str3));
        byteBuffer.put(cvx.a(str2));
        byteBuffer.put(cvx.a(str));
    }

    private void b(String str, String str2, String str3, ByteBuffer byteBuffer) {
        byteBuffer.put(cvx.a(str3));
        byteBuffer.put(cvx.a(str2));
        byteBuffer.put(cvx.a(str));
    }

    private void d(String str, String str2, String str3, ByteBuffer byteBuffer) {
        byteBuffer.put(cvx.a(str3));
        byteBuffer.put(cvx.a(str2));
        byteBuffer.put(cvx.a(str));
    }

    private void j(String str, String str2, String str3, ByteBuffer byteBuffer) {
        byteBuffer.put(cvx.a(str3));
        byteBuffer.put(cvx.a(str2));
        byteBuffer.put(cvx.a(str));
    }

    private void a(String str, String str2, String str3, ByteBuffer byteBuffer) {
        byteBuffer.put(cvx.a(str3));
        byteBuffer.put(cvx.a(str2));
        byteBuffer.put(cvx.a(str));
    }

    public void e(String str, IBaseResponseCallback iBaseResponseCallback) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(1);
        deviceCommand.setCommandID(25);
        String c = cvx.c(str);
        String d = cvx.d(c.length() / 2);
        String e2 = cvx.e(1);
        ByteBuffer allocate = ByteBuffer.allocate((e2.length() / 2) + (d.length() / 2) + (c.length() / 2));
        allocate.put(cvx.a(e2));
        allocate.put(cvx.a(d));
        allocate.put(cvx.a(c));
        b(deviceCommand, allocate, 25, iBaseResponseCallback, true);
    }

    public void a(IBaseResponseCallback iBaseResponseCallback) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(1);
        deviceCommand.setCommandID(24);
        ByteBuffer allocate = ByteBuffer.allocate(2);
        allocate.put((byte) 1);
        allocate.put((byte) 0);
        b(deviceCommand, allocate, 24, iBaseResponseCallback, true);
    }

    private void b(DeviceCommand deviceCommand, ByteBuffer byteBuffer, int i, IBaseResponseCallback iBaseResponseCallback, boolean z) {
        if (iBaseResponseCallback != null && z) {
            jfh.b(i, iBaseResponseCallback);
        }
        deviceCommand.setDataLen(byteBuffer.array().length);
        deviceCommand.setDataContent(byteBuffer.array());
        c(deviceCommand);
    }

    private void c(DeviceCommand deviceCommand) {
        jfq.c().b(deviceCommand);
    }

    public void e(DeviceInfo deviceInfo) {
        d(deviceInfo);
    }
}
