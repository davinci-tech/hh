package defpackage;

import android.text.TextUtils;
import com.huawei.datatype.DeviceCommand;
import com.huawei.health.knit.ui.KnitHealthDetailActivity;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.wearengine.monitor.EnumMonitorType;
import health.compact.a.CommonUtil;
import java.util.List;

/* loaded from: classes7.dex */
public class tqu {
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static DeviceCommand b(String str) {
        char c;
        if (str == null) {
            return null;
        }
        str.hashCode();
        switch (str.hashCode()) {
            case -1943109551:
                if (str.equals("wearStatus")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -615154554:
                if (str.equals("sportStatus")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 440773975:
                if (str.equals("powerStatus")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 628829609:
                if (str.equals(KnitHealthDetailActivity.KEY_SLEEP_STATUS)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1267337158:
                if (str.equals("chargeStatus")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1623401406:
                if (str.equals("userAvailableKbytes")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            DeviceCommand deviceCommand = new DeviceCommand();
            deviceCommand.setServiceID(46);
            deviceCommand.setCommandID(3);
            StringBuilder sb = new StringBuilder(16);
            sb.append(cvx.e(1));
            sb.append(cvx.e(0));
            deviceCommand.setDataLen(sb.length() / 2);
            deviceCommand.setDataContent(cvx.a(sb.toString()));
            deviceCommand.setNeedAck(true);
            return deviceCommand;
        }
        if (c != 1) {
            if (c == 2) {
                DeviceCommand deviceCommand2 = new DeviceCommand();
                deviceCommand2.setServiceID(1);
                deviceCommand2.setCommandID(8);
                StringBuilder sb2 = new StringBuilder(16);
                sb2.append(cvx.e(1));
                sb2.append(cvx.e(0));
                deviceCommand2.setDataLen(sb2.length() / 2);
                deviceCommand2.setDataContent(cvx.a(sb2.toString()));
                return deviceCommand2;
            }
            if (c != 3 && c != 4 && c != 5) {
                LogUtil.b("WearEngine_MonitorMessageUtil", "not supported eventType", str);
                return null;
            }
        }
        int valueByDesc = EnumMonitorType.getValueByDesc(str);
        if (valueByDesc == -1) {
            return null;
        }
        DeviceCommand deviceCommand3 = new DeviceCommand();
        deviceCommand3.setServiceID(53);
        deviceCommand3.setCommandID(2);
        byte[] a2 = cvx.a(b(4, valueByDesc));
        deviceCommand3.setDataLength(a2.length);
        deviceCommand3.setDataContent(a2);
        return deviceCommand3;
    }

    public static DeviceCommand c(int i, String str) {
        DeviceCommand deviceCommand = new DeviceCommand();
        int valueByDesc = EnumMonitorType.getValueByDesc(str);
        if (valueByDesc == -1) {
            return null;
        }
        deviceCommand.setServiceID(53);
        deviceCommand.setCommandID(2);
        byte[] a2 = cvx.a(b(i, valueByDesc));
        deviceCommand.setDataLength(a2.length);
        deviceCommand.setDataContent(a2);
        return deviceCommand;
    }

    private static String b(int i, int i2) {
        StringBuilder sb = new StringBuilder();
        String e = cvx.e(1);
        String e2 = cvx.e(i);
        String e3 = cvx.e(e2.length() / 2);
        sb.append(e);
        sb.append(e3);
        sb.append(e2);
        LogUtil.c("WearEngine_MonitorMessageUtil", "getCommandTlv subCmd is:", sb.toString());
        String e4 = cvx.e(2);
        String e5 = cvx.e(i2);
        String e6 = cvx.e(e5.length() / 2);
        sb.append(e4);
        sb.append(e6);
        sb.append(e5);
        LogUtil.c("WearEngine_MonitorMessageUtil", "getCommandTlv messageBody is:", sb.toString());
        return sb.toString();
    }

    public static int e(byte[] bArr, int i) {
        String d;
        try {
            d = cvx.d(bArr);
            LogUtil.a("WearEngine_MonitorMessageUtil", "Received message is:", d);
        } catch (cwg | NumberFormatException unused) {
            LogUtil.b("WearEngine_MonitorMessageUtil", "parse status result Exception");
        }
        if (!TextUtils.isEmpty(d) && d.length() >= 4) {
            List<cwd> e = new cwl().a(d.substring(4)).e();
            if (e != null && e.size() != 0) {
                for (cwd cwdVar : e) {
                    int w = CommonUtil.w(cwdVar.e());
                    if (w == 1) {
                        i = CommonUtil.w(cwdVar.c());
                        LogUtil.a("WearEngine_MonitorMessageUtil", "original result is:", Integer.valueOf(i));
                    } else if (w == 127) {
                        LogUtil.h("WearEngine_MonitorMessageUtil", "errorCode is: ", Integer.valueOf(CommonUtil.w(cwdVar.c())));
                    } else {
                        LogUtil.h("WearEngine_MonitorMessageUtil", "no support tag");
                    }
                }
                return i;
            }
            LogUtil.h("WearEngine_MonitorMessageUtil", "tlvList is error.");
            return i;
        }
        LogUtil.h("WearEngine_MonitorMessageUtil", "tlvString is error.");
        return i;
    }
}
