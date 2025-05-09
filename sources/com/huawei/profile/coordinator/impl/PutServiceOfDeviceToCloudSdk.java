package com.huawei.profile.coordinator.impl;

import android.content.Context;
import com.google.gson.Gson;
import com.huawei.profile.account.AccountClientSdk;
import com.huawei.profile.coordinator.CloudSettings;
import com.huawei.profile.coordinator.ProfileRequestCallback;
import com.huawei.profile.coordinator.ProfileRequestConstants;
import com.huawei.profile.coordinator.ProfileRequestInputParams;
import com.huawei.profile.coordinator.ProfileRequestInterface;
import com.huawei.profile.coordinator.RequestUtilsSdk;
import com.huawei.profile.coordinator.bean.CloudProfileBean;
import com.huawei.profile.coordinator.exception.ProfileRequestException;
import com.huawei.profile.coordinator.exception.ProfileRequestExceptionType;
import com.huawei.profile.coordinator.http.ProfileHttpClient;
import com.huawei.profile.coordinator.http.ProfileJsonResponseCallback;
import com.huawei.profile.profile.ProfileUtilsSdk;
import com.huawei.profile.profile.ServiceProfile;
import com.huawei.profile.utils.logger.DsLog;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class PutServiceOfDeviceToCloudSdk implements ProfileRequestInterface {
    private static final String TAG = "PutServiceOfDeviceToCloud";
    private String baseUrl = CloudSettings.getWiseDeviceUrl() + "/wisedevice/device-registry/v2/devices/";
    private Context mContext;
    private ProfileRequestInputParams mParams;

    @Override // com.huawei.profile.coordinator.ProfileRequestInterface
    public void request(Context context, ProfileRequestInputParams profileRequestInputParams, ProfileRequestCallback profileRequestCallback) {
        this.mParams = profileRequestInputParams;
        this.mContext = context;
        try {
            Map<String, String> generateRequestHeader = AccountClientSdk.getInstance(context).generateRequestHeader();
            ProfileHttpClient requestBodyCallback = new ProfileHttpClient(context).setUrl(prepareRequestUrl(profileRequestInputParams)).setRequestMethod(ProfileRequestConstants.PUT_TYPE).setSuccessCode(204).setRequestBody(prepareRequestBody()).setRequestBodyCallback(new ProfileJsonResponseCallback(TAG, profileRequestCallback));
            RequestUtilsSdk.setHeaderMapToClient(generateRequestHeader, requestBodyCallback);
            requestBodyCallback.requestForResponseBody();
        } catch (ProfileRequestException e) {
            DsLog.et(TAG, "Failed to put service to cloud, status code = " + e.getErrorTypeValue() + " message = " + e.getMessage(), new Object[0]);
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
        return parseServiceProfilesToBodyJson(profileRequestInputParams.getLocalDevId());
    }

    private String parseServiceProfilesToBodyJson(String str) throws ProfileRequestException {
        List<ServiceProfile> servicesInter = ProfileUtilsSdk.getInstance(this.mContext).getServicesInter(str);
        if (ProfileUtilsSdk.isNull(servicesInter)) {
            throw new ProfileRequestException(ProfileRequestExceptionType.INPUT_PARAM_INVALID, "service profile is empty");
        }
        CloudProfileBean cloudProfileBean = new CloudProfileBean();
        ArrayList arrayList = new ArrayList();
        for (ServiceProfile serviceProfile : servicesInter) {
            CloudProfileBean.ServicesBean servicesBean = new CloudProfileBean.ServicesBean();
            servicesBean.setSt(serviceProfile.getType());
            servicesBean.setSid(serviceProfile.getId());
            arrayList.add(servicesBean);
        }
        cloudProfileBean.setServices(arrayList);
        return new Gson().toJson(cloudProfileBean);
    }
}
