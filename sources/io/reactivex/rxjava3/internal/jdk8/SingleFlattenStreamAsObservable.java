package io.reactivex.rxjava3.internal.jdk8;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.internal.jdk8.MaybeFlattenStreamAsObservable;
import java.util.stream.Stream;

/* loaded from: classes.dex */
public final class SingleFlattenStreamAsObservable<T, R> extends Observable<R> {
    final Function<? super T, ? extends Stream<? extends R>> mapper;
    final Single<T> source;

    public SingleFlattenStreamAsObservable(Single<T> single, Function<? super T, ? extends Stream<? extends R>> function) {
        this.source = single;
        this.mapper = function;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public void subscribeActual(Observer<? super R> observer) {
        this.source.subscribe(new MaybeFlattenStreamAsObservable.FlattenStreamMultiObserver(observer, this.mapper));
    }
}
