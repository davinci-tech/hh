package io.reactivex.rxjava3.internal.fuseable;

import io.reactivex.rxjava3.core.Flowable;

/* loaded from: classes.dex */
public interface FuseToFlowable<T> {
    Flowable<T> fuseToFlowable();
}
