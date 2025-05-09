package com.huawei.hms.network.embedded;

import com.huawei.hms.framework.common.CheckParamUtils;
import com.huawei.hms.framework.common.ExtLogger;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.network.Version;
import com.huawei.hms.network.api.advance.WrapperLogger;
import com.huawei.hms.network.httpclient.Callback;
import com.huawei.hms.network.httpclient.Interceptor;
import com.huawei.hms.network.httpclient.Request;
import com.huawei.hms.network.httpclient.RequestBody;
import com.huawei.hms.network.httpclient.RequestFinishedInfo;
import com.huawei.hms.network.httpclient.Response;
import com.huawei.hms.network.httpclient.ResponseBody;
import com.huawei.hms.network.httpclient.Submit;
import com.huawei.hms.network.httpclient.config.NetworkConfig;
import com.huawei.hms.network.httpclient.websocket.WebSocket;
import com.huawei.hms.network.httpclient.websocket.WebSocketListener;
import com.huawei.hms.network.inner.api.RequestContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import javax.annotation.Nonnull;

/* loaded from: classes.dex */
public class h1 {

    /* renamed from: a, reason: collision with root package name */
    public static final String f5272a = "SafeApi";

    /* loaded from: classes9.dex */
    public static final class d extends Request {

        /* renamed from: a, reason: collision with root package name */
        public final Request f5276a;
        public e b;
        public NetworkConfig c;

        @Override // com.huawei.hms.network.httpclient.Request
        public Request.Builder newBuilder() {
            return this.f5276a.newBuilder();
        }

        @Override // com.huawei.hms.network.httpclient.Request
        public String getUrl() {
            return this.f5276a.getUrl();
        }

        @Override // com.huawei.hms.network.httpclient.Request
        public Object getTag() {
            return this.f5276a.getTag();
        }

        @Override // com.huawei.hms.network.httpclient.Request
        public String getOptions() {
            return this.f5276a.getOptions();
        }

        public NetworkConfig getNetConfig() {
            NetworkConfig networkConfig = this.c;
            if (networkConfig != null) {
                return networkConfig;
            }
            NetworkConfig networkConfig2 = new NetworkConfig(getOptions());
            this.c = networkConfig2;
            return networkConfig2;
        }

        @Override // com.huawei.hms.network.httpclient.Request
        public String getMethod() {
            return this.f5276a.getMethod();
        }

        @Override // com.huawei.hms.network.httpclient.Request
        public Map<String, List<String>> getHeaders() {
            return this.f5276a.getHeaders();
        }

        @Override // com.huawei.hms.network.httpclient.Request
        public e getBody() {
            e eVar = this.b;
            if (eVar != null) {
                return eVar;
            }
            RequestBody body = this.f5276a.getBody();
            if (body == null || (body instanceof e)) {
                this.b = (e) body;
            } else {
                this.b = new e(body);
            }
            return this.b;
        }

        public d(Request request) {
            CheckParamUtils.checkNotNull(request, "SafeRequest request == null");
            this.f5276a = request;
        }
    }

    /* loaded from: classes9.dex */
    public static final class h<T> extends Submit<T> {

        /* renamed from: a, reason: collision with root package name */
        public final Submit<T> f5280a;

        @Override // com.huawei.hms.network.httpclient.Submit
        public Request request() throws IOException {
            return this.f5280a.request();
        }

        @Override // com.huawei.hms.network.httpclient.Submit
        public boolean isExecuted() {
            return this.f5280a.isExecuted();
        }

        @Override // com.huawei.hms.network.httpclient.Submit
        public boolean isCanceled() {
            return this.f5280a.isCanceled();
        }

        @Override // com.huawei.hms.network.httpclient.Submit
        public RequestFinishedInfo getRequestFinishedInfo() {
            return this.f5280a.getRequestFinishedInfo();
        }

        @Override // com.huawei.hms.network.httpclient.Submit
        public Response<T> execute() throws IOException {
            return this.f5280a.execute();
        }

        @Override // com.huawei.hms.network.httpclient.Submit
        public void enqueue(Callback<T> callback) {
            this.f5280a.enqueue(callback);
        }

        @Override // com.huawei.hms.network.httpclient.Submit
        /* renamed from: clone */
        public Submit<T> mo631clone() {
            return this.f5280a.mo631clone();
        }

        @Override // com.huawei.hms.network.httpclient.Submit
        public void cancel() {
            this.f5280a.cancel();
        }

        public h(Submit<T> submit) {
            CheckParamUtils.checkNotNull(submit, "SafeSubmit<T> submit == null");
            this.f5280a = submit;
        }
    }

    public static final class i extends WebSocket {

        /* renamed from: a, reason: collision with root package name */
        public final WebSocket f5281a;

        @Override // com.huawei.hms.network.httpclient.websocket.WebSocket
        public boolean send(byte[] bArr) {
            return this.f5281a.send(bArr);
        }

        @Override // com.huawei.hms.network.httpclient.websocket.WebSocket
        public boolean send(String str) {
            return this.f5281a.send(str);
        }

        @Override // com.huawei.hms.network.httpclient.websocket.WebSocket
        public void resetPingInterval(long j) {
            this.f5281a.resetPingInterval(j);
        }

        @Override // com.huawei.hms.network.httpclient.websocket.WebSocket
        public Request request() {
            return this.f5281a.request();
        }

        @Override // com.huawei.hms.network.httpclient.websocket.WebSocket
        public long queueSize() {
            return this.f5281a.queueSize();
        }

        public WebSocket getWebSocket() {
            return this.f5281a;
        }

        @Override // com.huawei.hms.network.httpclient.websocket.WebSocket
        public boolean enableDynamicPing(int i) {
            return this.f5281a.enableDynamicPing(i);
        }

        @Override // com.huawei.hms.network.httpclient.websocket.WebSocket
        public boolean close(int i, @Nonnull String str) {
            return this.f5281a.close(i, str);
        }

        @Override // com.huawei.hms.network.httpclient.websocket.WebSocket
        public void cancel() {
            this.f5281a.cancel();
        }

        public i(WebSocket webSocket) {
            CheckParamUtils.checkNotNull(webSocket, "SafeWebSocket webSocket == null");
            this.f5281a = webSocket;
        }
    }

    /* loaded from: classes9.dex */
    public static final class b extends Interceptor.Chain {

        /* renamed from: a, reason: collision with root package name */
        public final Interceptor.Chain f5274a;

        @Override // com.huawei.hms.network.httpclient.Interceptor.Chain
        public RequestFinishedInfo requestFinishedInfo() {
            d1 requestTask;
            if (!h1.apiAvailable(5) || (requestTask = ((b1) this.f5274a).getRequestTask()) == null) {
                return null;
            }
            return requestTask.getRequestFinishedInfo();
        }

        @Override // com.huawei.hms.network.httpclient.Interceptor.Chain
        public d request() {
            return (d) this.f5274a.request();
        }

        public Response<ResponseBody> proceed(RequestContext requestContext, d1 d1Var) throws IOException {
            return ((b1) this.f5274a).a(requestContext, d1Var);
        }

        @Override // com.huawei.hms.network.httpclient.Interceptor.Chain
        public Response<ResponseBody> proceed(Request request) throws IOException {
            return this.f5274a.proceed(request);
        }

        public d1 getRequestTask() {
            return ((b1) this.f5274a).getRequestTask();
        }

        public a1 getRealHttpclient() {
            return (a1) ((b1) this.f5274a).getClient();
        }

        public z2 getRCEventListener() {
            return ((b1) this.f5274a).getRCEventListener();
        }

        public b(Interceptor.Chain chain) {
            CheckParamUtils.checkNotNull(chain, "SafeChain chain == null");
            this.f5274a = chain;
        }
    }

    /* loaded from: classes9.dex */
    public static final class f<T> extends Response<T> {

        /* renamed from: a, reason: collision with root package name */
        public final Response<T> f5278a;

        @Override // com.huawei.hms.network.httpclient.Response
        public String getUrl() {
            return this.f5278a.getUrl();
        }

        @Override // com.huawei.hms.network.httpclient.Response
        public String getMessage() {
            return this.f5278a.getMessage();
        }

        @Override // com.huawei.hms.network.httpclient.Response
        public Map<String, List<String>> getHeaders() {
            return this.f5278a.getHeaders();
        }

        @Override // com.huawei.hms.network.httpclient.Response
        public ResponseBody getErrorBody() {
            return this.f5278a.getErrorBody();
        }

        @Override // com.huawei.hms.network.httpclient.Response
        public int getCode() {
            return this.f5278a.getCode();
        }

        @Override // com.huawei.hms.network.httpclient.Response
        public T getBody() {
            return this.f5278a.getBody();
        }

        @Override // com.huawei.hms.network.httpclient.Response
        public Response.Builder createBuilder() {
            return this.f5278a.createBuilder();
        }

        @Override // com.huawei.hms.network.httpclient.Response, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            this.f5278a.close();
        }

        public f(Response<T> response) {
            CheckParamUtils.checkNotNull(response, "SafeResponse<T> response == null");
            this.f5278a = response;
        }
    }

    /* loaded from: classes9.dex */
    public static final class e extends RequestBody {

        /* renamed from: a, reason: collision with root package name */
        public final RequestBody f5277a;

        @Override // com.huawei.hms.network.httpclient.RequestBody
        public void writeTo(OutputStream outputStream) throws IOException {
            this.f5277a.writeTo(outputStream);
        }

        @Override // com.huawei.hms.network.httpclient.RequestBody
        public boolean isDuplex() {
            if (h1.apiAvailable(5)) {
                return this.f5277a.isDuplex();
            }
            return false;
        }

        public int hashCode() {
            return this.f5277a.hashCode();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || e.class != obj.getClass()) {
                return false;
            }
            return this.f5277a.equals(((e) obj).f5277a);
        }

        @Override // com.huawei.hms.network.httpclient.RequestBody
        public boolean endOfStream() {
            return this.f5277a.endOfStream();
        }

        @Override // com.huawei.hms.network.httpclient.RequestBody
        public String contentType() {
            return this.f5277a.contentType();
        }

        @Override // com.huawei.hms.network.httpclient.RequestBody
        public long contentLength() throws IOException {
            return this.f5277a.contentLength();
        }

        public e(RequestBody requestBody) {
            CheckParamUtils.checkNotNull(requestBody, "SafeRequestBody requestBody == null");
            this.f5277a = requestBody;
        }
    }

    /* loaded from: classes9.dex */
    public static class k extends ExtLogger {

        /* renamed from: a, reason: collision with root package name */
        public final WrapperLogger f5283a;

        @Override // com.huawei.hms.framework.common.ExtLogger
        public void w(String str, String str2, Throwable th) {
            this.f5283a.w(str, str2, th);
        }

        @Override // com.huawei.hms.framework.common.ExtLogger
        public void w(String str, String str2) {
            this.f5283a.w(str, str2);
        }

        @Override // com.huawei.hms.framework.common.ExtLogger
        public void v(String str, String str2) {
            this.f5283a.v(str, str2);
        }

        @Override // com.huawei.hms.framework.common.ExtLogger
        public void i(String str, String str2) {
            this.f5283a.i(str, str2);
        }

        @Override // com.huawei.hms.framework.common.ExtLogger
        public void e(String str, String str2, Throwable th) {
            this.f5283a.e(str, str2, th);
        }

        @Override // com.huawei.hms.framework.common.ExtLogger
        public void e(String str, String str2) {
            this.f5283a.e(str, str2);
        }

        @Override // com.huawei.hms.framework.common.ExtLogger
        public void d(String str, String str2) {
            this.f5283a.d(str, str2);
        }

        public k(WrapperLogger wrapperLogger) {
            CheckParamUtils.checkNotNull(wrapperLogger, "SafeWrapperLogger wrapperLogger == null");
            this.f5283a = wrapperLogger;
        }
    }

    /* loaded from: classes9.dex */
    public static class j extends WebSocketListener {

        /* renamed from: a, reason: collision with root package name */
        public final WebSocketListener f5282a;

        @Override // com.huawei.hms.network.httpclient.websocket.WebSocketListener
        public void onOpen(WebSocket webSocket, Response<ResponseBody> response) {
            this.f5282a.onOpen(webSocket, response);
        }

        @Override // com.huawei.hms.network.httpclient.websocket.WebSocketListener
        public void onMessage(WebSocket webSocket, byte[] bArr) {
            this.f5282a.onMessage(webSocket, bArr);
        }

        @Override // com.huawei.hms.network.httpclient.websocket.WebSocketListener
        public void onMessage(WebSocket webSocket, String str) {
            this.f5282a.onMessage(webSocket, str);
        }

        @Override // com.huawei.hms.network.httpclient.websocket.WebSocketListener
        public void onFailure(WebSocket webSocket, Throwable th, Response<ResponseBody> response) {
            this.f5282a.onFailure(webSocket, th, response);
        }

        @Override // com.huawei.hms.network.httpclient.websocket.WebSocketListener
        public void onClosing(WebSocket webSocket, int i, String str) {
            this.f5282a.onClosing(webSocket, i, str);
        }

        @Override // com.huawei.hms.network.httpclient.websocket.WebSocketListener
        public void onClosed(WebSocket webSocket, int i, String str) {
            this.f5282a.onClosed(webSocket, i, str);
        }

        public j(WebSocketListener webSocketListener) {
            CheckParamUtils.checkNotNull(webSocketListener, "SafeWebSocketListener listener == null");
            this.f5282a = webSocketListener;
        }
    }

    /* loaded from: classes9.dex */
    public static final class g extends ResponseBody {

        /* renamed from: a, reason: collision with root package name */
        public final ResponseBody f5279a;

        @Override // com.huawei.hms.network.httpclient.ResponseBody
        public InputStream getInputStream() {
            return this.f5279a.getInputStream();
        }

        @Override // com.huawei.hms.network.httpclient.ResponseBody
        public String getContentType() {
            return this.f5279a.getContentType();
        }

        @Override // com.huawei.hms.network.httpclient.ResponseBody
        public long getContentLength() {
            return this.f5279a.getContentLength();
        }

        @Override // com.huawei.hms.network.httpclient.ResponseBody, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            this.f5279a.close();
        }

        public g(ResponseBody responseBody) {
            CheckParamUtils.checkNotNull(responseBody, "SafeResponseBody responseBody == null");
            this.f5279a = responseBody;
        }
    }

    /* loaded from: classes9.dex */
    public static final class a<T> extends Callback<T> {

        /* renamed from: a, reason: collision with root package name */
        public final Callback<T> f5273a;

        @Override // com.huawei.hms.network.httpclient.Callback
        public void onResponse(Submit<T> submit, Response<T> response) throws IOException {
            this.f5273a.onResponse(submit, response);
        }

        @Override // com.huawei.hms.network.httpclient.Callback
        public void onFailure(Submit<T> submit, Throwable th) {
            this.f5273a.onFailure(submit, th);
        }

        public a(Callback<T> callback) {
            CheckParamUtils.checkNotNull(callback, "SafeCallback<T> callback == null");
            this.f5273a = callback;
        }
    }

    /* loaded from: classes9.dex */
    public static final class c extends Interceptor {

        /* renamed from: a, reason: collision with root package name */
        public final Interceptor f5275a;

        public String toString() {
            return "SafeInterceptor{interceptor=" + this.f5275a.getClass().getName() + '}';
        }

        @Override // com.huawei.hms.network.httpclient.Interceptor
        public Response<ResponseBody> intercept(Interceptor.Chain chain) throws IOException {
            return this.f5275a.intercept(chain);
        }

        public c(Interceptor interceptor) {
            CheckParamUtils.checkNotNull(interceptor, "SafeInterceptor interceptor == null");
            this.f5275a = interceptor;
        }
    }

    public static boolean apiAvailable(int i2) {
        Logger.v(f5272a, "Version.getApi = " + Version.getApi());
        return i2 <= Version.getApi();
    }
}
