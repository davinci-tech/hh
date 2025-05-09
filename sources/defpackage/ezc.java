package defpackage;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.google.flatbuffers.reflection.BaseType;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* loaded from: classes.dex */
public class ezc {
    public static final String d = CommonUtil.ac();

    /* renamed from: a, reason: collision with root package name */
    private static final char[] f12391a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static String c(Context context, String str) {
        if (context == null || str == null) {
            LogUtil.b("DE_AppAuthorityUtil", "context or packageName is null");
            return "";
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 64);
            if (packageInfo.signatures[0] == null) {
                LogUtil.h("DE_AppAuthorityUtil", "getAppSecretString ", "signature is null");
                return "";
            }
            return d(packageInfo.signatures[0].toByteArray());
        } catch (PackageManager.NameNotFoundException e) {
            LogUtil.b("DE_AppAuthorityUtil", "getAppSecretString type =", e.getMessage());
            return "";
        }
    }

    private static String d(byte[] bArr) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(bArr);
            byte[] digest = messageDigest.digest();
            if (digest != null) {
                char[] cArr = new char[digest.length * 2];
                int i = 0;
                for (byte b : digest) {
                    int i2 = i + 1;
                    char[] cArr2 = f12391a;
                    cArr[i] = cArr2[(b >>> 4) & 15];
                    i += 2;
                    cArr[i2] = cArr2[b & BaseType.Obj];
                }
                return new String(cArr);
            }
            LogUtil.a("DE_AppAuthorityUtil", "mdTemp is null or md.length < 0 ");
            return null;
        } catch (NoSuchAlgorithmException e) {
            LogUtil.b("DE_AppAuthorityUtil", "NoSuchAlgorithmException type =", e.getMessage());
            return null;
        }
    }
}
