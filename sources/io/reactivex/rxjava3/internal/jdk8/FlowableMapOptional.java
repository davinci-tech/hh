package io.reactivex.rxjava3.internal.jdk8;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.internal.subscribers.BasicFuseableConditionalSubscriber;
import io.reactivex.rxjava3.internal.subscribers.BasicFuseableSubscriber;
import io.reactivex.rxjava3.operators.ConditionalSubscriber;
import java.util.Objects;
import java.util.Optional;
import org.reactivestreams.Subscriber;

/* loaded from: classes.dex */
public final class FlowableMapOptional<T, R> extends Flowable<R> {
    final Function<? super T, Optional<? extends R>> mapper;
    final Flowable<T> source;

    public FlowableMapOptional(Flowable<T> flowable, Function<? super T, Optional<? extends R>> function) {
        this.source = flowable;
        this.mapper = function;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public void subscribeActual(Subscriber<? super R> subscriber) {
        if (subscriber instanceof ConditionalSubscriber) {
            this.source.subscribe((FlowableSubscriber) new MapOptionalConditionalSubscriber((ConditionalSubscriber) subscriber, this.mapper));
        } else {
            this.source.subscribe((FlowableSubscriber) new MapOptionalSubscriber(subscriber, this.mapper));
        }
    }

    /* loaded from: classes10.dex */
    static final class MapOptionalSubscriber<T, R> extends BasicFuseableSubscriber<T, R> implements ConditionalSubscriber<T> {
        final Function<? super T, Optional<? extends R>> mapper;

        MapOptionalSubscriber(Subscriber<? super R> subscriber, Function<? super T, Optional<? extends R>> function) {
            super(subscriber);
            this.mapper = function;
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            if (tryOnNext(t)) {
                return;
            }
            this.upstream.request(1L);
        }

        @Override // io.reactivex.rxjava3.operators.ConditionalSubscriber
        public boolean tryOnNext(T t) {
            if (this.done) {
                return true;
            }
            if (this.sourceMode != 0) {
                this.downstream.onNext(null);
                return true;
            }
            try {
                Optional optional = (Optional) Objects.requireNonNull(this.mapper.apply(t), "The mapper returned a null Optional");
                if (!optional.isPresent()) {
                    return false;
                }
                this.downstream.onNext((Object) optional.get());
                return true;
            } catch (Throwable th) {
                fail(th);
                return true;
            }
        }

        @Override // io.reactivex.rxjava3.operators.QueueFuseable
        public int requestFusion(int i) {
            return transitiveBoundaryFusion(i);
        }

        @Override // io.reactivex.rxjava3.operators.SimpleQueue
        public R poll() throws Throwable {
            while (true) {
                T poll = this.qs.poll();
                if (poll == null) {
                    return null;
                }
                Optional optional = (Optional) Objects.requireNonNull(this.mapper.apply(poll), "The mapper returned a null Optional");
                if (optional.isPresent()) {
                    return (R) optional.get();
                }
                if (this.sourceMode == 2) {
                    this.qs.request(1L);
                }
            }
        }
    }

    /* loaded from: classes10.dex */
    static final class MapOptionalConditionalSubscriber<T, R> extends BasicFuseableConditionalSubscriber<T, R> {
        final Function<? super T, Optional<? extends R>> mapper;

        MapOptionalConditionalSubscriber(ConditionalSubscriber<? super R> conditionalSubscriber, Function<? super T, Optional<? extends R>> function) {
            super(conditionalSubscriber);
            this.mapper = function;
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            if (tryOnNext(t)) {
                return;
            }
            this.upstream.request(1L);
        }

        @Override // io.reactivex.rxjava3.operators.ConditionalSubscriber
        public boolean tryOnNext(T t) {
            if (this.done) {
                return true;
            }
            if (this.sourceMode != 0) {
                this.downstream.onNext(null);
                return true;
            }
            try {
                Optional optional = (Optional) Objects.requireNonNull(this.mapper.apply(t), "The mapper returned a null Optional");
                if (optional.isPresent()) {
                    return this.downstream.tryOnNext((Object) optional.get());
                }
                return false;
            } catch (Throwable th) {
                fail(th);
                return true;
            }
        }

        @Override // io.reactivex.rxjava3.operators.QueueFuseable
        public int requestFusion(int i) {
            return transitiveBoundaryFusion(i);
        }

        @Override // io.reactivex.rxjava3.operators.SimpleQueue
        public R poll() throws Throwable {
            while (true) {
                T poll = this.qs.poll();
                if (poll == null) {
                    return null;
                }
                Optional optional = (Optional) Objects.requireNonNull(this.mapper.apply(poll), "The mapper returned a null Optional");
                if (optional.isPresent()) {
                    return (R) optional.get();
                }
                if (this.sourceMode == 2) {
                    this.qs.request(1L);
                }
            }
        }
    }
}
