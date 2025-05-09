package com.huawei.hwdevice.phoneprocess.mgr.exercise.utils;

import android.text.TextUtils;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartZoneConf;
import com.huawei.hwfoundationmodel.trackmodel.HeartRateData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.up.model.UserInfomation;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import java.util.List;

/* loaded from: classes5.dex */
public class HeartRateAndStepsUtils {
    private static final int HEART_RATE_ZONE_CONFIG_INDEX = 0;
    private static final int HEART_RATE_ZONE_CONFIG_SIZE = 2;
    private static final int HEART_RATE_ZONE_THRESHOLD_INDEX = 1;
    private static final int INITIAL_NUMBER = -1;
    private static final String TAG = "HeartRateAndStepsUtils";
    private static HeartZoneConf mHeartZoneConf;

    private HeartRateAndStepsUtils() {
    }

    public static int[] requestHeartInteDuration(List<HeartRateData> list) {
        int i = 5;
        int[] iArr = new int[5];
        int i2 = 0;
        if (list.size() >= 2 && list.get(1).acquireTime() - list.get(0).acquireTime() >= 40000) {
            i = 60;
        }
        HeartZoneConf heartZoneConf = getHeartZoneConf();
        StringBuilder sb = new StringBuilder("heartzone:");
        int i3 = 0;
        int i4 = 0;
        int i5 = -1;
        while (i2 < list.size()) {
            HeartRateData heartRateData = list.get(i2);
            int i6 = i * i2;
            int findRateRegion = heartZoneConf.findRateRegion(heartRateData.acquireHeartRate());
            sb.append(heartRateData.acquireHeartRate());
            sb.append(":");
            sb.append(findRateRegion);
            sb.append(" ");
            if (findRateRegion != -1) {
                if (i5 != findRateRegion) {
                    if (i5 != -1) {
                        iArr[i5] = iArr[i5] + (i6 - i3);
                    }
                    i3 = i6;
                    i5 = findRateRegion;
                }
            } else if (i5 != -1) {
                iArr[i5] = iArr[i5] + (i6 - i3);
                i5 = -1;
            }
            i2++;
            i4 = i6;
        }
        HeartRateData heartRateData2 = list.get(list.size() - 1);
        int findRateRegion2 = heartZoneConf.findRateRegion(heartRateData2.acquireHeartRate());
        if (findRateRegion2 != -1 && findRateRegion2 == i5) {
            iArr[i5] = iArr[i5] + (i4 - i3);
            sb.append(heartRateData2.acquireHeartRate());
            sb.append(":");
            sb.append(findRateRegion2);
        }
        LogUtil.c(TAG, sb);
        return iArr;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0056  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.huawei.hwcommonmodel.fitnessdatatype.HeartZoneConf getHeartZoneConf() {
        /*
            com.huawei.hwcommonmodel.fitnessdatatype.HeartZoneConf r0 = com.huawei.hwdevice.phoneprocess.mgr.exercise.utils.HeartRateAndStepsUtils.mHeartZoneConf
            if (r0 != 0) goto L84
            java.lang.String r0 = "getHeartZoneConf enter"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            java.lang.String r1 = "HeartRateAndStepsUtils"
            com.huawei.hwlogsmodel.LogUtil.c(r1, r0)
            com.huawei.up.model.UserInfomation r0 = getLocalUserInfo()
            int r2 = r0.getAge()
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            java.lang.String r3 = "getHeartZoneConf enter ,age:"
            java.lang.Object[] r2 = new java.lang.Object[]{r3, r2}
            com.huawei.hwlogsmodel.LogUtil.c(r1, r2)
            com.huawei.hwcommonmodel.fitnessdatatype.HeartZoneConf r2 = new com.huawei.hwcommonmodel.fitnessdatatype.HeartZoneConf
            int r0 = r0.getAge()
            r2.<init>(r0)
            android.content.Context r0 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()
            com.huawei.hihealth.api.HiHealthApi r0 = com.huawei.hihealth.api.HiHealthManager.d(r0)
            java.lang.String r3 = "custom.UserPreference_HeartRate_Classify_Method"
            com.huawei.hihealth.HiUserPreference r0 = r0.getUserPreference(r3)
            if (r0 == 0) goto L68
            java.lang.String r0 = r0.getValue()     // Catch: java.lang.NumberFormatException -> L49
            int r0 = java.lang.Integer.parseInt(r0)     // Catch: java.lang.NumberFormatException -> L49
            r2.setClassifyMethod(r0)     // Catch: java.lang.NumberFormatException -> L4a
            goto L53
        L49:
            r0 = 0
        L4a:
            java.lang.String r3 = "parse UserPreference value fail"
            java.lang.Object[] r3 = new java.lang.Object[]{r3}
            com.huawei.hwlogsmodel.LogUtil.b(r1, r3)
        L53:
            if (r0 != 0) goto L56
            goto L68
        L56:
            android.content.Context r0 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()
            com.huawei.hihealth.api.HiHealthApi r0 = com.huawei.hihealth.api.HiHealthManager.d(r0)
            java.lang.String r3 = "custom.UserPreference_HRRHeartZone_Config"
            com.huawei.hihealth.HiUserPreference r0 = r0.getUserPreference(r3)
            setHeartZoneConfigure(r0, r2)
            goto L79
        L68:
            android.content.Context r0 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()
            com.huawei.hihealth.api.HiHealthApi r0 = com.huawei.hihealth.api.HiHealthManager.d(r0)
            java.lang.String r3 = "custom.UserPreference_HeartZone_Config"
            com.huawei.hihealth.HiUserPreference r0 = r0.getUserPreference(r3)
            setHeartZoneConfigure(r0, r2)
        L79:
            java.lang.String r0 = "getHeartZoneConf"
            java.lang.Object[] r0 = new java.lang.Object[]{r0, r2}
            com.huawei.hwlogsmodel.LogUtil.c(r1, r0)
            com.huawei.hwdevice.phoneprocess.mgr.exercise.utils.HeartRateAndStepsUtils.mHeartZoneConf = r2
        L84:
            com.huawei.hwcommonmodel.fitnessdatatype.HeartZoneConf r0 = com.huawei.hwdevice.phoneprocess.mgr.exercise.utils.HeartRateAndStepsUtils.mHeartZoneConf
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hwdevice.phoneprocess.mgr.exercise.utils.HeartRateAndStepsUtils.getHeartZoneConf():com.huawei.hwcommonmodel.fitnessdatatype.HeartZoneConf");
    }

    private static HeartZoneConf setHeartZoneConfigure(HiUserPreference hiUserPreference, HeartZoneConf heartZoneConf) {
        if (hiUserPreference != null && !TextUtils.isEmpty(hiUserPreference.getValue())) {
            String[] split = hiUserPreference.getValue().split(",");
            if (split.length == 2) {
                heartZoneConf.setHRZoneConf(split[0]);
                heartZoneConf.setThreshold(split[1]);
            }
        }
        return heartZoneConf;
    }

    private static UserInfomation getLocalUserInfo() {
        UserProfileMgrApi userProfileMgrApi = (UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class);
        boolean h = UnitUtil.h();
        if (userProfileMgrApi != null) {
            UserInfomation userInfo = userProfileMgrApi.getUserInfo();
            if (userInfo != null) {
                LogUtil.a(TAG, "getLocalUserInfo return MerryuserInfomation");
                return userInfo;
            }
            UserInfomation userInfomation = new UserInfomation(h ? 1 : 0);
            LogUtil.h(TAG, "getLocalUserInfo is null");
            return userInfomation;
        }
        UserInfomation userInfomation2 = new UserInfomation(h ? 1 : 0);
        LogUtil.h(TAG, "userProfileMgrApi is null");
        return userInfomation2;
    }
}
