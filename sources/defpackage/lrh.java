package defpackage;

import android.text.TextUtils;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hms.framework.network.restclient.Headers;
import com.huawei.hms.framework.network.restclient.Submit;
import com.huawei.hms.framework.network.restclient.hianalytics.RequestFinishedInfo;
import com.huawei.hms.framework.network.restclient.hwhttp.Request;
import com.huawei.hms.framework.network.restclient.hwhttp.Response;
import com.huawei.operation.utils.Constants;
import com.huawei.phoneservice.faq.base.constants.TrackConstants$Events;
import health.compact.a.BuildConfigProperties;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class lrh {
    public static void c(int i, Request request) {
        String str;
        HashMap hashMap = new HashMap(5);
        hashMap.put("request id", Integer.valueOf(i));
        hashMap.put("request url", request.getUrl().getUrl());
        hashMap.put("request method", request.getMethod());
        Headers headers = request.getHeaders();
        HashMap hashMap2 = new HashMap(5);
        for (String str2 : headers.names()) {
            hashMap2.put(str2, headers.values(str2));
        }
        hashMap.put("headers", hashMap2);
        if (d()) {
            if (request.getBody() != null) {
                byte[] body = request.getBody().body();
                str = body != null ? new String(body, StandardCharsets.UTF_8) : "empty";
            } else {
                str = Constants.NULL;
            }
            hashMap.put("request body", str);
        }
        LogUtil.d("R_NetworkClientUtils", hashMap.toString());
    }

    public static Response e(int i, Response response) {
        String str;
        if (!response.isSuccessful()) {
            LogUtil.a("R_NetworkClientUtils", "response failed, url:" + e(String.valueOf(response.getUrl())) + ", response code:" + response.getCode() + ", message:" + response.getMessage());
        }
        HashMap hashMap = new HashMap(5);
        hashMap.put("request id", Integer.valueOf(i));
        hashMap.put("status_code", Integer.valueOf(response.getCode()));
        Headers headers = response.getHeaders();
        HashMap hashMap2 = new HashMap(5);
        for (String str2 : headers.names()) {
            hashMap2.put(str2, headers.values(str2));
        }
        hashMap.put("headers", hashMap2);
        if (!d()) {
            str = "no print due to un-debug version";
        } else if (response.getHeaders().get("Content-Type") == null || !response.getHeaders().get("Content-Type").contains("json")) {
            str = "binary stream";
        } else {
            InputStream inputStream = response.getBody().getInputStream();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[1024];
            while (true) {
                try {
                    int read = inputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, read);
                } catch (IOException unused) {
                    str = TrackConstants$Events.EXCEPTION;
                }
            }
            byteArrayOutputStream.flush();
            str = byteArrayOutputStream.toString("utf-8");
            response = response.newBuilder().body(response.getBody().newBuilder().inputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray())).build()).build();
        }
        hashMap.put("response body", str);
        LogUtil.d("R_NetworkClientUtils", hashMap.toString());
        return response;
    }

    private static boolean d() {
        return LogUtil.e() || BuildConfigProperties.e("IS_TEST_VERSION", false) || BuildConfigProperties.e("FORCE_ALL_LOG", false);
    }

    public static void a(Submit submit) {
        RequestFinishedInfo requestFinishedInfo;
        try {
            requestFinishedInfo = submit.request().getRequestFinishedInfo();
        } catch (IOException e) {
            ReleaseLogUtil.c("R_NetworkClientUtils", "get Request from submit fail", ExceptionUtils.d(e));
            requestFinishedInfo = null;
        }
        if (requestFinishedInfo == null) {
            ReleaseLogUtil.a("R_NetworkClientUtils", "RequestFinishedInfo is null");
            return;
        }
        RequestFinishedInfo.MetricsTime metricsRealTime = requestFinishedInfo.getMetricsRealTime();
        if (metricsRealTime == null) {
            ReleaseLogUtil.a("R_NetworkClientUtils", "metricsTime is null");
            return;
        }
        String str = "requestUrl:" + e(requestFinishedInfo.getUrl()) + ", totalTime:" + metricsRealTime.getTotalTime() + ", DnsTime:" + (metricsRealTime.getDnsEndTime() - metricsRealTime.getDnsStartTime()) + ", connectTime:" + (metricsRealTime.getConnectEndTime() - metricsRealTime.getConnectStartTime()) + ", sslTime:" + (metricsRealTime.getSecureConnectEndTime() - metricsRealTime.getSecureConnectStartTime()) + ", firstPackage:" + metricsRealTime.getTtfb();
        if (metricsRealTime.getTotalTime() > 300) {
            LogUtil.a("R_NetworkClientUtils", str);
        } else {
            LogUtil.d("R_NetworkClientUtils", str);
        }
    }

    private static String e(String str) {
        if (!LogUtil.a() || TextUtils.isEmpty(str)) {
            return str;
        }
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (i % 2 != 0) {
                charArray[i] = '*';
            }
        }
        return new String(charArray);
    }
}
