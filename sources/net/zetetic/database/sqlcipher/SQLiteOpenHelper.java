package net.zetetic.database.sqlcipher;

import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.huawei.hihealth.HiUserInfo;
import java.io.File;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import net.zetetic.database.DatabaseErrorHandler;
import net.zetetic.database.sqlcipher.SQLiteDatabase;

/* loaded from: classes.dex */
public abstract class SQLiteOpenHelper implements SupportSQLiteOpenHelper {
    private static final boolean DEBUG_STRICT_READONLY = false;
    private static final String TAG = "SQLiteOpenHelper";
    private final Context mContext;
    private SQLiteDatabase mDatabase;
    private final SQLiteDatabaseHook mDatabaseHook;
    private boolean mEnableWriteAheadLogging;
    private final DatabaseErrorHandler mErrorHandler;
    private final SQLiteDatabase.CursorFactory mFactory;
    private boolean mIsInitializing;
    private final int mMinimumSupportedVersion;
    private final String mName;
    private final int mNewVersion;
    private byte[] mPassword;

    public void onBeforeDelete(SQLiteDatabase sQLiteDatabase) {
    }

    public void onConfigure(SQLiteDatabase sQLiteDatabase) {
    }

    public abstract void onCreate(SQLiteDatabase sQLiteDatabase);

    public void onOpen(SQLiteDatabase sQLiteDatabase) {
    }

    public abstract void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2);

    public SQLiteOpenHelper(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory, int i) {
        this(context, str, cursorFactory, i, null);
    }

    public SQLiteOpenHelper(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory, int i, DatabaseErrorHandler databaseErrorHandler) {
        this(context, str, cursorFactory, i, 0, databaseErrorHandler);
    }

    public SQLiteOpenHelper(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory, int i, int i2, DatabaseErrorHandler databaseErrorHandler) {
        this(context, str, new byte[0], cursorFactory, i, i2, databaseErrorHandler, (SQLiteDatabaseHook) null, false);
    }

    public SQLiteOpenHelper(Context context, String str, String str2, SQLiteDatabase.CursorFactory cursorFactory, int i, int i2, DatabaseErrorHandler databaseErrorHandler, SQLiteDatabaseHook sQLiteDatabaseHook, boolean z) {
        this(context, str, getBytes(str2), cursorFactory, i, i2, databaseErrorHandler, sQLiteDatabaseHook, z);
    }

    public SQLiteOpenHelper(Context context, String str, byte[] bArr, SQLiteDatabase.CursorFactory cursorFactory, int i, int i2, DatabaseErrorHandler databaseErrorHandler, SQLiteDatabaseHook sQLiteDatabaseHook, boolean z) {
        if (i < 1) {
            throw new IllegalArgumentException("Version must be >= 1, was " + i);
        }
        this.mContext = context;
        this.mName = str;
        this.mPassword = bArr;
        this.mFactory = cursorFactory;
        this.mNewVersion = i;
        this.mErrorHandler = databaseErrorHandler;
        this.mDatabaseHook = sQLiteDatabaseHook;
        this.mEnableWriteAheadLogging = z;
        this.mMinimumSupportedVersion = Math.max(0, i2);
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper
    public String getDatabaseName() {
        return this.mName;
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper
    public void setWriteAheadLoggingEnabled(boolean z) {
        synchronized (this) {
            if (this.mEnableWriteAheadLogging != z) {
                SQLiteDatabase sQLiteDatabase = this.mDatabase;
                if (sQLiteDatabase != null && sQLiteDatabase.isOpen() && !this.mDatabase.isReadOnly()) {
                    if (z) {
                        this.mDatabase.enableWriteAheadLogging();
                    } else {
                        this.mDatabase.disableWriteAheadLogging();
                    }
                }
                this.mEnableWriteAheadLogging = z;
            }
        }
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper
    public SQLiteDatabase getWritableDatabase() {
        SQLiteDatabase databaseLocked;
        synchronized (this) {
            databaseLocked = getDatabaseLocked(true);
        }
        return databaseLocked;
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper
    public SQLiteDatabase getReadableDatabase() {
        SQLiteDatabase databaseLocked;
        synchronized (this) {
            databaseLocked = getDatabaseLocked(false);
        }
        return databaseLocked;
    }

    private SQLiteDatabase getDatabaseLocked(boolean z) {
        SQLiteDatabase sQLiteDatabase = this.mDatabase;
        if (sQLiteDatabase != null) {
            if (!sQLiteDatabase.isOpen()) {
                this.mDatabase = null;
            } else if (!z || !this.mDatabase.isReadOnly()) {
                return this.mDatabase;
            }
        }
        if (this.mIsInitializing) {
            throw new IllegalStateException("getDatabase called recursively");
        }
        SQLiteDatabase sQLiteDatabase2 = this.mDatabase;
        try {
            this.mIsInitializing = true;
            if (sQLiteDatabase2 != null) {
                if (z && sQLiteDatabase2.isReadOnly()) {
                    sQLiteDatabase2.reopenReadWrite();
                }
            } else {
                String str = this.mName;
                if (str == null) {
                    sQLiteDatabase2 = SQLiteDatabase.create(null);
                } else {
                    try {
                        if (!str.startsWith("file:")) {
                            str = this.mContext.getDatabasePath(str).getPath();
                        }
                        String str2 = str;
                        File file = new File(new File(str2).getParent());
                        if (!file.exists()) {
                            file.mkdirs();
                        }
                        sQLiteDatabase2 = SQLiteDatabase.openDatabase(str2, this.mPassword, this.mFactory, this.mEnableWriteAheadLogging ? HiUserInfo.DATA_CLOUD : 268435456, this.mErrorHandler, this.mDatabaseHook);
                    } catch (SQLiteException e) {
                        if (z) {
                            throw e;
                        }
                        Log.e(TAG, "Couldn't open " + this.mName + " for writing (will try read-only):", e);
                        sQLiteDatabase2 = SQLiteDatabase.openDatabase(this.mContext.getDatabasePath(this.mName).getPath(), this.mPassword, this.mFactory, 1, this.mErrorHandler, this.mDatabaseHook);
                    }
                }
            }
            onConfigure(sQLiteDatabase2);
            int version = sQLiteDatabase2.getVersion();
            if (version != this.mNewVersion) {
                if (sQLiteDatabase2.isReadOnly()) {
                    throw new SQLiteException("Can't upgrade read-only database from version " + sQLiteDatabase2.getVersion() + " to " + this.mNewVersion + ": " + this.mName);
                }
                if (version > 0 && version < this.mMinimumSupportedVersion) {
                    File file2 = new File(sQLiteDatabase2.getPath());
                    onBeforeDelete(sQLiteDatabase2);
                    sQLiteDatabase2.close();
                    if (SQLiteDatabase.deleteDatabase(file2)) {
                        this.mIsInitializing = false;
                        return getDatabaseLocked(z);
                    }
                    throw new IllegalStateException("Unable to delete obsolete database " + this.mName + " with version " + version);
                }
                sQLiteDatabase2.beginTransaction();
                try {
                    if (version == 0) {
                        onCreate(sQLiteDatabase2);
                    } else {
                        int i = this.mNewVersion;
                        if (version > i) {
                            onDowngrade(sQLiteDatabase2, version, i);
                        } else {
                            onUpgrade(sQLiteDatabase2, version, i);
                        }
                    }
                    sQLiteDatabase2.setVersion(this.mNewVersion);
                    sQLiteDatabase2.setTransactionSuccessful();
                    sQLiteDatabase2.endTransaction();
                } catch (Throwable th) {
                    sQLiteDatabase2.endTransaction();
                    throw th;
                }
            }
            onOpen(sQLiteDatabase2);
            if (sQLiteDatabase2.isReadOnly()) {
                Log.w(TAG, "Opened " + this.mName + " in read-only mode");
            }
            this.mDatabase = sQLiteDatabase2;
            this.mIsInitializing = false;
            return sQLiteDatabase2;
        } finally {
            this.mIsInitializing = false;
            if (sQLiteDatabase2 != null && sQLiteDatabase2 != this.mDatabase) {
                sQLiteDatabase2.close();
            }
        }
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        synchronized (this) {
            if (this.mIsInitializing) {
                throw new IllegalStateException("Closed during initialization");
            }
            SQLiteDatabase sQLiteDatabase = this.mDatabase;
            if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
                this.mDatabase.close();
                this.mDatabase = null;
            }
        }
    }

    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        throw new SQLiteException("Can't downgrade database from version " + i + " to " + i2);
    }

    private static byte[] getBytes(String str) {
        if (str == null || str.length() == 0) {
            return new byte[0];
        }
        ByteBuffer encode = Charset.forName("UTF-8").encode(CharBuffer.wrap(str));
        byte[] bArr = new byte[encode.limit()];
        encode.get(bArr);
        return bArr;
    }
}
