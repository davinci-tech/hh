package com.huawei.hwdevice.phoneprocess.mgr.exercise.multisync;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.SparseArray;
import com.huawei.datatype.DeviceCommand;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealth.HiDataInsertOption;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.dmsmanager.HwDeviceMgrInterface;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseAdviceManager;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.LastSyncTimestampDb;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.MotionPath;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.MotionPathSimplify;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.PaceIndexStruct;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.RunPlanParameter;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.RunPlanRecord;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.RunPlanRecordInfo;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.RunPlanRecordStruct;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.StepRateData;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.TrackSpeedData;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.WorkoutDisplayInfo;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.utils.HwExerciseUtils;
import com.huawei.hwfoundationmodel.trackmodel.HeartRateData;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.blt;
import defpackage.cun;
import defpackage.cvt;
import defpackage.cvx;
import defpackage.cwd;
import defpackage.cwe;
import defpackage.cwg;
import defpackage.cwl;
import defpackage.ffs;
import defpackage.fft;
import defpackage.ixt;
import defpackage.iyv;
import defpackage.jec;
import defpackage.jra;
import defpackage.jrq;
import defpackage.jsz;
import defpackage.kkm;
import health.compact.a.CommonUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class HwExerciseAdviceAw70Manager {
    private static final int CONVERT_BIT_LENGTH = 2;
    private static final String DATA_HEADER = "dataHeader";
    private static final float DEFAULT_AVG_PACE = 0.0f;
    private static final int DEFAULT_DATA_VALUE = 0;
    private static final int DEFAULT_FALLBACK = -1;
    private static final int DEFAULT_SIZE = 16;
    private static final float DIFFERENT_STEP_NUMBER = 0.5f;
    private static final String END_TIME = "endTime";
    private static final int EXERCISE_ADVICE_SYNC_DETAIL_TIMEOUT = 300000;
    private static final int EXERCISE_MGR_POOL_NUMBER = 5;
    private static final int FIRST_INDEX = 0;
    private static final int FULL_BYTE_MASK = 255;
    private static final int HIGH_BIT_NUMBER = 128;
    private static final int HOUR = 3600;
    private static final String KEY_ID = "id";
    private static final String KEY_VALUE = "value";
    private static final int MESSAGE_EXERCISE_ADVICE_SYNC_DETAIL_TIMEOUT = 0;
    private static final int MILLI_SECOND_UNIT = 1000;
    private static final int OBTAIN_GPS_ERROR = -1;
    private static final long ONE_DAY_SECOND = 86400000;
    private static final int RADIX_DEFAULT_16 = 16;
    private static final String RUN_PLAN_RECORD_ID = "run_plan_record_id";
    private static final String RUN_PLAN_RECORD_INFO_EXERCISE_DURATION = "run_plan_record_info_exercise_duration";
    private static final String RUN_PLAN_RECORD_INFO_ID = "run_plan_record_info_id";
    private static final String RUN_PLAN_RECORD_INFO_STEP = "run_plan_record_info_step";
    private static final String RUN_PLAN_RECORD_STRUCT_LIST = "runPlanRecordStructList";
    private static final long SEVEN_DAY_SECOND = 604800000;
    private static final int SHIFT_16 = 16;
    private static final int SHIFT_24 = 24;
    private static final int SHIFT_8 = 8;
    private static final int SKATE_CODE = 256;
    private static final String START_TIME = "startTime";
    private static final String TAG = "HwExerciseAdviceAw70Manager";
    private static final String TAG_RELEASE = "BTSYNC_HwExerciseAdviceAw70Manager";
    private static final int TEN_DEFAULT_10 = 10;
    private static final int THOUSAND = 1000;
    private static final long THOUSAND_LONG = 1000;
    private static final int TIME_CONVERT = 60;
    private static final int TL_LENGTH = 4;
    private static final float TOTAL_COUNT_PARAM = 5.0f;
    private static final String WORKOUT_DATA_INDEX = "workout_data_index";
    private static final String WORKOUT_DATA_INFO_LISTS = "workoutDataInfoLists";
    private static final String WORKOUT_EXERCISE_DURATION = "workout_exercise_duration";
    private static final String WORKOUT_RECORD_ID = "workout_record_id";
    private static final String WORKOUT_RECORD_SAVE_FINISH = "com.huawei.health.workout_record_save_finish";
    private static final String WORKOUT_RECORD_STEP = "workout_record_step";
    private static final String WORKOUT_RECORD_STRUCT_LIST = "workoutRecordStructList";
    private static final String WORKOUT_TYPE = "workout_type";
    private static HwDeviceMgrInterface sHwDeviceMgr;
    private static HwExerciseAdviceAw70Manager sInstance;
    private HwExerciseAdviceAw70ManagerUtil mAw70ManagerUtil;
    private Context mContext;
    private HwExerciseAdviceManager mManager;
    private JSONObject mRunPlanRecord;
    private ThreadPoolManager mThreadPool;
    private JSONObject mWorkoutRecord;
    private static final Object LOCK = new Object();
    private static final Object LOCK_OBJECT = new Object();
    private static List<IBaseResponseCallback> sRunPlanParameterCallbackList = new ArrayList(16);
    private static List<IBaseResponseCallback> sSetRunPlanCallbackList = new ArrayList(16);
    private static List<IBaseResponseCallback> sSetRunPlanReminderSwitchCallbackList = new ArrayList(16);
    private static List<IBaseResponseCallback> sGetRunPlanRecordCallbackList = new ArrayList(16);
    private static List<IBaseResponseCallback> sGetRunPlanRecordInfoCallbackList = new ArrayList(16);
    private static List<IBaseResponseCallback> sNotificationRunPlanRecordInfoCallbackList = new ArrayList(16);
    private boolean mIsDeviceSupportCapacity = false;
    private boolean mIsDeviceSupportPaceMap = false;
    private SparseArray<JSONObject> mWorkoutRecordsStatistic = new SparseArray<>();
    private SparseArray<JSONObject> mRunPlanRecordsStatistic = new SparseArray<>();
    private Map<Integer, List<JSONArray>> mWorkoutRecordJumpDataMap = new HashMap(16);
    private List<JSONObject> mWorkoutDetailDataList = new ArrayList(16);
    private List<JSONObject> mWorkoutDataList = new ArrayList(16);
    private List<Integer> mGpsWorkoutRecordIdList = new ArrayList(16);
    private List<Integer> mGpsRunPlanRecordIdList = new ArrayList(16);
    private List<PaceIndexStruct> mWorkoutRecordPaceMapIdList = new ArrayList(16);
    private List<JSONObject> mWorkoutRecordPaceMapList = new ArrayList(16);
    private ArrayList<HeartRateData> mPackTrackHeartrateList = new ArrayList<>(16);
    private ArrayList<StepRateData> mPackTrackstepList = new ArrayList<>(16);
    private ArrayList<ffs> mPackTrackRunningPostureList = new ArrayList<>(16);
    private ArrayList<TrackSpeedData> mPackTrackSpeedDataArrayList = new ArrayList<>(16);
    private Map<Integer, Map<Long, double[]>> mGpsWorkoutMap = new HashMap(16);
    private Map<Integer, Map<Long, double[]>> mGpsRunPlanMap = new HashMap(16);
    private int mSaveDataItemNumber = 0;
    private int mWorkoutRecordStatisticIndex = 0;
    private int mRunPlanRecordStatisticIndex = 0;
    private int mSliceRecordIdIndex = 0;
    private int mSliceNumberIndex = 0;
    private boolean mIsDetailSyncing = false;
    private ExtendHandler mExtendHandler = null;
    private long mCurrentTime = 0;
    private long mLastSyncTime = 0;
    private int mPackTrackMaxPace = 0;
    private long mPackTrackTotalPace = 0;
    private int mPackTrackMaxStep = 0;
    private int mPackTrackTotalStep = 0;
    private long mPackTrackTotalHeart = 0;
    private int mPackTrackTotalCount = 0;
    private int mPackTrackHeartTotalCount = 0;
    private int mPackTrackSummaryTotalStep = 0;
    private int mPackTrackIndex = 0;
    private long mPackTrackTotalTime = 0;
    private int mPackTrackCount = 1;
    private String mCurrentDeviceId = "";
    private int mWorkoutDataNumber = 0;
    private int mDeviceCapability = 0;
    private boolean mIsAw70SyncSuccess = false;
    private IBaseResponseCallback mDeviceWorkoutDetailCallback = new IBaseResponseCallback() { // from class: com.huawei.hwdevice.phoneprocess.mgr.exercise.multisync.HwExerciseAdviceAw70Manager.1
        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            JSONObject jSONObject;
            if (i != 100000 || !(obj instanceof Map)) {
                HwExerciseAdviceAw70Manager.this.notifyDetailSyncComplete(i, "getWorkoutDetailData");
                HwExerciseAdviceAw70Manager.this.sendAw70WorkoutSyncEvent(i);
                iyv.e(i);
                return;
            }
            try {
                jSONObject = new JSONObject(((Map) obj).get("value").toString());
            } catch (JSONException unused) {
                LogUtil.b(HwExerciseAdviceAw70Manager.TAG, "deviceWorkoutDetailCallback JSONException");
                jSONObject = null;
            }
            if (jSONObject != null) {
                HwExerciseAdviceAw70Manager.this.mWorkoutDetailDataList.add(jSONObject);
                try {
                    int size = HwExerciseAdviceAw70Manager.this.mWorkoutDataList.size();
                    int i2 = 0;
                    while (true) {
                        if (i2 < size) {
                            if (jSONObject.getInt("workout_data_index") == ((JSONObject) HwExerciseAdviceAw70Manager.this.mWorkoutDataList.get(i2)).getInt("workout_data_index") && jSONObject.getInt("workout_record_id") == ((JSONObject) HwExerciseAdviceAw70Manager.this.mWorkoutDataList.get(i2)).getInt("workout_record_id")) {
                                HwExerciseAdviceAw70Manager.this.mWorkoutDataList.remove(i2);
                                break;
                            }
                            i2++;
                        } else {
                            break;
                        }
                    }
                    if (HwExerciseAdviceAw70Manager.this.mWorkoutDataList.isEmpty()) {
                        if (!HwExerciseAdviceAw70Manager.this.mIsDeviceSupportPaceMap || HwExerciseAdviceAw70Manager.this.mWorkoutRecordPaceMapIdList.isEmpty()) {
                            HwExerciseAdviceAw70Manager.this.getWorkoutRecordJumpData();
                            return;
                        } else {
                            HwExerciseAdviceAw70Manager hwExerciseAdviceAw70Manager = HwExerciseAdviceAw70Manager.this;
                            hwExerciseAdviceAw70Manager.getWorkoutRecordPaceMap((PaceIndexStruct) hwExerciseAdviceAw70Manager.mWorkoutRecordPaceMapIdList.get(0));
                            return;
                        }
                    }
                    HwExerciseAdviceAw70Manager hwExerciseAdviceAw70Manager2 = HwExerciseAdviceAw70Manager.this;
                    hwExerciseAdviceAw70Manager2.getWorkoutDetailData((JSONObject) hwExerciseAdviceAw70Manager2.mWorkoutDataList.get(0));
                    return;
                } catch (JSONException unused2) {
                    LogUtil.b(HwExerciseAdviceAw70Manager.TAG, "deviceWorkoutDetailCallback JSONException");
                    iyv.e(i);
                    return;
                }
            }
            LogUtil.h(HwExerciseAdviceAw70Manager.TAG, "detail is null");
        }
    };
    private IBaseResponseCallback mGetWorkoutRecordPaceMapCallback = new IBaseResponseCallback() { // from class: com.huawei.hwdevice.phoneprocess.mgr.exercise.multisync.HwExerciseAdviceAw70Manager.2
        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            JSONObject jSONObject;
            if (i != 100000 || !(obj instanceof Map)) {
                HwExerciseAdviceAw70Manager.this.notifyDetailSyncComplete(i, "getWorkoutRecordPaceMap");
                HwExerciseAdviceAw70Manager.this.sendAw70WorkoutSyncEvent(i);
                iyv.e(i);
                return;
            }
            try {
                Object obj2 = ((Map) obj).get("value");
                LogUtil.a(HwExerciseAdviceAw70Manager.TAG, "getWorkoutRecordPaceMapCallback value.toString(): ", obj2.toString());
                jSONObject = new JSONObject(obj2.toString());
            } catch (JSONException unused) {
                LogUtil.b(HwExerciseAdviceAw70Manager.TAG, "getWorkoutRecordPaceMapCallback JSONException");
                jSONObject = null;
            }
            if (jSONObject != null) {
                HwExerciseAdviceAw70Manager.this.mWorkoutRecordPaceMapList.add(jSONObject);
                try {
                    LogUtil.a(HwExerciseAdviceAw70Manager.TAG, "getWorkoutRecordPaceMapCallback workoutID: ", jSONObject.get("workout_record_id"));
                    int size = HwExerciseAdviceAw70Manager.this.mWorkoutRecordPaceMapIdList.size();
                    int i2 = 0;
                    while (true) {
                        if (i2 < size) {
                            if (jSONObject.getInt("workout_record_id") == ((PaceIndexStruct) HwExerciseAdviceAw70Manager.this.mWorkoutRecordPaceMapIdList.get(i2)).getRecordId() && jSONObject.optInt(HwExerciseConstants.PACE_INDEX_NAME, -1) == ((PaceIndexStruct) HwExerciseAdviceAw70Manager.this.mWorkoutRecordPaceMapIdList.get(i2)).getPaceIndex()) {
                                LogUtil.a(HwExerciseAdviceAw70Manager.TAG, "remove");
                                HwExerciseAdviceAw70Manager.this.mWorkoutRecordPaceMapIdList.remove(i2);
                                break;
                            }
                            i2++;
                        } else {
                            break;
                        }
                    }
                    if (HwExerciseAdviceAw70Manager.this.mWorkoutRecordPaceMapIdList.isEmpty()) {
                        HwExerciseAdviceAw70Manager.this.getWorkoutRecordJumpData();
                        return;
                    } else {
                        HwExerciseAdviceAw70Manager hwExerciseAdviceAw70Manager = HwExerciseAdviceAw70Manager.this;
                        hwExerciseAdviceAw70Manager.getWorkoutRecordPaceMap((PaceIndexStruct) hwExerciseAdviceAw70Manager.mWorkoutRecordPaceMapIdList.get(0));
                        return;
                    }
                } catch (JSONException unused2) {
                    LogUtil.b(HwExerciseAdviceAw70Manager.TAG, "getWorkoutRecordPaceMapCallback JSONException");
                    iyv.e(i);
                    return;
                }
            }
            LogUtil.h(HwExerciseAdviceAw70Manager.TAG, "paceMap is null");
        }
    };

    static /* synthetic */ int access$1710(HwExerciseAdviceAw70Manager hwExerciseAdviceAw70Manager) {
        int i = hwExerciseAdviceAw70Manager.mSaveDataItemNumber;
        hwExerciseAdviceAw70Manager.mSaveDataItemNumber = i - 1;
        return i;
    }

    static /* synthetic */ int access$1808(HwExerciseAdviceAw70Manager hwExerciseAdviceAw70Manager) {
        int i = hwExerciseAdviceAw70Manager.mWorkoutDataNumber;
        hwExerciseAdviceAw70Manager.mWorkoutDataNumber = i + 1;
        return i;
    }

    private HwExerciseAdviceAw70Manager(Context context, HwExerciseAdviceManager hwExerciseAdviceManager) {
        this.mContext = context;
        this.mManager = hwExerciseAdviceManager;
        sHwDeviceMgr = jsz.b(context);
        initDeviceInfo();
        this.mThreadPool = ThreadPoolManager.e(5, 5, TAG);
        this.mAw70ManagerUtil = new HwExerciseAdviceAw70ManagerUtil(this.mContext);
    }

    public boolean isAw70SyncSuccess() {
        return this.mIsAw70SyncSuccess;
    }

    public void setIsAw70SyncSuccess(boolean z) {
        this.mIsAw70SyncSuccess = z;
    }

    public static HwExerciseAdviceAw70Manager getInstance(HwExerciseAdviceManager hwExerciseAdviceManager) {
        HwExerciseAdviceAw70Manager hwExerciseAdviceAw70Manager;
        synchronized (LOCK) {
            if (sInstance == null) {
                sInstance = new HwExerciseAdviceAw70Manager(BaseApplication.getContext(), hwExerciseAdviceManager);
            }
            hwExerciseAdviceAw70Manager = sInstance;
        }
        return hwExerciseAdviceAw70Manager;
    }

    private void initDeviceInfo() {
        this.mExtendHandler = HandlerCenter.yt_(new HwExerciseAdviceMgrHandlerCallback(), TAG);
    }

    class HwExerciseAdviceMgrHandlerCallback implements Handler.Callback {
        private HwExerciseAdviceMgrHandlerCallback() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            if (message.what == 0) {
                HwExerciseAdviceAw70Manager.this.notifyDetailSyncComplete(300001, "TIMEOUT");
                LogUtil.a(HwExerciseAdviceAw70Manager.TAG, "reset maintenance flag delete this code! have problem!");
                HwExerciseAdviceAw70Manager.this.sendAw70WorkoutSyncEvent(0);
                return true;
            }
            LogUtil.h(HwExerciseAdviceAw70Manager.TAG, "HwExerciseAdviceMgrHandler default branch");
            return false;
        }
    }

    private static DeviceInfo getCurrentDeviceInfo() {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, TAG);
        if (!deviceList.isEmpty()) {
            LogUtil.a(TAG, "getCurrentDeviceInfo() deviceInfoList.size():", Integer.valueOf(deviceList.size()));
            for (DeviceInfo deviceInfo : deviceList) {
                if (deviceInfo.getDeviceActiveState() == 1 && cvt.c(deviceInfo.getProductType())) {
                    return deviceInfo;
                }
            }
            LogUtil.a(TAG, "getCurrentDeviceInfo() deviceInfo's ActiveState not DeviceActiveState.DEVICE_ACTIVE_ENABLE");
            return null;
        }
        LogUtil.h(TAG, "getCurrentDeviceInfo() deviceInfoList is null");
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLastSyncTime(long j) {
        new LastSyncTimestampDb().setLastTimestamp(this.mManager, this.mAw70ManagerUtil.getCurrentDeviceId(), j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long getLastSyncTime() {
        return new LastSyncTimestampDb().getLastTimestamp(this.mManager, this.mAw70ManagerUtil.getCurrentDeviceId());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyDetailSyncComplete(final int i, String str) {
        LogUtil.a(TAG, "notifyDetailSyncComplete errorCode: ", Integer.valueOf(i));
        this.mSliceNumberIndex = 0;
        this.mSliceRecordIdIndex = 0;
        ExtendHandler extendHandler = this.mExtendHandler;
        if (extendHandler != null) {
            extendHandler.removeMessages(0);
        }
        if (i == 0 || i == -1) {
            jrq.b(TAG, new Runnable() { // from class: com.huawei.hwdevice.phoneprocess.mgr.exercise.multisync.HwExerciseAdviceAw70Manager.3
                @Override // java.lang.Runnable
                public void run() {
                    if (HwExerciseAdviceAw70Manager.this.mCurrentDeviceId.equals(HwExerciseAdviceAw70Manager.this.mAw70ManagerUtil.getCurrentDeviceId())) {
                        HwExerciseAdviceAw70Manager.this.saveData();
                        if (i == 0) {
                            HwExerciseAdviceAw70Manager hwExerciseAdviceAw70Manager = HwExerciseAdviceAw70Manager.this;
                            hwExerciseAdviceAw70Manager.setLastSyncTime(hwExerciseAdviceAw70Manager.mCurrentTime);
                        } else {
                            LogUtil.h(HwExerciseAdviceAw70Manager.TAG, "sync start device is not same with end device, or gps error, don't save timestamp");
                        }
                    }
                    HwExerciseAdviceAw70Manager.this.mIsDetailSyncing = false;
                }
            });
        } else {
            this.mIsDetailSyncing = false;
            LogUtil.h(TAG, "notifyDetailSyncComplete fail for ", str, "errorCode: ", Integer.valueOf(i));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0032, code lost:
    
        if (r0 == false) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0036, code lost:
    
        com.huawei.hwdevice.phoneprocess.mgr.exercise.DeviceDbForHomeCards.updateExerciseInsertTime(r5.mCurrentTime);
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x003b, code lost:
    
        sendAw70WorkoutSyncEvent(100000);
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:?, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0034, code lost:
    
        if (r3 != false) goto L19;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void saveData() {
        /*
            r5 = this;
            r0 = 0
            r5.mSaveDataItemNumber = r0
            org.json.JSONObject r1 = r5.mWorkoutRecord     // Catch: org.json.JSONException -> L42
            r2 = 1
            if (r1 == 0) goto L1c
            java.lang.String r3 = "workoutRecordStructList"
            org.json.JSONArray r1 = r1.getJSONArray(r3)     // Catch: org.json.JSONException -> L42
            int r3 = r1.length()     // Catch: org.json.JSONException -> L42
            if (r3 <= 0) goto L17
            r3 = r2
            goto L18
        L17:
            r3 = r0
        L18:
            r5.getDataFromJson(r1)     // Catch: org.json.JSONException -> L42
            goto L1d
        L1c:
            r3 = r0
        L1d:
            org.json.JSONObject r1 = r5.mRunPlanRecord     // Catch: org.json.JSONException -> L42
            if (r1 == 0) goto L34
            java.lang.String r4 = "runPlanRecordStructList"
            org.json.JSONArray r1 = r1.getJSONArray(r4)     // Catch: org.json.JSONException -> L42
            int r4 = r1.length()     // Catch: org.json.JSONException -> L42
            if (r4 <= 0) goto L2f
            r0 = r2
        L2f:
            r5.dealSaveData(r1, r4)     // Catch: org.json.JSONException -> L42
            if (r0 != 0) goto L36
        L34:
            if (r3 == 0) goto L3b
        L36:
            long r0 = r5.mCurrentTime     // Catch: org.json.JSONException -> L42
            com.huawei.hwdevice.phoneprocess.mgr.exercise.DeviceDbForHomeCards.updateExerciseInsertTime(r0)     // Catch: org.json.JSONException -> L42
        L3b:
            r0 = 100000(0x186a0, float:1.4013E-40)
            r5.sendAw70WorkoutSyncEvent(r0)     // Catch: org.json.JSONException -> L42
            goto L4e
        L42:
            java.lang.String r0 = "saveData parse Json Exception"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            java.lang.String r1 = "HwExerciseAdviceAw70Manager"
            com.huawei.hwlogsmodel.LogUtil.b(r1, r0)
        L4e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hwdevice.phoneprocess.mgr.exercise.multisync.HwExerciseAdviceAw70Manager.saveData():void");
    }

    private void dealSaveData(JSONArray jSONArray, int i) throws JSONException {
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = jSONArray.getJSONObject(i2).getInt("run_plan_record_id");
            JSONArray jSONArray2 = new JSONArray();
            for (JSONObject jSONObject : this.mWorkoutDetailDataList) {
                if (i3 == jSONObject.getInt("workout_record_id")) {
                    jSONArray2.put(jSONObject);
                }
            }
            JSONArray jSONArray3 = new JSONArray();
            for (JSONObject jSONObject2 : this.mWorkoutRecordPaceMapList) {
                if (i3 == jSONObject2.getInt("workout_record_id")) {
                    jSONArray3.put(jSONObject2);
                }
            }
            if (this.mGpsRunPlanMap.get(Integer.valueOf(i3)) != null) {
                LogUtil.a(TAG, "runplan_workout_id:", Integer.valueOf(jSONArray.getJSONObject(i2).getInt(HwExerciseConstants.JSON_NAME_WORKOUT_RUN_PLAY_ID)), "mapGPSRunPlan size:", Integer.valueOf(this.mGpsRunPlanMap.get(Integer.valueOf(i3)).size()));
            }
            this.mSaveDataItemNumber++;
            saveDataToTrack(this.mRunPlanRecordsStatistic.get(i3), jSONArray2, this.mGpsRunPlanMap.get(Integer.valueOf(i3)), jSONArray3);
        }
    }

    private void getDataFromJson(JSONArray jSONArray) {
        try {
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                int i2 = jSONArray.getJSONObject(i).getInt("workout_record_id");
                JSONObject jSONObject = this.mWorkoutRecordsStatistic.get(i2);
                if (jSONObject == null) {
                    LogUtil.h(TAG, "total is null: ", Integer.valueOf(i), " error can't get the total value, with id: ", Integer.valueOf(i2));
                } else {
                    JSONArray jSONArray2 = new JSONArray();
                    for (JSONObject jSONObject2 : this.mWorkoutDetailDataList) {
                        if (i2 == jSONObject2.getInt("workout_record_id")) {
                            jSONArray2.put(jSONObject2);
                        }
                    }
                    JSONArray jSONArray3 = new JSONArray();
                    for (JSONObject jSONObject3 : this.mWorkoutRecordPaceMapList) {
                        if (i2 == jSONObject3.getInt("workout_record_id")) {
                            jSONArray3.put(jSONObject3);
                        }
                    }
                    if (this.mGpsWorkoutMap.get(Integer.valueOf(i2)) != null) {
                        LogUtil.a(TAG, "mapGPSWorkout size: ", Integer.valueOf(this.mGpsWorkoutMap.get(Integer.valueOf(i2)).size()));
                    }
                    if (this.mAw70ManagerUtil.checkSupportWorkoutType(jSONObject.getInt("workout_type"))) {
                        this.mSaveDataItemNumber++;
                        saveWorkoutRecordDataToTrack(jSONObject, jSONArray2, i2, jSONArray3);
                    }
                }
            }
        } catch (JSONException unused) {
            LogUtil.b(TAG, "getDataFromJson parse Json Exception");
        }
    }

    private void saveDataToTrack(JSONObject jSONObject, JSONArray jSONArray, Map<Long, double[]> map, JSONArray jSONArray2) {
        MotionPath motionPath = new MotionPath();
        MotionPathSimplify motionPathSimplify = new MotionPathSimplify();
        if (map != null) {
            motionPath.setLbsDataMap(map);
        }
        try {
            int i = (int) jSONObject.getLong("run_plan_record_info_exercise_duration");
            if (jSONArray2 != null) {
                Map<Integer, Float> changePaceMapStruct = this.mAw70ManagerUtil.changePaceMapStruct(jSONArray2, 0);
                motionPath.setPaceMap(changePaceMapStruct);
                motionPathSimplify.setPaceMap(changePaceMapStruct);
                Map<Integer, Float> changePaceMapStruct2 = this.mAw70ManagerUtil.changePaceMapStruct(jSONArray2, 1);
                motionPath.setBritishPaceMap(changePaceMapStruct2);
                motionPathSimplify.setBritishPaceMap(changePaceMapStruct2);
                motionPathSimplify.setPartTimeMap(this.mAw70ManagerUtil.changePartTimePaceMapStruct(jSONArray2, 0));
                motionPathSimplify.setBritishPartTimeMap(this.mAw70ManagerUtil.changePartTimePaceMapStruct(jSONArray2, 1));
            }
            motionPathSimplify.setTotalSteps(jSONObject.getInt("run_plan_record_info_step"));
            motionPathSimplify.setTotalTime(i);
            packTrackData(jSONArray, motionPath, motionPathSimplify, null);
            this.mAw70ManagerUtil.setSimplifyMore(jSONObject, map, motionPathSimplify);
            HashMap hashMap = new HashMap(16);
            hashMap.put("record_id", Integer.valueOf(jSONObject.getInt("run_plan_record_info_id")));
            hashMap.put("status", Integer.valueOf(jSONObject.getInt(HwExerciseConstants.JSON_NAME_SPORT_DATA_KEY_RECORD_STATUS)));
            hashMap.put("load_peak", Integer.valueOf(jSONObject.getInt(HwExerciseConstants.JSON_NAME_SPORT_DATA_KEY_RECORD_LOAD_PEAK)));
            hashMap.put("etraining_effect", Integer.valueOf(jSONObject.getInt(HwExerciseConstants.JSON_NAME_SPORT_DATA_KEY_RECORD_ETRAINING_EFFECT)));
            hashMap.put("extra_poc", Integer.valueOf(jSONObject.getInt(HwExerciseConstants.JSON_NAME_SPORT_DATA_KEY_RECORD_EPOC)));
            hashMap.put("max_met", Integer.valueOf(jSONObject.getInt(HwExerciseConstants.JSON_NAME_SPORT_DATA_KEY_RECORD_MAX_MET)));
            hashMap.put("recovery_time", Integer.valueOf(jSONObject.getInt(HwExerciseConstants.JSON_NAME_SPORT_DATA_KEY_RECORD_RECOVERY_TIME)));
            hashMap.put("achieve_percent", Integer.valueOf(jSONObject.getInt(HwExerciseConstants.JSON_NAME_SPORT_DATA_KEY_RECORD_ACHIEVE_PERCENT)));
            motionPathSimplify.setSportData(hashMap);
            motionPathSimplify.setSportData(hashMap);
        } catch (JSONException unused) {
            LogUtil.b(TAG, "saveDataToTrack JSONException");
        }
        saveTrackDataToHealthLib(motionPathSimplify, motionPath, -1);
        LogUtil.a(TAG, "save runPlan Record DataToTrack finish");
    }

    private void saveTrackDataToHealthLib(final MotionPathSimplify motionPathSimplify, MotionPath motionPath, final int i) {
        LogUtil.a(TAG, "enter saveTrackDataToHealthLib MotionPath");
        HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
        this.mAw70ManagerUtil.convertHealthTrackDataToHiData(motionPathSimplify, motionPath, hiDataInsertOption);
        HiHealthManager.d(BaseApplication.getContext()).insertHiHealthData(hiDataInsertOption, new HiDataOperateListener() { // from class: com.huawei.hwdevice.phoneprocess.mgr.exercise.multisync.HwExerciseAdviceAw70Manager.4
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public void onResult(int i2, Object obj) {
                LogUtil.a(HwExerciseAdviceAw70Manager.TAG, "saveTrackData MotionPath onSuccess object: ", obj, " errorCode: ", Integer.valueOf(i2), " DataItemNumber: ", Integer.valueOf(HwExerciseAdviceAw70Manager.this.mSaveDataItemNumber));
                if (i2 == 0) {
                    iyv.l();
                    HwExerciseUtils.writeTrackTimeToList(motionPathSimplify);
                    HwExerciseAdviceAw70Manager.this.setIsAw70SyncSuccess(true);
                }
                if (HwExerciseAdviceAw70Manager.this.isAw70SyncSuccess() && i != -1 && HwWorkoutServiceAw70Manager.getInstance().isAw70LastData(i)) {
                    LogUtil.a(HwExerciseAdviceAw70Manager.TAG, "saveTrackDataToHealthLib notifyMsg");
                    HwExerciseUtils.sendNotifyBySwitch();
                    HwExerciseAdviceAw70Manager.this.setIsAw70SyncSuccess(false);
                }
                HwExerciseAdviceAw70Manager.access$1710(HwExerciseAdviceAw70Manager.this);
                HwExerciseAdviceAw70Manager.access$1808(HwExerciseAdviceAw70Manager.this);
                if (HwExerciseAdviceAw70Manager.this.mSaveDataItemNumber == 0) {
                    LogUtil.a(HwExerciseAdviceAw70Manager.TAG, "saveTrackData finished broardCast to health");
                    Intent intent = new Intent("com.huawei.health.workout_record_save_finish");
                    intent.setPackage(HwExerciseAdviceAw70Manager.this.mContext.getPackageName());
                    HwExerciseAdviceAw70Manager.this.mContext.sendBroadcast(intent, LocalBroadcast.c);
                    HwExerciseUtils.triggerHiHealthCloutSync();
                }
            }
        });
    }

    private void saveWorkoutRecordDataToTrack(JSONObject jSONObject, JSONArray jSONArray, int i, JSONArray jSONArray2) {
        Map<Long, double[]> map = this.mGpsWorkoutMap.get(Integer.valueOf(i));
        List<JSONArray> list = this.mWorkoutRecordJumpDataMap.get(Integer.valueOf(i));
        MotionPath motionPath = new MotionPath();
        if (map != null) {
            motionPath.setLbsDataMap(map);
        }
        MotionPathSimplify motionPathSimplify = new MotionPathSimplify();
        setSimplifyData(jSONObject, motionPathSimplify, jSONArray2, motionPath);
        try {
            packTrackData(jSONArray, motionPath, motionPathSimplify, list);
            WorkoutDisplayInfo workoutDisplayInfo = new WorkoutDisplayInfo();
            this.mAw70ManagerUtil.checkWorkoutDisplayInfo(jSONObject.getInt("workout_type"), map, workoutDisplayInfo);
            this.mAw70ManagerUtil.setWorkoutRecordSimplify(jSONObject, workoutDisplayInfo, motionPathSimplify);
            HashMap hashMap = new HashMap(16);
            this.mAw70ManagerUtil.saveSportData(hashMap, jSONObject);
            assembleSportData(hashMap, jSONObject);
            motionPathSimplify.setSportData(hashMap);
            saveTrackDataToHealthLib(motionPathSimplify, motionPath, i);
            LogUtil.a(TAG, "save workout Record DataToTrack finish");
        } catch (JSONException unused) {
            LogUtil.b(TAG, "saveWorkoutRecordDataToTrack JSONException");
        }
    }

    private void assembleSportData(Map<String, Integer> map, JSONObject jSONObject) {
        int optInt = jSONObject.optInt("mMaxRunSpeed", -1);
        if (optInt != -1) {
            map.put("max_spriting_speed", Integer.valueOf(optInt));
        }
        int optInt2 = jSONObject.optInt("mRunScore", -1);
        if (optInt2 != -1) {
            map.put("run_score", Integer.valueOf(optInt2));
        }
        int optInt3 = jSONObject.optInt("mMoveScore", -1);
        if (optInt3 != -1) {
            map.put("breakthrough_score", Integer.valueOf(optInt3));
        }
        int optInt4 = jSONObject.optInt("mJumpScore", -1);
        if (optInt4 != -1) {
            map.put("jump_score", Integer.valueOf(optInt4));
        }
        int optInt5 = jSONObject.optInt("mTotalScore", -1);
        if (optInt5 != -1) {
            map.put("overall_score", Integer.valueOf(optInt5));
        }
        int optInt6 = jSONObject.optInt("mExplosiveScore", -1);
        if (optInt6 != -1) {
            map.put("burst_score", Integer.valueOf(optInt6));
        }
        int optInt7 = jSONObject.optInt("mIntenseScore", -1);
        if (optInt7 != -1) {
            map.put("sport_intensity_score", Integer.valueOf(optInt7));
        }
    }

    private void setSimplifyData(JSONObject jSONObject, MotionPathSimplify motionPathSimplify, JSONArray jSONArray, MotionPath motionPath) {
        try {
            int i = (int) jSONObject.getLong("workout_exercise_duration");
            if (jSONArray != null) {
                Map<Integer, Float> changePaceMapStruct = this.mAw70ManagerUtil.changePaceMapStruct(jSONArray, 0);
                motionPath.setPaceMap(changePaceMapStruct);
                motionPathSimplify.setPaceMap(changePaceMapStruct);
                Map<Integer, Float> changePaceMapStruct2 = this.mAw70ManagerUtil.changePaceMapStruct(jSONArray, 1);
                motionPath.setBritishPaceMap(changePaceMapStruct2);
                motionPathSimplify.setBritishPaceMap(changePaceMapStruct2);
                motionPathSimplify.setPartTimeMap(this.mAw70ManagerUtil.changePartTimePaceMapStruct(jSONArray, 0));
                motionPathSimplify.setBritishPartTimeMap(this.mAw70ManagerUtil.changePartTimePaceMapStruct(jSONArray, 1));
            }
            motionPathSimplify.setTotalTime(i);
            motionPathSimplify.setTotalSteps(((Integer) jSONObject.get("workout_record_step")).intValue());
        } catch (JSONException unused) {
            LogUtil.b(TAG, "JSONException");
        }
    }

    private void packTrackData(JSONArray jSONArray, MotionPath motionPath, MotionPathSimplify motionPathSimplify, List<JSONArray> list) {
        initPackTrackData();
        int length = jSONArray.length();
        LogUtil.a(TAG, "the size is ", Integer.valueOf(length), " the detail is ", jSONArray.toString());
        this.mPackTrackSummaryTotalStep = motionPathSimplify.getTotalSteps();
        this.mPackTrackTotalTime = motionPathSimplify.getTotalTime();
        dealJsonData(length, jSONArray);
        this.mAw70ManagerUtil.parseSpeedData(length, jSONArray, this.mPackTrackSpeedDataArrayList);
        ArrayList<ixt> arrayList = new ArrayList<>(16);
        this.mAw70ManagerUtil.parseJumpData(list, arrayList);
        this.mPackTrackTotalStep /= 12;
        LogUtil.a(TAG, "summaryTotalStep: ", Integer.valueOf(this.mPackTrackSummaryTotalStep), " iTotalStep: ", Integer.valueOf(this.mPackTrackTotalStep), " totaltime: ", Long.valueOf((this.mPackTrackTotalTime / 1000) / 60));
        int i = this.mPackTrackSummaryTotalStep;
        int i2 = this.mPackTrackTotalStep;
        if (i > i2 && i - i2 > (this.mPackTrackTotalTime / 1000) / 60) {
            this.mPackTrackMaxStep = dealStepData(i, i2, this.mPackTrackstepList, this.mPackTrackIndex, this.mPackTrackMaxStep);
        }
        this.mAw70ManagerUtil.setMotionList(motionPath, this.mPackTrackHeartrateList, this.mPackTrackstepList, this.mPackTrackRunningPostureList, arrayList);
        motionPath.setSpeedList(this.mPackTrackSpeedDataArrayList);
        setSimplifyDataOne(motionPathSimplify, this.mPackTrackTotalPace, this.mPackTrackTotalCount, this.mPackTrackMaxPace, this.mPackTrackTotalStep);
        setSimplifyDataTwo(motionPathSimplify, this.mPackTrackTotalHeart, this.mPackTrackHeartTotalCount, this.mPackTrackRunningPostureList, this.mPackTrackMaxStep);
    }

    private void initPackTrackData() {
        this.mPackTrackHeartrateList = new ArrayList<>(16);
        this.mPackTrackstepList = new ArrayList<>(16);
        this.mPackTrackRunningPostureList = new ArrayList<>(16);
        this.mPackTrackSpeedDataArrayList = new ArrayList<>(16);
        this.mPackTrackMaxPace = 0;
        this.mPackTrackTotalPace = 0L;
        this.mPackTrackMaxStep = 0;
        this.mPackTrackTotalStep = 0;
        this.mPackTrackTotalHeart = 0L;
        this.mPackTrackTotalCount = 0;
        this.mPackTrackHeartTotalCount = 0;
        this.mPackTrackSummaryTotalStep = 0;
        this.mPackTrackIndex = 0;
        this.mPackTrackTotalTime = 0L;
        this.mPackTrackCount = 1;
    }

    private void dealJsonData(int i, JSONArray jSONArray) {
        JSONObject jSONObject;
        String str = "workoutDataInfoLists";
        String str2 = "dataHeader";
        int i2 = i;
        int i3 = 0;
        while (i3 < i2) {
            try {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i3);
                int i4 = jSONObject2.getJSONObject(str2).getInt(HwExerciseConstants.JSON_NAME_TIME_INTERVAL);
                int length = jSONObject2.getJSONObject(str2).getJSONArray(str).length();
                this.mPackTrackTotalCount += length;
                int i5 = 0;
                while (i5 < length) {
                    JSONObject jSONObject3 = jSONObject2.getJSONObject(str2).getJSONArray(str).getJSONObject(i5);
                    long j = jSONObject2.getJSONObject(str2).getLong("time") + (i4 * i5 * 1000);
                    int i6 = jSONObject3.getInt("data2");
                    if (i6 > this.mPackTrackMaxPace) {
                        this.mPackTrackMaxPace = i6;
                    }
                    String str3 = str;
                    String str4 = str2;
                    this.mPackTrackTotalPace += i6;
                    int i7 = jSONObject3.getInt("data1");
                    if (i7 == 0 || i7 == 255) {
                        jSONObject = jSONObject2;
                    } else {
                        this.mAw70ManagerUtil.setHeartRateList(j, i7, this.mPackTrackHeartrateList);
                        jSONObject = jSONObject2;
                        this.mPackTrackTotalHeart += i7;
                        this.mPackTrackHeartTotalCount++;
                    }
                    int i8 = jSONObject3.getJSONObject("mRunPostureDataInfo").getInt("mCadence");
                    this.mPackTrackTotalStep += i8;
                    this.mAw70ManagerUtil.setStepList(i8, j, this.mPackTrackstepList);
                    if (i8 > this.mPackTrackMaxStep) {
                        this.mPackTrackMaxStep = i8;
                        this.mPackTrackIndex = this.mPackTrackstepList.size() - 1;
                    }
                    this.mAw70ManagerUtil.dealRunPostData(jSONObject3, this.mPackTrackRunningPostureList, i4, this.mPackTrackCount);
                    this.mPackTrackCount++;
                    i5++;
                    jSONObject2 = jSONObject;
                    str = str3;
                    str2 = str4;
                }
                i3++;
                i2 = i;
            } catch (JSONException unused) {
                LogUtil.b(TAG, "packTrackData JSONException");
                return;
            }
        }
    }

    private int dealStepData(int i, int i2, ArrayList<StepRateData> arrayList, int i3, int i4) {
        int size = (int) (((i - i2) / arrayList.size()) + 0.5f);
        int size2 = arrayList.size();
        for (int i5 = 0; i5 < size2; i5++) {
            arrayList.get(i5).setStepRate(arrayList.get(i5).getStepRate() + size);
        }
        return i3 < size2 ? arrayList.get(i3).getStepRate() : i4;
    }

    private void setSimplifyDataOne(MotionPathSimplify motionPathSimplify, long j, int i, int i2, int i3) {
        motionPathSimplify.setSportId("gps_maptracking_" + jec.c(new Date(), "yyyyMMddHHmmssSSS"));
        motionPathSimplify.setSportType(258);
        if (j == 0 || i == 0) {
            motionPathSimplify.setAvgPace(0.0f);
        } else {
            motionPathSimplify.setAvgPace(3600.0f / ((j / 10.0f) / i));
        }
        if (i2 == 0) {
            motionPathSimplify.setBestPace(0.0f);
        } else {
            motionPathSimplify.setBestPace(3600.0f / (i2 / 10.0f));
        }
        motionPathSimplify.setAvgStepRate((int) ((i3 / (i * 5.0f)) * 60.0f));
    }

    private void setSimplifyDataTwo(MotionPathSimplify motionPathSimplify, long j, int i, ArrayList<ffs> arrayList, int i2) {
        motionPathSimplify.setBestStepRate(i2);
        if (i == 0) {
            motionPathSimplify.setAvgHeartRate(0);
        } else {
            motionPathSimplify.setAvgHeartRate(Long.valueOf(j / i).intValue());
        }
        Bundle awQ_ = fft.awQ_(arrayList);
        motionPathSimplify.saveAvgGroundContactTime(awQ_.getInt("avgGroundContactTime"));
        motionPathSimplify.saveAvgGroundImpactAcceleration(awQ_.getInt("avgGroundImpactAcceleration"));
        motionPathSimplify.saveAvgSwingAngle(awQ_.getInt("avgSwingAngle"));
        motionPathSimplify.saveAvgEversionExcursion(awQ_.getInt("avgEversionExcursion"));
        motionPathSimplify.saveAvgForeFootStrikePattern(awQ_.getInt("foreFootStrikePatternPercentage"));
        motionPathSimplify.saveAvgWholeFootStrikePattern(awQ_.getInt("wholeFootStrikePatternPercentage"));
        motionPathSimplify.saveAvgHindFootStrikePattern(awQ_.getInt("hindFootStrikePatternPercentage"));
        motionPathSimplify.saveAverageHangTime(awQ_.getInt("averageHangTime", -1));
        motionPathSimplify.saveGroundHangTimeRate(awQ_.getFloat("groundHangTimeRate", -1.0f));
        motionPathSimplify.setTrackType(4);
    }

    private String getAw70Identify() {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_AW70_DEVICES, null, TAG);
        DeviceInfo deviceInfo = deviceList.size() > 0 ? deviceList.get(0) : null;
        if (deviceInfo != null) {
            return deviceInfo.getDeviceIdentify();
        }
        return null;
    }

    public void getRunPlanRecord(JSONObject jSONObject, IBaseResponseCallback iBaseResponseCallback) throws JSONException {
        synchronized (LOCK_OBJECT) {
            DeviceCommand deviceCommand = new DeviceCommand();
            deviceCommand.setServiceID(22);
            deviceCommand.setCommandID(4);
            String aw70Identify = getAw70Identify();
            if (!TextUtils.isEmpty(aw70Identify)) {
                deviceCommand.setmIdentify(aw70Identify);
            }
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
            sHwDeviceMgr.sendDeviceData(deviceCommand);
            synchronized (getGetRunPlanRecordCallbackList()) {
                sGetRunPlanRecordCallbackList.add(iBaseResponseCallback);
            }
        }
    }

    public void getRunPlanRecordInfo(JSONObject jSONObject, IBaseResponseCallback iBaseResponseCallback) throws JSONException {
        synchronized (LOCK_OBJECT) {
            DeviceCommand deviceCommand = new DeviceCommand();
            deviceCommand.setServiceID(22);
            deviceCommand.setCommandID(5);
            String aw70Identify = getAw70Identify();
            if (!TextUtils.isEmpty(aw70Identify)) {
                deviceCommand.setmIdentify(aw70Identify);
            }
            String a2 = cvx.a(jSONObject.getInt("id"));
            String d = cvx.d(a2.length() / 2);
            String str = cvx.e(2) + d + a2;
            String d2 = cvx.d(str.length() / 2);
            String e = cvx.e(129);
            StringBuilder sb = new StringBuilder(16);
            sb.append(e);
            sb.append(d2);
            sb.append(str);
            deviceCommand.setDataLen(sb.length() / 2);
            deviceCommand.setDataContent(cvx.a(sb.toString()));
            sHwDeviceMgr.sendDeviceData(deviceCommand);
            synchronized (getGetRunPlanRecordInfoCallbackList()) {
                sGetRunPlanRecordInfoCallbackList.add(iBaseResponseCallback);
            }
        }
    }

    public void getResult(byte[] bArr) {
        blt.d(TAG, bArr, "HwExerciseAdviceManager getResult(): ");
        if (cwl.b(bArr)) {
            return;
        }
        String d = cvx.d(bArr);
        if (d.length() > 4) {
            try {
                cwe a2 = new cwl().a(d.substring(4, d.length()));
                dealResultData(bArr[1], a2.e(), a2.a());
                return;
            } catch (cwg | NumberFormatException e) {
                LogUtil.b(TAG, "receive tlv commander exception:", ExceptionUtils.d(e));
                return;
            }
        }
        LogUtil.h(TAG, "receive commander exception");
    }

    private void dealResultData(int i, List<cwd> list, List<cwe> list2) {
        switch (i) {
            case 1:
                if (list != null && !list.isEmpty() && Integer.parseInt(list.get(0).e(), 16) == 127) {
                    dealRunPlanParameterCallbackList(list);
                    break;
                } else {
                    dealTlvFatherListData(list2);
                    break;
                }
                break;
            case 2:
                dealSetRunPlanData(list);
                break;
            case 3:
                dealSetRunPlanReminderSwitch(list);
                break;
            case 4:
                if (!list.isEmpty() && Integer.parseInt(list.get(0).e(), 16) == 127) {
                    dealGetRunPlanRecordCallbackList(list);
                    break;
                } else {
                    dealRunPlanRecordData(list2);
                    break;
                }
                break;
            case 5:
                if (!list.isEmpty() && CommonUtil.a(list.get(0).e(), 16) == 127) {
                    dealGetRunPlanRecordInfoCallbackList(list);
                    break;
                } else {
                    ArrayList arrayList = new ArrayList(16);
                    this.mAw70ManagerUtil.dealRunPlanRecordTlvList(list2, arrayList);
                    syncGetRunPlanRecordInfoCallback(arrayList);
                    break;
                }
                break;
            case 6:
                ArrayList arrayList2 = new ArrayList(16);
                this.mAw70ManagerUtil.dealNotificationRunPlanRecordInfo(list2, arrayList2);
                syncNotificationRunPlanRecordInfoCallback(arrayList2);
                break;
            default:
                LogUtil.h(TAG, "dealResultData default");
                break;
        }
    }

    private void syncNotificationRunPlanRecordInfoCallback(List<RunPlanRecordInfo> list) {
        synchronized (getNotificationRunPlanRecordInfoCallbackList()) {
            Iterator<IBaseResponseCallback> it = sNotificationRunPlanRecordInfoCallbackList.iterator();
            while (it.hasNext()) {
                it.next().d(100000, kkm.d(list, "registerNotificationRunPlanRecordInfoCallbackList"));
            }
        }
    }

    private void syncGetRunPlanRecordInfoCallback(List<RunPlanRecordInfo> list) {
        synchronized (getGetRunPlanRecordInfoCallbackList()) {
            if (!sGetRunPlanRecordInfoCallbackList.isEmpty()) {
                sGetRunPlanRecordInfoCallbackList.get(0).d(100000, kkm.d(list, "getRunPlanRecordInfo"));
                sGetRunPlanRecordInfoCallbackList.remove(0);
            }
        }
    }

    private void dealGetRunPlanRecordInfoCallbackList(List<cwd> list) {
        synchronized (getGetRunPlanRecordInfoCallbackList()) {
            if (!sGetRunPlanRecordInfoCallbackList.isEmpty()) {
                int parseInt = Integer.parseInt(list.get(0).c(), 16);
                sGetRunPlanRecordInfoCallbackList.get(0).d(parseInt, kkm.d(Integer.valueOf(parseInt), "getRunPlanRecordInfo"));
                sGetRunPlanRecordInfoCallbackList.remove(0);
            }
        }
    }

    private void dealGetRunPlanRecordCallbackList(List<cwd> list) {
        synchronized (getGetRunPlanRecordCallbackList()) {
            if (!sGetRunPlanRecordCallbackList.isEmpty()) {
                int parseInt = Integer.parseInt(list.get(0).c(), 16);
                sGetRunPlanRecordCallbackList.get(0).d(parseInt, kkm.d(Integer.valueOf(parseInt), "getRunPlanRecord"));
                sGetRunPlanRecordCallbackList.remove(0);
            }
        }
    }

    private void dealRunPlanParameterCallbackList(List<cwd> list) {
        synchronized (getRunPlanParameterCallbackList()) {
            if (!sRunPlanParameterCallbackList.isEmpty()) {
                int parseInt = Integer.parseInt(list.get(0).c(), 16);
                sRunPlanParameterCallbackList.get(0).d(parseInt, kkm.d(Integer.valueOf(parseInt), "getRunPlanParameterforhealth"));
                sRunPlanParameterCallbackList.remove(0);
            }
        }
    }

    private void dealRunPlanRecordData(List<cwe> list) {
        RunPlanRecord runPlanRecord = new RunPlanRecord();
        ArrayList arrayList = new ArrayList(16);
        for (cwe cweVar : list) {
            for (cwd cwdVar : cweVar.e()) {
                if (Integer.parseInt(cwdVar.e(), 16) == 2) {
                    runPlanRecord.setRunPlanRecordCount(Integer.parseInt(cwdVar.c(), 16));
                } else {
                    LogUtil.h(TAG, "dealRunPlanRecordData default branch");
                }
            }
            dealRunPlanRecordTlvChildFathers(cweVar, arrayList);
        }
        runPlanRecord.setRunPlanRecordStructList(arrayList);
        synchronized (getGetRunPlanRecordCallbackList()) {
            if (!sGetRunPlanRecordCallbackList.isEmpty()) {
                sGetRunPlanRecordCallbackList.get(0).d(100000, kkm.d(runPlanRecord, "getRunPlanRecord"));
                sGetRunPlanRecordCallbackList.remove(0);
            }
        }
    }

    private void dealRunPlanRecordTlvChildFathers(cwe cweVar, List<RunPlanRecordStruct> list) {
        for (cwe cweVar2 : cweVar.a()) {
            RunPlanRecordStruct runPlanRecordStruct = new RunPlanRecordStruct();
            for (cwd cwdVar : cweVar2.e()) {
                switch (Integer.parseInt(cwdVar.e(), 16)) {
                    case 6:
                        runPlanRecordStruct.setRunPlanWorkoutId(Integer.parseInt(cwdVar.c(), 16));
                        break;
                    case 7:
                        runPlanRecordStruct.setRunPlanRecordId(Integer.parseInt(cwdVar.c(), 16));
                        break;
                    case 8:
                        runPlanRecordStruct.setRunPlanIndexCount(Integer.parseInt(cwdVar.c(), 16));
                        break;
                    case 9:
                        runPlanRecordStruct.setPaceIndexCount(Integer.parseInt(cwdVar.c(), 16));
                        break;
                    default:
                        LogUtil.h(TAG, "dealRunPlanRecordTlvChildFathers default branch");
                        break;
                }
            }
            list.add(runPlanRecordStruct);
        }
    }

    private void dealSetRunPlanReminderSwitch(List<cwd> list) {
        int i = 0;
        for (cwd cwdVar : list) {
            if (Integer.parseInt(cwdVar.e(), 16) == 127) {
                i = Integer.parseInt(cwdVar.c(), 16);
            } else {
                LogUtil.h(TAG, "dealSetRunPlanReminderSwitch default branch");
            }
        }
        synchronized (getSetRunPlanReminderSwitchCallbackList()) {
            if (!sSetRunPlanReminderSwitchCallbackList.isEmpty()) {
                sSetRunPlanReminderSwitchCallbackList.get(0).d(i, kkm.d(Integer.valueOf(i), "setRunPlanReminderSwitch"));
                sSetRunPlanReminderSwitchCallbackList.remove(0);
            }
        }
    }

    private void dealSetRunPlanData(List<cwd> list) {
        int i = 0;
        for (cwd cwdVar : list) {
            if (Integer.parseInt(cwdVar.e(), 16) == 127) {
                i = Integer.parseInt(cwdVar.c(), 16);
            } else {
                LogUtil.h(TAG, "dealSetRunPlanData default branch");
            }
        }
        synchronized (getSetRunPlanCallbackList()) {
            if (!sSetRunPlanCallbackList.isEmpty()) {
                sSetRunPlanCallbackList.get(0).d(i, kkm.d(Integer.valueOf(i), "setRunPlan"));
                sSetRunPlanCallbackList.remove(0);
            }
        }
    }

    private void dealTlvFatherListData(List<cwe> list) {
        RunPlanParameter runPlanParameter = new RunPlanParameter();
        if (list != null && !list.isEmpty()) {
            Iterator<cwe> it = list.iterator();
            while (it.hasNext()) {
                dealTlvsData(it.next().e(), runPlanParameter);
            }
        }
        synchronized (getRunPlanParameterCallbackList()) {
            if (!sRunPlanParameterCallbackList.isEmpty()) {
                int runPlanSyncSize = runPlanParameter.getRunPlanSyncSize();
                runPlanParameter.setRunPlanSyncSizePre(runPlanSyncSize / 256);
                runPlanParameter.setRunPlanSyncSizeSub(runPlanSyncSize % 256);
                sRunPlanParameterCallbackList.get(0).d(100000, kkm.d(runPlanParameter, "getRunPlanParameterforhealth"));
                sRunPlanParameterCallbackList.remove(0);
            }
        }
    }

    private void dealTlvsData(List<cwd> list, RunPlanParameter runPlanParameter) {
        for (cwd cwdVar : list) {
            int parseInt = Integer.parseInt(cwdVar.e(), 16);
            if (parseInt == 2) {
                runPlanParameter.setRunPlanTotalSign(cvx.e(cwdVar.c()));
            } else if (parseInt == 3) {
                runPlanParameter.setRunPlanSign(cwdVar.c());
            } else if (parseInt == 4) {
                runPlanParameter.setRunPlanAlgorithmType(Integer.parseInt(cwdVar.c(), 16));
            } else if (parseInt == 5) {
                runPlanParameter.setRunPlanAlgorithmVersion(cvx.e(cwdVar.c()));
            } else if (parseInt == 6) {
                runPlanParameter.setRunPlanSyncSize(Integer.parseInt(cwdVar.c(), 16));
            } else {
                LogUtil.h(TAG, "dealTlvFatherListData default branch");
            }
        }
    }

    public void setIsDetailSyncing(boolean z) {
        LogUtil.a(TAG, "isDetailSyncing: ", Boolean.valueOf(z));
        this.mIsDetailSyncing = z;
    }

    public void syncDeviceWorkoutRecordInfo() {
        getDeviceSupportCapacity();
        LogUtil.a(TAG, "calling syncDeviceWorkoutRecordInfo");
        DeviceInfo currentDeviceInfo = getCurrentDeviceInfo();
        if (this.mIsDeviceSupportCapacity) {
            LogUtil.a(TAG, "syncDeviceWorkoutRecordInfo isDetailSyncing: ", Boolean.valueOf(this.mIsDetailSyncing));
            if (this.mIsDetailSyncing) {
                LogUtil.h(TAG, "is syning detail, please wait");
                return;
            }
            setIsAw70SyncSuccess(false);
            this.mIsDetailSyncing = true;
            if (currentDeviceInfo == null || currentDeviceInfo.getDeviceConnectState() != 2) {
                LogUtil.a(TAG, "no device is connected.");
                this.mIsDetailSyncing = false;
            } else {
                this.mThreadPool.execute(new Runnable() { // from class: com.huawei.hwdevice.phoneprocess.mgr.exercise.multisync.HwExerciseAdviceAw70Manager.5
                    @Override // java.lang.Runnable
                    public void run() {
                        HwExerciseAdviceAw70Manager.this.mWorkoutDataNumber = 0;
                        HwExerciseAdviceAw70Manager.this.mCurrentTime = System.currentTimeMillis();
                        HwExerciseAdviceAw70Manager hwExerciseAdviceAw70Manager = HwExerciseAdviceAw70Manager.this;
                        hwExerciseAdviceAw70Manager.mCurrentDeviceId = hwExerciseAdviceAw70Manager.mAw70ManagerUtil.getCurrentDeviceId();
                        HwExerciseAdviceAw70Manager hwExerciseAdviceAw70Manager2 = HwExerciseAdviceAw70Manager.this;
                        hwExerciseAdviceAw70Manager2.mLastSyncTime = hwExerciseAdviceAw70Manager2.getLastSyncTime();
                        LogUtil.a(HwExerciseAdviceAw70Manager.TAG, "syncDeviceWorkoutRecordInfo,currentTime - lastSyncTime: ", Long.valueOf(HwExerciseAdviceAw70Manager.this.mCurrentTime - HwExerciseAdviceAw70Manager.this.mLastSyncTime));
                        HwExerciseAdviceAw70Manager.this.dealMoreData();
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0014, code lost:
    
        if (r7 >= 0) goto L9;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void dealMoreData() {
        /*
            r9 = this;
            long r0 = r9.mLastSyncTime
            r2 = 0
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            r5 = 604800000(0x240c8400, double:2.988109026E-315)
            if (r4 == 0) goto L16
            long r7 = r9.mCurrentTime
            long r7 = r7 - r0
            int r0 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
            if (r0 > 0) goto L16
            int r0 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            if (r0 >= 0) goto L1b
        L16:
            long r0 = r9.mCurrentTime
            long r0 = r0 - r5
            r9.mLastSyncTime = r0
        L1b:
            long r0 = r9.mLastSyncTime
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            long r1 = r9.mCurrentTime
            java.lang.Long r1 = java.lang.Long.valueOf(r1)
            java.lang.String r2 = "syncDeviceWorkoutRecordInfo, starttime: "
            java.lang.String r3 = " endtime: "
            java.lang.Object[] r0 = new java.lang.Object[]{r2, r0, r3, r1}
            java.lang.String r1 = "HwExerciseAdviceAw70Manager"
            com.huawei.hwlogsmodel.LogUtil.a(r1, r0)
            com.huawei.haf.handler.ExtendHandler r0 = r9.mExtendHandler
            r2 = 300000(0x493e0, double:1.482197E-318)
            r4 = 0
            r0.sendEmptyMessage(r4, r2)
            java.util.List<org.json.JSONObject> r0 = r9.mWorkoutDetailDataList
            r0.clear()
            java.util.List<com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.PaceIndexStruct> r0 = r9.mWorkoutRecordPaceMapIdList
            r0.clear()
            java.util.List<org.json.JSONObject> r0 = r9.mWorkoutRecordPaceMapList
            r0.clear()
            java.util.Map<java.lang.Integer, java.util.List<org.json.JSONArray>> r0 = r9.mWorkoutRecordJumpDataMap
            r0.clear()
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>()
            java.lang.String r2 = "startTime"
            long r5 = r9.mLastSyncTime     // Catch: org.json.JSONException -> L67
            r0.put(r2, r5)     // Catch: org.json.JSONException -> L67
            java.lang.String r2 = "endTime"
            long r5 = r9.mCurrentTime     // Catch: org.json.JSONException -> L67
            r0.put(r2, r5)     // Catch: org.json.JSONException -> L67
            goto L70
        L67:
            java.lang.String r2 = "dealMoreData JSONException"
            java.lang.Object[] r2 = new java.lang.Object[]{r2}
            com.huawei.hwlogsmodel.LogUtil.b(r1, r2)
        L70:
            boolean r1 = r9.isSupportExerciseDynamicCapability()
            r9.mDeviceCapability = r4
            if (r1 == 0) goto L85
            com.huawei.hwdevice.phoneprocess.mgr.exercise.multisync.HwWorkoutServiceAw70Manager r1 = com.huawei.hwdevice.phoneprocess.mgr.exercise.multisync.HwWorkoutServiceAw70Manager.getInstance()
            com.huawei.hwdevice.phoneprocess.mgr.exercise.multisync.HwExerciseAdviceAw70Manager$6 r2 = new com.huawei.hwdevice.phoneprocess.mgr.exercise.multisync.HwExerciseAdviceAw70Manager$6
            r2.<init>()
            r1.getWorkoutCapability(r2)
            goto L88
        L85:
            r9.getDeviceWorkoutRecordIdList(r0)
        L88:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hwdevice.phoneprocess.mgr.exercise.multisync.HwExerciseAdviceAw70Manager.dealMoreData():void");
    }

    private boolean isSupportExerciseDynamicCapability() {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_AW70_DEVICES, null, TAG);
        DeviceInfo deviceInfo = deviceList.size() > 0 ? deviceList.get(0) : null;
        Map<String, DeviceCapability> queryDeviceCapability = jsz.b(BaseApplication.getContext()).queryDeviceCapability(2, "", TAG);
        if (deviceInfo == null || queryDeviceCapability.isEmpty()) {
            LogUtil.h(TAG, "aw70 capability do not get.");
            return false;
        }
        DeviceCapability deviceCapability = queryDeviceCapability.get(deviceInfo.getDeviceIdentify());
        if (deviceCapability != null) {
            return deviceCapability.isSupportWorkoutCapabilicy();
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getDetailData() {
        this.mWorkoutDataList.clear();
        syncWorkoutDetailData(this.mWorkoutRecord);
        syncRunPlanDetailData(this.mRunPlanRecord);
        if (!this.mWorkoutDataList.isEmpty()) {
            getWorkoutDetailData(this.mWorkoutDataList.get(0));
        } else if (this.mIsDeviceSupportPaceMap && !this.mWorkoutRecordPaceMapIdList.isEmpty()) {
            getWorkoutRecordPaceMap(this.mWorkoutRecordPaceMapIdList.get(0));
        } else {
            getWorkoutRecordJumpData();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getWorkoutRecordJumpData() {
        Object[] objArr = new Object[2];
        objArr[0] = "enter getWorkoutRecordJumpData mWorkoutRecord == null is ";
        objArr[1] = Boolean.valueOf(this.mWorkoutRecord == null);
        LogUtil.a(TAG, objArr);
        JSONObject jSONObject = this.mWorkoutRecord;
        if (jSONObject == null) {
            return;
        }
        try {
            if (this.mSliceRecordIdIndex < jSONObject.getJSONArray("workoutRecordStructList").length()) {
                getWorkoutSliceData(new ArrayList<>(16));
            } else {
                LogUtil.a(TAG, "getWorkoutRecordSliceData end");
                notifyDetailSyncComplete(0, "getWorkoutRecordSliceData");
            }
        } catch (JSONException unused) {
            LogUtil.b(TAG, "getWorkoutRecordSliceData JSONException");
        }
    }

    private void getWorkoutSliceData(final ArrayList<JSONArray> arrayList) {
        JSONObject jSONObject = this.mWorkoutRecord;
        if (jSONObject == null) {
            LogUtil.h(TAG, "getWorkoutSliceData mWorkoutRecord is null");
            return;
        }
        try {
            JSONObject jSONObject2 = jSONObject.getJSONArray("workoutRecordStructList").getJSONObject(this.mSliceRecordIdIndex);
            if (jSONObject2.getInt("mWorkoutSliceNumber") > 0) {
                HwWorkoutServiceAw70Manager.getInstance().getWorkoutRecordSliceData(jSONObject2.getInt("workout_record_id"), this.mSliceNumberIndex, new IBaseResponseCallback() { // from class: com.huawei.hwdevice.phoneprocess.mgr.exercise.multisync.HwExerciseAdviceAw70Manager.7
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public void d(int i, Object obj) {
                        LogUtil.a(HwExerciseAdviceAw70Manager.TAG, "getWorkoutSliceData errorCode: ", Integer.valueOf(i));
                        HwExerciseAdviceAw70Manager.this.doWorkoutSliceResponse(i, obj, arrayList);
                    }
                });
            } else {
                this.mSliceNumberIndex = 0;
                this.mSliceRecordIdIndex++;
                getWorkoutRecordJumpData();
            }
        } catch (JSONException unused) {
            LogUtil.b(TAG, "getWorkoutSliceData JSONException");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doWorkoutSliceResponse(int i, Object obj, ArrayList<JSONArray> arrayList) {
        if (i == 100000) {
            if (obj instanceof Map) {
                try {
                    arrayList.add(new JSONArray(((Map) obj).get("value").toString()));
                    this.mSliceNumberIndex++;
                    JSONObject jSONObject = this.mWorkoutRecord.getJSONArray("workoutRecordStructList").getJSONObject(this.mSliceRecordIdIndex);
                    int i2 = jSONObject.getInt("mWorkoutSliceNumber");
                    int i3 = this.mSliceNumberIndex;
                    if (i3 < i2) {
                        LogUtil.a(TAG, "getWorkoutSliceData mSliceNumberIndex: ", Integer.valueOf(i3), " workoutSliceNumber: ", Integer.valueOf(i2), " jumpDataResult size: ", Integer.valueOf(arrayList.size()));
                        getWorkoutSliceData(arrayList);
                    } else {
                        this.mSliceNumberIndex = 0;
                        this.mSliceRecordIdIndex++;
                        this.mWorkoutRecordJumpDataMap.put(Integer.valueOf(jSONObject.getInt("workout_record_id")), arrayList);
                        getWorkoutRecordJumpData();
                    }
                    return;
                } catch (JSONException unused) {
                    LogUtil.b(TAG, "getWorkoutRecordSliceData onResponse JSONException");
                    return;
                }
            }
            return;
        }
        notifyDetailSyncComplete(i, "getWorkoutSliceData");
        sendAw70WorkoutSyncEvent(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getWorkoutRecordPaceMap(PaceIndexStruct paceIndexStruct) {
        HwWorkoutServiceAw70Manager.getInstance().getWorkoutRecordPaceMap(paceIndexStruct, this.mGetWorkoutRecordPaceMapCallback);
    }

    private void getDeviceWorkoutRecordStatistic() {
        try {
            JSONObject jSONObject = this.mWorkoutRecord;
            if (jSONObject == null) {
                LogUtil.h(TAG, "mWorkoutRecord is null");
                return;
            }
            JSONArray jSONArray = jSONObject.getJSONArray("workoutRecordStructList");
            if (this.mWorkoutRecordStatisticIndex < jSONArray.length()) {
                int i = jSONArray.getJSONObject(this.mWorkoutRecordStatisticIndex).getInt("workout_record_id");
                LogUtil.a(TAG, "getWorkoutRecord id size: ", Integer.valueOf(jSONArray.length()), " id: ", Integer.valueOf(i));
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("id", i);
                HwWorkoutServiceAw70Manager.getInstance().getWorkoutRecordStatistic(jSONObject2, new IBaseResponseCallback() { // from class: com.huawei.hwdevice.phoneprocess.mgr.exercise.multisync.HwExerciseAdviceAw70Manager.8
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public void d(int i2, Object obj) {
                        LogUtil.a(HwExerciseAdviceAw70Manager.TAG, "enter getDeviceWorkoutRecordStatistic response: ", Integer.valueOf(i2), "  mWorkoutRecordStatisticIndex is: ", Integer.valueOf(HwExerciseAdviceAw70Manager.this.mWorkoutRecordStatisticIndex));
                        try {
                            if (i2 == 100000) {
                                HwExerciseAdviceAw70Manager.this.doWorkoutRecordSuccess(obj);
                            } else {
                                HwExerciseAdviceAw70Manager.this.notifyDetailSyncComplete(i2, "getDeviceWorkoutRecordStatistic");
                                HwExerciseAdviceAw70Manager.this.sendAw70WorkoutSyncEvent(i2);
                                iyv.e(i2);
                            }
                        } catch (JSONException unused) {
                            LogUtil.b(HwExerciseAdviceAw70Manager.TAG, "getDeviceWorkoutRecordStatistic JSONException");
                            iyv.e(i2);
                        }
                    }
                });
                return;
            }
            this.mAw70ManagerUtil.printWorkoutRecordInfo(this.mWorkoutRecordsStatistic);
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("startTime", this.mLastSyncTime);
            jSONObject3.put("endTime", this.mCurrentTime);
            getDeviceRunPlanRecordIdList(jSONObject3);
        } catch (JSONException unused) {
            LogUtil.b(TAG, "getDeviceWorkoutRecordStatistic JSONException");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doWorkoutRecordSuccess(Object obj) throws JSONException {
        if (obj instanceof Map) {
            Map map = (Map) obj;
            if (map.get("value") instanceof String) {
                JSONObject jSONObject = new JSONObject((String) map.get("value"));
                this.mWorkoutRecordsStatistic.put(jSONObject.getInt("workout_record_id"), jSONObject);
                this.mWorkoutRecordStatisticIndex++;
                this.mGpsWorkoutRecordIdList.add(Integer.valueOf(jSONObject.getInt("workout_record_id")));
                getDeviceWorkoutRecordStatistic();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getDeviceRunPlanRecordStatistic() {
        try {
            JSONObject jSONObject = this.mRunPlanRecord;
            if (jSONObject != null) {
                JSONArray jSONArray = jSONObject.getJSONArray("runPlanRecordStructList");
                if (this.mRunPlanRecordStatisticIndex < this.mRunPlanRecord.getJSONArray("runPlanRecordStructList").length()) {
                    int i = jSONArray.getJSONObject(this.mRunPlanRecordStatisticIndex).getInt("run_plan_record_id");
                    LogUtil.a(TAG, "getRunPlanRecordStatistic id size: ", Integer.valueOf(jSONArray.length()), " id: ", Integer.valueOf(i));
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("id", i);
                    getRunPlanRecordInfo(jSONObject2, new IBaseResponseCallback() { // from class: com.huawei.hwdevice.phoneprocess.mgr.exercise.multisync.HwExerciseAdviceAw70Manager.9
                        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                        /* renamed from: onResponse */
                        public void d(int i2, Object obj) {
                            HwExerciseAdviceAw70Manager.this.doRunPlanRecordResponse(i2, obj);
                        }
                    });
                } else {
                    this.mAw70ManagerUtil.testRunPlanRecordInfoDebug(this.mRunPlanRecordsStatistic);
                    getDetailData();
                }
            }
        } catch (JSONException unused) {
            LogUtil.b(TAG, "getDeviceRunPlanRecordStatistic JSONException");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doRunPlanRecordResponse(int i, Object obj) {
        try {
            if (obj instanceof Map) {
                Object obj2 = ((Map) obj).get("value");
                if (i == 100000) {
                    LogUtil.a(TAG, "the value is ", obj2.toString(), " the value is instanceof a list ", Boolean.valueOf(obj2 instanceof List));
                    JSONObject jSONObject = new JSONArray(obj2.toString()).getJSONObject(0);
                    jSONObject.put("run_plan_record_info_wourkout_id", this.mRunPlanRecord.getJSONArray("runPlanRecordStructList").getJSONObject(this.mRunPlanRecordStatisticIndex).getInt(HwExerciseConstants.JSON_NAME_WORKOUT_RUN_PLAY_ID));
                    this.mRunPlanRecordsStatistic.put(jSONObject.getInt("run_plan_record_info_id"), jSONObject);
                    this.mRunPlanRecordStatisticIndex++;
                    this.mGpsRunPlanRecordIdList.add(Integer.valueOf(jSONObject.getInt("run_plan_record_info_id")));
                    getDeviceRunPlanRecordStatistic();
                } else {
                    LogUtil.h(TAG, "getDeviceRunPlanRecordStatistic error: ", Integer.valueOf(i));
                    notifyDetailSyncComplete(i, "getDeviceRunPlanRecordStatistic");
                    sendAw70WorkoutSyncEvent(i);
                }
            }
        } catch (JSONException unused) {
            LogUtil.b(TAG, "doRunPlanRecordResponse JSONException");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getDeviceWorkoutRecordIdList(JSONObject jSONObject) {
        this.mWorkoutRecord = null;
        LogUtil.a(TAG, "start syncWorkoutData getDeviceWorkoutRecordIdList");
        try {
            HwWorkoutServiceAw70Manager.getInstance().getWorkoutRecord(jSONObject, new IBaseResponseCallback() { // from class: com.huawei.hwdevice.phoneprocess.mgr.exercise.multisync.HwExerciseAdviceAw70Manager.10
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    if (i == 100000) {
                        try {
                            if (obj instanceof Map) {
                                Object obj2 = ((Map) obj).get("value");
                                if (obj2 instanceof String) {
                                    HwExerciseAdviceAw70Manager.this.mWorkoutRecord = new JSONObject((String) obj2);
                                    JSONArray jSONArray = HwExerciseAdviceAw70Manager.this.mWorkoutRecord.getJSONArray("workoutRecordStructList");
                                    ReleaseLogUtil.e(HwExerciseAdviceAw70Manager.TAG_RELEASE, "aw70 count is : ", Integer.valueOf(jSONArray.length()));
                                    ArrayList arrayList = new ArrayList(16);
                                    int length = jSONArray.length();
                                    for (int i2 = 0; i2 < length; i2++) {
                                        if (jSONArray.get(i2) instanceof JSONObject) {
                                            JSONObject jSONObject2 = (JSONObject) jSONArray.get(i2);
                                            int optInt = jSONObject2.optInt("paceIndexCount", -1);
                                            int optInt2 = jSONObject2.optInt("workout_record_id");
                                            arrayList.add(" paceIndexCount:" + optInt + " recordId:" + optInt2);
                                            HwExerciseAdviceAw70Manager.this.getPaceIndexArray(optInt2, optInt);
                                        }
                                    }
                                    LogUtil.a(HwExerciseAdviceAw70Manager.TAG, "workoutRecordDetail: ", arrayList);
                                    arrayList.clear();
                                    HwExerciseAdviceAw70Manager.this.doMoreRecord();
                                    return;
                                }
                                LogUtil.h(HwExerciseAdviceAw70Manager.TAG, "getDeviceWorkoutRecordIdList value is error");
                                return;
                            }
                        } catch (JSONException unused) {
                            LogUtil.b(HwExerciseAdviceAw70Manager.TAG, "getWorkoutRecord JSONException");
                            iyv.e(i);
                            return;
                        }
                    }
                    LogUtil.h(HwExerciseAdviceAw70Manager.TAG, "getWorkoutRecord errorCode: ", Integer.valueOf(i));
                    HwExerciseAdviceAw70Manager.this.notifyDetailSyncComplete(i, "getDeviceWorkoutRecordIdList");
                    HwExerciseAdviceAw70Manager.this.sendAw70WorkoutSyncEvent(i);
                    iyv.e(i);
                }
            });
        } catch (JSONException unused) {
            LogUtil.b(TAG, "getDeviceWorkoutRecordIdList JSONException");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doMoreRecord() {
        try {
            this.mWorkoutRecordStatisticIndex = 0;
            this.mWorkoutRecordsStatistic.clear();
            this.mGpsWorkoutRecordIdList.clear();
            JSONObject jSONObject = this.mWorkoutRecord;
            if (jSONObject != null && jSONObject.getInt("workout_record_count") > 0) {
                getDeviceWorkoutRecordStatistic();
            } else {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("startTime", this.mLastSyncTime);
                jSONObject2.put("endTime", this.mCurrentTime);
                getDeviceRunPlanRecordIdList(jSONObject2);
            }
        } catch (JSONException unused) {
            LogUtil.b(TAG, "doMoreRecord JSONException");
        }
    }

    private void getDeviceRunPlanRecordIdList(JSONObject jSONObject) {
        this.mRunPlanRecord = null;
        try {
            getRunPlanRecord(jSONObject, new IBaseResponseCallback() { // from class: com.huawei.hwdevice.phoneprocess.mgr.exercise.multisync.HwExerciseAdviceAw70Manager.11
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    if (i == 100000) {
                        try {
                            if (obj instanceof Map) {
                                HwExerciseAdviceAw70Manager.this.mRunPlanRecord = new JSONObject(((Map) obj).get("value").toString());
                                JSONArray jSONArray = HwExerciseAdviceAw70Manager.this.mRunPlanRecord.getJSONArray("runPlanRecordStructList");
                                int length = jSONArray.length();
                                for (int i2 = 0; i2 < length; i2++) {
                                    if (jSONArray.get(i2) instanceof JSONObject) {
                                        JSONObject jSONObject2 = (JSONObject) jSONArray.get(i2);
                                        HwExerciseAdviceAw70Manager.this.getPaceIndexArray(jSONObject2.optInt("run_plan_record_id"), jSONObject2.optInt("paceIndexCount", -1));
                                    }
                                }
                                HwExerciseAdviceAw70Manager.this.mRunPlanRecordStatisticIndex = 0;
                                HwExerciseAdviceAw70Manager.this.mRunPlanRecordsStatistic.clear();
                                HwExerciseAdviceAw70Manager.this.mGpsRunPlanRecordIdList.clear();
                                if (HwExerciseAdviceAw70Manager.this.mRunPlanRecord == null || HwExerciseAdviceAw70Manager.this.mRunPlanRecord.getInt("run_plan_record_count") <= 0) {
                                    HwExerciseAdviceAw70Manager.this.getDetailData();
                                    return;
                                } else {
                                    HwExerciseAdviceAw70Manager.this.getDeviceRunPlanRecordStatistic();
                                    return;
                                }
                            }
                        } catch (JSONException unused) {
                            LogUtil.b(HwExerciseAdviceAw70Manager.TAG, "getRunPlanRecord JSONException");
                            return;
                        }
                    }
                    LogUtil.h(HwExerciseAdviceAw70Manager.TAG, "getRunPlanRecord errorCode: ", Integer.valueOf(i));
                    HwExerciseAdviceAw70Manager.this.notifyDetailSyncComplete(i, HwExerciseConstants.RECORD_ID_LIST_NAME);
                    HwExerciseAdviceAw70Manager.this.sendAw70WorkoutSyncEvent(i);
                }
            });
        } catch (JSONException unused) {
            LogUtil.b(TAG, "getDeviceRunPlanRecordIdList JSONException");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getPaceIndexArray(int i, int i2) {
        if (i2 == -1) {
            PaceIndexStruct paceIndexStruct = new PaceIndexStruct();
            paceIndexStruct.setRecordId(i);
            paceIndexStruct.setPaceIndex(-1);
            this.mWorkoutRecordPaceMapIdList.add(paceIndexStruct);
            return;
        }
        if (i2 <= 0) {
            LogUtil.h(TAG, "getPaceIndexArray param is error");
            return;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            PaceIndexStruct paceIndexStruct2 = new PaceIndexStruct();
            paceIndexStruct2.setRecordId(i);
            paceIndexStruct2.setPaceIndex(i3);
            this.mWorkoutRecordPaceMapIdList.add(paceIndexStruct2);
        }
    }

    private void getDeviceSupportCapacity() {
        Map<String, DeviceCapability> queryDeviceCapability;
        DeviceCapability deviceCapability;
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_AW70_DEVICES, null, TAG);
        DeviceInfo deviceInfo = deviceList.size() > 0 ? deviceList.get(0) : null;
        if (deviceInfo != null && !TextUtils.isEmpty(deviceInfo.getDeviceIdentify()) && (queryDeviceCapability = sHwDeviceMgr.queryDeviceCapability(2, "", TAG)) != null && (deviceCapability = queryDeviceCapability.get(deviceInfo.getDeviceIdentify())) != null) {
            this.mIsDeviceSupportCapacity = deviceCapability.isSupportExerciseAdvice();
            this.mIsDeviceSupportPaceMap = deviceCapability.isSupportWorkoutRecordPaceMap();
        }
        LogUtil.a(TAG, "get Device Support runplan Capacity, capacity: ", Boolean.valueOf(this.mIsDeviceSupportCapacity), " paceMapSupport: ", Boolean.valueOf(this.mIsDeviceSupportPaceMap));
    }

    private void syncWorkoutDetailData(JSONObject jSONObject) {
        try {
            if (jSONObject == null) {
                LogUtil.h(TAG, "syncWorkoutDetailData wr is null");
                return;
            }
            JSONArray jSONArray = jSONObject.getJSONArray("workoutRecordStructList");
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                for (int i2 = 0; i2 < jSONObject2.getInt("workout_index_count"); i2++) {
                    JSONObject jSONObject3 = new JSONObject();
                    jSONObject3.put("workout_record_id", jSONObject2.get("workout_record_id"));
                    jSONObject3.put("workout_data_index", i2);
                    this.mWorkoutDataList.add(jSONObject3);
                }
            }
        } catch (JSONException unused) {
            LogUtil.b(TAG, "syncWorkoutDetailData JSONException");
        }
    }

    private void syncRunPlanDetailData(JSONObject jSONObject) {
        try {
            JSONArray jSONArray = jSONObject.getJSONArray("runPlanRecordStructList");
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                for (int i2 = 0; i2 < jSONObject2.getInt("run_plan_index_count"); i2++) {
                    JSONObject jSONObject3 = new JSONObject();
                    jSONObject3.put("workout_record_id", jSONObject2.get("run_plan_record_id"));
                    jSONObject3.put("workout_data_index", i2);
                    this.mWorkoutDataList.add(jSONObject3);
                }
            }
        } catch (JSONException unused) {
            LogUtil.b(TAG, "syncRunPlanDetailData JSONException");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getWorkoutDetailData(JSONObject jSONObject) {
        HwWorkoutServiceAw70Manager.getInstance().getWorkoutData(jSONObject, this.mDeviceWorkoutDetailCallback, this.mDeviceCapability);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAw70WorkoutSyncEvent(int i) {
        LogUtil.a(TAG, "enter sendAw70WorkoutSyncEvent result: ", Integer.valueOf(i));
        jra.b(i, this.mCurrentTime, this.mWorkoutDataNumber, getCurrentDeviceInfo());
    }

    private static List<IBaseResponseCallback> getRunPlanParameterCallbackList() {
        List<IBaseResponseCallback> list;
        synchronized (HwExerciseAdviceAw70Manager.class) {
            list = sRunPlanParameterCallbackList;
        }
        return list;
    }

    private static List<IBaseResponseCallback> getSetRunPlanCallbackList() {
        List<IBaseResponseCallback> list;
        synchronized (HwExerciseAdviceAw70Manager.class) {
            list = sSetRunPlanCallbackList;
        }
        return list;
    }

    private static List<IBaseResponseCallback> getSetRunPlanReminderSwitchCallbackList() {
        List<IBaseResponseCallback> list;
        synchronized (HwExerciseAdviceAw70Manager.class) {
            list = sSetRunPlanReminderSwitchCallbackList;
        }
        return list;
    }

    private static List<IBaseResponseCallback> getGetRunPlanRecordCallbackList() {
        List<IBaseResponseCallback> list;
        synchronized (HwExerciseAdviceAw70Manager.class) {
            list = sGetRunPlanRecordCallbackList;
        }
        return list;
    }

    private static List<IBaseResponseCallback> getGetRunPlanRecordInfoCallbackList() {
        List<IBaseResponseCallback> list;
        synchronized (HwExerciseAdviceAw70Manager.class) {
            list = sGetRunPlanRecordInfoCallbackList;
        }
        return list;
    }

    private static List<IBaseResponseCallback> getNotificationRunPlanRecordInfoCallbackList() {
        List<IBaseResponseCallback> list;
        synchronized (HwExerciseAdviceAw70Manager.class) {
            list = sNotificationRunPlanRecordInfoCallbackList;
        }
        return list;
    }
}
