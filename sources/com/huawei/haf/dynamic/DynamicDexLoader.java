package com.huawei.haf.dynamic;

import com.huawei.haf.common.utils.CommonConstant;
import health.compact.a.LogUtil;
import health.compact.a.ReflectionUtils;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
final class DynamicDexLoader {
    private DynamicDexLoader() {
    }

    static Object[] e(ClassLoader classLoader, File file, List<File> list) throws DynamicLoaderException {
        if (list.isEmpty()) {
            return CommonConstant.d;
        }
        try {
            return AndroidPlatformV23.d(classLoader, list, file);
        } catch (IOException | IllegalAccessException | NoSuchFieldException | NoSuchMethodException | InvocationTargetException e) {
            throw new DynamicLoaderException("Failed to loadDex " + classLoader.getClass().getName(), e);
        }
    }

    static void c(ClassLoader classLoader, Object[] objArr) throws DynamicLoaderException {
        if (objArr.length == 0) {
            return;
        }
        try {
            ReflectionUtils.e(ReflectionUtils.d(classLoader, "pathList").get(classLoader), "dexElements", objArr);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new DynamicLoaderException("unloadDex fail.", e);
        }
    }

    static final class AndroidPlatformV23 {
        private AndroidPlatformV23() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static Object[] d(ClassLoader classLoader, List<File> list, File file) throws NoSuchFieldException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, IOException {
            Object obj = ReflectionUtils.d(classLoader, "pathList").get(classLoader);
            ArrayList arrayList = new ArrayList();
            Object[] c = c(obj, new ArrayList(list), file, arrayList);
            if (!arrayList.isEmpty()) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    IOException iOException = (IOException) it.next();
                    LogUtil.e("HAF_DexLoader", "Exception in makePathElement ex=", LogUtil.a(iOException));
                    if (c.length == 0) {
                        throw iOException;
                    }
                }
            }
            ReflectionUtils.a(obj, "dexElements", c);
            return c;
        }

        private static Object[] c(Object obj, ArrayList<File> arrayList, File file, ArrayList<IOException> arrayList2) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
            Method e;
            try {
                e = ReflectionUtils.e(obj, "makePathElements", (Class<?>[]) new Class[]{List.class, File.class, List.class});
            } catch (NoSuchMethodException unused) {
                LogUtil.e("HAF_DexLoader", "NoSuchMethodException: makePathElements(List, File, List) failure");
                try {
                    e = ReflectionUtils.e(obj, "makePathElements", (Class<?>[]) new Class[]{ArrayList.class, File.class, ArrayList.class});
                } catch (NoSuchMethodException unused2) {
                    LogUtil.e("HAF_DexLoader", "NoSuchMethodException: makeDexElements(ArrayList, File, ArrayList) failure");
                    try {
                        LogUtil.a("HAF_DexLoader", "NoSuchMethodException: try use v19 instead");
                        return AndroidPlatformV19.a(obj, arrayList, file, arrayList2);
                    } catch (NoSuchMethodException e2) {
                        LogUtil.e("HAF_DexLoader", "NoSuchMethodException: makeDexElements(List, File, List) failure");
                        throw e2;
                    }
                }
            }
            Object invoke = e.invoke(obj, arrayList, file, arrayList2);
            return invoke instanceof Object[] ? (Object[]) invoke : CommonConstant.d;
        }
    }

    static final class AndroidPlatformV19 {
        private AndroidPlatformV19() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static Object[] a(Object obj, ArrayList<File> arrayList, File file, ArrayList<IOException> arrayList2) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
            Method e;
            try {
                e = ReflectionUtils.e(obj, "makeDexElements", (Class<?>[]) new Class[]{ArrayList.class, File.class, ArrayList.class});
            } catch (NoSuchMethodException unused) {
                LogUtil.e("HAF_DexLoader", "NoSuchMethodException: makeDexElements(ArrayList, File, ArrayList) failure");
                try {
                    e = ReflectionUtils.e(obj, "makeDexElements", (Class<?>[]) new Class[]{List.class, File.class, List.class});
                } catch (NoSuchMethodException e2) {
                    LogUtil.e("HAF_DexLoader", "NoSuchMethodException: makeDexElements(List, File, List) failure");
                    throw e2;
                }
            }
            Object invoke = e.invoke(obj, arrayList, file, arrayList2);
            return invoke instanceof Object[] ? (Object[]) invoke : CommonConstant.d;
        }
    }
}
