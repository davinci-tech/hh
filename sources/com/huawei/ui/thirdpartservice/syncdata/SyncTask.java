package com.huawei.ui.thirdpartservice.syncdata;

import com.huawei.hihealth.HiHealthData;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OperationKey;
import com.huawei.ui.thirdpartservice.syncdata.constants.SyncDataConstant;
import defpackage.ixx;
import defpackage.sjs;
import java.io.File;
import java.util.HashMap;

/* loaded from: classes7.dex */
public abstract class SyncTask {
    private static final int DEFAULT_RETRY_COUNT = 2;
    private final int mCanRetryCount;
    protected String mDescription;
    protected File mGpxFile;
    protected HiHealthData mHiHealthData;
    private int mUploadCount = 0;
    protected String mUploadDomain;
    private UploadListener mUploadListener;

    public interface UploadListener {
        void tokenInvalidation(SyncTask syncTask);

        void uploadStatusCallback(SyncTask syncTask, boolean z);
    }

    private int getCanRetryCount() {
        return 2;
    }

    protected abstract void doUpload();

    protected abstract String getTag();

    protected abstract void retryTooManyTimes();

    protected abstract void sportDataException();

    public SyncTask(HiHealthData hiHealthData, String str, File file) {
        this.mGpxFile = file;
        this.mHiHealthData = hiHealthData;
        this.mDescription = str;
        int canRetryCount = getCanRetryCount();
        this.mCanRetryCount = canRetryCount >= 0 ? canRetryCount : 0;
    }

    public HiHealthData getHealthData() {
        return this.mHiHealthData;
    }

    final boolean canRetry() {
        return this.mUploadCount <= this.mCanRetryCount;
    }

    final void setUploadListener(UploadListener uploadListener) {
        this.mUploadListener = uploadListener;
    }

    public void recordUploadBiEvent(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put(SyncDataConstant.BI_KEY_ACTIVITY_TYPE, str);
        hashMap.put(SyncDataConstant.BI_KEY_ACCOUNT_TYPE, str2);
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.THIRD_ACTIVITY_UPLOAD_2041094.value(), hashMap, 0);
        sjs.d(OperationKey.HEALTH_APP_SYNC_DATA_80070005.value(), str, str2, "", "Success");
    }

    public String toString() {
        return this.mHiHealthData.getStartTime() + "_" + this.mHiHealthData.getEndTime();
    }

    public void tokenInvalidation() {
        this.mUploadCount = 0;
        UploadListener uploadListener = this.mUploadListener;
        if (uploadListener != null) {
            uploadListener.tokenInvalidation(this);
        }
    }

    public void callResult(boolean z) {
        UploadListener uploadListener = this.mUploadListener;
        if (uploadListener != null) {
            uploadListener.uploadStatusCallback(this, z);
        }
    }

    public void upload() {
        if (this.mUploadCount > this.mCanRetryCount) {
            LogUtil.h(getTag(), "Exceeded retries");
            callResult(false);
        }
        this.mUploadCount++;
        if (this.mGpxFile == null) {
            callResult(true);
        } else {
            LogUtil.h(getTag(), "realExecuteUpload");
            doUpload();
        }
    }
}
