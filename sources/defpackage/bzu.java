package defpackage;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import com.huawei.haf.bundle.AppBundleLauncher;
import com.huawei.haf.bundle.AppBundlePluginProxy;
import com.huawei.health.baseapi.wallet.HealthInitWalletApi;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes.dex */
public class bzu extends AppBundlePluginProxy<HealthInitWalletApi> implements HealthInitWalletApi {
    private static volatile bzu d;

    /* renamed from: a, reason: collision with root package name */
    private HealthInitWalletApi f574a;

    private bzu() {
        super("HealthInitWalletProxy", "PluginPay", "com.huawei.nfc.carrera.wear.HealthInitWalletApiImpl");
        this.f574a = createPluginApi();
    }

    public static bzu b() {
        bzu bzuVar;
        if (d == null) {
            synchronized (bzu.class) {
                if (d == null) {
                    d = new bzu();
                }
                bzuVar = d;
            }
            return bzuVar;
        }
        return d;
    }

    @Override // com.huawei.health.baseapi.wallet.HealthInitWalletApi
    public void initBaseConfig(Context context) {
        HealthInitWalletApi healthInitWalletApi = this.f574a;
        if (healthInitWalletApi != null) {
            healthInitWalletApi.initBaseConfig(context);
        } else {
            loadPlugin();
        }
    }

    @Override // com.huawei.health.baseapi.wallet.HealthInitWalletApi
    public void initHealthPayAdapter(Context context) {
        HealthInitWalletApi healthInitWalletApi = this.f574a;
        if (healthInitWalletApi != null) {
            healthInitWalletApi.initHealthPayAdapter(context);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.haf.bundle.AppBundlePluginProxy
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void initialize(HealthInitWalletApi healthInitWalletApi) {
        healthInitWalletApi.initBaseConfig(BaseApplication.getContext());
        this.f574a = healthInitWalletApi;
        LogUtil.a("HealthInitWalletProxy", "initialize initHealthPayAdapter");
        initHealthPayAdapter(BaseApplication.getContext());
    }

    @Override // com.huawei.health.baseapi.wallet.HealthInitWalletApi
    public void walletAccountRemove(Context context, Intent intent) {
        HealthInitWalletApi healthInitWalletApi = this.f574a;
        if (healthInitWalletApi != null) {
            healthInitWalletApi.walletAccountRemove(context, intent);
        } else {
            LogUtil.a("HealthInitWalletProxy", "walletAccountRemove mHealthInitWalletApi is null");
        }
    }

    @Override // com.huawei.health.baseapi.wallet.HealthInitWalletApi
    public IBinder getServiceBinder(Context context, String str, Intent intent) {
        HealthInitWalletApi healthInitWalletApi = this.f574a;
        if (healthInitWalletApi != null) {
            return healthInitWalletApi.getServiceBinder(context, str, intent);
        }
        LogUtil.a("HealthInitWalletProxy", "getServiceBinder mHealthInitWalletApi is null");
        return null;
    }

    @Override // com.huawei.health.baseapi.wallet.HealthInitWalletApi
    public void serviceOnCreate(Context context, String str) {
        HealthInitWalletApi healthInitWalletApi = this.f574a;
        if (healthInitWalletApi != null) {
            healthInitWalletApi.serviceOnCreate(context, str);
        } else {
            LogUtil.a("HealthInitWalletProxy", "serviceOnCreate mHealthInitWalletApi is null");
        }
    }

    @Override // com.huawei.health.baseapi.wallet.HealthInitWalletApi
    public void serviceOnDestroy(Context context, String str) {
        HealthInitWalletApi healthInitWalletApi = this.f574a;
        if (healthInitWalletApi != null) {
            healthInitWalletApi.serviceOnDestroy(context, str);
        } else {
            LogUtil.a("HealthInitWalletProxy", "serviceOnDestory mHealthInitWalletApi is null");
        }
    }

    @Override // com.huawei.health.baseapi.wallet.HealthInitWalletApi
    public void pushWearWalletMessage(final Context context, final String str) {
        HealthInitWalletApi healthInitWalletApi = this.f574a;
        if (healthInitWalletApi != null) {
            healthInitWalletApi.pushWearWalletMessage(context, str);
        } else {
            loadPlugin(new AppBundleLauncher.InstallCallback() { // from class: bzu.4
                @Override // com.huawei.haf.bundle.AppBundleLauncher.InstallCallback
                public boolean call(Context context2, Intent intent) {
                    LogUtil.a("HealthInitWalletProxy", "pushWearWallMessage loadPlugin call");
                    bzu.this.pushWearWalletMessage(context, str);
                    return false;
                }
            });
        }
    }

    @Override // com.huawei.haf.bundle.AppBundlePluginProxy
    public boolean isPluginAvaiable() {
        return this.f574a != null;
    }
}
