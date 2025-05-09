package com.huawei.hms.framework.network.restclient;

import com.huawei.hms.network.base.util.TypeUtils;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

@Deprecated
/* loaded from: classes4.dex */
public interface SubmitAdapter<R, T> {
    /* renamed from: adapt */
    T adapt2(Submit<R> submit);

    Type responseType();

    @Deprecated
    public static abstract class Factory {
        public abstract SubmitAdapter<?, ?> get(Type type, Annotation[] annotationArr, RestClient restClient);

        protected static Type getParameterUpperBound(int i, ParameterizedType parameterizedType) {
            return TypeUtils.getParameterUpperBound(i, parameterizedType);
        }

        protected static Class<?> getRawType(Type type) {
            return TypeUtils.getRawType(type);
        }
    }
}
