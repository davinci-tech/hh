package com.huawei.hms.push.task;

import com.huawei.hms.common.internal.constant.AuthInternalPickerConstant;

/* loaded from: classes9.dex */
public class ConsentTask extends BaseVoidTask {
    public ConsentTask(String str, String str2, String str3) {
        super(str, str2, str3);
    }

    @Override // com.huawei.hms.common.internal.TaskApiCall
    public int getApiLevel() {
        return 5;
    }

    @Override // com.huawei.hms.push.task.BaseVoidTask, com.huawei.hms.common.internal.TaskApiCall
    public int getMinApkVersion() {
        return AuthInternalPickerConstant.HMS_SDK_VERSION;
    }
}
