package defpackage;

import android.app.Activity;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import com.huawei.haf.dynamic.DynamicLoaderException;
import com.huawei.haf.dynamic.DynamicResourcesLoader;
import java.io.File;

/* loaded from: classes8.dex */
public class xt {
    private static final xh e = new xh("SplitInstallHelper");

    public static void e(Context context) {
        if (ei_(context, context.getResources())) {
            return;
        }
        e.a("Failed to load context resources", new Object[0]);
    }

    public static void ef_(Activity activity, Resources resources) {
        if (ei_(activity, resources)) {
            return;
        }
        e.a("Failed to load activity resources", new Object[0]);
    }

    public static void eg_(Service service) {
        if (ei_(service, service.getBaseContext().getResources())) {
            return;
        }
        e.a("Failed to load service resources", new Object[0]);
    }

    public static void eh_(BroadcastReceiver broadcastReceiver, Context context) {
        if (!"ReceiverRestrictedContext".equals(context.getClass().getSimpleName()) || ei_(((ContextWrapper) context).getBaseContext(), context.getResources())) {
            return;
        }
        e.a("Failed to load receiver resources", new Object[0]);
    }

    public static void e(Context context, String str) {
        if (zi.b(context, str)) {
            return;
        }
        try {
            System.loadLibrary(str);
        } catch (UnsatisfiedLinkError e2) {
            String str2 = context.getApplicationInfo().nativeLibraryDir + File.pathSeparatorChar + System.mapLibraryName(str);
            if (new File(str2).exists()) {
                System.load(str2);
                return;
            }
            throw e2;
        }
    }

    private static boolean ei_(Context context, Resources resources) {
        try {
            DynamicResourcesLoader.yk_(context, resources, zh.a().getLoadedModuleApkPaths());
            return true;
        } catch (DynamicLoaderException e2) {
            e.e(e2, "checkOrUpdateResources fail.", new Object[0]);
            return false;
        }
    }
}
