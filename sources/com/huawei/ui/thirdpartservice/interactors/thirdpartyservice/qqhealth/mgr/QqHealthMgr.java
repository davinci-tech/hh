package com.huawei.ui.thirdpartservice.interactors.thirdpartyservice.qqhealth.mgr;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.text.TextUtils;
import com.huawei.hwbasemgr.HwBaseManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.LocalBroadcast;

/* loaded from: classes8.dex */
public class QqHealthMgr extends HwBaseManager {
    private static final String TAG = "QqHealthMgr";
    private Context mContext;

    private QqHealthMgr(Context context) {
        super(context);
        this.mContext = context;
    }

    static class Instance {
        private static final QqHealthMgr INSTANCE = new QqHealthMgr(BaseApplication.getContext());

        private Instance() {
        }
    }

    public static QqHealthMgr getInstance() {
        return Instance.INSTANCE;
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public Integer getModuleId() {
        return 10002;
    }

    public void create(String str, int i, String str2) {
        LogUtil.a(TAG, "enter create():");
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.a(TAG, "create tableId isEmpty or sql isEmpty");
        } else {
            createStorageDataTable(str, i, str2);
        }
    }

    public long insert(String str, int i, ContentValues contentValues) {
        if (TextUtils.isEmpty(str) || contentValues == null) {
            LogUtil.a(TAG, "insert tableId isEmpty or values == null");
            return -1L;
        }
        long insertStorageData = insertStorageData(str, i, contentValues);
        LogUtil.a(TAG, "insert result = ", Long.valueOf(insertStorageData));
        return insertStorageData;
    }

    public int delete(String str, int i, String str2) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a(TAG, "delete tableId isEmpty or values == null");
            return -1;
        }
        int deleteStorageData = deleteStorageData(str, i, str2);
        LogUtil.a(TAG, "delete() result = ", Integer.valueOf(deleteStorageData));
        return deleteStorageData;
    }

    public int update(String str, int i, ContentValues contentValues, String str2) {
        if (TextUtils.isEmpty(str) || contentValues == null) {
            LogUtil.a(TAG, "update tableId isEmpty or values == null");
            return -1;
        }
        int updateStorageData = updateStorageData(str, i, contentValues, str2);
        LogUtil.a(TAG, "update() result = ", Integer.valueOf(updateStorageData));
        return updateStorageData;
    }

    public Cursor get(String str, int i, String str2) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a(TAG, "get tableId isEmpty");
            return null;
        }
        LogUtil.a(TAG, "enter get():");
        return queryStorageData(str, i, str2);
    }

    public void sendQqHealthConnectSuccessBroadcast() {
        LogUtil.a(TAG, "enter sendQqHealthConnectSuccessBroadcast().");
        Intent intent = new Intent("com.huawei.bone.action.QQ_HEALTH_CONNECT_SUCCESS");
        intent.setPackage(BaseApplication.getContext().getPackageName());
        this.mContext.sendBroadcast(intent, LocalBroadcast.c);
    }
}
