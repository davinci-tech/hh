package defpackage;

import android.os.Build;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import com.google.gson.JsonSyntaxException;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.common.os.FileUtils;
import com.huawei.haf.common.utils.NetworkUtil;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hms.framework.network.grs.GrsBaseInfo;
import com.huawei.hms.framework.network.grs.GrsClient;
import com.huawei.hms.framework.network.restclient.Headers;
import com.huawei.hms.framework.network.restclient.Response;
import com.huawei.hms.framework.network.restclient.RestClient;
import com.huawei.hms.framework.network.restclient.RestClientGlobalInstance;
import com.huawei.hms.framework.network.restclient.Submit;
import com.huawei.hms.framework.network.restclient.converter.gson.GsonConverterFactory;
import com.huawei.hms.framework.network.restclient.hianalytics.RequestFinishedInfo;
import com.huawei.hms.framework.network.restclient.hwhttp.HttpClient;
import com.huawei.hms.framework.network.restclient.hwhttp.Interceptor;
import com.huawei.hms.framework.network.restclient.hwhttp.Request;
import com.huawei.hms.framework.network.restclient.hwhttp.RequestBody;
import com.huawei.hms.framework.network.restclient.hwhttp.ResponseBody;
import com.huawei.hms.framework.network.restclient.hwhttp.url.HttpUrl;
import com.huawei.hms.network.NetworkKit;
import com.huawei.hms.network.embedded.j2;
import com.huawei.hms.network.embedded.w;
import com.huawei.networkclient.CommonApi;
import com.huawei.networkclient.IDownloadStrategy;
import com.huawei.networkclient.ProgressListener;
import com.huawei.networkclient.ResultCallback;
import com.huawei.networkclient.cache.ICacheStrategy;
import com.huawei.networkclient.repository.DataConverterRepository;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.operation.ble.BleConstants;
import com.huawei.secure.android.common.ssl.SSLUtil;
import com.huawei.secure.android.common.ssl.SecureSSLSocketFactoryNew;
import com.huawei.secure.android.common.ssl.SecureX509SingleInstance;
import health.compact.a.BuildConfigProperties;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

/* loaded from: classes.dex */
public final class lqi {

    /* renamed from: a, reason: collision with root package name */
    private static final String f14832a;
    private static final String c;
    private static volatile lqi h;
    private DataConverterRepository f;
    private lqs i;
    private CommonApi j;
    private RestClient o;
    private static ConcurrentHashMap<String, ProgressListener> b = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, Submit<ResponseBody>> e = new ConcurrentHashMap<>();
    private static final Object d = new Object();
    private static long g = 0;

    static {
        String str = BuildConfigProperties.b() == "customTest" ? BleConstants.WEIGHT_KEY : BuildConfigProperties.e("IS_GREEN_VERSION", false) ? "green" : "";
        f14832a = str;
        c = "healthcloud" + str;
        h = null;
    }

    private lqi(boolean z) {
        if (BaseApplication.e().getMainLooper() == Looper.myLooper()) {
            ReleaseLogUtil.c("R_NetworkClientMgr", "init networkKit in main thread");
        }
        try {
            Future init = NetworkKit.init(BaseApplication.e(), new NetworkKit.Callback() { // from class: lqi.3
                @Override // com.huawei.hms.network.NetworkKit.Callback
                public void onResult(boolean z2) {
                    ReleaseLogUtil.b("R_NetworkClientMgr", "init networkKit result is: ", Boolean.valueOf(z2));
                }
            });
            if (init != null) {
                init.get(5000L, TimeUnit.MILLISECONDS);
            }
            NetworkKit.getInstance().initConnectionPool(5, 2L, TimeUnit.MINUTES);
        } catch (IllegalStateException unused) {
            ReleaseLogUtil.c("R_NetworkClientMgr", "NetworkClientMgr init IllegalStateException");
        } catch (InterruptedException unused2) {
            ReleaseLogUtil.c("R_NetworkClientMgr", "NetworkClientMgr init InterruptedException");
        } catch (ExecutionException unused3) {
            ReleaseLogUtil.c("R_NetworkClientMgr", "NetworkClientMgr init ExecutionException");
        } catch (TimeoutException unused4) {
            ReleaseLogUtil.c("R_NetworkClientMgr", "NetworkClientMgr init TimeoutException");
        }
        if (z) {
            return;
        }
        e(b());
    }

    public static lqi d() {
        if (h == null) {
            synchronized (d) {
                if (h == null) {
                    h = new lqi(false);
                }
            }
        }
        return h;
    }

    public static lqi a(HttpClient httpClient) {
        LogUtil.c("NetworkClientMgr", "getDiyInstance");
        lqi lqiVar = new lqi(true);
        lqiVar.e(httpClient);
        return lqiVar;
    }

    public <T> T b(Class<? extends T> cls) {
        RestClient restClient = this.o;
        if (restClient == null) {
            ReleaseLogUtil.c("R_NetworkClientMgr", "not init");
            return null;
        }
        try {
            return (T) restClient.create(cls);
        } catch (IllegalArgumentException e2) {
            ReleaseLogUtil.c("R_NetworkClientMgr", "create network api fail, message is ", ExceptionUtils.d(e2));
            return null;
        }
    }

    public void c(String str) {
        e.remove(str);
        b.remove(str);
    }

    public boolean e(String str) {
        return !e.containsKey(str);
    }

    /* JADX WARN: Not initialized variable reg: 7, insn: 0x0136: MOVE (r0 I:??[OBJECT, ARRAY]) = (r7 I:??[OBJECT, ARRAY]), block:B:54:0x0136 */
    public <Rsp> Rsp d(String str, Map<String, String> map, String str2, Class<Rsp> cls) {
        Closeable closeable;
        ResponseBody responseBody;
        Submit<ResponseBody> submit;
        Response<ResponseBody> execute;
        if (BaseApplication.e().getMainLooper() == Looper.myLooper()) {
            ReleaseLogUtil.c("R_NetworkClientMgr", "network action in main thread");
            LogUtil.e("NetworkClientMgr", "network action in main thread, responseType is ", cls.getSimpleName());
        }
        Closeable closeable2 = (Rsp) null;
        if (!e(map, null)) {
            return null;
        }
        try {
        } catch (Throwable th) {
            th = th;
            closeable2 = closeable;
        }
        try {
            try {
                submit = this.j.commonPostRaw(str, map, str2);
                try {
                    execute = submit.execute();
                    responseBody = execute.getBody();
                } catch (IOException e2) {
                    e = e2;
                    responseBody = null;
                }
            } catch (IOException e3) {
                e = e3;
                submit = null;
                responseBody = null;
            }
            try {
                lrh.a(submit);
                if (responseBody != null) {
                    lra.e(str, str2, responseBody.getContentLength());
                }
                if (execute.isOK()) {
                    closeable2 = (Rsp) this.f.a(cls).convert(execute.getBody(), cls);
                } else {
                    ReleaseLogUtil.a("NetworkClientMgr", cls.getSimpleName(), " status code:", Integer.valueOf(execute.getCode()));
                }
            } catch (JsonSyntaxException e4) {
                e = e4;
                ReleaseLogUtil.c("NetworkClientMgr", cls.getSimpleName(), " string convert error:", ExceptionUtils.d(e), " resBody:", responseBody);
                FileUtils.d(responseBody);
                return (Rsp) closeable2;
            } catch (IOException e5) {
                e = e5;
                ReleaseLogUtil.c("NetworkClientMgr", cls.getSimpleName(), " fail:", ExceptionUtils.d(e));
                String message = e.getMessage();
                if (message != null && message.contains("javax.net.ssl.SSLSocketFactory javax.net.ssl.SSLContext.getSocketFactory()") && message.contains("null object reference")) {
                    try {
                        SecureSSLSocketFactoryNew secureSSLSocketFactoryNew = SecureSSLSocketFactoryNew.getInstance(BaseApplication.e());
                        if (secureSSLSocketFactoryNew.getSslContext() == null) {
                            secureSSLSocketFactoryNew.setContext(BaseApplication.e());
                            SSLContext sSLContext = SSLUtil.setSSLContext();
                            secureSSLSocketFactoryNew.setSslContext(sSLContext);
                            sSLContext.init(null, new TrustManager[]{SecureX509SingleInstance.getInstance(BaseApplication.e())}, null);
                        }
                    } catch (IOException | IllegalAccessException | IllegalArgumentException | KeyManagementException | KeyStoreException | NoSuchAlgorithmException | CertificateException e6) {
                        ReleaseLogUtil.c("NetworkClientMgr", cls.getSimpleName(), " fail to reset SSLContext:", ExceptionUtils.d(e6));
                    }
                }
                e(submit);
                FileUtils.d(responseBody);
                return (Rsp) closeable2;
            }
        } catch (JsonSyntaxException e7) {
            e = e7;
            responseBody = null;
        } catch (Throwable th2) {
            th = th2;
            FileUtils.d(closeable2);
            throw th;
        }
        FileUtils.d(responseBody);
        return (Rsp) closeable2;
    }

    public <Rsp> void b(String str, Map<String, String> map, String str2, Class<Rsp> cls, ResultCallback<Rsp> resultCallback) {
        if (e(map, resultCallback)) {
            Submit<ResponseBody> commonPostRaw = this.j.commonPostRaw(str, map, str2);
            lra.a(str, str2);
            commonPostRaw.enqueue(c(resultCallback, cls, commonPostRaw));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <Rsp> void d(String str, Map<String, String> map, String str2, final Class<Rsp> cls, final ResultCallback<lqq<Rsp>> resultCallback, final ICacheStrategy iCacheStrategy) {
        Rsp rsp;
        ReleaseLogUtil.b("R_NetworkClientMgr", "enter:callHttpPost with cache");
        final String key = iCacheStrategy.getKey();
        final int cacheStrategy = iCacheStrategy.getCacheStrategy();
        final boolean[] zArr = new boolean[1];
        if (lqs.e(cacheStrategy)) {
            Object e2 = this.i.e(key, cls, iCacheStrategy);
            rsp = e2;
            if (e2 != 0) {
                ReleaseLogUtil.b("R_NetworkClientMgr", "data from disk");
                resultCallback.onSuccess(new lqq<>(2, e2));
                zArr[0] = true;
                rsp = e2;
            }
        } else {
            rsp = null;
        }
        final Rsp rsp2 = rsp;
        if (lqs.a(cacheStrategy) && (lqs.d(cacheStrategy) || rsp2 == null)) {
            b(str, map, str2, cls, new ResultCallback<Rsp>() { // from class: lqi.2
                @Override // com.huawei.networkclient.ResultCallback
                public void onSuccess(Rsp rsp3) {
                    DataConverterRepository.DataConverter a2 = lqi.this.f.a(cls);
                    if (rsp2 == null || !a2.revert(rsp3).equals(a2.revert(rsp2))) {
                        ReleaseLogUtil.b("R_NetworkClientMgr", "data from network");
                        resultCallback.onSuccess(new lqq(1, rsp3));
                    }
                    if (lqs.e(cacheStrategy)) {
                        lqi.this.c(rsp3, a2, key, iCacheStrategy);
                    }
                }

                @Override // com.huawei.networkclient.ResultCallback
                public void onFailure(Throwable th) {
                    if (zArr[0]) {
                        return;
                    }
                    resultCallback.onFailure(th);
                }
            });
        }
        c((ResultCallback<lqq<int>>) resultCallback, cacheStrategy, (int) rsp2);
    }

    private <Rsp> void c(ResultCallback<lqq<Rsp>> resultCallback, int i, Rsp rsp) {
        if (lqs.e(i) && rsp == null && !lqs.a(i)) {
            ReleaseLogUtil.c("R_NetworkClientMgr", "no cache on disk, consider enableNetwork to get data from network");
            resultCallback.onFailure(new Throwable("no cache on disk, consider enableNetwork to get data from network"));
        } else {
            if (lqs.e(i) || lqs.a(i)) {
                return;
            }
            ReleaseLogUtil.c("R_NetworkClientMgr", "wrong cacheStrategy");
            resultCallback.onFailure(new Throwable("wrong cacheStrategy"));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <Rsp> void c(final Rsp rsp, final DataConverterRepository.DataConverter dataConverter, final String str, final ICacheStrategy iCacheStrategy) {
        ThreadPoolManager.d().c("network_cache", new Runnable() { // from class: lqi.5
            @Override // java.lang.Runnable
            public void run() {
                lqi.this.i.e(str, iCacheStrategy, dataConverter.revert(rsp));
            }
        });
    }

    /* JADX WARN: Can't wrap try/catch for region: R(7:(3:23|24|(1:26)(6:27|10|11|(1:13)(1:17)|14|15))|9|10|11|(0)(0)|14|15) */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x008d, code lost:
    
        r5 = e;
     */
    /* JADX WARN: Removed duplicated region for block: B:13:0x005e A[Catch: IOException -> 0x008d, JsonSyntaxException -> 0x008f, TryCatch #0 {JsonSyntaxException -> 0x008f, blocks: (B:24:0x0034, B:27:0x003b, B:11:0x0048, B:13:0x005e, B:17:0x006f, B:9:0x0042), top: B:23:0x0034 }] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x006f A[Catch: IOException -> 0x008d, JsonSyntaxException -> 0x008f, TRY_LEAVE, TryCatch #0 {JsonSyntaxException -> 0x008f, blocks: (B:24:0x0034, B:27:0x003b, B:11:0x0048, B:13:0x005e, B:17:0x006f, B:9:0x0042), top: B:23:0x0034 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public <Rsp> Rsp b(java.lang.String r5, java.util.Map<java.lang.String, java.lang.String> r6, java.util.Map<java.lang.String, java.lang.String> r7, java.lang.Class<Rsp> r8) {
        /*
            r4 = this;
            android.content.Context r0 = com.huawei.haf.application.BaseApplication.e()
            android.os.Looper r0 = r0.getMainLooper()
            android.os.Looper r1 = android.os.Looper.myLooper()
            java.lang.String r2 = "NetworkClientMgr"
            if (r0 != r1) goto L2a
            java.lang.String r0 = "network action in main thread"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            java.lang.String r1 = "R_NetworkClientMgr"
            health.compact.a.ReleaseLogUtil.c(r1, r0)
            java.lang.String r0 = "network action in main thread, responseType is "
            java.lang.String r1 = r8.getSimpleName()
            java.lang.Object[] r0 = new java.lang.Object[]{r0, r1}
            health.compact.a.LogUtil.e(r2, r0)
        L2a:
            r0 = 0
            boolean r1 = r4.e(r6, r0)
            if (r1 != 0) goto L32
            return r0
        L32:
            if (r7 == 0) goto L42
            boolean r1 = r7.isEmpty()     // Catch: com.google.gson.JsonSyntaxException -> L8f java.io.IOException -> La2
            if (r1 == 0) goto L3b
            goto L42
        L3b:
            com.huawei.networkclient.CommonApi r1 = r4.j     // Catch: com.google.gson.JsonSyntaxException -> L8f java.io.IOException -> La2
            com.huawei.hms.framework.network.restclient.Submit r6 = r1.commonGet(r5, r6, r7)     // Catch: com.google.gson.JsonSyntaxException -> L8f java.io.IOException -> La2
            goto L48
        L42:
            com.huawei.networkclient.CommonApi r7 = r4.j     // Catch: com.google.gson.JsonSyntaxException -> L8f java.io.IOException -> La2
            com.huawei.hms.framework.network.restclient.Submit r6 = r7.commonGet(r5, r6)     // Catch: com.google.gson.JsonSyntaxException -> L8f java.io.IOException -> La2
        L48:
            com.huawei.hms.framework.network.restclient.Response r7 = r6.execute()     // Catch: java.io.IOException -> L8d com.google.gson.JsonSyntaxException -> L8f
            defpackage.lrh.a(r6)     // Catch: java.io.IOException -> L8d com.google.gson.JsonSyntaxException -> L8f
            java.lang.Object r1 = r7.getBody()     // Catch: java.io.IOException -> L8d com.google.gson.JsonSyntaxException -> L8f
            java.lang.String r1 = (java.lang.String) r1     // Catch: java.io.IOException -> L8d com.google.gson.JsonSyntaxException -> L8f
            defpackage.lra.a(r5, r1)     // Catch: java.io.IOException -> L8d com.google.gson.JsonSyntaxException -> L8f
            boolean r5 = r7.isOK()     // Catch: java.io.IOException -> L8d com.google.gson.JsonSyntaxException -> L8f
            if (r5 == 0) goto L6f
            com.huawei.networkclient.repository.DataConverterRepository r5 = r4.f     // Catch: java.io.IOException -> L8d com.google.gson.JsonSyntaxException -> L8f
            com.huawei.networkclient.repository.DataConverterRepository$DataConverter r5 = r5.a(r8)     // Catch: java.io.IOException -> L8d com.google.gson.JsonSyntaxException -> L8f
            java.lang.Object r7 = r7.getBody()     // Catch: java.io.IOException -> L8d com.google.gson.JsonSyntaxException -> L8f
            java.lang.String r7 = (java.lang.String) r7     // Catch: java.io.IOException -> L8d com.google.gson.JsonSyntaxException -> L8f
            java.lang.Object r0 = r5.convert(r7, r8)     // Catch: java.io.IOException -> L8d com.google.gson.JsonSyntaxException -> L8f
            goto Lb8
        L6f:
            r5 = 3
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch: java.io.IOException -> L8d com.google.gson.JsonSyntaxException -> L8f
            java.lang.String r1 = r8.getSimpleName()     // Catch: java.io.IOException -> L8d com.google.gson.JsonSyntaxException -> L8f
            r3 = 0
            r5[r3] = r1     // Catch: java.io.IOException -> L8d com.google.gson.JsonSyntaxException -> L8f
            java.lang.String r1 = " status code:"
            r3 = 1
            r5[r3] = r1     // Catch: java.io.IOException -> L8d com.google.gson.JsonSyntaxException -> L8f
            int r7 = r7.getCode()     // Catch: java.io.IOException -> L8d com.google.gson.JsonSyntaxException -> L8f
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)     // Catch: java.io.IOException -> L8d com.google.gson.JsonSyntaxException -> L8f
            r1 = 2
            r5[r1] = r7     // Catch: java.io.IOException -> L8d com.google.gson.JsonSyntaxException -> L8f
            health.compact.a.LogUtil.a(r2, r5)     // Catch: java.io.IOException -> L8d com.google.gson.JsonSyntaxException -> L8f
            goto Lb8
        L8d:
            r5 = move-exception
            goto La4
        L8f:
            r5 = move-exception
            java.lang.String r6 = r8.getSimpleName()
            java.lang.String r7 = " string convert error:"
            java.lang.String r5 = com.huawei.haf.common.exception.ExceptionUtils.d(r5)
            java.lang.Object[] r5 = new java.lang.Object[]{r6, r7, r5}
            health.compact.a.LogUtil.e(r2, r5)
            goto Lb8
        La2:
            r5 = move-exception
            r6 = r0
        La4:
            r4.e(r6)
            java.lang.String r6 = r8.getSimpleName()
            java.lang.String r7 = " fail:"
            java.lang.String r5 = com.huawei.haf.common.exception.ExceptionUtils.d(r5)
            java.lang.Object[] r5 = new java.lang.Object[]{r6, r7, r5}
            health.compact.a.LogUtil.e(r2, r5)
        Lb8:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.lqi.b(java.lang.String, java.util.Map, java.util.Map, java.lang.Class):java.lang.Object");
    }

    public <Rsp> void c(String str, Map<String, String> map, Map<String, String> map2, Class<Rsp> cls, ResultCallback<Rsp> resultCallback) {
        if (e(map, resultCallback)) {
            Submit<ResponseBody> commonGetRaw = this.j.commonGetRaw(str, map, map2);
            commonGetRaw.enqueue(c(resultCallback, cls, commonGetRaw));
        }
    }

    public void d(final IDownloadStrategy iDownloadStrategy) {
        ProgressListener listener = iDownloadStrategy.getListener();
        if (listener == null) {
            listener = new lqh();
        }
        final ProgressListener progressListener = listener;
        final String url = iDownloadStrategy.getUrl();
        if (e.containsKey(url) || b.containsKey(url)) {
            ReleaseLogUtil.a("R_NetworkClientMgr", "is downloading, call cancelDownload or wait download finish");
            Throwable th = new Throwable("duplicate task calling when previous is downloading");
            iDownloadStrategy.handleException(th, 1003);
            progressListener.onFail(th);
            return;
        }
        b.put(url, progressListener);
        final Submit<ResponseBody> a2 = a(iDownloadStrategy, url);
        if (a2 == null) {
            LogUtil.e("NetworkClientMgr", "commonDownload downloadSubmit is null");
        } else {
            e.put(url, a2);
            a2.enqueue(new com.huawei.hms.framework.network.restclient.ResultCallback<ResponseBody>() { // from class: lqi.1
                @Override // com.huawei.hms.framework.network.restclient.ResultCallback
                public void onResponse(Response<ResponseBody> response) {
                    if (!response.isSuccessful()) {
                        ReleaseLogUtil.a("R_NetworkClientMgr", "download response not 2XX");
                    }
                    ResponseBody body = response.getBody();
                    lra.d(url, body.getContentLength());
                    if (body.getInputStream() == null) {
                        ReleaseLogUtil.c("R_NetworkClientMgr", "download inputStream is null");
                        iDownloadStrategy.handleException(new Throwable("download inputStream is null"), response.getCode());
                        progressListener.onFail(new Throwable("download inputStream is null"));
                        lqi.this.c(url);
                        FileUtils.d(body);
                        return;
                    }
                    iDownloadStrategy.handleResponseBody(body, response.getCode());
                    lqi.this.c(url);
                }

                @Override // com.huawei.hms.framework.network.restclient.ResultCallback
                public void onFailure(Throwable th2) {
                    ReleaseLogUtil.c("R_NetworkClientMgr", "download url fail", ExceptionUtils.d(th2));
                    iDownloadStrategy.handleException(th2, 1001);
                    progressListener.onFail(th2);
                    lqi.this.c(url);
                    lqi.this.e(a2);
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x002a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private com.huawei.hms.framework.network.restclient.Submit<com.huawei.hms.framework.network.restclient.hwhttp.ResponseBody> a(com.huawei.networkclient.IDownloadStrategy r6, java.lang.String r7) {
        /*
            r5 = this;
            java.util.Map r0 = r6.getHeaders()
            int r1 = r6.getRequestMethod()
            java.lang.String r6 = r6.getRequestBody()
            r2 = 1
            r3 = 0
            if (r1 != r2) goto L26
            if (r0 == 0) goto L1f
            boolean r2 = r5.b(r0)
            if (r2 == 0) goto L26
            com.huawei.networkclient.CommonApi r2 = r5.j
            com.huawei.hms.framework.network.restclient.Submit r2 = r2.commonDownload(r7, r0)
            goto L27
        L1f:
            com.huawei.networkclient.CommonApi r2 = r5.j
            com.huawei.hms.framework.network.restclient.Submit r2 = r2.commonDownload(r7)
            goto L27
        L26:
            r2 = r3
        L27:
            r4 = 2
            if (r1 != r4) goto L46
            boolean r1 = android.text.TextUtils.isEmpty(r6)
            if (r1 == 0) goto L3a
            java.lang.String r6 = ""
            com.huawei.hms.framework.network.restclient.hwhttp.RequestBody r6 = com.huawei.hms.framework.network.restclient.hwhttp.RequestBody.create(r3, r6)
            java.lang.String r6 = r6.toString()
        L3a:
            boolean r1 = r5.b(r0)
            if (r1 == 0) goto L46
            com.huawei.networkclient.CommonApi r1 = r5.j
            com.huawei.hms.framework.network.restclient.Submit r2 = r1.commonDownload(r7, r0, r6)
        L46:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.lqi.a(com.huawei.networkclient.IDownloadStrategy, java.lang.String):com.huawei.hms.framework.network.restclient.Submit");
    }

    public boolean d(String str) {
        if (TextUtils.isEmpty(str)) {
            ReleaseLogUtil.c("R_NetworkClientMgr", "url is null");
            return false;
        }
        if (!e.containsKey(str)) {
            ReleaseLogUtil.c("R_NetworkClientMgr", "without matched download task");
            return false;
        }
        b.remove(str);
        Submit<ResponseBody> remove = e.remove(str);
        if (remove == null) {
            return false;
        }
        remove.cancel();
        return true;
    }

    public void a() {
        this.i.a();
        b.clear();
        e.clear();
        this.i = new lqs("network_cache", this.f);
    }

    private void e(HttpClient httpClient) {
        this.o = new RestClient.Builder().httpClient(httpClient.newBuilder().addInterceptor(new Interceptor() { // from class: lqk
            @Override // com.huawei.hms.framework.network.restclient.hwhttp.Interceptor
            public final com.huawei.hms.framework.network.restclient.hwhttp.Response intercept(Interceptor.Chain chain) {
                com.huawei.hms.framework.network.restclient.hwhttp.Response d2;
                d2 = lqi.this.d(chain);
                return d2;
            }
        }).build()).addConverterFactory(new lqr()).addConverterFactory(new lqm()).addConverterFactory(GsonConverterFactory.create()).build();
        this.j = (CommonApi) b(CommonApi.class);
        DataConverterRepository dataConverterRepository = new DataConverterRepository();
        this.f = dataConverterRepository;
        dataConverterRepository.e(String.class, new lre());
        this.f.e(byte[].class, new lqz());
        this.i = new lqs("network_cache", this.f);
    }

    private HttpClient b() {
        RestClientGlobalInstance.getInstance().init(BaseApplication.e());
        return new HttpClient.Builder().readTimeout(30000).writeTimeout(30000).build();
    }

    private <Rsp> com.huawei.hms.framework.network.restclient.ResultCallback<ResponseBody> c(final ResultCallback<Rsp> resultCallback, final Class<Rsp> cls, final Submit<ResponseBody> submit) {
        return new com.huawei.hms.framework.network.restclient.ResultCallback<ResponseBody>() { // from class: lqi.4
            @Override // com.huawei.hms.framework.network.restclient.ResultCallback
            public void onResponse(Response<ResponseBody> response) {
                ResponseBody responseBody;
                Object convert;
                lrh.a(submit);
                if (response == null) {
                    resultCallback.onFailure(new Throwable("empty response"));
                    return;
                }
                if (!response.isOK()) {
                    int code = response.getCode();
                    resultCallback.onFailure(new lqj(code, "response status code:" + code));
                    return;
                }
                ResponseBody responseBody2 = null;
                try {
                    try {
                        convert = lqi.this.f.a(cls).convert(response.getBody(), (Class<Object>) cls);
                        responseBody = response.getBody();
                    } catch (JsonSyntaxException e2) {
                        e = e2;
                    }
                } catch (Throwable th) {
                    th = th;
                    responseBody = responseBody2;
                }
                try {
                    if (response.getUrl() != null && responseBody != null) {
                        lra.d(response.getUrl().toString(), responseBody.getContentLength());
                    }
                    if (convert == null) {
                        resultCallback.onFailure(new Throwable("rsp is null"));
                    } else {
                        resultCallback.onSuccess(convert);
                    }
                    FileUtils.d(responseBody);
                } catch (JsonSyntaxException e3) {
                    e = e3;
                    responseBody2 = responseBody;
                    resultCallback.onFailure(new Throwable(e.getMessage()));
                    FileUtils.d(responseBody2);
                } catch (Throwable th2) {
                    th = th2;
                    FileUtils.d(responseBody);
                    throw th;
                }
            }

            @Override // com.huawei.hms.framework.network.restclient.ResultCallback
            public void onFailure(Throwable th) {
                resultCallback.onFailure(th);
                lqi.this.e(submit);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public com.huawei.hms.framework.network.restclient.hwhttp.Response d(Interceptor.Chain chain) throws IOException {
        final Request request = chain.request();
        final Headers headers = request.getHeaders();
        int d2 = d(10000) + 100000000;
        lrh.c(d2, request);
        String str = headers.get(j2.v);
        if (!TextUtils.isEmpty(str) && str.toLowerCase(Locale.ENGLISH).contains(Constants.GZIP)) {
            request = request.newBuilder().headers(headers.newBuilder().removeAll(j2.v)).build();
        }
        String str2 = headers.get("Content-Encoding");
        if (!TextUtils.isEmpty(str2) && str2.toLowerCase(Locale.ENGLISH).contains(Constants.GZIP)) {
            request = request.newBuilder().requestBody(new RequestBody() { // from class: lqi.8
                @Override // com.huawei.hms.framework.network.restclient.hwhttp.RequestBody
                public void writeTo(OutputStream outputStream) throws IOException {
                }

                @Override // com.huawei.hms.framework.network.restclient.hwhttp.RequestBody
                public byte[] body() {
                    return lql.e(request.getBody().body());
                }

                @Override // com.huawei.hms.framework.network.restclient.hwhttp.RequestBody
                public String contentType() {
                    return headers.get("Content-Type");
                }
            }).build();
        }
        com.huawei.hms.framework.network.restclient.hwhttp.Response e2 = lrh.e(d2, chain.proceed(request));
        HttpUrl url = chain.request().getUrl();
        if (url == null || url.getUrl() == null || !b.containsKey(url.getUrl())) {
            return e2;
        }
        InputStream inputStream = e2.getBody().getInputStream();
        LogUtil.d("NetworkClientMgr", "downloading url with listener:", url.getUrl());
        return e2.newBuilder().body(e2.getBody().newBuilder().inputStream(new lqp(inputStream, e2.getBody().getContentLength(), b.get(url.getUrl()))).build()).build();
    }

    private boolean b(Map<String, String> map) {
        if (map == null) {
            ReleaseLogUtil.c("R_NetworkClientMgr", "request headers is null");
            return false;
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (TextUtils.isEmpty(entry.getValue())) {
                ReleaseLogUtil.c("R_NetworkClientMgr", "value of key:", entry.getKey(), " is empty");
                return false;
            }
        }
        return true;
    }

    private <Rsp> boolean e(Map<String, String> map, ResultCallback<Rsp> resultCallback) {
        try {
            if (NetworkUtil.i()) {
                if (this.j == null) {
                    if (resultCallback != null) {
                        resultCallback.onFailure(new Throwable("commonApi not init"));
                    }
                    ReleaseLogUtil.a("R_NetworkClientMgr", "isPreConditionCheckPass ", "commonApi not init");
                    return false;
                }
                if (b(map)) {
                    return true;
                }
                if (resultCallback != null) {
                    resultCallback.onFailure(new Throwable("headers invalid"));
                }
                ReleaseLogUtil.a("R_NetworkClientMgr", "isPreConditionCheckPass ", "request headers invalid");
                return false;
            }
        } catch (NoClassDefFoundError unused) {
            ReleaseLogUtil.c("R_NetworkClientMgr", "isPreConditionCheckPass ", "NetworkUtil NoClassDefFoundError.");
        }
        if (resultCallback != null) {
            resultCallback.onFailure(new Throwable("no network connection"));
        }
        ReleaseLogUtil.a("R_NetworkClientMgr", "isPreConditionCheckPass ", "no network connection");
        return false;
    }

    public <Rsp> void e(String str, Map<String, String> map, Class<Rsp> cls, ResultCallback<Rsp> resultCallback) {
        if (e(map, resultCallback)) {
            Submit<ResponseBody> deleteReqRaw = this.j.deleteReqRaw(str, map);
            deleteReqRaw.enqueue(c(resultCallback, cls, deleteReqRaw));
        }
    }

    public <Rsp> void c(String str, Map<String, String> map, String str2, Class<Rsp> cls, ResultCallback<Rsp> resultCallback) {
        if (e(map, resultCallback)) {
            Submit<ResponseBody> putReqRaw = this.j.putReqRaw(str, map, str2);
            lra.a(str, str2);
            putReqRaw.enqueue(c(resultCallback, cls, putReqRaw));
        }
    }

    public static boolean c(long j) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (elapsedRealtime - g < j) {
            return false;
        }
        g = elapsedRealtime;
        return true;
    }

    public void e(Submit<?> submit) {
        if (submit == null) {
            LogUtil.a("NetworkClientMgr", "requestFail submit is null.");
            return;
        }
        try {
            RequestFinishedInfo requestFinishedInfo = submit.request().getRequestFinishedInfo();
            if (requestFinishedInfo == null) {
                LogUtil.a("NetworkClientMgr", "requestFail info is null.");
                return;
            }
            RequestFinishedInfo.Metrics metrics = requestFinishedInfo.getMetrics();
            if (metrics == null) {
                LogUtil.a("NetworkClientMgr", "requestFail metrics is null.");
                return;
            }
            LogUtil.c("NetworkClientMgr", "DnsType : " + metrics.getDnsType() + " url: " + requestFinishedInfo.getUrl() + " : ");
            if ((w.l.equalsIgnoreCase(metrics.getDnsType()) || w.m.equalsIgnoreCase(metrics.getDnsType())) && c(3600000L)) {
                c();
            }
        } catch (Exception e2) {
            ReleaseLogUtil.c("R_NetworkClientMgr", " fail to requestFail", ExceptionUtils.d(e2));
        }
    }

    private void c() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: lqi.9
            @Override // java.lang.Runnable
            public void run() {
                GrsBaseInfo grsBaseInfo = new GrsBaseInfo();
                grsBaseInfo.setAppName(lqi.c);
                String string = BaseApplication.e().getSharedPreferences("grs_init_info", 0).getString("grs_countrycode_key", "");
                ReleaseLogUtil.b("R_NetworkClientMgr", "AppName : " + lqi.c + " CountrySource:" + string);
                if (TextUtils.isEmpty(string)) {
                    ReleaseLogUtil.b("R_NetworkClientMgr", "countryCode is null");
                    return;
                }
                grsBaseInfo.setCountrySource(string);
                grsBaseInfo.setSerCountry(string);
                grsBaseInfo.setRegCountry(string);
                grsBaseInfo.setVersionName(Build.VERSION.RELEASE);
                GrsClient grsClient = new GrsClient(BaseApplication.e(), grsBaseInfo);
                ReleaseLogUtil.b("R_NetworkClientMgr", "grsClient.forceExpire()");
                grsClient.forceExpire();
            }
        });
    }

    private SecureRandom h() {
        try {
            return SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e2) {
            ReleaseLogUtil.c("NetworkClientMgr", "getSecureRandom exception ", e2);
            return new SecureRandom();
        }
    }

    public int d(int i) {
        if (i <= 0) {
            ReleaseLogUtil.a("NetworkClientMgr", "getSecureRandomForInt bound ", Integer.valueOf(i));
            return 0;
        }
        return h().nextInt(i);
    }
}
