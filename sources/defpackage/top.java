package defpackage;

import android.text.TextUtils;
import java.io.UnsupportedEncodingException;

/* loaded from: classes7.dex */
public class top {
    public static <T> T a(T t, Object obj) {
        if (t != null) {
            return t;
        }
        tov.d("Preconditions", String.valueOf(obj));
        throw new tnx(5);
    }

    public static void d(String str, int i) {
        try {
            if (!TextUtils.isEmpty(str)) {
                if (str.getBytes("UTF-8").length <= i) {
                    return;
                }
            }
            tov.d("Preconditions", "checkArgumentByteLimit exceeded the maximum length: " + str);
            throw new tnx(5);
        } catch (UnsupportedEncodingException unused) {
            tov.d("Preconditions", "checkArgumentByteLimit UnsupportedEncodingException");
            throw new tnx(5);
        }
    }
}
