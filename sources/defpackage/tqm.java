package defpackage;

import android.text.TextUtils;
import com.huawei.wearengine.scope.ScopeInfoResponse;
import com.huawei.wearengine.scope.cache.ScopeCache;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes8.dex */
public class tqm implements ScopeCache {
    private Map<String, ScopeInfoResponse> c = new ConcurrentHashMap();

    @Override // com.huawei.wearengine.scope.cache.ScopeCache
    public void saveScope(String str, ScopeInfoResponse scopeInfoResponse) {
        if (scopeInfoResponse == null || TextUtils.isEmpty(str)) {
            return;
        }
        this.c.put(str, scopeInfoResponse);
    }

    @Override // com.huawei.wearengine.scope.cache.ScopeCache
    public void deleteScope(String str) {
        if (this.c.containsKey(str)) {
            this.c.remove(str);
        }
    }

    @Override // com.huawei.wearengine.scope.cache.ScopeCache
    public void updateScope(String str, ScopeInfoResponse scopeInfoResponse) {
        if (scopeInfoResponse == null || TextUtils.isEmpty(str)) {
            return;
        }
        this.c.put(str, scopeInfoResponse);
    }

    @Override // com.huawei.wearengine.scope.cache.ScopeCache
    public ScopeInfoResponse getScope(String str) {
        if (this.c.containsKey(str)) {
            return this.c.get(str);
        }
        return null;
    }
}
