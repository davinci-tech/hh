package com.huawei.hms.ads.dynamic;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import com.huawei.hms.ads.analysis.DynamicLoaderAnalysis;
import com.huawei.hms.ads.common.inter.LoaderCommonInter;
import com.huawei.hms.ads.common.inter.LoaderSpHandlerInter;
import com.huawei.hms.ads.dynamic.IDynamicLoader;
import com.huawei.hms.ads.dynamicloader.g;
import com.huawei.hms.ads.uiengineloader.aa;
import com.huawei.hms.ads.uiengineloader.af;
import com.huawei.hms.ads.uiengineloader.d;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Objects;

/* loaded from: classes4.dex */
public class DynamicModule {
    public static final int MODULE_INTER_ERROR = 3;
    public static final int MODULE_NORMAL = 0;

    /* renamed from: a, reason: collision with root package name */
    protected static LoaderSpHandlerInter f4292a = null;
    protected static LoaderCommonInter b = null;
    private static final String c = "DynamicModule";
    private static final int d = 256;
    private static final int e = -100;
    private static final ThreadLocal<HashMap<String, Boolean>> f = new ThreadLocal<>();
    private static final ThreadLocal<HashMap<String, String>> g = new ThreadLocal<>();
    private static final ThreadLocal<HashMap<String, IDynamicLoader>> h = new ThreadLocal<>();
    private Context i;

    public final Context getModuleContext() {
        return this.i;
    }

    public static void setSpHandler(LoaderSpHandlerInter loaderSpHandlerInter) {
        f4292a = loaderSpHandlerInter;
    }

    public static void setCommonInter(LoaderCommonInter loaderCommonInter) {
        b = loaderCommonInter;
    }

    public static Bundle queryHMSModuleBundle(Context context, String str) throws LoadingException, a {
        byte b2 = 0;
        try {
            ContentResolver contentResolver = context.getContentResolver();
            if (contentResolver == null) {
                throw new a("Query remote version failed: null contentResolver.", b2);
            }
            Bundle call = contentResolver.call(Uri.parse("content://com.huawei.hms"), str, (String) null, (Bundle) null);
            if (call == null) {
                af.c(c, "Failed to get bundle info:null.");
                throw new a("Query remote version failed: null bundle info.", b2);
            }
            int i = call.getInt("errcode");
            call.getString("loader_path");
            af.b(c, "bundle info: errorCode:" + i + ", moduleVersion:" + call.getInt("module_version") + ", loader_version:" + call.getInt("loader_version") + ", armeabiType:" + call.getInt("armeabiType"));
            if (i == 0) {
                return call;
            }
            af.c(c, "Failed to get " + str + " bundle info, errcode:" + i);
            throw new LoadingException("Query " + str + " unavailable, errorCode:" + i, call);
        } catch (LoadingException e2) {
            throw e2;
        } catch (Exception unused) {
            throw new a("failed to get :" + str + " info.", b2);
        }
    }

    public static DynamicModule load(Context context, Integer num, String str, String str2) throws LoadingException {
        if (context == null || num == null || TextUtils.isEmpty(str) || str.length() > 256) {
            DynamicLoaderAnalysis.getInstance().onLoaderException(str, 1, "Null param, please check it.");
            throw new LoadingException("Null param, please check it.");
        }
        Bundle bundle = new Bundle();
        bundle.putString(com.huawei.hms.ads.dynamic.a.n, str2);
        return a(context, num, str, bundle);
    }

    public static LoaderSpHandlerInter getSpHandler() {
        return f4292a;
    }

    public static int getRemoteVersion(Context context, String str) throws LoadingException {
        try {
            Bundle b2 = b(context, str);
            if (b2 != null && !b2.isEmpty()) {
                return b2.getInt("module_version");
            }
            af.c(c, "Query remote module:" + str + " info failed.");
            throw new LoadingException("Query remote module info failed: null or empty.");
        } catch (a e2) {
            af.c(c, "Query remote module:" + str + " exception:" + e2.getClass().getSimpleName());
            return 0;
        }
    }

    public static Bundle getRemoteModuleInfo(Context context, String str) throws LoadingException {
        try {
        } catch (LoadingException e2) {
            throw e2;
        } catch (Exception e3) {
            af.c(c, "Get remote module info for " + str + " failed." + e3.getClass().getSimpleName());
        }
        synchronized (DynamicModule.class) {
            ThreadLocal<HashMap<String, Boolean>> threadLocal = f;
            if (threadLocal.get() == null || threadLocal.get().get(str) == null || !threadLocal.get().get(str).booleanValue()) {
                Bundle a2 = a(context, str);
                if (a2.getInt("module_version") > 0) {
                    return a2;
                }
            }
            if (threadLocal.get().get(str).booleanValue()) {
                try {
                    return b(context, str);
                } catch (a e4) {
                    af.c(c, "Query remote module info in HMS failed." + e4.getClass().getSimpleName());
                }
            }
            return new Bundle();
        }
    }

    public static int getLocalVersion(Context context, String str) {
        String str2;
        if (context == null || str.length() == 0 || str.length() > 256) {
            af.d(c, "Invalid context or moduleName.");
            return 0;
        }
        try {
            String str3 = "com.huawei.hms.ads.dynamic.descriptors." + str + ".ModuleDescriptor";
            if (context.getApplicationContext() != null) {
                context = context.getApplicationContext();
            }
            return context.getClassLoader().loadClass(str3).getDeclaredField("MODULE_VERSION").getInt(null);
        } catch (ClassNotFoundException unused) {
            str2 = "Cannot find the class of module descriptor for ".concat(String.valueOf(str));
            af.c(c, str2);
            return 0;
        } catch (Exception e2) {
            str2 = "Get local module info failed." + e2.getClass().getSimpleName();
            af.c(c, str2);
            return 0;
        }
    }

    public static Bundle getLocalModuleInfo(Context context, String str) {
        int localVersion = getLocalVersion(context, str);
        Bundle bundle = new Bundle();
        bundle.putString("module_name", str);
        bundle.putInt("local_module_version", localVersion);
        return bundle;
    }

    public static LoaderCommonInter getCommonInter() {
        return b;
    }

    private static Bundle b(Context context, String str) throws LoadingException, a {
        try {
            Bundle queryHMSModuleBundle = queryHMSModuleBundle(context, str);
            String string = queryHMSModuleBundle.getString("loader_path");
            if (!TextUtils.isEmpty(string) && new File(string).exists()) {
                g.set(new HashMap<String, String>(str, string) { // from class: com.huawei.hms.ads.dynamic.DynamicModule.2

                    /* renamed from: a, reason: collision with root package name */
                    final /* synthetic */ String f4294a;
                    final /* synthetic */ String b;

                    {
                        this.f4294a = str;
                        this.b = string;
                        put(str, string);
                    }
                });
                af.b(c, "Query remote version by module name:" + str + " success.");
                return queryHMSModuleBundle;
            }
            af.c(c, "The loaderPath in query bundle is not available,change the module version to:-100");
            queryHMSModuleBundle.putInt("module_version", -100);
            return queryHMSModuleBundle;
        } catch (LoadingException e2) {
            throw e2;
        } catch (Exception unused) {
            throw new a("failed to Query remote version.", (byte) 0);
        }
    }

    private static boolean a(String str) {
        return !TextUtils.isEmpty(str) && str.length() <= 256;
    }

    private static void a(String str, ClassLoader classLoader) throws LoadingException {
        try {
            h.set(new HashMap<String, IDynamicLoader>(str, (IBinder) classLoader.loadClass(com.huawei.hms.ads.dynamic.a.b).getConstructor(new Class[0]).newInstance(new Object[0])) { // from class: com.huawei.hms.ads.dynamic.DynamicModule.3

                /* renamed from: a, reason: collision with root package name */
                final /* synthetic */ String f4295a;
                final /* synthetic */ IBinder b;

                {
                    this.f4295a = str;
                    this.b = r2;
                    put(str, IDynamicLoader.Stub.asInterface(r2));
                }
            });
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e2) {
            throw new LoadingException("Failed to get loader interface:" + e2.getMessage());
        }
    }

    private static Class<?> a(Context context) throws LoadingException {
        Class<?> cls;
        try {
            if (context.getApplicationContext() != null) {
                context = context.getApplicationContext();
            }
            cls = context.getClassLoader().loadClass(DynamicLoaderClassLoader.class.getName());
        } catch (ClassNotFoundException unused) {
            af.c(c, "ClassLoader class not found when use client context.");
            cls = null;
        }
        if (cls == null) {
            try {
                cls = ((ClassLoader) Objects.requireNonNull(DynamicModule.class.getClassLoader())).loadClass(DynamicLoaderClassLoader.class.getName());
                if (cls == null) {
                    throw new LoadingException("ClassLoader class is null.");
                }
            } catch (ClassNotFoundException unused2) {
                throw new LoadingException("ClassLoader class not found when use DynamicModule's classLoader.");
            }
        }
        return cls;
    }

    private static DynamicModule a(Context context, String str, Integer num, Bundle bundle) throws LoadingException {
        int intValue = num.intValue();
        bundle.putString("module_name", str);
        bundle.putInt("version_strategy_type", intValue);
        try {
            g gVar = new g();
            af.b(c, "new DynamicLoader.");
            Context a2 = a(context.getApplicationContext() == null ? context : context.getApplicationContext(), str, bundle, gVar);
            if (a2 != null) {
                return new DynamicModule(a2);
            }
            throw new LoadingException("New version policy: Failed to get module context: null.");
        } catch (LoadingException e2) {
            throw e2;
        } catch (Exception e3) {
            af.d(c, "Other exception," + e3.getClass().getSimpleName());
            if (context.getApplicationContext() != null) {
                context = context.getApplicationContext();
            }
            return new DynamicModule(context);
        }
    }

    private static DynamicModule a(Context context, String str, Bundle bundle) throws LoadingException {
        g gVar = new g();
        af.b(c, "new DynamicLoader.");
        if (context.getApplicationContext() != null) {
            context = context.getApplicationContext();
        }
        Context a2 = a(context, str, bundle, gVar);
        if (a2 != null) {
            return new DynamicModule(a2);
        }
        throw new LoadingException("New version policy: Failed to get module context: null.");
    }

    /* loaded from: classes9.dex */
    public static class DynamicLoaderClassLoader {

        /* renamed from: a, reason: collision with root package name */
        private static HashMap<String, ClassLoader> f4296a = new HashMap<>();

        public static void setsClassLoader(String str, ClassLoader classLoader) {
            f4296a.put(str, classLoader);
        }

        public static ClassLoader getsClassLoader(String str) {
            return f4296a.get(str);
        }
    }

    private static DynamicModule a(Context context, Integer num, String str, Bundle bundle) throws LoadingException {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            if (!aa.a(context)) {
                DynamicLoaderAnalysis.getInstance().onLoaderException(str, 2, "HMS not installed.");
                throw new LoadingException("HMS not installed.");
            }
            if (!d.a()) {
                String a2 = d.a("ro.build.2b2c.partner.ext_channel");
                if (TextUtils.isEmpty(a2) || !a2.startsWith(com.huawei.hms.ads.dynamic.a.t)) {
                    DynamicLoaderAnalysis.getInstance().onLoaderException(str, 3, "Do not allow loading on other devices.");
                    throw new LoadingException("Do not allow loading on other devices.");
                }
            }
            af.b(c, "Load start in new-version-policy.");
            DynamicModule a3 = a(context, str, num, bundle);
            DynamicLoaderAnalysis.getInstance().onLoaderSuccess(str, System.currentTimeMillis() - currentTimeMillis);
            return a3;
        } catch (LoadingException e2) {
            DynamicLoaderAnalysis.getInstance().onLoaderException(str, 4, e2.getMessage());
            throw e2;
        } catch (Exception e3) {
            af.d(c, "Other exception:" + e3.getClass().getSimpleName());
            DynamicLoaderAnalysis.getInstance().onLoaderException(str, 4, "Other exception, " + e3.getClass().getSimpleName());
            throw new LoadingException("Load failed.");
        }
    }

    public static class LoadingException extends Exception {

        /* renamed from: a, reason: collision with root package name */
        private Bundle f4297a;

        public Bundle getBundle() {
            return this.f4297a;
        }

        public LoadingException(String str, Bundle bundle) {
            super(str);
            this.f4297a = bundle;
        }

        public LoadingException(String str) {
            super(str);
        }
    }

    private static Bundle a(Context context, String str) throws LoadingException {
        boolean z = false;
        try {
            try {
                Class<?> a2 = a(context);
                Method declaredMethod = a2.getDeclaredMethod("getsClassLoader", String.class);
                Method declaredMethod2 = a2.getDeclaredMethod("setsClassLoader", String.class, ClassLoader.class);
                ClassLoader classLoader = (ClassLoader) declaredMethod.invoke(null, str);
                if (classLoader == null) {
                    try {
                        af.b(c, "No available cached loader, query remote.");
                        Bundle b2 = b(context, str);
                        synchronized (DynamicModule.class) {
                            String str2 = (String) ((HashMap) Objects.requireNonNull(g.get())).get(str);
                            if (TextUtils.isEmpty(str2)) {
                                return b2;
                            }
                            com.huawei.hms.ads.uiengineloader.g gVar = new com.huawei.hms.ads.uiengineloader.g(str2, ClassLoader.getSystemClassLoader());
                            a(str, gVar);
                            declaredMethod2.invoke(null, str, gVar);
                            f.set(new HashMap<String, Boolean>(str) { // from class: com.huawei.hms.ads.dynamic.DynamicModule.1

                                /* renamed from: a, reason: collision with root package name */
                                final /* synthetic */ String f4293a;

                                {
                                    this.f4293a = str;
                                    put(str, Boolean.TRUE);
                                }
                            });
                            return b2;
                        }
                    } catch (a unused) {
                    }
                } else if (classLoader != ClassLoader.getSystemClassLoader()) {
                    af.b(c, "Cached loader is available, ready to use it.");
                    try {
                        a(str, classLoader);
                    } catch (LoadingException e2) {
                        af.c(c, "Get loader interface failed." + e2.getClass().getSimpleName());
                    }
                    z = true;
                }
            } catch (LoadingException e3) {
                throw e3;
            }
        } catch (Exception e4) {
            af.c(c, "failed to load." + e4.getClass().getSimpleName());
        }
        HashMap<String, Boolean> hashMap = new HashMap<>();
        hashMap.put(str, Boolean.valueOf(z));
        f.set(hashMap);
        return new Bundle();
    }

    /* loaded from: classes9.dex */
    static final class a extends Exception {
        /* synthetic */ a(String str, byte b) {
            this(str);
        }

        private a(String str) {
            super(str);
        }
    }

    private static Context a(Context context, String str, Bundle bundle, IDynamicLoader iDynamicLoader) throws LoadingException {
        try {
            IObjectWrapper load = iDynamicLoader.load(ObjectWrapper.wrap(context), str, bundle.getInt("module_version"), ObjectWrapper.wrap(bundle));
            Object unwrap = ObjectWrapper.unwrap(load);
            if (unwrap == null) {
                af.c(c, "Get remote context is null, module:".concat(String.valueOf(str)));
                return null;
            }
            if (unwrap instanceof Context) {
                af.b(c, "Get context for module:" + str + " success.");
                return (Context) unwrap;
            }
            if (!unwrap.getClass().getName().equals(LoadingException.class.getName())) {
                af.c(c, "Get remote context is null, module:".concat(String.valueOf(str)));
                return null;
            }
            Bundle bundle2 = (Bundle) ObjectWrapper.unwrap(load).getClass().getDeclaredMethod("getBundle", new Class[0]).invoke(ObjectWrapper.unwrap(load), new Object[0]);
            af.c(c, "Successfully get the bundle in exception.");
            throw new LoadingException("Failed to load, please check the bundle in exception.", bundle2);
        } catch (LoadingException e2) {
            throw e2;
        } catch (Exception e3) {
            af.c(c, "Failed to get module context for:" + str + " " + e3.getClass().getSimpleName());
            return null;
        }
    }

    private DynamicModule(Context context) {
        this.i = context;
    }
}
