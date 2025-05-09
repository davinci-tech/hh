package com.huawei.wearengine.repository.api;

import com.huawei.wearengine.scope.ScopeInfoResponse;

/* loaded from: classes8.dex */
public interface ScopeResponseRepository {
    boolean deleteScopeResponse(String str);

    ScopeInfoResponse getScopeResponse(String str);

    boolean insertScopeResponse(ScopeInfoResponse scopeInfoResponse, String str);

    boolean updateScopeResponse(ScopeInfoResponse scopeInfoResponse, String str);
}
