package defpackage;

import android.text.TextUtils;
import com.huawei.datatype.DeviceCommand;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import health.compact.a.util.LogUtil;
import java.util.List;

/* loaded from: classes6.dex */
public class nug {
    public static void e(String str, List<nyu> list) {
        if (list == null || list.isEmpty()) {
            LogUtil.c("CommandUtils", "sendUserSettingCommand list is null or empty");
            return;
        }
        jfq c = jfq.c();
        DeviceCommand c2 = c(str, list);
        if (c2 == null || c == null) {
            return;
        }
        c.b(c2);
        LogUtil.d("CommandUtils", "sendUserSettingCommand" + c2.toString());
    }

    private static DeviceCommand c(String str, List<nyu> list) {
        String c = c(list);
        if (TextUtils.isEmpty(c)) {
            LogUtil.c("CommandUtils", "commandHex is null or empty string");
            return null;
        }
        LogUtil.d("CommandUtils", "getDeviceCommand commanHex:" + c);
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setmIdentify(str);
        deviceCommand.setServiceID(1);
        deviceCommand.setCommandID(48);
        deviceCommand.setDataContent(cvx.a(c));
        deviceCommand.setDataLen(cvx.a(c).length);
        return deviceCommand;
    }

    private static String c(List<nyu> list) {
        StringBuilder sb = new StringBuilder(16);
        if (list != null && list.size() > 0) {
            long currentTimeMillis = System.currentTimeMillis();
            for (nyu nyuVar : list) {
                if (nyuVar != null && !TextUtils.isEmpty(nyuVar.d()) && !b(nyuVar.d())) {
                    String c = cvx.c(nyuVar.d());
                    String str = cvx.e(3) + cvx.e(c.length() / 2) + c;
                    String e = cvx.e(nyuVar.g() ? 1 : 0);
                    String str2 = cvx.e(4) + cvx.e(e.length() / 2) + e;
                    String c2 = cvx.c(nyuVar.f());
                    String str3 = cvx.e(5) + cvx.e(c2.length() / 2) + c2;
                    String c3 = cvx.c(String.valueOf(currentTimeMillis));
                    String str4 = str + str2 + str3 + (cvx.e(6) + cvx.e(c3.length() / 2) + c3);
                    sb.append(cvx.e(OldToNewMotionPath.SPORT_TYPE_TENNIS) + cvx.e(str4.length() / 2) + str4);
                }
            }
            String str5 = cvx.e(129) + cvx.d(sb.length() / 2);
            if (sb.length() > 0) {
                sb.insert(0, str5);
            }
        } else {
            sb.append(cvx.e(129) + cvx.e(0));
        }
        return sb.toString();
    }

    private static boolean b(String str) {
        for (String str2 : nuj.b()) {
            if (str2.equals(str)) {
                return true;
            }
        }
        return false;
    }
}
