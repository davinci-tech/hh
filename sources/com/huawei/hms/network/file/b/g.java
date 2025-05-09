package com.huawei.hms.network.file.b;

import android.text.TextUtils;
import com.huawei.hms.network.file.api.GlobalRequestConfig;
import com.huawei.hms.network.file.core.FileManagerException;
import com.huawei.hms.network.file.core.task.k;
import com.huawei.hms.network.file.core.util.FLogger;
import com.huawei.hms.network.file.core.util.Utils;
import com.huawei.hms.network.file.upload.api.BodyRequest;
import com.huawei.hms.network.file.upload.api.PostRequest;
import com.huawei.hms.network.httpclient.HttpClient;
import com.huawei.hms.network.httpclient.Request;
import com.huawei.hms.network.httpclient.RequestBody;
import com.huawei.hms.network.httpclient.Response;
import com.huawei.hms.network.httpclient.ResponseBody;
import com.huawei.hms.network.httpclient.Submit;
import com.huawei.profile.coordinator.ProfileRequestConstants;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class g {

    /* renamed from: a, reason: collision with root package name */
    private HttpClient f5620a;
    private GlobalRequestConfig b;
    private com.huawei.hms.network.file.core.task.f c;

    void a(Submit submit, k<BodyRequest> kVar) {
        if (submit != null) {
            try {
                if (submit.getRequestFinishedInfo() != null) {
                    kVar.b(submit.getRequestFinishedInfo());
                }
            } catch (RuntimeException unused) {
                FLogger.w("UploadUtils", "collectFinishedInfo RuntimeException", new Object[0]);
            } catch (Exception unused2) {
                FLogger.w("UploadUtils", "collectFinishedInfo exception", new Object[0]);
            }
        }
    }

    void a(k<BodyRequest> kVar) {
        kVar.a(0L);
    }

    public Response a(RequestBody requestBody, f fVar, com.huawei.hms.network.file.core.task.f fVar2) {
        this.c = fVar2;
        IOException e = null;
        if (this.f5620a == null || TextUtils.isEmpty(fVar.v())) {
            return null;
        }
        int b = b(fVar);
        String str = "IO exception";
        int i = 0;
        int i2 = 0;
        while (i2 <= b && !fVar.h()) {
            a(fVar);
            try {
                FLogger.i("UploadUtils", "before connect, task:" + fVar.p().getId() + ", taskFileTotalSize:" + fVar.a() + ",configRetryCount:" + b + ",sliceRetryCount:" + i2, new Object[0]);
                Response a2 = a(requestBody, fVar);
                fVar.f(System.currentTimeMillis() - fVar.y());
                StringBuilder sb = new StringBuilder();
                sb.append("upload responsecode:");
                sb.append(a2.getCode());
                FLogger.i("UploadUtils", sb.toString(), new Object[0]);
                return a2;
            } catch (IOException e2) {
                e = e2;
                if (!(e.getCause() instanceof FileManagerException)) {
                    if (!TextUtils.isEmpty(str)) {
                        str = e.getMessage();
                    }
                    i = com.huawei.hms.network.file.core.util.b.a(e);
                    if (!com.huawei.hms.network.file.core.util.b.a((Throwable) e)) {
                        break;
                    }
                    i2++;
                } else {
                    throw ((FileManagerException) Utils.cast(e.getCause()));
                }
            }
        }
        FLogger.w("UploadUtils", "doWithIOException failed, retrycount=" + i2 + ", code=" + i, new Object[0]);
        fVar.a(i2);
        throw new FileManagerException(i, str, e);
    }

    private int b(k<BodyRequest> kVar) {
        if (kVar.p().getConfig() == null || this.b.getRetryTimes() == -100) {
            return 0;
        }
        return this.b.getRetryTimes();
    }

    private Response a(RequestBody requestBody, f fVar) {
        String str = fVar.p() instanceof PostRequest ? "POST" : ProfileRequestConstants.PUT_TYPE;
        FLogger.i("UploadUtils", "callUpload method:".concat(str), new Object[0]);
        Request.Builder url = this.f5620a.newRequest().url(fVar.v());
        url.options(Utils.convertToJsonString(this.b));
        Set<Map.Entry<String, String>> entrySet = fVar.p().getHeaders().entrySet();
        if (entrySet.size() > 0) {
            for (Map.Entry<String, String> entry : entrySet) {
                url.addHeader(entry.getKey(), entry.getValue());
            }
        }
        HashMap hashMap = new HashMap();
        if (!fVar.p().getReportInfos().isEmpty()) {
            hashMap.putAll(fVar.p().getReportInfos());
        }
        if (!hashMap.containsKey("trace_id")) {
            hashMap.put("trace_id", String.valueOf(fVar.p().getId()));
        }
        try {
            JSONObject jSONObject = new JSONObject();
            if (hashMap.containsKey("trace_id")) {
                jSONObject.put("trace_id", hashMap.get("trace_id"));
            }
            if (hashMap.containsKey("device_id")) {
                jSONObject.put("device_id", hashMap.get("device_id"));
            }
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("metrics_data", jSONObject);
            url.options(jSONObject2.toString());
        } catch (JSONException unused) {
            FLogger.e("UploadUtils", "callUpload requestBuilder.options exception");
        }
        url.method(str);
        url.requestBody(requestBody);
        Submit<ResponseBody> newSubmit = this.f5620a.newSubmit(url.build());
        com.huawei.hms.network.file.core.task.f fVar2 = this.c;
        if (fVar2 != null) {
            fVar2.a((Submit) newSubmit);
        }
        fVar.b(System.currentTimeMillis());
        Response<ResponseBody> execute = newSubmit.execute();
        a(newSubmit, fVar);
        return execute;
    }

    public g(GlobalRequestConfig globalRequestConfig, HttpClient httpClient) {
        this.f5620a = httpClient;
        this.b = globalRequestConfig;
    }
}
