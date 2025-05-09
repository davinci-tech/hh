package okhttp3.internal.ws;

import androidx.core.app.NotificationCompat;
import com.huawei.agconnect.apms.instrument.okhttp3.OkHttp3Instrumentation;
import com.huawei.hms.network.embedded.ua;
import com.huawei.hms.network.embedded.y;
import com.huawei.phoneservice.faq.base.constants.TrackConstants$Opers;
import com.huawei.pluginachievement.manager.model.MedalConstants;
import com.huawei.watchface.videoedit.gles.Constant;
import defpackage.ueu;
import defpackage.ufe;
import defpackage.uhy;
import defpackage.uiv;
import defpackage.ujy;
import java.io.Closeable;
import java.io.IOException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.EventListener;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okhttp3.internal.Util;
import okhttp3.internal.concurrent.Task;
import okhttp3.internal.concurrent.TaskQueue;
import okhttp3.internal.concurrent.TaskRunner;
import okhttp3.internal.connection.Exchange;
import okhttp3.internal.connection.RealCall;
import okhttp3.internal.ws.RealWebSocket;
import okhttp3.internal.ws.WebSocketReader;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;

@Metadata(d1 = {"\u0000¶\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u001c\u0018\u0000 `2\u00020\u00012\u00020\u0002:\u0005_`abcB?\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\u0006\u0010\u000f\u001a\u00020\f¢\u0006\u0002\u0010\u0010J\u0016\u00102\u001a\u0002032\u0006\u00104\u001a\u00020\f2\u0006\u00105\u001a\u000206J\b\u00107\u001a\u000203H\u0016J\u001f\u00108\u001a\u0002032\u0006\u00109\u001a\u00020:2\b\u0010;\u001a\u0004\u0018\u00010<H\u0000¢\u0006\u0002\b=J\u001a\u0010>\u001a\u00020\u00122\u0006\u0010?\u001a\u00020%2\b\u0010@\u001a\u0004\u0018\u00010\u0018H\u0016J \u0010>\u001a\u00020\u00122\u0006\u0010?\u001a\u00020%2\b\u0010@\u001a\u0004\u0018\u00010\u00182\u0006\u0010A\u001a\u00020\fJ\u000e\u0010B\u001a\u0002032\u0006\u0010C\u001a\u00020DJ\u001c\u0010E\u001a\u0002032\n\u0010F\u001a\u00060Gj\u0002`H2\b\u00109\u001a\u0004\u0018\u00010:J\u0016\u0010I\u001a\u0002032\u0006\u0010\u001e\u001a\u00020\u00182\u0006\u0010*\u001a\u00020+J\u0006\u0010J\u001a\u000203J\u0018\u0010K\u001a\u0002032\u0006\u0010?\u001a\u00020%2\u0006\u0010@\u001a\u00020\u0018H\u0016J\u0010\u0010L\u001a\u0002032\u0006\u0010M\u001a\u00020\u0018H\u0016J\u0010\u0010L\u001a\u0002032\u0006\u0010N\u001a\u00020 H\u0016J\u0010\u0010O\u001a\u0002032\u0006\u0010P\u001a\u00020 H\u0016J\u0010\u0010Q\u001a\u0002032\u0006\u0010P\u001a\u00020 H\u0016J\u000e\u0010R\u001a\u00020\u00122\u0006\u0010P\u001a\u00020 J\u0006\u0010S\u001a\u00020\u0012J\b\u0010!\u001a\u00020\fH\u0016J\u0006\u0010'\u001a\u00020%J\u0006\u0010(\u001a\u00020%J\b\u0010T\u001a\u00020\u0006H\u0016J\b\u0010U\u001a\u000203H\u0002J\u0010\u0010V\u001a\u00020\u00122\u0006\u0010M\u001a\u00020\u0018H\u0016J\u0010\u0010V\u001a\u00020\u00122\u0006\u0010N\u001a\u00020 H\u0016J\u0018\u0010V\u001a\u00020\u00122\u0006\u0010W\u001a\u00020 2\u0006\u0010X\u001a\u00020%H\u0002J\u0006\u0010)\u001a\u00020%J\u0006\u0010Y\u001a\u000203J\r\u0010Z\u001a\u00020\u0012H\u0000¢\u0006\u0002\b[J\r\u0010\\\u001a\u000203H\u0000¢\u0006\u0002\b]J\f\u0010^\u001a\u00020\u0012*\u00020\u000eH\u0002R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020 0\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\"\u001a\u0004\u0018\u00010#X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020%X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010&\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020%X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020%X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020%X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010*\u001a\u0004\u0018\u00010+X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020-X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010.\u001a\u0004\u0018\u00010/X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00100\u001a\u0004\u0018\u000101X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006d"}, d2 = {"Lokhttp3/internal/ws/RealWebSocket;", "Lokhttp3/WebSocket;", "Lokhttp3/internal/ws/WebSocketReader$FrameCallback;", "taskRunner", "Lokhttp3/internal/concurrent/TaskRunner;", "originalRequest", "Lokhttp3/Request;", "listener", "Lokhttp3/WebSocketListener;", "random", "Ljava/util/Random;", "pingIntervalMillis", "", "extensions", "Lokhttp3/internal/ws/WebSocketExtensions;", "minimumDeflateSize", "(Lokhttp3/internal/concurrent/TaskRunner;Lokhttp3/Request;Lokhttp3/WebSocketListener;Ljava/util/Random;JLokhttp3/internal/ws/WebSocketExtensions;J)V", "awaitingPong", "", NotificationCompat.CATEGORY_CALL, "Lokhttp3/Call;", "enqueuedClose", "failed", MedalConstants.EVENT_KEY, "", "getListener$okhttp", "()Lokhttp3/WebSocketListener;", "messageAndCloseQueue", "Ljava/util/ArrayDeque;", "", "name", "pongQueue", "Lokio/ByteString;", "queueSize", "reader", "Lokhttp3/internal/ws/WebSocketReader;", "receivedCloseCode", "", "receivedCloseReason", "receivedPingCount", "receivedPongCount", "sentPingCount", "streams", "Lokhttp3/internal/ws/RealWebSocket$Streams;", "taskQueue", "Lokhttp3/internal/concurrent/TaskQueue;", "writer", "Lokhttp3/internal/ws/WebSocketWriter;", "writerTask", "Lokhttp3/internal/concurrent/Task;", "awaitTermination", "", "timeout", "timeUnit", "Ljava/util/concurrent/TimeUnit;", "cancel", "checkUpgradeSuccess", TrackConstants$Opers.RESPONSE, "Lokhttp3/Response;", "exchange", "Lokhttp3/internal/connection/Exchange;", "checkUpgradeSuccess$okhttp", "close", "code", "reason", "cancelAfterCloseMillis", "connect", "client", "Lokhttp3/OkHttpClient;", "failWebSocket", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "initReaderAndWriter", "loopReader", "onReadClose", "onReadMessage", Constant.TEXT, "bytes", "onReadPing", "payload", "onReadPong", "pong", "processNextFrame", "request", "runWriter", "send", "data", "formatOpcode", "tearDown", "writeOneFrame", "writeOneFrame$okhttp", "writePingFrame", "writePingFrame$okhttp", "isValid", "Close", "Companion", "Message", "Streams", "WriterTask", y.b.j}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class RealWebSocket implements WebSocket, WebSocketReader.FrameCallback {
    private static final long CANCEL_AFTER_CLOSE_MILLIS = 60000;
    public static final long DEFAULT_MINIMUM_DEFLATE_SIZE = 1024;
    private static final long MAX_QUEUE_SIZE = 16777216;
    private boolean awaitingPong;
    private Call call;
    private boolean enqueuedClose;
    private WebSocketExtensions extensions;
    private boolean failed;
    private final String key;
    private final WebSocketListener listener;
    private final ArrayDeque<Object> messageAndCloseQueue;
    private long minimumDeflateSize;
    private String name;
    private final Request originalRequest;
    private final long pingIntervalMillis;
    private final ArrayDeque<ByteString> pongQueue;
    private long queueSize;
    private final Random random;
    private WebSocketReader reader;
    private int receivedCloseCode;
    private String receivedCloseReason;
    private int receivedPingCount;
    private int receivedPongCount;
    private int sentPingCount;
    private Streams streams;
    private TaskQueue taskQueue;
    private WebSocketWriter writer;
    private Task writerTask;
    private static final List<Protocol> ONLY_HTTP1 = ufe.d(Protocol.HTTP_1_1);

    public RealWebSocket(TaskRunner taskRunner, Request request, WebSocketListener webSocketListener, Random random, long j, WebSocketExtensions webSocketExtensions, long j2) {
        uhy.e((Object) taskRunner, "");
        uhy.e((Object) request, "");
        uhy.e((Object) webSocketListener, "");
        uhy.e((Object) random, "");
        this.originalRequest = request;
        this.listener = webSocketListener;
        this.random = random;
        this.pingIntervalMillis = j;
        this.extensions = webSocketExtensions;
        this.minimumDeflateSize = j2;
        this.taskQueue = taskRunner.newQueue();
        this.pongQueue = new ArrayDeque<>();
        this.messageAndCloseQueue = new ArrayDeque<>();
        this.receivedCloseCode = -1;
        if (!uhy.e((Object) "GET", (Object) request.method())) {
            throw new IllegalArgumentException(("Request must be GET: " + request.method()).toString());
        }
        ByteString.Companion companion = ByteString.INSTANCE;
        byte[] bArr = new byte[16];
        random.nextBytes(bArr);
        ueu ueuVar = ueu.d;
        this.key = ByteString.Companion.of$default(companion, bArr, 0, 0, 3, null).base64();
    }

    /* renamed from: getListener$okhttp, reason: from getter */
    public final WebSocketListener getListener() {
        return this.listener;
    }

    @Override // okhttp3.WebSocket
    /* renamed from: request, reason: from getter */
    public Request getOriginalRequest() {
        return this.originalRequest;
    }

    @Override // okhttp3.WebSocket
    public long queueSize() {
        long j;
        synchronized (this) {
            j = this.queueSize;
        }
        return j;
    }

    @Override // okhttp3.WebSocket
    public void cancel() {
        Call call = this.call;
        uhy.d(call);
        call.cancel();
    }

    public final void connect(OkHttpClient client) {
        uhy.e((Object) client, "");
        if (this.originalRequest.header(ua.L) != null) {
            failWebSocket(new ProtocolException("Request header not permitted: 'Sec-WebSocket-Extensions'"), null);
            return;
        }
        OkHttpClient build = client.newBuilder().eventListener(EventListener.NONE).protocols(ONLY_HTTP1).build();
        Request.Builder header = this.originalRequest.newBuilder().header("Upgrade", "websocket").header("Connection", "Upgrade").header("Sec-WebSocket-Key", this.key).header("Sec-WebSocket-Version", "13").header(ua.L, ua.M);
        final Request build2 = !(header instanceof Request.Builder) ? header.build() : OkHttp3Instrumentation.build(header);
        RealCall realCall = new RealCall(build, build2, true);
        this.call = realCall;
        uhy.d(realCall);
        realCall.enqueue(new Callback() { // from class: okhttp3.internal.ws.RealWebSocket$connect$1
            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) {
                boolean isValid;
                ArrayDeque arrayDeque;
                uhy.e((Object) call, "");
                uhy.e((Object) response, "");
                Exchange exchange = response.getExchange();
                try {
                    RealWebSocket.this.checkUpgradeSuccess$okhttp(response, exchange);
                    uhy.d(exchange);
                    RealWebSocket.Streams newWebSocketStreams = exchange.newWebSocketStreams();
                    WebSocketExtensions parse = WebSocketExtensions.INSTANCE.parse(response.headers());
                    RealWebSocket.this.extensions = parse;
                    isValid = RealWebSocket.this.isValid(parse);
                    if (!isValid) {
                        RealWebSocket realWebSocket = RealWebSocket.this;
                        synchronized (realWebSocket) {
                            arrayDeque = realWebSocket.messageAndCloseQueue;
                            arrayDeque.clear();
                            realWebSocket.close(1010, "unexpected Sec-WebSocket-Extensions in response header");
                        }
                    }
                    try {
                        RealWebSocket.this.initReaderAndWriter(Util.okHttpName + " WebSocket " + build2.url().redact(), newWebSocketStreams);
                        RealWebSocket.this.getListener().onOpen(RealWebSocket.this, response);
                        RealWebSocket.this.loopReader();
                    } catch (Exception e) {
                        RealWebSocket.this.failWebSocket(e, null);
                    }
                } catch (IOException e2) {
                    RealWebSocket.this.failWebSocket(e2, response);
                    Util.closeQuietly(response);
                    if (exchange != null) {
                        exchange.webSocketUpgradeFailed();
                    }
                }
            }

            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException e) {
                uhy.e((Object) call, "");
                uhy.e((Object) e, "");
                RealWebSocket.this.failWebSocket(e, null);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isValid(WebSocketExtensions webSocketExtensions) {
        if (!webSocketExtensions.unknownValues && webSocketExtensions.clientMaxWindowBits == null) {
            return webSocketExtensions.serverMaxWindowBits == null || new uiv(8, 15).b(webSocketExtensions.serverMaxWindowBits.intValue());
        }
        return false;
    }

    public final void checkUpgradeSuccess$okhttp(Response response, Exchange exchange) throws IOException {
        uhy.e((Object) response, "");
        if (response.code() != 101) {
            throw new ProtocolException("Expected HTTP 101 response but was '" + response.code() + ' ' + response.message() + '\'');
        }
        String header$default = Response.header$default(response, "Connection", null, 2, null);
        if (!ujy.c("Upgrade", header$default, true)) {
            throw new ProtocolException("Expected 'Connection' header value 'Upgrade' but was '" + header$default + '\'');
        }
        String header$default2 = Response.header$default(response, "Upgrade", null, 2, null);
        if (!ujy.c("websocket", header$default2, true)) {
            throw new ProtocolException("Expected 'Upgrade' header value 'websocket' but was '" + header$default2 + '\'');
        }
        String header$default3 = Response.header$default(response, "Sec-WebSocket-Accept", null, 2, null);
        String base64 = ByteString.INSTANCE.encodeUtf8(this.key + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11").sha1().base64();
        if (uhy.e((Object) base64, (Object) header$default3)) {
            if (exchange == null) {
                throw new ProtocolException("Web Socket exchange missing: bad interceptor?");
            }
            return;
        }
        throw new ProtocolException("Expected 'Sec-WebSocket-Accept' header value '" + base64 + "' but was '" + header$default3 + '\'');
    }

    public final void initReaderAndWriter(String name, Streams streams) throws IOException {
        uhy.e((Object) name, "");
        uhy.e((Object) streams, "");
        WebSocketExtensions webSocketExtensions = this.extensions;
        uhy.d(webSocketExtensions);
        synchronized (this) {
            this.name = name;
            this.streams = streams;
            this.writer = new WebSocketWriter(streams.getClient(), streams.getSink(), this.random, webSocketExtensions.perMessageDeflate, webSocketExtensions.noContextTakeover(streams.getClient()), this.minimumDeflateSize);
            this.writerTask = new WriterTask();
            if (this.pingIntervalMillis != 0) {
                final long nanos = TimeUnit.MILLISECONDS.toNanos(this.pingIntervalMillis);
                final String str = name + " ping";
                this.taskQueue.schedule(new Task(str) { // from class: okhttp3.internal.ws.RealWebSocket$initReaderAndWriter$lambda$3$$inlined$schedule$1
                    @Override // okhttp3.internal.concurrent.Task
                    public long runOnce() {
                        this.writePingFrame$okhttp();
                        return nanos;
                    }
                }, nanos);
            }
            if (!this.messageAndCloseQueue.isEmpty()) {
                runWriter();
            }
            ueu ueuVar = ueu.d;
        }
        this.reader = new WebSocketReader(streams.getClient(), streams.getSource(), this, webSocketExtensions.perMessageDeflate, webSocketExtensions.noContextTakeover(!streams.getClient()));
    }

    public final void loopReader() throws IOException {
        while (this.receivedCloseCode == -1) {
            WebSocketReader webSocketReader = this.reader;
            uhy.d(webSocketReader);
            webSocketReader.processNextFrame();
        }
    }

    public final boolean processNextFrame() throws IOException {
        try {
            WebSocketReader webSocketReader = this.reader;
            uhy.d(webSocketReader);
            webSocketReader.processNextFrame();
        } catch (Exception e) {
            failWebSocket(e, null);
        }
        return this.receivedCloseCode == -1;
    }

    public final void awaitTermination(long timeout, TimeUnit timeUnit) throws InterruptedException {
        uhy.e((Object) timeUnit, "");
        this.taskQueue.idleLatch().await(timeout, timeUnit);
    }

    public final void tearDown() throws InterruptedException {
        this.taskQueue.shutdown();
        this.taskQueue.idleLatch().await(10L, TimeUnit.SECONDS);
    }

    public final int sentPingCount() {
        int i;
        synchronized (this) {
            i = this.sentPingCount;
        }
        return i;
    }

    public final int receivedPingCount() {
        int i;
        synchronized (this) {
            i = this.receivedPingCount;
        }
        return i;
    }

    public final int receivedPongCount() {
        int i;
        synchronized (this) {
            i = this.receivedPongCount;
        }
        return i;
    }

    @Override // okhttp3.internal.ws.WebSocketReader.FrameCallback
    public void onReadMessage(String text) throws IOException {
        uhy.e((Object) text, "");
        this.listener.onMessage(this, text);
    }

    @Override // okhttp3.internal.ws.WebSocketReader.FrameCallback
    public void onReadMessage(ByteString bytes) throws IOException {
        uhy.e((Object) bytes, "");
        this.listener.onMessage(this, bytes);
    }

    @Override // okhttp3.internal.ws.WebSocketReader.FrameCallback
    public void onReadPing(ByteString payload) {
        synchronized (this) {
            uhy.e((Object) payload, "");
            if (!this.failed && (!this.enqueuedClose || !this.messageAndCloseQueue.isEmpty())) {
                this.pongQueue.add(payload);
                runWriter();
                this.receivedPingCount++;
            }
        }
    }

    @Override // okhttp3.internal.ws.WebSocketReader.FrameCallback
    public void onReadPong(ByteString payload) {
        synchronized (this) {
            uhy.e((Object) payload, "");
            this.receivedPongCount++;
            this.awaitingPong = false;
        }
    }

    @Override // okhttp3.internal.ws.WebSocketReader.FrameCallback
    public void onReadClose(int code, String reason) {
        Streams streams;
        WebSocketReader webSocketReader;
        WebSocketWriter webSocketWriter;
        uhy.e((Object) reason, "");
        if (code == -1) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        synchronized (this) {
            if (this.receivedCloseCode != -1) {
                throw new IllegalStateException("already closed".toString());
            }
            this.receivedCloseCode = code;
            this.receivedCloseReason = reason;
            streams = null;
            if (this.enqueuedClose && this.messageAndCloseQueue.isEmpty()) {
                Streams streams2 = this.streams;
                this.streams = null;
                webSocketReader = this.reader;
                this.reader = null;
                webSocketWriter = this.writer;
                this.writer = null;
                this.taskQueue.shutdown();
                streams = streams2;
            } else {
                webSocketReader = null;
                webSocketWriter = null;
            }
            ueu ueuVar = ueu.d;
        }
        try {
            this.listener.onClosing(this, code, reason);
            if (streams != null) {
                this.listener.onClosed(this, code, reason);
            }
            if (streams != null) {
                Util.closeQuietly(streams);
            }
            if (webSocketReader != null) {
                Util.closeQuietly(webSocketReader);
            }
            if (webSocketWriter != null) {
                Util.closeQuietly(webSocketWriter);
            }
        } catch (Throwable th) {
            if (streams != null) {
                Util.closeQuietly(streams);
            }
            if (webSocketReader != null) {
                Util.closeQuietly(webSocketReader);
            }
            if (webSocketWriter != null) {
                Util.closeQuietly(webSocketWriter);
            }
            throw th;
        }
    }

    @Override // okhttp3.WebSocket
    public boolean send(String text) {
        uhy.e((Object) text, "");
        return send(ByteString.INSTANCE.encodeUtf8(text), 1);
    }

    @Override // okhttp3.WebSocket
    public boolean send(ByteString bytes) {
        uhy.e((Object) bytes, "");
        return send(bytes, 2);
    }

    private final boolean send(ByteString data, int formatOpcode) {
        synchronized (this) {
            if (!this.failed && !this.enqueuedClose) {
                if (this.queueSize + data.size() > 16777216) {
                    close(1001, null);
                    return false;
                }
                this.queueSize += data.size();
                this.messageAndCloseQueue.add(new Message(formatOpcode, data));
                runWriter();
                return true;
            }
            return false;
        }
    }

    public final boolean pong(ByteString payload) {
        synchronized (this) {
            uhy.e((Object) payload, "");
            if (!this.failed && (!this.enqueuedClose || !this.messageAndCloseQueue.isEmpty())) {
                this.pongQueue.add(payload);
                runWriter();
                return true;
            }
            return false;
        }
    }

    @Override // okhttp3.WebSocket
    public boolean close(int code, String reason) {
        return close(code, reason, 60000L);
    }

    public final boolean close(int code, String reason, long cancelAfterCloseMillis) {
        ByteString byteString;
        synchronized (this) {
            WebSocketProtocol.INSTANCE.validateCloseCode(code);
            if (reason != null) {
                byteString = ByteString.INSTANCE.encodeUtf8(reason);
                if (byteString.size() > 123) {
                    throw new IllegalArgumentException(("reason.size() > 123: " + reason).toString());
                }
            } else {
                byteString = null;
            }
            if (!this.failed && !this.enqueuedClose) {
                this.enqueuedClose = true;
                this.messageAndCloseQueue.add(new Close(code, byteString, cancelAfterCloseMillis));
                runWriter();
                return true;
            }
            return false;
        }
    }

    public final boolean writeOneFrame$okhttp() throws IOException {
        Streams streams;
        String str;
        Closeable closeable;
        Closeable closeable2;
        Closeable closeable3;
        synchronized (this) {
            if (this.failed) {
                return false;
            }
            WebSocketWriter webSocketWriter = this.writer;
            ByteString poll = this.pongQueue.poll();
            final boolean z = true;
            int i = -1;
            Object obj = null;
            Streams streams2 = null;
            if (poll == null) {
                Object poll2 = this.messageAndCloseQueue.poll();
                if (poll2 instanceof Close) {
                    int i2 = this.receivedCloseCode;
                    str = this.receivedCloseReason;
                    if (i2 != -1) {
                        Streams streams3 = this.streams;
                        this.streams = null;
                        closeable = this.reader;
                        this.reader = null;
                        Closeable closeable4 = this.writer;
                        this.writer = null;
                        this.taskQueue.shutdown();
                        streams2 = streams3;
                        i = i2;
                        closeable3 = closeable4;
                        streams = streams2;
                        obj = poll2;
                        closeable2 = closeable3;
                    } else {
                        long cancelAfterCloseMillis = ((Close) poll2).getCancelAfterCloseMillis();
                        TaskQueue taskQueue = this.taskQueue;
                        final String str2 = this.name + " cancel";
                        taskQueue.schedule(new Task(str2, z) { // from class: okhttp3.internal.ws.RealWebSocket$writeOneFrame$lambda$8$$inlined$execute$default$1
                            @Override // okhttp3.internal.concurrent.Task
                            public long runOnce() {
                                this.cancel();
                                return -1L;
                            }
                        }, TimeUnit.MILLISECONDS.toNanos(cancelAfterCloseMillis));
                        i = i2;
                        closeable = null;
                    }
                } else {
                    if (poll2 == null) {
                        return false;
                    }
                    str = null;
                    closeable = null;
                }
                closeable3 = closeable;
                streams = streams2;
                obj = poll2;
                closeable2 = closeable3;
            } else {
                streams = null;
                str = null;
                closeable = null;
                closeable2 = null;
            }
            ueu ueuVar = ueu.d;
            try {
                if (poll != null) {
                    uhy.d(webSocketWriter);
                    webSocketWriter.writePong(poll);
                } else if (obj instanceof Message) {
                    Message message = (Message) obj;
                    uhy.d(webSocketWriter);
                    webSocketWriter.writeMessageFrame(message.getFormatOpcode(), message.getData());
                    synchronized (this) {
                        this.queueSize -= message.getData().size();
                        ueu ueuVar2 = ueu.d;
                    }
                } else if (obj instanceof Close) {
                    Close close = (Close) obj;
                    uhy.d(webSocketWriter);
                    webSocketWriter.writeClose(close.getCode(), close.getReason());
                    if (streams != null) {
                        uhy.d((Object) str);
                        this.listener.onClosed(this, i, str);
                    }
                } else {
                    throw new AssertionError();
                }
                if (streams != null) {
                    Util.closeQuietly(streams);
                }
                if (closeable != null) {
                    Util.closeQuietly(closeable);
                }
                if (closeable2 != null) {
                    Util.closeQuietly(closeable2);
                }
                return true;
            } catch (Throwable th) {
                if (streams != null) {
                    Util.closeQuietly(streams);
                }
                if (closeable != null) {
                    Util.closeQuietly(closeable);
                }
                if (closeable2 != null) {
                    Util.closeQuietly(closeable2);
                }
                throw th;
            }
        }
    }

    public final void writePingFrame$okhttp() {
        synchronized (this) {
            if (this.failed) {
                return;
            }
            WebSocketWriter webSocketWriter = this.writer;
            if (webSocketWriter == null) {
                return;
            }
            int i = this.awaitingPong ? this.sentPingCount : -1;
            this.sentPingCount++;
            this.awaitingPong = true;
            ueu ueuVar = ueu.d;
            if (i != -1) {
                failWebSocket(new SocketTimeoutException("sent ping but didn't receive pong within " + this.pingIntervalMillis + "ms (after " + (i - 1) + " successful ping/pongs)"), null);
                return;
            }
            try {
                webSocketWriter.writePing(ByteString.EMPTY);
            } catch (IOException e) {
                failWebSocket(e, null);
            }
        }
    }

    public final void failWebSocket(Exception e, Response response) {
        uhy.e((Object) e, "");
        synchronized (this) {
            if (this.failed) {
                return;
            }
            this.failed = true;
            Streams streams = this.streams;
            this.streams = null;
            WebSocketReader webSocketReader = this.reader;
            this.reader = null;
            WebSocketWriter webSocketWriter = this.writer;
            this.writer = null;
            this.taskQueue.shutdown();
            ueu ueuVar = ueu.d;
            try {
                this.listener.onFailure(this, e, response);
                if (streams != null) {
                    Util.closeQuietly(streams);
                }
                if (webSocketReader != null) {
                    Util.closeQuietly(webSocketReader);
                }
                if (webSocketWriter != null) {
                    Util.closeQuietly(webSocketWriter);
                }
            } catch (Throwable th) {
                if (streams != null) {
                    Util.closeQuietly(streams);
                }
                if (webSocketReader != null) {
                    Util.closeQuietly(webSocketReader);
                }
                if (webSocketWriter != null) {
                    Util.closeQuietly(webSocketWriter);
                }
                throw th;
            }
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lokhttp3/internal/ws/RealWebSocket$Message;", "", "formatOpcode", "", "data", "Lokio/ByteString;", "(ILokio/ByteString;)V", "getData", "()Lokio/ByteString;", "getFormatOpcode", "()I", y.b.j}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes10.dex */
    public static final class Message {
        private final ByteString data;
        private final int formatOpcode;

        public Message(int i, ByteString byteString) {
            uhy.e((Object) byteString, "");
            this.formatOpcode = i;
            this.data = byteString;
        }

        public final int getFormatOpcode() {
            return this.formatOpcode;
        }

        public final ByteString getData() {
            return this.data;
        }
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lokhttp3/internal/ws/RealWebSocket$Close;", "", "code", "", "reason", "Lokio/ByteString;", "cancelAfterCloseMillis", "", "(ILokio/ByteString;J)V", "getCancelAfterCloseMillis", "()J", "getCode", "()I", "getReason", "()Lokio/ByteString;", y.b.j}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes10.dex */
    public static final class Close {
        private final long cancelAfterCloseMillis;
        private final int code;
        private final ByteString reason;

        public Close(int i, ByteString byteString, long j) {
            this.code = i;
            this.reason = byteString;
            this.cancelAfterCloseMillis = j;
        }

        public final int getCode() {
            return this.code;
        }

        public final ByteString getReason() {
            return this.reason;
        }

        public final long getCancelAfterCloseMillis() {
            return this.cancelAfterCloseMillis;
        }
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b&\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lokhttp3/internal/ws/RealWebSocket$Streams;", "Ljava/io/Closeable;", "client", "", "source", "Lokio/BufferedSource;", "sink", "Lokio/BufferedSink;", "(ZLokio/BufferedSource;Lokio/BufferedSink;)V", "getClient", "()Z", "getSink", "()Lokio/BufferedSink;", "getSource", "()Lokio/BufferedSource;", y.b.j}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes10.dex */
    public static abstract class Streams implements Closeable {
        private final boolean client;
        private final BufferedSink sink;
        private final BufferedSource source;

        public Streams(boolean z, BufferedSource bufferedSource, BufferedSink bufferedSink) {
            uhy.e((Object) bufferedSource, "");
            uhy.e((Object) bufferedSink, "");
            this.client = z;
            this.source = bufferedSource;
            this.sink = bufferedSink;
        }

        public final boolean getClient() {
            return this.client;
        }

        public final BufferedSource getSource() {
            return this.source;
        }

        public final BufferedSink getSink() {
            return this.sink;
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lokhttp3/internal/ws/RealWebSocket$WriterTask;", "Lokhttp3/internal/concurrent/Task;", "(Lokhttp3/internal/ws/RealWebSocket;)V", "runOnce", "", y.b.j}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes10.dex */
    final class WriterTask extends Task {
        public WriterTask() {
            super(RealWebSocket.this.name + " writer", false, 2, null);
        }

        @Override // okhttp3.internal.concurrent.Task
        public long runOnce() {
            try {
                return RealWebSocket.this.writeOneFrame$okhttp() ? 0L : -1L;
            } catch (IOException e) {
                RealWebSocket.this.failWebSocket(e, null);
                return -1L;
            }
        }
    }

    private final void runWriter() {
        if (!Util.assertionsEnabled || Thread.holdsLock(this)) {
            Task task = this.writerTask;
            if (task != null) {
                TaskQueue.schedule$default(this.taskQueue, task, 0L, 2, null);
                return;
            }
            return;
        }
        throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST hold lock on " + this);
    }
}
