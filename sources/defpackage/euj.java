package defpackage;

import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import com.huawei.basefitnessadvice.model.PlanRecord;
import com.huawei.health.plan.model.PlanStat;
import com.huawei.health.plan.model.data.BaseDao;
import com.huawei.health.plan.model.util.SqlUtil;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import com.huawei.operation.utils.Constants;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import health.compact.a.LogAnonymous;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes3.dex */
public class euj extends BaseDao {
    public static final String c = new SqlUtil.b().a("recordId").e().e("recordIndex", SqlUtil.SqliteColumnType.INTEGER).e(JsbMapKeyNames.H5_USER_ID, SqlUtil.SqliteColumnType.TEXT).e("planId", SqlUtil.SqliteColumnType.TEXT).e(HwExerciseConstants.METHOD_PARAM_WORKOUT_ID, SqlUtil.SqliteColumnType.TEXT).e("workoutName", SqlUtil.SqliteColumnType.TEXT).e("workoutOrder", SqlUtil.SqliteColumnType.INTEGER).e("workoutDate", SqlUtil.SqliteColumnType.TEXT).e("exerciseDate", SqlUtil.SqliteColumnType.INTEGER).e("exerciseTime", SqlUtil.SqliteColumnType.INTEGER).e("weekNum", SqlUtil.SqliteColumnType.INTEGER).e("during", SqlUtil.SqliteColumnType.INTEGER).e("actualCalorie", SqlUtil.SqliteColumnType.REAL).e("actualDistance", SqlUtil.SqliteColumnType.REAL).e("calorie", SqlUtil.SqliteColumnType.REAL).e("distance", SqlUtil.SqliteColumnType.REAL).e("finishRate", SqlUtil.SqliteColumnType.REAL).e("upperHeartRate", SqlUtil.SqliteColumnType.INTEGER).e("lowerHeartRate", SqlUtil.SqliteColumnType.INTEGER).e("bestPace", SqlUtil.SqliteColumnType.INTEGER).e("oxygen", SqlUtil.SqliteColumnType.REAL).e("trainingLoadPeak", SqlUtil.SqliteColumnType.INTEGER).e(WorkoutRecord.Extend.RunWorkout.RUN_WORKOUT_TRAJECTORY_ID, SqlUtil.SqliteColumnType.TEXT).e("actionSummary", SqlUtil.SqliteColumnType.TEXT).e("recordType", SqlUtil.SqliteColumnType.INTEGER).e("version", SqlUtil.SqliteColumnType.TEXT).e("wearType", SqlUtil.SqliteColumnType.INTEGER).e("extend", SqlUtil.SqliteColumnType.TEXT).e("heartRateList", SqlUtil.SqliteColumnType.TEXT).e("invalidHeartRateList", SqlUtil.SqliteColumnType.TEXT).e("category", SqlUtil.SqliteColumnType.INTEGER).e("intensityZone", SqlUtil.SqliteColumnType.TEXT).e(HwExerciseConstants.JSON_NAME_TRAINING_POINTS, SqlUtil.SqliteColumnType.INTEGER).e("sportRecordType", SqlUtil.SqliteColumnType.INTEGER).e(ParsedFieldTag.NPES_TOTAL_SCORE, SqlUtil.SqliteColumnType.INTEGER).e("recordModeType", SqlUtil.SqliteColumnType.INTEGER).b();

    /* JADX WARN: Code restructure failed: missing block: B:12:0x006a, code lost:
    
        if (r4 != null) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0086, code lost:
    
        com.huawei.hwlogsmodel.LogUtil.a("Suggestion_WorkoutRecordDao", "getIntelligentPlanProgress planRecord:", r0.toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0093, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0083, code lost:
    
        r4.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0081, code lost:
    
        if (r4 == null) goto L20;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.huawei.basefitnessadvice.model.PlanRecord d(java.lang.String r6, java.lang.String r7) {
        /*
            ett r0 = defpackage.ett.i()
            eui r0 = r0.t()
            com.huawei.basefitnessadvice.model.PlanRecord r0 = r0.e(r6, r7)
            if (r0 != 0) goto L13
            com.huawei.basefitnessadvice.model.PlanRecord r0 = new com.huawei.basefitnessadvice.model.PlanRecord
            r0.<init>()
        L13:
            r0.savePlanId(r7)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "select plan.finishDate as finishDate,sum( record.actualCalorie) as actualCalorie,sum( record.actualDistance) as actualDistance,sum( record.during) as during,count(distinct record.workoutDate) as days,count(record.recordId) as workoutTimes from "
            r1.<init>(r2)
            ett r2 = defpackage.ett.i()
            java.lang.String r3 = "plan_records"
            java.lang.String r2 = r2.getTableFullName(r3)
            r1.append(r2)
            java.lang.String r2 = " as plan left join "
            r1.append(r2)
            ett r2 = defpackage.ett.i()
            java.lang.String r3 = "workout_record"
            java.lang.String r2 = r2.getTableFullName(r3)
            r1.append(r2)
            java.lang.String r2 = " as record on record.planId = plan.planId and record.userId = plan.userId where plan.planId=? and plan.userId=? and (record.actualCalorie>0 or record.actualDistance>0)"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = "getIntelligentPlanProgress sql:"
            java.lang.Object[] r2 = new java.lang.Object[]{r2, r1}
            java.lang.String r3 = "Suggestion_WorkoutRecordDao"
            com.huawei.hwlogsmodel.LogUtil.a(r3, r2)
            r2 = 1
            r4 = 0
            ett r5 = defpackage.ett.i()     // Catch: java.lang.Throwable -> L6d android.database.sqlite.SQLiteException -> L6f
            java.lang.String[] r6 = new java.lang.String[]{r7, r6}     // Catch: java.lang.Throwable -> L6d android.database.sqlite.SQLiteException -> L6f
            android.database.Cursor r4 = r5.rawQueryStorageData(r2, r1, r6)     // Catch: java.lang.Throwable -> L6d android.database.sqlite.SQLiteException -> L6f
            if (r4 == 0) goto L6a
            boolean r6 = r4.moveToNext()     // Catch: java.lang.Throwable -> L6d android.database.sqlite.SQLiteException -> L6f
            if (r6 == 0) goto L6a
            asp_(r0, r4)     // Catch: java.lang.Throwable -> L6d android.database.sqlite.SQLiteException -> L6f
        L6a:
            if (r4 == 0) goto L86
            goto L83
        L6d:
            r6 = move-exception
            goto L94
        L6f:
            r6 = move-exception
            r7 = 2
            java.lang.Object[] r7 = new java.lang.Object[r7]     // Catch: java.lang.Throwable -> L6d
            java.lang.String r1 = "getIntelligentPlanProgress,"
            r5 = 0
            r7[r5] = r1     // Catch: java.lang.Throwable -> L6d
            java.lang.String r6 = health.compact.a.LogAnonymous.b(r6)     // Catch: java.lang.Throwable -> L6d
            r7[r2] = r6     // Catch: java.lang.Throwable -> L6d
            com.huawei.hwlogsmodel.LogUtil.h(r3, r7)     // Catch: java.lang.Throwable -> L6d
            if (r4 == 0) goto L86
        L83:
            r4.close()
        L86:
            java.lang.String r6 = "getIntelligentPlanProgress planRecord:"
            java.lang.String r7 = r0.toString()
            java.lang.Object[] r6 = new java.lang.Object[]{r6, r7}
            com.huawei.hwlogsmodel.LogUtil.a(r3, r6)
            return r0
        L94:
            if (r4 == 0) goto L99
            r4.close()
        L99:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.euj.d(java.lang.String, java.lang.String):com.huawei.basefitnessadvice.model.PlanRecord");
    }

    private static void asp_(PlanRecord planRecord, Cursor cursor) {
        float f = cursor.getFloat(cursor.getColumnIndex("actualCalorie"));
        if (f > planRecord.acquireActualCalorie()) {
            planRecord.saveActualCalorie(f);
        }
        float f2 = cursor.getFloat(cursor.getColumnIndex("actualDistance"));
        if (f2 > planRecord.acquireActualDistance()) {
            planRecord.saveActualDistance(f2);
        }
        long j = cursor.getInt(cursor.getColumnIndex("during"));
        if (j > planRecord.getTotalDuration()) {
            planRecord.setDuration(j);
        }
        int i = cursor.getInt(cursor.getColumnIndex("days"));
        if (i > planRecord.acquireWorkoutDays()) {
            planRecord.saveWorkoutDays(i);
        }
        int i2 = cursor.getInt(cursor.getColumnIndex("workoutTimes"));
        if (i2 > planRecord.acquireWorkoutTimes()) {
            planRecord.saveWorkoutTimes(i2);
        }
        planRecord.saveFinishDate(cursor.getLong(cursor.getColumnIndex("finishDate")));
        planRecord.saveFinishRate(moe.a(c()));
    }

    private static float c() {
        int b = ase.b(etr.b().getCurrentPlan());
        if (b == 0) {
            return 0.0f;
        }
        return (ase.c(r0) * 100.0f) / b;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x006b, code lost:
    
        if (r5 != null) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0085, code lost:
    
        com.huawei.hwlogsmodel.LogUtil.a("Suggestion_WorkoutRecordDao", "getPlanProgress planRecord:", r1.toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0092, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0082, code lost:
    
        r5.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0080, code lost:
    
        if (r5 == null) goto L17;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.huawei.basefitnessadvice.model.PlanRecord c(java.lang.String r9, java.lang.String r10) {
        /*
            java.lang.String r0 = "Suggestion_WorkoutRecordDao"
            com.huawei.basefitnessadvice.model.PlanRecord r1 = new com.huawei.basefitnessadvice.model.PlanRecord
            r1.<init>()
            r1.savePlanId(r10)
            r2 = 0
            r3 = 2
            r4 = 1
            r5 = 0
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L6e android.database.sqlite.SQLiteException -> L70
            java.lang.String r7 = "select plan.startDate,plan.finishDate,plan.planName,plan.planId,sum(record.actualCalorie) as actualCalorie,sum( case when record.calorie < record.actualCalorie then record.calorie else record.actualCalorie end ) as calorie,sum(record.actualDistance) as actualDistance,sum( record.during) as during,sum( case when record.distance < record.actualDistance then record.distance else record.actualDistance end ) as distance,sum(record.finishRate * ( case when plan.planType=0 then record.distance * 1.0/plan.distance else record.calorie * 1.0/plan.calorie end )) as finishRate,count(distinct record.workoutDate) as workoutDays,count(record.recordId) as workoutTimes from "
            r6.<init>(r7)     // Catch: java.lang.Throwable -> L6e android.database.sqlite.SQLiteException -> L70
            ett r7 = defpackage.ett.i()     // Catch: java.lang.Throwable -> L6e android.database.sqlite.SQLiteException -> L70
            java.lang.String r8 = "plan_records"
            java.lang.String r7 = r7.getTableFullName(r8)     // Catch: java.lang.Throwable -> L6e android.database.sqlite.SQLiteException -> L70
            r6.append(r7)     // Catch: java.lang.Throwable -> L6e android.database.sqlite.SQLiteException -> L70
            java.lang.String r7 = " as plan left join "
            r6.append(r7)     // Catch: java.lang.Throwable -> L6e android.database.sqlite.SQLiteException -> L70
            ett r7 = defpackage.ett.i()     // Catch: java.lang.Throwable -> L6e android.database.sqlite.SQLiteException -> L70
            java.lang.String r8 = "workout_record"
            java.lang.String r7 = r7.getTableFullName(r8)     // Catch: java.lang.Throwable -> L6e android.database.sqlite.SQLiteException -> L70
            r6.append(r7)     // Catch: java.lang.Throwable -> L6e android.database.sqlite.SQLiteException -> L70
            java.lang.String r7 = " as record on record.planId = plan.planId and record.userId = plan.userId where plan.planId=? and record.recordType=? and plan.userId=? and (record.actualCalorie>0 or record.actualDistance>0)"
            r6.append(r7)     // Catch: java.lang.Throwable -> L6e android.database.sqlite.SQLiteException -> L70
            java.lang.String r6 = r6.toString()     // Catch: java.lang.Throwable -> L6e android.database.sqlite.SQLiteException -> L70
            ett r7 = defpackage.ett.i()     // Catch: java.lang.Throwable -> L6e android.database.sqlite.SQLiteException -> L70
            r8 = 3
            java.lang.String[] r8 = new java.lang.String[r8]     // Catch: java.lang.Throwable -> L6e android.database.sqlite.SQLiteException -> L70
            r8[r2] = r10     // Catch: java.lang.Throwable -> L6e android.database.sqlite.SQLiteException -> L70
            java.lang.String r10 = java.lang.String.valueOf(r4)     // Catch: java.lang.Throwable -> L6e android.database.sqlite.SQLiteException -> L70
            r8[r4] = r10     // Catch: java.lang.Throwable -> L6e android.database.sqlite.SQLiteException -> L70
            r8[r3] = r9     // Catch: java.lang.Throwable -> L6e android.database.sqlite.SQLiteException -> L70
            android.database.Cursor r5 = r7.rawQueryStorageData(r4, r6, r8)     // Catch: java.lang.Throwable -> L6e android.database.sqlite.SQLiteException -> L70
            java.lang.Object[] r9 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> L6e android.database.sqlite.SQLiteException -> L70
            java.lang.String r10 = "getPlanProgress sql:"
            r9[r2] = r10     // Catch: java.lang.Throwable -> L6e android.database.sqlite.SQLiteException -> L70
            r9[r4] = r6     // Catch: java.lang.Throwable -> L6e android.database.sqlite.SQLiteException -> L70
            com.huawei.hwlogsmodel.LogUtil.a(r0, r9)     // Catch: java.lang.Throwable -> L6e android.database.sqlite.SQLiteException -> L70
            if (r5 == 0) goto L6b
            boolean r9 = r5.moveToNext()     // Catch: java.lang.Throwable -> L6e android.database.sqlite.SQLiteException -> L70
            if (r9 == 0) goto L6b
            com.huawei.basefitnessadvice.model.PlanRecord r9 = aso_(r5)     // Catch: java.lang.Throwable -> L6e android.database.sqlite.SQLiteException -> L70
            r1 = r9
        L6b:
            if (r5 == 0) goto L85
            goto L82
        L6e:
            r9 = move-exception
            goto L93
        L70:
            r9 = move-exception
            java.lang.Object[] r10 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> L6e
            java.lang.String r3 = "getPlanProgress:"
            r10[r2] = r3     // Catch: java.lang.Throwable -> L6e
            java.lang.String r9 = health.compact.a.LogAnonymous.b(r9)     // Catch: java.lang.Throwable -> L6e
            r10[r4] = r9     // Catch: java.lang.Throwable -> L6e
            com.huawei.hwlogsmodel.LogUtil.b(r0, r10)     // Catch: java.lang.Throwable -> L6e
            if (r5 == 0) goto L85
        L82:
            r5.close()
        L85:
            java.lang.String r9 = "getPlanProgress planRecord:"
            java.lang.String r10 = r1.toString()
            java.lang.Object[] r9 = new java.lang.Object[]{r9, r10}
            com.huawei.hwlogsmodel.LogUtil.a(r0, r9)
            return r1
        L93:
            if (r5 == 0) goto L98
            r5.close()
        L98:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.euj.c(java.lang.String, java.lang.String):com.huawei.basefitnessadvice.model.PlanRecord");
    }

    public static List<PlanRecord> c(int i, int i2, String str) {
        ArrayList arrayList = new ArrayList();
        if (i == 0) {
            i2++;
        } else {
            i++;
        }
        String str2 = "select plan.startDate,plan.finishDate,plan.planName,plan.planId,sum(record.actualCalorie) as actualCalorie,sum( case when record.calorie < record.actualCalorie then record.calorie else record.actualCalorie end ) as calorie,sum(record.actualDistance) as actualDistance,sum( record.during) as during,sum( case when record.distance < record.actualDistance then record.distance else record.actualDistance end ) as distance,sum(record.finishRate * ( case when plan.planType=0 then record.distance * 1.0/plan.distance else record.calorie * 1.0/plan.calorie end )) as finishRate,count(distinct record.workoutDate) as workoutDays,count(record.recordId) as workoutTimes from " + ett.i().getTableFullName("plan_records") + " as plan left join " + ett.i().getTableFullName("workout_record") + " as record on record.planId = plan.planId and record.userId = plan.userId where record.recordType=? and plan.planCategory= ? and plan.userId=? and (record.actualCalorie>0 or record.actualDistance>0) GROUP BY plan.planId ORDER BY finishDate DESC LIMIT ?,?";
        LogUtil.c("Suggestion_WorkoutRecordDao", "getOldRunPlanLocalRecords sql:", str2);
        String[] strArr = {String.valueOf(1), String.valueOf(0), str, String.valueOf(i), String.valueOf(i2)};
        LogUtil.c("Suggestion_WorkoutRecordDao", "getOldRunPlanLocalRecords selectArgs: ", Arrays.toString(strArr));
        try {
            Cursor rawQueryStorageData = ett.i().rawQueryStorageData(1, str2, strArr);
            if (rawQueryStorageData != null) {
                while (rawQueryStorageData.moveToNext()) {
                    try {
                        arrayList.add(aso_(rawQueryStorageData));
                    } finally {
                    }
                }
            }
            if (rawQueryStorageData != null) {
                rawQueryStorageData.close();
            }
        } catch (SQLiteException e) {
            LogUtil.b("Suggestion_WorkoutRecordDao", "getPlanProgress:", LogAnonymous.b((Throwable) e));
        }
        return arrayList;
    }

    private static PlanRecord aso_(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        PlanRecord planRecord = new PlanRecord();
        planRecord.saveActualCalorie(cursor.getFloat(cursor.getColumnIndex("actualCalorie")));
        planRecord.saveActualDistance(cursor.getFloat(cursor.getColumnIndex("actualDistance")));
        planRecord.setDuration(cursor.getLong(cursor.getColumnIndex("during")) * 1000);
        planRecord.saveCalorie(cursor.getFloat(cursor.getColumnIndex("calorie")));
        planRecord.saveDistance(cursor.getFloat(cursor.getColumnIndex("distance")));
        planRecord.saveWorkoutDays(cursor.getInt(cursor.getColumnIndex("workoutDays")));
        planRecord.saveWorkoutTimes(cursor.getInt(cursor.getColumnIndex("workoutTimes")));
        planRecord.saveStartDate(cursor.getString(cursor.getColumnIndex(Constants.START_DATE)));
        planRecord.saveFinishDate(cursor.getLong(cursor.getColumnIndex("finishDate")));
        planRecord.saveFinishRate(moe.a(cursor.getFloat(cursor.getColumnIndex("finishRate"))));
        planRecord.savePlanId(cursor.getString(cursor.getColumnIndex("planId")));
        planRecord.savePlanName(cursor.getString(cursor.getColumnIndex("planName")));
        LogUtil.a("Suggestion_WorkoutRecordDao", "getPlanRecord result: ", planRecord.toString());
        return planRecord;
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x006a, code lost:
    
        if (r6 == null) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0053, code lost:
    
        if (r6 != null) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x006f, code lost:
    
        return r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x006c, code lost:
    
        r6.close();
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0075  */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.huawei.health.sport.model.WorkoutRecord e(java.lang.String r6, java.lang.String r7) {
        /*
            java.lang.String r0 = "Suggestion_WorkoutRecordDao"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "select * from "
            r1.<init>(r2)
            ett r2 = defpackage.ett.i()
            java.lang.String r3 = "workout_record"
            java.lang.String r2 = r2.getTableFullName(r3)
            r1.append(r2)
            java.lang.String r2 = " where userId=? and exerciseTime=?"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.String[] r6 = new java.lang.String[]{r6, r7}
            r7 = 0
            r2 = 2
            r3 = 1
            r4 = 0
            ett r5 = defpackage.ett.i()     // Catch: java.lang.Throwable -> L56 android.database.sqlite.SQLiteException -> L58
            android.database.Cursor r6 = r5.rawQueryStorageData(r3, r1, r6)     // Catch: java.lang.Throwable -> L56 android.database.sqlite.SQLiteException -> L58
            if (r6 == 0) goto L53
            java.lang.Object[] r1 = new java.lang.Object[r2]     // Catch: android.database.sqlite.SQLiteException -> L51 java.lang.Throwable -> L70
            java.lang.String r5 = "Columns="
            r1[r7] = r5     // Catch: android.database.sqlite.SQLiteException -> L51 java.lang.Throwable -> L70
            int r5 = r6.getColumnCount()     // Catch: android.database.sqlite.SQLiteException -> L51 java.lang.Throwable -> L70
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch: android.database.sqlite.SQLiteException -> L51 java.lang.Throwable -> L70
            r1[r3] = r5     // Catch: android.database.sqlite.SQLiteException -> L51 java.lang.Throwable -> L70
            com.huawei.hwlogsmodel.LogUtil.a(r0, r1)     // Catch: android.database.sqlite.SQLiteException -> L51 java.lang.Throwable -> L70
            boolean r1 = r6.moveToNext()     // Catch: android.database.sqlite.SQLiteException -> L51 java.lang.Throwable -> L70
            if (r1 == 0) goto L53
            com.huawei.health.sport.model.WorkoutRecord r7 = defpackage.eup.asw_(r6)     // Catch: android.database.sqlite.SQLiteException -> L51 java.lang.Throwable -> L70
            r4 = r7
            goto L53
        L51:
            r1 = move-exception
            goto L5b
        L53:
            if (r6 == 0) goto L6f
            goto L6c
        L56:
            r6 = move-exception
            goto L73
        L58:
            r6 = move-exception
            r1 = r6
            r6 = r4
        L5b:
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> L70
            java.lang.String r5 = "getWorkoutRecord"
            r2[r7] = r5     // Catch: java.lang.Throwable -> L70
            java.lang.String r7 = health.compact.a.LogAnonymous.b(r1)     // Catch: java.lang.Throwable -> L70
            r2[r3] = r7     // Catch: java.lang.Throwable -> L70
            com.huawei.hwlogsmodel.LogUtil.b(r0, r2)     // Catch: java.lang.Throwable -> L70
            if (r6 == 0) goto L6f
        L6c:
            r6.close()
        L6f:
            return r4
        L70:
            r7 = move-exception
            r4 = r6
            r6 = r7
        L73:
            if (r4 == 0) goto L78
            r4.close()
        L78:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.euj.e(java.lang.String, java.lang.String):com.huawei.health.sport.model.WorkoutRecord");
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0052, code lost:
    
        if (r3 != null) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x006e, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x006b, code lost:
    
        r3.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0069, code lost:
    
        if (r3 == null) goto L23;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.util.List<com.huawei.health.sport.model.WorkoutRecord> a(java.lang.String r6, java.lang.String r7) {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r1 = 10
            r0.<init>(r1)
            boolean r1 = android.text.TextUtils.isEmpty(r6)
            java.lang.String r2 = "Suggestion_WorkoutRecordDao"
            if (r1 != 0) goto L75
            boolean r1 = android.text.TextUtils.isEmpty(r7)
            if (r1 == 0) goto L16
            goto L75
        L16:
            java.lang.String[] r6 = new java.lang.String[]{r7, r6}
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r1 = "select * from "
            r7.<init>(r1)
            ett r1 = defpackage.ett.i()
            java.lang.String r3 = "workout_record"
            java.lang.String r1 = r1.getTableFullName(r3)
            r7.append(r1)
            java.lang.String r1 = " where planId=? and userId=? and recordType=1"
            r7.append(r1)
            java.lang.String r7 = r7.toString()
            r1 = 1
            r3 = 0
            ett r4 = defpackage.ett.i()     // Catch: java.lang.Throwable -> L55 android.database.sqlite.SQLiteException -> L57
            android.database.Cursor r3 = r4.rawQueryStorageData(r1, r7, r6)     // Catch: java.lang.Throwable -> L55 android.database.sqlite.SQLiteException -> L57
            if (r3 == 0) goto L52
        L44:
            boolean r6 = r3.moveToNext()     // Catch: java.lang.Throwable -> L55 android.database.sqlite.SQLiteException -> L57
            if (r6 == 0) goto L52
            com.huawei.health.sport.model.WorkoutRecord r6 = defpackage.eup.asw_(r3)     // Catch: java.lang.Throwable -> L55 android.database.sqlite.SQLiteException -> L57
            r0.add(r6)     // Catch: java.lang.Throwable -> L55 android.database.sqlite.SQLiteException -> L57
            goto L44
        L52:
            if (r3 == 0) goto L6e
            goto L6b
        L55:
            r6 = move-exception
            goto L6f
        L57:
            r6 = move-exception
            r7 = 2
            java.lang.Object[] r7 = new java.lang.Object[r7]     // Catch: java.lang.Throwable -> L55
            java.lang.String r4 = "getWorkoutRecords:"
            r5 = 0
            r7[r5] = r4     // Catch: java.lang.Throwable -> L55
            java.lang.String r6 = health.compact.a.LogAnonymous.b(r6)     // Catch: java.lang.Throwable -> L55
            r7[r1] = r6     // Catch: java.lang.Throwable -> L55
            com.huawei.hwlogsmodel.LogUtil.b(r2, r7)     // Catch: java.lang.Throwable -> L55
            if (r3 == 0) goto L6e
        L6b:
            r3.close()
        L6e:
            return r0
        L6f:
            if (r3 == 0) goto L74
            r3.close()
        L74:
            throw r6
        L75:
            java.lang.String r6 = "the value of userId planId is null"
            java.lang.Object[] r6 = new java.lang.Object[]{r6}
            com.huawei.hwlogsmodel.LogUtil.b(r2, r6)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.euj.a(java.lang.String, java.lang.String):java.util.List");
    }

    /* JADX WARN: Code restructure failed: missing block: B:38:0x00fc, code lost:
    
        if (0 == 0) goto L34;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.util.List<com.huawei.health.sport.model.WorkoutRecord> b(java.lang.String r21, java.lang.String r22, java.lang.String r23, java.lang.String r24) {
        /*
            Method dump skipped, instructions count: 264
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.euj.b(java.lang.String, java.lang.String, java.lang.String, java.lang.String):java.util.List");
    }

    public static PlanStat b(String str, String str2, int i) {
        if (i == 0) {
            return f(str, str2);
        }
        return h(str, str2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x00a2, code lost:
    
        if (r5 != null) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x00be, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x00bb, code lost:
    
        r5.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x00b9, code lost:
    
        if (r5 == null) goto L21;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static com.huawei.health.plan.model.PlanStat f(java.lang.String r11, java.lang.String r12) {
        /*
            java.lang.String r0 = "workout_record"
            com.huawei.health.plan.model.PlanStat r1 = new com.huawei.health.plan.model.PlanStat
            r1.<init>()
            r2 = 0
            r3 = 2
            r4 = 1
            r5 = 0
            ett r6 = defpackage.ett.i()     // Catch: java.lang.Throwable -> La5 android.database.sqlite.SQLiteException -> La7
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> La5 android.database.sqlite.SQLiteException -> La7
            java.lang.String r8 = "select max(actualDistance) as actualDistance,max(during) as during from "
            r7.<init>(r8)     // Catch: java.lang.Throwable -> La5 android.database.sqlite.SQLiteException -> La7
            ett r8 = defpackage.ett.i()     // Catch: java.lang.Throwable -> La5 android.database.sqlite.SQLiteException -> La7
            java.lang.String r8 = r8.getTableFullName(r0)     // Catch: java.lang.Throwable -> La5 android.database.sqlite.SQLiteException -> La7
            r7.append(r8)     // Catch: java.lang.Throwable -> La5 android.database.sqlite.SQLiteException -> La7
            java.lang.String r8 = " where  userId=? and recordType=? and planId=?"
            r7.append(r8)     // Catch: java.lang.Throwable -> La5 android.database.sqlite.SQLiteException -> La7
            java.lang.String r7 = r7.toString()     // Catch: java.lang.Throwable -> La5 android.database.sqlite.SQLiteException -> La7
            r8 = 3
            java.lang.String[] r9 = new java.lang.String[r8]     // Catch: java.lang.Throwable -> La5 android.database.sqlite.SQLiteException -> La7
            r9[r2] = r11     // Catch: java.lang.Throwable -> La5 android.database.sqlite.SQLiteException -> La7
            java.lang.String r10 = java.lang.String.valueOf(r4)     // Catch: java.lang.Throwable -> La5 android.database.sqlite.SQLiteException -> La7
            r9[r4] = r10     // Catch: java.lang.Throwable -> La5 android.database.sqlite.SQLiteException -> La7
            r9[r3] = r12     // Catch: java.lang.Throwable -> La5 android.database.sqlite.SQLiteException -> La7
            android.database.Cursor r5 = r6.rawQueryStorageData(r4, r7, r9)     // Catch: java.lang.Throwable -> La5 android.database.sqlite.SQLiteException -> La7
            if (r5 == 0) goto L5e
            boolean r6 = r5.moveToNext()     // Catch: java.lang.Throwable -> La5 android.database.sqlite.SQLiteException -> La7
            if (r6 == 0) goto L5e
            java.lang.String r6 = "actualDistance"
            int r6 = r5.getColumnIndex(r6)     // Catch: java.lang.Throwable -> La5 android.database.sqlite.SQLiteException -> La7
            float r6 = r5.getFloat(r6)     // Catch: java.lang.Throwable -> La5 android.database.sqlite.SQLiteException -> La7
            r1.setFarthestRunning(r6)     // Catch: java.lang.Throwable -> La5 android.database.sqlite.SQLiteException -> La7
            java.lang.String r6 = "during"
            int r6 = r5.getColumnIndex(r6)     // Catch: java.lang.Throwable -> La5 android.database.sqlite.SQLiteException -> La7
            int r6 = r5.getInt(r6)     // Catch: java.lang.Throwable -> La5 android.database.sqlite.SQLiteException -> La7
            r1.setLongestRunning(r6)     // Catch: java.lang.Throwable -> La5 android.database.sqlite.SQLiteException -> La7
        L5e:
            ett r6 = defpackage.ett.i()     // Catch: java.lang.Throwable -> La5 android.database.sqlite.SQLiteException -> La7
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> La5 android.database.sqlite.SQLiteException -> La7
            java.lang.String r9 = "SELECT bestPace FROM "
            r7.<init>(r9)     // Catch: java.lang.Throwable -> La5 android.database.sqlite.SQLiteException -> La7
            ett r9 = defpackage.ett.i()     // Catch: java.lang.Throwable -> La5 android.database.sqlite.SQLiteException -> La7
            java.lang.String r0 = r9.getTableFullName(r0)     // Catch: java.lang.Throwable -> La5 android.database.sqlite.SQLiteException -> La7
            r7.append(r0)     // Catch: java.lang.Throwable -> La5 android.database.sqlite.SQLiteException -> La7
            java.lang.String r0 = " WHERE userId=? and recordType=? AND planId=? AND actualDistance>=1 ORDER BY exerciseTime LIMIT 0,1 "
            r7.append(r0)     // Catch: java.lang.Throwable -> La5 android.database.sqlite.SQLiteException -> La7
            java.lang.String r0 = r7.toString()     // Catch: java.lang.Throwable -> La5 android.database.sqlite.SQLiteException -> La7
            java.lang.String[] r7 = new java.lang.String[r8]     // Catch: java.lang.Throwable -> La5 android.database.sqlite.SQLiteException -> La7
            r7[r2] = r11     // Catch: java.lang.Throwable -> La5 android.database.sqlite.SQLiteException -> La7
            java.lang.String r11 = java.lang.String.valueOf(r4)     // Catch: java.lang.Throwable -> La5 android.database.sqlite.SQLiteException -> La7
            r7[r4] = r11     // Catch: java.lang.Throwable -> La5 android.database.sqlite.SQLiteException -> La7
            r7[r3] = r12     // Catch: java.lang.Throwable -> La5 android.database.sqlite.SQLiteException -> La7
            android.database.Cursor r5 = r6.rawQueryStorageData(r4, r0, r7)     // Catch: java.lang.Throwable -> La5 android.database.sqlite.SQLiteException -> La7
            if (r5 == 0) goto La2
            boolean r11 = r5.moveToNext()     // Catch: java.lang.Throwable -> La5 android.database.sqlite.SQLiteException -> La7
            if (r11 == 0) goto La2
            java.lang.String r11 = "bestPace"
            int r11 = r5.getColumnIndex(r11)     // Catch: java.lang.Throwable -> La5 android.database.sqlite.SQLiteException -> La7
            int r11 = r5.getInt(r11)     // Catch: java.lang.Throwable -> La5 android.database.sqlite.SQLiteException -> La7
            r1.setBestRecordForFirstOneKm(r11)     // Catch: java.lang.Throwable -> La5 android.database.sqlite.SQLiteException -> La7
        La2:
            if (r5 == 0) goto Lbe
            goto Lbb
        La5:
            r11 = move-exception
            goto Lbf
        La7:
            r11 = move-exception
            java.lang.Object[] r12 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> La5
            java.lang.String r0 = "getRunPlanStat"
            r12[r2] = r0     // Catch: java.lang.Throwable -> La5
            java.lang.String r11 = health.compact.a.LogAnonymous.b(r11)     // Catch: java.lang.Throwable -> La5
            r12[r4] = r11     // Catch: java.lang.Throwable -> La5
            java.lang.String r11 = "Suggestion_WorkoutRecordDao"
            com.huawei.hwlogsmodel.LogUtil.b(r11, r12)     // Catch: java.lang.Throwable -> La5
            if (r5 == 0) goto Lbe
        Lbb:
            r5.close()
        Lbe:
            return r1
        Lbf:
            if (r5 == 0) goto Lc4
            r5.close()
        Lc4:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.euj.f(java.lang.String, java.lang.String):com.huawei.health.plan.model.PlanStat");
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x00a9, code lost:
    
        if (r5 != null) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x00c5, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x00c2, code lost:
    
        r5.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x00c0, code lost:
    
        if (r5 == null) goto L21;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static com.huawei.health.plan.model.PlanStat h(java.lang.String r11, java.lang.String r12) {
        /*
            java.lang.String r0 = "workout_record"
            com.huawei.health.plan.model.PlanStat r1 = new com.huawei.health.plan.model.PlanStat
            r1.<init>()
            r2 = 0
            r3 = 2
            r4 = 1
            r5 = 0
            ett r6 = defpackage.ett.i()     // Catch: java.lang.Throwable -> Lac android.database.sqlite.SQLiteException -> Lae
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lac android.database.sqlite.SQLiteException -> Lae
            java.lang.String r8 = "select max(s.actualCalorie) as actualCalorie , max(s.during) as during from ( select sum(actualCalorie) as actualCalorie,sum(during) as during from "
            r7.<init>(r8)     // Catch: java.lang.Throwable -> Lac android.database.sqlite.SQLiteException -> Lae
            ett r8 = defpackage.ett.i()     // Catch: java.lang.Throwable -> Lac android.database.sqlite.SQLiteException -> Lae
            java.lang.String r8 = r8.getTableFullName(r0)     // Catch: java.lang.Throwable -> Lac android.database.sqlite.SQLiteException -> Lae
            r7.append(r8)     // Catch: java.lang.Throwable -> Lac android.database.sqlite.SQLiteException -> Lae
            java.lang.String r8 = " where  userId=? and recordType=? and planId=? group by weekNum ) as s"
            r7.append(r8)     // Catch: java.lang.Throwable -> Lac android.database.sqlite.SQLiteException -> Lae
            java.lang.String r7 = r7.toString()     // Catch: java.lang.Throwable -> Lac android.database.sqlite.SQLiteException -> Lae
            r8 = 3
            java.lang.String[] r9 = new java.lang.String[r8]     // Catch: java.lang.Throwable -> Lac android.database.sqlite.SQLiteException -> Lae
            r9[r2] = r11     // Catch: java.lang.Throwable -> Lac android.database.sqlite.SQLiteException -> Lae
            java.lang.String r10 = java.lang.String.valueOf(r4)     // Catch: java.lang.Throwable -> Lac android.database.sqlite.SQLiteException -> Lae
            r9[r4] = r10     // Catch: java.lang.Throwable -> Lac android.database.sqlite.SQLiteException -> Lae
            r9[r3] = r12     // Catch: java.lang.Throwable -> Lac android.database.sqlite.SQLiteException -> Lae
            android.database.Cursor r5 = r6.rawQueryStorageData(r4, r7, r9)     // Catch: java.lang.Throwable -> Lac android.database.sqlite.SQLiteException -> Lae
            if (r5 == 0) goto L5e
            boolean r6 = r5.moveToNext()     // Catch: java.lang.Throwable -> Lac android.database.sqlite.SQLiteException -> Lae
            if (r6 == 0) goto L5e
            java.lang.String r6 = "actualCalorie"
            int r6 = r5.getColumnIndex(r6)     // Catch: java.lang.Throwable -> Lac android.database.sqlite.SQLiteException -> Lae
            float r6 = r5.getFloat(r6)     // Catch: java.lang.Throwable -> Lac android.database.sqlite.SQLiteException -> Lae
            r1.saveMostCaloriePerWeek(r6)     // Catch: java.lang.Throwable -> Lac android.database.sqlite.SQLiteException -> Lae
            java.lang.String r6 = "during"
            int r6 = r5.getColumnIndex(r6)     // Catch: java.lang.Throwable -> Lac android.database.sqlite.SQLiteException -> Lae
            int r6 = r5.getInt(r6)     // Catch: java.lang.Throwable -> Lac android.database.sqlite.SQLiteException -> Lae
            r1.saveLongestTimePerWeek(r6)     // Catch: java.lang.Throwable -> Lac android.database.sqlite.SQLiteException -> Lae
        L5e:
            e(r11, r12, r1)     // Catch: java.lang.Throwable -> Lac android.database.sqlite.SQLiteException -> Lae
            ett r6 = defpackage.ett.i()     // Catch: java.lang.Throwable -> Lac android.database.sqlite.SQLiteException -> Lae
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lac android.database.sqlite.SQLiteException -> Lae
            java.lang.String r9 = "select max(finishRate) as finishRate from "
            r7.<init>(r9)     // Catch: java.lang.Throwable -> Lac android.database.sqlite.SQLiteException -> Lae
            ett r9 = defpackage.ett.i()     // Catch: java.lang.Throwable -> Lac android.database.sqlite.SQLiteException -> Lae
            java.lang.String r0 = r9.getTableFullName(r0)     // Catch: java.lang.Throwable -> Lac android.database.sqlite.SQLiteException -> Lae
            r7.append(r0)     // Catch: java.lang.Throwable -> Lac android.database.sqlite.SQLiteException -> Lae
            java.lang.String r0 = " where userId=? and recordType=? and planId=?"
            r7.append(r0)     // Catch: java.lang.Throwable -> Lac android.database.sqlite.SQLiteException -> Lae
            java.lang.String r0 = r7.toString()     // Catch: java.lang.Throwable -> Lac android.database.sqlite.SQLiteException -> Lae
            java.lang.String[] r7 = new java.lang.String[r8]     // Catch: java.lang.Throwable -> Lac android.database.sqlite.SQLiteException -> Lae
            r7[r2] = r11     // Catch: java.lang.Throwable -> Lac android.database.sqlite.SQLiteException -> Lae
            java.lang.String r11 = java.lang.String.valueOf(r4)     // Catch: java.lang.Throwable -> Lac android.database.sqlite.SQLiteException -> Lae
            r7[r4] = r11     // Catch: java.lang.Throwable -> Lac android.database.sqlite.SQLiteException -> Lae
            r7[r3] = r12     // Catch: java.lang.Throwable -> Lac android.database.sqlite.SQLiteException -> Lae
            android.database.Cursor r5 = r6.rawQueryStorageData(r4, r0, r7)     // Catch: java.lang.Throwable -> Lac android.database.sqlite.SQLiteException -> Lae
            if (r5 == 0) goto La9
            boolean r11 = r5.moveToNext()     // Catch: java.lang.Throwable -> Lac android.database.sqlite.SQLiteException -> Lae
            if (r11 == 0) goto La9
            java.lang.String r11 = "finishRate"
            int r11 = r5.getColumnIndex(r11)     // Catch: java.lang.Throwable -> Lac android.database.sqlite.SQLiteException -> Lae
            float r11 = r5.getFloat(r11)     // Catch: java.lang.Throwable -> Lac android.database.sqlite.SQLiteException -> Lae
            float r11 = defpackage.moe.a(r11)     // Catch: java.lang.Throwable -> Lac android.database.sqlite.SQLiteException -> Lae
            r1.saveHighestCompleteRate(r11)     // Catch: java.lang.Throwable -> Lac android.database.sqlite.SQLiteException -> Lae
        La9:
            if (r5 == 0) goto Lc5
            goto Lc2
        Lac:
            r11 = move-exception
            goto Lc6
        Lae:
            r11 = move-exception
            java.lang.Object[] r12 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> Lac
            java.lang.String r0 = "getFitPlanStatistics:"
            r12[r2] = r0     // Catch: java.lang.Throwable -> Lac
            java.lang.String r11 = health.compact.a.LogAnonymous.b(r11)     // Catch: java.lang.Throwable -> Lac
            r12[r4] = r11     // Catch: java.lang.Throwable -> Lac
            java.lang.String r11 = "Suggestion_WorkoutRecordDao"
            com.huawei.hwlogsmodel.LogUtil.b(r11, r12)     // Catch: java.lang.Throwable -> Lac
            if (r5 == 0) goto Lc5
        Lc2:
            r5.close()
        Lc5:
            return r1
        Lc6:
            if (r5 == 0) goto Lcb
            r5.close()
        Lcb:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.euj.h(java.lang.String, java.lang.String):com.huawei.health.plan.model.PlanStat");
    }

    private static void e(String str, String str2, PlanStat planStat) {
        if (planStat == null) {
            return;
        }
        Cursor cursor = null;
        try {
            try {
                cursor = ett.i().rawQueryStorageData(1, "select  workoutId ,workoutName, max(s.workoutTimes) as workoutTimes from (select workoutId ,workoutName,count(workoutId) as workoutTimes from " + ett.i().getTableFullName("workout_record") + " where userId=? and recordType=? and planId=? group by workoutId ) as s", new String[]{str, String.valueOf(1), str2});
                if (cursor != null && cursor.moveToNext()) {
                    planStat.saveMostWorkoutTimes(cursor.getInt(cursor.getColumnIndex("workoutTimes")));
                    planStat.setMostWorkoutName(cursor.getString(cursor.getColumnIndex("workoutName")));
                }
                if (cursor == null) {
                    return;
                }
            } catch (SQLiteException e) {
                LogUtil.b("Suggestion_WorkoutRecordDao", "getMostTimeWorkout:", LogAnonymous.b((Throwable) e));
                if (cursor == null) {
                    return;
                }
            }
            cursor.close();
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0041, code lost:
    
        if (r2 != null) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x005e, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x005b, code lost:
    
        r2.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0059, code lost:
    
        if (r2 == null) goto L18;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int e(java.lang.String r7, java.lang.String r8, java.lang.String r9) {
        /*
            r0 = 1
            r1 = 0
            r2 = 0
            ett r3 = defpackage.ett.i()     // Catch: java.lang.Throwable -> L44 android.database.sqlite.SQLiteException -> L46
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L44 android.database.sqlite.SQLiteException -> L46
            java.lang.String r5 = "select count(recordId) as workoutCount from "
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L44 android.database.sqlite.SQLiteException -> L46
            ett r5 = defpackage.ett.i()     // Catch: java.lang.Throwable -> L44 android.database.sqlite.SQLiteException -> L46
            java.lang.String r6 = "workout_record"
            java.lang.String r5 = r5.getTableFullName(r6)     // Catch: java.lang.Throwable -> L44 android.database.sqlite.SQLiteException -> L46
            r4.append(r5)     // Catch: java.lang.Throwable -> L44 android.database.sqlite.SQLiteException -> L46
            java.lang.String r5 = " where userId=? and workoutId=? and version=?"
            r4.append(r5)     // Catch: java.lang.Throwable -> L44 android.database.sqlite.SQLiteException -> L46
            java.lang.String r4 = r4.toString()     // Catch: java.lang.Throwable -> L44 android.database.sqlite.SQLiteException -> L46
            java.lang.String[] r7 = new java.lang.String[]{r7, r8, r9}     // Catch: java.lang.Throwable -> L44 android.database.sqlite.SQLiteException -> L46
            android.database.Cursor r2 = r3.rawQueryStorageData(r0, r4, r7)     // Catch: java.lang.Throwable -> L44 android.database.sqlite.SQLiteException -> L46
            if (r2 == 0) goto L41
            boolean r7 = r2.moveToNext()     // Catch: java.lang.Throwable -> L44 android.database.sqlite.SQLiteException -> L46
            if (r7 == 0) goto L41
            java.lang.String r7 = "workoutCount"
            int r7 = r2.getColumnIndex(r7)     // Catch: java.lang.Throwable -> L44 android.database.sqlite.SQLiteException -> L46
            int r7 = r2.getInt(r7)     // Catch: java.lang.Throwable -> L44 android.database.sqlite.SQLiteException -> L46
            r1 = r7
        L41:
            if (r2 == 0) goto L5e
            goto L5b
        L44:
            r7 = move-exception
            goto L5f
        L46:
            r7 = move-exception
            r8 = 2
            java.lang.Object[] r8 = new java.lang.Object[r8]     // Catch: java.lang.Throwable -> L44
            java.lang.String r9 = "getWorkoutCompleteTimes:"
            r8[r1] = r9     // Catch: java.lang.Throwable -> L44
            java.lang.String r7 = health.compact.a.LogAnonymous.b(r7)     // Catch: java.lang.Throwable -> L44
            r8[r0] = r7     // Catch: java.lang.Throwable -> L44
            java.lang.String r7 = "Suggestion_WorkoutRecordDao"
            com.huawei.hwlogsmodel.LogUtil.b(r7, r8)     // Catch: java.lang.Throwable -> L44
            if (r2 == 0) goto L5e
        L5b:
            r2.close()
        L5e:
            return r1
        L5f:
            if (r2 == 0) goto L64
            r2.close()
        L64:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.euj.e(java.lang.String, java.lang.String, java.lang.String):int");
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0059, code lost:
    
        if (r2 != null) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0077, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0074, code lost:
    
        r2.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0072, code lost:
    
        if (r2 == null) goto L18;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.util.Map<java.lang.String, java.lang.Integer> b(java.lang.String r7, java.lang.String r8) {
        /*
            java.util.HashMap r0 = new java.util.HashMap
            r1 = 16
            r0.<init>(r1)
            r1 = 1
            r2 = 0
            ett r3 = defpackage.ett.i()     // Catch: java.lang.Throwable -> L5c android.database.sqlite.SQLiteException -> L5e
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L5c android.database.sqlite.SQLiteException -> L5e
            java.lang.String r5 = "SELECT workoutOrder ,workoutDate FROM "
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L5c android.database.sqlite.SQLiteException -> L5e
            ett r5 = defpackage.ett.i()     // Catch: java.lang.Throwable -> L5c android.database.sqlite.SQLiteException -> L5e
            java.lang.String r6 = "workout_record"
            java.lang.String r5 = r5.getTableFullName(r6)     // Catch: java.lang.Throwable -> L5c android.database.sqlite.SQLiteException -> L5e
            r4.append(r5)     // Catch: java.lang.Throwable -> L5c android.database.sqlite.SQLiteException -> L5e
            java.lang.String r5 = " WHERE planId=? AND userId=? ORDER BY exerciseTime"
            r4.append(r5)     // Catch: java.lang.Throwable -> L5c android.database.sqlite.SQLiteException -> L5e
            java.lang.String r4 = r4.toString()     // Catch: java.lang.Throwable -> L5c android.database.sqlite.SQLiteException -> L5e
            java.lang.String[] r7 = new java.lang.String[]{r8, r7}     // Catch: java.lang.Throwable -> L5c android.database.sqlite.SQLiteException -> L5e
            android.database.Cursor r2 = r3.rawQueryStorageData(r1, r4, r7)     // Catch: java.lang.Throwable -> L5c android.database.sqlite.SQLiteException -> L5e
            if (r2 == 0) goto L59
        L35:
            boolean r7 = r2.moveToNext()     // Catch: java.lang.Throwable -> L5c android.database.sqlite.SQLiteException -> L5e
            if (r7 == 0) goto L59
            java.lang.String r7 = "workoutDate"
            int r7 = r2.getColumnIndex(r7)     // Catch: java.lang.Throwable -> L5c android.database.sqlite.SQLiteException -> L5e
            java.lang.String r7 = r2.getString(r7)     // Catch: java.lang.Throwable -> L5c android.database.sqlite.SQLiteException -> L5e
            java.lang.String r8 = "workoutOrder"
            int r8 = r2.getColumnIndex(r8)     // Catch: java.lang.Throwable -> L5c android.database.sqlite.SQLiteException -> L5e
            int r8 = r2.getInt(r8)     // Catch: java.lang.Throwable -> L5c android.database.sqlite.SQLiteException -> L5e
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch: java.lang.Throwable -> L5c android.database.sqlite.SQLiteException -> L5e
            r0.put(r7, r8)     // Catch: java.lang.Throwable -> L5c android.database.sqlite.SQLiteException -> L5e
            goto L35
        L59:
            if (r2 == 0) goto L77
            goto L74
        L5c:
            r7 = move-exception
            goto L78
        L5e:
            r7 = move-exception
            r8 = 2
            java.lang.Object[] r8 = new java.lang.Object[r8]     // Catch: java.lang.Throwable -> L5c
            java.lang.String r3 = "getWorkoutOrders:"
            r4 = 0
            r8[r4] = r3     // Catch: java.lang.Throwable -> L5c
            java.lang.String r7 = health.compact.a.LogAnonymous.b(r7)     // Catch: java.lang.Throwable -> L5c
            r8[r1] = r7     // Catch: java.lang.Throwable -> L5c
            java.lang.String r7 = "Suggestion_WorkoutRecordDao"
            com.huawei.hwlogsmodel.LogUtil.b(r7, r8)     // Catch: java.lang.Throwable -> L5c
            if (r2 == 0) goto L77
        L74:
            r2.close()
        L77:
            return r0
        L78:
            if (r2 == 0) goto L7d
            r2.close()
        L7d:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.euj.b(java.lang.String, java.lang.String):java.util.Map");
    }

    public static List<WorkoutRecord> c(String str, long j, long j2) {
        String str2 = "select * from " + ett.i().getTableFullName("workout_record") + " where workoutId GLOB ?  and userId = ?  and exerciseTime between " + j + " and " + j2 + " order by exerciseTime desc";
        ArrayList arrayList = new ArrayList();
        Cursor rawQueryStorageData = ett.i().rawQueryStorageData(1, str2, new String[]{"?[0-9][0-9]?*", str});
        if (rawQueryStorageData != null) {
            while (rawQueryStorageData.moveToNext()) {
                arrayList.add(eup.asw_(rawQueryStorageData));
            }
            rawQueryStorageData.close();
        }
        return arrayList;
    }

    public static WorkoutRecord e(String str, long j, String str2) {
        String str3 = "select * from " + ett.i().getTableFullName("workout_record") + " where userId=? and workoutId=? and exerciseTime between ? and ?";
        String[] strArr = {str, str2, Long.toString(j - 1000), Long.toString(j + 1000)};
        WorkoutRecord workoutRecord = null;
        try {
            Cursor rawQueryStorageData = ett.i().rawQueryStorageData(1, str3, strArr);
            if (rawQueryStorageData != null) {
                try {
                    LogUtil.a("Suggestion_WorkoutRecordDao", "Columns=", Integer.valueOf(rawQueryStorageData.getColumnCount()));
                    if (rawQueryStorageData.moveToNext()) {
                        workoutRecord = eup.asw_(rawQueryStorageData);
                    }
                } finally {
                }
            }
            if (rawQueryStorageData != null) {
                rawQueryStorageData.close();
            }
        } catch (SQLiteException e) {
            LogUtil.b("Suggestion_WorkoutRecordDao", "getWorkoutRecord", LogAnonymous.b((Throwable) e));
        }
        return workoutRecord;
    }
}
