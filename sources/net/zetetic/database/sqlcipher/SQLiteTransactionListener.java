package net.zetetic.database.sqlcipher;

/* loaded from: classes7.dex */
public interface SQLiteTransactionListener {
    void onBegin();

    void onCommit();

    void onRollback();
}
