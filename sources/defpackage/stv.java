package defpackage;

import android.os.Process;
import androidx.exifinterface.media.ExifInterface;
import com.huawei.operation.utils.Constants;

/* loaded from: classes7.dex */
public class stv {
    private static int c = 2;

    public static boolean c(int i) {
        return i >= c;
    }

    public static void b(String str, String str2) {
        if (c(4)) {
            sto.e().e(new stp(ExifInterface.LONGITUDE_EAST, str + Constants.LEFT_BRACKET_ONLY + Process.myTid() + Constants.RIGHT_BRACKET_ONLY, str2));
        }
    }
}
