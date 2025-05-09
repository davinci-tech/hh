package com.huawei.haf.router.core;

import com.huawei.haf.router.facade.service.ServiceProviderService;

/* loaded from: classes.dex */
final class AppRouterServiceProviderService {
    private final ServiceProviderService d = (ServiceProviderService) AppRouterHelper.e(ServiceProviderService.class);

    AppRouterServiceProviderService() {
    }

    <T> T c(String str, Class<T> cls, String str2) {
        ServiceProviderService serviceProviderService = this.d;
        if (serviceProviderService != null) {
            return (T) serviceProviderService.getService(str, cls, str2);
        }
        return null;
    }

    void c(ClassLoader classLoader, String str, String[] strArr) {
        ServiceProviderService serviceProviderService = this.d;
        if (serviceProviderService != null) {
            serviceProviderService.loadRegistry(classLoader, str, strArr);
        }
    }
}
