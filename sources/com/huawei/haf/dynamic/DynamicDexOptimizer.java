package com.huawei.haf.dynamic;

import android.content.Context;
import android.os.Build;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ResultReceiver;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.openalliance.ad.constant.ParamConstants;
import dalvik.system.DexClassLoader;
import health.compact.a.LogUtil;
import health.compact.a.ReflectionUtils;
import java.io.File;
import java.io.FileDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public final class DynamicDexOptimizer {

    /* renamed from: a, reason: collision with root package name */
    private static Object f2109a;
    private static IBinder b;
    private static final String e = d();
    private static boolean d = true;

    private DynamicDexOptimizer() {
    }

    public static boolean e(File file) {
        return new File(file, e).exists();
    }

    public static boolean c(File file, File file2) {
        return e(file, file2, true);
    }

    public static boolean e(File file, File file2, boolean z) {
        return a(Arrays.asList(file.getAbsolutePath()), null, file2, "quicken", z);
    }

    public static boolean c(List<String> list, File file, File file2, boolean z) {
        return a(list, file, file2, "quicken", z);
    }

    private static boolean a(List<String> list, File file, File file2, String str, boolean z) {
        if (list.isEmpty()) {
            return true;
        }
        if (!z && Build.VERSION.SDK_INT > 29) {
            return false;
        }
        boolean z2 = z && d;
        File file3 = new File(file2, e);
        long currentTimeMillis = System.currentTimeMillis();
        if (z2) {
            b(list, str, file3);
        } else {
            a(list, file, file2);
        }
        long currentTimeMillis2 = System.currentTimeMillis();
        boolean exists = file3.exists();
        Object[] objArr = new Object[8];
        objArr[0] = "optimize ";
        objArr[1] = exists ? "success" : ParamConstants.CallbackMethod.ON_FAIL;
        objArr[2] = ", isNewOpt=";
        objArr[3] = Boolean.valueOf(z2);
        objArr[4] = ", cost time:";
        objArr[5] = Long.valueOf(currentTimeMillis2 - currentTimeMillis);
        objArr[6] = "ms, size=";
        objArr[7] = Integer.valueOf(list.size());
        LogUtil.c("HAF_DexOptimizer", objArr);
        return exists;
    }

    private static void a(List<String> list, File file, File file2) {
        try {
            new DexClassLoader(TextUtils.join(File.pathSeparator, list), file2.getAbsolutePath(), file == null ? null : file.getAbsolutePath(), DynamicDexOptimizer.class.getClassLoader());
        } catch (Throwable th) {
            LogUtil.a("HAF_DexOptimizer", "dex class loader ex=", LogUtil.a(th));
        }
    }

    private static void b(List<String> list, String str, File file) {
        synchronized (DynamicDexOptimizer.class) {
            Context e2 = BaseApplication.e();
            if (!c(e2)) {
                LogUtil.a("HAF_DexOptimizer", "maybeInit fail.");
            } else if (c(e2, list)) {
                e(e2, str, file);
                a(e2);
            }
        }
    }

    private static boolean c(Context context) {
        if (b != null) {
            return true;
        }
        Object b2 = ReflectionUtils.b(context.getPackageManager(), "mPM");
        f2109a = b2;
        if (!(b2 instanceof IInterface)) {
            return false;
        }
        IBinder asBinder = ((IInterface) b2).asBinder();
        if (asBinder != null) {
            b = asBinder;
        }
        return b != null;
    }

    private static void e(Context context, String str, File file) {
        for (int i = 1; i <= 3; i++) {
            a(b(context, str));
            if (file.exists()) {
                return;
            }
        }
    }

    private static boolean c(Context context, List<String> list) {
        String packageName = context.getPackageName();
        if (Build.VERSION.SDK_INT >= 30) {
            return d(packageName, list);
        }
        return b(packageName, list);
    }

    private static boolean d(String str, List<String> list) {
        Method d2 = ReflectionUtils.d(f2109a.getClass(), "notifyDexLoad", String.class, Map.class, String.class);
        if (d2 == null) {
            d = false;
            return false;
        }
        HashMap hashMap = new HashMap(list.size());
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            hashMap.put(it.next(), "PCL[]");
        }
        ReflectionUtils.c(d2, f2109a, str, hashMap, e);
        return true;
    }

    private static boolean b(String str, List<String> list) {
        Method d2 = ReflectionUtils.d(f2109a.getClass(), "notifyDexLoad", String.class, List.class, List.class, String.class);
        if (d2 == null) {
            d = false;
            return false;
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (String str2 : list) {
            arrayList.add("dalvik.system.DexClassLoader");
        }
        ReflectionUtils.c(d2, f2109a, str, arrayList, list, e);
        return true;
    }

    private static void a(Context context) {
        a(b(context));
    }

    private static void a(String[] strArr) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        obtain.writeFileDescriptor(FileDescriptor.in);
        obtain.writeFileDescriptor(FileDescriptor.out);
        obtain.writeFileDescriptor(FileDescriptor.err);
        obtain.writeStringArray(strArr);
        obtain.writeStrongBinder(null);
        new ResultReceiver(null).writeToParcel(obtain, 0);
        try {
            b.transact(1598246212, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            try {
            } finally {
            }
        }
    }

    private static String[] b(Context context, String str) {
        return new String[]{"compile", "-m", str, "-f", "--secondary-dex", context.getPackageName()};
    }

    private static String[] b(Context context) {
        return new String[]{"reconcile-secondary-dex-files", context.getPackageName()};
    }

    private static String d() {
        Class<?> b2 = ReflectionUtils.b("dalvik.system.VMRuntime");
        if (b2 != null) {
            Object c = ReflectionUtils.c(b2, "getCurrentInstructionSet");
            if (c instanceof String) {
                return (String) c;
            }
        }
        return "arm64";
    }
}
