package com.huawei.hwbasemgr;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.DbManager;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.io.Serializable;
import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes.dex */
public class HwBaseManager {
    public static final String CONTENT_KEY = "content";
    private static final String TAG = "HwBaseManager";
    private static Set<Integer> sModuleIdSet = new HashSet();
    private static Set<HwBaseManager> sModuleInstancesSet = new HashSet();
    private LocalBroadcastManager mBroadcastManager;
    private Context mContext;

    private Serializable getDataForBroadcast(String str) {
        return null;
    }

    protected Integer getModuleId() {
        return null;
    }

    public boolean onDataMigrate() {
        return true;
    }

    public HwBaseManager(Context context) {
        this.mContext = context;
        init();
    }

    private static Set<Integer> getModuleIdSet() {
        return sModuleIdSet;
    }

    private static Set<HwBaseManager> getModuleInstancesSet() {
        return sModuleInstancesSet;
    }

    protected Set<String> getAvailableBroadcastSet() {
        return new HashSet();
    }

    public Serializable getDataForBroadcastFromOtherManager(String str) {
        for (HwBaseManager hwBaseManager : getModuleInstancesSet()) {
            if (hwBaseManager.getAvailableBroadcastSet().contains(str)) {
                return hwBaseManager.getDataForBroadcast(str);
            }
        }
        return null;
    }

    private void init() {
        LogUtil.a(TAG, "init(), AvailableBroadcastSet is ", getAvailableBroadcastSet());
        if (getModuleId() == null) {
            LogUtil.h(TAG, "init(), The module id is empty! you must implements getModuleId method first.");
            throw new InvalidParameterException("init(), The module id is empty! you must implements getModuleId method first.");
        }
        if (getModuleIdSet().contains(getModuleId())) {
            LogUtil.h(TAG, "init(), The module id is duplicated!");
        } else {
            getModuleIdSet().add(getModuleId());
            getModuleInstancesSet().add(this);
        }
        this.mBroadcastManager = LocalBroadcastManager.getInstance(this.mContext);
    }

    public void onDestroy() {
        getModuleIdSet().remove(getModuleId());
    }

    public void registerBroadcast(BroadcastReceiver broadcastReceiver, String str) {
        if (this.mBroadcastManager == null) {
            LogUtil.h(TAG, "registerBroadcast, but mBroadcastManager is null");
        } else {
            this.mBroadcastManager.registerReceiver(broadcastReceiver, new IntentFilter(str));
        }
    }

    public void unregisterBroadcast(BroadcastReceiver broadcastReceiver) {
        LocalBroadcastManager localBroadcastManager = this.mBroadcastManager;
        if (localBroadcastManager == null) {
            LogUtil.h(TAG, "unregisterBroadcast, but mBroadcastManager is null");
        } else {
            localBroadcastManager.unregisterReceiver(broadcastReceiver);
        }
    }

    public boolean sendBroadcast(String str) {
        if (!getAvailableBroadcastSet().contains(str)) {
            LogUtil.h(TAG, "sendBroadcast, this broadcast is invalid!");
            return false;
        }
        if (this.mBroadcastManager == null) {
            LogUtil.h(TAG, "sendBroadcast, but mBroadcastManager is null");
            return false;
        }
        Intent intent = new Intent(str);
        intent.putExtra("content", getDataForBroadcast(str));
        this.mBroadcastManager.sendBroadcast(intent);
        return true;
    }

    public int setSharedPreference(String str, String str2, StorageParams storageParams) {
        return SharedPreferenceManager.e(this.mContext, String.valueOf(getModuleId()), str, str2, storageParams);
    }

    public Set<String> getExistingKeys() {
        return SharedPreferenceManager.d(this.mContext, String.valueOf(getModuleId()));
    }

    public String getSharedPreference(String str) {
        return SharedPreferenceManager.b(this.mContext, String.valueOf(getModuleId()), str);
    }

    public int deleteSharedPreference(String str) {
        return SharedPreferenceManager.c(this.mContext, String.valueOf(getModuleId()), str);
    }

    public int resetSharedPreference() {
        return SharedPreferenceManager.j(this.mContext, String.valueOf(getModuleId()));
    }

    public int createStorageDataTable(String str, int i, String str2) {
        return DbManager.c(this.mContext, String.valueOf(getModuleId()), str, i, str2);
    }

    public boolean deleteDatabase() {
        return DbManager.b(String.valueOf(getModuleId()));
    }

    public boolean deleteTable(String str, String str2, int i) {
        return DbManager.d(this.mContext, str, str2, i) == 0;
    }

    public Cursor queryStorageData(String str, int i, String str2) {
        return DbManager.bGE_(this.mContext, String.valueOf(getModuleId()), str, i, str2);
    }

    public Cursor queryStorageDataToOrder(String str, int i, String str2, String str3) {
        DbManager.b bVar = new DbManager.b();
        bVar.b(this.mContext);
        bVar.e(String.valueOf(getModuleId()));
        bVar.c(str);
        bVar.a(i);
        return DbManager.bGF_(bVar, str2, str3);
    }

    public int updateStorageData(String str, int i, ContentValues contentValues, String str2) {
        DbManager.b bVar = new DbManager.b();
        bVar.b(this.mContext);
        bVar.e(String.valueOf(getModuleId()));
        bVar.c(str);
        bVar.a(i);
        return DbManager.bGH_(bVar, contentValues, str2);
    }

    public int updateStorageData(String str, int i, ContentValues contentValues, String str2, String[] strArr) {
        DbManager.b bVar = new DbManager.b();
        bVar.b(this.mContext);
        bVar.e(String.valueOf(getModuleId()));
        bVar.c(str);
        bVar.a(i);
        return DbManager.bGI_(bVar, contentValues, str2, strArr);
    }

    public long insertStorageData(String str, int i, ContentValues contentValues) {
        return DbManager.bGC_(this.mContext, String.valueOf(getModuleId()), str, i, contentValues);
    }

    public long insertStorageDataWithOnConfict(String str, int i, ContentValues contentValues, int i2) {
        DbManager.b bVar = new DbManager.b();
        bVar.b(this.mContext);
        bVar.e(String.valueOf(getModuleId()));
        bVar.c(str);
        bVar.a(i);
        return DbManager.bGD_(bVar, contentValues, i2);
    }

    public int deleteStorageData(String str, int i, String str2) {
        return DbManager.a(this.mContext, String.valueOf(getModuleId()), str, i, str2);
    }

    public int deleteStorageData(String str, int i, String str2, String[] strArr) {
        DbManager.b bVar = new DbManager.b();
        bVar.b(this.mContext);
        bVar.e(String.valueOf(getModuleId()));
        bVar.c(str);
        bVar.a(i);
        return DbManager.e(bVar, str2, strArr);
    }

    public Cursor rawQueryStorageData(int i, String str, String[] strArr) {
        return DbManager.bGG_(String.valueOf(getModuleId()), i, str, strArr);
    }

    public String getTableFullName(String str) {
        return DbManager.c(String.valueOf(getModuleId()), str);
    }

    public int alterStorageDataTable(String str, int i, String str2) {
        return DbManager.d(this.mContext, String.valueOf(getModuleId()), str, i, str2);
    }
}
