package com.huawei.health.plugincaaskit;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import com.huawei.haf.bundle.AppBundle;
import com.huawei.haf.bundle.AppBundleLauncher;
import com.huawei.haf.bundle.AppBundlePluginProxy;
import com.huawei.haf.bundle.InstallSessionState;
import com.huawei.haf.bundle.InstallStateListener;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsConstants;

/* loaded from: classes8.dex */
public class PluginCaaskitProxy extends AppBundlePluginProxy<PluginCaaskitApi> implements PluginCaaskitApi {
    private static volatile PluginCaaskitProxy d;
    private PluginCaaskitApi c;
    private final Handler e;

    public interface LoadListener {
        void onFail(int i);

        void onSuccess(int i);
    }

    private PluginCaaskitProxy() {
        super("PluginCaaskitProxy", "PluginCaaskit", "com.huawei.health.plugincaaskit.PluginCaaskitApiImpl");
        this.e = new Handler() { // from class: com.huawei.health.plugincaaskit.PluginCaaskitProxy.3
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                if (message == null) {
                    LogUtil.h("PluginCaaskitProxy", "handleMessage msg is null");
                } else {
                    if (message.what == 0) {
                        if (message.obj instanceof LoadListener) {
                            ((LoadListener) message.obj).onFail(-1);
                            return;
                        }
                        return;
                    }
                    LogUtil.h("PluginCaaskitProxy", "handleMessage default what:", Integer.valueOf(message.what));
                }
            }
        };
        this.c = createPluginApi();
    }

    public static PluginCaaskitProxy e() {
        PluginCaaskitProxy pluginCaaskitProxy;
        if (d == null) {
            synchronized (PluginCaaskitProxy.class) {
                if (d == null) {
                    d = new PluginCaaskitProxy();
                }
                pluginCaaskitProxy = d;
            }
            return pluginCaaskitProxy;
        }
        return d;
    }

    @Override // com.huawei.haf.bundle.AppBundlePluginProxy
    public boolean isPluginAvaiable() {
        return this.c != null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.haf.bundle.AppBundlePluginProxy
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void initialize(PluginCaaskitApi pluginCaaskitApi) {
        this.c = pluginCaaskitApi;
    }

    public void e(Context context, final LoadListener loadListener) {
        if (d()) {
            Intent intent = new Intent();
            AppBundle.e().getInstallManager().registerListener(new InstallStateListener() { // from class: com.huawei.health.plugincaaskit.PluginCaaskitProxy.5
                @Override // com.huawei.haf.bundle.InstallStateListener
                public void onStateUpdate(InstallSessionState installSessionState) {
                    if (installSessionState.moduleNames().contains("PluginCaaskit") && installSessionState.status() == 6) {
                        loadListener.onFail(-1);
                        AppBundle.e().getInstallManager().unregisterListener(this);
                    } else if (installSessionState.moduleNames().contains("PluginCaaskit")) {
                        if (installSessionState.status() == 7 || installSessionState.status() == 9) {
                            loadListener.onFail(-1);
                            AppBundle.e().getInstallManager().unregisterListener(this);
                        }
                    }
                }
            });
            launchActivity(context, intent, new AppBundleLauncher.InstallCallback() { // from class: com.huawei.health.plugincaaskit.PluginCaaskitProxy.2
                @Override // com.huawei.haf.bundle.AppBundleLauncher.InstallCallback
                public boolean call(Context context2, Intent intent2) {
                    LogUtil.a("PluginCaaskitProxy", "downloadCaaskit launchActivity call");
                    LoadListener loadListener2 = loadListener;
                    if (loadListener2 != null) {
                        loadListener2.onSuccess(0);
                    }
                    return false;
                }
            });
        } else {
            if (isPluginAvaiable()) {
                loadListener.onSuccess(0);
                return;
            }
            Message obtainMessage = this.e.obtainMessage();
            obtainMessage.obj = loadListener;
            obtainMessage.what = 0;
            this.e.sendMessageDelayed(obtainMessage, OpAnalyticsConstants.H5_LOADING_DELAY);
            loadPlugin(new AppBundleLauncher.InstallCallback() { // from class: com.huawei.health.plugincaaskit.PluginCaaskitProxy.4
                @Override // com.huawei.haf.bundle.AppBundleLauncher.InstallCallback
                public boolean call(Context context2, Intent intent2) {
                    LogUtil.a("PluginCaaskitProxy", "loadModule call");
                    PluginCaaskitProxy.this.e.removeMessages(0);
                    loadListener.onSuccess(0);
                    return false;
                }
            });
        }
    }

    private boolean d() {
        return AppBundle.c().isBundleModule("PluginCaaskit") && !AppBundle.e().getInstalledModules().contains("PluginCaaskit");
    }
}
