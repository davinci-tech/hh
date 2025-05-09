package com.huawei.wearengine.scope.cache;

import com.huawei.wearengine.scope.ScopeInfoResponse;

/* loaded from: classes8.dex */
public interface ScopeCache {
    void deleteScope(String str);

    ScopeInfoResponse getScope(String str);

    void saveScope(String str, ScopeInfoResponse scopeInfoResponse);

    void updateScope(String str, ScopeInfoResponse scopeInfoResponse);
}
