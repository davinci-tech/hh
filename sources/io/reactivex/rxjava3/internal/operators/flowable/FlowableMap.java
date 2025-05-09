package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.internal.subscribers.BasicFuseableConditionalSubscriber;
import io.reactivex.rxjava3.internal.subscribers.BasicFuseableSubscriber;
import io.reactivex.rxjava3.operators.ConditionalSubscriber;
import java.util.Objects;
import org.reactivestreams.Subscriber;

/* loaded from: classes.dex */
public final class FlowableMap<T, U> extends AbstractFlowableWithUpstream<T, U> {
    final Function<? super T, ? extends U> mapper;

    public FlowableMap(Flowable<T> flowable, Function<? super T, ? extends U> function) {
        super(flowable);
        this.mapper = function;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public void subscribeActual(Subscriber<? super U> subscriber) {
        if (subscriber instanceof ConditionalSubscriber) {
            this.source.subscribe((FlowableSubscriber) new MapConditionalSubscriber((ConditionalSubscriber) subscriber, this.mapper));
        } else {
            this.source.subscribe((FlowableSubscriber) new MapSubscriber(subscriber, this.mapper));
        }
    }

    /* loaded from: classes10.dex */
    static final class MapSubscriber<T, U> extends BasicFuseableSubscriber<T, U> {
        final Function<? super T, ? extends U> mapper;

        MapSubscriber(Subscriber<? super U> subscriber, Function<? super T, ? extends U> function) {
            super(subscriber);
            this.mapper = function;
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            if (this.done) {
                return;
            }
            if (this.sourceMode != 0) {
                this.downstream.onNext(null);
                return;
            }
            try {
                this.downstream.onNext(Objects.requireNonNull(this.mapper.apply(t), "The mapper function returned a null value."));
            } catch (Throwable th) {
                fail(th);
            }
        }

        @Override // io.reactivex.rxjava3.operators.QueueFuseable
        public int requestFusion(int i) {
            return transitiveBoundaryFusion(i);
        }

        @Override // io.reactivex.rxjava3.operators.SimpleQueue
        public U poll() throws Throwable {
            T poll = this.qs.poll();
            if (poll != null) {
                return (U) Objects.requireNonNull(this.mapper.apply(poll), "The mapper function returned a null value.");
            }
            return null;
        }
    }

    /* loaded from: classes10.dex */
    static final class MapConditionalSubscriber<T, U> extends BasicFuseableConditionalSubscriber<T, U> {
        final Function<? super T, ? extends U> mapper;

        MapConditionalSubscriber(ConditionalSubscriber<? super U> conditionalSubscriber, Function<? super T, ? extends U> function) {
            super(conditionalSubscriber);
            this.mapper = function;
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            if (this.done) {
                return;
            }
            if (this.sourceMode != 0) {
                this.downstream.onNext(null);
                return;
            }
            try {
                this.downstream.onNext(Objects.requireNonNull(this.mapper.apply(t), "The mapper function returned a null value."));
            } catch (Throwable th) {
                fail(th);
            }
        }

        @Override // io.reactivex.rxjava3.operators.ConditionalSubscriber
        public boolean tryOnNext(T t) {
            if (this.done) {
                return true;
            }
            if (this.sourceMode != 0) {
                this.downstream.tryOnNext(null);
                return true;
            }
            try {
                return this.downstream.tryOnNext(Objects.requireNonNull(this.mapper.apply(t), "The mapper function returned a null value."));
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
        public U poll() throws Throwable {
            T poll = this.qs.poll();
            if (poll != null) {
                return (U) Objects.requireNonNull(this.mapper.apply(poll), "The mapper function returned a null value.");
            }
            return null;
        }
    }
}
