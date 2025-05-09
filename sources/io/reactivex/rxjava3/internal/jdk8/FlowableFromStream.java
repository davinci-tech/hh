package io.reactivex.rxjava3.internal.jdk8;

import androidx.core.location.LocationRequestCompat;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.internal.subscriptions.EmptySubscription;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.internal.util.BackpressureHelper;
import io.reactivex.rxjava3.operators.ConditionalSubscriber;
import io.reactivex.rxjava3.operators.QueueSubscription;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;
import org.reactivestreams.Subscriber;

/* loaded from: classes.dex */
public final class FlowableFromStream<T> extends Flowable<T> {
    final Stream<T> stream;

    public FlowableFromStream(Stream<T> stream) {
        this.stream = stream;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public void subscribeActual(Subscriber<? super T> subscriber) {
        subscribeStream(subscriber, this.stream);
    }

    public static <T> void subscribeStream(Subscriber<? super T> subscriber, Stream<T> stream) {
        try {
            Iterator<T> it = stream.iterator();
            if (!it.hasNext()) {
                EmptySubscription.complete(subscriber);
                closeSafely(stream);
            } else if (subscriber instanceof ConditionalSubscriber) {
                subscriber.onSubscribe(new StreamConditionalSubscription((ConditionalSubscriber) subscriber, it, stream));
            } else {
                subscriber.onSubscribe(new StreamSubscription(subscriber, it, stream));
            }
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            EmptySubscription.error(th, subscriber);
            closeSafely(stream);
        }
    }

    static void closeSafely(AutoCloseable autoCloseable) {
        try {
            autoCloseable.close();
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            RxJavaPlugins.onError(th);
        }
    }

    /* loaded from: classes10.dex */
    static abstract class AbstractStreamSubscription<T> extends AtomicLong implements QueueSubscription<T> {
        private static final long serialVersionUID = -9082954702547571853L;
        volatile boolean cancelled;
        AutoCloseable closeable;
        Iterator<T> iterator;
        boolean once;

        abstract void run(long j);

        AbstractStreamSubscription(Iterator<T> it, AutoCloseable autoCloseable) {
            this.iterator = it;
            this.closeable = autoCloseable;
        }

        @Override // org.reactivestreams.Subscription
        public void request(long j) {
            if (SubscriptionHelper.validate(j) && BackpressureHelper.add(this, j) == 0) {
                run(j);
            }
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            this.cancelled = true;
            request(1L);
        }

        @Override // io.reactivex.rxjava3.operators.QueueFuseable
        public int requestFusion(int i) {
            if ((i & 1) == 0) {
                return 0;
            }
            lazySet(LocationRequestCompat.PASSIVE_INTERVAL);
            return 1;
        }

        @Override // io.reactivex.rxjava3.operators.SimpleQueue
        public boolean offer(T t) {
            throw new UnsupportedOperationException();
        }

        @Override // io.reactivex.rxjava3.operators.SimpleQueue
        public boolean offer(T t, T t2) {
            throw new UnsupportedOperationException();
        }

        @Override // io.reactivex.rxjava3.operators.SimpleQueue
        public T poll() {
            Iterator<T> it = this.iterator;
            if (it == null) {
                return null;
            }
            if (!this.once) {
                this.once = true;
            } else if (!it.hasNext()) {
                clear();
                return null;
            }
            return (T) Objects.requireNonNull(this.iterator.next(), "The Stream's Iterator.next() returned a null value");
        }

        @Override // io.reactivex.rxjava3.operators.SimpleQueue
        public boolean isEmpty() {
            Iterator<T> it = this.iterator;
            if (it == null) {
                return true;
            }
            if (!this.once || it.hasNext()) {
                return false;
            }
            clear();
            return true;
        }

        @Override // io.reactivex.rxjava3.operators.SimpleQueue
        public void clear() {
            this.iterator = null;
            AutoCloseable autoCloseable = this.closeable;
            this.closeable = null;
            if (autoCloseable != null) {
                FlowableFromStream.closeSafely(autoCloseable);
            }
        }
    }

    /* loaded from: classes10.dex */
    static final class StreamSubscription<T> extends AbstractStreamSubscription<T> {
        private static final long serialVersionUID = -9082954702547571853L;
        final Subscriber<? super T> downstream;

        StreamSubscription(Subscriber<? super T> subscriber, Iterator<T> it, AutoCloseable autoCloseable) {
            super(it, autoCloseable);
            this.downstream = subscriber;
        }

        @Override // io.reactivex.rxjava3.internal.jdk8.FlowableFromStream.AbstractStreamSubscription
        public void run(long j) {
            Iterator<T> it = this.iterator;
            Subscriber<? super T> subscriber = this.downstream;
            long j2 = 0;
            while (!this.cancelled) {
                try {
                    subscriber.onNext((Object) Objects.requireNonNull(it.next(), "The Stream's Iterator returned a null value"));
                    if (this.cancelled) {
                        continue;
                    } else {
                        try {
                            if (it.hasNext()) {
                                j2++;
                                if (j2 != j) {
                                    continue;
                                } else {
                                    j = get();
                                    if (j2 != j) {
                                        continue;
                                    } else if (compareAndSet(j, 0L)) {
                                        return;
                                    } else {
                                        j = get();
                                    }
                                }
                            } else {
                                subscriber.onComplete();
                                this.cancelled = true;
                            }
                        } catch (Throwable th) {
                            Exceptions.throwIfFatal(th);
                            subscriber.onError(th);
                            this.cancelled = true;
                        }
                    }
                } catch (Throwable th2) {
                    Exceptions.throwIfFatal(th2);
                    subscriber.onError(th2);
                    this.cancelled = true;
                }
            }
            clear();
        }
    }

    /* loaded from: classes10.dex */
    static final class StreamConditionalSubscription<T> extends AbstractStreamSubscription<T> {
        private static final long serialVersionUID = -9082954702547571853L;
        final ConditionalSubscriber<? super T> downstream;

        StreamConditionalSubscription(ConditionalSubscriber<? super T> conditionalSubscriber, Iterator<T> it, AutoCloseable autoCloseable) {
            super(it, autoCloseable);
            this.downstream = conditionalSubscriber;
        }

        @Override // io.reactivex.rxjava3.internal.jdk8.FlowableFromStream.AbstractStreamSubscription
        public void run(long j) {
            Iterator<T> it = this.iterator;
            ConditionalSubscriber<? super T> conditionalSubscriber = this.downstream;
            long j2 = 0;
            while (!this.cancelled) {
                try {
                    if (conditionalSubscriber.tryOnNext((Object) Objects.requireNonNull(it.next(), "The Stream's Iterator returned a null value"))) {
                        j2++;
                    }
                    if (this.cancelled) {
                        continue;
                    } else {
                        try {
                            if (!it.hasNext()) {
                                conditionalSubscriber.onComplete();
                                this.cancelled = true;
                            } else if (j2 != j) {
                                continue;
                            } else {
                                j = get();
                                if (j2 != j) {
                                    continue;
                                } else if (compareAndSet(j, 0L)) {
                                    return;
                                } else {
                                    j = get();
                                }
                            }
                        } catch (Throwable th) {
                            Exceptions.throwIfFatal(th);
                            conditionalSubscriber.onError(th);
                            this.cancelled = true;
                        }
                    }
                } catch (Throwable th2) {
                    Exceptions.throwIfFatal(th2);
                    conditionalSubscriber.onError(th2);
                    this.cancelled = true;
                }
            }
            clear();
        }
    }
}
