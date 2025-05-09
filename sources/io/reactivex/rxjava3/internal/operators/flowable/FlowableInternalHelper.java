package io.reactivex.rxjava3.internal.operators.flowable;

import androidx.core.location.LocationRequestCompat;
import io.reactivex.rxjava3.core.Emitter;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.flowables.ConnectableFlowable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.BiConsumer;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.internal.functions.Functions;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* loaded from: classes.dex */
public final class FlowableInternalHelper {
    private FlowableInternalHelper() {
        throw new IllegalStateException("No instances!");
    }

    /* loaded from: classes10.dex */
    static final class SimpleGenerator<T, S> implements BiFunction<S, Emitter<T>, S> {
        final Consumer<Emitter<T>> consumer;

        /* JADX WARN: Multi-variable type inference failed */
        @Override // io.reactivex.rxjava3.functions.BiFunction
        public /* bridge */ /* synthetic */ Object apply(Object obj, Object obj2) throws Throwable {
            return apply((SimpleGenerator<T, S>) obj, (Emitter) obj2);
        }

        SimpleGenerator(Consumer<Emitter<T>> consumer) {
            this.consumer = consumer;
        }

        public S apply(S s, Emitter<T> emitter) throws Throwable {
            this.consumer.accept(emitter);
            return s;
        }
    }

    public static <T, S> BiFunction<S, Emitter<T>, S> simpleGenerator(Consumer<Emitter<T>> consumer) {
        return new SimpleGenerator(consumer);
    }

    /* loaded from: classes10.dex */
    static final class SimpleBiGenerator<T, S> implements BiFunction<S, Emitter<T>, S> {
        final BiConsumer<S, Emitter<T>> consumer;

        /* JADX WARN: Multi-variable type inference failed */
        @Override // io.reactivex.rxjava3.functions.BiFunction
        public /* bridge */ /* synthetic */ Object apply(Object obj, Object obj2) throws Throwable {
            return apply((SimpleBiGenerator<T, S>) obj, (Emitter) obj2);
        }

        SimpleBiGenerator(BiConsumer<S, Emitter<T>> biConsumer) {
            this.consumer = biConsumer;
        }

        public S apply(S s, Emitter<T> emitter) throws Throwable {
            this.consumer.accept(s, emitter);
            return s;
        }
    }

    public static <T, S> BiFunction<S, Emitter<T>, S> simpleBiGenerator(BiConsumer<S, Emitter<T>> biConsumer) {
        return new SimpleBiGenerator(biConsumer);
    }

    /* loaded from: classes10.dex */
    static final class ItemDelayFunction<T, U> implements Function<T, Publisher<T>> {
        final Function<? super T, ? extends Publisher<U>> itemDelay;

        /* JADX WARN: Multi-variable type inference failed */
        @Override // io.reactivex.rxjava3.functions.Function
        public /* bridge */ /* synthetic */ Object apply(Object obj) throws Throwable {
            return apply((ItemDelayFunction<T, U>) obj);
        }

        ItemDelayFunction(Function<? super T, ? extends Publisher<U>> function) {
            this.itemDelay = function;
        }

        @Override // io.reactivex.rxjava3.functions.Function
        public Publisher<T> apply(T t) throws Throwable {
            return new FlowableTakePublisher((Publisher) Objects.requireNonNull(this.itemDelay.apply(t), "The itemDelay returned a null Publisher"), 1L).map(Functions.justFunction(t)).defaultIfEmpty(t);
        }
    }

    public static <T, U> Function<T, Publisher<T>> itemDelay(Function<? super T, ? extends Publisher<U>> function) {
        return new ItemDelayFunction(function);
    }

    /* loaded from: classes10.dex */
    static final class SubscriberOnNext<T> implements Consumer<T> {
        final Subscriber<T> subscriber;

        SubscriberOnNext(Subscriber<T> subscriber) {
            this.subscriber = subscriber;
        }

        @Override // io.reactivex.rxjava3.functions.Consumer
        public void accept(T t) {
            this.subscriber.onNext(t);
        }
    }

    /* loaded from: classes10.dex */
    static final class SubscriberOnError<T> implements Consumer<Throwable> {
        final Subscriber<T> subscriber;

        SubscriberOnError(Subscriber<T> subscriber) {
            this.subscriber = subscriber;
        }

        @Override // io.reactivex.rxjava3.functions.Consumer
        public void accept(Throwable th) {
            this.subscriber.onError(th);
        }
    }

    /* loaded from: classes10.dex */
    static final class SubscriberOnComplete<T> implements Action {
        final Subscriber<T> subscriber;

        SubscriberOnComplete(Subscriber<T> subscriber) {
            this.subscriber = subscriber;
        }

        @Override // io.reactivex.rxjava3.functions.Action
        public void run() {
            this.subscriber.onComplete();
        }
    }

    public static <T> Consumer<T> subscriberOnNext(Subscriber<T> subscriber) {
        return new SubscriberOnNext(subscriber);
    }

    public static <T> Consumer<Throwable> subscriberOnError(Subscriber<T> subscriber) {
        return new SubscriberOnError(subscriber);
    }

    public static <T> Action subscriberOnComplete(Subscriber<T> subscriber) {
        return new SubscriberOnComplete(subscriber);
    }

    /* loaded from: classes10.dex */
    static final class FlatMapWithCombinerInner<U, R, T> implements Function<U, R> {
        private final BiFunction<? super T, ? super U, ? extends R> combiner;
        private final T t;

        FlatMapWithCombinerInner(BiFunction<? super T, ? super U, ? extends R> biFunction, T t) {
            this.combiner = biFunction;
            this.t = t;
        }

        @Override // io.reactivex.rxjava3.functions.Function
        public R apply(U u) throws Throwable {
            return this.combiner.apply(this.t, u);
        }
    }

    /* loaded from: classes10.dex */
    static final class FlatMapWithCombinerOuter<T, R, U> implements Function<T, Publisher<R>> {
        private final BiFunction<? super T, ? super U, ? extends R> combiner;
        private final Function<? super T, ? extends Publisher<? extends U>> mapper;

        /* JADX WARN: Multi-variable type inference failed */
        @Override // io.reactivex.rxjava3.functions.Function
        public /* bridge */ /* synthetic */ Object apply(Object obj) throws Throwable {
            return apply((FlatMapWithCombinerOuter<T, R, U>) obj);
        }

        FlatMapWithCombinerOuter(BiFunction<? super T, ? super U, ? extends R> biFunction, Function<? super T, ? extends Publisher<? extends U>> function) {
            this.combiner = biFunction;
            this.mapper = function;
        }

        @Override // io.reactivex.rxjava3.functions.Function
        public Publisher<R> apply(T t) throws Throwable {
            return new FlowableMapPublisher((Publisher) Objects.requireNonNull(this.mapper.apply(t), "The mapper returned a null Publisher"), new FlatMapWithCombinerInner(this.combiner, t));
        }
    }

    public static <T, U, R> Function<T, Publisher<R>> flatMapWithCombiner(Function<? super T, ? extends Publisher<? extends U>> function, BiFunction<? super T, ? super U, ? extends R> biFunction) {
        return new FlatMapWithCombinerOuter(biFunction, function);
    }

    /* loaded from: classes10.dex */
    static final class FlatMapIntoIterable<T, U> implements Function<T, Publisher<U>> {
        private final Function<? super T, ? extends Iterable<? extends U>> mapper;

        /* JADX WARN: Multi-variable type inference failed */
        @Override // io.reactivex.rxjava3.functions.Function
        public /* bridge */ /* synthetic */ Object apply(Object obj) throws Throwable {
            return apply((FlatMapIntoIterable<T, U>) obj);
        }

        FlatMapIntoIterable(Function<? super T, ? extends Iterable<? extends U>> function) {
            this.mapper = function;
        }

        @Override // io.reactivex.rxjava3.functions.Function
        public Publisher<U> apply(T t) throws Throwable {
            return new FlowableFromIterable((Iterable) Objects.requireNonNull(this.mapper.apply(t), "The mapper returned a null Iterable"));
        }
    }

    public static <T, U> Function<T, Publisher<U>> flatMapIntoIterable(Function<? super T, ? extends Iterable<? extends U>> function) {
        return new FlatMapIntoIterable(function);
    }

    public static <T> Supplier<ConnectableFlowable<T>> replaySupplier(Flowable<T> flowable) {
        return new ReplaySupplier(flowable);
    }

    public static <T> Supplier<ConnectableFlowable<T>> replaySupplier(Flowable<T> flowable, int i, boolean z) {
        return new BufferedReplaySupplier(flowable, i, z);
    }

    public static <T> Supplier<ConnectableFlowable<T>> replaySupplier(Flowable<T> flowable, int i, long j, TimeUnit timeUnit, Scheduler scheduler, boolean z) {
        return new BufferedTimedReplay(flowable, i, j, timeUnit, scheduler, z);
    }

    public static <T> Supplier<ConnectableFlowable<T>> replaySupplier(Flowable<T> flowable, long j, TimeUnit timeUnit, Scheduler scheduler, boolean z) {
        return new TimedReplay(flowable, j, timeUnit, scheduler, z);
    }

    public enum RequestMax implements Consumer<Subscription> {
        INSTANCE;

        @Override // io.reactivex.rxjava3.functions.Consumer
        public void accept(Subscription subscription) {
            subscription.request(LocationRequestCompat.PASSIVE_INTERVAL);
        }
    }

    /* loaded from: classes10.dex */
    static final class ReplaySupplier<T> implements Supplier<ConnectableFlowable<T>> {
        final Flowable<T> parent;

        ReplaySupplier(Flowable<T> flowable) {
            this.parent = flowable;
        }

        @Override // io.reactivex.rxjava3.functions.Supplier
        public ConnectableFlowable<T> get() {
            return this.parent.replay();
        }
    }

    /* loaded from: classes10.dex */
    static final class BufferedReplaySupplier<T> implements Supplier<ConnectableFlowable<T>> {
        final int bufferSize;
        final boolean eagerTruncate;
        final Flowable<T> parent;

        BufferedReplaySupplier(Flowable<T> flowable, int i, boolean z) {
            this.parent = flowable;
            this.bufferSize = i;
            this.eagerTruncate = z;
        }

        @Override // io.reactivex.rxjava3.functions.Supplier
        public ConnectableFlowable<T> get() {
            return this.parent.replay(this.bufferSize, this.eagerTruncate);
        }
    }

    /* loaded from: classes10.dex */
    static final class BufferedTimedReplay<T> implements Supplier<ConnectableFlowable<T>> {
        final int bufferSize;
        final boolean eagerTruncate;
        final Flowable<T> parent;
        final Scheduler scheduler;
        final long time;
        final TimeUnit unit;

        BufferedTimedReplay(Flowable<T> flowable, int i, long j, TimeUnit timeUnit, Scheduler scheduler, boolean z) {
            this.parent = flowable;
            this.bufferSize = i;
            this.time = j;
            this.unit = timeUnit;
            this.scheduler = scheduler;
            this.eagerTruncate = z;
        }

        @Override // io.reactivex.rxjava3.functions.Supplier
        public ConnectableFlowable<T> get() {
            return this.parent.replay(this.bufferSize, this.time, this.unit, this.scheduler, this.eagerTruncate);
        }
    }

    /* loaded from: classes10.dex */
    static final class TimedReplay<T> implements Supplier<ConnectableFlowable<T>> {
        final boolean eagerTruncate;
        private final Flowable<T> parent;
        private final Scheduler scheduler;
        private final long time;
        private final TimeUnit unit;

        TimedReplay(Flowable<T> flowable, long j, TimeUnit timeUnit, Scheduler scheduler, boolean z) {
            this.parent = flowable;
            this.time = j;
            this.unit = timeUnit;
            this.scheduler = scheduler;
            this.eagerTruncate = z;
        }

        @Override // io.reactivex.rxjava3.functions.Supplier
        public ConnectableFlowable<T> get() {
            return this.parent.replay(this.time, this.unit, this.scheduler, this.eagerTruncate);
        }
    }
}
