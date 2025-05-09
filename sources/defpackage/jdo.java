package defpackage;

import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes5.dex */
public class jdo {
    public static String d(byte b) {
        StringBuffer stringBuffer = new StringBuffer(16);
        stringBuffer.append((int) ((byte) ((b >> 7) & 1)));
        stringBuffer.append((int) ((byte) ((b >> 6) & 1)));
        stringBuffer.append((int) ((byte) ((b >> 5) & 1)));
        stringBuffer.append((int) ((byte) ((b >> 4) & 1)));
        stringBuffer.append((int) ((byte) ((b >> 3) & 1)));
        stringBuffer.append((int) ((byte) ((b >> 2) & 1)));
        stringBuffer.append((int) ((byte) ((b >> 1) & 1)));
        stringBuffer.append((int) ((byte) (b & 1)));
        return stringBuffer.toString();
    }

    public static int b(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        try {
            return Integer.parseInt(str, 2);
        } catch (NumberFormatException unused) {
            LogUtil.b("CommandPackageUtil", "stringToInt NumberFormatException");
            return 0;
        }
    }

    public static int a(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer(16);
        stringBuffer.append(d(bArr[5]));
        stringBuffer.append(d(bArr[6]));
        stringBuffer.append(d(bArr[7]));
        stringBuffer.append(d(bArr[8]));
        return b(stringBuffer.toString());
    }
}
