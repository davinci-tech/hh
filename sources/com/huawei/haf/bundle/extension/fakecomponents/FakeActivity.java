package com.huawei.haf.bundle.extension.fakecomponents;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import health.compact.a.LogUtil;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public class FakeActivity extends Activity {
    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        int wO_ = wO_(this);
        super.onCreate(bundle);
        wH_(this, wO_);
        if (getIntent() != null) {
            setIntent(null);
        }
        finish();
    }

    private static int wO_(Activity activity) {
        int i = -1;
        if (Build.VERSION.SDK_INT != 26 || activity.getApplicationInfo().targetSdkVersion <= 26 || !wN_(activity) || !wJ_(activity)) {
            return -1;
        }
        try {
            Object wI_ = wI_(activity);
            Field b = b();
            int i2 = b.getInt(wI_);
            if (i2 != -1) {
                try {
                    b.setInt(wI_, -1);
                } catch (IllegalAccessException | NoSuchFieldException e) {
                    e = e;
                    i = i2;
                    LogUtil.e("Bundle_FakeActivity", "releaseFixedOrientation:ex=", LogUtil.a(e));
                    return i;
                }
            }
            return i2;
        } catch (IllegalAccessException e2) {
            e = e2;
        } catch (NoSuchFieldException e3) {
            e = e3;
        }
    }

    private static void wH_(Activity activity, int i) {
        if (i != -1 && Build.VERSION.SDK_INT == 26 && activity.getApplicationInfo().targetSdkVersion > 26 && wN_(activity) && !wJ_(activity)) {
            try {
                Object wI_ = wI_(activity);
                Field b = b();
                if (b.getInt(wI_) == -1) {
                    b.setInt(wI_, i);
                }
            } catch (IllegalAccessException | NoSuchFieldException e) {
                LogUtil.e("Bundle_FakeActivity", "fixedOrientation:ex=", LogUtil.a(e));
            }
        }
    }

    private static boolean wN_(Activity activity) {
        try {
            Class<?> cls = Class.forName("com.android.internal.R$styleable");
            Field declaredField = cls.getDeclaredField("Window");
            boolean z = true;
            declaredField.setAccessible(true);
            Field declaredField2 = cls.getDeclaredField("Window_windowIsTranslucent");
            declaredField2.setAccessible(true);
            Field declaredField3 = cls.getDeclaredField("Window_windowSwipeToDismiss");
            declaredField3.setAccessible(true);
            Field declaredField4 = cls.getDeclaredField("Window_windowIsFloating");
            declaredField4.setAccessible(true);
            Object obj = declaredField.get(null);
            if (obj instanceof int[]) {
                TypedArray obtainStyledAttributes = activity.obtainStyledAttributes((int[]) obj);
                if (!wK_(obtainStyledAttributes, declaredField4) && !wM_(obtainStyledAttributes, declaredField2) && !wL_(obtainStyledAttributes, declaredField3, declaredField2)) {
                    z = false;
                }
                obtainStyledAttributes.recycle();
                return z;
            }
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException e) {
            LogUtil.e("Bundle_FakeActivity", "isTranslucentOrFloating:ex=", LogUtil.a(e));
        }
        return false;
    }

    private static boolean wK_(TypedArray typedArray, Field field) throws IllegalAccessException {
        Object obj = field.get(null);
        if (obj instanceof Integer) {
            return typedArray.getBoolean(((Integer) obj).intValue(), false);
        }
        return false;
    }

    private static boolean wM_(TypedArray typedArray, Field field) throws IllegalAccessException {
        Object obj = field.get(null);
        if (obj instanceof Integer) {
            return typedArray.getBoolean(((Integer) obj).intValue(), false);
        }
        return false;
    }

    private static boolean wL_(TypedArray typedArray, Field field, Field field2) throws IllegalAccessException {
        Object obj = field.get(null);
        boolean z = obj instanceof Integer ? typedArray.getBoolean(((Integer) obj).intValue(), false) : false;
        Object obj2 = field2.get(null);
        return obj2 instanceof Integer ? !typedArray.hasValue(((Integer) obj2).intValue()) && z : z;
    }

    private static boolean wJ_(Activity activity) {
        try {
            Object wI_ = wI_(activity);
            Method declaredMethod = ActivityInfo.class.getDeclaredMethod("isFixedOrientation", new Class[0]);
            declaredMethod.setAccessible(true);
            Object invoke = declaredMethod.invoke(wI_, new Object[0]);
            if (invoke instanceof Boolean) {
                return ((Boolean) invoke).booleanValue();
            }
            return false;
        } catch (IllegalAccessException | NoSuchFieldException | NoSuchMethodException | InvocationTargetException e) {
            LogUtil.e("Bundle_FakeActivity", "isFixedOrientation:ex=", LogUtil.a(e));
            return false;
        }
    }

    private static Object wI_(Activity activity) throws NoSuchFieldException, IllegalAccessException {
        Field declaredField = Activity.class.getDeclaredField("mActivityInfo");
        declaredField.setAccessible(true);
        return declaredField.get(activity);
    }

    private static Field b() throws NoSuchFieldException {
        Field declaredField = ActivityInfo.class.getDeclaredField("screenOrientation");
        declaredField.setAccessible(true);
        return declaredField;
    }
}
