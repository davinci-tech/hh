package com.huawei.hwdevice.phoneprocess.mgr.exercise.utils;

import android.os.Bundle;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.CommonSectionInfo;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.StatisticExtendDataStruct;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.TriathlonStruct;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.WorkoutDataInfo;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.WorkoutRecordPaceMap;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.WorkoutRecordStatistic;
import com.huawei.hwfoundationmodel.trackmodel.HeartRateData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwsportmodel.GolfShotTrack;
import defpackage.bmq;
import defpackage.bmu;
import defpackage.cvx;
import defpackage.cwd;
import defpackage.cwe;
import defpackage.ffr;
import defpackage.kon;
import health.compact.a.CommonUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class HwWorkoutServiceUtils {
    private static final int ARRAY_LIST_DEFAULT_SIZE = 12;
    private static final int FIX_HIDE_CODE = 255;
    public static final String HEART_RATE_LIST = "HeartRateList";
    public static final int HEART_RATE_TRUST_VALUE = 254;
    private static final int INT_VALUE_ZERO = 0;
    private static final int INVALID_HEART_RATE_INDEX = -1;
    public static final String INVALID_HEART_RATE_LIST = "InvalidHeartRateList";
    private static final int LINK_HAS_DETAIL_TAG = 1;
    private static final int METER_TURN_METERS = 10;
    private static final int MILL_SECOND_TO_SECOND = 1000;
    private static final int MOVE_RIGHT_ONE_BYTE_BITS = 8;
    private static final int MOVE_RIGHT_THREE_BYTE_BITS = 24;
    private static final int MOVE_RIGHT_TWO_BYTE_BITS = 16;
    private static final String OPERATOR_TYPE = "operator_type";
    private static final long REVERSE_MASK_CODE = -1;
    private static final String RUN_PLAN_DATE = "run_plan_date";
    private static final String SPORT_TYPE = "sport_type";
    private static final String TAG = "HwWorkoutServiceUtils";
    private static final int WORKOUT_RELATION_TYPE_DEFAULT = 0;

    private static int transSportType(int i) {
        if (i == 137) {
            return i;
        }
        if (i == 257) {
            return 2;
        }
        if (i == 259) {
            return 3;
        }
        if (i == 279) {
            return 255;
        }
        if (i == 264) {
            return 5;
        }
        if (i == 265) {
            return 7;
        }
        if (i != 273) {
            return i != 274 ? 1 : 135;
        }
        return 134;
    }

    private HwWorkoutServiceUtils() {
    }

    public static StringBuffer getOperatorTypeStringTlv(StringBuffer stringBuffer, JSONObject jSONObject) throws JSONException {
        if (stringBuffer != null && jSONObject != null) {
            String e = cvx.e(jSONObject.getInt(OPERATOR_TYPE));
            String d = cvx.d(e.length() / 2);
            stringBuffer.append(cvx.e(2));
            stringBuffer.append(d);
            stringBuffer.append(e);
        }
        return stringBuffer;
    }

    public static StringBuffer getOperatorSportTypeStringTlv(StringBuffer stringBuffer, JSONObject jSONObject) throws JSONException {
        if (stringBuffer != null && jSONObject != null) {
            String e = cvx.e(jSONObject.getInt(SPORT_TYPE));
            String d = cvx.d(e.length() / 2);
            stringBuffer.append(cvx.e(3));
            stringBuffer.append(d);
            stringBuffer.append(e);
        }
        return stringBuffer;
    }

    public static StringBuffer getOperatorRunPlanDateStringTlv(StringBuffer stringBuffer, JSONObject jSONObject) throws JSONException {
        if (stringBuffer != null && jSONObject != null) {
            int i = jSONObject.has(RUN_PLAN_DATE) ? (int) (jSONObject.getLong(RUN_PLAN_DATE) / 1000) : 0;
            String str = cvx.e(i >> 24) + cvx.e((i >> 16) & 255) + cvx.e((i >> 8) & 255) + cvx.e(i & 255);
            String d = cvx.d(str.length() / 2);
            String e = cvx.e(4);
            if (jSONObject.getInt(SPORT_TYPE) == 2) {
                stringBuffer.append(e);
                stringBuffer.append(d);
                stringBuffer.append(str);
            }
        }
        return stringBuffer;
    }

    public static StringBuffer getOperatorWorkoutTypeStringTlv(StringBuffer stringBuffer, JSONObject jSONObject) throws JSONException {
        if (stringBuffer != null && jSONObject != null) {
            String e = cvx.e(transSportType(jSONObject.has(HwExerciseConstants.JSON_NAME_WORKOUT_TYPE) ? jSONObject.getInt(HwExerciseConstants.JSON_NAME_WORKOUT_TYPE) : 258));
            String d = cvx.d(e.length() / 2);
            stringBuffer.append(cvx.e(5));
            stringBuffer.append(d);
            stringBuffer.append(e);
        }
        return stringBuffer;
    }

    private static StringBuffer getOperationTimeValueHex(StringBuffer stringBuffer, JSONObject jSONObject) throws JSONException {
        if (stringBuffer != null && jSONObject != null) {
            int i = (int) (jSONObject.getLong("operation_time") / 1000);
            String str = cvx.e(i >> 24) + cvx.e((i >> 16) & 255) + cvx.e((i >> 8) & 255) + cvx.e(i & 255);
            String d = cvx.d(str.length() / 2);
            stringBuffer.append(cvx.e(6));
            stringBuffer.append(d);
            stringBuffer.append(str);
        }
        return stringBuffer;
    }

    public static StringBuffer getOperatorSonStructStringTlv(StringBuffer stringBuffer, JSONObject jSONObject, int i, String str, String str2) throws JSONException {
        if (stringBuffer != null && jSONObject != null && str != null && str2 != null) {
            stringBuffer = getOperationTimeValueHex(stringBuffer, jSONObject);
            if (i != 0) {
                stringBuffer.append(str2);
                stringBuffer.append(str);
                String b = cvx.b(jSONObject.has("distance") ? jSONObject.getInt("distance") : 0);
                String d = cvx.d(b.length() / 2);
                stringBuffer.append(cvx.e(8));
                stringBuffer.append(d);
                stringBuffer.append(b);
                String b2 = cvx.b(jSONObject.has("calorie") ? jSONObject.getInt("calorie") : 0);
                String d2 = cvx.d(b2.length() / 2);
                stringBuffer.append(cvx.e(9));
                stringBuffer.append(d2);
                stringBuffer.append(b2);
                String b3 = cvx.b(jSONObject.has("duration") ? jSONObject.getInt("duration") : 0);
                String d3 = cvx.d(b3.length() / 2);
                stringBuffer.append(cvx.e(10));
                stringBuffer.append(d3);
                stringBuffer.append(b3);
            }
        }
        return stringBuffer;
    }

    public static StringBuffer getOperatorVersionStringTlv(StringBuffer stringBuffer, JSONObject jSONObject) throws JSONException {
        if (stringBuffer != null && jSONObject != null) {
            String e = cvx.e(jSONObject.has("version") ? jSONObject.getInt("version") : 0);
            String d = cvx.d(e.length() / 2);
            String e2 = cvx.e(11);
            if (jSONObject.has("version")) {
                stringBuffer.append(e2);
                stringBuffer.append(d);
                stringBuffer.append(e);
            }
        }
        return stringBuffer;
    }

    public static StringBuffer getOperatorStartTimeStringTlv(StringBuffer stringBuffer, JSONObject jSONObject) throws JSONException {
        if (stringBuffer != null && jSONObject != null) {
            int i = jSONObject.has("start_time") ? (int) (jSONObject.getLong("start_time") / 1000) : 0;
            String str = cvx.e(i >> 24) + cvx.e((i >> 16) & 255) + cvx.e((i >> 8) & 255) + cvx.e(i & 255);
            String d = cvx.d(str.length() / 2);
            stringBuffer.append(cvx.e(12));
            stringBuffer.append(d);
            stringBuffer.append(str);
            LogUtil.a(TAG, "5.23.1 :", stringBuffer.toString());
        }
        return stringBuffer;
    }

    public static StringBuffer getOperatorRunCourseVersionStringTlv(StringBuffer stringBuffer, JSONObject jSONObject) throws JSONException {
        if (stringBuffer != null && jSONObject != null) {
            String e = cvx.e(jSONObject.has("run_course_version") ? jSONObject.getInt("run_course_version") : 0);
            String d = cvx.d(e.length() / 2);
            String e2 = cvx.e(13);
            if (jSONObject.has("run_course_version")) {
                stringBuffer.append(e2);
                stringBuffer.append(d);
                stringBuffer.append(e);
            }
        }
        return stringBuffer;
    }

    public static StringBuffer getOperatorForbidPauseStringTlv(StringBuffer stringBuffer, JSONObject jSONObject) throws JSONException {
        if (stringBuffer != null && jSONObject != null) {
            String e = cvx.e(jSONObject.has("forbid_pause") ? jSONObject.getInt("forbid_pause") : 0);
            String d = cvx.d(e.length() / 2);
            stringBuffer.append(cvx.e(14));
            stringBuffer.append(d);
            stringBuffer.append(e);
        }
        return stringBuffer;
    }

    private static void getWorkoutAvgSpeed(int i, StringBuffer stringBuffer) {
        if (stringBuffer == null) {
            return;
        }
        String a2 = cvx.a(i);
        String d = cvx.d(a2.length() / 2);
        stringBuffer.append(cvx.e(8));
        stringBuffer.append(d);
        stringBuffer.append(a2);
    }

    public static void getWorkoutStringTlvIncludeDistance(StringBuffer stringBuffer, JSONObject jSONObject) throws JSONException {
        if (stringBuffer == null || jSONObject == null) {
            return;
        }
        String b = cvx.b(jSONObject.getInt("exercise_duration"));
        String d = cvx.d(b.length() / 2);
        stringBuffer.append(cvx.e(2));
        stringBuffer.append(d);
        stringBuffer.append(b);
        String b2 = cvx.b(jSONObject.getInt("distance") * 10);
        String d2 = cvx.d(b2.length() / 2);
        stringBuffer.append(cvx.e(3));
        stringBuffer.append(d2);
        stringBuffer.append(b2);
        String b3 = cvx.b(jSONObject.getInt("calorie"));
        String d3 = cvx.d(b3.length() / 2);
        stringBuffer.append(cvx.e(4));
        stringBuffer.append(d3);
        stringBuffer.append(b3);
        String a2 = cvx.a(jSONObject.getInt("speed"));
        String d4 = cvx.d(a2.length() / 2);
        stringBuffer.append(cvx.e(5));
        stringBuffer.append(d4);
        stringBuffer.append(a2);
        String e = cvx.e(jSONObject.has("hr") ? jSONObject.getInt("hr") : 0);
        String d5 = cvx.d(e.length() / 2);
        stringBuffer.append(cvx.e(7));
        stringBuffer.append(d5);
        stringBuffer.append(e);
        getWorkoutAvgSpeed(jSONObject.has("avg_speed") ? jSONObject.getInt("avg_speed") : 0, stringBuffer);
    }

    public static void getWorkoutStringTlvIncludeAvgPace(StringBuffer stringBuffer, JSONObject jSONObject) throws JSONException {
        if (stringBuffer == null || jSONObject == null) {
            return;
        }
        String a2 = cvx.a(jSONObject.has("avg_pace") ? jSONObject.getInt("avg_pace") : 0);
        String d = cvx.d(a2.length() / 2);
        stringBuffer.append(cvx.e(9));
        stringBuffer.append(d);
        stringBuffer.append(a2);
        String b = cvx.b(jSONObject.has("total_rise") ? jSONObject.getInt("total_rise") : 0);
        String d2 = cvx.d(b.length() / 2);
        stringBuffer.append(cvx.e(10));
        stringBuffer.append(d2);
        stringBuffer.append(b);
        String b2 = cvx.b(jSONObject.has("total_descend") ? jSONObject.getInt("total_descend") : 0);
        String d3 = cvx.d(b2.length() / 2);
        stringBuffer.append(cvx.e(11));
        stringBuffer.append(d3);
        stringBuffer.append(b2);
        String b3 = cvx.b(jSONObject.has("altitude") ? jSONObject.getInt("altitude") : 0);
        String d4 = cvx.d(b3.length() / 2);
        stringBuffer.append(cvx.e(12));
        stringBuffer.append(d4);
        stringBuffer.append(b3);
    }

    public static void getWorkoutStringTlvIncludeAerobicTrainEffect(StringBuffer stringBuffer, JSONObject jSONObject) throws JSONException {
        if (stringBuffer == null || jSONObject == null) {
            return;
        }
        String e = cvx.e(jSONObject.has("aerobic_te") ? jSONObject.getInt("aerobic_te") : 0);
        String d = cvx.d(e.length() / 2);
        stringBuffer.append(cvx.e(13));
        stringBuffer.append(d);
        stringBuffer.append(e);
        String e2 = cvx.e(jSONObject.has("anaerobic_te") ? jSONObject.getInt("anaerobic_te") : 0);
        String d2 = cvx.d(e2.length() / 2);
        stringBuffer.append(cvx.e(14));
        stringBuffer.append(d2);
        stringBuffer.append(e2);
        String e3 = cvx.e(jSONObject.has("performance_condition") ? jSONObject.getInt("performance_condition") : 0);
        String d3 = cvx.d(e3.length() / 2);
        stringBuffer.append(cvx.e(15));
        stringBuffer.append(d3);
        stringBuffer.append(e3);
        String e4 = cvx.e(jSONObject.has(OPERATOR_TYPE) ? jSONObject.getInt(OPERATOR_TYPE) : 0);
        String d4 = cvx.d(e4.length() / 2);
        stringBuffer.append(cvx.e(16));
        stringBuffer.append(d4);
        stringBuffer.append(e4);
    }

    public static void getWorkoutStringTlvIncludeRunningAction(StringBuffer stringBuffer, JSONObject jSONObject) throws JSONException {
        if (stringBuffer == null || jSONObject == null) {
            return;
        }
        String e = cvx.e(jSONObject.has("running_course_number") ? jSONObject.getInt("running_course_number") : 0);
        String d = cvx.d(e.length() / 2);
        stringBuffer.append(cvx.e(17));
        stringBuffer.append(d);
        stringBuffer.append(e);
        String e2 = cvx.e(jSONObject.has("running_course_action_count") ? jSONObject.getInt("running_course_action_count") : 0);
        String d2 = cvx.d(e2.length() / 2);
        stringBuffer.append(cvx.e(18));
        stringBuffer.append(d2);
        stringBuffer.append(e2);
        String c = cvx.c(jSONObject.has("running_course_action_id") ? jSONObject.getString("running_course_action_id") : "");
        String d3 = cvx.d(c.length() / 2);
        stringBuffer.append(cvx.e(19));
        stringBuffer.append(d3);
        stringBuffer.append(c);
    }

    public static void getWorkoutFifthPartStringTlvIncludeRunningCourse(StringBuffer stringBuffer, JSONObject jSONObject) throws JSONException {
        if (stringBuffer == null || jSONObject == null) {
            return;
        }
        String e = cvx.e(jSONObject.has("running_course_left_type") ? jSONObject.getInt("running_course_left_type") : 0);
        String d = cvx.d(e.length() / 2);
        stringBuffer.append(cvx.e(20));
        stringBuffer.append(d);
        stringBuffer.append(e);
    }

    public static void getWorkoutStringTlvIncludeRunningCourseContent(StringBuffer stringBuffer, JSONObject jSONObject) throws JSONException {
        if (stringBuffer == null || jSONObject == null) {
            return;
        }
        String b = cvx.b(jSONObject.has("running_course_content") ? jSONObject.getInt("running_course_content") : 0);
        String d = cvx.d(b.length() / 2);
        stringBuffer.append(cvx.e(21));
        stringBuffer.append(d);
        stringBuffer.append(b);
        String a2 = cvx.a(jSONObject.has("step_cadence") ? jSONObject.getInt("step_cadence") : 0);
        String d2 = cvx.d(a2.length() / 2);
        stringBuffer.append(cvx.e(22));
        stringBuffer.append(d2);
        stringBuffer.append(a2);
        String b2 = cvx.b(jSONObject.has("step") ? jSONObject.getInt("step") : 0);
        String d3 = cvx.d(b2.length() / 2);
        stringBuffer.append(cvx.e(23));
        stringBuffer.append(d3);
        stringBuffer.append(b2);
        if (jSONObject.has("pace")) {
            String a3 = cvx.a(jSONObject.getInt("pace"));
            String d4 = cvx.d(a3.length() / 2);
            stringBuffer.append(cvx.e(6));
            stringBuffer.append(d4);
            stringBuffer.append(a3);
        }
    }

    public static void getWorkoutStringTlvIncludeNewSpeed(StringBuffer stringBuffer, JSONObject jSONObject) throws JSONException {
        if (stringBuffer == null || jSONObject == null) {
            LogUtil.h(TAG, "getWorkoutStringTlvIncludeNewSpeed, input parameters is illegal");
            return;
        }
        String a2 = cvx.a(jSONObject.has("speed_new") ? jSONObject.getInt("speed_new") : 0);
        String d = cvx.d(a2.length() / 2);
        stringBuffer.append(cvx.e(24));
        stringBuffer.append(d);
        stringBuffer.append(a2);
        String a3 = cvx.a(jSONObject.has("avg_speed_new") ? jSONObject.getInt("avg_speed_new") : 0);
        String d2 = cvx.d(a3.length() / 2);
        stringBuffer.append(cvx.e(25));
        stringBuffer.append(d2);
        stringBuffer.append(a3);
    }

    public static void getWorkoutStringTlvIncludeEquipmentLinkage(StringBuffer stringBuffer, JSONObject jSONObject) throws JSONException {
        if (stringBuffer == null || jSONObject == null) {
            LogUtil.h(TAG, "getWorkoutStringTlvIncludeNewSpeed, input parameters is illegal");
            return;
        }
        if (jSONObject.has("linkage_cadence")) {
            String a2 = cvx.a(jSONObject.getInt("linkage_cadence"));
            String d = cvx.d(a2.length() / 2);
            stringBuffer.append(cvx.e(26));
            stringBuffer.append(d);
            stringBuffer.append(a2);
        }
        if (jSONObject.has("linkage_pulp_frequency")) {
            String a3 = cvx.a(jSONObject.getInt("linkage_pulp_frequency"));
            String d2 = cvx.d(a3.length() / 2);
            stringBuffer.append(cvx.e(27));
            stringBuffer.append(d2);
            stringBuffer.append(a3);
        }
        if (jSONObject.has("linkage_slurry")) {
            String b = cvx.b(jSONObject.getInt("linkage_slurry"));
            String d3 = cvx.d(b.length() / 2);
            stringBuffer.append(cvx.e(28));
            stringBuffer.append(d3);
            stringBuffer.append(b);
        }
        if (jSONObject.has("linkage_power")) {
            String a4 = cvx.a(jSONObject.getInt("linkage_power"));
            String d4 = cvx.d(a4.length() / 2);
            stringBuffer.append(cvx.e(29));
            stringBuffer.append(d4);
            stringBuffer.append(a4);
        }
        includeEquipmentLinkage(stringBuffer, jSONObject);
    }

    private static void includeEquipmentLinkage(StringBuffer stringBuffer, JSONObject jSONObject) throws JSONException {
        if (jSONObject.has("linkage_resistance_level")) {
            String e = cvx.e(jSONObject.getInt("linkage_resistance_level"));
            String d = cvx.d(e.length() / 2);
            stringBuffer.append(cvx.e(30));
            stringBuffer.append(d);
            stringBuffer.append(e);
        }
        if (jSONObject.has("linkage_resistance_level_max")) {
            String e2 = cvx.e(jSONObject.getInt("linkage_resistance_level_max"));
            String d2 = cvx.d(e2.length() / 2);
            stringBuffer.append(cvx.e(31));
            stringBuffer.append(d2);
            stringBuffer.append(e2);
        }
        if (jSONObject.has("linkage_resistance_level_min")) {
            String e3 = cvx.e(jSONObject.getInt("linkage_resistance_level_min"));
            String d3 = cvx.d(e3.length() / 2);
            stringBuffer.append(cvx.e(32));
            stringBuffer.append(d3);
            stringBuffer.append(e3);
        }
    }

    public static void parseRunPostureDataInfo(List<String> list, WorkoutDataInfo workoutDataInfo) {
        if (list == null || workoutDataInfo == null) {
            LogUtil.h(TAG, "parseRunPostureDataInfo, input parameters is illegal");
            return;
        }
        LogUtil.a(TAG, "parseRunPostureDataInfo enter");
        StringBuilder sb = new StringBuilder(getFirstValueFromList(list));
        list.remove(0);
        sb.append(getFirstValueFromList(list));
        list.remove(0);
        LogUtil.c(TAG, "parseRunPostureDataInfo setCadence info:", sb);
        ffr ffrVar = new ffr();
        ffrVar.e(CommonUtil.w(sb.toString()));
        sb.setLength(0);
        sb.append(getFirstValueFromList(list));
        list.remove(0);
        sb.append(getFirstValueFromList(list));
        list.remove(0);
        LogUtil.c(TAG, "parseRunPostureDataInfo setStepLength info:", sb);
        ffrVar.f(CommonUtil.w(sb.toString()));
        sb.setLength(0);
        sb.append(getFirstValueFromList(list));
        list.remove(0);
        sb.append(getFirstValueFromList(list));
        list.remove(0);
        LogUtil.c(TAG, "parseRunPostureDataInfo setGroundContactTime info:", sb);
        ffrVar.c(CommonUtil.w(sb.toString()));
        sb.setLength(0);
        sb.append(getFirstValueFromList(list));
        list.remove(0);
        LogUtil.c(TAG, "parseRunPostureDataInfo setGroundImpactAcceleration info:", sb);
        ffrVar.d(CommonUtil.w(sb.toString()));
        parseOtherRunPostureDataInfo(list, sb, ffrVar);
        sb.setLength(0);
        sb.append(getFirstValueFromList(list));
        LogUtil.c(TAG, "parseRunPostureDataInfo setEversionExcursion info:", sb);
        ffrVar.b(CommonUtil.w(sb.toString()));
        LogUtil.c(TAG, "parseRunPostureDataInfo runPostureDataInfo:", ffrVar);
        workoutDataInfo.setRunPostureDataInfo(ffrVar);
    }

    private static void parseOtherRunPostureDataInfo(List<String> list, StringBuilder sb, ffr ffrVar) {
        sb.setLength(0);
        sb.append(getFirstValueFromList(list));
        list.remove(0);
        sb.append(getFirstValueFromList(list));
        list.remove(0);
        LogUtil.c(TAG, "parseRunPostureDataInfo setSwingAngle info:", sb);
        ffrVar.n(CommonUtil.w(sb.toString()));
        sb.setLength(0);
        sb.append(getFirstValueFromList(list));
        list.remove(0);
        LogUtil.c(TAG, "parseRunPostureDataInfo setForeFootStrikePattern info:", sb);
        ffrVar.a(CommonUtil.w(sb.toString()));
        sb.setLength(0);
        sb.append(getFirstValueFromList(list));
        list.remove(0);
        LogUtil.c(TAG, "parseRunPostureDataInfo setWholeFootStrikePattern info:", sb);
        ffrVar.o(CommonUtil.w(sb.toString()));
        sb.setLength(0);
        sb.append(getFirstValueFromList(list));
        list.remove(0);
        LogUtil.c(TAG, "parseRunPostureDataInfo setHindPawStrikePattern info:", sb);
        ffrVar.j(CommonUtil.w(sb.toString()));
    }

    public static void dealTriathlon(bmq bmqVar, WorkoutRecordStatistic workoutRecordStatistic) {
        if (bmqVar == null || workoutRecordStatistic == null) {
            LogUtil.h(TAG, "dealTriathlon, input parameters is illegal");
            return;
        }
        List<bmq> a2 = bmqVar.a();
        ArrayList arrayList = new ArrayList(12);
        Iterator<bmq> it = a2.iterator();
        while (it.hasNext()) {
            List<bmu> d = it.next().d();
            TriathlonStruct triathlonStruct = new TriathlonStruct();
            for (bmu bmuVar : d) {
                try {
                    switch (bmuVar.a()) {
                        case 40:
                            triathlonStruct.setWorkoutRelationType(cvx.c(bmuVar.c(), 0));
                            break;
                        case 41:
                            triathlonStruct.setWorkoutLinkDetailsType(cvx.c(bmuVar.c(), 0));
                            break;
                        case 42:
                            triathlonStruct.setWorkoutLinkDetailsStartTime(cvx.c(bmuVar.c(), 0));
                            break;
                        case 43:
                            triathlonStruct.setWorkoutLinkDetailsEndTime(cvx.c(bmuVar.c(), 0));
                            break;
                        case 44:
                            triathlonStruct.setWorkoutLinkDetailsTotalTime(cvx.c(bmuVar.c(), 0));
                            break;
                        default:
                            parseOtherTriathlonInfo(bmuVar, triathlonStruct);
                            break;
                    }
                } catch (NumberFormatException e) {
                    LogUtil.b(TAG, e.getMessage());
                }
            }
            if (triathlonStruct.getWorkoutRelationType() != 0) {
                arrayList.add(triathlonStruct);
            }
        }
        if (arrayList.size() != 0) {
            workoutRecordStatistic.setTriathlonStructList(arrayList);
        }
    }

    private static void parseOtherTriathlonInfo(bmu bmuVar, TriathlonStruct triathlonStruct) throws NumberFormatException {
        switch (bmuVar.a()) {
            case 45:
                triathlonStruct.setWorkoutLinkDetailsDistance(cvx.c(bmuVar.c(), 0));
                break;
            case 46:
                triathlonStruct.setWorkoutLinkDetailsCalorie(cvx.c(bmuVar.c(), 0) * 1000);
                break;
            case 47:
                triathlonStruct.setWorkoutLinkTransitionTime(cvx.c(bmuVar.c(), 0));
                break;
            case 48:
                triathlonStruct.setWorkoutLinkHasDetail(cvx.c(bmuVar.c(), 0) == 1);
                break;
            default:
                LogUtil.c(TAG, "parseOtherTriathlonInfo, this type not support parse.");
                break;
        }
    }

    public static void parsePaceMapDataStruct(cwe cweVar, WorkoutRecordPaceMap workoutRecordPaceMap) {
        for (cwd cwdVar : cweVar.e()) {
            int w = CommonUtil.w(cwdVar.e());
            if (w == 4) {
                workoutRecordPaceMap.setDistance(CommonUtil.w(cwdVar.c()));
            } else if (w == 5) {
                workoutRecordPaceMap.setUnitType(CommonUtil.w(cwdVar.c()));
            } else if (w == 6) {
                workoutRecordPaceMap.setPace((int) Long.parseLong(cwdVar.c(), 16));
            } else if (w == 7) {
                workoutRecordPaceMap.setPointIndex(CommonUtil.w(cwdVar.c()));
            } else if (w == 9) {
                workoutRecordPaceMap.setLastLessDistance(CommonUtil.w(cwdVar.c()));
                workoutRecordPaceMap.setIsLastLessDistance(true);
            } else {
                LogUtil.h(TAG, "parsePaceMapDataStruct tlvChild parse else");
            }
        }
    }

    public static void parseSwimSectionDataStruct(cwe cweVar, Bundle bundle) {
        for (cwd cwdVar : cweVar.e()) {
            switch (CommonUtil.w(cwdVar.e())) {
                case 4:
                    bundle.putInt("distance", CommonUtil.w(cwdVar.c()));
                    break;
                case 5:
                    bundle.putInt("unit", CommonUtil.w(cwdVar.c()));
                    break;
                case 6:
                    bundle.putLong("pace", CommonUtil.y(cwdVar.c()));
                    break;
                case 7:
                    bundle.putInt(HwExerciseConstants.JSON_NAME_POINT_INDEX, CommonUtil.w(cwdVar.c()));
                    break;
                case 8:
                default:
                    LogUtil.h(TAG, "parseSwimSectionDataStruct tlvChild parse else");
                    break;
                case 9:
                    bundle.putInt(HwExerciseConstants.JSON_NAME_SECTION_NUM, CommonUtil.w(cwdVar.c()));
                    break;
                case 10:
                    bundle.putInt(HwExerciseConstants.JSON_NAME_SWIM_TYPE, CommonUtil.w(cwdVar.c()));
                    break;
                case 11:
                    bundle.putInt(HwExerciseConstants.JSON_NAME_SWIM_PULL_TIMES, CommonUtil.w(cwdVar.c()));
                    break;
                case 12:
                    bundle.putInt(HwExerciseConstants.JSON_NAME_SWIM_AVG_SWOLF, CommonUtil.w(cwdVar.c()));
                    break;
                case 13:
                    bundle.putLong(HwExerciseConstants.JSON_NAME_SWIM_TIME, CommonUtil.y(cwdVar.c()));
                    break;
            }
        }
    }

    public static void parseWorkoutReportPlayData(List<cwd> list, kon konVar) {
        for (cwd cwdVar : list) {
            switch (CommonUtil.w(cwdVar.e())) {
                case 2:
                    konVar.c(CommonUtil.y(cwdVar.c()) * 1000);
                    break;
                case 3:
                    konVar.f(CommonUtil.w(cwdVar.c()));
                    break;
                case 4:
                    konVar.j(CommonUtil.w(cwdVar.c()));
                    break;
                case 5:
                    konVar.l(CommonUtil.w(cwdVar.c()));
                    break;
                case 6:
                    konVar.a(CommonUtil.w(cwdVar.c()));
                    break;
                case 7:
                    konVar.b(CommonUtil.w(cwdVar.c()));
                    break;
                case 8:
                    konVar.b(CommonUtil.y(cwdVar.c()) * 10);
                    break;
                case 9:
                    konVar.g(CommonUtil.w(cwdVar.c()));
                    break;
                case 10:
                    konVar.n(CommonUtil.w(cwdVar.c()));
                    break;
                default:
                    parseWorkoutReportPlayDataElse(cwdVar, konVar);
                    break;
            }
        }
    }

    private static void parseWorkoutReportPlayDataElse(cwd cwdVar, kon konVar) {
        switch (CommonUtil.w(cwdVar.e())) {
            case 11:
                konVar.o(CommonUtil.w(cwdVar.c()));
                break;
            case 12:
                konVar.m((int) CommonUtil.y(cwdVar.c()));
                break;
            case 13:
                konVar.b(CommonUtil.w(cwdVar.c()));
                break;
            case 14:
                konVar.e(CommonUtil.w(cwdVar.c()));
                break;
            case 15:
                konVar.i(CommonUtil.w(cwdVar.c()));
                break;
            case 16:
                konVar.d(CommonUtil.y(cwdVar.c()) * 1000);
                break;
            case 17:
                konVar.h(CommonUtil.a(cwdVar.c(), 10));
                break;
            case 18:
                konVar.e(CommonUtil.y(cwdVar.c()));
                break;
            case 19:
                konVar.a(CommonUtil.w(cwdVar.c()));
                break;
            case 20:
                konVar.c(CommonUtil.w(cwdVar.c()));
                break;
            default:
                LogUtil.c(TAG, "parseWorkoutReportPlayDataElse tlvChild parse else");
                break;
        }
    }

    public static void parseCommonSectionData(cwe cweVar, CommonSectionInfo commonSectionInfo, int i) {
        if (cweVar == null || commonSectionInfo == null) {
            LogUtil.h(TAG, "tlvFather or commonSectionInfo is null");
            return;
        }
        for (cwd cwdVar : cweVar.e()) {
            parseRunSectionData(cwdVar, commonSectionInfo);
            parseGolfSectionData(cwdVar, commonSectionInfo);
            parseSkiSectionData(cwdVar, commonSectionInfo);
            parseFitnessSectionData(cwdVar, commonSectionInfo);
        }
        List<cwe> a2 = cweVar.a();
        ArrayList arrayList = new ArrayList(16);
        Iterator<cwe> it = a2.iterator();
        while (it.hasNext()) {
            List<cwd> e = it.next().e();
            if (i == 19) {
                parseGolfSectionTrackData(e, arrayList);
            }
            parseCommonFieldData(e, commonSectionInfo.getCommonFieldMap());
        }
        commonSectionInfo.setSectionTrackStruct(arrayList);
    }

    private static void parseCommonFieldData(List<cwd> list, Map<String, List<String>> map) {
        StatisticExtendDataStruct statisticExtendDataStruct = new StatisticExtendDataStruct();
        for (cwd cwdVar : list) {
            int w = CommonUtil.w(cwdVar.e());
            if (w == 56) {
                statisticExtendDataStruct.setFieldName(String.valueOf(CommonUtil.w(cwdVar.c())));
            } else if (w == 57) {
                statisticExtendDataStruct.setFieldValue(cvx.e(cwdVar.c()));
            } else {
                LogUtil.c(TAG, "parseCommonFieldData command");
            }
        }
        String fieldName = statisticExtendDataStruct.getFieldName();
        String fieldValue = statisticExtendDataStruct.getFieldValue();
        if (fieldName == null || fieldValue == null) {
            LogUtil.h(TAG, "parseCommonFieldData invalid data.");
        } else {
            if (!map.containsKey(fieldName)) {
                ArrayList arrayList = new ArrayList(16);
                arrayList.add(fieldValue);
                map.put(statisticExtendDataStruct.getFieldName(), arrayList);
                return;
            }
            map.get(fieldName).add(fieldValue);
        }
    }

    private static void parseRunSectionData(cwd cwdVar, CommonSectionInfo commonSectionInfo) {
        switch (CommonUtil.w(cwdVar.e())) {
            case 5:
                commonSectionInfo.setSectionNum(CommonUtil.w(cwdVar.c()));
                break;
            case 6:
                commonSectionInfo.setSectionTime(CommonUtil.y(cwdVar.c()));
                break;
            case 7:
                commonSectionInfo.setSectionDistance(CommonUtil.y(cwdVar.c()));
                break;
            case 8:
                commonSectionInfo.setSectionPace(CommonUtil.w(cwdVar.c()));
                break;
            case 9:
                commonSectionInfo.setSectionHeartRate(CommonUtil.w(cwdVar.c()));
                break;
            case 10:
                commonSectionInfo.setSectionCadence(CommonUtil.w(cwdVar.c()));
                break;
            case 11:
                commonSectionInfo.setSectionStepLength(CommonUtil.w(cwdVar.c()));
                break;
            case 12:
                commonSectionInfo.setSectionTotalRise(CommonUtil.y(cwdVar.c()));
                break;
            case 13:
                commonSectionInfo.setSectionTotalDescend(CommonUtil.y(cwdVar.c()));
                break;
            case 14:
                commonSectionInfo.setSectionGroundContactTime(CommonUtil.w(cwdVar.c()));
                break;
            case 15:
                commonSectionInfo.setSectionGroundImpactAcceleration(CommonUtil.w(cwdVar.c()));
                break;
            case 16:
                commonSectionInfo.setSectionSwingAngle(CommonUtil.w(cwdVar.c()));
                break;
            case 17:
                commonSectionInfo.setSectionEversionExcursion(CommonUtil.w(cwdVar.c()));
                break;
            default:
                parseRunSectionDataBranch(cwdVar, commonSectionInfo);
                break;
        }
    }

    private static void parseRunSectionDataBranch(cwd cwdVar, CommonSectionInfo commonSectionInfo) {
        int w = CommonUtil.w(cwdVar.e());
        if (w == 34) {
            commonSectionInfo.setSectionAvgCadence(CommonUtil.w(cwdVar.c()));
        }
        if (w == 35) {
            commonSectionInfo.setSectionIntervalTrainingType(CommonUtil.w(cwdVar.c()));
            return;
        }
        switch (w) {
            case 40:
                commonSectionInfo.setSectionDivingMaxDepth(CommonUtil.w(cwdVar.c()));
                break;
            case 41:
                commonSectionInfo.setSectionDivingUnderwaterTime(CommonUtil.w(cwdVar.c()));
                break;
            case 42:
                commonSectionInfo.setSectionDivingBreakTime(CommonUtil.w(cwdVar.c()));
                break;
            default:
                LogUtil.c(TAG, "parseRunSectionDataBranch command");
                break;
        }
    }

    private static void parseGolfSectionData(cwd cwdVar, CommonSectionInfo commonSectionInfo) {
        int w = CommonUtil.w(cwdVar.e());
        switch (w) {
            case 18:
                commonSectionInfo.setSectionBackSwingTime(CommonUtil.w(cwdVar.c()));
                break;
            case 19:
                commonSectionInfo.setSectionDownSwingTime(CommonUtil.w(cwdVar.c()));
                break;
            case 20:
                commonSectionInfo.setSectionHeadSpeed(CommonUtil.w(cwdVar.c()));
                break;
            case 21:
                commonSectionInfo.setSectionSwingTempo(CommonUtil.w(cwdVar.c()));
                break;
            default:
                switch (w) {
                    case 43:
                        commonSectionInfo.setSectionHoleId(CommonUtil.y(cwdVar.c()));
                        break;
                    case 44:
                        commonSectionInfo.setSectionPar(CommonUtil.w(cwdVar.c()));
                        break;
                    case 45:
                        commonSectionInfo.setSectionHoleScore(CommonUtil.w(cwdVar.c()));
                        break;
                    case 46:
                        commonSectionInfo.setSectionHolePutts(CommonUtil.w(cwdVar.c()));
                        break;
                    case 47:
                        commonSectionInfo.setSectionHolePenalty(CommonUtil.w(cwdVar.c()));
                        break;
                    case 48:
                        commonSectionInfo.setSectionHoleHits(CommonUtil.w(cwdVar.c()));
                        break;
                    case 49:
                        commonSectionInfo.setSectionHandicap(Integer.valueOf(cwdVar.c(), 16).byteValue());
                        break;
                    case 50:
                        commonSectionInfo.setSectionValidTracks(CommonUtil.w(cwdVar.c()));
                        break;
                    default:
                        LogUtil.c(TAG, "parseGolfSectionData command");
                        break;
                }
        }
    }

    private static void parseGolfSectionTrackData(List<cwd> list, List<GolfShotTrack> list2) {
        GolfShotTrack.e eVar = new GolfShotTrack.e();
        for (cwd cwdVar : list) {
            switch (CommonUtil.w(cwdVar.e())) {
                case 52:
                    eVar.b(cvx.b(cwdVar.c()));
                    break;
                case 53:
                    eVar.e(cvx.b(cwdVar.c()));
                    break;
                case 54:
                    eVar.d(CommonUtil.w(cwdVar.c()));
                    break;
                default:
                    LogUtil.c(TAG, "parseGolfSectionTrackData command");
                    break;
            }
        }
        list2.add(eVar.c());
    }

    private static void parseSkiSectionData(cwd cwdVar, CommonSectionInfo commonSectionInfo) {
        switch (CommonUtil.w(cwdVar.e())) {
            case 22:
                commonSectionInfo.setSectionMaxSpeed(CommonUtil.w(cwdVar.c()));
                break;
            case 23:
                commonSectionInfo.setSectionAvgSpeed(CommonUtil.w(cwdVar.c()));
                break;
            case 24:
                commonSectionInfo.setSectionStartGpsPointIndex(CommonUtil.y(cwdVar.c()));
                break;
            case 25:
                commonSectionInfo.setSectionEndGpsPointIndex(CommonUtil.y(cwdVar.c()));
                break;
            case 26:
                commonSectionInfo.setSectionCableStartGpsPointIndex(CommonUtil.y(cwdVar.c()));
                break;
            case 27:
                commonSectionInfo.setSectionCableEndGpsPointIndex(CommonUtil.y(cwdVar.c()));
                break;
            case 28:
                commonSectionInfo.setSectionSlopeDegree(CommonUtil.w(cwdVar.c()));
                break;
            case 29:
                commonSectionInfo.setSectionSlopePercent(CommonUtil.w(cwdVar.c()));
                break;
            default:
                LogUtil.c(TAG, "parseSkiSectionDataElse else command");
                break;
        }
    }

    private static void parseFitnessSectionData(cwd cwdVar, CommonSectionInfo commonSectionInfo) {
        switch (CommonUtil.w(cwdVar.e())) {
            case 30:
                commonSectionInfo.setSectionActionId(cvx.e(cwdVar.c()));
                break;
            case 31:
                commonSectionInfo.setSectionActionType(CommonUtil.w(cwdVar.c()));
                break;
            case 32:
                commonSectionInfo.setSectionActionResultValue(CommonUtil.w(cwdVar.c()));
                break;
            case 33:
                commonSectionInfo.setSectionActionTargetValue(CommonUtil.w(cwdVar.c()));
                break;
            default:
                LogUtil.c(TAG, "parseFitnessSectionData else command");
                break;
        }
    }

    private static String getFirstValueFromList(List<String> list) {
        return (list == null || list.isEmpty()) ? "" : list.get(0);
    }

    public static HashMap<String, ArrayList> filterHeartRate(List<HeartRateData> list) {
        int i;
        HashMap<String, ArrayList> hashMap = new HashMap<>(16);
        ArrayList arrayList = new ArrayList(16);
        ArrayList arrayList2 = new ArrayList(16);
        hashMap.put(HEART_RATE_LIST, arrayList);
        hashMap.put(INVALID_HEART_RATE_LIST, arrayList2);
        if (list != null && !list.isEmpty()) {
            int size = list.size() - 1;
            int i2 = size;
            while (true) {
                i = -1;
                if (i2 < 0) {
                    i2 = -1;
                    break;
                }
                HeartRateData heartRateData = list.get(i2);
                if ((i2 == size && heartRateData.acquireHeartRate() != 254) || heartRateData.acquireHeartRate() != 254) {
                    break;
                }
                heartRateData.saveHeartRate(-1);
                arrayList2.add(heartRateData);
                i2--;
            }
            if (i2 != -1) {
                for (int i3 = 0; i3 <= i2; i3++) {
                    HeartRateData heartRateData2 = list.get(i3);
                    if ((i3 == 0 && heartRateData2.acquireHeartRate() != 254) || heartRateData2.acquireHeartRate() != 254) {
                        i = i3;
                        break;
                    }
                    heartRateData2.saveHeartRate(-1);
                    arrayList2.add(heartRateData2);
                }
            }
            filterMiddleHeartRate(list, arrayList, i, i2);
        }
        return hashMap;
    }

    private static void filterMiddleHeartRate(List<HeartRateData> list, List<HeartRateData> list2, int i, int i2) {
        if (i != -1) {
            while (i <= i2) {
                HeartRateData heartRateData = list.get(i);
                if (heartRateData.acquireHeartRate() != 254) {
                    list2.add(heartRateData);
                }
                i++;
            }
        }
    }

    public static void sortListByTime(ArrayList<HeartRateData> arrayList) {
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        Collections.sort(arrayList, new Comparator<HeartRateData>() { // from class: com.huawei.hwdevice.phoneprocess.mgr.exercise.utils.HwWorkoutServiceUtils.1
            @Override // java.util.Comparator
            public int compare(HeartRateData heartRateData, HeartRateData heartRateData2) {
                if (heartRateData.acquireTime() - heartRateData2.acquireTime() > 0) {
                    return 1;
                }
                return heartRateData.acquireTime() - heartRateData2.acquireTime() < 0 ? -1 : 0;
            }
        });
    }
}
