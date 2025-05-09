package io.reactivex.rxjava3.internal.operators.flowable;

import androidx.core.location.LocationRequestCompat;
import androidx.webkit.ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0;
import defpackage.aai;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.core.MaybeSource;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.internal.util.AtomicThrowable;
import io.reactivex.rxjava3.internal.util.BackpressureHelper;
import io.reactivex.rxjava3.operators.SpscLinkedArrayQueue;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* loaded from: classes.dex */
public final class FlowableFlatMapMaybe<T, R> extends AbstractFlowableWithUpstream<T, R> {
    final boolean delayErrors;
    final Function<? super T, ? extends MaybeSource<? extends R>> mapper;
    final int maxConcurrency;

    public FlowableFlatMapMaybe(Flowable<T> flowable, Function<? super T, ? extends MaybeSource<? extends R>> function, boolean z, int i) {
        super(flowable);
        this.mapper = function;
        this.delayErrors = z;
        this.maxConcurrency = i;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public void subscribeActual(Subscriber<? super R> subscriber) {
        this.source.subscribe((FlowableSubscriber) new FlatMapMaybeSubscriber(subscriber, this.mapper, this.delayErrors, this.maxConcurrency));
    }

    /* loaded from: classes10.dex */
    static final class FlatMapMaybeSubscriber<T, R> extends AtomicInteger implements FlowableSubscriber<T>, Subscription {
        private static final long serialVersionUID = 8600231336733376951L;
        volatile boolean cancelled;
        final boolean delayErrors;
        final Subscriber<? super R> downstream;
        final Function<? super T, ? extends MaybeSource<? extends R>> mapper;
        final int maxConcurrency;
        Subscription upstream;
        final AtomicLong requested = new AtomicLong();
        final CompositeDisposable set = new CompositeDisposable();
        final AtomicThrowable errors = new AtomicThrowable();
        final AtomicInteger active = new AtomicInteger(1);
        final AtomicReference<SpscLinkedArrayQueue<R>> queue = new AtomicReference<>();

        FlatMapMaybeSubscriber(Subscriber<? super R> subscriber, Function<? super T, ? extends MaybeSource<? extends R>> function, boolean z, int i) {
            this.downstream = subscriber;
            this.mapper = function;
            this.delayErrors = z;
            this.maxConcurrency = i;
        }

        @Override // io.reactivex.rxjava3.core.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.upstream, subscription)) {
                this.upstream = subscription;
                this.downstream.onSubscribe(this);
                int i = this.maxConcurrency;
                if (i == Integer.MAX_VALUE) {
                    subscription.request(LocationRequestCompat.PASSIVE_INTERVAL);
                } else {
                    subscription.request(i);
                }
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            try {
                MaybeSource maybeSource = (MaybeSource) Objects.requireNonNull(this.mapper.apply(t), "The mapper returned a null MaybeSource");
                this.active.getAndIncrement();
                InnerObserver innerObserver = new InnerObserver();
                if (this.cancelled || !this.set.add(innerObserver)) {
                    return;
                }
                maybeSource.subscribe(innerObserver);
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                this.upstream.cancel();
                onError(th);
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            this.active.decrementAndGet();
            if (this.errors.tryAddThrowableOrReport(th)) {
                if (!this.delayErrors) {
                    this.set.dispose();
                }
                drain();
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            this.active.decrementAndGet();
            drain();
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            this.cancelled = true;
            this.upstream.cancel();
            this.set.dispose();
            this.errors.tryTerminateAndReport();
        }

        @Override // org.reactivestreams.Subscription
        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                BackpressureHelper.add(this.requested, j);
                drain();
            }
        }

        void innerSuccess(FlatMapMaybeSubscriber<T, R>.InnerObserver innerObserver, R r) {
            this.set.delete(innerObserver);
            if (get() == 0) {
                if (compareAndSet(0, 1)) {
                    boolean z = this.active.decrementAndGet() == 0;
                    if (this.requested.get() != 0) {
                        this.downstream.onNext(r);
                        if (checkTerminate(z, this.queue.get())) {
                            this.errors.tryTerminateConsumer(this.downstream);
                            return;
                        } else {
                            BackpressureHelper.produced(this.requested, 1L);
                            if (this.maxConcurrency != Integer.MAX_VALUE) {
                                this.upstream.request(1L);
                            }
                        }
                    } else {
                        SpscLinkedArrayQueue<R> orCreateQueue = getOrCreateQueue();
                        synchronized (orCreateQueue) {
                            orCreateQueue.offer(r);
                        }
                    }
                    if (decrementAndGet() == 0) {
                        return;
                    }
                    drainLoop();
                }
            }
            SpscLinkedArrayQueue<R> orCreateQueue2 = getOrCreateQueue();
            synchronized (orCreateQueue2) {
                orCreateQueue2.offer(r);
            }
            this.active.decrementAndGet();
            if (getAndIncrement() != 0) {
                return;
            }
            drainLoop();
        }

        SpscLinkedArrayQueue<R> getOrCreateQueue() {
            SpscLinkedArrayQueue<R> spscLinkedArrayQueue = this.queue.get();
            if (spscLinkedArrayQueue != null) {
                return spscLinkedArrayQueue;
            }
            SpscLinkedArrayQueue<R> spscLinkedArrayQueue2 = new SpscLinkedArrayQueue<>(Flowable.bufferSize());
            return ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0.m(this.queue, null, spscLinkedArrayQueue2) ? spscLinkedArrayQueue2 : this.queue.get();
        }

        void innerError(FlatMapMaybeSubscriber<T, R>.InnerObserver innerObserver, Throwable th) {
            this.set.delete(innerObserver);
            if (this.errors.tryAddThrowableOrReport(th)) {
                if (!this.delayErrors) {
                    this.upstream.cancel();
                    this.set.dispose();
                } else if (this.maxConcurrency != Integer.MAX_VALUE) {
                    this.upstream.request(1L);
                }
                this.active.decrementAndGet();
                drain();
            }
        }

        void innerComplete(FlatMapMaybeSubscriber<T, R>.InnerObserver innerObserver) {
            this.set.delete(innerObserver);
            if (get() == 0) {
                if (compareAndSet(0, 1)) {
                    if (checkTerminate(this.active.decrementAndGet() == 0, this.queue.get())) {
                        this.errors.tryTerminateConsumer(this.downstream);
                        return;
                    }
                    if (this.maxConcurrency != Integer.MAX_VALUE) {
                        this.upstream.request(1L);
                    }
                    if (decrementAndGet() == 0) {
                        return;
                    }
                    drainLoop();
                    return;
                }
            }
            this.active.decrementAndGet();
            if (this.maxConcurrency != Integer.MAX_VALUE) {
                this.upstream.request(1L);
            }
            drain();
        }

        static boolean checkTerminate(boolean z, SpscLinkedArrayQueue<?> spscLinkedArrayQueue) {
            return z && (spscLinkedArrayQueue == null || spscLinkedArrayQueue.isEmpty());
        }

        void drain() {
            if (getAndIncrement() == 0) {
                drainLoop();
            }
        }

        void clear() {
            SpscLinkedArrayQueue<R> spscLinkedArrayQueue = this.queue.get();
            if (spscLinkedArrayQueue != null) {
                spscLinkedArrayQueue.clear();
            }
        }

        void drainLoop() {
            Subscriber<? super R> subscriber = this.downstream;
            AtomicInteger atomicInteger = this.active;
            AtomicReference<SpscLinkedArrayQueue<R>> atomicReference = this.queue;
            int i = 1;
            do {
                long j = this.requested.get();
                long j2 = 0;
                while (true) {
                    if (j2 == j) {
                        break;
                    }
                    if (this.cancelled) {
                        clear();
                        return;
                    }
                    if (!this.delayErrors && this.errors.get() != null) {
                        clear();
                        this.errors.tryTerminateConsumer(subscriber);
                        return;
                    }
                    boolean z = atomicInteger.get() == 0;
                    SpscLinkedArrayQueue<R> spscLinkedArrayQueue = atomicReference.get();
                    aai poll = spscLinkedArrayQueue != null ? spscLinkedArrayQueue.poll() : null;
                    boolean z2 = poll == null;
                    if (z && z2) {
                        this.errors.tryTerminateConsumer(subscriber);
                        return;
                    } else {
                        if (z2) {
                            break;
                        }
                        subscriber.onNext(poll);
                        j2++;
                    }
                }
                if (j2 == j) {
                    if (this.cancelled) {
                        clear();
                        return;
                    }
                    if (!this.delayErrors && this.errors.get() != null) {
                        clear();
                        this.errors.tryTerminateConsumer(subscriber);
                        return;
                    }
                    boolean z3 = atomicInteger.get() == 0;
                    SpscLinkedArrayQueue<R> spscLinkedArrayQueue2 = atomicReference.get();
                    boolean z4 = spscLinkedArrayQueue2 == null || spscLinkedArrayQueue2.isEmpty();
                    if (z3 && z4) {
                        this.errors.tryTerminateConsumer(subscriber);
                        return;
                    }
                }
                if (j2 != 0) {
                    BackpressureHelper.produced(this.requested, j2);
                    if (this.maxConcurrency != Integer.MAX_VALUE) {
                        this.upstream.request(j2);
                    }
                }
                i = addAndGet(-i);
            } while (i != 0);
        }

        final class InnerObserver extends AtomicReference<Disposable> implements MaybeObserver<R>, Disposable {
            private static final long serialVersionUID = -502562646270949838L;

            InnerObserver() {
            }

            @Override // io.reactivex.rxjava3.core.MaybeObserver, io.reactivex.rxjava3.core.SingleObserver, io.reactivex.rxjava3.core.CompletableObserver
            public void onSubscribe(Disposable disposable) {
                DisposableHelper.setOnce(this, disposable);
            }

            @Override // io.reactivex.rxjava3.core.MaybeObserver, io.reactivex.rxjava3.core.SingleObserver
            public void onSuccess(R r) {
                FlatMapMaybeSubscriber.this.innerSuccess(this, r);
            }

            @Override // io.reactivex.rxjava3.core.MaybeObserver, io.reactivex.rxjava3.core.SingleObserver, io.reactivex.rxjava3.core.CompletableObserver
            public void onError(Throwable th) {
                FlatMapMaybeSubscriber.this.innerError(this, th);
            }

            @Override // io.reactivex.rxjava3.core.MaybeObserver, io.reactivex.rxjava3.core.CompletableObserver
            public void onComplete() {
                FlatMapMaybeSubscriber.this.innerComplete(this);
            }

            @Override // io.reactivex.rxjava3.disposables.Disposable
            public boolean isDisposed() {
                return DisposableHelper.isDisposed(get());
            }

            @Override // io.reactivex.rxjava3.disposables.Disposable
            public void dispose() {
                DisposableHelper.dispose(this);
            }
        }
    }
}
