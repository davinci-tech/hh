package com.huawei.android.hicloud.sync.logic;

import com.huawei.android.hicloud.sync.service.aidl.LocalId;
import com.huawei.android.hicloud.sync.service.aidl.SyncData;
import com.huawei.android.hicloud.sync.service.aidl.UnstructData;
import defpackage.aag;
import defpackage.zt;
import defpackage.zu;
import defpackage.zv;
import java.util.List;
import java.util.Map;

/* loaded from: classes8.dex */
public interface SyncProcessInterface {
    @Deprecated
    zv addCompare(String str, List<String> list, SyncData syncData) throws aag;

    @Deprecated
    zv conflictCompare(String str, String str2, SyncData syncData) throws aag;

    List<zt> dataQueryByID(String str, List<String> list) throws aag;

    List<String> deleteData(String str, List<String> list) throws aag;

    List<Object> getUpdateDataResults() throws aag;

    void hiCloudVersionTooLow(int i);

    void onDataSyncEnd(String str, int i) throws aag;

    void onDownloadSyncStart(String str, Map<String, Integer> map) throws aag;

    void onGetExceedLimitsResult(String str, Map<String, Long> map) throws aag;

    void onGetStructDataCountResult(String str, Map<String, Integer> map) throws aag;

    void onSyncEnd();

    void onUnstructDataDownloadEnd(String str, List<UnstructData> list, List<UnstructData> list2, Map<Integer, List<String>> map, boolean z, int i) throws aag;

    void onUploadSyncStart(String str) throws aag;

    @Deprecated
    List<zv> processLocalModifyCloudDelete(String str, List<String> list) throws aag;

    List<LocalId> queryLocalIds(String str, int i) throws aag;

    List<zu> updateStructData(String str, List<SyncData> list, List<SyncData> list2) throws aag;

    void updateSyncResult(String str, List<SyncData> list, List<SyncData> list2, List<String> list3, Map<Integer, List<String>> map) throws aag;
}
