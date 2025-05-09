package com.huawei.hwdevice.phoneprocess.mgr.exercise.multisync;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.datatype.DeviceCommand;
import com.huawei.health.device.model.OperatorStatus;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.dmsmanager.HwDeviceMgrInterface;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.DataHeader;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.PaceIndexStruct;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.SportReminder;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.WorkRecordIndexPaceMapList;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.WorkoutDataStruct;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.WorkoutRealTimeInfo;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.WorkoutRecord;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.WorkoutRecordJumpData;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.WorkoutRecordPaceMap;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.WorkoutRecordSpeechPlay;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.WorkoutRecordStatistic;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.WorkoutRecordStruct;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.utils.HwExerciseCommandUtil;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.blt;
import defpackage.cun;
import defpackage.cvx;
import defpackage.cwd;
import defpackage.cwe;
import defpackage.cwg;
import defpackage.cwl;
import defpackage.jds;
import defpackage.jsz;
import defpackage.kkm;
import health.compact.a.CommonUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class HwWorkoutServiceAw70Manager {
    private static final int HALF_LENGTH = 2;
    private static final long MILLISECONDS_TO_SECOND = 1000;
    private static final String TAG = "HwWorkoutServiceAw70Manager";
    private static HwWorkoutServiceAw70Manager sInstance;
    private Context mContext;
    private HwDeviceMgrInterface mHwDeviceManager;
    private static final Object INSTANCE_LOCK = new Object();
    private static final Object LOCK_OBJECT = new Object();
    private static List<IBaseResponseCallback> sSetOperatorCallbackList = new ArrayList(16);
    private static List<IBaseResponseCallback> sGetOperatorCallbackList = new ArrayList(16);
    private static List<IBaseResponseCallback> sGetWorkoutRealTimeInfoCallbackList = new ArrayList(16);
    private static List<IBaseResponseCallback> sGetWorkoutRecordCallbackList = new ArrayList(16);
    private static List<IBaseResponseCallback> sGetWorkoutRecordStatisticCallbackList = new ArrayList(16);
    private static List<IBaseResponseCallback> sGetWorkoutDataCallbackList = new ArrayList(16);
    private static List<IBaseResponseCallback> sGetNotificationStatusCallbackList = new ArrayList(16);
    private static List<IBaseResponseCallback> sGetNotificationWorkoutRealTimeInfoCallbackList = new ArrayList(16);
    private static List<IBaseResponseCallback> sGetNotificationSportReminderCallbackList = new ArrayList(16);
    private static List<IBaseResponseCallback> sGetNotificationGetWorkoutRecordStatisticCallbackList = new ArrayList(16);
    private static List<IBaseResponseCallback> sGetWorkoutOperatorRealTimeDataCallbackList = new ArrayList(16);
    private static List<IBaseResponseCallback> sGetWorkoutRecordPaceMapListCallbackList = new ArrayList(16);
    private static List<IBaseResponseCallback> sGetNotificationWorkoutRecordSpeechPlayCallbackList = new ArrayList(16);
    private static List<IBaseResponseCallback> sWorkoutRecordSliceCallbackList = new ArrayList(16);
    private int mAw70LastRecordId = -1;
    private List<IBaseResponseCallback> mWorkoutCapabilityCallbackList = new Vector(16);

    private HwWorkoutServiceAw70Manager(Context context) {
        this.mContext = context;
        this.mHwDeviceManager = jsz.b(context);
    }

    public static HwWorkoutServiceAw70Manager getInstance() {
        HwWorkoutServiceAw70Manager hwWorkoutServiceAw70Manager;
        synchronized (INSTANCE_LOCK) {
            if (sInstance == null) {
                sInstance = new HwWorkoutServiceAw70Manager(BaseApplication.getContext());
            }
            hwWorkoutServiceAw70Manager = sInstance;
        }
        return hwWorkoutServiceAw70Manager;
    }

    public void setAw70LastRecordId(int i) {
        this.mAw70LastRecordId = i;
    }

    public boolean isAw70LastData(int i) {
        LogUtil.a(TAG, "mAw70LastRecordId is:", Integer.valueOf(this.mAw70LastRecordId), ",recordId is:", Integer.valueOf(i));
        return i == this.mAw70LastRecordId;
    }

    public void getWorkoutRealTimeInfo(JSONObject jSONObject, IBaseResponseCallback iBaseResponseCallback) throws JSONException {
        if (jSONObject == null || iBaseResponseCallback == null) {
            return;
        }
        synchronized (LOCK_OBJECT) {
            DeviceCommand deviceCommand = new DeviceCommand();
            deviceCommand.setServiceID(23);
            deviceCommand.setCommandID(4);
            String aw70Identify = getAw70Identify();
            if (!TextUtils.isEmpty(aw70Identify)) {
                deviceCommand.setmIdentify(aw70Identify);
            }
            StringBuilder realTimeInfoStruct = getRealTimeInfoStruct(jSONObject);
            deviceCommand.setDataLen(realTimeInfoStruct.length() / 2);
            deviceCommand.setDataContent(cvx.a(realTimeInfoStruct.toString()));
            synchronized (getGetWorkoutRealTimeInfoCallbackList()) {
                sGetWorkoutRealTimeInfoCallbackList.add(iBaseResponseCallback);
            }
            this.mHwDeviceManager.sendDeviceData(deviceCommand);
        }
    }

    private StringBuilder getRealTimeInfoStruct(JSONObject jSONObject) throws JSONException {
        String d = cvx.d(3);
        String e = cvx.e(1);
        String e2 = cvx.e(jSONObject.getInt("sport_type"));
        String d2 = cvx.d(e2.length() / 2);
        String e3 = cvx.e(2);
        StringBuilder sb = new StringBuilder(16);
        sb.append(e);
        sb.append(d);
        sb.append(e3);
        sb.append(d2);
        sb.append(e2);
        return sb;
    }

    public void getWorkoutRecord(JSONObject jSONObject, IBaseResponseCallback iBaseResponseCallback) throws JSONException {
        if (jSONObject == null || iBaseResponseCallback == null) {
            return;
        }
        synchronized (LOCK_OBJECT) {
            DeviceCommand deviceCommand = new DeviceCommand();
            deviceCommand.setServiceID(23);
            deviceCommand.setCommandID(7);
            String aw70Identify = getAw70Identify();
            if (!TextUtils.isEmpty(aw70Identify)) {
                deviceCommand.setmIdentify(aw70Identify);
            }
            StringBuilder workoutRecordListHex = getWorkoutRecordListHex(jSONObject);
            deviceCommand.setDataLen(workoutRecordListHex.length() / 2);
            deviceCommand.setDataContent(cvx.a(workoutRecordListHex.toString()));
            synchronized (getGetWorkoutRecordCallbackList()) {
                sGetWorkoutRecordCallbackList.add(iBaseResponseCallback);
            }
            this.mHwDeviceManager.sendDeviceData(deviceCommand);
        }
    }

    private StringBuilder getWorkoutRecordListHex(JSONObject jSONObject) throws JSONException {
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
        return sb;
    }

    public void getWorkoutRecordStatistic(JSONObject jSONObject, IBaseResponseCallback iBaseResponseCallback) throws JSONException {
        if (jSONObject == null || iBaseResponseCallback == null) {
            return;
        }
        synchronized (LOCK_OBJECT) {
            DeviceCommand deviceCommand = new DeviceCommand();
            deviceCommand.setServiceID(23);
            deviceCommand.setCommandID(8);
            String aw70Identify = getAw70Identify();
            if (!TextUtils.isEmpty(aw70Identify)) {
                deviceCommand.setmIdentify(aw70Identify);
            }
            String d = cvx.d(4);
            String e = cvx.e(129);
            LogUtil.a(TAG, "get getWorkoutRecordStatistic id");
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
            synchronized (getGetWorkoutRecordStatisticCallbackList()) {
                sGetWorkoutRecordStatisticCallbackList.add(iBaseResponseCallback);
            }
            this.mHwDeviceManager.sendDeviceData(deviceCommand);
        }
    }

    private String getAw70Identify() {
        DeviceInfo deviceInfo;
        synchronized (LOCK_OBJECT) {
            List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_AW70_DEVICES, null, TAG);
            deviceInfo = deviceList.size() > 0 ? deviceList.get(0) : null;
        }
        if (deviceInfo != null) {
            return deviceInfo.getDeviceIdentify();
        }
        return null;
    }

    public void getWorkoutData(JSONObject jSONObject, IBaseResponseCallback iBaseResponseCallback, int i) {
        if (jSONObject == null || iBaseResponseCallback == null) {
            return;
        }
        synchronized (LOCK_OBJECT) {
            DeviceCommand deviceCommand = new DeviceCommand();
            deviceCommand.setServiceID(23);
            deviceCommand.setCommandID(10);
            String aw70Identify = getAw70Identify();
            if (!TextUtils.isEmpty(aw70Identify)) {
                deviceCommand.setmIdentify(aw70Identify);
            }
            String workoutRecordStatisticStructHex = HwExerciseAdviceAw70ManagerUtil.getWorkoutRecordStatisticStructHex(jSONObject, i);
            deviceCommand.setDataLen(workoutRecordStatisticStructHex.length() / 2);
            deviceCommand.setDataContent(cvx.a(workoutRecordStatisticStructHex));
            synchronized (getGetWorkoutDataCallbackList()) {
                sGetWorkoutDataCallbackList.add(iBaseResponseCallback);
            }
            this.mHwDeviceManager.sendDeviceData(deviceCommand);
        }
    }

    public void workoutOperateRealtimeData(JSONObject jSONObject, IBaseResponseCallback iBaseResponseCallback) throws JSONException {
        LogUtil.a(TAG, "setRealTimeData enter");
        if (jSONObject == null || iBaseResponseCallback == null) {
            return;
        }
        synchronized (LOCK_OBJECT) {
            DeviceCommand deviceCommand = new DeviceCommand();
            deviceCommand.setServiceID(23);
            deviceCommand.setCommandID(11);
            String aw70Identify = getAw70Identify();
            if (!TextUtils.isEmpty(aw70Identify)) {
                deviceCommand.setmIdentify(aw70Identify);
            }
            StringBuilder sb = new StringBuilder(16);
            sb.append(cvx.e(129));
            setValues(jSONObject, sb);
            sb.append(cvx.e(5));
            String a2 = cvx.a(jSONObject.getInt("speed"));
            sb.append(cvx.d(a2.length() / 2));
            sb.append(a2);
            setPaceValues(jSONObject, sb);
            deviceCommand.setDataLen(sb.length() / 2);
            deviceCommand.setDataContent(cvx.a(sb.toString()));
            synchronized (getGetWorkoutOperatorRealTimeDataCallbackList()) {
                sGetWorkoutOperatorRealTimeDataCallbackList.add(iBaseResponseCallback);
            }
            this.mHwDeviceManager.sendDeviceData(deviceCommand);
        }
    }

    private void setValues(JSONObject jSONObject, StringBuilder sb) throws JSONException {
        String d;
        if (jSONObject.has("pace")) {
            d = cvx.d(26);
        } else {
            d = cvx.d(22);
        }
        String b = cvx.b(jSONObject.getInt("exercise_duration"));
        String d2 = cvx.d(b.length() / 2);
        String e = cvx.e(2);
        String b2 = cvx.b(jSONObject.getInt("distance") * 10);
        String d3 = cvx.d(b2.length() / 2);
        String e2 = cvx.e(3);
        String b3 = cvx.b(jSONObject.getInt("calorie"));
        String d4 = cvx.d(b3.length() / 2);
        String e3 = cvx.e(4);
        sb.append(d);
        sb.append(e);
        sb.append(d2);
        sb.append(b);
        sb.append(e2);
        sb.append(d3);
        sb.append(b2);
        sb.append(e3);
        sb.append(d4);
        sb.append(b3);
    }

    private void setPaceValues(JSONObject jSONObject, StringBuilder sb) throws JSONException {
        if (jSONObject.has("pace")) {
            String a2 = cvx.a(jSONObject.getInt("pace"));
            String d = cvx.d(a2.length() / 2);
            sb.append(cvx.e(6));
            sb.append(d);
            sb.append(a2);
        }
    }

    public void setNotificationStatusResponse(JSONObject jSONObject, IBaseResponseCallback iBaseResponseCallback) {
        if (jSONObject == null || iBaseResponseCallback == null) {
            return;
        }
        LogUtil.c(TAG, "response of NotificationStatus info");
        synchronized (LOCK_OBJECT) {
            DeviceCommand deviceCommand = new DeviceCommand();
            deviceCommand.setServiceID(23);
            deviceCommand.setCommandID(2);
            String aw70Identify = getAw70Identify();
            if (!TextUtils.isEmpty(aw70Identify)) {
                deviceCommand.setmIdentify(aw70Identify);
            }
            try {
                String b = cvx.b(jSONObject.getInt("notification_status_response"));
                String e = cvx.e(b.length() / 2);
                String e2 = cvx.e(127);
                StringBuilder sb = new StringBuilder(16);
                sb.append(e2);
                sb.append(e);
                sb.append(b);
                deviceCommand.setDataLen(sb.length() / 2);
                deviceCommand.setDataContent(cvx.a(sb.toString()));
                this.mHwDeviceManager.sendDeviceData(deviceCommand);
                iBaseResponseCallback.d(100000, kkm.d("success", "setNotificationStatusResponse"));
            } catch (JSONException unused) {
                LogUtil.b(TAG, "json exception");
            }
        }
    }

    public void getWorkoutRecordPaceMap(PaceIndexStruct paceIndexStruct, IBaseResponseCallback iBaseResponseCallback) {
        if (paceIndexStruct == null || iBaseResponseCallback == null) {
            return;
        }
        synchronized (LOCK_OBJECT) {
            DeviceCommand deviceCommand = new DeviceCommand();
            deviceCommand.setServiceID(23);
            deviceCommand.setCommandID(12);
            String aw70Identify = getAw70Identify();
            if (!TextUtils.isEmpty(aw70Identify)) {
                deviceCommand.setmIdentify(aw70Identify);
            }
            StringBuilder sb = new StringBuilder(16);
            setWorkoutRecordValues(paceIndexStruct, sb);
            deviceCommand.setDataLen(sb.length() / 2);
            deviceCommand.setDataContent(cvx.a(sb.toString()));
            synchronized (getGetWorkoutRecordPaceMapListCallbackList()) {
                sGetWorkoutRecordPaceMapListCallbackList.add(iBaseResponseCallback);
            }
            this.mHwDeviceManager.sendDeviceData(deviceCommand);
        }
    }

    private void setWorkoutRecordValues(PaceIndexStruct paceIndexStruct, StringBuilder sb) {
        String str;
        String str2;
        String str3;
        String d = cvx.d(4);
        if (paceIndexStruct.getPaceIndex() >= 0) {
            String d2 = cvx.d(8);
            String a2 = cvx.a(paceIndexStruct.getPaceIndex());
            str3 = cvx.d(a2.length() / 2);
            str = cvx.e(8);
            d = d2;
            str2 = a2;
        } else {
            str = "";
            str2 = "";
            str3 = str2;
        }
        sb.append(cvx.e(129));
        sb.append(d);
        sb.append(cvx.e(2));
        String a3 = cvx.a(paceIndexStruct.getRecordId());
        sb.append(cvx.d(a3.length() / 2));
        sb.append(a3);
        if (paceIndexStruct.getPaceIndex() >= 0) {
            sb.append(str);
            sb.append(str3);
            sb.append(str2);
        }
    }

    public void getWorkoutRecordSliceData(int i, int i2, IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            return;
        }
        synchronized (LOCK_OBJECT) {
            DeviceCommand deviceCommand = new DeviceCommand();
            deviceCommand.setServiceID(23);
            deviceCommand.setCommandID(19);
            String aw70Identify = getAw70Identify();
            if (!TextUtils.isEmpty(aw70Identify)) {
                deviceCommand.setmIdentify(aw70Identify);
            }
            String e = cvx.e(1);
            String a2 = cvx.a(i);
            String d = cvx.d(a2.length() / 2);
            String e2 = cvx.e(2);
            String a3 = cvx.a(i2);
            String d2 = cvx.d(a3.length() / 2);
            StringBuilder sb = new StringBuilder(16);
            sb.append(e);
            sb.append(d);
            sb.append(a2);
            sb.append(e2);
            sb.append(d2);
            sb.append(a3);
            deviceCommand.setDataLen(sb.length() / 2);
            deviceCommand.setDataContent(cvx.a(sb.toString()));
            synchronized (getWorkoutRecordSliceCallbackList()) {
                sWorkoutRecordSliceCallbackList.add(iBaseResponseCallback);
            }
            this.mHwDeviceManager.sendDeviceData(deviceCommand);
        }
    }

    public void notificationWorkoutRecordSpeechPlayReportStatus(JSONObject jSONObject, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.c(TAG, "ENTER notificationWorkoutRecordSpeechPlayReportStatus...");
        synchronized (LOCK_OBJECT) {
            if (jSONObject != null) {
                if (jSONObject.optInt("result") == 0) {
                    DeviceCommand deviceCommand = new DeviceCommand();
                    deviceCommand.setServiceID(23);
                    deviceCommand.setCommandID(13);
                    String aw70Identify = getAw70Identify();
                    if (!TextUtils.isEmpty(aw70Identify)) {
                        deviceCommand.setmIdentify(aw70Identify);
                    }
                    String e = cvx.e(1);
                    String d = cvx.d(1);
                    String e2 = cvx.e(2);
                    StringBuilder sb = new StringBuilder(16);
                    sb.append(e2);
                    sb.append(d);
                    sb.append(e);
                    deviceCommand.setDataLen(sb.length() / 2);
                    deviceCommand.setDataContent(cvx.a(sb.toString()));
                    this.mHwDeviceManager.sendDeviceData(deviceCommand);
                }
            }
        }
    }

    public void getResult(byte[] bArr) {
        blt.d(TAG, bArr, "getResult(): ");
        if (bArr == null) {
            return;
        }
        String d = cvx.d(bArr);
        if (d.length() > 4) {
            try {
                cwe a2 = new cwl().a(d.substring(4, d.length()));
                List<cwd> e = a2.e();
                List<cwe> a3 = a2.a();
                byte b = bArr[1];
                if (b == 1) {
                    parseSetOperator(e);
                } else if (b == 2) {
                    parseNotificationStatus(a3);
                } else if (b == 3) {
                    parseGetOperator(e, a3);
                } else if (b == 4) {
                    parseGetWorkoutRealTimeInfo(e, a3);
                } else if (b == 5) {
                    parseNotificationWorkoutRealTime(a3);
                } else {
                    parseResult(b, e, a3);
                }
                return;
            } catch (cwg e2) {
                LogUtil.b(TAG, "error code TlvException ", e2.getMessage());
                return;
            } catch (IndexOutOfBoundsException e3) {
                LogUtil.b(TAG, "error code IndexOutOfBoundsException ", e3.getMessage());
                return;
            } catch (Exception unused) {
                LogUtil.b(TAG, "error code Exception");
                return;
            }
        }
        LogUtil.h(TAG, "error code data length less 4");
    }

    public void getWorkoutCapability(IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            return;
        }
        this.mWorkoutCapabilityCallbackList.add(iBaseResponseCallback);
        String aw70Identify = getAw70Identify();
        DeviceCommand workoutCapability = HwExerciseCommandUtil.getWorkoutCapability();
        if (!TextUtils.isEmpty(aw70Identify)) {
            workoutCapability.setmIdentify(aw70Identify);
        }
        synchronized (LOCK_OBJECT) {
            this.mHwDeviceManager.sendDeviceData(workoutCapability);
        }
    }

    private void parseSetOperator(List<cwd> list) {
        int w = CommonUtil.w(list.get(0).c());
        synchronized (getSetOperatorCallbackList()) {
            if (sSetOperatorCallbackList.size() != 0) {
                sSetOperatorCallbackList.get(0).d(w, kkm.d(Integer.valueOf(w), "setOperator"));
                sSetOperatorCallbackList.remove(0);
            }
        }
    }

    private void parseNotificationStatus(List<cwe> list) {
        OperatorStatus operatorStatus = new OperatorStatus();
        Iterator<cwe> it = list.iterator();
        while (it.hasNext()) {
            for (cwd cwdVar : it.next().e()) {
                int w = CommonUtil.w(cwdVar.e());
                if (w == 2) {
                    operatorStatus.setOperatorType(CommonUtil.w(cwdVar.c()));
                } else if (w == 3) {
                    operatorStatus.setSportType(CommonUtil.w(cwdVar.c()));
                } else if (w == 4) {
                    operatorStatus.setRunPlanDate(CommonUtil.y(cwdVar.c()) * 1000);
                } else if (w == 5) {
                    operatorStatus.setWorkoutType(CommonUtil.w(cwdVar.c()));
                } else if (w == 6) {
                    operatorStatus.setOperationTime(CommonUtil.y(cwdVar.c()));
                } else {
                    LogUtil.h(TAG, "parseNotificationStatus default");
                }
            }
        }
        synchronized (getGetNotificationStatusCallbackList()) {
            Iterator<IBaseResponseCallback> it2 = sGetNotificationStatusCallbackList.iterator();
            while (it2.hasNext()) {
                it2.next().d(100000, kkm.d(operatorStatus, "notificationStatus"));
            }
        }
    }

    private void parseGetOperator(List<cwd> list, List<cwe> list2) {
        if (list != null && !list.isEmpty() && CommonUtil.w(list.get(0).e()) == 127) {
            synchronized (getGetOperatorCallbackList()) {
                if (sGetOperatorCallbackList.size() != 0) {
                    int w = CommonUtil.w(list.get(0).c());
                    sGetOperatorCallbackList.get(0).d(w, kkm.d(Integer.valueOf(w), "getOperator"));
                    sGetOperatorCallbackList.remove(0);
                }
            }
            return;
        }
        OperatorStatus operatorStatus = getOperatorStatus(list2);
        synchronized (getGetOperatorCallbackList()) {
            if (sGetOperatorCallbackList.size() != 0) {
                sGetOperatorCallbackList.get(0).d(100000, kkm.d(operatorStatus, "getOperator"));
                sGetOperatorCallbackList.remove(0);
            }
        }
    }

    private OperatorStatus getOperatorStatus(List<cwe> list) {
        OperatorStatus operatorStatus = new OperatorStatus();
        Iterator<cwe> it = list.iterator();
        while (it.hasNext()) {
            for (cwd cwdVar : it.next().e()) {
                int w = CommonUtil.w(cwdVar.e());
                if (w == 2) {
                    operatorStatus.setTrainMonitorState(CommonUtil.w(cwdVar.c()));
                } else if (w == 3) {
                    operatorStatus.setOperatorType(CommonUtil.w(cwdVar.c()));
                } else if (w == 4) {
                    operatorStatus.setSportType(CommonUtil.w(cwdVar.c()));
                } else if (w == 5) {
                    operatorStatus.setRunPlanDate(CommonUtil.y(cwdVar.c()) * 1000);
                } else if (w == 6) {
                    operatorStatus.setWorkoutType(CommonUtil.w(cwdVar.c()));
                } else {
                    LogUtil.h(TAG, "parseGetOperator default");
                }
            }
        }
        return operatorStatus;
    }

    private void parseGetWorkoutRealTimeInfo(List<cwd> list, List<cwe> list2) {
        if (list != null && !list.isEmpty() && CommonUtil.w(list.get(0).e()) == 127) {
            synchronized (getGetWorkoutRealTimeInfoCallbackList()) {
                int w = CommonUtil.w(list.get(0).c());
                sGetWorkoutRealTimeInfoCallbackList.get(0).d(w, kkm.d(Integer.valueOf(w), "getWorkoutRealTimeInfo"));
                sGetWorkoutRealTimeInfoCallbackList.remove(0);
            }
            return;
        }
        WorkoutRealTimeInfo workoutRealTimeInfo = new WorkoutRealTimeInfo();
        Iterator<cwe> it = list2.iterator();
        while (it.hasNext()) {
            for (cwd cwdVar : it.next().e()) {
                switch (CommonUtil.w(cwdVar.e())) {
                    case 2:
                    case 3:
                    case 4:
                    case 6:
                        workoutRealTimeInfo.setSportType(CommonUtil.w(cwdVar.c()));
                        break;
                    case 5:
                        workoutRealTimeInfo.setClimeInfo(CommonUtil.y(cwdVar.c()) * 1000);
                        break;
                    case 7:
                        workoutRealTimeInfo.setDistanceInfo(CommonUtil.y(cwdVar.c()));
                        break;
                    case 8:
                        workoutRealTimeInfo.setClimeInfo(CommonUtil.y(cwdVar.c()));
                        break;
                    default:
                        LogUtil.h(TAG, "parseGetWorkoutRealTimeInfo default");
                        break;
                }
            }
        }
        synchronized (getGetWorkoutRealTimeInfoCallbackList()) {
            sGetWorkoutRealTimeInfoCallbackList.get(0).d(100000, kkm.d(workoutRealTimeInfo, "getWorkoutRealTimeInfo"));
            sGetWorkoutRealTimeInfoCallbackList.remove(0);
        }
    }

    private void parseNotificationWorkoutRealTime(List<cwe> list) {
        WorkoutRealTimeInfo workoutRealTimeInfo = new WorkoutRealTimeInfo();
        Iterator<cwe> it = list.iterator();
        while (it.hasNext()) {
            for (cwd cwdVar : it.next().e()) {
                switch (CommonUtil.w(cwdVar.e())) {
                    case 2:
                        workoutRealTimeInfo.setSportType(CommonUtil.w(cwdVar.c()));
                        break;
                    case 3:
                        workoutRealTimeInfo.setSpeedInfo(CommonUtil.w(cwdVar.c()) / 10.0f);
                        break;
                    case 4:
                        workoutRealTimeInfo.setHeartRateInfo(CommonUtil.w(cwdVar.c()));
                        break;
                    case 5:
                        workoutRealTimeInfo.setClimeInfo(CommonUtil.y(cwdVar.c()) * 1000);
                        break;
                    case 6:
                        workoutRealTimeInfo.setCalorieInfo(CommonUtil.y(cwdVar.c()));
                        break;
                    case 7:
                        workoutRealTimeInfo.setDistanceInfo(CommonUtil.y(cwdVar.c()));
                        break;
                    case 8:
                        workoutRealTimeInfo.setClimeInfo(CommonUtil.y(cwdVar.c()));
                        break;
                    default:
                        LogUtil.h(TAG, "parseNotificationWorkoutRealTime default");
                        break;
                }
            }
        }
        synchronized (getGetNotificationWorkoutRealTimeInfoCallbackList()) {
            Iterator<IBaseResponseCallback> it2 = sGetNotificationWorkoutRealTimeInfoCallbackList.iterator();
            while (it2.hasNext()) {
                it2.next().d(100000, kkm.d(workoutRealTimeInfo, "notificationWorkoutRealTimeInfo"));
            }
        }
    }

    private void parseResult(byte b, List<cwd> list, List<cwe> list2) {
        if (b == 19) {
            parseCommandIdWorkoutRecordSliceData(list, list2);
            return;
        }
        if (b != 21) {
            switch (b) {
                case 6:
                    parseNotificationSportReminder(list2);
                    break;
                case 7:
                    parseGetWorkoutRecord(list, list2);
                    break;
                case 8:
                    parseGetWorkoutRecordStatistic(list, list2);
                    break;
                case 9:
                    parseNotificationGetWorkoutRecordStatistic(list2);
                    break;
                case 10:
                    parseGetWorkoutData(list, list2);
                    break;
                case 11:
                    parseWorkoutOperateRealTime(list);
                    break;
                case 12:
                    parseWorkoutRecordPaceMap(list, list2);
                    break;
                case 13:
                    parseNotificationWorkoutRecordSpeechPlay(list);
                    break;
                default:
                    LogUtil.h(TAG, "parseResult default");
                    break;
            }
            return;
        }
        HwExerciseAdviceAw70ManagerUtil.parseWorkoutCapability(list, this.mWorkoutCapabilityCallbackList);
    }

    private void parseNotificationSportReminder(List<cwe> list) {
        SportReminder sportReminder = new SportReminder();
        Iterator<cwe> it = list.iterator();
        while (it.hasNext()) {
            for (cwd cwdVar : it.next().e()) {
                switch (CommonUtil.w(cwdVar.e())) {
                    case 3:
                        sportReminder.setReminderType(CommonUtil.w(cwdVar.c()));
                        break;
                    case 4:
                        sportReminder.setRunPhraseNumber(CommonUtil.w(cwdVar.c()));
                        break;
                    case 5:
                        ArrayList arrayList = new ArrayList(16);
                        arrayList.add(Integer.valueOf(CommonUtil.w(cwdVar.c().substring(0, 4))));
                        arrayList.add(Integer.valueOf(CommonUtil.w(cwdVar.c().substring(4, 8))));
                        sportReminder.setRunPhraseVariable(arrayList);
                        break;
                    case 6:
                        sportReminder.setDistanceInfo(CommonUtil.y(cwdVar.c()));
                        break;
                    case 7:
                        sportReminder.setTimeInfo(CommonUtil.y(cwdVar.c()));
                        break;
                    case 8:
                        sportReminder.setHrValueInfo(CommonUtil.w(cwdVar.c()));
                        break;
                    case 9:
                        sportReminder.setHrStatusInfo(CommonUtil.w(cwdVar.c()));
                        break;
                    default:
                        LogUtil.h(TAG, "parseNotificationSportReminder default");
                        break;
                }
            }
        }
        synchronized (getGetNotificationSportReminderCallbackList()) {
            Iterator<IBaseResponseCallback> it2 = sGetNotificationSportReminderCallbackList.iterator();
            while (it2.hasNext()) {
                it2.next().d(100000, kkm.d(sportReminder, "notificationSportReminder"));
            }
        }
    }

    private void parseGetWorkoutRecord(List<cwd> list, List<cwe> list2) {
        if (list != null && !list.isEmpty() && CommonUtil.w(list.get(0).e()) == 127) {
            synchronized (getGetWorkoutRecordCallbackList()) {
                if (sGetWorkoutRecordCallbackList.size() != 0) {
                    int w = CommonUtil.w(list.get(0).c());
                    sGetWorkoutRecordCallbackList.get(0).d(w, kkm.d(Integer.valueOf(w), "getWorkoutRecord"));
                    sGetWorkoutRecordCallbackList.remove(0);
                }
            }
            return;
        }
        WorkoutRecord workoutRecord = new WorkoutRecord();
        ArrayList arrayList = new ArrayList(16);
        parseGetWorkoutRecordSecond(list2, workoutRecord, arrayList);
        workoutRecord.setWorkoutRecordStructList(arrayList);
        if (!arrayList.isEmpty()) {
            setAw70LastRecordId(arrayList.get(arrayList.size() - 1).getWorkoutRecordId());
        }
        synchronized (getGetWorkoutRecordCallbackList()) {
            if (sGetWorkoutRecordCallbackList.size() != 0) {
                sGetWorkoutRecordCallbackList.get(0).d(100000, kkm.d(workoutRecord, "getWorkoutRecord"));
                sGetWorkoutRecordCallbackList.remove(0);
            }
        }
    }

    private void parseGetWorkoutRecordSecond(List<cwe> list, WorkoutRecord workoutRecord, List<WorkoutRecordStruct> list2) {
        for (cwe cweVar : list) {
            for (cwd cwdVar : cweVar.e()) {
                if (CommonUtil.w(cwdVar.e()) == 2) {
                    workoutRecord.setWorkoutRecordCount(CommonUtil.w(cwdVar.c()));
                } else {
                    LogUtil.h(TAG, "parseGetWorkoutRecordSecond tlvList default");
                }
            }
            Iterator<cwe> it = cweVar.a().iterator();
            while (it.hasNext()) {
                List<cwd> e = it.next().e();
                WorkoutRecordStruct workoutRecordStruct = new WorkoutRecordStruct();
                Iterator<cwd> it2 = e.iterator();
                while (it2.hasNext()) {
                    parseGetWorkoutRecordThird(it2.next(), workoutRecordStruct);
                }
                list2.add(workoutRecordStruct);
            }
        }
    }

    private void parseGetWorkoutRecordThird(cwd cwdVar, WorkoutRecordStruct workoutRecordStruct) {
        int w = CommonUtil.w(cwdVar.e());
        if (w == 6) {
            workoutRecordStruct.setWorkoutRecordId(CommonUtil.w(cwdVar.c()));
            return;
        }
        if (w == 7) {
            workoutRecordStruct.setWorkoutIndexCount(CommonUtil.w(cwdVar.c()));
            return;
        }
        if (w == 8) {
            workoutRecordStruct.setPaceIndexCount(CommonUtil.w(cwdVar.c()));
        } else if (w == 10) {
            workoutRecordStruct.setWorkoutSliceNumber(CommonUtil.w(cwdVar.c()));
        } else {
            LogUtil.h(TAG, "parseGetWorkoutRecordSecond default");
        }
    }

    private void parseGetWorkoutRecordStatistic(List<cwd> list, List<cwe> list2) {
        if (list != null && !list.isEmpty() && CommonUtil.w(list.get(0).e()) == 127) {
            synchronized (getGetWorkoutRecordStatisticCallbackList()) {
                if (sGetWorkoutRecordStatisticCallbackList.size() != 0) {
                    int w = CommonUtil.w(list.get(0).c());
                    sGetWorkoutRecordStatisticCallbackList.get(0).d(w, kkm.d(Integer.valueOf(w), "getWorkoutRecordStatistic"));
                    sGetWorkoutRecordStatisticCallbackList.remove(0);
                }
            }
            return;
        }
        WorkoutRecordStatistic workoutRecordStatistic = new WorkoutRecordStatistic();
        Iterator<cwe> it = list2.iterator();
        while (it.hasNext()) {
            for (cwd cwdVar : it.next().e()) {
                int c = jds.c(cwdVar.e(), 16);
                if (c == 2) {
                    workoutRecordStatistic.setWorkoutRecordId(jds.c(cwdVar.c(), 16));
                } else if (c == 3) {
                    workoutRecordStatistic.setWorkoutRecordStatus(jds.c(cwdVar.c(), 16));
                } else if (c == 4) {
                    workoutRecordStatistic.setWorkoutRecordStartTime(CommonUtil.y(cwdVar.c()) * 1000);
                } else {
                    parseGetWorkoutRecordStatisticSecond(cwdVar, workoutRecordStatistic);
                }
            }
        }
        synchronized (getGetWorkoutRecordStatisticCallbackList()) {
            if (sGetWorkoutRecordStatisticCallbackList.size() != 0) {
                sGetWorkoutRecordStatisticCallbackList.get(0).d(100000, kkm.d(workoutRecordStatistic, "getWorkoutRecordStatistic"));
                sGetWorkoutRecordStatisticCallbackList.remove(0);
            }
        }
    }

    private void parseGetWorkoutRecordStatisticSecond(cwd cwdVar, WorkoutRecordStatistic workoutRecordStatistic) {
        switch (jds.c(cwdVar.e(), 16)) {
            case 5:
                workoutRecordStatistic.setWorkoutRecordEndTime(CommonUtil.y(cwdVar.c()) * 1000);
                break;
            case 6:
                workoutRecordStatistic.setWorkoutRecordCalorie(jds.c(cwdVar.c(), 16));
                break;
            case 7:
                workoutRecordStatistic.setWorkoutRecordDistance(jds.c(cwdVar.c(), 16));
                break;
            case 8:
                workoutRecordStatistic.setWorkoutRecordStep(CommonUtil.y(cwdVar.c()));
                break;
            case 9:
                workoutRecordStatistic.setWorkoutRecordTotalTime(CommonUtil.y(cwdVar.c()));
                break;
            case 10:
                workoutRecordStatistic.setWorkoutRecordSpeed(jds.c(cwdVar.c(), 16));
                break;
            case 11:
                workoutRecordStatistic.setWorkoutClimb(CommonUtil.y(cwdVar.c()));
                break;
            case 12:
                workoutRecordStatistic.setWorkoutHrPeakMin(jds.c(cwdVar.c().substring(0, 2), 16));
                workoutRecordStatistic.setWorkoutHrPeakMax(jds.c(cwdVar.c().substring(2, 4), 16));
                break;
            case 13:
                workoutRecordStatistic.setWorkoutLoadPeak(jds.c(cwdVar.c(), 16));
                break;
            default:
                parseGetWorkoutRecordStatisticThird(cwdVar, workoutRecordStatistic);
                break;
        }
    }

    private void parseGetWorkoutRecordStatisticThird(cwd cwdVar, WorkoutRecordStatistic workoutRecordStatistic) {
        switch (jds.c(cwdVar.e(), 16)) {
            case 14:
                workoutRecordStatistic.setWorkoutEtrainingEffect(jds.c(cwdVar.c(), 16));
                break;
            case 15:
                workoutRecordStatistic.setWorkoutEpoc(jds.c(cwdVar.c(), 16));
                break;
            case 16:
                workoutRecordStatistic.setWorkoutMaxMet(jds.c(cwdVar.c(), 16));
                break;
            case 17:
                workoutRecordStatistic.setWorkoutRecoveryTime(jds.c(cwdVar.c(), 16));
                break;
            case 18:
                workoutRecordStatistic.setWorkoutExerciseDuration(CommonUtil.y(cwdVar.c()) * 1000);
                break;
            case 19:
                workoutRecordStatistic.setWorkoutDateInfo(CommonUtil.y(cwdVar.c()));
                break;
            case 20:
                workoutRecordStatistic.setWorkoutType(jds.c(cwdVar.c(), 16));
                break;
            case 21:
                workoutRecordStatistic.setSwimType(jds.c(cwdVar.c(), 16));
                break;
            case 22:
                workoutRecordStatistic.setSwimPullTimes(jds.c(cwdVar.c(), 16));
                break;
            case 23:
                workoutRecordStatistic.setSwimPullRate(jds.c(cwdVar.c(), 16));
                break;
            default:
                parseGetWorkoutRecordStatisticFourth(cwdVar, workoutRecordStatistic);
                break;
        }
    }

    private void parseGetWorkoutRecordStatisticFourth(cwd cwdVar, WorkoutRecordStatistic workoutRecordStatistic) {
        int c = jds.c(cwdVar.e(), 16);
        switch (c) {
            case 24:
                workoutRecordStatistic.setSwimPoolLength(jds.c(cwdVar.c(), 16));
                break;
            case 25:
                workoutRecordStatistic.setSwimTripTimes(jds.c(cwdVar.c(), 16));
                break;
            case 26:
                workoutRecordStatistic.setSwimAvgSwolf(jds.c(cwdVar.c(), 16));
                break;
            default:
                switch (c) {
                    case 49:
                        workoutRecordStatistic.setActiveDuration(jds.c(cwdVar.c(), 16));
                        break;
                    case 50:
                        workoutRecordStatistic.setJumpTimes(jds.c(cwdVar.c(), 16));
                        break;
                    case 51:
                        workoutRecordStatistic.setMaxJumpHeight(jds.c(cwdVar.c(), 16));
                        break;
                    case 52:
                        workoutRecordStatistic.setMaxJumpDuration(jds.c(cwdVar.c(), 16));
                        break;
                    case 53:
                        workoutRecordStatistic.setMaxRunSpeed(jds.c(cwdVar.c(), 16));
                        break;
                    case 54:
                        workoutRecordStatistic.setRunScore(jds.c(cwdVar.c(), 16));
                        break;
                    case 55:
                        workoutRecordStatistic.setMoveScore(jds.c(cwdVar.c(), 16));
                        break;
                    default:
                        parseGetWorkoutRecordStatisticFifth(cwdVar, workoutRecordStatistic);
                        break;
                }
        }
    }

    private void parseGetWorkoutRecordStatisticFifth(cwd cwdVar, WorkoutRecordStatistic workoutRecordStatistic) {
        switch (jds.c(cwdVar.e(), 16)) {
            case 56:
                workoutRecordStatistic.setJumpScore(jds.c(cwdVar.c(), 16));
                break;
            case 57:
                workoutRecordStatistic.setTotalScore(jds.c(cwdVar.c(), 16));
                break;
            case 58:
                workoutRecordStatistic.setExplosiveScore(jds.c(cwdVar.c(), 16));
                break;
            case 59:
                workoutRecordStatistic.setIntenseScore(jds.c(cwdVar.c(), 16));
                break;
            default:
                LogUtil.h(TAG, "parseGetWorkoutRecordStatisticFifth default");
                break;
        }
    }

    private void parseNotificationGetWorkoutRecordStatistic(List<cwe> list) {
        WorkoutRecordStatistic workoutRecordStatistic = new WorkoutRecordStatistic();
        Iterator<cwe> it = list.iterator();
        while (it.hasNext()) {
            for (cwd cwdVar : it.next().e()) {
                switch (CommonUtil.w(cwdVar.e())) {
                    case 2:
                        workoutRecordStatistic.setWorkoutRecordId(CommonUtil.w(cwdVar.c()));
                        break;
                    case 3:
                        workoutRecordStatistic.setWorkoutRecordStatus(CommonUtil.w(cwdVar.c()));
                        break;
                    case 4:
                        workoutRecordStatistic.setWorkoutRecordStartTime(CommonUtil.y(cwdVar.c()) * 1000);
                        break;
                    case 5:
                        workoutRecordStatistic.setWorkoutRecordEndTime(CommonUtil.y(cwdVar.c()) * 1000);
                        break;
                    case 6:
                        workoutRecordStatistic.setWorkoutRecordCalorie(CommonUtil.w(cwdVar.c()));
                        break;
                    case 7:
                        workoutRecordStatistic.setWorkoutRecordDistance(CommonUtil.w(cwdVar.c()));
                        break;
                    case 8:
                        workoutRecordStatistic.setWorkoutRecordStep(CommonUtil.y(cwdVar.c()));
                        break;
                    default:
                        parseNotificationGetWorkoutRecordStatisticSecond(cwdVar, workoutRecordStatistic);
                        break;
                }
            }
        }
        synchronized (getGetNotificationGetWorkoutRecordStatisticCallbackList()) {
            Iterator<IBaseResponseCallback> it2 = sGetNotificationGetWorkoutRecordStatisticCallbackList.iterator();
            while (it2.hasNext()) {
                it2.next().d(100000, kkm.d(workoutRecordStatistic, "notificationGetWorkoutRecordStatistic"));
            }
        }
    }

    private void parseNotificationGetWorkoutRecordStatisticSecond(cwd cwdVar, WorkoutRecordStatistic workoutRecordStatistic) {
        switch (CommonUtil.w(cwdVar.e())) {
            case 9:
                workoutRecordStatistic.setWorkoutRecordTotalTime(CommonUtil.y(cwdVar.c()));
                break;
            case 10:
                workoutRecordStatistic.setWorkoutRecordSpeed(CommonUtil.w(cwdVar.c()));
                break;
            case 11:
                workoutRecordStatistic.setWorkoutClimb(CommonUtil.y(cwdVar.c()));
                break;
            case 12:
                workoutRecordStatistic.setWorkoutHrPeakMax(CommonUtil.w(cwdVar.c().substring(0, 2)));
                workoutRecordStatistic.setWorkoutHrPeakMin(CommonUtil.w(cwdVar.c().substring(2, 4)));
                break;
            case 13:
                workoutRecordStatistic.setWorkoutLoadPeak(CommonUtil.w(cwdVar.c()));
                break;
            case 14:
                workoutRecordStatistic.setWorkoutEtrainingEffect(CommonUtil.w(cwdVar.c()));
                break;
            case 15:
                workoutRecordStatistic.setWorkoutEpoc(CommonUtil.w(cwdVar.c()));
                break;
            case 16:
                workoutRecordStatistic.setWorkoutMaxMet(CommonUtil.w(cwdVar.c()));
                break;
            case 17:
                workoutRecordStatistic.setWorkoutRecoveryTime(CommonUtil.w(cwdVar.c()));
                break;
            default:
                LogUtil.h(TAG, "parseNotificationGetWorkoutRecordStatisticSecond default");
                parseNotificationGetWorkoutRecordStatisticThird(cwdVar, workoutRecordStatistic);
                break;
        }
    }

    private void parseNotificationGetWorkoutRecordStatisticThird(cwd cwdVar, WorkoutRecordStatistic workoutRecordStatistic) {
        int w = CommonUtil.w(cwdVar.e());
        if (w == 18) {
            workoutRecordStatistic.setWorkoutExerciseDuration(CommonUtil.y(cwdVar.c()));
        } else if (w == 19) {
            workoutRecordStatistic.setWorkoutDateInfo(CommonUtil.y(cwdVar.c()));
        } else {
            LogUtil.h(TAG, "parseNotificationGetWorkoutRecordStatisticThird default");
        }
    }

    private void parseGetWorkoutData(List<cwd> list, List<cwe> list2) {
        if (list != null && !list.isEmpty() && CommonUtil.w(list.get(0).e()) == 127) {
            synchronized (getGetWorkoutDataCallbackList()) {
                if (sGetWorkoutDataCallbackList.size() != 0) {
                    int w = CommonUtil.w(list.get(0).c());
                    sGetWorkoutDataCallbackList.get(0).d(w, kkm.d(Integer.valueOf(w), "getWorkoutData"));
                    sGetWorkoutDataCallbackList.remove(0);
                }
            }
            return;
        }
        WorkoutDataStruct workoutDataStruct = new WorkoutDataStruct();
        if (list2 != null && !list2.isEmpty()) {
            Iterator<cwe> it = list2.iterator();
            while (it.hasNext()) {
                parseWorkoutData(it.next(), workoutDataStruct);
            }
        }
        synchronized (getGetWorkoutDataCallbackList()) {
            if (sGetWorkoutDataCallbackList.size() != 0) {
                sGetWorkoutDataCallbackList.get(0).d(100000, kkm.d(workoutDataStruct, "getWorkoutData"));
                sGetWorkoutDataCallbackList.remove(0);
            }
        }
    }

    private void parseWorkoutData(cwe cweVar, WorkoutDataStruct workoutDataStruct) {
        List<cwd> e = cweVar.e();
        DataHeader dataHeader = new DataHeader();
        ArrayList arrayList = new ArrayList(16);
        ExtrasDataUtils extrasDataUtils = new ExtrasDataUtils();
        String str = null;
        int i = 26;
        String str2 = "0000000111111111";
        for (cwd cwdVar : e) {
            int w = CommonUtil.w(cwdVar.e());
            if (w == 2) {
                workoutDataStruct.setWorkoutRecordId(CommonUtil.w(cwdVar.c()));
            } else if (w == 3) {
                workoutDataStruct.setWorkoutDataIndex(CommonUtil.w(cwdVar.c()));
            } else if (w == 4) {
                LogUtil.a(TAG, "ready parse header data.");
                extrasDataUtils.parseHeader(cwdVar.c(), dataHeader);
            } else if (w == 5) {
                str = cwdVar.c();
            } else if (w == 8) {
                i = CommonUtil.w(cwdVar.c()) * 2;
            } else if (w == 9) {
                str2 = extrasDataUtils.parseExtraBitmap(cwdVar.c());
            } else {
                LogUtil.h(TAG, "parseWorkoutData default");
            }
        }
        LogUtil.a(TAG, "ready parse frame data.");
        extrasDataUtils.parseFrameData(str, i, str2, dataHeader, arrayList);
        dataHeader.setWorkoutDataInfoList(arrayList);
        workoutDataStruct.setDataHeader(dataHeader);
    }

    private void parseWorkoutOperateRealTime(List<cwd> list) {
        synchronized (getGetWorkoutOperatorRealTimeDataCallbackList()) {
            if (sGetWorkoutOperatorRealTimeDataCallbackList.size() != 0) {
                int w = CommonUtil.w(list.get(0).c());
                sGetWorkoutOperatorRealTimeDataCallbackList.get(0).d(w, kkm.d(Integer.valueOf(w), "workoutOperateRealtimeData"));
                sGetWorkoutOperatorRealTimeDataCallbackList.remove(0);
            }
        }
    }

    private void parseWorkoutRecordPaceMap(List<cwd> list, List<cwe> list2) {
        if (list != null && !list.isEmpty() && CommonUtil.w(list.get(0).e()) == 127) {
            synchronized (getGetWorkoutRecordPaceMapListCallbackList()) {
                if (sGetWorkoutRecordPaceMapListCallbackList.size() != 0) {
                    int w = CommonUtil.w(list.get(0).c());
                    sGetWorkoutRecordPaceMapListCallbackList.get(0).d(w, kkm.d(Integer.valueOf(w), "getWorkoutRecordPaceMap"));
                    sGetWorkoutRecordPaceMapListCallbackList.remove(0);
                }
            }
            return;
        }
        WorkRecordIndexPaceMapList workRecordIndexPaceMapList = new WorkRecordIndexPaceMapList();
        ArrayList arrayList = new ArrayList(16);
        for (cwd cwdVar : list2.get(0).e()) {
            if (CommonUtil.w(cwdVar.e()) == 2) {
                workRecordIndexPaceMapList.setWorkoutRecordId(CommonUtil.w(cwdVar.c()));
            } else if (CommonUtil.w(cwdVar.e()) == 8) {
                workRecordIndexPaceMapList.setPaceIndex(CommonUtil.w(cwdVar.c()));
            } else {
                LogUtil.h(TAG, "parseWorkoutRecordPaceMap else");
            }
        }
        for (cwe cweVar : list2.get(0).a()) {
            WorkoutRecordPaceMap workoutRecordPaceMap = new WorkoutRecordPaceMap();
            parseWorkoutRecordPaceMapSecond(cweVar.e(), workoutRecordPaceMap);
            arrayList.add(workoutRecordPaceMap);
        }
        workRecordIndexPaceMapList.setPaceMapList(arrayList);
        synchronized (getGetWorkoutRecordPaceMapListCallbackList()) {
            if (sGetWorkoutRecordPaceMapListCallbackList.size() != 0) {
                sGetWorkoutRecordPaceMapListCallbackList.get(0).d(100000, kkm.d(workRecordIndexPaceMapList, "getWorkoutRecordPaceMap"));
                sGetWorkoutRecordPaceMapListCallbackList.remove(0);
            }
        }
    }

    private void parseWorkoutRecordPaceMapSecond(List<cwd> list, WorkoutRecordPaceMap workoutRecordPaceMap) {
        for (cwd cwdVar : list) {
            int w = CommonUtil.w(cwdVar.e());
            if (w == 4) {
                workoutRecordPaceMap.setDistance(CommonUtil.w(cwdVar.c()));
            } else if (w == 5) {
                workoutRecordPaceMap.setUnitType(CommonUtil.w(cwdVar.c()));
            } else if (w == 6) {
                workoutRecordPaceMap.setPace(CommonUtil.w(cwdVar.c()));
            } else if (w == 7) {
                workoutRecordPaceMap.setPointIndex(CommonUtil.w(cwdVar.c()));
            } else if (w == 9) {
                workoutRecordPaceMap.setLastLessDistance(CommonUtil.w(cwdVar.c()));
                workoutRecordPaceMap.setIsLastLessDistance(true);
            } else {
                LogUtil.h(TAG, "parseWorkoutRecordPaceMapSecond default");
            }
        }
    }

    private void parseNotificationWorkoutRecordSpeechPlay(List<cwd> list) {
        WorkoutRecordSpeechPlay workoutRecordSpeechPlay = new WorkoutRecordSpeechPlay();
        for (cwd cwdVar : list) {
            if (CommonUtil.w(cwdVar.e()) == 1) {
                workoutRecordSpeechPlay.setWorkoutRecordSpeechPlayRequest(CommonUtil.w(cwdVar.c()));
            }
        }
        synchronized (getGetNotificationWorkoutRecordSpeechPlayCallbackList()) {
            Iterator<IBaseResponseCallback> it = sGetNotificationWorkoutRecordSpeechPlayCallbackList.iterator();
            while (it.hasNext()) {
                it.next().d(100000, kkm.d(workoutRecordSpeechPlay, "notificationWorkoutRecordSpeechPlay"));
            }
        }
    }

    private void parseCommandIdWorkoutRecordSliceData(List<cwd> list, List<cwe> list2) {
        ArrayList arrayList = new ArrayList(16);
        int i = 0;
        for (cwd cwdVar : list) {
            try {
                if (CommonUtil.w(cwdVar.e()) == 1) {
                    i = CommonUtil.w(cwdVar.c());
                    LogUtil.a(TAG, "recordId is ", Integer.valueOf(i));
                } else if (CommonUtil.w(cwdVar.e()) == 2) {
                    LogUtil.a(TAG, "sliceIndex is ", Integer.valueOf(CommonUtil.w(cwdVar.c())));
                } else {
                    LogUtil.h(TAG, "tlv.getTag() is ", cwdVar.e());
                }
            } catch (NumberFormatException unused) {
                LogUtil.b(TAG, "getResult NumberFormatException");
            }
        }
        Iterator<cwe> it = list2.iterator();
        while (it.hasNext()) {
            Iterator<cwe> it2 = it.next().a().iterator();
            while (it2.hasNext()) {
                List<cwd> e = it2.next().e();
                WorkoutRecordJumpData workoutRecordJumpData = new WorkoutRecordJumpData();
                workoutRecordJumpData.setRecordId(i);
                Iterator<cwd> it3 = e.iterator();
                while (it3.hasNext()) {
                    try {
                        configWorkoutRecordJumpData(workoutRecordJumpData, it3.next());
                    } catch (NumberFormatException unused2) {
                        LogUtil.b(TAG, "parseRunPostureDataInfo NumberFormatException1");
                    }
                }
                arrayList.add(workoutRecordJumpData);
            }
        }
        synchronized (getWorkoutRecordSliceCallbackList()) {
            if (sWorkoutRecordSliceCallbackList.size() != 0) {
                sWorkoutRecordSliceCallbackList.get(0).d(100000, kkm.d(arrayList, "getWorkoutRecordSliceData"));
                sWorkoutRecordSliceCallbackList.remove(0);
            }
        }
    }

    private void configWorkoutRecordJumpData(WorkoutRecordJumpData workoutRecordJumpData, cwd cwdVar) {
        int w = CommonUtil.w(cwdVar.e());
        if (w == 5) {
            int w2 = CommonUtil.w(cwdVar.c());
            LogUtil.a(TAG, "jump_time is ", Integer.valueOf(w2));
            workoutRecordJumpData.setJumpTime(w2);
        } else if (w == 6) {
            int w3 = CommonUtil.w(cwdVar.c());
            LogUtil.a(TAG, "jump_duration is ", Integer.valueOf(w3));
            workoutRecordJumpData.setJumpDuration(w3);
        } else {
            if (w == 7) {
                int w4 = CommonUtil.w(cwdVar.c());
                LogUtil.a(TAG, "jump_height is ", Integer.valueOf(w4));
                workoutRecordJumpData.setJumpHeight(w4);
                return;
            }
            LogUtil.h(TAG, "configWorkoutRecordJumpData default");
        }
    }

    private static List<IBaseResponseCallback> getSetOperatorCallbackList() {
        List<IBaseResponseCallback> list;
        synchronized (HwWorkoutServiceAw70Manager.class) {
            list = sSetOperatorCallbackList;
        }
        return list;
    }

    private static List<IBaseResponseCallback> getGetOperatorCallbackList() {
        List<IBaseResponseCallback> list;
        synchronized (HwWorkoutServiceAw70Manager.class) {
            list = sGetOperatorCallbackList;
        }
        return list;
    }

    private static List<IBaseResponseCallback> getGetWorkoutRealTimeInfoCallbackList() {
        List<IBaseResponseCallback> list;
        synchronized (HwWorkoutServiceAw70Manager.class) {
            list = sGetWorkoutRealTimeInfoCallbackList;
        }
        return list;
    }

    private static List<IBaseResponseCallback> getGetWorkoutRecordCallbackList() {
        List<IBaseResponseCallback> list;
        synchronized (HwWorkoutServiceAw70Manager.class) {
            list = sGetWorkoutRecordCallbackList;
        }
        return list;
    }

    private static List<IBaseResponseCallback> getGetWorkoutRecordStatisticCallbackList() {
        List<IBaseResponseCallback> list;
        synchronized (HwWorkoutServiceAw70Manager.class) {
            list = sGetWorkoutRecordStatisticCallbackList;
        }
        return list;
    }

    private static List<IBaseResponseCallback> getGetWorkoutDataCallbackList() {
        List<IBaseResponseCallback> list;
        synchronized (HwWorkoutServiceAw70Manager.class) {
            list = sGetWorkoutDataCallbackList;
        }
        return list;
    }

    private static List<IBaseResponseCallback> getGetNotificationStatusCallbackList() {
        List<IBaseResponseCallback> list;
        synchronized (HwWorkoutServiceAw70Manager.class) {
            list = sGetNotificationStatusCallbackList;
        }
        return list;
    }

    private static List<IBaseResponseCallback> getGetNotificationWorkoutRealTimeInfoCallbackList() {
        List<IBaseResponseCallback> list;
        synchronized (HwWorkoutServiceAw70Manager.class) {
            list = sGetNotificationWorkoutRealTimeInfoCallbackList;
        }
        return list;
    }

    private static List<IBaseResponseCallback> getGetNotificationSportReminderCallbackList() {
        List<IBaseResponseCallback> list;
        synchronized (HwWorkoutServiceAw70Manager.class) {
            list = sGetNotificationSportReminderCallbackList;
        }
        return list;
    }

    private static List<IBaseResponseCallback> getGetNotificationGetWorkoutRecordStatisticCallbackList() {
        List<IBaseResponseCallback> list;
        synchronized (HwWorkoutServiceAw70Manager.class) {
            list = sGetNotificationGetWorkoutRecordStatisticCallbackList;
        }
        return list;
    }

    private static List<IBaseResponseCallback> getGetWorkoutOperatorRealTimeDataCallbackList() {
        return sGetWorkoutOperatorRealTimeDataCallbackList;
    }

    private static List<IBaseResponseCallback> getGetWorkoutRecordPaceMapListCallbackList() {
        List<IBaseResponseCallback> list;
        synchronized (HwWorkoutServiceAw70Manager.class) {
            list = sGetWorkoutRecordPaceMapListCallbackList;
        }
        return list;
    }

    private static List<IBaseResponseCallback> getWorkoutRecordSliceCallbackList() {
        List<IBaseResponseCallback> list;
        synchronized (HwWorkoutServiceAw70Manager.class) {
            list = sWorkoutRecordSliceCallbackList;
        }
        return list;
    }

    private static List<IBaseResponseCallback> getGetNotificationWorkoutRecordSpeechPlayCallbackList() {
        return sGetNotificationWorkoutRecordSpeechPlayCallbackList;
    }
}
