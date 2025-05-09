package defpackage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import com.huawei.android.hicloud.sync.c.a.a;

/* loaded from: classes8.dex */
public final class zz {
    private static volatile SQLiteDatabase c;
    private static volatile a d;
    private static Context e;

    public static void e(Context context) {
        synchronized (zz.class) {
            abd.c("SyncDBManager", "initDataBase start!");
            e = context.createDeviceProtectedStorageContext();
            if (d == null) {
                abd.c("SyncDBManager", "init db helper");
                d = new a(e);
            }
        }
    }

    public static SQLiteDatabase eN_() {
        SQLiteDatabase sQLiteDatabase;
        synchronized (zz.class) {
            if (c == null) {
                try {
                    if (d == null) {
                        e(e);
                    }
                    c = d.getWritableDatabase();
                } catch (SQLiteException unused) {
                    abd.b("SyncDBManager", "getDB() SQLiteException");
                }
            }
            sQLiteDatabase = c;
        }
        return sQLiteDatabase;
    }

    public static void d(Context context) {
        e = context;
    }

    public static Context e() {
        return e;
    }
}
