package com.huawei.hmf.orb.tbis.result;

import com.huawei.hmf.orb.tbis.TextCodecFactory;

/* loaded from: classes9.dex */
public class DefaultResult extends TBResult {
    private final Object value;

    public DefaultResult(Object obj) {
        this.value = obj;
    }

    @Override // com.huawei.hmf.orb.tbis.result.TBResult
    public Object getValue() {
        return TextCodecFactory.create().toString(this.value);
    }
}
