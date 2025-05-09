package com.huawei.hms.network.embedded;

import com.huawei.hms.framework.common.CheckParamUtils;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.network.embedded.d6;
import com.huawei.hms.network.embedded.k6;
import com.huawei.hms.network.httpclient.HttpClient;
import com.huawei.hms.network.httpclient.RequestBody;
import com.huawei.hms.network.httpclient.ResponseBody;
import com.huawei.hms.network.httpclient.Submit;
import com.huawei.hms.network.inner.api.PolicyNetworkService;
import com.huawei.hms.network.restclient.Converter;
import com.huawei.hms.network.restclient.RestClient;
import com.huawei.hms.network.restclient.SubmitAdapter;
import com.huawei.hms.network.restclient.internal.IRestClientBuilder;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class h6 extends RestClient {
    public static final String i = "RealRestClient";

    /* renamed from: a, reason: collision with root package name */
    public final d3 f5288a;
    public final List<Converter.Factory> b;
    public final List<SubmitAdapter.Factory> c;

    @Nullable
    public final Executor d;
    public final boolean e;
    public Submit.Factory f;
    public final boolean g;
    public final Map<Method, k6<?, ?>> h;

    public SubmitAdapter<?, ?> submitAdapter(Type type, Annotation[] annotationArr) {
        return nextSubmitAdapter(null, type, annotationArr);
    }

    public <T> Converter<T, String> stringConverter(Type type, Annotation[] annotationArr) {
        CheckParamUtils.checkNotNull(annotationArr, "annotations == null");
        CheckParamUtils.checkNotNull(type, "type == null");
        int size = this.b.size();
        for (int i2 = 0; i2 < size; i2++) {
            Converter<T, String> converter = (Converter<T, String>) this.b.get(i2).stringConverter(type, annotationArr, this);
            if (converter != null) {
                return converter;
            }
        }
        return d6.c.f5217a;
    }

    public <T> Converter<ResponseBody, T> responseBodyConverter(Type type, Annotation[] annotationArr) {
        return nextResponseBodyConverter(null, type, annotationArr);
    }

    public <T> Converter<T, RequestBody> requestBodyConverter(Type type, Annotation[] annotationArr, Annotation[] annotationArr2) {
        return nextRequestBodyConverter(null, type, annotationArr, annotationArr2);
    }

    public SubmitAdapter<?, ?> nextSubmitAdapter(SubmitAdapter.Factory factory, Type type, Annotation[] annotationArr) {
        CheckParamUtils.checkNotNull(type, "returnType == null");
        CheckParamUtils.checkNotNull(annotationArr, "annotations == null");
        int size = this.c.size();
        for (int indexOf = this.c.indexOf(factory) + 1; indexOf < size; indexOf++) {
            SubmitAdapter<?, ?> submitAdapter = this.c.get(indexOf).get(type, annotationArr, this);
            if (submitAdapter != null) {
                return submitAdapter;
            }
        }
        throw new IllegalArgumentException("Could not locate submit adapter for " + type);
    }

    public <T> Converter<ResponseBody, T> nextResponseBodyConverter(@Nullable Converter.Factory factory, Type type, Annotation[] annotationArr) {
        CheckParamUtils.checkNotNull(type, "type == null");
        CheckParamUtils.checkNotNull(annotationArr, "annotations == null");
        int size = this.b.size();
        for (int indexOf = this.b.indexOf(factory) + 1; indexOf < size; indexOf++) {
            Converter<ResponseBody, T> converter = (Converter<ResponseBody, T>) this.b.get(indexOf).responseBodyConverter(type, annotationArr, this);
            if (converter != null) {
                return converter;
            }
        }
        throw new IllegalArgumentException("Could not locate ResponseBody converter for " + type);
    }

    public <T> Converter<T, RequestBody> nextRequestBodyConverter(@Nullable Converter.Factory factory, Type type, Annotation[] annotationArr, Annotation[] annotationArr2) {
        CheckParamUtils.checkNotNull(type, "type == null");
        CheckParamUtils.checkNotNull(annotationArr, "parameterAnnotations == null");
        CheckParamUtils.checkNotNull(annotationArr2, "methodAnnotations == null");
        int size = this.b.size();
        for (int indexOf = this.b.indexOf(factory) + 1; indexOf < size; indexOf++) {
            Converter<T, RequestBody> converter = (Converter<T, RequestBody>) this.b.get(indexOf).requestBodyConverter(type, annotationArr, annotationArr2, this);
            if (converter != null) {
                return converter;
            }
        }
        throw new IllegalArgumentException("Could not locate RequestBody converter for " + type);
    }

    public static final class b extends IRestClientBuilder {

        /* renamed from: a, reason: collision with root package name */
        public final List<Converter.Factory> f5290a;
        public final List<SubmitAdapter.Factory> b;
        public Submit.Factory c;
        public d3 d;

        @Nullable
        public Executor e;
        public boolean f;
        public boolean g;

        @Override // com.huawei.hms.network.restclient.internal.IRestClientBuilder
        public b validateEagerly(boolean z) {
            this.f = z;
            return this;
        }

        @Override // com.huawei.hms.network.restclient.internal.IRestClientBuilder
        public b submitFactory(Submit.Factory factory) {
            this.c = (Submit.Factory) CheckParamUtils.checkNotNull(factory, "factory == null");
            return this;
        }

        @Override // com.huawei.hms.network.restclient.internal.IRestClientBuilder
        public b httpClient(HttpClient httpClient) {
            CheckParamUtils.checkNotNull(httpClient, "client == null");
            return submitFactory((Submit.Factory) httpClient);
        }

        @Override // com.huawei.hms.network.restclient.internal.IRestClientBuilder
        public IRestClientBuilder disableDefaultToStringConverterFactory() {
            this.g = false;
            return this;
        }

        @Override // com.huawei.hms.network.restclient.internal.IRestClientBuilder
        public b callbackExecutor(Executor executor) {
            this.e = (Executor) CheckParamUtils.checkNotNull(executor, "executor == null");
            return this;
        }

        @Override // com.huawei.hms.network.restclient.internal.IRestClientBuilder
        public RestClient build() {
            if (this.d == null) {
                Logger.w(h6.i, "may be you need a baseUrl");
            }
            Executor executor = this.e;
            ArrayList arrayList = new ArrayList(this.b);
            arrayList.add(a(executor));
            ArrayList arrayList2 = new ArrayList(this.f5290a.size() + 2);
            arrayList2.add(new d6());
            if (this.g) {
                arrayList2.add(new l6());
            }
            arrayList2.addAll(this.f5290a);
            Logger.d(h6.i, "build time = " + j1.getBuildTime());
            return new h6(this.d, Collections.unmodifiableList(arrayList2), Collections.unmodifiableList(arrayList), executor, this.g, this.f, this.c, null);
        }

        @Override // com.huawei.hms.network.restclient.internal.IRestClientBuilder
        public b baseUrl(String str) {
            this.d = new d3(str);
            return this;
        }

        public b baseUrl(d3 d3Var) {
            CheckParamUtils.checkNotNull(d3Var, "baseUrl == null");
            this.d = d3Var;
            return this;
        }

        @Override // com.huawei.hms.network.restclient.internal.IRestClientBuilder
        public b addSubmitAdapterFactory(SubmitAdapter.Factory factory) {
            this.b.add((SubmitAdapter.Factory) CheckParamUtils.checkNotNull(factory, "factory == null"));
            return this;
        }

        @Override // com.huawei.hms.network.restclient.internal.IRestClientBuilder
        public b addConverterFactory(Converter.Factory factory) {
            this.f5290a.add((Converter.Factory) CheckParamUtils.checkNotNull(factory, "factory == null"));
            return this;
        }

        private SubmitAdapter.Factory a(@Nullable Executor executor) {
            return executor != null ? new f6(executor) : e6.f5229a;
        }

        public b(h6 h6Var) {
            ArrayList arrayList = new ArrayList();
            this.f5290a = arrayList;
            ArrayList arrayList2 = new ArrayList();
            this.b = arrayList2;
            this.g = true;
            this.d = h6Var.f5288a;
            arrayList.addAll(h6Var.b);
            boolean z = h6Var.g;
            this.g = z;
            if (z) {
                arrayList.remove(1);
            }
            arrayList.remove(0);
            arrayList2.addAll(h6Var.c);
            arrayList2.remove(arrayList2.size() - 1);
            this.e = h6Var.d;
            this.f = h6Var.e;
            this.c = h6Var.f;
        }

        public b() {
            this.f5290a = new ArrayList();
            this.b = new ArrayList();
            this.g = true;
        }
    }

    @Override // com.huawei.hms.network.restclient.RestClient
    public IRestClientBuilder newBuilder() {
        return new b(this);
    }

    public Submit.Factory getSubmitFactory() {
        return this.f;
    }

    public List<SubmitAdapter.Factory> getSubmitAdapterFactories() {
        return this.c;
    }

    public HttpClient getHttpClient() {
        Submit.Factory factory = this.f;
        if (factory instanceof HttpClient) {
            return (HttpClient) factory;
        }
        Logger.w(i, "the httpclient hasn't be set,and return the default httpclient!");
        return new HttpClient.Builder().build();
    }

    public List<Converter.Factory> getConverterFactories() {
        return this.b;
    }

    @Nullable
    public Executor getCallbackExecutor() {
        return this.d;
    }

    public d3 getBaseUrl() {
        return this.f5288a;
    }

    @Override // com.huawei.hms.network.restclient.RestClient
    public <T> T create(Class<T> cls) {
        if (!cls.isInterface()) {
            throw new IllegalArgumentException("API declarations must be interfaces.");
        }
        if (cls.getInterfaces().length > 0) {
            throw new IllegalArgumentException("API interfaces must not extend other interfaces.");
        }
        if (this.e) {
            a((Class<?>) cls);
        }
        return (T) Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new a(cls));
    }

    private void a(Class<?> cls) {
        for (Method method : cls.getDeclaredMethods()) {
            a(method, cls);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public k6<?, ?> a(Method method, Class cls) {
        k6 k6Var;
        k6<?, ?> k6Var2 = this.h.get(method);
        if (k6Var2 != null) {
            return k6Var2;
        }
        synchronized (this.h) {
            k6Var = this.h.get(method);
            if (k6Var == null) {
                k6Var = new k6.b(this, method, cls).build();
                this.h.put(method, k6Var);
            }
        }
        return k6Var;
    }

    public class a implements InvocationHandler {

        /* renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Class f5289a;

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, @Nullable Object[] objArr) {
            k6 a2 = h6.this.a(method, this.f5289a);
            return a2.a(new i6(h6.this.f, a2, objArr));
        }

        public a(Class cls) {
            this.f5289a = cls;
        }
    }

    public /* synthetic */ h6(d3 d3Var, List list, List list2, Executor executor, boolean z, boolean z2, Submit.Factory factory, a aVar) {
        this(d3Var, list, list2, executor, z, z2, factory);
    }

    public h6(d3 d3Var, List<Converter.Factory> list, List<SubmitAdapter.Factory> list2, @Nullable Executor executor, boolean z, boolean z2, Submit.Factory factory) {
        this.h = new ConcurrentHashMap();
        this.f5288a = d3Var;
        this.b = list;
        this.c = list2;
        this.d = executor;
        this.e = z2;
        this.g = z;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(PolicyNetworkService.ProfileConstants.SCENE_TYPE_OPTION_KEY, PolicyNetworkService.ProfileConstants.RESTFUL);
        } catch (JSONException e) {
            Logger.w(i, "Init httpclient occurs JSONException " + e.getMessage());
        }
        this.f = factory;
        if (factory == null) {
            this.f = new HttpClient.Builder().options(jSONObject.toString()).build();
        }
    }
}
