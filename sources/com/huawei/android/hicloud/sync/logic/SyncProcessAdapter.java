package com.huawei.android.hicloud.sync.logic;

import com.huawei.android.hicloud.sync.service.aidl.SyncData;
import defpackage.aag;
import defpackage.abd;
import defpackage.zv;
import java.util.List;

/* loaded from: classes8.dex */
public abstract class SyncProcessAdapter implements SyncProcessInterface {
    @Override // com.huawei.android.hicloud.sync.logic.SyncProcessInterface
    public zv addCompare(String str, List<String> list, SyncData syncData) throws aag {
        abd.d("SyncProcessAdapter", "addCompare is deprecated");
        return null;
    }

    @Override // com.huawei.android.hicloud.sync.logic.SyncProcessInterface
    public zv conflictCompare(String str, String str2, SyncData syncData) throws aag {
        abd.d("SyncProcessAdapter", "conflictCompare is deprecated");
        return null;
    }

    @Override // com.huawei.android.hicloud.sync.logic.SyncProcessInterface
    public List<zv> processLocalModifyCloudDelete(String str, List<String> list) throws aag {
        abd.d("SyncProcessAdapter", "processLocalModifyCloudDelete is deprecated");
        return null;
    }
}
