package io.reactivex.rxjava3.internal.operators.maybe;

import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.core.MaybeSource;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.fuseable.FuseToMaybe;
import io.reactivex.rxjava3.internal.fuseable.HasUpstreamMaybeSource;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;

/* loaded from: classes.dex */
public final class MaybeIsEmptySingle<T> extends Single<Boolean> implements HasUpstreamMaybeSource<T>, FuseToMaybe<Boolean> {
    final MaybeSource<T> source;

    public MaybeIsEmptySingle(MaybeSource<T> maybeSource) {
        this.source = maybeSource;
    }

    @Override // io.reactivex.rxjava3.internal.fuseable.HasUpstreamMaybeSource
    public MaybeSource<T> source() {
        return this.source;
    }

    @Override // io.reactivex.rxjava3.internal.fuseable.FuseToMaybe
    public Maybe<Boolean> fuseToMaybe() {
        return RxJavaPlugins.onAssembly(new MaybeIsEmpty(this.source));
    }

    @Override // io.reactivex.rxjava3.core.Single
    public void subscribeActual(SingleObserver<? super Boolean> singleObserver) {
        this.source.subscribe(new IsEmptyMaybeObserver(singleObserver));
    }

    /* loaded from: classes10.dex */
    static final class IsEmptyMaybeObserver<T> implements MaybeObserver<T>, Disposable {
        final SingleObserver<? super Boolean> downstream;
        Disposable upstream;

        IsEmptyMaybeObserver(SingleObserver<? super Boolean> singleObserver) {
            this.downstream = singleObserver;
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            this.upstream.dispose();
            this.upstream = DisposableHelper.DISPOSED;
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return this.upstream.isDisposed();
        }

        @Override // io.reactivex.rxjava3.core.MaybeObserver, io.reactivex.rxjava3.core.SingleObserver, io.reactivex.rxjava3.core.CompletableObserver
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.upstream, disposable)) {
                this.upstream = disposable;
                this.downstream.onSubscribe(this);
            }
        }

        @Override // io.reactivex.rxjava3.core.MaybeObserver, io.reactivex.rxjava3.core.SingleObserver
        public void onSuccess(T t) {
            this.upstream = DisposableHelper.DISPOSED;
            this.downstream.onSuccess(false);
        }

        @Override // io.reactivex.rxjava3.core.MaybeObserver, io.reactivex.rxjava3.core.SingleObserver, io.reactivex.rxjava3.core.CompletableObserver
        public void onError(Throwable th) {
            this.upstream = DisposableHelper.DISPOSED;
            this.downstream.onError(th);
        }

        @Override // io.reactivex.rxjava3.core.MaybeObserver, io.reactivex.rxjava3.core.CompletableObserver
        public void onComplete() {
            this.upstream = DisposableHelper.DISPOSED;
            this.downstream.onSuccess(true);
        }
    }
}
