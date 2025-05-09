package defpackage;

import android.content.Context;
import android.text.TextUtils;
import java.lang.reflect.InvocationTargetException;

/* loaded from: classes7.dex */
public class trd {
    public static String d(Context context) {
        try {
            Class<?> cls = Class.forName("com.huawei.wearengine.utills.WearEngineReflectUtil");
            tos.b("AccountUtil", "reflectInvokeGetUserId getUserId");
            Object invoke = cls.getDeclaredMethod("getUserId", Context.class).invoke(cls, context);
            if (invoke instanceof String) {
                return (String) invoke;
            }
            return null;
        } catch (ClassNotFoundException unused) {
            tos.e("AccountUtil", "getUserId ClassNotFoundException");
            return null;
        } catch (IllegalAccessException unused2) {
            tos.e("AccountUtil", "getUserId IllegalAccessException");
            return null;
        } catch (IllegalArgumentException unused3) {
            tos.e("AccountUtil", "getUserId IllegalArgumentException");
            return null;
        } catch (NoSuchMethodException unused4) {
            tos.e("AccountUtil", "getUserId NoSuchMethodException");
            return null;
        } catch (InvocationTargetException unused5) {
            tos.e("AccountUtil", "getUserId InvocationTargetException");
            return null;
        }
    }

    public static Boolean b(Context context) {
        try {
            Class<?> cls = Class.forName("com.huawei.wearengine.utills.WearEngineReflectUtil");
            tos.b("AccountUtil", "reflectInvokeIsAuthorizedInHealth isAuthorizedInHealth");
            Object invoke = cls.getDeclaredMethod("isAuthorizedInHealth", Context.class).invoke(cls, context);
            if (invoke instanceof Boolean) {
                return (Boolean) invoke;
            }
        } catch (ClassNotFoundException unused) {
            tos.e("AccountUtil", "isAuthorizedInHealth ClassNotFoundException");
        } catch (IllegalAccessException unused2) {
            tos.e("AccountUtil", "isAuthorizedInHealth IllegalAccessException");
        } catch (IllegalArgumentException unused3) {
            tos.e("AccountUtil", "isAuthorizedInHealth IllegalArgumentException");
        } catch (NoSuchMethodException unused4) {
            tos.e("AccountUtil", "isAuthorizedInHealth NoSuchMethodException");
        } catch (InvocationTargetException unused5) {
            tos.e("AccountUtil", "isAuthorizedInHealth InvocationTargetException");
        }
        return false;
    }

    public static String c(Context context) {
        try {
            Class<?> cls = Class.forName("com.huawei.wearengine.utills.WearEngineReflectUtil");
            tos.b("AccountUtil", "reflectInvokeGetGrsUrl getGrsUrl");
            Object invoke = cls.getDeclaredMethod("getGrsUrl", Context.class).invoke(cls, context);
            return invoke instanceof String ? (String) invoke : "";
        } catch (ClassNotFoundException unused) {
            tos.e("AccountUtil", "getGrsUrl ClassNotFoundException");
            return "";
        } catch (IllegalAccessException unused2) {
            tos.e("AccountUtil", "getGrsUrl IllegalAccessException");
            return "";
        } catch (IllegalArgumentException unused3) {
            tos.e("AccountUtil", "getGrsUrl IllegalArgumentException");
            return "";
        } catch (NoSuchMethodException unused4) {
            tos.e("AccountUtil", "getGrsUrl NoSuchMethodException");
            return "";
        } catch (InvocationTargetException unused5) {
            tos.e("AccountUtil", "getGrsUrl InvocationTargetException");
            return "";
        }
    }

    public static boolean e(String str) {
        if (!TextUtils.isEmpty(str) && !"0".equals(str)) {
            return true;
        }
        tos.e("AccountUtil", "checkUserId userId is empty! userId = " + str);
        return false;
    }
}
