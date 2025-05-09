package com.huawei.ui.thirdpartservice.interactors.thirdpartyservice.qqhealth.mgr;

import android.content.ContentValues;
import android.database.Cursor;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes8.dex */
public class QqHealthDb {
    private static final String COLUMN_EXPIRES_IN = "expires_in";
    private static final String COLUMN_NICK_NAME = "nickname";
    private static final String COLUMN_OPEN_ID = "openid";
    private static final String COLUMN_QQ_LOGO_PATH = "qqlogopath";
    private static final String COLUMN_SPLIT = ",";
    private static final String COLUMN_TOKEN = "token";
    private static final String DATABASE_TABLE = "qqhealth";
    public static final int INVALID_VALUE = -1;
    private static final String MAX_CHAR_LENGTH = " varchar(3000)";
    private static final String TAG = "QqHealthDb";
    private static String sCreateTableSql;
    private QqHealthMgr mQqHealthMgr;

    static {
        StringBuilder sb = new StringBuilder(16);
        sb.append("openid varchar(3000),token varchar(3000),expires_in varchar(3000),nickname varchar(3000),qqlogopath varchar(3000)");
        sCreateTableSql = sb.toString();
    }

    public QqHealthDb() {
        QqHealthMgr qqHealthMgr = QqHealthMgr.getInstance();
        this.mQqHealthMgr = qqHealthMgr;
        qqHealthMgr.create(DATABASE_TABLE, 1, sCreateTableSql);
    }

    public long insert(QqHealthTable qqHealthTable) {
        if (qqHealthTable == null) {
            LogUtil.a(TAG, "insert() healthTable == null");
            return -1L;
        }
        long insert = this.mQqHealthMgr.insert(DATABASE_TABLE, 1, getContentValues(qqHealthTable));
        LogUtil.a(TAG, "insert() result = ", Long.valueOf(insert));
        return insert;
    }

    public int delete() {
        int delete = this.mQqHealthMgr.delete(DATABASE_TABLE, 1, null);
        LogUtil.a(TAG, "delete() result = ", Integer.valueOf(delete));
        return delete;
    }

    private ContentValues getContentValues(QqHealthTable qqHealthTable) {
        ContentValues contentValues = new ContentValues();
        if (qqHealthTable != null) {
            contentValues.put("openid", qqHealthTable.getOpenId());
            contentValues.put("token", qqHealthTable.getToken());
            contentValues.put("expires_in", qqHealthTable.getExpireTime());
            contentValues.put(COLUMN_NICK_NAME, qqHealthTable.getNickName());
            contentValues.put(COLUMN_QQ_LOGO_PATH, qqHealthTable.getQqLogoPath());
        }
        LogUtil.c(TAG, "initialValues = ", contentValues);
        return contentValues;
    }

    public int update(QqHealthTable qqHealthTable) {
        if (qqHealthTable == null) {
            LogUtil.a(TAG, "update() healthTable == null");
            return -1;
        }
        int update = this.mQqHealthMgr.update(DATABASE_TABLE, 1, getContentValues(qqHealthTable), "token=" + qqHealthTable.getToken() + " and openid like '" + qqHealthTable.getOpenId() + "'");
        LogUtil.a(TAG, "update() result = ", Integer.valueOf(update));
        return update;
    }

    public QqHealthTable get() {
        QqHealthTable qqHealthTable = new QqHealthTable();
        Cursor queryStorageData = this.mQqHealthMgr.queryStorageData(DATABASE_TABLE, 1, null);
        if (queryStorageData != null) {
            try {
                if (queryStorageData.getCount() != 0) {
                    if (queryStorageData.moveToFirst()) {
                        qqHealthTable.setOpenId(queryStorageData.getString(queryStorageData.getColumnIndex("openid")));
                        qqHealthTable.setToken(queryStorageData.getString(queryStorageData.getColumnIndex("token")));
                        qqHealthTable.setExpireTime(queryStorageData.getString(queryStorageData.getColumnIndex("expires_in")));
                        qqHealthTable.setNickName(queryStorageData.getString(queryStorageData.getColumnIndex(COLUMN_NICK_NAME)));
                        qqHealthTable.setQqLogoPath(queryStorageData.getString(queryStorageData.getColumnIndex(COLUMN_QQ_LOGO_PATH)));
                    }
                    if (queryStorageData != null) {
                        queryStorageData.close();
                    }
                    return qqHealthTable;
                }
            } catch (Throwable th) {
                if (queryStorageData != null) {
                    try {
                        queryStorageData.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        }
        LogUtil.a(TAG, "get() query failed");
        if (queryStorageData != null) {
            queryStorageData.close();
        }
        return null;
    }
}
