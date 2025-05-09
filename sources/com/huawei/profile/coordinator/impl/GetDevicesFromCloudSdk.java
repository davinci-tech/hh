package com.huawei.profile.coordinator.impl;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.profile.account.AccountClientSdk;
import com.huawei.profile.coordinator.CloudSettings;
import com.huawei.profile.coordinator.ProfileRequestCallback;
import com.huawei.profile.coordinator.ProfileRequestInputParams;
import com.huawei.profile.coordinator.ProfileRequestInterface;
import com.huawei.profile.coordinator.RequestUtilsSdk;
import com.huawei.profile.coordinator.exception.ProfileRequestException;
import com.huawei.profile.coordinator.http.ProfileHttpClient;
import com.huawei.profile.coordinator.http.ProfileJsonResponseCallback;
import com.huawei.profile.profile.ProfileUtilsSdk;
import com.huawei.profile.utils.logger.DsLog;
import java.util.Map;

/* loaded from: classes6.dex */
public class GetDevicesFromCloudSdk implements ProfileRequestInterface {
    private static final String DEVICE_ID_URL = "devId=";
    private static final String DEVICE_TYPE_URL = "devType=";
    private static final String SEPARATOR_QUESTION_MARK = "?";
    private static final String TAG = "GetDevicesFromCloud";
    private String baseUrl = CloudSettings.getWiseDeviceUrl() + "/wisedevice/scenario-service/v2/devices/registry";
    private Context mContext;
    private ProfileRequestInputParams mParams;
    private ProfileUtilsSdk profileUtilsSdk;

    @Override // com.huawei.profile.coordinator.ProfileRequestInterface
    public void request(Context context, ProfileRequestInputParams profileRequestInputParams, ProfileRequestCallback profileRequestCallback) {
        this.mParams = profileRequestInputParams;
        this.mContext = context;
        try {
            Map<String, String> generateRequestHeader = AccountClientSdk.getInstance(context).generateRequestHeader();
            ProfileHttpClient requestBodyCallback = new ProfileHttpClient(context).setUrl(prepareRequestUrl(profileRequestInputParams)).setRequestMethod("GET").setRequestBodyCallback(new ProfileJsonResponseCallback(TAG, profileRequestCallback));
            RequestUtilsSdk.setHeaderMapToClient(generateRequestHeader, requestBodyCallback);
            requestBodyCallback.requestForResponseBody();
        } catch (ProfileRequestException e) {
            DsLog.et(TAG, "Failed to get device from cloud, status code = " + e.getErrorType() + " message = " + e.getMessage(), new Object[0]);
            profileRequestCallback.onFailure(e.getErrorTypeValue(), e.getMessage());
        }
    }

    @Override // com.huawei.profile.coordinator.ProfileRequestInterface
    public String prepareRequestUrl(ProfileRequestInputParams profileRequestInputParams) {
        if (profileRequestInputParams == null) {
            DsLog.et(TAG, "the params is null", new Object[0]);
            return this.baseUrl;
        }
        if (!TextUtils.isEmpty(profileRequestInputParams.getLocalDevId())) {
            ProfileUtilsSdk profileUtilsSdk = ProfileUtilsSdk.getInstance(this.mContext);
            this.profileUtilsSdk = profileUtilsSdk;
            String cloudDevId = profileUtilsSdk.getCloudDevId(profileRequestInputParams.getLocalDevId());
            if (TextUtils.isEmpty(cloudDevId)) {
                return this.baseUrl;
            }
            return this.baseUrl + "?devId=" + cloudDevId;
        }
        String cloudDevId2 = profileRequestInputParams.getCloudDevId();
        if (!TextUtils.isEmpty(cloudDevId2)) {
            return this.baseUrl + "?devId=" + cloudDevId2;
        }
        String devType = profileRequestInputParams.getDevType();
        if (!TextUtils.isEmpty(devType)) {
            return this.baseUrl + "?devType=" + devType;
        }
        return this.baseUrl;
    }

    @Override // com.huawei.profile.coordinator.ProfileRequestInterface
    public String prepareRequestBody() {
        return "";
    }
}
