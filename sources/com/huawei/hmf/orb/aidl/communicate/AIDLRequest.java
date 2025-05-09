package com.huawei.hmf.orb.aidl.communicate;

import com.huawei.hmf.orb.IMessageEntity;
import com.huawei.hmf.services.internal.GenericTypeReflector;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/* loaded from: classes9.dex */
public abstract class AIDLRequest<R extends IMessageEntity> {
    private static final String TAG = "AIDLRequest";
    public ClientIdentity clientIdentity;
    public AIDLResponse response;

    protected int checkPermission(R r) {
        return 0;
    }

    protected abstract void onRequest(R r);

    public final void execute(R r) {
        ClientIdentity clientIdentity = this.clientIdentity;
        if (clientIdentity == null || !clientIdentity.isValid()) {
            this.response.failure(207135000);
            return;
        }
        int checkPermission = !this.clientIdentity.isCore() ? checkPermission(r) : 0;
        if (checkPermission <= 0) {
            onRequest(r);
        } else {
            this.response.failure(checkPermission);
        }
    }

    public R makeParam() {
        try {
            return getParamType().newInstance();
        } catch (Exception unused) {
            return null;
        }
    }

    private Class<R> getParamType() {
        Class<?> cls = getClass();
        Type genericSuperclass = cls.getGenericSuperclass();
        while (!(genericSuperclass instanceof ParameterizedType)) {
            cls = cls.getSuperclass();
            genericSuperclass = cls.getGenericSuperclass();
        }
        return (Class<R>) GenericTypeReflector.getType(((ParameterizedType) genericSuperclass).getActualTypeArguments()[0]);
    }
}
