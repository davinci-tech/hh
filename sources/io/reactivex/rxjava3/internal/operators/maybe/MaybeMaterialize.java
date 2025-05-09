package io.reactivex.rxjava3.internal.operators.maybe;

import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Notification;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.internal.operators.mixed.MaterializeSingleObserver;

/* loaded from: classes.dex */
public final class MaybeMaterialize<T> extends Single<Notification<T>> {
    final Maybe<T> source;

    public MaybeMaterialize(Maybe<T> maybe) {
        this.source = maybe;
    }

    @Override // io.reactivex.rxjava3.core.Single
    public void subscribeActual(SingleObserver<? super Notification<T>> singleObserver) {
        this.source.subscribe(new MaterializeSingleObserver(singleObserver));
    }
}
