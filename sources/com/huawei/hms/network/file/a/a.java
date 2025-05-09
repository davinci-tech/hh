package com.huawei.hms.network.file.a;

import com.huawei.hms.framework.common.ExecutorsUtils;
import com.huawei.hms.network.file.api.GlobalRequestConfig;
import com.huawei.hms.network.file.core.Constants;
import com.huawei.hms.network.file.core.FileManagerException;
import com.huawei.hms.network.file.core.task.k;
import com.huawei.hms.network.file.core.util.FLogger;
import com.huawei.hms.network.file.core.util.Utils;
import com.huawei.hms.network.file.download.api.GetRequest;
import com.huawei.hms.network.httpclient.HttpClient;
import com.huawei.hms.network.httpclient.Request;
import com.huawei.hms.network.httpclient.Response;
import com.huawei.hms.network.httpclient.ResponseBody;
import com.huawei.hms.network.httpclient.Submit;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class a implements com.huawei.hms.network.file.core.task.b<GetRequest, d> {
    private d d;
    private HttpClient e;
    Submit<ResponseBody> f;
    private GlobalRequestConfig k;
    i l;

    /* renamed from: a, reason: collision with root package name */
    private String f5596a = "";
    private String b = "";
    String c = "";
    long g = 0;
    private int h = 0;
    private int i = 0;
    private com.huawei.hms.network.file.core.task.f j = null;

    boolean a(String str) {
        File file = new File(str);
        if (file.exists() || file.getParentFile() == null) {
            return false;
        }
        return file.getParentFile().mkdirs();
    }

    void a(Submit<ResponseBody> submit, k<GetRequest> kVar) {
        if (submit != null) {
            try {
                if (submit.getRequestFinishedInfo() != null) {
                    kVar.b(submit.getRequestFinishedInfo());
                }
            } catch (RuntimeException e) {
                FLogger.w("DownloadImpl", "collectFinishedInfo RuntimeException", e);
            } catch (Exception e2) {
                FLogger.w("DownloadImpl", "collectFinishedInfo exception", e2);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:78:0x03d1 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:85:0x03da A[SYNTHETIC] */
    @Override // com.huawei.hms.network.file.core.task.b
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.io.Closeable a(com.huawei.hms.network.file.a.d r23, com.huawei.hms.network.file.core.task.f r24) {
        /*
            Method dump skipped, instructions count: 1041
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.network.file.a.a.a(com.huawei.hms.network.file.a.d, com.huawei.hms.network.file.core.task.f):java.io.Closeable");
    }

    private boolean b(IOException iOException) {
        return com.huawei.hms.network.file.core.util.b.a((Throwable) iOException) && this.i <= this.h;
    }

    private StringBuilder b(String str, Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        if (str.indexOf(38) > 0 || str.indexOf(63) > 0) {
            sb.append("&");
        } else {
            sb.append("?");
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String encode = URLEncoder.encode(entry.getValue(), "UTF-8");
            sb.append(entry.getKey());
            sb.append("=");
            sb.append(encode);
            sb.append("&");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb;
    }

    private void a(IOException iOException) {
        String message = !Utils.isBlank(iOException.getMessage()) ? iOException.getMessage() : "IO exception";
        this.i++;
        int a2 = com.huawei.hms.network.file.core.util.b.a(iOException);
        if (b(iOException)) {
            return;
        }
        FLogger.e("DownloadImpl", "doWithIOException failed, retrycount=" + this.i + ", code=" + a2);
        throw new FileManagerException(a2, message, iOException);
    }

    /* JADX WARN: Code restructure failed: missing block: B:112:0x011e, code lost:
    
        if (java.lang.System.currentTimeMillis() > (r16 + 1000)) goto L45;
     */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0201 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:38:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0156  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0198  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void a(java.io.BufferedInputStream r30, java.io.RandomAccessFile r31, java.lang.String r32, long r33) {
        /*
            Method dump skipped, instructions count: 523
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.network.file.a.a.a(java.io.BufferedInputStream, java.io.RandomAccessFile, java.lang.String, long):void");
    }

    private void a(int i, long j, long j2) {
        String str = "[responseCode=" + i + ", hostStr=" + this.b + "]";
        if (i == -1) {
            throw new FileManagerException(Constants.ErrorCode.SERVER_EXCEPTION, str + " download failed,response null, hostStr=" + this.b);
        }
        if (i != 416) {
            if (i != 200 && i != 206 && i != 304) {
                throw new FileManagerException(i, str);
            }
            return;
        }
        String str2 = str + " package= " + this.d.f() + ", storeSize=" + this.d.a() + ", rangeStart=" + j + ", rangeEnd=" + j2;
        FLogger.w("DownloadImpl", "httpErrorcode:" + i, new Object[0]);
        throw new FileManagerException(i, str2);
    }

    private void a() {
        this.j.a((com.huawei.hms.network.file.core.task.f) this.d);
    }

    private String a(String str, Map<String, String> map) {
        if (map != null && !map.isEmpty()) {
            try {
                return b(str, map).toString();
            } catch (UnsupportedEncodingException e) {
                FLogger.e("DownloadImpl", "createUrlWithParams errorr:", e);
            }
        }
        return str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Response a(Throwable[] thArr) {
        try {
            return this.f.execute();
        } catch (IOException e) {
            FLogger.e("DownloadImpl", "execute ioexception");
            thArr[0] = e;
            return null;
        }
    }

    private Response<ResponseBody> a(String str, long j, long j2, d dVar) {
        Request.Builder newRequest = this.e.newRequest();
        newRequest.options(Utils.convertToJsonString(this.k));
        newRequest.url(str);
        if (dVar.C()) {
            FLogger.i("DownloadImpl", "getDownloadResult needAddRangHeader for start:" + j + ",end:" + j2, new Object[0]);
            String str2 = (j < 0 || j2 < j) ? "" : "bytes=" + j + com.huawei.openalliance.ad.constant.Constants.LINK + j2;
            if (j2 == -1 && j > 0) {
                str2 = "bytes=" + j + com.huawei.openalliance.ad.constant.Constants.LINK;
            }
            if (!Utils.isBlank(str2)) {
                FLogger.i("DownloadImpl", "getDownloadResult add range for slice", new Object[0]);
                newRequest.addHeader("Range", str2);
            }
        }
        Map<String, String> headers = dVar.p().getHeaders();
        if (!headers.isEmpty()) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                newRequest.addHeader(entry.getKey(), entry.getValue());
            }
        }
        HashMap hashMap = new HashMap();
        if (!dVar.p().getReportInfos().isEmpty()) {
            hashMap.putAll(dVar.p().getReportInfos());
        }
        if (!hashMap.containsKey("trace_id")) {
            hashMap.put("trace_id", String.valueOf(dVar.p().getId()));
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
            newRequest.options(jSONObject2.toString());
        } catch (JSONException unused) {
            FLogger.e("DownloadImpl", "getDownloadResult requestBuilder.options exception");
        }
        this.f = this.e.newSubmit(newRequest.build());
        if (dVar.h()) {
            this.f.cancel();
        }
        dVar.a(this.f);
        final Throwable[] thArr = new Throwable[1];
        Future submit = ExecutorsUtils.newSingleThreadExecutor("DownloadImpl").submit(new Callable() { // from class: com.huawei.hms.network.file.a.a$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                Response a2;
                a2 = a.this.a(thArr);
                return a2;
            }
        });
        Response<ResponseBody> response = (submit.isCancelled() || dVar.h()) ? null : (Response) submit.get(2L, TimeUnit.MINUTES);
        Throwable th = thArr[0];
        if (th == null) {
            return response;
        }
        throw th;
    }

    private int a(RandomAccessFile randomAccessFile, byte[] bArr, int i) {
        randomAccessFile.write(bArr, 0, i);
        d dVar = this.d;
        long j = i;
        dVar.a(dVar.c() + j);
        this.g += j;
        a();
        return 0;
    }

    private int a(int i) {
        i iVar = this.l;
        return iVar != null ? iVar.a(i) : i;
    }

    public a(GlobalRequestConfig globalRequestConfig, HttpClient httpClient) {
        this.e = httpClient;
        this.k = globalRequestConfig;
    }
}
