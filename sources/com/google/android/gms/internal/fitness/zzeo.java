package com.google.android.gms.internal.fitness;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes8.dex */
final class zzeo implements BaseImplementation.ResultHolder<Status> {
    private final /* synthetic */ TaskCompletionSource zzgc;

    zzeo(TaskCompletionSource taskCompletionSource) {
        this.zzgc = taskCompletionSource;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder
    public final void setFailedResult(Status status) {
        throw new UnsupportedOperationException("This method should never get invoked");
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder
    public final /* synthetic */ void setResult(Status status) {
        TaskUtil.setResultOrApiException(status, this.zzgc);
    }
}
