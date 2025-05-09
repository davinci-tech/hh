package com.huawei.hms.network.restclient.adapter.rxjava3;

import com.huawei.hms.network.base.util.TypeUtils;
import com.huawei.hms.network.httpclient.Response;
import com.huawei.hms.network.restclient.RestClient;
import com.huawei.hms.network.restclient.SubmitAdapter;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.Single;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import javax.annotation.Nullable;

/* loaded from: classes9.dex */
public final class RxJava3SubmitAdapterFactory extends SubmitAdapter.Factory {
    private final boolean isAsync;

    @Nullable
    private final Scheduler scheduler;

    private RxJava3SubmitAdapterFactory(@Nullable Scheduler scheduler, boolean z) {
        this.scheduler = scheduler;
        this.isAsync = z;
    }

    public static RxJava3SubmitAdapterFactory create() {
        return new RxJava3SubmitAdapterFactory(null, false);
    }

    public static RxJava3SubmitAdapterFactory createAsync() {
        return new RxJava3SubmitAdapterFactory(null, true);
    }

    public static RxJava3SubmitAdapterFactory createWithScheduler(Scheduler scheduler) {
        if (scheduler == null) {
            throw new NullPointerException("scheduler == null");
        }
        return new RxJava3SubmitAdapterFactory(scheduler, false);
    }

    @Override // com.huawei.hms.network.restclient.SubmitAdapter.Factory
    public RxJava3SubmitAdapter<Object> get(Type type, Annotation[] annotationArr, RestClient restClient) {
        Type type2;
        boolean z;
        boolean z2;
        Class<?> rawType = TypeUtils.getRawType(type);
        if (rawType == Completable.class) {
            return new RxJava3SubmitAdapter<>(Void.class, this.scheduler, this.isAsync, false, true, false, false, false, true);
        }
        boolean z3 = rawType == Flowable.class;
        boolean z4 = rawType == Maybe.class;
        boolean z5 = rawType == Single.class;
        if (Observable.class != rawType && !z3 && !z5 && !z4) {
            return null;
        }
        if (!(type instanceof ParameterizedType)) {
            String str = !z3 ? !z5 ? z4 ? "Maybe" : "Observable" : "Single" : "Flowable";
            throw new IllegalStateException(str + " return type must be parameterized as " + str + "<Foo> or " + str + "<? extends Foo>");
        }
        Type parameterUpperBound = TypeUtils.getParameterUpperBound(0, (ParameterizedType) type);
        Class<?> rawType2 = TypeUtils.getRawType(parameterUpperBound);
        if (Response.class == rawType2) {
            if (!(parameterUpperBound instanceof ParameterizedType)) {
                throw new IllegalStateException("Response must be parameterized as Response<Foo> or Response<? extends Foo>");
            }
            type2 = TypeUtils.getParameterUpperBound(0, (ParameterizedType) parameterUpperBound);
            z2 = false;
            z = false;
        } else if (Result.class != rawType2) {
            type2 = parameterUpperBound;
            z = true;
            z2 = false;
        } else {
            if (!(parameterUpperBound instanceof ParameterizedType)) {
                throw new IllegalStateException("Result must be parameterized as Result<Foo> or Result<? extends Foo>");
            }
            type2 = TypeUtils.getParameterUpperBound(0, (ParameterizedType) parameterUpperBound);
            z2 = true;
            z = false;
        }
        return new RxJava3SubmitAdapter<>(type2, this.scheduler, this.isAsync, z2, z, z3, z5, z4, false);
    }
}
