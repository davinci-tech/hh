package com.huawei.health.h5pro.service;

import android.text.TextUtils;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class H5ProService {

    /* renamed from: a, reason: collision with root package name */
    public Object f2452a;
    public String[] b;
    public H5ProServiceLiveTerm d;
    public Map<String, H5ProMethod> e;

    public Object getServiceInstance() {
        return this.f2452a;
    }

    public String getServiceClassName() {
        Class<?> declaringClass;
        Object obj = this.f2452a;
        if (obj != null) {
            declaringClass = obj.getClass();
        } else {
            Map<String, H5ProMethod> map = this.e;
            if (map == null || map.isEmpty()) {
                return "";
            }
            declaringClass = this.e.values().iterator().next().getMethod().getDeclaringClass();
        }
        return declaringClass.getName();
    }

    public H5ProMethod getMethod(String str) {
        return this.e.get(str);
    }

    public H5ProServiceLiveTerm getLiveTerm() {
        return this.d;
    }

    public String[] getDeclaredUsers() {
        return this.b;
    }

    public H5ProService(Object obj) {
        this(obj.getClass(), false);
        this.f2452a = obj;
    }

    public H5ProService(Class<?> cls, boolean z) {
        com.huawei.health.h5pro.service.anotation.H5ProMethod h5ProMethod;
        com.huawei.health.h5pro.service.anotation.H5ProService h5ProService = (com.huawei.health.h5pro.service.anotation.H5ProService) cls.getAnnotation(com.huawei.health.h5pro.service.anotation.H5ProService.class);
        this.b = h5ProService.users();
        this.d = h5ProService.liveTerm();
        this.e = new HashMap();
        for (Method method : cls.getMethods()) {
            int modifiers = method.getModifiers();
            if (Modifier.isPublic(modifiers) && ((!z || Modifier.isStatic(modifiers)) && (h5ProMethod = (com.huawei.health.h5pro.service.anotation.H5ProMethod) method.getAnnotation(com.huawei.health.h5pro.service.anotation.H5ProMethod.class)) != null)) {
                String name = h5ProMethod.name();
                this.e.put(TextUtils.isEmpty(name) ? method.getName() : name, new H5ProMethod(method, h5ProMethod.scope()));
            }
        }
    }

    public H5ProService(Class<?> cls) {
        this(cls, true);
    }
}
