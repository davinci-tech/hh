package defpackage;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.huawei.basefitnessadvice.model.Plan;
import com.huawei.health.device.model.RecordAction;
import com.huawei.health.plan.model.data.BaseDao;
import com.huawei.health.sport.model.AnnualWorkoutRecord;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.hwfoundationmodel.trackmodel.HeartRateData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import com.huawei.operation.utils.Constants;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.pluginfitnessadvice.Classify;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import health.compact.a.utils.StringUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

/* loaded from: classes3.dex */
public class eup extends BaseDao {

    /* renamed from: a, reason: collision with root package name */
    private static Map<String, Integer> f12338a;
    private static ffu e = new ffu();

    static {
        TreeMap treeMap = new TreeMap();
        f12338a = treeMap;
        treeMap.put("Y001", 137);
        f12338a.put("Y002", 137);
        f12338a.put("Y003", 137);
        f12338a.put("Y004", 137);
        f12338a.put("Y005", 137);
        f12338a.put("Y006", 137);
        f12338a.put("Y007", 137);
        f12338a.put("Y008", 137);
        f12338a.put("Y009", 137);
        f12338a.put("Y010", 137);
        f12338a.put("Y015", 137);
        f12338a.put("Y016", 137);
        f12338a.put("Y017", 137);
        f12338a.put("Y018", 137);
    }

    private static boolean e(WorkoutRecord workoutRecord) {
        return workoutRecord.acquireDistance() > 0.0f;
    }

    public static boolean e(String str, WorkoutRecord workoutRecord) {
        LogUtil.a("Suggestion_WRDaoHelper", "updatePlanProgress enter");
        if (workoutRecord == null) {
            LogUtil.h("Suggestion_WRDaoHelper", "workoutRecord == null");
            return false;
        }
        if (workoutRecord.isFitnessRecordFromDevice() && c(str, workoutRecord)) {
            LogUtil.h("Suggestion_WRDaoHelper", "device sync same record. workout record id:", Integer.valueOf(workoutRecord.acquireId()));
            return true;
        }
        if (TextUtils.isEmpty(workoutRecord.acquirePlanId())) {
            LogUtil.a("Suggestion_WRDaoHelper", "Fitness workoutRecord");
            workoutRecord.saveRecordType(0);
        } else if (eve.b) {
            epo a2 = ety.c().a();
            if (a2 == null && evf.a(workoutRecord.acquirePlanId()) == 0) {
                d(str, workoutRecord);
            } else if (eve.a(a2, workoutRecord)) {
                workoutRecord.savePlanId("");
            } else {
                workoutRecord.saveRecordType(1);
            }
        } else if (evf.a(workoutRecord.acquirePlanId()) == 0) {
            LogUtil.a("Suggestion_WRDaoHelper", "runPlan workoutRecord");
            d(str, workoutRecord);
        } else if (evf.a(workoutRecord.acquirePlanId()) == 3) {
            LogUtil.a("Suggestion_WRDaoHelper", "fitness plan workoutRecord");
            if (euc.e().b(workoutRecord)) {
                LogUtil.a("Suggestion_WRDaoHelper", "today workout record has exceed plan last date");
                workoutRecord.savePlanId("");
            } else {
                List<WorkoutRecord> a3 = a(str, workoutRecord.acquirePlanId(), workoutRecord.acquireWorkoutDate());
                if (a3.isEmpty()) {
                    LogUtil.a("Suggestion_WRDaoHelper", "today has no record");
                    workoutRecord.saveRecordType(1);
                } else {
                    b(str, workoutRecord, a3);
                }
            }
        }
        boolean b = b(str, workoutRecord);
        LogUtil.a("Suggestion_WRDaoHelper", "insert result:", Boolean.valueOf(b));
        return b;
    }

    private static void d(String str, WorkoutRecord workoutRecord) {
        if (b(workoutRecord)) {
            LogUtil.a("Suggestion_WRDaoHelper", "runPlan workoutRecord beyond plan");
            workoutRecord.savePlanId("");
        } else {
            a(str, workoutRecord);
        }
    }

    public static boolean c(String str, WorkoutRecord workoutRecord) {
        return euj.e(str, Long.toString(workoutRecord.acquireExerciseTime())) != null;
    }

    private static void b(String str, WorkoutRecord workoutRecord, List<WorkoutRecord> list) {
        if (koq.b(list) || workoutRecord == null) {
            return;
        }
        long b = gib.b(workoutRecord.acquireExerciseTime());
        for (WorkoutRecord workoutRecord2 : list) {
            if (workoutRecord2 != null) {
                long b2 = gib.b(workoutRecord2.acquireExerciseTime());
                if (b == b2) {
                    LogUtil.a("Suggestion_WRDaoHelper", "today has record");
                    a(str, workoutRecord);
                } else if (b > b2) {
                    LogUtil.a("Suggestion_WRDaoHelper", "invalid plan record");
                    workoutRecord.savePlanId("");
                }
            }
        }
    }

    private static boolean b(WorkoutRecord workoutRecord) {
        Plan currentPlan = etr.b().getCurrentPlan();
        if (currentPlan == null) {
            LogUtil.h("Suggestion_WRDaoHelper", "hasWorkoutRecordBeyondRunPlan plan == null");
            return true;
        }
        if (evf.a(currentPlan.acquireId()) == 3) {
            LogUtil.h("Suggestion_WRDaoHelper", "hasWorkoutRecordBeyondRunPlan fitness plan");
            return true;
        }
        if (gib.b(workoutRecord.acquireExerciseTime()) <= gib.b(ghz.a(currentPlan.getEndDate(), "yyyy-MM-dd") * 1000)) {
            return false;
        }
        LogUtil.a("Suggestion_WRDaoHelper", "hasWorkoutRecordBeyondRunPlan plan expired");
        return true;
    }

    private static void a(String str, WorkoutRecord workoutRecord) {
        if (workoutRecord == null) {
            return;
        }
        WorkoutRecord a2 = a(str, workoutRecord.acquirePlanId(), workoutRecord.acquireWorkoutId(), workoutRecord.acquireWorkoutDate(), workoutRecord.acquireWorkoutOrder());
        if (a2 == null) {
            LogUtil.a("Suggestion_WRDaoHelper", "currentWorkoutRecord == null");
            workoutRecord.saveRecordType(1);
            return;
        }
        if (a2.acquireWorkoutDate() != null && !a2.acquireWorkoutDate().equals(gib.d(a2.acquireWorkoutDate(), "yyyy-MM-dd", Locale.ENGLISH))) {
            LogUtil.a("Suggestion_WRDaoHelper", "update workout data: ", a2.acquireWorkoutDate());
            e(str, a2.acquirePlanId(), a2.acquireWorkoutDate(), a2.acquireWorkoutOrder());
        }
        if (!etn.c(a2)) {
            LogUtil.a("Suggestion_WRDaoHelper", "invalidWorkoutRecord");
            workoutRecord.saveRecordType(1);
            a(str, a2.acquirePlanId(), a2.acquireWorkoutDate(), a2.acquireWorkoutOrder());
        } else if (!d(a2, workoutRecord)) {
            LogUtil.a("Suggestion_WRDaoHelper", "invalid plan record");
            workoutRecord.savePlanId("");
        } else if (a(a2, workoutRecord)) {
            LogUtil.a("Suggestion_WRDaoHelper", "updateWorkoutRecord");
            workoutRecord.saveRecordType(1);
            d(str, a2.acquirePlanId(), a2.acquireWorkoutDate(), a2.acquireWorkoutOrder());
        } else {
            LogUtil.a("Suggestion_WRDaoHelper", "handlePlanRecord common record type");
            workoutRecord.saveRecordType(0);
        }
    }

    private static boolean d(WorkoutRecord workoutRecord, WorkoutRecord workoutRecord2) {
        if (gib.b(workoutRecord.acquireExerciseTime()) == gib.b(workoutRecord2.acquireExerciseTime())) {
            return true;
        }
        LogUtil.a("Suggestion_WRDaoHelper", "history workoutRecord is not same day as current workoutRecord");
        return false;
    }

    public static void e(String str, List<WorkoutRecord> list) {
        if (koq.b(list)) {
            return;
        }
        ett.i().f();
        for (WorkoutRecord workoutRecord : list) {
            if (workoutRecord != null) {
                workoutRecord.saveRecordType(1);
                WorkoutRecord a2 = a(str, workoutRecord.acquirePlanId(), workoutRecord.acquireWorkoutId(), workoutRecord.acquireWorkoutDate(), workoutRecord.acquireWorkoutOrder());
                if (a2 == null) {
                    b(str, workoutRecord);
                } else {
                    if (a2.acquireWorkoutDate() != null && !a2.acquireWorkoutDate().equals(gib.d(a2.acquireWorkoutDate(), "yyyy-MM-dd", Locale.ENGLISH))) {
                        LogUtil.a("Suggestion_WRDaoHelper", "update workout Id:", a2.acquireWorkoutId(), " data: ", a2.acquireWorkoutDate());
                        e(str, a2.acquirePlanId(), a2.acquireWorkoutDate(), a2.acquireWorkoutOrder());
                    }
                    if (!etn.c(a2)) {
                        a(str, a2.acquirePlanId(), a2.acquireWorkoutDate(), a2.acquireWorkoutOrder());
                        b(str, workoutRecord);
                    } else if (a(a2, workoutRecord)) {
                        d(str, a2.acquirePlanId(), a2.acquireWorkoutDate(), a2.acquireWorkoutOrder());
                        b(str, workoutRecord);
                    }
                }
            }
        }
        ett.i().j();
        LogUtil.c("Suggestion_WRDaoHelper", "workoutRecords：", list.toString());
    }

    public static void a(String str, String str2, List<WorkoutRecord> list) {
        if (koq.b(list)) {
            return;
        }
        ett.i().f();
        List<WorkoutRecord> a2 = euj.a(str2, str);
        for (WorkoutRecord workoutRecord : list) {
            if (workoutRecord != null) {
                workoutRecord.saveRecordType(1);
                WorkoutRecord c = c(a2, workoutRecord.acquireExerciseTime());
                if (c == null) {
                    LogUtil.a("Suggestion_WRDaoHelper", "local record not exist.the cloudWorkoutRecord:", Integer.valueOf(workoutRecord.acquireId()));
                    b(str2, workoutRecord);
                } else if (!etn.c(c)) {
                    LogUtil.a("Suggestion_WRDaoHelper", "currentWorkoutRecord is not valid.");
                    c(str2, c.acquirePlanId(), c.acquireExerciseTime());
                    b(str2, workoutRecord);
                } else {
                    LogUtil.a("Suggestion_WRDaoHelper", "local has exist record,", workoutRecord.acquireWorkoutName());
                }
                LogUtil.a("Suggestion_WRDaoHelper", "insertWorkoutRecords:", workoutRecord.acquirePlanId(), " workoutId:", workoutRecord.acquireWorkoutId(), " exerciseTime:", Long.valueOf(workoutRecord.acquireExerciseTime()));
            }
        }
        ett.i().j();
        LogUtil.c("Suggestion_WRDaoHelper", "workoutRecords：", list.toString());
    }

    private static WorkoutRecord c(List<WorkoutRecord> list, long j) {
        for (WorkoutRecord workoutRecord : list) {
            LogUtil.a("Suggestion_WRDaoHelper", workoutRecord.acquireWorkoutName(), " local tm:", Long.valueOf(workoutRecord.acquireExerciseTime()), " cloud tm:", Long.valueOf(j));
            if (ghz.d(workoutRecord.acquireExerciseTime()) == ghz.d(j)) {
                return workoutRecord;
            }
        }
        return null;
    }

    private static boolean a(WorkoutRecord workoutRecord, WorkoutRecord workoutRecord2) {
        return e(workoutRecord2) ? workoutRecord.acquireActualDistance() != workoutRecord2.acquireActualDistance() ? workoutRecord.acquireActualDistance() < workoutRecord2.acquireActualDistance() : workoutRecord.getDuration() > workoutRecord2.getDuration() : workoutRecord.acquireActualCalorie() != workoutRecord2.acquireActualCalorie() ? workoutRecord.acquireActualCalorie() < workoutRecord2.acquireActualCalorie() : workoutRecord.getDuration() < workoutRecord2.getDuration();
    }

    public static boolean b(String str, WorkoutRecord workoutRecord) {
        String d;
        ContentValues contentValues = new ContentValues();
        if (workoutRecord == null) {
            LogUtil.h("Suggestion_WRDaoHelper", "insertWorkoutRecord workoutRecord");
            return false;
        }
        contentValues.put(JsbMapKeyNames.H5_USER_ID, str);
        contentValues.put("planId", StringUtils.c((Object) workoutRecord.acquirePlanId()));
        contentValues.put(HwExerciseConstants.METHOD_PARAM_WORKOUT_ID, workoutRecord.acquireWorkoutId());
        contentValues.put("recordIndex", Integer.valueOf(workoutRecord.acquireId()));
        contentValues.put("workoutName", workoutRecord.acquireWorkoutName());
        contentValues.put("exerciseDate", Integer.valueOf(gic.e((Object) ggl.a(new Date(workoutRecord.acquireExerciseTime()), "yyyyMMdd"))));
        contentValues.put("exerciseTime", Long.valueOf(workoutRecord.acquireExerciseTime()));
        contentValues.put("actualCalorie", Float.valueOf(workoutRecord.acquireActualCalorie()));
        contentValues.put("actualDistance", Float.valueOf(workoutRecord.acquireActualDistance()));
        contentValues.put("calorie", Float.valueOf(workoutRecord.acquireCalorie()));
        contentValues.put("distance", Float.valueOf(workoutRecord.acquireDistance()));
        contentValues.put("finishRate", Float.valueOf(workoutRecord.acquireFinishRate()));
        contentValues.put("during", Integer.valueOf(workoutRecord.getDuration()));
        contentValues.put("workoutOrder", Integer.valueOf(workoutRecord.acquireWorkoutOrder()));
        contentValues.put("upperHeartRate", Integer.valueOf(workoutRecord.acquireUpperHeartRate()));
        contentValues.put("lowerHeartRate", Integer.valueOf(workoutRecord.acquireLowerHeartRate()));
        if (TextUtils.isEmpty(workoutRecord.acquireWorkoutDate())) {
            d = gib.j(workoutRecord.acquireExerciseTime());
        } else {
            d = gib.d(workoutRecord.acquireWorkoutDate(), "yyyy-MM-dd", Locale.ENGLISH);
        }
        contentValues.put("workoutDate", d);
        contentValues.put("bestPace", Integer.valueOf(workoutRecord.acquireBestPace()));
        contentValues.put("oxygen", Double.valueOf(moe.b(workoutRecord.acquireOxygen())));
        contentValues.put("trainingLoadPeak", Integer.valueOf(workoutRecord.acquireTrainingLoadPeak()));
        contentValues.put("weekNum", Integer.valueOf(workoutRecord.acquireWeekNum()));
        contentValues.put(WorkoutRecord.Extend.RunWorkout.RUN_WORKOUT_TRAJECTORY_ID, workoutRecord.acquireTrajectoryId());
        contentValues.put("actionSummary", workoutRecord.acquireActionSummary());
        contentValues.put("recordType", Integer.valueOf(workoutRecord.acquireRecordType()));
        contentValues.put("version", workoutRecord.acquireVersion());
        contentValues.put("wearType", Integer.valueOf(workoutRecord.acquireWearType()));
        contentValues.put("sportRecordType", Integer.valueOf(workoutRecord.getSportRecordType()));
        contentValues.put(ParsedFieldTag.NPES_TOTAL_SCORE, Long.valueOf(workoutRecord.getTotalScore()));
        contentValues.put("recordModeType", Integer.valueOf(workoutRecord.getRecordModeType()));
        contentValues.put("extend", workoutRecord.acquireExtend());
        return asy_(contentValues, workoutRecord) > 0;
    }

    private static long asy_(ContentValues contentValues, WorkoutRecord workoutRecord) {
        List<HeartRateData> acquireHeartRateDataList = workoutRecord.acquireHeartRateDataList();
        Gson gson = new Gson();
        if (acquireHeartRateDataList != null) {
            contentValues.put("heartRateList", gson.toJson(acquireHeartRateDataList));
        }
        List<HeartRateData> acquireInvalidHeartRateList = workoutRecord.acquireInvalidHeartRateList();
        if (acquireInvalidHeartRateList != null) {
            contentValues.put("invalidHeartRateList", gson.toJson(acquireInvalidHeartRateList));
        }
        contentValues.put("category", Integer.valueOf(workoutRecord.acquireCategory()));
        List<Integer> intensityZone = workoutRecord.getIntensityZone();
        if (intensityZone != null) {
            contentValues.put("intensityZone", gson.toJson(intensityZone));
        }
        contentValues.put(HwExerciseConstants.JSON_NAME_TRAINING_POINTS, Integer.valueOf(workoutRecord.getTrainPoint()));
        return ett.i().insertStorageData("workout_record", 1, contentValues);
    }

    public static boolean d(String str, String str2, String str3, int i) {
        ContentValues contentValues = new ContentValues();
        String d = gib.d(str3, "yyyy-MM-dd", Locale.ENGLISH);
        contentValues.put("recordType", (Integer) 0);
        ett i2 = ett.i();
        StringBuilder sb = new StringBuilder("planId=? and userId=? and recordType=1 and workoutDate=? and workoutOrder=");
        sb.append(i);
        return i2.updateStorageData("workout_record", 1, contentValues, sb.toString(), new String[]{StringUtils.c((Object) str2), StringUtils.c((Object) str), StringUtils.c((Object) d)}) > 0;
    }

    public static boolean b(String str, WorkoutRecord workoutRecord, int i) {
        if (workoutRecord == null) {
            return false;
        }
        ett.i().f();
        ContentValues contentValues = new ContentValues();
        contentValues.put("recordIndex", Integer.valueOf(i));
        int updateStorageData = ett.i().updateStorageData("workout_record", 1, contentValues, "userId=? and exerciseTime= " + workoutRecord.acquireExerciseTime(), new String[]{StringUtils.c((Object) str)});
        ett.i().j();
        return updateStorageData > 0;
    }

    public static boolean a(String str, String str2, String str3, int i) {
        int i2;
        if (str3 == null || str2 == null || str == null) {
            LogUtil.b("Suggestion_WRDaoHelper", "delWorkoutRecord para error");
            return false;
        }
        String d = gib.d(str3, "yyyy-MM-dd", Locale.ENGLISH);
        int deleteStorageData = ett.i().deleteStorageData("workout_record", 1, "planId=? and userId=? and recordType=1 and workoutDate=? and workoutOrder=" + i, new String[]{StringUtils.c((Object) str2), StringUtils.c((Object) str), StringUtils.c((Object) d)});
        if (str3.equals(d)) {
            i2 = 0;
        } else {
            i2 = ett.i().deleteStorageData("workout_record", 1, "planId=? and userId=? and recordType=1 and workoutDate=? and workoutOrder=" + i, new String[]{StringUtils.c((Object) str2), StringUtils.c((Object) str), StringUtils.c((Object) str3)});
        }
        return deleteStorageData > 0 || i2 > 0;
    }

    public static void c(String str, String str2, long j) {
        int deleteStorageData = ett.i().deleteStorageData("workout_record", 1, "planId=? and userId=? and exerciseTime=" + j, new String[]{StringUtils.c((Object) str2), StringUtils.c((Object) str)});
        Object[] objArr = new Object[3];
        objArr[0] = "delWorkoutRecord:";
        objArr[1] = Long.valueOf(j);
        objArr[2] = Boolean.valueOf(deleteStorageData > 0);
        LogUtil.a("Suggestion_WRDaoHelper", objArr);
    }

    public static void d(String str) {
        ett.i().deleteStorageData("workout_record", 1, "planId=?", new String[]{StringUtils.c((Object) str)});
    }

    public static int e(long j, String str, String str2) {
        return ett.i().deleteStorageData("workout_record", 1, "exerciseTime=? and userId=? and workoutId=?", new String[]{StringUtils.c(Long.valueOf(j)), str, str2});
    }

    public static WorkoutRecord a(String str, String str2, String str3, String str4, int i) {
        WorkoutRecord a2 = a(new String[]{str2, str, str3, StringUtils.c((Object) str4), StringUtils.c(Integer.valueOf(i))}, "select * from " + ett.i().getTableFullName("workout_record") + " where planId=? and userId=? and workoutId=? and recordType=1 and workoutDate=? and workoutOrder=?");
        if (a2 != null) {
            return a2;
        }
        String d = gib.d(str4, "yyyy-MM-dd", Locale.ENGLISH);
        if (d != null && str4 != null) {
            return !str4.equals(d) ? b(str, str2, str4, i, d) : a2;
        }
        LogUtil.b("Suggestion_WRDaoHelper", "getWorkoutRecords format fail, workoutDate", str4);
        return a2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x003e, code lost:
    
        if (r6 == null) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0026, code lost:
    
        if (r6 != null) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0043, code lost:
    
        return r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0040, code lost:
    
        r6.close();
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0049  */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r3v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static com.huawei.health.sport.model.WorkoutRecord a(java.lang.String[] r6, java.lang.String r7) {
        /*
            java.lang.String r0 = "Suggestion_WRDaoHelper"
            r1 = 0
            r2 = 1
            r3 = 0
            ett r4 = defpackage.ett.i()     // Catch: java.lang.Throwable -> L29 android.database.sqlite.SQLiteException -> L2b
            android.database.Cursor r6 = r4.rawQueryStorageData(r2, r7, r6)     // Catch: java.lang.Throwable -> L29 android.database.sqlite.SQLiteException -> L2b
            if (r6 == 0) goto L26
            boolean r7 = r6.moveToNext()     // Catch: android.database.sqlite.SQLiteException -> L24 java.lang.Throwable -> L44
            if (r7 == 0) goto L26
            java.lang.Object[] r7 = new java.lang.Object[r2]     // Catch: android.database.sqlite.SQLiteException -> L24 java.lang.Throwable -> L44
            java.lang.String r4 = "getWorkoutRecord with compatibility"
            r7[r1] = r4     // Catch: android.database.sqlite.SQLiteException -> L24 java.lang.Throwable -> L44
            com.huawei.hwlogsmodel.LogUtil.a(r0, r7)     // Catch: android.database.sqlite.SQLiteException -> L24 java.lang.Throwable -> L44
            com.huawei.health.sport.model.WorkoutRecord r7 = asw_(r6)     // Catch: android.database.sqlite.SQLiteException -> L24 java.lang.Throwable -> L44
            r3 = r7
            goto L26
        L24:
            r7 = move-exception
            goto L2e
        L26:
            if (r6 == 0) goto L43
            goto L40
        L29:
            r6 = move-exception
            goto L47
        L2b:
            r6 = move-exception
            r7 = r6
            r6 = r3
        L2e:
            r4 = 2
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch: java.lang.Throwable -> L44
            java.lang.String r5 = "getWorkoutRecord data:"
            r4[r1] = r5     // Catch: java.lang.Throwable -> L44
            java.lang.String r7 = health.compact.a.LogAnonymous.b(r7)     // Catch: java.lang.Throwable -> L44
            r4[r2] = r7     // Catch: java.lang.Throwable -> L44
            com.huawei.hwlogsmodel.LogUtil.b(r0, r4)     // Catch: java.lang.Throwable -> L44
            if (r6 == 0) goto L43
        L40:
            r6.close()
        L43:
            return r3
        L44:
            r7 = move-exception
            r3 = r6
            r6 = r7
        L47:
            if (r3 == 0) goto L4c
            r3.close()
        L4c:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.eup.a(java.lang.String[], java.lang.String):com.huawei.health.sport.model.WorkoutRecord");
    }

    public static WorkoutRecord asw_(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        WorkoutRecord workoutRecord = new WorkoutRecord();
        asx_(cursor, workoutRecord);
        String asr_ = asr_(cursor, "heartRateList");
        if (!TextUtils.isEmpty(asr_) && !Constants.NULL.equals(asr_)) {
            workoutRecord.saveHeartRateDataList(moj.b(asr_, HeartRateData[].class));
        }
        String asr_2 = asr_(cursor, "invalidHeartRateList");
        if (!TextUtils.isEmpty(asr_2) && !Constants.NULL.equals(asr_2)) {
            workoutRecord.saveInvalidHeartRateList(moj.b(asr_2, HeartRateData[].class));
        }
        workoutRecord.saveCategory(asu_(cursor, "category"));
        String asr_3 = asr_(cursor, "intensityZone");
        if (!TextUtils.isEmpty(asr_3) && !Constants.NULL.equals(asr_3)) {
            workoutRecord.setIntensityZone(moj.b(asr_3, Integer[].class));
        }
        workoutRecord.setTrainPoint(asu_(cursor, HwExerciseConstants.JSON_NAME_TRAINING_POINTS));
        d(workoutRecord);
        LogUtil.a("Suggestion_WRDaoHelper", "getWorkoutRecord PId=", workoutRecord.acquirePlanId(), ",WId=", workoutRecord.acquireWorkoutId(), ",WName=", workoutRecord.acquireWorkoutName());
        return workoutRecord;
    }

    private static void asx_(Cursor cursor, WorkoutRecord workoutRecord) {
        workoutRecord.savePlanId(asr_(cursor, "planId"));
        workoutRecord.saveId(asu_(cursor, "recordIndex"));
        workoutRecord.saveWorkoutId(asr_(cursor, HwExerciseConstants.METHOD_PARAM_WORKOUT_ID));
        workoutRecord.saveWorkoutName(asr_(cursor, "workoutName"));
        workoutRecord.saveActualCalorie(ast_(cursor, "actualCalorie"));
        workoutRecord.saveActualDistance(ast_(cursor, "actualDistance"));
        workoutRecord.saveCalorie(ast_(cursor, "calorie"));
        workoutRecord.saveDistance(ast_(cursor, "distance"));
        workoutRecord.setDuration(asu_(cursor, "during"));
        workoutRecord.saveWorkoutOrder(asu_(cursor, "workoutOrder"));
        workoutRecord.saveWorkoutDate(asr_(cursor, "workoutDate"));
        workoutRecord.saveExerciseTime(asv_(cursor, "exerciseTime"));
        workoutRecord.saveFinishRate(ast_(cursor, "finishRate"));
        workoutRecord.saveUpperHeartRate(asu_(cursor, "upperHeartRate"));
        workoutRecord.saveLowerHeartRate(asu_(cursor, "lowerHeartRate"));
        workoutRecord.saveBestPace(asu_(cursor, "bestPace"));
        workoutRecord.saveOxygen(ass_(cursor, "oxygen"));
        workoutRecord.saveTrainingLoadPeak(asu_(cursor, "trainingLoadPeak"));
        workoutRecord.saveWeekNum(asu_(cursor, "weekNum"));
        workoutRecord.saveTrajectoryId(asr_(cursor, WorkoutRecord.Extend.RunWorkout.RUN_WORKOUT_TRAJECTORY_ID));
        workoutRecord.saveActionSummary(asr_(cursor, "actionSummary"));
        workoutRecord.saveRecordType(asu_(cursor, "recordType"));
        workoutRecord.saveVersion(asr_(cursor, "version"));
        workoutRecord.saveWearType(asu_(cursor, "wearType"));
        workoutRecord.saveTotalScore(asv_(cursor, ParsedFieldTag.NPES_TOTAL_SCORE));
        workoutRecord.saveRecordModeType(asu_(cursor, "recordModeType"));
        workoutRecord.saveExtend(asr_(cursor, "extend"));
    }

    public static AnnualWorkoutRecord asq_(Cursor cursor) {
        AnnualWorkoutRecord annualWorkoutRecord = new AnnualWorkoutRecord();
        if (cursor == null) {
            return annualWorkoutRecord;
        }
        annualWorkoutRecord.saveWorkoutName(asr_(cursor, "workoutName"));
        annualWorkoutRecord.setDuration(asu_(cursor, "during"));
        annualWorkoutRecord.saveWorkoutDate(asr_(cursor, "workoutDate"));
        annualWorkoutRecord.saveExerciseTime(asv_(cursor, "exerciseTime"));
        annualWorkoutRecord.saveActualCalorie(ast_(cursor, "actualCalorie"));
        annualWorkoutRecord.saveWorkoutId(asr_(cursor, HwExerciseConstants.METHOD_PARAM_WORKOUT_ID));
        annualWorkoutRecord.saveVersion(asr_(cursor, "version"));
        String asr_ = asr_(cursor, "primaryClassify");
        if (!TextUtils.isEmpty(asr_) && !Constants.NULL.equals(asr_)) {
            annualWorkoutRecord.setPrimaryClassify(moj.b(asr_, Classify[].class));
        }
        String asr_2 = asr_(cursor, "secondClassify");
        if (!TextUtils.isEmpty(asr_2) && !Constants.NULL.equals(asr_2)) {
            annualWorkoutRecord.setSecondClassify(moj.b(asr_2, Classify[].class));
        }
        return annualWorkoutRecord;
    }

    private static String asr_(Cursor cursor, String str) {
        try {
            int columnIndex = cursor.getColumnIndex(str);
            return columnIndex == -1 ? "" : cursor.getString(columnIndex);
        } catch (SQLiteException unused) {
            ReleaseLogUtil.c("Suggestion_WRDaoHelper", "getColumnValue SQLiteException");
            return "";
        } catch (IllegalStateException unused2) {
            ReleaseLogUtil.c("Suggestion_WRDaoHelper", "getColumnValue IllegalStateException");
            return "";
        }
    }

    public static int asu_(Cursor cursor, String str) {
        try {
            int columnIndex = cursor.getColumnIndex(str);
            if (columnIndex == -1) {
                return 0;
            }
            return cursor.getInt(columnIndex);
        } catch (SQLiteException unused) {
            ReleaseLogUtil.c("Suggestion_WRDaoHelper", "getColumnValueInt SQLiteException");
            return 0;
        } catch (IllegalStateException unused2) {
            ReleaseLogUtil.c("Suggestion_WRDaoHelper", "getColumnValueInt IllegalStateException");
            return 0;
        }
    }

    private static float ast_(Cursor cursor, String str) {
        try {
            int columnIndex = cursor.getColumnIndex(str);
            if (columnIndex == -1) {
                return 0.0f;
            }
            return cursor.getFloat(columnIndex);
        } catch (SQLiteException unused) {
            ReleaseLogUtil.c("Suggestion_WRDaoHelper", "getColumnValueFloat SQLiteException");
            return 0.0f;
        } catch (IllegalStateException unused2) {
            ReleaseLogUtil.c("Suggestion_WRDaoHelper", "getColumnValueFloat IllegalStateException");
            return 0.0f;
        }
    }

    private static long asv_(Cursor cursor, String str) {
        try {
            int columnIndex = cursor.getColumnIndex(str);
            if (columnIndex == -1) {
                return 0L;
            }
            return cursor.getLong(columnIndex);
        } catch (SQLiteException unused) {
            ReleaseLogUtil.c("Suggestion_WRDaoHelper", "getColumnValueLong SQLiteException");
            return 0L;
        } catch (IllegalStateException unused2) {
            ReleaseLogUtil.c("Suggestion_WRDaoHelper", "getColumnValueLong IllegalStateException");
            return 0L;
        }
    }

    private static double ass_(Cursor cursor, String str) {
        try {
            int columnIndex = cursor.getColumnIndex(str);
            if (columnIndex == -1) {
                return 0.0d;
            }
            return cursor.getDouble(columnIndex);
        } catch (SQLiteException unused) {
            ReleaseLogUtil.c("Suggestion_WRDaoHelper", "getColumnValueDouble SQLiteException");
            return 0.0d;
        } catch (IllegalStateException unused2) {
            ReleaseLogUtil.c("Suggestion_WRDaoHelper", "getColumnValueDouble IllegalStateException");
            return 0.0d;
        }
    }

    private static void d(WorkoutRecord workoutRecord) {
        int e2;
        int e3;
        if (!workoutRecord.isFitnessRecordFromDevice()) {
            LogUtil.a("Suggestion_WRDaoHelper", "record not from device");
            return;
        }
        if (workoutRecord.acquireWorkoutName() == null && (e3 = e.e(workoutRecord.acquireWorkoutId().trim())) != 0) {
            workoutRecord.saveWorkoutName(BaseApplication.getContext().getResources().getString(e3));
        }
        List<RecordAction> b = moj.b(workoutRecord.acquireActionSummary(), RecordAction[].class);
        ArrayList arrayList = new ArrayList(b.size());
        for (RecordAction recordAction : b) {
            if (recordAction != null && (e2 = e(arrayList, recordAction)) != 0) {
                recordAction.setActionName(BaseApplication.getContext().getResources().getString(e2));
                arrayList.add(recordAction);
            }
        }
        workoutRecord.saveActionSummary(new Gson().toJson(arrayList));
    }

    private static int e(List<RecordAction> list, RecordAction recordAction) {
        String trim;
        if (!TextUtils.isEmpty(recordAction.getActionId())) {
            trim = recordAction.getActionId().trim();
        } else {
            trim = !TextUtils.isEmpty(recordAction.getActionName()) ? recordAction.getActionName().trim() : "";
        }
        int e2 = e.e(trim);
        if (e2 != 0) {
            return e2;
        }
        list.add(recordAction);
        return 0;
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0077, code lost:
    
        if (r3 == null) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x005f, code lost:
    
        if (r3 != null) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x007c, code lost:
    
        return r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0079, code lost:
    
        r3.close();
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0082  */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX WARN: Type inference failed for: r7v3, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r7v4 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static com.huawei.health.sport.model.WorkoutRecord b(java.lang.String r3, java.lang.String r4, java.lang.String r5, int r6, java.lang.String r7) {
        /*
            java.lang.String r0 = "getWorkoutRecords format data src: "
            java.lang.String r1 = " dst start data: "
            java.lang.Object[] r5 = new java.lang.Object[]{r0, r5, r1, r7}
            java.lang.String r0 = "Suggestion_WRDaoHelper"
            com.huawei.hwlogsmodel.LogUtil.h(r0, r5)
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r1 = "select * from "
            r5.<init>(r1)
            ett r1 = defpackage.ett.i()
            java.lang.String r2 = "workout_record"
            java.lang.String r1 = r1.getTableFullName(r2)
            r5.append(r1)
            java.lang.String r1 = " where planId=? and userId=? and recordType=1 and workoutDate=? and workoutOrder=?"
            r5.append(r1)
            java.lang.String r5 = r5.toString()
            java.lang.String r7 = health.compact.a.utils.StringUtils.c(r7)
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            java.lang.String r6 = health.compact.a.utils.StringUtils.c(r6)
            java.lang.String[] r3 = new java.lang.String[]{r4, r3, r7, r6}
            r4 = 0
            r6 = 1
            r7 = 0
            ett r1 = defpackage.ett.i()     // Catch: java.lang.Throwable -> L62 android.database.sqlite.SQLiteException -> L64
            android.database.Cursor r3 = r1.rawQueryStorageData(r6, r5, r3)     // Catch: java.lang.Throwable -> L62 android.database.sqlite.SQLiteException -> L64
            if (r3 == 0) goto L5f
            boolean r5 = r3.moveToNext()     // Catch: android.database.sqlite.SQLiteException -> L5d java.lang.Throwable -> L7d
            if (r5 == 0) goto L5f
            java.lang.Object[] r5 = new java.lang.Object[r6]     // Catch: android.database.sqlite.SQLiteException -> L5d java.lang.Throwable -> L7d
            java.lang.String r1 = "getWorkoutRecord"
            r5[r4] = r1     // Catch: android.database.sqlite.SQLiteException -> L5d java.lang.Throwable -> L7d
            com.huawei.hwlogsmodel.LogUtil.a(r0, r5)     // Catch: android.database.sqlite.SQLiteException -> L5d java.lang.Throwable -> L7d
            com.huawei.health.sport.model.WorkoutRecord r4 = asw_(r3)     // Catch: android.database.sqlite.SQLiteException -> L5d java.lang.Throwable -> L7d
            r7 = r4
            goto L5f
        L5d:
            r5 = move-exception
            goto L67
        L5f:
            if (r3 == 0) goto L7c
            goto L79
        L62:
            r3 = move-exception
            goto L80
        L64:
            r3 = move-exception
            r5 = r3
            r3 = r7
        L67:
            r1 = 2
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L7d
            java.lang.String r2 = "getWorkoutRecordCompatibility:"
            r1[r4] = r2     // Catch: java.lang.Throwable -> L7d
            java.lang.String r4 = health.compact.a.LogAnonymous.b(r5)     // Catch: java.lang.Throwable -> L7d
            r1[r6] = r4     // Catch: java.lang.Throwable -> L7d
            com.huawei.hwlogsmodel.LogUtil.b(r0, r1)     // Catch: java.lang.Throwable -> L7d
            if (r3 == 0) goto L7c
        L79:
            r3.close()
        L7c:
            return r7
        L7d:
            r4 = move-exception
            r7 = r3
            r3 = r4
        L80:
            if (r7 == 0) goto L85
            r7.close()
        L85:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.eup.b(java.lang.String, java.lang.String, java.lang.String, int, java.lang.String):com.huawei.health.sport.model.WorkoutRecord");
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0069, code lost:
    
        r2 = defpackage.gib.d(r10, "yyyy-MM-dd", java.util.Locale.ENGLISH);
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0072, code lost:
    
        if (r2 == null) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0074, code lost:
    
        if (r10 != null) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x007b, code lost:
    
        if (r10.equals(r2) != false) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x007d, code lost:
    
        com.huawei.hwlogsmodel.LogUtil.h("Suggestion_WRDaoHelper", "getWorkoutRecords format data src: ", r10, " dst start data: ", r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x008c, code lost:
    
        return c(r8, r9, r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:?, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x008d, code lost:
    
        com.huawei.hwlogsmodel.LogUtil.b("Suggestion_WRDaoHelper", "getWorkoutRecords format fail, workoutDate", r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0096, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0066, code lost:
    
        r6.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0064, code lost:
    
        if (r6 == null) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x004e, code lost:
    
        if (r6 != null) goto L16;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.util.List<com.huawei.health.sport.model.WorkoutRecord> a(java.lang.String r8, java.lang.String r9, java.lang.String r10) {
        /*
            java.lang.String r0 = "Suggestion_WRDaoHelper"
            java.util.ArrayList r1 = new java.util.ArrayList
            r2 = 10
            r1.<init>(r2)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "select * from "
            r2.<init>(r3)
            ett r3 = defpackage.ett.i()
            java.lang.String r4 = "workout_record"
            java.lang.String r3 = r3.getTableFullName(r4)
            r2.append(r3)
            java.lang.String r3 = " where planId=? and userId=? and recordType=1 and workoutDate=? "
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            java.lang.String[] r3 = new java.lang.String[]{r9, r8, r10}
            r4 = 0
            r5 = 1
            r6 = 0
            ett r7 = defpackage.ett.i()     // Catch: java.lang.Throwable -> L51 android.database.sqlite.SQLiteException -> L53
            android.database.Cursor r6 = r7.rawQueryStorageData(r5, r2, r3)     // Catch: java.lang.Throwable -> L51 android.database.sqlite.SQLiteException -> L53
            if (r6 == 0) goto L4e
            boolean r2 = r6.moveToNext()     // Catch: java.lang.Throwable -> L51 android.database.sqlite.SQLiteException -> L53
            if (r2 == 0) goto L4e
            java.lang.Object[] r2 = new java.lang.Object[r5]     // Catch: java.lang.Throwable -> L51 android.database.sqlite.SQLiteException -> L53
            java.lang.String r3 = "getWorkoutRecord by date"
            r2[r4] = r3     // Catch: java.lang.Throwable -> L51 android.database.sqlite.SQLiteException -> L53
            com.huawei.hwlogsmodel.LogUtil.a(r0, r2)     // Catch: java.lang.Throwable -> L51 android.database.sqlite.SQLiteException -> L53
            com.huawei.health.sport.model.WorkoutRecord r2 = asw_(r6)     // Catch: java.lang.Throwable -> L51 android.database.sqlite.SQLiteException -> L53
            r1.add(r2)     // Catch: java.lang.Throwable -> L51 android.database.sqlite.SQLiteException -> L53
        L4e:
            if (r6 == 0) goto L69
            goto L66
        L51:
            r8 = move-exception
            goto L97
        L53:
            r2 = move-exception
            r3 = 2
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> L51
            java.lang.String r7 = "getWorkoutRecordByDay:"
            r3[r4] = r7     // Catch: java.lang.Throwable -> L51
            java.lang.String r2 = health.compact.a.LogAnonymous.b(r2)     // Catch: java.lang.Throwable -> L51
            r3[r5] = r2     // Catch: java.lang.Throwable -> L51
            com.huawei.hwlogsmodel.LogUtil.b(r0, r3)     // Catch: java.lang.Throwable -> L51
            if (r6 == 0) goto L69
        L66:
            r6.close()
        L69:
            java.util.Locale r2 = java.util.Locale.ENGLISH
            java.lang.String r3 = "yyyy-MM-dd"
            java.lang.String r2 = defpackage.gib.d(r10, r3, r2)
            if (r2 == 0) goto L8d
            if (r10 != 0) goto L77
            goto L8d
        L77:
            boolean r3 = r10.equals(r2)
            if (r3 != 0) goto L8c
            java.lang.String r1 = "getWorkoutRecords format data src: "
            java.lang.String r3 = " dst start data: "
            java.lang.Object[] r10 = new java.lang.Object[]{r1, r10, r3, r2}
            com.huawei.hwlogsmodel.LogUtil.h(r0, r10)
            java.util.List r1 = c(r8, r9, r2)
        L8c:
            return r1
        L8d:
            java.lang.String r8 = "getWorkoutRecords format fail, workoutDate"
            java.lang.Object[] r8 = new java.lang.Object[]{r8, r10}
            com.huawei.hwlogsmodel.LogUtil.b(r0, r8)
            return r1
        L97:
            if (r6 == 0) goto L9c
            r6.close()
        L9c:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.eup.a(java.lang.String, java.lang.String, java.lang.String):java.util.List");
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0069, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0066, code lost:
    
        r3.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0064, code lost:
    
        if (r3 == null) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x004e, code lost:
    
        if (r3 != null) goto L16;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.util.List<com.huawei.health.sport.model.WorkoutRecord> c(java.lang.String r5, java.lang.String r6, java.lang.String r7) {
        /*
            java.lang.String r0 = "Suggestion_WRDaoHelper"
            java.util.ArrayList r1 = new java.util.ArrayList
            r2 = 10
            r1.<init>(r2)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "select * from "
            r2.<init>(r3)
            ett r3 = defpackage.ett.i()
            java.lang.String r4 = "workout_record"
            java.lang.String r3 = r3.getTableFullName(r4)
            r2.append(r3)
            java.lang.String r3 = " where planId=? and userId=? and recordType=1 and workoutDate=? "
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            java.lang.String[] r5 = new java.lang.String[]{r6, r5, r7}
            r6 = 0
            r7 = 1
            r3 = 0
            ett r4 = defpackage.ett.i()     // Catch: java.lang.Throwable -> L51 android.database.sqlite.SQLiteException -> L53
            android.database.Cursor r3 = r4.rawQueryStorageData(r7, r2, r5)     // Catch: java.lang.Throwable -> L51 android.database.sqlite.SQLiteException -> L53
            if (r3 == 0) goto L4e
            boolean r5 = r3.moveToNext()     // Catch: java.lang.Throwable -> L51 android.database.sqlite.SQLiteException -> L53
            if (r5 == 0) goto L4e
            java.lang.Object[] r5 = new java.lang.Object[r7]     // Catch: java.lang.Throwable -> L51 android.database.sqlite.SQLiteException -> L53
            java.lang.String r2 = "getWorkoutRecord workout date"
            r5[r6] = r2     // Catch: java.lang.Throwable -> L51 android.database.sqlite.SQLiteException -> L53
            com.huawei.hwlogsmodel.LogUtil.a(r0, r5)     // Catch: java.lang.Throwable -> L51 android.database.sqlite.SQLiteException -> L53
            com.huawei.health.sport.model.WorkoutRecord r5 = asw_(r3)     // Catch: java.lang.Throwable -> L51 android.database.sqlite.SQLiteException -> L53
            r1.add(r5)     // Catch: java.lang.Throwable -> L51 android.database.sqlite.SQLiteException -> L53
        L4e:
            if (r3 == 0) goto L69
            goto L66
        L51:
            r5 = move-exception
            goto L6a
        L53:
            r5 = move-exception
            r2 = 2
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> L51
            java.lang.String r4 = "getWorkoutDataCursor"
            r2[r6] = r4     // Catch: java.lang.Throwable -> L51
            java.lang.String r5 = health.compact.a.LogAnonymous.b(r5)     // Catch: java.lang.Throwable -> L51
            r2[r7] = r5     // Catch: java.lang.Throwable -> L51
            com.huawei.hwlogsmodel.LogUtil.b(r0, r2)     // Catch: java.lang.Throwable -> L51
            if (r3 == 0) goto L69
        L66:
            r3.close()
        L69:
            return r1
        L6a:
            if (r3 == 0) goto L6f
            r3.close()
        L6f:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.eup.c(java.lang.String, java.lang.String, java.lang.String):java.util.List");
    }

    public static boolean e(String str, String str2, String str3, int i) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("workoutDate", gib.d(str3, "yyyy-MM-dd", Locale.ENGLISH));
        ett i2 = ett.i();
        StringBuilder sb = new StringBuilder("planId=? and userId=? and recordType=1 and workoutDate=? and workoutOrder=");
        sb.append(i);
        return i2.updateStorageData("workout_record", 1, contentValues, sb.toString(), new String[]{StringUtils.c((Object) str2), StringUtils.c((Object) str), str3}) > 0;
    }

    public static boolean a() {
        return ett.i().deleteStorageData("workout_record", 1, null, null) == 0;
    }

    public static void e(List<WorkoutRecord> list) {
        if (koq.b(list)) {
            return;
        }
        for (WorkoutRecord workoutRecord : list) {
            if (workoutRecord != null && workoutRecord.acquireCategory() == 0) {
                Integer num = f12338a.get(workoutRecord.acquireWorkoutId());
                int intValue = num != null ? num.intValue() : 0;
                workoutRecord.saveCategory(intValue);
                workoutRecord.setCategoryIntoExtend(intValue);
            }
        }
    }
}
