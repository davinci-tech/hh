package com.huawei.hmf.services.inject;

import com.huawei.hmf.services.Module;

/* loaded from: classes9.dex */
public interface InjectInstanceCreator<T> {
    T createInstance(Module module, String str);
}
