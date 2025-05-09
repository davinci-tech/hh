package com.huawei.login.ui.login;

import com.huawei.operation.ble.BleConstants;
import health.compact.a.CommonUtil;

/* loaded from: classes.dex */
public class AccountConstants {
    public static final int ACCESS_TOKEN = 1007;
    public static final int ACCESS_TOKEN_EXPIRE_TIME = 1016;
    public static final int ACCOUNT_NAME = 1000;
    public static final int ACCOUNT_NULL = -2;
    public static final int ACCOUNT_PLAINTEXT_NAME = 1001;

    @Deprecated
    public static final int ACCOUNT_TYPE = 1004;
    public static final int ACCOUNT_TYPE_HMS = 1019;
    public static final String AT_TOKEN_TYPE = "2";
    public static final int BIRTH_DATE = 1006;
    public static final int COUNTRY_CODE = 1010;
    public static final int DOWN_HMS_HWID_LOGINED_WEAR_NOT_LOGINED = 2;
    public static final int DOWN_HMS_HWID_NOT_LOGINED = 1;
    public static final int EMAIL = 1018;
    public static final int GENDER = 1005;
    public static final String GRS_APP_NAME;
    public static final String HEALTH_APP_THIRD_LOGIN = "health_app_third_login";
    public static final int HWID_LOGINED_TYPE = 0;
    public static final int MOBILE = 1017;
    public static final int NEW_ACCESS_TOKEN = 1015;
    public static final int SERVER_TOKEN = 1013;
    public static final int SERVICE_COUNTRY_CODE = 1014;
    public static final String SERVICE_SUFFIX;
    public static final int SITE_ID = 1009;
    public static final String ST_TOKEN_TYPE = "1";
    public static final int TOKEN = 1008;
    public static final int TOKEN_TYPE = 1012;
    public static final int USER_ID = 1011;
    public static final int USER_NAME = 1002;
    public static final int USER_PIC = 1003;

    static {
        String str = CommonUtil.cc() ? BleConstants.WEIGHT_KEY : "";
        SERVICE_SUFFIX = str;
        GRS_APP_NAME = "healthcloud" + str;
    }

    private AccountConstants() {
    }
}
