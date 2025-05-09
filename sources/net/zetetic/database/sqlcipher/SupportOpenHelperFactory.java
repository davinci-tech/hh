package net.zetetic.database.sqlcipher;

import androidx.sqlite.db.SupportSQLiteOpenHelper;

/* loaded from: classes10.dex */
public class SupportOpenHelperFactory implements SupportSQLiteOpenHelper.Factory {
    private static final int UNCHANGED = -1;
    private final boolean enableWriteAheadLogging;
    private final SQLiteDatabaseHook hook;
    private final int minimumSupportedVersion;
    private final byte[] password;

    public SupportOpenHelperFactory(byte[] bArr) {
        this(bArr, null, false);
    }

    public SupportOpenHelperFactory(byte[] bArr, SQLiteDatabaseHook sQLiteDatabaseHook, boolean z) {
        this(bArr, sQLiteDatabaseHook, z, -1);
    }

    public SupportOpenHelperFactory(byte[] bArr, SQLiteDatabaseHook sQLiteDatabaseHook, boolean z, int i) {
        this.password = bArr;
        this.hook = sQLiteDatabaseHook;
        this.enableWriteAheadLogging = z;
        this.minimumSupportedVersion = i;
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper.Factory
    public SupportSQLiteOpenHelper create(SupportSQLiteOpenHelper.Configuration configuration) {
        int i = this.minimumSupportedVersion;
        if (i == -1) {
            return new SupportHelper(configuration, this.password, this.hook, this.enableWriteAheadLogging);
        }
        return new SupportHelper(configuration, this.password, this.hook, this.enableWriteAheadLogging, i);
    }
}
