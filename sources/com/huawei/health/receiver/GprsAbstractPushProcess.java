package com.huawei.health.receiver;

import android.content.Context;
import com.huawei.health.device.api.EcologyDeviceApi;
import com.huawei.health.messagecenter.model.CommonUtil;
import com.huawei.health.receiver.GprsAbstractPushProcess;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import defpackage.ezq;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes3.dex */
public abstract class GprsAbstractPushProcess {
    private static final String TAG = "GprsAbstractPushProcess";
    private static final String TAG_RELEASE = "DEVMGR_GprsAbstractPushProcess";
    private volatile boolean mIsShowNotifySuccess = false;

    public interface ProcessResult {
        void onResult();
    }

    protected abstract void process(Context context, ezq.e eVar, ProcessResult processResult);

    public void processPushMsg(Context context, ezq.e eVar) {
        if (context == null) {
            LogUtil.b(TAG, "processPushMsg message is null return.");
        } else {
            this.mIsShowNotifySuccess = false;
            startSyncCloud(context, eVar);
        }
    }

    private void startSyncCloud(final Context context, final ezq.e eVar) {
        LogUtil.a(TAG, "startSyncCloud");
        ((EcologyDeviceApi) Services.c("EcologyDevice", EcologyDeviceApi.class)).syncCloud(Integer.parseInt(eVar.d), true, new IBaseResponseCallback() { // from class: ezi
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                GprsAbstractPushProcess.this.m431x7459da64(context, eVar, i, obj);
            }
        });
    }

    /* renamed from: lambda$startSyncCloud$1$com-huawei-health-receiver-GprsAbstractPushProcess, reason: not valid java name */
    public /* synthetic */ void m431x7459da64(Context context, ezq.e eVar, int i, Object obj) {
        if (i == Integer.parseInt("0") || i == Integer.parseInt(BleConstants.ERRCODE_TIMEOUT)) {
            LogUtil.a(TAG, "startSyncCloud syncCloud success");
            if (this.mIsShowNotifySuccess) {
                LogUtil.h(TAG, "startSyncCloud show notify is success");
                return;
            } else if (!CommonUtil.isSystemBarNoticeSwitchOnOrDefault(context)) {
                ReleaseLogUtil.d(TAG_RELEASE, "Notification closed.");
                return;
            } else {
                process(context, eVar, new ProcessResult() { // from class: ezk
                    @Override // com.huawei.health.receiver.GprsAbstractPushProcess.ProcessResult
                    public final void onResult() {
                        GprsAbstractPushProcess.this.m430x46814005();
                    }
                });
                return;
            }
        }
        ReleaseLogUtil.c(TAG_RELEASE, "startSyncCloud syncCloud failed, errorCode:", Integer.valueOf(i));
    }

    /* renamed from: lambda$startSyncCloud$0$com-huawei-health-receiver-GprsAbstractPushProcess, reason: not valid java name */
    public /* synthetic */ void m430x46814005() {
        this.mIsShowNotifySuccess = true;
    }
}
