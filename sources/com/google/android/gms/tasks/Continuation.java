package com.google.android.gms.tasks;

/* loaded from: classes8.dex */
public interface Continuation<TResult, TContinuationResult> {
    TContinuationResult then(Task<TResult> task) throws Exception;
}
