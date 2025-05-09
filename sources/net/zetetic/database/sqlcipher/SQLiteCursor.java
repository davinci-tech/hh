package net.zetetic.database.sqlcipher;

import android.database.AbstractWindowedCursor;
import android.database.CursorWindow;
import android.os.Build;
import android.util.Log;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import net.zetetic.database.DatabaseUtils;

/* loaded from: classes7.dex */
public class SQLiteCursor extends AbstractWindowedCursor {
    private static final int CURSOR_WINDOW_EXTRA = 512;
    private static boolean CURSOR_WINDOW_NEEDS_RECREATED = false;
    private static final int DEFAULT_CURSOR_WINDOW_SIZE;
    static final int NO_COUNT = -1;
    public static int PREFERRED_CURSOR_WINDOW_SIZE = 0;
    static final String TAG = "SQLiteCursor";
    private Map<String, Integer> mColumnNameMap;
    private final String[] mColumns;
    private int mCount;
    private int mCursorWindowCapacity;
    private final SQLiteCursorDriver mDriver;
    private final String mEditTable;
    private final SQLiteQuery mQuery;

    static {
        int pow = (int) (Math.pow(1024.0d, 2.0d) * 8.0d);
        DEFAULT_CURSOR_WINDOW_SIZE = pow;
        PREFERRED_CURSOR_WINDOW_SIZE = pow;
    }

    @Deprecated
    public SQLiteCursor(SQLiteDatabase sQLiteDatabase, SQLiteCursorDriver sQLiteCursorDriver, String str, SQLiteQuery sQLiteQuery) {
        this(sQLiteCursorDriver, str, sQLiteQuery);
    }

    public SQLiteCursor(SQLiteCursorDriver sQLiteCursorDriver, String str, SQLiteQuery sQLiteQuery) {
        this.mCount = -1;
        if (sQLiteQuery == null) {
            throw new IllegalArgumentException("query object cannot be null");
        }
        this.mDriver = sQLiteCursorDriver;
        this.mEditTable = str;
        this.mColumnNameMap = null;
        this.mQuery = sQLiteQuery;
        this.mColumns = sQLiteQuery.getColumnNames();
    }

    public SQLiteDatabase getDatabase() {
        return this.mQuery.getDatabase();
    }

    @Override // android.database.AbstractCursor, android.database.CrossProcessCursor
    public boolean onMove(int i, int i2) {
        if (this.mWindow != null && i2 >= this.mWindow.getStartPosition() && i2 < this.mWindow.getStartPosition() + this.mWindow.getNumRows()) {
            return true;
        }
        fillWindow(i2);
        return true;
    }

    @Override // android.database.AbstractCursor, android.database.Cursor
    public int getCount() {
        if (this.mCount == -1) {
            fillWindow(0);
        }
        return this.mCount;
    }

    public static void setCursorWindowSize(int i) {
        PREFERRED_CURSOR_WINDOW_SIZE = i;
        CURSOR_WINDOW_NEEDS_RECREATED = true;
    }

    public static void resetCursorWindowSize() {
        PREFERRED_CURSOR_WINDOW_SIZE = DEFAULT_CURSOR_WINDOW_SIZE;
        CURSOR_WINDOW_NEEDS_RECREATED = true;
    }

    private void awc_clearOrCreateWindow(String str) {
        CursorWindow cursorWindow;
        int i = PREFERRED_CURSOR_WINDOW_SIZE + 512;
        if (CURSOR_WINDOW_NEEDS_RECREATED) {
            awc_closeWindow();
            CURSOR_WINDOW_NEEDS_RECREATED = false;
        }
        CursorWindow window = getWindow();
        if (window == null) {
            if (Build.VERSION.SDK_INT >= 28) {
                cursorWindow = new CursorWindow(str, i);
            } else {
                try {
                    Field declaredField = CursorWindow.class.getDeclaredField("sCursorWindowSize");
                    if (declaredField != null) {
                        declaredField.setAccessible(true);
                        declaredField.set(null, Integer.valueOf(i));
                        Log.i(TAG, String.format("Set CursorWindow allocation size to %s", Integer.valueOf(i)));
                    }
                } catch (Exception e) {
                    Log.e(TAG, "Failed to override CursorWindow allocation size", e);
                }
                cursorWindow = new CursorWindow(str);
            }
            setWindow(cursorWindow);
            return;
        }
        window.clear();
    }

    private void awc_closeWindow() {
        setWindow(null);
    }

    private void fillWindow(int i) {
        awc_clearOrCreateWindow(getDatabase().getPath());
        try {
            if (this.mCount == -1) {
                this.mCount = this.mQuery.fillWindow(this.mWindow, DatabaseUtils.cursorPickFillWindowStartPosition(i, 0), i, true);
                this.mCursorWindowCapacity = this.mWindow.getNumRows();
                if (Log.isLoggable(TAG, 3)) {
                    Log.d(TAG, "received count(*) from native_fill_window: " + this.mCount);
                    return;
                }
                return;
            }
            this.mQuery.fillWindow(this.mWindow, DatabaseUtils.cursorPickFillWindowStartPosition(i, this.mCursorWindowCapacity), i, false);
        } catch (RuntimeException e) {
            awc_closeWindow();
            throw e;
        }
    }

    @Override // android.database.AbstractCursor, android.database.Cursor
    public int getColumnIndex(String str) {
        if (this.mColumnNameMap == null) {
            String[] strArr = this.mColumns;
            int length = strArr.length;
            HashMap hashMap = new HashMap(length, 1.0f);
            for (int i = 0; i < length; i++) {
                hashMap.put(strArr[i], Integer.valueOf(i));
            }
            this.mColumnNameMap = hashMap;
        }
        int lastIndexOf = str.lastIndexOf(46);
        if (lastIndexOf != -1) {
            Log.e(TAG, "requesting column name with table name -- " + str, new Exception());
            str = str.substring(lastIndexOf + 1);
        }
        Integer num = this.mColumnNameMap.get(str);
        if (num != null) {
            return num.intValue();
        }
        return -1;
    }

    @Override // android.database.AbstractCursor, android.database.Cursor
    public String[] getColumnNames() {
        return this.mColumns;
    }

    @Override // android.database.AbstractCursor, android.database.Cursor
    public void deactivate() {
        super.deactivate();
        this.mDriver.cursorDeactivated();
    }

    @Override // android.database.AbstractCursor, android.database.Cursor, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        super.close();
        synchronized (this) {
            this.mQuery.close();
            this.mDriver.cursorClosed();
        }
    }

    @Override // android.database.AbstractCursor, android.database.Cursor
    public boolean requery() {
        if (isClosed()) {
            return false;
        }
        synchronized (this) {
            if (!this.mQuery.getDatabase().isOpen()) {
                return false;
            }
            if (this.mWindow != null) {
                this.mWindow.clear();
            }
            this.mPos = -1;
            this.mCount = -1;
            this.mDriver.cursorRequeried(this);
            try {
                return super.requery();
            } catch (IllegalStateException e) {
                Log.w(TAG, "requery() failed " + e.getMessage(), e);
                return false;
            }
        }
    }

    @Override // android.database.AbstractWindowedCursor
    public void setWindow(CursorWindow cursorWindow) {
        super.setWindow(cursorWindow);
        this.mCount = -1;
    }

    public void setSelectionArguments(String[] strArr) {
        this.mDriver.setBindArguments(strArr);
    }

    @Override // android.database.AbstractCursor
    protected void finalize() {
        try {
            if (this.mWindow != null) {
                close();
            }
        } finally {
            super.finalize();
        }
    }
}
