package defpackage;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import com.huawei.health.plan.model.data.BaseDao;
import com.huawei.health.plan.model.model.BestRecordFitStat;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import health.compact.a.LogAnonymous;
import health.compact.a.utils.StringUtils;

/* loaded from: classes3.dex */
public class ete extends BaseDao {

    public static class b {

        /* renamed from: a, reason: collision with root package name */
        private static final int[] f12247a = new int[0];
        private static final int[] e = {1};
        private static final int[] b = new int[0];
    }

    static class c {
        int b;
        String c;
        long d;
        int e;

        private c() {
        }
    }

    private int e(String str, c cVar, Object obj) {
        if (cVar == null || !a(obj)) {
            return -2;
        }
        String c2 = c(str, cVar.c, cVar.b);
        if (c2 == null) {
            int c3 = c(str, cVar, obj);
            LogUtil.c("Suggestion_BestRecordFitDao", "insert best record: ", Integer.valueOf(cVar.b), obj.toString());
            return c3;
        }
        if (!c(cVar.b, obj, c2)) {
            return -1;
        }
        int c4 = c(str, cVar, StringUtils.c(obj));
        LogUtil.c("Suggestion_BestRecordFitDao", "update best record: ", Integer.valueOf(cVar.b), obj.toString());
        return c4;
    }

    private int c(String str, c cVar, String str2) {
        if (cVar == null) {
            return -1;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("value", str2);
        contentValues.put("status", Integer.valueOf(cVar.e));
        contentValues.put(ParsedFieldTag.TASK_COMPLETE_TIME, Long.valueOf(cVar.d));
        ett.i().updateStorageData("best_record_fit", 1, contentValues, "actionId=? and userId=? and type=" + cVar.b, new String[]{StringUtils.c((Object) cVar.c), StringUtils.c((Object) str)});
        LogUtil.c("Suggestion_BestRecordFitDao", "update best record: ", contentValues.toString());
        return 1;
    }

    public void a(String str, String str2, WorkoutRecord workoutRecord) {
        if (workoutRecord == null) {
            return;
        }
        LogUtil.c("Suggestion_BestRecordFitDao", "update best record：userId", str, " actionId:", str2);
        ett.i().f();
        c cVar = new c();
        cVar.c = str2;
        cVar.b = 1;
        cVar.e = 1;
        int acquireBestTimes = workoutRecord.acquireBestTimes();
        cVar.d = workoutRecord.acquireExerciseTime();
        LogUtil.c("Suggestion_BestRecordFitDao", "updateBestRecords(String userId, String actionId, WorkoutRecord workoutRecord) result = ", Integer.valueOf(e(str, cVar, Integer.valueOf(acquireBestTimes))));
        ett.i().j();
    }

    public void c(String str, String str2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("status", (Integer) 0);
        ett.i().updateStorageData("best_record_fit", 1, contentValues, "( actionId=? OR actionId='' ) AND userId=?", new String[]{StringUtils.c((Object) str2), StringUtils.c((Object) str)});
        LogUtil.c("Suggestion_BestRecordFitDao", "update best record: ", contentValues.toString());
    }

    private int c(String str, c cVar, Object obj) {
        if (cVar == null) {
            return -1;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("actionId", StringUtils.c((Object) cVar.c));
        contentValues.put(JsbMapKeyNames.H5_USER_ID, StringUtils.c((Object) str));
        contentValues.put("type", Integer.valueOf(cVar.b));
        contentValues.put("value", StringUtils.c(obj));
        contentValues.put("status", Integer.valueOf(cVar.e));
        contentValues.put(ParsedFieldTag.TASK_COMPLETE_TIME, Long.valueOf(cVar.d));
        ett.i().insertStorageData("best_record_fit", 1, contentValues);
        LogUtil.c("Suggestion_BestRecordFitDao", "insert best record：", contentValues.toString());
        return 0;
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0071, code lost:
    
        if (r4 == null) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x005b, code lost:
    
        if (r4 != null) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0076, code lost:
    
        return r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0073, code lost:
    
        r4.close();
     */
    /* JADX WARN: Removed duplicated region for block: B:22:0x007c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String c(java.lang.String r4, java.lang.String r5, int r6) {
        /*
            r3 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "select value from "
            r0.<init>(r1)
            ett r1 = defpackage.ett.i()
            java.lang.String r2 = "best_record_fit"
            java.lang.String r1 = r1.getTableFullName(r2)
            r0.append(r1)
            java.lang.String r1 = " where  actionId=? and userId=? and type=?"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.String r5 = health.compact.a.utils.StringUtils.c(r5)
            java.lang.String r4 = health.compact.a.utils.StringUtils.c(r4)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r6)
            java.lang.String r6 = ""
            r1.append(r6)
            java.lang.String r6 = r1.toString()
            java.lang.String[] r4 = new java.lang.String[]{r5, r4, r6}
            r5 = 1
            r6 = 0
            ett r1 = defpackage.ett.i()     // Catch: java.lang.Throwable -> L5e android.database.sqlite.SQLiteException -> L60
            android.database.Cursor r4 = r1.rawQueryStorageData(r5, r0, r4)     // Catch: java.lang.Throwable -> L5e android.database.sqlite.SQLiteException -> L60
            if (r4 == 0) goto L5b
            boolean r0 = r4.moveToNext()     // Catch: android.database.sqlite.SQLiteException -> L59 java.lang.Throwable -> L77
            if (r0 == 0) goto L5b
            java.lang.String r0 = "value"
            int r0 = r4.getColumnIndex(r0)     // Catch: android.database.sqlite.SQLiteException -> L59 java.lang.Throwable -> L77
            java.lang.String r5 = r4.getString(r0)     // Catch: android.database.sqlite.SQLiteException -> L59 java.lang.Throwable -> L77
            r6 = r5
            goto L5b
        L59:
            r0 = move-exception
            goto L63
        L5b:
            if (r4 == 0) goto L76
            goto L73
        L5e:
            r4 = move-exception
            goto L7a
        L60:
            r4 = move-exception
            r0 = r4
            r4 = r6
        L63:
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch: java.lang.Throwable -> L77
            java.lang.String r0 = health.compact.a.LogAnonymous.b(r0)     // Catch: java.lang.Throwable -> L77
            r1 = 0
            r5[r1] = r0     // Catch: java.lang.Throwable -> L77
            java.lang.String r0 = "Suggestion_BestRecordFitDao"
            com.huawei.hwlogsmodel.LogUtil.b(r0, r5)     // Catch: java.lang.Throwable -> L77
            if (r4 == 0) goto L76
        L73:
            r4.close()
        L76:
            return r6
        L77:
            r5 = move-exception
            r6 = r4
            r4 = r5
        L7a:
            if (r6 == 0) goto L7f
            r6.close()
        L7f:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ete.c(java.lang.String, java.lang.String, int):java.lang.String");
    }

    public BestRecordFitStat a(String str, String str2) {
        Throwable th;
        Cursor cursor;
        SQLiteException e;
        BestRecordFitStat bestRecordFitStat;
        Cursor cursor2 = null;
        r1 = null;
        BestRecordFitStat bestRecordFitStat2 = null;
        cursor2 = null;
        try {
            try {
                cursor = ett.i().rawQueryStorageData(1, "SELECT *  FROM " + ett.i().getTableFullName("best_record_fit") + " WHERE ( actionId=? OR actionId='' ) AND userId=? AND status=1", new String[]{StringUtils.c((Object) str2), StringUtils.c((Object) str)});
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        try {
                            if (bestRecordFitStat2 == null) {
                                bestRecordFitStat2 = new BestRecordFitStat();
                            }
                            String string = cursor.getString(cursor.getColumnIndex("actionId"));
                            int i = cursor.getInt(cursor.getColumnIndex("type"));
                            String string2 = cursor.getString(cursor.getColumnIndex("value"));
                            long j = cursor.getLong(cursor.getColumnIndex(ParsedFieldTag.TASK_COMPLETE_TIME));
                            if (!TextUtils.isEmpty(string2)) {
                                bestRecordFitStat2.setValue(string, i, string2, j);
                            }
                        } catch (SQLiteException e2) {
                            e = e2;
                            BestRecordFitStat bestRecordFitStat3 = bestRecordFitStat2;
                            cursor2 = cursor;
                            bestRecordFitStat = bestRecordFitStat3;
                            LogUtil.b("Suggestion_BestRecordFitDao", LogAnonymous.b((Throwable) e));
                            if (cursor2 != null) {
                                cursor2.close();
                            }
                            return bestRecordFitStat;
                        } catch (Throwable th2) {
                            th = th2;
                            if (cursor != null) {
                                cursor.close();
                            }
                            throw th;
                        }
                    }
                }
                if (cursor == null) {
                    return bestRecordFitStat2;
                }
                cursor.close();
                return bestRecordFitStat2;
            } catch (SQLiteException e3) {
                e = e3;
                bestRecordFitStat = null;
            }
        } catch (Throwable th3) {
            th = th3;
            cursor = cursor2;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0050, code lost:
    
        r2.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x004e, code lost:
    
        if (r2 == null) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x003b, code lost:
    
        if (r2 != null) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0053, code lost:
    
        return r1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean e(java.lang.String r5, java.lang.String r6) {
        /*
            r4 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "select type from "
            r0.<init>(r1)
            ett r1 = defpackage.ett.i()
            java.lang.String r2 = "best_record_fit"
            java.lang.String r1 = r1.getTableFullName(r2)
            r0.append(r1)
            java.lang.String r1 = " where ( actionId=? OR actionId='' ) AND userId=? and status=1"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.String r6 = health.compact.a.utils.StringUtils.c(r6)
            java.lang.String r5 = health.compact.a.utils.StringUtils.c(r5)
            java.lang.String[] r5 = new java.lang.String[]{r6, r5}
            r6 = 1
            r1 = 0
            r2 = 0
            ett r3 = defpackage.ett.i()     // Catch: java.lang.Throwable -> L3e android.database.sqlite.SQLiteException -> L40
            android.database.Cursor r2 = r3.rawQueryStorageData(r6, r0, r5)     // Catch: java.lang.Throwable -> L3e android.database.sqlite.SQLiteException -> L40
            if (r2 == 0) goto L3b
            boolean r5 = r2.moveToNext()     // Catch: java.lang.Throwable -> L3e android.database.sqlite.SQLiteException -> L40
            r1 = r5
        L3b:
            if (r2 == 0) goto L53
            goto L50
        L3e:
            r5 = move-exception
            goto L54
        L40:
            r5 = move-exception
            java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch: java.lang.Throwable -> L3e
            java.lang.String r5 = health.compact.a.LogAnonymous.b(r5)     // Catch: java.lang.Throwable -> L3e
            r6[r1] = r5     // Catch: java.lang.Throwable -> L3e
            java.lang.String r5 = "Suggestion_BestRecordFitDao"
            com.huawei.hwlogsmodel.LogUtil.b(r5, r6)     // Catch: java.lang.Throwable -> L3e
            if (r2 == 0) goto L53
        L50:
            r2.close()
        L53:
            return r1
        L54:
            if (r2 == 0) goto L59
            r2.close()
        L59:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ete.e(java.lang.String, java.lang.String):boolean");
    }

    private boolean c(int i, Object obj, String str) {
        for (int i2 : b.f12247a) {
            if (i == i2) {
                return b(obj, str);
            }
        }
        for (int i3 : b.e) {
            if (i == i3) {
                return d(obj, str);
            }
        }
        for (int i4 : b.b) {
            if (i == i4) {
                return a(obj, str);
            }
        }
        return false;
    }

    private boolean a(Object obj, String str) {
        return gic.b(obj).floatValue() > gic.b((Object) str).floatValue();
    }

    private boolean d(Object obj, String str) {
        return gic.e(obj) > gic.e((Object) str);
    }

    private boolean b(Object obj, String str) {
        int e = gic.e(obj);
        return e != 0 && e < gic.e((Object) str);
    }

    private boolean a(Object obj) {
        return gic.e(obj) != 0;
    }
}
