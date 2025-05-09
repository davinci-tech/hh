package defpackage;

import com.huawei.datatype.DeviceCommand;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes5.dex */
public class kdv {
    public static void e(int i, int i2, DeviceInfo deviceInfo) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(32);
        deviceCommand.setCommandID(1);
        String b = cvx.b(i);
        String str = cvx.e(3) + cvx.e(b.length() / 2) + b;
        String b2 = cvx.b(i2);
        String str2 = cvx.e(4) + cvx.e(b2.length() / 2) + b2;
        String str3 = cvx.e(129) + cvx.e((str.length() / 2) + (str2.length() / 2)) + str + str2;
        deviceCommand.setDataLen(cvx.a(str3).length);
        deviceCommand.setDataContent(cvx.a(str3));
        deviceCommand.setNeedAck(true);
        deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        LogUtil.a("StressSendCommandUtil", "getStressRecordFrameListIndex deviceCommand:", cvx.e(deviceCommand.getServiceID()), cvx.e(deviceCommand.getCommandID()), cvx.d(deviceCommand.getDataContent()));
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }

    public static void b(int i, DeviceInfo deviceInfo) {
        ReleaseLogUtil.e("BTSYNC_StressSendCommandUtil", "getStressRecordDetail index:", Integer.valueOf(i));
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(32);
        deviceCommand.setCommandID(2);
        String e = cvx.e(129);
        String d = cvx.d(4);
        String e2 = cvx.e(2);
        String a2 = cvx.a(i);
        String d2 = cvx.d(a2.length() / 2);
        StringBuilder sb = new StringBuilder(16);
        sb.append(e);
        sb.append(d);
        sb.append(e2);
        sb.append(d2);
        sb.append(a2);
        deviceCommand.setDataLen(sb.length() / 2);
        deviceCommand.setDataContent(cvx.a(sb.toString()));
        deviceCommand.setNeedAck(true);
        deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        LogUtil.a("StressSendCommandUtil", "getStressRecordDetail deviceCommand:", cvx.e(deviceCommand.getServiceID()), cvx.e(deviceCommand.getCommandID()), cvx.d(deviceCommand.getDataContent()));
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }

    public static void b(int i, int i2, DeviceInfo deviceInfo) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(32);
        deviceCommand.setCommandID(3);
        String b = cvx.b(i);
        String str = cvx.e(3) + cvx.e(b.length() / 2) + b;
        String b2 = cvx.b(i2);
        String str2 = cvx.e(4) + cvx.e(b2.length() / 2) + b2;
        String str3 = cvx.e(129) + cvx.e((str.length() / 2) + (str2.length() / 2)) + str + str2;
        deviceCommand.setDataLen(cvx.a(str3).length);
        deviceCommand.setDataContent(cvx.a(str3));
        deviceCommand.setNeedAck(true);
        deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        LogUtil.a("StressSendCommandUtil", "getRelaxRecordFrameListIndex deviceCommand:", cvx.e(deviceCommand.getServiceID()), cvx.e(deviceCommand.getCommandID()), cvx.d(deviceCommand.getDataContent()));
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }

    public static void d(int i, DeviceInfo deviceInfo) {
        ReleaseLogUtil.e("BTSYNC_StressSendCommandUtil", "getRelaxRecordDetail index:", Integer.valueOf(i));
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(32);
        deviceCommand.setCommandID(4);
        String e = cvx.e(129);
        String d = cvx.d(4);
        String e2 = cvx.e(2);
        String a2 = cvx.a(i);
        String d2 = cvx.d(a2.length() / 2);
        StringBuilder sb = new StringBuilder(16);
        sb.append(e);
        sb.append(d);
        sb.append(e2);
        sb.append(d2);
        sb.append(a2);
        deviceCommand.setDataLen(sb.length() / 2);
        deviceCommand.setDataContent(cvx.a(sb.toString()));
        deviceCommand.setNeedAck(true);
        deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        LogUtil.a("StressSendCommandUtil", "getRelaxRecordDetail deviceCommand:", cvx.e(deviceCommand.getServiceID()), cvx.e(deviceCommand.getCommandID()), cvx.d(deviceCommand.getDataContent()));
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }
}
