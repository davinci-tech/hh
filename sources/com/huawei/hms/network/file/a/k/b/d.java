package com.huawei.hms.network.file.a.k.b;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import com.huawei.health.knit.ui.KnitFragment;
import com.huawei.hms.framework.common.StringUtils;
import com.huawei.hms.network.file.core.util.FLogger;
import java.io.File;
import java.util.Arrays;

/* loaded from: classes4.dex */
class d extends SQLiteOpenHelper {

    /* renamed from: a, reason: collision with root package name */
    private h f5611a;
    private h b;

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        FLogger.i("DBHelper", "onUpgrade oldVersion:" + i + "," + i2, new Object[0]);
        try {
            try {
                sQLiteDatabase.beginTransaction();
                if (i <= 1) {
                    sQLiteDatabase.execSQL(this.f5611a.a());
                    sQLiteDatabase.execSQL(this.b.a());
                    if (i2 == 2) {
                        sQLiteDatabase.execSQL("insert into download_request (requestId,name,fileSize,status,fileSha256,startPosition,manager) select taskId,name,fileSize,status,fileSha256,startPostition,manager from download_task");
                        sQLiteDatabase.execSQL("insert into download_taskinfo (requestId,taskId,start,end,finished,filePath,manager) select taskId,sliceId,start,end,finished,'', manager from download_slice");
                        a(sQLiteDatabase);
                        sQLiteDatabase.execSQL("drop table download_task");
                        sQLiteDatabase.execSQL("drop table download_slice");
                    }
                }
                sQLiteDatabase.setTransactionSuccessful();
                FLogger.i("DBHelper", "onUpgrade success", new Object[0]);
            } catch (Exception e) {
                FLogger.e("DBHelper", "onUpgrade error", e);
                FLogger.v("DBHelper", "onUpgrade error:" + e.getMessage() + ",for:" + e.getMessage());
            }
        } finally {
            f.a(sQLiteDatabase, "when endTransaction has error!,this time is onUpgrade");
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        FLogger.i("DBHelper", "onCreate", new Object[0]);
        a(sQLiteDatabase, this.f5611a.a(), this.b.a());
    }

    private void a(SQLiteDatabase sQLiteDatabase, String... strArr) {
        FLogger.v("DBHelper", Arrays.toString(strArr));
        try {
            try {
                sQLiteDatabase.beginTransaction();
                for (String str : strArr) {
                    sQLiteDatabase.execSQL(str);
                }
                sQLiteDatabase.setTransactionSuccessful();
            } catch (RuntimeException unused) {
                FLogger.w("DBHelper", "excuteSafely is runtimeException", new Object[0]);
            }
        } finally {
            f.a(sQLiteDatabase, "when endTransaction has error!,this time is excuteSafely");
        }
    }

    private void a(SQLiteDatabase sQLiteDatabase) {
        int i;
        Cursor cursor = null;
        try {
            try {
                cursor = sQLiteDatabase.query("download_task", null, null, null, null, null, null, null);
                i = 0;
                while (!cursor.isClosed() && cursor.moveToNext()) {
                    try {
                        String string = cursor.getString(cursor.getColumnIndex("filePath"));
                        i++;
                        if (!TextUtils.isEmpty(string)) {
                            long j = cursor.getLong(cursor.getColumnIndex("taskId"));
                            File file = new File(string + ".tmp");
                            if (new File(string + ".tmp").exists()) {
                                boolean renameTo = file.renameTo(new File(string + ".tmp" + j));
                                StringBuilder sb = new StringBuilder();
                                sb.append("rename result:");
                                sb.append(renameTo);
                                FLogger.d("DBHelper", sb.toString(), new Object[0]);
                            }
                            ContentValues contentValues = new ContentValues();
                            c.a(contentValues, "filePath", StringUtils.str2Byte(string));
                            FLogger.d("DBHelper", "update result:" + sQLiteDatabase.update("download_request", contentValues, "requestId=?", new String[]{String.valueOf(j)}), new Object[0]);
                        }
                    } catch (Exception e) {
                        e = e;
                        FLogger.logException(e);
                        f.a(cursor);
                        FLogger.d("DBHelper", "renameTmpFilesAndUpdateFilePath num:" + i, new Object[0]);
                    }
                }
            } catch (Throwable th) {
                f.a((Cursor) null);
                throw th;
            }
        } catch (Exception e2) {
            e = e2;
            i = 0;
        }
        f.a(cursor);
        FLogger.d("DBHelper", "renameTmpFilesAndUpdateFilePath num:" + i, new Object[0]);
    }

    d(Context context) {
        super(context, "DownloadData.db", (SQLiteDatabase.CursorFactory) null, 2);
        this.f5611a = new h("download_request");
        this.b = new h("download_taskinfo");
        this.f5611a.a(new a("requestId", "INTEGER", true, true)).a(new a("name", "TEXT")).a(new a("fileSize", "INTEGER")).a(new a("filePath", "BLOB")).a(new a("status", "INTEGER")).a(new a("fileSha256", "TEXT")).a(new a("startPosition", "INTEGER")).a(new a("manager", "TEXT")).a(new a("headers", "BLOB")).a(new a(com.alipay.sdk.m.p.e.n, "BLOB")).a(new a("logInfos", "BLOB")).a(new a("configs", "BLOB")).a(new a(com.huawei.hms.kit.awareness.b.a.a.b, "BLOB")).a(new a(KnitFragment.KEY_EXTRA, "TEXT"));
        this.b.a(new a("requestId", "INTEGER")).a(new a("taskId", "INTEGER")).a(new a("start", "INTEGER")).a(new a("end", "INTEGER")).a(new a("finished", "INTEGER")).a(new a("filePath", "BLOB")).a(new a("manager", "TEXT")).a(new a(KnitFragment.KEY_EXTRA, "TEXT")).a(new b("requestId", "taskId"));
    }
}
