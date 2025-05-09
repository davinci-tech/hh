package defpackage;

import android.text.TextUtils;
import com.huawei.wearengine.scope.ScopeInfoResponse;
import com.huawei.wearengine.scope.cache.ScopeCache;

/* loaded from: classes8.dex */
public class tqn implements ScopeCache {

    /* renamed from: a, reason: collision with root package name */
    private ScopeCache f17348a = new tql();
    private ScopeCache c = new tqm();

    @Override // com.huawei.wearengine.scope.cache.ScopeCache
    public void saveScope(String str, ScopeInfoResponse scopeInfoResponse) {
        tos.b("ScopeCacheManager", "saveScope, scopeKey=" + str);
        if (TextUtils.isEmpty(str) || scopeInfoResponse == null) {
            return;
        }
        this.c.saveScope(str, scopeInfoResponse);
        this.f17348a.saveScope(str, scopeInfoResponse);
    }

    @Override // com.huawei.wearengine.scope.cache.ScopeCache
    public void deleteScope(String str) {
        tos.b("ScopeCacheManager", "deleteScope, scopeKey=" + str);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.c.deleteScope(str);
        this.f17348a.deleteScope(str);
    }

    @Override // com.huawei.wearengine.scope.cache.ScopeCache
    public void updateScope(String str, ScopeInfoResponse scopeInfoResponse) {
        tos.b("ScopeCacheManager", "updateScope, scopeKey=" + str);
        if (TextUtils.isEmpty(str) || scopeInfoResponse == null) {
            return;
        }
        this.c.updateScope(str, scopeInfoResponse);
        this.f17348a.updateScope(str, scopeInfoResponse);
    }

    @Override // com.huawei.wearengine.scope.cache.ScopeCache
    public ScopeInfoResponse getScope(String str) {
        tos.b("ScopeCacheManager", "getScope, scopeKey=" + str);
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        tos.b("ScopeCacheManager", "getScope from memory, scopeKey=" + str);
        ScopeInfoResponse scope = this.c.getScope(str);
        if (scope != null) {
            return scope;
        }
        tos.b("ScopeCacheManager", "getScope from db, scopeKey=" + str);
        return this.f17348a.getScope(str);
    }
}
