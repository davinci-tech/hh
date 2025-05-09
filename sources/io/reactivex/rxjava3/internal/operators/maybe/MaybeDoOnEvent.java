package io.reactivex.rxjava3.internal.operators.maybe;

import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.core.MaybeSource;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.CompositeException;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.BiConsumer;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;

/* loaded from: classes.dex */
public final class MaybeDoOnEvent<T> extends AbstractMaybeWithUpstream<T, T> {
    final BiConsumer<? super T, ? super Throwable> onEvent;

    public MaybeDoOnEvent(MaybeSource<T> maybeSource, BiConsumer<? super T, ? super Throwable> biConsumer) {
        super(maybeSource);
        this.onEvent = biConsumer;
    }

    @Override // io.reactivex.rxjava3.core.Maybe
    public void subscribeActual(MaybeObserver<? super T> maybeObserver) {
        this.source.subscribe(new DoOnEventMaybeObserver(maybeObserver, this.onEvent));
    }

    /* loaded from: classes10.dex */
    static final class DoOnEventMaybeObserver<T> implements MaybeObserver<T>, Disposable {
        final MaybeObserver<? super T> downstream;
        final BiConsumer<? super T, ? super Throwable> onEvent;
        Disposable upstream;

        DoOnEventMaybeObserver(MaybeObserver<? super T> maybeObserver, BiConsumer<? super T, ? super Throwable> biConsumer) {
            this.downstream = maybeObserver;
            this.onEvent = biConsumer;
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
            try {
                this.onEvent.accept(t, null);
                this.downstream.onSuccess(t);
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                this.downstream.onError(th);
            }
        }

        @Override // io.reactivex.rxjava3.core.MaybeObserver, io.reactivex.rxjava3.core.SingleObserver, io.reactivex.rxjava3.core.CompletableObserver
        public void onError(Throwable th) {
            this.upstream = DisposableHelper.DISPOSED;
            try {
                this.onEvent.accept(null, th);
            } catch (Throwable th2) {
                Exceptions.throwIfFatal(th2);
                th = new CompositeException(th, th2);
            }
            this.downstream.onError(th);
        }

        @Override // io.reactivex.rxjava3.core.MaybeObserver, io.reactivex.rxjava3.core.CompletableObserver
        public void onComplete() {
            this.upstream = DisposableHelper.DISPOSED;
            try {
                this.onEvent.accept(null, null);
                this.downstream.onComplete();
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                this.downstream.onError(th);
            }
        }
    }
}
