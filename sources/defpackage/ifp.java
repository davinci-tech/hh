package defpackage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hihealth.util.ReleaseLogUtil;
import com.huawei.hihealthservice.db.helper.HiHealthDBHelper;
import com.huawei.hihealthservice.db.table.DBPointCommon;
import com.huawei.hwlogsmodel.common.LogConfig;
import com.tencent.connect.common.Constants;
import health.compact.a.CommonUtil;
import health.compact.a.HiDateUtil;
import health.compact.a.LogZipUtil;
import health.compact.a.util.LogUtil;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import net.zetetic.database.sqlcipher.SQLiteDatabase;

/* loaded from: classes4.dex */
public class ifp {
    private static final List<String> e = Arrays.asList("create_time", "modified_time", "start_time", "end_time", "sync_type_version", "modify_time");
    private static volatile AtomicBoolean d = new AtomicBoolean(false);

    static class b {
        private static final ifp b = new ifp();
    }

    public static ifp c() {
        return b.b;
    }

    public boolean c(Context context, int i, String str) {
        try {
            try {
                LogUtil.d("HiH_HiExportManager", "Start to exportEncryptDB");
            } catch (Exception e2) {
                ReleaseLogUtil.c("HiH_HiExportManager", "Failed to export DB cause:", e2.getMessage());
            }
            if (!d.compareAndSet(false, true)) {
                LogUtil.d("HiH_HiExportManager", "Failed to export cause: the db is exporting...");
                return false;
            }
            long currentTimeMillis = System.currentTimeMillis();
            e(context, i, str, true);
            e(context, i, str, false);
            LogUtil.d("HiH_HiExportManager", "Success to exportEncryptDB totalTime=", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
            return true;
        } finally {
            d.compareAndSet(true, false);
        }
    }

    private void e(Context context, int i, String str, boolean z) throws IOException {
        SQLiteDatabase writableDatabase;
        SQLiteDatabase writableDatabase2;
        if (!z || CommonUtil.av()) {
            long currentTimeMillis = System.currentTimeMillis();
            if (z) {
                writableDatabase = HiHealthDBHelper.e("hihealth_sensitive.db").getWritableDatabase();
                writableDatabase2 = HiHealthDBHelper.e("hihealth_sensitive_export.db").getWritableDatabase();
            } else {
                writableDatabase = HiHealthDBHelper.e("hihealth_003.db").getWritableDatabase();
                writableDatabase2 = HiHealthDBHelper.e("hihealth_003_export.db").getWritableDatabase();
            }
            List<String> c = c(writableDatabase, writableDatabase2);
            LogUtil.d("HiH_HiExportManager", "Success to createTable");
            b(writableDatabase, writableDatabase2);
            LogUtil.d("HiH_HiExportManager", "Success to createTableIndex");
            for (String str2 : c) {
                long currentTimeMillis2 = System.currentTimeMillis();
                a(writableDatabase, writableDatabase2, str2, i);
                LogUtil.b("HiH_HiExportManager", "Success to migrate db, tableName=", str2, ", totalTime=", Long.valueOf(System.currentTimeMillis() - currentTimeMillis2));
            }
            File c2 = c(context, str, z);
            e(c2);
            if (c2 != null && c2.exists()) {
                c2.delete();
            }
            LogUtil.d("HiH_HiExportManager", "Success to commonExportDb totalTime=" + (System.currentTimeMillis() - currentTimeMillis));
        }
    }

    private void e(File file) throws IOException {
        if (file == null) {
            return;
        }
        File file2 = new File(file.getCanonicalPath().replaceAll(".db", ".zip"));
        if (file2.exists()) {
            file2.delete();
        }
        file2.createNewFile();
        LogZipUtil.e(file, file2);
        if (file.exists()) {
            file.delete();
        }
    }

    public boolean e() {
        try {
            File f = LogConfig.f();
            if (f == null) {
                LogUtil.e("HiH_HiExportManager", "Failed to removeTempDbFile, cause: file is null");
                return false;
            }
            boolean z = c(new File(f, "hihealth_003_export.zip")) && c(new File(f, "hihealth_sensitive_export.zip"));
            LogUtil.d("HiH_HiExportManager", "removeTempDbFile isSuccess=", Boolean.valueOf(z));
            return z;
        } catch (Exception e2) {
            ReleaseLogUtil.c("HiH_HiExportManager", "Failed to removeTempDbFile cause:", e2.getMessage());
            return false;
        }
    }

    private boolean c(File file) {
        if (file == null || !file.exists()) {
            return true;
        }
        if (System.currentTimeMillis() - file.lastModified() < 86400000) {
            LogUtil.d("HiH_HiExportManager", "No need to deleteFile lastModified=" + file.lastModified());
            return true;
        }
        return file.delete();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void a(SQLiteDatabase sQLiteDatabase, SQLiteDatabase sQLiteDatabase2, String str, int i) {
        char c;
        long e2 = jdl.e(System.currentTimeMillis(), i);
        str.hashCode();
        switch (str.hashCode()) {
            case -1887652205:
                if (str.equals("hihealth_stat_day")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1380171680:
                if (str.equals("sample_point_health")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -1139263654:
                if (str.equals("sample_session_health")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -774613759:
                if (str.equals("sample_session")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 452576955:
                if (str.equals("sample_point")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1169624093:
                if (str.equals("sample_session_core")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 1387389555:
                if (str.equals("sample_point_health_stress")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 1701245622:
                if (str.equals("sample_sequence")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                c(sQLiteDatabase, sQLiteDatabase2, str, jdl.d(System.currentTimeMillis(), -400), 5000);
                break;
            case 1:
            case 4:
            case 6:
                a(sQLiteDatabase, sQLiteDatabase2, str, e2, 10000);
                break;
            case 2:
            case 3:
            case 5:
            case 7:
                c(sQLiteDatabase, sQLiteDatabase2, str, e2, 5000);
                break;
            default:
                d(sQLiteDatabase, sQLiteDatabase2, str, 5000);
                break;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00d7  */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1, types: [net.zetetic.database.sqlcipher.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r2v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.io.File c(android.content.Context r8, java.lang.String r9, boolean r10) {
        /*
            r7 = this;
            java.lang.String r0 = "HiH_HiExportManager"
            if (r9 == 0) goto La
            boolean r1 = r9.isEmpty()
            if (r1 == 0) goto Lc
        La:
            java.lang.String r9 = ""
        Lc:
            if (r10 == 0) goto L11
            java.lang.String r10 = "hihealth_sensitive_export.db"
            goto L13
        L11:
            java.lang.String r10 = "hihealth_003_export.db"
        L13:
            r1 = 1
            r2 = 0
            r3 = 0
            java.io.File r8 = r8.getDatabasePath(r10)     // Catch: java.lang.Throwable -> Lc0 java.lang.Exception -> Lc2
            if (r8 == 0) goto Lb6
            boolean r10 = r8.exists()     // Catch: java.lang.Throwable -> Lc0 java.lang.Exception -> Lc2
            if (r10 == 0) goto Lb6
            boolean r10 = r8.isDirectory()     // Catch: java.lang.Throwable -> Lc0 java.lang.Exception -> Lc2
            if (r10 == 0) goto L2a
            goto Lb6
        L2a:
            java.io.File r10 = com.huawei.hwlogsmodel.common.LogConfig.f()     // Catch: java.lang.Throwable -> Lc0 java.lang.Exception -> Lc2
            if (r10 != 0) goto L3a
            java.lang.Object[] r8 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> Lc0 java.lang.Exception -> Lc2
            java.lang.String r9 = "exportDatabase file is null"
            r8[r3] = r9     // Catch: java.lang.Throwable -> Lc0 java.lang.Exception -> Lc2
            health.compact.a.util.LogUtil.e(r0, r8)     // Catch: java.lang.Throwable -> Lc0 java.lang.Exception -> Lc2
            return r2
        L3a:
            java.io.File r4 = new java.io.File     // Catch: java.lang.Throwable -> Lc0 java.lang.Exception -> Lc2
            java.lang.String r5 = r8.getName()     // Catch: java.lang.Throwable -> Lc0 java.lang.Exception -> Lc2
            r4.<init>(r10, r5)     // Catch: java.lang.Throwable -> Lc0 java.lang.Exception -> Lc2
            boolean r10 = r4.exists()     // Catch: java.lang.Throwable -> Lc0 java.lang.Exception -> Lc2
            if (r10 == 0) goto L5d
            boolean r10 = r4.delete()     // Catch: java.lang.Throwable -> Lc0 java.lang.Exception -> Lc2
            r5 = 2
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch: java.lang.Throwable -> Lc0 java.lang.Exception -> Lc2
            java.lang.String r6 = "exportDatabase targetFile exists, isDelete = "
            r5[r3] = r6     // Catch: java.lang.Throwable -> Lc0 java.lang.Exception -> Lc2
            java.lang.Boolean r10 = java.lang.Boolean.valueOf(r10)     // Catch: java.lang.Throwable -> Lc0 java.lang.Exception -> Lc2
            r5[r1] = r10     // Catch: java.lang.Throwable -> Lc0 java.lang.Exception -> Lc2
            health.compact.a.util.LogUtil.d(r0, r5)     // Catch: java.lang.Throwable -> Lc0 java.lang.Exception -> Lc2
        L5d:
            android.content.Context r10 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()     // Catch: java.lang.Throwable -> Lc0 java.lang.Exception -> Lc2
            defpackage.jev.e(r10)     // Catch: java.lang.Throwable -> Lc0 java.lang.Exception -> Lc2
            r10 = 13
            byte[] r10 = health.compact.a.KeyManager.a(r10)     // Catch: java.lang.Throwable -> Lc0 java.lang.Exception -> Lc2
            java.lang.String r5 = new java.lang.String     // Catch: java.lang.Throwable -> Lc0 java.lang.Exception -> Lc2
            java.nio.charset.Charset r6 = java.nio.charset.StandardCharsets.UTF_8     // Catch: java.lang.Throwable -> Lc0 java.lang.Exception -> Lc2
            r5.<init>(r10, r6)     // Catch: java.lang.Throwable -> Lc0 java.lang.Exception -> Lc2
            net.zetetic.database.sqlcipher.SQLiteDatabase r8 = net.zetetic.database.sqlcipher.SQLiteDatabase.openOrCreateDatabase(r8, r5, r2, r2)     // Catch: java.lang.Throwable -> Lc0 java.lang.Exception -> Lc2
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> Lc3 java.lang.Throwable -> Ld2
            java.lang.String r5 = "ATTACH DATABASE '"
            r10.<init>(r5)     // Catch: java.lang.Exception -> Lc3 java.lang.Throwable -> Ld2
            java.lang.String r5 = r4.getCanonicalPath()     // Catch: java.lang.Exception -> Lc3 java.lang.Throwable -> Ld2
            r10.append(r5)     // Catch: java.lang.Exception -> Lc3 java.lang.Throwable -> Ld2
            java.lang.String r5 = "' AS plaintext KEY '"
            r10.append(r5)     // Catch: java.lang.Exception -> Lc3 java.lang.Throwable -> Ld2
            r10.append(r9)     // Catch: java.lang.Exception -> Lc3 java.lang.Throwable -> Ld2
            java.lang.String r9 = "';"
            r10.append(r9)     // Catch: java.lang.Exception -> Lc3 java.lang.Throwable -> Ld2
            java.lang.String r9 = r10.toString()     // Catch: java.lang.Exception -> Lc3 java.lang.Throwable -> Ld2
            java.lang.Object[] r10 = new java.lang.Object[r3]     // Catch: java.lang.Exception -> Lc3 java.lang.Throwable -> Ld2
            r8.rawExecSQL(r9, r10)     // Catch: java.lang.Exception -> Lc3 java.lang.Throwable -> Ld2
            java.lang.Object[] r9 = new java.lang.Object[r3]     // Catch: java.lang.Exception -> Lc3 java.lang.Throwable -> Ld2
            java.lang.String r10 = "SELECT sqlcipher_export('plaintext');"
            r8.rawExecSQL(r10, r9)     // Catch: java.lang.Exception -> Lc3 java.lang.Throwable -> Ld2
            java.lang.Object[] r9 = new java.lang.Object[r3]     // Catch: java.lang.Exception -> Lc3 java.lang.Throwable -> Ld2
            java.lang.String r10 = "DETACH DATABASE plaintext;"
            r8.rawExecSQL(r10, r9)     // Catch: java.lang.Exception -> Lc3 java.lang.Throwable -> Ld2
            java.lang.Object[] r9 = new java.lang.Object[r1]     // Catch: java.lang.Exception -> Lc3 java.lang.Throwable -> Ld2
            java.lang.String r10 = "success to exportDatabase"
            r9[r3] = r10     // Catch: java.lang.Exception -> Lc3 java.lang.Throwable -> Ld2
            health.compact.a.util.LogUtil.d(r0, r9)     // Catch: java.lang.Exception -> Lc3 java.lang.Throwable -> Ld2
            if (r8 == 0) goto Lb5
            r8.close()
        Lb5:
            return r4
        Lb6:
            java.lang.Object[] r8 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> Lc0 java.lang.Exception -> Lc2
            java.lang.String r9 = "exportDatabase databaseFile not exists"
            r8[r3] = r9     // Catch: java.lang.Throwable -> Lc0 java.lang.Exception -> Lc2
            health.compact.a.util.LogUtil.e(r0, r8)     // Catch: java.lang.Throwable -> Lc0 java.lang.Exception -> Lc2
            return r2
        Lc0:
            r8 = move-exception
            goto Ld5
        Lc2:
            r8 = r2
        Lc3:
            java.lang.Object[] r9 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> Ld2
            java.lang.String r10 = "exportDatabase exception"
            r9[r3] = r10     // Catch: java.lang.Throwable -> Ld2
            com.huawei.hihealth.util.ReleaseLogUtil.c(r0, r9)     // Catch: java.lang.Throwable -> Ld2
            if (r8 == 0) goto Ld1
            r8.close()
        Ld1:
            return r2
        Ld2:
            r9 = move-exception
            r2 = r8
            r8 = r9
        Ld5:
            if (r2 == 0) goto Lda
            r2.close()
        Lda:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ifp.c(android.content.Context, java.lang.String, boolean):java.io.File");
    }

    private List<Integer> b(SQLiteDatabase sQLiteDatabase, String str) {
        Cursor rawQuery = sQLiteDatabase.rawQuery("SELECT distinct(type_id) as type_id FROM " + str, (String[]) null);
        ArrayList arrayList = new ArrayList(33);
        while (rawQuery.moveToNext()) {
            try {
                try {
                    arrayList.add(Integer.valueOf(rawQuery.getInt(rawQuery.getColumnIndex("type_id"))));
                } catch (Exception e2) {
                    ReleaseLogUtil.c("HiH_HiExportManager", "Error to getAllTypeId: ", e2.getMessage());
                }
            } finally {
                rawQuery.close();
            }
        }
        return arrayList;
    }

    private String a(String str, int i, int i2, int i3, long j) {
        StringBuffer stringBuffer = new StringBuffer("SELECT _id, start_time, end_time, value, client_id, merged, sync_status, timeZone, modified_time FROM ");
        stringBuffer.append(str).append(" where type_id = ").append(i).append(" and start_time >= ").append(j).append(" order by start_time asc limit ").append(i2).append(" , ").append(i3).append(";");
        return stringBuffer.toString();
    }

    private String d(String str, int i, int i2, long j) {
        StringBuffer stringBuffer = new StringBuffer();
        if ("hihealth_stat_day".equals(str)) {
            stringBuffer.append("SELECT * FROM ").append(str).append(" where date >= ").append(HiDateUtil.c(j)).append(" order by date asc limit ").append(i).append(" , ").append(i2).append(";");
        } else if ("sample_sequence".equals(str)) {
            stringBuffer.append("SELECT start_time,end_time,type_id,sub_type_id,meta_data,simple_data,client_id,merged,sync_status,timeZone,modified_time,reserve FROM ").append(str).append(" where start_time >= ").append(j).append(" order by start_time asc limit ").append(i).append(" , ").append(i2).append(";");
        } else {
            stringBuffer.append("SELECT * FROM ").append(str).append(" where start_time >= ").append(j).append(" order by start_time asc limit ").append(i).append(" , ").append(i2).append(";");
        }
        return stringBuffer.toString();
    }

    private List<ContentValues> e(SQLiteDatabase sQLiteDatabase, String str, int i, int i2, int i3) {
        return bwY_(sQLiteDatabase.rawQuery("SELECT * FROM " + str + " WHERE _id >" + i3 + " LIMIT " + i + " , " + i2 + ";", (String[]) null), str, i2);
    }

    private List<ContentValues> bwY_(Cursor cursor, String str, int i) {
        if (cursor == null) {
            return new ArrayList(0);
        }
        try {
            ArrayList arrayList = new ArrayList(i);
            while (cursor.moveToNext()) {
                ContentValues contentValues = new ContentValues();
                int columnCount = cursor.getColumnCount();
                for (int i2 = 0; i2 < columnCount; i2++) {
                    String columnName = cursor.getColumnName(i2);
                    int type = cursor.getType(i2);
                    if (type == 0) {
                        contentValues.putNull(columnName);
                    } else if (type != 1) {
                        if (type == 2) {
                            contentValues.put(columnName, Float.valueOf(cursor.getFloat(i2)));
                        } else if (type != 3) {
                            if (type == 4) {
                                contentValues.put(columnName, "");
                            } else {
                                LogUtil.e("HiH_HiExportManager", "Unknown field type: " + type);
                            }
                        } else if ("hihealth_account".equals(str) && Constants.PARAM_ACCESS_TOKEN.equals(columnName)) {
                            contentValues.put(columnName, "");
                        } else {
                            contentValues.put(columnName, cursor.getString(i2));
                        }
                    } else if (e.contains(columnName)) {
                        contentValues.put(columnName, Long.valueOf(cursor.getLong(i2)));
                    } else {
                        contentValues.put(columnName, Integer.valueOf(cursor.getInt(i2)));
                    }
                }
                if ("sample_sequence".equals(str)) {
                    contentValues.put("data", "");
                }
                arrayList.add(contentValues);
            }
            return arrayList;
        } catch (Exception e2) {
            ReleaseLogUtil.c("HiH_HiExportManager", "Error to queryByPage: ", e2.getMessage());
            return new ArrayList(0);
        } finally {
            cursor.close();
        }
    }

    private void a(SQLiteDatabase sQLiteDatabase, SQLiteDatabase sQLiteDatabase2, String str, long j, int i) {
        List<Integer> b2 = b(sQLiteDatabase, str);
        if (b2.isEmpty()) {
            return;
        }
        for (final Integer num : b2) {
            boolean z = true;
            int i2 = 0;
            do {
                List<ContentValues> bwY_ = bwY_(sQLiteDatabase.rawQuery(a(str, num.intValue(), i2, i, j), (String[]) null), str, i);
                bwY_.forEach(new Consumer() { // from class: ifv
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        ifp.bwZ_(num, (ContentValues) obj);
                    }
                });
                e(sQLiteDatabase2, str, bwY_);
                i2 += i;
                if (bwY_.size() < i) {
                    z = false;
                }
            } while (z);
        }
    }

    static /* synthetic */ void bwZ_(Integer num, ContentValues contentValues) {
        contentValues.put("type_id", num);
        contentValues.put(DBPointCommon.COLUMN_UNIT_ID, (Integer) 0);
    }

    private void c(SQLiteDatabase sQLiteDatabase, SQLiteDatabase sQLiteDatabase2, String str, long j, int i) {
        boolean z = true;
        int i2 = 0;
        do {
            List<ContentValues> bwY_ = bwY_(sQLiteDatabase.rawQuery(d(str, i2, i, j), (String[]) null), str, i);
            e(sQLiteDatabase2, str, bwY_);
            i2 += i;
            if (bwY_.size() < i) {
                z = false;
            }
        } while (z);
    }

    private void d(SQLiteDatabase sQLiteDatabase, SQLiteDatabase sQLiteDatabase2, String str, int i) {
        boolean z = true;
        int i2 = 0;
        int i3 = 0;
        do {
            List<ContentValues> e2 = e(sQLiteDatabase, str, i2, i, i3);
            e(sQLiteDatabase2, str, e2);
            i2 += i;
            if (e2.size() < i) {
                z = false;
            }
            if (e2.size() == i) {
                i3 = e2.get(i - 1).getAsInteger("_id").intValue();
            }
        } while (z);
    }

    private void e(SQLiteDatabase sQLiteDatabase, String str, List<ContentValues> list) {
        try {
            try {
                sQLiteDatabase.beginTransaction();
                Iterator<ContentValues> it = list.iterator();
                while (it.hasNext()) {
                    sQLiteDatabase.insert(str, (String) null, it.next());
                }
                sQLiteDatabase.setTransactionSuccessful();
            } catch (Exception e2) {
                ReleaseLogUtil.c("HiH_HiExportManager", "Failed to insertBatch,cause:", ExceptionUtils.d(e2));
                new RuntimeException("Error insertBatch");
            }
        } finally {
            sQLiteDatabase.endTransaction();
        }
    }

    private static List<String> c(SQLiteDatabase sQLiteDatabase, SQLiteDatabase sQLiteDatabase2) {
        Cursor rawQuery = sQLiteDatabase.rawQuery("SELECT name, sql FROM sqlite_master WHERE type='table';", (String[]) null);
        ArrayList arrayList = new ArrayList(33);
        while (rawQuery.moveToNext()) {
            try {
                try {
                    String string = rawQuery.getString(rawQuery.getColumnIndex("name"));
                    String string2 = rawQuery.getString(rawQuery.getColumnIndex("sql"));
                    if (!string.equals("android_metadata")) {
                        arrayList.add(string);
                        sQLiteDatabase2.execSQL("DROP TABLE IF EXISTS " + string + ";");
                        sQLiteDatabase2.execSQL(string2);
                    }
                } catch (SQLiteException e2) {
                    ReleaseLogUtil.c("HiH_HiExportManager", "Error to createTable: ", ExceptionUtils.d(e2));
                    throw new RuntimeException("Error to createTable");
                }
            } finally {
                rawQuery.close();
            }
        }
        return arrayList;
    }

    private static void b(SQLiteDatabase sQLiteDatabase, SQLiteDatabase sQLiteDatabase2) {
        Cursor rawQuery = sQLiteDatabase.rawQuery("SELECT * FROM sqlite_master where type='index';", (String[]) null);
        while (rawQuery.moveToNext()) {
            try {
                try {
                    sQLiteDatabase2.execSQL(rawQuery.getString(rawQuery.getColumnIndex("sql")));
                } catch (Exception e2) {
                    ReleaseLogUtil.c("HiH_HiExportManager", "Error to createTableIndex: ", ExceptionUtils.d(e2));
                    throw new RuntimeException("Error to createTableIndex");
                }
            } finally {
                rawQuery.close();
            }
        }
    }
}
