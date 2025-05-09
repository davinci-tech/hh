package net.zetetic.database;

import android.database.sqlite.SQLiteException;
import android.util.Log;
import android.util.Pair;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import net.zetetic.database.sqlcipher.SQLiteDatabase;
import net.zetetic.database.sqlcipher.SQLiteDatabaseConfiguration;

/* loaded from: classes7.dex */
public final class DefaultDatabaseErrorHandler implements DatabaseErrorHandler {
    private static final String TAG = "DefaultDatabaseErrorHandler";

    @Override // net.zetetic.database.DatabaseErrorHandler
    public void onCorruption(SQLiteDatabase sQLiteDatabase) {
        Log.e(TAG, "Corruption reported by sqlite on database: " + sQLiteDatabase.getPath());
        if (SQLiteDatabase.hasCodec()) {
            return;
        }
        if (!sQLiteDatabase.isOpen()) {
            deleteDatabaseFile(sQLiteDatabase.getPath());
            return;
        }
        List<Pair<String, String>> list = null;
        try {
            try {
                list = sQLiteDatabase.getAttachedDbs();
            } finally {
                if (list != null) {
                    Iterator<Pair<String, String>> it = list.iterator();
                    while (it.hasNext()) {
                        deleteDatabaseFile((String) it.next().second);
                    }
                } else {
                    deleteDatabaseFile(sQLiteDatabase.getPath());
                }
            }
        } catch (SQLiteException unused) {
        }
        try {
            sQLiteDatabase.close();
        } catch (SQLiteException unused2) {
        }
    }

    private void deleteDatabaseFile(String str) {
        if (str.equalsIgnoreCase(SQLiteDatabaseConfiguration.MEMORY_DB_PATH) || str.trim().length() == 0) {
            return;
        }
        Log.e(TAG, "deleting the database file: " + str);
        try {
            SQLiteDatabase.deleteDatabase(new File(str));
        } catch (Exception e) {
            Log.w(TAG, "delete failed: " + e.getMessage());
        }
    }
}
