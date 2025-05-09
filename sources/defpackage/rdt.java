package defpackage;

import android.text.TextUtils;
import com.google.gson.JsonSyntaxException;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.model.HiTrackMetaData;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.CommonUtils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import health.compact.a.utils.StringUtils;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* loaded from: classes7.dex */
public class rdt {
    public static void e(List<HiHealthData> list, List<rdn> list2) {
        if (koq.c(list)) {
            for (HiHealthData hiHealthData : list) {
                if (hiHealthData != null) {
                    rdn rdnVar = new rdn();
                    try {
                        HiTrackMetaData hiTrackMetaData = (HiTrackMetaData) HiJsonUtil.e(hiHealthData.getMetaData(), HiTrackMetaData.class);
                        b(rdnVar, hiTrackMetaData);
                        if ("AMAP".equals(hiTrackMetaData.getVendor())) {
                            rdnVar.b(1);
                        } else if ("GOOGLE".equals(hiTrackMetaData.getVendor())) {
                            rdnVar.b(2);
                        }
                        a(rdnVar, hiTrackMetaData, hiHealthData);
                        rdnVar.h(hiTrackMetaData.getTrackType());
                        rdnVar.a(hiHealthData.getInt("trackdata_deviceType"));
                        rdnVar.d(hiHealthData.getString("device_prodid"));
                        rdnVar.e(hiTrackMetaData.getSportDataSource());
                        rdnVar.b(hiTrackMetaData.getRuncourseId());
                        rdnVar.c(hiTrackMetaData.getAbnormalTrack());
                        rdnVar.d(hiTrackMetaData.getDuplicated());
                        rdnVar.j(hiHealthData.getClientId());
                        rdnVar.c(hiTrackMetaData.getFatherSportItem());
                        rdnVar.d(hiTrackMetaData.getChildSportItems());
                        rdnVar.d(hiTrackMetaData.getHasTrackPoint());
                        String str = hiTrackMetaData.getExtendTrackMap().get("courseName");
                        if (!StringUtils.i(str)) {
                            str = "";
                        }
                        rdnVar.j(str);
                        String str2 = hiTrackMetaData.getExtendTrackMap().get("totalHandicap");
                        rdnVar.c(StringUtils.i(str2) ? str2 : "");
                        String str3 = hiTrackMetaData.getExtendTrackMap().get("mWorkoutTypeOrigin");
                        if (!StringUtils.i(str3)) {
                            str3 = "0";
                        }
                        rdnVar.f(CommonUtils.h(str3));
                        a(rdnVar, hiTrackMetaData);
                        rdnVar.a(e(hiTrackMetaData.getExtendTrackMap()));
                        rdnVar.i(a(hiTrackMetaData, "divingMode"));
                        list2.add(rdnVar);
                    } catch (JsonSyntaxException unused) {
                        ReleaseLogUtil.d("Track_SportListParseUtils", "parseTrackSimplifyData trackMetaData is jsonSyntaxException");
                    }
                }
            }
        }
    }

    private static String e(Map<String, String> map) {
        if (map.containsKey("mWorkoutTypeOrigin")) {
            return map.get("alias");
        }
        return gwg.e(map);
    }

    private static int a(HiTrackMetaData hiTrackMetaData, String str) {
        Map<String, String> extendTrackMap = hiTrackMetaData.getExtendTrackMap();
        if (!extendTrackMap.containsKey(str)) {
            return 0;
        }
        String str2 = extendTrackMap.get(str);
        if (!StringUtils.i(str2)) {
            str2 = "0";
        }
        return CommonUtils.h(str2);
    }

    private static void a(rdn rdnVar, HiTrackMetaData hiTrackMetaData) {
        String str = hiTrackMetaData.getExtendTrackMap().get(HwExerciseConstants.JSON_NAME_RECORD_FLAG);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        rdnVar.e(str);
    }

    private static void b(rdn rdnVar, HiTrackMetaData hiTrackMetaData) {
        int chiefSportDataType = hiTrackMetaData.getChiefSportDataType();
        if (chiefSportDataType == 2) {
            rdnVar.d(hiTrackMetaData.getTotalCalories(), chiefSportDataType);
            rdnVar.a(hiTrackMetaData.getAvgPace());
            return;
        }
        if (chiefSportDataType == 1) {
            rdnVar.d(hiTrackMetaData.getTotalCalories(), chiefSportDataType);
            if (hiTrackMetaData.getSportType() != 290) {
                rdnVar.a(hiTrackMetaData.getAvgHeartRate());
                return;
            }
            return;
        }
        if (chiefSportDataType == 5) {
            e(rdnVar, hiTrackMetaData, chiefSportDataType);
            return;
        }
        if (hiTrackMetaData.getChiefSportDataType() == 6) {
            d(rdnVar, hiTrackMetaData, chiefSportDataType);
            return;
        }
        if (chiefSportDataType == 7) {
            int h = hiTrackMetaData.getExtendTrackMap().get("skipNum") == null ? 0 : CommonUtil.h(hiTrackMetaData.getExtendTrackMap().get("skipNum"));
            r2 = hiTrackMetaData.getExtendTrackMap().get("skipSpeed") != null ? CommonUtil.h(hiTrackMetaData.getExtendTrackMap().get("skipSpeed")) : 0;
            rdnVar.d(h, chiefSportDataType);
            rdnVar.a(r2);
            return;
        }
        if (chiefSportDataType == 8) {
            rdnVar.d(hiTrackMetaData.getTotalDescent() / 10.0f, chiefSportDataType);
            try {
                r2 = Integer.parseInt(hiTrackMetaData.getExtendTrackMap().get("divingCount"));
            } catch (NumberFormatException unused) {
                LogUtil.b("Track_SportListParseUtils", "alterData is null");
            }
            rdnVar.a(hiTrackMetaData.getTotalTime());
            rdnVar.a(r2);
            return;
        }
        if (chiefSportDataType == 9) {
            rdnVar.d(hiTrackMetaData.getTotalTime() / 60000.0f, chiefSportDataType);
            rdnVar.a(hiTrackMetaData.getAvgHeartRate());
            return;
        }
        if (chiefSportDataType == 10) {
            rdnVar.d(hiTrackMetaData.getTotalCalories(), chiefSportDataType);
            rdnVar.a(hiTrackMetaData.getTotalSteps());
            return;
        }
        if (chiefSportDataType == 11) {
            rdnVar.d(hiTrackMetaData.getTotalTime(), chiefSportDataType);
            rdnVar.a(hiTrackMetaData.getExtendTrackMap().get("wayPointDistance") == null ? 0.0f : CommonUtil.h(hiTrackMetaData.getExtendTrackMap().get("wayPointDistance")));
            return;
        }
        rdnVar.d(hiTrackMetaData.getTotalDistance(), chiefSportDataType);
        if (hiTrackMetaData.getSportType() == 260) {
            rdnVar.a(hiTrackMetaData.getCreepingWave() / 10.0f);
        } else if (kxb.c(hiTrackMetaData.getSportType())) {
            c(rdnVar, hiTrackMetaData);
        } else {
            rdnVar.a(hiTrackMetaData.getAvgPace());
        }
    }

    private static void a(rdn rdnVar, HiTrackMetaData hiTrackMetaData, HiHealthData hiHealthData) {
        long j;
        long startTime = hiHealthData.getStartTime();
        long endTime = hiHealthData.getEndTime();
        if (hiTrackMetaData.getChiefSportDataType() == 8) {
            try {
                j = Long.parseLong(hiTrackMetaData.getExtendTrackMap().get("divingTime")) * 1000;
            } catch (NumberFormatException unused) {
                LogUtil.b("Track_SportListParseUtils", "saveSingleTrackData is null");
                j = 0;
            }
        } else {
            j = hiTrackMetaData.getTotalTime();
        }
        int totalCalories = hiTrackMetaData.getTotalCalories();
        int sportType = hiTrackMetaData.getSportType();
        rdnVar.b(startTime);
        rdnVar.e(endTime);
        rdnVar.c(j);
        rdnVar.d(totalCalories);
        rdnVar.g(sportType);
    }

    private static void c(rdn rdnVar, HiTrackMetaData hiTrackMetaData) {
        int totalDistance = hiTrackMetaData.getTotalDistance();
        long seconds = TimeUnit.MILLISECONDS.toSeconds(hiTrackMetaData.getTotalTime());
        rdnVar.a(seconds != 0 ? (totalDistance * 3.6f) / seconds : 0.0f);
    }

    private static void d(rdn rdnVar, HiTrackMetaData hiTrackMetaData, int i) {
        float parseFloat;
        Map<String, String> extendTrackMap = hiTrackMetaData.getExtendTrackMap();
        if (extendTrackMap != null) {
            try {
                if (extendTrackMap.get("golfSwingCount") == null) {
                    LogUtil.h("Track_SportListParseUtils", "extendTrackDataMap.get(TrackConstants.GOLF_SWING_COUNT) == null");
                    parseFloat = -1.0f;
                } else {
                    parseFloat = Float.parseFloat(extendTrackMap.get("golfSwingCount"));
                }
                rdnVar.d(parseFloat, i);
            } catch (NumberFormatException unused) {
                ReleaseLogUtil.c("Track_SportListParseUtils", "saveGolfChiefValue NumberFormatException");
            }
        }
        rdnVar.a(hiTrackMetaData.getTotalCalories());
    }

    private static void e(rdn rdnVar, HiTrackMetaData hiTrackMetaData, int i) {
        Map<String, Integer> wearSportData = hiTrackMetaData.getWearSportData();
        if (wearSportData != null && wearSportData.containsKey("overall_score") && wearSportData.get("overall_score") != null) {
            rdnVar.d(wearSportData.get("overall_score").intValue(), i);
            LogUtil.c("Track_SportListParseUtils", "SPORT_DATA_KEY_OVERALL_SCORE", wearSportData.get("overall_score"));
        }
        if (wearSportData == null || !wearSportData.containsKey("jump_times") || wearSportData.get("jump_times") == null) {
            return;
        }
        rdnVar.a(wearSportData.get("jump_times").intValue());
    }
}
