package com.huawei.health.plan.model.model.fitness;

import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.basefitnessadvice.callback.DataCallback;
import com.huawei.basefitnessadvice.model.FitnessSummaryRecord;
import com.huawei.basefitnessadvice.model.FitnessTrackRecord;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.health.suggestion.ResultCallback;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.main.stories.recommendcloud.constants.RecommendConstants;
import com.huawei.wisecloud.drmclient.license.HwDrmConstant;
import defpackage.asc;
import defpackage.eqa;
import defpackage.etr;
import defpackage.ett;
import defpackage.etx;
import defpackage.eub;
import defpackage.euj;
import defpackage.eup;
import defpackage.ffm;
import defpackage.gib;
import defpackage.koq;
import defpackage.kwy;
import health.compact.a.HiDateUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class FitnessHistoryModel {
    private static final int ACCOUNT_NULL = -2;
    private static final String CALORIE = "calorie";
    private static final String DATE = "date";
    private static final String DURING = "during";
    public static final int FAILURE_CODE = 1;
    private static final int FITNESS_RECORD_TYPE = 1;
    private static final String FROM = " from ";
    private static final int HAS_MORE_DATA = 1000;
    private static final Object LOCK = new Object();
    private static final int NO_MORE_DATA = 1001;
    private static final long ONE_SECOND_IN_MILLISECOND = 1000;
    private static final int PARAMETER_INVALID = -1;
    private static final int RECEIVE_DATA = 1002;
    private static final String SQL_RULE = "?[0-9][0-9]?*";
    public static final int SUCCESS_CODE = 0;
    private static final String SYNC_LOCAL_WHERE_CASE = " (workoutId GLOB ? or wearType = 3) ";
    private static final String TAG = "Suggestion_FitnessHistoryModel";
    private static final String TIMES = "times";
    private static final int WEAR_DEVICE_TYPE = 2;
    private static final String WHERE_CASE = " (workoutId GLOB ? or wearType = 3) and (sportRecordType = 0) ";
    private CopyOnWriteArrayList<WorkoutRecord> mCloudData;
    private JSONArray mCloudDataNumber;
    private long mDeletingRecord;
    private Handler mHandler;
    private HandlerThread mHandlerThread;
    private boolean mIsDownloadingWorkoutRecords;
    private boolean mIsNeedAdd;
    private ResultCallback mResultCallback;

    private FitnessHistoryModel() {
        this.mIsDownloadingWorkoutRecords = false;
        this.mIsNeedAdd = false;
        this.mDeletingRecord = 0L;
        this.mHandlerThread = new HandlerThread("fitnessRecordSync");
        this.mCloudData = new CopyOnWriteArrayList<>();
        if (!this.mHandlerThread.isAlive()) {
            this.mHandlerThread.start();
        }
        this.mHandler = new Handler(this.mHandlerThread.getLooper()) { // from class: com.huawei.health.plan.model.model.fitness.FitnessHistoryModel.1
            int start = 0;

            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                switch (message.what) {
                    case 1000:
                        FitnessHistoryModel.this.onHasMoreData(message);
                        break;
                    case 1001:
                        LogUtil.a(FitnessHistoryModel.TAG, "NO_MORE_DATA");
                        FitnessHistoryModel.this.onNoMoreData(message);
                        this.start++;
                        break;
                    case 1002:
                        LogUtil.a(FitnessHistoryModel.TAG, "RECEIVE_DATA START = ", Integer.valueOf(this.start));
                        if (this.start == 0 && (message.obj instanceof JSONArray)) {
                            FitnessHistoryModel.this.mCloudDataNumber = (JSONArray) message.obj;
                        }
                        if (FitnessHistoryModel.this.mCloudDataNumber != null && this.start < FitnessHistoryModel.this.mCloudDataNumber.length()) {
                            JSONObject optJSONObject = FitnessHistoryModel.this.mCloudDataNumber.optJSONObject(this.start);
                            if (optJSONObject == null) {
                                LogUtil.h(FitnessHistoryModel.TAG, "RECEIVE_DATA object == null");
                                break;
                            } else {
                                LogUtil.a(FitnessHistoryModel.TAG, "queryTrainCount month: ", optJSONObject.optString("months"), " count: ", optJSONObject.optString("count"));
                                if (FitnessHistoryModel.this.compareFitnessRecordCount(optJSONObject)) {
                                    this.start++;
                                    FitnessHistoryModel.this.mHandler.sendEmptyMessage(1002);
                                    break;
                                }
                            }
                        } else {
                            this.start = 0;
                            FitnessHistoryModel.this.onResultSuccess();
                            break;
                        }
                        break;
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onResultSuccess() {
        ResultCallback resultCallback = this.mResultCallback;
        if (resultCallback != null) {
            resultCallback.onResult(0, new Object());
        } else {
            LogUtil.h(TAG, "mResultCallback is null");
        }
        synchronized (LOCK) {
            this.mIsDownloadingWorkoutRecords = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onNoMoreData(Message message) {
        compareData(getWorkoutRecordsSyncByMonth(((Long) message.obj).longValue() * 1000), this.mCloudData);
        this.mCloudData.clear();
        Message obtain = Message.obtain();
        obtain.what = 1002;
        obtain.obj = new Object();
        this.mHandler.sendMessage(obtain);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onHasMoreData(Message message) {
        LogUtil.a(TAG, "HAS_MORE_DATA");
        if (message.obj instanceof Bundle) {
            Bundle bundle = (Bundle) message.obj;
            postFitnessHistoryRequest(bundle.getLong("startTime"), bundle.getLong("endTime"), bundle.getInt("pageNum") + 1);
        }
    }

    static class SingletonHolder {
        private static final FitnessHistoryModel INSTANCE = new FitnessHistoryModel();

        private SingletonHolder() {
        }
    }

    public static FitnessHistoryModel getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private void compareData(List<WorkoutRecord> list, List<WorkoutRecord> list2) {
        LogUtil.a(TAG, "compareData ");
        if (list2 == null) {
            LogUtil.h(TAG, "cloud has no fitness history record");
            return;
        }
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        if (accountInfo == null) {
            LogUtil.h(TAG, "compareData() mAccountInfo or huid is null!");
        } else if (!koq.b(list)) {
            LogUtil.a(TAG, " loudData size is ", Integer.valueOf(list2.size()), " and localData is ", Integer.valueOf(list.size()));
            compare(list, list2, accountInfo);
        } else {
            LogUtil.h(TAG, "local has no fitness history record");
            addExercisedRecord(list2);
        }
    }

    private void compare(List<WorkoutRecord> list, List<WorkoutRecord> list2, String str) {
        boolean z;
        for (WorkoutRecord workoutRecord : list2) {
            if (workoutRecord == null) {
                LogUtil.h(TAG, "waring cloudTmpRecord == null");
            } else {
                int acquireId = workoutRecord.acquireId();
                LogUtil.a(TAG, "local has same history recordId: ", Integer.valueOf(acquireId), "exerciseTime:", Long.valueOf(workoutRecord.acquireExerciseTime()));
                int i = 0;
                if (this.mIsNeedAdd && workoutRecord.acquireExerciseTime() / 1000 == this.mDeletingRecord / 1000) {
                    LogUtil.a(TAG, "compareData recordId = 0 but need to delete this record");
                    ett.i().o().e(str, "", 9, String.valueOf(acquireId));
                    this.mIsNeedAdd = false;
                    this.mDeletingRecord = 0L;
                    z = true;
                } else {
                    z = false;
                }
                while (true) {
                    if (i < list.size()) {
                        WorkoutRecord workoutRecord2 = list.get(i);
                        if (workoutRecord.acquireExerciseTime() / 1000 == workoutRecord2.acquireExerciseTime() / 1000) {
                            LogUtil.a(TAG, "the cloudTempRecord Id:", workoutRecord.acquireWorkoutId(), " exerciseTime:", Long.valueOf(workoutRecord.acquireExerciseTime()));
                            eup.b(str, workoutRecord2, acquireId);
                            break;
                        }
                        i++;
                    } else if (!z) {
                        addExercisedRecord(workoutRecord);
                    }
                }
            }
        }
    }

    public void acquireAnnualExerciseRecordByAll(final ResultCallback resultCallback) {
        if (resultCallback == null) {
            LogUtil.h(TAG, "acquireExerciseRecordByAll callback == null");
            return;
        }
        LogUtil.a(TAG, "acquireExerciseRecordByAll");
        final String str = "select record.*, info.primaryClassify as primaryClassify, info.secondClassify as secondClassify from " + ett.i().getTableFullName("workout_record") + " record inner join " + ett.i().getTableFullName(eub.e()) + " info on record.workoutId = info.workoutId where (record.workoutId GLOB ?  or record.wearType = 3) and (record.sportRecordType = 0) and record.userId = ?  order by exerciseTime desc";
        asc.e().b(new Runnable() { // from class: com.huawei.health.plan.model.model.fitness.FitnessHistoryModel$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                FitnessHistoryModel.lambda$acquireAnnualExerciseRecordByAll$0(str, resultCallback);
            }
        });
    }

    static /* synthetic */ void lambda$acquireAnnualExerciseRecordByAll$0(String str, ResultCallback resultCallback) {
        ArrayList arrayList = new ArrayList();
        Cursor rawQueryStorageData = ett.i().rawQueryStorageData(1, str, new String[]{SQL_RULE, LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011)});
        if (rawQueryStorageData != null) {
            while (rawQueryStorageData.moveToNext()) {
                arrayList.add(eup.asq_(rawQueryStorageData));
            }
            rawQueryStorageData.close();
        }
        resultCallback.onResult(0, arrayList);
    }

    public void acquireExerciseRecordByTimeAndId(long j, long j2, final String str, final String str2, final ResultCallback resultCallback) {
        LogUtil.a(TAG, "acquireExerciseRecordByTimeAndId");
        if (resultCallback == null) {
            LogUtil.h(TAG, "acquireExerciseRecordByTimeAndId callback == null");
            return;
        }
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.h(TAG, "acquireExerciseRecordByTimeAndId workOutId or version is empty");
            resultCallback.onResult(1, null);
            return;
        }
        if (j > j2 || j < 0) {
            LogUtil.h(TAG, "invalid time startTime = ", Long.valueOf(j), ". endTime = ", Long.valueOf(j2));
            resultCallback.onResult(1, null);
            return;
        }
        final String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        if (accountInfo == null) {
            LogUtil.h(TAG, "acquireExerciseRecordByTimeAndId mAccountInfo is null");
            return;
        }
        final String str3 = "select * from " + ett.i().getTableFullName("workout_record") + " where  (workoutId GLOB ? or wearType = 3) and (sportRecordType = 0)  and userId = ?  and exerciseTime >= " + j + " and exerciseTime <= " + j2 + " and workoutId = ? and version = ? order by finishRate desc";
        asc.e().b(new Runnable() { // from class: com.huawei.health.plan.model.model.fitness.FitnessHistoryModel$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                FitnessHistoryModel.lambda$acquireExerciseRecordByTimeAndId$1(str3, accountInfo, str, str2, resultCallback);
            }
        });
    }

    static /* synthetic */ void lambda$acquireExerciseRecordByTimeAndId$1(String str, String str2, String str3, String str4, ResultCallback resultCallback) {
        Cursor rawQueryStorageData = ett.i().rawQueryStorageData(1, str, new String[]{SQL_RULE, str2, str3, str4});
        WorkoutRecord workoutRecord = new WorkoutRecord();
        if (rawQueryStorageData != null) {
            LogUtil.a(TAG, "cursor not null");
            if (rawQueryStorageData.moveToNext()) {
                workoutRecord = eup.asw_(rawQueryStorageData);
            }
            rawQueryStorageData.close();
        }
        LogUtil.a(TAG, "onResult callback SUCCESS_CODE:", 0);
        resultCallback.onResult(0, workoutRecord);
    }

    public void acquireExerciseFinished(long j, long j2, final String str, final ResultCallback resultCallback) {
        LogUtil.a(TAG, "acquireExerciseFinished enter");
        if (resultCallback == null) {
            LogUtil.h(TAG, "acquireExerciseFinished callback == null");
            return;
        }
        if (j > j2 || j < 0 || TextUtils.isEmpty(str)) {
            LogUtil.h(TAG, "acquireExerciseFinished invalid params startTime = ", Long.valueOf(j), ", endTime = ", Long.valueOf(j2), ", workoutId = ", str);
            resultCallback.onResult(1, "invalid params");
            return;
        }
        final String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        if (accountInfo == null) {
            LogUtil.h(TAG, "acquireExerciseFinished mAccountInfo is null");
            resultCallback.onResult(1, "mAccountInfo is null");
            return;
        }
        final String str2 = "select finishRate from " + ett.i().getTableFullName("workout_record") + " where  (workoutId GLOB ? or wearType = 3)  and userId = ?  and exerciseTime >= " + j + " and exerciseTime <= " + j2 + " and workoutId = ? order by finishRate desc limit 1";
        asc.e().b(new Runnable() { // from class: com.huawei.health.plan.model.model.fitness.FitnessHistoryModel$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                FitnessHistoryModel.lambda$acquireExerciseFinished$2(str2, accountInfo, str, resultCallback);
            }
        });
    }

    static /* synthetic */ void lambda$acquireExerciseFinished$2(String str, String str2, String str3, ResultCallback resultCallback) {
        Cursor rawQueryStorageData = ett.i().rawQueryStorageData(1, str, new String[]{SQL_RULE, str2, str3});
        if (rawQueryStorageData != null) {
            LogUtil.a(TAG, "acquireExerciseFinished cursor not null");
            r13 = rawQueryStorageData.moveToNext() ? rawQueryStorageData.getFloat(rawQueryStorageData.getColumnIndex("finishRate")) : 0.0f;
            rawQueryStorageData.close();
        }
        String e = UnitUtil.e(r13, 2, 0);
        String e2 = UnitUtil.e(100.0d, 2, 0);
        boolean equals = e.equals(e2);
        LogUtil.a(TAG, "acquireExerciseFinished onResult callback finishRate = ", Float.valueOf(r13), " localRate = ", e, " targetRate = ", e2, " isFinished = ", Boolean.valueOf(equals));
        resultCallback.onResult(0, Boolean.valueOf(equals));
    }

    private void getDataFromDb(SimpleDateFormat simpleDateFormat, List<FitnessTrackRecord> list, String str) {
        Date date;
        Cursor rawQueryStorageData = ett.i().rawQueryStorageData(1, str, new String[]{SQL_RULE, LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011)});
        try {
            if (rawQueryStorageData == null) {
                LogUtil.b(TAG, "getDataFrmDb cursor==null");
                if (rawQueryStorageData != null) {
                    rawQueryStorageData.close();
                    return;
                }
                return;
            }
            while (rawQueryStorageData.moveToNext()) {
                FitnessTrackRecord fitnessTrackRecord = new FitnessTrackRecord();
                float f = rawQueryStorageData.getFloat(rawQueryStorageData.getColumnIndex("calorie"));
                fitnessTrackRecord.saveSumCalorie(f);
                int i = rawQueryStorageData.getInt(rawQueryStorageData.getColumnIndex("times"));
                fitnessTrackRecord.saveSumExerciseTimes(i);
                fitnessTrackRecord.saveSumExerciseTime(rawQueryStorageData.getLong(rawQueryStorageData.getColumnIndex(DURING)));
                String string = rawQueryStorageData.getString(rawQueryStorageData.getColumnIndex("date"));
                fitnessTrackRecord.saveMonthDate(string);
                LogUtil.a(TAG, "date=", string, " cal=", Float.valueOf(f), " cnt=", Integer.valueOf(i));
                try {
                    if (!TextUtils.isEmpty(string)) {
                        date = simpleDateFormat.parse(string);
                    } else {
                        LogUtil.h(TAG, "getData dateString == null");
                        date = new Date();
                    }
                    LogUtil.a(TAG, "monthZeroTime=", Long.valueOf(date.getTime()));
                    fitnessTrackRecord.saveMonthZeroTime(date.getTime());
                } catch (ParseException e) {
                    LogUtil.b(TAG, "getData e ", LogAnonymous.b((Throwable) e));
                }
                fitnessTrackRecord.saveRecordType(10001);
                list.add(fitnessTrackRecord);
            }
            if (rawQueryStorageData != null) {
                rawQueryStorageData.close();
            }
        } catch (Throwable th) {
            if (rawQueryStorageData != null) {
                try {
                    rawQueryStorageData.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public void acquireFitnessRecordCategoryList(final IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a(TAG, "acquireFitnessRecordCategoryList");
        asc.e().b(new Runnable() { // from class: com.huawei.health.plan.model.model.fitness.FitnessHistoryModel$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                FitnessHistoryModel.lambda$acquireFitnessRecordCategoryList$3(IBaseResponseCallback.this);
            }
        });
    }

    static /* synthetic */ void lambda$acquireFitnessRecordCategoryList$3(IBaseResponseCallback iBaseResponseCallback) {
        String str = "select distinct category from " + ett.i().getTableFullName("workout_record") + " where userId = ? and sportRecordType = 0 order by category";
        ArrayList arrayList = new ArrayList();
        Cursor rawQueryStorageData = ett.i().rawQueryStorageData(1, str, new String[]{LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011)});
        if (rawQueryStorageData != null) {
            while (rawQueryStorageData.moveToNext()) {
                int i = rawQueryStorageData.getInt(rawQueryStorageData.getColumnIndex("category"));
                if (i != 0) {
                    arrayList.add(Integer.valueOf(i));
                }
            }
            rawQueryStorageData.close();
        }
        LogUtil.a(TAG, "categoryList is: ", arrayList);
        iBaseResponseCallback.d(0, arrayList);
    }

    private boolean isValidCheckParameters(kwy kwyVar, IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            LogUtil.h(TAG, "isValidCheckParameters callback == null");
            return false;
        }
        if (kwyVar.a() > kwyVar.c() || kwyVar.a() < 0) {
            LogUtil.h(TAG, "isValidCheckParameters startTime = ", Long.valueOf(kwyVar.a()), ". endTime = ", Long.valueOf(kwyVar.c()));
            iBaseResponseCallback.d(-1, "startTime > endTime");
            return false;
        }
        if (LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011) != null) {
            return true;
        }
        LogUtil.h(TAG, "isValidCheckParameters mAccountInfo == null");
        iBaseResponseCallback.d(-2, "mAccountInfo == null");
        return false;
    }

    public void deleteFitnessRecord(WorkoutRecord workoutRecord, IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            LogUtil.h(TAG, "deleteFitnessRecord callback == null");
            return;
        }
        if (workoutRecord == null) {
            LogUtil.h(TAG, "deleteFitnessRecord record == null");
            iBaseResponseCallback.d(-1, "deleteFitnessRecord record == null");
            return;
        }
        int acquireId = workoutRecord.acquireId();
        LogUtil.a(TAG, "deleteFitnessRecord webId = ", Integer.valueOf(acquireId));
        if (workoutRecord.acquireWearType() == 2) {
            iBaseResponseCallback.d(-1, "deleteFitnessRecord record is device record, not support delete");
        }
        try {
            long acquireExerciseTime = workoutRecord.acquireExerciseTime();
            LogUtil.a(TAG, "deleteFitnessRecord exerciseTime = ", Long.valueOf(acquireExerciseTime));
            int e = eup.e(workoutRecord.acquireExerciseTime(), LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011), workoutRecord.acquireWorkoutId());
            if (acquireId == 0) {
                LogUtil.a(TAG, "deleteFitnessRecord this record has no web Id");
                long j = acquireExerciseTime / 1000;
                getInstance().postFitnessHistoryRequest(j - 1, j + 1, 0);
                this.mDeletingRecord = acquireExerciseTime;
                this.mIsNeedAdd = true;
            } else {
                ReleaseLogUtil.e(TAG, "deleteFitnessRecord:", Integer.valueOf(workoutRecord.acquireId()), " ", workoutRecord.acquireWorkoutId(), " ", workoutRecord.acquireWorkoutName());
                etr.b().deleteWorkoutRecords(workoutRecord.acquireId(), 1, iBaseResponseCallback);
            }
            iBaseResponseCallback.d(e, "fitnessRecord delete success");
        } catch (Exception e2) {
            LogUtil.b(TAG, "deleteFitnessRecord ", LogAnonymous.b((Throwable) e2));
        }
    }

    public void deleteFitnessRecord(String str, long j, IBaseResponseCallback iBaseResponseCallback) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a(TAG, "not course record.");
            return;
        }
        WorkoutRecord e = euj.e(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011), j, str);
        if (e == null) {
            LogUtil.b(TAG, "deleteFitnessRecord mRecord == null");
        } else {
            if (e.acquireWorkoutId() == null || !e.acquireWorkoutId().equals(str)) {
                return;
            }
            deleteFitnessRecord(e, iBaseResponseCallback);
            ReleaseLogUtil.e(TAG, "deleteFitnessRecord:", Integer.valueOf(e.acquireId()), " ", e.acquireWorkoutId(), " ", e.acquireWorkoutName());
        }
    }

    public WorkoutRecord acquireWorkoutRecordByRecordId(int i) {
        Cursor rawQueryStorageData = ett.i().rawQueryStorageData(1, "select * from " + ett.i().getTableFullName("workout_record") + " where  (workoutId GLOB ? or wearType = 3)  and userId = ?  and recordIndex = " + i, new String[]{SQL_RULE, LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011)});
        WorkoutRecord workoutRecord = null;
        if (rawQueryStorageData != null) {
            while (rawQueryStorageData.moveToNext()) {
                workoutRecord = eup.asw_(rawQueryStorageData);
            }
            rawQueryStorageData.close();
        }
        return workoutRecord;
    }

    private List<WorkoutRecord> getWorkoutRecordsSyncByMonth(long j) {
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        if (accountInfo == null) {
            LogUtil.h(TAG, "getWorkoutRecordsSyncByMonth mAccountInfo is null");
            return null;
        }
        long c = gib.c(j);
        long e = gib.e(j);
        String str = "select * from " + ett.i().getTableFullName("workout_record") + " where  (workoutId GLOB ? or wearType = 3)  and userId = ?  and exerciseTime between " + c + " and " + e + " order by exerciseTime desc";
        LogUtil.a(TAG, "search workout record start time: ", Long.valueOf(c), "end time: ", Long.valueOf(e));
        ArrayList arrayList = new ArrayList();
        Cursor rawQueryStorageData = ett.i().rawQueryStorageData(1, str, new String[]{SQL_RULE, accountInfo});
        if (rawQueryStorageData != null) {
            while (rawQueryStorageData.moveToNext()) {
                arrayList.add(eup.asw_(rawQueryStorageData));
            }
            rawQueryStorageData.close();
        }
        return arrayList;
    }

    private void addExercisedRecord(List<WorkoutRecord> list) {
        if (list == null) {
            LogUtil.h(TAG, "addExercisedRecord records are null");
            return;
        }
        LogUtil.a(TAG, "addExercisedRecord multiple records ", Integer.valueOf(list.size()));
        for (int i = 0; i < list.size(); i++) {
            int acquireId = list.get(i).acquireId();
            if (this.mIsNeedAdd && list.get(i).acquireExerciseTime() / 1000 == this.mDeletingRecord / 1000) {
                ett.i().o().e(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011), "", 9, String.valueOf(acquireId));
                this.mIsNeedAdd = false;
                this.mDeletingRecord = 0L;
            } else {
                addExercisedRecord(list.get(i));
            }
        }
    }

    private void addExercisedRecord(WorkoutRecord workoutRecord) {
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        if (workoutRecord == null) {
            LogUtil.h(TAG, "addExercisedRecord record is null");
            return;
        }
        if (accountInfo != null) {
            if (eup.c(accountInfo, workoutRecord)) {
                LogUtil.h(TAG, "exercised record has exist.", Integer.valueOf(workoutRecord.acquireId()));
                return;
            }
            LogUtil.a(TAG, "insert res=", Boolean.valueOf(eup.b(accountInfo, workoutRecord)), " ", Integer.valueOf(workoutRecord.acquireId()), " ", workoutRecord.acquireWorkoutName(), " pId=", workoutRecord.acquirePlanId());
            if (workoutRecord.getSportRecordType() == 0) {
                ett.i().k().a(accountInfo, workoutRecord.acquireExerciseTime(), workoutRecord.acquireActualCalorie(), workoutRecord.getDuration(), workoutRecord.acquireWorkoutId(), false);
            } else {
                LogUtil.a(TAG, "addExercisedRecord : SportRecordType != 0");
            }
            if (TextUtils.isEmpty(workoutRecord.acquirePlanId())) {
                return;
            }
            etx.b().e();
            return;
        }
        LogUtil.b(TAG, "mAccountInfo == null");
    }

    public void downloadFitnessRecordFromCloud(ResultCallback resultCallback) {
        synchronized (LOCK) {
            LogUtil.a(TAG, "downloadFitnessRecordFromCloud start");
            if (resultCallback == null) {
                LogUtil.h(TAG, "downloadFitnessRecordFromCloud start");
                return;
            }
            if (this.mIsDownloadingWorkoutRecords) {
                LogUtil.a(TAG, "downloadFitnessRecordFromCloud is downloading");
                resultCallback.onResult(-998, "repeat downloading");
            } else {
                this.mIsDownloadingWorkoutRecords = true;
                LogUtil.a(TAG, "downloadFitnessRecordFromCloud ", LoginInit.getInstance(BaseApplication.getContext()));
                this.mResultCallback = resultCallback;
                getFitnessHistoryByMonth();
            }
        }
    }

    public void unregisterResultCallback() {
        this.mResultCallback = null;
    }

    public void postFitnessHistoryRequest(final long j, final long j2, final int i) {
        LogUtil.a(TAG, "postFitnessHistoryRequest");
        eqa.a().queryTrainList(j, j2, i, new DataCallback() { // from class: com.huawei.health.plan.model.model.fitness.FitnessHistoryModel.2
            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onFailure(int i2, String str) {
                LogUtil.h(FitnessHistoryModel.TAG, "postFitnessHistoryRequest ", str);
                FitnessHistoryModel.this.onDownloadFail();
                FitnessHistoryModel.this.mIsNeedAdd = false;
            }

            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onSuccess(JSONObject jSONObject) {
                JSONArray optJSONArray = jSONObject.optJSONArray("trainInfoList");
                if (optJSONArray != null) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
                    LogUtil.a(FitnessHistoryModel.TAG, "sTm=", simpleDateFormat.format(Long.valueOf(j * 1000)), ",eTm=", simpleDateFormat.format(Long.valueOf(j2 * 1000)));
                    FitnessHistoryModel.this.mCloudData.addAll(ffm.e(optJSONArray));
                    eup.e(FitnessHistoryModel.this.mCloudData);
                    for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                        LogUtil.c(FitnessHistoryModel.TAG, "postFitnessHistoryRequest onSuccess ", optJSONArray.optJSONObject(i2).toString());
                    }
                } else {
                    LogUtil.a(FitnessHistoryModel.TAG, "no data");
                }
                if (jSONObject.optBoolean("hasMore")) {
                    Message obtain = Message.obtain();
                    obtain.what = 1000;
                    Bundle bundle = new Bundle();
                    bundle.putLong("startTime", j);
                    bundle.putLong("endTime", j2);
                    bundle.putInt("pageNum", i);
                    obtain.obj = bundle;
                    FitnessHistoryModel.this.mHandler.sendMessage(obtain);
                    return;
                }
                Message obtain2 = Message.obtain();
                obtain2.what = 1001;
                obtain2.obj = Long.valueOf(j);
                FitnessHistoryModel.this.mHandler.sendMessage(obtain2);
            }
        });
    }

    private void getFitnessHistoryByMonth() {
        eqa.a().queryTrainCount(new DataCallback() { // from class: com.huawei.health.plan.model.model.fitness.FitnessHistoryModel.3
            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onFailure(int i, String str) {
                LogUtil.h(FitnessHistoryModel.TAG, "queryTrainCount errorCode = ", Integer.valueOf(i), "errorInfo = ", str);
                FitnessHistoryModel.this.onDownloadFail();
            }

            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onSuccess(JSONObject jSONObject) {
                if (jSONObject == null) {
                    LogUtil.h(FitnessHistoryModel.TAG, "queryTrainCount no data");
                    FitnessHistoryModel.this.onDownloadFail();
                    return;
                }
                LogUtil.a(FitnessHistoryModel.TAG, "received message: ", jSONObject);
                JSONArray optJSONArray = jSONObject.optJSONArray("monthTrainList");
                if (optJSONArray != null) {
                    Message obtain = Message.obtain();
                    obtain.obj = optJSONArray;
                    obtain.what = 1002;
                    FitnessHistoryModel.this.mHandler.sendMessage(obtain);
                    return;
                }
                LogUtil.h(FitnessHistoryModel.TAG, "queryTrainCount no data");
                FitnessHistoryModel.this.onDownloadFail();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onDownloadFail() {
        synchronized (LOCK) {
            this.mIsDownloadingWorkoutRecords = false;
        }
        ResultCallback resultCallback = this.mResultCallback;
        if (resultCallback != null) {
            resultCallback.onResult(-997, RecommendConstants.DOWNLOAD_FAIL);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean compareFitnessRecordCount(JSONObject jSONObject) {
        int i;
        Date date;
        String optString = jSONObject.optString("months");
        String optString2 = jSONObject.optString("count");
        if (optString.isEmpty() || optString2.isEmpty()) {
            LogUtil.h(TAG, "invalid data");
            return true;
        }
        try {
            i = Integer.parseInt(optString2);
        } catch (NumberFormatException e) {
            LogUtil.b(TAG, "parse wrong", e.getMessage());
            i = 0;
        }
        try {
            date = new SimpleDateFormat(HwDrmConstant.TIME_FORMAT).parse(optString + "01000000");
        } catch (ParseException e2) {
            LogUtil.b(TAG, "compareFitnessRecordCount ", LogAnonymous.b((Throwable) e2));
            date = null;
        }
        if (date == null) {
            return true;
        }
        long time = date.getTime();
        List<WorkoutRecord> workoutRecordsSyncByMonth = getWorkoutRecordsSyncByMonth(time);
        if (workoutRecordsSyncByMonth == null) {
            return false;
        }
        LogUtil.a(TAG, "localSize=", Integer.valueOf(workoutRecordsSyncByMonth.size()), " cloudSize=", Integer.valueOf(i), " month=", Long.valueOf(time));
        if (workoutRecordsSyncByMonth.size() == i) {
            return true;
        }
        postFitnessHistoryRequest(gib.c(time) / 1000, gib.e(time) / 1000, 0);
        return false;
    }

    public void acquireSummaryFitnessRecord(final long j, final long j2, final IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a(TAG, "acquireSummaryFitnessRecord");
        if (!isValidCheckParameters(new kwy.a().a(j).e(j).d(), iBaseResponseCallback)) {
            LogUtil.h(TAG, "deleteFitnessRecord isValidCheckParameters fail");
            return;
        }
        final String str = "select sum(actualCalorie) as sumCalorie , sum(during) as sumDuring,  count(workoutId) as sumCount from " + ett.i().getTableFullName("workout_record") + " where  (workoutId GLOB ? or wearType = 3) and (sportRecordType = 0)  and userId = ?  and exerciseTime between " + j + " and " + j2;
        asc.e().b(new Runnable() { // from class: com.huawei.health.plan.model.model.fitness.FitnessHistoryModel$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                FitnessHistoryModel.lambda$acquireSummaryFitnessRecord$4(str, j, j2, iBaseResponseCallback);
            }
        });
    }

    static /* synthetic */ void lambda$acquireSummaryFitnessRecord$4(String str, long j, long j2, IBaseResponseCallback iBaseResponseCallback) {
        FitnessSummaryRecord fitnessSummaryRecord = new FitnessSummaryRecord();
        Cursor rawQueryStorageData = ett.i().rawQueryStorageData(1, str, new String[]{SQL_RULE, LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011)});
        if (rawQueryStorageData != null) {
            while (rawQueryStorageData.moveToNext()) {
                float f = rawQueryStorageData.getFloat(rawQueryStorageData.getColumnIndex("sumCalorie"));
                long j3 = rawQueryStorageData.getInt(rawQueryStorageData.getColumnIndex("sumDuring"));
                int i = rawQueryStorageData.getInt(rawQueryStorageData.getColumnIndex("sumCount"));
                fitnessSummaryRecord.saveRecordsSumCalorie(f);
                fitnessSummaryRecord.saveRecordsSumTime(j3);
                fitnessSummaryRecord.saveRecordsCount(i);
                fitnessSummaryRecord.saveRecordsStartTime(j);
                fitnessSummaryRecord.saveRecordsEndTime(j2);
                fitnessSummaryRecord.saveRecordType(10001);
            }
            rawQueryStorageData.close();
        }
        iBaseResponseCallback.d(0, fitnessSummaryRecord);
    }

    public void acquireSummaryFitnessRecord(final kwy kwyVar, final IBaseResponseCallback iBaseResponseCallback) {
        if (!isValidCheckParameters(kwyVar, iBaseResponseCallback)) {
            LogUtil.h(TAG, "acquireSummaryFitnessRecord isValidCheckParameters fail");
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.health.plan.model.model.fitness.FitnessHistoryModel$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    FitnessHistoryModel.this.m427x2d73e64a(kwyVar, iBaseResponseCallback);
                }
            });
        }
    }

    /* renamed from: lambda$acquireSummaryFitnessRecord$5$com-huawei-health-plan-model-model-fitness-FitnessHistoryModel, reason: not valid java name */
    /* synthetic */ void m427x2d73e64a(kwy kwyVar, IBaseResponseCallback iBaseResponseCallback) {
        String acquireSummaryFitnessRecordSql = getAcquireSummaryFitnessRecordSql(kwyVar);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(getStartTimeDateFormat(kwyVar.g()), Locale.ENGLISH);
        ArrayList arrayList = new ArrayList();
        getDataFromDb(simpleDateFormat, arrayList, acquireSummaryFitnessRecordSql);
        LogUtil.a(TAG, "acquireSummaryFitnessRecord size: ", Integer.valueOf(arrayList.size()));
        iBaseResponseCallback.d(0, arrayList);
    }

    public void acquireDetailFitnessRecord(final kwy kwyVar, final IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a(TAG, "acquireDetailFitnessRecord");
        if (!isValidCheckParameters(kwyVar, iBaseResponseCallback)) {
            LogUtil.h(TAG, "deleteFitnessRecord isValidCheckParameters fail");
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.health.plan.model.model.fitness.FitnessHistoryModel$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    FitnessHistoryModel.lambda$acquireDetailFitnessRecord$6(kwy.this, iBaseResponseCallback);
                }
            });
        }
    }

    static /* synthetic */ void lambda$acquireDetailFitnessRecord$6(kwy kwyVar, IBaseResponseCallback iBaseResponseCallback) {
        String str;
        String str2 = "";
        if (kwyVar.i() == 0) {
            str = "";
        } else {
            str = " and category = " + kwyVar.i();
        }
        if (kwyVar.e() != 0) {
            str2 = " limit " + kwyVar.e();
        }
        StringBuilder sb = new StringBuilder();
        String str3 = kwyVar.b() == 0 ? " DESC " : " ASC ";
        sb.append("select * from ");
        sb.append(ett.i().getTableFullName("workout_record"));
        sb.append(" where  (workoutId GLOB ? or wearType = 3) and (sportRecordType = 0)  and userId = ? ");
        sb.append(str);
        sb.append(" and exerciseTime between ");
        sb.append(kwyVar.a());
        sb.append(" and ");
        sb.append(kwyVar.c());
        sb.append(" order by exerciseTime");
        sb.append(str3);
        sb.append(str2);
        LogUtil.a(TAG, "query detailRecords SQL is:", sb);
        ArrayList arrayList = new ArrayList();
        Cursor rawQueryStorageData = ett.i().rawQueryStorageData(1, sb.toString(), new String[]{SQL_RULE, LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011)});
        if (rawQueryStorageData != null) {
            while (rawQueryStorageData.moveToNext()) {
                try {
                    arrayList.add(eup.asw_(rawQueryStorageData));
                } catch (Throwable th) {
                    if (rawQueryStorageData != null) {
                        try {
                            rawQueryStorageData.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    }
                    throw th;
                }
            }
        }
        if (rawQueryStorageData != null) {
            rawQueryStorageData.close();
        }
        LogUtil.a(TAG, "exit acquireDetailFitnessRecord, records size：", Integer.valueOf(arrayList.size()));
        iBaseResponseCallback.d(0, arrayList);
    }

    private String getStartTimeDateFormat(int i) {
        String str;
        if (i == 3) {
            str = "yyyyMMdd";
        } else if (i == 4) {
            str = "yyyy-ww";
        } else if (i == 5) {
            str = "yyyyMM";
        } else if (i != 6) {
            if (i != 7) {
                LogUtil.b(TAG, "ERROR, notSupport HiGroupUnitType");
            }
            str = "";
        } else {
            str = "yyyy";
        }
        LogUtil.c(TAG, "getStartTimeDateFormat，timeUnit: ", Integer.valueOf(i), " dateFormat: ", str);
        return str;
    }

    private String getAcquireSummaryFitnessRecordSql(kwy kwyVar) {
        String str;
        String str2;
        int g = kwyVar.g();
        if (g == 3) {
            str = "substr(exerciseDate, 1, 8) as date,";
        } else if (g == 4) {
            str = "strftime('%Y-%W',datetime(exerciseTime/1000 " + HiDateUtil.d((String) null) + "*36, 'unixepoch')) as date,";
        } else if (g == 5) {
            str = "substr(exerciseDate, 1, 6) as date,";
        } else {
            if (g != 6) {
                LogUtil.b(TAG, "not group by date");
            }
            str = "substr(exerciseDate, 1, 4) as date,";
        }
        if (kwyVar.i() == 0) {
            str2 = "";
        } else {
            str2 = " and category = " + kwyVar.i();
        }
        boolean z = kwyVar.g() == 0 || kwyVar.g() == 7;
        String str3 = "select " + str + "sum(actualCalorie) as calorie ,sum(during) as during, count(workoutId) as times from " + ett.i().getTableFullName("workout_record") + " where  (workoutId GLOB ? or wearType = 3) and (sportRecordType = 0)  and userId = ?" + str2 + " and exerciseTime between " + kwyVar.a() + " and " + kwyVar.c() + (z ? "" : " group by date") + (z ? "" : " order by date desc");
        LogUtil.c(TAG, str3);
        return str3;
    }
}
