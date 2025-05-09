package com.huawei.framework.servicemgr;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.haf.router.facade.service.ServiceProviderService;
import com.huawei.hmf.repository.ComponentRepository;
import com.huawei.hmf.repository.Repository;
import com.huawei.hmf.services.Module;
import health.compact.a.LogUtil;
import health.compact.a.ReflectionUtils;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public final class HmfProviderService implements ServiceProviderService {
    @Override // com.huawei.haf.router.facade.template.ServiceProvider
    public void init(Context context) {
    }

    @Override // com.huawei.haf.router.facade.service.ServiceProviderService
    public <T> T getService(String str, Class<T> cls, String str2) {
        Module lookup = ComponentRepository.getRepository().lookup(str);
        if (lookup != null) {
            return TextUtils.isEmpty(str2) ? (T) lookup.create(cls) : (T) lookup.create(cls, str2);
        }
        LogUtil.a("HmfProviderService", "The provider module not exist: ", str);
        return null;
    }

    @Override // com.huawei.haf.router.facade.service.ServiceProviderService
    public void loadRegistry(ClassLoader classLoader, String str, String[] strArr) {
        d(classLoader, str, (List<String>) Arrays.asList(strArr));
    }

    public static void b(ClassLoader classLoader, String str) {
        d(classLoader, str, (List<String>) Collections.singletonList(str));
    }

    private static void d(ClassLoader classLoader, String str, List<String> list) {
        try {
            Class<?> b = b("com.huawei.hmf.md.bundle." + str + "ModuleRegistry", classLoader);
            if (b != null) {
                d(b, str, "pluginName");
                return;
            }
            for (String str2 : list) {
                Class<?> b2 = b("com.huawei.hmf.md.bootstrap." + str2 + "ModuleBootstrap", classLoader);
                if (b2 != null) {
                    d(b2, str2, "provider");
                }
            }
        } catch (Exception e) {
            LogUtil.e("HmfProviderService", "Initialize registry information failed: ", str, ", cause: ", LogUtil.a(e));
        }
    }

    private static Class<?> b(String str, ClassLoader classLoader) {
        try {
            return ReflectionUtils.c(str, classLoader);
        } catch (ClassNotFoundException e) {
            LogUtil.d("HmfProviderService", "getClass ex=", LogUtil.a(e));
            return null;
        }
    }

    private static void d(Class<?> cls, String str, String str2) {
        try {
            cls.getMethod("register", Repository.class).invoke(null, ComponentRepository.getRepository());
            LogUtil.c("HmfProviderService", "The registry information of ", str2, "=", str, " was initialized successfully");
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            LogUtil.c("HmfProviderService", "The registry information NOT found: ", str, ", cause: ", LogUtil.a(e));
        }
    }
}
