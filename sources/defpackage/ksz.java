package defpackage;

import android.text.TextUtils;
import com.huawei.hms.framework.common.EmuiUtil;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;

/* loaded from: classes5.dex */
class ksz {
    public static Class<?> a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (!EmuiUtil.BUILDEX_VERSION.equals(str) && !EmuiUtil.IMMERSION_STYLE.equals(str)) {
            return null;
        }
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException unused) {
            ksy.d("ReflectionUtils", "className not found:", true);
            return null;
        }
    }

    public static Object d(String str, String str2) {
        Class<?> a2 = a(str);
        if (a2 != null && !TextUtils.isEmpty(str2) && EmuiUtil.BUILDEX_VERSION.equals(str) && EmuiUtil.EMUI_SDK_INT.equals(str2)) {
            try {
                Field declaredField = a2.getDeclaredField(str2);
                AccessibleObject.setAccessible(new Field[]{declaredField}, true);
                return declaredField.get(a2);
            } catch (IllegalAccessException unused) {
                ksy.d("ReflectionUtils", "Exception in getFieldObj :: IllegalAccessException", true);
            } catch (IllegalArgumentException unused2) {
                ksy.d("ReflectionUtils", "Exception in getFieldObj :: IllegalArgumentException", true);
            } catch (NoSuchFieldException unused3) {
                ksy.d("ReflectionUtils", "Exception in getFieldObj :: NoSuchFieldException", true);
            } catch (SecurityException unused4) {
                ksy.b("ReflectionUtils", "not security int method getStaticFieldObj", true);
            }
        }
        return null;
    }
}
