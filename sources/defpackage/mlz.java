package defpackage;

import com.huawei.haf.bundle.AppBundlePluginProxy;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.pluginappgallery.AppGalleryApi;
import health.compact.a.util.LogUtil;

/* loaded from: classes6.dex */
public class mlz extends AppBundlePluginProxy<AppGalleryApi> implements AppGalleryApi {
    private static final Object c = new Object();
    private static volatile mlz e;
    private AppGalleryApi b;

    private mlz() {
        super("AppGalleryProxy", "PluginWearAbility", "com.huawei.pluginappgallery.PluginAppGalleryImpl");
        LogUtil.d("AppGalleryProxy", "AppGalleryProxy");
        this.b = createPluginApi();
    }

    public static mlz c() {
        mlz mlzVar;
        LogUtil.d("AppGalleryProxy", "AppGalleryProxy");
        synchronized (c) {
            if (e == null) {
                e = new mlz();
            }
            mlzVar = e;
        }
        return mlzVar;
    }

    @Override // com.huawei.haf.bundle.AppBundlePluginProxy
    public boolean isPluginAvaiable() {
        Object[] objArr = new Object[2];
        objArr[0] = "isPluginAvaiable : ";
        objArr[1] = Boolean.valueOf(this.b != null);
        LogUtil.d("AppGalleryProxy", objArr);
        return this.b != null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.haf.bundle.AppBundlePluginProxy
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void initialize(AppGalleryApi appGalleryApi) {
        LogUtil.d("AppGalleryProxy", "initialize");
        this.b = appGalleryApi;
    }

    @Override // com.huawei.pluginappgallery.AppGalleryApi
    public void launchAppGallery(int i) {
        LogUtil.d("AppGalleryProxy", "launchAppGallery type : ", Integer.valueOf(i));
        if (isPluginAvaiable()) {
            this.b.launchAppGallery(i);
        } else {
            loadPlugin();
        }
    }

    @Override // com.huawei.pluginappgallery.AppGalleryApi
    public void initAppMarketAdapter() {
        LogUtil.d("AppGalleryProxy", "initAppMarketAdapter");
        if (isPluginAvaiable()) {
            this.b.initAppMarketAdapter();
        } else {
            loadPlugin();
        }
    }

    @Override // com.huawei.pluginappgallery.AppGalleryApi
    public void destroyAppMarketAdapter() {
        LogUtil.d("AppGalleryProxy", "destroyAppMarketAdapter");
        if (isPluginAvaiable()) {
            this.b.destroyAppMarketAdapter();
        } else {
            LogUtil.c("AppGalleryProxy", "destroyApi mAppGalleryApi is null");
        }
    }

    @Override // com.huawei.pluginappgallery.AppGalleryApi
    public void hasNewVersion() {
        LogUtil.d("AppGalleryProxy", "hasNewVersion");
        if (isPluginAvaiable()) {
            this.b.hasNewVersion();
        } else {
            loadPlugin();
        }
    }

    @Override // com.huawei.pluginappgallery.AppGalleryApi
    public void sendCommand(int i, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.d("AppGalleryProxy", "sendCommand type : ", Integer.valueOf(i));
        if (isPluginAvaiable()) {
            this.b.sendCommand(i, iBaseResponseCallback);
        } else {
            loadPlugin();
        }
    }
}
