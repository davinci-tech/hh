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
import com.huawei.profile.profile.ProfileUtilsSdk;
import com.huawei.profile.utils.logger.DsLog;
import java.util.Map;

/* loaded from: classes6.dex */
public class PutCharacteristicToCloudSdk implements ProfileRequestInterface {
    private static final String TAG = "PutCharacteristicToCloud";
    private String baseUrl = CloudSettings.getWiseDeviceUrl() + "/wisedevice/device-shadow/v2/devices/";
    private Context mContext;
    private ProfileRequestInputParams mParams;

    @Override // com.huawei.profile.coordinator.ProfileRequestInterface
    public void request(Context context, ProfileRequestInputParams profileRequestInputParams, ProfileRequestCallback profileRequestCallback) {
        this.mParams = profileRequestInputParams;
        this.mContext = context;
        try {
            Map<String, String> generateRequestHeader = AccountClientSdk.getInstance(context).generateRequestHeader();
            ProfileHttpClient requestBodyCallback = new ProfileHttpClient(context).setUrl(prepareRequestUrl(profileRequestInputParams)).setRequestMethod("POST").setRequestBody(prepareRequestBody()).setSuccessCode(204).setRequestBodyCallback(new ProfileJsonResponseCallback(TAG, profileRequestCallback));
            RequestUtilsSdk.setHeaderMapToClient(generateRequestHeader, requestBodyCallback);
            requestBodyCallback.requestForResponseBody();
        } catch (ProfileRequestException e) {
            DsLog.et(TAG, "Failed to put service characteristic to cloud, status code = " + e.getErrorTypeValue() + " message = " + e.getMessage(), new Object[0]);
            profileRequestCallback.onFailure(e.getErrorTypeValue(), e.getMessage());
        }
    }

    @Override // com.huawei.profile.coordinator.ProfileRequestInterface
    public String prepareRequestUrl(ProfileRequestInputParams profileRequestInputParams) throws ProfileRequestException {
        return this.baseUrl + RequestUtilsSdk.getCloudDevId(this.mContext, profileRequestInputParams) + "/reported";
    }

    @Override // com.huawei.profile.coordinator.ProfileRequestInterface
    public String prepareRequestBody() throws ProfileRequestException {
        ProfileUtilsSdk profileUtilsSdk = ProfileUtilsSdk.getInstance(this.mContext);
        ProfileRequestInputParams profileRequestInputParams = this.mParams;
        if (profileRequestInputParams == null) {
            throw new ProfileRequestException(ProfileRequestExceptionType.INPUT_PARAM_INVALID, "ProfileRequestInputParams is invalid");
        }
        return RequestUtilsSdk.parseCharacterProfileToBodyJson(this.mParams, profileUtilsSdk.getCharacterInter(profileRequestInputParams.getLocalDevId(), this.mParams.getSid()));
    }
}
