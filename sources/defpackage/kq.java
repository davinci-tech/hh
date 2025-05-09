package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.alipay.sdk.m.l0.b;
import java.util.Random;

/* loaded from: classes7.dex */
public class kq {
    public static String a() {
        try {
            return (String) Class.forName("com.yunos.baseservice.clouduuid.CloudUUID").getMethod("getCloudUUID", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception unused) {
            return "";
        }
    }

    public static String b() {
        String c = kp.c("ro.aliyun.clouduuid", "");
        if (TextUtils.isEmpty(c)) {
            c = kp.c("ro.sys.aliyun.clouduuid", "");
        }
        return TextUtils.isEmpty(c) ? a() : c;
    }

    public static String e() {
        int currentTimeMillis = (int) (System.currentTimeMillis() / 1000);
        int nanoTime = (int) System.nanoTime();
        int nextInt = new Random().nextInt();
        int nextInt2 = new Random().nextInt();
        byte[] a2 = km.a(currentTimeMillis);
        byte[] a3 = km.a(nanoTime);
        byte[] a4 = km.a(nextInt);
        byte[] a5 = km.a(nextInt2);
        byte[] bArr = new byte[16];
        System.arraycopy(a2, 0, bArr, 0, 4);
        System.arraycopy(a3, 0, bArr, 4, 4);
        System.arraycopy(a4, 0, bArr, 8, 4);
        System.arraycopy(a5, 0, bArr, 12, 4);
        return b.c(bArr, 2);
    }

    public static String a(Context context) {
        String b = ks.e(null) ? b() : null;
        return ks.e(b) ? e() : b;
    }

    public static String b(Context context) {
        return "";
    }
}
