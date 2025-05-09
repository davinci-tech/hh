package com.huawei.profile.coordinator.impl;

import android.content.Context;
import com.huawei.profile.account.AccountClientSdk;
import com.huawei.profile.coordinator.CloudSettings;
import com.huawei.profile.coordinator.ProfileRequestCallback;
import com.huawei.profile.coordinator.ProfileRequestInputParams;
import com.huawei.profile.coordinator.ProfileRequestInterface;
import com.huawei.profile.coordinator.RequestUtilsSdk;
import com.huawei.profile.coordinator.exception.ProfileRequestException;
import com.huawei.profile.coordinator.exception.ProfileRequestExceptionType;
import com.huawei.profile.coordinator.http.ProfileHttpClient;
import com.huawei.profile.coordinator.http.ProfileJsonResponseCallback;
import com.huawei.profile.profile.DeviceProfile;
import com.huawei.profile.profile.ProfileUtilsSdk;
import com.huawei.profile.utils.logger.DsLog;
import java.util.Map;

/* loaded from: classes6.dex */
public class RegisterDeviceToCloudSdk implements ProfileRequestInterface {
    private static final String TAG = "RegisterDeviceToCloud";
    private String baseUrl = CloudSettings.getWiseDeviceUrl() + "/wisedevice/device-registry/v2/devices";
    private ProfileRequestInputParams mParams;
    private ProfileUtilsSdk profileUtilsSdk;

    @Override // com.huawei.profile.coordinator.ProfileRequestInterface
    public void request(Context context, ProfileRequestInputParams profileRequestInputParams, ProfileRequestCallback profileRequestCallback) {
        this.mParams = profileRequestInputParams;
        String prepareRequestUrl = prepareRequestUrl(profileRequestInputParams);
        this.profileUtilsSdk = ProfileUtilsSdk.getInstance(context);
        try {
            String prepareRequestBody = prepareRequestBody();
            Map<String, String> generateRequestHeader = AccountClientSdk.getInstance(context).generateRequestHeader();
            ProfileHttpClient requestBody = new ProfileHttpClient(context).setUrl(prepareRequestUrl).setRequestMethod("POST").setRequestBody(prepareRequestBody);
            RequestUtilsSdk.setHeaderMapToClient(generateRequestHeader, requestBody);
            requestBody.setRequestBodyCallback(new ProfileJsonResponseCallback(TAG, profileRequestCallback)).requestForResponseBody();
        } catch (ProfileRequestException e) {
            DsLog.et(TAG, "Failed to register device to cloud, status code = " + e.getErrorTypeValue() + " message = " + e.getMessage(), new Object[0]);
            profileRequestCallback.onFailure(e.getErrorTypeValue(), e.getMessage());
        }
    }

    @Override // com.huawei.profile.coordinator.ProfileRequestInterface
    public String prepareRequestUrl(ProfileRequestInputParams profileRequestInputParams) {
        return this.baseUrl;
    }

    @Override // com.huawei.profile.coordinator.ProfileRequestInterface
    public String prepareRequestBody() throws ProfileRequestException {
        ProfileRequestInputParams profileRequestInputParams = this.mParams;
        if (profileRequestInputParams == null) {
            throw new ProfileRequestException(ProfileRequestExceptionType.INPUT_PARAM_INVALID, "ProfileRequestInputParams is invalid");
        }
        String localDevId = profileRequestInputParams.getLocalDevId();
        ProfileUtilsSdk profileUtilsSdk = this.profileUtilsSdk;
        if (profileUtilsSdk == null) {
            throw new ProfileRequestException(ProfileRequestExceptionType.INPUT_PARAM_INVALID, "ProfileUtils is invalid");
        }
        DeviceProfile deviceInter = profileUtilsSdk.getDeviceInter(localDevId);
        if (!RequestUtilsSdk.isDevTypeValid(deviceInter)) {
            throw new ProfileRequestException(ProfileRequestExceptionType.INPUT_PARAM_INVALID, "device type is invalid.");
        }
        return RequestUtilsSdk.parseDeviceProfileToBodyJson(deviceInter);
    }
}
