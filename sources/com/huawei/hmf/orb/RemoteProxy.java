package com.huawei.hmf.orb;

import com.huawei.hmf.annotation.NamedMethod;
import com.huawei.hmf.orb.aidl.NamingRemoteProxy;
import com.huawei.hmf.orb.aidl.request.InvokeService;
import com.huawei.hmf.orb.aidl.request.TypeKind;
import com.huawei.hmf.orb.bridge.Bridge;
import com.huawei.hmf.orb.bridge.RemoteBridgeFactory;
import com.huawei.hmf.orb.exception.InvocationException;
import com.huawei.hmf.services.codec.TypeToken;
import java.lang.reflect.Method;

/* loaded from: classes8.dex */
public class RemoteProxy {
    private static final Object[] EMPTY_ARRAY = new Object[0];
    private RemoteInvoker mRemote;
    private long mSequence = -1;

    public RemoteProxy(RemoteInvoker remoteInvoker) {
        this.mRemote = remoteInvoker;
    }

    protected void setSequence(long j) {
        this.mSequence = j;
    }

    protected Object send(String str, String str2, TypeToken typeToken, Object... objArr) {
        Class rawType;
        Bridge bridge;
        if (typeToken != null && (rawType = typeToken.getRawType()) != null && (bridge = RemoteBridgeFactory.getBridge(rawType)) != null) {
            return bridge.send(this.mRemote, this.mRemote.post(str, str2, assemblyArgument(objArr)), typeToken);
        }
        Class rawType2 = typeToken != null ? typeToken.getRawType() : null;
        TypeKind typeKind = TypeKind.CLASS;
        if (rawType2 != null && rawType2.isInterface()) {
            Method[] methods = rawType2.getMethods();
            int length = methods.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                if (methods[i].getAnnotation(NamedMethod.class) != null) {
                    typeKind = TypeKind.NamedClass;
                    break;
                }
                i++;
            }
        }
        InvokeService.Response send = this.mRemote.send(str, str2, typeKind, assemblyArgument(objArr));
        if (typeToken == null) {
            return null;
        }
        if (send == null) {
            throw new InvocationException("Unexpected null value returned.");
        }
        if (!send.isSuccessful()) {
            throw new InvocationException("Invoke failed, error code " + (send.ret == null ? "unknown" : send.ret.cast(Integer.TYPE)));
        }
        if (typeKind == TypeKind.NamedClass) {
            return NamingRemoteProxy.create(this.mRemote, (Class<?>[]) new Class[]{typeToken.getRawType()}, Long.valueOf(((Long) send.ret.cast(Long.class)).longValue()));
        }
        return send.ret.cast(typeToken.getType());
    }

    private Object[] assemblyArgument(Object[] objArr) {
        if (objArr == null) {
            objArr = EMPTY_ARRAY;
        }
        Object[] objArr2 = new Object[objArr.length + 1];
        objArr2[0] = Long.valueOf(this.mSequence);
        if (objArr.length > 0) {
            System.arraycopy(objArr, 0, objArr2, 1, objArr.length);
        }
        return objArr2;
    }
}
