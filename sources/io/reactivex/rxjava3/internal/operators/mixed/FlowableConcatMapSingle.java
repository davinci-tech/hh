package io.reactivex.rxjava3.internal.operators.mixed;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.util.AtomicThrowable;
import io.reactivex.rxjava3.internal.util.BackpressureHelper;
import io.reactivex.rxjava3.internal.util.ErrorMode;
import io.reactivex.rxjava3.operators.SimpleQueue;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* loaded from: classes.dex */
public final class FlowableConcatMapSingle<T, R> extends Flowable<R> {
    final ErrorMode errorMode;
    final Function<? super T, ? extends SingleSource<? extends R>> mapper;
    final int prefetch;
    final Flowable<T> source;

    public FlowableConcatMapSingle(Flowable<T> flowable, Function<? super T, ? extends SingleSource<? extends R>> function, ErrorMode errorMode, int i) {
        this.source = flowable;
        this.mapper = function;
        this.errorMode = errorMode;
        this.prefetch = i;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public void subscribeActual(Subscriber<? super R> subscriber) {
        this.source.subscribe((FlowableSubscriber) new ConcatMapSingleSubscriber(subscriber, this.mapper, this.prefetch, this.errorMode));
    }

    /* loaded from: classes10.dex */
    static final class ConcatMapSingleSubscriber<T, R> extends ConcatMapXMainSubscriber<T> implements Subscription {
        static final int STATE_ACTIVE = 1;
        static final int STATE_INACTIVE = 0;
        static final int STATE_RESULT_VALUE = 2;
        private static final long serialVersionUID = -9140123220065488293L;
        int consumed;
        final Subscriber<? super R> downstream;
        long emitted;
        final ConcatMapSingleObserver<R> inner;
        R item;
        final Function<? super T, ? extends SingleSource<? extends R>> mapper;
        final AtomicLong requested;
        volatile int state;

        ConcatMapSingleSubscriber(Subscriber<? super R> subscriber, Function<? super T, ? extends SingleSource<? extends R>> function, int i, ErrorMode errorMode) {
            super(i, errorMode);
            this.downstream = subscriber;
            this.mapper = function;
            this.requested = new AtomicLong();
            this.inner = new ConcatMapSingleObserver<>(this);
        }

        @Override // io.reactivex.rxjava3.internal.operators.mixed.ConcatMapXMainSubscriber
        void onSubscribeDownstream() {
            this.downstream.onSubscribe(this);
        }

        @Override // org.reactivestreams.Subscription
        public void request(long j) {
            BackpressureHelper.add(this.requested, j);
            drain();
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            stop();
        }

        @Override // io.reactivex.rxjava3.internal.operators.mixed.ConcatMapXMainSubscriber
        void clearValue() {
            this.item = null;
        }

        @Override // io.reactivex.rxjava3.internal.operators.mixed.ConcatMapXMainSubscriber
        void disposeInner() {
            this.inner.dispose();
        }

        void innerSuccess(R r) {
            this.item = r;
            this.state = 2;
            drain();
        }

        void innerError(Throwable th) {
            if (this.errors.tryAddThrowableOrReport(th)) {
                if (this.errorMode != ErrorMode.END) {
                    this.upstream.cancel();
                }
                this.state = 0;
                drain();
            }
        }

        @Override // io.reactivex.rxjava3.internal.operators.mixed.ConcatMapXMainSubscriber
        void drain() {
            if (getAndIncrement() != 0) {
                return;
            }
            Subscriber<? super R> subscriber = this.downstream;
            ErrorMode errorMode = this.errorMode;
            SimpleQueue<T> simpleQueue = this.queue;
            AtomicThrowable atomicThrowable = this.errors;
            AtomicLong atomicLong = this.requested;
            int i = this.prefetch - (this.prefetch >> 1);
            boolean z = this.syncFused;
            int i2 = 1;
            while (true) {
                if (this.cancelled) {
                    simpleQueue.clear();
                    this.item = null;
                } else {
                    int i3 = this.state;
                    if (atomicThrowable.get() == null || (errorMode != ErrorMode.IMMEDIATE && (errorMode != ErrorMode.BOUNDARY || i3 != 0))) {
                        if (i3 == 0) {
                            boolean z2 = this.done;
                            try {
                                T poll = simpleQueue.poll();
                                boolean z3 = poll == null;
                                if (z2 && z3) {
                                    atomicThrowable.tryTerminateConsumer(subscriber);
                                    return;
                                }
                                if (!z3) {
                                    if (!z) {
                                        int i4 = this.consumed + 1;
                                        if (i4 == i) {
                                            this.consumed = 0;
                                            this.upstream.request(i);
                                        } else {
                                            this.consumed = i4;
                                        }
                                    }
                                    try {
                                        SingleSource singleSource = (SingleSource) Objects.requireNonNull(this.mapper.apply(poll), "The mapper returned a null SingleSource");
                                        this.state = 1;
                                        singleSource.subscribe(this.inner);
                                    } catch (Throwable th) {
                                        Exceptions.throwIfFatal(th);
                                        this.upstream.cancel();
                                        simpleQueue.clear();
                                        atomicThrowable.tryAddThrowableOrReport(th);
                                        atomicThrowable.tryTerminateConsumer(subscriber);
                                        return;
                                    }
                                }
                            } catch (Throwable th2) {
                                Exceptions.throwIfFatal(th2);
                                this.upstream.cancel();
                                atomicThrowable.tryAddThrowableOrReport(th2);
                                atomicThrowable.tryTerminateConsumer(subscriber);
                                return;
                            }
                        } else if (i3 == 2) {
                            long j = this.emitted;
                            if (j != atomicLong.get()) {
                                R r = this.item;
                                this.item = null;
                                subscriber.onNext(r);
                                this.emitted = j + 1;
                                this.state = 0;
                            }
                        }
                    }
                }
                i2 = addAndGet(-i2);
                if (i2 == 0) {
                    return;
                }
            }
            simpleQueue.clear();
            this.item = null;
            atomicThrowable.tryTerminateConsumer(subscriber);
        }

        static final class ConcatMapSingleObserver<R> extends AtomicReference<Disposable> implements SingleObserver<R> {
            private static final long serialVersionUID = -3051469169682093892L;
            final ConcatMapSingleSubscriber<?, R> parent;

            ConcatMapSingleObserver(ConcatMapSingleSubscriber<?, R> concatMapSingleSubscriber) {
                this.parent = concatMapSingleSubscriber;
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
