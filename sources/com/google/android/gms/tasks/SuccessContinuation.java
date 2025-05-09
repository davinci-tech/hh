package com.google.android.gms.tasks;

/* loaded from: classes8.dex */
public interface SuccessContinuation<TResult, TContinuationResult> {
    Task<TContinuationResult> then(TResult tresult) throws Exception;
}
