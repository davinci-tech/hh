package net.zetetic.database.sqlcipher;

/* loaded from: classes2.dex */
public interface SQLiteDatabaseHook {
    void postKey(SQLiteConnection sQLiteConnection);

    void preKey(SQLiteConnection sQLiteConnection);
}
