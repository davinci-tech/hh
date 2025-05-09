package net.zetetic.database.sqlcipher;

import android.os.StatFs;

/* loaded from: classes7.dex */
public final class SQLiteGlobal {
    private static final String TAG = "SQLiteGlobal";
    private static int sDefaultPageSize = 4096;
    private static final Object sLock = new Object();

    public static int getJournalSizeLimit() {
        return 10000;
    }

    private static native int nativeReleaseMemory();

    private SQLiteGlobal() {
    }

    public static int releaseMemory() {
        return nativeReleaseMemory();
    }

    public static int getDefaultPageSize() {
        synchronized (sLock) {
            if (sDefaultPageSize == 0) {
                sDefaultPageSize = new StatFs("/data").getBlockSize();
            }
        }
        return 4096;
    }

    public static int getWALAutoCheckpoint() {
        return Math.max(1, 1000);
    }

    public static int getWALConnectionPoolSize() {
        return Math.max(2, 10);
    }

    public static String getWALSyncMode() {
        return "normal";
    }

    public static String getDefaultSyncMode() {
        return "normal";
    }

    public static String getDefaultJournalMode() {
        return "delete";
    }
}
