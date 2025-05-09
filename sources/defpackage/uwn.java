package defpackage;

import android.util.Log;
import com.huawei.openalliance.ad.constant.Constants;

/* loaded from: classes7.dex */
public class uwn {
    private static boolean b = false;
    private static final ThreadLocal<StringBuilder> c = new ThreadLocal<StringBuilder>() { // from class: uwn.4
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public StringBuilder initialValue() {
            return new StringBuilder();
        }
    };
    private static boolean d = true;

    static {
        try {
            b = Log.isLoggable("HiChain", 3);
            d = Log.isLoggable("HiChain", 4);
        } catch (IllegalArgumentException unused) {
            e("HiChain", "error:getLogField--IllegalArgumentException");
        }
    }

    public static void a(String str, String str2) {
        if (d) {
            Log.i("HiChain", str + ":" + str2);
        }
    }

    public static void b(String str, Object... objArr) {
        if (d) {
            Log.i("HiChain", str + ":" + d(objArr));
        }
    }

    public static void b(String str, String str2) {
        Log.w("HiChain", str + ":" + str2);
    }

    public static void e(String str, String str2) {
        Log.e("HiChain", str + ":" + str2);
    }

    private static String d(Object... objArr) {
        if (objArr == null || objArr.length == 0) {
            return "NULL";
        }
        StringBuilder d2 = d();
        for (Object obj : objArr) {
            d2.append(obj);
        }
        return d2.toString().replaceAll("[\t" + System.lineSeparator() + "]", Constants.LINK);
    }

    private static StringBuilder d() {
        StringBuilder sb = c.get();
        sb.setLength(0);
        return sb;
    }
}
