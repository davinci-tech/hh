package defpackage;

import android.text.TextUtils;
import com.huawei.wearengine.repository.ScopeResponseRepositoryImpl;
import com.huawei.wearengine.repository.api.ScopeResponseRepository;
import com.huawei.wearengine.scope.ScopeInfoResponse;
import com.huawei.wearengine.scope.cache.ScopeCache;

/* loaded from: classes8.dex */
public class tql implements ScopeCache {
    private ScopeResponseRepository d = new ScopeResponseRepositoryImpl(tot.a());

    @Override // com.huawei.wearengine.scope.cache.ScopeCache
    public void saveScope(String str, ScopeInfoResponse scopeInfoResponse) {
        if (TextUtils.isEmpty(str) || scopeInfoResponse == null) {
            return;
        }
        this.d.insertScopeResponse(scopeInfoResponse, str);
    }

    @Override // com.huawei.wearengine.scope.cache.ScopeCache
    public void deleteScope(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.d.deleteScopeResponse(str);
    }

    @Override // com.huawei.wearengine.scope.cache.ScopeCache
    public void updateScope(String str, ScopeInfoResponse scopeInfoResponse) {
        if (TextUtils.isEmpty(str) || scopeInfoResponse == null) {
            return;
        }
        this.d.updateScopeResponse(scopeInfoResponse, str);
    }

    @Override // com.huawei.wearengine.scope.cache.ScopeCache
    public ScopeInfoResponse getScope(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return this.d.getScopeResponse(str);
    }
}
