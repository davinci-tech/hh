package defpackage;

import android.text.TextUtils;
import com.huawei.datatype.DeviceCommand;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.nio.ByteBuffer;
import java.util.ArrayList;

/* loaded from: classes5.dex */
class jql {
    jql() {
    }

    public static void c(boolean z, String str, int i, int i2, IBaseResponseCallback iBaseResponseCallback) {
        ReleaseLogUtil.e("DEVMGR_SwitchSendCommandUtil", "sendSetSwitchStatusCmd serviceId = ", Integer.valueOf(i), ",commandId = ", Integer.valueOf(i2));
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(i);
        deviceCommand.setCommandID(i2);
        if (!TextUtils.isEmpty(str)) {
            deviceCommand.setmIdentify(str);
        }
        ByteBuffer allocate = ByteBuffer.allocate(3);
        allocate.put((byte) 1);
        allocate.put((byte) 1);
        if (z) {
            allocate.put((byte) 1);
        } else {
            allocate.put((byte) 0);
        }
        if (iBaseResponseCallback != null) {
            jfh.b(i2, iBaseResponseCallback);
        }
        deviceCommand.setDataLen(allocate.array().length);
        deviceCommand.setDataContent(allocate.array());
        jfq.c().b(deviceCommand);
    }

    public static void b(byte[] bArr, String str, int i, int i2) {
        ReleaseLogUtil.e("DEVMGR_SwitchSendCommandUtil", "sendSetSwitchStatusCmd2 serviceId = ", Integer.valueOf(i), ",commandId = ", Integer.valueOf(i2));
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(i);
        deviceCommand.setCommandID(i2);
        deviceCommand.setDataLen(bArr.length);
        deviceCommand.setDataContent(bArr);
        if (!TextUtils.isEmpty(str)) {
            deviceCommand.setmIdentify(str);
        }
        jfq.c().b(deviceCommand);
    }

    public static void a(int i, String str, String str2) {
        try {
            int parseInt = Integer.parseInt(str);
            StringBuffer stringBuffer = new StringBuffer(16);
            stringBuffer.append(cvx.e(1) + cvx.e(4) + cvx.b(i)).append(cvx.e(2) + cvx.e(1) + cvx.e(parseInt));
            b(cvx.a(stringBuffer.toString()), str2, 1, 57);
        } catch (NumberFormatException unused) {
            ReleaseLogUtil.c("DEVMGR_SwitchSendCommandUtil", "sendSettingSwitchCommand NumberFormatException");
        }
    }

    public static void e(String str, String str2, long j, String str3) {
        DeviceInfo d = jpt.d("SwitchSendCommandUtil");
        if (d == null || TextUtils.isEmpty(str3)) {
            ReleaseLogUtil.d("DEVMGR_SwitchSendCommandUtil", "sendSettingDeviceCommand deviceInfo is null or switchStatus isEmpty");
            return;
        }
        if (d.getDeviceConnectState() != 2) {
            ReleaseLogUtil.d("DEVMGR_SwitchSendCommandUtil", "sendSettingDeviceCommand device is not connected");
            return;
        }
        ReleaseLogUtil.e("DEVMGR_SwitchSendCommandUtil", "sendSettingDeviceCommand switchStatus = ", str3);
        cvq cvqVar = new cvq();
        cvqVar.setSrcPkgName(str);
        cvqVar.setWearPkgName(str2);
        cvqVar.setCommandId(1);
        cvn cvnVar = new cvn();
        cvnVar.e(j);
        cvnVar.c(cvx.a(cvx.e(1) + cvx.e(str3.length()) + cvx.e("1".equals(str3) ? 1 : 0)));
        cvnVar.d(1);
        ArrayList arrayList = new ArrayList(5);
        arrayList.add(cvnVar);
        cvqVar.setConfigInfoList(arrayList);
        ReleaseLogUtil.e("DEVMGR_SwitchSendCommandUtil", "sendSettingDeviceCommand sampleConfig = ", cvqVar.toString());
        cuk.b().sendSampleConfigCommand(d, cvqVar, "SwitchSendCommandUtil");
    }
}
