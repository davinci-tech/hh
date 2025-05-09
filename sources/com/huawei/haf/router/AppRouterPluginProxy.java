package com.huawei.haf.router;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.bundle.AppBundlePluginProxy;
import com.huawei.haf.router.core.AppRouterHelper;
import com.huawei.haf.router.facade.template.ServiceProvider;

/* loaded from: classes.dex */
public abstract class AppRouterPluginProxy<T extends ServiceProvider> extends AppBundlePluginProxy<T> implements ServiceProvider {
    private final String mPath;
    private final Class<T> mServiceApi;

    @Override // com.huawei.haf.router.facade.template.ServiceProvider
    public void init(Context context) {
    }

    protected AppRouterPluginProxy(String str, String str2, Class<T> cls) {
        this(str, str2, cls, null);
    }

    protected AppRouterPluginProxy(String str, String str2, Class<T> cls, String str3) {
        super(str, str2, null);
        this.mServiceApi = cls;
        this.mPath = str3;
    }

    public final Guidepost build(String str) {
        return AppRouterHelper.d(str, getPluginName());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.haf.bundle.AppBundlePluginProxy
    public final T getPluginService() {
        if (!AppRouter.c(BaseApplication.e(), getPluginName())) {
            return null;
        }
        if (TextUtils.isEmpty(this.mPath)) {
            return (T) AppRouter.a(this.mServiceApi);
        }
        return (T) AppRouter.e(this.mPath, this.mServiceApi);
    }
}
