package com.huawei.hwdevice.phoneprocess.mgr.hwcontactsyncmgr.contacts.sync.db;

import com.huawei.hwlogsmodel.LogUtil;
import net.zetetic.database.sqlcipher.SQLiteDatabase;

/* loaded from: classes5.dex */
public abstract class RunOnTransaction {
    private static final String TAG = "RunOnTransaction";
    private SQLiteDatabase mDatabase;

    public abstract void run();

    public RunOnTransaction(SQLiteDatabase sQLiteDatabase) {
        this.mDatabase = sQLiteDatabase;
    }

    public void start() {
        if (this.mDatabase == null) {
            LogUtil.h(TAG, "mDatabase is null.");
        } else {
            transaction();
        }
    }

    private void transaction() {
        this.mDatabase.beginTransaction();
        try {
            run();
            this.mDatabase.setTransactionSuccessful();
        } finally {
            this.mDatabase.endTransaction();
        }
    }
}
