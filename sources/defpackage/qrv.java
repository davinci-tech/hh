package defpackage;

import android.content.Context;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.R$string;
import com.huawei.up.model.UserInfomation;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import java.util.Map;
import java.util.TreeMap;

/* loaded from: classes7.dex */
public class qrv {
    public static Integer[] a(int i, int i2) {
        if (i2 == 0) {
            LogUtil.h("Track_Vo2maxUtils", "getOxRange maybe on wrong age 0");
        }
        TreeMap treeMap = new TreeMap();
        if (i == 0) {
            treeMap.put(0, new Integer[]{26, 32, 38, 44, 51, 57, 63, 69, 0, 24});
            treeMap.put(25, new Integer[]{26, 31, 36, 43, 49, 54, 60, 66, 25, 29});
            treeMap.put(30, new Integer[]{23, 29, 35, 41, 46, 52, 57, 62, 30, 34});
            treeMap.put(35, new Integer[]{23, 28, 33, 39, 44, 49, 55, 61, 35, 39});
            treeMap.put(40, new Integer[]{20, 26, 32, 36, 42, 47, 52, 57, 40, 44});
            treeMap.put(45, new Integer[]{20, 25, 30, 35, 40, 44, 49, 54, 45, 49});
            treeMap.put(50, new Integer[]{20, 24, 28, 33, 37, 42, 47, 52, 50, 54});
            treeMap.put(55, new Integer[]{17, 22, 27, 31, 35, 40, 44, 48, 55, 59});
            treeMap.put(60, new Integer[]{17, 21, 25, 29, 33, 37, 41, 45, 60, 65});
        } else {
            treeMap.put(0, new Integer[]{22, 27, 32, 37, 42, 47, 52, 57, 0, 24});
            treeMap.put(25, new Integer[]{21, 26, 31, 36, 41, 45, 50, 55, 25, 29});
            treeMap.put(30, new Integer[]{20, 25, 30, 34, 38, 43, 47, 51, 30, 34});
            treeMap.put(35, new Integer[]{20, 24, 28, 32, 36, 41, 45, 49, 35, 39});
            treeMap.put(40, new Integer[]{18, 22, 26, 30, 34, 38, 42, 46, 40, 44});
            treeMap.put(45, new Integer[]{18, 21, 24, 28, 32, 36, 39, 42, 45, 49});
            treeMap.put(50, new Integer[]{14, 19, 23, 26, 30, 33, 37, 41, 50, 54});
            treeMap.put(55, new Integer[]{15, 18, 21, 24, 28, 31, 34, 37, 55, 59});
            treeMap.put(60, new Integer[]{13, 16, 19, 22, 25, 28, 31, 34, 60, 65});
        }
        Map.Entry floorEntry = treeMap.floorEntry(Integer.valueOf(i2));
        if (floorEntry == null) {
            return new Integer[]{0};
        }
        return (Integer[]) floorEntry.getValue();
    }

    public static int b(int i, Integer[] numArr) {
        if (numArr == null || numArr.length <= 1 || numArr.length != 10) {
            return 0;
        }
        if (i >= numArr[6].intValue()) {
            return 6;
        }
        if (i >= numArr[5].intValue()) {
            return 5;
        }
        if (i >= numArr[4].intValue()) {
            return 4;
        }
        if (i >= numArr[3].intValue()) {
            return 3;
        }
        if (i >= numArr[2].intValue()) {
            return 2;
        }
        return i >= numArr[1].intValue() ? 1 : 0;
    }

    public static String e(int i) {
        Context e = BaseApplication.e();
        switch (i) {
            case 0:
                return e.getString(R$string.IDS_data_vo_value_male_low);
            case 1:
                return e.getString(R$string.IDS_data_vo_value_male_poor);
            case 2:
                return e.getString(R$string.IDS_data_vo_value_male_fair);
            case 3:
                return e.getString(R$string.IDS_data_vo_value_male_average);
            case 4:
                return e.getString(R$string.IDS_data_vo_value_male_good);
            case 5:
                return e.getString(R$string.IDS_data_vo_value_male_very_good);
            case 6:
                return e.getString(R$string.IDS_data_vo_value_male_excellent);
            default:
                return e.getString(R$string.IDS_data_vo_value_male_average);
        }
    }

    public static String b(int i) {
        Context e = BaseApplication.e();
        switch (i) {
            case 0:
                return e.getString(R$string.IDS_data_vo_value_female_low);
            case 1:
                return e.getString(R$string.IDS_data_vo_value_female_poor);
            case 2:
                return e.getString(R$string.IDS_data_vo_value_female_fair);
            case 3:
                return e.getString(R$string.IDS_data_vo_value_female_average);
            case 4:
                return e.getString(R$string.IDS_data_vo_value_female_good);
            case 5:
                return e.getString(R$string.IDS_data_vo_value_female_very_good);
            case 6:
                return e.getString(R$string.IDS_data_vo_value_female_excellent);
            default:
                return e.getString(R$string.IDS_data_vo_value_female_average);
        }
    }

    public static Integer[] b() {
        UserInfomation userInfo = ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).getUserInfo();
        if (userInfo == null) {
            userInfo = new UserInfomation(UnitUtil.h() ? 1 : 0);
        }
        return a(userInfo.getGender(), userInfo.getAge());
    }

    public static String d(int i) {
        int i2;
        Context e = BaseApplication.e();
        switch (i) {
            case 0:
                i2 = R$string.IDS_hwh_health_vo2max_level_verypoor;
                break;
            case 1:
                i2 = R$string.IDS_hwh_health_vo2max_level_poor;
                break;
            case 2:
                i2 = R$string.IDS_hwh_health_vo2max_level_fair;
                break;
            case 3:
                i2 = R$string.IDS_hwh_health_vo2max_level_average;
                break;
            case 4:
                i2 = R$string.IDS_hwh_health_vo2max_level_good;
                break;
            case 5:
                i2 = R$string.IDS_hwh_health_vo2max_level_verygood;
                break;
            case 6:
                i2 = R$string.IDS_hwh_health_vo2max_level_excellent;
                break;
            default:
                i2 = R$string.IDS_hwh_health_vo2max_level_average;
                break;
        }
        return e.getResources().getString(i2);
    }
}
