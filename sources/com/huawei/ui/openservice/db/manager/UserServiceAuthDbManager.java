package com.huawei.ui.openservice.db.manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import com.huawei.ui.openservice.db.model.UserServiceAuth;
import com.huawei.ui.openservice.db.model.UserServiceAuthTable;

/* loaded from: classes7.dex */
public class UserServiceAuthDbManager {
    private OpenServiceDbCommon dbCommon;

    public UserServiceAuthDbManager(Context context) {
        this.dbCommon = new OpenServiceDbCommon(context.getApplicationContext(), UserServiceAuthTable.TABLE_NAME, UserServiceAuthTable.getColumns());
    }

    public boolean insertOrUpdate(UserServiceAuth userServiceAuth) {
        return insertOrUpdateSync(userServiceAuth);
    }

    private boolean insertOrUpdateSync(UserServiceAuth userServiceAuth) {
        synchronized (this) {
            if (userServiceAuth != null) {
                if (userServiceAuth.getServiceID() != null && userServiceAuth.getHuid() != null) {
                    if (queryAuth(userServiceAuth.getHuid(), userServiceAuth.getServiceID()) == null) {
                        return insertAuth(userServiceAuth);
                    }
                    return updateAuth(userServiceAuth);
                }
            }
            return false;
        }
    }

    public UserServiceAuth queryAuth(String str, String str2) {
        if (str == null || str2 == null) {
            return null;
        }
        return parseServiceAuth(this.dbCommon.query("huid =? and service_id =? ", new String[]{str, str2}, null, null, null));
    }

    private boolean insertAuth(UserServiceAuth userServiceAuth) {
        return this.dbCommon.insert(buildUserServiceAuthValues(userServiceAuth)) > 0;
    }

    private boolean updateAuth(UserServiceAuth userServiceAuth) {
        return this.dbCommon.update(buildUserServiceAuthValues(userServiceAuth), "huid =? and service_id =? ", new String[]{userServiceAuth.getHuid(), userServiceAuth.getServiceID()}) > 0;
    }

    private ContentValues buildUserServiceAuthValues(UserServiceAuth userServiceAuth) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("huid", userServiceAuth.getHuid());
        contentValues.put("service_id", userServiceAuth.getServiceID());
        contentValues.put(UserServiceAuthTable.COLUMN_SERVICE_AUTH, Integer.valueOf(userServiceAuth.fetchAuthType()));
        contentValues.put("modify_time", Long.valueOf(System.currentTimeMillis()));
        return contentValues;
    }

    private UserServiceAuth parseServiceAuth(Cursor cursor) {
        UserServiceAuth userServiceAuth = null;
        if (cursor == null) {
            return null;
        }
        try {
            if (cursor.moveToNext()) {
                userServiceAuth = new UserServiceAuth();
                userServiceAuth.configHuid(cursor.getString(cursor.getColumnIndex("huid")));
                userServiceAuth.configAuthType(cursor.getInt(cursor.getColumnIndex(UserServiceAuthTable.COLUMN_SERVICE_AUTH)));
                userServiceAuth.configServiceID(cursor.getString(cursor.getColumnIndex("service_id")));
            }
            return userServiceAuth;
        } finally {
            cursor.close();
        }
    }
}
