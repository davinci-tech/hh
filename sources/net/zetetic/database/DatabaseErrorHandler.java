package net.zetetic.database;

import net.zetetic.database.sqlcipher.SQLiteDatabase;

/* loaded from: classes2.dex */
public interface DatabaseErrorHandler {
    void onCorruption(SQLiteDatabase sQLiteDatabase);
}
