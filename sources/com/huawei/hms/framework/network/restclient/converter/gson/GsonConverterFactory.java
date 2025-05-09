package com.huawei.hms.framework.network.restclient.converter.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huawei.hms.framework.network.restclient.Converter;
import com.huawei.hms.framework.network.restclient.RestClient;
import com.huawei.hms.framework.network.restclient.hwhttp.RequestBody;
import com.huawei.hms.framework.network.restclient.hwhttp.ResponseBody;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

@Deprecated
/* loaded from: classes4.dex */
public final class GsonConverterFactory extends Converter.Factory {
    private final Gson gson;

    private GsonConverterFactory(Gson gson) {
        this.gson = gson;
    }

    public static GsonConverterFactory create() {
        return create(new Gson());
    }

    public static GsonConverterFactory create(Gson gson) {
        if (gson == null) {
            throw new NullPointerException("gson == null");
        }
        return new GsonConverterFactory(gson);
    }

    @Override // com.huawei.hms.framework.network.restclient.Converter.Factory
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotationArr, RestClient restClient) {
        return new GsonResponseBodyConverter(this.gson, this.gson.getAdapter(TypeToken.get(type)));
    }

    @Override // com.huawei.hms.framework.network.restclient.Converter.Factory
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] annotationArr, Annotation[] annotationArr2, RestClient restClient) {
        return new GsonRequestBodyConverter(this.gson);
    }
}
