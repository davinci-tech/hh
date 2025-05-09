package io.reactivex.rxjava3.internal.operators.flowable;

import androidx.core.location.LocationRequestCompat;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.exceptions.MissingBackpressureException;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.internal.queue.MpscLinkedQueue;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.internal.util.AtomicThrowable;
import io.reactivex.rxjava3.internal.util.BackpressureHelper;
import io.reactivex.rxjava3.internal.util.ExceptionHelper;
import io.reactivex.rxjava3.operators.SimplePlainQueue;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import io.reactivex.rxjava3.processors.UnicastProcessor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* loaded from: classes.dex */
public final class FlowableWindowBoundarySelector<T, B, V> extends AbstractFlowableWithUpstream<T, Flowable<T>> {
    final int bufferSize;
    final Function<? super B, ? extends Publisher<V>> closingIndicator;
    final Publisher<B> open;

    public FlowableWindowBoundarySelector(Flowable<T> flowable, Publisher<B> publisher, Function<? super B, ? extends Publisher<V>> function, int i) {
        super(flowable);
        this.open = publisher;
        this.closingIndicator = function;
        this.bufferSize = i;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public void subscribeActual(Subscriber<? super Flowable<T>> subscriber) {
        this.source.subscribe((FlowableSubscriber) new WindowBoundaryMainSubscriber(subscriber, this.open, this.closingIndicator, this.bufferSize));
    }

    /* loaded from: classes10.dex */
    static final class WindowBoundaryMainSubscriber<T, B, V> extends AtomicInteger implements FlowableSubscriber<T>, Subscription, Runnable {
        private static final long serialVersionUID = 8646217640096099753L;
        final int bufferSize;
        final Function<? super B, ? extends Publisher<V>> closingIndicator;
        final Subscriber<? super Flowable<T>> downstream;
        long emitted;
        final Publisher<B> open;
        volatile boolean openDone;
        Subscription upstream;
        volatile boolean upstreamCanceled;
        volatile boolean upstreamDone;
        final SimplePlainQueue<Object> queue = new MpscLinkedQueue();
        final CompositeDisposable resources = new CompositeDisposable();
        final List<UnicastProcessor<T>> windows = new ArrayList();
        final AtomicLong windowCount = new AtomicLong(1);
        final AtomicBoolean downstreamCancelled = new AtomicBoolean();
        final AtomicThrowable error = new AtomicThrowable();
        final WindowStartSubscriber<B> startSubscriber = new WindowStartSubscriber<>(this);
        final AtomicLong requested = new AtomicLong();

        WindowBoundaryMainSubscriber(Subscriber<? super Flowable<T>> subscriber, Publisher<B> publisher, Function<? super B, ? extends Publisher<V>> function, int i) {
            this.downstream = subscriber;
            this.open = publisher;
            this.closingIndicator = function;
            this.bufferSize = i;
        }

        @Override // io.reactivex.rxjava3.core.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.upstream, subscription)) {
                this.upstream = subscription;
                this.downstream.onSubscribe(this);
                this.open.subscribe(this.startSubscriber);
                subscription.request(LocationRequestCompat.PASSIVE_INTERVAL);
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            this.queue.offer(t);
            drain();
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            this.startSubscriber.cancel();
            this.resources.dispose();
            if (this.error.tryAddThrowableOrReport(th)) {
                this.upstreamDone = true;
                drain();
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            this.startSubscriber.cancel();
            this.resources.dispose();
            this.upstreamDone = true;
            drain();
        }

        @Override // org.reactivestreams.Subscription
        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                BackpressureHelper.add(this.requested, j);
            }
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            if (this.downstreamCancelled.compareAndSet(false, true)) {
                if (this.windowCount.decrementAndGet() == 0) {
                    this.upstream.cancel();
                    this.startSubscriber.cancel();
                    this.resources.dispose();
                    this.error.tryTerminateAndReport();
                    this.upstreamCanceled = true;
                    drain();
                    return;
                }
                this.startSubscriber.cancel();
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.windowCount.decrementAndGet() == 0) {
                this.upstream.cancel();
                this.startSubscriber.cancel();
                this.resources.dispose();
                this.error.tryTerminateAndReport();
                this.upstreamCanceled = true;
                drain();
            }
        }

        void open(B b) {
            this.queue.offer(new WindowStartItem(b));
            drain();
        }

        void openError(Throwable th) {
            this.upstream.cancel();
            this.resources.dispose();
            if (this.error.tryAddThrowableOrReport(th)) {
                this.upstreamDone = true;
                drain();
            }
        }

        void openComplete() {
            this.openDone = true;
            drain();
        }

        void close(WindowEndSubscriberIntercept<T, V> windowEndSubscriberIntercept) {
            this.queue.offer(windowEndSubscriberIntercept);
            drain();
        }

        void closeError(Throwable th) {
            this.upstream.cancel();
            this.startSubscriber.cancel();
            this.resources.dispose();
            if (this.error.tryAddThrowableOrReport(th)) {
                this.upstreamDone = true;
                drain();
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        void drain() {
            if (getAndIncrement() != 0) {
                return;
            }
            Subscriber<? super Flowable<T>> subscriber = this.downstream;
            SimplePlainQueue<Object> simplePlainQueue = this.queue;
            List<UnicastProcessor<T>> list = this.windows;
            int i = 1;
            while (true) {
                if (this.upstreamCanceled) {
                    simplePlainQueue.clear();
                    list.clear();
                } else {
                    boolean z = this.upstreamDone;
                    Object poll = simplePlainQueue.poll();
                    boolean z2 = poll == null;
                    if (z && (z2 || this.error.get() != null)) {
                        terminateDownstream(subscriber);
                        this.upstreamCanceled = true;
                    } else if (!z2) {
                        if (poll instanceof WindowStartItem) {
                            if (!this.downstreamCancelled.get()) {
                                long j = this.emitted;
                                if (this.requested.get() != j) {
                                    this.emitted = j + 1;
                                    try {
                                        Publisher publisher = (Publisher) Objects.requireNonNull(this.closingIndicator.apply(((WindowStartItem) poll).item), "The closingIndicator returned a null Publisher");
                                        this.windowCount.getAndIncrement();
                                        UnicastProcessor<T> create = UnicastProcessor.create(this.bufferSize, this);
                                        WindowEndSubscriberIntercept windowEndSubscriberIntercept = new WindowEndSubscriberIntercept(this, create);
                                        subscriber.onNext(windowEndSubscriberIntercept);
                                        if (windowEndSubscriberIntercept.tryAbandon()) {
                                            create.onComplete();
                                        } else {
                                            list.add(create);
                                            this.resources.add(windowEndSubscriberIntercept);
                                            publisher.subscribe(windowEndSubscriberIntercept);
                                        }
                                    } catch (Throwable th) {
                                        Exceptions.throwIfFatal(th);
                                        this.upstream.cancel();
                                        this.startSubscriber.cancel();
                                        this.resources.dispose();
                                        Exceptions.throwIfFatal(th);
                                        this.error.tryAddThrowableOrReport(th);
                                        this.upstreamDone = true;
                                    }
                                } else {
                                    this.upstream.cancel();
                                    this.startSubscriber.cancel();
                                    this.resources.dispose();
                                    this.error.tryAddThrowableOrReport(new MissingBackpressureException(FlowableWindowTimed.missingBackpressureMessage(j)));
                                    this.upstreamDone = true;
                                }
                            }
                        } else if (poll instanceof WindowEndSubscriberIntercept) {
                            UnicastProcessor<T> unicastProcessor = ((WindowEndSubscriberIntercept) poll).window;
                            list.remove(unicastProcessor);
                            this.resources.delete((Disposable) poll);
                            unicastProcessor.onComplete();
                        } else {
                            Iterator<UnicastProcessor<T>> it = list.iterator();
                            while (it.hasNext()) {
                                it.next().onNext(poll);
                            }
                        }
                    } else if (this.openDone && list.size() == 0) {
                        this.upstream.cancel();
                        this.startSubscriber.cancel();
                        this.resources.dispose();
                        terminateDownstream(subscriber);
                        this.upstreamCanceled = true;
                    }
                }
                i = addAndGet(-i);
                if (i == 0) {
                    return;
                }
            }
        }

        void terminateDownstream(Subscriber<?> subscriber) {
            Throwable terminate = this.error.terminate();
            if (terminate == null) {
                Iterator<UnicastProcessor<T>> it = this.windows.iterator();
                while (it.hasNext()) {
                    it.next().onComplete();
                }
                subscriber.onComplete();
                return;
            }
            if (terminate != ExceptionHelper.TERMINATED) {
                Iterator<UnicastProcessor<T>> it2 = this.windows.iterator();
                while (it2.hasNext()) {
                    it2.next().onError(terminate);
                }
                subscriber.onError(terminate);
            }
        }

        static final class WindowStartItem<B> {
            final B item;

            WindowStartItem(B b) {
                this.item = b;
            }
        }

        static final class WindowStartSubscriber<B> extends AtomicReference<Subscription> implements FlowableSubscriber<B> {
            private static final long serialVersionUID = -3326496781427702834L;
            final WindowBoundaryMainSubscriber<?, B, ?> parent;

            WindowStartSubscriber(WindowBoundaryMainSubscriber<?, B, ?> windowBoundaryMainSubscriber) {
                this.parent = windowBoundaryMainSubscriber;
            }

            @Override // io.reactivex.rxjava3.core.FlowableSubscriber, org.reactivestreams.Subscriber
            public void onSubscribe(Subscription subscription) {
                if (SubscriptionHelper.setOnce(this, subscription)) {
                    subscription.request(LocationRequestCompat.PASSIVE_INTERVAL);
                }
            }

            @Override // org.reactivestreams.Subscriber
            public void onNext(B b) {
                this.parent.open(b);
            }

            @Override // org.reactivestreams.Subscriber
            public void onError(Throwable th) {
                this.parent.openError(th);
            }

            @Override // org.reactivestreams.Subscriber
            public void onComplete() {
                this.parent.openComplete();
            }

            void cancel() {
                SubscriptionHelper.cancel(this);
            }
        }

        static final class WindowEndSubscriberIntercept<T, V> extends Flowable<T> implements FlowableSubscriber<V>, Disposable {
            final WindowBoundaryMainSubscriber<T, ?, V> parent;
            final UnicastProcessor<T> window;
            final AtomicReference<Subscription> upstream = new AtomicReference<>();
            final AtomicBoolean once = new AtomicBoolean();

            WindowEndSubscriberIntercept(WindowBoundaryMainSubscriber<T, ?, V> windowBoundaryMainSubscriber, UnicastProcessor<T> unicastProcessor) {
                this.parent = windowBoundaryMainSubscriber;
                this.window = unicastProcessor;
            }

            @Override // io.reactivex.rxjava3.core.FlowableSubscriber, org.reactivestreams.Subscriber
            public void onSubscribe(Subscription subscription) {
                if (SubscriptionHelper.setOnce(this.upstream, subscription)) {
                    subscription.request(LocationRequestCompat.PASSIVE_INTERVAL);
                }
            }

            @Override // org.reactivestreams.Subscriber
            public void onNext(V v) {
                if (SubscriptionHelper.cancel(this.upstream)) {
                    this.parent.close(this);
                }
            }

            @Override // org.reactivestreams.Subscriber
            public void onError(Throwable th) {
                if (isDisposed()) {
                    RxJavaPlugins.onError(th);
                } else {
                    this.parent.closeError(th);
                }
            }

            @Override // org.reactivestreams.Subscriber
            public void onComplete() {
                this.parent.close(this);
            }

            @Override // io.reactivex.rxjava3.disposables.Disposable
            public void dispose() {
                SubscriptionHelper.cancel(this.upstream);
            }

            @Override // io.reactivex.rxjava3.disposables.Disposable
            public boolean isDisposed() {
                return this.upstream.get() == SubscriptionHelper.CANCELLED;
            }

            @Override // io.reactivex.rxjava3.core.Flowable
            public void subscribeActual(Subscriber<? super T> subscriber) {
                this.window.subscribe(subscriber);
                this.once.set(true);
            }

            boolean tryAbandon() {
                return !this.once.get() && this.once.compareAndSet(false, true);
            }
        }
    }
}
