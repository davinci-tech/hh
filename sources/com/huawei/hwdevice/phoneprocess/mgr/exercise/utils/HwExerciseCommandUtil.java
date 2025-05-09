package com.huawei.hwdevice.phoneprocess.mgr.exercise.utils;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.huawei.datatype.DeviceCommand;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwWorkoutServiceManager;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.CommonSectionList;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.PaceIndexStruct;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.RunPlanInfo;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.RunPlanReminder;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.RunPlanStruct;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.TrainingStruct;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.cvx;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class HwExerciseCommandUtil {
    private static final int ALL_F_ONE_BYTE = 255;
    private static final int CHILD_NODE_BASE = 128;
    private static final int INT_VALUE_ZERO = 0;
    private static final int MOVE_RIGHT_ONE_BYTE_BITS = 8;
    private static final int MOVE_RIGHT_THREE_BYTE_BITS = 24;
    private static final int MOVE_RIGHT_TWO_BYTE_BITS = 16;
    private static final String PARAM_END_TIME = "endTime";
    private static final String PARAM_ID = "id";
    private static final String PARAM_START_TIME = "startTime";
    private static final int PLACE_HOLDERS = 2;
    private static final int REALTIME_INFO_LENGTH = 3;
    private static final String SPORT_TYPE = "sport_type";
    private static final String TAG = "HwExerciseCommandUtil";
    private static final int TLV_TYPE_ONE_BYTE_OFFSET = 128;
    private static final int WORKOUT_RECORD_LIST_LENGTH = 12;
    private static final int WORKOUT_RECORD_STATISTIC_LENGTH = 4;
    private static DeviceInfo sDeviceInfo;

    private HwExerciseCommandUtil() {
    }

    public static DeviceCommand getRunPlanParameterCommand() {
        LogUtil.a(TAG, "Enter getRunPlanParameterCommand");
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(22);
        deviceCommand.setCommandID(1);
        String d = cvx.d(0);
        String e = cvx.e(129);
        StringBuilder sb = new StringBuilder();
        sb.append(e);
        sb.append(d);
        deviceCommand.setDataLen(sb.length() / 2);
        deviceCommand.setDataContent(cvx.a(sb.toString()));
        return deviceCommand;
    }

    public static DeviceCommand getSetRunPlanForHealthParam(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        RunPlanInfo runPlanInfo = (RunPlanInfo) new Gson().fromJson(jSONObject.getString("runPlanInfo"), RunPlanInfo.class);
        List<RunPlanStruct> runPlanStructList = runPlanInfo.getRunPlanStructList();
        Object[] objArr = new Object[3];
        objArr[0] = "setRunPlanForHealth called";
        objArr[1] = ", setRunPlanForHealth runPlanStructList is null = ";
        objArr[2] = Boolean.valueOf(runPlanStructList == null);
        LogUtil.a(TAG, objArr);
        if (runPlanStructList == null) {
            return null;
        }
        DeviceCommand deviceCommand = new DeviceCommand();
        ArrayList arrayList = new ArrayList(16);
        for (int i = 0; i < runPlanStructList.size(); i++) {
            ArrayList arrayList2 = new ArrayList(16);
            setTrainingStructHexList(runPlanStructList, i, arrayList2);
            StringBuilder sb = new StringBuilder();
            for (int i2 = 0; i2 < arrayList2.size(); i2++) {
                sb.append((String) arrayList2.get(i2));
            }
            setRunPlanStructList(runPlanStructList, arrayList, i, sb);
        }
        String normalPlanValue = setNormalPlanValue(runPlanInfo, arrayList);
        String d = cvx.d(normalPlanValue.length() / 2);
        String e = cvx.e(129);
        StringBuilder sb2 = new StringBuilder();
        sb2.append(e);
        sb2.append(d);
        sb2.append(normalPlanValue);
        deviceCommand.setServiceID(22);
        deviceCommand.setCommandID(2);
        deviceCommand.setDataLen(sb2.length() / 2);
        deviceCommand.setDataContent(cvx.a(sb2.toString()));
        return deviceCommand;
    }

    private static String setNormalPlanValue(RunPlanInfo runPlanInfo, List<String> list) {
        String d;
        String str;
        String d2;
        String str2 = null;
        if (!TextUtils.isEmpty(runPlanInfo.getRunPlanTotalSign())) {
            str = cvx.c(runPlanInfo.getRunPlanTotalSign());
            d = cvx.d(str.length() / 2);
        } else {
            d = cvx.d(0);
            str = null;
        }
        String e = cvx.e(2);
        if (!TextUtils.isEmpty(runPlanInfo.getRunPlanSign())) {
            str2 = runPlanInfo.getRunPlanSign();
            d2 = cvx.d(str2.length() / 2);
        } else {
            d2 = cvx.d(0);
        }
        String e2 = cvx.e(3);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
        }
        String str3 = !TextUtils.isEmpty(str) ? e + d + str : e + d;
        String str4 = !TextUtils.isEmpty(str2) ? str3 + e2 + d2 + str2 : str3 + e2 + d2;
        int runPlanStartDate = (int) (runPlanInfo.getRunPlanStartDate() / 1000);
        String str5 = cvx.e(runPlanStartDate >> 24) + cvx.e((runPlanStartDate >> 16) & 255) + cvx.e((runPlanStartDate >> 8) & 255) + cvx.e(runPlanStartDate & 255);
        return str4 + cvx.e(4) + cvx.d(str5.length() / 2) + str5 + sb.toString();
    }

    private static void setRunPlanStructList(List<RunPlanStruct> list, List<String> list2, int i, StringBuilder sb) {
        String d = cvx.d(sb.length() / 2);
        String e = cvx.e(OldToNewMotionPath.SPORT_TYPE_AEROBICS);
        String c = cvx.c(list.get(i).getRunPlanName());
        String d2 = cvx.d(c.length() / 2);
        String e2 = cvx.e(6);
        int runPlanDate = (int) (list.get(i).getRunPlanDate() / 1000);
        String str = cvx.e(runPlanDate >> 24) + cvx.e((runPlanDate >> 16) & 255) + cvx.e((runPlanDate >> 8) & 255) + cvx.e(runPlanDate & 255);
        String d3 = cvx.d(str.length() / 2);
        String e3 = cvx.e(7);
        String b = cvx.b(list.get(i).getRunPlanWorkoutId());
        String d4 = cvx.d(b.length() / 2);
        String e4 = cvx.e(18);
        String e5 = cvx.e(list.get(i).getRunPlanTrainEffect());
        String d5 = cvx.d(e5.length() / 2);
        String e6 = cvx.e(8);
        String e7 = cvx.e(list.get(i).getRunPlanRepeats());
        String d6 = cvx.d(e7.length() / 2);
        String e8 = cvx.e(9);
        String b2 = cvx.b(list.get(i).getRunPlanDistance());
        String d7 = cvx.d(b2.length() / 2);
        String str2 = e2 + d2 + c + e3 + d3 + str + e6 + d5 + e5 + e8 + d6 + e7 + cvx.e(10) + d7 + b2 + e + d + ((Object) sb) + e4 + d4 + b;
        String d8 = cvx.d(str2.length() / 2);
        list2.add(cvx.e(OldToNewMotionPath.SPORT_TYPE_VOLLEYBALL) + d8 + str2);
    }

    private static void setTrainingStructHexList(List<RunPlanStruct> list, int i, List<String> list2) {
        List<TrainingStruct> trainingStructList = list.get(i).getTrainingStructList();
        if (trainingStructList != null) {
            int i2 = 0;
            while (i2 < trainingStructList.size()) {
                String e = cvx.e(trainingStructList.get(i2).getTrainingType());
                String d = cvx.d(e.length() / 2);
                String e2 = cvx.e(13);
                String str = cvx.e(trainingStructList.get(i2).getTrainingSpeedLimitHigh()) + cvx.e(trainingStructList.get(i2).getTrainingSpeedLimitLow());
                String d2 = cvx.d(str.length() / 2);
                String e3 = cvx.e(14);
                String str2 = cvx.e(trainingStructList.get(i2).getTrainingHrLimitHigh()) + cvx.e(trainingStructList.get(i2).getTrainingHrLimitLow());
                String d3 = cvx.d(str2.length() / 2);
                String e4 = cvx.e(15);
                String str3 = cvx.e(trainingStructList.get(i2).getTrainingIntensityLimitHigh()) + cvx.e(trainingStructList.get(i2).getTrainingIntensityLimitLow());
                String d4 = cvx.d(str3.length() / 2);
                String e5 = cvx.e(16);
                String a2 = cvx.a(trainingStructList.get(i2).getTrainingDuration());
                String d5 = cvx.d(a2.length() / 2);
                List<TrainingStruct> list3 = trainingStructList;
                addTrainingStructHexList(e2 + d + e + e3 + d2 + str + e4 + d3 + str2 + e5 + d4 + str3 + cvx.e(17) + d5 + a2, list2);
                i2++;
                trainingStructList = list3;
            }
        }
    }

    private static void addTrainingStructHexList(String str, List<String> list) {
        String d = cvx.d(str.length() / 2);
        list.add(cvx.e(140) + d + str);
    }

    public static DeviceCommand getRunPlanRecordInfoCommand(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(22);
        deviceCommand.setCommandID(5);
        String a2 = cvx.a(jSONObject.getInt("id"));
        String d = cvx.d(a2.length() / 2);
        String str = cvx.e(2) + d + a2;
        String d2 = cvx.d(str.length() / 2);
        String e = cvx.e(129);
        StringBuilder sb = new StringBuilder();
        sb.append(e);
        sb.append(d2);
        sb.append(str);
        deviceCommand.setDataLen(sb.length() / 2);
        deviceCommand.setDataContent(cvx.a(sb.toString()));
        return deviceCommand;
    }

    public static DeviceCommand getRunPlanRecordCommand(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        LogUtil.a(TAG, "getRunPlanRecordCommand startTime:", Long.valueOf(jSONObject.getLong("startTime")), " endTime:", Long.valueOf(jSONObject.getLong("endTime")));
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(22);
        deviceCommand.setCommandID(4);
        String d = cvx.d(12);
        String e = cvx.e(129);
        int i = (int) (jSONObject.getLong("startTime") / 1000);
        String str = cvx.e(i >> 24) + cvx.e((i >> 16) & 255) + cvx.e((i >> 8) & 255) + cvx.e(i & 255);
        String d2 = cvx.d(str.length() / 2);
        String e2 = cvx.e(3);
        int i2 = (int) (jSONObject.getLong("endTime") / 1000);
        String str2 = cvx.e(i2 >> 24) + cvx.e((i2 >> 16) & 255) + cvx.e((i2 >> 8) & 255) + cvx.e(i2 & 255);
        String d3 = cvx.d(str2.length() / 2);
        String e3 = cvx.e(4);
        StringBuilder sb = new StringBuilder();
        sb.append(e);
        sb.append(d);
        sb.append(e2);
        sb.append(d2);
        sb.append(str);
        sb.append(e3);
        sb.append(d3);
        sb.append(str2);
        deviceCommand.setDataLen(sb.length() / 2);
        deviceCommand.setDataContent(cvx.a(sb.toString()));
        return deviceCommand;
    }

    public static DeviceCommand getRunPlanReminderSwitchCommand(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        DeviceCommand deviceCommand = new DeviceCommand();
        RunPlanReminder runPlanReminder = (RunPlanReminder) new Gson().fromJson(jSONObject.getString("runPlanReminder"), RunPlanReminder.class);
        deviceCommand.setServiceID(22);
        deviceCommand.setCommandID(3);
        String e = cvx.e(runPlanReminder.getRunPlanReminderSwitch());
        String d = cvx.d(e.length() / 2);
        String e2 = cvx.e(1);
        String str = cvx.e(runPlanReminder.getRunPlanReminderTimeHour()) + cvx.e(runPlanReminder.getRunPlanReminderTimeMinute());
        String d2 = cvx.d(str.length() / 2);
        String e3 = cvx.e(2);
        StringBuilder sb = new StringBuilder();
        sb.append(e2);
        sb.append(d);
        sb.append(e);
        sb.append(e3);
        sb.append(d2);
        sb.append(str);
        deviceCommand.setDataLen(sb.length() / 2);
        deviceCommand.setDataContent(cvx.a(sb.toString()));
        return deviceCommand;
    }

    public static DeviceCommand getWorkoutRecordCommand(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            LogUtil.h(TAG, "getWorkoutRecordCommand, input parameters is illegal");
            return null;
        }
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(23);
        deviceCommand.setCommandID(7);
        String d = cvx.d(12);
        String e = cvx.e(129);
        int i = (int) (jSONObject.getLong("startTime") / 1000);
        String str = cvx.e(i >> 24) + cvx.e((i >> 16) & 255) + cvx.e((i >> 8) & 255) + cvx.e(i & 255);
        String d2 = cvx.d(str.length() / 2);
        String e2 = cvx.e(3);
        int i2 = (int) (jSONObject.getLong("endTime") / 1000);
        String str2 = cvx.e(i2 >> 24) + cvx.e((i2 >> 16) & 255) + cvx.e((i2 >> 8) & 255) + cvx.e(i2 & 255);
        String d3 = cvx.d(str2.length() / 2);
        String e3 = cvx.e(4);
        StringBuilder sb = new StringBuilder(16);
        sb.append(e);
        sb.append(d);
        sb.append(e2);
        sb.append(d2);
        sb.append(str);
        sb.append(e3);
        sb.append(d3);
        sb.append(str2);
        deviceCommand.setDataLen(sb.length() / 2);
        deviceCommand.setDataContent(cvx.a(sb.toString()));
        return deviceCommand;
    }

    public static DeviceCommand notifyOperatorStatus(JSONObject jSONObject, DeviceInfo deviceInfo) throws JSONException {
        String d;
        if (jSONObject == null) {
            LogUtil.h(TAG, "getOperatorStatus, input parameters is illegal");
            return null;
        }
        int i = jSONObject.has("version") ? 48 : 42;
        if (jSONObject.has("run_course_version")) {
            i += 6;
        }
        sDeviceInfo = deviceInfo;
        DeviceCapability singleDeviceCapability = HwExerciseDeviceUtil.getSingleDeviceCapability(deviceInfo);
        if (singleDeviceCapability != null && singleDeviceCapability.isSupportWorkoutCapabilicy()) {
            i += 6;
        }
        String e = cvx.e(135);
        String d2 = cvx.d(18);
        int length = i + e.length() + 36 + d2.length();
        if (jSONObject.getInt(SPORT_TYPE) == 2) {
            d = cvx.d((length + 12) / 2);
        } else {
            d = cvx.d(length / 2);
        }
        String e2 = cvx.e(129);
        StringBuffer stringBuffer = new StringBuffer(16);
        stringBuffer.append(e2);
        stringBuffer.append(d);
        getOperatorStringTlv(stringBuffer, jSONObject, 36, d2, e);
        return getNotifyOperatorStatusCommand(stringBuffer, deviceInfo);
    }

    private static DeviceCommand getNotifyOperatorStatusCommand(StringBuffer stringBuffer, DeviceInfo deviceInfo) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(23);
        deviceCommand.setCommandID(1);
        deviceCommand.setDataLen(stringBuffer.length() / 2);
        deviceCommand.setDataContent(cvx.a(stringBuffer.toString()));
        deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        return deviceCommand;
    }

    private static void getOperatorStringTlv(StringBuffer stringBuffer, JSONObject jSONObject, int i, String str, String str2) throws JSONException {
        HwWorkoutServiceUtils.getOperatorTypeStringTlv(stringBuffer, jSONObject);
        HwWorkoutServiceUtils.getOperatorSportTypeStringTlv(stringBuffer, jSONObject);
        HwWorkoutServiceUtils.getOperatorRunPlanDateStringTlv(stringBuffer, jSONObject);
        HwWorkoutServiceUtils.getOperatorWorkoutTypeStringTlv(stringBuffer, jSONObject);
        HwWorkoutServiceUtils.getOperatorSonStructStringTlv(stringBuffer, jSONObject, i, str, str2);
        HwWorkoutServiceUtils.getOperatorVersionStringTlv(stringBuffer, jSONObject);
        HwWorkoutServiceUtils.getOperatorStartTimeStringTlv(stringBuffer, jSONObject);
        HwWorkoutServiceUtils.getOperatorRunCourseVersionStringTlv(stringBuffer, jSONObject);
        DeviceCapability singleDeviceCapability = HwExerciseDeviceUtil.getSingleDeviceCapability(sDeviceInfo);
        if (singleDeviceCapability == null || !singleDeviceCapability.isSupportWorkoutCapabilicy()) {
            return;
        }
        HwWorkoutServiceUtils.getOperatorForbidPauseStringTlv(stringBuffer, jSONObject);
    }

    public static DeviceCommand getOperatorStatus(DeviceInfo deviceInfo) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(23);
        deviceCommand.setCommandID(3);
        String d = cvx.d(0);
        String e = cvx.e(129);
        StringBuilder sb = new StringBuilder(16);
        sb.append(e);
        sb.append(d);
        deviceCommand.setDataLen(sb.length() / 2);
        deviceCommand.setDataContent(cvx.a(sb.toString()));
        deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        return deviceCommand;
    }

    public static DeviceCommand getWorkoutRealtimeInfo(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            LogUtil.h(TAG, "getWorkoutRealTimeInfo, input parameters is illegal");
            return null;
        }
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(23);
        deviceCommand.setCommandID(4);
        String d = cvx.d(3);
        String e = cvx.e(1);
        String e2 = cvx.e(jSONObject.getInt(SPORT_TYPE));
        String d2 = cvx.d(e2.length() / 2);
        String e3 = cvx.e(2);
        StringBuilder sb = new StringBuilder(16);
        sb.append(e);
        sb.append(d);
        sb.append(e3);
        sb.append(d2);
        sb.append(e2);
        deviceCommand.setDataLen(sb.length() / 2);
        deviceCommand.setDataContent(cvx.a(sb.toString()));
        return deviceCommand;
    }

    public static DeviceCommand getWorkoutRecordStatistic(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            LogUtil.h(TAG, "getWorkoutRecordStatistic, input parameters is illegal");
            return null;
        }
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(23);
        deviceCommand.setCommandID(8);
        String d = cvx.d(4);
        String e = cvx.e(129);
        LogUtil.a(TAG, "get getWorkoutRecordStatistic id ");
        String a2 = cvx.a(jSONObject.getInt("id"));
        String d2 = cvx.d(a2.length() / 2);
        String e2 = cvx.e(2);
        StringBuilder sb = new StringBuilder(16);
        sb.append(e);
        sb.append(d);
        sb.append(e2);
        sb.append(d2);
        sb.append(a2);
        deviceCommand.setDataLen(sb.length() / 2);
        deviceCommand.setDataContent(cvx.a(sb.toString()));
        return deviceCommand;
    }

    public static DeviceCommand getWorkoutData(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            LogUtil.h(TAG, "getWorkoutData, input parameters is illegal");
            return null;
        }
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(23);
        deviceCommand.setCommandID(10);
        LogUtil.a(TAG, "getWorkoutData id, the parameters are ", jSONObject.toString());
        String a2 = cvx.a(jSONObject.getInt(HwExerciseConstants.JSON_NAME_WORKOUT_RECORD_ID));
        String d = cvx.d(a2.length() / 2);
        String e = cvx.e(2);
        String a3 = cvx.a(jSONObject.getInt(HwExerciseConstants.WORKOUT_DATA_INDEX));
        String d2 = cvx.d(a2.length() / 2);
        String e2 = cvx.e(3);
        StringBuilder sb = new StringBuilder(16);
        sb.append(e);
        sb.append(d);
        sb.append(a2);
        sb.append(e2);
        sb.append(d2);
        sb.append(a3);
        if (HwWorkoutServiceManager.getInstance().isSupportNewStep()) {
            handleNewStepCommand(sb);
        }
        sb.append(cvx.e(7));
        sb.append(cvx.e(0));
        sb.insert(0, cvx.d(sb.length() / 2)).insert(0, cvx.e(129));
        deviceCommand.setDataLen(sb.length() / 2);
        deviceCommand.setDataContent(cvx.a(sb.toString()));
        return deviceCommand;
    }

    private static void handleNewStepCommand(StringBuilder sb) {
        String e = cvx.e(6);
        String d = cvx.d(1);
        String e2 = cvx.e(1);
        sb.append(e);
        sb.append(d);
        sb.append(e2);
    }

    public static DeviceCommand getWorkoutCapability() {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(23);
        deviceCommand.setCommandID(21);
        String d = cvx.d(0);
        String e = cvx.e(1);
        StringBuilder sb = new StringBuilder(16);
        sb.append(e);
        sb.append(d);
        deviceCommand.setDataLen(sb.length() / 2);
        deviceCommand.setDataContent(cvx.a(sb.toString()));
        return deviceCommand;
    }

    public static DeviceCommand getSectionListDeviceCommand(JSONObject jSONObject) throws JSONException {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(23);
        deviceCommand.setCommandID(14);
        String e = cvx.e(129);
        String a2 = cvx.a(jSONObject.getInt("id"));
        String d = cvx.d(a2.length() / 2);
        String e2 = cvx.e(2);
        String a3 = cvx.a(jSONObject.getInt("sectionIndex"));
        String d2 = cvx.d(a3.length() / 2);
        String e3 = cvx.e(8);
        String e4 = cvx.e((((((a2.length() + d.length()) + e2.length()) + a3.length()) + d2.length()) + e3.length()) / 2);
        StringBuilder sb = new StringBuilder(16);
        sb.append(e);
        sb.append(e4);
        sb.append(e2);
        sb.append(d);
        sb.append(a2);
        sb.append(e3);
        sb.append(d2);
        sb.append(a3);
        deviceCommand.setDataLen(sb.length() / 2);
        deviceCommand.setDataContent(cvx.a(sb.toString()));
        return deviceCommand;
    }

    public static DeviceCommand getNotificationResponseCommand(JSONObject jSONObject) throws JSONException {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(23);
        deviceCommand.setCommandID(2);
        String b = cvx.b(jSONObject.getInt("notification_status_response"));
        String e = cvx.e(b.length() / 2);
        String e2 = cvx.e(127);
        StringBuilder sb = new StringBuilder(16);
        sb.append(e2);
        sb.append(e);
        sb.append(b);
        deviceCommand.setDataLen(sb.length() / 2);
        deviceCommand.setDataContent(cvx.a(sb.toString()));
        return deviceCommand;
    }

    public static DeviceCommand getWorkoutRecordPaceMapCommand(PaceIndexStruct paceIndexStruct) {
        String str;
        String str2;
        String str3;
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(23);
        deviceCommand.setCommandID(12);
        String e = cvx.e(129);
        String d = cvx.d(4);
        String a2 = cvx.a(paceIndexStruct.getRecordId());
        if (paceIndexStruct.getPaceIndex() >= 0) {
            String d2 = cvx.d(8);
            String a3 = cvx.a(paceIndexStruct.getPaceIndex());
            str3 = cvx.d(a3.length() / 2);
            str = cvx.e(8);
            d = d2;
            str2 = a3;
        } else {
            str = "";
            str2 = "";
            str3 = str2;
        }
        StringBuilder sb = new StringBuilder(16);
        sb.append(e);
        String d3 = cvx.d(a2.length() / 2);
        String e2 = cvx.e(2);
        sb.append(d);
        sb.append(e2);
        sb.append(d3);
        sb.append(a2);
        if (paceIndexStruct.getPaceIndex() >= 0) {
            sb.append(str);
            sb.append(str3);
            sb.append(str2);
        }
        deviceCommand.setDataLen(sb.length() / 2);
        deviceCommand.setDataContent(cvx.a(sb.toString()));
        return deviceCommand;
    }

    public static DeviceCommand getWorkoutRecordSpeechPlayReportCommand() {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(23);
        deviceCommand.setCommandID(13);
        String e = cvx.e(1);
        String d = cvx.d(1);
        String e2 = cvx.e(2);
        StringBuilder sb = new StringBuilder(16);
        sb.append(e2);
        sb.append(d);
        sb.append(e);
        deviceCommand.setDataLen(sb.length() / 2);
        deviceCommand.setDataContent(cvx.a(sb.toString()));
        return deviceCommand;
    }

    public static DeviceCommand getCommonSectionListCommand(CommonSectionList commonSectionList) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(23);
        deviceCommand.setCommandID(22);
        String a2 = cvx.a(commonSectionList.getWorkoutRecordId());
        String d = cvx.d(a2.length() / 2);
        String e = cvx.e(1);
        String a3 = cvx.a(commonSectionList.getSectionIndex());
        String d2 = cvx.d(a3.length() / 2);
        String e2 = cvx.e(2);
        StringBuilder sb = new StringBuilder(16);
        sb.append(e);
        sb.append(d);
        sb.append(a2);
        sb.append(e2);
        sb.append(d2);
        sb.append(a3);
        deviceCommand.setDataLen(sb.length() / 2);
        deviceCommand.setDataContent(cvx.a(sb.toString()));
        return deviceCommand;
    }
}
