package com.huawei.fullscenarioservice.pluginwearota;

import com.huawei.fullscenarioservice.pluginwearota.callback.CompleteCallback;
import com.huawei.fullscenarioservice.pluginwearota.callback.InitCallback;

/* loaded from: classes3.dex */
public interface PluginWearOtaApi {
    void checkNewVersion(String str, CompleteCallback completeCallback);

    void doUpgrade(String str, String str2, CompleteCallback completeCallback);

    void getDeviceNewVersion(String str, CompleteCallback completeCallback);

    void getNewVersion(String str, CompleteCallback completeCallback);

    void getSettingSwitch(String str, CompleteCallback completeCallback);

    void getUpgradeStatus(String str, CompleteCallback completeCallback);

    void initDueSdk(String str, String str2, InitCallback initCallback);

    void registerUpgradeListener(String str, CompleteCallback completeCallback);

    void setSettingSwitch(String str, String str2);

    void silentUpgrade(String str, CompleteCallback completeCallback);

    void unRegisterUpgradeListener(String str);
}
