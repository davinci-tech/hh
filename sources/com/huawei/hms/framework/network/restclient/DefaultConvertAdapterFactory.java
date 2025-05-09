package com.huawei.hms.framework.network.restclient;

import com.huawei.hms.framework.network.restclient.Converter;
import com.huawei.hms.framework.network.restclient.hwhttp.RequestBody;
import com.huawei.hms.framework.network.restclient.hwhttp.ResponseBody;
import com.huawei.hms.network.base.util.TypeUtils;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/* loaded from: classes4.dex */
public class DefaultConvertAdapterFactory extends Converter.Factory {
    static final DefaultConvertAdapterFactory INSTANCE = new DefaultConvertAdapterFactory();

    @Override // com.huawei.hms.framework.network.restclient.Converter.Factory
    public Converter<ResponseBody, ResponseBody> responseBodyConverter(Type type, Annotation[] annotationArr, RestClient restClient) {
        if (type == ResponseBody.class) {
            return ResponseBodyConverter.INSTANCE;
        }
        return null;
    }

    @Override // com.huawei.hms.framework.network.restclient.Converter.Factory
    public Converter<RequestBody, RequestBody> requestBodyConverter(Type type, Annotation[] annotationArr, Annotation[] annotationArr2, RestClient restClient) {
        if (RequestBody.class.isAssignableFrom(TypeUtils.getRawType(type))) {
            return RequestBodyConverter.INSTANCE;
        }
        return null;
    }

    /* loaded from: classes9.dex */
    static final class RequestBodyConverter implements Converter<RequestBody, RequestBody> {
        static final RequestBodyConverter INSTANCE = new RequestBodyConverter();

        @Override // com.huawei.hms.framework.network.restclient.Converter
        public RequestBody convert(RequestBody requestBody) {
            return requestBody;
        }

        RequestBodyConverter() {
        }
    }

    /* loaded from: classes9.dex */
    static final class ResponseBodyConverter implements Converter<ResponseBody, ResponseBody> {
        static final ResponseBodyConverter INSTANCE = new ResponseBodyConverter();

        @Override // com.huawei.hms.framework.network.restclient.Converter
        public ResponseBody convert(ResponseBody responseBody) {
            return responseBody;
        }

        ResponseBodyConverter() {
        }
    }
}
