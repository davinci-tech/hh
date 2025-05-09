package com.huawei.hmf.orb.tbis;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
class TBMethodCache {
    private Map<Class, List<TBMethod>> cache = new HashMap();

    TBMethodCache() {
    }

    public List<TBMethod> get(Class cls) {
        List<TBMethod> list = this.cache.get(cls);
        if (list == null) {
            return null;
        }
        return Collections.unmodifiableList(list);
    }

    public List<TBMethod> put(Class cls, List<TBMethod> list) {
        this.cache.put(cls, list);
        return Collections.unmodifiableList(list);
    }
}
