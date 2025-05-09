package com.huawei.hms.network.embedded;

import com.huawei.hms.network.base.common.RequestBodyProviders;
import com.huawei.hms.network.httpclient.RequestBody;
import com.huawei.hms.network.httpclient.ResponseBody;
import com.huawei.hms.network.restclient.Converter;
import com.huawei.hms.network.restclient.RestClient;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/* loaded from: classes9.dex */
public class l6 extends Converter.Factory {

    /* renamed from: a, reason: collision with root package name */
    public static final String f5360a = "text/plain; charset=UTF-8";

    public class a extends Converter<ResponseBody, String> {
        @Override // com.huawei.hms.network.restclient.Converter
        public String convert(ResponseBody responseBody) throws IOException {
            return new String(responseBody.bytes(), "UTF-8");
        }

        public a() {
        }
    }

    public class b extends Converter<String, RequestBody> {
        @Override // com.huawei.hms.network.restclient.Converter
        public RequestBody convert(String str) throws IOException {
            return RequestBodyProviders.create("text/plain; charset=UTF-8", str.getBytes("UTF-8"));
        }

        public b() {
        }
    }

    @Override // com.huawei.hms.network.restclient.Converter.Factory
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotationArr, RestClient restClient) {
        if (String.class.equals(type)) {
            return new a();
        }
        return null;
    }

    @Override // com.huawei.hms.network.restclient.Converter.Factory
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] annotationArr, Annotation[] annotationArr2, RestClient restClient) {
        if (String.class.equals(type)) {
            return new b();
        }
        return null;
    }
}
