package com.huawei.hmf.orb;

import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hmf.orb.exception.ApiNotExistException;
import com.huawei.hmf.repository.ComponentRepository;
import com.huawei.hmf.services.Module;
import com.huawei.hmf.services.codec.TypeToken;
import com.huawei.hmf.services.codec.Variant;
import com.huawei.hmf.services.interception.CreateOptions;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes8.dex */
public abstract class RemoteTarget<T> implements Releasable {
    private static Map<String, List<TypeToken>> methodProto = new HashMap();
    private String mAlias;
    private T mService;

    public abstract Class<T> getServiceType();

    public abstract String getServiceTypeName();

    @Override // com.huawei.hmf.orb.Releasable
    public void release() {
    }

    public static class Builder {
        private String mAlias;
        private String mModule;

        private Builder() {
        }

        public Builder module(String str) {
            this.mModule = str;
            return this;
        }

        public Builder alias(String str) {
            this.mAlias = str;
            return this;
        }

        public RemoteTarget build(Class<?> cls) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
            return (RemoteTarget) cls.getConstructor(Builder.class).newInstance(this);
        }
    }

    protected static void registryMethod(String str, TypeToken... typeTokenArr) {
        methodProto.put(str, Arrays.asList(typeTokenArr));
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getAlias() {
        return this.mAlias;
    }

    public RemoteTarget(T t) {
        this.mService = t;
    }

    public RemoteTarget(Builder builder) {
        Bundle bundle;
        Module lookup = ComponentRepository.getRepository().lookup(builder.mModule);
        if (!TextUtils.isEmpty(builder.mAlias)) {
            this.mAlias = builder.mAlias;
        }
        if (lookup.getInterceptor() != null) {
            bundle = new Bundle();
            bundle.putBoolean(CreateOptions.DoNotIntercept, true);
        } else {
            bundle = null;
        }
        if (TextUtils.isEmpty(this.mAlias)) {
            this.mService = (T) lookup.create(getServiceType(), bundle);
        } else {
            this.mService = (T) lookup.create(getServiceType(), this.mAlias, bundle);
        }
        if (this.mService == null) {
            throw new ApiNotExistException();
        }
    }

    public void setService(T t) {
        this.mService = t;
    }

    public T service() {
        return this.mService;
    }

    public Object onTransact(String str, Object... objArr) {
        throw new ApiNotExistException();
    }

    protected Object cast(TypeToken typeToken, Object obj) {
        return ((Variant) obj).cast(typeToken.getType());
    }

    protected Object[] cast(String str, Object... objArr) {
        List<TypeToken> list = methodProto.get(str);
        if (list == null || list.size() != objArr.length) {
            return new Object[0];
        }
        Object[] objArr2 = new Object[objArr.length];
        for (int i = 0; i < objArr.length; i++) {
            objArr2[i] = cast(list.get(i), objArr[i]);
        }
        return objArr2;
    }
}
