package com.huawei.hmf.orb.aidl;

import com.huawei.hmf.annotation.NamedMethod;
import com.huawei.hmf.orb.Releasable;
import com.huawei.hmf.orb.RemoteTarget;
import com.huawei.hmf.services.codec.Variant;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/* loaded from: classes9.dex */
public class NamingRemoteTarget<T> extends RemoteTarget<T> {
    @Override // com.huawei.hmf.orb.RemoteTarget
    public Class getServiceType() {
        return null;
    }

    @Override // com.huawei.hmf.orb.RemoteTarget
    public String getServiceTypeName() {
        return null;
    }

    public NamingRemoteTarget(T t) {
        super(t);
    }

    @Override // com.huawei.hmf.orb.RemoteTarget
    public Object onTransact(String str, Object... objArr) {
        if (service() == null) {
            return null;
        }
        Class<?>[] interfaces = service().getClass().getInterfaces();
        int length = interfaces.length;
        Object[] objArr2 = objArr;
        for (int i = 0; i < length; i++) {
            for (Method method : interfaces[i].getMethods()) {
                NamedMethod namedMethod = (NamedMethod) method.getAnnotation(NamedMethod.class);
                if (namedMethod != null && namedMethod.value().equals(str)) {
                    try {
                        Type[] genericParameterTypes = method.getGenericParameterTypes();
                        if (genericParameterTypes.length > 0) {
                            Object[] objArr3 = new Object[genericParameterTypes.length];
                            for (int i2 = 0; i2 < genericParameterTypes.length; i2++) {
                                objArr3[i2] = ((Variant) objArr2[i2]).cast(genericParameterTypes[i2]);
                            }
                            objArr2 = objArr3;
                        }
                        return method.invoke(service(), objArr2);
                    } catch (IllegalAccessException | InvocationTargetException unused) {
                        continue;
                    }
                }
            }
        }
        return null;
    }

    @Override // com.huawei.hmf.orb.RemoteTarget, com.huawei.hmf.orb.Releasable
    public void release() {
        super.release();
        T service = service();
        if (service == null || !(service instanceof Releasable)) {
            return;
        }
        ((Releasable) service).release();
    }
}
