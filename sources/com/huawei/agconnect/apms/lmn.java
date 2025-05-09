package com.huawei.agconnect.apms;

import android.text.TextUtils;
import com.huawei.agconnect.apms.collect.model.basic.ApplicationInformation;
import com.huawei.agconnect.apms.collect.model.basic.DeviceInformation;
import com.huawei.agconnect.apms.collect.model.basic.PlatformInformation;
import com.huawei.agconnect.apms.collect.model.basic.UserSettingsInformation;

/* loaded from: classes8.dex */
public abstract class lmn {
    private String userId = Agent.getUserIdentifier();
    private String agentVersion = Agent.getVersion();
    private ApplicationInformation applicationInformation = Agent.getApplicationInformation();
    private DeviceInformation deviceInformation = Agent.getDeviceInformation();
    private PlatformInformation platformInformation = Agent.getPlatformInformation();
    private UserSettingsInformation userSettingsInformation = Agent.getUserSettingsInformation();

    public String getAgentVersion() {
        return TextUtils.isEmpty(this.agentVersion) ? "" : this.agentVersion;
    }

    public ApplicationInformation getApplicationInformation() {
        return this.applicationInformation;
    }

    public DeviceInformation getDeviceInformation() {
        return this.deviceInformation;
    }

    public PlatformInformation getPlatformInformation() {
        return this.platformInformation;
    }

    public String getUserId() {
        return TextUtils.isEmpty(this.userId) ? "" : this.userId;
    }

    public UserSettingsInformation getUserSettingsInformation() {
        return this.userSettingsInformation;
    }

    public abstract String toJsonString();
}
