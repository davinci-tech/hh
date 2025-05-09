package defpackage;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.huawei.health.deviceconnect.callback.BtDeviceResponseCallback;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.WhiteBoxManager;
import java.io.UnsupportedEncodingException;
import java.security.InvalidParameterException;
import java.security.NoSuchAlgorithmException;

/* loaded from: classes5.dex */
public class jss {
    public static boolean b() {
        try {
            BaseApplication.getContext().getPackageManager().getApplicationInfo("com.huawei.iconnect", 0);
            LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "DmsUtil", "iconnect pkg exist.");
            PackageInfo packageInfo = BaseApplication.getContext().getPackageManager().getPackageInfo("com.huawei.iconnect", 0);
            if (packageInfo != null) {
                int i = packageInfo.versionCode;
                LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "DmsUtil", "iconnect code:", Integer.valueOf(i));
                if (i > 2) {
                    return true;
                }
            }
            return false;
        } catch (PackageManager.NameNotFoundException unused) {
            LogUtil.b(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "DmsUtil", "iconnect pkg do not exist.");
            return false;
        }
    }

    public static void b(BtDeviceResponseCallback btDeviceResponseCallback) {
        jsz.b().c(btDeviceResponseCallback);
    }

    public static String e(String str) {
        WhiteBoxManager d = WhiteBoxManager.d();
        try {
            byte[] a2 = d.a(cvx.a(d.d(1, 21) + d.d(1, 1021) + d.d(1, 2021)));
            if (a2 != null && a2.length != 0) {
                String str2 = new String(a2, "utf-8");
                byte[] a3 = iyx.a(16);
                return cvx.d(a3) + cvx.d(iyx.c(1, str.getBytes("utf-8"), cvx.a(str2), a3));
            }
            LogUtil.h("DmsUtil", "encryptIconnectData encryptKeyBytes is empty.");
            return "";
        } catch (UnsupportedEncodingException e) {
            LogUtil.b("DmsUtil", "UnsupportedEncodingException msg:", e.getMessage());
            return "";
        } catch (InvalidParameterException e2) {
            LogUtil.b("DmsUtil", "InvalidParameterException msg:", e2.getMessage());
            return "";
        } catch (NoSuchAlgorithmException e3) {
            LogUtil.b("DmsUtil", "NoSuchAlgorithmException msg:", e3.getMessage());
            return "";
        }
    }

    public static String b(byte[] bArr) {
        if (bArr == null || bArr.length < 2) {
            return "";
        }
        return cvx.e(bArr[0]) + cvx.e(bArr[1]);
    }
}
