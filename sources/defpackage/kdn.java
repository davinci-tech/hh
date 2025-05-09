package defpackage;

import android.text.TextUtils;
import com.huawei.datatype.DeviceCommand;
import defpackage.gmu;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import health.compact.a.util.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes5.dex */
public class kdn {
    public static String a(gmu gmuVar) {
        if (gmuVar == null) {
            ReleaseLogUtil.d("DEVMGR_SosUtils", "buildEmergencyInfoCommand emergencyInfo is null");
            sqo.aj("SosUtils buildEmergencyInfoCommand: emergencyInfo is null");
            return "";
        }
        return c(gmuVar) + e(gmuVar);
    }

    public static String b() {
        return cvx.e(1) + cvx.e(0) + cvx.e(2) + cvx.e(0) + cvx.e(3) + cvx.e(0);
    }

    public static DeviceCommand e(String str) {
        if (TextUtils.isEmpty(str)) {
            ReleaseLogUtil.d("DEVMGR_SosUtils", "getDeviceCommand commandHex is null or empty string");
            sqo.aj("SosUtils getDeviceCommand: commandHex is null or empty");
            return null;
        }
        LogUtil.d("SosUtils", "getDeviceCommand commanHex:" + str);
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(51);
        deviceCommand.setCommandID(1);
        deviceCommand.setDataContent(cvx.a(str));
        deviceCommand.setDataLen(cvx.a(str).length);
        return deviceCommand;
    }

    public static DeviceCommand d(String str) {
        if (TextUtils.isEmpty(str)) {
            ReleaseLogUtil.d("DEVMGR_SosUtils", "getImageInfoCommand commandHex is null or empty string");
            sqo.aj("SosUtils getImageInfoCommand: commandHex is null or empty");
            return null;
        }
        DeviceCommand deviceCommand = new DeviceCommand();
        LogUtil.d("SosUtils", "getImageInfoCommand commandHex:", str);
        deviceCommand.setServiceID(51);
        deviceCommand.setCommandID(4);
        deviceCommand.setDataContent(cvx.a(str));
        deviceCommand.setDataLen(cvx.a(str).length);
        return deviceCommand;
    }

    public static String e(gmu gmuVar) {
        StringBuilder sb = new StringBuilder();
        if (gmuVar == null) {
            ReleaseLogUtil.d("DEVMGR_SosUtils", "buildUpContactInfo emergencyInfo is null");
            sqo.aj("SosUtils buildUpContactInfo: emergencyInfo is null");
            return "";
        }
        ArrayList<gmu.e> d = gmuVar.d();
        if (d != null && d.size() > 0) {
            Iterator<gmu.e> it = d.iterator();
            while (it.hasNext()) {
                gmu.e next = it.next();
                if (next != null) {
                    String str = cvx.e(10) + cvx.e(1) + cvx.e(next.e());
                    String c = cvx.c(next.c() == null ? "" : next.c());
                    String str2 = cvx.e(11) + cvx.d(c.length() / 2) + c;
                    String c2 = cvx.c(next.d() == null ? "" : next.d());
                    String str3 = cvx.e(12) + cvx.e(c2.length() / 2) + c2;
                    String d2 = TextUtils.isEmpty(cvx.d(next.b())) ? "" : cvx.d(next.b());
                    String str4 = str + str2 + str3 + (cvx.e(13) + cvx.d(d2.length() / 2) + d2);
                    sb.append(cvx.e(137) + cvx.d(str4.length() / 2) + str4);
                }
            }
            String str5 = cvx.e(136) + cvx.d(sb.length() / 2);
            if (sb.length() > 0) {
                sb.insert(0, str5);
            }
        } else {
            sb.append(cvx.e(136) + cvx.e(0));
        }
        return sb.toString();
    }

    public static String c(gmu gmuVar) {
        if (gmuVar == null) {
            ReleaseLogUtil.d("DEVMGR_SosUtils", "buildUpEmergencyInfo emergencyInfo is null");
            sqo.aj("SosUtils buildUpEmergencyInfo: emergencyInfo is null");
            return "";
        }
        return a(1, gmuVar.h()) + a(2, gmuVar.f()) + c(3, gmuVar.e()) + a(4, gmuVar.b()) + a(5, gmuVar.a()) + b(6, gmuVar.g()) + a(7, gmuVar.c());
    }

    public static String a(int i, String str) {
        if (TextUtils.isEmpty(str)) {
            return cvx.e(i) + cvx.e(0);
        }
        String c = cvx.c(str);
        return cvx.e(i) + cvx.d(c.length() / 2) + c;
    }

    public static String c(int i, int i2) {
        if (i2 >= 0 && i2 <= 9) {
            return cvx.e(i) + cvx.e(1) + cvx.e(i2);
        }
        ReleaseLogUtil.d("DEVMGR_SosUtils", "buildUpBloodTypeHex bloodType value is invalid");
        return "";
    }

    public static String b(int i, int i2) {
        if (i2 >= 0 && i2 <= 2) {
            return cvx.e(i) + cvx.e(1) + cvx.e(i2);
        }
        ReleaseLogUtil.d("DEVMGR_SosUtils", "buildUpOrganDonorHex organDonor value is invalid");
        return "";
    }
}
