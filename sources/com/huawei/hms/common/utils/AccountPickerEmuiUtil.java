package com.huawei.hms.common.utils;

import android.text.TextUtils;
import com.huawei.hms.framework.common.EmuiUtil;
import defpackage.ksy;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;

/* loaded from: classes4.dex */
public class AccountPickerEmuiUtil {

    /* renamed from: a, reason: collision with root package name */
    private static int f4476a = -1;
    private static int b;

    static {
        a();
    }

    private static void a() {
        int c = c();
        b = c;
        if (c >= 17) {
            f4476a = 90;
        }
        if (c >= 11) {
            f4476a = 50;
        } else if (c >= 10) {
            f4476a = 41;
        } else if (c >= 9) {
            f4476a = 40;
        } else if (c >= 8) {
            f4476a = 31;
        } else if (c >= 7) {
            f4476a = 30;
        }
        if (f4476a == -1) {
            b();
        }
    }

    private static void b() {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            String str = (String) cls.getDeclaredMethod("get", String.class).invoke(cls, "ro.build.version.emui");
            if (str != null) {
                if (str.contains("EmotionUI_3.0")) {
                    f4476a = 30;
                } else if (str.contains("EmotionUI_3.1")) {
                    f4476a = 31;
                } else if (str.contains("EmotionUI_4.0")) {
                    f4476a = 40;
                } else if (str.contains("EmotionUI_4.1")) {
                    f4476a = 41;
                } else if (str.contains("EmotionUI_5.0")) {
                    f4476a = 50;
                }
            }
        } catch (RuntimeException unused) {
            ksy.c("AccountPickerEmuiUtil", "RuntimeException getEmuiType.", true);
        } catch (Exception unused2) {
            ksy.c("AccountPickerEmuiUtil", "getEmuiType Exception.", true);
        }
    }

    public static boolean isAboveEMUI100() {
        return b >= 21;
    }

    private static int c() {
        Object b2 = ReflectionUtils.b();
        if (b2 != null) {
            try {
                b = ((Integer) b2).intValue();
            } catch (ClassCastException unused) {
                ksy.c("AccountPickerEmuiUtil", "EMUIVersionCode is not a number", true);
            }
        }
        return b;
    }

    static class ReflectionUtils {
        ReflectionUtils() {
        }

        public static Class<?> getClass(String str) {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            if (!EmuiUtil.BUILDEX_VERSION.equals(str) && !EmuiUtil.IMMERSION_STYLE.equals(str)) {
                return null;
            }
            try {
                return Class.forName(str);
            } catch (ClassNotFoundException unused) {
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static Object b() {
            Class<?> cls = getClass(EmuiUtil.BUILDEX_VERSION);
            if (cls != null && !TextUtils.isEmpty(EmuiUtil.EMUI_SDK_INT)) {
                try {
                    Field declaredField = cls.getDeclaredField(EmuiUtil.EMUI_SDK_INT);
                    AccessibleObject.setAccessible(new Field[]{declaredField}, true);
                    return declaredField.get(cls);
                } catch (IllegalAccessException unused) {
                    ksy.d("ReflectionUtils", "IllegalAccessException", true);
                } catch (IllegalArgumentException unused2) {
                    ksy.d("ReflectionUtils", "IllegalArgumentException", true);
                } catch (NoSuchFieldException unused3) {
                    ksy.d("ReflectionUtils", "NoSuchFieldException", true);
                } catch (SecurityException unused4) {
                    ksy.b("ReflectionUtils", "not security int method", true);
                }
            }
            return null;
        }
    }
}
