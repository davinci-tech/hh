package net.zetetic.database.sqlcipher;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabaseCorruptException;
import android.database.sqlite.SQLiteException;
import android.os.CancellationSignal;
import android.os.Looper;
import android.text.TextUtils;
import android.util.EventLog;
import android.util.Log;
import android.util.Pair;
import android.util.Printer;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteQuery;
import com.huawei.hms.network.embedded.g4;
import java.io.File;
import java.io.FileFilter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.WeakHashMap;
import net.zetetic.database.DatabaseErrorHandler;
import net.zetetic.database.DatabaseUtils;
import net.zetetic.database.DefaultDatabaseErrorHandler;
import net.zetetic.database.sqlcipher.SQLiteDebug;

/* loaded from: classes2.dex */
public final class SQLiteDatabase extends SQLiteClosable implements SupportSQLiteDatabase {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int CONFLICT_ABORT = 2;
    public static final int CONFLICT_FAIL = 3;
    public static final int CONFLICT_IGNORE = 4;
    public static final int CONFLICT_NONE = 0;
    public static final int CONFLICT_REPLACE = 5;
    public static final int CONFLICT_ROLLBACK = 1;
    public static final int CREATE_IF_NECESSARY = 268435456;
    public static final int ENABLE_WRITE_AHEAD_LOGGING = 536870912;
    private static final int EVENT_DB_CORRUPT = 75004;
    public static final int MAX_SQL_CACHE_SIZE = 100;
    public static final int NO_LOCALIZED_COLLATORS = 16;
    public static final int OPEN_READONLY = 1;
    public static final int OPEN_READWRITE = 0;
    private static final int OPEN_READ_MASK = 1;
    public static final int SQLITE_MAX_LIKE_PATTERN_LENGTH = 50000;
    private static final String TAG = "SQLiteDatabase";
    private final SQLiteDatabaseConfiguration mConfigurationLocked;
    private SQLiteConnectionPool mConnectionPoolLocked;
    private final CursorFactory mCursorFactory;
    private final DatabaseErrorHandler mErrorHandler;
    private boolean mHasAttachedDbsLocked;
    private static WeakHashMap<SQLiteDatabase, Object> sActiveDatabases = new WeakHashMap<>();
    private static final String[] CONFLICT_VALUES = {"", " OR ROLLBACK ", " OR ABORT ", " OR FAIL ", " OR IGNORE ", " OR REPLACE "};
    private final ThreadLocal<SQLiteSession> mThreadSession = new ThreadLocal<SQLiteSession>() { // from class: net.zetetic.database.sqlcipher.SQLiteDatabase.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public SQLiteSession initialValue() {
            return SQLiteDatabase.this.createSession();
        }
    };
    private final Object mLock = new Object();
    private final CloseGuard mCloseGuardLocked = CloseGuard.get();

    /* loaded from: classes7.dex */
    public interface CursorFactory {
        Cursor newCursor(SQLiteDatabase sQLiteDatabase, SQLiteCursorDriver sQLiteCursorDriver, String str, SQLiteQuery sQLiteQuery);
    }

    /* loaded from: classes10.dex */
    public interface CustomFunction {
        void callback(String[] strArr);
    }

    @Deprecated
    public boolean isDbLockedByOtherThreads() {
        return false;
    }

    @Deprecated
    public void markTableSyncable(String str, String str2) {
    }

    @Deprecated
    public void markTableSyncable(String str, String str2, String str3) {
    }

    @Deprecated
    public void setLockingEnabled(boolean z) {
    }

    private SQLiteDatabase(String str, byte[] bArr, int i, CursorFactory cursorFactory, DatabaseErrorHandler databaseErrorHandler, SQLiteDatabaseHook sQLiteDatabaseHook) {
        this.mCursorFactory = cursorFactory;
        this.mErrorHandler = databaseErrorHandler == null ? new DefaultDatabaseErrorHandler() : databaseErrorHandler;
        this.mConfigurationLocked = new SQLiteDatabaseConfiguration(str, i, bArr, sQLiteDatabaseHook);
    }

    protected void finalize() throws Throwable {
        try {
            dispose(true);
        } finally {
            super.finalize();
        }
    }

    @Override // net.zetetic.database.sqlcipher.SQLiteClosable
    protected void onAllReferencesReleased() {
        dispose(false);
    }

    private void dispose(boolean z) {
        SQLiteConnectionPool sQLiteConnectionPool;
        synchronized (this.mLock) {
            CloseGuard closeGuard = this.mCloseGuardLocked;
            if (closeGuard != null) {
                if (z) {
                    closeGuard.warnIfOpen();
                }
                this.mCloseGuardLocked.close();
            }
            sQLiteConnectionPool = this.mConnectionPoolLocked;
            this.mConnectionPoolLocked = null;
        }
        if (z) {
            return;
        }
        synchronized (sActiveDatabases) {
            sActiveDatabases.remove(this);
        }
        if (sQLiteConnectionPool != null) {
            sQLiteConnectionPool.close();
        }
    }

    public static int releaseMemory() {
        return SQLiteGlobal.releaseMemory();
    }

    String getLabel() {
        String str;
        synchronized (this.mLock) {
            str = this.mConfigurationLocked.label;
        }
        return str;
    }

    void onCorruption() {
        EventLog.writeEvent(EVENT_DB_CORRUPT, getLabel());
        this.mErrorHandler.onCorruption(this);
    }

    SQLiteSession getThreadSession() {
        return this.mThreadSession.get();
    }

    SQLiteSession createSession() {
        SQLiteConnectionPool sQLiteConnectionPool;
        synchronized (this.mLock) {
            throwIfNotOpenLocked();
            sQLiteConnectionPool = this.mConnectionPoolLocked;
        }
        return new SQLiteSession(sQLiteConnectionPool);
    }

    int getThreadDefaultConnectionFlags(boolean z) {
        int i = z ? 1 : 2;
        return isMainThread() ? i | 4 : i;
    }

    private static boolean isMainThread() {
        Looper myLooper = Looper.myLooper();
        return myLooper != null && myLooper == Looper.getMainLooper();
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public void beginTransaction() {
        beginTransaction(null, true);
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public void beginTransactionNonExclusive() {
        beginTransaction(null, false);
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public void beginTransactionWithListener(final android.database.sqlite.SQLiteTransactionListener sQLiteTransactionListener) {
        beginTransaction(new SQLiteTransactionListener() { // from class: net.zetetic.database.sqlcipher.SQLiteDatabase.2
            @Override // net.zetetic.database.sqlcipher.SQLiteTransactionListener
            public void onBegin() {
                sQLiteTransactionListener.onBegin();
            }

            @Override // net.zetetic.database.sqlcipher.SQLiteTransactionListener
            public void onCommit() {
                sQLiteTransactionListener.onCommit();
            }

            @Override // net.zetetic.database.sqlcipher.SQLiteTransactionListener
            public void onRollback() {
                sQLiteTransactionListener.onRollback();
            }
        }, true);
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public void beginTransactionWithListenerNonExclusive(final android.database.sqlite.SQLiteTransactionListener sQLiteTransactionListener) {
        beginTransactionWithListenerNonExclusive(new SQLiteTransactionListener() { // from class: net.zetetic.database.sqlcipher.SQLiteDatabase.3
            @Override // net.zetetic.database.sqlcipher.SQLiteTransactionListener
            public void onBegin() {
                sQLiteTransactionListener.onBegin();
            }

            @Override // net.zetetic.database.sqlcipher.SQLiteTransactionListener
            public void onCommit() {
                sQLiteTransactionListener.onCommit();
            }

            @Override // net.zetetic.database.sqlcipher.SQLiteTransactionListener
            public void onRollback() {
                sQLiteTransactionListener.onRollback();
            }
        });
    }

    public void beginTransactionWithListener(SQLiteTransactionListener sQLiteTransactionListener) {
        beginTransaction(sQLiteTransactionListener, true);
    }

    public void beginTransactionWithListenerNonExclusive(SQLiteTransactionListener sQLiteTransactionListener) {
        beginTransaction(sQLiteTransactionListener, false);
    }

    private void beginTransaction(SQLiteTransactionListener sQLiteTransactionListener, boolean z) {
        acquireReference();
        try {
            getThreadSession().beginTransaction(z ? 2 : 1, sQLiteTransactionListener, getThreadDefaultConnectionFlags(false), null);
        } finally {
            releaseReference();
        }
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public void endTransaction() {
        acquireReference();
        try {
            getThreadSession().endTransaction(null);
        } finally {
            releaseReference();
        }
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public void setTransactionSuccessful() {
        acquireReference();
        try {
            getThreadSession().setTransactionSuccessful();
        } finally {
            releaseReference();
        }
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public boolean inTransaction() {
        acquireReference();
        try {
            return getThreadSession().hasTransaction();
        } finally {
            releaseReference();
        }
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public boolean isDbLockedByCurrentThread() {
        acquireReference();
        try {
            return getThreadSession().hasConnection();
        } finally {
            releaseReference();
        }
    }

    @Deprecated
    public boolean yieldIfContended() {
        return yieldIfContendedHelper(false, -1L);
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public boolean yieldIfContendedSafely() {
        return yieldIfContendedHelper(true, -1L);
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public boolean yieldIfContendedSafely(long j) {
        return yieldIfContendedHelper(true, j);
    }

    private boolean yieldIfContendedHelper(boolean z, long j) {
        acquireReference();
        try {
            return getThreadSession().yieldTransaction(j, z, null);
        } finally {
            releaseReference();
        }
    }

    @Deprecated
    public Map<String, String> getSyncedTables() {
        return new HashMap(0);
    }

    public static SQLiteDatabase openDatabase(String str, CursorFactory cursorFactory, int i) {
        return openDatabase(str, cursorFactory, i, null);
    }

    public static SQLiteDatabase openDatabase(String str, String str2, CursorFactory cursorFactory, int i, SQLiteDatabaseHook sQLiteDatabaseHook) {
        return openDatabase(str, getBytes(str2), cursorFactory, i, (DatabaseErrorHandler) null, sQLiteDatabaseHook);
    }

    public static SQLiteDatabase openDatabase(String str, byte[] bArr, CursorFactory cursorFactory, int i, SQLiteDatabaseHook sQLiteDatabaseHook) {
        return openDatabase(str, bArr, cursorFactory, i, (DatabaseErrorHandler) null, sQLiteDatabaseHook);
    }

    public static SQLiteDatabase openDatabase(String str, CursorFactory cursorFactory, int i, DatabaseErrorHandler databaseErrorHandler) {
        return openDatabase(str, new byte[0], cursorFactory, i, databaseErrorHandler, (SQLiteDatabaseHook) null);
    }

    public static SQLiteDatabase openDatabase(String str, String str2, CursorFactory cursorFactory, int i, DatabaseErrorHandler databaseErrorHandler, SQLiteDatabaseHook sQLiteDatabaseHook) {
        return openDatabase(str, getBytes(str2), cursorFactory, i, databaseErrorHandler, sQLiteDatabaseHook);
    }

    public static SQLiteDatabase openDatabase(String str, byte[] bArr, CursorFactory cursorFactory, int i, DatabaseErrorHandler databaseErrorHandler, SQLiteDatabaseHook sQLiteDatabaseHook) {
        SQLiteDatabase sQLiteDatabase = new SQLiteDatabase(str, bArr, i, cursorFactory, databaseErrorHandler, sQLiteDatabaseHook);
        sQLiteDatabase.open();
        return sQLiteDatabase;
    }

    public static SQLiteDatabase openOrCreateDatabase(File file, CursorFactory cursorFactory) {
        return openOrCreateDatabase(file.getPath(), cursorFactory);
    }

    public static SQLiteDatabase openOrCreateDatabase(String str, CursorFactory cursorFactory) {
        return openDatabase(str, cursorFactory, 268435456, null);
    }

    public static SQLiteDatabase openOrCreateDatabase(String str, CursorFactory cursorFactory, DatabaseErrorHandler databaseErrorHandler) {
        return openDatabase(str, cursorFactory, 268435456, databaseErrorHandler);
    }

    public static SQLiteDatabase openOrCreateDatabase(File file, String str, CursorFactory cursorFactory, DatabaseErrorHandler databaseErrorHandler) {
        return openDatabase(file.getAbsolutePath(), str, cursorFactory, 268435456, databaseErrorHandler, (SQLiteDatabaseHook) null);
    }

    public static SQLiteDatabase openOrCreateDatabase(File file, byte[] bArr, CursorFactory cursorFactory, DatabaseErrorHandler databaseErrorHandler) {
        return openDatabase(file.getAbsolutePath(), bArr, cursorFactory, 268435456, databaseErrorHandler, (SQLiteDatabaseHook) null);
    }

    public static SQLiteDatabase openOrCreateDatabase(String str, String str2, CursorFactory cursorFactory, DatabaseErrorHandler databaseErrorHandler) {
        return openDatabase(str, str2, cursorFactory, 268435456, databaseErrorHandler, (SQLiteDatabaseHook) null);
    }

    public static SQLiteDatabase openOrCreateDatabase(String str, byte[] bArr, CursorFactory cursorFactory, DatabaseErrorHandler databaseErrorHandler) {
        return openDatabase(str, bArr, cursorFactory, 268435456, databaseErrorHandler, (SQLiteDatabaseHook) null);
    }

    public static SQLiteDatabase openOrCreateDatabase(File file, String str, CursorFactory cursorFactory, DatabaseErrorHandler databaseErrorHandler, SQLiteDatabaseHook sQLiteDatabaseHook) {
        return openDatabase(file.getAbsolutePath(), str, cursorFactory, 268435456, databaseErrorHandler, sQLiteDatabaseHook);
    }

    public static SQLiteDatabase openOrCreateDatabase(File file, byte[] bArr, CursorFactory cursorFactory, DatabaseErrorHandler databaseErrorHandler, SQLiteDatabaseHook sQLiteDatabaseHook) {
        return openDatabase(file.getAbsolutePath(), bArr, cursorFactory, 268435456, databaseErrorHandler, sQLiteDatabaseHook);
    }

    public static SQLiteDatabase openOrCreateDatabase(String str, String str2, CursorFactory cursorFactory, DatabaseErrorHandler databaseErrorHandler, SQLiteDatabaseHook sQLiteDatabaseHook) {
        return openDatabase(str, str2, cursorFactory, 268435456, databaseErrorHandler, sQLiteDatabaseHook);
    }

    public static SQLiteDatabase openOrCreateDatabase(String str, byte[] bArr, CursorFactory cursorFactory, DatabaseErrorHandler databaseErrorHandler, SQLiteDatabaseHook sQLiteDatabaseHook) {
        return openDatabase(str, bArr, cursorFactory, 268435456, databaseErrorHandler, sQLiteDatabaseHook);
    }

    public static boolean deleteDatabase(File file) {
        if (file == null) {
            throw new IllegalArgumentException("file must not be null");
        }
        boolean delete = file.delete() | new File(file.getPath() + "-journal").delete() | new File(file.getPath() + "-shm").delete() | new File(file.getPath() + "-wal").delete();
        File parentFile = file.getParentFile();
        if (parentFile != null) {
            final String str = file.getName() + "-mj";
            File[] listFiles = parentFile.listFiles(new FileFilter() { // from class: net.zetetic.database.sqlcipher.SQLiteDatabase.4
                @Override // java.io.FileFilter
                public boolean accept(File file2) {
                    return file2.getName().startsWith(str);
                }
            });
            if (listFiles != null) {
                for (File file2 : listFiles) {
                    delete |= file2.delete();
                }
            }
        }
        return delete;
    }

    public void reopenReadWrite() {
        synchronized (this.mLock) {
            throwIfNotOpenLocked();
            if (isReadOnlyLocked()) {
                int i = this.mConfigurationLocked.openFlags;
                this.mConfigurationLocked.openFlags &= -2;
                try {
                    this.mConnectionPoolLocked.reconfigure(this.mConfigurationLocked);
                } catch (RuntimeException e) {
                    this.mConfigurationLocked.openFlags = i;
                    throw e;
                }
            }
        }
    }

    private void open() {
        try {
            try {
                openInner();
            } catch (SQLiteDatabaseCorruptException unused) {
                onCorruption();
                openInner();
            }
        } catch (SQLiteException e) {
            Log.e(TAG, "Failed to open database '" + getLabel() + "'.", e);
            close();
            throw e;
        }
    }

    private void openInner() {
        synchronized (this.mLock) {
            this.mConnectionPoolLocked = SQLiteConnectionPool.open(this.mConfigurationLocked);
            this.mCloseGuardLocked.open("close");
        }
        synchronized (sActiveDatabases) {
            sActiveDatabases.put(this, null);
        }
    }

    public static SQLiteDatabase create(CursorFactory cursorFactory) {
        return openDatabase(SQLiteDatabaseConfiguration.MEMORY_DB_PATH, cursorFactory, 268435456);
    }

    public void addCustomFunction(String str, int i, CustomFunction customFunction) {
        SQLiteCustomFunction sQLiteCustomFunction = new SQLiteCustomFunction(str, i, customFunction);
        synchronized (this.mLock) {
            throwIfNotOpenLocked();
            this.mConfigurationLocked.customFunctions.add(sQLiteCustomFunction);
            try {
                this.mConnectionPoolLocked.reconfigure(this.mConfigurationLocked);
            } catch (RuntimeException e) {
                this.mConfigurationLocked.customFunctions.remove(sQLiteCustomFunction);
                throw e;
            }
        }
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public int getVersion() {
        return Long.valueOf(DatabaseUtils.longForQuery(this, "PRAGMA user_version;", null)).intValue();
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public void setVersion(int i) {
        execSQL("PRAGMA user_version = " + i);
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public long getMaximumSize() {
        return DatabaseUtils.longForQuery(this, "PRAGMA max_page_count;", null) * getPageSize();
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public long setMaximumSize(long j) {
        long pageSize = getPageSize();
        long j2 = j / pageSize;
        if (j % pageSize != 0) {
            j2++;
        }
        return DatabaseUtils.longForQuery(this, "PRAGMA max_page_count = " + j2, null) * pageSize;
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public long getPageSize() {
        return DatabaseUtils.longForQuery(this, "PRAGMA page_size;", null);
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public void setPageSize(long j) {
        execSQL("PRAGMA page_size = " + j);
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public Cursor query(String str) {
        return rawQuery(str, new Object[0]);
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public Cursor query(String str, Object[] objArr) {
        return rawQuery(str, objArr);
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public Cursor query(SupportSQLiteQuery supportSQLiteQuery) {
        return query(supportSQLiteQuery, (CancellationSignal) null);
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public Cursor query(SupportSQLiteQuery supportSQLiteQuery, CancellationSignal cancellationSignal) {
        acquireReference();
        try {
            String sql = supportSQLiteQuery.getSql();
            SQLiteDirectCursorDriver sQLiteDirectCursorDriver = new SQLiteDirectCursorDriver(this, sql, "", cancellationSignal);
            SQLiteQuery sQLiteQuery = new SQLiteQuery(this, sql, cancellationSignal);
            supportSQLiteQuery.bindTo(sQLiteQuery);
            return new SQLiteCursor(sQLiteDirectCursorDriver, "", sQLiteQuery);
        } finally {
            releaseReference();
        }
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public long insert(String str, int i, ContentValues contentValues) throws SQLException {
        return insertWithOnConflict(str, null, contentValues, i);
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public int delete(String str, String str2, Object[] objArr) {
        String[] strArr = new String[objArr.length];
        for (int i = 0; i < objArr.length; i++) {
            strArr[i] = objArr[i].toString();
        }
        return delete(str, str2, strArr);
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public int update(String str, int i, ContentValues contentValues, String str2, Object[] objArr) {
        String[] strArr = new String[objArr.length];
        for (int i2 = 0; i2 < objArr.length; i2++) {
            strArr[i2] = objArr[i2].toString();
        }
        return updateWithOnConflict(str, contentValues, str2, strArr, i);
    }

    public static String findEditTable(String str) {
        if (!TextUtils.isEmpty(str)) {
            int indexOf = str.indexOf(32);
            int indexOf2 = str.indexOf(44);
            if (indexOf <= 0 || (indexOf >= indexOf2 && indexOf2 >= 0)) {
                return indexOf2 > 0 ? (indexOf2 < indexOf || indexOf < 0) ? str.substring(0, indexOf2) : str : str;
            }
            return str.substring(0, indexOf);
        }
        throw new IllegalStateException("Invalid tables");
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public SQLiteStatement compileStatement(String str) throws SQLException {
        acquireReference();
        try {
            return new SQLiteStatement(this, str, null);
        } finally {
            releaseReference();
        }
    }

    public Cursor query(boolean z, String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6) {
        return queryWithFactory(null, z, str, strArr, str2, strArr2, str3, str4, str5, str6, null);
    }

    public Cursor query(boolean z, String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6, CancellationSignal cancellationSignal) {
        return queryWithFactory(null, z, str, strArr, str2, strArr2, str3, str4, str5, str6, cancellationSignal);
    }

    public Cursor queryWithFactory(CursorFactory cursorFactory, boolean z, String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6) {
        return queryWithFactory(cursorFactory, z, str, strArr, str2, strArr2, str3, str4, str5, str6, null);
    }

    public Cursor queryWithFactory(CursorFactory cursorFactory, boolean z, String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6, CancellationSignal cancellationSignal) {
        acquireReference();
        try {
            return rawQueryWithFactory(cursorFactory, SQLiteQueryBuilder.buildQueryString(z, str, strArr, str2, str3, str4, str5, str6), strArr2, findEditTable(str), cancellationSignal);
        } finally {
            releaseReference();
        }
    }

    public Cursor query(String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5) {
        return query(false, str, strArr, str2, strArr2, str3, str4, str5, null);
    }

    public Cursor query(String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6) {
        return query(false, str, strArr, str2, strArr2, str3, str4, str5, str6);
    }

    public Cursor rawQuery(String str, String[] strArr) {
        return rawQueryWithFactory(null, str, strArr, null, null);
    }

    public Cursor rawQuery(String str, Object... objArr) {
        acquireReference();
        try {
            return new SQLiteDirectCursorDriver(this, str, null, null).query(this.mCursorFactory, objArr);
        } finally {
            releaseReference();
        }
    }

    public Cursor rawQuery(String str, String[] strArr, CancellationSignal cancellationSignal) {
        return rawQueryWithFactory(null, str, strArr, null, cancellationSignal);
    }

    public Cursor rawQueryWithFactory(CursorFactory cursorFactory, String str, String[] strArr, String str2) {
        return rawQueryWithFactory(cursorFactory, str, strArr, str2, null);
    }

    public Cursor rawQueryWithFactory(CursorFactory cursorFactory, String str, String[] strArr, String str2, CancellationSignal cancellationSignal) {
        acquireReference();
        try {
            SQLiteDirectCursorDriver sQLiteDirectCursorDriver = new SQLiteDirectCursorDriver(this, str, str2, cancellationSignal);
            if (cursorFactory == null) {
                cursorFactory = this.mCursorFactory;
            }
            return sQLiteDirectCursorDriver.query(cursorFactory, strArr);
        } finally {
            releaseReference();
        }
    }

    public long insert(String str, String str2, ContentValues contentValues) {
        try {
            return insertWithOnConflict(str, str2, contentValues, 0);
        } catch (SQLException e) {
            Log.e(TAG, "Error inserting", e);
            return -1L;
        }
    }

    public long insertOrThrow(String str, String str2, ContentValues contentValues) throws SQLException {
        return insertWithOnConflict(str, str2, contentValues, 0);
    }

    public long replace(String str, String str2, ContentValues contentValues) {
        try {
            return insertWithOnConflict(str, str2, contentValues, 5);
        } catch (SQLException e) {
            Log.e(TAG, "Error inserting", e);
            return -1L;
        }
    }

    public long replaceOrThrow(String str, String str2, ContentValues contentValues) throws SQLException {
        return insertWithOnConflict(str, str2, contentValues, 5);
    }

    public long insertWithOnConflict(String str, String str2, ContentValues contentValues, int i) {
        Object[] objArr;
        acquireReference();
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT");
            sb.append(CONFLICT_VALUES[i]);
            sb.append(" INTO ");
            sb.append(str);
            sb.append(g4.k);
            int i2 = 0;
            int size = (contentValues == null || contentValues.size() <= 0) ? 0 : contentValues.size();
            if (size > 0) {
                objArr = new Object[size];
                int i3 = 0;
                for (String str3 : contentValues.keySet()) {
                    sb.append(i3 > 0 ? "," : "");
                    sb.append(str3);
                    objArr[i3] = contentValues.get(str3);
                    i3++;
                }
                sb.append(g4.l);
                sb.append(" VALUES (");
                while (i2 < size) {
                    sb.append(i2 > 0 ? ",?" : "?");
                    i2++;
                }
            } else {
                sb.append(str2 + ") VALUES (NULL");
                objArr = null;
            }
            sb.append(g4.l);
            SQLiteStatement sQLiteStatement = new SQLiteStatement(this, sb.toString(), objArr);
            try {
                return sQLiteStatement.executeInsert();
            } finally {
                sQLiteStatement.close();
            }
        } finally {
            releaseReference();
        }
    }

    public int delete(String str, String str2, String[] strArr) {
        String str3;
        acquireReference();
        try {
            StringBuilder sb = new StringBuilder("DELETE FROM ");
            sb.append(str);
            if (TextUtils.isEmpty(str2)) {
                str3 = "";
            } else {
                str3 = " WHERE " + str2;
            }
            sb.append(str3);
            SQLiteStatement sQLiteStatement = new SQLiteStatement(this, sb.toString(), strArr);
            try {
                return sQLiteStatement.executeUpdateDelete();
            } finally {
                sQLiteStatement.close();
            }
        } finally {
            releaseReference();
        }
    }

    public int update(String str, ContentValues contentValues, String str2, String[] strArr) {
        return updateWithOnConflict(str, contentValues, str2, strArr, 0);
    }

    public int updateWithOnConflict(String str, ContentValues contentValues, String str2, String[] strArr, int i) {
        if (contentValues == null || contentValues.size() == 0) {
            throw new IllegalArgumentException("Empty values");
        }
        acquireReference();
        try {
            StringBuilder sb = new StringBuilder(120);
            sb.append("UPDATE ");
            sb.append(CONFLICT_VALUES[i]);
            sb.append(str);
            sb.append(" SET ");
            int size = contentValues.size();
            int length = strArr == null ? size : strArr.length + size;
            Object[] objArr = new Object[length];
            int i2 = 0;
            for (String str3 : contentValues.keySet()) {
                sb.append(i2 > 0 ? "," : "");
                sb.append(str3);
                objArr[i2] = contentValues.get(str3);
                sb.append("=?");
                i2++;
            }
            if (strArr != null) {
                for (int i3 = size; i3 < length; i3++) {
                    objArr[i3] = strArr[i3 - size];
                }
            }
            if (!TextUtils.isEmpty(str2)) {
                sb.append(" WHERE ");
                sb.append(str2);
            }
            SQLiteStatement sQLiteStatement = new SQLiteStatement(this, sb.toString(), objArr);
            try {
                return sQLiteStatement.executeUpdateDelete();
            } finally {
                sQLiteStatement.close();
            }
        } finally {
            releaseReference();
        }
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public void execSQL(String str) throws SQLException {
        executeSql(str, null);
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public void execSQL(String str, Object[] objArr) throws SQLException {
        if (objArr == null) {
            throw new IllegalArgumentException("Empty bindArgs");
        }
        executeSql(str, objArr);
    }

    public void rawExecSQL(String str, Object... objArr) throws SQLException {
        acquireReference();
        try {
            SQLiteStatement sQLiteStatement = new SQLiteStatement(this, str, objArr);
            try {
                sQLiteStatement.executeRaw();
            } finally {
                sQLiteStatement.close();
            }
        } finally {
            releaseReference();
        }
    }

    private int executeSql(String str, Object[] objArr) throws SQLException {
        boolean z;
        acquireReference();
        try {
            if (DatabaseUtils.getSqlStatementType(str) == 3) {
                synchronized (this.mLock) {
                    if (this.mHasAttachedDbsLocked) {
                        z = false;
                    } else {
                        z = true;
                        this.mHasAttachedDbsLocked = true;
                    }
                }
                if (z) {
                    disableWriteAheadLogging();
                }
            }
            SQLiteStatement sQLiteStatement = new SQLiteStatement(this, str, objArr);
            try {
                return sQLiteStatement.executeUpdateDelete();
            } finally {
                sQLiteStatement.close();
            }
        } finally {
            releaseReference();
        }
    }

    public void validateSql(String str, CancellationSignal cancellationSignal) {
        getThreadSession().prepare(str, getThreadDefaultConnectionFlags(true), cancellationSignal, null);
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public boolean isReadOnly() {
        boolean isReadOnlyLocked;
        synchronized (this.mLock) {
            isReadOnlyLocked = isReadOnlyLocked();
        }
        return isReadOnlyLocked;
    }

    private boolean isReadOnlyLocked() {
        return (this.mConfigurationLocked.openFlags & 1) == 1;
    }

    public boolean isInMemoryDatabase() {
        boolean isInMemoryDb;
        synchronized (this.mLock) {
            isInMemoryDb = this.mConfigurationLocked.isInMemoryDb();
        }
        return isInMemoryDb;
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public boolean isOpen() {
        boolean z;
        synchronized (this.mLock) {
            z = this.mConnectionPoolLocked != null;
        }
        return z;
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public boolean needUpgrade(int i) {
        return i > getVersion();
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public final String getPath() {
        String str;
        synchronized (this.mLock) {
            str = this.mConfigurationLocked.path;
        }
        return str;
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public void setLocale(Locale locale) {
        if (locale == null) {
            throw new IllegalArgumentException("locale must not be null.");
        }
        synchronized (this.mLock) {
            throwIfNotOpenLocked();
            Locale locale2 = this.mConfigurationLocked.locale;
            this.mConfigurationLocked.locale = locale;
            try {
                this.mConnectionPoolLocked.reconfigure(this.mConfigurationLocked);
            } catch (RuntimeException e) {
                this.mConfigurationLocked.locale = locale2;
                throw e;
            }
        }
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public void setMaxSqlCacheSize(int i) {
        if (i > 100 || i < 0) {
            throw new IllegalStateException("expected value between 0 and 100");
        }
        synchronized (this.mLock) {
            throwIfNotOpenLocked();
            int i2 = this.mConfigurationLocked.maxSqlCacheSize;
            this.mConfigurationLocked.maxSqlCacheSize = i;
            try {
                this.mConnectionPoolLocked.reconfigure(this.mConfigurationLocked);
            } catch (RuntimeException e) {
                this.mConfigurationLocked.maxSqlCacheSize = i2;
                throw e;
            }
        }
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public void setForeignKeyConstraintsEnabled(boolean z) {
        synchronized (this.mLock) {
            throwIfNotOpenLocked();
            if (this.mConfigurationLocked.foreignKeyConstraintsEnabled == z) {
                return;
            }
            this.mConfigurationLocked.foreignKeyConstraintsEnabled = z;
            try {
                this.mConnectionPoolLocked.reconfigure(this.mConfigurationLocked);
            } catch (RuntimeException e) {
                this.mConfigurationLocked.foreignKeyConstraintsEnabled = !z;
                throw e;
            }
        }
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public boolean enableWriteAheadLogging() {
        synchronized (this.mLock) {
            throwIfNotOpenLocked();
            if ((this.mConfigurationLocked.openFlags & 536870912) != 0) {
                return true;
            }
            if (isReadOnlyLocked()) {
                return false;
            }
            if (this.mConfigurationLocked.isInMemoryDb()) {
                Log.i(TAG, "can't enable WAL for memory databases.");
                return false;
            }
            if (this.mHasAttachedDbsLocked) {
                if (Log.isLoggable(TAG, 3)) {
                    Log.d(TAG, "this database: " + this.mConfigurationLocked.label + " has attached databases. can't  enable WAL.");
                }
                return false;
            }
            SQLiteDatabaseConfiguration sQLiteDatabaseConfiguration = this.mConfigurationLocked;
            sQLiteDatabaseConfiguration.openFlags = 536870912 | sQLiteDatabaseConfiguration.openFlags;
            try {
                this.mConnectionPoolLocked.reconfigure(this.mConfigurationLocked);
                return true;
            } catch (RuntimeException e) {
                this.mConfigurationLocked.openFlags &= -536870913;
                throw e;
            }
        }
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public void disableWriteAheadLogging() {
        synchronized (this.mLock) {
            throwIfNotOpenLocked();
            if ((this.mConfigurationLocked.openFlags & 536870912) == 0) {
                return;
            }
            this.mConfigurationLocked.openFlags &= -536870913;
            try {
                this.mConnectionPoolLocked.reconfigure(this.mConfigurationLocked);
            } catch (RuntimeException e) {
                SQLiteDatabaseConfiguration sQLiteDatabaseConfiguration = this.mConfigurationLocked;
                sQLiteDatabaseConfiguration.openFlags = 536870912 | sQLiteDatabaseConfiguration.openFlags;
                throw e;
            }
        }
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public boolean isWriteAheadLoggingEnabled() {
        boolean z;
        synchronized (this.mLock) {
            throwIfNotOpenLocked();
            z = (this.mConfigurationLocked.openFlags & 536870912) != 0;
        }
        return z;
    }

    static ArrayList<SQLiteDebug.DbStats> getDbStats() {
        ArrayList<SQLiteDebug.DbStats> arrayList = new ArrayList<>();
        Iterator<SQLiteDatabase> it = getActiveDatabases().iterator();
        while (it.hasNext()) {
            it.next().collectDbStats(arrayList);
        }
        return arrayList;
    }

    private void collectDbStats(ArrayList<SQLiteDebug.DbStats> arrayList) {
        synchronized (this.mLock) {
            SQLiteConnectionPool sQLiteConnectionPool = this.mConnectionPoolLocked;
            if (sQLiteConnectionPool != null) {
                sQLiteConnectionPool.collectDbStats(arrayList);
            }
        }
    }

    private static ArrayList<SQLiteDatabase> getActiveDatabases() {
        ArrayList<SQLiteDatabase> arrayList = new ArrayList<>();
        synchronized (sActiveDatabases) {
            arrayList.addAll(sActiveDatabases.keySet());
        }
        return arrayList;
    }

    static void dumpAll(Printer printer, boolean z) {
        Iterator<SQLiteDatabase> it = getActiveDatabases().iterator();
        while (it.hasNext()) {
            it.next().dump(printer, z);
        }
    }

    private void dump(Printer printer, boolean z) {
        synchronized (this.mLock) {
            if (this.mConnectionPoolLocked != null) {
                printer.println("");
                this.mConnectionPoolLocked.dump(printer, z);
            }
        }
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public List<Pair<String, String>> getAttachedDbs() {
        ArrayList arrayList = new ArrayList();
        synchronized (this.mLock) {
            Cursor cursor = null;
            if (this.mConnectionPoolLocked == null) {
                return null;
            }
            if (!this.mHasAttachedDbsLocked) {
                arrayList.add(new Pair("main", this.mConfigurationLocked.path));
                return arrayList;
            }
            acquireReference();
            try {
                try {
                    cursor = rawQuery("pragma database_list;", (String[]) null);
                    while (cursor.moveToNext()) {
                        arrayList.add(new Pair(cursor.getString(1), cursor.getString(2)));
                    }
                    return arrayList;
                } finally {
                    if (cursor != null) {
                        cursor.close();
                    }
                }
            } finally {
                releaseReference();
            }
        }
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public boolean isDatabaseIntegrityOk() {
        List<Pair<String, String>> arrayList;
        acquireReference();
        try {
            try {
                arrayList = getAttachedDbs();
            } catch (SQLiteException unused) {
                arrayList = new ArrayList<>();
                arrayList.add(new Pair<>("main", getPath()));
            }
            if (arrayList == null) {
                throw new IllegalStateException("databaselist for: " + getPath() + " couldn't be retrieved. probably because the database is closed");
            }
            for (int i = 0; i < arrayList.size(); i++) {
                Pair<String, String> pair = arrayList.get(i);
                SQLiteStatement sQLiteStatement = null;
                try {
                    sQLiteStatement = compileStatement("PRAGMA " + ((String) pair.first) + ".integrity_check(1);");
                    String simpleQueryForString = sQLiteStatement.simpleQueryForString();
                    if (!simpleQueryForString.equalsIgnoreCase("ok")) {
                        Log.e(TAG, "PRAGMA integrity_check on " + ((String) pair.second) + " returned: " + simpleQueryForString);
                        return false;
                    }
                    if (sQLiteStatement != null) {
                        sQLiteStatement.close();
                    }
                } finally {
                    if (sQLiteStatement != null) {
                        sQLiteStatement.close();
                    }
                }
            }
            releaseReference();
            return true;
        } finally {
            releaseReference();
        }
    }

    public void changePassword(String str) {
        changePassword(getBytes(str));
    }

    public void changePassword(byte[] bArr) {
        synchronized (this.mLock) {
            throwIfNotOpenLocked();
            if (isReadOnlyLocked()) {
                throw new IllegalStateException("Can't change password for readonly databases.");
            }
            if (this.mConfigurationLocked.isInMemoryDb()) {
                throw new IllegalStateException("Can't change password for in-memory databases.");
            }
            byte[] bArr2 = this.mConfigurationLocked.password;
            this.mConfigurationLocked.password = bArr;
            try {
                this.mConnectionPoolLocked.reconfigure(this.mConfigurationLocked);
            } catch (RuntimeException e) {
                this.mConfigurationLocked.password = bArr2;
                throw e;
            }
        }
    }

    public String toString() {
        return "SQLiteDatabase: " + getPath();
    }

    private void throwIfNotOpenLocked() {
        if (this.mConnectionPoolLocked != null) {
            return;
        }
        throw new IllegalStateException("The database '" + this.mConfigurationLocked.label + "' is not open.");
    }

    public static boolean hasCodec() {
        return SQLiteConnection.hasCodec();
    }

    public void enableLocalizedCollators() {
        this.mConnectionPoolLocked.enableLocalizedCollators();
    }

    private static byte[] getBytes(String str) {
        return (str == null || str.length() == 0) ? new byte[0] : str.getBytes(Charset.forName("UTF-8"));
    }
}
