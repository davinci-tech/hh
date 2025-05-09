package defpackage;

import com.huawei.datatype.DeviceCommand;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.unitedevice.hwcommonfilemgr.entity.RequestFileInfo;
import health.compact.a.CommonUtil;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class jyv {
    public static DeviceCommand b(jys jysVar) {
        StringBuilder sb = new StringBuilder(a(jysVar.n(), jysVar.q(), jysVar.s()));
        String c = cvx.c(jysVar.ab());
        String str = cvx.e(8) + cvx.d(c.length() / 2) + c;
        String c2 = cvx.c(jysVar.i());
        String str2 = cvx.e(9) + cvx.d(c2.length() / 2) + c2;
        String c3 = cvx.c(jysVar.b());
        String str3 = cvx.e(10) + cvx.d(c3.length() / 2) + c3;
        String c4 = cvx.c(jysVar.y());
        String str4 = cvx.e(11) + cvx.d(c4.length() / 2) + c4;
        String c5 = cvx.c(jysVar.f());
        String str5 = cvx.e(12) + cvx.d(c5.length() / 2) + c5;
        sb.append(str);
        sb.append(str2);
        sb.append(str3);
        sb.append(str4);
        sb.append(str5);
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(40);
        deviceCommand.setCommandID(2);
        deviceCommand.setDataContent(cvx.a(sb.toString()));
        deviceCommand.setDataLen(cvx.a(sb.toString()).length);
        deviceCommand.setNeedAck(true);
        LogUtil.a("CommonFile_CommandUtils", "getStartSendCommand deviceCommand:", deviceCommand.toString());
        return deviceCommand;
    }

    public static String a(String str, long j, int i) {
        String c = cvx.c(str);
        return (cvx.e(1) + cvx.d(c.length() / 2) + c) + (cvx.e(2) + cvx.e(4) + cvx.b(j)) + (cvx.e(3) + cvx.e(1) + cvx.e(i));
    }

    public static int e(int i, String str, jys jysVar) {
        LogUtil.a("CommonFile_CommandUtils", "handleAppPackageNameTlv type: ", Integer.valueOf(i));
        if (i == 8) {
            jysVar.i(cvx.e(str));
        } else if (i == 9) {
            jysVar.b(cvx.e(str));
        } else if (i == 11) {
            jysVar.j(cvx.e(str));
        } else if (i == 12) {
            jysVar.c(cvx.e(str));
        } else if (i == 127) {
            return CommonUtil.w(str);
        }
        return 0;
    }

    public static void b(int i, String str, jys jysVar) {
        if (i == 1) {
            String e = cvx.e(str);
            jysVar.e(e);
            LogUtil.a("CommonFile_CommandUtils", "handleAppFileMessageTlv file_name :", e);
        } else if (i == 3) {
            int w = CommonUtil.w(str);
            jysVar.h(w);
            LogUtil.a("CommonFile_CommandUtils", "handleAppFileMessageTlv file_type :", Integer.valueOf(w));
        } else {
            if (i == 4) {
                int w2 = CommonUtil.w(str);
                jysVar.b(w2);
                LogUtil.a("CommonFile_CommandUtils", "handleAppFileMessageTlv file_id :", Integer.valueOf(w2));
                return;
            }
            LogUtil.h("CommonFile_CommandUtils", "handleAppFileMessageTlv default");
        }
    }

    public static int c(int i, jys jysVar, String str) {
        if (i == 127) {
            int w = CommonUtil.w(str);
            LogUtil.a("CommonFile_CommandUtils", "handleRequestPackageNameTlv error code:", Integer.valueOf(w));
            return w;
        }
        switch (i) {
            case 7:
                String e = cvx.e(str);
                jysVar.i(e);
                LogUtil.a("CommonFile_CommandUtils", "handleRequestPackageNameTlv source:", e);
                return 0;
            case 8:
                String e2 = cvx.e(str);
                jysVar.b(e2);
                LogUtil.a("CommonFile_CommandUtils", "handleRequestPackageNameTlv destination:", e2);
                return 0;
            case 9:
                String e3 = cvx.e(str);
                jysVar.d(e3);
                LogUtil.a("CommonFile_CommandUtils", "handleRequestPackageNameTlv description:", e3);
                return 0;
            case 10:
                String e4 = cvx.e(str);
                jysVar.j(e4);
                LogUtil.a("CommonFile_CommandUtils", "handleRequestPackageNameTlv sourceCertificate:", e4);
                return 0;
            case 11:
                String e5 = cvx.e(str);
                jysVar.c(e5);
                LogUtil.a("CommonFile_CommandUtils", "handleRequestPackageNameTlv destinationCertificate:", e5);
                return 0;
            default:
                LogUtil.h("CommonFile_CommandUtils", "handleRequestPackageNameTlv default");
                return 0;
        }
    }

    public static void e(int i, jys jysVar, String str) {
        if (i == 1) {
            String e = cvx.e(str);
            jysVar.e(e);
            LogUtil.a("CommonFile_CommandUtils", "handleRequestFileMessageTlv file_name:", e);
            return;
        }
        if (i == 2) {
            int w = CommonUtil.w(str);
            jysVar.h(w);
            LogUtil.a("CommonFile_CommandUtils", "handleRequestFileMessageTlv file_type:", Integer.valueOf(w));
        } else if (i == 3) {
            int w2 = CommonUtil.w(str);
            jysVar.b(w2);
            LogUtil.a("CommonFile_CommandUtils", "handleRequestFileMessageTlv file_id:", Integer.valueOf(w2));
        } else {
            if (i == 4) {
                int w3 = CommonUtil.w(str);
                jysVar.g(w3);
                LogUtil.a("CommonFile_CommandUtils", "handleRequestFileMessageTlv file_size:", Integer.valueOf(w3));
                return;
            }
            LogUtil.h("CommonFile_CommandUtils", "handleRequestFileMessageTlv default");
        }
    }

    public static DeviceCommand e(jys jysVar) {
        StringBuilder sb = new StringBuilder(16);
        String c = cvx.c(jysVar.n());
        sb.append(cvx.e(1) + cvx.e(c.length() / 2) + c);
        sb.append(cvx.e(2) + cvx.e(1) + cvx.e(jysVar.s()));
        String c2 = cvx.c(jysVar.ab());
        String str = cvx.e(7) + cvx.e(c2.length() / 2) + c2;
        sb.append(str);
        String c3 = cvx.c(jysVar.ab());
        String str2 = cvx.e(8) + cvx.e(c3.length() / 2) + c3;
        String c4 = cvx.c(jysVar.y());
        String str3 = cvx.e(10) + cvx.e(c4.length() / 2) + c4;
        String c5 = cvx.c(jysVar.y());
        String str4 = cvx.e(11) + cvx.e(c5.length() / 2) + c5;
        sb.append(str);
        sb.append(str2);
        sb.append(str3);
        sb.append(str4);
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(44);
        deviceCommand.setCommandID(1);
        deviceCommand.setDataContent(cvx.a(sb.toString()));
        deviceCommand.setDataLen(cvx.a(sb.toString()).length);
        deviceCommand.setNeedAck(true);
        LogUtil.a("CommonFile_CommandUtils", "sendCommonFileInfo,deviceCommand:", deviceCommand.toString());
        return deviceCommand;
    }

    public static DeviceInfo a() {
        LogUtil.a("CommonFile_CommandUtils", "getMainDevice entry !");
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "CommonFile_CommandUtils");
        if (deviceList.size() > 0) {
            return deviceList.get(0);
        }
        LogUtil.a("CommonFile_CommandUtils", "getMainDevice is error !");
        return null;
    }

    public static String e() {
        LogUtil.a("CommonFile_CommandUtils", "getMainDeviceMac entry !");
        DeviceInfo a2 = a();
        return a2 != null ? a2.getDeviceIdentify() : "";
    }

    public static String b() {
        LogUtil.a("CommonFile_CommandUtils", "getAw70DeviceMac entry !");
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_AW70_DEVICES, null, "CommonFile_CommandUtils");
        String str = "";
        if (deviceList.size() == 1) {
            Iterator<DeviceInfo> it = deviceList.iterator();
            while (it.hasNext()) {
                str = it.next().getDeviceIdentify();
            }
        } else {
            LogUtil.a("CommonFile_CommandUtils", "getAw70DeviceMac is error !");
        }
        return str;
    }

    public static RequestFileInfo b(DeviceInfo deviceInfo, jys jysVar) {
        RequestFileInfo requestFileInfo = new RequestFileInfo();
        requestFileInfo.setFileName(jysVar.n());
        requestFileInfo.setFileType(jysVar.s());
        requestFileInfo.setFileId(jysVar.h());
        requestFileInfo.setFileSize(jysVar.q());
        requestFileInfo.setDescription(jysVar.b());
        requestFileInfo.setSourcePackageName(jysVar.ab());
        requestFileInfo.setDestinationPackageName(jysVar.i());
        requestFileInfo.setSourceCertificate(jysVar.y());
        requestFileInfo.setDestinationCertificate(jysVar.f());
        requestFileInfo.setCancelTransmission(jysVar.ag());
        requestFileInfo.setNeedVerify(jysVar.al());
        requestFileInfo.setTimes(new int[]{jysVar.z(), jysVar.g()});
        requestFileInfo.setKit(false);
        requestFileInfo.setDeviceReport(true);
        if (deviceInfo != null) {
            requestFileInfo.setDeviceMac(deviceInfo.getDeviceIdentify());
        }
        return requestFileInfo;
    }
}
