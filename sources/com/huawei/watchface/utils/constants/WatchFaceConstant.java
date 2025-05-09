package com.huawei.watchface.utils.constants;

import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.watchface.utils.MobileInfoHelper;

/* loaded from: classes7.dex */
public interface WatchFaceConstant {
    public static final String AUTHORIZATION = "Authorization";
    public static final String CIPHER_VERSION = "cipherVersion";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String EMPTY_STRING = "";
    public static final String HASHCODE = "hashCode";
    public static final String HOST = "Host";
    public static final int HTTP_SUCCESS = 200;
    public static final String JS_INTERACTION = "JsInteraction";
    public static final String JS_INTERFACE = "JsInterface";
    public static final String SCENARIO_ID = "scenarioID";
    public static final int SUCCESS = 0;
    public static final int TYPE_MINI_SHOP_ACTIVITY = 3002;
    public static final String VERSION_CODE = "versionCode";
    public static final String X_AMZ_CONTENT_SHA256 = "x-amz-content-sha256";
    public static final String X_AMZ_DATE = "x-amz-date";
    public static final String X_CLIENTTRACEID = "x-clienttraceid";
    public static final String X_SIGN = "x-sign";
    public static final String X_UID = "x-uid";

    /* renamed from: a, reason: collision with root package name */
    public static final String f11201a;
    public static final String b;

    static {
        f11201a = MobileInfoHelper.isUpMagicUI6() ? "com.hihonor.parentcontrol" : Constants.PARENT_CONTROL_PACKAGE_NAME;
        b = MobileInfoHelper.isUpMagicUI6() ? "msc.config.fold_disp" : "ro.config.hw_fold_disp";
    }
}
