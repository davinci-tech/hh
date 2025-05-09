package defpackage;

import android.app.Activity;
import android.content.Context;
import com.huawei.android.bundlecore.download.Downloader;
import com.huawei.android.bundlecore.install.remote.ModuleInstallSupervisor;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes8.dex */
public final class yk {
    private static final AtomicReference<ModuleInstallSupervisor> c = new AtomicReference<>();

    public static void c(Context context, Downloader downloader, Class<? extends Activity> cls) {
        if (a() == null) {
            yq yqVar = new yq(context, downloader, cls);
            c.set(yqVar);
            yqVar.initialize(context);
        }
    }

    public static ModuleInstallSupervisor a() {
        return c.get();
    }
}
