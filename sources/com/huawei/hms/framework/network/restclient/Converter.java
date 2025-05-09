package com.huawei.hms.framework.network.restclient;

import com.huawei.hms.framework.network.restclient.hwhttp.RequestBody;
import com.huawei.hms.framework.network.restclient.hwhttp.ResponseBody;
import com.huawei.hms.network.base.util.TypeUtils;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

@Deprecated
/* loaded from: classes4.dex */
public interface Converter<F, T> {
    T convert(F f) throws IOException;

    @Deprecated
    public static abstract class Factory {
        public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] annotationArr, Annotation[] annotationArr2, RestClient restClient) {
            return null;
        }

        public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotationArr, RestClient restClient) {
            return null;
        }

        public Converter<?, String> stringConverter(Type type, Annotation[] annotationArr, RestClient restClient) {
            return null;
        }

        protected static Type getParameterUpperBound(int i, ParameterizedType parameterizedType) {
            return TypeUtils.getParameterUpperBound(i, parameterizedType);
        }

        protected static Class<?> getRawType(Type type) {
            return TypeUtils.getRawType(type);
        }
    }
}
