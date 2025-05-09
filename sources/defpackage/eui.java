package defpackage;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;
import com.huawei.basefitnessadvice.model.Plan;
import com.huawei.basefitnessadvice.model.PlanRecord;
import com.huawei.health.plan.model.data.BaseDao;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import com.huawei.operation.utils.Constants;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import health.compact.a.utils.StringUtils;
import java.util.List;

/* loaded from: classes3.dex */
public class eui extends BaseDao {
    /* JADX WARN: Code restructure failed: missing block: B:19:0x005e, code lost:
    
        if (r7 != null) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0072, code lost:
    
        health.compact.a.util.LogUtil.b("Suggestion_PlanRecordDao", "getJoinedPlans: ", r1.toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x007f, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x006f, code lost:
    
        r7.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x006d, code lost:
    
        if (r7 == null) goto L22;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.util.List<com.huawei.basefitnessadvice.model.PlanRecord> a(int r6, int r7, java.lang.String r8) {
        /*
            r5 = this;
            java.lang.String r0 = "Suggestion_PlanRecordDao"
            if (r6 != 0) goto L7
            int r7 = r7 + 1
            goto L9
        L7:
            int r6 = r6 + 1
        L9:
            java.util.ArrayList r1 = new java.util.ArrayList
            r2 = 10
            r1.<init>(r2)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "select * from "
            r2.<init>(r3)
            ett r3 = defpackage.ett.i()
            java.lang.String r4 = "plan_records"
            java.lang.String r3 = r3.getTableFullName(r4)
            r2.append(r3)
            java.lang.String r3 = " where userId=? AND (status=0 OR (status=1 AND workoutTimes>0)) ORDER BY status ASC,finishDate DESC LIMIT ?,?"
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            java.lang.String r8 = health.compact.a.utils.StringUtils.c(r8)
            java.lang.String r6 = java.lang.String.valueOf(r6)
            java.lang.String r7 = java.lang.String.valueOf(r7)
            java.lang.String[] r6 = new java.lang.String[]{r8, r6, r7}
            r7 = 0
            r8 = 1
            ett r3 = defpackage.ett.i()     // Catch: java.lang.Throwable -> L61 android.database.sqlite.SQLiteException -> L63
            android.database.Cursor r7 = r3.rawQueryStorageData(r8, r2, r6)     // Catch: java.lang.Throwable -> L61 android.database.sqlite.SQLiteException -> L63
            if (r7 == 0) goto L5e
        L49:
            boolean r6 = r7.moveToNext()     // Catch: java.lang.Throwable -> L61 android.database.sqlite.SQLiteException -> L63
            if (r6 == 0) goto L5e
            com.huawei.basefitnessadvice.model.PlanRecord r6 = r5.asm_(r7)     // Catch: java.lang.Throwable -> L61 android.database.sqlite.SQLiteException -> L63
            int r2 = r6.acquirePlanType()     // Catch: java.lang.Throwable -> L61 android.database.sqlite.SQLiteException -> L63
            r3 = 3
            if (r2 == r3) goto L49
            r1.add(r6)     // Catch: java.lang.Throwable -> L61 android.database.sqlite.SQLiteException -> L63
            goto L49
        L5e:
            if (r7 == 0) goto L72
            goto L6f
        L61:
            r6 = move-exception
            goto L80
        L63:
            java.lang.Object[] r6 = new java.lang.Object[r8]     // Catch: java.lang.Throwable -> L61
            java.lang.String r8 = "SQLiteException getPlan"
            r2 = 0
            r6[r2] = r8     // Catch: java.lang.Throwable -> L61
            health.compact.a.util.LogUtil.e(r0, r6)     // Catch: java.lang.Throwable -> L61
            if (r7 == 0) goto L72
        L6f:
            r7.close()
        L72:
            java.lang.String r6 = "getJoinedPlans: "
            java.lang.String r7 = r1.toString()
            java.lang.Object[] r6 = new java.lang.Object[]{r6, r7}
            health.compact.a.util.LogUtil.b(r0, r6)
            return r1
        L80:
            if (r7 == 0) goto L85
            r7.close()
        L85:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.eui.a(int, int, java.lang.String):java.util.List");
    }

    private PlanRecord asm_(Cursor cursor) {
        PlanRecord planRecord = new PlanRecord();
        planRecord.savePlanId(cursor.getString(cursor.getColumnIndex("planId")));
        planRecord.savePlanName(cursor.getString(cursor.getColumnIndex("planName")));
        planRecord.savePlanType(cursor.getInt(cursor.getColumnIndex("planType")));
        planRecord.saveStartDate(cursor.getString(cursor.getColumnIndex(Constants.START_DATE)));
        planRecord.saveEndDate(cursor.getString(cursor.getColumnIndex("endDate")));
        planRecord.saveCalorie(cursor.getFloat(cursor.getColumnIndex("calorie")));
        planRecord.saveDistance(cursor.getFloat(cursor.getColumnIndex("distance")));
        planRecord.saveActualCalorie(cursor.getFloat(cursor.getColumnIndex("actualCalorie")));
        planRecord.saveActualDistance(cursor.getFloat(cursor.getColumnIndex("actualDistance")));
        planRecord.setDuration(cursor.getLong(cursor.getColumnIndex("actualDuration")));
        planRecord.saveFinishRate(cursor.getFloat(cursor.getColumnIndex("finishRate")));
        planRecord.saveFinishDate(cursor.getLong(cursor.getColumnIndex("finishDate")));
        planRecord.saveWeekTimes(cursor.getInt(cursor.getColumnIndex("weekTimes")));
        planRecord.saveWorkoutTimes(cursor.getInt(cursor.getColumnIndex("workoutTimes")));
        planRecord.saveDifficulty(cursor.getInt(cursor.getColumnIndex("difficulty")));
        planRecord.saveWorkoutDays(cursor.getInt(cursor.getColumnIndex("workoutDays")));
        planRecord.saveGoal(cursor.getInt(cursor.getColumnIndex(ParsedFieldTag.GOAL)));
        planRecord.setPlanCategory(cursor.getInt(cursor.getColumnIndex("planCategory")));
        planRecord.saveExcludedDates(gic.i(cursor.getString(cursor.getColumnIndex("excludedDates"))));
        return planRecord;
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x00fd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.huawei.basefitnessadvice.model.Plan a(java.lang.String r5, java.lang.String r6) {
        /*
            Method dump skipped, instructions count: 257
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.eui.a(java.lang.String, java.lang.String):com.huawei.basefitnessadvice.model.Plan");
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0051, code lost:
    
        if (r4 == null) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x003b, code lost:
    
        if (r4 != null) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0056, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0053, code lost:
    
        r4.close();
     */
    /* JADX WARN: Removed duplicated region for block: B:22:0x005c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.huawei.basefitnessadvice.model.PlanRecord e(java.lang.String r4, java.lang.String r5) {
        /*
            r3 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "select * from "
            r0.<init>(r1)
            ett r1 = defpackage.ett.i()
            java.lang.String r2 = "plan_records"
            java.lang.String r1 = r1.getTableFullName(r2)
            r0.append(r1)
            java.lang.String r1 = " where userId=? AND planId=?"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.String[] r4 = new java.lang.String[]{r4, r5}
            r5 = 1
            r1 = 0
            ett r2 = defpackage.ett.i()     // Catch: java.lang.Throwable -> L3e android.database.sqlite.SQLiteException -> L40
            android.database.Cursor r4 = r2.rawQueryStorageData(r5, r0, r4)     // Catch: java.lang.Throwable -> L3e android.database.sqlite.SQLiteException -> L40
            if (r4 == 0) goto L3b
            boolean r0 = r4.moveToNext()     // Catch: android.database.sqlite.SQLiteException -> L39 java.lang.Throwable -> L57
            if (r0 == 0) goto L3b
            com.huawei.basefitnessadvice.model.PlanRecord r5 = r3.asm_(r4)     // Catch: android.database.sqlite.SQLiteException -> L39 java.lang.Throwable -> L57
            r1 = r5
            goto L3b
        L39:
            r0 = move-exception
            goto L43
        L3b:
            if (r4 == 0) goto L56
            goto L53
        L3e:
            r4 = move-exception
            goto L5a
        L40:
            r4 = move-exception
            r0 = r4
            r4 = r1
        L43:
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch: java.lang.Throwable -> L57
            java.lang.String r0 = health.compact.a.LogAnonymous.b(r0)     // Catch: java.lang.Throwable -> L57
            r2 = 0
            r5[r2] = r0     // Catch: java.lang.Throwable -> L57
            java.lang.String r0 = "Suggestion_PlanRecordDao"
            health.compact.a.util.LogUtil.e(r0, r5)     // Catch: java.lang.Throwable -> L57
            if (r4 == 0) goto L56
        L53:
            r4.close()
        L56:
            return r1
        L57:
            r5 = move-exception
            r1 = r4
            r4 = r5
        L5a:
            if (r1 == 0) goto L5f
            r1.close()
        L5f:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.eui.e(java.lang.String, java.lang.String):com.huawei.basefitnessadvice.model.PlanRecord");
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x00a4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.huawei.basefitnessadvice.model.Plan e(java.lang.String r6) {
        /*
            r5 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "select * from "
            r0.<init>(r1)
            ett r1 = defpackage.ett.i()
            java.lang.String r2 = "plan_records"
            java.lang.String r1 = r1.getTableFullName(r2)
            r0.append(r1)
            java.lang.String r1 = " where userId=? and status=0"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.String r6 = health.compact.a.utils.StringUtils.c(r6)
            java.lang.String[] r6 = new java.lang.String[]{r6}
            r1 = 1
            r2 = 0
            ett r3 = defpackage.ett.i()     // Catch: java.lang.Throwable -> L87 android.database.sqlite.SQLiteException -> L89
            android.database.Cursor r6 = r3.rawQueryStorageData(r1, r0, r6)     // Catch: java.lang.Throwable -> L87 android.database.sqlite.SQLiteException -> L89
            if (r6 == 0) goto L81
            boolean r0 = r6.moveToNext()     // Catch: java.lang.Throwable -> L78 android.database.sqlite.SQLiteException -> L7b
            if (r0 == 0) goto L81
            com.huawei.basefitnessadvice.model.Plan r0 = new com.huawei.basefitnessadvice.model.Plan     // Catch: java.lang.Throwable -> L78 android.database.sqlite.SQLiteException -> L7b
            r0.<init>()     // Catch: java.lang.Throwable -> L78 android.database.sqlite.SQLiteException -> L7b
            java.lang.String r2 = "planId"
            int r2 = r6.getColumnIndex(r2)     // Catch: android.database.sqlite.SQLiteException -> L73 java.lang.Throwable -> L78
            java.lang.String r2 = r6.getString(r2)     // Catch: android.database.sqlite.SQLiteException -> L73 java.lang.Throwable -> L78
            r0.saveId(r2)     // Catch: android.database.sqlite.SQLiteException -> L73 java.lang.Throwable -> L78
            java.lang.String r2 = "planName"
            int r2 = r6.getColumnIndex(r2)     // Catch: android.database.sqlite.SQLiteException -> L73 java.lang.Throwable -> L78
            java.lang.String r2 = r6.getString(r2)     // Catch: android.database.sqlite.SQLiteException -> L73 java.lang.Throwable -> L78
            r0.putName(r2)     // Catch: android.database.sqlite.SQLiteException -> L73 java.lang.Throwable -> L78
            java.lang.String r2 = "planType"
            int r2 = r6.getColumnIndex(r2)     // Catch: android.database.sqlite.SQLiteException -> L73 java.lang.Throwable -> L78
            int r2 = r6.getInt(r2)     // Catch: android.database.sqlite.SQLiteException -> L73 java.lang.Throwable -> L78
            r0.saveType(r2)     // Catch: android.database.sqlite.SQLiteException -> L73 java.lang.Throwable -> L78
            java.lang.String r2 = "version"
            int r2 = r6.getColumnIndex(r2)     // Catch: android.database.sqlite.SQLiteException -> L73 java.lang.Throwable -> L78
            java.lang.String r2 = r6.getString(r2)     // Catch: android.database.sqlite.SQLiteException -> L73 java.lang.Throwable -> L78
            r0.setVersion(r2)     // Catch: android.database.sqlite.SQLiteException -> L73 java.lang.Throwable -> L78
            r2 = r0
            goto L81
        L73:
            r2 = move-exception
            r4 = r2
            r2 = r6
            r6 = r4
            goto L8b
        L78:
            r0 = move-exception
            r2 = r6
            goto La1
        L7b:
            r0 = move-exception
            r4 = r2
            r2 = r6
            r6 = r0
            r0 = r4
            goto L8b
        L81:
            if (r6 == 0) goto L9f
            r6.close()
            goto L9f
        L87:
            r6 = move-exception
            goto La2
        L89:
            r6 = move-exception
            r0 = r2
        L8b:
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> La0
            java.lang.String r6 = health.compact.a.LogAnonymous.b(r6)     // Catch: java.lang.Throwable -> La0
            r3 = 0
            r1[r3] = r6     // Catch: java.lang.Throwable -> La0
            java.lang.String r6 = "Suggestion_PlanRecordDao"
            health.compact.a.util.LogUtil.e(r6, r1)     // Catch: java.lang.Throwable -> La0
            if (r2 == 0) goto L9e
            r2.close()
        L9e:
            r2 = r0
        L9f:
            return r2
        La0:
            r0 = move-exception
        La1:
            r6 = r0
        La2:
            if (r2 == 0) goto La7
            r2.close()
        La7:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.eui.e(java.lang.String):com.huawei.basefitnessadvice.model.Plan");
    }

    public boolean c(String str, Plan plan) {
        if (plan == null) {
            return false;
        }
        ett.i().deleteStorageData("plan_records", 1, "userId=? and planId=?", new String[]{StringUtils.c((Object) str), StringUtils.c((Object) plan.acquireId())});
        ContentValues contentValues = new ContentValues();
        contentValues.put(JsbMapKeyNames.H5_USER_ID, str);
        contentValues.put("planId", plan.acquireId());
        contentValues.put("planType", Integer.valueOf(plan.acquireType()));
        contentValues.put("planName", plan.acquireName());
        contentValues.put("workoutCount", Integer.valueOf(plan.getWorkoutCount()));
        contentValues.put("weekCount", Integer.valueOf(plan.getWeekCount()));
        contentValues.put(Constants.START_DATE, plan.acquireStartDate());
        contentValues.put("endDate", plan.getEndDate());
        contentValues.put("calorie", Float.valueOf(plan.getCalorie()));
        contentValues.put("planCategory", Integer.valueOf(plan.getPlanCategory()));
        contentValues.put("distance", Float.valueOf(plan.getDistance()));
        contentValues.put("difficulty", Integer.valueOf(plan.getDifficulty()));
        contentValues.put(ParsedFieldTag.GOAL, Integer.valueOf(plan.acquireGoal()));
        contentValues.put("version", plan.acquireVersion());
        contentValues.put("weekTimes", Integer.valueOf(plan.acquireWeekTimes()));
        contentValues.put("excludedDates", ffm.a(plan.acquireExcludedDates()));
        contentValues.put("status", (Integer) 0);
        return ett.i().insertStorageData("plan_records", 1, contentValues) > 0;
    }

    public void d(String str, List<PlanRecord> list) {
        if (list == null) {
            return;
        }
        ett.i().f();
        c();
        for (PlanRecord planRecord : list) {
            if (planRecord != null) {
                PlanRecord e = e(str, planRecord.acquirePlanId());
                if (e == null) {
                    a(str, planRecord);
                } else if (planRecord.acquireFinishDate() != e.acquireFinishDate()) {
                    e(str, planRecord);
                }
            }
        }
        ett.i().j();
    }

    private void a(String str, PlanRecord planRecord) {
        if (planRecord == null) {
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(JsbMapKeyNames.H5_USER_ID, str);
        contentValues.put("planId", planRecord.acquirePlanId());
        contentValues.put("planType", Integer.valueOf(planRecord.acquirePlanType()));
        contentValues.put("planName", planRecord.acquirePlanName());
        contentValues.put("weekCount", Integer.valueOf(planRecord.acquireWeekCount()));
        contentValues.put(Constants.START_DATE, planRecord.acquireStartDate());
        contentValues.put("endDate", planRecord.acquireEndDate());
        contentValues.put("calorie", Float.valueOf(planRecord.acquireCalorie()));
        contentValues.put("distance", Float.valueOf(planRecord.acquireDistance()));
        contentValues.put("actualCalorie", Float.valueOf(planRecord.acquireActualCalorie()));
        contentValues.put("actualDistance", Float.valueOf(planRecord.acquireActualDistance()));
        contentValues.put("actualDuration", Long.valueOf(planRecord.getTotalDuration()));
        contentValues.put("difficulty", Integer.valueOf(planRecord.acquireDifficulty()));
        contentValues.put(ParsedFieldTag.GOAL, Integer.valueOf(planRecord.acquireGoal()));
        contentValues.put("weekTimes", Integer.valueOf(planRecord.acquireWeekTimes()));
        contentValues.put("finishDate", Long.valueOf(planRecord.acquireFinishDate()));
        contentValues.put("finishRate", Float.valueOf(planRecord.acquireFinishRate()));
        contentValues.put("workoutTimes", Integer.valueOf(planRecord.acquireWorkoutTimes()));
        contentValues.put("workoutDays", Integer.valueOf(planRecord.acquireWorkoutDays()));
        contentValues.put("excludedDates", ffm.a(planRecord.acquireExcludedDates()));
        contentValues.put("version", planRecord.acquireVersion());
        contentValues.put("status", (Integer) 1);
        contentValues.put("planCategory", Integer.valueOf(planRecord.getPlanCategory()));
        ett.i().insertStorageData("plan_records", 1, contentValues);
    }

    public void c(String str, String str2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("workoutRecords", Integer.valueOf(gic.e((Object) "1")));
        ett.i().updateStorageData("plan_records", 1, contentValues, "planId=? AND userId=? AND status=1", new String[]{StringUtils.c((Object) str2), StringUtils.c((Object) str)});
    }

    public void e(String str, PlanRecord planRecord) {
        if (planRecord == null) {
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("actualCalorie", Float.valueOf(planRecord.acquireActualCalorie()));
        contentValues.put("actualDistance", Float.valueOf(planRecord.acquireActualDistance()));
        contentValues.put("actualDuration", Long.valueOf(planRecord.getTotalDuration()));
        contentValues.put("finishRate", Float.valueOf(planRecord.acquireFinishRate()));
        contentValues.put("workoutTimes", Integer.valueOf(planRecord.acquireWorkoutTimes()));
        contentValues.put("workoutDays", Integer.valueOf(planRecord.acquireWorkoutDays()));
        if (!TextUtils.isEmpty(planRecord.acquirePlanName())) {
            contentValues.put("planName", planRecord.acquirePlanName());
        }
        contentValues.put("finishDate", Long.valueOf(planRecord.acquireFinishDate()));
        ett.i().updateStorageData("plan_records", 1, contentValues, "planId=? AND userId=?", new String[]{StringUtils.c((Object) planRecord.acquirePlanId()), StringUtils.c((Object) str)});
    }

    public boolean b(String str, String str2, String str3) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("planName", str3);
        return ett.i().updateStorageData("plan_records", 1, contentValues, "planId=? and userId=?", new String[]{StringUtils.c((Object) str2), StringUtils.c((Object) str)}) > 0;
    }

    public boolean b(String str, String str2, long j) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("status", (Integer) 1);
        contentValues.put("finishDate", Long.valueOf(j));
        return ett.i().updateStorageData("plan_records", 1, contentValues, "planId=? and userId=? and status=0", new String[]{StringUtils.c((Object) str2), StringUtils.c((Object) str)}) > 0;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0048, code lost:
    
        r2.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0046, code lost:
    
        if (r2 == null) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0033, code lost:
    
        if (r2 != null) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x004b, code lost:
    
        return r1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean b(java.lang.String r5, java.lang.String r6) {
        /*
            r4 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "select * from "
            r0.<init>(r1)
            ett r1 = defpackage.ett.i()
            java.lang.String r2 = "plan_records"
            java.lang.String r1 = r1.getTableFullName(r2)
            r0.append(r1)
            java.lang.String r1 = " where userId=? AND planId=? AND workoutRecords=1"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.String[] r5 = new java.lang.String[]{r5, r6}
            r6 = 1
            r1 = 0
            r2 = 0
            ett r3 = defpackage.ett.i()     // Catch: java.lang.Throwable -> L36 android.database.sqlite.SQLiteException -> L38
            android.database.Cursor r2 = r3.rawQueryStorageData(r6, r0, r5)     // Catch: java.lang.Throwable -> L36 android.database.sqlite.SQLiteException -> L38
            if (r2 == 0) goto L33
            boolean r5 = r2.moveToNext()     // Catch: java.lang.Throwable -> L36 android.database.sqlite.SQLiteException -> L38
            r1 = r5
        L33:
            if (r2 == 0) goto L4b
            goto L48
        L36:
            r5 = move-exception
            goto L4c
        L38:
            r5 = move-exception
            java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch: java.lang.Throwable -> L36
            java.lang.String r5 = health.compact.a.LogAnonymous.b(r5)     // Catch: java.lang.Throwable -> L36
            r6[r1] = r5     // Catch: java.lang.Throwable -> L36
            java.lang.String r5 = "Suggestion_PlanRecordDao"
            health.compact.a.util.LogUtil.e(r5, r6)     // Catch: java.lang.Throwable -> L36
            if (r2 == 0) goto L4b
        L48:
            r2.close()
        L4b:
            return r1
        L4c:
            if (r2 == 0) goto L51
            r2.close()
        L51:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.eui.b(java.lang.String, java.lang.String):boolean");
    }

    private void c() {
        ett.i().deleteStorageData("plan_records", 1, "status=1 AND workoutTimes=0");
    }

    public void d(String str, String str2) {
        ett.i().deleteStorageData("plan_records", 1, "userId=? AND planId=?", new String[]{StringUtils.c((Object) str), StringUtils.c((Object) str2)});
    }

    public void d() {
        ett.i().deleteStorageData("plan_records", 1, null, null);
    }
}
