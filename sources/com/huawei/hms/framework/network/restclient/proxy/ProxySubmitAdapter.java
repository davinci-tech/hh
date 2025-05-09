package com.huawei.hms.framework.network.restclient.proxy;

import com.huawei.hms.framework.network.restclient.SubmitAdapter;
import com.huawei.hms.network.httpclient.Submit;
import com.huawei.hms.network.restclient.RestClient;
import com.huawei.hms.network.restclient.SubmitAdapter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/* loaded from: classes4.dex */
public class ProxySubmitAdapter<R, T> extends SubmitAdapter<R, T> {
    public com.huawei.hms.framework.network.restclient.SubmitAdapter<R, T> adapter;

    public ProxySubmitAdapter(com.huawei.hms.framework.network.restclient.SubmitAdapter<R, T> submitAdapter) {
        this.adapter = submitAdapter;
    }

    @Override // com.huawei.hms.network.restclient.SubmitAdapter
    public Type responseType() {
        return this.adapter.responseType();
    }

    @Override // com.huawei.hms.network.restclient.SubmitAdapter
    /* renamed from: adapt */
    public T adapt2(Submit<R> submit) {
        return this.adapter.adapt2(new ProxyTypeSubmit(submit));
    }

    public static class ProxySubmitAdapterFactory extends SubmitAdapter.Factory {
        private SubmitAdapter.Factory proxy;

        public ProxySubmitAdapterFactory(SubmitAdapter.Factory factory) {
            this.proxy = factory;
        }

        @Override // com.huawei.hms.network.restclient.SubmitAdapter.Factory
        public com.huawei.hms.network.restclient.SubmitAdapter<?, ?> get(Type type, Annotation[] annotationArr, RestClient restClient) {
            com.huawei.hms.framework.network.restclient.SubmitAdapter<?, ?> submitAdapter = this.proxy.get(type, annotationArr, new com.huawei.hms.framework.network.restclient.RestClient(restClient));
            if (submitAdapter == null) {
                return null;
            }
            return new ProxySubmitAdapter(submitAdapter);
        }
    }
}
