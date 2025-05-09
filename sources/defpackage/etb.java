package defpackage;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import com.huawei.health.plan.model.PlanStat;
import com.huawei.health.plan.model.data.BaseDao;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import health.compact.a.LogAnonymous;
import health.compact.a.utils.StringUtils;

/* loaded from: classes3.dex */
public class etb extends BaseDao {
    private boolean e(int i, int i2) {
        return i == i2;
    }

    public static class e {
        private static final int[] d = {4, 0, 1, 2, 3, 7};
        private static final int[] b = {6, 8, 11};

        /* renamed from: a, reason: collision with root package name */
        private static final int[] f12244a = {5, 9, 10};
    }

    public boolean c(String str, String str2, int i, int i2) {
        int a2 = a(str, str2, i, Integer.valueOf(i2), 1);
        if (a2 >= 0) {
            int e2 = e(str, null, i);
            if (i2 != 0 && (e2 == 0 || i2 < e2)) {
                a(str, null, i, Integer.valueOf(i2), 1);
            }
        }
        return a2 >= 0;
    }

    public void a(String str, String str2, PlanStat planStat) {
        if (planStat == null) {
            return;
        }
        ett.i().f();
        for (int i = 0; i < 12; i++) {
            int a2 = a(str, str2, i, planStat.getValue(str2, i), 0);
            if (a2 >= 0 && i == 11) {
                if (a2 == 0) {
                    e(str, str2, 12, StringUtils.c(planStat.getValue(str2, 12)), 0);
                } else {
                    b(str, str2, 12, StringUtils.c(planStat.getValue(str2, 12)), 0);
                }
            }
        }
        ett.i().j();
    }

    public void d(String str, String str2, PlanStat planStat) {
        LogUtil.c("Suggestion_BestRecordDao", "update best record: userId", str, " planId:", str2);
        if (planStat == null) {
            return;
        }
        ett.i().f();
        for (int i = 0; i < 12; i++) {
            int a2 = a(str, str2, i, planStat.getValue(str2, i), 1);
            if (a2 >= 0 && i == 11) {
                if (a2 == 0) {
                    e(str, str2, 12, StringUtils.c(planStat.getValue(str2, 12)), 1);
                } else {
                    b(str, str2, 12, StringUtils.c(planStat.getValue(str2, 12)), 1);
                }
            }
        }
        ett.i().j();
    }

    public void b(String str, String str2) {
        LogUtil.c("Suggestion_BestRecordDao", "delete best record：userId", str, " planId:", str2);
        ett.i().deleteStorageData("best_record", 1, "planId=? and userId=?", new String[]{StringUtils.c((Object) str2), StringUtils.c((Object) str)});
    }

    public void d(String str, String str2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("status", (Integer) 0);
        ett.i().updateStorageData("best_record", 1, contentValues, "( planId=? OR planId='' ) AND userId=?", new String[]{StringUtils.c((Object) str2), StringUtils.c((Object) str)});
        LogUtil.c("Suggestion_BestRecordDao", "update best record: ", contentValues.toString());
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x006a, code lost:
    
        if (r2 != null) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0083, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0080, code lost:
    
        r2.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x007e, code lost:
    
        if (r2 == null) goto L19;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.huawei.health.plan.model.PlanStat a(java.lang.String r6, java.lang.String r7) {
        /*
            r5 = this;
            com.huawei.health.plan.model.PlanStat r0 = new com.huawei.health.plan.model.PlanStat
            r0.<init>()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "select planId,type,value from "
            r1.<init>(r2)
            ett r2 = defpackage.ett.i()
            java.lang.String r3 = "best_record"
            java.lang.String r2 = r2.getTableFullName(r3)
            r1.append(r2)
            java.lang.String r2 = " where  planId=? and userId=?"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.String r7 = health.compact.a.utils.StringUtils.c(r7)
            java.lang.String r6 = health.compact.a.utils.StringUtils.c(r6)
            java.lang.String[] r6 = new java.lang.String[]{r7, r6}
            r7 = 1
            r2 = 0
            ett r3 = defpackage.ett.i()     // Catch: java.lang.Throwable -> L6d android.database.sqlite.SQLiteException -> L6f
            android.database.Cursor r2 = r3.rawQueryStorageData(r7, r1, r6)     // Catch: java.lang.Throwable -> L6d android.database.sqlite.SQLiteException -> L6f
            if (r2 == 0) goto L6a
        L3a:
            boolean r6 = r2.moveToNext()     // Catch: java.lang.Throwable -> L6d android.database.sqlite.SQLiteException -> L6f
            if (r6 == 0) goto L6a
            java.lang.String r6 = "planId"
            int r6 = r2.getColumnIndex(r6)     // Catch: java.lang.Throwable -> L6d android.database.sqlite.SQLiteException -> L6f
            java.lang.String r6 = r2.getString(r6)     // Catch: java.lang.Throwable -> L6d android.database.sqlite.SQLiteException -> L6f
            java.lang.String r1 = "type"
            int r1 = r2.getColumnIndex(r1)     // Catch: java.lang.Throwable -> L6d android.database.sqlite.SQLiteException -> L6f
            int r1 = r2.getInt(r1)     // Catch: java.lang.Throwable -> L6d android.database.sqlite.SQLiteException -> L6f
            java.lang.String r3 = "value"
            int r3 = r2.getColumnIndex(r3)     // Catch: java.lang.Throwable -> L6d android.database.sqlite.SQLiteException -> L6f
            java.lang.String r3 = r2.getString(r3)     // Catch: java.lang.Throwable -> L6d android.database.sqlite.SQLiteException -> L6f
            boolean r4 = android.text.TextUtils.isEmpty(r3)     // Catch: java.lang.Throwable -> L6d android.database.sqlite.SQLiteException -> L6f
            if (r4 != 0) goto L3a
            r0.setValue(r6, r1, r3)     // Catch: java.lang.Throwable -> L6d android.database.sqlite.SQLiteException -> L6f
            goto L3a
        L6a:
            if (r2 == 0) goto L83
            goto L80
        L6d:
            r6 = move-exception
            goto L84
        L6f:
            r6 = move-exception
            java.lang.Object[] r7 = new java.lang.Object[r7]     // Catch: java.lang.Throwable -> L6d
            java.lang.String r6 = health.compact.a.LogAnonymous.b(r6)     // Catch: java.lang.Throwable -> L6d
            r1 = 0
            r7[r1] = r6     // Catch: java.lang.Throwable -> L6d
            java.lang.String r6 = "Suggestion_BestRecordDao"
            com.huawei.hwlogsmodel.LogUtil.b(r6, r7)     // Catch: java.lang.Throwable -> L6d
            if (r2 == 0) goto L83
        L80:
            r2.close()
        L83:
            return r0
        L84:
            if (r2 == 0) goto L89
            r2.close()
        L89:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.etb.a(java.lang.String, java.lang.String):com.huawei.health.plan.model.PlanStat");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public PlanStat c(String str, String str2) {
        SQLiteException e2;
        PlanStat planStat;
        Throwable th;
        Cursor cursor;
        PlanStat planStat2 = null;
        planStat2 = null;
        planStat2 = null;
        Cursor cursor2 = null;
        try {
            try {
                cursor = ett.i().rawQueryStorageData(1, "SELECT planId,type,value FROM " + ett.i().getTableFullName("best_record") + " WHERE ( planId=? OR planId='' ) AND userId=? AND status=1", new String[]{StringUtils.c((Object) str2), StringUtils.c((Object) str)});
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        try {
                            if (planStat2 == null) {
                                planStat2 = new PlanStat();
                            }
                            String string = cursor.getString(cursor.getColumnIndex("planId"));
                            int i = cursor.getInt(cursor.getColumnIndex("type"));
                            String string2 = cursor.getString(cursor.getColumnIndex("value"));
                            if (!TextUtils.isEmpty(string2)) {
                                planStat2.setValue(string, i, string2);
                            }
                        } catch (SQLiteException e3) {
                            e2 = e3;
                            PlanStat planStat3 = planStat2;
                            cursor2 = cursor;
                            planStat = planStat3;
                            LogUtil.b("Suggestion_BestRecordDao", LogAnonymous.b((Throwable) e2));
                            if (cursor2 != null) {
                                cursor2.close();
                            }
                            planStat2 = planStat;
                            return planStat2;
                        } catch (Throwable th2) {
                            th = th2;
                            if (cursor != null) {
                                cursor.close();
                            }
                            throw th;
                        }
                    }
                }
                if (cursor != null) {
                    cursor.close();
                }
            } catch (Throwable th3) {
                th = th3;
                cursor = planStat2;
            }
        } catch (SQLiteException e4) {
            e2 = e4;
            planStat = null;
        }
        return planStat2;
    }

    public boolean e(String str, String str2) {
        SQLiteException e2;
        boolean z;
        boolean z2 = false;
        Cursor cursor = null;
        try {
            try {
                cursor = ett.i().rawQueryStorageData(1, "select type from " + ett.i().getTableFullName("best_record") + " where ( planId=? OR planId='' ) AND userId=? and status=1", new String[]{StringUtils.c((Object) str2), StringUtils.c((Object) str)});
                if (cursor != null) {
                    z = cursor.moveToNext();
                    try {
                        cursor.close();
                        z2 = z;
                    } catch (SQLiteException e3) {
                        e2 = e3;
                        LogUtil.b("Suggestion_BestRecordDao", LogAnonymous.b((Throwable) e2));
                        return z;
                    }
                }
                if (cursor == null) {
                    return z2;
                }
                cursor.close();
                return z2;
            } catch (SQLiteException e4) {
                e2 = e4;
                z = false;
            }
        } finally {
            if (0 != 0) {
                cursor.close();
            }
        }
    }

    private int e(String str, String str2, int i, Object obj, int i2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("planId", StringUtils.c((Object) str2));
        contentValues.put(JsbMapKeyNames.H5_USER_ID, StringUtils.c((Object) str));
        contentValues.put("type", Integer.valueOf(i));
        contentValues.put("value", StringUtils.c(obj));
        contentValues.put("status", Integer.valueOf(i2));
        ett.i().insertStorageData("best_record", 1, contentValues);
        LogUtil.c("Suggestion_BestRecordDao", "insert best record: ", contentValues.toString());
        return 0;
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0064, code lost:
    
        if (r4 == null) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x004e, code lost:
    
        if (r4 != null) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0069, code lost:
    
        return r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0066, code lost:
    
        r4.close();
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:22:0x006f  */
    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r6v3, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r6v4 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String a(java.lang.String r4, java.lang.String r5, int r6) {
        /*
            r3 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "select value from "
            r0.<init>(r1)
            ett r1 = defpackage.ett.i()
            java.lang.String r2 = "best_record"
            java.lang.String r1 = r1.getTableFullName(r2)
            r0.append(r1)
            java.lang.String r1 = " where  planId=? and userId=? and type=?"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.String r5 = health.compact.a.utils.StringUtils.c(r5)
            java.lang.String r4 = health.compact.a.utils.StringUtils.c(r4)
            java.lang.String r6 = java.lang.Integer.toString(r6)
            java.lang.String[] r4 = new java.lang.String[]{r5, r4, r6}
            r5 = 1
            r6 = 0
            ett r1 = defpackage.ett.i()     // Catch: java.lang.Throwable -> L51 android.database.sqlite.SQLiteException -> L53
            android.database.Cursor r4 = r1.rawQueryStorageData(r5, r0, r4)     // Catch: java.lang.Throwable -> L51 android.database.sqlite.SQLiteException -> L53
            if (r4 == 0) goto L4e
            boolean r0 = r4.moveToNext()     // Catch: android.database.sqlite.SQLiteException -> L4c java.lang.Throwable -> L6a
            if (r0 == 0) goto L4e
            java.lang.String r0 = "value"
            int r0 = r4.getColumnIndex(r0)     // Catch: android.database.sqlite.SQLiteException -> L4c java.lang.Throwable -> L6a
            java.lang.String r5 = r4.getString(r0)     // Catch: android.database.sqlite.SQLiteException -> L4c java.lang.Throwable -> L6a
            r6 = r5
            goto L4e
        L4c:
            r0 = move-exception
            goto L56
        L4e:
            if (r4 == 0) goto L69
            goto L66
        L51:
            r4 = move-exception
            goto L6d
        L53:
            r4 = move-exception
            r0 = r4
            r4 = r6
        L56:
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch: java.lang.Throwable -> L6a
            java.lang.String r0 = health.compact.a.LogAnonymous.b(r0)     // Catch: java.lang.Throwable -> L6a
            r1 = 0
            r5[r1] = r0     // Catch: java.lang.Throwable -> L6a
            java.lang.String r0 = "Suggestion_BestRecordDao"
            com.huawei.hwlogsmodel.LogUtil.b(r0, r5)     // Catch: java.lang.Throwable -> L6a
            if (r4 == 0) goto L69
        L66:
            r4.close()
        L69:
            return r6
        L6a:
            r5 = move-exception
            r6 = r4
            r4 = r5
        L6d:
            if (r6 == 0) goto L72
            r6.close()
        L72:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.etb.a(java.lang.String, java.lang.String, int):java.lang.String");
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x0072, code lost:
    
        if (r7 != null) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0087, code lost:
    
        r7.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0085, code lost:
    
        if (r7 == null) goto L28;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int e(java.lang.String r6, java.lang.String r7, int r8) {
        /*
            r5 = this;
            r0 = 4
            boolean r0 = r5.e(r8, r0)
            r1 = 1
            r2 = 0
            if (r0 != 0) goto L24
            boolean r0 = r5.e(r8, r2)
            if (r0 != 0) goto L24
            boolean r0 = r5.e(r8, r1)
            if (r0 == 0) goto L16
            goto L24
        L16:
            r0 = 2
            boolean r0 = r5.e(r8, r0)
            if (r0 != 0) goto L24
            r0 = 3
            boolean r0 = r5.e(r8, r0)
            if (r0 == 0) goto L8a
        L24:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r3 = "select value from "
            r0.<init>(r3)
            ett r3 = defpackage.ett.i()
            java.lang.String r4 = "best_record"
            java.lang.String r3 = r3.getTableFullName(r4)
            r0.append(r3)
            java.lang.String r3 = " where  planId=? and userId=? and type=?"
            r0.append(r3)
            java.lang.String r0 = r0.toString()
            java.lang.String r7 = health.compact.a.utils.StringUtils.c(r7)
            java.lang.String r6 = health.compact.a.utils.StringUtils.c(r6)
            java.lang.String r8 = java.lang.Integer.toString(r8)
            java.lang.String[] r6 = new java.lang.String[]{r7, r6, r8}
            r7 = 0
            ett r8 = defpackage.ett.i()     // Catch: java.lang.Throwable -> L75 android.database.sqlite.SQLiteException -> L77
            android.database.Cursor r7 = r8.rawQueryStorageData(r1, r0, r6)     // Catch: java.lang.Throwable -> L75 android.database.sqlite.SQLiteException -> L77
            if (r7 == 0) goto L72
            boolean r6 = r7.moveToNext()     // Catch: java.lang.Throwable -> L75 android.database.sqlite.SQLiteException -> L77
            if (r6 == 0) goto L72
            java.lang.String r6 = "value"
            int r6 = r7.getColumnIndex(r6)     // Catch: java.lang.Throwable -> L75 android.database.sqlite.SQLiteException -> L77
            java.lang.String r6 = r7.getString(r6)     // Catch: java.lang.Throwable -> L75 android.database.sqlite.SQLiteException -> L77
            int r6 = defpackage.gic.e(r6)     // Catch: java.lang.Throwable -> L75 android.database.sqlite.SQLiteException -> L77
            r2 = r6
        L72:
            if (r7 == 0) goto L8a
            goto L87
        L75:
            r6 = move-exception
            goto L8b
        L77:
            r6 = move-exception
            java.lang.Object[] r8 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L75
            java.lang.String r6 = health.compact.a.LogAnonymous.b(r6)     // Catch: java.lang.Throwable -> L75
            r8[r2] = r6     // Catch: java.lang.Throwable -> L75
            java.lang.String r6 = "Suggestion_BestRecordDao"
            com.huawei.hwlogsmodel.LogUtil.b(r6, r8)     // Catch: java.lang.Throwable -> L75
            if (r7 == 0) goto L8a
        L87:
            r7.close()
        L8a:
            return r2
        L8b:
            if (r7 == 0) goto L90
            r7.close()
        L90:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.etb.e(java.lang.String, java.lang.String, int):int");
    }

    private boolean b(int i, Object obj, String str) {
        for (int i2 : e.d) {
            if (i == i2) {
                return a(obj, str);
            }
        }
        for (int i3 : e.b) {
            if (i == i3) {
                return d(obj, str);
            }
        }
        for (int i4 : e.f12244a) {
            if (i == i4) {
                return e(obj, str);
            }
        }
        return false;
    }

    private boolean e(Object obj, String str) {
        return gic.b(obj).floatValue() > gic.b((Object) str).floatValue();
    }

    private boolean d(Object obj, String str) {
        return gic.e(obj) > gic.e((Object) str);
    }

    private boolean a(Object obj, String str) {
        int e2 = gic.e(obj);
        return e2 != 0 && e2 < gic.e((Object) str);
    }

    private boolean c(Object obj) {
        return obj instanceof Integer ? gic.e(obj) != 0 : (obj instanceof Float) && gic.b(obj).floatValue() != 0.0f;
    }

    private int a(String str, String str2, int i, Object obj, int i2) {
        if (!c(obj)) {
            return -2;
        }
        String a2 = a(str, str2, i);
        if (a2 == null) {
            int e2 = e(str, str2, i, obj, i2);
            LogUtil.c("Suggestion_BestRecordDao", "insert best record: ", Integer.valueOf(i), obj.toString());
            return e2;
        }
        if (!b(i, obj, a2)) {
            return -1;
        }
        int b = b(str, str2, i, StringUtils.c(obj), i2);
        LogUtil.c("Suggestion_BestRecordDao", "update best record: ", Integer.valueOf(i), obj.toString());
        return b;
    }

    private int b(String str, String str2, int i, String str3, int i2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("value", str3);
        contentValues.put("status", Integer.valueOf(i2));
        ett.i().updateStorageData("best_record", 1, contentValues, "planId=? and userId=? and type=" + i, new String[]{StringUtils.c((Object) str2), StringUtils.c((Object) str)});
        LogUtil.c("Suggestion_BestRecordDao", "update best record: ", contentValues.toString());
        return 1;
    }
}
