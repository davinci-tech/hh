package io.reactivex.rxjava3.internal.operators.parallel;

import io.reactivex.rxjava3.parallel.ParallelFlowable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

/* loaded from: classes.dex */
public final class ParallelFromArray<T> extends ParallelFlowable<T> {
    final Publisher<T>[] sources;

    public ParallelFromArray(Publisher<T>[] publisherArr) {
        this.sources = publisherArr;
    }

    @Override // io.reactivex.rxjava3.parallel.ParallelFlowable
    public int parallelism() {
        return this.sources.length;
    }

    @Override // io.reactivex.rxjava3.parallel.ParallelFlowable
    public void subscribe(Subscriber<? super T>[] subscriberArr) {
        Subscriber<? super T>[] onSubscribe = RxJavaPlugins.onSubscribe(this, subscriberArr);
        if (validate(onSubscribe)) {
            int length = onSubscribe.length;
            for (int i = 0; i < length; i++) {
                this.sources[i].subscribe(onSubscribe[i]);
            }
        }
    }
}
