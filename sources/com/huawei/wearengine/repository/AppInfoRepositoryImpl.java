package com.huawei.wearengine.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import com.huawei.pluginachievement.manager.model.MedalConstants;
import com.huawei.wearengine.auth.HiAppInfo;
import com.huawei.wearengine.repository.api.AppInfoRepository;
import com.tencent.open.SocialOperation;
import defpackage.tos;
import defpackage.tqh;
import defpackage.tqi;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public class AppInfoRepositoryImpl implements AppInfoRepository {
    private static final String TAG = "AppInfoRepositoryImpl";
    private tqh mDbCommon;

    public AppInfoRepositoryImpl(Context context) {
        this.mDbCommon = new tqh(context, "tb_wear_engine_app_info", tqi.e());
    }

    @Override // com.huawei.wearengine.repository.api.AppInfoRepository
    public boolean insertAppInfo(HiAppInfo hiAppInfo) {
        ContentValues buildHiAppInfoContentValues = buildHiAppInfoContentValues(hiAppInfo);
        return buildHiAppInfoContentValues != null && this.mDbCommon.fex_(buildHiAppInfoContentValues) > 0;
    }

    @Override // com.huawei.wearengine.repository.api.AppInfoRepository
    public boolean deleteAppInfo(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return this.mDbCommon.a("key =? ", new String[]{str}) > 0;
    }

    @Override // com.huawei.wearengine.repository.api.AppInfoRepository
    public HiAppInfo getHiAppInfo(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return parseHiAppInfo(this.mDbCommon.fey_("key =? ", new String[]{str}, null, null, null));
        } catch (Exception unused) {
            tos.e(TAG, "query Exception");
            return null;
        }
    }

    @Override // com.huawei.wearengine.repository.api.AppInfoRepository
    public List<HiAppInfo> getAllHiAppInfo() {
        try {
            return parseHiAppInfoList(this.mDbCommon.fey_(null, null, null, null, null));
        } catch (Exception unused) {
            tos.e(TAG, "query Exception");
            return null;
        }
    }

    private ContentValues buildHiAppInfoContentValues(HiAppInfo hiAppInfo) {
        if (hiAppInfo == null) {
            return null;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(MedalConstants.EVENT_KEY, hiAppInfo.getKey());
        contentValues.put("app_uid", Integer.valueOf(hiAppInfo.getAppUid()));
        contentValues.put("package_name", hiAppInfo.getPackageName());
        contentValues.put("app_id", Integer.valueOf(hiAppInfo.getAppId()));
        contentValues.put("user_id", hiAppInfo.getUserId());
        contentValues.put("app_name", hiAppInfo.getAppName());
        contentValues.put("app_icon", hiAppInfo.getByteDraw());
        contentValues.put(SocialOperation.GAME_SIGNATURE, hiAppInfo.getSignature());
        contentValues.put("version", hiAppInfo.getVersion());
        contentValues.put("cloud_code", Long.valueOf(hiAppInfo.getCloudCode()));
        contentValues.put("create_time", Long.valueOf(System.currentTimeMillis()));
        return contentValues;
    }

    private HiAppInfo parseHiAppInfo(Cursor cursor) {
        HiAppInfo hiAppInfo = null;
        if (cursor == null) {
            return null;
        }
        while (cursor.moveToNext()) {
            try {
                hiAppInfo = queryHiAppInfo(cursor);
            } finally {
                cursor.close();
            }
        }
        return hiAppInfo;
    }

    private List<HiAppInfo> parseHiAppInfoList(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        while (cursor.moveToNext()) {
            try {
                arrayList.add(queryHiAppInfo(cursor));
            } finally {
                cursor.close();
            }
        }
        return arrayList;
    }

    private HiAppInfo queryHiAppInfo(Cursor cursor) {
        HiAppInfo hiAppInfo = new HiAppInfo();
        hiAppInfo.setKey(cursor.getString(cursor.getColumnIndex(MedalConstants.EVENT_KEY)));
        hiAppInfo.setAppUid(cursor.getInt(cursor.getColumnIndex("app_uid")));
        hiAppInfo.setPackageName(cursor.getString(cursor.getColumnIndex("package_name")));
        hiAppInfo.setAppId(cursor.getInt(cursor.getColumnIndex("app_id")));
        hiAppInfo.setUserId(cursor.getString(cursor.getColumnIndex("user_id")));
        hiAppInfo.setAppName(cursor.getString(cursor.getColumnIndex("app_name")));
        hiAppInfo.setByteDraw(cursor.getBlob(cursor.getColumnIndex("app_icon")));
        hiAppInfo.setSignature(cursor.getString(cursor.getColumnIndex(SocialOperation.GAME_SIGNATURE)));
        hiAppInfo.setCloudCode(cursor.getLong(cursor.getColumnIndex("cloud_code")));
        hiAppInfo.setVersion(cursor.getString(cursor.getColumnIndex("version")));
        return hiAppInfo;
    }
}
