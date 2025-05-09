package com.huawei.haf.dynamic;

import android.os.Build;
import health.compact.a.LogUtil;
import health.compact.a.ReflectionUtils;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
final class DynamicLibraryLoader {
    private DynamicLibraryLoader() {
    }

    static void c(ClassLoader classLoader, File file) throws DynamicLoaderException {
        try {
            AndroidPlatformV25.c(classLoader, file);
        } catch (IllegalAccessException | NoSuchFieldException | NoSuchMethodException | InvocationTargetException e) {
            try {
                LogUtil.e("HAF_LibraryLoader", "load v25 fail, try to fallback to V23, sdk=", Integer.valueOf(Build.VERSION.SDK_INT), ", ex=", LogUtil.a(e));
                AndroidPlatformV23.b(classLoader, file);
            } catch (IllegalAccessException | NoSuchFieldException | NoSuchMethodException | InvocationTargetException e2) {
                throw new DynamicLoaderException("Failed to loadLibrary " + file + " for " + classLoader.getClass().getName(), e2);
            }
        }
    }

    private static List<File> a(Object obj, File file) throws NoSuchFieldException, IllegalAccessException {
        Object obj2 = ReflectionUtils.d(obj, "nativeLibraryDirectories").get(obj);
        List<File> list = obj2 instanceof List ? (List) obj2 : null;
        if (list != null) {
            Iterator<File> it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                if (file.equals(it.next())) {
                    it.remove();
                    break;
                }
            }
            list.add(0, file);
            return list;
        }
        return Collections.emptyList();
    }

    private static List<File> b(Object obj) throws NoSuchFieldException, IllegalAccessException {
        Object obj2 = ReflectionUtils.d(obj, "systemNativeLibraryDirectories").get(obj);
        return obj2 instanceof List ? (List) obj2 : Collections.EMPTY_LIST;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static List<File> c(Object obj, File file) throws NoSuchFieldException, IllegalAccessException {
        List<File> a2 = a(obj, file);
        List<File> b = b(obj);
        ArrayList arrayList = new ArrayList(a2.size() + b.size());
        arrayList.addAll(a2);
        arrayList.addAll(b);
        return arrayList;
    }

    static final class AndroidPlatformV14 {
        private AndroidPlatformV14() {
        }
    }

    static final class AndroidPlatformV23 {
        private AndroidPlatformV23() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void b(ClassLoader classLoader, File file) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
            Object obj = ReflectionUtils.d(classLoader, "pathList").get(classLoader);
            List c = DynamicLibraryLoader.c(obj, file);
            LogUtil.d("HAF_LibraryLoader", "V23 load ", Arrays.toString(c.toArray()));
            Object invoke = ReflectionUtils.e(obj, "makePathElements", (Class<?>[]) new Class[]{List.class, File.class, List.class}).invoke(obj, c, null, new ArrayList());
            if (invoke instanceof Object[]) {
                ReflectionUtils.d(obj, "nativeLibraryPathElements").set(obj, invoke);
            }
        }
    }

    static final class AndroidPlatformV25 {
        private AndroidPlatformV25() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void c(ClassLoader classLoader, File file) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
            Object obj = ReflectionUtils.d(classLoader, "pathList").get(classLoader);
            List c = DynamicLibraryLoader.c(obj, file);
            LogUtil.d("HAF_LibraryLoader", "V25 load ", Arrays.toString(c.toArray()));
            Object invoke = ReflectionUtils.e(obj, "makePathElements", (Class<?>[]) new Class[]{List.class}).invoke(obj, c);
            if (invoke instanceof Object[]) {
                ReflectionUtils.d(obj, "nativeLibraryPathElements").set(obj, invoke);
            }
        }
    }
}
