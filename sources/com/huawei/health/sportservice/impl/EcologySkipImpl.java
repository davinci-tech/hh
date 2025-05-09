package com.huawei.health.sportservice.impl;

import android.os.Bundle;
import com.huawei.btsportdevice.callback.MessageOrStateCallback;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.sportservice.SportLifecycle;
import com.huawei.health.sportservice.datasource.SkipDataAiSource;
import com.huawei.health.sportservice.inter.EcologySkipApi;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.cei;
import defpackage.mwq;

/* loaded from: classes8.dex */
public class EcologySkipImpl implements SportLifecycle, EcologySkipApi {
    private static final String TAG = "Track_EcologySkipImpl";
    private MessageOrStateCallback mMessageOrStateCallback = new MessageOrStateCallback() { // from class: com.huawei.health.sportservice.impl.EcologySkipImpl.1
        @Override // com.huawei.btsportdevice.callback.MessageOrStateCallback
        public void onNewMessage(int i, Bundle bundle) {
            if (bundle == null) {
                LogUtil.a(EcologySkipImpl.TAG, "bundle == null ");
            } else {
                LogUtil.a(EcologySkipImpl.TAG, "MessageOrStateCallback ", bundle.toString());
            }
        }

        @Override // com.huawei.btsportdevice.callback.MessageOrStateCallback
        public void onStateChange(String str) {
            LogUtil.a(EcologySkipImpl.TAG, "onStateChange ", str);
            if (str != null) {
                EcologySkipImpl.this.setBleStateListener(str);
            }
        }
    };

    @Override // com.huawei.health.sportservice.inter.EcologySkipApi
    public void setBleStateListener(String str) {
    }

    @Override // com.huawei.health.sportservice.inter.EcologySkipApi
    public void updateDataToSource(Bundle bundle) {
    }

    public void init() {
        cei.b().setMessageOrStateCallback(BaseApplication.wa_().getClass().getSimpleName(), this.mMessageOrStateCallback, true);
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onCountDown() {
        LogUtil.a(TAG, "onCountDown Enter");
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        LogUtil.a(TAG, "onPreSport Enter");
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onStartSport() {
        LogUtil.a(TAG, "onPreSport Enter");
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onStopSport() {
        LogUtil.a(TAG, "onStopSport Enter");
        cei.b().removeMessageOrStateCallback(BaseApplication.wa_().getClass().getSimpleName(), true);
    }

    @Override // com.huawei.health.sportservice.inter.EcologySkipApi
    public void setAiRopeListener(SkipDataAiSource skipDataAiSource) {
        if (skipDataAiSource != null) {
            mwq.d().setRopeListener(skipDataAiSource);
        }
    }
}
