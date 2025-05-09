package com.huawei.hms.support.picker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.huawei.hmf.tasks.Task;
import com.huawei.hmf.tasks.TaskCompletionSource;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.common.internal.constant.AuthInternalPickerConstant;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hms.support.picker.common.AccountPickerUtil;
import com.huawei.hms.support.picker.request.AccountPickerParams;
import com.huawei.hms.support.picker.result.AccountPickerResult;
import com.huawei.hms.support.picker.result.AuthAccountPicker;
import com.huawei.hms.support.picker.service.AccountPickerService;
import com.huawei.hms.support.picker.service.AccountPickerServiceImpl;

/* loaded from: classes4.dex */
public final class AccountPickerManager {
    public static AccountPickerService getService(Context context, AccountPickerParams accountPickerParams) {
        return new AccountPickerServiceImpl(context, accountPickerParams, AuthInternalPickerConstant.HMS_SDK_VERSION);
    }

    public static AccountPickerService getService(Activity activity, AccountPickerParams accountPickerParams) {
        return new AccountPickerServiceImpl(activity, accountPickerParams, AuthInternalPickerConstant.HMS_SDK_VERSION);
    }

    public static AccountPickerService getService(Context context, AccountPickerParams accountPickerParams, int i) {
        return new AccountPickerServiceImpl(context, accountPickerParams, AuthInternalPickerConstant.HMS_SDK_VERSION, i);
    }

    public static AccountPickerService getService(Activity activity, AccountPickerParams accountPickerParams, int i) {
        return new AccountPickerServiceImpl(activity, accountPickerParams, AuthInternalPickerConstant.HMS_SDK_VERSION, i);
    }

    public static Task<AuthAccountPicker> parseAuthResultFromIntent(Intent intent) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        AccountPickerResult signInResultFromIntent = AccountPickerUtil.getSignInResultFromIntent(intent);
        if (signInResultFromIntent == null) {
            taskCompletionSource.setException(new ApiException(new Status(8)));
        } else if (!signInResultFromIntent.isSuccess() || signInResultFromIntent.getAuthAccountPicker() == null) {
            taskCompletionSource.setException(new ApiException(signInResultFromIntent.getStatus()));
        } else {
            taskCompletionSource.setResult(signInResultFromIntent.getAuthAccountPicker());
        }
        return taskCompletionSource.getTask();
    }
}
