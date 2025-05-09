package io.reactivex.rxjava3.internal.operators.parallel;

import io.reactivex.rxjava3.exceptions.CompositeException;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.operators.ConditionalSubscriber;
import io.reactivex.rxjava3.parallel.ParallelFailureHandling;
import io.reactivex.rxjava3.parallel.ParallelFlowable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* loaded from: classes.dex */
public final class ParallelMapTry<T, R> extends ParallelFlowable<R> {
    final BiFunction<? super Long, ? super Throwable, ParallelFailureHandling> errorHandler;
    final Function<? super T, ? extends R> mapper;
    final ParallelFlowable<T> source;

    public ParallelMapTry(ParallelFlowable<T> parallelFlowable, Function<? super T, ? extends R> function, BiFunction<? super Long, ? super Throwable, ParallelFailureHandling> biFunction) {
        this.source = parallelFlowable;
        this.mapper = function;
        this.errorHandler = biFunction;
    }

    @Override // io.reactivex.rxjava3.parallel.ParallelFlowable
    public void subscribe(Subscriber<? super R>[] subscriberArr) {
        Subscriber<?>[] onSubscribe = RxJavaPlugins.onSubscribe(this, subscriberArr);
        if (validate(onSubscribe)) {
            int length = onSubscribe.length;
            Subscriber<? super T>[] subscriberArr2 = new Subscriber[length];
            for (int i = 0; i < length; i++) {
                Subscriber<?> subscriber = onSubscribe[i];
                if (subscriber instanceof ConditionalSubscriber) {
                    subscriberArr2[i] = new ParallelMapTryConditionalSubscriber((ConditionalSubscriber) subscriber, this.mapper, this.errorHandler);
                } else {
                    subscriberArr2[i] = new ParallelMapTrySubscriber(subscriber, this.mapper, this.errorHandler);
                }
            }
            this.source.subscribe(subscriberArr2);
        }
    }

    @Override // io.reactivex.rxjava3.parallel.ParallelFlowable
    public int parallelism() {
        return this.source.parallelism();
    }

    /* loaded from: classes10.dex */
    static final class ParallelMapTrySubscriber<T, R> implements ConditionalSubscriber<T>, Subscription {
        boolean done;
        final Subscriber<? super R> downstream;
        final BiFunction<? super Long, ? super Throwable, ParallelFailureHandling> errorHandler;
        final Function<? super T, ? extends R> mapper;
        Subscription upstream;

        ParallelMapTrySubscriber(Subscriber<? super R> subscriber, Function<? super T, ? extends R> function, BiFunction<? super Long, ? super Throwable, ParallelFailureHandling> biFunction) {
            this.downstream = subscriber;
            this.mapper = function;
            this.errorHandler = biFunction;
        }

        @Override // org.reactivestreams.Subscription
        public void request(long j) {
            this.upstream.request(j);
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            this.upstream.cancel();
        }

        @Override // io.reactivex.rxjava3.core.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.upstream, subscription)) {
                this.upstream = subscription;
                this.downstream.onSubscribe(this);
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            if (tryOnNext(t) || this.done) {
                return;
            }
            this.upstream.request(1L);
        }

        @Override // io.reactivex.rxjava3.operators.ConditionalSubscriber
        public boolean tryOnNext(T t) {
            int i;
            if (this.done) {
                return false;
            }
            long j = 0;
            do {
                try {
                    this.downstream.onNext(Objects.requireNonNull(this.mapper.apply(t), "The mapper returned a null value"));
                    return true;
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    try {
                        j++;
                        i = AnonymousClass1.$SwitchMap$io$reactivex$rxjava3$parallel$ParallelFailureHandling[((ParallelFailureHandling) Objects.requireNonNull(this.errorHandler.apply(Long.valueOf(j), th), "The errorHandler returned a null ParallelFailureHandling")).ordinal()];
                    } catch (Throwable th2) {
                        Exceptions.throwIfFatal(th2);
                        cancel();
                        onError(new CompositeException(th, th2));
                        return false;
                    }
                }
            } while (i == 1);
            if (i != 2) {
                if (i == 3) {
                    cancel();
                    onComplete();
                } else {
                    cancel();
                    onError(th);
                    return false;
                }
            }
            return false;
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.onError(th);
            } else {
                this.done = true;
                this.downstream.onError(th);
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            if (this.done) {
                return;
            }
            this.done = true;
            this.downstream.onComplete();
        }
    }

    /* renamed from: io.reactivex.rxjava3.internal.operators.parallel.ParallelMapTry$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$io$reactivex$rxjava3$parallel$ParallelFailureHandling;

        static {
            int[] iArr = new int[ParallelFailureHandling.values().length];
            $SwitchMap$io$reactivex$rxjava3$parallel$ParallelFailureHandling = iArr;
            try {
                iArr[ParallelFailureHandling.RETRY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$reactivex$rxjava3$parallel$ParallelFailureHandling[ParallelFailureHandling.SKIP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$reactivex$rxjava3$parallel$ParallelFailureHandling[ParallelFailureHandling.STOP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* loaded from: classes10.dex */
    static final class ParallelMapTryConditionalSubscriber<T, R> implements ConditionalSubscriber<T>, Subscription {
        boolean done;
        final ConditionalSubscriber<? super R> downstream;
        final BiFunction<? super Long, ? super Throwable, ParallelFailureHandling> errorHandler;
        final Function<? super T, ? extends R> mapper;
        Subscription upstream;

        ParallelMapTryConditionalSubscriber(ConditionalSubscriber<? super R> conditionalSubscriber, Function<? super T, ? extends R> function, BiFunction<? super Long, ? super Throwable, ParallelFailureHandling> biFunction) {
            this.downstream = conditionalSubscriber;
            this.mapper = function;
            this.errorHandler = biFunction;
        }

        @Override // org.reactivestreams.Subscription
        public void request(long j) {
            this.upstream.request(j);
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            this.upstream.cancel();
        }

        @Override // io.reactivex.rxjava3.core.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.upstream, subscription)) {
                this.upstream = subscription;
                this.downstream.onSubscribe(this);
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            if (tryOnNext(t) || this.done) {
                return;
            }
            this.upstream.request(1L);
        }

        @Override // io.reactivex.rxjava3.operators.ConditionalSubscriber
        public boolean tryOnNext(T t) {
            int i;
            if (this.done) {
                return false;
            }
            long j = 0;
            do {
                try {
                    return this.downstream.tryOnNext(Objects.requireNonNull(this.mapper.apply(t), "The mapper returned a null value"));
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    try {
                        j++;
                        i = AnonymousClass1.$SwitchMap$io$reactivex$rxjava3$parallel$ParallelFailureHandling[((ParallelFailureHandling) Objects.requireNonNull(this.errorHandler.apply(Long.valueOf(j), th), "The errorHandler returned a null ParallelFailureHandling")).ordinal()];
                    } catch (Throwable th2) {
                        Exceptions.throwIfFatal(th2);
                        cancel();
                        onError(new CompositeException(th, th2));
                        return false;
                    }
                }
            } while (i == 1);
            if (i != 2) {
                if (i == 3) {
                    cancel();
                    onComplete();
                } else {
                    cancel();
                    onError(th);
                    return false;
                }
            }
            return false;
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.onError(th);
            } else {
                this.done = true;
                this.downstream.onError(th);
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            if (this.done) {
                return;
            }
            this.done = true;
            this.downstream.onComplete();
        }
    }
}
