package com.huawei.hmf.services.inject;

import com.huawei.hmf.services.ApiSet;
import com.huawei.hmf.services.inject.Selector;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes9.dex */
public class SelectorImpl extends Selector {
    private Map<String, Object> mValue = new HashMap();

    @Override // com.huawei.hmf.services.inject.Selector
    public Selector.Binder alias(String str) {
        return new Selector.Binder(str);
    }

    @Override // com.huawei.hmf.services.inject.Selector
    void add(Selector.Binder binder) {
        if (ApiSet.builder().obtain(binder.mService) == null) {
            throw new IllegalArgumentException(binder.mService.getName() + " cannot be bind without an @ActivityDefine or @FragmentDefine Annotation");
        }
        this.mValue.put(binder.mName, binder.mService);
    }

    public Map<String, Object> getValue() {
        return this.mValue;
    }

    @Override // com.huawei.hmf.services.inject.Selector
    public boolean isEmpty() {
        return this.mValue.isEmpty();
    }
}
