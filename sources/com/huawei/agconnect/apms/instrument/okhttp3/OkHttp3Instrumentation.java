package com.huawei.agconnect.apms.instrument.okhttp3;

import android.text.TextUtils;
import androidx.webkit.ProxyConfig;
import com.huawei.agconnect.apms.Agent;
import com.huawei.agconnect.apms.a;
import com.huawei.agconnect.apms.c;
import com.huawei.agconnect.apms.collect.HiAnalyticsManager;
import com.huawei.agconnect.apms.collect.model.HeaderType;
import com.huawei.agconnect.apms.g;
import com.huawei.agconnect.apms.k0;
import com.huawei.agconnect.apms.log.AgentLog;
import com.huawei.agconnect.apms.log.AgentLogManager;
import com.huawei.agconnect.apms.wxy;
import com.huawei.agconnect.apms.zab;
import com.huawei.agconnect.apms.zyx;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import okhttp3.Call;
import okhttp3.EventListener;
import okhttp3.OkHttpClient;
import okhttp3.OkUrlFactory;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* loaded from: classes2.dex */
public class OkHttp3Instrumentation {
    private static final AgentLog LOG = AgentLogManager.getAgentLog();
    private static final String OKHTTP3_LISTENER_TAG = "okhttp3.RealCall";
    private static final String OKHTTP4_4_LISTENER_TAG = "okhttp3.internal.connection.RealCall";
    private static final String OKHTTP4_TRANSMITTER_TAG = "okhttp3.internal.connection.Transmitter";

    public static class abc {
        public static final Class abc;

        static {
            Class<?> cls;
            try {
                try {
                    cls = Class.forName(OkHttp3Instrumentation.OKHTTP3_LISTENER_TAG);
                } catch (ClassNotFoundException unused) {
                    cls = Class.forName(OkHttp3Instrumentation.OKHTTP4_4_LISTENER_TAG);
                }
            } catch (ClassNotFoundException unused2) {
                OkHttp3Instrumentation.LOG.warn("cannot found target class, some network metrics cannot be collected.");
                cls = null;
            }
            if (cls != null) {
                OkHttp3Instrumentation.LOG.debug("enhance network metrics initialization succeeded.");
            }
            abc = cls;
        }
    }

    public static class bcd {
        public static final Class abc;

        static {
            Class<?> cls;
            try {
                cls = Class.forName(OkHttp3Instrumentation.OKHTTP4_TRANSMITTER_TAG);
            } catch (Throwable unused) {
                cls = null;
            }
            abc = cls;
        }
    }

    private OkHttp3Instrumentation() {
    }

    private static void addEventListenerAndInject(Call call, wxy wxyVar) {
        Class cls;
        try {
            cls = getListenerParentClazz();
            if (cls == null) {
                return;
            }
            try {
                Field declaredField = cls.getDeclaredField("eventListener");
                if (!declaredField.isAccessible()) {
                    declaredField.setAccessible(true);
                }
                setListenerProxy(call, declaredField, wxyVar);
            } catch (Throwable th) {
                th = th;
                try {
                    if (tryAddEventListenerToTransmitter(call, cls, wxyVar)) {
                        return;
                    }
                    LOG.error(th.getMessage());
                } catch (Throwable th2) {
                    LOG.error(th2.getMessage());
                }
            }
        } catch (Throwable th3) {
            th = th3;
            cls = null;
        }
    }

    private static void addNuwaHeaderId(Request.Builder builder, String str, String str2) {
        if (TextUtils.isEmpty(builder.build().header("net-msg-id"))) {
            builder.addHeader("net-msg-id", str);
        }
        if (TextUtils.isEmpty(builder.build().header(HeaderType.X_NUWA_SPAN_ID))) {
            builder.addHeader(HeaderType.X_NUWA_SPAN_ID, str2);
        }
        if (TextUtils.isEmpty(builder.build().header(HeaderType.X_NUWA_MICROSERVICE_NAME))) {
            builder.addHeader(HeaderType.X_NUWA_MICROSERVICE_NAME, HiAnalyticsManager.APM_EVENT_ID);
        }
    }

    public static Response.Builder body(Response.Builder builder, ResponseBody responseBody) {
        if (Agent.isDisabled()) {
            return builder.body(responseBody);
        }
        try {
            return new g(builder).abc.body(responseBody);
        } catch (Throwable th) {
            LOG.warn("skipping APMS OkHttp3 proxy: " + th.getMessage());
            return builder.body(responseBody);
        }
    }

    public static Request build(Request.Builder builder) {
        if (Agent.isDisabled()) {
            return builder.build();
        }
        try {
            if (Agent.isNuwaTracerEnable()) {
                addNuwaHeaderId(builder, k0.bcd(), k0.abc());
            }
            return builder.build();
        } catch (Throwable th) {
            LOG.warn("skipping APMS OkHttp3 proxy: " + th.getMessage());
            return builder.build();
        }
    }

    private static Class getListenerParentClazz() {
        return abc.abc;
    }

    private static Class getTransmitterClazz() {
        return bcd.abc;
    }

    private static String getValueFromHeader(Request request, String str) {
        if (!TextUtils.isEmpty(str)) {
            String header = request.header(str);
            if (!TextUtils.isEmpty(header)) {
                return header;
            }
        }
        return "";
    }

    public static Response.Builder newBuilder(Response.Builder builder) {
        if (Agent.isDisabled()) {
            return builder;
        }
        try {
            return new g(builder);
        } catch (Throwable th) {
            LOG.warn("skipping APMS OkHttp3 proxy: " + th.getMessage());
            return builder;
        }
    }

    public static Call newCall(OkHttpClient okHttpClient, Request request) {
        if (Agent.isDisabled()) {
            return okHttpClient.newCall(request);
        }
        try {
            wxy wxyVar = new wxy();
            wxyVar.jih = getValueFromHeader(request, "sessionId");
            wxyVar.ihg = getValueFromHeader(request, "interactionId");
            wxyVar.hgf = getValueFromHeader(request, "traceId");
            if (Agent.isNuwaTracerEnable()) {
                wxyVar.fed = getValueFromHeader(request, "net-msg-id");
                wxyVar.edc = getValueFromHeader(request, HeaderType.X_NUWA_SPAN_ID);
            }
            Call newCall = okHttpClient.newCall(request);
            addEventListenerAndInject(newCall, wxyVar);
            wxyVar.ghi = 2;
            return new a(request, newCall, wxyVar);
        } catch (Throwable th) {
            LOG.warn("skipping APMS OkHttp3 proxy: " + th.getMessage());
            return okHttpClient.newCall(request);
        }
    }

    public static HttpURLConnection open(OkUrlFactory okUrlFactory, URL url) {
        HttpURLConnection open = okUrlFactory.open(url);
        if (Agent.isDisabled()) {
            return open;
        }
        try {
            return (ProxyConfig.MATCH_HTTPS.equals(url.getProtocol()) && (open instanceof HttpsURLConnection)) ? new zyx((HttpsURLConnection) open) : new zab(open);
        } catch (Throwable th) {
            LOG.warn("skipping APMS OkHttp3 proxy: " + th.getMessage());
            return open;
        }
    }

    private static void setListenerProxy(Object obj, Field field, wxy wxyVar) {
        try {
            field.set(obj, new c((EventListener) field.get(obj), wxyVar));
        } catch (Throwable th) {
            LOG.error(th.getMessage());
        }
    }

    private static boolean tryAddEventListenerToTransmitter(Call call, Class cls, wxy wxyVar) {
        if (call != null && cls != null && wxyVar != null) {
            Class transmitterClazz = getTransmitterClazz();
            try {
                Field declaredField = cls.getDeclaredField("transmitter");
                Field declaredField2 = transmitterClazz.getDeclaredField("eventListener");
                if (!declaredField.isAccessible()) {
                    declaredField.setAccessible(true);
                }
                if (!declaredField2.isAccessible()) {
                    declaredField2.setAccessible(true);
                }
                try {
                    setListenerProxy(declaredField.get(call), declaredField2, wxyVar);
                    return true;
                } catch (Throwable th) {
                    wxyVar.abc(th.getMessage());
                    LOG.error(th.getMessage());
                    return false;
                }
            } catch (NoSuchFieldException e) {
                wxyVar.abc(e.getMessage());
                LOG.error(e.getMessage());
            }
        }
        return false;
    }
}
