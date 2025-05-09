package defpackage;

import android.content.Context;
import com.huawei.haf.threadpool.ThreadPoolManager;
import health.compact.a.LogUtil;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes7.dex */
public class spe {
    private static String b = "";

    public static String b() {
        SecureRandom f = f();
        if (f == null) {
            return "";
        }
        int[] iArr = new int[3];
        StringBuilder sb = new StringBuilder(16);
        for (int i = 0; i < 3; i++) {
            int nextInt = f.nextInt(10);
            iArr[i] = nextInt;
            sb.append(nextInt);
        }
        LogUtil.a("WifiP2pTransferUtil", "getNetWorkName randomNetworkName is: ", sb.toString());
        return "DIRECT-" + sb.toString();
    }

    private static SecureRandom f() {
        try {
            return SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
            LogUtil.e("WifiP2pTransferUtil", "getRandom exception : ", bll.a(e));
            return null;
        }
    }

    public static String c() {
        ArrayList arrayList = new ArrayList(8);
        arrayList.add(Character.valueOf(e()));
        arrayList.add(Character.valueOf(g()));
        arrayList.add(Character.valueOf(d()));
        arrayList.add(Character.valueOf(j()));
        SecureRandom f = f();
        if (f == null) {
            return "";
        }
        for (int i = 4; i < 8; i++) {
            arrayList.add(Character.valueOf(d(f.nextInt(4))));
        }
        Collections.shuffle(arrayList);
        StringBuilder sb = new StringBuilder(arrayList.size());
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            sb.append((Character) it.next());
        }
        return sb.toString();
    }

    private static char e(String str) {
        SecureRandom f = f();
        if (f != null) {
            return str.charAt(f.nextInt(str.length()));
        }
        return str.charAt(0);
    }

    private static char e() {
        return e("abcdefghijklmnopqrstuvwxyz");
    }

    private static char g() {
        return Character.toUpperCase(e());
    }

    private static char d() {
        return e("0123456789");
    }

    private static char j() {
        return e("~!@#$%^&*()_+/-=[]{};:'<>?.");
    }

    private static char d(int i) {
        if (i == 0) {
            return e();
        }
        if (i == 1) {
            return g();
        }
        if (i == 2) {
            return d();
        }
        return j();
    }

    public static bmj e(byte[] bArr) {
        String d = blq.d(bArr);
        if (d == null) {
            LogUtil.a("WifiP2pTransferUtil", "parseDataToTlvFormat dataStrInfo is null");
            return null;
        }
        try {
            return new bmn().c(d.substring(6));
        } catch (bmk unused) {
            LogUtil.c("WifiP2pTransferUtil", "tlv resolve exception.");
            return null;
        }
    }

    public static int a() {
        return bky.a() ? 0 : 1;
    }

    public static String b(final Context context) {
        if (context == null) {
            LogUtil.a("WifiP2pTransferUtil", "error, context is null. please check.");
            return b;
        }
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        try {
            ThreadPoolManager.d().execute(new Runnable() { // from class: spe.4
                @Override // java.lang.Runnable
                public void run() {
                    String unused = spe.b = snu.e().getCommonCountryCode(context);
                    countDownLatch.countDown();
                }
            });
            countDownLatch.await();
            LogUtil.c("WifiP2pTransferUtil", "getCountryCode is: ", b);
        } catch (InterruptedException e) {
            LogUtil.c("WifiP2pTransferUtil", "getCountryCode exception: ", bll.a(e));
        }
        return b;
    }
}
