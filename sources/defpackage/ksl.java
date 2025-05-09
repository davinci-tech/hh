package defpackage;

import android.text.TextUtils;
import com.google.flatbuffers.reflection.BaseType;
import com.huawei.watchface.utils.CommonUtils;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class ksl {
    public static String a(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        if (bArr.length == 0) {
            return "";
        }
        char[] cArr = new char[bArr.length * 2];
        char[] cArr2 = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        for (int i = 0; i < bArr.length; i++) {
            byte b = bArr[i];
            int i2 = i * 2;
            cArr[i2] = cArr2[(b & 240) >> 4];
            cArr[i2 + 1] = cArr2[b & BaseType.Obj];
        }
        return new String(cArr);
    }

    public static byte[] b(String str) {
        if (TextUtils.isEmpty(str)) {
            ksy.c(CommonUtils.f11190a, "getUTF8Bytes, str is empty", true);
            return new byte[0];
        }
        try {
            return str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            ksy.c(CommonUtils.f11190a, "getBytes error" + e.getClass().getSimpleName(), true);
            return new byte[0];
        }
    }

    public static boolean c(String str) {
        ksy.b(CommonUtils.f11190a, "the loginUrl is:" + str, false);
        try {
            String host = new URL(str.replaceAll("[\\\\#]", "/")).getHost();
            ArrayList<String> r = ksi.r();
            if (r == null || r.size() == 0 || TextUtils.isEmpty(host) || !r.contains(host)) {
                ksy.b(CommonUtils.f11190a, "host  is not in whitelist", true);
                return false;
            }
            ksy.b(CommonUtils.f11190a, "host is in whitelist", true);
            return true;
        } catch (MalformedURLException unused) {
            ksy.b(CommonUtils.f11190a, "MalformedURLException", true);
            return false;
        }
    }
}
