package defpackage;

import android.app.Activity;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.res.Resources;
import com.huawei.haf.bundle.AppBundleResources;

/* loaded from: classes8.dex */
public final class zo implements AppBundleResources {
    @Override // com.huawei.haf.bundle.AppBundleResources
    public void loadResources(Context context) {
        xt.e(context);
    }

    @Override // com.huawei.haf.bundle.AppBundleResources
    public void loadResources(Activity activity, Resources resources) {
        xt.ef_(activity, resources);
    }

    @Override // com.huawei.haf.bundle.AppBundleResources
    public void loadResources(Service service) {
        xt.eg_(service);
    }

    @Override // com.huawei.haf.bundle.AppBundleResources
    public void loadResources(BroadcastReceiver broadcastReceiver, Context context) {
        xt.eh_(broadcastReceiver, context);
    }

    @Override // com.huawei.haf.bundle.AppBundleResources
    public void loadLibrary(Context context, String str) {
        xt.e(context, str);
    }
}
