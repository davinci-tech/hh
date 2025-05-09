package defpackage;

import android.content.Context;
import com.huawei.haf.dynamic.DynamicResourcesLoader;
import health.compact.a.AuthorizationUtils;

/* loaded from: classes.dex */
public class bxp extends DynamicResourcesLoader.WebViewLoadPolicy {
    @Override // com.huawei.haf.dynamic.DynamicResourcesLoader.WebViewLoadPolicy
    public boolean c(Context context) {
        return super.c(context) && AuthorizationUtils.a(context);
    }

    @Override // com.huawei.haf.dynamic.DynamicResourcesLoader.WebViewLoadPolicy
    public boolean b(Context context) {
        return !AuthorizationUtils.a(context);
    }
}
