package defpackage;

import android.text.TextUtils;
import java.util.concurrent.ExecutorService;

/* loaded from: classes6.dex */
public class mmb {
    public static boolean b(String str) {
        return TextUtils.isEmpty(str);
    }

    public static void d(ExecutorService executorService) {
        if (executorService != null) {
            executorService.shutdown();
        }
    }
}
