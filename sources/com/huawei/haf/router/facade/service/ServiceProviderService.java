package com.huawei.haf.router.facade.service;

import com.huawei.haf.router.facade.template.SingleServiceProvider;

/* loaded from: classes.dex */
public interface ServiceProviderService extends SingleServiceProvider {
    <T> T getService(String str, Class<T> cls, String str2);

    void loadRegistry(ClassLoader classLoader, String str, String[] strArr);
}
