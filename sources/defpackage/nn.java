package defpackage;

import android.os.Build;
import com.careforeyou.library.utils.CsBtUtil_v11;

/* loaded from: classes2.dex */
public class nn {
    public static boolean e(boolean z) {
        if (z) {
            if (Build.BRAND.equalsIgnoreCase("samsung")) {
                if (!Build.MODEL.contains("SM-G9300")) {
                    Build.MODEL.contains("SM-G9008");
                }
            } else if (!Build.BRAND.equalsIgnoreCase("yanbochuang") || Build.MODEL.equalsIgnoreCase("A106")) {
            }
            return false;
        }
        return true;
    }

    public static CsBtUtil_v11.CONNECT_MODE a() {
        CsBtUtil_v11.CONNECT_MODE connect_mode = CsBtUtil_v11.CONNECT_MODE.FSAC;
        return (Build.BRAND.equalsIgnoreCase("xiaomi") && Build.DEVICE.startsWith("HM")) ? CsBtUtil_v11.CONNECT_MODE.Alway_Conn : connect_mode;
    }
}
