package com.huawei.hwdevice.phoneprocess.mgr.exercise;

import android.content.Context;
import android.os.Bundle;
import com.huawei.datatype.DeviceCommand;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealth.dictionary.HiHealthDictManager;
import com.huawei.hihealth.dictionary.model.HiHealthDictField;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.binder.ParserInterface;
import com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.dmsmanager.HwDeviceMgrInterface;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.CommonSectionInfo;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.CommonSectionList;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.DataHeader;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.PaceIndexStruct;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.SectionInfo;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.SectionList;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.SportReminder;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.StatisticExtendDataStruct;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.WorkRecordIndexPaceMapList;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.WorkoutDataInfo;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.WorkoutDataStruct;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.WorkoutRealTimeInfo;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.WorkoutRecord;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.WorkoutRecordPaceMap;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.WorkoutRecordStatistic;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.WorkoutRecordStruct;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.multisync.ExtrasDataUtils;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.utils.HwExerciseCommandUtil;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.utils.HwWorkoutServiceUtils;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.utils.TriathlonUtils;
import com.huawei.hwfoundationmodel.trackmodel.HeartRateData;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.blq;
import defpackage.bmq;
import defpackage.bmu;
import defpackage.cvx;
import defpackage.cwd;
import defpackage.cwe;
import defpackage.cwg;
import defpackage.jsz;
import defpackage.juc;
import defpackage.kdb;
import defpackage.kkm;
import defpackage.sqo;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class HwWorkoutServiceManager implements ParserInterface {
    private static final int DECIMAL_NUM = 10;
    private static final int DEFAULT_VALUE = -1;
    private static final int FIRST_CALLBACK = 0;
    private static final int HALF_LENGTH = 2;
    private static final int MILLISECONDS_IN_SECOND = 1000;
    private static final long MILLISECONDS_TO_SECOND = 1000;
    private static final int RECOVERY_HEART_INTERVAL_TIME = 5000;
    private static final int RECOVERY_HEART_LENGTH = 2;
    private static final int SERVICE_AND_COMMAND_LENGTH = 4;
    private static final String TAG = "HwWorkoutServiceManager";
    private static final String TAG_RELEASE = "BTSYNC_HwWorkoutServiceManager";
    private static HwWorkoutServiceManager sInstance;
    private Context mContext;
    private HwDeviceMgrInterface mHwDeviceMgr;
    private IBaseResponseCallback mSectionListCallback;
    private TriathlonUtils mTriathlonUtils;
    private IBaseResponseCallback mWorkoutCapabilityCallback;
    private IBaseResponseCallback mWorkoutDataCallback;
    private IBaseResponseCallback mWorkoutRecordCallback;
    private IBaseResponseCallback mWorkoutRecordPaceMapListCallback;
    private IBaseResponseCallback mWorkoutRecordStatisticCallback;
    private static final Object SYNC_LOCK = new Object();
    private static final Object COMMAND_CALLBACK_LOCK = new Object();
    private static List<IBaseResponseCallback> sWorkoutRealTimeInfoCallbackList = new ArrayList(16);
    private static List<IBaseResponseCallback> sNotificationWorkoutRealTimeInfoCallbackList = new ArrayList(16);
    private static List<IBaseResponseCallback> sNotificationSportReminderCallbackList = new ArrayList(16);
    private Map<Integer, IBaseResponseCallback> mCommandCallbackMap = new HashMap(16);
    private boolean mIsSupportNewStep = false;
    private boolean mIsSupportNewEllipticalAndRowingMachine = false;

    private HwWorkoutServiceManager(Context context) {
        LogUtil.a(TAG, "HwWorkoutServiceManager construct.");
        this.mContext = context;
        this.mHwDeviceMgr = jsz.b(context);
        this.mTriathlonUtils = TriathlonUtils.getInstance();
    }

    public boolean isSupportNewStep() {
        return this.mIsSupportNewStep;
    }

    public void setIsSupportNewStep(boolean z) {
        this.mIsSupportNewStep = z;
    }

    public boolean getIsSupportNewEllipticalAndRowingMachine() {
        return this.mIsSupportNewEllipticalAndRowingMachine;
    }

    public static HwWorkoutServiceManager getInstance() {
        HwWorkoutServiceManager hwWorkoutServiceManager;
        synchronized (SYNC_LOCK) {
            if (sInstance == null) {
                sInstance = new HwWorkoutServiceManager(BaseApplication.getContext());
            }
            hwWorkoutServiceManager = sInstance;
        }
        return hwWorkoutServiceManager;
    }

    public void getWorkoutRealTimeInfo(JSONObject jSONObject, IBaseResponseCallback iBaseResponseCallback) throws JSONException {
        synchronized (SYNC_LOCK) {
            synchronized (getWorkoutRealTimeInfoCallbackList()) {
                sWorkoutRealTimeInfoCallbackList.add(iBaseResponseCallback);
            }
            this.mHwDeviceMgr.sendDeviceData(HwExerciseCommandUtil.getWorkoutRealtimeInfo(jSONObject));
        }
    }

    public void getWorkoutRecord(JSONObject jSONObject, IBaseResponseCallback iBaseResponseCallback) throws JSONException {
        synchronized (SYNC_LOCK) {
            ReleaseLogUtil.e(TAG_RELEASE, "getWorkoutRecord enter.");
            this.mWorkoutRecordCallback = iBaseResponseCallback;
            this.mHwDeviceMgr.sendDeviceData(HwExerciseCommandUtil.getWorkoutRecordCommand(jSONObject));
        }
    }

    public void getWorkoutRecordStatistic(JSONObject jSONObject, IBaseResponseCallback iBaseResponseCallback) throws JSONException {
        synchronized (SYNC_LOCK) {
            ReleaseLogUtil.e(TAG_RELEASE, "getWorkoutRecordStatistic enter.");
            this.mWorkoutRecordStatisticCallback = iBaseResponseCallback;
            this.mHwDeviceMgr.sendDeviceData(HwExerciseCommandUtil.getWorkoutRecordStatistic(jSONObject));
        }
    }

    public void getWorkoutData(JSONObject jSONObject, IBaseResponseCallback iBaseResponseCallback) throws JSONException {
        synchronized (SYNC_LOCK) {
            ReleaseLogUtil.e(TAG_RELEASE, "getWorkoutData enter.");
            this.mWorkoutDataCallback = iBaseResponseCallback;
            this.mHwDeviceMgr.sendDeviceData(HwExerciseCommandUtil.getWorkoutData(jSONObject));
        }
    }

    public void getWorkoutCapability(IBaseResponseCallback iBaseResponseCallback) {
        synchronized (SYNC_LOCK) {
            this.mIsSupportNewStep = false;
            this.mIsSupportNewEllipticalAndRowingMachine = false;
            ReleaseLogUtil.e(TAG_RELEASE, "getWorkoutCapability enter.");
            this.mWorkoutCapabilityCallback = iBaseResponseCallback;
            this.mHwDeviceMgr.sendDeviceData(HwExerciseCommandUtil.getWorkoutCapability());
        }
    }

    public void getSectionListStatistic(JSONObject jSONObject, IBaseResponseCallback iBaseResponseCallback) throws JSONException {
        synchronized (SYNC_LOCK) {
            ReleaseLogUtil.e(TAG_RELEASE, "getSectionListStatistic enter.");
            this.mSectionListCallback = iBaseResponseCallback;
            this.mHwDeviceMgr.sendDeviceData(HwExerciseCommandUtil.getSectionListDeviceCommand(jSONObject));
        }
    }

    public void getCommonSectionListStatistic(CommonSectionList commonSectionList, IBaseResponseCallback iBaseResponseCallback) {
        ReleaseLogUtil.e(TAG_RELEASE, "getCommonSectionListStatistic enter");
        synchronized (SYNC_LOCK) {
            if (commonSectionList == null) {
                ReleaseLogUtil.d(TAG_RELEASE, "getCommonSectionListStatistic, input parameters is invalid");
                return;
            }
            DeviceCommand commonSectionListCommand = HwExerciseCommandUtil.getCommonSectionListCommand(commonSectionList);
            LogUtil.a(TAG, "getCommonSectionListStatistic command data is ", cvx.d(commonSectionListCommand.getDataContent()));
            addCommandToMap(22, iBaseResponseCallback);
            this.mHwDeviceMgr.sendDeviceData(commonSectionListCommand);
        }
    }

    public void registerNotificationSportReminderCallbackList(IBaseResponseCallback iBaseResponseCallback) {
        synchronized (getNotificationSportReminderCallbackList()) {
            if (iBaseResponseCallback == null) {
                ReleaseLogUtil.d(TAG_RELEASE, "registerNotificationSportReminderCallbackList callback is null");
                return;
            }
            if (!sNotificationSportReminderCallbackList.isEmpty()) {
                if (!sNotificationSportReminderCallbackList.contains(iBaseResponseCallback)) {
                    sNotificationSportReminderCallbackList.add(iBaseResponseCallback);
                }
            } else {
                sNotificationSportReminderCallbackList.add(iBaseResponseCallback);
            }
        }
    }

    public void getWorkoutRecordPaceMap(PaceIndexStruct paceIndexStruct, IBaseResponseCallback iBaseResponseCallback) {
        synchronized (SYNC_LOCK) {
            if (paceIndexStruct == null || iBaseResponseCallback == null) {
                ReleaseLogUtil.d(TAG_RELEASE, "getWorkoutRecordPaceMap, input parameters is invalid");
                sqo.q("getWorkoutRecordPaceMap, input parameters is invalid");
            } else {
                ReleaseLogUtil.e(TAG_RELEASE, "getWorkoutRecordPaceMap enter.");
                this.mWorkoutRecordPaceMapListCallback = iBaseResponseCallback;
                this.mHwDeviceMgr.sendDeviceData(HwExerciseCommandUtil.getWorkoutRecordPaceMapCommand(paceIndexStruct));
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0092 A[Catch: Exception -> 0x00b0, RuntimeException -> 0x00d2, IndexOutOfBoundsException -> 0x00f6, cwg -> 0x011a, TryCatch #2 {cwg -> 0x011a, IndexOutOfBoundsException -> 0x00f6, RuntimeException -> 0x00d2, Exception -> 0x00b0, blocks: (B:11:0x0039, B:16:0x0059, B:17:0x008d, B:18:0x008f, B:19:0x0092, B:21:0x0097, B:23:0x009c, B:25:0x00a1, B:27:0x00a6, B:29:0x00ab, B:31:0x0073), top: B:10:0x0039 }] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0097 A[Catch: Exception -> 0x00b0, RuntimeException -> 0x00d2, IndexOutOfBoundsException -> 0x00f6, cwg -> 0x011a, TryCatch #2 {cwg -> 0x011a, IndexOutOfBoundsException -> 0x00f6, RuntimeException -> 0x00d2, Exception -> 0x00b0, blocks: (B:11:0x0039, B:16:0x0059, B:17:0x008d, B:18:0x008f, B:19:0x0092, B:21:0x0097, B:23:0x009c, B:25:0x00a1, B:27:0x00a6, B:29:0x00ab, B:31:0x0073), top: B:10:0x0039 }] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x009c A[Catch: Exception -> 0x00b0, RuntimeException -> 0x00d2, IndexOutOfBoundsException -> 0x00f6, cwg -> 0x011a, TryCatch #2 {cwg -> 0x011a, IndexOutOfBoundsException -> 0x00f6, RuntimeException -> 0x00d2, Exception -> 0x00b0, blocks: (B:11:0x0039, B:16:0x0059, B:17:0x008d, B:18:0x008f, B:19:0x0092, B:21:0x0097, B:23:0x009c, B:25:0x00a1, B:27:0x00a6, B:29:0x00ab, B:31:0x0073), top: B:10:0x0039 }] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00a1 A[Catch: Exception -> 0x00b0, RuntimeException -> 0x00d2, IndexOutOfBoundsException -> 0x00f6, cwg -> 0x011a, TryCatch #2 {cwg -> 0x011a, IndexOutOfBoundsException -> 0x00f6, RuntimeException -> 0x00d2, Exception -> 0x00b0, blocks: (B:11:0x0039, B:16:0x0059, B:17:0x008d, B:18:0x008f, B:19:0x0092, B:21:0x0097, B:23:0x009c, B:25:0x00a1, B:27:0x00a6, B:29:0x00ab, B:31:0x0073), top: B:10:0x0039 }] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00a6 A[Catch: Exception -> 0x00b0, RuntimeException -> 0x00d2, IndexOutOfBoundsException -> 0x00f6, cwg -> 0x011a, TryCatch #2 {cwg -> 0x011a, IndexOutOfBoundsException -> 0x00f6, RuntimeException -> 0x00d2, Exception -> 0x00b0, blocks: (B:11:0x0039, B:16:0x0059, B:17:0x008d, B:18:0x008f, B:19:0x0092, B:21:0x0097, B:23:0x009c, B:25:0x00a1, B:27:0x00a6, B:29:0x00ab, B:31:0x0073), top: B:10:0x0039 }] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00ab A[Catch: Exception -> 0x00b0, RuntimeException -> 0x00d2, IndexOutOfBoundsException -> 0x00f6, cwg -> 0x011a, TRY_LEAVE, TryCatch #2 {cwg -> 0x011a, IndexOutOfBoundsException -> 0x00f6, RuntimeException -> 0x00d2, Exception -> 0x00b0, blocks: (B:11:0x0039, B:16:0x0059, B:17:0x008d, B:18:0x008f, B:19:0x0092, B:21:0x0097, B:23:0x009c, B:25:0x00a1, B:27:0x00a6, B:29:0x00ab, B:31:0x0073), top: B:10:0x0039 }] */
    @Override // com.huawei.hwdevice.phoneprocess.binder.ParserInterface
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void getResult(com.huawei.health.devicemgr.business.entity.DeviceInfo r11, byte[] r12) {
        /*
            Method dump skipped, instructions count: 332
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hwdevice.phoneprocess.mgr.exercise.HwWorkoutServiceManager.getResult(com.huawei.health.devicemgr.business.entity.DeviceInfo, byte[]):void");
    }

    private void handleResultWorkoutRecord(byte[] bArr, List<cwd> list, List<cwe> list2, DeviceInfo deviceInfo) {
        byte b = bArr[1];
        if (b == 10) {
            handleGetWorkoutData(list, list2);
        }
        if (b == 12) {
            handleWorkoutRecordPaceMap(list, list2);
            return;
        }
        if (b == 14) {
            handleWorkoutRecordSwimSectionList(list2);
            return;
        }
        switch (b) {
            case 20:
                juc.b().b(list, list2);
                break;
            case 21:
                handleWorkoutCapability(bArr, list);
                break;
            case 22:
                handleCommonSectionList(list, list2);
                break;
            default:
                HwLinkageServiceManager.getInstance().getResult(deviceInfo, bArr);
                break;
        }
    }

    private void handleGetWorkoutRealTimeInfo(List<cwd> list, List<cwe> list2) {
        if (!list.isEmpty() && CommonUtil.w(list.get(0).e()) == 127) {
            synchronized (getWorkoutRealTimeInfoCallbackList()) {
                int w = CommonUtil.w(list.get(0).c());
                if (!sWorkoutRealTimeInfoCallbackList.isEmpty()) {
                    sWorkoutRealTimeInfoCallbackList.get(0).d(w, kkm.d(Integer.valueOf(w), "getWorkoutRealTimeInfo"));
                    sWorkoutRealTimeInfoCallbackList.remove(0);
                }
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
                        LogUtil.c(TAG, "handleGetWorkoutRealTimeInfo else");
                        break;
                }
            }
        }
        synchronized (getWorkoutRealTimeInfoCallbackList()) {
            handleGetWorkoutRealTimeInfoResponse(workoutRealTimeInfo);
        }
    }

    private void handleGetWorkoutRealTimeInfoResponse(WorkoutRealTimeInfo workoutRealTimeInfo) {
        if (sWorkoutRealTimeInfoCallbackList.isEmpty()) {
            return;
        }
        sWorkoutRealTimeInfoCallbackList.get(0).d(100000, kkm.d(workoutRealTimeInfo, "getWorkoutRealTimeInfo"));
        sWorkoutRealTimeInfoCallbackList.remove(0);
    }

    private void handleNotificationWorkoutRealTimeInfo(List<cwe> list) {
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
                        LogUtil.c(TAG, "handleNotificationWorkoutRealTimeInfo else");
                        break;
                }
            }
        }
        synchronized (getNotificationWorkoutRealTimeInfoCallbackList()) {
            Iterator<IBaseResponseCallback> it2 = sNotificationWorkoutRealTimeInfoCallbackList.iterator();
            while (it2.hasNext()) {
                it2.next().d(100000, kkm.d(workoutRealTimeInfo, "notificationWorkoutRealTimeInfo"));
            }
        }
    }

    private void handleNotificationSportReminder(List<cwe> list) {
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
                        LogUtil.c(TAG, "handleNotificationSportReminder else");
                        break;
                }
            }
        }
        synchronized (getNotificationSportReminderCallbackList()) {
            Iterator<IBaseResponseCallback> it2 = sNotificationSportReminderCallbackList.iterator();
            while (it2.hasNext()) {
                it2.next().d(100000, kkm.d(sportReminder, "notificationSportReminder"));
            }
        }
    }

    private void handleWorkoutRecord(List<bmu> list, List<bmq> list2) {
        if (handleWorkoutRecordError(list)) {
            return;
        }
        WorkoutRecord workoutRecord = new WorkoutRecord();
        ArrayList arrayList = new ArrayList(16);
        for (bmq bmqVar : list2) {
            for (bmu bmuVar : bmqVar.d()) {
                if (bmuVar.a() == 2) {
                    int c = cvx.c(bmuVar.c(), 0);
                    workoutRecord.setWorkoutRecordCount(c);
                    ReleaseLogUtil.e(TAG_RELEASE, "need sync record total count is :", Integer.valueOf(c));
                } else {
                    LogUtil.c(TAG, "handleWorkoutRecord tlv parse else");
                }
            }
            Iterator<bmq> it = bmqVar.a().iterator();
            while (it.hasNext()) {
                List<bmu> d = it.next().d();
                WorkoutRecordStruct workoutRecordStruct = new WorkoutRecordStruct();
                handleWorkoutRecordDataStruct(d, workoutRecordStruct);
                arrayList.add(workoutRecordStruct);
            }
        }
        workoutRecord.setWorkoutRecordStructList(arrayList);
        if (!arrayList.isEmpty()) {
            this.mTriathlonUtils.saveLastRecordId(arrayList.get(arrayList.size() - 1).getWorkoutRecordId());
        }
        IBaseResponseCallback iBaseResponseCallback = this.mWorkoutRecordCallback;
        if (iBaseResponseCallback != null) {
            iBaseResponseCallback.d(100000, kkm.d(workoutRecord, "getWorkoutRecord"));
        } else {
            ReleaseLogUtil.d(TAG_RELEASE, "handleWorkoutRecord mWorkoutRecordCallback is null.");
        }
    }

    private void handleWorkoutRecordDataStruct(List<bmu> list, WorkoutRecordStruct workoutRecordStruct) {
        for (bmu bmuVar : list) {
            switch (bmuVar.a()) {
                case 6:
                    workoutRecordStruct.setWorkoutRecordId(cvx.c(bmuVar.c(), 0));
                    break;
                case 7:
                    workoutRecordStruct.setWorkoutIndexCount(cvx.c(bmuVar.c(), 0));
                    break;
                case 8:
                    workoutRecordStruct.setPaceIndexCount(cvx.c(bmuVar.c(), -1));
                    break;
                case 9:
                    workoutRecordStruct.setWorkoutSectionIndex(cvx.c(bmuVar.c(), 0));
                    break;
                case 10:
                case 11:
                default:
                    LogUtil.c(TAG, "handleWorkoutRecord tlvChild parse else");
                    break;
                case 12:
                    workoutRecordStruct.setWorkoutBloodOxygenIndex(cvx.c(bmuVar.c(), 0));
                    break;
                case 13:
                    workoutRecordStruct.setWorkoutSectionNumber(cvx.c(bmuVar.c(), 0));
                    break;
                case 14:
                    workoutRecordStruct.setWorkoutTrajectories(cvx.c(bmuVar.c(), -1));
                    break;
                case 15:
                    workoutRecordStruct.setDivingEvent(cvx.c(bmuVar.c(), 0));
                    break;
                case 16:
                    workoutRecordStruct.setDictType(bytesToString(bmuVar.c()));
                    break;
            }
        }
    }

    private boolean handleWorkoutRecordError(List<bmu> list) {
        if (list.isEmpty() || list.get(0).a() != Byte.MAX_VALUE) {
            return false;
        }
        if (this.mWorkoutRecordCallback == null) {
            return true;
        }
        int c = cvx.c(list.get(0).c(), -1);
        this.mWorkoutRecordCallback.d(c, kkm.d(Integer.valueOf(c), "getWorkoutRecord"));
        return true;
    }

    private void handleGetWorkoutRecordStatistic(List<bmu> list, List<bmq> list2, DeviceInfo deviceInfo) {
        if (handleGetWorkoutRecordStatisticError(list)) {
            return;
        }
        WorkoutRecordStatistic workoutRecordStatistic = new WorkoutRecordStatistic();
        boolean handleDeviceIsSupportRunPaceConfig = handleDeviceIsSupportRunPaceConfig(deviceInfo);
        ArrayList arrayList = new ArrayList(16);
        for (bmq bmqVar : list2) {
            getStatisticData(bmqVar, workoutRecordStatistic, handleDeviceIsSupportRunPaceConfig);
            for (bmq bmqVar2 : bmqVar.a()) {
                StatisticExtendDataStruct statisticExtendDataStruct = new StatisticExtendDataStruct();
                getStatisticExtendData(bmqVar2.d(), statisticExtendDataStruct);
                arrayList.add(statisticExtendDataStruct);
            }
            workoutRecordStatistic.setExtendList(arrayList);
            HwWorkoutServiceUtils.dealTriathlon(bmqVar, workoutRecordStatistic);
        }
        LogUtil.a(TAG, "handleGetWorkoutRecordStatistic workoutRecordStatistic:", workoutRecordStatistic.toString());
        IBaseResponseCallback iBaseResponseCallback = this.mWorkoutRecordStatisticCallback;
        if (iBaseResponseCallback != null) {
            iBaseResponseCallback.d(100000, kkm.d(workoutRecordStatistic, "getWorkoutRecordStatistic"));
        } else {
            ReleaseLogUtil.d(TAG_RELEASE, "handleGetWorkoutRecordStatistic mWorkoutRecordStatisticCallback is null.");
        }
    }

    private String bytesToString(byte[] bArr) {
        String str;
        try {
            str = new String(bArr, "UTF-8");
            try {
                LogUtil.a(TAG, "bytesToString:", str);
            } catch (UnsupportedEncodingException e) {
                e = e;
                ReleaseLogUtil.c(TAG, "bytesToString UnsupportedEncodingException :", ExceptionUtils.d(e));
                return str;
            }
        } catch (UnsupportedEncodingException e2) {
            e = e2;
            str = "";
        }
        return str;
    }

    private void getStatisticData(bmq bmqVar, WorkoutRecordStatistic workoutRecordStatistic, boolean z) {
        for (bmu bmuVar : bmqVar.d()) {
            byte a2 = bmuVar.a();
            if (a2 == 49) {
                workoutRecordStatistic.setHighestBloodOxygen(cvx.c(bmuVar.c(), 0));
            } else if (a2 == 50) {
                workoutRecordStatistic.setLowestBloodOxygen(cvx.c(bmuVar.c(), 0));
            } else if (a2 == 77) {
                workoutRecordStatistic.setTargetPercent(cvx.c(bmuVar.c(), -1));
            } else if (a2 == 79) {
                workoutRecordStatistic.setExerciseLevel(cvx.c(bmuVar.c(), -1));
            } else if (a2 != 102) {
                switch (a2) {
                    case 35:
                        workoutRecordStatistic.setRecordFlag(cvx.c(bmuVar.c(), 0));
                        break;
                    case 36:
                        workoutRecordStatistic.setWorkoutHeartRateType(cvx.c(bmuVar.c(), -1));
                        break;
                    case 37:
                        workoutRecordStatistic.setWorkoutExerciseId(bytesToString(bmuVar.c()));
                        break;
                    default:
                        switch (a2) {
                            case 91:
                                workoutRecordStatistic.setLongestStreak(cvx.c(bmuVar.c(), -1));
                                break;
                            case 92:
                                workoutRecordStatistic.setTripped(cvx.c(bmuVar.c(), -1));
                                break;
                            case 93:
                                workoutRecordStatistic.setAlgType(cvx.c(bmuVar.c(), 0));
                                break;
                            default:
                                handleWorkoutGolfAndSkiingBasicData(bmuVar, workoutRecordStatistic);
                                break;
                        }
                }
            } else {
                workoutRecordStatistic.setRecoveryHeartRateList(getRecoveryHeartRateData(bmuVar.c(), workoutRecordStatistic.getWorkoutRecordEndTime()));
            }
            if (z) {
                handleWorkoutRecordRunPace(workoutRecordStatistic, bmuVar);
            }
        }
    }

    private void getStatisticExtendData(List<bmu> list, StatisticExtendDataStruct statisticExtendDataStruct) {
        for (bmu bmuVar : list) {
            byte a2 = bmuVar.a();
            if (a2 == 104) {
                int c = cvx.c(bmuVar.c(), 0);
                HiHealthDictField b = HiHealthDictManager.d(this.mContext).b(c);
                String a3 = b != null ? b.a() : "";
                if (a3.isEmpty()) {
                    a3 = DicDataTypeUtil.NonDictDataTransUtil.d(c);
                }
                statisticExtendDataStruct.setFieldName(a3);
            } else if (a2 == 105) {
                statisticExtendDataStruct.setFieldValue(bytesToString(bmuVar.c()));
            } else {
                ReleaseLogUtil.d(TAG_RELEASE, "getStatisticExtendData tlvChild parse else");
            }
        }
    }

    private ArrayList<HeartRateData> getRecoveryHeartRateData(byte[] bArr, long j) {
        ArrayList<HeartRateData> arrayList = new ArrayList<>(16);
        long j2 = j - 5000;
        for (byte b : bArr) {
            HeartRateData heartRateData = new HeartRateData();
            heartRateData.saveHeartRate(cvx.c(new byte[]{b}, 0));
            j2 += 5000;
            heartRateData.saveTime(j2);
            LogUtil.c(TAG, "recovery_heart_rate: ", heartRateData.toString());
            arrayList.add(heartRateData);
        }
        return arrayList;
    }

    private boolean handleDeviceIsSupportRunPaceConfig(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            ReleaseLogUtil.d(TAG_RELEASE, "handleGetWorkoutRecordStatistic deviceInfo is null");
            return false;
        }
        DeviceCapability capability = getCapability(deviceInfo);
        if (capability != null && capability.isSupportRunPaceSetCapability()) {
            return true;
        }
        ReleaseLogUtil.d(TAG_RELEASE, "handleGetWorkoutRecordStatistic isSupportRunPaceCapability is false");
        return false;
    }

    private void handleWorkoutRecordRunPace(WorkoutRecordStatistic workoutRecordStatistic, bmu bmuVar) {
        switch (bmuVar.a()) {
            case 80:
                workoutRecordStatistic.setRompedPaceZoneMinValue(cvx.c(bmuVar.c(), -1));
                break;
            case 81:
                workoutRecordStatistic.setMarathonPaceZoneMinValue(cvx.c(bmuVar.c(), -1));
                break;
            case 82:
                workoutRecordStatistic.setLacticAcidPaceZoneMinValue(cvx.c(bmuVar.c(), -1));
                break;
            case 83:
                workoutRecordStatistic.setAnaerobicPaceZoneMinValue(cvx.c(bmuVar.c(), -1));
                break;
            case 84:
                workoutRecordStatistic.setMaxOxygenPaceZoneMinValue(cvx.c(bmuVar.c(), -1));
                break;
            case 85:
                workoutRecordStatistic.setMaxOxygenPaceZoneMaxValue(cvx.c(bmuVar.c(), -1));
                break;
            case 86:
                workoutRecordStatistic.setRompedTime(cvx.c(bmuVar.c(), -1));
                break;
            case 87:
                workoutRecordStatistic.setMarathonTime(cvx.c(bmuVar.c(), -1));
                break;
            case 88:
                workoutRecordStatistic.setLacticAcidTime(cvx.c(bmuVar.c(), -1));
                break;
            case 89:
                workoutRecordStatistic.setAnaerobicTime(cvx.c(bmuVar.c(), -1));
                break;
            case 90:
                workoutRecordStatistic.setMaxOxygenTime(cvx.c(bmuVar.c(), -1));
                break;
            default:
                LogUtil.c(TAG, "handleWorkoutRecordRunPace tlvChild parse else");
                break;
        }
    }

    private void handleWorkoutGolfAndSkiingBasicData(bmu bmuVar, WorkoutRecordStatistic workoutRecordStatistic) {
        switch (bmuVar.a()) {
            case 62:
                workoutRecordStatistic.setGolfSwingCount(cvx.c(bmuVar.c(), 0));
                break;
            case 63:
                workoutRecordStatistic.setGolfBackSwingTime(cvx.c(bmuVar.c(), 0));
                break;
            case 64:
                workoutRecordStatistic.setGolfDownSwingTime(cvx.c(bmuVar.c(), 0));
                break;
            case 65:
                workoutRecordStatistic.setGolfSwingSpeed(cvx.c(bmuVar.c(), 0));
                break;
            case 66:
                workoutRecordStatistic.setGolfMaxSwingSpeed(cvx.c(bmuVar.c(), 0));
                break;
            case 67:
                workoutRecordStatistic.setGolfSwingTempo(cvx.c(bmuVar.c(), 0));
                break;
            case 68:
                workoutRecordStatistic.setSkiMaxSlopeDegree(cvx.c(bmuVar.c(), -1));
                break;
            case 69:
                workoutRecordStatistic.setSkiMaxSlopePercent(cvx.c(bmuVar.c(), -1));
                break;
            case 70:
                workoutRecordStatistic.setSkiTotalTime(cvx.c(bmuVar.c(), 0) * 1000);
                break;
            case 71:
                workoutRecordStatistic.setSkiTotalDistance(cvx.c(bmuVar.c(), 0));
                break;
            case 72:
                workoutRecordStatistic.setTemperature(bmuVar.c()[0]);
                break;
            case 73:
                workoutRecordStatistic.setWeather(cvx.c(bmuVar.c(), -1));
                break;
            default:
                handleWorkoutRecordStatisticBasicData(bmuVar, workoutRecordStatistic);
                break;
        }
    }

    private void handleWorkoutRecordStatisticBasicData(bmu bmuVar, WorkoutRecordStatistic workoutRecordStatistic) {
        switch (bmuVar.a()) {
            case 2:
                workoutRecordStatistic.setWorkoutRecordId(cvx.c(bmuVar.c(), 0));
                break;
            case 3:
                workoutRecordStatistic.setWorkoutRecordStatus(cvx.c(bmuVar.c(), 0));
                break;
            case 4:
                workoutRecordStatistic.setWorkoutRecordStartTime(blq.e(bmuVar.c(), 0L) * 1000);
                break;
            case 5:
                workoutRecordStatistic.setWorkoutRecordEndTime(blq.e(bmuVar.c(), 0L) * 1000);
                break;
            case 6:
                workoutRecordStatistic.setWorkoutRecordCalorie(cvx.c(bmuVar.c(), 0));
                break;
            case 7:
                workoutRecordStatistic.setWorkoutRecordDistance(cvx.c(bmuVar.c(), 0));
                break;
            case 8:
                workoutRecordStatistic.setWorkoutRecordStep(cvx.c(bmuVar.c(), 0));
                break;
            case 9:
                workoutRecordStatistic.setWorkoutRecordTotalTime(blq.e(bmuVar.c(), 0L));
                break;
            case 10:
                workoutRecordStatistic.setWorkoutRecordSpeed(cvx.c(bmuVar.c(), 0));
                break;
            case 11:
                workoutRecordStatistic.setWorkoutClimb(blq.e(bmuVar.c(), -1L));
                break;
            case 12:
                workoutRecordStatistic.setWorkoutHrPeakMin(cvx.c(new byte[]{bmuVar.c()[0]}, 0));
                workoutRecordStatistic.setWorkoutHrPeakMax(cvx.c(new byte[]{bmuVar.c()[1]}, 0));
                break;
            case 13:
                workoutRecordStatistic.setWorkoutLoadPeak(cvx.c(bmuVar.c(), 0));
                break;
            case 14:
                workoutRecordStatistic.setWorkoutEtrainingEffect(cvx.c(bmuVar.c(), 0));
                break;
            default:
                handleWorkoutRecordStatisticBasicDataElse(bmuVar, workoutRecordStatistic);
                break;
        }
    }

    private void handleWorkoutRecordStatisticBasicDataElse(bmu bmuVar, WorkoutRecordStatistic workoutRecordStatistic) {
        switch (bmuVar.a()) {
            case 15:
                workoutRecordStatistic.setWorkoutEpoc(cvx.c(bmuVar.c(), 0));
                break;
            case 16:
                workoutRecordStatistic.setWorkoutMaxMet(cvx.c(bmuVar.c(), 0));
                break;
            case 17:
                workoutRecordStatistic.setWorkoutRecoveryTime(cvx.c(bmuVar.c(), 0));
                break;
            case 18:
                workoutRecordStatistic.setWorkoutExerciseDuration(blq.e(bmuVar.c(), 0L) * 1000);
                break;
            case 19:
                workoutRecordStatistic.setWorkoutDateInfo(blq.e(bmuVar.c(), 0L));
                break;
            case 20:
                int c = cvx.c(bmuVar.c(), 0);
                workoutRecordStatistic.setWorkoutType(c);
                ReleaseLogUtil.e(TAG_RELEASE, "handleWorkoutRecordStatisticBasicDataElse WorkoutType:", Integer.valueOf(c));
                break;
            case 21:
                workoutRecordStatistic.setSwimType(cvx.c(bmuVar.c(), -1));
                break;
            case 22:
                workoutRecordStatistic.setSwimPullTimes(cvx.c(bmuVar.c(), -1));
                break;
            case 23:
                workoutRecordStatistic.setSwimPullRate(cvx.c(bmuVar.c(), -1));
                break;
            case 24:
                workoutRecordStatistic.setSwimPoolLength(cvx.c(bmuVar.c(), -1));
                break;
            case 25:
                workoutRecordStatistic.setSwimTripTimes(cvx.c(bmuVar.c(), -1));
                break;
            case 26:
                workoutRecordStatistic.setSwimAvgSwolf(cvx.c(bmuVar.c(), -1));
                break;
            case 27:
                workoutRecordStatistic.setAccumulativeDropHeight(cvx.c(bmuVar.c(), -1));
                break;
            default:
                handleWorkoutRecordStatisticBasicSwim(bmuVar, workoutRecordStatistic);
                break;
        }
    }

    private void handleWorkoutRecordStatisticBasicSwim(bmu bmuVar, WorkoutRecordStatistic workoutRecordStatistic) {
        switch (bmuVar.a()) {
            case 28:
                workoutRecordStatistic.setHighestAltitude((int) blq.e(bmuVar.c(), -1L));
                break;
            case 29:
                workoutRecordStatistic.setLowestAltitude((int) blq.e(bmuVar.c(), -1L));
                break;
            case 30:
                workoutRecordStatistic.setSwolfBaseKm(cvx.c(bmuVar.c(), -1));
                break;
            case 31:
                workoutRecordStatistic.setSwolfBaseMile(cvx.c(bmuVar.c(), -1));
                break;
            case 32:
                workoutRecordStatistic.setAnaerobicTrainingEffect(cvx.c(bmuVar.c(), -1));
                break;
            case 33:
                workoutRecordStatistic.setHalfMarathonTime(cvx.c(bmuVar.c(), -1));
                break;
            case 34:
                workoutRecordStatistic.setTotalMarathonTime(cvx.c(bmuVar.c(), -1));
                break;
            default:
                handleWorkoutRecordStatisticCoursePlan(bmuVar, workoutRecordStatistic);
                break;
        }
    }

    private void handleWorkoutRecordStatisticCoursePlan(bmu bmuVar, WorkoutRecordStatistic workoutRecordStatistic) {
        switch (bmuVar.a()) {
            case 94:
                workoutRecordStatistic.setPlanId(bytesToString(bmuVar.c()));
                break;
            case 95:
                workoutRecordStatistic.setCourseName(bytesToString(bmuVar.c()));
                break;
            case 96:
                workoutRecordStatistic.setPlanCourseTime(cvx.c(bmuVar.c(), -1));
                break;
            case 97:
                workoutRecordStatistic.setCourseTargetType(cvx.c(bmuVar.c(), -1));
                break;
            case 98:
                workoutRecordStatistic.setCourseTargetValue(cvx.c(bmuVar.c(), -1));
                break;
            case 99:
                workoutRecordStatistic.setTrainingPoints(cvx.c(bmuVar.c(), -1));
                break;
            case 100:
                workoutRecordStatistic.setTrainingExperience(cvx.c(bmuVar.c(), -1));
                break;
            case 101:
                workoutRecordStatistic.setCourseModifiedTime(cvx.c(bmuVar.c(), -1));
                break;
            default:
                LogUtil.c(TAG, "handleWorkoutRecordStatisticCoursePlan tlvChild parse else");
                break;
        }
    }

    private boolean handleGetWorkoutRecordStatisticError(List<bmu> list) {
        if (list.isEmpty() || list.get(0).a() != Byte.MAX_VALUE) {
            return false;
        }
        if (this.mWorkoutRecordStatisticCallback == null) {
            return true;
        }
        int c = cvx.c(list.get(0).c(), -1);
        this.mWorkoutRecordStatisticCallback.d(c, kkm.d(Integer.valueOf(c), "getWorkoutRecordStatistic"));
        return true;
    }

    private void handleGetWorkoutData(List<cwd> list, List<cwe> list2) {
        if (list != null && !list.isEmpty() && CommonUtil.w(list.get(0).e()) == 127) {
            if (this.mWorkoutDataCallback != null) {
                int w = CommonUtil.w(list.get(0).c());
                this.mWorkoutDataCallback.d(w, kkm.d(Integer.valueOf(w), "getWorkoutData"));
                return;
            }
            return;
        }
        WorkoutDataStruct workoutDataStruct = new WorkoutDataStruct();
        if (list2 != null && !list2.isEmpty()) {
            Iterator<cwe> it = list2.iterator();
            while (it.hasNext()) {
                handleGetWorkoutDataStruct(workoutDataStruct, it.next().e(), new DataHeader(), new ArrayList(16));
            }
        }
        IBaseResponseCallback iBaseResponseCallback = this.mWorkoutDataCallback;
        if (iBaseResponseCallback != null) {
            iBaseResponseCallback.d(100000, kkm.d(workoutDataStruct, "getWorkoutData"));
        } else {
            ReleaseLogUtil.d(TAG_RELEASE, "handleGetWorkoutData mWorkoutDataCallback is null.");
            sqo.q("handleGetWorkoutData mWorkoutDataCallback is null.");
        }
    }

    private void handleGetWorkoutDataStruct(WorkoutDataStruct workoutDataStruct, List<cwd> list, DataHeader dataHeader, List<WorkoutDataInfo> list2) {
        ArrayList arrayList = new ArrayList(16);
        ExtrasDataUtils extrasDataUtils = new ExtrasDataUtils();
        int i = 0;
        String str = "";
        String str2 = null;
        for (cwd cwdVar : list) {
            int w = CommonUtil.w(cwdVar.e());
            if (w == 2) {
                workoutDataStruct.setWorkoutRecordId(CommonUtil.w(cwdVar.c()));
            } else if (w == 3) {
                workoutDataStruct.setWorkoutDataIndex(CommonUtil.w(cwdVar.c()));
            } else if (w == 4) {
                extrasDataUtils.parseHeader(cwdVar.c(), dataHeader);
                handleWorkoutDataHeader(dataHeader, arrayList, cwdVar);
            } else if (w == 5) {
                str2 = cwdVar.c();
            } else if (w == 8) {
                i = CommonUtil.w(cwdVar.c()) * 2;
            } else if (w == 9) {
                str = extrasDataUtils.parseExtraBitmap(cwdVar.c());
            } else {
                LogUtil.c(TAG, "handleGetWorkoutDataStruct tlvChild parse else");
            }
        }
        ReleaseLogUtil.e(TAG_RELEASE, "ready parse frame data.");
        extrasDataUtils.parseFrameData(str2, i, str, dataHeader, list2);
        dataHeader.setWorkoutDataInfoList(list2);
        workoutDataStruct.setDataHeader(dataHeader);
    }

    private int handleWorkoutDataHeader(DataHeader dataHeader, List<String> list, cwd cwdVar) {
        int i = 0;
        dataHeader.setSportId(CommonUtil.w(cwdVar.c().substring(0, 4)));
        dataHeader.setFrameId(CommonUtil.w(cwdVar.c().substring(4, 8)));
        dataHeader.setTime(CommonUtil.y(cwdVar.c().substring(8, 16)) * 1000);
        dataHeader.setTimeInterval(CommonUtil.w(cwdVar.c().substring(16, 18)));
        int w = CommonUtil.w(cwdVar.c().substring(18, 22));
        String stringBuffer = new StringBuffer(Integer.toBinaryString(CommonUtil.w(cwdVar.c().substring(24, cwdVar.c().length())))).reverse().toString();
        int length = stringBuffer.length();
        while (i < length) {
            int i2 = i + 1;
            if (i2 <= length) {
                list.add(stringBuffer.substring(i, i2));
            } else {
                list.add("0");
            }
            i = i2;
        }
        dataHeader.setBitMap(list);
        return w;
    }

    private void handleWorkoutDataRecordCommand(List<WorkoutDataInfo> list, int i, List<String> list2, List<String> list3) {
        for (int i2 = 0; i2 < i; i2++) {
            WorkoutDataInfo workoutDataInfo = new WorkoutDataInfo();
            int size = list2.size();
            for (int i3 = 0; i3 < size; i3++) {
                if ("1".equals(list2.get(i3))) {
                    handleWorkoutDataBitMap(list3, workoutDataInfo, i3);
                    list3.remove(0);
                }
            }
            list.add(workoutDataInfo);
        }
    }

    private void handleWorkoutDataBitMap(List<String> list, WorkoutDataInfo workoutDataInfo, int i) {
        switch (i) {
            case 0:
                workoutDataInfo.setDataHeartRate(CommonUtil.w(list.get(0)));
                break;
            case 1:
                handleWorkoutDataSpeed(list, workoutDataInfo);
                break;
            case 2:
                workoutDataInfo.setDataStepRate(CommonUtil.w(list.get(0)));
                break;
            case 3:
                String str = list.get(0);
                list.remove(0);
                workoutDataInfo.setDataSwolf(CommonUtil.w(str + list.get(0)));
                break;
            case 4:
                String str2 = list.get(0);
                list.remove(0);
                workoutDataInfo.setDataSwimRate(CommonUtil.w(str2 + list.get(0)));
                break;
            case 5:
                handleWorkoutDataAltitude(list, workoutDataInfo);
                break;
            case 6:
                HwWorkoutServiceUtils.parseRunPostureDataInfo(list, workoutDataInfo);
                break;
            case 7:
                workoutDataInfo.setDataCalories(CommonUtil.w(list.get(0)));
                break;
            default:
                handleWorkoutDataBitMapExtendedField(i, list, workoutDataInfo);
                break;
        }
    }

    private void handleWorkoutDataBitMapExtendedField(int i, List<String> list, WorkoutDataInfo workoutDataInfo) {
        switch (i) {
            case 8:
                String str = list.get(0);
                list.remove(0);
                workoutDataInfo.setDataFrequency(CommonUtil.w(str + list.get(0)));
                break;
            case 9:
                String str2 = list.get(0);
                list.remove(0);
                workoutDataInfo.setRidePower(CommonUtil.w(str2 + list.get(0)));
                break;
            case 10:
                workoutDataInfo.setExtendedFieldEleven(CommonUtil.w(list.get(0)));
                break;
            case 11:
                workoutDataInfo.setExtendedFieldTwelve(CommonUtil.w(list.get(0)));
                break;
            case 12:
                workoutDataInfo.setExtendedFieldThirteen(CommonUtil.w(list.get(0)));
                break;
            case 13:
                workoutDataInfo.setExtendedFieldFourteen(CommonUtil.w(list.get(0)));
                break;
            case 14:
                workoutDataInfo.setExtendedFieldFifteen(CommonUtil.w(list.get(0)));
                break;
            case 15:
                workoutDataInfo.setExtendedFieldSixteen(CommonUtil.w(list.get(0)));
                break;
            default:
                LogUtil.c(TAG, "handleWorkoutDataBitMapExtendedField tlvChild parse else");
                break;
        }
    }

    private void handleWorkoutDataSpeed(List<String> list, WorkoutDataInfo workoutDataInfo) {
        String str = list.get(0);
        list.remove(0);
        workoutDataInfo.setDataSpeed(CommonUtil.w(str + list.get(0)));
    }

    private void handleWorkoutDataAltitude(List<String> list, WorkoutDataInfo workoutDataInfo) {
        String str = list.get(0);
        list.remove(0);
        String str2 = list.get(0);
        list.remove(0);
        String str3 = list.get(0);
        list.remove(0);
        workoutDataInfo.setDataAttitude((int) CommonUtil.y(str + str2 + str3 + list.get(0)));
    }

    private void handleWorkoutRecordPaceMap(List<cwd> list, List<cwe> list2) {
        if (!list.isEmpty() && CommonUtil.w(list.get(0).e()) == 127) {
            if (this.mWorkoutRecordPaceMapListCallback != null) {
                int w = CommonUtil.w(list.get(0).c());
                this.mWorkoutRecordPaceMapListCallback.d(w, kkm.d(Integer.valueOf(w), "getWorkoutRecordPaceMap"));
                return;
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
                ReleaseLogUtil.d(TAG_RELEASE, "tlv.getTag is else");
            }
        }
        for (cwe cweVar : list2.get(0).a()) {
            WorkoutRecordPaceMap workoutRecordPaceMap = new WorkoutRecordPaceMap();
            HwWorkoutServiceUtils.parsePaceMapDataStruct(cweVar, workoutRecordPaceMap);
            arrayList.add(workoutRecordPaceMap);
        }
        workRecordIndexPaceMapList.setPaceMapList(arrayList);
        IBaseResponseCallback iBaseResponseCallback = this.mWorkoutRecordPaceMapListCallback;
        if (iBaseResponseCallback != null) {
            iBaseResponseCallback.d(100000, kkm.d(workRecordIndexPaceMapList, "getWorkoutRecordPaceMap"));
        } else {
            ReleaseLogUtil.d(TAG_RELEASE, "handleWorkoutRecordPaceMap mWorkoutRecordPaceMapListCallback is null.");
            sqo.q("handleWorkoutRecordPaceMap mWorkoutRecordPaceMapListCallback is null.");
        }
    }

    private void handleWorkoutRecordSwimSectionList(List<cwe> list) {
        SectionList sectionList = new SectionList();
        Iterator<cwe> it = list.iterator();
        while (it.hasNext()) {
            for (cwd cwdVar : it.next().e()) {
                int w = CommonUtil.w(cwdVar.e());
                if (w == 2) {
                    sectionList.setWorkoutRecordId(CommonUtil.w(cwdVar.c()));
                } else if (w == 8) {
                    sectionList.setSectionIndex(CommonUtil.w(cwdVar.c()));
                } else {
                    LogUtil.c(TAG, "parseSectionListStruct tlvChild parse else");
                }
            }
        }
        ArrayList arrayList = new ArrayList(16);
        Iterator<cwe> it2 = list.iterator();
        while (it2.hasNext()) {
            for (cwe cweVar : it2.next().a()) {
                SectionInfo sectionInfo = new SectionInfo();
                Bundle bundle = new Bundle();
                HwWorkoutServiceUtils.parseSwimSectionDataStruct(cweVar, bundle);
                sectionInfo.setBundle(bundle);
                arrayList.add(sectionInfo);
            }
        }
        sectionList.setSectionInfos(arrayList);
        IBaseResponseCallback iBaseResponseCallback = this.mSectionListCallback;
        if (iBaseResponseCallback != null) {
            iBaseResponseCallback.d(100000, kkm.d(sectionList, "getWorkoutRecordSectionList"));
        } else {
            ReleaseLogUtil.d(TAG_RELEASE, "handleWorkoutRecordSwimSectionList mSectionListCallback is null.");
        }
    }

    private void handleWorkoutCapability(byte[] bArr, List<cwd> list) {
        if (this.mWorkoutCapabilityCallback == null) {
            ReleaseLogUtil.d(TAG_RELEASE, "handleWorkoutCapability mWorkoutCapabilityCallback is null");
            sqo.q("handleWorkoutCapability mWorkoutCapabilityCallback is null.");
            this.mIsSupportNewStep = false;
            this.mIsSupportNewEllipticalAndRowingMachine = false;
            return;
        }
        if (handleWorkoutCapabilityError(bArr)) {
            return;
        }
        for (cwd cwdVar : list) {
            if (CommonUtil.w(cwdVar.e()) == 1) {
                LogUtil.c(TAG, "handleWorkoutCapability enter");
                int w = CommonUtil.w(cwdVar.c());
                if ((w & 2) == 2) {
                    LogUtil.c(TAG, "mIsSupportNewStep is true");
                    this.mIsSupportNewStep = true;
                } else {
                    this.mIsSupportNewStep = false;
                }
                if ((w & 4) == 4) {
                    this.mIsSupportNewEllipticalAndRowingMachine = true;
                    ReleaseLogUtil.e(TAG_RELEASE, "mIsSupportNewEllipticalAndRowingMachine is true");
                } else {
                    this.mIsSupportNewEllipticalAndRowingMachine = false;
                }
            } else {
                this.mIsSupportNewStep = false;
                this.mIsSupportNewEllipticalAndRowingMachine = false;
            }
        }
        this.mWorkoutCapabilityCallback.d(100000, kkm.d(Boolean.valueOf(this.mIsSupportNewStep), "getWorkoutCapability"));
    }

    private boolean handleWorkoutCapabilityError(byte[] bArr) {
        if (bArr.length != 8 || bArr[2] != Byte.MAX_VALUE) {
            return false;
        }
        this.mIsSupportNewStep = false;
        this.mIsSupportNewEllipticalAndRowingMachine = false;
        try {
            this.mWorkoutCapabilityCallback.d(kdb.a(bArr), kkm.d("", "getWorkoutCapability"));
            return true;
        } catch (cwg e) {
            ReleaseLogUtil.c(TAG_RELEASE, "handleWorkoutCapability Exception: ", ExceptionUtils.d(e));
            sqo.q("handleWorkoutCapability Exception: " + ExceptionUtils.d(e));
            this.mWorkoutCapabilityCallback.d(201000, kkm.d("", "getWorkoutCapability"));
            return true;
        }
    }

    private void handleCommonSectionList(List<cwd> list, List<cwe> list2) {
        CommonSectionList commonSectionList = new CommonSectionList();
        for (cwd cwdVar : list) {
            int w = CommonUtil.w(cwdVar.e());
            if (w == 1) {
                commonSectionList.setWorkoutRecordId(CommonUtil.w(cwdVar.c()));
            } else if (w == 2) {
                commonSectionList.setSectionIndex(CommonUtil.w(cwdVar.c()));
            } else {
                LogUtil.c(TAG, "deal else value");
            }
        }
        if (list2.isEmpty()) {
            ReleaseLogUtil.d(TAG_RELEASE, "handleCommonSectionList tlvFatherList is empty");
            sqo.q("handleCommonSectionList tlvFatherList is empty");
            processCallback(22, -1, null);
            return;
        }
        LogUtil.a(TAG, "tlvFatherList size:", Integer.valueOf(list2.size()));
        List<CommonSectionInfo> arrayList = new ArrayList<>(16);
        try {
            JSONObject jSONObject = HwExerciseParams.getInstance().getWorkoutRecordsStatisticArray().get(commonSectionList.getWorkoutRecordId());
            if (jSONObject != null) {
                processWorkoutData(list2, arrayList, jSONObject);
            }
        } catch (JSONException e) {
            ReleaseLogUtil.c(TAG_RELEASE, "handleCommonSectionList JSONException:", ExceptionUtils.d(e));
        }
        ReleaseLogUtil.e(TAG_RELEASE, "sectionInfos size: ", Integer.valueOf(arrayList.size()));
        commonSectionList.setSectionInfos(arrayList);
        processCallback(22, 100000, commonSectionList);
    }

    private void processWorkoutData(List<cwe> list, List<CommonSectionInfo> list2, JSONObject jSONObject) throws JSONException {
        int i = jSONObject.getInt(HwExerciseConstants.JSON_NAME_WORKOUT_TYPE);
        if (i == 22 || i == 25) {
            Iterator<cwe> it = list.iterator();
            while (it.hasNext()) {
                analyzeCommonSectionInfo(list2, it.next(), i);
            }
            return;
        }
        analyzeCommonSectionInfo(list2, list.get(0), i);
    }

    private void analyzeCommonSectionInfo(List<CommonSectionInfo> list, cwe cweVar, int i) {
        for (cwe cweVar2 : cweVar.a()) {
            CommonSectionInfo commonSectionInfo = new CommonSectionInfo();
            HwWorkoutServiceUtils.parseCommonSectionData(cweVar2, commonSectionInfo, i);
            list.add(commonSectionInfo);
        }
    }

    private void addCommandToMap(int i, IBaseResponseCallback iBaseResponseCallback) {
        synchronized (COMMAND_CALLBACK_LOCK) {
            if (iBaseResponseCallback != null) {
                this.mCommandCallbackMap.put(Integer.valueOf(i), iBaseResponseCallback);
            }
        }
    }

    private void processCallback(int i, int i2, Object obj) {
        LogUtil.a(TAG, "processCallback callback commandId is ", Integer.valueOf(i), " error is ", Integer.valueOf(i2));
        synchronized (COMMAND_CALLBACK_LOCK) {
            IBaseResponseCallback iBaseResponseCallback = this.mCommandCallbackMap.get(Integer.valueOf(i));
            if (iBaseResponseCallback != null) {
                iBaseResponseCallback.d(i2, obj);
            }
        }
    }

    private static List<IBaseResponseCallback> getWorkoutRealTimeInfoCallbackList() {
        List<IBaseResponseCallback> list;
        synchronized (HwWorkoutServiceManager.class) {
            list = sWorkoutRealTimeInfoCallbackList;
        }
        return list;
    }

    private static List<IBaseResponseCallback> getNotificationWorkoutRealTimeInfoCallbackList() {
        List<IBaseResponseCallback> list;
        synchronized (HwWorkoutServiceManager.class) {
            list = sNotificationWorkoutRealTimeInfoCallbackList;
        }
        return list;
    }

    private static List<IBaseResponseCallback> getNotificationSportReminderCallbackList() {
        List<IBaseResponseCallback> list;
        synchronized (HwWorkoutServiceManager.class) {
            list = sNotificationSportReminderCallbackList;
        }
        return list;
    }

    private DeviceCapability getCapability(DeviceInfo deviceInfo) {
        Map<String, DeviceCapability> queryDeviceCapability = this.mHwDeviceMgr.queryDeviceCapability(1, deviceInfo.getDeviceIdentify(), TAG);
        if (queryDeviceCapability == null || queryDeviceCapability.isEmpty()) {
            ReleaseLogUtil.d(TAG_RELEASE, "getCapability deviceCapabilityHashMaps is null");
            sqo.q("getCapability deviceCapabilityHashMaps is null");
            return null;
        }
        return queryDeviceCapability.get(deviceInfo.getDeviceIdentify());
    }
}
