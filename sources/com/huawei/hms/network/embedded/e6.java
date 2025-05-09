package com.huawei.hms.network.embedded;

import com.huawei.hms.network.base.util.TypeUtils;
import com.huawei.hms.network.httpclient.Submit;
import com.huawei.hms.network.restclient.RestClient;
import com.huawei.hms.network.restclient.SubmitAdapter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/* loaded from: classes9.dex */
public final class e6 extends SubmitAdapter.Factory {

    /* renamed from: a, reason: collision with root package name */
    public static final SubmitAdapter.Factory f5229a = new e6();

    public class a extends SubmitAdapter<Object, Submit<?>> {

        /* renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Type f5230a;

        @Override // com.huawei.hms.network.restclient.SubmitAdapter
        /* renamed from: adapt, reason: merged with bridge method [inline-methods] */
        public Submit<?> adapt2(Submit<Object> submit) {
            return submit;
        }

        @Override // com.huawei.hms.network.restclient.SubmitAdapter
        public Type responseType() {
            return this.f5230a;
        }

        public a(Type type) {
            this.f5230a = type;
        }
    }

    @Override // com.huawei.hms.network.restclient.SubmitAdapter.Factory
    public SubmitAdapter<?, ?> get(Type type, Annotation[] annotationArr, RestClient restClient) {
        return new a(TypeUtils.getSubmitResponseType(type));
    }
}
