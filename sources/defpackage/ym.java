package defpackage;

import android.content.Context;
import android.content.Intent;
import com.huawei.android.bundlecore.UserConfirmationDialog;
import com.huawei.android.bundlecore.download.Downloader;
import com.huawei.android.bundlecore.update.ModuleUpdateService;
import health.compact.a.LogUtil;
import health.compact.a.ProcessUtil;

/* loaded from: classes8.dex */
public final class ym {
    public static void d(Context context) {
        zh.a().e();
        xk.d(context);
    }

    public static void d(Context context, Downloader downloader, Class<? extends UserConfirmationDialog> cls) {
        if (ProcessUtil.d()) {
            yk.c(context, downloader, cls);
        }
        zh.a().d();
    }

    public static boolean e(Context context, String str, String str2) {
        if (!ProcessUtil.d()) {
            return false;
        }
        try {
            Intent intent = new Intent("ModuleUpdateService");
            intent.setClassName(context, ModuleUpdateService.class.getName());
            intent.putExtra("new_module_info_version", str);
            intent.putExtra("new_module_info_path", str2);
            context.startService(intent);
            return true;
        } catch (IllegalStateException e) {
            LogUtil.e("Bundle_BundleFramework", "updateModuleInfo, ex=", LogUtil.a(e));
            return false;
        }
    }
}
