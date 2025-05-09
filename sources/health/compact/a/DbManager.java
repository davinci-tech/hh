package health.compact.a;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.text.TextUtils;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hiai.awareness.AwarenessConstants;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdataaccessmodel.db.contentprovider.DbContentProvider;
import com.huawei.operation.utils.Constants;
import java.util.ArrayList;
import java.util.List;
import net.zetetic.database.sqlcipher.SQLiteDatabase;

/* loaded from: classes.dex */
public class DbManager {
    public static final String e = "content://" + BaseApplication.getAppPackage() + ".data.access.provider/";

    private DbManager() {
    }

    public static int c(Context context, String str, String str2, int i, String str3) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("create table IF NOT EXISTS ");
        stringBuffer.append("module_" + str + "_" + str2 + Constants.LEFT_BRACKET_ONLY);
        stringBuffer.append(str3);
        stringBuffer.append(Constants.RIGHT_BRACKET_ONLY);
        try {
            if (i == 1) {
                SQLiteDatabase c = DataBaseHelper.c(str).c();
                if (c == null) {
                    health.compact.a.hwlogsmodel.ReleaseLogUtil.c("R_DbManager", "get database wrong, please check : ", str);
                    return 201000;
                }
                c.execSQL(String.valueOf(stringBuffer));
            } else {
                health.compact.a.hwlogsmodel.ReleaseLogUtil.e("R_DbManager", "exec CreateTable Sql in provider,tableId= " + str2);
                DbContentProvider.c(context).c(String.valueOf(stringBuffer));
            }
            return 0;
        } catch (SQLiteException e2) {
            health.compact.a.hwlogsmodel.ReleaseLogUtil.c("R_DbManager", "createStorageDataTable SQLiteException =", ExceptionUtils.d(e2));
            return 201000;
        } catch (Exception unused) {
            health.compact.a.hwlogsmodel.ReleaseLogUtil.c("R_DbManager", "createStorageDataTable Exception");
            return 201000;
        }
    }

    public static boolean b(String str) {
        return DataBaseHelper.c(str).e();
    }

    public static int d(Context context, String str, String str2, int i) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("DROP TABLE IF EXISTS ");
        stringBuffer.append("module_" + str + "_" + str2);
        try {
            if (i == 1) {
                SQLiteDatabase c = DataBaseHelper.c(str).c();
                if (c == null) {
                    return 0;
                }
                c.execSQL(String.valueOf(stringBuffer));
                return 0;
            }
            DbContentProvider.c(context).c(String.valueOf(stringBuffer));
            return 0;
        } catch (SQLiteException e2) {
            com.huawei.hwlogsmodel.LogUtil.b("DbManager", "deleteStorageDataTable SQLException =", e2.getMessage());
            return 201000;
        } catch (Exception unused) {
            com.huawei.hwlogsmodel.LogUtil.b("DbManager", "deleteStorageDataTable Exception");
            return 201000;
        }
    }

    public static long bGC_(Context context, String str, String str2, int i, ContentValues contentValues) {
        if (context == null || contentValues == null) {
            com.huawei.hwlogsmodel.LogUtil.h("DbManager", "insertStorageData context or values is null");
            return 200004L;
        }
        try {
            if (i == 1) {
                SQLiteDatabase c = DataBaseHelper.c(str).c();
                if (c == null) {
                    return 0L;
                }
                return c.insert("module_" + str + "_" + str2, (String) null, contentValues);
            }
            ContentResolver contentResolver = context.getContentResolver();
            StringBuilder sb = new StringBuilder();
            sb.append(e);
            sb.append("module_");
            sb.append(str);
            sb.append("_");
            sb.append(str2);
            if ((contentResolver.insert(Uri.parse(sb.toString()), contentValues) == null ? -1L : 0L) != -1) {
                return 0L;
            }
            com.huawei.hwlogsmodel.LogUtil.b("DbManager", "insertStorageData failed");
            return 201000L;
        } catch (Exception unused) {
            com.huawei.hwlogsmodel.LogUtil.b("DbManager", "insertStorageData insert() Exception");
            return 201000L;
        }
    }

    public static long bGD_(b bVar, ContentValues contentValues, int i) {
        if (bVar == null || contentValues == null) {
            com.huawei.hwlogsmodel.LogUtil.h("DbManager", "insertStorageDataWithOnConflict params or values is null");
            return 200004L;
        }
        Context b2 = bVar.b();
        if (b2 == null) {
            com.huawei.hwlogsmodel.LogUtil.h("DbManager", "insertStorageDataWithOnConflict context is null");
            return 200004L;
        }
        try {
            String e2 = bVar.e();
            String d = bVar.d();
            if (bVar.c() == 1) {
                SQLiteDatabase c = DataBaseHelper.c(e2).c();
                if (c == null) {
                    return 0L;
                }
                return c.insertWithOnConflict("module_" + e2 + "_" + d, null, contentValues, 5);
            }
            ContentResolver contentResolver = b2.getContentResolver();
            StringBuilder sb = new StringBuilder();
            sb.append(e);
            sb.append("module_");
            sb.append(e2);
            sb.append("_");
            sb.append(d);
            if ((contentResolver.insert(Uri.parse(sb.toString()), contentValues) == null ? -1L : 0L) != -1) {
                return 0L;
            }
            com.huawei.hwlogsmodel.LogUtil.b("DbManager", "insertStorageDataWithOnConflict insert() failed");
            return 201000L;
        } catch (Exception unused) {
            com.huawei.hwlogsmodel.LogUtil.b("DbManager", "insertStorageDataWithOnConflict insert() Exception");
            return 201000L;
        }
    }

    public static int a(Context context, String str, String str2, int i, String str3) {
        if (context == null) {
            com.huawei.hwlogsmodel.LogUtil.h("DbManager", "deleteStorageData context is null");
            return AwarenessConstants.ERROR_UNKNOWN_CODE;
        }
        try {
            com.huawei.hwlogsmodel.LogUtil.c("DbManager", "deleteStorageData enter");
            if (i == 1) {
                SQLiteDatabase c = DataBaseHelper.c(str).c();
                if (c != null) {
                    c.delete("module_" + str + "_" + str2, str3, (String[]) null);
                }
            } else {
                context.getContentResolver().delete(Uri.parse(e + "module_" + str + "_" + str2), str3, null);
            }
            return 0;
        } catch (Exception unused) {
            com.huawei.hwlogsmodel.LogUtil.b("DbManager", "deleteStorageData Exception");
            return 201000;
        }
    }

    public static int e(b bVar, String str, String[] strArr) {
        if (bVar == null) {
            com.huawei.hwlogsmodel.LogUtil.h("DbManager", "deleteStorageData params is null");
            return AwarenessConstants.ERROR_UNKNOWN_CODE;
        }
        Context b2 = bVar.b();
        if (b2 == null) {
            com.huawei.hwlogsmodel.LogUtil.h("DbManager", "deleteStorageData context is null");
            return AwarenessConstants.ERROR_UNKNOWN_CODE;
        }
        try {
            com.huawei.hwlogsmodel.LogUtil.c("DbManager", "deleteStorageData start");
            String e2 = bVar.e();
            String d = bVar.d();
            if (bVar.c() == 1) {
                SQLiteDatabase c = DataBaseHelper.c(e2).c();
                if (c != null) {
                    c.delete("module_" + e2 + "_" + d, str, strArr);
                }
            } else {
                b2.getContentResolver().delete(Uri.parse(e + "module_" + e2 + "_" + d), str, strArr);
            }
            return 0;
        } catch (Exception unused) {
            com.huawei.hwlogsmodel.LogUtil.b("DbManager", "deleteStorageData Exception");
            return 201000;
        }
    }

    public static int bGI_(b bVar, ContentValues contentValues, String str, String[] strArr) {
        if (bVar == null || contentValues == null) {
            com.huawei.hwlogsmodel.LogUtil.h("DbManager", "updateStorageData params or values is null");
            return AwarenessConstants.ERROR_UNKNOWN_CODE;
        }
        Context b2 = bVar.b();
        if (b2 == null) {
            com.huawei.hwlogsmodel.LogUtil.h("DbManager", "updateStorageData context is null");
            return AwarenessConstants.ERROR_UNKNOWN_CODE;
        }
        try {
            com.huawei.hwlogsmodel.LogUtil.c("DbManager", "updateStorageData enter");
            String e2 = bVar.e();
            String d = bVar.d();
            if (bVar.c() == 1) {
                SQLiteDatabase c = DataBaseHelper.c(e2).c();
                if (c == null) {
                    return 0;
                }
                return c.update("module_" + e2 + "_" + d, contentValues, str, strArr);
            }
            b2.getContentResolver().update(Uri.parse(e + "module_" + e2 + "_" + d), contentValues, str, strArr);
            return 0;
        } catch (Exception unused) {
            com.huawei.hwlogsmodel.LogUtil.b("DbManager", "updateStorageData Exception");
            return 201000;
        }
    }

    public static int bGH_(b bVar, ContentValues contentValues, String str) {
        if (bVar == null || contentValues == null) {
            com.huawei.hwlogsmodel.LogUtil.h("DbManager", "updateStorageData params or values is null");
            return AwarenessConstants.ERROR_UNKNOWN_CODE;
        }
        Context b2 = bVar.b();
        if (b2 == null) {
            com.huawei.hwlogsmodel.LogUtil.h("DbManager", "updateStorageData context is null");
            return AwarenessConstants.ERROR_UNKNOWN_CODE;
        }
        try {
            com.huawei.hwlogsmodel.LogUtil.c("DbManager", "updateStorageData start");
            String e2 = bVar.e();
            String d = bVar.d();
            if (bVar.c() == 1) {
                SQLiteDatabase c = DataBaseHelper.c(e2).c();
                if (c != null) {
                    c.update("module_" + e2 + "_" + d, contentValues, str, null);
                }
            } else {
                b2.getContentResolver().update(Uri.parse(e + "module_" + e2 + "_" + d), contentValues, str, null);
            }
            return 0;
        } catch (Exception unused) {
            com.huawei.hwlogsmodel.LogUtil.b("DbManager", "updateStorageData Exception");
            return 201000;
        }
    }

    public static Cursor bGE_(Context context, String str, String str2, int i, String str3) {
        Cursor cursor = null;
        if (context == null) {
            com.huawei.hwlogsmodel.LogUtil.h("DbManager", "queryStorageData context is null");
            return null;
        }
        try {
            if (i == 1) {
                SQLiteDatabase c = DataBaseHelper.c(str).c();
                if (c != null) {
                    cursor = c.query("module_" + str + "_" + str2, null, str3, null, null, null, null);
                }
            } else {
                cursor = context.getContentResolver().query(Uri.parse(e + "module_" + str + "_" + str2), null, str3, null, null);
            }
            return cursor;
        } catch (Exception unused) {
            com.huawei.hwlogsmodel.LogUtil.b("DbManager", "queryStorageData Exception");
            return cursor;
        }
    }

    public static Cursor bGF_(b bVar, String str, String str2) {
        if (bVar == null) {
            com.huawei.hwlogsmodel.LogUtil.h("DbManager", "queryStorageDataToOrder params is null");
            return null;
        }
        Context b2 = bVar.b();
        String e2 = bVar.e();
        String d = bVar.d();
        if (b2 == null || TextUtils.isEmpty(e2) || TextUtils.isEmpty(d)) {
            com.huawei.hwlogsmodel.LogUtil.h("DbManager", "queryStorageDataToOrder param is invalid");
            return null;
        }
        if (bVar.c() == 1) {
            SQLiteDatabase c = DataBaseHelper.c(e2).c();
            if (c == null) {
                return null;
            }
            try {
                return c.query("module_" + e2 + "_" + d, null, str, null, null, null, str2);
            } catch (SQLException unused) {
                com.huawei.hwlogsmodel.LogUtil.b("DbManager", "queryStorageDataToOrder sqlcipher SQLException");
                return null;
            }
        }
        try {
            return b2.getContentResolver().query(Uri.parse(e + "module_" + e2 + "_" + d), null, str, null, str2);
        } catch (SQLException unused2) {
            com.huawei.hwlogsmodel.LogUtil.b("DbManager", "queryStorageDataToOrder android SQLException");
            return null;
        }
    }

    public static Cursor bGG_(String str, int i, String str2, String[] strArr) {
        SQLiteDatabase c;
        if (i == 1 && (c = DataBaseHelper.c(str).c()) != null) {
            try {
                return c.rawQuery(str2, strArr);
            } catch (SQLiteException e2) {
                health.compact.a.hwlogsmodel.ReleaseLogUtil.c("R_DbManager", "rawQueryStorageData SQLiteException ", ExceptionUtils.d(e2));
            } catch (Exception e3) {
                health.compact.a.hwlogsmodel.ReleaseLogUtil.c("R_DbManager", "rawQueryStorageData Exception ", ExceptionUtils.d(e3));
            }
        }
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x0039, code lost:
    
        if (r3.getColumnIndex(r8) != (-1)) goto L23;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean b(java.lang.String r6, java.lang.String r7, java.lang.String r8) {
        /*
            java.lang.String r0 = "checkColumnExist"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            java.lang.String r1 = "DbManager"
            com.huawei.hwlogsmodel.LogUtil.a(r1, r0)
            health.compact.a.DataBaseHelper r6 = health.compact.a.DataBaseHelper.c(r6)
            net.zetetic.database.sqlcipher.SQLiteDatabase r6 = r6.c()
            r0 = 1
            r2 = 0
            r3 = 0
            if (r6 == 0) goto L32
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L30 java.lang.Exception -> L3c
            java.lang.String r5 = "SELECT * FROM "
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L30 java.lang.Exception -> L3c
            r4.append(r7)     // Catch: java.lang.Throwable -> L30 java.lang.Exception -> L3c
            java.lang.String r7 = " LIMIT 0"
            r4.append(r7)     // Catch: java.lang.Throwable -> L30 java.lang.Exception -> L3c
            java.lang.String r7 = r4.toString()     // Catch: java.lang.Throwable -> L30 java.lang.Exception -> L3c
            android.database.Cursor r3 = r6.rawQuery(r7, r3)     // Catch: java.lang.Throwable -> L30 java.lang.Exception -> L3c
            goto L32
        L30:
            r6 = move-exception
            goto L51
        L32:
            if (r3 == 0) goto L5d
            int r6 = r3.getColumnIndex(r8)     // Catch: java.lang.Throwable -> L30 java.lang.Exception -> L3c
            r7 = -1
            if (r6 == r7) goto L5d
            goto L5e
        L3c:
            java.lang.Object[] r6 = new java.lang.Object[r0]     // Catch: java.lang.Throwable -> L30
            java.lang.String r7 = "checkColumnExists exception"
            r6[r2] = r7     // Catch: java.lang.Throwable -> L30
            com.huawei.hwlogsmodel.LogUtil.b(r1, r6)     // Catch: java.lang.Throwable -> L30
            if (r3 == 0) goto L6a
            boolean r6 = r3.isClosed()
            if (r6 != 0) goto L6a
            r3.close()
            goto L6a
        L51:
            if (r3 == 0) goto L5c
            boolean r7 = r3.isClosed()
            if (r7 != 0) goto L5c
            r3.close()
        L5c:
            throw r6
        L5d:
            r0 = r2
        L5e:
            if (r3 == 0) goto L69
            boolean r6 = r3.isClosed()
            if (r6 != 0) goto L69
            r3.close()
        L69:
            r2 = r0
        L6a:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: health.compact.a.DbManager.b(java.lang.String, java.lang.String, java.lang.String):boolean");
    }

    public static String c(String str, String str2) {
        return "module_" + str + "_" + str2;
    }

    public static void e(String str) {
        SQLiteDatabase c = DataBaseHelper.c(str).c();
        if (c != null) {
            c.beginTransaction();
        }
    }

    public static void c(String str) {
        if (DataBaseHelper.c(str).c() != null) {
            DataBaseHelper.c(str).c().setTransactionSuccessful();
            DataBaseHelper.c(str).c().endTransaction();
        }
    }

    public static void e(String str, String str2) {
        try {
            if (DataBaseHelper.c(str).c() != null) {
                DataBaseHelper.c(str).c().execSQL(str2);
            }
        } catch (SQLException unused) {
            com.huawei.hwlogsmodel.LogUtil.b("DbManager", "execSql SQLException");
        } catch (Exception unused2) {
            com.huawei.hwlogsmodel.LogUtil.b("DbManager", "execSql Exception");
        }
    }

    public static List<String> a(String str) {
        Cursor bGG_ = bGG_(str, 1, "select name from sqlite_master where type='table' order by name", null);
        if (bGG_ == null) {
            com.huawei.hwlogsmodel.LogUtil.h("DbManager", "getAllTableNames, cursor == null");
            return null;
        }
        ArrayList arrayList = new ArrayList(10);
        while (bGG_.moveToNext()) {
            try {
                try {
                    String string = bGG_.getString(0);
                    if (string != null && !string.isEmpty()) {
                        arrayList.add(string);
                    }
                } catch (SQLiteException unused) {
                    com.huawei.hwlogsmodel.LogUtil.b("DbManager", "getAllTableNames, SQLiteException");
                }
            } finally {
                bGG_.close();
            }
        }
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:5:0x004f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String d(java.lang.String r4) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "propertyDataKey='"
            r0.<init>(r1)
            r0.append(r4)
            java.lang.String r4 = "'"
            r0.append(r4)
            java.lang.String r4 = r0.toString()
            android.content.Context r0 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()
            r1 = 1010(0x3f2, float:1.415E-42)
            java.lang.String r1 = java.lang.String.valueOf(r1)
            java.lang.String r2 = "commonPropertyTable"
            r3 = 2
            android.database.Cursor r4 = bGE_(r0, r1, r2, r3, r4)
            if (r4 == 0) goto L4b
            int r0 = r4.getCount()     // Catch: java.lang.Throwable -> L3f
            if (r0 <= 0) goto L4b
            boolean r0 = r4.moveToFirst()     // Catch: java.lang.Throwable -> L3f
            if (r0 == 0) goto L4b
            java.lang.String r0 = "propertyDataValue"
            int r0 = r4.getColumnIndex(r0)     // Catch: java.lang.Throwable -> L3f
            java.lang.String r0 = r4.getString(r0)     // Catch: java.lang.Throwable -> L3f
            goto L4d
        L3f:
            r0 = move-exception
            if (r4 == 0) goto L4a
            r4.close()     // Catch: java.lang.Throwable -> L46
            goto L4a
        L46:
            r4 = move-exception
            r0.addSuppressed(r4)
        L4a:
            throw r0
        L4b:
            java.lang.String r0 = ""
        L4d:
            if (r4 == 0) goto L52
            r4.close()
        L52:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: health.compact.a.DbManager.d(java.lang.String):java.lang.String");
    }

    public static int d(Context context, String str, String str2, int i, String str3) {
        Cursor bGE_ = bGE_(context, str, str2, i, null);
        if (bGE_ == null) {
            com.huawei.hwlogsmodel.LogUtil.h("DbManager", "alterStorageDataTable table not exist !");
            return AwarenessConstants.ERROR_PARAMETER_CODE;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("ALTER TABLE ");
        stringBuffer.append("module_" + str + "_" + str2 + " ");
        stringBuffer.append(str3);
        com.huawei.hwlogsmodel.LogUtil.c("DbManager", "alterStorageDataTable sql: ", str3);
        try {
            if (i == 1) {
                if (DataBaseHelper.c(str).c() != null) {
                    DataBaseHelper.c(str).c().execSQL(String.valueOf(stringBuffer));
                    return 0;
                }
            }
            DbContentProvider.c(context).c(String.valueOf(stringBuffer));
            return 0;
        } catch (SQLiteException e2) {
            com.huawei.hwlogsmodel.LogUtil.b("DbManager", "alterStorageDataTable SQLiteException =", e2.getMessage());
            return 201000;
        } catch (Exception unused) {
            com.huawei.hwlogsmodel.LogUtil.b("DbManager", "alterStorageDataTable Exception");
            return 201000;
        } finally {
            bGE_.close();
        }
    }

    public static class b {

        /* renamed from: a, reason: collision with root package name */
        private String f13111a;
        private String b;
        private int c;
        private Context d;

        Context b() {
            return this.d;
        }

        public void b(Context context) {
            this.d = context;
        }

        String e() {
            return this.f13111a;
        }

        public void e(String str) {
            this.f13111a = str;
        }

        String d() {
            return this.b;
        }

        public void c(String str) {
            this.b = str;
        }

        int c() {
            return this.c;
        }

        public void a(int i) {
            this.c = i;
        }

        public String toString() {
            return "DataBaseParams{context=" + this.d + ", moduleId='" + this.f13111a + "', tableId='" + this.b + "', storageType=" + this.c + '}';
        }
    }
}
