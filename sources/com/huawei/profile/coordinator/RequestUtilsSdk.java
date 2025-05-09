package com.huawei.profile.coordinator;

import android.content.Context;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huawei.profile.coordinator.bean.CloudProfileBean;
import com.huawei.profile.coordinator.bean.PutCharacteristicBodyBean;
import com.huawei.profile.coordinator.bean.ResponseDevIdBean;
import com.huawei.profile.coordinator.exception.ProfileRequestException;
import com.huawei.profile.coordinator.exception.ProfileRequestExceptionType;
import com.huawei.profile.coordinator.http.ProfileHttpClient;
import com.huawei.profile.coordinator.task.ProfileTaskPoolSdk;
import com.huawei.profile.profile.DeviceProfile;
import com.huawei.profile.profile.ProfileBaseUtils;
import com.huawei.profile.profile.ProfileUtilsSdk;
import com.huawei.profile.profile.ServiceCharacteristicProfile;
import com.huawei.profile.profile.ServiceProfile;
import com.huawei.profile.utils.AnonymousUtil;
import com.huawei.profile.utils.CloudJsonUtil;
import com.huawei.profile.utils.JsonUtils;
import com.huawei.profile.utils.SensitiveUtil;
import com.huawei.profile.utils.logger.DsLog;
import com.huawei.wearengine.sensor.DataResult;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

/* loaded from: classes6.dex */
public class RequestUtilsSdk {
    private static final String CLOUD_DATE_FORMAT = "yyyyMMdd'T'HHmmssSSS'Z'";
    private static final int DEVICE_TYPE_VALID_LENGTH = 3;
    private static final String TAG = "ProfileRequestUtils";

    private RequestUtilsSdk() {
    }

    public static String parseCloudDevIdFromJson(String str) {
        ResponseDevIdBean responseDevIdBean;
        try {
            responseDevIdBean = (ResponseDevIdBean) new Gson().fromJson(JsonUtils.sanitize(str), ResponseDevIdBean.class);
        } catch (JsonSyntaxException e) {
            DsLog.et(TAG, "Failed to parseCloudDevIdFromJson, error: " + e.getMessage(), new Object[0]);
            responseDevIdBean = null;
        }
        if (responseDevIdBean == null) {
            DsLog.et(TAG, "response devId is empty.", new Object[0]);
            return "";
        }
        return responseDevIdBean.getDevId();
    }

    public static List<DeviceProfile> parseCloudResponseToDeviceProfiles(Context context, String str) {
        Collection<CloudProfileBean> collection;
        try {
            collection = (Collection) new Gson().fromJson(JsonUtils.sanitize(str), new TypeToken<Collection<CloudProfileBean>>() { // from class: com.huawei.profile.coordinator.RequestUtilsSdk.1
            }.getType());
        } catch (JsonSyntaxException e) {
            DsLog.et(TAG, "Failed to parseCloudResponseToDeviceProfiles, error: " + e.getMessage(), new Object[0]);
            collection = null;
        }
        if (collection == null || collection.size() == 0) {
            DsLog.et(TAG, "response profile is empty.", new Object[0]);
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        for (CloudProfileBean cloudProfileBean : collection) {
            CloudProfileBean.DevInfoBean devInfo = cloudProfileBean.getDevInfo();
            DeviceProfile deviceProfile = new DeviceProfile();
            if (!deviceProfile.setId(devInfo.getUdid())) {
                DsLog.wt(TAG, "device " + AnonymousUtil.anonymousContent(devInfo.getUdid()) + " udid is empty or invalid", new Object[0]);
            } else {
                arrayList2.add(devInfo.getUdid());
                arrayList3.add(cloudProfileBean.getDevId());
                Map<String, Object> profile = deviceProfile.getProfile();
                profile.put("sn", devInfo.getDeviceSn());
                profile.put("udid", devInfo.getUdid());
                profile.put("model", devInfo.getModel());
                profile.put("devType", devInfo.getDevType());
                profile.put(ProfileRequestConstants.MANU, devInfo.getManu());
                profile.put("deviceName", devInfo.getDeviceName());
                profile.put("prodId", devInfo.getProdId());
                profile.put(ProfileRequestConstants.HIV, devInfo.getHiv());
                profile.put("mac", devInfo.getMac());
                profile.put(ProfileRequestConstants.FWV, devInfo.getFwv());
                profile.put(ProfileRequestConstants.HWV, devInfo.getHwv());
                profile.put(ProfileRequestConstants.SWV, devInfo.getSwv());
                profile.put(ProfileRequestConstants.PROT_TYPE, Integer.valueOf(devInfo.getProtType()));
                profile.put(ProfileRequestConstants.SUB_PROD_ID, devInfo.getSubProdId());
                profile.put(ProfileRequestConstants.INTERNAL_MODEL, devInfo.getInternalModel());
                arrayList.add(deviceProfile);
            }
        }
        if (arrayList2.size() == 0) {
            return arrayList;
        }
        ProfileUtilsSdk.getInstance(context).saveCloudDevIdList(arrayList2, arrayList3);
        return arrayList;
    }

    public static List<ServiceProfile> parseCloudResponseToServiceProfiles(String str, String str2) {
        try {
            List<CloudProfileBean> profileBeanFromJson = CloudJsonUtil.getProfileBeanFromJson(JsonUtils.sanitize(str2), new TypeToken<List<CloudProfileBean>>() { // from class: com.huawei.profile.coordinator.RequestUtilsSdk.2
            }.getType());
            if (profileBeanFromJson == null || profileBeanFromJson.size() == 0) {
                DsLog.et(TAG, "response profile is empty.", new Object[0]);
                return new ArrayList();
            }
            CloudProfileBean cloudProfileBean = profileBeanFromJson.get(0);
            if (TextUtils.isEmpty(cloudProfileBean.getDevId())) {
                DsLog.et(TAG, "device's id is empty.", new Object[0]);
                return new ArrayList();
            }
            List<CloudProfileBean.ServicesBean> services = cloudProfileBean.getServices();
            if (services == null || services.isEmpty()) {
                DsLog.et(TAG, "service list is empty.", new Object[0]);
                return new ArrayList();
            }
            ArrayList arrayList = new ArrayList();
            for (CloudProfileBean.ServicesBean servicesBean : services) {
                ServiceProfile serviceProfile = new ServiceProfile();
                String st = servicesBean.getSt();
                String sid = servicesBean.getSid();
                if (TextUtils.isEmpty(st)) {
                    DsLog.et(TAG, "service's type is empty.", new Object[0]);
                } else if (TextUtils.isEmpty(sid)) {
                    DsLog.et(TAG, "service's id is empty.", new Object[0]);
                } else {
                    serviceProfile.setDeviceId(str);
                    serviceProfile.setId(sid);
                    serviceProfile.setType(st);
                    serviceProfile.addEntityInfo("sid", sid);
                    serviceProfile.addEntityInfo("type", st);
                    arrayList.add(serviceProfile);
                }
            }
            return arrayList;
        } catch (JsonSyntaxException e) {
            DsLog.et(TAG, "Failed to parse parseCloudResponseToServiceProfiles, error: " + e.getMessage(), new Object[0]);
            return new ArrayList();
        }
    }

    public static ServiceCharacteristicProfile parseCloudResponseToCharacteristicProfile(String str, String str2, String str3) {
        List<CloudProfileBean> list;
        try {
            list = CloudJsonUtil.getProfileBeanFromJson(JsonUtils.sanitize(str), new TypeToken<List<CloudProfileBean>>() { // from class: com.huawei.profile.coordinator.RequestUtilsSdk.3
            }.getType());
        } catch (JsonSyntaxException e) {
            DsLog.et(TAG, "Failed to parse parseCloudResponseToCharacteristicProfile, error: " + e.getMessage(), new Object[0]);
            list = null;
        }
        if (list == null || list.size() == 0) {
            DsLog.et(TAG, "response profile is empty", new Object[0]);
            return null;
        }
        List<CloudProfileBean.ServicesBean> services = list.get(0).getServices();
        if (services == null || services.size() == 0) {
            DsLog.et(TAG, "response service profile is empty", new Object[0]);
            return null;
        }
        ServiceCharacteristicProfile serviceCharacteristicProfile = new ServiceCharacteristicProfile();
        serviceCharacteristicProfile.setDeviceId(str2);
        serviceCharacteristicProfile.setServiceId(str3);
        for (CloudProfileBean.ServicesBean servicesBean : services) {
            if (str3.equals(servicesBean.getSid())) {
                for (CloudProfileBean.ServicesBean.CharacteristicBean characteristicBean : servicesBean.getCharacteristic()) {
                    serviceCharacteristicProfile.addEntityInfo(characteristicBean.getCharacter(), characteristicBean.getValue());
                }
            }
        }
        return serviceCharacteristicProfile;
    }

    public static String parseDeviceProfileToBodyJson(DeviceProfile deviceProfile) throws ProfileRequestException {
        if (deviceProfile == null) {
            throw new ProfileRequestException(ProfileRequestExceptionType.DEVICE_PROFILE_INVALID, "device profile is null");
        }
        Map<String, Object> profile = deviceProfile.getProfile();
        if (profile.isEmpty()) {
            throw new ProfileRequestException(ProfileRequestExceptionType.DEVICE_PROFILE_INVALID, "device profile map is empty");
        }
        CloudProfileBean cloudProfileBean = new CloudProfileBean();
        CloudProfileBean.DevInfoBean devInfoBean = new CloudProfileBean.DevInfoBean();
        cloudProfileBean.setDevInfo(devInfoBean);
        devInfoBean.setSn(getStringFromObjectMap(profile, "sn", true));
        devInfoBean.setUdid(getStringFromObjectMap(profile, "udid", true));
        devInfoBean.setModel(getStringFromObjectMap(profile, "model", false));
        devInfoBean.setDevType(getStringFromObjectMap(profile, "devType", false));
        devInfoBean.setDeviceName(getStringFromObjectMap(profile, "deviceName", true));
        devInfoBean.setManu(getStringFromObjectMap(profile, ProfileRequestConstants.MANU, false));
        devInfoBean.setProdId(getStringFromObjectMap(profile, "prodId", false));
        devInfoBean.setHiv(getStringFromObjectMap(profile, ProfileRequestConstants.HIV, false));
        devInfoBean.setMac(getStringFromObjectMap(profile, "mac", true));
        devInfoBean.setFwv(getStringFromObjectMap(profile, ProfileRequestConstants.FWV, true));
        devInfoBean.setHwv(getStringFromObjectMap(profile, ProfileRequestConstants.HWV, true));
        devInfoBean.setSwv(getStringFromObjectMap(profile, ProfileRequestConstants.SWV, true));
        devInfoBean.setSubProdId(getStringFromObjectMap(profile, ProfileRequestConstants.SUB_PROD_ID, true));
        devInfoBean.setInternalModel(getStringFromObjectMap(profile, ProfileRequestConstants.INTERNAL_MODEL, true));
        Object intFromObjectMap = getIntFromObjectMap(profile, ProfileRequestConstants.PROT_TYPE, true);
        if (intFromObjectMap instanceof Number) {
            devInfoBean.setProtType(((Number) intFromObjectMap).intValue());
        }
        try {
            return new Gson().toJson(cloudProfileBean);
        } catch (JsonSyntaxException e) {
            throw new ProfileRequestException(ProfileRequestExceptionType.JSON_PARSE_EXCEPTION, "failed to parse json: " + e.getMessage());
        }
    }

    private static String getStringFromObjectMap(Map<String, Object> map, String str, boolean z) throws ProfileRequestException {
        Object obj = map.get(str);
        if (obj instanceof String) {
            return (String) obj;
        }
        if (z) {
            return null;
        }
        throw new ProfileRequestException(ProfileRequestExceptionType.DEVICE_PROFILE_INVALID, str + " is not instance of string, maybe it's null");
    }

    private static Object getIntFromObjectMap(Map<String, Object> map, String str, boolean z) throws ProfileRequestException {
        Object obj = map.get(str);
        if (obj instanceof Number) {
            return obj;
        }
        if (z) {
            return null;
        }
        throw new ProfileRequestException(ProfileRequestExceptionType.DEVICE_PROFILE_INVALID, str + " is not instance of Integer, maybe it's null");
    }

    public static String parseCharacterProfileToBodyJson(ProfileRequestInputParams profileRequestInputParams, ServiceCharacteristicProfile serviceCharacteristicProfile) throws ProfileRequestException {
        String parseTimestamp;
        if (serviceCharacteristicProfile == null) {
            throw new ProfileRequestException(ProfileRequestExceptionType.INPUT_PARAM_INVALID, "characteristicProfile is null");
        }
        Map<String, Object> profile = serviceCharacteristicProfile.getProfile();
        String timestamp = profileRequestInputParams.getTimestamp();
        if (!TextUtils.isEmpty(timestamp)) {
            parseTimestamp = parseDeleteTimestamp(timestamp);
        } else {
            parseTimestamp = parseTimestamp(profile);
        }
        if (TextUtils.isEmpty(parseTimestamp)) {
            throw new ProfileRequestException(ProfileRequestExceptionType.INPUT_PARAM_INVALID, "timestamp is null or empty");
        }
        PutCharacteristicBodyBean.ServicesBean servicesBean = new PutCharacteristicBodyBean.ServicesBean();
        servicesBean.setSid(profileRequestInputParams.getSid());
        servicesBean.setTs(parseTimestamp);
        servicesBean.setData(profile);
        ArrayList arrayList = new ArrayList();
        arrayList.add(servicesBean);
        PutCharacteristicBodyBean putCharacteristicBodyBean = new PutCharacteristicBodyBean();
        putCharacteristicBodyBean.setServices(arrayList);
        try {
            return new Gson().toJson(putCharacteristicBodyBean);
        } catch (JsonSyntaxException e) {
            throw new ProfileRequestException(ProfileRequestExceptionType.JSON_PARSE_EXCEPTION, "Failed to parse character profile to json, error:" + e.getMessage());
        }
    }

    private static String parseTimestamp(Map<String, Object> map) {
        Object obj = map.get("timestamp");
        long longValue = obj instanceof Number ? ((Number) obj).longValue() : 0L;
        if (longValue == 0) {
            DsLog.et(TAG, "timestamp is 0", new Object[0]);
            return "";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(CLOUD_DATE_FORMAT, Locale.ENGLISH);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(DataResult.UTC));
        String format = simpleDateFormat.format(Long.valueOf(longValue));
        DsLog.dt(TAG, "cloud timestamp = " + format, new Object[0]);
        return format;
    }

    private static String parseDeleteTimestamp(String str) {
        try {
            long parseLong = Long.parseLong(str);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(CLOUD_DATE_FORMAT, Locale.ENGLISH);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone(DataResult.UTC));
            String format = simpleDateFormat.format(Long.valueOf(parseLong));
            DsLog.dt(TAG, "cloud timestamp = " + format, new Object[0]);
            return format;
        } catch (NumberFormatException e) {
            DsLog.et(TAG, "NumberFormatException: " + SensitiveUtil.getMessage(e), new Object[0]);
            return "";
        }
    }

    public static boolean isDevTypeValid(DeviceProfile deviceProfile) throws ProfileRequestException {
        if (deviceProfile == null) {
            throw new ProfileRequestException(ProfileRequestExceptionType.DEVICE_PROFILE_INVALID, "device profile is null");
        }
        Map<String, Object> profile = deviceProfile.getProfile();
        if (profile.isEmpty()) {
            throw new ProfileRequestException(ProfileRequestExceptionType.DEVICE_PROFILE_INVALID, "device profile map is empty");
        }
        String stringFromObjectMap = getStringFromObjectMap(profile, "devType", false);
        if (TextUtils.isEmpty(stringFromObjectMap)) {
            DsLog.et(TAG, "device type is empty.", new Object[0]);
            return false;
        }
        if (stringFromObjectMap.length() == 3) {
            return true;
        }
        DsLog.et(TAG, "device type length is invalid.", new Object[0]);
        return false;
    }

    public static boolean isNeedResend(Context context, int i, String str, String str2) {
        boolean z;
        DsLog.et(TAG, "error code = " + i + " check if need to resend.", new Object[0]);
        boolean z2 = true;
        if (i == ProfileRequestExceptionType.EMPTY_CLOUD_DEVICE_ID.getExceptionTypeValue() && ProfileBaseUtils.isDeviceToBeUpload(context, str2)) {
            DsLog.dt(TAG, "cloud device id error, resend.", new Object[0]);
            z = true;
        } else {
            z = false;
        }
        if (i == ProfileRequestExceptionType.AT_EXPIRED.getExceptionTypeValue()) {
            DsLog.dt(TAG, "AT expired, resend.", new Object[0]);
            z = true;
        }
        if (i == ProfileRequestExceptionType.NETWORK_ERROR.getExceptionTypeValue()) {
            DsLog.dt(TAG, "network error, resend.", new Object[0]);
            z = true;
        }
        if (i == 401 && RequestBaseUtils.isAccessTokenExpired(str)) {
            DsLog.dt(TAG, "Access Token might be expired, resend.", new Object[0]);
            ProfileUtilsSdk.getInstance(context).resetAccessTokenTime();
            z = true;
        }
        if (i == ProfileRequestExceptionType.IO_EXCEPTION.getExceptionTypeValue()) {
            DsLog.dt(TAG, "io exception, resend.", new Object[0]);
        } else {
            z2 = z;
        }
        if (z2) {
            ProfileTaskPoolSdk.getInstance().execute(context, 3, false, 0);
        }
        return z2;
    }

    public static String getCloudDevId(Context context, ProfileRequestInputParams profileRequestInputParams) throws ProfileRequestException {
        String localDevId = profileRequestInputParams.getLocalDevId();
        if (TextUtils.isEmpty(localDevId)) {
            throw new ProfileRequestException(ProfileRequestExceptionType.INPUT_PARAM_INVALID, " local device id is empty, request interrupt.");
        }
        String cloudDevId = ProfileUtilsSdk.getInstance(context).getCloudDevId(localDevId);
        if (TextUtils.isEmpty(cloudDevId)) {
            throw new ProfileRequestException(ProfileRequestExceptionType.EMPTY_CLOUD_DEVICE_ID, " cloud device id is empty, request interrupt.");
        }
        DsLog.dt(TAG, "anonymous cloud device id = " + ProfileBaseUtils.anonymousContent(cloudDevId) + ", local device id = " + ProfileBaseUtils.anonymousContent(localDevId), new Object[0]);
        return cloudDevId;
    }

    public static boolean isNeedUpload(Context context) {
        Map<String, Map<String, String>> allResendIndex = ProfileUtilsSdk.getInstance(context).getAllResendIndex();
        if (allResendIndex == null || allResendIndex.isEmpty()) {
            DsLog.wt(TAG, "allResendIndex is null or empty.", new Object[0]);
            return false;
        }
        DsLog.dt(TAG, "all resend index = " + AnonymousUtil.anonymousJson(allResendIndex.toString()), new Object[0]);
        return hasResendIndex(allResendIndex, "devices") || hasResendIndex(allResendIndex, "services") || hasResendIndex(allResendIndex, "characteristics");
    }

    public static void setHeaderMapToClient(Map<String, String> map, ProfileHttpClient profileHttpClient) {
        if (map == null) {
            DsLog.wt(TAG, "headerMap is null.", new Object[0]);
            return;
        }
        if (profileHttpClient == null) {
            DsLog.wt(TAG, "client is null.", new Object[0]);
            return;
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            profileHttpClient.addItemToHeaderMap(entry.getKey(), entry.getValue());
        }
    }

    private static boolean hasResendIndex(Map<String, Map<String, String>> map, String str) {
        Map<String, String> map2 = map.get(str);
        if (map2 != null && !map2.isEmpty()) {
            return true;
        }
        DsLog.wt(TAG, "there is no " + str + " to upload.", new Object[0]);
        return false;
    }
}
