package defpackage;

import android.graphics.Bitmap;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hms.framework.common.ReflectionUtils;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.HarmonyBuild;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class eow {
    private static final Map<String, Bitmap> b = new HashMap(1);
    private static Boolean c;

    public static boolean a() {
        Boolean bool = c;
        if (bool != null) {
            return bool.booleanValue();
        }
        Object invokeStaticMethod = ReflectionUtils.invokeStaticMethod("com.huawei.media.controller.MediaControlCenter", "isCompatibleForMediaControl", BaseApplication.d());
        if (invokeStaticMethod instanceof Boolean) {
            Boolean bool2 = (Boolean) invokeStaticMethod;
            c = bool2;
            LogUtil.a("AudioUtils", "isInMediaControlWhiteList has result: ", bool2);
        } else {
            c = false;
            LogUtil.b("AudioUtils", "isInMediaControlWhiteList has no result, return false by default, resultObj = ", invokeStaticMethod);
        }
        return c.booleanValue();
    }

    public static boolean c() {
        String str = HarmonyBuild.b;
        return str != null && str.length() > 0 && str.charAt(0) >= '3' && str.charAt(0) <= '9';
    }

    public static boolean d() {
        String str = HarmonyBuild.b;
        return str != null && str.length() > 0 && str.charAt(0) >= '4' && str.charAt(0) <= '9';
    }

    public static boolean b() {
        return d() && a();
    }

    public static Bitmap arN_(String str) {
        synchronized (eow.class) {
            if (TextUtils.isEmpty(str)) {
                ReleaseLogUtil.e("AudioUtils", "queryBitmap failed, url is null");
                return null;
            }
            Map<String, Bitmap> map = b;
            if (map.get(str) == null) {
                map.clear();
                map.put(str, nrf.cHT_(BaseApplication.e(), str));
            }
            return map.get(str);
        }
    }

    public static String c(enq enqVar) {
        return enqVar == null ? "empty audioItem" : enqVar.n();
    }
}
