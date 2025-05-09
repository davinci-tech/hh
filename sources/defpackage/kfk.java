package defpackage;

import android.text.TextUtils;
import com.huawei.datatype.DeviceCommand;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes5.dex */
public class kfk {
    public static void e(int i) {
        StringBuilder sb = new StringBuilder(16);
        String e = cvx.e(i);
        String d = cvx.d(e.length() / 2);
        sb.append(cvx.e(2));
        sb.append(d);
        sb.append(e);
        String e2 = cvx.e(1);
        String d2 = cvx.d(e2.length() / 2);
        sb.append(cvx.e(8));
        sb.append(d2);
        sb.append(e2);
        d(sb.toString(), 1);
    }

    public static void d(String str) {
        StringBuilder sb = new StringBuilder(16);
        String c = cvx.c(str);
        String d = cvx.d(c.length() / 2);
        sb.append(cvx.e(11));
        sb.append(d);
        sb.append(c);
        String e = cvx.e(str.length());
        String d2 = cvx.d(e.length() / 2);
        sb.append(cvx.e(12));
        sb.append(d2);
        sb.append(e);
        d(sb.toString(), 1);
    }

    public static void a(String str, int i) {
        StringBuilder sb = new StringBuilder(16);
        if (!TextUtils.isEmpty(str)) {
            String c = cvx.c(str);
            String d = cvx.d(c.length() / 2);
            sb.append(cvx.e(1));
            sb.append(d);
            sb.append(c);
        }
        if (i != -1) {
            String e = cvx.e(i);
            String d2 = cvx.d(e.length() / 2);
            sb.append(cvx.e(2));
            sb.append(d2);
            sb.append(e);
        }
        d(sb.toString(), 3);
    }

    public static void b(String str, String str2) {
        if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
            LogUtil.h("HwVoiceKitManager_SendToDeviceKitData", "sendDisplayTextAndAsr displayAsr and displayText isEmpty");
            return;
        }
        LogUtil.a("HwVoiceKitManager_SendToDeviceKitData", "sendDisplayTextAndAsr displayAsr:", str, " , displayText:", str2);
        StringBuilder sb = new StringBuilder(16);
        if (!TextUtils.isEmpty(str)) {
            String c = cvx.c(str);
            String d = cvx.d(c.length() / 2);
            sb.append(cvx.e(1));
            sb.append(d);
            sb.append(c);
        }
        if (!TextUtils.isEmpty(str2)) {
            String c2 = cvx.c(str2);
            String d2 = cvx.d(c2.length() / 2);
            sb.append(cvx.e(2));
            sb.append(d2);
            sb.append(c2);
        }
        d(sb.toString(), 4);
    }

    public static void c(int i) {
        StringBuilder sb = new StringBuilder(16);
        String e = cvx.e(200);
        String d = cvx.d(e.length() / 2);
        String e2 = cvx.e(1);
        String e3 = cvx.e(i);
        String d2 = cvx.d(e3.length() / 2);
        String e4 = cvx.e(8);
        sb.append(e2);
        sb.append(d);
        sb.append(e);
        sb.append(e4);
        sb.append(d2);
        sb.append(e3);
        d(sb.toString(), 5);
    }

    public static void a(int i) {
        StringBuilder sb = new StringBuilder(16);
        String e = cvx.e(i);
        String d = cvx.d(e.length() / 2);
        sb.append(cvx.e(1));
        sb.append(d);
        sb.append(e);
        d(sb.toString(), 6);
    }

    public static void b() {
        StringBuilder sb = new StringBuilder(16);
        String e = cvx.e(1);
        String e2 = cvx.e(0);
        sb.append(e);
        sb.append(e2);
        d(sb.toString(), 8);
    }

    public static void c(String str) {
        StringBuilder sb = new StringBuilder(16);
        b(b(str), sb);
        b(a(), sb);
        d(sb.toString(), 8);
    }

    private static String b(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        int c = jds.c(str, 10);
        if (c == -1) {
            LogUtil.h("HwVoiceKitManager_SendToDeviceKitData", "buildMediaTypeTlv Invalid mediaType");
            return "";
        }
        String e = cvx.e(c);
        return cvx.e(2) + cvx.d(e.length() / 2) + e;
    }

    private static String a() {
        return cvx.e(3) + cvx.e(0);
    }

    private static void b(String str, StringBuilder sb) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        sb.append(str);
        LogUtil.a("HwVoiceKitManager_SendToDeviceKitData", "appendTlv tlv:", str);
    }

    public static void d(String str, String str2, String str3) {
        LogUtil.a("HwVoiceKitManager_SendToDeviceKitData", "sendDomain doMainId:", str, ", subDomainId:", str2, ", intentId:", str3);
        StringBuilder sb = new StringBuilder(16);
        String c = cvx.c(str);
        String d = cvx.d(c.length() / 2);
        sb.append(cvx.e(1));
        sb.append(d);
        sb.append(c);
        String c2 = cvx.c(str2);
        String d2 = cvx.d(c2.length() / 2);
        sb.append(cvx.e(2));
        sb.append(d2);
        sb.append(c2);
        String c3 = cvx.c(str3);
        String d3 = cvx.d(c3.length() / 2);
        sb.append(cvx.e(3));
        sb.append(d3);
        sb.append(c3);
        d(sb.toString(), 9);
    }

    public static void d(String str, int i) {
        byte[] a2 = cvx.a(str);
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(49);
        deviceCommand.setCommandID(i);
        deviceCommand.setDataContent(a2);
        deviceCommand.setDataLen(a2.length);
        LogUtil.a("HwVoiceKitManager_SendToDeviceKitData", "sendToDevice commandId is:", Integer.valueOf(i));
        LogUtil.a("HwVoiceKitManager_SendToDeviceKitData", "sendToDevice command:", cvx.d(deviceCommand.getDataContent()));
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }
}
