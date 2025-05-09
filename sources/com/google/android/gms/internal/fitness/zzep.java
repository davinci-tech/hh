package com.google.android.gms.internal.fitness;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes8.dex */
final class zzep implements BaseImplementation.ResultHolder<Status> {
    private final /* synthetic */ TaskCompletionSource zzgc;

    zzep(TaskCompletionSource taskCompletionSource) {
        this.zzgc = taskCompletionSource;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder
    public final void setFailedResult(Status status) {
        throw new UnsupportedOperationException("This method should never get invoked");
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder
    public final /* synthetic */ void setResult(Status status) {
        Status status2 = status;
        TaskUtil.setResultOrApiException(status2, Boolean.valueOf(status2.isSuccess()), this.zzgc);
    }
}
