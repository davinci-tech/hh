package defpackage;

import com.huawei.fullscenarioservice.pluginwearota.PluginWearOtaApi;
import com.huawei.fullscenarioservice.pluginwearota.callback.CompleteCallback;
import com.huawei.fullscenarioservice.pluginwearota.callback.InitCallback;
import com.huawei.haf.bundle.AppBundlePluginProxy;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes3.dex */
public class bwf extends AppBundlePluginProxy<PluginWearOtaApi> implements PluginWearOtaApi {
    private static volatile bwf b;

    /* renamed from: a, reason: collision with root package name */
    private String f537a;
    private PluginWearOtaApi d;
    private String e;

    private bwf() {
        super("PluginWearOtaProxy", "PluginWearAbility", "com.huawei.health.pluginwearota.PluginWearOtaApiImpl");
        this.d = createPluginApi();
    }

    public static bwf a() {
        bwf bwfVar;
        if (b == null) {
            synchronized (bwf.class) {
                if (b == null) {
                    b = new bwf();
                }
                bwfVar = b;
            }
            return bwfVar;
        }
        return b;
    }

    @Override // com.huawei.haf.bundle.AppBundlePluginProxy
    public boolean isPluginAvaiable() {
        return this.d != null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.haf.bundle.AppBundlePluginProxy
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void initialize(PluginWearOtaApi pluginWearOtaApi) {
        pluginWearOtaApi.initDueSdk(this.f537a, this.e, null);
        this.d = pluginWearOtaApi;
    }

    @Override // com.huawei.fullscenarioservice.pluginwearota.PluginWearOtaApi
    public void initDueSdk(String str, String str2, InitCallback initCallback) {
        this.f537a = str;
        this.e = str2;
        PluginWearOtaApi pluginWearOtaApi = this.d;
        if (pluginWearOtaApi != null) {
            pluginWearOtaApi.initDueSdk(str, str2, initCallback);
        } else {
            LogUtil.h("PluginWearOtaProxy", "init loadPlugin");
            loadPlugin();
        }
    }

    @Override // com.huawei.fullscenarioservice.pluginwearota.PluginWearOtaApi
    public void checkNewVersion(String str, CompleteCallback completeCallback) {
        PluginWearOtaApi pluginWearOtaApi = this.d;
        if (pluginWearOtaApi != null) {
            pluginWearOtaApi.checkNewVersion(str, completeCallback);
        } else {
            LogUtil.h("PluginWearOtaProxy", "checkNewVersion mPluginWearOtaApi is null");
        }
    }

    @Override // com.huawei.fullscenarioservice.pluginwearota.PluginWearOtaApi
    public void getNewVersion(String str, CompleteCallback completeCallback) {
        PluginWearOtaApi pluginWearOtaApi = this.d;
        if (pluginWearOtaApi != null) {
            pluginWearOtaApi.getNewVersion(str, completeCallback);
        } else {
            LogUtil.h("PluginWearOtaProxy", "getNewVersion mPluginWearOtaApi is null");
            loadPlugin();
        }
    }

    @Override // com.huawei.fullscenarioservice.pluginwearota.PluginWearOtaApi
    public void silentUpgrade(String str, CompleteCallback completeCallback) {
        PluginWearOtaApi pluginWearOtaApi = this.d;
        if (pluginWearOtaApi != null) {
            pluginWearOtaApi.silentUpgrade(str, completeCallback);
        } else {
            LogUtil.h("PluginWearOtaProxy", "silentUpgrade mPluginWearOtaApi is null");
        }
    }

    @Override // com.huawei.fullscenarioservice.pluginwearota.PluginWearOtaApi
    public void setSettingSwitch(String str, String str2) {
        this.f537a = str;
        this.e = str2;
        PluginWearOtaApi pluginWearOtaApi = this.d;
        if (pluginWearOtaApi != null) {
            pluginWearOtaApi.setSettingSwitch(str, str2);
        } else {
            LogUtil.h("PluginWearOtaProxy", "setSettingSwitch mPluginWearOtaApi is null");
        }
    }

    @Override // com.huawei.fullscenarioservice.pluginwearota.PluginWearOtaApi
    public void getSettingSwitch(String str, CompleteCallback completeCallback) {
        PluginWearOtaApi pluginWearOtaApi = this.d;
        if (pluginWearOtaApi != null) {
            pluginWearOtaApi.getSettingSwitch(str, completeCallback);
        } else {
            LogUtil.h("PluginWearOtaProxy", "getSettingSwitch mPluginWearOtaApi is null");
        }
    }

    @Override // com.huawei.fullscenarioservice.pluginwearota.PluginWearOtaApi
    public void getDeviceNewVersion(String str, CompleteCallback completeCallback) {
        PluginWearOtaApi pluginWearOtaApi = this.d;
        if (pluginWearOtaApi != null) {
            pluginWearOtaApi.getDeviceNewVersion(str, completeCallback);
        } else {
            LogUtil.h("PluginWearOtaProxy", "getDeviceNewVersion mPluginWearOtaApi is null");
        }
    }

    @Override // com.huawei.fullscenarioservice.pluginwearota.PluginWearOtaApi
    public void doUpgrade(String str, String str2, CompleteCallback completeCallback) {
        PluginWearOtaApi pluginWearOtaApi = this.d;
        if (pluginWearOtaApi != null) {
            pluginWearOtaApi.doUpgrade(str, str2, completeCallback);
        } else {
            LogUtil.h("PluginWearOtaProxy", "doUpgrade mPluginWearOtaApi is null");
        }
    }

    @Override // com.huawei.fullscenarioservice.pluginwearota.PluginWearOtaApi
    public void getUpgradeStatus(String str, CompleteCallback completeCallback) {
        PluginWearOtaApi pluginWearOtaApi = this.d;
        if (pluginWearOtaApi != null) {
            pluginWearOtaApi.getUpgradeStatus(str, completeCallback);
        } else {
            LogUtil.h("PluginWearOtaProxy", "getUpgradeStatus mPluginWearOtaApi is null");
        }
    }

    @Override // com.huawei.fullscenarioservice.pluginwearota.PluginWearOtaApi
    public void registerUpgradeListener(String str, CompleteCallback completeCallback) {
        PluginWearOtaApi pluginWearOtaApi = this.d;
        if (pluginWearOtaApi != null) {
            pluginWearOtaApi.registerUpgradeListener(str, completeCallback);
        } else {
            LogUtil.h("PluginWearOtaProxy", "registerUpgradeListener mPluginWearOtaApi is null");
        }
    }

    @Override // com.huawei.fullscenarioservice.pluginwearota.PluginWearOtaApi
    public void unRegisterUpgradeListener(String str) {
        PluginWearOtaApi pluginWearOtaApi = this.d;
        if (pluginWearOtaApi != null) {
            pluginWearOtaApi.unRegisterUpgradeListener(str);
        } else {
            LogUtil.h("PluginWearOtaProxy", "unRegisterUpgradeListener mPluginWearOtaApi is null");
        }
    }
}
