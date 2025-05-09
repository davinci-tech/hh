package com.huawei.hms.hmsscankit;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.util.Log;
import com.huawei.hms.common.Preconditions;
import com.huawei.hms.feature.dynamic.DynamicModule;
import com.huawei.hms.hmsscankit.api.IRemoteCreator;
import com.huawei.hms.scankit.p.b4;
import com.huawei.hms.scankit.p.o4;
import com.huawei.hms.scankit.p.y3;
import com.huawei.phoneservice.faq.base.constants.TrackConstants$Events;
import java.lang.reflect.InvocationTargetException;

/* loaded from: classes4.dex */
public class g {

    /* renamed from: a, reason: collision with root package name */
    public static boolean f4638a = false;
    public static boolean b = false;
    public static boolean c = false;
    static int d = Integer.MIN_VALUE;
    static int e = Integer.MIN_VALUE;
    private static volatile Context f;

    public static int a(Context context) {
        try {
            return context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.getInt("huawei_module_scankit_local", Integer.MAX_VALUE);
        } catch (PackageManager.NameNotFoundException unused) {
            o4.b(TrackConstants$Events.EXCEPTION, "NameNotFoundException");
            return Integer.MAX_VALUE;
        }
    }

    public static void b(Context context) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        context.getClassLoader().loadClass("com.huawei.hms.feature.DynamicModuleInitializer").getDeclaredMethod("initializeModule", Context.class).invoke(null, context);
    }

    static IRemoteCreator c(Context context) {
        Preconditions.checkNotNull(context);
        try {
            Context e2 = e(context);
            if (e2 == null) {
                return null;
            }
            Object newInstance = e2.getClassLoader().loadClass("com.huawei.hms.scankit.Creator").newInstance();
            if (newInstance instanceof IBinder) {
                return IRemoteCreator.Stub.asInterface((IBinder) newInstance);
            }
            return null;
        } catch (ClassNotFoundException unused) {
            o4.b(TrackConstants$Events.EXCEPTION, "ClassNotFoundException");
            return null;
        } catch (IllegalAccessException unused2) {
            o4.b(TrackConstants$Events.EXCEPTION, "IllegalAccessException");
            return null;
        } catch (InstantiationException unused3) {
            o4.b(TrackConstants$Events.EXCEPTION, "InstantiationException");
            return null;
        } catch (NoSuchMethodException unused4) {
            o4.b(TrackConstants$Events.EXCEPTION, "NoSuchMethodException");
            return null;
        } catch (InvocationTargetException unused5) {
            o4.b(TrackConstants$Events.EXCEPTION, "InvocationTargetException");
            return null;
        }
    }

    static IRemoteCreator d(Context context) {
        Preconditions.checkNotNull(context);
        try {
            Object newInstance = context.getClassLoader().loadClass("com.huawei.hms.scankit.Creator").newInstance();
            if (newInstance instanceof IBinder) {
                return IRemoteCreator.Stub.asInterface((IBinder) newInstance);
            }
        } catch (ClassNotFoundException unused) {
            o4.b(TrackConstants$Events.EXCEPTION, "ClassNotFoundException");
        } catch (IllegalAccessException unused2) {
            o4.b(TrackConstants$Events.EXCEPTION, "IllegalAccessException");
        } catch (InstantiationException unused3) {
            o4.b(TrackConstants$Events.EXCEPTION, "InvocationTargetException");
        }
        return null;
    }

    public static Context e(Context context) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Log.i("ScankitSDK", "ScankitSDK Version: SCAN2.12.0.301");
        b(context);
        if (f != null && !a()) {
            Log.i("ScankitSDK", "context has been inited");
            return f;
        }
        try {
            b4.f5742a = false;
            if (d == Integer.MIN_VALUE) {
                d = a(context);
            }
            Context moduleContext = DynamicModule.load(context.getApplicationContext(), DynamicModule.PREFER_REMOTE, "huawei_module_scankit").getModuleContext();
            if (e == Integer.MIN_VALUE) {
                e = DynamicModule.getRemoteVersion(context.getApplicationContext(), "huawei_module_scankit");
            }
            if (d >= 21200300) {
                c = true;
            } else {
                c = false;
            }
            String b2 = y3.b(context);
            o4.d("ScankitSDK", "local Version: " + d + " remote Version: " + e);
            if (!a() && d < e && !b2.equals("com.huawei.scanner")) {
                f4638a = true;
                b4.f5742a = true;
                b4.b = String.valueOf(e);
                Log.i("ScankitSDK", "use remote scankit " + e);
                f = moduleContext;
                return f;
            }
            o4.d("ScankitSDK", "use local Version: " + d);
            b(context);
            f4638a = false;
            f = null;
            return context;
        } catch (DynamicModule.LoadingException e2) {
            o4.b("ScankitSDK", "ClassNotFoundException exception " + e2.getMessage());
            b(context);
            return context;
        } catch (ClassNotFoundException unused) {
            o4.b("ScankitSDK", "ClassNotFoundException exception");
            b(context);
            return context;
        } catch (IllegalAccessException unused2) {
            o4.b("ScankitSDK", "IllegalAccessException exception");
            b(context);
            return context;
        } catch (NoSuchMethodException unused3) {
            o4.b("ScankitSDK", "NoSuchMethodException exception");
            b(context);
            return context;
        } catch (RuntimeException unused4) {
            o4.b("ScankitSDK", "other RuntimeException exception");
            b(context);
            return context;
        } catch (InvocationTargetException unused5) {
            o4.b("ScankitSDK", "InvocationTargetException exception");
            b(context);
            return context;
        } catch (Exception unused6) {
            o4.b("ScankitSDK", "Exception exception");
            b(context);
            return context;
        } catch (Throwable unused7) {
            o4.b("ScankitSDK", "Throwable exception");
            b(context);
            return context;
        }
    }

    public static boolean a() {
        return c && f4638a && b;
    }
}
