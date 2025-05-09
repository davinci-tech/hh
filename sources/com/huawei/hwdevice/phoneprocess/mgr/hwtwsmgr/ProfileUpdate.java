package com.huawei.hwdevice.phoneprocess.mgr.hwtwsmgr;

import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.profile.client.connect.ServiceConnectCallback;
import com.huawei.profile.client.profile.CloudInteractionMode;
import com.huawei.profile.client.profile.ProfileClient;

/* loaded from: classes5.dex */
public enum ProfileUpdate {
    PROFILE_UPDATE_BY_APK(CloudInteractionMode.BY_PROFILE),
    PROFILE_UPDATE_BY_AAR(CloudInteractionMode.DIRECTLY);

    private static final String TAG = "ProfileUpdate";
    private ProfileClient mProfileClient;

    ProfileUpdate(CloudInteractionMode cloudInteractionMode) {
        this.mProfileClient = new ProfileClient(BaseApplication.getContext(), cloudInteractionMode);
    }

    public ProfileClient getProfileClient() {
        return this.mProfileClient;
    }

    public boolean isConnected() {
        return this.mProfileClient.hasConnected();
    }

    public void connectProfile(ServiceConnectCallback serviceConnectCallback) {
        if (isConnected()) {
            return;
        }
        this.mProfileClient.connect(serviceConnectCallback);
    }

    public void disconnectProfile() {
        if (this.mProfileClient.hasConnected()) {
            this.mProfileClient.disconnect();
        }
    }
}
