package defpackage;

import com.huawei.hms.framework.network.restclient.Converter;
import com.huawei.hms.framework.network.restclient.RestClient;
import com.huawei.hms.framework.network.restclient.hwhttp.RequestBody;
import com.huawei.hms.framework.network.restclient.hwhttp.ResponseBody;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

/* loaded from: classes5.dex */
public class lqm extends Converter.Factory {
    static /* synthetic */ ResponseBody d(ResponseBody responseBody) throws IOException {
        return responseBody;
    }

    @Override // com.huawei.hms.framework.network.restclient.Converter.Factory
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotationArr, RestClient restClient) {
        if (ResponseBody.class.equals(type)) {
            return new Converter() { // from class: lqn
                @Override // com.huawei.hms.framework.network.restclient.Converter
                public final Object convert(Object obj) {
                    return lqm.d((ResponseBody) obj);
                }
            };
        }
        return null;
    }

    @Override // com.huawei.hms.framework.network.restclient.Converter.Factory
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] annotationArr, Annotation[] annotationArr2, RestClient restClient) {
        if (String.class.equals(type)) {
            return new Converter() { // from class: lqo
                @Override // com.huawei.hms.framework.network.restclient.Converter
                public final Object convert(Object obj) {
                    RequestBody create;
                    create = RequestBody.create("text/plain; charset=UTF-8", ((String) obj).getBytes(StandardCharsets.UTF_8));
                    return create;
                }
            };
        }
        return null;
    }
}
