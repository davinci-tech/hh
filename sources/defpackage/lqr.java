package defpackage;

import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.common.os.FileUtils;
import com.huawei.hms.framework.network.restclient.Converter;
import com.huawei.hms.framework.network.restclient.RestClient;
import com.huawei.hms.framework.network.restclient.hwhttp.RequestBody;
import com.huawei.hms.framework.network.restclient.hwhttp.ResponseBody;
import health.compact.a.LogUtil;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

/* loaded from: classes5.dex */
public class lqr extends Converter.Factory {
    @Override // com.huawei.hms.framework.network.restclient.Converter.Factory
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotationArr, RestClient restClient) {
        if (String.class.equals(type)) {
            return new Converter() { // from class: lqt
                @Override // com.huawei.hms.framework.network.restclient.Converter
                public final Object convert(Object obj) {
                    return lqr.d((ResponseBody) obj);
                }
            };
        }
        return null;
    }

    static /* synthetic */ String d(ResponseBody responseBody) throws IOException {
        InputStream inputStream = responseBody.getInputStream();
        if (inputStream == null) {
            LogUtil.e("StringConverterFactory", " response body null");
            return "";
        }
        int available = inputStream.available() > 0 ? inputStream.available() : 2048;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(available);
        try {
            try {
                byte[] bArr = new byte[Math.min(available, 2048)];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, read);
                }
                byteArrayOutputStream.flush();
            } catch (IOException e) {
                LogUtil.a("StringConverterFactory", "convert to String error:", ExceptionUtils.d(e));
            }
            FileUtils.d(inputStream);
            FileUtils.d(byteArrayOutputStream);
            return byteArrayOutputStream.toString("UTF-8");
        } catch (Throwable th) {
            FileUtils.d(inputStream);
            FileUtils.d(byteArrayOutputStream);
            throw th;
        }
    }

    @Override // com.huawei.hms.framework.network.restclient.Converter.Factory
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] annotationArr, Annotation[] annotationArr2, RestClient restClient) {
        if (String.class.equals(type)) {
            return new Converter() { // from class: lqu
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
