package com.huawei.hms.framework.network.restclient;

import com.huawei.hms.framework.network.restclient.SubmitAdapter;
import com.huawei.hms.network.base.util.TypeUtils;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/* loaded from: classes4.dex */
final class DefaultSubmitAdapterFactory extends SubmitAdapter.Factory {
    static final SubmitAdapter.Factory INSTANCE = new DefaultSubmitAdapterFactory();

    DefaultSubmitAdapterFactory() {
    }

    @Override // com.huawei.hms.framework.network.restclient.SubmitAdapter.Factory
    public SubmitAdapter<?, ?> get(Type type, Annotation[] annotationArr, RestClient restClient) {
        final Type submitResponseType = TypeUtils.getSubmitResponseType(type);
        return new SubmitAdapter<Object, Submit<?>>() { // from class: com.huawei.hms.framework.network.restclient.DefaultSubmitAdapterFactory.1
            @Override // com.huawei.hms.framework.network.restclient.SubmitAdapter
            /* renamed from: adapt, reason: merged with bridge method [inline-methods] */
            public Submit<?> adapt2(Submit<Object> submit) {
                return submit;
            }

            @Override // com.huawei.hms.framework.network.restclient.SubmitAdapter
            public Type responseType() {
                return submitResponseType;
            }
        };
    }
}
