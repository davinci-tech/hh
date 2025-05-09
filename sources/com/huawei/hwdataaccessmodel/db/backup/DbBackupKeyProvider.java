package com.huawei.hwdataaccessmodel.db.backup;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/* loaded from: classes.dex */
public class DbBackupKeyProvider extends SQLiteOpenHelper {

    /* renamed from: a, reason: collision with root package name */
    private static final byte[] f6271a = new byte[1];
    private static volatile DbBackupKeyProvider b;

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    private DbBackupKeyProvider(Context context) {
        super(context, "HwCPBackupDatas.db", (SQLiteDatabase.CursorFactory) null, 1);
    }

    public static DbBackupKeyProvider b(Context context) {
        if (b == null) {
            synchronized (f6271a) {
                if (b == null) {
                    b = new DbBackupKeyProvider(context);
                }
            }
        }
        return b;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("create table IF NOT EXISTS backupCp(backupKey varchar primary key ,backupValue varchar)");
        sQLiteDatabase.execSQL(String.valueOf(stringBuffer));
    }
}
