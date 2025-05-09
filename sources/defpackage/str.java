package defpackage;

import android.os.Process;
import com.huawei.operation.utils.Constants;

/* loaded from: classes7.dex */
public class str {
    public static void e(String str, String str2) {
        if (stv.c(2)) {
            sto.e().e(new stp("I", str + Constants.LEFT_BRACKET_ONLY + Process.myTid() + Constants.RIGHT_BRACKET_ONLY, str2));
        }
    }

    public static void a(String str, String str2) {
        stv.b(str, str2);
    }
}
