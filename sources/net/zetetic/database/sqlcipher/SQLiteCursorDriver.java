package net.zetetic.database.sqlcipher;

import android.database.Cursor;
import net.zetetic.database.sqlcipher.SQLiteDatabase;

/* loaded from: classes7.dex */
public interface SQLiteCursorDriver {
    void cursorClosed();

    void cursorDeactivated();

    void cursorRequeried(Cursor cursor);

    Cursor query(SQLiteDatabase.CursorFactory cursorFactory, String[] strArr);

    void setBindArguments(String[] strArr);
}
