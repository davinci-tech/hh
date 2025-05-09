package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* loaded from: classes.dex */
public class jdq {
    public static String e(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            LogUtil.h("Md5Utils", "encode null");
            return null;
        }
        try {
            byte[] bArr = new byte[0];
            try {
                bArr = MessageDigest.getInstance("MD5").digest(str.getBytes("UTF-8"));
            } catch (UnsupportedEncodingException unused) {
                LogUtil.b("Md5Utils", "encode UnsupportedEncodingException");
            }
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : bArr) {
                String hexString = Integer.toHexString(b & 255);
                if (hexString.length() == 1) {
                    stringBuffer.append(0);
                }
                stringBuffer.append(hexString);
            }
            return stringBuffer.toString();
        } catch (NoSuchAlgorithmException unused2) {
            LogUtil.b("Md5Utils", "encode NoSuchAlgorithmException");
            return "";
        }
    }
}
