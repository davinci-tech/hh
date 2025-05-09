package defpackage;

import com.huawei.ucd.helper.android.Medal3DLogApi;
import java.lang.ref.WeakReference;

/* loaded from: classes9.dex */
public class njw {
    private static WeakReference<Medal3DLogApi> b = null;
    private static boolean d = true;

    public static void c(String str, String str2) {
        WeakReference<Medal3DLogApi> weakReference = b;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        b.get().medalLogShow("Medal3D_" + str, str2);
    }

    public static void c(WeakReference<Medal3DLogApi> weakReference) {
        b = weakReference;
    }

    public static String b() {
        if (!d) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        try {
            stringBuffer.append(Thread.currentThread().getName());
            stringBuffer.append("-> ");
            stringBuffer.append(Thread.currentThread().getStackTrace()[3].getMethodName());
            stringBuffer.append("()");
            stringBuffer.append(" ");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuffer.toString();
    }
}
