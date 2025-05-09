package defpackage;

import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import health.compact.a.LogUtil;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Locale;

/* loaded from: classes5.dex */
public class iyg {
    public static boolean e() {
        return d().toLowerCase(Locale.ENGLISH).indexOf("vivo") != -1;
    }

    public static boolean b() {
        return d().toLowerCase(Locale.ENGLISH).indexOf("oppo") != -1;
    }

    public static boolean c() {
        return d().toLowerCase(Locale.ENGLISH).indexOf("samsung") != -1;
    }

    private static String d() {
        return Build.BRAND;
    }

    public static String c(int i, int i2) {
        StringBuffer stringBuffer = new StringBuffer(16);
        stringBuffer.append(String.format(Locale.ROOT, "%02d", Integer.valueOf(i))).append(String.format(Locale.ROOT, "%02d", Integer.valueOf(i2)));
        return stringBuffer.toString();
    }

    public static void a(int i, List<String> list, boolean z) {
        LogUtil.c("BTCommonUtil", "Enter updateDeviceFilterNameList productType: ", Integer.valueOf(i));
        if (list == null) {
            LogUtil.a("BTCommonUtil", "updateDeviceFilterNameList deviceFilterNameList is null.");
            return;
        }
        if (bku.e(i)) {
            LogUtil.a("BTCommonUtil", "updateDeviceFilterNameList productType is aw70");
            return;
        }
        if (i == 66) {
            LogUtil.a("BTCommonUtil", "productType is ProductType.PORSCHE_RS");
            return;
        }
        LogUtil.c("BTCommonUtil", "updateDeviceFilterNameList isBand: ", Boolean.valueOf(z));
        if (z) {
            list.add("BAND");
        } else if (i == 34) {
            list.add("PORSCHE");
            list.add("WATCH");
        } else {
            list.add("WATCH");
        }
        if (bky.h() || bky.e()) {
            LogUtil.c("BTCommonUtil", "updateDeviceFilterNameList storeDemo or beta");
            list.add("HUAWEI");
            list.add("HONOR");
        }
    }

    public static void e(String str) {
        LogUtil.c("BTCommonUtil", "sendInitConnectionMessage:", str);
        Intent intent = new Intent("com.huawei.bone.action.REQUEST_BIND_DEVICE");
        intent.setPackage(BaseApplication.e().getPackageName());
        intent.putExtra("connect_status", "initConnect");
        intent.putExtra("deviceName", str);
        BaseApplication.e().sendBroadcast(intent, bin.d);
    }

    public static String c(String str) {
        LogUtil.c("BTCommonUtil", "Enter decryptMacAddress.");
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("BTCommonUtil", "Enter decryptMacAddress encryptId is null.");
            return "";
        }
        try {
            String str2 = new String(bmo.a(blq.a(bmo.e(1, 21) + bmo.e(1, 1021) + bmo.e(1, 2021))), "utf-8");
            if (str.length() < 32) {
                return "";
            }
            return new String(iyx.a(1, blq.a(str.substring(32, str.length())), blq.a(str2), blq.a(str.substring(0, 32))), "utf-8");
        } catch (UnsupportedEncodingException unused) {
            LogUtil.e("BTCommonUtil", "decryptMacAddress UnsupportedEncodingException.");
            return "";
        } catch (Exception unused2) {
            LogUtil.e("BTCommonUtil", "decryptMacAddress exception.");
            return "";
        }
    }

    public static void a(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.a("BTCommonUtil", "tag or data is empty.");
        } else if (str2.length() > 1000) {
            LogUtil.c(str, "SDK-->Device : ", iyq.d(str2), " dataLen:", Integer.valueOf(str2.length()));
        } else {
            LogUtil.c(str, "SDK-->Device : ", str2);
        }
    }
}
