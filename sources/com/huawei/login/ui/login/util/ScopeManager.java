package com.huawei.login.ui.login.util;

import com.huawei.hms.support.api.entity.common.CommonConstant;

/* loaded from: classes.dex */
public class ScopeManager {
    public static final String HEALTHKIT_ACTIVITY_BOTH = "https://www.huawei.com/healthkit/activity.both";
    public static final String HEALTHKIT_ACTIVITY_RECORD_BOTH = "https://www.huawei.com/healthkit/activityrecord.both";
    public static final String HEALTHKIT_BLOOD_GLUCOSE_READ = "https://www.huawei.com/healthkit/bloodglucose.both";
    public static final String HEALTHKIT_BLOOD_PRESSURE_BOTH = "https://www.huawei.com/healthkit/bloodpressure.both";
    public static final String HEALTHKIT_DEVICE_READ = "https://www.huawei.com/healthkit/extend/device.read";
    public static final String HEALTHKIT_DEVICE_WRITE = "https://www.huawei.com/healthkit/extend/device.write";
    public static final String HEALTHKIT_HEIGHT_WEIGHT_BOTH = "https://www.huawei.com/healthkit/heightweight.both";
    public static final String HEALTHKIT_SLEEP_BOTH = "https://www.huawei.com/healthkit/sleep.both";
    public static final String HEALTHKIT_STEP_BOTH = "https://www.huawei.com/healthkit/step.both";
    public static final String HICLOUD_DOWNLOAD = "https://www.huawei.com/auth/drive.file";
    public static final String HICLOUD_UPLOAD = "https://www.huawei.com/auth/drive.appdata";

    public static String[] getScopeList() {
        return new String[]{"https://www.huawei.com/auth/account/base.profile", CommonConstant.SCOPE.SCOPE_ACCOUNT_GENDER, "https://www.huawei.com/auth/account/birthday", "https://www.huawei.com/auth/account/guardian.uid", "https://www.huawei.com/auth/account/guardian.account", "https://www.huawei.com/auth/account/accountlist", "https://www.huawei.com/auth/cloud.message", "https://www.huawei.com/auth/cloud.position", "https://www.huawei.com/auth/sns/read", "https://www.huawei.com/auth/sns/friends.account", "https://smarthome.com/auth/smarthome/skill", "https://smarthome.com/auth/smarthome/devices", "https://www.huawei.com/auth/account/state", CommonConstant.SCOPE.SCOPE_ACCOUNT_COUNTRY, "https://www.huawei.com/auth/account/mobile.number", "email", CommonConstant.SCOPE.SCOPE_AGE_RANGE, "https://www.huawei.com/auth/account/realname/ctf.type", "https://www.huawei.com/auth/account/realname/identity", "https://www.huawei.com/auth/account/realname/state", "https://www.huawei.com/healthkit/activity.both", "https://www.huawei.com/healthkit/heightweight.both", "https://www.huawei.com/healthkit/step.both", "https://www.huawei.com/healthkit/bloodglucose.both", "https://www.huawei.com/healthkit/bloodpressure.both", "https://www.huawei.com/healthkit/sleep.both", "https://www.huawei.com/healthkit/activityrecord.both", HEALTHKIT_DEVICE_READ, HEALTHKIT_DEVICE_WRITE, HICLOUD_UPLOAD, HICLOUD_DOWNLOAD};
    }
}
