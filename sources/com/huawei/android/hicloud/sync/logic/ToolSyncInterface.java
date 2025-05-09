package com.huawei.android.hicloud.sync.logic;

import com.huawei.android.hicloud.sync.service.aidl.UnstructData;
import defpackage.aag;
import java.util.List;
import java.util.Map;

/* loaded from: classes8.dex */
public interface ToolSyncInterface {
    void onGetExceedLimitsResult(String str, Map<String, Long> map) throws aag;

    void onUnstructDataDownloadEnd(String str, String str2, List<UnstructData> list, List<UnstructData> list2, Map<Integer, List<String>> map, int i) throws aag;
}
