package io.reactivex.rxjava3.internal.operators.mixed;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.util.AtomicThrowable;
import io.reactivex.rxjava3.internal.util.ErrorMode;
import io.reactivex.rxjava3.operators.SimpleQueue;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes.dex */
public final class ObservableConcatMapSingle<T, R> extends Observable<R> {
    final ErrorMode errorMode;
    final Function<? super T, ? extends SingleSource<? extends R>> mapper;
    final int prefetch;
    final ObservableSource<T> source;

    public ObservableConcatMapSingle(ObservableSource<T> observableSource, Function<? super T, ? extends SingleSource<? extends R>> function, ErrorMode errorMode, int i) {
        this.source = observableSource;
        this.mapper = function;
        this.errorMode = errorMode;
        this.prefetch = i;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public void subscribeActual(Observer<? super R> observer) {
        if (ScalarXMapZHelper.tryAsSingle(this.source, this.mapper, observer)) {
            return;
        }
        this.source.subscribe(new ConcatMapSingleMainObserver(observer, this.mapper, this.prefetch, this.errorMode));
    }

    /* loaded from: classes10.dex */
    static final class ConcatMapSingleMainObserver<T, R> extends ConcatMapXMainObserver<T> {
        static final int STATE_ACTIVE = 1;
        static final int STATE_INACTIVE = 0;
        static final int STATE_RESULT_VALUE = 2;
        private static final long serialVersionUID = -9140123220065488293L;
        final Observer<? super R> downstream;
        final ConcatMapSingleObserver<R> inner;
        R item;
        final Function<? super T, ? extends SingleSource<? extends R>> mapper;
        volatile int state;

        ConcatMapSingleMainObserver(Observer<? super R> observer, Function<? super T, ? extends SingleSource<? extends R>> function, int i, ErrorMode errorMode) {
            super(i, errorMode);
            this.downstream = observer;
            this.mapper = function;
            this.inner = new ConcatMapSingleObserver<>(this);
        }

        void innerSuccess(R r) {
            this.item = r;
            this.state = 2;
            drain();
        }

        void innerError(Throwable th) {
            if (this.errors.tryAddThrowableOrReport(th)) {
                if (this.errorMode != ErrorMode.END) {
                    this.upstream.dispose();
                }
                this.state = 0;
                drain();
            }
        }

        @Override // io.reactivex.rxjava3.internal.operators.mixed.ConcatMapXMainObserver
        void disposeInner() {
            this.inner.dispose();
        }

        @Override // io.reactivex.rxjava3.internal.operators.mixed.ConcatMapXMainObserver
        void onSubscribeDownstream() {
            this.downstream.onSubscribe(this);
        }

        @Override // io.reactivex.rxjava3.internal.operators.mixed.ConcatMapXMainObserver
        void clearValue() {
            this.item = null;
        }

        @Override // io.reactivex.rxjava3.internal.operators.mixed.ConcatMapXMainObserver
        void drain() {
            if (getAndIncrement() != 0) {
                return;
            }
            Observer<? super R> observer = this.downstream;
            ErrorMode errorMode = this.errorMode;
            SimpleQueue<T> simpleQueue = this.queue;
            AtomicThrowable atomicThrowable = this.errors;
            int i = 1;
            while (true) {
                if (this.disposed) {
                    simpleQueue.clear();
                    this.item = null;
                } else {
                    int i2 = this.state;
                    if (atomicThrowable.get() == null || (errorMode != ErrorMode.IMMEDIATE && (errorMode != ErrorMode.BOUNDARY || i2 != 0))) {
                        if (i2 == 0) {
                            boolean z = this.done;
                            try {
                                T poll = simpleQueue.poll();
                                boolean z2 = poll == null;
                                if (z && z2) {
                                    atomicThrowable.tryTerminateConsumer(observer);
                                    return;
                                }
                                if (!z2) {
                                    try {
                                        SingleSource singleSource = (SingleSource) Objects.requireNonNull(this.mapper.apply(poll), "The mapper returned a null SingleSource");
                                        this.state = 1;
                                        singleSource.subscribe(this.inner);
                                    } catch (Throwable th) {
                                        Exceptions.throwIfFatal(th);
                                        this.upstream.dispose();
                                        simpleQueue.clear();
                                        atomicThrowable.tryAddThrowableOrReport(th);
                                        atomicThrowable.tryTerminateConsumer(observer);
                                        return;
                                    }
                                }
                            } catch (Throwable th2) {
                                Exceptions.throwIfFatal(th2);
                                this.disposed = true;
                                this.upstream.dispose();
                                atomicThrowable.tryAddThrowableOrReport(th2);
                                atomicThrowable.tryTerminateConsumer(observer);
                                return;
                            }
                        } else if (i2 == 2) {
                            R r = this.item;
                            this.item = null;
                            observer.onNext(r);
                            this.state = 0;
                        }
                    }
                }
                i = addAndGet(-i);
                if (i == 0) {
                    return;
                }
            }
            simpleQueue.clear();
            this.item = null;
            atomicThrowable.tryTerminateConsumer(observer);
        }

        static final class ConcatMapSingleObserver<R> extends AtomicReference<Disposable> implements SingleObserver<R> {
            private static final long serialVersionUID = -3051469169682093892L;
            final ConcatMapSingleMainObserver<?, R> parent;

            ConcatMapSingleObserver(ConcatMapSingleMainObserver<?, R> concatMapSingleMainObserver) {
                this.parent = concatMapSingleMainObserver;
            }

            @Override // io.reactivex.rxjava3.core.SingleObserver, io.reactivex.rxjava3.core.CompletableObserver
            public void onSubscribe(Disposable disposable) {
                DisposableHelper.replace(this, disposable);
            }

            @Override // io.reactivex.rxjava3.core.SingleObserver
            public void onSuccess(R r) {
                this.parent.innerSuccess(r);
            }

            @Override // io.reactivex.rxjava3.core.SingleObserver, io.reactivex.rxjava3.core.CompletableObserver
            public void onError(Throwable th) {
                this.parent.innerError(th);
            }

            void dispose() {
                DisposableHelper.dispose(this);
            }
        }
    }
}
