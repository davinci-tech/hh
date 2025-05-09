package com.huawei.hms.framework.network.restclient.proxy;

import com.huawei.hms.framework.network.restclient.Converter;
import com.huawei.hms.framework.network.restclient.proxy.new2old.ProxyRequestBody;
import com.huawei.hms.framework.network.restclient.proxy.new2old.ProxyResponseBody;
import com.huawei.hms.network.httpclient.RequestBody;
import com.huawei.hms.network.httpclient.ResponseBody;
import com.huawei.hms.network.restclient.Converter;
import com.huawei.hms.network.restclient.RestClient;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/* loaded from: classes4.dex */
public class ProxyConverter<F, T> extends Converter<F, T> {
    private final com.huawei.hms.framework.network.restclient.Converter<F, T> proxyConverter;

    public ProxyConverter(com.huawei.hms.framework.network.restclient.Converter<F, T> converter) {
        this.proxyConverter = converter;
    }

    @Override // com.huawei.hms.network.restclient.Converter
    public T convert(F f) throws IOException {
        return this.proxyConverter.convert(f);
    }

    public static class ProxyConverterFactory extends Converter.Factory {
        private final Converter.Factory proxy;

        public ProxyConverterFactory(Converter.Factory factory) {
            this.proxy = factory;
        }

        @Override // com.huawei.hms.network.restclient.Converter.Factory
        public com.huawei.hms.network.restclient.Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotationArr, RestClient restClient) {
            final com.huawei.hms.framework.network.restclient.Converter<com.huawei.hms.framework.network.restclient.hwhttp.ResponseBody, ?> responseBodyConverter = this.proxy.responseBodyConverter(type, annotationArr, new com.huawei.hms.framework.network.restclient.RestClient(restClient));
            if (responseBodyConverter != null) {
                return new com.huawei.hms.network.restclient.Converter<ResponseBody, Object>() { // from class: com.huawei.hms.framework.network.restclient.proxy.ProxyConverter.ProxyConverterFactory.1
                    @Override // com.huawei.hms.network.restclient.Converter
                    public Object convert(ResponseBody responseBody) throws IOException {
                        return responseBodyConverter.convert(ProxyResponseBody.response2Old(responseBody));
                    }
                };
            }
            return null;
        }

        @Override // com.huawei.hms.network.restclient.Converter.Factory
        public com.huawei.hms.network.restclient.Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] annotationArr, Annotation[] annotationArr2, RestClient restClient) {
            final com.huawei.hms.framework.network.restclient.Converter<?, com.huawei.hms.framework.network.restclient.hwhttp.RequestBody> requestBodyConverter = this.proxy.requestBodyConverter(type, annotationArr, annotationArr2, new com.huawei.hms.framework.network.restclient.RestClient(restClient));
            if (requestBodyConverter != null) {
                return new com.huawei.hms.network.restclient.Converter<Object, RequestBody>() { // from class: com.huawei.hms.framework.network.restclient.proxy.ProxyConverter.ProxyConverterFactory.2
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.huawei.hms.network.restclient.Converter
                    public RequestBody convert(Object obj) throws IOException {
                        return ProxyRequestBody.requestBody2New((com.huawei.hms.framework.network.restclient.hwhttp.RequestBody) requestBodyConverter.convert(obj));
                    }
                };
            }
            return null;
        }

        @Override // com.huawei.hms.network.restclient.Converter.Factory
        public com.huawei.hms.network.restclient.Converter<?, String> stringConverter(Type type, Annotation[] annotationArr, RestClient restClient) {
            final com.huawei.hms.framework.network.restclient.Converter<?, String> stringConverter = this.proxy.stringConverter(type, annotationArr, new com.huawei.hms.framework.network.restclient.RestClient(restClient));
            if (stringConverter != null) {
                return new com.huawei.hms.network.restclient.Converter<Object, String>() { // from class: com.huawei.hms.framework.network.restclient.proxy.ProxyConverter.ProxyConverterFactory.3
                    @Override // com.huawei.hms.network.restclient.Converter
                    public String convert(Object obj) throws IOException {
                        return (String) stringConverter.convert(obj);
                    }
                };
            }
            return null;
        }
    }
}
