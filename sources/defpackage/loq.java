package defpackage;

import android.util.Log;
import com.huawei.operation.utils.Constants;
import java.util.Arrays;

/* loaded from: classes5.dex */
public class loq {
    public static final Boolean b = false;

    public static int d(String str, String str2) {
        return c(str, str2, false);
    }

    public static int c(String str, String str2, boolean z) {
        return Log.d("HwMultiSIM", b(str, str2, z));
    }

    public static int a(String str, String str2) {
        if (b.booleanValue()) {
            return c(str, str2);
        }
        return 0;
    }

    public static int c(String str, String str2) {
        return e(str, str2, false);
    }

    public static int b(String str, String str2, String str3) {
        if (b.booleanValue()) {
            return c(str, str2 + str3);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str2);
        sb.append(str3 != null ? Integer.valueOf(str3.length()) : Constants.NULL);
        return c(str, sb.toString());
    }

    public static int e(String str, String str2) {
        return a(str, str2);
    }

    public static int e(String str, String str2, boolean z) {
        return Log.i("HwMultiSIM", b(str, str2, z));
    }

    public static int g(String str, String str2) {
        return a(str, str2, false);
    }

    public static int a(String str, String str2, boolean z) {
        return Log.w("HwMultiSIM", b(str, str2, z));
    }

    public static int b(String str, String str2) {
        return d(str, str2, false);
    }

    public static int e(String str, String str2, Exception exc) {
        if (b.booleanValue()) {
            return b(str, str2 + Arrays.toString(exc.getStackTrace()));
        }
        return b(str, str2 + exc.getMessage());
    }

    public static int d(String str, String str2, boolean z) {
        return Log.e("HwMultiSIM", b(str, str2, z));
    }

    private static String b(String str, String str2, boolean z) {
        String str3;
        if (z) {
            str3 = a() + "->";
        } else {
            str3 = "";
        }
        return str + "->" + str3 + str2;
    }

    private static String a() {
        return "thread id = " + Thread.currentThread().getId();
    }

    public static int e(String str, String str2, Object... objArr) {
        return Log.i("HwMultiSIM", b(str, String.format(str2, objArr), false));
    }

    public static int c(String str, String str2, Object... objArr) {
        return Log.w("HwMultiSIM", b(str, String.format(str2, objArr), false));
    }

    public static int d(String str, String str2, Object... objArr) {
        return Log.e("HwMultiSIM", b(str, String.format(str2, objArr), false));
    }
}
