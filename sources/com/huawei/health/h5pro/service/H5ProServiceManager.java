package com.huawei.health.h5pro.service;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.huawei.health.h5pro.utils.LogUtil;
import com.huawei.health.h5pro.vengine.H5ProAppInfo;
import com.huawei.health.h5pro.vengine.H5ProJsCbkInvoker;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* loaded from: classes.dex */
public class H5ProServiceManager {
    public static H5ProServiceManager c;
    public static final Gson d = new GsonBuilder().create();
    public final ConcurrentHashMap<String, H5ProService> e = new ConcurrentHashMap<>();

    /* renamed from: a, reason: collision with root package name */
    public ReentrantReadWriteLock f2453a = new ReentrantReadWriteLock();
    public H5ProScopeChecker b = new H5ProScopeChecker() { // from class: com.huawei.health.h5pro.service.H5ProServiceManager.1
        @Override // com.huawei.health.h5pro.service.H5ProScopeChecker
        public boolean checkScope(H5ProAppInfo h5ProAppInfo, String str) {
            return true;
        }
    };

    public boolean unregisterService(Object obj) {
        try {
            Class<?> cls = obj.getClass();
            this.f2453a.writeLock().lock();
            com.huawei.health.h5pro.service.anotation.H5ProService h5ProService = (com.huawei.health.h5pro.service.anotation.H5ProService) cls.getAnnotation(com.huawei.health.h5pro.service.anotation.H5ProService.class);
            if (h5ProService == null) {
                LogUtil.e("H5PRO_H5ProServiceManager", "unregisterService: failed, the class has no H5ProService annotation.");
            } else {
                String name = h5ProService.name();
                if (TextUtils.isEmpty(name)) {
                    name = cls.getName();
                }
                if (this.e.get(name) != null) {
                    this.e.remove(name);
                    LogUtil.i("H5PRO_H5ProServiceManager", "unregisterService: " + name);
                    return true;
                }
            }
            return false;
        } finally {
            this.f2453a.writeLock().unlock();
        }
    }

    public boolean unregisterService(Class<?> cls) {
        try {
            this.f2453a.writeLock().lock();
            com.huawei.health.h5pro.service.anotation.H5ProService h5ProService = (com.huawei.health.h5pro.service.anotation.H5ProService) cls.getAnnotation(com.huawei.health.h5pro.service.anotation.H5ProService.class);
            if (h5ProService == null) {
                LogUtil.e("H5PRO_H5ProServiceManager", "unregisterService: failed, the class has no H5ProService annotation.");
            } else {
                String name = h5ProService.name();
                if (TextUtils.isEmpty(name)) {
                    name = cls.getName();
                }
                if (this.e.get(name) != null) {
                    this.e.remove(name);
                    LogUtil.i("H5PRO_H5ProServiceManager", "unregisterService: " + name);
                    return true;
                }
            }
            return false;
        } finally {
            this.f2453a.writeLock().unlock();
        }
    }

    public void setScopeChecker(H5ProScopeChecker h5ProScopeChecker) {
        this.b = h5ProScopeChecker;
    }

    public void restoreRegisteredServices(String[] strArr) {
        if (strArr == null || strArr.length == 0 || !this.e.isEmpty()) {
            return;
        }
        try {
            LogUtil.i("H5PRO_H5ProServiceManager", "restoreRegisteredServices");
            this.f2453a.writeLock().lock();
            for (int i = 0; i < strArr.length; i++) {
                if (!TextUtils.isEmpty(strArr[i])) {
                    String[] split = strArr[i].split(",");
                    if (split.length == 2) {
                        a(split[0], split[1]);
                    }
                }
            }
        } finally {
            this.f2453a.writeLock().unlock();
        }
    }

    public boolean registerService(Object obj) {
        try {
            Class<?> cls = obj.getClass();
            this.f2453a.writeLock().lock();
            com.huawei.health.h5pro.service.anotation.H5ProService h5ProService = (com.huawei.health.h5pro.service.anotation.H5ProService) cls.getAnnotation(com.huawei.health.h5pro.service.anotation.H5ProService.class);
            if (h5ProService == null) {
                LogUtil.e("H5PRO_H5ProServiceManager", "registerService: failed, the class has no H5ProService annotation.");
            } else {
                String name = h5ProService.name();
                if (TextUtils.isEmpty(name)) {
                    name = cls.getName();
                }
                if (this.e.get(name) == null) {
                    this.e.put(name, new H5ProService(obj));
                    LogUtil.i("H5PRO_H5ProServiceManager", "registerService: " + name);
                    return true;
                }
            }
            return false;
        } finally {
            this.f2453a.writeLock().unlock();
        }
    }

    public boolean registerService(Class<?> cls) {
        try {
            this.f2453a.writeLock().lock();
            if (cls == null) {
                LogUtil.e("H5PRO_H5ProServiceManager", "registerService: failed, serviceClass is null");
            } else {
                com.huawei.health.h5pro.service.anotation.H5ProService h5ProService = (com.huawei.health.h5pro.service.anotation.H5ProService) cls.getAnnotation(com.huawei.health.h5pro.service.anotation.H5ProService.class);
                if (h5ProService == null) {
                    LogUtil.e("H5PRO_H5ProServiceManager", "registerService: failed, the class has no H5ProService annotation.");
                } else {
                    String name = h5ProService.name();
                    if (TextUtils.isEmpty(name)) {
                        name = cls.getName();
                    }
                    if (this.e.get(name) == null) {
                        this.e.put(name, new H5ProService(cls));
                        return true;
                    }
                }
            }
            return false;
        } finally {
            this.f2453a.writeLock().unlock();
        }
    }

    public String invoke(H5ProAppInfo h5ProAppInfo, String str, String str2, String... strArr) {
        H5ProService a2 = a(str);
        if (a2 == null) {
            throw new RuntimeException(String.format("service %s not found", str));
        }
        H5ProMethod method = a2.getMethod(str2);
        if (method == null) {
            throw new RuntimeException(String.format("%s.%s not found", str, str2));
        }
        if (!b(h5ProAppInfo, a2.getDeclaredUsers())) {
            throw new RuntimeException(String.format("it's not allowed to access service %s, caller is %s", str, h5ProAppInfo.toString()));
        }
        if (!d(h5ProAppInfo, method.getScope())) {
            throw new RuntimeException("check scope failed");
        }
        Object call = method.call(a2.getServiceInstance(), b(method, strArr));
        if (method.getMethod().getReturnType().equals(Void.TYPE)) {
            return null;
        }
        return b(call);
    }

    public String[] getAllRegisteredServices() {
        ConcurrentHashMap<String, H5ProService> concurrentHashMap = this.e;
        if (concurrentHashMap == null || concurrentHashMap.isEmpty()) {
            return new String[0];
        }
        ArrayList arrayList = new ArrayList();
        for (H5ProService h5ProService : this.e.values()) {
            String serviceClassName = h5ProService.getServiceClassName();
            String str = h5ProService.getServiceInstance() == null ? "0" : "1";
            if (!TextUtils.isEmpty(serviceClassName)) {
                arrayList.add(serviceClassName + "," + str);
            }
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public void autoUnregisterService(H5ProServiceLiveTerm h5ProServiceLiveTerm) {
        try {
            this.f2453a.writeLock().lock();
            if (!this.e.isEmpty()) {
                for (Map.Entry<String, H5ProService> entry : this.e.entrySet()) {
                    H5ProService value = entry.getValue();
                    if (value != null && value.getLiveTerm() == h5ProServiceLiveTerm) {
                        LogUtil.i("H5PRO_H5ProServiceManager", String.format(Locale.ROOT, "autoUnregisterService: %s", entry.getKey()));
                        this.e.remove(entry.getKey());
                    }
                }
            }
        } finally {
            this.f2453a.writeLock().unlock();
        }
    }

    public void asyncInvoke(H5ProAppInfo h5ProAppInfo, H5ProJsCbkInvoker<Object> h5ProJsCbkInvoker, long j, String str, String str2, String... strArr) {
        H5ProService a2 = a(str);
        if (a2 == null) {
            throw new RuntimeException(String.format("service %s not found", str));
        }
        H5ProMethod method = a2.getMethod(str2);
        if (method == null) {
            throw new RuntimeException(String.format("%s.%s not found", str, str2));
        }
        if (!b(h5ProAppInfo, a2.getDeclaredUsers())) {
            throw new RuntimeException(String.format("it's not allowed to access service %s, caller is %s", str, h5ProAppInfo.toString()));
        }
        if (!d(h5ProAppInfo, method.getScope())) {
            throw new RuntimeException("check scope failed");
        }
        method.asyncCall(a2.getServiceInstance(), h5ProJsCbkInvoker, j, b(method, strArr));
    }

    private String b(Object obj) {
        return d.toJson(new H5ProInvokeResult(obj), H5ProInvokeResult.class);
    }

    private void a(String str, String str2) {
        try {
            LogUtil.i("H5PRO_H5ProServiceManager", "registerServiceByClassName: " + str + ": " + str2);
            Class<?> cls = Class.forName(str);
            if ("1".equals(str2)) {
                Constructor<?> c2 = c(cls);
                if (c2 != null) {
                    registerService(c2.newInstance(new Object[0]));
                }
            } else if ("0".equals(str2)) {
                registerService(cls);
            }
        } catch (ClassNotFoundException e) {
            LogUtil.e("H5PRO_H5ProServiceManager", "registerService by className: ClassNotFoundException, " + e.getMessage());
        } catch (Exception e2) {
            LogUtil.e("H5PRO_H5ProServiceManager", "registerService by className: " + e2.getMessage());
        }
    }

    private boolean b(H5ProAppInfo h5ProAppInfo, String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            return true;
        }
        for (String str : strArr) {
            if (str.equals(str.startsWith("http") ? h5ProAppInfo.getBaseUrl() : str.contains(".") ? h5ProAppInfo.getPkgName() : h5ProAppInfo.getCertPrint())) {
                return true;
            }
        }
        return false;
    }

    public static H5ProServiceManager getInstance() {
        if (c == null) {
            synchronized (H5ProServiceManager.class) {
                if (c == null) {
                    c = new H5ProServiceManager();
                }
            }
        }
        return c;
    }

    private Constructor<?> c(Class<?> cls) {
        for (Constructor<?> constructor : cls.getConstructors()) {
            if (constructor.getParameterTypes() == null || constructor.getParameterTypes().length == 0) {
                return constructor;
            }
        }
        return null;
    }

    private H5ProService a(String str) {
        this.f2453a.readLock().lock();
        H5ProService h5ProService = this.e.get(str);
        if (h5ProService == null) {
            this.f2453a.readLock().unlock();
            return null;
        }
        this.f2453a.readLock().unlock();
        return h5ProService;
    }

    private Object[] b(H5ProMethod h5ProMethod, String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            return strArr;
        }
        Class<?>[] parameterTypes = h5ProMethod.getMethod().getParameterTypes();
        if (parameterTypes.length != strArr.length && (parameterTypes.length != strArr.length + 1 || !h5ProMethod.hasCallback())) {
            throw new RuntimeException("method prototype mismatch");
        }
        Object[] objArr = new Object[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            if (parameterTypes[i].equals(String.class)) {
                objArr[i] = strArr[i];
            } else {
                objArr[i] = d.fromJson(strArr[i], (Class) parameterTypes[i]);
            }
        }
        return objArr;
    }

    private boolean d(H5ProAppInfo h5ProAppInfo, String str) {
        return this.b.checkScope(h5ProAppInfo, str);
    }
}
