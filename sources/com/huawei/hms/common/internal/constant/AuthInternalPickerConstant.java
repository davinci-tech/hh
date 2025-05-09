package com.huawei.hms.common.internal.constant;

/* loaded from: classes9.dex */
public interface AuthInternalPickerConstant {
    public static final String ACCESS_TOKEN = "accessToken";
    public static final int DISABLED_HMS_APK = 3;
    public static final String EXT_CLIENTINFO_STUB = "ext_clientInfo=HWID-Account-Picker-Ext-ClientInfo";
    public static final int HMS_APK_VERSION_MIN = 50008300;
    public static final int HMS_NOT_SUPPORT_HMS_APK = 21;
    public static final int HMS_SDK_VERSION = 60900100;
    public static final int NEED_UPDATE_HMS_APK = 2;
    public static final int NOT_INSTALLED_HMS_APK = 1;
    public static final String PARAM_ERROR = "parameter error";
    public static final int PETAL_MAIL_FLAG_VALUE = 1;
    public static final int PICKER_TYPE_DEPEND_HMS = 2;
    public static final int PICKER_TYPE_THREE_FLAG = 3;
    public static final int REQUEST_API_LEVEL_9 = 9;
    public static final int REQUEST_API_LEVEL_MIN = 1;
    public static final String RESPONSE_BODY = "{}";
    public static final String UNKOWN_ERROR = "unknow error";
    public static final int UPDATE_SUCCESS = 0;
    public static final int VAILD_HMS_APK = 0;

    public interface IntentAction {
        public static final String ACTION_SIGN_IN_HUB = "com.huawei.hms.account.picker.signIn";
    }

    public static final class SignInReqKey {
        public static final String ACCESS_TOKEN = "accessToken";
        public static final String HUAWEIIDCPCLIENTINFO = "HUAWEIID_CP_CLIENTINFO";
        public static final String HUAWEIIDSIGNINREQUEST = "HUAWEIID_SIGNIN_REQUEST";
        public static final String IS_FULL_SCREEN = "intent.extra.isfullscreen";
        public static final String IS_NEW_SERVICE = "isNewService";
        public static final String MAIL_SUPPORT_COUNTRY_LIST = "petalMailSupportCountryList";
        public static final String MCP_SIGN_IN = "signInMcp";
        public static final String PERMISSION = "permission";
        public static final String PERMISSION_ARRAYLIST = "permissionArrayList";
        public static final String PETAL_MAIL_FLAG = "petalMailFlag";
        public static final String PICKER_TYPE = "pickerType";
        public static final String SCOPE_URI = "mScopeUri";
        public static final String SCOP_ARRAYLIST = "scopeArrayList";
        public static final String TRANS_ID = "transId";
        public static final String UI_STYLE = "ui_style";
    }

    public static final class SignInRspKey {
        public static final String HUAWEIIDSIGNINRESULT = "HUAWEIID_SIGNIN_RESULT";
    }
}
