package com.huawei.hms.framework.network.restclient.proxy.new2old;

import com.huawei.hms.framework.network.restclient.hwhttp.ResponseBody;
import com.huawei.hms.framework.network.restclient.proxy.new2old.ResponseBodyImp;

/* loaded from: classes9.dex */
public class ProxyResponseBody {
    public static ResponseBody response2Old(com.huawei.hms.network.httpclient.ResponseBody responseBody) {
        ResponseBody.Builder builder = new ResponseBody.Builder();
        builder.contentType(responseBody.getContentType()).contentLength(responseBody.getContentLength());
        if (responseBody.getInputStream() != null) {
            builder.inputStream(responseBody.getInputStream());
        }
        ResponseBody build = builder.build();
        build.setReader(responseBody.charStream());
        return build;
    }

    public static com.huawei.hms.network.httpclient.ResponseBody response2New(ResponseBody responseBody) {
        ResponseBodyImp.Builder builder = new ResponseBodyImp.Builder();
        builder.contentType(responseBody.getContentType()).contentLength(responseBody.getContentLength());
        if (responseBody.getInputStream() != null) {
            builder.inputStream(responseBody.getInputStream()).charSet(responseBody.getCharSet());
        }
        return builder.build();
    }
}
