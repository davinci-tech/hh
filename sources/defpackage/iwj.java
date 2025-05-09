package defpackage;

import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartRateThresholdConfig;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes7.dex */
public class iwj {
    public static int b(int i) {
        switch (i) {
            case 1:
            case 2:
                return 20004;
            case 3:
                return 20005;
            case 4:
                return 20003;
            case 5:
                return 20002;
            case 6:
                return 22001;
            case 7:
                return 22002;
            case 8:
                return 22003;
            case 9:
                return 20007;
            case 10:
                return 20010;
            default:
                return 0;
        }
    }

    public static int e(int i) {
        if (i == 20007) {
            return 9;
        }
        if (i == 20010) {
            return 10;
        }
        switch (i) {
            case 20002:
                return 5;
            case 20003:
                return 4;
            case 20004:
                return 2;
            case 20005:
                return 3;
            default:
                switch (i) {
                    case 22001:
                        return 6;
                    case 22002:
                        return 7;
                    case 22003:
                        return 8;
                    default:
                        return 0;
                }
        }
    }

    public static boolean d(List<HiHealthData> list) {
        if (list == null) {
            return false;
        }
        Iterator<HiHealthData> it = list.iterator();
        while (it.hasNext()) {
            int type = it.next().getType();
            boolean z = type >= 20001 && type <= 21000;
            boolean z2 = type > 40001 && type < 41000;
            if (z || z2) {
                return true;
            }
        }
        return false;
    }

    public static int c(int i) {
        if (i == 271) {
            return 109;
        }
        if (i == 273) {
            return 111;
        }
        if (i == 274) {
            return 112;
        }
        if (i == 279) {
            return 117;
        }
        if (i == 280) {
            return 118;
        }
        switch (i) {
            case 257:
                return 5;
            case 258:
                return 4;
            case 259:
                return 3;
            case 260:
                return 2;
            case 261:
                return 1;
            case 262:
                return 102;
            default:
                switch (i) {
                    case 264:
                        return 101;
                    case OldToNewMotionPath.SPORT_TYPE_INDOOR_BIKE /* 265 */:
                        return 103;
                    case OldToNewMotionPath.SPORT_TYPE_OPEN_AREA_SWIM /* 266 */:
                        return 104;
                    default:
                        return i(i);
                }
        }
    }

    private static int i(int i) {
        if (i == 133) {
            return 110;
        }
        if (i == 270) {
            return 108;
        }
        if (i == 275) {
            return 113;
        }
        if (i == 283) {
            return 283;
        }
        switch (i) {
            case 128:
                return 105;
            case 129:
                return 106;
            case OldToNewMotionPath.SPORT_TYPE_TENNIS /* 130 */:
                return 107;
            default:
                switch (i) {
                    case 137:
                        return 114;
                    case OldToNewMotionPath.SPORT_TYPE_PILATES /* 138 */:
                        return 115;
                    case OldToNewMotionPath.SPORT_TYPE_AEROBICS /* 139 */:
                        return 116;
                    default:
                        switch (i) {
                            case 217:
                                return 217;
                            case 218:
                                return 218;
                            case 219:
                                return 219;
                            case HeartRateThresholdConfig.HEART_RATE_LIMIT /* 220 */:
                                return HeartRateThresholdConfig.HEART_RATE_LIMIT;
                            default:
                                LogUtil.h("Debug_HiTypeUtil", "getCloudTrackSportType do not has this type yet , localType is ", Integer.valueOf(i));
                                return i;
                        }
                }
        }
    }

    public static int d(int i) {
        if (i == 1) {
            return 261;
        }
        if (i == 2) {
            return 260;
        }
        if (i == 3) {
            return 259;
        }
        if (i == 4) {
            return 258;
        }
        if (i == 5) {
            return 257;
        }
        if (i == 109) {
            return OldToNewMotionPath.SPORT_TYPE_BASKETBALL;
        }
        if (i == 111) {
            return OldToNewMotionPath.SPORT_TYPE_CROSS_TRAINER;
        }
        if (i == 112) {
            return OldToNewMotionPath.SPORT_TYPE_ROW_MACHINE;
        }
        if (i == 117) {
            return OldToNewMotionPath.SPORT_TYPE_OTHER_SPORT;
        }
        if (i == 118) {
            return OldToNewMotionPath.SPORT_TYPE_CROSS_COUNTRY_RACE;
        }
        switch (i) {
            case 101:
                return 264;
            case 102:
                return 262;
            case 103:
                return OldToNewMotionPath.SPORT_TYPE_INDOOR_BIKE;
            case 104:
                return OldToNewMotionPath.SPORT_TYPE_OPEN_AREA_SWIM;
            default:
                return j(i);
        }
    }

    private static int j(int i) {
        if (i == 110) {
            return OldToNewMotionPath.SPORT_TYPE_VOLLEYBALL;
        }
        if (i == 283) {
            return 283;
        }
        switch (i) {
            case 105:
                return 128;
            case 106:
                return 129;
            case 107:
                return OldToNewMotionPath.SPORT_TYPE_TENNIS;
            case 108:
                return 270;
            default:
                switch (i) {
                    case 113:
                        return OldToNewMotionPath.SPORT_TYPE_TREAD_MACHINE;
                    case 114:
                        return 137;
                    case 115:
                        return OldToNewMotionPath.SPORT_TYPE_PILATES;
                    case 116:
                        return OldToNewMotionPath.SPORT_TYPE_AEROBICS;
                    default:
                        switch (i) {
                            case 217:
                                return 217;
                            case 218:
                                return 218;
                            case 219:
                                return 219;
                            case HeartRateThresholdConfig.HEART_RATE_LIMIT /* 220 */:
                                return HeartRateThresholdConfig.HEART_RATE_LIMIT;
                            default:
                                LogUtil.h("Debug_HiTypeUtil", "getLocalTrackSportType do not has this type yet , cloudType is ", Integer.valueOf(i));
                                return i;
                        }
                }
        }
    }

    public static String a(int i) {
        if (i == 0) {
            return "config_data";
        }
        if (i == 1) {
            return "sample_point";
        }
        if (i == 2) {
            return "sample_point_health";
        }
        if (i != 3) {
            return null;
        }
        return "config_stat_day";
    }
}
