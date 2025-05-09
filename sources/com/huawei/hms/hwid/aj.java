package com.huawei.hms.hwid;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import com.huawei.hmf.tasks.Task;
import com.huawei.hmf.tasks.TaskCompletionSource;
import com.huawei.hms.api.Api;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.common.HuaweiApi;
import com.huawei.hms.common.internal.AbstractClientBuilder;
import com.huawei.hms.common.internal.constant.AuthInternalPickerConstant;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hms.support.api.entity.common.CommonNaming;
import com.huawei.hms.support.hianalytics.HiAnalyticsClient;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class aj extends HuaweiApi<Api.ApiOptions.NoOptions> {
    @Override // com.huawei.hms.common.HuaweiApi
    public int getApiLevel() {
        return 1;
    }

    public aj(Activity activity, Api<Api.ApiOptions.NoOptions> api, Api.ApiOptions.NoOptions noOptions, AbstractClientBuilder abstractClientBuilder) {
        super(activity, api, noOptions, abstractClientBuilder);
    }

    public aj(Context context, Api<Api.ApiOptions.NoOptions> api, Api.ApiOptions.NoOptions noOptions, AbstractClientBuilder abstractClientBuilder) {
        super(context, api, noOptions, abstractClientBuilder);
    }

    public Task<Void> a() {
        String a2 = b().a();
        if (TextUtils.isEmpty(a2)) {
            TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
            taskCompletionSource.setException(new ApiException(new Status(2020, CommonConstant.RETCODE.SMS_PARAM_ERROR)));
            return taskCompletionSource.getTask();
        }
        return doWrite(new ak(CommonNaming.startSmsRetriever, a2, HiAnalyticsClient.reportEntry(getContext(), CommonNaming.startSmsRetriever, AuthInternalPickerConstant.HMS_SDK_VERSION)));
    }

    public Task<Void> a(String str) {
        as.b("ReadSmsService", "startConsent enter", true);
        if (str != null && str.length() > 120) {
            TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
            taskCompletionSource.setException(new ApiException(new Status(2020, CommonConstant.RETCODE.SMS_PARAM_ERROR)));
            return taskCompletionSource.getTask();
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("packageName", getContext().getPackageName());
            jSONObject.put("phoneNumber", str);
        } catch (JSONException unused) {
            as.d("ReadSmsService", "toJson failed", true);
        }
        if (TextUtils.isEmpty(jSONObject.toString())) {
            TaskCompletionSource taskCompletionSource2 = new TaskCompletionSource();
            taskCompletionSource2.setException(new ApiException(new Status(2020, CommonConstant.RETCODE.SMS_PARAM_ERROR)));
            return taskCompletionSource2.getTask();
        }
        return doWrite(new al(CommonNaming.startConsent, jSONObject.toString(), HiAnalyticsClient.reportEntry(getContext(), CommonNaming.startConsent, AuthInternalPickerConstant.HMS_SDK_VERSION)));
    }

    private af b() {
        return new af(getContext().getPackageName());
    }
}
