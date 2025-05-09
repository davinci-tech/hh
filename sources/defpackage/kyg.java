package defpackage;

import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.WhiteBoxManager;
import java.io.UnsupportedEncodingException;

/* loaded from: classes5.dex */
final class kyg {
    public static String d(String str, String str2) {
        int i;
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("R_OTA_AirActiveUtils", "Invalid param:SN is null");
            return "";
        }
        if (TextUtils.isEmpty(str2)) {
            LogUtil.h("R_OTA_AirActiveUtils", "Invalid param:mac is null");
            return "";
        }
        byte[] b = b();
        if (b == null || b.length == 0) {
            LogUtil.h("R_OTA_AirActiveUtils", "Invalid param:whiteBoxKeyBytes is null");
            return "";
        }
        try {
            byte[] bytes = str.getBytes("utf-8");
            byte[] bytes2 = str2.getBytes("utf-8");
            if (bytes == null || bytes2 == null) {
                LogUtil.h("R_OTA_AirActiveUtils", "Invalid param:originalDataBytes or encryptFactorBytes is null");
                return "";
            }
            byte[] bArr = new byte[bytes.length + 38];
            try {
                i = kyi.e(bytes, b, bytes2, bArr);
            } catch (UnsupportedOperationException unused) {
                LogUtil.b("AirActiveUtils", "getAirActiveInfo UnsupportedOperationException");
                i = -1;
            }
            LogUtil.b("R_OTA_AirActiveUtils", "Invalid param:errorCode is ", Integer.valueOf(i));
            return i == 0 ? cvx.d(bArr) : "";
        } catch (UnsupportedEncodingException unused2) {
            LogUtil.b("R_OTA_AirActiveUtils", "Invalid param:getAirActiveInfo occur UnsupportedEncodingException");
            return "";
        }
    }

    private static byte[] b() {
        WhiteBoxManager d = WhiteBoxManager.d();
        return d.a(cvx.a(d.d(1, 42) + d.d(1, 1042) + d.d(1, 2042)));
    }
}
