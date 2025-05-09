package com.huawei.profile.coordinator;

import com.huawei.profile.function.ProfileFunction;
import com.huawei.profile.utils.AssetUrlReader;
import com.huawei.profile.utils.DelayLoadedString;

/* loaded from: classes6.dex */
public final class ProfileRequestConstants {
    public static final String APPLICATION_JSON = "application/json";
    public static final String AUTHORIZATION = "Authorization";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String DELETE_TYPE = "DELETE";
    public static final String DEVICE_NAME = "deviceName";
    public static final CharSequence ENVIRONMENT_URL = new DelayLoadedString(new ProfileFunction<String, String>() { // from class: com.huawei.profile.coordinator.ProfileRequestConstants.1
        @Override // com.huawei.profile.function.ProfileFunction
        public String apply(String str) {
            return str == null ? AssetUrlReader.getUrl("environment_url") : str;
        }
    });
    public static final String FWV = "fwv";
    public static final String GET_TYPE = "GET";
    public static final String HIV = "hiv";
    public static final String HWV = "hwv";
    public static final String INTERNAL_MODEL = "internalModel";
    public static final String MAC = "mac";
    public static final String MANU = "manu";
    public static final String MODEL = "model";
    public static final String POST_TYPE = "POST";
    public static final String PROD_ID = "prodId";
    public static final String PROT_TYPE = "protType";
    public static final String PUT_TYPE = "PUT";
    public static final String SN = "sn";
    public static final String SUB_PROD_ID = "subProdId";
    public static final String SWV = "swv";
    public static final String UDID = "udid";
    public static final String X_APPID_KEY = "x-appid";
    public static final String X_HUID_KEY = "x-huid";
    public static final String X_LANGUAGE_KEY = "x-language";
    public static final String X_LANGUAGE_VALUE = "zh_CN";
    public static final String X_SERVICE_CHAIN = "x-service-chain";
    public static final String X_VERSION_KEY = "x-version";
    public static final String X_VERSION_VALUE = "1.0.11";

    private ProfileRequestConstants() {
    }
}
