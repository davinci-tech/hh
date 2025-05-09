package com.huawei.hmf.services.ui;

import com.huawei.hmf.services.ApiSpec;

/* loaded from: classes4.dex */
public class UIModuleSpec extends ApiSpec {
    Class<?> mProtocol;
    Class<?> mResult;

    public UIModuleSpec(Class<?> cls) {
        this(cls, null, null);
    }

    public UIModuleSpec(Class<?> cls, Class<?> cls2, Class<?> cls3) {
        super(cls);
        this.mProtocol = cls2;
        this.mResult = cls3;
    }

    public Class<?> getProtocol() {
        return this.mProtocol;
    }

    public Class<?> getResult() {
        return this.mResult;
    }
}
