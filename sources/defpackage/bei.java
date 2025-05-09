package defpackage;

import android.text.TextUtils;
import com.huawei.hms.network.httpclient.Interceptor;
import com.huawei.hms.network.httpclient.Request;
import com.huawei.hms.network.httpclient.RequestBody;
import com.huawei.hms.network.httpclient.Response;
import com.huawei.hms.network.httpclient.ResponseBody;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/* loaded from: classes3.dex */
public class bei extends Interceptor {
    @Override // com.huawei.hms.network.httpclient.Interceptor
    public Response<ResponseBody> intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain != null ? chain.request() : null;
        if (request == null) {
            return null;
        }
        bes.e("networkKit", "--> " + request.getMethod() + " " + request.getUrl());
        final Map<String, List<String>> headers = request.getHeaders();
        if (headers != null) {
            headers.keySet().forEach(new Consumer() { // from class: beh
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    bes.e("networkKit", r2 + ": " + headers.get((String) obj));
                }
            });
        }
        RequestBody body = request.getBody();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        String str = "";
        if (body != null) {
            body.writeTo(byteArrayOutputStream);
            bes.e("networkKit", "" + byteArrayOutputStream);
            bes.e("networkKit", "--> END " + request.getMethod());
        }
        Response<ResponseBody> proceed = chain.proceed(request);
        if (proceed != null) {
            String message = proceed.getMessage();
            if (!TextUtils.isEmpty(message)) {
                str = message + proceed.getUrl();
            }
            bes.e("networkKit", "<-- " + proceed.getCode() + " " + str);
            final Map<String, List<String>> headers2 = proceed.getHeaders();
            if (headers2 != null) {
                headers2.keySet().forEach(new Consumer() { // from class: ben
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        bes.e("networkKit", r2 + ": " + headers2.get((String) obj));
                    }
                });
            }
        } else {
            bes.e("networkKit", "<-- ");
        }
        return proceed;
    }
}
