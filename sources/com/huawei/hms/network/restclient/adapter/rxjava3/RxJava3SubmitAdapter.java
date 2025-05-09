package com.huawei.hms.network.restclient.adapter.rxjava3;

import com.huawei.hms.network.restclient.SubmitAdapter;
import io.reactivex.rxjava3.core.Scheduler;
import java.lang.reflect.Type;
import javax.annotation.Nullable;

/* loaded from: classes9.dex */
final class RxJava3SubmitAdapter<R> extends SubmitAdapter<R, Object> {
    private final boolean isAsync;
    private final boolean isBody;
    private final boolean isCompletable;
    private final boolean isFlowable;
    private final boolean isMaybe;
    private final boolean isResult;
    private final boolean isSingle;
    private final Type responseType;

    @Nullable
    private final Scheduler scheduler;

    RxJava3SubmitAdapter(Type type, @Nullable Scheduler scheduler, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7) {
        this.responseType = type;
        this.scheduler = scheduler;
        this.isResult = z2;
        this.isBody = z3;
        this.isSingle = z5;
        this.isAsync = z;
        this.isFlowable = z4;
        this.isCompletable = z7;
        this.isMaybe = z6;
    }

    @Override // com.huawei.hms.network.restclient.SubmitAdapter
    public Type responseType() {
        return this.responseType;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0028  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0037  */
    @Override // com.huawei.hms.network.restclient.SubmitAdapter
    /* renamed from: adapt */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object adapt2(com.huawei.hms.network.httpclient.Submit<R> r2) {
        /*
            r1 = this;
            boolean r0 = r1.isAsync
            if (r0 == 0) goto La
            com.huawei.hms.network.restclient.adapter.rxjava3.SubmitEnqueueObservable r0 = new com.huawei.hms.network.restclient.adapter.rxjava3.SubmitEnqueueObservable
            r0.<init>(r2)
            goto Lf
        La:
            com.huawei.hms.network.restclient.adapter.rxjava3.SubmitExecuteObservable r0 = new com.huawei.hms.network.restclient.adapter.rxjava3.SubmitExecuteObservable
            r0.<init>(r2)
        Lf:
            boolean r2 = r1.isResult
            if (r2 == 0) goto L1a
            com.huawei.hms.network.restclient.adapter.rxjava3.ResultObservable r2 = new com.huawei.hms.network.restclient.adapter.rxjava3.ResultObservable
            r2.<init>(r0)
        L18:
            r0 = r2
            goto L24
        L1a:
            boolean r2 = r1.isBody
            if (r2 == 0) goto L24
            com.huawei.hms.network.restclient.adapter.rxjava3.BodyObservable r2 = new com.huawei.hms.network.restclient.adapter.rxjava3.BodyObservable
            r2.<init>(r0)
            goto L18
        L24:
            io.reactivex.rxjava3.core.Scheduler r2 = r1.scheduler
            if (r2 == 0) goto L2c
            io.reactivex.rxjava3.core.Observable r0 = r0.subscribeOn(r2)
        L2c:
            boolean r2 = r1.isFlowable
            if (r2 == 0) goto L37
            io.reactivex.rxjava3.core.BackpressureStrategy r2 = io.reactivex.rxjava3.core.BackpressureStrategy.LATEST
            io.reactivex.rxjava3.core.Flowable r2 = r0.toFlowable(r2)
            return r2
        L37:
            boolean r2 = r1.isSingle
            if (r2 == 0) goto L40
            io.reactivex.rxjava3.core.Single r2 = r0.singleOrError()
            return r2
        L40:
            boolean r2 = r1.isMaybe
            if (r2 == 0) goto L49
            io.reactivex.rxjava3.core.Maybe r2 = r0.singleElement()
            return r2
        L49:
            boolean r2 = r1.isCompletable
            if (r2 == 0) goto L52
            io.reactivex.rxjava3.core.Completable r2 = r0.ignoreElements()
            return r2
        L52:
            io.reactivex.rxjava3.core.Observable r2 = io.reactivex.rxjava3.plugins.RxJavaPlugins.onAssembly(r0)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.network.restclient.adapter.rxjava3.RxJava3SubmitAdapter.adapt2(com.huawei.hms.network.httpclient.Submit):java.lang.Object");
    }
}
