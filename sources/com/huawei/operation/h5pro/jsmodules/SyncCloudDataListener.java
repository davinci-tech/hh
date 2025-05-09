package com.huawei.operation.h5pro.jsmodules;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.health.h5pro.utils.GeneralUtil;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.gxf;
import health.compact.a.BroadcastManagerUtil;
import java.lang.ref.WeakReference;

/* loaded from: classes5.dex */
class SyncCloudDataListener {
    private static final String TAG = "SyncCloudDataListener";
    private SyncCloudDataReceiver mSyncCloudDataReceiver;

    SyncCloudDataListener() {
    }

    void registerSyncCloudDataListener(JsBaseModule jsBaseModule, long j) {
        if (this.mSyncCloudDataReceiver != null) {
            LogUtil.h(TAG, "registerSyncCloudDataListener: registered listener");
            return;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("sync_cloud_data_action");
        this.mSyncCloudDataReceiver = new SyncCloudDataReceiver(jsBaseModule, j);
        BroadcastManagerUtil.bFz_(gxf.d(), this.mSyncCloudDataReceiver, intentFilter);
    }

    void unRegisterSyncCloudDataListener(JsBaseModule jsBaseModule, long j) {
        unRegisterSyncCloudDataListener();
        jsBaseModule.onSuccessCallback(j);
    }

    void unRegisterSyncCloudDataListener() {
        if (this.mSyncCloudDataReceiver == null) {
            return;
        }
        BroadcastManagerUtil.bFJ_(gxf.d(), this.mSyncCloudDataReceiver);
        this.mSyncCloudDataReceiver = null;
    }

    static class SyncCloudDataReceiver extends BroadcastReceiver {
        private final long mCallbackId;
        private final WeakReference<JsBaseModule> mJsBaseModule;

        SyncCloudDataReceiver(JsBaseModule jsBaseModule, long j) {
            this.mJsBaseModule = new WeakReference<>(jsBaseModule);
            this.mCallbackId = j;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null || !"sync_cloud_data_action".equals(intent.getAction())) {
                return;
            }
            String stringExtra = intent.getStringExtra("sync_cloud_data_status");
            if ("sync_cloud_data_finish".equals(stringExtra)) {
                onCallback(true);
            } else if ("sync_cloud_data_fail".equals(stringExtra)) {
                onCallback(false);
            }
        }

        private void onCallback(boolean z) {
            JsBaseModule jsBaseModule = (JsBaseModule) GeneralUtil.getReferent(this.mJsBaseModule);
            if (jsBaseModule == null) {
                LogUtil.h(SyncCloudDataListener.TAG, "onCallback: jsBaseModule is null - " + z);
            } else if (z) {
                jsBaseModule.onSuccessCallback(this.mCallbackId);
            } else {
                jsBaseModule.onFailureCallback(this.mCallbackId, "sync fail");
            }
        }
    }
}
