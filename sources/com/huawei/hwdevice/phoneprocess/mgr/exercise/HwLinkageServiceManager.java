package com.huawei.hwdevice.phoneprocess.mgr.exercise;

import android.content.Context;
import com.huawei.datatype.DeviceCommand;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.device.model.OperatorStatus;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.binder.ParserInterface;
import com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.dmsmanager.HwDeviceMgrInterface;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.WorkoutRecordSpeechPlay;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.WorkoutRecordStatistic;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.utils.HwExerciseCommandUtil;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.utils.HwWorkoutServiceUtils;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.blt;
import defpackage.cun;
import defpackage.cvt;
import defpackage.cvx;
import defpackage.cwd;
import defpackage.cwe;
import defpackage.cwg;
import defpackage.cwl;
import defpackage.jsz;
import defpackage.kkm;
import defpackage.kon;
import defpackage.sqo;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class HwLinkageServiceManager implements ParserInterface {
    private static final int CONVERT_RADIX_HEX = 16;
    private static final int FIRST_CALLBACK = 0;
    private static final int METER_TURN_METERS = 10;
    private static final int MILLISECONDS_IN_SECOND = 1000;
    private static final long MILLISECONDS_TO_SECOND = 1000;
    private static final int PLACE_HOLDERS = 2;
    private static final String TAG = "HwLinkageServiceManager";
    private static final String TAG_RELEASE = "BTSYNC_HwLinkageServiceManager";
    private static final int TL_NUMBER_STRUCT = 128;
    private static HwLinkageServiceManager sInstance;
    private Context mContext;
    private HwDeviceMgrInterface mHwDeviceMgr;
    private IBaseResponseCallback mRealTimeSportDataListCallback;
    private static final Object SYNC_LOCK = new Object();
    private static List<IBaseResponseCallback> sNotificationOperatorCallbackList = new ArrayList(16);
    private static List<IBaseResponseCallback> sOperatorCallbackList = new ArrayList(16);
    private static List<IBaseResponseCallback> sWorkoutOperatorRealtimeDataCallbackList = new ArrayList(16);
    private static List<IBaseResponseCallback> sNotificationStatusCallbackList = new ArrayList(16);
    private static List<IBaseResponseCallback> sNotificationGetWorkoutRecordStatisticCallbackList = new ArrayList(16);
    private static List<IBaseResponseCallback> sNotificationWorkoutRecordSpeechPlayCallbackList = new ArrayList(16);

    private HwLinkageServiceManager(Context context) {
        ReleaseLogUtil.e(TAG_RELEASE, "HwWorkoutServiceManager construct.");
        this.mContext = context;
        this.mHwDeviceMgr = jsz.b(context);
    }

    public static HwLinkageServiceManager getInstance() {
        HwLinkageServiceManager hwLinkageServiceManager;
        synchronized (SYNC_LOCK) {
            if (sInstance == null) {
                sInstance = new HwLinkageServiceManager(BaseApplication.getContext());
            }
            hwLinkageServiceManager = sInstance;
        }
        return hwLinkageServiceManager;
    }

    @Override // com.huawei.hwdevice.phoneprocess.binder.ParserInterface
    public void getResult(DeviceInfo deviceInfo, byte[] bArr) {
        ReleaseLogUtil.e(TAG_RELEASE, "getDataInfo() to get data info");
        blt.d(TAG, bArr, "getDataInfo(): ");
        String d = cvx.d(bArr);
        if (d.length() > 4) {
            try {
                cwe a2 = new cwl().a(d.substring(4, d.length()));
                List<cwd> e = a2.e();
                List<cwe> a3 = a2.a();
                byte b = bArr[1];
                if (b == 1) {
                    handleNotificationOperatorStatus(e);
                } else if (b == 2) {
                    handleOperatorStatus(a3);
                } else if (b == 3) {
                    handleGetOperatorStatus(e, a3);
                } else if (b == 9) {
                    handleNotificationWorkoutRecordStatistic(a3);
                } else if (b == 11) {
                    handleWorkoutOperateRealTimeData(e);
                } else if (b == 13) {
                    handleNotificationWorkoutSpeechPlay(e);
                } else if (b == 17) {
                    handleWorkoutReportPlayData(a3, deviceInfo);
                } else {
                    ReleaseLogUtil.d(TAG_RELEASE, "getResult error commandId.");
                }
                return;
            } catch (cwg e2) {
                ReleaseLogUtil.c(TAG_RELEASE, "receive command TlvException: ", ExceptionUtils.d(e2));
                sqo.w("receive command TlvException: " + ExceptionUtils.d(e2));
                return;
            } catch (IndexOutOfBoundsException e3) {
                e = e3;
                ReleaseLogUtil.c(TAG_RELEASE, "receive command Exception: ", ExceptionUtils.d(e));
                sqo.w("receive command Exception: " + ExceptionUtils.d(e));
                return;
            } catch (NumberFormatException e4) {
                e = e4;
                ReleaseLogUtil.c(TAG_RELEASE, "receive command Exception: ", ExceptionUtils.d(e));
                sqo.w("receive command Exception: " + ExceptionUtils.d(e));
                return;
            } catch (RuntimeException e5) {
                ReleaseLogUtil.c(TAG_RELEASE, "receive command RuntimeException: ", ExceptionUtils.d(e5));
                sqo.w("receive command RuntimeException: " + ExceptionUtils.d(e5));
                return;
            } catch (Exception e6) {
                ReleaseLogUtil.c(TAG_RELEASE, "receive command error:", ExceptionUtils.d(e6));
                sqo.w("receive command error: " + ExceptionUtils.d(e6));
                return;
            }
        }
        ReleaseLogUtil.d(TAG_RELEASE, "receive command data lenth less 4");
        sqo.w("receive command data lenth less 4");
    }

    public void setOperator(JSONObject jSONObject, IBaseResponseCallback iBaseResponseCallback) throws JSONException {
        if (jSONObject == null || iBaseResponseCallback == null) {
            ReleaseLogUtil.d(TAG_RELEASE, "setOperator parameters is error.");
            sqo.w("setOperator parameters is error.");
            return;
        }
        synchronized (SYNC_LOCK) {
            ReleaseLogUtil.e(TAG_RELEASE, "setOperator OperatorType:", Integer.valueOf(jSONObject.getInt("operator_type")));
            if (this.mHwDeviceMgr == null) {
                iBaseResponseCallback.d(0, kkm.d(100001, "setOperator"));
                sqo.w("mHwDeviceMgr is null.");
                return;
            }
            DeviceInfo connectedDeviceWithoutAw70 = getConnectedDeviceWithoutAw70();
            if (connectedDeviceWithoutAw70 == null) {
                iBaseResponseCallback.d(0, kkm.d(100001, "setOperator"));
                sqo.w("deviceInfo is null.");
            } else {
                synchronized (getNotificationOperatorCallbackList()) {
                    sNotificationOperatorCallbackList.add(iBaseResponseCallback);
                }
                this.mHwDeviceMgr.sendDeviceData(HwExerciseCommandUtil.notifyOperatorStatus(jSONObject, connectedDeviceWithoutAw70));
            }
        }
    }

    public void setNotificationStatusResponse(JSONObject jSONObject, IBaseResponseCallback iBaseResponseCallback) {
        synchronized (SYNC_LOCK) {
            ReleaseLogUtil.e(TAG_RELEASE, "response of NotificationStatus info");
            if (jSONObject == null || iBaseResponseCallback == null) {
                ReleaseLogUtil.d(TAG_RELEASE, "setNotificationStatusResponse, input parameters is illegal");
                sqo.w("setNotificationStatusResponse, input parameters is illegal");
                return;
            }
            try {
                this.mHwDeviceMgr.sendDeviceData(HwExerciseCommandUtil.getNotificationResponseCommand(jSONObject));
                iBaseResponseCallback.d(100000, kkm.d("success", "setNotificationStatusResponse"));
            } catch (JSONException e) {
                ReleaseLogUtil.c(TAG_RELEASE, "setNotificationStatusResponse JSONException:", ExceptionUtils.d(e));
                sqo.w("setNotificationStatusResponse JSONException: " + e.getMessage());
            }
        }
    }

    public void registerNotificationStatusCallbackList(IBaseResponseCallback iBaseResponseCallback) {
        synchronized (getNotificationStatusCallbackList()) {
            if (iBaseResponseCallback == null) {
                ReleaseLogUtil.d(TAG_RELEASE, "registerNotificationStatusCallbackList callback is null");
                sqo.w("registerNotificationStatusCallbackList callback is null");
            } else {
                if (!sNotificationStatusCallbackList.isEmpty()) {
                    if (!sNotificationStatusCallbackList.contains(iBaseResponseCallback)) {
                        sNotificationStatusCallbackList.add(iBaseResponseCallback);
                    }
                } else {
                    sNotificationStatusCallbackList.add(iBaseResponseCallback);
                }
            }
        }
    }

    public void getOperator(IBaseResponseCallback iBaseResponseCallback) {
        synchronized (SYNC_LOCK) {
            ReleaseLogUtil.e(TAG_RELEASE, "getOperator called");
            if (this.mHwDeviceMgr == null) {
                ReleaseLogUtil.d(TAG_RELEASE, "mHwDeviceMgr is null");
                iBaseResponseCallback.d(0, kkm.d(100001, "getOperator"));
                sqo.w("mHwDeviceMgr is null");
                return;
            }
            DeviceInfo connectedDeviceWithoutAw70 = getConnectedDeviceWithoutAw70();
            if (connectedDeviceWithoutAw70 == null) {
                ReleaseLogUtil.d(TAG_RELEASE, "no device is connected.");
                iBaseResponseCallback.d(0, kkm.d(100001, "getOperator"));
                sqo.w("no device is connected");
            } else {
                synchronized (getGetOperatorCallbackList()) {
                    sOperatorCallbackList.add(iBaseResponseCallback);
                }
                this.mHwDeviceMgr.sendDeviceData(HwExerciseCommandUtil.getOperatorStatus(connectedDeviceWithoutAw70));
            }
        }
    }

    public void workoutOperateRealtimeData(JSONObject jSONObject, IBaseResponseCallback iBaseResponseCallback) throws JSONException {
        synchronized (SYNC_LOCK) {
            ReleaseLogUtil.e(TAG_RELEASE, "workoutOperateRealtimeData enter.");
            DeviceCommand deviceCommand = new DeviceCommand();
            deviceCommand.setServiceID(23);
            deviceCommand.setCommandID(11);
            StringBuffer stringBuffer = new StringBuffer(16);
            HwWorkoutServiceUtils.getWorkoutStringTlvIncludeDistance(stringBuffer, jSONObject);
            HwWorkoutServiceUtils.getWorkoutStringTlvIncludeAvgPace(stringBuffer, jSONObject);
            HwWorkoutServiceUtils.getWorkoutStringTlvIncludeAerobicTrainEffect(stringBuffer, jSONObject);
            HwWorkoutServiceUtils.getWorkoutStringTlvIncludeRunningAction(stringBuffer, jSONObject);
            HwWorkoutServiceUtils.getWorkoutFifthPartStringTlvIncludeRunningCourse(stringBuffer, jSONObject);
            HwWorkoutServiceUtils.getWorkoutStringTlvIncludeRunningCourseContent(stringBuffer, jSONObject);
            DeviceCapability capability = getCapability();
            if (capability != null && capability.isSupportWorkoutCapabilicy()) {
                HwWorkoutServiceUtils.getWorkoutStringTlvIncludeNewSpeed(stringBuffer, jSONObject);
            }
            HwWorkoutServiceUtils.getWorkoutStringTlvIncludeEquipmentLinkage(stringBuffer, jSONObject);
            stringBuffer.insert(0, cvx.e(129) + cvx.d(stringBuffer.length() / 2));
            deviceCommand.setDataLen(stringBuffer.length() / 2);
            deviceCommand.setDataContent(cvx.a(stringBuffer.toString()));
            LogUtil.a(TAG, "5.23.11 setRealTimeData enter: ", deviceCommand.toString());
            synchronized (getWorkoutOperatorRealtimeDataCallbackList()) {
                sWorkoutOperatorRealtimeDataCallbackList.add(iBaseResponseCallback);
            }
            this.mHwDeviceMgr.sendDeviceData(deviceCommand);
        }
    }

    public void notificationWorkoutRecordSpeechPlayReportStatus(JSONObject jSONObject, IBaseResponseCallback iBaseResponseCallback) {
        synchronized (SYNC_LOCK) {
            ReleaseLogUtil.e(TAG_RELEASE, "enter notificationWorkoutRecordSpeechPlayReportStatus");
            if (jSONObject != null) {
                if (jSONObject.optInt("result") == 0) {
                    this.mHwDeviceMgr.sendDeviceData(HwExerciseCommandUtil.getWorkoutRecordSpeechPlayReportCommand());
                }
            } else {
                ReleaseLogUtil.d(TAG_RELEASE, "notificationWorkoutRecordSpeechPlayReportStatus parameters is null.");
                sqo.w("notificationWorkoutRecordSpeechPlayReportStatus parameters is null.");
            }
        }
    }

    public void registerNotificationGetWorkoutRecordStatisticCallbackList(IBaseResponseCallback iBaseResponseCallback) {
        synchronized (getNotificationGetWorkoutRecordStatisticCallbackList()) {
            if (iBaseResponseCallback == null) {
                ReleaseLogUtil.d(TAG_RELEASE, "registerNotificationGetWorkoutRecordStatisticCallbackList callback is null");
                sqo.w("callback is null.");
            } else {
                if (!sNotificationGetWorkoutRecordStatisticCallbackList.isEmpty()) {
                    if (!sNotificationGetWorkoutRecordStatisticCallbackList.contains(iBaseResponseCallback)) {
                        sNotificationGetWorkoutRecordStatisticCallbackList.add(iBaseResponseCallback);
                    }
                } else {
                    sNotificationGetWorkoutRecordStatisticCallbackList.add(iBaseResponseCallback);
                }
            }
        }
    }

    public void registerNotificationWorkoutRecordSpeechPlayCallbackList(IBaseResponseCallback iBaseResponseCallback) {
        synchronized (getNotificationWorkoutRecordSpeechPlayCallbackList()) {
            if (iBaseResponseCallback == null) {
                ReleaseLogUtil.d(TAG_RELEASE, "registerNotificationWorkoutRecordSpeechPlayCallbackList callback is null");
                sqo.w("registerNotificationWorkoutRecordSpeechPlayCallbackList callback is null.");
            } else {
                if (!sNotificationWorkoutRecordSpeechPlayCallbackList.isEmpty()) {
                    if (!sNotificationWorkoutRecordSpeechPlayCallbackList.contains(iBaseResponseCallback)) {
                        sNotificationWorkoutRecordSpeechPlayCallbackList.add(iBaseResponseCallback);
                    }
                } else {
                    sNotificationWorkoutRecordSpeechPlayCallbackList.add(iBaseResponseCallback);
                }
            }
        }
    }

    public void registerGetRealtimeSportDataListCallbackList(IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            ReleaseLogUtil.d(TAG_RELEASE, "registerGetRealtimeSportDataListCallbackList callback is null");
            sqo.w("registerGetRealtimeSportDataListCallbackList callback is null.");
        } else {
            this.mRealTimeSportDataListCallback = iBaseResponseCallback;
        }
    }

    private static List<IBaseResponseCallback> getNotificationOperatorCallbackList() {
        List<IBaseResponseCallback> list;
        synchronized (HwLinkageServiceManager.class) {
            list = sNotificationOperatorCallbackList;
        }
        return list;
    }

    private static List<IBaseResponseCallback> getGetOperatorCallbackList() {
        List<IBaseResponseCallback> list;
        synchronized (HwLinkageServiceManager.class) {
            list = sOperatorCallbackList;
        }
        return list;
    }

    private static List<IBaseResponseCallback> getNotificationStatusCallbackList() {
        List<IBaseResponseCallback> list;
        synchronized (HwLinkageServiceManager.class) {
            list = sNotificationStatusCallbackList;
        }
        return list;
    }

    private static List<IBaseResponseCallback> getNotificationGetWorkoutRecordStatisticCallbackList() {
        List<IBaseResponseCallback> list;
        synchronized (HwLinkageServiceManager.class) {
            list = sNotificationGetWorkoutRecordStatisticCallbackList;
        }
        return list;
    }

    private static List<IBaseResponseCallback> getNotificationWorkoutRecordSpeechPlayCallbackList() {
        return sNotificationWorkoutRecordSpeechPlayCallbackList;
    }

    private static List<IBaseResponseCallback> getWorkoutOperatorRealtimeDataCallbackList() {
        return sWorkoutOperatorRealtimeDataCallbackList;
    }

    private void handleNotificationOperatorStatus(List<cwd> list) {
        int a2 = CommonUtil.a(list.get(0).c(), 16);
        synchronized (getNotificationOperatorCallbackList()) {
            ReleaseLogUtil.e(TAG_RELEASE, "handleNotificationOperatorStatus info:", Integer.valueOf(a2));
            if (!sNotificationOperatorCallbackList.isEmpty()) {
                sNotificationOperatorCallbackList.get(0).d(a2, kkm.d(Integer.valueOf(a2), "setOperator"));
                sNotificationOperatorCallbackList.remove(0);
            }
        }
    }

    private void handleOperatorStatus(List<cwe> list) {
        OperatorStatus operatorStatus = new OperatorStatus();
        Iterator<cwe> it = list.iterator();
        while (it.hasNext()) {
            Iterator<cwd> it2 = it.next().e().iterator();
            while (it2.hasNext()) {
                handleOperatorStatusTlvData(operatorStatus, it2.next());
            }
        }
        ReleaseLogUtil.e(TAG_RELEASE, "handleOperatorStatus OperatorType:", Integer.valueOf(operatorStatus.getOperatorType()));
        synchronized (getNotificationStatusCallbackList()) {
            Iterator<IBaseResponseCallback> it3 = sNotificationStatusCallbackList.iterator();
            while (it3.hasNext()) {
                it3.next().d(100000, kkm.d(operatorStatus, "notificationStatus"));
            }
        }
    }

    private void handleOperatorStatusTlvData(OperatorStatus operatorStatus, cwd cwdVar) {
        int a2 = CommonUtil.a(cwdVar.e(), 16);
        if (a2 == 2) {
            operatorStatus.setOperatorType(Integer.parseInt(cwdVar.c(), 16));
            return;
        }
        if (a2 == 3) {
            operatorStatus.setSportType(Integer.parseInt(cwdVar.c(), 16));
            return;
        }
        if (a2 == 4) {
            operatorStatus.setRunPlanDate(Long.parseLong(cwdVar.c(), 16) * 1000);
            return;
        }
        if (a2 != 5) {
            if (a2 == 6) {
                operatorStatus.setOperationTime(Long.parseLong(cwdVar.c(), 16));
                return;
            } else {
                ReleaseLogUtil.d(TAG_RELEASE, "dealOperatorStatus, not support the tlv type");
                return;
            }
        }
        int parseInt = Integer.parseInt(cwdVar.c(), 16);
        if (parseInt == 255) {
            parseInt = OldToNewMotionPath.SPORT_TYPE_OTHER_SPORT;
        }
        operatorStatus.setWorkoutType(parseInt);
    }

    private void handleGetOperatorStatus(List<cwd> list, List<cwe> list2) {
        if (list != null && !list.isEmpty() && CommonUtil.w(list.get(0).e()) == 127) {
            synchronized (getGetOperatorCallbackList()) {
                int w = CommonUtil.w(list.get(0).c());
                ReleaseLogUtil.d(TAG_RELEASE, "handleGetOperatorStatus errorCode:", Integer.valueOf(w));
                if (!sOperatorCallbackList.isEmpty()) {
                    sOperatorCallbackList.get(0).d(w, kkm.d(Integer.valueOf(w), "getOperator"));
                    sOperatorCallbackList.remove(0);
                }
            }
            return;
        }
        OperatorStatus operatorStatus = new OperatorStatus();
        Iterator<cwe> it = list2.iterator();
        while (it.hasNext()) {
            handleGetOperatorStatusTlvData(operatorStatus, it.next());
        }
        ReleaseLogUtil.e(TAG_RELEASE, "handleGetOperatorStatus OperatorType:", Integer.valueOf(operatorStatus.getOperatorType()), ", TrainMonitorState:", Integer.valueOf(operatorStatus.getTrainMonitorState()));
        synchronized (getGetOperatorCallbackList()) {
            handleGetOperatorStatusResponse(operatorStatus);
        }
    }

    private void handleGetOperatorStatusTlvData(OperatorStatus operatorStatus, cwe cweVar) {
        for (cwd cwdVar : cweVar.e()) {
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
                ReleaseLogUtil.d(TAG_RELEASE, "handleGetOperatorStatusTlvData else.");
            }
        }
    }

    private void handleGetOperatorStatusResponse(OperatorStatus operatorStatus) {
        if (sOperatorCallbackList.isEmpty()) {
            return;
        }
        sOperatorCallbackList.get(0).d(100000, kkm.d(operatorStatus, "getOperator"));
        sOperatorCallbackList.remove(0);
    }

    private void handleNotificationWorkoutRecordStatistic(List<cwe> list) {
        WorkoutRecordStatistic workoutRecordStatistic = new WorkoutRecordStatistic();
        Iterator<cwe> it = list.iterator();
        while (it.hasNext()) {
            handleNotificationWorkoutStatisticDataStruct(it.next().e(), workoutRecordStatistic);
        }
        synchronized (getNotificationGetWorkoutRecordStatisticCallbackList()) {
            Iterator<IBaseResponseCallback> it2 = sNotificationGetWorkoutRecordStatisticCallbackList.iterator();
            while (it2.hasNext()) {
                it2.next().d(100000, kkm.d(workoutRecordStatistic, "notificationGetWorkoutRecordStatistic"));
            }
        }
    }

    private void handleNotificationWorkoutStatisticDataStruct(List<cwd> list, WorkoutRecordStatistic workoutRecordStatistic) {
        for (cwd cwdVar : list) {
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
                case 9:
                    workoutRecordStatistic.setWorkoutRecordTotalTime(CommonUtil.y(cwdVar.c()));
                    break;
                case 10:
                    workoutRecordStatistic.setWorkoutRecordSpeed(CommonUtil.w(cwdVar.c()));
                    break;
                default:
                    handleNotificationWorkoutStatisticDataStructElse(cwdVar, workoutRecordStatistic);
                    break;
            }
        }
    }

    private void handleNotificationWorkoutStatisticDataStructElse(cwd cwdVar, WorkoutRecordStatistic workoutRecordStatistic) {
        switch (CommonUtil.w(cwdVar.e())) {
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
            case 18:
                workoutRecordStatistic.setWorkoutExerciseDuration(CommonUtil.y(cwdVar.c()));
                break;
            case 19:
                workoutRecordStatistic.setWorkoutDateInfo(CommonUtil.y(cwdVar.c()));
                break;
            case 20:
                workoutRecordStatistic.setWorkoutType(CommonUtil.w(cwdVar.c()));
                break;
            case 21:
                workoutRecordStatistic.setAlgType(CommonUtil.w(cwdVar.c()));
                break;
            default:
                ReleaseLogUtil.d(TAG_RELEASE, "handleNotificationWorkoutStatisticDataStructElse tlvChild parse else");
                break;
        }
    }

    private void handleWorkoutOperateRealTimeData(List<cwd> list) {
        synchronized (getWorkoutOperatorRealtimeDataCallbackList()) {
            if (!sWorkoutOperatorRealtimeDataCallbackList.isEmpty()) {
                int w = CommonUtil.w(list.get(0).c());
                ReleaseLogUtil.e(TAG_RELEASE, "handleWorkoutOperateRealTimeData errorCode:", Integer.valueOf(w));
                sWorkoutOperatorRealtimeDataCallbackList.get(0).d(w, kkm.d(Integer.valueOf(w), "workoutOperateRealtimeData"));
                sWorkoutOperatorRealtimeDataCallbackList.remove(0);
            }
        }
    }

    private void handleNotificationWorkoutSpeechPlay(List<cwd> list) {
        WorkoutRecordSpeechPlay workoutRecordSpeechPlay = new WorkoutRecordSpeechPlay();
        for (cwd cwdVar : list) {
            if (CommonUtil.w(cwdVar.e()) == 1) {
                workoutRecordSpeechPlay.setWorkoutRecordSpeechPlayRequest(CommonUtil.w(cwdVar.c()));
            }
        }
        synchronized (getNotificationWorkoutRecordSpeechPlayCallbackList()) {
            Iterator<IBaseResponseCallback> it = sNotificationWorkoutRecordSpeechPlayCallbackList.iterator();
            while (it.hasNext()) {
                it.next().d(100000, kkm.d(workoutRecordSpeechPlay, "notificationWorkoutRecordSpeechPlay"));
            }
        }
    }

    private void handleWorkoutReportPlayData(List<cwe> list, DeviceInfo deviceInfo) {
        ReleaseLogUtil.e(TAG_RELEASE, "handleWorkoutReportPlayData enter.");
        kon konVar = new kon();
        if (deviceInfo != null) {
            konVar.c(deviceInfo.getSecurityUuid() + "#ANDROID21");
        }
        Iterator<cwe> it = list.iterator();
        while (it.hasNext()) {
            HwWorkoutServiceUtils.parseWorkoutReportPlayData(it.next().e(), konVar);
        }
        LogUtil.a("5.23.17 data: ", konVar.toString());
        IBaseResponseCallback iBaseResponseCallback = this.mRealTimeSportDataListCallback;
        if (iBaseResponseCallback != null) {
            iBaseResponseCallback.d(100000, kkm.d(konVar, "registerGetRTSportDataListCallbackList"));
        }
    }

    private DeviceInfo getConnectedDeviceWithoutAw70() {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, TAG);
        if (deviceList == null || deviceList.isEmpty()) {
            ReleaseLogUtil.d(TAG_RELEASE, "getConnectedDeviceWithoutAw70 deviceInfos is null or is empty.");
            sqo.w("getConnectedDeviceWithoutAw70 deviceInfos is null or is empty.");
            return null;
        }
        for (DeviceInfo deviceInfo : deviceList) {
            if (!cvt.c(deviceInfo.getProductType())) {
                return deviceInfo;
            }
        }
        return null;
    }

    private DeviceCapability getCapability() {
        Map<String, DeviceCapability> queryDeviceCapability = jsz.b(BaseApplication.getContext()).queryDeviceCapability(4, "", TAG);
        DeviceCapability deviceCapability = null;
        if (queryDeviceCapability == null || queryDeviceCapability.isEmpty()) {
            ReleaseLogUtil.d(TAG_RELEASE, "getCapability deviceCapabilityHashMaps is null");
            sqo.w("getCapability deviceCapabilityHashMaps is null");
            return null;
        }
        Iterator<Map.Entry<String, DeviceCapability>> it = queryDeviceCapability.entrySet().iterator();
        while (it.hasNext() && (deviceCapability = it.next().getValue()) == null) {
        }
        return deviceCapability;
    }
}
