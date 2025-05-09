package com.huawei.agconnect.core.a;

import android.content.Context;
import android.util.Log;
import com.huawei.agconnect.AGConnectInstance;
import com.huawei.agconnect.core.Service;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public final class d {

    /* renamed from: a, reason: collision with root package name */
    private static Map<Class<?>, Service> f1721a = new HashMap();
    private static Map<Class<?>, Object> b = new HashMap();
    private Map<Class<?>, Service> c = new HashMap();
    private Map<Class<?>, Object> d = new HashMap();

    /* JADX WARN: Removed duplicated region for block: B:27:0x0062 A[Catch: InvocationTargetException -> 0x007d, InstantiationException -> 0x0081, IllegalAccessException -> 0x0085, TryCatch #2 {IllegalAccessException -> 0x0085, InstantiationException -> 0x0081, InvocationTargetException -> 0x007d, blocks: (B:23:0x0050, B:27:0x0062, B:28:0x0073, B:31:0x006b), top: B:22:0x0050 }] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x006b A[Catch: InvocationTargetException -> 0x007d, InstantiationException -> 0x0081, IllegalAccessException -> 0x0085, TryCatch #2 {IllegalAccessException -> 0x0085, InstantiationException -> 0x0081, InvocationTargetException -> 0x007d, blocks: (B:23:0x0050, B:27:0x0062, B:28:0x0073, B:31:0x006b), top: B:22:0x0050 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(java.util.List<com.huawei.agconnect.core.Service> r7, android.content.Context r8) {
        /*
            r6 = this;
            java.lang.String r0 = "addService start"
            java.lang.String r1 = "AGC_ServiceRepository"
            android.util.Log.d(r1, r0)
            if (r7 != 0) goto La
            return
        La:
            java.util.Iterator r7 = r7.iterator()
        Le:
            boolean r0 = r7.hasNext()
            if (r0 == 0) goto L8c
            java.lang.Object r0 = r7.next()
            com.huawei.agconnect.core.Service r0 = (com.huawei.agconnect.core.Service) r0
            boolean r2 = r0.isSharedInstance()
            if (r2 == 0) goto L2f
            java.util.Map<java.lang.Class<?>, com.huawei.agconnect.core.Service> r2 = com.huawei.agconnect.core.a.d.f1721a
            java.lang.Class r3 = r0.getInterface()
            boolean r2 = r2.containsKey(r3)
            if (r2 != 0) goto L38
            java.util.Map<java.lang.Class<?>, com.huawei.agconnect.core.Service> r2 = com.huawei.agconnect.core.a.d.f1721a
            goto L31
        L2f:
            java.util.Map<java.lang.Class<?>, com.huawei.agconnect.core.Service> r2 = r6.c
        L31:
            java.lang.Class r3 = r0.getInterface()
            r2.put(r3, r0)
        L38:
            boolean r2 = r0.isAutoCreated()
            if (r2 == 0) goto Le
            java.lang.Class r2 = r0.getType()
            if (r2 == 0) goto Le
            java.util.Map<java.lang.Class<?>, java.lang.Object> r2 = com.huawei.agconnect.core.a.d.b
            java.lang.Class r3 = r0.getInterface()
            boolean r2 = r2.containsKey(r3)
            if (r2 != 0) goto Le
            java.lang.Class r2 = r0.getType()     // Catch: java.lang.reflect.InvocationTargetException -> L7d java.lang.InstantiationException -> L81 java.lang.IllegalAccessException -> L85
            r3 = 1
            java.lang.Class[] r3 = new java.lang.Class[r3]     // Catch: java.lang.reflect.InvocationTargetException -> L7d java.lang.InstantiationException -> L81 java.lang.IllegalAccessException -> L85
            java.lang.Class<android.content.Context> r4 = android.content.Context.class
            r5 = 0
            r3[r5] = r4     // Catch: java.lang.reflect.InvocationTargetException -> L7d java.lang.InstantiationException -> L81 java.lang.IllegalAccessException -> L85
            java.lang.reflect.Constructor r2 = a(r2, r3)     // Catch: java.lang.reflect.InvocationTargetException -> L7d java.lang.InstantiationException -> L81 java.lang.IllegalAccessException -> L85
            if (r2 == 0) goto L6b
            java.lang.Object[] r3 = new java.lang.Object[]{r8}     // Catch: java.lang.reflect.InvocationTargetException -> L7d java.lang.InstantiationException -> L81 java.lang.IllegalAccessException -> L85
            java.lang.Object r2 = r2.newInstance(r3)     // Catch: java.lang.reflect.InvocationTargetException -> L7d java.lang.InstantiationException -> L81 java.lang.IllegalAccessException -> L85
            goto L73
        L6b:
            java.lang.Class r2 = r0.getType()     // Catch: java.lang.reflect.InvocationTargetException -> L7d java.lang.InstantiationException -> L81 java.lang.IllegalAccessException -> L85
            java.lang.Object r2 = r2.newInstance()     // Catch: java.lang.reflect.InvocationTargetException -> L7d java.lang.InstantiationException -> L81 java.lang.IllegalAccessException -> L85
        L73:
            java.util.Map<java.lang.Class<?>, java.lang.Object> r3 = com.huawei.agconnect.core.a.d.b     // Catch: java.lang.reflect.InvocationTargetException -> L7d java.lang.InstantiationException -> L81 java.lang.IllegalAccessException -> L85
            java.lang.Class r0 = r0.getInterface()     // Catch: java.lang.reflect.InvocationTargetException -> L7d java.lang.InstantiationException -> L81 java.lang.IllegalAccessException -> L85
            r3.put(r0, r2)     // Catch: java.lang.reflect.InvocationTargetException -> L7d java.lang.InstantiationException -> L81 java.lang.IllegalAccessException -> L85
            goto Le
        L7d:
            r0 = move-exception
            java.lang.String r2 = "TargetException"
            goto L88
        L81:
            r0 = move-exception
            java.lang.String r2 = "InstantiationException"
            goto L88
        L85:
            r0 = move-exception
            java.lang.String r2 = "AccessException"
        L88:
            r6.a(r2, r0)
            goto Le
        L8c:
            java.lang.String r7 = "addService end"
            android.util.Log.d(r1, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.agconnect.core.a.d.a(java.util.List, android.content.Context):void");
    }

    public <T> T a(AGConnectInstance aGConnectInstance, Class<?> cls) {
        T t;
        Service service = this.c.get(cls);
        if (service == null && (service = f1721a.get(cls)) != null) {
            return (T) b.get(cls);
        }
        if (service == null) {
            return null;
        }
        if (service.isSingleton() && (t = (T) this.d.get(cls)) != null) {
            return t;
        }
        T t2 = (T) a(aGConnectInstance, service);
        if (t2 != null && service.isSingleton()) {
            this.d.put(cls, t2);
        }
        return t2;
    }

    private void a(String str, Exception exc) {
        Log.e("AGC_ServiceRepository", "Instantiate shared service " + str + exc.getLocalizedMessage());
        StringBuilder sb = new StringBuilder("cause message:");
        sb.append(exc.getCause() != null ? exc.getCause().getMessage() : "");
        Log.e("AGC_ServiceRepository", sb.toString());
    }

    private static Constructor a(Class cls, Class... clsArr) {
        boolean z = false;
        for (Constructor<?> constructor : cls.getDeclaredConstructors()) {
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            if (parameterTypes.length == clsArr.length) {
                for (int i = 0; i < clsArr.length; i++) {
                    z = parameterTypes[i] == clsArr[i];
                }
                if (z) {
                    return constructor;
                }
            }
        }
        return null;
    }

    private Object a(AGConnectInstance aGConnectInstance, Service service) {
        StringBuilder sb;
        String localizedMessage;
        if (service.getInstance() != null) {
            return service.getInstance();
        }
        Class<?> type = service.getType();
        if (type == null) {
            return null;
        }
        try {
            Constructor a2 = a(type, Context.class, AGConnectInstance.class);
            if (a2 != null) {
                return a2.newInstance(aGConnectInstance.getContext(), aGConnectInstance);
            }
            Constructor a3 = a(type, Context.class);
            return a3 != null ? a3.newInstance(aGConnectInstance.getContext()) : type.newInstance();
        } catch (IllegalAccessException e) {
            sb = new StringBuilder("Instantiate service exception ");
            localizedMessage = e.getLocalizedMessage();
            sb.append(localizedMessage);
            Log.e("AGC_ServiceRepository", sb.toString());
            return null;
        } catch (InstantiationException e2) {
            sb = new StringBuilder("Instantiate service exception ");
            localizedMessage = e2.getLocalizedMessage();
            sb.append(localizedMessage);
            Log.e("AGC_ServiceRepository", sb.toString());
            return null;
        } catch (InvocationTargetException e3) {
            sb = new StringBuilder("Instantiate service exception ");
            localizedMessage = e3.getLocalizedMessage();
            sb.append(localizedMessage);
            Log.e("AGC_ServiceRepository", sb.toString());
            return null;
        }
    }

    d(List<Service> list, Context context) {
        a(list, context);
    }
}
