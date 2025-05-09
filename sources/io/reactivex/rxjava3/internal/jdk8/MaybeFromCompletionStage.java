package io.reactivex.rxjava3.internal.jdk8;

import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.jdk8.FlowableFromCompletionStage;
import java.util.concurrent.CompletionStage;
import java.util.function.BiConsumer;

/* loaded from: classes.dex */
public final class MaybeFromCompletionStage<T> extends Maybe<T> {
    final CompletionStage<T> stage;

    public MaybeFromCompletionStage(CompletionStage<T> completionStage) {
        this.stage = completionStage;
    }

    @Override // io.reactivex.rxjava3.core.Maybe
    public void subscribeActual(MaybeObserver<? super T> maybeObserver) {
        FlowableFromCompletionStage.BiConsumerAtomicReference biConsumerAtomicReference = new FlowableFromCompletionStage.BiConsumerAtomicReference();
        CompletionStageHandler completionStageHandler = new CompletionStageHandler(maybeObserver, biConsumerAtomicReference);
        biConsumerAtomicReference.lazySet(completionStageHandler);
        maybeObserver.onSubscribe(completionStageHandler);
        this.stage.whenComplete(biConsumerAtomicReference);
    }

    /* loaded from: classes10.dex */
    static final class CompletionStageHandler<T> implements Disposable, BiConsumer<T, Throwable> {
        final MaybeObserver<? super T> downstream;
        final FlowableFromCompletionStage.BiConsumerAtomicReference<T> whenReference;

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.function.BiConsumer
        public /* bridge */ /* synthetic */ void accept(Object obj, Throwable th) {
            accept2((CompletionStageHandler<T>) obj, th);
        }

        CompletionStageHandler(MaybeObserver<? super T> maybeObserver, FlowableFromCompletionStage.BiConsumerAtomicReference<T> biConsumerAtomicReference) {
            this.downstream = maybeObserver;
            this.whenReference = biConsumerAtomicReference;
        }

        /* renamed from: accept, reason: avoid collision after fix types in other method */
        public void accept2(T t, Throwable th) {
            if (th != null) {
                this.downstream.onError(th);
            } else if (t != null) {
                this.downstream.onSuccess(t);
            } else {
                this.downstream.onComplete();
            }
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            this.whenReference.set(null);
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return this.whenReference.get() == null;
        }
    }
}
