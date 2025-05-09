package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Emitter;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.BiConsumer;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.internal.functions.Functions;
import io.reactivex.rxjava3.observables.ConnectableObservable;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public final class ObservableInternalHelper {
    private ObservableInternalHelper() {
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
    static final class ItemDelayFunction<T, U> implements Function<T, ObservableSource<T>> {
        final Function<? super T, ? extends ObservableSource<U>> itemDelay;

        /* JADX WARN: Multi-variable type inference failed */
        @Override // io.reactivex.rxjava3.functions.Function
        public /* bridge */ /* synthetic */ Object apply(Object obj) throws Throwable {
            return apply((ItemDelayFunction<T, U>) obj);
        }

        ItemDelayFunction(Function<? super T, ? extends ObservableSource<U>> function) {
            this.itemDelay = function;
        }

        @Override // io.reactivex.rxjava3.functions.Function
        public ObservableSource<T> apply(T t) throws Throwable {
            return new ObservableTake((ObservableSource) Objects.requireNonNull(this.itemDelay.apply(t), "The itemDelay returned a null ObservableSource"), 1L).map(Functions.justFunction(t)).defaultIfEmpty(t);
        }
    }

    public static <T, U> Function<T, ObservableSource<T>> itemDelay(Function<? super T, ? extends ObservableSource<U>> function) {
        return new ItemDelayFunction(function);
    }

    /* loaded from: classes10.dex */
    static final class ObserverOnNext<T> implements Consumer<T> {
        final Observer<T> observer;

        ObserverOnNext(Observer<T> observer) {
            this.observer = observer;
        }

        @Override // io.reactivex.rxjava3.functions.Consumer
        public void accept(T t) {
            this.observer.onNext(t);
        }
    }

    /* loaded from: classes10.dex */
    static final class ObserverOnError<T> implements Consumer<Throwable> {
        final Observer<T> observer;

        ObserverOnError(Observer<T> observer) {
            this.observer = observer;
        }

        @Override // io.reactivex.rxjava3.functions.Consumer
        public void accept(Throwable th) {
            this.observer.onError(th);
        }
    }

    /* loaded from: classes10.dex */
    static final class ObserverOnComplete<T> implements Action {
        final Observer<T> observer;

        ObserverOnComplete(Observer<T> observer) {
            this.observer = observer;
        }

        @Override // io.reactivex.rxjava3.functions.Action
        public void run() {
            this.observer.onComplete();
        }
    }

    public static <T> Consumer<T> observerOnNext(Observer<T> observer) {
        return new ObserverOnNext(observer);
    }

    public static <T> Consumer<Throwable> observerOnError(Observer<T> observer) {
        return new ObserverOnError(observer);
    }

    public static <T> Action observerOnComplete(Observer<T> observer) {
        return new ObserverOnComplete(observer);
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
    static final class FlatMapWithCombinerOuter<T, R, U> implements Function<T, ObservableSource<R>> {
        private final BiFunction<? super T, ? super U, ? extends R> combiner;
        private final Function<? super T, ? extends ObservableSource<? extends U>> mapper;

        /* JADX WARN: Multi-variable type inference failed */
        @Override // io.reactivex.rxjava3.functions.Function
        public /* bridge */ /* synthetic */ Object apply(Object obj) throws Throwable {
            return apply((FlatMapWithCombinerOuter<T, R, U>) obj);
        }

        FlatMapWithCombinerOuter(BiFunction<? super T, ? super U, ? extends R> biFunction, Function<? super T, ? extends ObservableSource<? extends U>> function) {
            this.combiner = biFunction;
            this.mapper = function;
        }

        @Override // io.reactivex.rxjava3.functions.Function
        public ObservableSource<R> apply(T t) throws Throwable {
            return new ObservableMap((ObservableSource) Objects.requireNonNull(this.mapper.apply(t), "The mapper returned a null ObservableSource"), new FlatMapWithCombinerInner(this.combiner, t));
        }
    }

    public static <T, U, R> Function<T, ObservableSource<R>> flatMapWithCombiner(Function<? super T, ? extends ObservableSource<? extends U>> function, BiFunction<? super T, ? super U, ? extends R> biFunction) {
        return new FlatMapWithCombinerOuter(biFunction, function);
    }

    /* loaded from: classes10.dex */
    static final class FlatMapIntoIterable<T, U> implements Function<T, ObservableSource<U>> {
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
        public ObservableSource<U> apply(T t) throws Throwable {
            return new ObservableFromIterable((Iterable) Objects.requireNonNull(this.mapper.apply(t), "The mapper returned a null Iterable"));
        }
    }

    public static <T, U> Function<T, ObservableSource<U>> flatMapIntoIterable(Function<? super T, ? extends Iterable<? extends U>> function) {
        return new FlatMapIntoIterable(function);
    }

    /* loaded from: classes10.dex */
    enum MapToInt implements Function<Object, Object> {
        INSTANCE;

        @Override // io.reactivex.rxjava3.functions.Function
        public Object apply(Object obj) {
            return 0;
        }
    }

    public static <T> Supplier<ConnectableObservable<T>> replaySupplier(Observable<T> observable) {
        return new ReplaySupplier(observable);
    }

    public static <T> Supplier<ConnectableObservable<T>> replaySupplier(Observable<T> observable, int i, boolean z) {
        return new BufferedReplaySupplier(observable, i, z);
    }

    public static <T> Supplier<ConnectableObservable<T>> replaySupplier(Observable<T> observable, int i, long j, TimeUnit timeUnit, Scheduler scheduler, boolean z) {
        return new BufferedTimedReplaySupplier(observable, i, j, timeUnit, scheduler, z);
    }

    public static <T> Supplier<ConnectableObservable<T>> replaySupplier(Observable<T> observable, long j, TimeUnit timeUnit, Scheduler scheduler, boolean z) {
        return new TimedReplayCallable(observable, j, timeUnit, scheduler, z);
    }

    /* loaded from: classes10.dex */
    static final class ReplaySupplier<T> implements Supplier<ConnectableObservable<T>> {
        private final Observable<T> parent;

        ReplaySupplier(Observable<T> observable) {
            this.parent = observable;
        }

        @Override // io.reactivex.rxjava3.functions.Supplier
        public ConnectableObservable<T> get() {
            return this.parent.replay();
        }
    }

    /* loaded from: classes10.dex */
    static final class BufferedReplaySupplier<T> implements Supplier<ConnectableObservable<T>> {
        final int bufferSize;
        final boolean eagerTruncate;
        final Observable<T> parent;

        BufferedReplaySupplier(Observable<T> observable, int i, boolean z) {
            this.parent = observable;
            this.bufferSize = i;
            this.eagerTruncate = z;
        }

        @Override // io.reactivex.rxjava3.functions.Supplier
        public ConnectableObservable<T> get() {
            return this.parent.replay(this.bufferSize, this.eagerTruncate);
        }
    }

    /* loaded from: classes10.dex */
    static final class BufferedTimedReplaySupplier<T> implements Supplier<ConnectableObservable<T>> {
        final int bufferSize;
        final boolean eagerTruncate;
        final Observable<T> parent;
        final Scheduler scheduler;
        final long time;
        final TimeUnit unit;

        BufferedTimedReplaySupplier(Observable<T> observable, int i, long j, TimeUnit timeUnit, Scheduler scheduler, boolean z) {
            this.parent = observable;
            this.bufferSize = i;
            this.time = j;
            this.unit = timeUnit;
            this.scheduler = scheduler;
            this.eagerTruncate = z;
        }

        @Override // io.reactivex.rxjava3.functions.Supplier
        public ConnectableObservable<T> get() {
            return this.parent.replay(this.bufferSize, this.time, this.unit, this.scheduler, this.eagerTruncate);
        }
    }

    /* loaded from: classes10.dex */
    static final class TimedReplayCallable<T> implements Supplier<ConnectableObservable<T>> {
        final boolean eagerTruncate;
        final Observable<T> parent;
        final Scheduler scheduler;
        final long time;
        final TimeUnit unit;

        TimedReplayCallable(Observable<T> observable, long j, TimeUnit timeUnit, Scheduler scheduler, boolean z) {
            this.parent = observable;
            this.time = j;
            this.unit = timeUnit;
            this.scheduler = scheduler;
            this.eagerTruncate = z;
        }

        @Override // io.reactivex.rxjava3.functions.Supplier
        public ConnectableObservable<T> get() {
            return this.parent.replay(this.time, this.unit, this.scheduler, this.eagerTruncate);
        }
    }
}
