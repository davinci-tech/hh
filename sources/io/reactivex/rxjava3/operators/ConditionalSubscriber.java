package io.reactivex.rxjava3.operators;

import io.reactivex.rxjava3.core.FlowableSubscriber;

/* loaded from: classes10.dex */
public interface ConditionalSubscriber<T> extends FlowableSubscriber<T> {
    boolean tryOnNext(T t);
}
