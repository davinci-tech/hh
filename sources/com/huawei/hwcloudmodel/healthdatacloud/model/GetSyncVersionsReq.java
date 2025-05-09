package com.huawei.hwcloudmodel.healthdatacloud.model;

import com.huawei.hwcloudmodel.model.unite.SyncKey;
import com.huawei.networkclient.IRequest;
import health.compact.a.Utils;
import java.util.List;

/* loaded from: classes7.dex */
public class GetSyncVersionsReq implements IRequest {
    private List<SyncKey> syncKeys;

    public List<SyncKey> getSyncKeys() {
        return this.syncKeys;
    }

    public void setSyncKeys(List<SyncKey> list) {
        this.syncKeys = list;
    }

    public String toString() {
        return "GetSyncVersionsReq{syncKeys=" + this.syncKeys + '}';
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return Utils.e() + "/dataQuery/common/getSyncVersions";
    }
}
