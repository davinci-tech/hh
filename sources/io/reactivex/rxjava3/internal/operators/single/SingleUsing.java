package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.CompositeException;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes.dex */
public final class SingleUsing<T, U> extends Single<T> {
    final Consumer<? super U> disposer;
    final boolean eager;
    final Supplier<U> resourceSupplier;
    final Function<? super U, ? extends SingleSource<? extends T>> singleFunction;

    public SingleUsing(Supplier<U> supplier, Function<? super U, ? extends SingleSource<? extends T>> function, Consumer<? super U> consumer, boolean z) {
        this.resourceSupplier = supplier;
        this.singleFunction = function;
        this.disposer = consumer;
        this.eager = z;
    }

    @Override // io.reactivex.rxjava3.core.Single
    public void subscribeActual(SingleObserver<? super T> singleObserver) {
        try {
            U u = this.resourceSupplier.get();
            try {
                ((SingleSource) Objects.requireNonNull(this.singleFunction.apply(u), "The singleFunction returned a null SingleSource")).subscribe(new UsingSingleObserver(singleObserver, u, this.eager, this.disposer));
            } catch (Throwable th) {
                th = th;
                Exceptions.throwIfFatal(th);
                if (this.eager) {
                    try {
                        this.disposer.accept(u);
                    } catch (Throwable th2) {
                        Exceptions.throwIfFatal(th2);
                        th = new CompositeException(th, th2);
                    }
                }
                EmptyDisposable.error(th, singleObserver);
                if (this.eager) {
                    return;
                }
                try {
                    this.disposer.accept(u);
                } catch (Throwable th3) {
                    Exceptions.throwIfFatal(th3);
                    RxJavaPlugins.onError(th3);
                }
            }
        } catch (Throwable th4) {
            Exceptions.throwIfFatal(th4);
            EmptyDisposable.error(th4, singleObserver);
        }
    }

    /* loaded from: classes10.dex */
    static final class UsingSingleObserver<T, U> extends AtomicReference<Object> implements SingleObserver<T>, Disposable {
        private static final long serialVersionUID = -5331524057054083935L;
        final Consumer<? super U> disposer;
        final SingleObserver<? super T> downstream;
        final boolean eager;
        Disposable upstream;

        UsingSingleObserver(SingleObserver<? super T> singleObserver, U u, boolean z, Consumer<? super U> consumer) {
            super(u);
            this.downstream = singleObserver;
            this.eager = z;
            this.disposer = consumer;
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            if (this.eager) {
                disposeResource();
                this.upstream.dispose();
                this.upstream = DisposableHelper.DISPOSED;
            } else {
                this.upstream.dispose();
                this.upstream = DisposableHelper.DISPOSED;
                disposeResource();
            }
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return this.upstream.isDisposed();
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver, io.reactivex.rxjava3.core.CompletableObserver
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.upstream, disposable)) {
                this.upstream = disposable;
                this.downstream.onSubscribe(this);
            }
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver
        public void onSuccess(T t) {
            this.upstream = DisposableHelper.DISPOSED;
            if (this.eager) {
                Object andSet = getAndSet(this);
                if (andSet == this) {
                    return;
                }
                try {
                    this.disposer.accept(andSet);
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    this.downstream.onError(th);
                    return;
                }
            }
            this.downstream.onSuccess(t);
            if (this.eager) {
                return;
            }
            disposeResource();
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver, io.reactivex.rxjava3.core.CompletableObserver
        public void onError(Throwable th) {
            this.upstream = DisposableHelper.DISPOSED;
            if (this.eager) {
                Object andSet = getAndSet(this);
                if (andSet == this) {
                    return;
                }
                try {
                    this.disposer.accept(andSet);
                } catch (Throwable th2) {
                    Exceptions.throwIfFatal(th2);
                    th = new CompositeException(th, th2);
                }
            }
            this.downstream.onError(th);
            if (this.eager) {
                return;
            }
            disposeResource();
        }

        void disposeResource() {
            Object andSet = getAndSet(this);
            if (andSet != this) {
                try {
                    this.disposer.accept(andSet);
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    RxJavaPlugins.onError(th);
                }
            }
        }
    }
}
