package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.internal.operators.maybe.MaybeOnErrorComplete;

/* loaded from: classes.dex */
public final class SingleOnErrorComplete<T> extends Maybe<T> {
    final Predicate<? super Throwable> predicate;
    final Single<T> source;

    public SingleOnErrorComplete(Single<T> single, Predicate<? super Throwable> predicate) {
        this.source = single;
        this.predicate = predicate;
    }

    @Override // io.reactivex.rxjava3.core.Maybe
    public void subscribeActual(MaybeObserver<? super T> maybeObserver) {
        this.source.subscribe(new MaybeOnErrorComplete.OnErrorCompleteMultiObserver(maybeObserver, this.predicate));
    }
}
