package com.huawei.wearengine.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import android.util.LruCache;
import com.huawei.pluginachievement.manager.model.MedalConstants;
import com.huawei.wearengine.auth.AuthInfo;
import com.huawei.wearengine.repository.api.AuthInfoRepository;
import defpackage.tos;
import defpackage.tot;
import defpackage.tqh;
import defpackage.tqj;
import defpackage.tri;
import defpackage.trm;
import defpackage.trp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes8.dex */
public class AuthInfoRepositoryImpl implements AuthInfoRepository {
    private static final int APPID_INDEX = 2;
    private static final int APPUID_INDEX = 0;
    private static final int CACHE_SIZE = 5;
    private static final String TAG = "AuthInfoRepositoryImpl";
    private static final int THREE_LENGTH = 3;
    private static final int USERID_INDEX = 1;
    private LruCache<String, List<AuthInfo>> authInfoCache = new LruCache<>(5);
    private Context mContext;
    private tqh mDbCommon;

    public AuthInfoRepositoryImpl(Context context) {
        this.mContext = context;
        this.mDbCommon = new tqh(context, "tb_wear_engine_auth_info", tqj.c());
    }

    @Override // com.huawei.wearengine.repository.api.AuthInfoRepository
    public boolean insertAuth(AuthInfo authInfo) {
        ContentValues buildAuthInfoContentValues = buildAuthInfoContentValues(authInfo);
        if (buildAuthInfoContentValues == null) {
            return false;
        }
        tos.b(TAG, "insertAuth contentValues:" + buildAuthInfoContentValues.toString());
        return this.mDbCommon.fex_(buildAuthInfoContentValues) > 0;
    }

    @Override // com.huawei.wearengine.repository.api.AuthInfoRepository
    public boolean deleteAuth(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return this.mDbCommon.a("key =? ", new String[]{str}) > 0;
    }

    @Override // com.huawei.wearengine.repository.api.AuthInfoRepository
    public boolean deleteAuth(int i, String str, int i2) {
        tos.b(TAG, "deleteAuth userId: " + str);
        tos.b(TAG, "deleteAuth appId: " + i2);
        tos.b(TAG, "deleteAuth appUid: " + i);
        return this.mDbCommon.a(getAuthSqlSelection(), getAuthSqlSelectionArgs(i, str, i2)) > 0;
    }

    @Override // com.huawei.wearengine.repository.api.AuthInfoRepository
    public void deleteAuthFromCache(String str) {
        tos.b(TAG, "deleteAuthFromCache packageName: " + str);
        Context a2 = tot.a();
        String keyByParams = getKeyByParams(tri.d(a2, str), tri.a(a2), tri.j(a2, str));
        tos.b(TAG, "deleteAuthFromCache with key: " + keyByParams);
        this.authInfoCache.remove(keyByParams);
    }

    @Override // com.huawei.wearengine.repository.api.AuthInfoRepository
    public void updateCache(String str) {
        tos.b(TAG, "updateCache packageName: " + str);
        Context a2 = tot.a();
        int d = tri.d(a2, str);
        String a3 = tri.a(a2);
        int j = tri.j(a2, str);
        String keyByParams = getKeyByParams(d, a3, j);
        tos.b(TAG, "updateCache with key: " + keyByParams);
        this.authInfoCache.put(keyByParams, getAuthFromDataBase(d, a3, j));
    }

    @Override // com.huawei.wearengine.repository.api.AuthInfoRepository
    public List<AuthInfo> getAuth(int i, String str, int i2) {
        tos.b(TAG, "getAuth appUid: " + i);
        tos.b(TAG, "getAuth userId: " + str);
        tos.b(TAG, "getAuth appId: " + i2);
        List<AuthInfo> list = this.authInfoCache.get(getKeyByParams(i, str, i2));
        if (list != null && list.size() > 0) {
            return list;
        }
        List<AuthInfo> authFromDataBase = getAuthFromDataBase(i, str, i2);
        this.authInfoCache.put(getKeyByParams(i, str, i2), authFromDataBase);
        return authFromDataBase;
    }

    private String getKeyByParams(int i, String str, int i2) {
        return i + str + i2;
    }

    private List<AuthInfo> getAuthFromDataBase(int i, String str, int i2) {
        tos.b(TAG, "getAuthFromDataBase");
        return parseAuthInfo(this.mContext, this.mDbCommon.fey_(getAuthSqlSelection(), getAuthSqlSelectionArgs(i, str, i2), null, null, null));
    }

    private String getAuthSqlSelection() {
        tos.b(TAG, "sbSelector: app_uid =?  and user_id =?  and app_id =? ");
        return "app_uid =?  and user_id =?  and app_id =? ";
    }

    private String[] getAuthSqlSelectionArgs(int i, String str, int i2) {
        String[] strArr = {String.valueOf(i), str, String.valueOf(i2)};
        tos.b(TAG, "selectAgs: " + Arrays.toString(strArr));
        return strArr;
    }

    @Override // com.huawei.wearengine.repository.api.AuthInfoRepository
    public boolean updateAuth(AuthInfo authInfo) {
        if (authInfo == null) {
            return false;
        }
        tos.b(TAG, "updateAuth authInfo: " + authInfo.toString());
        String[] strArr = {authInfo.getKey()};
        List<AuthInfo> parseAuthInfo = parseAuthInfo(this.mContext, this.mDbCommon.fey_("key =? ", strArr, null, null, null));
        ContentValues buildAuthInfoContentValues = buildAuthInfoContentValues(authInfo);
        if (buildAuthInfoContentValues == null) {
            return false;
        }
        if (parseAuthInfo.isEmpty()) {
            tos.b(TAG, "updateAuth insert contentValues:" + buildAuthInfoContentValues.toString());
            return this.mDbCommon.fex_(buildAuthInfoContentValues) > 0;
        }
        tos.b(TAG, "authInfoList:" + parseAuthInfo.toString());
        tos.b(TAG, "updateAuth update contentValues:" + buildAuthInfoContentValues.toString());
        return this.mDbCommon.fez_(buildAuthInfoContentValues, "key =? ", strArr) > 0;
    }

    public static ContentValues buildAuthInfoContentValues(AuthInfo authInfo) {
        if (authInfo == null) {
            return null;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(MedalConstants.EVENT_KEY, authInfo.getKey());
        contentValues.put("app_uid", Integer.valueOf(authInfo.getAppUid()));
        contentValues.put("user_id", authInfo.getUserId());
        contentValues.put("app_id", Integer.valueOf(authInfo.getAppId()));
        contentValues.put("permission", authInfo.getPermission());
        contentValues.put("open_status", Integer.valueOf(authInfo.getOpenStatus()));
        contentValues.put("encrypted_string", trm.c(authInfo.toString(), trp.d()));
        return contentValues;
    }

    public static List<AuthInfo> parseAuthInfo(Context context, Cursor cursor) {
        if (cursor == null) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        while (cursor.moveToNext()) {
            try {
                tos.b(TAG, "parseAuthInfo cursor.moveToNext()");
                AuthInfo authInfo = new AuthInfo();
                authInfo.setKey(cursor.getString(cursor.getColumnIndex(MedalConstants.EVENT_KEY)));
                authInfo.setAppUid(cursor.getInt(cursor.getColumnIndex("app_uid")));
                authInfo.setUserId(cursor.getString(cursor.getColumnIndex("user_id")));
                authInfo.setAppId(cursor.getInt(cursor.getColumnIndex("app_id")));
                authInfo.setPermission(cursor.getString(cursor.getColumnIndex("permission")));
                authInfo.setOpenStatus(cursor.getInt(cursor.getColumnIndex("open_status")));
                if (isModified(context, authInfo.toString(), authInfo.getKey(), cursor.getString(cursor.getColumnIndex("encrypted_string")))) {
                    authInfo.setOpenStatus(0);
                }
                arrayList.add(authInfo);
            } finally {
                cursor.close();
            }
        }
        return arrayList;
    }

    private static boolean isModified(Context context, String str, String str2, String str3) {
        tos.b(TAG, "isModified currentString:" + str);
        return (str3.equals(trm.c(str, trp.d())) || str3.equals(trm.a(str, str2)) || str.equals(trp.e(context, str3, str2))) ? false : true;
    }
}
