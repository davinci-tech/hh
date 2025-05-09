package defpackage;

import android.content.ContentValues;
import com.huawei.health.plan.model.data.BaseDao;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import health.compact.a.utils.StringUtils;

/* loaded from: classes3.dex */
public class euk extends BaseDao {
    public void a(String str, String str2, int i) {
        LogUtil.c("Suggestion_TrainCountDao", "insert：beginTransaction");
        ett.i().f();
        int b = b(str, str2);
        if (b == 0) {
            c(str, str2, i);
        } else if (i <= b) {
            LogUtil.c("Suggestion_TrainCountDao", "insert：count=", Integer.valueOf(i), ", idCount=", Integer.valueOf(b));
        } else {
            b(str, str2, i);
        }
        ett.i().j();
        LogUtil.c("Suggestion_TrainCountDao", "insert：endTransaction");
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x005d, code lost:
    
        if (r4 != null) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0079, code lost:
    
        return r7;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int b(java.lang.String r7, java.lang.String r8) {
        /*
            r6 = this;
            java.lang.String r0 = "Suggestion_TrainCountDao"
            ett r1 = defpackage.ett.i()
            java.lang.String r2 = "train_count"
            java.lang.String r1 = r1.getTableFullName(r2)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "select * from "
            r2.<init>(r3)
            r2.append(r1)
            java.lang.String r1 = " where userId=? and id=?"
            r2.append(r1)
            java.lang.String r1 = r2.toString()
            java.lang.String r7 = health.compact.a.utils.StringUtils.c(r7)
            java.lang.String r8 = health.compact.a.utils.StringUtils.c(r8)
            java.lang.String[] r7 = new java.lang.String[]{r7, r8}
            r8 = 2
            r2 = 1
            r3 = 0
            r4 = 0
            ett r5 = defpackage.ett.i()     // Catch: java.lang.Throwable -> L60 android.database.sqlite.SQLiteException -> L62
            android.database.Cursor r4 = r5.rawQueryStorageData(r2, r1, r7)     // Catch: java.lang.Throwable -> L60 android.database.sqlite.SQLiteException -> L62
            r7 = r3
            if (r4 == 0) goto L4e
        L3b:
            boolean r1 = r4.moveToNext()     // Catch: android.database.sqlite.SQLiteException -> L4c java.lang.Throwable -> L60
            if (r1 == 0) goto L4e
            java.lang.String r1 = "count"
            int r1 = r4.getColumnIndex(r1)     // Catch: android.database.sqlite.SQLiteException -> L4c java.lang.Throwable -> L60
            int r7 = r4.getInt(r1)     // Catch: android.database.sqlite.SQLiteException -> L4c java.lang.Throwable -> L60
            goto L3b
        L4c:
            r1 = move-exception
            goto L65
        L4e:
            java.lang.Object[] r1 = new java.lang.Object[r8]     // Catch: android.database.sqlite.SQLiteException -> L4c java.lang.Throwable -> L60
            java.lang.String r5 = "getIdCount count = "
            r1[r3] = r5     // Catch: android.database.sqlite.SQLiteException -> L4c java.lang.Throwable -> L60
            java.lang.Integer r5 = java.lang.Integer.valueOf(r7)     // Catch: android.database.sqlite.SQLiteException -> L4c java.lang.Throwable -> L60
            r1[r2] = r5     // Catch: android.database.sqlite.SQLiteException -> L4c java.lang.Throwable -> L60
            com.huawei.hwlogsmodel.LogUtil.c(r0, r1)     // Catch: android.database.sqlite.SQLiteException -> L4c java.lang.Throwable -> L60
            if (r4 == 0) goto L79
            goto L76
        L60:
            r7 = move-exception
            goto L7a
        L62:
            r7 = move-exception
            r1 = r7
            r7 = r3
        L65:
            java.lang.Object[] r8 = new java.lang.Object[r8]     // Catch: java.lang.Throwable -> L60
            java.lang.String r5 = "SQLiteException getIdCount:"
            r8[r3] = r5     // Catch: java.lang.Throwable -> L60
            java.lang.String r1 = health.compact.a.LogAnonymous.b(r1)     // Catch: java.lang.Throwable -> L60
            r8[r2] = r1     // Catch: java.lang.Throwable -> L60
            com.huawei.hwlogsmodel.LogUtil.b(r0, r8)     // Catch: java.lang.Throwable -> L60
            if (r4 == 0) goto L79
        L76:
            r4.close()
        L79:
            return r7
        L7a:
            if (r4 == 0) goto L7f
            r4.close()
        L7f:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.euk.b(java.lang.String, java.lang.String):int");
    }

    private void b(String str, String str2, int i) {
        ContentValues asn_ = asn_(str, str2, i);
        LogUtil.c("Suggestion_TrainCountDao", "update：", asn_.toString());
        ett.i().updateStorageData("train_count", 1, asn_, "userId=? and id=?", new String[]{StringUtils.c((Object) str), StringUtils.c((Object) str2)});
    }

    private void c(String str, String str2, int i) {
        ContentValues asn_ = asn_(str, str2, i);
        ett.i().insertStorageData("train_count", 1, asn_);
        LogUtil.c("Suggestion_TrainCountDao", "insertCount values = ", asn_);
    }

    private ContentValues asn_(String str, String str2, int i) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(JsbMapKeyNames.H5_USER_ID, str);
        contentValues.put("id", str2);
        contentValues.put("count", Integer.valueOf(i));
        return contentValues;
    }
}
