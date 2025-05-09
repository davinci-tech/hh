package defpackage;

import android.content.ContentValues;
import com.huawei.basefitnessadvice.model.Plan;
import com.huawei.health.plan.model.data.BaseDao;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.utils.StringUtils;

/* loaded from: classes3.dex */
public class eue extends BaseDao {
    public boolean e(String str, String str2, String str3, String str4) {
        ett.i().deleteStorageData("plans", 1, "planId='" + str + "'");
        ContentValues contentValues = new ContentValues();
        contentValues.put("planId", str);
        contentValues.put("lan", StringUtils.c((Object) str2));
        contentValues.put("version", StringUtils.c((Object) str3));
        contentValues.put("content", str4);
        long insertStorageData = ett.i().insertStorageData("plans", 1, contentValues);
        LogUtil.c("Suggestion_PlanDao", "insertPlan：", contentValues.toString());
        return insertStorageData > 0;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x004d, code lost:
    
        r1.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x004b, code lost:
    
        if (r1 == null) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x003b, code lost:
    
        if (r1 != null) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0050, code lost:
    
        return r6;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean a(java.lang.String r4, java.lang.String r5, java.lang.String r6) {
        /*
            r3 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "select * from "
            r0.<init>(r1)
            ett r1 = defpackage.ett.i()
            java.lang.String r2 = "plans"
            java.lang.String r1 = r1.getTableFullName(r2)
            r0.append(r1)
            java.lang.String r1 = " where planId=? and lan=? and version=?"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.String r5 = health.compact.a.utils.StringUtils.c(r5)
            java.lang.String r6 = health.compact.a.utils.StringUtils.c(r6)
            java.lang.String[] r4 = new java.lang.String[]{r4, r5, r6}
            r5 = 1
            r6 = 0
            r1 = 0
            ett r2 = defpackage.ett.i()     // Catch: java.lang.Throwable -> L3e android.database.sqlite.SQLiteException -> L40
            android.database.Cursor r1 = r2.rawQueryStorageData(r5, r0, r4)     // Catch: java.lang.Throwable -> L3e android.database.sqlite.SQLiteException -> L40
            if (r1 == 0) goto L3b
            boolean r4 = r1.moveToNext()     // Catch: java.lang.Throwable -> L3e android.database.sqlite.SQLiteException -> L40
            r6 = r4
        L3b:
            if (r1 == 0) goto L50
            goto L4d
        L3e:
            r4 = move-exception
            goto L51
        L40:
            java.lang.Object[] r4 = new java.lang.Object[r5]     // Catch: java.lang.Throwable -> L3e
            java.lang.String r5 = "SQLiteException isExistPlan"
            r4[r6] = r5     // Catch: java.lang.Throwable -> L3e
            java.lang.String r5 = "Suggestion_PlanDao"
            com.huawei.hwlogsmodel.LogUtil.b(r5, r4)     // Catch: java.lang.Throwable -> L3e
            if (r1 == 0) goto L50
        L4d:
            r1.close()
        L50:
            return r6
        L51:
            if (r1 == 0) goto L56
            r1.close()
        L56:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.eue.a(java.lang.String, java.lang.String, java.lang.String):boolean");
    }

    public boolean d(String str, Plan plan, String str2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("content", plan.toString());
        int updateStorageData = ett.i().updateStorageData("plans", 1, contentValues, "planId=? and lan=? and version=?", new String[]{plan.acquireId(), StringUtils.c((Object) str), StringUtils.c((Object) str2)});
        LogUtil.a("Suggestion_PlanDao", "update plan clock result:", Integer.valueOf(updateStorageData));
        return updateStorageData > 0;
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x006d, code lost:
    
        if (r8 == null) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0051, code lost:
    
        if (r8 != null) goto L20;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:29:0x007d  */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.huawei.basefitnessadvice.model.Plan e(java.lang.String r7, java.lang.String r8, java.lang.String r9) {
        /*
            r6 = this;
            java.lang.String r0 = "Suggestion_PlanDao"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "select * from "
            r1.<init>(r2)
            ett r2 = defpackage.ett.i()
            java.lang.String r3 = "plans"
            java.lang.String r2 = r2.getTableFullName(r3)
            r1.append(r2)
            java.lang.String r2 = " where planId=? and lan=? and version=?"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.String r8 = health.compact.a.utils.StringUtils.c(r8)
            java.lang.String r2 = health.compact.a.utils.StringUtils.c(r9)
            java.lang.String[] r8 = new java.lang.String[]{r7, r8, r2}
            r2 = 0
            r3 = 1
            r4 = 0
            ett r5 = defpackage.ett.i()     // Catch: java.lang.Throwable -> L54 android.database.sqlite.SQLiteException -> L56 com.google.gson.JsonSyntaxException -> L63
            android.database.Cursor r8 = r5.rawQueryStorageData(r3, r1, r8)     // Catch: java.lang.Throwable -> L54 android.database.sqlite.SQLiteException -> L56 com.google.gson.JsonSyntaxException -> L63
            if (r8 == 0) goto L51
            boolean r1 = r8.moveToNext()     // Catch: android.database.sqlite.SQLiteException -> L57 com.google.gson.JsonSyntaxException -> L64 java.lang.Throwable -> L79
            if (r1 == 0) goto L51
            java.lang.String r1 = "content"
            int r1 = r8.getColumnIndex(r1)     // Catch: android.database.sqlite.SQLiteException -> L57 com.google.gson.JsonSyntaxException -> L64 java.lang.Throwable -> L79
            java.lang.String r1 = r8.getString(r1)     // Catch: android.database.sqlite.SQLiteException -> L57 com.google.gson.JsonSyntaxException -> L64 java.lang.Throwable -> L79
            java.lang.Class<com.huawei.basefitnessadvice.model.Plan> r5 = com.huawei.basefitnessadvice.model.Plan.class
            java.lang.Object r1 = defpackage.moj.e(r1, r5)     // Catch: android.database.sqlite.SQLiteException -> L57 com.google.gson.JsonSyntaxException -> L64 java.lang.Throwable -> L79
            com.huawei.basefitnessadvice.model.Plan r1 = (com.huawei.basefitnessadvice.model.Plan) r1     // Catch: android.database.sqlite.SQLiteException -> L57 com.google.gson.JsonSyntaxException -> L64 java.lang.Throwable -> L79
            r4 = r1
        L51:
            if (r8 == 0) goto L72
            goto L6f
        L54:
            r7 = move-exception
            goto L7b
        L56:
            r8 = r4
        L57:
            java.lang.Object[] r1 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> L79
            java.lang.String r3 = "SQLiteException getPlan"
            r1[r2] = r3     // Catch: java.lang.Throwable -> L79
            com.huawei.hwlogsmodel.LogUtil.b(r0, r1)     // Catch: java.lang.Throwable -> L79
            if (r8 == 0) goto L72
            goto L6f
        L63:
            r8 = r4
        L64:
            java.lang.Object[] r1 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> L79
            java.lang.String r3 = "JsonSyntaxException getPlan"
            r1[r2] = r3     // Catch: java.lang.Throwable -> L79
            com.huawei.hwlogsmodel.LogUtil.b(r0, r1)     // Catch: java.lang.Throwable -> L79
            if (r8 == 0) goto L72
        L6f:
            r8.close()
        L72:
            if (r4 != 0) goto L78
            com.huawei.basefitnessadvice.model.Plan r4 = r6.b(r7, r9)
        L78:
            return r4
        L79:
            r7 = move-exception
            r4 = r8
        L7b:
            if (r4 == 0) goto L80
            r4.close()
        L80:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.eue.e(java.lang.String, java.lang.String, java.lang.String):com.huawei.basefitnessadvice.model.Plan");
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0069, code lost:
    
        if (r6 == null) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x004d, code lost:
    
        if (r6 != null) goto L20;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0074  */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v2, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r3v3 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private com.huawei.basefitnessadvice.model.Plan b(java.lang.String r6, java.lang.String r7) {
        /*
            r5 = this;
            java.lang.String r0 = "Suggestion_PlanDao"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "select * from "
            r1.<init>(r2)
            ett r2 = defpackage.ett.i()
            java.lang.String r3 = "plans"
            java.lang.String r2 = r2.getTableFullName(r3)
            r1.append(r2)
            java.lang.String r2 = " where planId=? and version=?"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.String r7 = health.compact.a.utils.StringUtils.c(r7)
            java.lang.String[] r6 = new java.lang.String[]{r6, r7}
            r7 = 0
            r2 = 1
            r3 = 0
            ett r4 = defpackage.ett.i()     // Catch: java.lang.Throwable -> L50 android.database.sqlite.SQLiteException -> L52 com.google.gson.JsonSyntaxException -> L5f
            android.database.Cursor r6 = r4.rawQueryStorageData(r2, r1, r6)     // Catch: java.lang.Throwable -> L50 android.database.sqlite.SQLiteException -> L52 com.google.gson.JsonSyntaxException -> L5f
            if (r6 == 0) goto L4d
            boolean r1 = r6.moveToNext()     // Catch: android.database.sqlite.SQLiteException -> L53 com.google.gson.JsonSyntaxException -> L60 java.lang.Throwable -> L6f
            if (r1 == 0) goto L4d
            java.lang.String r1 = "content"
            int r1 = r6.getColumnIndex(r1)     // Catch: android.database.sqlite.SQLiteException -> L53 com.google.gson.JsonSyntaxException -> L60 java.lang.Throwable -> L6f
            java.lang.String r1 = r6.getString(r1)     // Catch: android.database.sqlite.SQLiteException -> L53 com.google.gson.JsonSyntaxException -> L60 java.lang.Throwable -> L6f
            java.lang.Class<com.huawei.basefitnessadvice.model.Plan> r4 = com.huawei.basefitnessadvice.model.Plan.class
            java.lang.Object r1 = defpackage.moj.e(r1, r4)     // Catch: android.database.sqlite.SQLiteException -> L53 com.google.gson.JsonSyntaxException -> L60 java.lang.Throwable -> L6f
            com.huawei.basefitnessadvice.model.Plan r1 = (com.huawei.basefitnessadvice.model.Plan) r1     // Catch: android.database.sqlite.SQLiteException -> L53 com.google.gson.JsonSyntaxException -> L60 java.lang.Throwable -> L6f
            r3 = r1
        L4d:
            if (r6 == 0) goto L6e
            goto L6b
        L50:
            r6 = move-exception
            goto L72
        L52:
            r6 = r3
        L53:
            java.lang.Object[] r1 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> L6f
            java.lang.String r2 = "SQLiteException getExistPlan"
            r1[r7] = r2     // Catch: java.lang.Throwable -> L6f
            com.huawei.hwlogsmodel.LogUtil.b(r0, r1)     // Catch: java.lang.Throwable -> L6f
            if (r6 == 0) goto L6e
            goto L6b
        L5f:
            r6 = r3
        L60:
            java.lang.Object[] r1 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> L6f
            java.lang.String r2 = "JsonSyntaxException getExistPlan"
            r1[r7] = r2     // Catch: java.lang.Throwable -> L6f
            com.huawei.hwlogsmodel.LogUtil.b(r0, r1)     // Catch: java.lang.Throwable -> L6f
            if (r6 == 0) goto L6e
        L6b:
            r6.close()
        L6e:
            return r3
        L6f:
            r7 = move-exception
            r3 = r6
            r6 = r7
        L72:
            if (r3 == 0) goto L77
            r3.close()
        L77:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.eue.b(java.lang.String, java.lang.String):com.huawei.basefitnessadvice.model.Plan");
    }
}
