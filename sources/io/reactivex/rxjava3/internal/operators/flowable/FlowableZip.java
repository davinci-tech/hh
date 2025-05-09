package io.reactivex.rxjava3.internal.operators.flowable;

import androidx.core.location.LocationRequestCompat;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.internal.subscriptions.EmptySubscription;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.internal.util.AtomicThrowable;
import io.reactivex.rxjava3.internal.util.BackpressureHelper;
import io.reactivex.rxjava3.operators.QueueSubscription;
import io.reactivex.rxjava3.operators.SimpleQueue;
import io.reactivex.rxjava3.operators.SpscArrayQueue;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* loaded from: classes.dex */
public final class FlowableZip<T, R> extends Flowable<R> {
    final int bufferSize;
    final boolean delayError;
    final Publisher<? extends T>[] sources;
    final Iterable<? extends Publisher<? extends T>> sourcesIterable;
    final Function<? super Object[], ? extends R> zipper;

    public FlowableZip(Publisher<? extends T>[] publisherArr, Iterable<? extends Publisher<? extends T>> iterable, Function<? super Object[], ? extends R> function, int i, boolean z) {
        this.sources = publisherArr;
        this.sourcesIterable = iterable;
        this.zipper = function;
        this.bufferSize = i;
        this.delayError = z;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public void subscribeActual(Subscriber<? super R> subscriber) {
        int length;
        Publisher<? extends T>[] publisherArr = this.sources;
        if (publisherArr == null) {
            publisherArr = new Publisher[8];
            length = 0;
            for (Publisher<? extends T> publisher : this.sourcesIterable) {
                if (length == publisherArr.length) {
                    Publisher<? extends T>[] publisherArr2 = new Publisher[(length >> 2) + length];
                    System.arraycopy(publisherArr, 0, publisherArr2, 0, length);
                    publisherArr = publisherArr2;
                }
                publisherArr[length] = publisher;
                length++;
            }
        } else {
            length = publisherArr.length;
        }
        int i = length;
        if (i == 0) {
            EmptySubscription.complete(subscriber);
            return;
        }
        ZipCoordinator zipCoordinator = new ZipCoordinator(subscriber, this.zipper, i, this.bufferSize, this.delayError);
        subscriber.onSubscribe(zipCoordinator);
        zipCoordinator.subscribe(publisherArr, i);
    }

    /* loaded from: classes10.dex */
    static final class ZipCoordinator<T, R> extends AtomicInteger implements Subscription {
        private static final long serialVersionUID = -2434867452883857743L;
        volatile boolean cancelled;
        final Object[] current;
        final boolean delayErrors;
        final Subscriber<? super R> downstream;
        final AtomicThrowable errors;
        final AtomicLong requested;
        final ZipSubscriber<T, R>[] subscribers;
        final Function<? super Object[], ? extends R> zipper;

        ZipCoordinator(Subscriber<? super R> subscriber, Function<? super Object[], ? extends R> function, int i, int i2, boolean z) {
            this.downstream = subscriber;
            this.zipper = function;
            this.delayErrors = z;
            ZipSubscriber<T, R>[] zipSubscriberArr = new ZipSubscriber[i];
            for (int i3 = 0; i3 < i; i3++) {
                zipSubscriberArr[i3] = new ZipSubscriber<>(this, i2);
            }
            this.current = new Object[i];
            this.subscribers = zipSubscriberArr;
            this.requested = new AtomicLong();
            this.errors = new AtomicThrowable();
        }

        void subscribe(Publisher<? extends T>[] publisherArr, int i) {
            ZipSubscriber<T, R>[] zipSubscriberArr = this.subscribers;
            for (int i2 = 0; i2 < i && !this.cancelled; i2++) {
                if (!this.delayErrors && this.errors.get() != null) {
                    return;
                }
                publisherArr[i2].subscribe(zipSubscriberArr[i2]);
            }
        }

        @Override // org.reactivestreams.Subscription
        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                BackpressureHelper.add(this.requested, j);
                drain();
            }
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            if (this.cancelled) {
                return;
            }
            this.cancelled = true;
            cancelAll();
        }

        void error(ZipSubscriber<T, R> zipSubscriber, Throwable th) {
            if (this.errors.tryAddThrowableOrReport(th)) {
                zipSubscriber.done = true;
                drain();
            }
        }

        void cancelAll() {
            for (ZipSubscriber<T, R> zipSubscriber : this.subscribers) {
                zipSubscriber.cancel();
            }
        }

        void drain() {
            T t;
            T t2;
            if (getAndIncrement() != 0) {
                return;
            }
            Subscriber<? super R> subscriber = this.downstream;
            ZipSubscriber<T, R>[] zipSubscriberArr = this.subscribers;
            int length = zipSubscriberArr.length;
            Object[] objArr = this.current;
            int i = 1;
            do {
                long j = this.requested.get();
                long j2 = 0;
                while (j != j2) {
                    if (this.cancelled) {
                        return;
                    }
                    if (!this.delayErrors && this.errors.get() != null) {
                        cancelAll();
                        this.errors.tryTerminateConsumer(subscriber);
                        return;
                    }
                    boolean z = false;
                    for (int i2 = 0; i2 < length; i2++) {
                        ZipSubscriber<T, R> zipSubscriber = zipSubscriberArr[i2];
                        if (objArr[i2] == null) {
                            boolean z2 = zipSubscriber.done;
                            SimpleQueue<T> simpleQueue = zipSubscriber.queue;
                            if (simpleQueue != null) {
                                try {
                                    t2 = simpleQueue.poll();
                                } catch (Throwable th) {
                                    Exceptions.throwIfFatal(th);
                                    this.errors.tryAddThrowableOrReport(th);
                                    if (!this.delayErrors) {
                                        cancelAll();
                                        this.errors.tryTerminateConsumer(subscriber);
                                        return;
                                    } else {
                                        t2 = null;
                                        z2 = true;
                                    }
                                }
                            } else {
                                t2 = null;
                            }
                            boolean z3 = t2 == null;
                            if (z2 && z3) {
                                cancelAll();
                                this.errors.tryTerminateConsumer(subscriber);
                                return;
                            } else if (z3) {
                                z = true;
                            } else {
                                objArr[i2] = t2;
                            }
                        }
                    }
                    if (z) {
                        break;
                    }
                    try {
                        subscriber.onNext((Object) Objects.requireNonNull(this.zipper.apply(objArr.clone()), "The zipper returned a null value"));
                        j2++;
                        Arrays.fill(objArr, (Object) null);
                    } catch (Throwable th2) {
                        Exceptions.throwIfFatal(th2);
                        cancelAll();
                        this.errors.tryAddThrowableOrReport(th2);
                        this.errors.tryTerminateConsumer(subscriber);
                        return;
                    }
                }
                if (j == j2) {
                    if (this.cancelled) {
                        return;
                    }
                    if (!this.delayErrors && this.errors.get() != null) {
                        cancelAll();
                        this.errors.tryTerminateConsumer(subscriber);
                        return;
                    }
                    for (int i3 = 0; i3 < length; i3++) {
                        ZipSubscriber<T, R> zipSubscriber2 = zipSubscriberArr[i3];
                        if (objArr[i3] == null) {
                            boolean z4 = zipSubscriber2.done;
                            SimpleQueue<T> simpleQueue2 = zipSubscriber2.queue;
                            if (simpleQueue2 != null) {
                                try {
                                    t = simpleQueue2.poll();
                                } catch (Throwable th3) {
                                    Exceptions.throwIfFatal(th3);
                                    this.errors.tryAddThrowableOrReport(th3);
                                    if (!this.delayErrors) {
                                        cancelAll();
                                        this.errors.tryTerminateConsumer(subscriber);
                                        return;
                                    } else {
                                        t = null;
                                        z4 = true;
                                    }
                                }
                            } else {
                                t = null;
                            }
                            boolean z5 = t == null;
                            if (z4 && z5) {
                                cancelAll();
                                this.errors.tryTerminateConsumer(subscriber);
                                return;
                            } else if (!z5) {
                                objArr[i3] = t;
                            }
                        }
                    }
                }
                if (j2 != 0) {
                    for (ZipSubscriber<T, R> zipSubscriber3 : zipSubscriberArr) {
                        zipSubscriber3.request(j2);
                    }
                    if (j != LocationRequestCompat.PASSIVE_INTERVAL) {
                        this.requested.addAndGet(-j2);
                    }
                }
                i = addAndGet(-i);
            } while (i != 0);
        }
    }

    /* loaded from: classes10.dex */
    static final class ZipSubscriber<T, R> extends AtomicReference<Subscription> implements FlowableSubscriber<T>, Subscription {
        private static final long serialVersionUID = -4627193790118206028L;
        volatile boolean done;
        final int limit;
        final ZipCoordinator<T, R> parent;
        final int prefetch;
        long produced;
        SimpleQueue<T> queue;
        int sourceMode;

        ZipSubscriber(ZipCoordinator<T, R> zipCoordinator, int i) {
            this.parent = zipCoordinator;
            this.prefetch = i;
            this.limit = i - (i >> 2);
        }

        @Override // io.reactivex.rxjava3.core.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.setOnce(this, subscription)) {
                if (subscription instanceof QueueSubscription) {
                    QueueSubscription queueSubscription = (QueueSubscription) subscription;
                    int requestFusion = queueSubscription.requestFusion(7);
                    if (requestFusion == 1) {
                        this.sourceMode = requestFusion;
                        this.queue = queueSubscription;
                        this.done = true;
                        this.parent.drain();
                        return;
                    }
                    if (requestFusion == 2) {
                        this.sourceMode = requestFusion;
                        this.queue = queueSubscription;
                        subscription.request(this.prefetch);
                        return;
                    }
                }
                this.queue = new SpscArrayQueue(this.prefetch);
                subscription.request(this.prefetch);
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            if (this.sourceMode != 2) {
                this.queue.offer(t);
            }
            this.parent.drain();
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            this.parent.error(this, th);
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            this.done = true;
            this.parent.drain();
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            SubscriptionHelper.cancel(this);
        }

        @Override // org.reactivestreams.Subscription
        public void request(long j) {
            if (this.sourceMode != 1) {
                long j2 = this.produced + j;
                if (j2 >= this.limit) {
                    this.produced = 0L;
                    get().request(j2);
                } else {
                    this.produced = j2;
                }
            }
        }
    }
}
