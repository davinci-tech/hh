package com.huawei.profile.datamanager;

import com.huawei.profile.kv.DBEntity;
import com.huawei.profile.utils.logger.DsLog;
import java.util.Collections;
import java.util.List;

/* loaded from: classes6.dex */
public class FalseDatabase extends AbstractDatabase {
    private static final String EMPTY_STR = "";
    private static final String ERR_MSG = "error!";
    private static final String TAG = "FalseDatabase";

    @Override // com.huawei.profile.datamanager.AbstractDatabase
    public boolean put(String str, DBEntity dBEntity) {
        DsLog.et(TAG, ERR_MSG, new Object[0]);
        return false;
    }

    @Override // com.huawei.profile.datamanager.AbstractDatabase
    public String get(String str, String str2) {
        DsLog.et(TAG, ERR_MSG, new Object[0]);
        return "";
    }

    @Override // com.huawei.profile.datamanager.AbstractDatabase
    public List<KvEntity> get(String str) {
        DsLog.et(TAG, ERR_MSG, new Object[0]);
        return Collections.emptyList();
    }

    @Override // com.huawei.profile.datamanager.AbstractDatabase
    public boolean remove(String str, String str2) {
        DsLog.et(TAG, ERR_MSG, new Object[0]);
        return false;
    }

    @Override // com.huawei.profile.datamanager.AbstractDatabase
    public boolean removeList(String str, List<String> list) {
        DsLog.et(TAG, ERR_MSG, new Object[0]);
        return false;
    }

    @Override // com.huawei.profile.datamanager.AbstractDatabase
    public boolean removeStartWithKey(String str, String str2) {
        DsLog.et(TAG, ERR_MSG, new Object[0]);
        return false;
    }
}
