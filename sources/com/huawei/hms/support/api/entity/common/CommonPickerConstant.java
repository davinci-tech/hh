package com.huawei.hms.support.api.entity.common;

/* loaded from: classes9.dex */
public class CommonPickerConstant {

    public static class IdTokenSupportAlg {
        public static final int PS256_ID_TOKEN_ALG = 1;
        public static final String PS_256 = "PS256";
        public static final int RS256_ID_TOKEN_ALG = 2;
        public static final String RS_256 = "RS256";
    }

    public static class IncludeGranted {
        public static final String NON_INCLUDE_GRANTED = "https://www.huawei.com/auth/account/nonincludegranted";
    }

    public static final class LITESDK {
        public static final String HMS_REDIRECT_URI = "hms://redirect_url";
        public static final int LITESDK_EXCEPTION_ERROR = 404;
        public static final int USER_CANCEL = 6;
    }

    public static class LOCALPERMISSION {
        public static final String ACCESS_TOKEN = "https://www.huawei.com/auth/account/base.profile/accesstoken";
        public static final String SERVICE_AUTH_CODE = "https://www.huawei.com/auth/account/base.profile/serviceauthcode";
        public static final String SETDIALOGAUTH = "setDialogAuth";
    }

    public static class PERMISSION {
        public static final String IDTOKEN = "idtoken";
        public static final String UID = "com.huawei.android.hms.account.getUID";
    }

    public static final class RETCODE {
        public static final int H5_SUCCESS_CODE = 200;
        public static final int H5_TOKEN_REVOKED_CODE = 1203;
        public static final int H5_UNAUTHORIZED_CODE = 1301;
        public static final int PARAMS_ERROR = 2003;
        public static final int REQUEST_NETWORK_ERROR = 2005;
        public static final int RESPONSE_RESULT_OK = 0;
        public static final int SIGN_IN_BY_H5 = 20051;
        public static final int SIGN_IN_CANCELLED = 2012;
        public static final int SIGN_IN_EXECUTING = 2013;
        public static final int SIGN_IN_FAILED = 2014;
        public static final int SIGN_IN_SDK_INNER_ERROR = 2015;
    }

    public static class RequestParams {
        public static final String KEY_ACCESS_TOKEN = "accessToken";
        public static final String KEY_ACCOUNT_ATTR = "accountAttr";
        public static final String KEY_DEVICE_INFO = "deviceInfo";
        public static final String KEY_GRS_APP_NAME = "grsAppName";
        public static final String KEY_GUIDE_LOGIN = "guideLogin";
        public static final String KEY_HOST_ACCESS_TOKEN = "hostAccessToken";
        public static final String KEY_ID_FORCE_REFRESH = "needForceRefresh";
        public static final String KEY_ID_TOKEN_SIGN_ALG = "idTokenSignAlg";
        public static final String KEY_LOGNIN_URL = "loginUrl";
        public static final String KEY_OOBE_FLAG = "oobeFlag";
        public static final String KEY_OPEN_ID = "openId";
        public static final String KEY_QR_PROMPT_FIRST_LEVEL_TYPE = "QRPromptFirstLevelType";
        public static final String KEY_QR_PROMPT_SECOND_LEVEL = "QRPromptSecondLevel";
        public static final String KEY_REDIRECT_URL = "redirectUrl";
        public static final String KEY_SIGN_IN_HUAWEI_ID = "signInHuaweiId";
        public static final String KEY_SIGN_IN_PARAMS = "signInParams";
        public static final String PETAL_MAIL_ADDR = "petalMailAddr";
    }

    public static class ResponseParams {
        public static final String NO_SYSTEM_ACCOUNT = "1";
        public static final String SYSTEM_ACCOUNT = "0";
        public static final String UNKNOW_ACCOUNT_TYPE = "-1";
    }

    public static class SCOPE {
        public static final String SCOPE_ACCOUNT_EMAIL = "email";
        public static final String SCOPE_ACCOUNT_GAME = "https://www.huawei.com/auth/games";
        public static final String SCOPE_ACCOUNT_OPENID = "openid";
        public static final String SCOPE_ACCOUNT_PROFILE = "profile";
        public static final String SCOPE_ACCOUNT_SHIPPING_ADDRESS = "https://www.huawei.com/auth/account/shipping.address";
        public static final String SCOPE_MOBILE_NUMBER = "https://www.huawei.com/auth/account/mobile.number";
    }
}
