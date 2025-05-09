package com.huawei.profile.coordinator.impl;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.profile.account.AccountClientSdk;
import com.huawei.profile.coordinator.CloudSettings;
import com.huawei.profile.coordinator.ProfileRequestCallback;
import com.huawei.profile.coordinator.ProfileRequestConstants;
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
public class UpdateDeviceToCloudSdk implements ProfileRequestInterface {
    private static final String TAG = "UpdateDeviceToCloud";
    private String baseUrl = CloudSettings.getWiseDeviceUrl() + "/wisedevice/device-registry/v2/devices/";
    private Context mContext;
    private ProfileRequestInputParams mParams;

    @Override // com.huawei.profile.coordinator.ProfileRequestInterface
    public void request(Context context, ProfileRequestInputParams profileRequestInputParams, ProfileRequestCallback profileRequestCallback) {
        this.mParams = profileRequestInputParams;
        this.mContext = context;
        try {
            String prepareRequestUrl = prepareRequestUrl(profileRequestInputParams);
            String prepareRequestBody = prepareRequestBody();
            Map<String, String> generateRequestHeader = AccountClientSdk.getInstance(context).generateRequestHeader();
            ProfileHttpClient requestBody = new ProfileHttpClient(context).setUrl(prepareRequestUrl).setRequestMethod(ProfileRequestConstants.PUT_TYPE).setSuccessCode(204).setRequestBody(prepareRequestBody);
            RequestUtilsSdk.setHeaderMapToClient(generateRequestHeader, requestBody);
            requestBody.setRequestBodyCallback(new ProfileJsonResponseCallback(TAG, profileRequestCallback)).requestForResponseBody();
        } catch (ProfileRequestException e) {
            DsLog.et(TAG, "Failed to update device to cloud, status code = " + e.getErrorTypeValue() + " message = " + e.getMessage(), new Object[0]);
            profileRequestCallback.onFailure(e.getErrorTypeValue(), e.getMessage());
        }
    }

    @Override // com.huawei.profile.coordinator.ProfileRequestInterface
    public String prepareRequestUrl(ProfileRequestInputParams profileRequestInputParams) throws ProfileRequestException {
        return this.baseUrl + RequestUtilsSdk.getCloudDevId(this.mContext, profileRequestInputParams);
    }

    @Override // com.huawei.profile.coordinator.ProfileRequestInterface
    public String prepareRequestBody() throws ProfileRequestException {
        ProfileRequestInputParams profileRequestInputParams = this.mParams;
        if (profileRequestInputParams == null) {
            throw new ProfileRequestException(ProfileRequestExceptionType.INPUT_PARAM_INVALID, "ProfileRequestInputParams is invalid");
        }
        String localDevId = profileRequestInputParams.getLocalDevId();
        if (TextUtils.isEmpty(localDevId)) {
            throw new ProfileRequestException(ProfileRequestExceptionType.INPUT_PARAM_INVALID, "local device id is empty.");
        }
        DeviceProfile deviceInter = ProfileUtilsSdk.getInstance(this.mContext).getDeviceInter(localDevId);
        if (!RequestUtilsSdk.isDevTypeValid(deviceInter)) {
            throw new ProfileRequestException(ProfileRequestExceptionType.INPUT_PARAM_INVALID, "device type is invalid.");
        }
        return RequestUtilsSdk.parseDeviceProfileToBodyJson(deviceInter);
    }
}
