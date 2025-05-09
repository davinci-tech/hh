package defpackage;

import com.huawei.datatype.DeviceCommand;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.wearengine.notify.NotificationParcel;
import health.compact.a.utils.StringUtils;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes7.dex */
public class trb {
    private static Map<Integer, Integer> b;

    static {
        HashMap hashMap = new HashMap();
        b = hashMap;
        hashMap.put(255, 5);
    }

    public static DeviceCommand d(NotificationParcel notificationParcel, String str) {
        DeviceCommand deviceCommand = new DeviceCommand();
        byte[] a2 = cvx.a(c(notificationParcel, str));
        deviceCommand.setServiceID(53);
        deviceCommand.setCommandID(3);
        deviceCommand.setDataLength(a2.length);
        deviceCommand.setDataContent(a2);
        return deviceCommand;
    }

    private static String c(NotificationParcel notificationParcel, String str) {
        String e;
        StringBuilder sb = new StringBuilder();
        String e2 = cvx.e(1);
        String e3 = cvx.e(notificationParcel.getTemplateId());
        String d = cvx.d(e3.length() / 2);
        sb.append(e2);
        sb.append(d);
        sb.append(e3);
        LogUtil.c("WearEngine_NotificationUtil", "getCommandTlv template part is:", sb.toString());
        String e4 = cvx.e(2);
        String c = cvx.c(notificationParcel.getPackageName());
        String d2 = cvx.d(c.length() / 2);
        sb.append(e4);
        sb.append(d2);
        sb.append(c);
        LogUtil.c("WearEngine_NotificationUtil", "getCommandTlv packageName part is:", sb.toString());
        String title = notificationParcel.getTitle();
        if (!StringUtils.g(title)) {
            String e5 = cvx.e(3);
            String c2 = cvx.c(title);
            String d3 = cvx.d(c2.length() / 2);
            sb.append(e5);
            sb.append(d3);
            sb.append(c2);
            LogUtil.c("WearEngine_NotificationUtil", "getCommandTlv title part is:", sb.toString());
        }
        String e6 = cvx.e(4);
        String c3 = cvx.c(str);
        String d4 = cvx.d(c3.length() / 2);
        sb.append(e6);
        sb.append(d4);
        sb.append(c3);
        LogUtil.c("WearEngine_NotificationUtil", "getCommandTlv id part is:", sb.toString());
        String text = notificationParcel.getText();
        if (!StringUtils.g(text)) {
            String e7 = cvx.e(5);
            String c4 = cvx.c(text);
            String d5 = cvx.d(c4.length() / 2);
            sb.append(e7);
            sb.append(d5);
            sb.append(c4);
            LogUtil.c("WearEngine_NotificationUtil", "getCommandTlv text part is:", sb.toString());
        }
        b(notificationParcel, sb);
        if (notificationParcel.getVibration() == 1) {
            e = cvx.e(1);
        } else {
            e = cvx.e(2);
        }
        String e8 = cvx.e(10);
        String d6 = cvx.d(e.length() / 2);
        sb.append(e8);
        sb.append(d6);
        sb.append(e);
        LogUtil.c("WearEngine_NotificationUtil", "getCommandTlv vibration part is:", sb.toString());
        a(notificationParcel, sb);
        return sb.toString();
    }

    private static void b(NotificationParcel notificationParcel, StringBuilder sb) {
        HashMap<Integer, String> buttonContents = notificationParcel.getButtonContents();
        String str = buttonContents == null ? null : buttonContents.get(2);
        String str2 = buttonContents == null ? null : buttonContents.get(3);
        String str3 = buttonContents != null ? buttonContents.get(4) : null;
        if (!StringUtils.g(str)) {
            String e = cvx.e(6);
            String c = cvx.c(str);
            String d = cvx.d(c.length() / 2);
            sb.append(e);
            sb.append(d);
            sb.append(c);
        }
        if (!StringUtils.g(str2)) {
            String e2 = cvx.e(7);
            String c2 = cvx.c(str2);
            String d2 = cvx.d(c2.length() / 2);
            sb.append(e2);
            sb.append(d2);
            sb.append(c2);
        }
        if (!StringUtils.g(str3)) {
            String e3 = cvx.e(8);
            String c3 = cvx.c(str3);
            String d3 = cvx.d(c3.length() / 2);
            sb.append(e3);
            sb.append(d3);
            sb.append(c3);
        }
        LogUtil.c("WearEngine_NotificationUtil", "getCommandTlv buttonContents part is:", sb.toString());
    }

    private static void a(NotificationParcel notificationParcel, StringBuilder sb) {
        int ringtoneId = notificationParcel.getRingtoneId();
        String e = cvx.e(11);
        if (ringtoneId == -1) {
            String e2 = cvx.e(2);
            String d = cvx.d(e2.length() / 2);
            sb.append(e);
            sb.append(d);
            sb.append(e2);
        } else {
            String e3 = cvx.e(1);
            String d2 = cvx.d(e3.length() / 2);
            sb.append(e);
            sb.append(d2);
            sb.append(e3);
            String e4 = cvx.e(12);
            String e5 = cvx.e(ringtoneId);
            String d3 = cvx.d(e5.length() / 2);
            sb.append(e4);
            sb.append(d3);
            sb.append(e5);
        }
        LogUtil.c("WearEngine_NotificationUtil", "getCommandTlv is:", sb.toString());
    }

    public static int d(int i) {
        return b.get(Integer.valueOf(i)).intValue();
    }

    public static boolean b(int i) {
        return b.containsKey(Integer.valueOf(i));
    }
}
