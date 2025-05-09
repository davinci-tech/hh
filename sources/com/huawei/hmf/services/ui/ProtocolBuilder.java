package com.huawei.hmf.services.ui;

import com.huawei.hmf.services.ApiSet;

/* loaded from: classes9.dex */
public class ProtocolBuilder {
    private final UIModule mUIModule;

    private ProtocolBuilder(UIModule uIModule) {
        this.mUIModule = uIModule;
    }

    public ProtocolRef build() {
        return new ProtocolRef(this.mUIModule.getUIModuleSpec().mProtocol, this.mUIModule.createProtocol());
    }

    public static ProtocolBuilder builder(Class<?> cls) {
        return new ProtocolBuilder(new UIModule("default", ApiSet.builder().obtain(cls)));
    }
}
