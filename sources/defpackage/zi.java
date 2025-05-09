package defpackage;

import android.app.Application;
import android.content.Context;
import com.huawei.haf.bundle.extension.BundleLoadMode;
import com.huawei.haf.dynamic.DynamicApplicationLoaders;
import defpackage.yi;
import health.compact.a.ReflectionUtils;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;

/* loaded from: classes8.dex */
public class zi {
    public static boolean b(Context context, String str) {
        BundleLoadMode c = zh.a().c();
        if (c == BundleLoadMode.SINGLE_CLASSLOADER) {
            return false;
        }
        for (yi yiVar : yg.a().a(context)) {
            if (yiVar.m() && (c == BundleLoadMode.MULTIPLE_CLASSLOADER || yiVar.s())) {
                if (d(context, str, yiVar)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean d(Context context, String str, yi yiVar) {
        Iterator<yi.c.C0336c> it = yiVar.b().b().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            yi.c.C0336c next = it.next();
            if (next.d().equals(System.mapLibraryName(str))) {
                if (context instanceof Application) {
                    try {
                        System.load(new File(yw.a().b(yiVar), next.d()).getPath());
                        return true;
                    } catch (UnsatisfiedLinkError unused) {
                        return false;
                    }
                }
                ClassLoader e = DynamicApplicationLoaders.e(yiVar.f());
                if (e != null) {
                    return e(e, yiVar.f(), str);
                }
            }
        }
        return false;
    }

    private static boolean e(ClassLoader classLoader, String str, String str2) {
        try {
            Class<?> loadClass = classLoader.loadClass("com.huawei.android.bundlecore.modulelib." + str + "ModuleLibraryLoader");
            ReflectionUtils.b(loadClass, "loadModuleLibrary", String.class).invoke(loadClass.newInstance(), str2);
            return true;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException unused) {
            return false;
        }
    }
}
