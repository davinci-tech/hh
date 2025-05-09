package com.huawei.hms.push;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import com.huawei.agconnect.config.AGConnectServicesConfig;
import com.huawei.hmf.tasks.Task;
import com.huawei.hmf.tasks.TaskCompletionSource;
import com.huawei.hms.aaid.constant.ErrorEnum;
import com.huawei.hms.aaid.task.PushClientBuilder;
import com.huawei.hms.api.Api;
import com.huawei.hms.api.HuaweiApiAvailability;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.common.HuaweiApi;
import com.huawei.hms.common.internal.AbstractClientBuilder;
import com.huawei.hms.common.internal.Preconditions;
import com.huawei.hms.mlsdk.common.AgConnectInfo;
import com.huawei.hms.push.task.ProfileTask;
import com.huawei.hms.push.utils.PushBiUtil;
import com.huawei.hms.support.api.entity.push.ProfileReq;
import com.huawei.hms.support.api.entity.push.PushNaming;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.JsonUtil;
import com.huawei.secure.android.common.encrypt.hash.SHA;

/* loaded from: classes9.dex */
public class HmsProfile {
    public static final int CUSTOM_PROFILE = 2;
    public static final int HUAWEI_PROFILE = 1;
    private static final String c = "HmsProfile";

    /* renamed from: a, reason: collision with root package name */
    private Context f5668a;
    private HuaweiApi<Api.ApiOptions.NoOptions> b;

    private HmsProfile(Context context) {
        this.f5668a = null;
        Preconditions.checkNotNull(context);
        this.f5668a = context;
        Api api = new Api(HuaweiApiAvailability.HMS_API_NAME_PUSH);
        if (context instanceof Activity) {
            this.b = new HuaweiApi<>((Activity) context, (Api<Api.ApiOptions>) api, (Api.ApiOptions) null, (AbstractClientBuilder) new PushClientBuilder());
        } else {
            this.b = new HuaweiApi<>(context, (Api<Api.ApiOptions>) api, (Api.ApiOptions) null, new PushClientBuilder());
        }
        this.b.setKitSdkVersion(61200300);
    }

    private Task<Void> a(int i, String str, int i2, String str2) {
        if (!isSupportProfile()) {
            TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
            taskCompletionSource.setException(ErrorEnum.ERROR_OPERATION_NOT_SUPPORTED.toApiException());
            return taskCompletionSource.getTask();
        }
        if (!TextUtils.isEmpty(str)) {
            String a2 = a(this.f5668a);
            if (TextUtils.isEmpty(a2)) {
                HMSLog.i(c, "agc connect services config missing project id.");
                TaskCompletionSource taskCompletionSource2 = new TaskCompletionSource();
                taskCompletionSource2.setException(ErrorEnum.ERROR_MISSING_PROJECT_ID.toApiException());
                return taskCompletionSource2.getTask();
            }
            if (str.equals(a2)) {
                str = "";
            }
        }
        ProfileReq profileReq = new ProfileReq();
        if (i == 0) {
            profileReq.setOperation(0);
            profileReq.setType(i2);
        } else {
            profileReq.setOperation(1);
        }
        String reportEntry = PushBiUtil.reportEntry(this.f5668a, PushNaming.PUSH_PROFILE);
        try {
            profileReq.setSubjectId(str);
            profileReq.setProfileId(SHA.sha256Encrypt(str2));
            profileReq.setPkgName(this.f5668a.getPackageName());
            return this.b.doWrite(new ProfileTask(PushNaming.PUSH_PROFILE, JsonUtil.createJsonString(profileReq), reportEntry));
        } catch (Exception e) {
            if (!(e.getCause() instanceof ApiException)) {
                TaskCompletionSource taskCompletionSource3 = new TaskCompletionSource();
                PushBiUtil.reportExit(this.f5668a, PushNaming.PUSH_PROFILE, reportEntry, ErrorEnum.ERROR_INTERNAL_ERROR);
                taskCompletionSource3.setException(ErrorEnum.ERROR_INTERNAL_ERROR.toApiException());
                return taskCompletionSource3.getTask();
            }
            TaskCompletionSource taskCompletionSource4 = new TaskCompletionSource();
            ApiException apiException = (ApiException) e.getCause();
            taskCompletionSource4.setException(apiException);
            PushBiUtil.reportExit(this.f5668a, PushNaming.PUSH_PROFILE, reportEntry, apiException.getStatusCode());
            return taskCompletionSource4.getTask();
        }
    }

    private boolean b(Context context) {
        return d.b(context) >= 110001400;
    }

    public static HmsProfile getInstance(Context context) {
        return new HmsProfile(context);
    }

    public Task<Void> addProfile(int i, String str) {
        return addProfile("", i, str);
    }

    public Task<Void> deleteProfile(String str) {
        return deleteProfile("", str);
    }

    public boolean isSupportProfile() {
        if (!d.d(this.f5668a)) {
            return true;
        }
        if (d.c()) {
            HMSLog.i(c, "current EMUI version below 9.1, not support profile operation.");
            return false;
        }
        if (b(this.f5668a)) {
            return true;
        }
        HMSLog.i(c, "current HwPushService.apk version below 11.0.1.400,please upgrade your HwPushService.apk version.");
        return false;
    }

    public Task<Void> addProfile(String str, int i, String str2) {
        if (i != 1 && i != 2) {
            HMSLog.i(c, "add profile type undefined.");
            TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
            taskCompletionSource.setException(ErrorEnum.ERROR_PUSH_ARGUMENTS_INVALID.toApiException());
            return taskCompletionSource.getTask();
        }
        if (!TextUtils.isEmpty(str2)) {
            return a(0, str, i, str2);
        }
        HMSLog.i(c, "add profile params is empty.");
        TaskCompletionSource taskCompletionSource2 = new TaskCompletionSource();
        taskCompletionSource2.setException(ErrorEnum.ERROR_PUSH_ARGUMENTS_INVALID.toApiException());
        return taskCompletionSource2.getTask();
    }

    public Task<Void> deleteProfile(String str, String str2) {
        if (!TextUtils.isEmpty(str2)) {
            return a(1, str, -1, str2);
        }
        HMSLog.e(c, "del profile params is empty.");
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        taskCompletionSource.setException(ErrorEnum.ERROR_PUSH_ARGUMENTS_INVALID.toApiException());
        return taskCompletionSource.getTask();
    }

    private static String a(Context context) {
        return AGConnectServicesConfig.fromContext(context).getString(AgConnectInfo.AgConnectKey.PROJECT_ID);
    }
}
