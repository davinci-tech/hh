package defpackage;

import com.huawei.haf.bundle.AppBundle;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.KeyValDbManager;

/* loaded from: classes5.dex */
public class jpr {
    public static void a(String str) {
        LogUtil.a("DevicePluginMgrUtil", "enter saveDownloadHiAiPluginFlag ,feature type is: ", str);
        if (!AppBundle.c().isBundleModule("PluginHiAiEngine") || bzo.c().isPluginAvaiable()) {
            return;
        }
        LogUtil.a("DevicePluginMgrUtil", "HiAI plugin not exist，save download Hi AI flag");
        KeyValDbManager.b(BaseApplication.getContext()).e("downloadHiAiPlugin", str);
    }
}
