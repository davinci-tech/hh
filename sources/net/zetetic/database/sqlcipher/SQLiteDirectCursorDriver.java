package net.zetetic.database.sqlcipher;

import android.database.Cursor;
import android.os.CancellationSignal;
import net.zetetic.database.sqlcipher.SQLiteDatabase;

/* loaded from: classes7.dex */
public final class SQLiteDirectCursorDriver implements SQLiteCursorDriver {
    private final CancellationSignal mCancellationSignal;
    private final SQLiteDatabase mDatabase;
    private final String mEditTable;
    private SQLiteQuery mQuery;
    private final String mSql;

    @Override // net.zetetic.database.sqlcipher.SQLiteCursorDriver
    public void cursorClosed() {
    }

    @Override // net.zetetic.database.sqlcipher.SQLiteCursorDriver
    public void cursorDeactivated() {
    }

    @Override // net.zetetic.database.sqlcipher.SQLiteCursorDriver
    public void cursorRequeried(Cursor cursor) {
    }

    public SQLiteDirectCursorDriver(SQLiteDatabase sQLiteDatabase, String str, String str2, CancellationSignal cancellationSignal) {
        this.mDatabase = sQLiteDatabase;
        this.mEditTable = str2;
        this.mSql = str;
        this.mCancellationSignal = cancellationSignal;
    }

    @Override // net.zetetic.database.sqlcipher.SQLiteCursorDriver
    public Cursor query(SQLiteDatabase.CursorFactory cursorFactory, String[] strArr) {
        Cursor newCursor;
        SQLiteQuery sQLiteQuery = new SQLiteQuery(this.mDatabase, this.mSql, this.mCancellationSignal);
        try {
            sQLiteQuery.bindAllArgsAsStrings(strArr);
            if (cursorFactory == null) {
                newCursor = new SQLiteCursor(this, this.mEditTable, sQLiteQuery);
            } else {
                newCursor = cursorFactory.newCursor(this.mDatabase, this, this.mEditTable, sQLiteQuery);
            }
            this.mQuery = sQLiteQuery;
            return newCursor;
        } catch (RuntimeException e) {
            sQLiteQuery.close();
            throw e;
        }
    }

    public Cursor query(SQLiteDatabase.CursorFactory cursorFactory, Object... objArr) {
        Cursor newCursor;
        SQLiteQuery sQLiteQuery = new SQLiteQuery(this.mDatabase, this.mSql, this.mCancellationSignal);
        try {
            sQLiteQuery.bindAllArgs(objArr);
            if (cursorFactory == null) {
                newCursor = new SQLiteCursor(this, this.mEditTable, sQLiteQuery);
            } else {
                newCursor = cursorFactory.newCursor(this.mDatabase, this, this.mEditTable, sQLiteQuery);
            }
            this.mQuery = sQLiteQuery;
            return newCursor;
        } catch (RuntimeException e) {
            sQLiteQuery.close();
            throw e;
        }
    }

    @Override // net.zetetic.database.sqlcipher.SQLiteCursorDriver
    public void setBindArguments(String[] strArr) {
        this.mQuery.bindAllArgsAsStrings(strArr);
    }

    public String toString() {
        return "SQLiteDirectCursorDriver: " + this.mSql;
    }
}
