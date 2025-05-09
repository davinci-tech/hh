package com.huawei.hmf.services;

import android.content.Context;
import com.huawei.hmf.services.interception.Interceptor;
import com.huawei.hmf.services.internal.ApplicationContext;

/* loaded from: classes4.dex */
public abstract class ModuleProvider {
    private Interceptor mInterceptor;
    private String mModuleName;

    protected void dependency() {
    }

    public void initialize() {
    }

    protected ApiSet register() {
        return null;
    }

    public final String getModuleName() {
        return this.mModuleName;
    }

    final void setModuleName(String str) {
        this.mModuleName = str;
    }

    protected void setInterceptor(Interceptor interceptor) {
        this.mInterceptor = interceptor;
    }

    protected Interceptor getInterceptor() {
        return this.mInterceptor;
    }

    public final Context getContext() {
        return ApplicationContext.getContext();
    }
}
