package com.huawei.hwdevice.phoneprocess.mgr.exercise;

import android.util.SparseArray;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.health.ICallbackInterface;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.CommonSectionInfo;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.PaceIndexStruct;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.utils.TriathlonUtils;
import defpackage.kof;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class HwExerciseParams {
    private static final Object LOCK_INSTANCE = new Object();
    private static HwExerciseParams sInstance;
    private ICallbackInterface mCallbackInterface;
    private ExtendHandler mHwExerciseAdviceManagerHandler;
    private JSONObject mRunPlanRecord;
    private List<JSONArray> mSectionRecordsStatisticJsonObjects;
    private TriathlonUtils mTriathlonUtils;
    private JSONObject mWorkoutRecord;
    private int mDetailCount = 0;
    private int[] mSyncPlanSize = {5, 7};
    private boolean mIsGpsEnable = true;
    private SparseArray<JSONObject> mWorkoutRecordsStatisticArray = new SparseArray<>(16);
    private SparseArray<JSONObject> mRunPlanRecordsStatisticArray = new SparseArray<>(16);
    private Map<Integer, List<JSONArray>> mSwimWorkoutRecordSectionMap = new HashMap(16);
    private Map<String, Integer> mWorkoutParamMap = new HashMap(16);
    private Map<String, JSONArray> mWorkoutParamJsonArrayMap = new HashMap(16);
    private Map<Integer, List<List<CommonSectionInfo>>> mWorkoutRecordCommonSectionMap = new HashMap(16);
    private List<List<CommonSectionInfo>> mCommonRecordStatisticsSectionList = new ArrayList(16);
    private List<kof> mTrackSpo2DataList = new ArrayList(16);
    private List<JSONObject> mWorkoutDetailDataList = new ArrayList(16);
    private List<JSONObject> mWorkoutDataList = new ArrayList(16);
    private List<Integer> mGpsWorkoutRecordIdList = new ArrayList(16);
    private List<Integer> mGpsRunPlanRecordIdList = new ArrayList(16);
    private List<Integer> mSwimSectionIndexList = new ArrayList(16);
    private List<PaceIndexStruct> mWorkoutRecordPaceMapIdList = new ArrayList(16);
    private List<JSONObject> mWorkoutRecordPaceMapList = new ArrayList(16);
    private Map<Integer, Map<Long, double[]>> mGpsWorkoutMap = new HashMap(16);
    private Map<Integer, Map<Long, double[]>> mGpsRunPlanMap = new HashMap(16);
    private Map<Integer, Integer> mGpsWorkoutAndRunPlanTypeMap = new HashMap(16);
    private int mSaveDataItemNumber = 0;
    private int mWorkoutRecordStatisticIndex = 0;
    private int mRunPlanRecordStatisticIndex = 0;
    private int mSectionListStatisticIndex = 0;
    private int mCommonListStatisticIndex = 0;
    private int mWorkoutDataNumber = 0;
    private boolean mIsDetailSyncing = false;
    private long mCurrentTime = 0;
    private long mLastSyncTime = 0;
    private String mCurrentDeviceId = "";
    private int mRunPlanExecuteState = 3;
    private boolean mIsUsingEte = false;
    private String mDeviceIdentify = "";
    private int mSectionIndex = 0;
    private int mSectionCommonIndex = 0;
    private int mWorkoutTrajectories = -1;
    private int mDivingEvent = -1;
    private List<Integer> dictTypeList = new ArrayList(0);
    private Map<String, List<WorkoutSyncSuccessDetailData>> mSucceedRecordMap = new HashMap(16);
    private Map<String, List<WorkoutSyncSuccessDetailData>> mTriathlonSuccessMap = new HashMap(16);
    private String mUserId = "";

    private HwExerciseParams() {
    }

    public static HwExerciseParams getInstance() {
        HwExerciseParams hwExerciseParams;
        synchronized (LOCK_INSTANCE) {
            if (sInstance == null) {
                sInstance = new HwExerciseParams();
            }
            hwExerciseParams = sInstance;
        }
        return hwExerciseParams;
    }

    public int getDetailCount() {
        return this.mDetailCount;
    }

    public void setDetailCount(int i) {
        this.mDetailCount = i;
    }

    public JSONObject getWorkoutRecord() {
        return this.mWorkoutRecord;
    }

    public void setWorkoutRecord(JSONObject jSONObject) {
        this.mWorkoutRecord = jSONObject;
    }

    public JSONObject getRunPlanRecord() {
        return this.mRunPlanRecord;
    }

    public void setRunPlanRecord(JSONObject jSONObject) {
        this.mRunPlanRecord = jSONObject;
    }

    public int[] getSyncPlanSize() {
        return (int[]) this.mSyncPlanSize.clone();
    }

    public void setSyncPlanSize(int[] iArr) {
        if (iArr != null) {
            this.mSyncPlanSize = (int[]) iArr.clone();
        }
    }

    public boolean isGpsEnable() {
        return this.mIsGpsEnable;
    }

    public void setIsGpsEnable(boolean z) {
        this.mIsGpsEnable = z;
    }

    public SparseArray<JSONObject> getWorkoutRecordsStatisticArray() {
        return this.mWorkoutRecordsStatisticArray;
    }

    public SparseArray<JSONObject> getRunPlanRecordsStatisticArray() {
        return this.mRunPlanRecordsStatisticArray;
    }

    public Map<Integer, List<JSONArray>> getSwimWorkoutRecordSectionMap() {
        return this.mSwimWorkoutRecordSectionMap;
    }

    public Map<String, Integer> getWorkoutParamMap() {
        return this.mWorkoutParamMap;
    }

    public Map<String, JSONArray> getWorkoutParamJsonArrayMap() {
        return this.mWorkoutParamJsonArrayMap;
    }

    public Map<Integer, List<List<CommonSectionInfo>>> getWorkoutRecordCommonSectionMap() {
        return this.mWorkoutRecordCommonSectionMap;
    }

    public void setWorkoutRecordCommonSectionMap(Map<Integer, List<List<CommonSectionInfo>>> map) {
        this.mWorkoutRecordCommonSectionMap = map;
    }

    public List<JSONArray> getSectionRecordsStatisticJsonObjects() {
        return this.mSectionRecordsStatisticJsonObjects;
    }

    public void setSectionRecordsStatisticJsonObjects(List<JSONArray> list) {
        this.mSectionRecordsStatisticJsonObjects = list;
    }

    public List<List<CommonSectionInfo>> getCommonRecordStatisticsSectionList() {
        return this.mCommonRecordStatisticsSectionList;
    }

    public void setCommonRecordStatisticsSectionList(List<List<CommonSectionInfo>> list) {
        this.mCommonRecordStatisticsSectionList = list;
    }

    public List<kof> getTrackSpo2DataList() {
        return this.mTrackSpo2DataList;
    }

    public List<JSONObject> getWorkoutDetailDataList() {
        return this.mWorkoutDetailDataList;
    }

    public void setWorkoutDetailDataList(List<JSONObject> list) {
        this.mWorkoutDetailDataList = list;
    }

    public List<JSONObject> getWorkoutDataList() {
        return this.mWorkoutDataList;
    }

    public List<Integer> getGpsWorkoutRecordIdList() {
        return this.mGpsWorkoutRecordIdList;
    }

    public List<Integer> getGpsRunPlanRecordIdList() {
        return this.mGpsRunPlanRecordIdList;
    }

    public List<Integer> getSwimSectionIndexList() {
        return this.mSwimSectionIndexList;
    }

    public List<PaceIndexStruct> getWorkoutRecordPaceMapIdList() {
        return this.mWorkoutRecordPaceMapIdList;
    }

    public List<JSONObject> getWorkoutRecordPaceMapList() {
        return this.mWorkoutRecordPaceMapList;
    }

    public void setWorkoutRecordPaceMapList(List<JSONObject> list) {
        this.mWorkoutRecordPaceMapList = list;
    }

    public Map<Integer, Map<Long, double[]>> getGpsWorkoutMap() {
        return this.mGpsWorkoutMap;
    }

    public Map<Integer, Map<Long, double[]>> getGpsRunPlanMap() {
        return this.mGpsRunPlanMap;
    }

    public Map<Integer, Integer> getGpsWorkoutAndRunPlanTypeMap() {
        return this.mGpsWorkoutAndRunPlanTypeMap;
    }

    public int getSaveDataItemNumber() {
        return this.mSaveDataItemNumber;
    }

    public void setSaveDataItemNumber(int i) {
        this.mSaveDataItemNumber = i;
    }

    public int getWorkoutRecordStatisticIndex() {
        return this.mWorkoutRecordStatisticIndex;
    }

    public void setWorkoutRecordStatisticIndex(int i) {
        this.mWorkoutRecordStatisticIndex = i;
    }

    public int getRunPlanRecordStatisticIndex() {
        return this.mRunPlanRecordStatisticIndex;
    }

    public void setRunPlanRecordStatisticIndex(int i) {
        this.mRunPlanRecordStatisticIndex = i;
    }

    public int getSectionListStatisticIndex() {
        return this.mSectionListStatisticIndex;
    }

    public void setSectionListStatisticIndex(int i) {
        this.mSectionListStatisticIndex = i;
    }

    public int getCommonListStatisticIndex() {
        return this.mCommonListStatisticIndex;
    }

    public void setCommonListStatisticIndex(int i) {
        this.mCommonListStatisticIndex = i;
    }

    public int getWorkoutDataNumber() {
        return this.mWorkoutDataNumber;
    }

    public void setWorkoutDataNumber(int i) {
        this.mWorkoutDataNumber = i;
    }

    public boolean isDetailSyncing() {
        return this.mIsDetailSyncing;
    }

    public void setIsDetailSyncing(boolean z) {
        this.mIsDetailSyncing = z;
    }

    public ExtendHandler getHwExerciseAdviceManagerHandler() {
        return this.mHwExerciseAdviceManagerHandler;
    }

    public void setHwExerciseAdviceManagerHandler(ExtendHandler extendHandler) {
        this.mHwExerciseAdviceManagerHandler = extendHandler;
    }

    public long getCurrentTime() {
        return this.mCurrentTime;
    }

    public void setCurrentTime(long j) {
        this.mCurrentTime = j;
    }

    public long getLastSyncTime() {
        return this.mLastSyncTime;
    }

    public void setLastSyncTime(long j) {
        this.mLastSyncTime = j;
    }

    public String getCurrentDeviceId() {
        return this.mCurrentDeviceId;
    }

    public void setCurrentDeviceId(String str) {
        this.mCurrentDeviceId = str;
    }

    public int getRunPlanExecuteState() {
        return this.mRunPlanExecuteState;
    }

    public void setRunPlanExecuteState(int i) {
        this.mRunPlanExecuteState = i;
    }

    public boolean isUsingEte() {
        return this.mIsUsingEte;
    }

    public void setIsUsingEte(boolean z) {
        this.mIsUsingEte = z;
    }

    public String getDeviceIdentify() {
        return this.mDeviceIdentify;
    }

    public void setDeviceIdentify(String str) {
        this.mDeviceIdentify = str;
    }

    public ICallbackInterface getCallbackInterface() {
        return this.mCallbackInterface;
    }

    public void setCallbackInterface(ICallbackInterface iCallbackInterface) {
        this.mCallbackInterface = iCallbackInterface;
    }

    public int getSectionIndex() {
        return this.mSectionIndex;
    }

    public void setSectionIndex(int i) {
        this.mSectionIndex = i;
    }

    public int getSectionCommonIndex() {
        return this.mSectionCommonIndex;
    }

    public void setSectionCommonIndex(int i) {
        this.mSectionCommonIndex = i;
    }

    public int getWorkoutTrajectories() {
        return this.mWorkoutTrajectories;
    }

    public void setWorkoutTrajectories(int i) {
        this.mWorkoutTrajectories = i;
    }

    public int getDivingEvent() {
        return this.mDivingEvent;
    }

    public void setDivingEvent(int i) {
        this.mDivingEvent = i;
    }

    public List<Integer> getDictTypeList() {
        return this.dictTypeList;
    }

    public void setDictTypeList(List<Integer> list) {
        this.dictTypeList = list;
    }

    public Map<String, List<WorkoutSyncSuccessDetailData>> getSucceedRecordMap() {
        return this.mSucceedRecordMap;
    }

    public Map<String, List<WorkoutSyncSuccessDetailData>> getTriathlonSuccessMap() {
        return this.mTriathlonSuccessMap;
    }

    public String getUserId() {
        return this.mUserId;
    }

    public void setUserId(String str) {
        this.mUserId = str;
    }

    public TriathlonUtils getTriathlonUtils() {
        return this.mTriathlonUtils;
    }

    public void setTriathlonUtils(TriathlonUtils triathlonUtils) {
        this.mTriathlonUtils = triathlonUtils;
    }
}
