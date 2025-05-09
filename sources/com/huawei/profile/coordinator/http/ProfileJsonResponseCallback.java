package com.huawei.profile.coordinator.http;

import com.huawei.profile.coordinator.ProfileRequestCallback;
import com.huawei.profile.coordinator.ProfileRequestResult;
import com.huawei.profile.coordinator.exception.ProfileRequestExceptionType;
import com.huawei.profile.utils.AnonymousUtil;
import com.huawei.profile.utils.logger.DsLog;

/* loaded from: classes6.dex */
public class ProfileJsonResponseCallback implements ProfileResponseCallback {
    private static final String REGISTER_DEVICE_TO_CLOUD_TAG = "RegisterDeviceToCloud";
    private static final String TAG = "ProfileJsonResponseCallback";
    private static final String UPDATE_DEVICE_TO_CLOUD_TAG = "UpdateDeviceToCloud";
    private ProfileRequestCallback callback;
    private String requestType;

    public ProfileJsonResponseCallback(String str, ProfileRequestCallback profileRequestCallback) {
        this.requestType = str;
        this.callback = profileRequestCallback;
    }

    @Override // com.huawei.profile.coordinator.http.ProfileResponseCallback
    public void onSuccess(String str) {
        DsLog.it(this.requestType, "success", new Object[0]);
        ProfileRequestResult profileRequestResult = new ProfileRequestResult();
        profileRequestResult.setJsonResult(str);
        this.callback.onSuccess(profileRequestResult);
    }

    @Override // com.huawei.profile.coordinator.http.ProfileResponseCallback
    public void onFailure(int i, ProfileHttpResponse profileHttpResponse) {
        if (profileHttpResponse == null) {
            this.callback.onFailure(ProfileRequestExceptionType.INPUT_PARAM_INVALID.getExceptionTypeValue(), "response is null");
            return;
        }
        DsLog.et(this.requestType, "Failed to request cloud, status code = " + i + " message = " + profileHttpResponse.getResponseMessage() + " detail = " + AnonymousUtil.anonymousJson(profileHttpResponse.getResponseBody()), new Object[0]);
        String responseMessage = profileHttpResponse.getResponseMessage();
        if (i == 400 && (REGISTER_DEVICE_TO_CLOUD_TAG.equals(this.requestType) || UPDATE_DEVICE_TO_CLOUD_TAG.equals(this.requestType))) {
            responseMessage = profileHttpResponse.getResponseBody();
        }
        this.callback.onFailure(i, responseMessage);
    }
}
