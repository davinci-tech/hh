package com.huawei.haf.dynamic;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Build;
import android.os.Looper;
import android.webkit.WebView;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.HafRuntimeException;
import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.haf.common.utils.CommonConstant;
import com.huawei.haf.handler.HandlerExecutor;
import health.compact.a.LogUtil;
import health.compact.a.MagicBuild;
import health.compact.a.ProcessUtil;
import health.compact.a.ReflectionUtils;
import java.io.File;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes.dex */
public final class DynamicResourcesLoader {

    /* renamed from: a, reason: collision with root package name */
    private static boolean f2110a;
    private static volatile boolean d;
    private static int f;
    private static int h;
    private static final Set<String> e = Collections.newSetFromMap(new ConcurrentHashMap());
    private static final Map<Context, Resources> b = new WeakHashMap(5);
    private static final Object c = new Object();
    private static WebViewLoadPolicy j = new WebViewLoadPolicy();
    private static String[] i = CommonConstant.e;
    private static WeakReference g = new WeakReference(BaseApplication.e().getAssets());

    private DynamicResourcesLoader() {
    }

    public static void a(WebViewLoadPolicy webViewLoadPolicy) {
        if (webViewLoadPolicy != null) {
            j = webViewLoadPolicy;
        }
    }

    public static void c(Context context) {
        if (Looper.myLooper() == null) {
            throw new HafRuntimeException("WebView cannot be initialized on a thread that has no Looper.");
        }
        c(context, "preloadWebViewResources", System.currentTimeMillis());
    }

    public static boolean b(String str) {
        return e.contains(str);
    }

    public static boolean yk_(Context context, Resources resources, Collection<String> collection) throws DynamicLoaderException {
        if (CollectionUtils.d(collection)) {
            collection = e;
        }
        return yi_(context, resources, collection, false);
    }

    private static boolean yl_(Context context, Resources resources, boolean z) throws DynamicLoaderException {
        Collection<String> ym_ = ym_(context, resources);
        int size = ym_.size();
        Set<String> set = e;
        boolean z2 = true;
        if (!set.isEmpty()) {
            ArrayList<String> arrayList = new ArrayList(set);
            arrayList.removeAll(ym_);
            for (String str : arrayList) {
                if (yg_(context, resources, str)) {
                    size++;
                } else {
                    if (z) {
                        e.remove(str);
                    }
                    z2 = false;
                }
            }
        }
        if (z) {
            h = size;
            f = h(context);
        }
        return z2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(Context context, String str, long j2) {
        try {
            Context applicationContext = context.getApplicationContext();
            boolean yi_ = yi_(applicationContext, a(applicationContext).getResources(), e, true);
            Object[] objArr = new Object[8];
            objArr[0] = str;
            objArr[1] = d ? " ok." : " fail.";
            objArr[2] = " result=";
            objArr[3] = String.valueOf(yi_);
            objArr[4] = ", cost time=";
            objArr[5] = Long.valueOf(System.currentTimeMillis() - j2);
            objArr[6] = "ms, ";
            objArr[7] = context.getClass().getSimpleName();
            LogUtil.c("HAF_ResourcesLoader", objArr);
        } catch (DynamicLoaderException e2) {
            LogUtil.a("HAF_ResourcesLoader", str, ", exception=", LogUtil.a(e2));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0049 A[Catch: all -> 0x00d0, TryCatch #0 {, blocks: (B:10:0x0031, B:12:0x0035, B:16:0x0049, B:21:0x0056, B:23:0x0060, B:25:0x0068, B:26:0x0071, B:27:0x007d, B:28:0x008e, B:36:0x006c, B:39:0x003d), top: B:9:0x0031 }] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0060 A[Catch: all -> 0x00d0, TryCatch #0 {, blocks: (B:10:0x0031, B:12:0x0035, B:16:0x0049, B:21:0x0056, B:23:0x0060, B:25:0x0068, B:26:0x0071, B:27:0x007d, B:28:0x008e, B:36:0x006c, B:39:0x003d), top: B:9:0x0031 }] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x007c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static boolean yi_(android.content.Context r28, android.content.res.Resources r29, java.util.Collection<java.lang.String> r30, boolean r31) throws com.huawei.haf.dynamic.DynamicLoaderException {
        /*
            r0 = r28
            r1 = r29
            r2 = r30
            r3 = r31
            android.content.Context r4 = r28.getApplicationContext()
            android.content.Context r5 = a(r4)
            android.content.res.Resources r5 = r5.getResources()
            boolean r6 = d(r4, r3)
            boolean r6 = a(r0, r6)
            r7 = 0
            r8 = 1
            if (r6 != 0) goto L2b
            if (r3 != 0) goto L2b
            boolean r3 = yh_(r5)
            if (r3 == 0) goto L29
            goto L2b
        L29:
            r3 = r7
            goto L2c
        L2b:
            r3 = r8
        L2c:
            java.util.Map r6 = java.util.Collections.EMPTY_MAP
            java.lang.Object r9 = com.huawei.haf.dynamic.DynamicResourcesLoader.c
            monitor-enter(r9)
            java.util.Set<java.lang.String> r10 = com.huawei.haf.dynamic.DynamicResourcesLoader.e     // Catch: java.lang.Throwable -> Ld0
            if (r2 == r10) goto L3b
            boolean r2 = r10.addAll(r2)     // Catch: java.lang.Throwable -> Ld0
            if (r2 != 0) goto L46
        L3b:
            if (r3 != 0) goto L46
            boolean r2 = b(r4)     // Catch: java.lang.Throwable -> Ld0
            if (r2 == 0) goto L44
            goto L46
        L44:
            r2 = r7
            goto L47
        L46:
            r2 = r8
        L47:
            if (r1 == r5) goto L5e
            java.util.Map<android.content.Context, android.content.res.Resources> r3 = com.huawei.haf.dynamic.DynamicResourcesLoader.b     // Catch: java.lang.Throwable -> Ld0
            java.lang.Object r3 = r3.put(r0, r1)     // Catch: java.lang.Throwable -> Ld0
            if (r3 == r1) goto L52
            r7 = r8
        L52:
            if (r2 != 0) goto L5e
            if (r7 == 0) goto L5e
            java.util.HashMap r6 = new java.util.HashMap     // Catch: java.lang.Throwable -> Ld0
            r6.<init>(r8)     // Catch: java.lang.Throwable -> Ld0
            r6.put(r0, r1)     // Catch: java.lang.Throwable -> Ld0
        L5e:
            if (r2 == 0) goto L7c
            java.util.Map<android.content.Context, android.content.res.Resources> r3 = com.huawei.haf.dynamic.DynamicResourcesLoader.b     // Catch: java.lang.Throwable -> Ld0
            boolean r6 = r3.isEmpty()     // Catch: java.lang.Throwable -> Ld0
            if (r6 == 0) goto L6c
            java.util.Map r3 = java.util.Collections.EMPTY_MAP     // Catch: java.lang.Throwable -> Ld0
            r6 = r3
            goto L71
        L6c:
            java.util.HashMap r6 = new java.util.HashMap     // Catch: java.lang.Throwable -> Ld0
            r6.<init>(r3)     // Catch: java.lang.Throwable -> Ld0
        L71:
            boolean r3 = yl_(r4, r5, r8)     // Catch: java.lang.Throwable -> Ld0
            g(r4)     // Catch: java.lang.Throwable -> Ld0
            yo_(r5)     // Catch: java.lang.Throwable -> Ld0
            goto L7d
        L7c:
            r3 = r8
        L7d:
            int r4 = com.huawei.haf.dynamic.DynamicResourcesLoader.h     // Catch: java.lang.Throwable -> Ld0
            int r11 = com.huawei.haf.dynamic.DynamicResourcesLoader.f     // Catch: java.lang.Throwable -> Ld0
            java.lang.String[] r12 = com.huawei.haf.dynamic.DynamicResourcesLoader.i     // Catch: java.lang.Throwable -> Ld0
            int r12 = r12.length     // Catch: java.lang.Throwable -> Ld0
            int r10 = r10.size()     // Catch: java.lang.Throwable -> Ld0
            java.util.Map<android.content.Context, android.content.res.Resources> r13 = com.huawei.haf.dynamic.DynamicResourcesLoader.b     // Catch: java.lang.Throwable -> Ld0
            int r13 = r13.size()     // Catch: java.lang.Throwable -> Ld0
            monitor-exit(r9)     // Catch: java.lang.Throwable -> Ld0
            boolean r1 = yj_(r5, r1, r6, r3)
            if (r2 != 0) goto L97
            if (r7 == 0) goto Lcf
        L97:
            java.lang.String r14 = "cacheSize="
            java.lang.Integer r15 = java.lang.Integer.valueOf(r10)
            java.lang.String r16 = ", splitResLen="
            java.lang.Integer r17 = java.lang.Integer.valueOf(r11)
            java.lang.String r18 = ", appResLen="
            java.lang.Integer r19 = java.lang.Integer.valueOf(r4)
            java.lang.String r20 = ", sharedLibsLen="
            java.lang.Integer r21 = java.lang.Integer.valueOf(r12)
            java.lang.String r22 = ", ctxNum="
            int r13 = r13 + r8
            java.lang.Integer r23 = java.lang.Integer.valueOf(r13)
            java.lang.String r24 = ", result="
            java.lang.Boolean r25 = java.lang.Boolean.valueOf(r1)
            java.lang.String r26 = ", "
            java.lang.Class r0 = r28.getClass()
            java.lang.String r27 = r0.getSimpleName()
            java.lang.Object[] r0 = new java.lang.Object[]{r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27}
            java.lang.String r2 = "HAF_ResourcesLoader"
            health.compact.a.LogUtil.c(r2, r0)
        Lcf:
            return r1
        Ld0:
            r0 = move-exception
            monitor-exit(r9)     // Catch: java.lang.Throwable -> Ld0
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.haf.dynamic.DynamicResourcesLoader.yi_(android.content.Context, android.content.res.Resources, java.util.Collection, boolean):boolean");
    }

    private static boolean yj_(Resources resources, Resources resources2, Map<Context, Resources> map, boolean z) {
        if (resources2 != resources) {
            z = map.isEmpty();
        }
        int size = map.size();
        if (size == 0) {
            return z;
        }
        HashSet hashSet = new HashSet(size);
        hashSet.add(resources.getAssets());
        int i2 = 0;
        for (Map.Entry<Context, Resources> entry : map.entrySet()) {
            Resources value = entry.getValue();
            if (hashSet.contains(value.getAssets())) {
                i2++;
                if (resources2 == value) {
                }
            } else {
                try {
                    if (yl_(entry.getKey(), value, false)) {
                        hashSet.add(value.getAssets());
                        if (resources2 == value) {
                        }
                    }
                } catch (DynamicLoaderException unused) {
                }
            }
            z = true;
        }
        int i3 = size + 1;
        int size2 = hashSet.size();
        int i4 = (i3 - size2) - i2;
        if (i4 != 0) {
            LogUtil.c("HAF_ResourcesLoader", "ctxNum=", Integer.valueOf(i3), ", assetNum=", Integer.valueOf(size2), ", dupNum=", Integer.valueOf(i2), ", failNum=", Integer.valueOf(i4));
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Context a(Context context) {
        while (context instanceof ContextWrapper) {
            context = ((ContextWrapper) context).getBaseContext();
        }
        return context;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean yh_(Resources resources) {
        return g.get() != resources.getAssets();
    }

    private static void yo_(Resources resources) {
        AssetManager assets = resources.getAssets();
        if (g.get() != assets) {
            g = new WeakReference(assets);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean d(Context context, boolean z) {
        if (!d || !b(context)) {
            return z;
        }
        d = false;
        return true;
    }

    private static boolean b(Context context) {
        return i != e(context);
    }

    private static void g(Context context) {
        i = e(context);
    }

    private static String[] e(Context context) {
        String[] strArr = context.getApplicationInfo().sharedLibraryFiles;
        return strArr == null ? CommonConstant.e : strArr;
    }

    private static boolean a(Context context, boolean z) {
        if (d) {
            return false;
        }
        if (!j.d(context)) {
            d = true;
            return false;
        }
        if (z || j.c(context)) {
            return e(context, j.b(context));
        }
        return false;
    }

    private static boolean e(Context context, boolean z) {
        if (z) {
            boolean c2 = WebViewFactoryProviderAdapter.c();
            if (WebViewFactoryProviderAdapter.e()) {
                if (!WebViewFactoryProviderAdapter.a()) {
                    return c2;
                }
                d = true;
                return true;
            }
        }
        synchronized (c) {
            if (!d && !f2110a) {
                f2110a = true;
                try {
                    d = j(context);
                    return d;
                } finally {
                    f2110a = false;
                }
            }
            return false;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.Exception] */
    /* JADX WARN: Type inference failed for: r1v14 */
    /* JADX WARN: Type inference failed for: r1v5, types: [java.lang.IllegalStateException, java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r1v8, types: [java.lang.Throwable] */
    private static boolean j(Context context) {
        String message;
        boolean z;
        Context applicationContext = context.getApplicationContext();
        Resources resources = a(applicationContext).getResources();
        AssetManager assets = resources.getAssets();
        try {
            new WebView(applicationContext).destroy();
        } catch (IllegalStateException e2) {
            e = e2;
            if (Build.VERSION.SDK_INT >= 28 && (message = e.getMessage()) != null && message.contains("WebView")) {
                LogUtil.a("HAF_ResourcesLoader", "loadWebViewResources disabled, ex=", LogUtil.a(e));
            }
        } catch (UnsupportedOperationException e3) {
            LogUtil.a("HAF_ResourcesLoader", "loadWebViewResources unsupported, ex=", LogUtil.a(e3));
        } catch (Exception e4) {
            e = e4;
        }
        e = 0;
        if (e != 0) {
            LogUtil.a("HAF_ResourcesLoader", "loadWebViewResources fail, ex=", LogUtil.a(e));
            z = false;
        } else {
            z = true;
        }
        AssetManager assets2 = resources.getAssets();
        if (assets != assets2) {
            LogUtil.c("HAF_ResourcesLoader", "loadWebViewResources ok, resources have changed, oldAsset=", assets, ", newAsset=", assets2);
            if (context instanceof Activity) {
                HandlerExecutor.a(new DelayCheckRunnable(context));
            }
        } else if (z) {
            LogUtil.c("HAF_ResourcesLoader", "loadWebViewResources ok, resources no changed, asset=", assets);
        }
        return z;
    }

    private static boolean yg_(Context context, Resources resources, String str) throws DynamicLoaderException {
        try {
            return AndroidPlatformV21.yq_(resources, str);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e2) {
            throw new DynamicLoaderException("Failed to addResources " + str + " for " + context.getClass().getName(), e2);
        }
    }

    private static int h(Context context) throws DynamicLoaderException {
        String[] c2 = c(context, "");
        Set<String> set = e;
        if (!set.isEmpty()) {
            ArrayList arrayList = new ArrayList(set);
            arrayList.removeAll(Arrays.asList(c2));
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                c2 = c(context, (String) it.next());
            }
        }
        return c2.length;
    }

    private static String[] c(Context context, String str) throws DynamicLoaderException {
        try {
            return AndroidPlatformV21.c(a(context), str);
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException e2) {
            throw new DynamicLoaderException("Failed to updateSplitResDirs '" + str + "' for " + context.getClass().getName(), e2);
        }
    }

    private static Collection<String> ym_(Context context, Resources resources) throws DynamicLoaderException {
        try {
            return yn_(resources.getAssets());
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException | NoSuchMethodException | InvocationTargetException e2) {
            throw new DynamicLoaderException("Failed to get all loaded module resources for " + context.getClass().getName(), e2);
        }
    }

    private static Collection<String> yn_(AssetManager assetManager) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, ClassNotFoundException {
        ArrayList arrayList = new ArrayList();
        if (Build.VERSION.SDK_INT >= 28) {
            Object invoke = VersionCompat.b().invoke(assetManager, new Object[0]);
            for (Object obj : invoke instanceof Object[] ? (Object[]) invoke : CommonConstant.d) {
                Object invoke2 = VersionCompat.d().invoke(obj, new Object[0]);
                if (invoke2 instanceof String) {
                    arrayList.add((String) invoke2);
                }
            }
        } else {
            Object obj2 = VersionCompat.g().get(assetManager);
            Object[] objArr = obj2 instanceof Object[] ? (Object[]) obj2 : CommonConstant.d;
            boolean z = false;
            for (int i2 = 1; i2 <= objArr.length; i2++) {
                try {
                    Object invoke3 = VersionCompat.e().invoke(assetManager, Integer.valueOf(i2));
                    if (invoke3 instanceof String) {
                        arrayList.add((String) invoke3);
                    }
                } catch (IndexOutOfBoundsException | InvocationTargetException e2) {
                    if (!z) {
                        LogUtil.a("HAF_ResourcesLoader", "Unable to get cookie name for resources index=", Integer.valueOf(i2), ", ex=", LogUtil.a(e2));
                        z = true;
                    }
                }
            }
        }
        return arrayList;
    }

    public static class WebViewLoadPolicy {
        public boolean b(Context context) {
            return false;
        }

        final boolean d(Context context) {
            return e(context);
        }

        protected boolean e(Context context) {
            return ProcessUtil.d();
        }

        public boolean c(Context context) {
            if (context instanceof Activity) {
                return true;
            }
            return MagicBuild.f13130a && MagicBuild.d >= 36;
        }
    }

    static class AndroidPlatformV21 extends VersionCompat {
        private AndroidPlatformV21() {
            super();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static boolean yq_(Resources resources, String str) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
            AssetManager assets = resources.getAssets();
            Object invoke = VersionCompat.c().invoke(assets, str);
            int intValue = invoke instanceof Integer ? ((Integer) invoke).intValue() : 0;
            boolean z = intValue > 0;
            LogUtil.c("HAF_ResourcesLoader", "addResources ", e(str), ", index=", Integer.valueOf(intValue), ", result=", Boolean.valueOf(z), ", asset=", assets);
            return z;
        }

        private static String e(String str) {
            int lastIndexOf = str.lastIndexOf(File.separatorChar);
            return lastIndexOf >= 0 ? str.substring(lastIndexOf + 1) : str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static String[] c(Context context, String str) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
            Object obj = f().get(context);
            Field a2 = a();
            Object obj2 = a2.get(obj);
            String[] strArr = obj2 instanceof String[] ? (String[]) obj2 : null;
            if (CollectionUtils.a(str)) {
                return strArr != null ? strArr : CommonConstant.e;
            }
            String[] strArr2 = {str};
            if (strArr == null) {
                a2.set(obj, strArr2);
                return strArr2;
            }
            for (String str2 : strArr) {
                if (str.equals(str2)) {
                    return strArr;
                }
            }
            String[] strArr3 = new String[strArr.length + 1];
            System.arraycopy(strArr, 0, strArr3, 0, strArr.length);
            System.arraycopy(strArr2, 0, strArr3, strArr.length, 1);
            a2.set(obj, strArr3);
            return strArr3;
        }
    }

    static class VersionCompat {

        /* renamed from: a, reason: collision with root package name */
        private static Method f2111a;
        private static Method b;
        private static Field c;
        private static Method d;
        private static Method e;
        private static Field i;
        private static Field j;

        private VersionCompat() {
        }

        static Field g() throws NoSuchFieldException {
            if (j == null) {
                j = ReflectionUtils.e(AssetManager.class, "mStringBlocks");
            }
            return j;
        }

        static Method c() throws NoSuchMethodException {
            if (e == null) {
                e = ReflectionUtils.b(AssetManager.class, "addAssetPath", String.class);
            }
            return e;
        }

        static Method e() throws NoSuchMethodException {
            if (b == null) {
                b = ReflectionUtils.b(AssetManager.class, "getCookieName", Integer.TYPE);
            }
            return b;
        }

        static Method d() throws ClassNotFoundException, NoSuchMethodException {
            if (f2111a == null) {
                f2111a = ReflectionUtils.b(ReflectionUtils.d("android.content.res.ApkAssets"), "getAssetPath", new Class[0]);
            }
            return f2111a;
        }

        static Method b() throws NoSuchMethodException {
            if (d == null) {
                d = ReflectionUtils.b(AssetManager.class, "getApkAssets", new Class[0]);
            }
            return d;
        }

        static Field f() throws ClassNotFoundException, NoSuchFieldException {
            if (c == null) {
                c = ReflectionUtils.e(ReflectionUtils.d("android.app.ContextImpl"), "mPackageInfo");
            }
            return c;
        }

        static Field a() throws ClassNotFoundException, NoSuchFieldException {
            if (i == null) {
                i = ReflectionUtils.e(ReflectionUtils.d("android.app.LoadedApk"), "mSplitResDirs");
            }
            return i;
        }
    }

    static class WebViewFactoryProviderAdapter implements InvocationHandler {

        /* renamed from: a, reason: collision with root package name */
        private static boolean f2112a;
        private static volatile Boolean d;
        private final Object e;

        private WebViewFactoryProviderAdapter(Object obj) {
            this.e = obj;
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            if ("createWebView".equals(method.getName())) {
                long currentTimeMillis = System.currentTimeMillis();
                Object invoke = method.invoke(this.e, objArr);
                b(currentTimeMillis);
                return invoke;
            }
            return method.invoke(this.e, objArr);
        }

        static boolean c() {
            return d == null;
        }

        static boolean e() {
            if (d != null) {
                return d.booleanValue();
            }
            long currentTimeMillis = System.currentTimeMillis();
            String str = "";
            synchronized (DynamicResourcesLoader.c) {
                if (d != null) {
                    return d.booleanValue();
                }
                try {
                    d = Boolean.valueOf(d());
                } catch (Exception e) {
                    d = Boolean.FALSE;
                    str = ", ex=" + LogUtil.a(e);
                }
                LogUtil.c("HAF_ResourcesLoader", "Install hooks to sense creation of WebView, result=", d, ", cost time=", Long.valueOf(System.currentTimeMillis() - currentTimeMillis), "ms", str);
                return d.booleanValue();
            }
        }

        static boolean a() {
            boolean z;
            synchronized (DynamicResourcesLoader.c) {
                z = f2112a;
            }
            return z;
        }

        private static void b(long j) {
            Context e = BaseApplication.e();
            if (c(e)) {
                synchronized (DynamicResourcesLoader.c) {
                    f2112a = true;
                    try {
                        DynamicResourcesLoader.c(e, "createWebView notify checkOrUpdateResources", j);
                    } finally {
                        f2112a = false;
                    }
                }
            }
        }

        private static boolean c(Context context) {
            return DynamicResourcesLoader.d(context, false) || !DynamicResourcesLoader.d || DynamicResourcesLoader.yh_(DynamicResourcesLoader.a(context).getResources());
        }

        private static boolean d() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
            ClassLoader classLoader = BaseApplication.e().getClassLoader();
            Class<?> c = ReflectionUtils.c("android.webkit.WebViewFactory", classLoader);
            return ReflectionUtils.e(c, "sProviderInstance", Proxy.newProxyInstance(classLoader, new Class[]{ReflectionUtils.c("android.webkit.WebViewFactoryProvider", classLoader)}, new WebViewFactoryProviderAdapter(ReflectionUtils.b(c, "getProvider", null).invoke(null, null))));
        }
    }

    static class DelayCheckRunnable implements Runnable {
        private final Context d;

        DelayCheckRunnable(Context context) {
            this.d = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            DynamicResourcesLoader.c(this.d, "delay notify checkOrUpdateResources", System.currentTimeMillis());
        }
    }
}
