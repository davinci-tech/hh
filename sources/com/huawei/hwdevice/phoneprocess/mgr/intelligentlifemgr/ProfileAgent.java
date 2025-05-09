package com.huawei.hwdevice.phoneprocess.mgr.intelligentlifemgr;

import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.profile.client.connect.ServiceConnectCallback;
import com.huawei.profile.client.profile.ProfileClient;
import defpackage.cpt;
import defpackage.jdx;

/* loaded from: classes9.dex */
public enum ProfileAgent {
    PROFILE_AGENT;

    private static final String TAG = "ProfileAgent";
    private ProfileClient clientAgent = new ProfileClient(BaseApplication.getContext());
    private boolean isConnected = false;

    ProfileAgent() {
    }

    public void connectProfile(final ServiceConnectCallback serviceConnectCallback) {
        LogUtil.a(TAG, "connectProfile start");
        jdx.b(new Runnable() { // from class: com.huawei.hwdevice.phoneprocess.mgr.intelligentlifemgr.ProfileAgent.2
            @Override // java.lang.Runnable
            public void run() {
                if (!ProfileAgent.this.clientAgent.hasConnected()) {
                    ProfileAgent.this.clientAgent.connect(serviceConnectCallback);
                } else {
                    serviceConnectCallback.onConnect();
                }
            }
        });
    }

    public boolean disconnectProfile() {
        if (this.clientAgent.hasConnected()) {
            this.isConnected = this.clientAgent.disconnect();
        } else {
            this.isConnected = false;
        }
        return this.isConnected;
    }

    public ProfileClient getClientAgent() {
        return this.clientAgent;
    }

    public boolean getConnected() {
        return this.isConnected;
    }

    public void setConnected(boolean z) {
        this.isConnected = ((Boolean) cpt.d(Boolean.valueOf(z))).booleanValue();
    }
}
