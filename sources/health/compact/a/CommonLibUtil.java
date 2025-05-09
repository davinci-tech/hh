package health.compact.a;

import android.content.Context;
import android.content.Intent;
import java.util.Locale;

/* loaded from: classes.dex */
public class CommonLibUtil {
    private CommonLibUtil() {
    }

    public static String b(byte[] bArr) {
        if (bArr == null) {
            com.huawei.hwlogsmodel.LogUtil.h("CommonLibUtil", "parseByte2HexStr source null");
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() == 1) {
                hexString = "0" + hexString;
            }
            stringBuffer.append(hexString.toUpperCase(Locale.ROOT));
        }
        return stringBuffer.toString();
    }

    public static void d(Context context) {
        if (context == null) {
            com.huawei.hwlogsmodel.LogUtil.h("CommonLibUtil", "reboot context is null");
            return;
        }
        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        com.huawei.hwlogsmodel.LogUtil.a("CommonLibUtil", "reboot: intent = ", launchIntentForPackage);
        launchIntentForPackage.addFlags(268468224);
        context.startActivity(launchIntentForPackage);
        System.exit(0);
    }
}
