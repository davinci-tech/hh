package com.huawei.hms.network.embedded;

import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.network.restclient.hwhttp.ClientConfiguration;
import com.huawei.hms.network.httpclient.RequestBody;
import com.huawei.hms.network.restclient.Converter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public abstract class g6<T> {

    /* renamed from: a, reason: collision with root package name */
    public static final String f5264a = "ParameterBuilder";

    public abstract void a(j6 j6Var, T t) throws IOException;

    public static final class k<T> extends g6<Map<String, T>> {
        public final Converter<T, String> b;
        public final boolean c;

        @Override // com.huawei.hms.network.embedded.g6
        public void a(j6 j6Var, Map<String, T> map) throws IOException {
            if (map == null) {
                g6.b("Query map was null.");
            }
            for (Map.Entry<String, T> entry : map.entrySet()) {
                String key = entry.getKey();
                if (key == null) {
                    g6.b("Query map contained null key.");
                }
                T value = entry.getValue();
                if (value == null) {
                    g6.b("Query map contained null value for key '" + key + "'.");
                }
                String convert = this.b.convert(value);
                if (convert == null) {
                    g6.b("Query map value '" + value + "' converted to null by " + this.b.getClass().getName() + " for key '" + key + "'.");
                }
                j6Var.a(key, convert, this.c);
            }
        }

        public k(Converter<T, String> converter, boolean z) {
            this.b = converter;
            this.c = z;
        }

        public k(Converter<T, String> converter) {
            this(converter, false);
        }
    }

    public final g6<Iterable<T>> b() {
        return new b();
    }

    public static final class d extends g6<ClientConfiguration> {
        @Override // com.huawei.hms.network.embedded.g6
        public void a(j6 j6Var, ClientConfiguration clientConfiguration) {
            j6Var.setNetworkParameters(a(clientConfiguration));
        }

        private String a(ClientConfiguration clientConfiguration) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("core_call_timeout", clientConfiguration.getCallTimeout());
                jSONObject.put("core_connect_timeout", clientConfiguration.getConnectTimeout());
                jSONObject.put("core_concurrent_connect_delay", clientConfiguration.getConnectionAttemptDelay());
                jSONObject.put("core_ping_interval", clientConfiguration.getPingInterval());
                jSONObject.put("core_read_timeout", clientConfiguration.getReadTimeout());
                jSONObject.put("core_write_timeout", clientConfiguration.getWriteTimeout());
                jSONObject.put("core_retry_time", clientConfiguration.getRetryTimeOnConnectionFailure());
                return jSONObject.toString();
            } catch (JSONException unused) {
                Logger.w(g6.f5264a, "JSONException error");
                return "";
            }
        }
    }

    public static final class e<T> extends g6<Map<String, T>> {
        public final Converter<T, String> b;

        @Override // com.huawei.hms.network.embedded.g6
        public void a(j6 j6Var, Map<String, T> map) throws IOException {
            if (map == null) {
                g6.b("Field map was null.");
            }
            for (Map.Entry<String, T> entry : map.entrySet()) {
                String key = entry.getKey();
                if (key == null) {
                    g6.b("Field map contained null key.");
                }
                T value = entry.getValue();
                if (value == null) {
                    g6.b("Field map contained null value for key '" + key + "'.");
                }
                String convert = this.b.convert(value);
                if (convert == null) {
                    g6.b("Field map value '" + value + "' converted to null by " + this.b.getClass().getName() + " for key '" + key + "'.");
                }
                j6Var.a(key, convert);
            }
        }

        public e(Converter<T, String> converter) {
            this.b = converter;
        }
    }

    public static final class h<T> extends g6<Map<String, T>> {
        public final Converter<T, String> b;

        @Override // com.huawei.hms.network.embedded.g6
        public void a(j6 j6Var, Map<String, T> map) throws IOException {
            if (map == null) {
                g6.b("Header map was null.");
            }
            for (Map.Entry<String, T> entry : map.entrySet()) {
                String key = entry.getKey();
                if (key == null) {
                    g6.b("Header map contained null key.");
                }
                T value = entry.getValue();
                if (value == null) {
                    g6.b("Header map contained null value for key '" + key + "'.");
                }
                j6Var.b(key, this.b.convert(value));
            }
        }

        public h(Converter<T, String> converter) {
            this.b = converter;
        }
    }

    public static final class i extends g6<String> {
        @Override // com.huawei.hms.network.embedded.g6
        public void a(j6 j6Var, String str) {
            j6Var.setNetworkParameters(str);
        }
    }

    public static final class l<T> extends g6<T> {
        public final String b;
        public final Converter<T, String> c;
        public final boolean d;

        @Override // com.huawei.hms.network.embedded.g6
        public void a(j6 j6Var, T t) throws IOException {
            String convert;
            if (t == null || (convert = this.c.convert(t)) == null) {
                return;
            }
            j6Var.a(this.b, convert, this.d);
        }

        public l(String str, Converter<T, String> converter, boolean z) {
            if (str == null) {
                g6.b("Query parameter name must be not null.");
            }
            this.b = str;
            this.c = converter;
            this.d = z;
        }

        public l(String str, Converter<T, String> converter) {
            this(str, converter, false);
        }
    }

    public static final class m<T> extends g6<Map<String, T>> {
        public final Converter<T, String> b;

        @Override // com.huawei.hms.network.embedded.g6
        public void a(j6 j6Var, Map<String, T> map) throws IOException {
            if (map == null) {
                g6.b("Record map was null.");
            }
            try {
                JSONObject jSONObject = new JSONObject();
                JSONObject jSONObject2 = new JSONObject();
                for (Map.Entry<String, T> entry : map.entrySet()) {
                    String key = entry.getKey();
                    if (key == null) {
                        g6.b("Record map contained null key.");
                    }
                    T value = entry.getValue();
                    if (value == null) {
                        g6.b("Record map contained null value for key '" + key + "'.");
                    }
                    String convert = this.b.convert(value);
                    if (convert == null) {
                        g6.b("Record map value '" + value + "' converted to null by " + this.b.getClass().getName() + " for key '" + key + "'.");
                    }
                    jSONObject.put(key, convert);
                }
                jSONObject2.put("core_metrics_data", jSONObject);
                j6Var.setMetricsData(jSONObject2.toString());
            } catch (JSONException unused) {
                Logger.e(g6.f5264a, "parse MetricsData has occurred a JSONException");
            }
        }

        public m(Converter<T, String> converter) {
            this.b = converter;
        }
    }

    public final g6<Object> a() {
        return new a();
    }

    public class a extends g6<Object> {
        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.huawei.hms.network.embedded.g6
        public void a(j6 j6Var, Object obj) throws IOException {
            if (obj == null) {
                Logger.w(g6.f5264a, "ParameterBuilder.array.build failed, values == null");
                return;
            }
            int length = Array.getLength(obj);
            for (int i = 0; i < length; i++) {
                g6.this.a(j6Var, Array.get(obj, i));
            }
        }

        public a() {
        }
    }

    public class b extends g6<Iterable<T>> {
        @Override // com.huawei.hms.network.embedded.g6
        public void a(j6 j6Var, Iterable<T> iterable) throws IOException {
            if (iterable == null) {
                Logger.w(g6.f5264a, "ParameterBuilder.iterable.build failed, values == null");
                return;
            }
            Iterator<T> it = iterable.iterator();
            while (it.hasNext()) {
                g6.this.a(j6Var, it.next());
            }
        }

        public b() {
        }
    }

    public static final class c<T> extends g6<T> {
        public final Converter<T, RequestBody> b;

        @Override // com.huawei.hms.network.embedded.g6
        public void a(j6 j6Var, T t) throws IOException {
            if (t == null) {
                g6.b("Body parameter value must not be null.");
            }
            j6Var.a(this.b.convert(t));
        }

        public c(Converter<T, RequestBody> converter) {
            this.b = converter;
        }
    }

    public static final class f<T> extends g6<T> {
        public final String b;
        public final Converter<T, String> c;

        @Override // com.huawei.hms.network.embedded.g6
        public void a(j6 j6Var, @Nullable T t) throws IOException {
            String convert;
            if (t == null || (convert = this.c.convert(t)) == null) {
                return;
            }
            j6Var.a(this.b, convert);
        }

        public f(String str, Converter<T, String> converter) {
            if (str == null) {
                g6.b("Field parameter name must be not null.");
            }
            this.b = str;
            this.c = converter;
        }
    }

    public static final class g<T> extends g6<T> {
        public final String b;
        public final Converter<T, String> c;

        @Override // com.huawei.hms.network.embedded.g6
        public void a(j6 j6Var, T t) throws IOException {
            String convert;
            if (t == null || (convert = this.c.convert(t)) == null) {
                return;
            }
            j6Var.b(this.b, convert);
        }

        public g(String str, Converter<T, String> converter) {
            if (str == null) {
                g6.b("Header parameter name must be not null.");
            }
            this.b = str;
            this.c = converter;
        }
    }

    public static final class j<T> extends g6<T> {
        public final String b;
        public final Converter<T, String> c;

        @Override // com.huawei.hms.network.embedded.g6
        public void a(j6 j6Var, T t) throws IOException {
            if (t == null) {
                g6.b("Path parameter \"" + this.b + "\" value must not be null.");
            }
            j6Var.c(this.b, this.c.convert(t));
        }

        public j(String str, Converter<T, String> converter) {
            if (str == null) {
                g6.b("Path parameter name must be not null.");
            }
            this.b = str;
            this.c = converter;
        }
    }

    public static final class n extends g6<Object> {
        @Override // com.huawei.hms.network.embedded.g6
        public void a(j6 j6Var, Object obj) {
            if (obj == null) {
                g6.b("@Url parameter is null.");
            }
            if (obj instanceof String) {
                j6Var.a((String) obj);
            } else {
                g6.b("@Url parameter must be String.");
            }
        }
    }

    public static void b(String str) {
        throw new IllegalArgumentException(str);
    }
}
