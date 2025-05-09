package com.huawei.hms.support.hwid.common.constants;

/* loaded from: classes9.dex */
public class CommonConstant {
    public static final String EMPTY_BODY = "{}";
    public static final String KEY_QR_CODE = "qrCode";
    public static final String KEY_QR_CONTENT = "qrContent";
    public static final String KEY_QR_SESSION_ID = "qrSessionId";
    public static final String KEY_QR_SITE_ID = "qrSiteId";

    public static class AckQrLoginVerifyValue {
        public static final String VERIFY_RESULT_CANCEL = "cancel";
        public static final String VERIFY_RESULT_OK = "login_ok";
    }

    public interface IdTokenSupportAlg {
        public static final String PS_256 = "PS256";
    }

    public static class PERMISSION {
        public static final String LOGIN_QR = "com.huawei.android.hms.account.startQrLogin";
        public static final String QET_DEVICE_INFO = "com.huawei.hms.account.getDeviceInfo";
        public static final String QUERY_ACCOUNT_CHANGED = "com.huawei.hms.account.accountChange";
    }

    public static class REALNAMEREQUEST {
        public static final String REQUEST_CHANNEL_EXTRA_INFO = "requestChannelExtraInfo";
        public static final String REQUEST_CHANNEL_INFO = "requestChannelInfo";
        public static final String REQUEST_TRANS_ID = "realNameTransId";
    }

    public static class REALNAMERESULT {
        public static final String KEY_AI = "ai";
        public static final String KEY_CHANNEL_ID = "channelId";
        public static final String KEY_CHANNEL_RESULT_ARRAY = "channelResultArray";
        public static final String KEY_NPPA_CHANNEL_INFO = "nppaChannelInfo";
        public static final String KEY_PI = "pi";
        public static final String KEY_VERIFY_RESULT = "verifyResult";
        public static final String KEY_VERIFY_TYPE = "verifyType";
    }

    public static final class RETCODE {
        public static final int GET_ACCOUNT_UNKNOWN_ERROR = 2005;
        public static final int GET_RESULT_SUCCESS = 0;
        public static final int SIGN_IN_SDK_INNER_ERROR = 2015;
    }

    public static final class RETKEY {
        public static final String QR_CODE = "QR_CODE";
        public static final String QR_SITEID = "QR_SITEID";
    }

    public static class SCOPE {
        public static final String SCOPE_BASE_PROFILE = "https://www.huawei.com/auth/account/base.profile";
        public static final String SCOPE_DEVICEINFO = "https://www.huawei.com/auth/account/deviceinfo";
        public static final String SCOPE_LOGIN_ACCOUNT = "https://www.huawei.com/auth/account/loginaccount";
        public static final String SCOPE_MOBILE_FLAG = "https://www.huawei.com/auth/account/mobile.flag";
        public static final String SCOPE_MOBILE_NUMBER = "https://www.huawei.com/auth/account/mobile.number";
        public static final String SCOPE_REALNAME_ANONYMOUS = "https://www.huawei.com/auth/account/realname.anonymous";
    }

    public static class StartQrLoginQrValue {
        public static final int DEFAULT_QUERY_TIME_OUT = 180;
        public static final String QRSCENE_DEFAULT = "DEFAULT";
        public static final String QRSCENE_RECHECK_USER = "RECHECK_USER";
        public static final String QRSCENE_VERIFY = "VERIFY";
    }
}
